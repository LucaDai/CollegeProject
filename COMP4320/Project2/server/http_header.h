#ifndef HTTP_HEADER_H
#define HTTP_HEADER_H

#include <iostream>
#include <string>
#include <sstream>
using namespace std;

class HttpHeader
{
private:
    string contentLen;
    string contentType;
    int statusCode;
    const string httpVersion = "HTTP/1.0";
public:
    HttpHeader(string cLen, int sCode, string cType){
        contentLen = cLen;
        statusCode = sCode;
        contentType = cType;
    }

    string gen_statusCode(int code){
        switch (code)
        {
        case 200:
            return "Document Follows";
        case 404:
            return "File Not Found";
        case 400:
            return "Bad Request";
        default:
            return "Invalid Status Code";
        }
    }

    int get_statusCode(){
        return statusCode;
    }

    void set_statusCode(int status){
        statusCode = status;
    }

    string to_string(){
        stringstream ss;
        ss<<statusCode;
        string header = httpVersion + " " + ss.str(); + " "+gen_statusCode(statusCode) + "\r\n" +
				"Content-Type: " +contentType+"\r\n"+"Content-Length: " + contentLen+"\r\n";
		return header;
    }
};


#endif //HTTP_HEADER_H