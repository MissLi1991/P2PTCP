package Unicast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class IClient {
	
	public static void main(String[] args) {
		
		try {
			//创建连接到服务器的socket，服务器IP和端口如下
			Socket socket = new Socket("211.81.251.23",9701);
			//将数据输入输出流连接到socket上
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			String str = "animal.owl#211.81.253.23:9702";
			dos.writeUTF(str);
			boolean goon=true;
			//数据从终端输入
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			//反复读用户的数据并计算
			while(goon){
				String outString=bf.readLine();		//数据从终端输入
				dos.writeUTF(outString);			//写到Socket dos中
				dos.flush();						//清空缓冲区，立即发送
				String inString=dis.readUTF();		//从Socket dis中读数据
				if(!inString.equals("end")){
					System.out.println("C从服务器返回的结果是："+inString);
				}else{
					goon=false;
					System.out.println("C服务结束！！！");
				}
			}
			//关闭socket和流
			socket.close();
			dis.close();
			dos.close();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
}