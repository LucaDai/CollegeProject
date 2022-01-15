#ifndef GREMLIN_H_
#define GREMLIN_H_
#include <iostream>
#include <string>
#include <math.h>

using namespace std;

class gremlin
{
private:
    double probLoss;
    double probDam;
    double probPass;
    int numLoss=0;
    int numDam=0;
    int numPass=0;
    string packet;
public:
    gremlin(double pLoss, double pDam){
        srand((int)time(0));
        probLoss=pLoss;
        probDam=pDam;
        probPass=1-(probLoss+probDam);
    }

    string get_packet(){
        return packet;
    }

    void load_packet(string data){
        packet = data;
    }

    string dam_packet(){
        double sample = rand() / double(RAND_MAX);
        string data = get_packet();

        if(sample<0.5){
            int rIndex = rand()%(get_packet().length());
            int random_index = abs(rIndex);
            unsigned char byte_var = data[random_index];
            int x = abs(rand()%8);
            unsigned char flipped = byte_var ^ (1<<x);
            data[random_index]= flipped;
        }else if(sample<=(0.3+0.5)){
            for(int i=0;i<2;i++){
                int rIndex = rand()%(get_packet().length());
                int random_index = abs(rIndex);
                unsigned char byte_var = data[random_index];
                int x = abs(rand()%8);
                unsigned char flipped = byte_var ^ (1<<x);
                data[random_index] = flipped;
            }
        }else{
            for(int i=0;i<3;i++){
                int rIndex = rand()%(get_packet().length());
                int random_index = abs(rIndex);
                unsigned char byte_var = data[random_index];
                int x = abs(rand()%8);
                unsigned char flipped = byte_var ^ (1<<x);
                data[random_index] = flipped;
            }
        }
        return data;
    }

    string loss_packet(){
        return "";
    }

    string pass_packet(){
        return packet;
    }

    double get_probLoss(){
        return probLoss;
    }

    void set_probLoss(double mLoss){
        this->probLoss = mLoss;
    }

    double get_probDam(){
        return probDam;
    }

    void set_probDam(double mDam){
        this->probDam = mDam;
    }

    double get_probPass(){
        return probPass;
    }

    void set_probPass(double mPass){
        this->probPass = mPass;
    }

    string filter(string datagram){
        load_packet(datagram);
        double sample = rand() / double(RAND_MAX);
        if(sample <= get_probPass()){
            numPass++;
            return pass_packet();
        }else if(sample<=(get_probPass()+get_probDam())){
            numDam++;
            return dam_packet();
        }else{
            numLoss++;
            return loss_packet();
        }
    }

};


#endif //GREMIN_H_
