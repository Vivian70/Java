package project;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class modifyPage extends JDialog {
	private int index ;
	// ����������
	BufferedReader br = null;
	// ��������
	List<String> list = new ArrayList<String>();
	public modifyPage(int index) {
		this.index = index;
		setTitle("һ���޸�С����");
		setSize(700, 500);
		setModal(true);
		setLayout(new GridLayout(6, 1));
		JLabel number = new JLabel("Number");
		number.setHorizontalAlignment(SwingConstants.CENTER);
		TextField num = new TextField(20);
		//��һ�����
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
		address.setHorizontalAlignment(SwingConstants.CENTER);
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

		try {
			br = new BufferedReader(new FileReader(DataIns.dataPath));
			String str = "";
			// ͨ��������һ�ζ�ȡһ�У��ŵ�������
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
			br.close();
			String updateData = list.get(index);
			String[] data = updateData.split("��");
			num.setText(data[0]);
			n.setText(data[1]);
			adr.setText(data[2]);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		JButton button = new JButton("Submit");
		JButton reset = new JButton("Reset");
		JButton close = new JButton("Close");
		// ��5�����
		JPanel panel5 = new JPanel();
		panel5.add(button);
		panel5.add(reset);
		panel5.add(close);
		Container c = getContentPane();
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		c.add(panel4);
		c.add(panel5);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String numtext = num.getText();
				String ntext = n.getText();
				String adrtext = adr.getText();
				
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(DataIns.dataPath));
					String total = numtext + DataIns.split + ntext + DataIns.split +combo.getSelectedItem()+DataIns.split+adr.getText()+DataIns.split ;
					
					list.set(index, total);
					//��ȡ���ϵĵ�����
					Iterator<String> it = list.iterator();
					//������������е�Ԫ�أ�����д�뵽�ļ���
					while (it.hasNext()) {
						String row = it.next();
						bw.write(row);
						bw.newLine();
					}
					bw.close();
					JOptionPane.showMessageDialog(getContentPane(), "Data modified successfully��", "һ��С��ʾ��", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String updateData = list.get(index);
				String[] data = updateData.split("��");
				num.setText(data[0]);
				n.setText(data[1]);
				String enteranceYear = data[3];
				String hobby = data[4];
				String[] hobbys = hobby.split("��");
				
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

