import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame implements WindowListener {

	private JPanel contentPane;
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private JTextField textField;
    private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	
	public void windowActivated(WindowEvent arg0) {  
	}  
	public void windowClosed(WindowEvent arg0) {  
	}  
	public void windowClosing(WindowEvent arg0) {  
	    dispose();  
	}  
	public void windowDeactivated(WindowEvent arg0) {  
	}  
	public void windowDeiconified(WindowEvent arg0) {  
	}  
	public void windowIconified(WindowEvent arg0) {  
	}  
	public void windowOpened(WindowEvent arg0) {  
	
	  try {
		   conn =  DBHandler.getConn(); 
		   String query = "select * from user";
		   ps = conn.prepareStatement(query);
		   rs= ps.executeQuery(); 
		 while(rs.next())
		 {
			 System.out.println(rs.getString("username"));
			 System.out.println(rs.getString("Gender"));
		 }
		 }
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	  
	}  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		this.addWindowListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogic = new JLabel("Login");
		
		JLabel lblUserName = new JLabel("User Name:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			
				
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "select * from user where username=\""+textField.getText()+
						"\"and password=\""+textField_1.getText()+"\"";
						
						try {
							ps = conn.prepareStatement(query);
							rs =ps.executeQuery();
							Boolean found = rs.next();
							if(found)
							{
								 JOptionPane.showMessageDialog(null, "Login Succesfully");
							// client = new Client();
							//	client.setVisible(true);
							//	client.show();
								getContentPane().hide();
						
								 
							}else{
				                JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				            }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		});
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "insert into user(username,password,Nickname,Gender,Age) values(\""+textField.getText()+
						"\",\""+textField_1.getText()+"\",\"ay7aga2\",\"Male\",22)";
						
						try {
							ps = conn.prepareStatement(query);
							int done =ps.executeUpdate();
							
							if(done==1)
							{
								 JOptionPane.showMessageDialog(null, "Registered Succesfully");
				
								 
							}else{
				                JOptionPane.showMessageDialog(null, "Registeration Failed");
				            }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblUserName))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addGap(171))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(203)
					.addComponent(lblLogic)
					.addContainerGap(234, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(161)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(338, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(23))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addGap(41)
					.addComponent(lblLogic)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
