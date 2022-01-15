#ifndef SELECTIVE_REPEATER_H
#define SELECTIVE_REPEATER_H

#include <string>
#include <iostream>
#include <vector>
#include "fragment.h"
#include "gremlin.h"
#include "error_detector.h"
#include <stdlib.h> 
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdio.h>
#include <string.h>

#define BUFLEN 512
using namespace std;

void die(const char *s)
{
    perror(s);
    exit(1);
}

class SelectRepeat
{
private:
    vector<string> ackNBuffer;
    vector<fragment*> fragVec;
    vector<fragment*> fragWin;

    int recBase;
    int windowBase;
    int sucReceived;
    int unSucReceived;
    struct sockaddr_in si_other;
    socklen_t slen;
    
    bool lastFragReceived;
    int clientSocket;

    gremlin* gre;
    bool trace;

public:
    SelectRepeat(int& cSock, struct sockaddr_in& si_other, socklen_t& slen, gremlin* gre, bool trace){
        recBase = 0;
        windowBase = 3;
        sucReceived = 0;
        unSucReceived = 0;
        lastFragReceived = false;
        this->gre = gre;
        //acknowledgmentBuffer.length=32
        clientSocket = cSock;
        fragWin.resize(32);
        ackNBuffer.resize(32);
        for(int i=0;i<32;i++){
            fragWin[i]=NULL;
            ackNBuffer[i]="N"+to_string(i);
        }
        
        this->si_other = si_other;
        this->slen = slen;

        this->trace = trace;
    }

    vector<fragment*> get_fragVec(){
        return fragVec;
    }

    void start(){
        
        while(!lastFragReceived){
            if(trace){
                cout<<"Fragment: "<<sucReceived+" Recieved"<<endl;
                cout<<"Fragment: "<<unSucReceived+" Recieved"<<endl;
            }
            cout<<"ack Info:"<<endl;
            for(int i=0;i<(int)ackNBuffer.size();i++){
                cout<<ackNBuffer[i]<<'\t';
            }
	    cout<<endl;

            char buf[BUFLEN];

            memset(buf,'\0', BUFLEN);
            int recv_len;
            // try to receive some data, this is a blocking call
            if ((recv_len=recvfrom(clientSocket, buf, BUFLEN, 0, (struct sockaddr *)&si_other, &slen)) == -1)
            {
                die("recvfrom()");
            }
            string strBuf = buf;
            
            //string strFilter = gre->filter(strBuf);
            string strFilter;
            strFilter.resize(recv_len);
            for(int i=0;i<recv_len;i++){
            	strFilter[i]=buf[i];
            }
            
            fragment* newFrag;

            if(strFilter!=""){
                cout<<"data recvfrom:"<<endl;
                for(int i=0;i<(int)strFilter.length();i++){
                	printf("%d\t",strFilter[i]);
                }
                cout<<endl;
                newFrag = new fragment(strFilter.substr(18,strFilter.length()-18));
                
                (newFrag->get_header())->set_checkSum((unsigned char*)strFilter.substr(0, 16).c_str());
                (newFrag->get_header())->set_seqId(strFilter[16]);
                (newFrag->get_header())->set_endSeq(strFilter[17]);
                cout<<"endSeq:"<<endl;
                printf("%d\n",strFilter[17]);
                int seqId = (newFrag->get_header())->get_seqId();
                cout<<"Seq no :"<<seqId<<endl;
                
                bool behindRcvBase=(seqId>=(recBase-4)%32 &&seqId<=(recBase-1)%32);
                bool inWindow = (seqId>=recBase&&seqId<=windowBase);
                bool inWindowMo = (seqId<=31&&recBase>windowBase);
                bool validFrag = errorDetector::validate_checksum(newFrag);
                cout<<"Fragment Valid:"<<validFrag<<endl;
                //cout<<"test1"<<endl;
                //bool validFrag=true;
                if(validFrag){
                    if(behindRcvBase||inWindow||inWindowMo){
                    	 cout<<"test 11:"<<seqId<<endl;
                        ackNBuffer[seqId]="A"+to_string(seqId);
                        fragWin[seqId]=newFrag;
                        cout<<"test4"<<endl;
                        send_ack();
                        cout<<"test3"<<endl;
                        if(seqId==recBase){
                            inc_window_pos();
                        }
                        sucReceived++;
                    }else{
                    	 cout<<"test5"<<endl;
                        send_ack();
                        unSucReceived++;
                    }
                }
                cout<<"test2"<<endl;
            }
        }
    }

    void send_ack(){
        vector<string> ackVec;
        for(int i=0;i<4;i++){
            ackVec.push_back(ackNBuffer[(recBase+i)%32]);
        }

        //unsigned char* tes = NULL;

        //vector<string> tmpAckVec;
        //tmpAckVec.resize(ackVec.size());
        if(trace){
            for(int i=0;i<(int)ackVec.size();i++){
                cout<<ackVec[i]+" Transmitted"<<endl;
            }
        }
        string strAck="";
        for(int i=0;i<(int)ackVec.size();i++){
            strAck+=ackVec[i]+" ";
        }
        cout<<"sendInfo:"<<endl;
        cout<<strAck<<endl;
        unsigned char* tes = (unsigned char *)strAck.c_str();
        if (sendto(clientSocket, tes,  strAck.length(), 0 , (struct sockaddr *) &si_other, slen)==-1)
        {
            die("sendto()");
        }
    }

    void inc_window_pos(){
        while(ackNBuffer[recBase][0]=='A'){
            lastFragReceived = ((fragWin[recBase]->get_header())->get_endSeq()==1)?true:false;
            if(lastFragReceived){
                cout<<"sf"<<endl;
            }
            fragVec.push_back(fragWin[recBase]);
            recBase=(recBase+1)%32;
            windowBase=(windowBase+1)%32;
            ackNBuffer[windowBase]="N"+to_string(windowBase);
            fragWin[windowBase]=NULL;
        }
    }
};




#endif //SELECTIVE_REPEATER_H
