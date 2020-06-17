
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
		defaultModel.setRowCount(0);// 清空表格模型中的数据
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
		defaultModel = (DefaultTableModel) table.getModel();// 获得表格模型
		defaultModel.setRowCount(0);// 清空表格模型中的数据
		defaultModel.setColumnIdentifiers(new Object[] { "用户名", "密码", "方向", "入学年份", "爱好" });// 定义表头
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultModel);// 设置表格模型
		setTitle("学生列表");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		selecttable();
		c.add(scrollpanel, BorderLayout.CENTER);
		JPanel btnPanel = new JPanel();
		JButton addbtn = new JButton("添加");
		JButton delbtn = new JButton("删除");
		JButton updatebtn = new JButton("修改");
		JButton closebtn = new JButton("关闭");
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
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行数据进行删除！", "信息提示框",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						int n = JOptionPane.showConfirmDialog(getContentPane(), "确认删除吗？", "删除对话框",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (n == JOptionPane.YES_OPTION) { // 如果用户确认信息
							//创建输入流
							BufferedReader br = new BufferedReader(new FileReader(DataIns.dataPath));
							//创建集合
							List<String> list = new ArrayList<String>();
							String str = "";
							//通过输入流一次读取一行，放到集合里
							while ((str = br.readLine()) != null) {
								list.add(str);
							}
							br.close();
							//将选中的行进行删除
							list.remove(index);
							//创建输出流
							BufferedWriter bw = new BufferedWriter(new FileWriter(DataIns.dataPath));
							//获取集合的迭代器
							Iterator<String> it = list.iterator();
							//逐个迭代集合中的元素，将其写入到文件中
							while (it.hasNext()) {
								String row = it.next();
								bw.write(row);
								bw.newLine();
							}
							bw.close();
							//重新加载表格
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
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行数据进行修改！", "信息提示框",
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
