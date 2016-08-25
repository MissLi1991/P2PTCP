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
			//�������ӵ���������socket��������IP�Ͷ˿�����
			Socket socket = new Socket("211.81.251.23",9701);
			//������������������ӵ�socket��
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			String str = "animal.owl#211.81.253.23:9702";
			dos.writeUTF(str);
			boolean goon=true;
			//���ݴ��ն�����
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			//�������û������ݲ�����
			while(goon){
				String outString=bf.readLine();		//���ݴ��ն�����
				dos.writeUTF(outString);			//д��Socket dos��
				dos.flush();						//��ջ���������������
				String inString=dis.readUTF();		//��Socket dis�ж�����
				if(!inString.equals("end")){
					System.out.println("C�ӷ��������صĽ���ǣ�"+inString);
				}else{
					goon=false;
					System.out.println("C�������������");
				}
			}
			//�ر�socket����
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