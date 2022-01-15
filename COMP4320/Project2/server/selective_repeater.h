#ifndef SELECTIVE_REPEATER_H
#define SELECTIVE_REPEATER_H

#include <string>
#include <iostream>
#include <vector>
#include "fragment.h"
//#include "gremlin.h"
#include "sar.h"
#include "event_time.h"
#include <stdlib.h> 
#include <arpa/inet.h>
#include <sys/socket.h>
#include <mutex>
#include "timer.h"
#include <thread>
#include <ctime>
//#include <pthread.h>

#define BUFFER_AMT 128
#define SERVER_PORT_NUM 10030
#define MAX_SEQ_NUM 32

using namespace std;

void die(const char *s)
{
    perror(s);
    exit(1);
}

mutex mtx;


vector<string> split(const string& str, const string& delim) {
	vector<string> res;
        if("" == str) return res;
          
        char * strs = new char[str.length() + 1] ;   
        strcpy(strs, str.c_str());

        char * d = new char[delim.length() + 1];
        strcpy(d, delim.c_str());

        char *p = strtok(strs, d);
        while(p) {
                string s = p;   
                res.push_back(s);   
                p = strtok(NULL, d);
        }

        return res;
}

class SelectRepeat
{
private:
    SAR* sar;
    int sucTransmit;
    int unsucTransmit;
    int sendBase;
    int windowBase;
    int curSeqNum;

    bool CLIENTRECIEVEDALLDATA;
    int lastFragIndex;

    vector<fragment*> fragWindow;
    vector<string> ackBuffer;
    vector<EventTime*> etVec;

    int serverSocket;

    bool trace;
    struct sockaddr_in si_me;
    struct sockaddr_in si_other;
    socklen_t slen;

public:
    SelectRepeat(SAR* sar, int& sSock, sockaddr_in& si_me, sockaddr_in& si_other, socklen_t& slen, bool trace){
        
        CLIENTRECIEVEDALLDATA = false;
        lastFragIndex=-1;
        
        fragWindow.resize(MAX_SEQ_NUM);
        for(int i=0;i<MAX_SEQ_NUM;i++){
            fragWindow[i]=NULL;
        }
        ackBuffer.resize(MAX_SEQ_NUM);
        etVec.resize(4);
        for(int i=0;i<4;i++){
            etVec[i]=NULL;
        }

        sucTransmit = 0;
        unsucTransmit = 0;
        sendBase = 0;
        windowBase = 3;
        curSeqNum = 0;

        this->sar = sar;
        this->trace = trace;
        

        slen = sizeof(si_other);
        this->serverSocket = sSock;
        this->si_me = si_me;
        this->si_other = si_other;
        this->slen = slen;

        
        for(int i=0;i<(int)ackBuffer.size();i++){
            ackBuffer[i]="N"+to_string(i);
        }
        fill_frag_window();
    }
    
   
    void send_fragment(int pos){
    	cout<<"send_fragment"<<endl;
        if(fragWindow[pos]!=NULL){
            string sendPacket = fragWindow[pos]->get_fragment();
            const char* buf = sendPacket.c_str();
            cout<<"before send:"<<buf<<sendPacket<<endl;
            cout<<"endSeq:"<<endl;
            printf("%d\n",(fragWindow[pos]->get_header())->get_endSeq());
            cout<<"seqId:"<<endl;
            printf("%d\n",(fragWindow[pos]->get_header())->get_seqId());
            if (sendto(serverSocket, buf, sendPacket.length(), 0, (struct sockaddr*) &si_other, slen) == -1)
            {
                die("sendto()");
            }
            cout<<"send buf:"<<endl;
            for(int i=0;i<(int)sendPacket.length();i++){
            	printf("%d\t",sendPacket[i]);
            }
            cout<<endl;
            etVec[pos%4] = new EventTime(pos, 30);
        }
    }

    void timerThread(){
    	//time_t now = time(0);
   
   	
        for(int i=0; i<(int)etVec.size(); i++){
			if(etVec[i] !=NULL){
				cout<<"test   "<<i<<endl;
				etVec[i]->dec_ExpireTime();
                		etVec[i]->dec_ExpireTime();
				if(etVec[i]->get_expireTime() <= 0.0){
					cout<<"test yyyyyyyyyy  "<<i<<endl;
					int tempID = etVec[i]->get_eventId();
					etVec[i] = NULL;
					if(ackBuffer[tempID][0]=='N'){
						send_fragment(tempID);
					}
				}
			}
		}//endfor
    }


    void start(){
        
       
	    //   pTimer->AsyncLoop(10, timerThread, this);
	    CppTime::Timer t;
        
	    auto id = t.add(chrono::milliseconds(10), [&](CppTime::timer_id) { 
	    	    mtx.lock();
		    timerThread();
		    mtx.unlock();
	    }, chrono::milliseconds(10));
       
        for(int i=0;i<4;i++){
            cout<<"test index:"<<i<<endl;
            mtx.lock();
            send_fragment(i);
            mtx.unlock();
        }
       
        cout<<"jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"<<endl;
        while(!CLIENTRECIEVEDALLDATA){
            char recvData[BUFFER_AMT];
            memset(recvData, 0, BUFFER_AMT);
            if(trace){
                cout<<"Fragment: "<<sucTransmit<<" Transmitted"<<endl;
                cout<<"Fragment: "<<unsucTransmit<<" Transmitted"<<endl;
            }
            int recv_len;
            
            if ((recv_len = recvfrom(serverSocket, recvData, BUFFER_AMT, 0, (struct sockaddr *) &si_other, &slen)) == -1)
            {
                die("recvfrom()");
            }
            cout<<"test recvData"<<endl;
            for(int i=0;i<BUFFER_AMT;i++){
            	printf("%d\t",recvData[i]);
            }
            cout<<endl;
            string strBuf = recvData;
            vector<string> acks = recieve_ack(strBuf);
            update_server_ack(acks);
            if(ackBuffer[sendBase][0]=='A'){
                inc_window();
            }
            for(int i=0;i<4;i++){
                if(ackBuffer[(sendBase+i)%32][0]=='N'){
                    cout<<"gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"<<endl;
                    mtx.lock();
                    send_fragment((sendBase+i)%32);
                    mtx.unlock();
                    unsucTransmit++;
                }
            }
            if(CLIENTRECIEVEDALLDATA){
                cout <<"Connection is Closing..."<<endl;
            }

        }
        t.remove(id);
        //serverSocket.close();
    }

    void update_server_ack(vector<string> ack){
        int ackSeqId;
        bool isAck =false;
        bool isLastFragAck = false;
        cout<<"update_server_ack"<<endl;
        for(int i=0;i<(int)ack.size();i++){
            cout<<ack[i]<<endl;
        }
        for(int i=0;i<4;i++){
            string strAckSeqId = ack[i].substr(1,ack[i].length()-1);
            ackSeqId = atoi(strAckSeqId.c_str());
            isAck=ack[i][0]=='A'?true:false;
            if(lastFragIndex!=-1){
            	cout<<"lastFragIndex:"<<lastFragIndex<<endl;
            	printf("getEndSeq():%d\n",(fragWindow[lastFragIndex]->get_header())->get_endSeq());
                isLastFragAck = ((fragWindow[lastFragIndex]->get_header())->get_endSeq()==1)?true:false;
            }
            if(isAck){
                sucTransmit++;
                ackBuffer[ackSeqId]=ack[i];
                if(ackSeqId>=sendBase&&ackSeqId<=windowBase){
                    etVec[ackSeqId%4]=NULL;
                }
                if(isLastFragAck){
                    CLIENTRECIEVEDALLDATA=true;
                }
            }
        }
    }

    vector<string> recieve_ack(string ack){
    	cout<<"recieve_ack:  "<<ack<<endl;
        vector<string> res = split(ack," ");
        return res;
    }

    void inc_window(){
        while(ackBuffer[sendBase][0]=='A'){
            sendBase=(sendBase+1)%32;
            windowBase=(windowBase+1)%32;
            ackBuffer[windowBase]="N"+to_string(windowBase);
            if(sar->hash_next()){
                fragWindow[windowBase]=sar->next();
                cout<<"inc_window                                       :ddddddddddddddsdsdf"<<endl;
                bool testForLastFrag=((fragWindow[windowBase]->get_header())->get_endSeq())?true:false;
                printf("%d  bool:%d\n",((fragWindow[windowBase]->get_header())->get_endSeq()), testForLastFrag);
                if(testForLastFrag){
                    lastFragIndex=windowBase;
                }
            }else{
                fragWindow[windowBase]=NULL;
            }
        }
    }

    void fill_frag_window(){
        for(int i=0;i<4;i++){
            if(sar->hash_next()){
                fragWindow[i]=sar->next();
            }else{
            	break;
            }
        }
    }

};




#endif //SELECTIVE_REPEATER_H
