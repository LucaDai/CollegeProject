#ifndef EVENT_TIME_H
#define EVENT_TIME_H
#include <string>
#include <iostream>
#include <sstream>
using namespace std;

class EventTime
{
private:
    double expireTime;
    int eventId;
public:
    EventTime(int eId, double eTime){
        expireTime = eTime;
        eventId = eId;
    }

    double get_expireTime(){
        return expireTime;
    }

    void set_ExpireTime(double eTime){
        expireTime = eTime;
    }

    void dec_ExpireTime(){
        expireTime--;
    }

    int get_eventId(){
        return eventId;
    }

    void set_eventId(int eId){
        eventId=eId;
    }

    string to_string(){
        stringstream si;
        si<<eventId;
        stringstream sd;
        sd<<expireTime;
        return "Event Id:"+si.str()+"\t Event has:"+sd.str();
    }
};



#endif //EVENTI_TIME_H