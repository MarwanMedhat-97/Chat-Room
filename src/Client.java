import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
public class Client extends JFrame implements WindowListener {
	
	public static String client_username="no_db_username";
	public static String client_Nickname="no_db_nickname";
	private Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;
	public JFrame frmClient;
	private JTextField textField;
	 Socket socket;
	 DataOutputStream dos;
	private static JTextArea textArea_1;

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client(client_username);
				window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Client(String username) {
		initialize();
	this.client_username=username;
		
		try {
			conn=new DBHandler().getConn();
			this.client_Nickname=getclientNickname();
			socket = new Socket("localhost", 6666);
			 dos = new DataOutputStream(socket.getOutputStream());
			 new Thread(new MessageUpdater(socket)).start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	static class MessageUpdater extends Thread
	{
		private Socket connection_socket;
		private DataInputStream textupdater;
		private String newmsg="def";
		
		public MessageUpdater(Socket sock) {
			//System.out.println("new msgupdater @"+sock.getPort()); for debugging
		this.connection_socket=sock;
			try {
				textupdater=new DataInputStream(connection_socket.getInputStream());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run()
		
		{
			
			
			while(true)
			{
				try {
					textArea_1.append("\n"+textupdater.readUTF());
					//System.out.println(newmsg); for debugging
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String getclientNickname()
	{
		String query = "select * from user where username=\""+client_username+"\";";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next())
			{
			String nickname= rs.getString("Nickname");
			return nickname;
			}
			else
				return "no_db_nickname";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "err_nickname";
		 
	}
	private void initialize() {
		frmClient = new JFrame();
		frmClient.getContentPane().setBackground(Color.DARK_GRAY);
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 564, 509);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.BOLD, 15));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					dos.writeUTF(client_Nickname+": "+textField.getText().trim());
					dos.flush();
					textArea_1.append("\n"+client_Nickname+": "+textField.getText());
					textField.setText("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		textArea_1 = new JTextArea();
		textArea_1.setForeground(Color.RED);
		textArea_1.setBackground(Color.LIGHT_GRAY);
		textArea_1.setFont(new Font("Consolas", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(frmClient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		frmClient.getContentPane().setLayout(groupLayout);
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
