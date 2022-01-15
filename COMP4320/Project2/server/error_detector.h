#ifndef ERROR_DETECTOR_H
#define ERROR_DETECTOR_H
#include <string>
#include <iostream>
#include "fragment.h"
#include "md5.h"

using namespace std;

class errorDetector
{
public:
    static fragment* gen_checksum(fragment* frag){
        if(frag!=NULL){
            //int data_len = (fragment->get_data()).length();
            string str2hash = frag->get_data();
            str2hash+=(char)(frag->get_header())->get_seqId();
            str2hash+=(char)(frag->get_header())->get_endSeq();
            
            cout<<"input to md5:"<<endl;
            for(int i=0;i<(int)str2hash.length();i++){
            	printf("%d\t",str2hash[i]);
            }
            cout<<endl;

            unsigned char decrypt[16];
            MD5_CTX md5;
            unsigned char *encrypt = (unsigned char *)str2hash.c_str();
    
            MD5Init(&md5);
            MD5Update(&md5, encrypt, (int)strlen((char *)encrypt));
            MD5Final(&md5, decrypt);
            (frag->get_header())->set_checkSum(decrypt);
            return frag;
        }else{
            return NULL;
        }
    }

    static bool validate_checksum(fragment* frag){
        if(frag!=NULL){
            string str2hash = frag->get_data();
            str2hash+=(char)(frag->get_header())->get_seqId();
            str2hash+=(char)(frag->get_header())->get_endSeq();

            unsigned char decrypt[16];
            MD5_CTX md5;
            unsigned char *encrypt = (unsigned char *)str2hash.c_str();
    
            MD5Init(&md5);
            MD5Update(&md5, encrypt, (int)strlen((char *)encrypt));
            MD5Final(&md5, decrypt);
            unsigned char *fragDecrypt = (frag->get_header())->get_checkSum();
            bool isEqu = true;
            for(int i=0;i<16;i++){
                if(decrypt[i]!=fragDecrypt[i]){
                    isEqu=false;
                    break;
                }
            }
            return isEqu;
        }else{
            return false;
        }
    }

};


#endif //ERROR_DETECTOR_H
