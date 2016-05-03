import java.io.*;
import java.net.*;

class Client
{
    Socket s;
    File f;
    Client(String ip, int port) throws Exception
    {
	String filename="ClientLog.txt";
	s=new Socket(ip,port);
	f=new File(filename);
        if(f.exists())
	    f.delete();
	f=new File(filename);
    }
    void service() throws Exception
    {
	FileOutputStream fos=new FileOutputStream(f,true);
	InputStream in=s.getInputStream();
	DataInputStream din=new DataInputStream(in);
	String message=din.readUTF();
	long len=din.readLong();
	int data;
	for(int i=0;i<len;i++)
	{
	        data=din.read();
		fos.write(data);
	}
	fos.flush();
	fos.close();

	System.out.println("\n"+message+"\n");
    }
    
    public static void main (String args[])
    {
    	try
    	{
    		Client c=new Client("127.0.0.1", 9090);
		while(true)
		c.service();
    	}
    	catch(Exception ex){}
    }
}
