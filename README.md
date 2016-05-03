# media-detector
Program to see if an extra media device has been connected to the computer.
 
This program continuously checks the contents of /run/media/<user directory> to check the difference in the contents of the media devices connected.

If a difference is found in the number of contents, it writes the contents in the directory into a file and sends the file to the admin.
