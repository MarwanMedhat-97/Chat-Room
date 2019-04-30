import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class LoginWindow {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
		
		 try {
			   conn =  DBHandler.getConn(); 
			/*   String query = "select * from user";
			   ps = conn.prepareStatement(query);
			   rs= ps.executeQuery(); 
			 while(rs.next())
			 {
				 System.out.println(rs.getString("username"));
				 System.out.println(rs.getString("Gender"));
			 }*/
			 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(Color.DARK_GRAY);
		frmLogin.getContentPane().setForeground(Color.DARK_GRAY);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 515, 484);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label = new JLabel("User Name:");
		label.setFont(new Font("Consolas", Font.BOLD, 18));
		label.setForeground(Color.YELLOW);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setFont(new Font("Consolas", Font.BOLD, 18));
		label_1.setForeground(Color.YELLOW);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("Login");
		label_2.setFont(new Font("Consolas", Font.BOLD, 24));
		label_2.setForeground(Color.YELLOW);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register reg = new Register();
				reg.frmRegisteration.setVisible(true);
				frmLogin.dispose();
				
			}
		});
		
		JButton button_1 = new JButton("Login");
		button_1.addActionListener(new ActionListener() {
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
							Client client = new Client(textField.getText());
							
							client.frmClient.setVisible(true);
							//client.show();
							frmLogin.dispose();
							//	client.show();
							//	getContentPane().hide();
						
								 
							}else{
				                JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				            }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(401, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(105)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addGap(19)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(134, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(58)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		frmLogin.getContentPane().setLayout(groupLayout);
	}
}
