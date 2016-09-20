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
	private char modifier='\0';	//运算符	
	private double result;
	private boolean flag=false;	//标记，是否单击了运算符按钮
	public CalFrame(){
		mainFrame=new JFrame("计算器");
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
		Font f=new Font("黑体",Font.BOLD,20);
		text.setFont(f);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(f);
			buttons[i].setForeground(Color.RED);
		}
	}


	private void init() {
		// 初始化
		JPanel north=new JPanel();
		JPanel center=new JPanel();
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(4,4,2,2));
		text=new JTextField(25);
		north.add(text);
		String str="123+456-780*0.=/";	//用字符串来表示所有按钮
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
		// 实现接口，即可访问本对象全部属性
		String str=e.getActionCommand();	//获得按钮上的文本
		if ("0123456789.".indexOf(str)!=-1) {	//输入的是数字
			if (flag) {		//如果已经单击了运算符按钮
				text.setText("");	//文本框为空
				flag=false;
			}
			text.setText(text.getText()+str);	//设置字符
		}else if ("+-*/".indexOf(str)!=-1) {	//单击运算符按钮
			modifier=str.charAt(0);
			double num=Double.valueOf(text.getText());
			result=num;
			flag=true;
		}else if (str.charAt(0)=='=') {		//单击运算符按钮
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
