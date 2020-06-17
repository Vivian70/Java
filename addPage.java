package project;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class addPage extends JDialog{
	
	public addPage() {
		setTitle("一个添加小窗口");
		setSize(700, 500);
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(5, 1));
		JLabel number = new JLabel("Number");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		TextField num = new TextField(20);
		//第1个面板
		JPanel panel1 = new JPanel();
		panel1.add(number);
		panel1.add(num);
		
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		TextField n = new TextField(20);
		//第2个面板
		JPanel panel2 = new JPanel();
		panel2.add(name);
		panel2.add(n);
		
		JLabel address = new JLabel("Address");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		TextField adr = new TextField(20);
		//第3个面板
		JPanel panel3 = new JPanel();
		panel3.add(address);
		panel3.add(adr);
		
		JLabel city = new JLabel("City：");
		String[] nian = {"澳门","安徽","北京","重庆","福建","甘肃","广东","广西","贵州","海南",
				"河北","河南","黑龙江","湖北","湖南","江苏","江西","吉林","辽宁","内蒙古","宁夏",
				"青海","山东","山西","陕西","上海","四川","台湾","天津","西藏","香港","新疆","云南","浙江"};
		JComboBox<String> combo = new JComboBox<String>(nian);
		//第4个面板
		JPanel panel4 = new JPanel();
		panel4.add(city);
		panel4.add(combo);
		
		JButton button = new JButton("Submit");
		JButton reset = new JButton("Reset");
		JButton close = new JButton("Close");
		//第5个面板
		JPanel panel5 = new JPanel();
		panel5.add(button);
		panel5.add(reset);
		panel5.add(close);
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numtext = num.getText();
				if(numtext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the number！");
				}
				if(numtext.length()>7) {
					JOptionPane.showMessageDialog(getContentPane(), "Number cannot exceed 7 characters！");
				}
				String ntext = n.getText();
				if(ntext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the name！");
				}
				if(ntext.length()>4) {
					JOptionPane.showMessageDialog(getContentPane(), "Name cannot exceed 4 characters！");
				}
				String adrtext = adr.getText();
				if(adrtext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the address！");
				}
				
				
				
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:/数据.txt",true));
					String total = numtext+"，"+ntext+"，"+combo.getSelectedItem()+"，"+adr.getText();
					bw.write(total);
					bw.newLine();
					bw.close();
					JOptionPane.showMessageDialog(getContentPane(), "Success！");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				num.setText("");
				n.setText("");
				adr.setText("");
			}
		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		
		Container c = getContentPane();
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		c.add(panel4);
		c.add(panel5);
		setVisible(true);
	}
	public void closeFrame() {
		this.setVisible(false);
	}
	
}

