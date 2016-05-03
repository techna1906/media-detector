# media-detector
Program to see if an extra media device has been connected to the computer.
 
This program continuously checks the contents of /run/media/<user directory> to check the difference in the contents of the media devices connected.

If a difference is found in the number of contents, it writes the contents in the directory into a file and sends the file to the admin.


To run:
Server:
 1. javac Server.java
 2. java Server <port_no>
Client:
 1. java Client.java
 2. java Client <ip> <port_no>

In Server.java, change variable filename2 to desired directory whose contents are to be checked.

Server log gets saved in log.txt.
Client log gets saved in ClientLog.txt.
