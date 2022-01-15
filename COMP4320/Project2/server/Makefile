CC=g++
FLAGS=-std=c++11 -Wall
OBJ=udp_server *.o
udp_client:
	${CC} ${FLAGS} -o udp_server udp_server.cpp md5.cpp -lpthread

#udp_client.o:
#	${CC} ${FLAGS} -c udp_client.cpp

#md5.o:
#	${CC} ${FLAGS} -c md5.cpp

clean:
	rm -fr ${OBJ}
