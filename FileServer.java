package Unicast;

import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.Random;   
  
  
/** 
 * �����ļ����� 
 * @author admin_Hzw 
 * 
 */  
public class FileServer {  
      
    /** 
     * ����main���� 
     * @param args 
     */  
	 static int length = 0;  
	 static double sumL = 0 ;  
     static byte[] sendBytes = null;  
     static Socket socket = null;  
     static DataOutputStream dos = null;  
     static FileInputStream fis = null;  
     static boolean bool = false;  
    public static void main(String[] args) {  
        try {  
            final ServerSocket server = new ServerSocket(9702);  
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
                            ShareFile(socket);  
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
  
    public void run() {  
    }  
  
    /** 
     * �����ļ����� 
     * @param socket 
     * @throws IOException 
     */  
    public static void ShareFile(Socket socket) throws IOException {  
    	try{
    	DataInputStream dis=new DataInputStream(socket.getInputStream());	
    	String string=dis.readUTF();
    	String path = "F:/code/OWL�ļ�/";
    	System.out.println(path+string);
    	File file = new File(path + string); //Ҫ������ļ�·��  
        long l = file.length();
        dos = new DataOutputStream(socket.getOutputStream());  
        fis = new FileInputStream(file);        
        sendBytes = new byte[1024];    
        while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {  
            sumL += length;    
            System.out.println("�Ѵ��䣺"+((sumL/l)*100)+"%");  
            dos.write(sendBytes, 0, length);  
            dos.flush();  
        }   
        //��Ȼ�������Ͳ�ͬ����JAVA���Զ�ת������ͬ�������ͺ������Ƚ�  
        if(sumL==l){  
            bool = true;  
        }  
    }catch (Exception e) {  
        System.out.println("�ͻ����ļ������쳣");  
        bool = false;  
        e.printStackTrace();    
    } finally{    
        if (dos != null)  
            dos.close();  
        if (fis != null)  
            fis.close();     
        if (socket != null)  
            socket.close();      
    }  
    System.out.println(bool?"�ɹ�":"ʧ��");  
}   
} 

