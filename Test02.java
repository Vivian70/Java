
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Dialog.ModalExclusionType;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Test02 extends JDialog {
	private int index ;
	// 创建输入流
	BufferedReader br = null;
	// 创建集合
	List<String> list = new ArrayList<String>();
	public Test02(int index) {
		this.index = index;
		setTitle("修改学生信息");
		setSize(800, 500);
		setModal(true);
		setLayout(new GridLayout(6, 1));
		JLabel username = new JLabel("用户名:");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField name = new JTextField(20);
		// 第一个面板
		JPanel panel1 = new JPanel();
		panel1.add(username);
		panel1.add(name);

		JLabel password = new JLabel("密码:");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField psw = new JTextField(20);
		// 第2个面板
		JPanel panel2 = new JPanel();
		panel2.add(password);
		panel2.add(psw);

		final JRadioButton rb1 = new JRadioButton("大数据方向");
		final JRadioButton rb2 = new JRadioButton("人工智能方向");
		final JRadioButton rb3 = new JRadioButton("UIUE方向");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		// 第3个面板
		JPanel panel3 = new JPanel();
		panel3.add(rb1);
		panel3.add(rb2);
		panel3.add(rb3);

		JLabel year = new JLabel("入学年份：");
		String[] nian = { "2014", "2015", "2016", "2017", "2018", "2019", "2020" };
		JComboBox<String> combo = new JComboBox<String>(nian);
		// 第4个面板
		JPanel panel4 = new JPanel();
		panel4.add(year);
		panel4.add(combo);

		JCheckBox cb1 = new JCheckBox("HTML5");
		JCheckBox cb2 = new JCheckBox("CSS3");
		JCheckBox cb3 = new JCheckBox("数据库");
		JCheckBox cb4 = new JCheckBox("Python");
		JCheckBox cb5 = new JCheckBox("Java");
		JCheckBox cb6 = new JCheckBox("PS");
		JCheckBox cb7 = new JCheckBox("数据清洗");
		JCheckBox cb8 = new JCheckBox("人工智能算法分析");
		// 第5个面板
		JPanel panel5 = new JPanel();
		panel5.add(cb1);
		panel5.add(cb2);
		panel5.add(cb3);
		panel5.add(cb4);
		panel5.add(cb5);
		panel5.add(cb6);
		panel5.add(cb7);
		panel5.add(cb8);
		try {
			br = new BufferedReader(new FileReader(DataIns.dataPath));
			String str = "";
			// 通过输入流一次读取一行，放到集合里
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
			br.close();
			String updateData = list.get(index);
			String[] data = updateData.split("，");
			name.setText(data[0]);
			psw.setText(data[1]);
			String prof = data[2];
			if(prof.equals("大数据方向")) {
				rb1.setSelected(true);
			}
			if(prof.equals("人工智能方向")) {
				rb2.setSelected(true);
			}
			if(prof.equals("UIUE方向")) {
				rb3.setSelected(true);
			}
			String enteranceYear = data[3];
			combo.setSelectedItem(enteranceYear);
			String hobby = data[4];
			String[] hobbys = hobby.split("、");
			for (int i = 0; i < hobbys.length; i++) {
				if(hobbys[i].equals("HTML5")) {
					cb1.setSelected(true);
				}
				if(hobbys[i].equals("CSS3")) {
					cb2.setSelected(true);
				}
				if(hobbys[i].equals("数据库")) {
					cb3.setSelected(true);
				}
				if(hobbys[i].equals("Python")) {
					cb4.setSelected(true);
				}
				if(hobbys[i].equals("Java")) {
					cb5.setSelected(true);
				}
				if(hobbys[i].equals("PS")) {
					cb6.setSelected(true);
				}
				if(hobbys[i].equals("数据清洗")) {
					cb7.setSelected(true);
				}
				if(hobbys[i].equals("人工智能算法分析")) {
					cb8.setSelected(true);
				}
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JButton button = new JButton("提交");
		JButton reset = new JButton("重置");
		JButton close = new JButton("关闭");
		// 第6个面板
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
				if (rb1.isSelected()) {
					prof = "大数据方向";
				}
				if (rb2.isSelected()) {
					prof = "人工智能方向";
				}
				if (rb3.isSelected()) {
					prof = "UIUE方向";
				}
				String entranceYear = combo.getSelectedItem().toString();
				StringBuffer hobby = new StringBuffer();
				if (cb1.isSelected()) {
					hobby.append("HTML5、");
				}
				if (cb2.isSelected()) {
					hobby.append("CSS3、");
				}
				if (cb3.isSelected()) {
					hobby.append("数据库、");
				}
				if (cb4.isSelected()) {
					hobby.append("Python、");
				}
				if (cb5.isSelected()) {
					hobby.append("Java、");
				}
				if (cb6.isSelected()) {
					hobby.append("PS、");
				}
				if (cb7.isSelected()) {
					hobby.append("数据清洗、");
				}
				if (cb8.isSelected()) {
					hobby.append("人工智能算法分析");
				}
				try {
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(DataIns.dataPath));
					String total = nametext + DataIns.split + pswtext + DataIns.split + prof + DataIns.split
							+ entranceYear + DataIns.split + hobby;
					if (!cb8.isSelected()) {
						total = total.substring(0, total.length() - 1);
					}
					list.set(index, total);
					//获取集合的迭代器
					Iterator<String> it = list.iterator();
					//逐个迭代集合中的元素，将其写入到文件中
					while (it.hasNext()) {
						String row = it.next();
						bw.write(row);
						bw.newLine();
					}
					bw.close();
					JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！", "信息提示框", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String updateData = list.get(index);
				String[] data = updateData.split("，");
				name.setText(data[0]);
				psw.setText(data[1]);
				String prof = data[2];
				if(prof.equals("大数据方向")) {
					rb1.setSelected(true);
				}
				if(prof.equals("人工智能方向")) {
					rb2.setSelected(true);
				}
				if(prof.equals("UIUE方向")) {
					rb3.setSelected(true);
				}
				String enteranceYear = data[3];
				combo.setSelectedItem(enteranceYear);
				cb1.setSelected(false);
				cb2.setSelected(false);
				cb3.setSelected(false);
				cb4.setSelected(false);
				cb5.setSelected(false);
				cb6.setSelected(false);
				cb7.setSelected(false);
				cb8.setSelected(false);
				String hobby = data[4];
				String[] hobbys = hobby.split("、");
				for (int i = 0; i < hobbys.length; i++) {
					if(hobbys[i].equals("HTML5")) {
						cb1.setSelected(true);
					}
					if(hobbys[i].equals("CSS3")) {
						cb2.setSelected(true);
					}
					if(hobbys[i].equals("数据库")) {
						cb3.setSelected(true);
					}
					if(hobbys[i].equals("Python")) {
						cb4.setSelected(true);
					}
					if(hobbys[i].equals("Java")) {
						cb5.setSelected(true);
					}
					if(hobbys[i].equals("PS")) {
						cb6.setSelected(true);
					}
					if(hobbys[i].equals("数据清洗")) {
						cb7.setSelected(true);
					}
					if(hobbys[i].equals("人工智能算法分析")) {
						cb8.setSelected(true);
					}
				}
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
