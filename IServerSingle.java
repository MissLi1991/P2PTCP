package Unicast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;


public class IServerSingle {

	/**
	 * @param args
	 */
	static HashMap hash = new HashMap<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("S�ȴ�����......");
		try {  
			final ServerSocket server = new ServerSocket(9701);  
            Thread th = new Thread(new Runnable() {  
                public void run() {  
                    while (true) {  
                        try {    
                            System.out.println("��ʼ����...");  
                            /* 
                             * ���û�з��������Զ��ȴ� 
                             */  
                            Socket socket = server.accept();  
                            System.out.println("������"+socket.getRemoteSocketAddress()); 
                            System.out.println("S�����������ԣ�"+socket.getInetAddress().getHostAddress());
                            receive(socket);  
                        } catch (Exception e) {  
                            System.out.println("�������쳣");  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            });  
            th.run(); //�����߳�����  
        } catch (Exception e) {  
            e.printStackTrace();  
        }       
    }  
  
    protected static void receive(Socket socket) {
		// TODO Auto-generated method stub

    	try{
		//�����������������
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		
		boolean goon=true;
		while(goon){		
			String string=dis.readUTF();			//��socket�ж�ȡ����
			if(!string.startsWith("?")){
				dealWith(string);			//������ִ���ض�����
				//dos.writeUTF("�յ�");				//��socket dosд����
				//dos.flush();						//��ջ���������������
			}else{
				String ip = find(string.substring(1));
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
	System.out.println(hash);
}
	public void run() {  
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