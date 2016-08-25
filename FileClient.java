package Unicast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileClient {
	static String str = "pet1.owl";
	public static void main(String args[]){
		Socket socket = new Socket();   
		boolean goon=true;
        try {
        	socket.connect(new InetSocketAddress("211.81.251.9", 9703));
        	DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			
			dos.writeUTF(str);
			//String string =null;
			//queryFile(string);
			LoadFile(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void queryFile(String string) {
		// TODO Auto-generated method stub
		
	}
	public static void LoadFile(Socket socket) throws IOException {  
        byte[] inputByte = null;  
        int length = 0;  
        DataInputStream dis = null;  
        FileOutputStream fos = null;  
        String filePath = "F:/code/otherpeer/"+GetDate.getDate()+str;  
        try {  
            try {  
                dis = new DataInputStream(socket.getInputStream());  
                File f = new File("F:/code/otherpeer");  
                if(!f.exists()){  
                    f.mkdir();    
                }  
                /*   
                 * 文件存储位置   
                 */  
                fos = new FileOutputStream(new File(filePath));      
                inputByte = new byte[1024];     
                System.out.println("开始接收数据...");    
                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {  
                    fos.write(inputByte, 0, length);  
                    fos.flush();      
                }  
                System.out.println("完成接收："+filePath);  
            } finally {  
                if (fos != null)  
                    fos.close();  
                if (dis != null)  
                    dis.close();  
                if (socket != null)  
                    socket.close();   
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
}
