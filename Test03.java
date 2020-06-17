
import java.awt.BorderLayout;
import java.awt.Container;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Test03 extends JFrame {
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

	public Test03() {
		Container c = getContentPane();
		scrollpanel = new JScrollPane();
		table = new JTable();
		defaultModel = (DefaultTableModel) table.getModel();// ��ñ��ģ��
		defaultModel.setRowCount(0);// ��ձ��ģ���е�����
		defaultModel.setColumnIdentifiers(new Object[] { "�û���", "����", "����", "��ѧ���", "����" });// �����ͷ
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultModel);// ���ñ��ģ��
		setTitle("ѧ���б�");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		selecttable();
		c.add(scrollpanel, BorderLayout.CENTER);
		JPanel btnPanel = new JPanel();
		JButton addbtn = new JButton("���");
		JButton delbtn = new JButton("ɾ��");
		JButton updatebtn = new JButton("�޸�");
		JButton closebtn = new JButton("�ر�");
		btnPanel.add(addbtn);
		btnPanel.add(updatebtn);
		btnPanel.add(delbtn);
		btnPanel.add(closebtn);
		c.add(btnPanel, BorderLayout.SOUTH);
		addbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test01 insertFrame = new Test01();
				insertFrame.setVisible(true);
				selecttable();
			}
		});
		delbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ�����ݽ���ɾ����", "��Ϣ��ʾ��",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int n = JOptionPane.showConfirmDialog(getContentPane(), "ȷ��ɾ����", "ɾ���Ի���",
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
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ�����ݽ����޸ģ�", "��Ϣ��ʾ��",
							JOptionPane.WARNING_MESSAGE);
				}else {
					Test02 updateFrame = new Test02(index);
					updateFrame.setVisible(true);
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
		Test03 frame = new Test03();
		frame.setVisible(true);
	}
}
