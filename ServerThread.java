package Unicast;

import java.io.*;
import java.net.Socket;
 
/**
 * Created by dong on 15-6-22.
 * 服务器端线程处理类
 */
public class ServerThread extends Thread {
 
    //和本线程相关的Socket
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
 
    //线程执行的操作，响应客户端的请求
    public void run(){
 

		// TODO Auto-generated method stub

    	try{
		//创建数据输入输出流
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		
		boolean goon=true;
		while(goon){		
			String string=dis.readUTF();			//从socket中读取数据
			if(!string.startsWith("?")){
				IServer.dealWith(string);			//服务器执行特定功能
				//dos.writeUTF("收到");				//向socket dos写数据
				//dos.flush();						//清空缓冲区，立即发送
			}else{
				String ip = IServer.find(string.substring(1));
				dos.writeUTF(ip);
				dos.flush();
			/*else{
				goon=false;
				dos.writeUTF("end");
				dos.flush();
			*/}

		}
		//关闭socket和流
		dis.close();
		dos.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

    }

}