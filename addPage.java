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
		setTitle("һ�����С����");
		setSize(700, 500);
		setModal(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(5, 1));
		JLabel number = new JLabel("Number");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		TextField num = new TextField(20);
		//��1�����
		JPanel panel1 = new JPanel();
		panel1.add(number);
		panel1.add(num);
		
		JLabel name = new JLabel("Name");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		TextField n = new TextField(20);
		//��2�����
		JPanel panel2 = new JPanel();
		panel2.add(name);
		panel2.add(n);
		
		JLabel address = new JLabel("Address");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		TextField adr = new TextField(20);
		//��3�����
		JPanel panel3 = new JPanel();
		panel3.add(address);
		panel3.add(adr);
		
		JLabel city = new JLabel("City��");
		String[] nian = {"����","����","����","����","����","����","�㶫","����","����","����",
				"�ӱ�","����","������","����","����","����","����","����","����","���ɹ�","����",
				"�ຣ","ɽ��","ɽ��","����","�Ϻ�","�Ĵ�","̨��","���","����","���","�½�","����","�㽭"};
		JComboBox<String> combo = new JComboBox<String>(nian);
		//��4�����
		JPanel panel4 = new JPanel();
		panel4.add(city);
		panel4.add(combo);
		
		JButton button = new JButton("Submit");
		JButton reset = new JButton("Reset");
		JButton close = new JButton("Close");
		//��5�����
		JPanel panel5 = new JPanel();
		panel5.add(button);
		panel5.add(reset);
		panel5.add(close);
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numtext = num.getText();
				if(numtext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the number��");
				}
				if(numtext.length()>7) {
					JOptionPane.showMessageDialog(getContentPane(), "Number cannot exceed 7 characters��");
				}
				String ntext = n.getText();
				if(ntext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the name��");
				}
				if(ntext.length()>4) {
					JOptionPane.showMessageDialog(getContentPane(), "Name cannot exceed 4 characters��");
				}
				String adrtext = adr.getText();
				if(adrtext.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter the address��");
				}
				
				
				
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("D:/����.txt",true));
					String total = numtext+"��"+ntext+"��"+combo.getSelectedItem()+"��"+adr.getText();
					bw.write(total);
					bw.newLine();
					bw.close();
					JOptionPane.showMessageDialog(getContentPane(), "Success��");
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

