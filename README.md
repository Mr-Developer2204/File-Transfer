<h1>INTRODUCTION</h1> 
<p>
The File Transfer Application is a simple android application that can be used to transfer your files from one android device to another android device in close proximity. The idea behind this application is to make transfers easy and fast for people having internet issues.
This app solves the problem of transferring files from one android device to another android device without the internet. It eliminates the old Bluetooth transfers and transfers files using wifi which provides much faster data transfers. You can share music, images, documents, etc without any mobile data usage.
</p>

<h2>TECHNOLOGY:</h2>
<pre>
This application works  on P2P(Peer To Peer) networking technology.

P2P networking is a type of networking where there is no need for a centralized server , each node works both as server and client. 
The individual users in this network are referred to as peers. The peers request for the files from other peers by establishing TCP or UDP connections.Each node has a particular ID assigned, by which they can identify internally.

When one peer makes a request, it is possible that multiple peers have the copy of that requested object. Now the problem is how to get the IP addresses of all those peers. This is decided by the underlying architecture supported by the P2P systems. By means of one of these methods, the client peer can get to know about all the peers which have the requested object/file and the file transfer takes place directly between these two peers.
</pre>

<h2>Working/Steps :</h2>

<h3>Enabling - Disabling - Discovering WIFI :</h3>
<pre>
We have used WifiP2pManager class provided by  Google Android.This class provides the API for managing Wi-Fi peer-to-peer connectivity. This lets an application discover available peers, setup connection to peers and query for the list of peers. When a p2p connection is formed over wifi, the device continues to maintain the uplink connection over mobile or any other available network for internet connectivity on the device.

So  after , discovering other devices (Client/Server), we need to establish the connection between the two.
 </pre>


<h3>Server- Client connection:</h3>
<pre>
(Sending requests and getting response)
 Client will send a request to the server , to which  the server will respond .We have achieved this feature by socket programming in our app.

Socket :
A network socket is a software structure within a network node of a computer network that serves as an endpoint for sending and receiving data across the network. The structure and properties of a socket are defined by an application programming interface for the networking architecture.

Sockets allow communication between two different processes on the same or different machines. 


Following is the flow of events for a socket:
 
The socket() API creates an endpoint for communications and returns a socket descriptor that represents the endpoint.
When an application has a socket descriptor, it can bind a unique name to the socket. Servers must bind a name to be accessible from the network.
The listen() API indicates a willingness to accept client connection requests. When a listen() API is issued for a socket, that socket cannot actively initiate connection requests. The listen() API is issued after a socket is allocated with a socket() API and the bind() API binds a name to the socket. A listen() API must be issued before an accept() API is issued.
The client application uses a connect() API on a stream socket to establish a connection to the server.
The server application uses the accept() API to accept a client connection request. The server must issue the bind() and listen() APIs successfully before it can issue an accept() API.
When a connection is established between stream sockets (between client and server), you can use any of the socket API data transfer APIs. Clients and servers have many data transfer APIs from which to choose, such as send(), recv(), read(), write(), and others.
When a server or client wants to stop operations, it issues a close() API to release any system resources acquired by the socket.
  </pre>
  
<h3>Programmatically:</h3>
<pre>
To achieve it programmatically ,
 we will use

<b>java.net.Socket class:</b> represents the socket between the client and the server

<b>java.net.ServerSocket class :</b> provides a mechanism for the server application to listen to clients and establish connections with them. 

(will add as we proceed)
</pre>

<h2>FEATURES:</h2>
<pre>

1.Enabling /Disabling WIFI and Hotspot .

2. Discovering  and displaying the nearer devices.

3.Establishing connection between the devices.

4. Accessing and displaying the files stored in external(shared storage space) storage .

5. Selecting single or multiple files (with different valid extensions) 

6.Sending selected files to other devices.

</pre>
