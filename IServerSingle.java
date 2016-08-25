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

		System.out.println("S等待连接......");
		try {  
			final ServerSocket server = new ServerSocket(9701);  
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
                            System.out.println("S连接请求来自："+socket.getInetAddress().getHostAddress());
                            receive(socket);  
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
  
    protected static void receive(Socket socket) {
		// TODO Auto-generated method stub

    	try{
		//创建数据输入输出流
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		
		boolean goon=true;
		while(goon){		
			String string=dis.readUTF();			//从socket中读取数据
			if(!string.startsWith("?")){
				dealWith(string);			//服务器执行特定功能
				//dos.writeUTF("收到");				//向socket dos写数据
				//dos.flush();						//清空缓冲区，立即发送
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
		//关闭socket和流
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
		System.out.print("S接收到的信息为（"+string+"）；");	
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