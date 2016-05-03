import java.io.*;
import java.net.*;

class Server implements Runnable
{
    ServerSocket port;
    Thread t;
    boolean flag;

    Server() throws Exception
    {
	port=new ServerSocket(9090);
	flag=true;
	t=new Thread(this);
	t.start();
    }

    public void run()
    {
	while(flag)
	{
	    try
	    {
		Socket s=port.accept();
		new Processor(s);
	    }
	    catch(Exception ex)
		{}
	}
    }

    public static void main(String args[])
    {
	try
	{
	    Server svr=new Server();
	}
	catch(Exception ex)
	    {}
    }
}

class Processor extends Thread
{
    static boolean flag1,flag2;
    static int length;
    static String contents[]=new String[1024];
    static Socket client;
    Processor(Socket s)
    {
	client=s;
	start();
    }
    static int getContents(File f)
    {
	contents=f.list();
	return contents.length;
    }
    static void printContents()
    {
	System.out.println("--------------------------------");
	for(int i=0;i<contents.length;i++)
	    System.out.println(" "+contents[i]);
	System.out.println("--------------------------------");
    }
    static void writeFile(String contents[],File filename) throws Exception
    {
	FileWriter writer=new FileWriter(filename);
	writer.write("----------------------\n");
	String time="Time: "+System.currentTimeMillis()+"\n";
	writer.write(time);
	for(int i=0;i<contents.length;i++)
	{
	    writer.write(contents[i]);
	    writer.write("\n");
	}
	writer.flush();
	writer.close();
	//File f=new File();
	//f=filename;
	FileInputStream fis=new FileInputStream(filename);
	OutputStream os=client.getOutputStream();
	DataOutputStream dos=new DataOutputStream(os);
	int x;
	byte arr[]=new byte[1024];
	String message;
	if(!flag2)
	message="Change in directory contents detected";
	else
	    message="Everything seems normal";
	dos.writeUTF(message);
	dos.writeLong(filename.length());
	while((x=fis.read(arr))!=-1)
	    dos.write(arr,0,x);
	dos.flush();
	fis.close();
	
    }
    public void run()
    {
	try{
	    String filename="/run/media/asharma";
	    String logfile="log.txt";
	    File f=new File(filename);
	    File file2=new File(logfile);
	    if(file2.exists())
		file2.delete();
	    file2=new File(logfile);
	   	flag1=true;
	flag2=true;
	String directory=f.getName();
	contents=f.list();
	length=contents.length;
	    while(flag1)
	    {
	    if(f.exists())
	    {
		if(f.isFile())
	        {
		    System.out.println(f.getAbsolutePath()+" is a file.");
		}
		else if(f.isDirectory())
		{
		    int new_length=getContents(f);
		    if(new_length!=length)
		    {
			flag2=false;
			length=new_length;
		    }
		    if(!flag2)
		    {			
     			//printContents();
			writeFile(contents,file2);
			flag2=true;
		    }
		}		    
	    }
	    else
	    {
		System.out.println(f.getAbsolutePath()+" does not exist.");
	    }
	    }
	   }
	catch(Exception ex)
	{
	    System.out.println(ex);
	}
}
}
