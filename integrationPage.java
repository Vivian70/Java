package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.directory.SearchResult;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class integrationPage extends JFrame {
	JScrollPane scrollpanel = null;
	JTable table = null;
	DefaultTableModel defaultModel = null;
	BufferedReader br = null;
	String str = null;

	public void selecttable() {
		defaultModel.setRowCount(0);// ��ձ��ģ���е�����
		try {
			br = new BufferedReader(new FileReader(DataIns.dataPath));
			while ((str = br.readLine()) != null) {
				String[] data = str.split(DataIns.split);
				defaultModel.addRow(data);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollpanel.setViewportView(table);

	}

	public integrationPage() {
		Container c = getContentPane();
		scrollpanel = new JScrollPane();
		table = new JTable();
		defaultModel = (DefaultTableModel) table.getModel();// ��ñ��ģ��
		defaultModel.setRowCount(0);// ��ձ��ģ���е�����
		defaultModel.setColumnIdentifiers(new Object[] { "Number", "Name", "City","Address"});// �����ͷ
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultModel);// ���ñ��ģ��
		setTitle("һ������С����");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		selecttable();
		c.add(scrollpanel, BorderLayout.CENTER);
		JPanel btnPanel = new JPanel();
		JButton addbtn = new JButton("Add");
		JButton delbtn = new JButton("Delete");
		JButton updatebtn = new JButton("Modify");
		JButton lookupbtn = new JButton("Lookup");
		JButton closebtn = new JButton("Close");
		JTextField t1=new JTextField(20);
		JPanel btnPanel2 = new JPanel(new FlowLayout(2,10,10));
		btnPanel.add(addbtn);
		btnPanel.add(updatebtn);
		btnPanel.add(delbtn);
		btnPanel2.add(t1);
		btnPanel2.add(lookupbtn);
		btnPanel.add(closebtn);
		c.add(btnPanel, BorderLayout.SOUTH);
		c.add(btnPanel2,BorderLayout.NORTH);
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPage addPage = new addPage();
				selecttable();
			}
		});
		
		lookupbtn.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				List<String> lookuplist = new ArrayList<String>();
				String l = t1.getText();
				try {
					BufferedReader br1 = new BufferedReader(new FileReader("D:/����.txt"));
					String a = "";
					List<String> beforelist = new ArrayList<String>();
					while((a = br1.readLine())!=null) {
						beforelist.add(a);
					}
					br1.close();
					int i = beforelist.size();
					for(int j=0; j<i; j++) {
						String b = beforelist.get(j);
						if(b.contains(l)) {
							lookuplist.add(b);
						}
					}
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				Iterator<String> it = lookuplist.iterator();
				defaultModel.setRowCount(0);
				while(it.hasNext()) {
					String row = it.next();
					String[] rows = row.split(DataIns.split);
					defaultModel.addRow(rows);
				}
			}
		});
		
		delbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Please select a row of data to delete��", "һ��С��ʾ��",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int n = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure to delete��", "һ��С��ʾ��",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (n == JOptionPane.YES_OPTION) { // ����û�ȷ����Ϣ
							//����������
							BufferedReader br = new BufferedReader(new FileReader(DataIns.dataPath));
							//��������
							List<String> list = new ArrayList<String>();
							String str = "";
							//ͨ��������һ�ζ�ȡһ�У��ŵ�������
							while ((str = br.readLine()) != null) {
								list.add(str);
							}
							br.close();
							//��ѡ�е��н���ɾ��
							list.remove(index);
							//���������
							BufferedWriter bw = new BufferedWriter(new FileWriter(DataIns.dataPath));
							//��ȡ���ϵĵ�����
							Iterator<String> it = list.iterator();
							//������������е�Ԫ�أ�����д�뵽�ļ���
							while (it.hasNext()) {
								String row = it.next();
								bw.write(row);
								bw.newLine();
							}
							bw.close();
							//���¼��ر��
							selecttable();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		updatebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "Please select a row of data to modify��", "һ��С��ʾ��",
							JOptionPane.WARNING_MESSAGE);
				}else {
					modifyPage modifyPage = new modifyPage(index);
					modifyPage.setVisible(true);
					selecttable();
				}
			}
		});
		
		closebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		integrationPage frame = new integrationPage();
		frame.setVisible(true);
	}

	
}