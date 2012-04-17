package com.miracula.main;


import java.io.*;
import java.net.*;


public class RunMain {
//	public static void main(String argv[]) throws Exception
//	{
//		String clientSentence;
//		String capitalizedSentence;
//		
//		ServerSocket welcomeSocket = new ServerSocket(22088);
//		
//		while (true)
//		{
//			Socket connectionSocket = welcomeSocket.accept();
//			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//		
//			clientSentence = inFromClient.readLine(); 
//			capitalizedSentence = clientSentence.toUpperCase() + "\n";
//			outToClient.writeBytes(capitalizedSentence);
//			
//			System.out.println("FROM SERVER: ");
//		}
//	}
	

    public static void main(String[] args) throws IOException
    {
        ServerSocket s = new ServerSocket(22088);
        System.out.println("Server Started");
        try
        {
            while (true)
            {
                Socket socket = s.accept();
                try
                {
                    new ServeOneThread(socket);
                }
                catch (IOException e)
                {
                    socket.close();
                }
            }
        }
        finally
        {
            s.close();
        }
    }
}

class ServeOneThread extends Thread
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ServeOneThread(Socket s) throws IOException
    {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
        this.setPriority(MIN_PRIORITY);
    }
    public void run()
    {
        try
        {
            while (true)
            {
                String str = in.readLine();
                if (str.equals("END"))
                    break;
                System.out.println("Echoing: " + str);
                out.println(str);
            }
            System.out.println("closing...");
        }
        catch (IOException e)
        {
            System.err.println("IO Exception");
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                System.err.println("Socket not closed");
            }
        }
    }
}
