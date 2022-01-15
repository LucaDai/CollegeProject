/*
    Simple udp server
*/
#include <stdio.h>      
#include <string.h>     
#include <stdlib.h>    
#include <arpa/inet.h>
#include <sys/socket.h> 
#include <unistd.h>    
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

#define BUFLEN 512 // max buffer length
#define PORT 10004    // hardcoded port

// die prints the error message on stderr and exits with a non-success code
void die(const char *s)
{
    perror(s);
    exit(1);
}

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

string makeFileHeader(int sequenceNum, long fileSize){
    string fileHeader = "Sequence Number " + to_string(sequenceNum) + "\n" + "HTTP/1.0 200 Document Follows\r\n"
            + "ogChecksum: " + "00000\r\n" + "Content-Type: text/plain\r\n"
            + "Content-Length: " + to_string(fileSize) + "\r\n\r\n" + "Data";
    return fileHeader;
}

string makePacketHeader(int sequenceNum) {
    string packetHeader = "Sequence Number " + to_string(sequenceNum) + "\n" + "ogChecksum: " + "00000\r\n" + "\r\n";
    return packetHeader;
}

string padPacketWithSpaces(string& header) {
    string newHeader(512,' ');
    int headerLen = header.length();
    for (int i = 0; i < 512; i++) {
        if (i < headerLen) {
            newHeader[i]= header[i];
        } 
    }
    return newHeader;
}

int checkSum(string& data) {
    int sum = 0;
    int dataLen = data.length();
    for (int i = 0; i < dataLen; i++) {
        sum += (int) data[i];
    }
    return sum;
}


string insertChecksum(string& message, string checkSum) {
    string packet = message;
    int index = packet.find(":") + 2;
    string checkSumToInsert = checkSum;
   
    if (checkSum.length() == 2) {
        message[index + 3] = checkSumToInsert[0];
        message[index + 4] = checkSumToInsert[1];
    }
    else if  (checkSum.length() == 3) {
        message[index + 2] = checkSumToInsert[0];
        message[index + 3] = checkSumToInsert[1];
        message[index + 4] = checkSumToInsert[2];
    }
    else if  (checkSum.length() == 4) {
        message[index + 1] = checkSumToInsert[0];
        message[index + 2] = checkSumToInsert[1];
        message[index + 3] = checkSumToInsert[2];
        message[index + 4] = checkSumToInsert[3];
    }
    else if (checkSum.length() == 5) {
        message[index] = checkSumToInsert[0];
        message[index + 1] = checkSumToInsert[1];
        message[index + 2] = checkSumToInsert[2];
        message[index + 3] = checkSumToInsert[3];
        message[index + 4] = checkSumToInsert[4];
    }
   
    string newMessage = message;
    cout<<"check sum:"<<message<<endl;
    return newMessage;
}

string calculateCheckSum(string& packet) {
    string message = packet;
    message = insertChecksum(packet, to_string(checkSum(packet)));
    return message;
}

int main(int argc, char* argv[])
{
    struct sockaddr_in si_me, si_other;

    int s;
    socklen_t slen = sizeof(si_other);
    int recv_len;

    char buf[BUFLEN];

    // create a UDP socket (SOCK_DGRAM)
    if ((s=socket(AF_INET, SOCK_DGRAM, 0)) == -1)
    {
        die("socket");
    }

    memset((char *) &si_me, 0, sizeof(si_me));

    si_me.sin_family = AF_INET;
    si_me.sin_port = htons(PORT); // setting to 0 would have system pick port.
    si_me.sin_addr.s_addr = htonl(INADDR_ANY); 

    // bind socket to port
    if( bind(s , (struct sockaddr*)&si_me, sizeof(si_me) ) == -1)
    {
        die("bind");
    }

    while(1)
    {
        printf("Waiting for data...");
        fflush(stdout);

        // try to receive some data, this is a blocking call
        if ((recv_len = recvfrom(s, buf, BUFLEN, 0, (struct sockaddr *) &si_other, &slen)) == -1)
        {
            die("recvfrom()");
        }

        printf(
          "Received packet from %s:%d\n", 
          inet_ntoa(si_other.sin_addr), 
          ntohs(si_other.sin_port)
        );
        printf("Data: %s\n" , buf);

        string strBuf = buf;
        vector<string> bufSplit = split(strBuf, " ");
        string inputFile = bufSplit[1];
        ifstream infile(inputFile);
        if(!infile.is_open()){
            die("open file error");
        }
        string strFile = "";
        string tmp = "";
	getline(infile, tmp);
	strFile = tmp;
        while(getline(infile, tmp)){
            strFile += ('\n' + tmp);
        }
        infile.close();
        int fileSize = strFile.length();
        int check = 0;
        int sequenceNum = 0;
        int fileIndex = 0;
        while(check != -1){
            string infoToSend = "";
            string header;
            string packet;
            
            if(sequenceNum == 0){
                header = makeFileHeader(sequenceNum, fileSize);
                packet = padPacketWithSpaces(header);
            }else{
                header = makePacketHeader(sequenceNum);
                packet = padPacketWithSpaces(header);
                int packetLen = packet.length();
		for(int i =header.length();i<packetLen-1;i++){
                    if(fileIndex>=fileSize){
			packet[i] = 0;
                        check=-1;
                        break;
                    }
                    packet[i]=strFile[fileIndex++];
                }
		packet[packet.length()-1] = 0;
            }

            infoToSend = calculateCheckSum(packet);
            if(check==-1){
		infoToSend = infoToSend.substr(0,infoToSend.find('\0'));
            }
            strncpy(buf,infoToSend.c_str(), 512);
	    cout<<"len of infoToSend:"<<infoToSend.length()<<endl;
            cout<<"message: "<<buf<<endl;
	        if (sendto(s, buf, BUFLEN, 0, (struct sockaddr*) &si_other, slen) == -1)
            {
                die("sendto()");
            }
	        sequenceNum++;
        }

    }

    close(s);
    return 0;
}
