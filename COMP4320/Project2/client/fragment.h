#ifndef FRAGMENT_H
#define FRAGMENT_H

#include <string>
#include <iostream>
#include <stdio.h>
using namespace std;


class fragmentHeader
{
private:
    unsigned char checkSum[16];
    unsigned char seqId;
    unsigned char endSeq;
public:
    unsigned char get_endSeq(){
        return endSeq;
    }

    void set_endSeq(unsigned char mEndSeq){
        this->endSeq = mEndSeq;
    }

    unsigned char* get_checkSum(){
        return checkSum;
    }

    void set_checkSum(unsigned char* cSum){
        
        for(int i=0;i<16;i++){
            checkSum[i]=cSum[i];
        }
    }

    unsigned char get_seqId(){
        return seqId;
    }

    void set_seqId(unsigned char sId){
        this->seqId = sId;
    }

    unsigned char* get_header(){
        unsigned char* ans = new unsigned char[18];
        for(int i=0;i<16;i++){
            ans[i]=checkSum[i];
        }
        ans[16]=get_seqId();
        ans[17]=get_endSeq();
        return ans;    
    }
};


class fragment
{
private:
    fragmentHeader* header;
    string data;
public:
    fragment(string data){
        this->data = data;
        this->header = new fragmentHeader();
    }
    fragmentHeader* get_header(){
        return header;
    }

    void set_data(string data){
        this->data = data;
    }

    string get_data(){
        return this->data;
    }

    string get_fragment(){
        //cout<<this->header->get_header()<<endl;
        
        unsigned char* ustrHeader = header->get_header();

	cout<<"before getFrag"<<endl;
        for(int i=0;i<18;i++){
            printf("%d\t",ustrHeader[i]);
        }
        cout<<endl;
        //cout<<this->data<<endl;
        string res;
        cout<<"after getFrag"<<endl;
        for(int i=0;i<18;i++){
        	res+=(char)ustrHeader[i];
        	printf("%d\t",res[i]);
        }
        cout<<endl;
        res+=(this->data);
        return res;
    }
};


#endif //FRAGMENT_H
