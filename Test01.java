
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class Test01 extends JDialog{
	public Test01() {
		setTitle("���ѧ����Ϣ");
		setSize(800, 500);
		setModal(true);
		setLayout(new GridLayout(6, 1));
		JLabel username = new JLabel("�û���:");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		TextField name = new TextField(20);
		//��һ�����
		JPanel panel1 = new JPanel();
		panel1.add(username);
		panel1.add(name);
		
		JLabel password = new JLabel("����:");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		JPasswordField psw = new JPasswordField(15);
		//��2�����
		JPanel panel2 = new JPanel();
		panel2.add(password);
		panel2.add(psw);
		
		final JRadioButton rb1 = new JRadioButton("�����ݷ���");
		final JRadioButton rb2 = new JRadioButton("�˹����ܷ���");
		final JRadioButton rb3 = new JRadioButton("UIUE����");
		ButtonGroup bg = new ButtonGroup();
		rb1.setSelected(true);
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		//��3�����
		JPanel panel3 = new JPanel();
		panel3.add(rb1);
		panel3.add(rb2);
		panel3.add(rb3);
		
		JLabel year = new JLabel("��ѧ��ݣ�");
		String[] nian = {"2014","2015","2016","2017","2018","2019","2020"};
		JComboBox<String> combo = new JComboBox<String>(nian);
		//��4�����
		JPanel panel4 = new JPanel();
		panel4.add(year);
		panel4.add(combo);
		
		JCheckBox cb1 = new JCheckBox("HTML5");
		JCheckBox cb2 = new JCheckBox("CSS3");
		JCheckBox cb3 = new JCheckBox("���ݿ�");
		JCheckBox cb4 = new JCheckBox("Python");
		JCheckBox cb5 = new JCheckBox("Java");
		JCheckBox cb6 = new JCheckBox("PS");
		JCheckBox cb7 = new JCheckBox("������ϴ");
		JCheckBox cb8 = new JCheckBox("�˹������㷨����");
		//��5�����
		JPanel panel5 = new JPanel();
		panel5.add(cb1);
		panel5.add(cb2);
		panel5.add(cb3);
		panel5.add(cb4);
		panel5.add(cb5);
		panel5.add(cb6);
		panel5.add(cb7);
		panel5.add(cb8);
		
		JButton button = new JButton("�ύ");
		JButton reset = new JButton("����");
		JButton close = new JButton("�ر�");
		//��6�����
		JPanel panel6 = new JPanel();
		panel6.add(button);
		panel6.add(reset);
		panel6.add(close);
		Container c = getContentPane();
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		c.add(panel4);
		c.add(panel5);
		c.add(panel6);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nametext = name.getText();
				String pswtext = psw.getText();
				String prof = "";
				if(rb1.isSelected()) {
					prof = "�����ݷ���";
				}
				if(rb2.isSelected()) {
					prof = "�˹����ܷ���";
				}
				if(rb3.isSelected()) {
					prof = "UIUE����";
				}
				String entranceYear = combo.getSelectedItem().toString();
				StringBuffer hobby = new StringBuffer();
				if(cb1.isSelected()) {
					hobby.append("HTML5��");
				}
				if(cb2.isSelected()) {
					hobby.append("CSS3��");
				}
				if(cb3.isSelected()) {
					hobby.append("���ݿ⡢");
				}
				if(cb4.isSelected()) {
					hobby.append("Python��");
				}
				if(cb5.isSelected()) {
					hobby.append("Java��");
				}
				if(cb6.isSelected()) {
					hobby.append("PS��");
				}
				if(cb7.isSelected()) {
					hobby.append("������ϴ��");
				}
				if(cb8.isSelected()) {
					hobby.append("�˹������㷨����");
				}
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(DataIns.dataPath,true));
					String total = nametext+DataIns.split+pswtext+DataIns.split+prof+DataIns.split+entranceYear+DataIns.split+hobby;
					if(!cb8.isSelected()) {
						total = total.substring(0, total.length()-1);
					}
					bw.write(total);
					bw.newLine();
					bw.close();
					JOptionPane.showMessageDialog(getContentPane(), "������ӳɹ���", "��Ϣ��ʾ��",
		                    JOptionPane.WARNING_MESSAGE);
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
				cb8.setSelected(false);
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
	}
	public void closeFrame() {
		this.setVisible(false);
	}
	
}
