# 2018.4-Redes-Sockets
First network practical work.

### To test the project modules (Linux):

**Clone repository**

    $ git clone https://github.com/souzaluuk/2018.4-Redes-Sockets.git ~/Redes-Sockets

**Compile the source code**

    $ cd ~/Redes-Sockets
    $ ant

**Run ./start.sh**

    $ ./start.sh tcp_server [port]
    $ ./start.sh udp_server [port]
    $ ./start.sh udp_client [hostname_dns:port] [domain]

**Or run java -jar dist/Redes-Sockets.jar**

    $ cd ~/Redes-Sockets
    $ java -jar dist/Redes-Sockets.jar tcp_server [port]
    $ java -jar dist/Redes-Sockets.jar udp_server [port]
    $ java -jar dist/Redes-Sockets.jar udp_client [hostname_dns:port] [domain]

**Examples**

Starting TCP in port 8080

      $ ./start.sh tcp_server 8080

Test TCP

      Go to http://localhost:8080 with browser

Starting and test UDP in port 8090 (Required client and server run)

      Start UDP Server
      $ ./start.sh udp_server 8090
      
      Client request
      $ ./start.sh udp_client localhost:8090 souzaluuk.org
