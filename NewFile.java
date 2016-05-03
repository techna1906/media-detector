import java.io.*;

class NewFile
{
    static boolean flag1,flag2;
    static int length;
    static String contents[]=new String[1024];
    public NewFile(File f)
    {
	flag1=true;
	flag2=false;
	String directory=f.getName();
	contents=f.list();
	length=contents.length;
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
	FileWriter writer=new FileWriter(filename, true);
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
	
    }
    public static void main(String args[])
    {
	try{
	    String filename="/run/media/asharma";
	    String logfile="log.txt"
	    File f=new File(filename);
	    File file2=new File(logfile);
	    if(file2.exists())
		file2.delete();
	    file2=new File(logfile);
	    NewFile nf=new NewFile(f);
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
			printContents();
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
