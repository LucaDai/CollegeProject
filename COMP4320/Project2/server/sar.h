#ifndef SAR_H_
#define SAR_H_

#include <iostream>
#include "fragment.h"
#include "error_detector.h"
#include <vector>

#define SEG_LEN 110
using namespace std;

class SAR
{
private:
    vector<fragment*> fragVec;
    errorDetector* ed;
    string mData;
public:
    SAR(string& ttData){
        ed=new errorDetector();
        mData = ttData;
        if(mData!=""||mData.length()!=0){
            seg_obj();
        }
    }
    
    void seg_obj(){
        int st=0;
        int end=SEG_LEN;
        for(int i=0;i<(int)(mData.length()/SEG_LEN)+1;i++){
            fragment* frag = new fragment(mData.substr(st,end-st+1));
            (frag->get_header())->set_seqId((char)(i%32));
            if((i+1)<(int)(mData.length()/SEG_LEN)){
                (frag->get_header())->set_endSeq(0);
            }else{
                (frag->get_header())->set_endSeq(1);
            }
            add_frag(ed->gen_checksum(frag));
            st=end;
            end+=SEG_LEN;
        }
        cout<<"frag size:"<<fragVec.size()<<endl;
    }

    bool hash_next(){
        return (fragVec.size()==0)?false:true;
    }

    fragment* next(){
        fragment* fragN = fragVec[0];
        fragVec.erase(fragVec.begin());
        return fragN;
    }

    void add_frag(fragment* frag){
        fragVec.push_back(frag);
    }
};



#endif //SAR_H_
