import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.xml.internal.security.Init;


class CalFrame implements ActionListener {

	private  JFrame mainFrame;
	private JTextField text;
	private JButton[] buttons;
	private char modifier='\0';	//�����	
	private double result;
	private boolean flag=false;	//��ǣ��Ƿ񵥻����������ť
	public CalFrame(){
		mainFrame=new JFrame("������");
		text=new JTextField();
		buttons=new JButton[16];
		init();
		setFontAndColor();
		addEventHandle();
	}
	
	public void show() {
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addEventHandle() {
		// TODO Auto-generated method stub
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this);
		}
	}


	private void setFontAndColor() {
		// TODO Auto-generated method stub
		Font f=new Font("����",Font.BOLD,20);
		text.setFont(f);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(f);
			buttons[i].setForeground(Color.RED);
		}
	}


	private void init() {
		// ��ʼ��
		JPanel north=new JPanel();
		JPanel center=new JPanel();
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(4,4,2,2));
		text=new JTextField(25);
		north.add(text);
		String str="123+456-780*0.=/";	//���ַ�������ʾ���а�ť
		for (int i = 0; i < 16; i++) {
			JButton jb=new JButton(String.valueOf(str.charAt(i)));
			buttons[i]=jb;
			center.add(jb);
		}
		mainFrame.add(north,BorderLayout.NORTH);
		mainFrame.add(center,BorderLayout.CENTER);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// ʵ�ֽӿڣ����ɷ��ʱ�����ȫ������
		String str=e.getActionCommand();	//��ð�ť�ϵ��ı�
		if ("0123456789.".indexOf(str)!=-1) {	//�����������
			if (flag) {		//����Ѿ��������������ť
				text.setText("");	//�ı���Ϊ��
				flag=false;
			}
			text.setText(text.getText()+str);	//�����ַ�
		}else if ("+-*/".indexOf(str)!=-1) {	//�����������ť
			modifier=str.charAt(0);
			double num=Double.valueOf(text.getText());
			result=num;
			flag=true;
		}else if (str.charAt(0)=='=') {		//�����������ť
			if (modifier=='+') {
				double num=Double.valueOf(text.getText());
				result+=num;
			}
			if (modifier=='-') {
				double num=Double.valueOf(text.getText());
				result-=num;
			}
			if (modifier=='*') {
				double num=Double.valueOf(text.getText());
				result*=num;
			}
			if (modifier=='/') {
				double num=Double.valueOf(text.getText());
				result/=num;
			}
			text.setText(result+"");
			modifier='\0';
			result=0;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
