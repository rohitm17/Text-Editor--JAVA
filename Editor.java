import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Editor extends JPanel implements ActionListener {

	File file;
	JButton save=new JButton("SAVE");
	JButton savec=new JButton("SAVE &CLOSE");
	JTextArea text=new JTextArea(20,40);
	
	
	public Editor(String s) {
		// TODO Auto-generated constructor stub
		file =new File(s);
		save.addActionListener(this);
		savec.addActionListener(this);
		if(file.exists())
		{
			try
			{
			BufferedReader input =new BufferedReader(new FileReader(file));
			String line=input.readLine();
			while(line!=null)
			{
				text.append(line+"\n");
				line=input.readLine();
			}
			input.close();
			}
			catch(IOException e)
			{
				
			}
		}
		add(save);
		add(savec);
		add(text);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FileWriter out;
		try {
			out = new FileWriter(file);
		
		out.write(text.getText());
		out.close();
		if(e.getSource()==savec)
		{
			Login login=(Login)getParent();
			login.c1.show(login,"editor");
			
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
