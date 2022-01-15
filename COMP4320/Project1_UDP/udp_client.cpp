/*
    Simple udp client
*/
#include <stdio.h> 
#include <string.h>
#include <stdlib.h> 
#include <arpa/inet.h>
#include <sys/socket.h>
#include <string>
#include <iostream>
#include <fstream>
#include <math.h>
#include <ctime>
#include <unistd.h>

#define BUFLEN 512  //Max length of buffer

using namespace std;

void die(const char *s)
{
    perror(s);
    exit(1);
}

int getProbability(){
    cout<<"Enter a probability(between 0-1) for error: "<<endl;
    double prob1;
    cin>>prob1;
    while(prob1 < 0.0 || prob1 > 1.0){
        cout<<"The probability should be between 0-1: "<<endl;
        cin>>prob1;
    }
    return (int)(prob1*100);
}

string gremlin(string buf, int prob){
    srand((int)time(0));
    int randInt1 = abs(rand()) % 100;
    int randInt2 = abs(rand()) % 100;
    int byte1 = abs(rand()) % 512;
    int byte2 = abs(rand()) % 512;
    int byte3 = abs(rand()) % 512;

    if(randInt1 <= prob && prob !=0){
        if(randInt2 < 50){
            buf[byte1] = 63;
        }else if(randInt2 >= 50 && randInt2 < 80){
            buf[byte1] = 63;
            buf[byte2] = 63;
        }else if(randInt2 >= 80){
            buf[byte1] = 63;
            buf[byte2] = 63;
            buf[byte3] = 63;
        }
    }
    return buf;
}

string getCheckSumSent(string& buf){
    string checkSum = "";
    char* byteCheckSum = new char[5];
    string info = buf;
    int index = info.find(":") + 1;
    int j =0;
    for(int i = index + 1;i<index+6;i++){
        byteCheckSum[j] = buf[i];
        j++;
    }
    checkSum = byteCheckSum;
    
    bool leadingZeros = true;
    while(leadingZeros){
        leadingZeros = checkSum[0]=='0'?true:false;
        if(leadingZeros){
            checkSum = checkSum.substr(1);
        }
    }
    return checkSum;
}

string zeroCheckSum(string& message){
    string info = message;
    int index = info.find(":") + 1;
    for(int i=index+1;i<index+6;i++){
        info[i]=48;
    }
    return info;
}

int checkSum(string& data){
    int sum = 0;
    int dataLen = data.length();
    for(int i =0;i<dataLen;i++){
        sum +=(int)data[i];
    }
    return sum;
}

bool errorDetected(string& buf){
    int icheckSum;
    bool errorExist = false;
    string ogMessage = buf;
    string sumIn = getCheckSumSent(buf);
    string packetHeader = zeroCheckSum(buf);
    icheckSum = checkSum(packetHeader);
    cout<<"sumIn:"<<sumIn<<" icheckSum:"<<icheckSum<<endl;
    if(sumIn == to_string(icheckSum)){
        cout<<"---------"<<endl;
    }else{
        errorExist = true;
        string packetInfo = buf;
        cout<<endl<<"An error was detected in this packet: ";
        cout<<ogMessage;
	    cout<<"-----------------------------------";
    }
    return errorExist;
}

string removeHeader(string& packetInfo){
    string data = packetInfo.substr(packetInfo.find(":")+11);
    return data;
}

int main(int argc, char* argv[])
{
    struct sockaddr_in si_other;
    int s;
    socklen_t slen=sizeof(si_other);
    char buf[BUFLEN];

    if ((s=socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1)
    {
        die("socket");
    }
    cout<<"Enter ipName:"<<endl;
    string ipName;
    cin>>ipName;
    const char* chIpName = ipName.c_str();
    cout<<"Enter port:"<<endl;
    int port;
    cin>>port;
    string serverName;
    cout<<"serverName:"<<endl;
    cin>>serverName;
    cout<<"localName:"<<endl;
    string localName;
    cin>>localName;
    int prob = getProbability();

    ofstream outfile(localName);
    if(!outfile.is_open()){
        die("localName can not open");
    }

    memset((char *) &si_other, 0, sizeof(si_other));
    si_other.sin_family = AF_INET;
    si_other.sin_port = htons(port);

    if (inet_aton(chIpName , &si_other.sin_addr) == 0)
    {
        fprintf(stderr, "inet_aton() failed\n");
        exit(1);
    }

    cout<<"Enter message : "<<endl;
    string strSendMessge = "GET " + serverName + ".html HTTP/1.1";
    cout<<strSendMessge<<endl;
    const char* chSendMessage = strSendMessge.c_str();

    // send the message
    if (sendto(s, chSendMessage, strlen(chSendMessage) , 0 , (struct sockaddr *) &si_other, slen)==-1)
    {
        die("sendto()");
    }
    int endOfMessage = 1;
    int iteration = 1;
    string dataContent="";

    while(endOfMessage != 0){
        // receive a reply and print it
        // clear the buffer by filling null, it might have previously received data
        memset(buf,'\0', BUFLEN);
        // try to receive some data, this is a blocking call
        if (recvfrom(s, buf, BUFLEN, 0, (struct sockaddr *) &si_other, &slen) == -1)
        {
            die("recvfrom()");
        }
        
        //puts(buf);
        string strBuf = buf;
        cout<<"recv message:"<<strBuf<<endl;
	for(int i =0;i<BUFLEN;i++){
	    cout<<buf[i];
	}
        string strGre = gremlin(strBuf, prob);
        errorDetected(strGre);

        int i =0;
        while (endOfMessage != 0&& i<BUFLEN-1)
        {
            endOfMessage = strBuf[i];
            i++;
        }
        if(iteration>1){
            string noHeader = removeHeader(strBuf);
            dataContent += noHeader;

        }
        iteration++;

    }
    cout<<"the full message is:"<<endl<<dataContent<<endl;
    outfile<<dataContent;
    outfile.close();

    close(s);
    return 0;
}
