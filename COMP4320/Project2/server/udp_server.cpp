#include <iostream>
#include "sar.h"
#include "http_header.h"
#include "selective_repeater.h"
#include <string>
#include <vector>
#include <arpa/inet.h>
#include <sys/socket.h> 
#include <unistd.h>
#include <fstream>
#include <sstream>

#define SERVER_PORT 10030
#define BUFFER_AMT 128
using namespace std;

string read_file(string fileName){
    ifstream infile(fileName);
    if(!infile.is_open()){
        die("open file error");
    } 
    string contents;
    string tmp;
    while(getline(infile, tmp)){
        contents += ('\n' + tmp);
    }
    infile.close();
    return contents;
}



int main(int argc, char* argv[]){
    bool trace = false;
    if(argc==2){
        if(atoi(argv[1])==1){
            trace=true;
        }
    }else{
        cout<<"error"<<endl;
    }

    SAR* sar;
    HttpHeader* header = NULL;

    
    int serverSocket;
    struct sockaddr_in si_me;
    struct sockaddr_in si_other;
    socklen_t slen = sizeof(si_other);


    if ((serverSocket=socket(AF_INET, SOCK_DGRAM, 0)) == -1)
    {
        die("socket");
    }

    memset((char *) &si_me, 0, sizeof(si_me));

    si_me.sin_family = AF_INET;
    si_me.sin_port = htons(SERVER_PORT); // setting to 0 would have system pick port.
    si_me.sin_addr.s_addr = htonl(INADDR_ANY); 

    // bind socket to port 
    if(bind(serverSocket , (struct sockaddr*)&si_me, sizeof(si_me)) == -1)
    {
        die("bind");
    }
    int recv_len;

    char receiveData[BUFFER_AMT];
    memset(receiveData, 0, BUFFER_AMT);
    while(true){
        cout<<"Waiting for connection...."<<endl;
        
        if ((recv_len = recvfrom(serverSocket, receiveData, BUFFER_AMT, 0, (struct sockaddr *) &si_other, &slen)) == -1)
        {
            die("recvfrom()");
        }

        string strBuf = receiveData;
	cout<<"test2 "<<strBuf<<endl;
	cout<<"test3 "<<receiveData<<endl;
        
        //GET fileName
        //vector<string> vecBuf = split(strBuf, "[ ]");
        
        
        string contents = read_file(strBuf);
	cout<<"test5"<<endl;
        
        header = new HttpHeader(to_string(contents.length()),200,"text/html");

        string headerInfo = header->to_string();
        string headerData = headerInfo + "\r\n" + contents;
        cout<<"contents"<<endl;
        cout<<headerData<<endl;

        sar=new SAR(headerData);
	cout<<"test1"<<endl;
        SelectRepeat* sr = new SelectRepeat(sar, serverSocket, si_me, si_other, slen, trace);
        sr->start();
        break;
    }

}

