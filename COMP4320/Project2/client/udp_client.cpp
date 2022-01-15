#include <stdlib.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>
#include "gremlin.h"
#include "selective_repeater.h"
#include "sar.h"

#include <iostream>
#include <fstream>
using namespace std;

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

int main(int argc, char* argv[]){
    double probLoss;
    double probDam;
    int trace = 0;
    if(argv==NULL || argc<2){
        cout<< "default probLoss:0.2  probDamaged:0.4"<<endl;
        cout<<"default trace is false"<<endl;
        probLoss=0.2;
        probDam=0.4;
    }else{
        probLoss=atof(argv[1]);
        probDam=atof(argv[2]);
        if(probLoss+probDam>1.0){
            cout<<"probLoss + probDamaged must less than 1"<<endl;
            probLoss=0.2;
            probDam=0.4;
        }
        cout<<"probLoss:"<<probLoss<<"  probDam:"<<probDam<<endl;
        if(argc==4){
            if(strcmp(argv[3],"false")==0){
                trace=0;
            }else if(strcmp(argv[3],"true")){
                trace=1;
            }else{
                trace=0;
            }
        }
    }
    gremlin* gre = new gremlin(probLoss, probDam);

    cout<<"Enter ipName:"<<endl;
    string ipName;
    cin>>ipName;
    cout<<"Enter port:"<<endl;
    int port;
    cin>>port;
    cout<<"sentence"<<endl;
    string sentence;
    cin>>sentence;
    cout<<"localName"<<endl;
    string localName;
    cin>>localName;

    ofstream outFile(localName);
    if(!outFile.is_open()){
        cout<<"cannot open file:"<<localName<<endl;
    }

    int clientSocket;
    struct sockaddr_in si_other;
    socklen_t slen = sizeof(si_other);
    if ((clientSocket=socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1)
    {
        die("socket");
    }

    memset((char *)&si_other, 0, sizeof(si_other));
    si_other.sin_family = AF_INET;
    si_other.sin_port = htons(port);
    const char* chIpName = ipName.c_str();
    if (inet_aton(chIpName , &si_other.sin_addr) == 0)
    {
        fprintf(stderr, "inet_aton() failed\n");
        exit(1);
    }

    const char *buf = sentence.c_str();
    cout<<"buf test "<<buf<<" len:"<<strlen(buf)<<endl;
    if (sendto(clientSocket, buf, strlen(buf) , 0 , (struct sockaddr *) &si_other, slen)==-1)
    {
        die("sendto()");
    }
    cout<<"sentence:"<<sentence<<" send suc"<<endl;

    SelectRepeat* sr = new SelectRepeat(clientSocket, si_other, slen, gre, trace);
    sr->start();
    SAR* sar=new SAR();

    sar->set_fragVec(sr->get_fragVec());
    sar->un_segFile();

    string tmp= sar->get_data();
    cout<<"get_data:"<<endl;
    cout<<tmp<<endl;
    //vector<string> tmpSplit = split(tmp,"\r");
    //cout<<tmpSplit[tmpSplit.size()-1];
    //outFile<<tmpSplit[tmpSplit.size()-1];
    outFile<<tmp<<endl;
    outFile.close();

}
