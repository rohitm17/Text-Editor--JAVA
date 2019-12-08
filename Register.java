import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JPanel implements ActionListener {
	JLabel userl=new JLabel("Choose a Username");
	JTextField usertf=new JTextField();
	JLabel passl=new JLabel("Password");
	JPasswordField passtf=new JPasswordField();
	JLabel passc=new JLabel("Password Conform");
	JPasswordField passtfc=new JPasswordField();
	JButton register=new JButton("REGISTER");
	JButton back =new JButton("BACK");

	public Register() {
		JPanel loginp =new JPanel();
		loginp.setLayout(new GridLayout(4,2));
		loginp.add(userl);
		loginp.add(usertf);
		loginp.add(passl);
		loginp.add(passtf);
		loginp.add(passc);
		loginp.add(passtfc);
		loginp.add(register);
		loginp.add(back);
		register.addActionListener(this);
		back.addActionListener(this);
		add(loginp);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent  e)
	{
		if(e.getSource()==register && passtf.getPassword().length>0 && usertf.getText().length()>0)
		{
			String pass =new String(passtf.getPassword());
			String conform=new String(passtfc.getPassword());
			if(pass.equals(conform)) {try {
				BufferedReader input=new BufferedReader(new FileReader("password.txt"));
				String line=input.readLine();
				while(line!=null) {
					StringTokenizer st=new StringTokenizer(line);
					if(usertf.getText().equals(st.nextToken()))
					{
						System.out.println("USER ALREADY EXISTS");
						return;
					}
					line=input.readLine();
						
				}
				input.close();
				MessageDigest md=MessageDigest.getInstance("SHA-256");
				md.update(pass.getBytes());
				byte byteData[]=md.digest();
				StringBuffer sb=new StringBuffer();
				for(int i=0;i<byteData.length;i++)
				{
					sb.append(Integer.toString((byteData[i] & 0xFF)+ 0x100,16).substring(1));
				}	
					BufferedWriter output=new BufferedWriter(new FileWriter("password.txt",true) );
					output.write(usertf.getText()+" "+sb.toString()+"\n");
					output.close();

					Login login=(Login)getParent();
					login.c1.show(login,"login");
					
				
				
			}catch(FileNotFoundException fe) {
				fe.printStackTrace();
			}catch(IOException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			
		}
		if(e.getSource()==back)
		{
			Login login=(Login)getParent();
			login.c1.show(login,"login");
			
		}
	}

}
