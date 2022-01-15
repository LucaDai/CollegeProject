CC=g++
FLAGS=-std=c++11 -Wall -lpthread
OBJ=udp_client *.o
udp_client:
	${CC} ${FLAGS} -o udp_client udp_client.cpp md5.cpp

#udp_client.o:
#	${CC} ${FLAGS} -c udp_client.cpp

#md5.o:
#	${CC} ${FLAGS} -c md5.cpp

clean:
	rm -fr ${OBJ}
