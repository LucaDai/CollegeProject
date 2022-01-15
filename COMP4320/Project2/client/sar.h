#ifndef SAR_H_
#define SAR_H_

#include <iostream>
#include "fragment.h"
#include "error_detector.h"
#include <vector>

using namespace std;

class SAR
{
private:
    vector<fragment*> fragVec;
    errorDetector* ed;
    string mData;
public:
    SAR(){
        ed=new errorDetector();
    }
    string get_data(){
        return mData;
    }

    void add_fragment(fragment* frag){
        fragVec.push_back(frag);
    }

    void set_fragVec(vector<fragment*> fragVecs){
        fragVec = fragVecs;
    }

    void un_segFile(){
	cout<<"un_segFile"<<endl;
        for(int i=0;i<(int)fragVec.size();i++){
            cout<<fragVec[i]->get_data()<<endl;
            mData+=((fragVec[i]->get_data())+"\n");
        }
    }
};



#endif //SAR_H_
