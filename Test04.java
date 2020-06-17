
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class Test04 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("一个小窗口");
		frame.setSize(800, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(6, 1));
		JLabel username = new JLabel("User Name");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		TextField name = new TextField(20);
		//第一个面板
		JPanel panel1 = new JPanel();
		panel1.add(username);
		panel1.add(name);
		
		JLabel password = new JLabel("Password");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		JPasswordField psw = new JPasswordField(20);
		
		//第2个面板
		JPanel panel2 = new JPanel();
		panel2.add(password);
		panel2.add(psw);
		
		final JRadioButton rb1 = new JRadioButton("Mega Data");
		final JRadioButton rb2 = new JRadioButton("AI");
		final JRadioButton rb3 = new JRadioButton("UI/UE");
		ButtonGroup bg = new ButtonGroup();
		rb1.setSelected(true);
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		
		//第3个面板
		JPanel panel3 = new JPanel();
		panel3.add(rb1);
		panel3.add(rb2);
		panel3.add(rb3);
		
		JLabel year = new JLabel("Enrollment Time");
		String[] nian = {"2020","2019","2018","2017","2016","2015","2014","2013","2012","2011","2010"};
		JComboBox<String> combo = new JComboBox<String>(nian);
		//第4个面板
		JPanel panel4 = new JPanel();
		panel4.add(year);
		panel4.add(combo);
		
		JCheckBox cb1 = new JCheckBox("Java");
		JCheckBox cb2 = new JCheckBox("Python");
		JCheckBox cb3 = new JCheckBox("Web");
		JCheckBox cb4 = new JCheckBox("Linux");
		JCheckBox cb5 = new JCheckBox("Big Data");
		JCheckBox cb6 = new JCheckBox("AI");
		JCheckBox cb7 = new JCheckBox("PS");
		
		//第5个面板
		JPanel panel5 = new JPanel();
		panel5.add(cb1);
		panel5.add(cb2);
		panel5.add(cb3);
		panel5.add(cb4);
		panel5.add(cb5);
		panel5.add(cb6);
		panel5.add(cb7);
		
		JButton button = new JButton("Submit");
		JButton reset = new JButton("Reset");
		JButton close = new JButton("Close");
		//第6个面板
		JPanel panel6 = new JPanel();
		panel6.add(button);
		panel6.add(reset);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nametext = name.getText();
				if(nametext.equals("")) {
					JOptionPane.showMessageDialog(frame, "请输入用户名！");
				}
				if(nametext.length()>8) {
					JOptionPane.showMessageDialog(frame, "用户名不能超过8个字符！");
				}
				String pswtext = psw.getText();
				if(pswtext.equals("")) {
					JOptionPane.showMessageDialog(frame, "请输入密码！");
				}
				if(pswtext.length()>16) {
					JOptionPane.showMessageDialog(frame, "密码不能超过16个字符！");
				}
				String prof = "";
				if(rb1.isSelected()) {
					prof = "Mega Data";
				}
				if(rb2.isSelected()) {
					prof = "AI";
				}
				if(rb3.isSelected()) {
					prof = "UI/UE";
				}
				String entranceYear = combo.getSelectedItem().toString();
				StringBuffer hobby = new StringBuffer();
				if(cb1.isSelected()) {
					hobby.append("Java、");
				}
				if(cb2.isSelected()) {
					hobby.append("Python、");
				}
				if(cb3.isSelected()) {
					hobby.append("Web、");
				}
				if(cb4.isSelected()) {
					hobby.append("Linux、");
				}
				if(cb5.isSelected()) {
					hobby.append("Big Data、");
				}
				if(cb6.isSelected()) {
					hobby.append("AI、");
				}
				if(cb7.isSelected()) {
					hobby.append("PS、");
				}
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:/数据.docx",true));
					String total = nametext+"，"+pswtext+"，"+prof+"，"+entranceYear+"，"+hobby;
					if(!cb7.isSelected()) {
						total = total.substring(0, total.length()-1);
					}
					bw.write(total);
					bw.newLine();
					bw.close();
					JOptionPane.showMessageDialog(frame, "Success！");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				psw.setText("");
				rb1.setSelected(true);
				combo.setSelectedIndex(0);
				cb1.setSelected(false);
				cb2.setSelected(false);
				cb3.setSelected(false);
				cb4.setSelected(false);
				cb5.setSelected(false);
				cb6.setSelected(false);
				cb7.setSelected(false);
			}
		});
		Container c = frame.getContentPane();
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		c.add(panel4);
		c.add(panel5);
		c.add(panel6);
		frame.setVisible(true);
	}
}
