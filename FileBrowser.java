import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FileBrowser extends JPanel implements ActionListener
{

	public FileBrowser() {
		// TODO Auto-generated constructor stub
	}
	JLabel label=new JLabel("File List:");
	JButton newFile =new JButton ("New FIle");
	JButton open =new JButton("Open");
	JTextField newFileTf=new JTextField(10);
	ButtonGroup bg;
	File directory;
	public FileBrowser(String dir)
	{
		directory=new File(dir);
		directory.mkdir();
		JPanel fileList =new JPanel(new GridLayout(directory.listFiles().length+3,1));
		fileList.add(label);
		bg=new ButtonGroup();
		for(File file :directory.listFiles()) {
			JRadioButton radio =new JRadioButton (file.getName());
			radio.setActionCommand(file.getName());
			bg.add(radio);
			fileList.add(radio);
		}
		JPanel newP=new JPanel();
		newP.add(newFileTf);
		newP.add(newFile);
		newFile.addActionListener(this);
		open.addActionListener(this);
		fileList.add(open);
		fileList.add(newP);
		add(fileList);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Login login =(Login)getParent();
		
		if(e.getSource()==open)
		{
			login.add (new Editor(directory.getName()+"\\"+bg.getSelection().getActionCommand()),"editor");
			login.c1.show(login,"edtor");
					
		}
		if(e.getSource()==newFile)
		{
			String file =directory.getName()+"\\"+newFileTf.getText()+".txt";
			if(newFileTf.getText().length()>0 &&!(new File(file).exists()))
			{
				login.add(new Editor(file),"editor");
				login.c1.show(login,"editor");
			}
		}
		
	}

}
