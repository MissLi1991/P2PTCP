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
 * 接收文件服务 
 * @author admin_Hzw 
 * 
 */  
public class FileServer {  
      
    /** 
     * 工程main方法 
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
                            System.out.println("开始监听...");  
                            /* 
                             * 如果没有访问它会自动等待 
                             */  
                            Socket socket = server.accept();  
                            System.out.println("有链接"+socket.getRemoteSocketAddress());  
                            ShareFile(socket);  
                        } catch (Exception e) {  
                            System.out.println("服务器异常");  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            });  
            th.run(); //启动线程运行  
        } catch (Exception e) {  
            e.printStackTrace();  
        }       
    }  
  
    public void run() {  
    }  
  
    /** 
     * 接收文件方法 
     * @param socket 
     * @throws IOException 
     */  
    public static void ShareFile(Socket socket) throws IOException {  
    	try{
    	DataInputStream dis=new DataInputStream(socket.getInputStream());	
    	String string=dis.readUTF();
    	String path = "F:/code/OWL文件/";
    	System.out.println(path+string);
    	File file = new File(path + string); //要传输的文件路径  
        long l = file.length();
        dos = new DataOutputStream(socket.getOutputStream());  
        fis = new FileInputStream(file);        
        sendBytes = new byte[1024];    
        while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {  
            sumL += length;    
            System.out.println("已传输："+((sumL/l)*100)+"%");  
            dos.write(sendBytes, 0, length);  
            dos.flush();  
        }   
        //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较  
        if(sumL==l){  
            bool = true;  
        }  
    }catch (Exception e) {  
        System.out.println("客户端文件传输异常");  
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
    System.out.println(bool?"成功":"失败");  
}   
} 

