CC=g++
CFLAGS=-Wall
TARGETS=udp_client udp_server

all:udp_client udp_server
udp_client:
	$(CC) $(CFLAGS) -o udp_client udp_client.cpp
udp_server:
	$(CC) $(CFLAGS) -o udp_server udp_server.cpp

clean:
	-rm -f ${TARGETS}
