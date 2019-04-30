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
			@SuppressWarnings("resource")
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
		@SuppressWarnings("unused")
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
					String msgin = dis.readUTF();// read the message 
					System.out.println(msgin);// print the incoming message
					for(int i=0; i<Clientsockets.size();i++)
					{
						Socket tempsock = Clientsockets.elementAt(i); // bring the socket for all in the chat room
						if(clientsocket.equals(tempsock))
						{
							continue; // skip if its the socket of the sender ( Don't send it back to him)
						}
						try {
						DataOutputStream dout = new DataOutputStream(tempsock.getOutputStream());// send the msg
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
