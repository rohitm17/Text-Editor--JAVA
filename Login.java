import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel implements ActionListener {

	JLabel userl=new JLabel("Username : ");
	JTextField usertf=new JTextField();
	JLabel passl=new JLabel("Password : ");
	JPasswordField passtf=new JPasswordField();
	JPanel loginp =new JPanel(new GridLayout(3,2));
	JPanel panel=new JPanel();
	JButton login=new JButton("LOGIN");
	JButton register =new JButton("REGISTER");
	
	CardLayout c1;
	
	 Login() {
		 setLayout(new CardLayout());
		 loginp.add(userl);
		 loginp.add(usertf);
		 loginp.add(passl);
		 loginp.add(passtf);
		 
		 register.addActionListener(this);
		 login.addActionListener(this);
		 
		 loginp.add(login);
		 loginp.add(register);
		 
		 panel.add(loginp);
		 add(panel,"login");
		 
		 c1=(CardLayout) getLayout();
		 
		 
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login)
		{
			try {
				BufferedReader input=new BufferedReader(new FileReader("password.txt"));
				String pass=null;
				String line=input.readLine();
				while(line!=null)
				{
					StringTokenizer st=new StringTokenizer(line);
					if(usertf.getText().equals(st.nextToken()))
						pass=st.nextToken();
					line=input.readLine();
					
				}
				input.close();
				MessageDigest md=MessageDigest.getInstance("SHA-256");
				md.update(new String(passtf.getPassword()).getBytes());
				byte byteData[]=md.digest();
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<byteData.length;i++)
				{
					sb.append(Integer.toString((byteData[i] & 0xFF)+ 0x100,16).substring(1));
				}
				if(pass.equals(sb.toString()))
				{
					add(new FileBrowser(usertf.getText()),"fb");
					c1.show(this,"fb");
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==register)
		{
			
		
		add (new Register(),"register");
		c1.show(this, "register");
		}
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[])
	{
		JFrame frame=new JFrame("Text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		Login login=new Login();
		frame.add(login);
		frame.setVisible(true);
	}

}
