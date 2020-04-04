import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainMenu
{

	private JFrame frame;
	private JTextArea jt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu()
	{
		initialize();
	}
	String tempStr="";
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		MainMenu temp=this;
		frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Input Panel");
		
		JTextField jt1=new JTextField();
		JTextField jt2=new JTextField();
		JTextField jt3=new JTextField();
		JTextField jt4=new JTextField();
		
		jt1.setBounds(95,50,900,30);
		jt2.setBounds(95,100,900,30);
		jt3.setBounds(95,150,200,30);
		jt4.setBounds(95,200,200,30);
		
		JLabel l1=new JLabel("vrna:");
		JLabel l2=new JLabel("vacrna:");
		JLabel l3=new JLabel("population:");
		JLabel l4=new JLabel("alpha:");
		JLabel l5=new JLabel("Output: ");
		
		l1.setBounds(20,50, 80,30);
		l2.setBounds(20,100, 80,30);
		l3.setBounds(20,150, 80,30);
		l4.setBounds(20,200, 80,30);
		l5.setBounds(20,300, 80,30);
		jt = new JTextArea("Output here");
		jt.setBounds(95,300,500,500);
		JButton sub=new JButton("Initiate Algorithm");
		sub.setBounds(250,250,200,30);
		sub.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					jt.setText("Output Here");
					tempStr="";
					String s1=jt1.getText();
					String s2=jt2.getText();
					int pop=Integer.parseInt(jt3.getText());
					double alph=Double.parseDouble(jt4.getText());
					new MainCovid().evolveParams(s1,s2,pop,alph,temp);
				}
				catch(Exception exc)
				{
					System.out.println(exc);
				}
			}
		});
				
		

		
		frame.add(jt1);
		frame.add(jt2);
		frame.add(jt3);
		frame.add(jt4);
		
		frame.add(l1);
		frame.add(l2);
		frame.add(l3);
		frame.add(l4);
		frame.add(l5);
	
		frame.add(sub);
		frame.add(jt);
	}
	public void getOutputText(String str)
	{
		tempStr+=str+"\n";
		jt.setText(tempStr);
	}
}
