package Unicast;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
 

public class IServer {
 
	static HashMap hash = new HashMap<>();
    public static void main(String[] args) {
 
        try {
            //1������һ����������Socket,��ServerSocket, ָ���󶨵Ķ˿ڣ��������˶˿�
            ServerSocket serverSocket = new ServerSocket(9701);
            Socket socket = null;
            //��¼�ͻ��˵�����
            int count = 0;
            System.out.println("***�����������������ȴ��ͻ��˵�����***");
            //ѭ�������ȴ��ͻ��˵�����
            while (true){
                //����accept()������ʼ�������ȴ��ͻ��˵�����
                socket = serverSocket.accept();
                //����һ���µ��߳�
                ServerThread serverThread = new ServerThread(socket);
                //�����߳�
                serverThread.start();
                count++; //ͳ�ƿͻ��˵�����
                System.out.println("�ͻ��˵�����: " + count);
                InetAddress address = socket.getInetAddress();
                System.out.println("��ǰ�ͻ��˵�IP �� " + address.getHostAddress());
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void dealWith(String string){
		System.out.print("S���յ�����ϢΪ��"+string+"����");	
		String key = string.substring(0, string.indexOf("#"));
		String value = string.substring(string.indexOf("#")+1);
		hash.put(key, value);
		System.out.println(hash);

	}
	public static String find(String str){
		String IP = (String) hash.get(str);
		return IP;
	}
}
