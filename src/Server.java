import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Vector;
public class Server {

	static Vector<Socket> Clientsockets;
	static int clientcount=0;
	
	public Server() throws IOException{
		// TODO Auto-generated constructor stub
		try {
			ServerSocket ss= new ServerSocket(6666);
			Clientsockets = new Vector<Socket>();

			while(true)
			{
			Socket clientsocket = ss.accept();
			System.out.println("New Connection at "+clientsocket);
			new Thread(new ClientHandler(clientsocket)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server= new Server();
		
		
	}

	static class ClientHandler extends Thread {
			
		Socket clientsocket;
		DataInputStream dis;
		DataOutputStream dos;
		
		ClientHandler(Socket clientsocket)  throws IOException {
				System.out.println("New ClientHandler @ "+clientsocket.getPort());
			this.clientsocket=clientsocket;
			dis = new DataInputStream(clientsocket.getInputStream());
			dos= new DataOutputStream(clientsocket.getOutputStream());
			clientcount++;
			Clientsockets.add(clientsocket);
			
		}
		
		public void run() {
			
			try {
				while(true) {
					String msgin = dis.readUTF();
					//System.out.println(msgin);
					for(int i=0; i<Clientsockets.size();i++)
					{
						Socket tempsock = Clientsockets.elementAt(i);
						if(clientsocket.equals(tempsock))
						{
							continue;
						}
						try {
						DataOutputStream dout = new DataOutputStream(tempsock.getOutputStream());
						dout.writeUTF(msgin);
						dout.flush();
						}catch(SocketException e) {
							continue;
						}
					
					}
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
