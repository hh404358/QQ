package com.ljr.client.control;

import com.ljr.client.frame.SendFileFrame;

import java.io.*;
import java.net.Socket;


public class SendTread extends Thread
{
	private SendFileFrame fram;
	private String FilePath;

	private Socket socket;

	private DataInputStream fileIn;
	private DataOutputStream out;
	private String connIP;
	private Integer clientPort;
	public SendTread(SendFileFrame fram, String Filepath,String connIP,int clientPort){
		this.fram = fram;
		this.FilePath = Filepath;
		this.connIP = connIP;
		this.clientPort = clientPort;
	}

	@Override
	public void run() {
		super.run();
		startSend( FilePath);
	}

	public void startSend(String filepath){
		try{
			socket = new Socket(connIP, clientPort);
			File file = new File(filepath);
			out = new DataOutputStream(socket.getOutputStream());
			String fileName = file.getName();
			long FileLen = file.length();
			out.writeUTF(fileName);
			out.flush();
			out.writeLong(FileLen);
			out.flush();

			fileIn = new DataInputStream(new FileInputStream(file));
			byte[] buff = new byte[1024];
			int len = 0;
			long passLen = 0;
			long startTime = System.currentTimeMillis();
			long endTime = 0;
			double passTime = 0;
			while (true){
				if (fileIn != null){
					len = fileIn.read(buff);
					passLen += len;
				}
				endTime = System.currentTimeMillis();
				passTime = endTime -startTime;
				if (len == -1){
					fram.updateProgressBar(fram.progressBar, FileLen,
							FileLen, fram.lblProgress,passTime);
					break;
				}
				
				fram.updateProgressBar(fram.progressBar, passLen, FileLen,
						fram.lblProgress,passTime);
				out.write(buff, 0, len);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			closeSend();
		}
	}

	public void closeSend(){
		try{
			if (out != null) {
				out.close();
			}
			if (fileIn != null) {
				fileIn.close();
			}
			if (socket != null) {
				socket.close();
			}
			
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}
