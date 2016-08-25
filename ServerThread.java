package Unicast;

import java.io.*;
import java.net.Socket;
 
/**
 * Created by dong on 15-6-22.
 * ���������̴߳�����
 */
public class ServerThread extends Thread {
 
    //�ͱ��߳���ص�Socket
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
 
    //�߳�ִ�еĲ�������Ӧ�ͻ��˵�����
    public void run(){
 

		// TODO Auto-generated method stub

    	try{
		//�����������������
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		
		boolean goon=true;
		while(goon){		
			String string=dis.readUTF();			//��socket�ж�ȡ����
			if(!string.startsWith("?")){
				IServer.dealWith(string);			//������ִ���ض�����
				//dos.writeUTF("�յ�");				//��socket dosд����
				//dos.flush();						//��ջ���������������
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
		//�ر�socket����
		dis.close();
		dos.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

    }

}