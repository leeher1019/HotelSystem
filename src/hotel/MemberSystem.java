package hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MemberSystem {
	JTabbedPane tp;
	JPanel plAddMember,
		   plChangePhone;
	JLabel lbName,
		   lbPhone,
		   lbBirth,
		   lbYear,
		   lbMonth,
		   lbDay,
		   lbOldPhone,
		   lbNewPhone;
	JTextField tfName,
			   tfPhone,
			   tfYear,
			   tfMonth,
			   tfDay,
			   tfOldPhone,
			   tfNewPhone;
	JButton btSave,
			btModify;
	static Boolean visible = false;
	
	
	public MemberSystem(){		
		tp = new JTabbedPane(JTabbedPane.TOP);
		plAddMember = new JPanel();
		plChangePhone = new JPanel();
		//------------新增会员--------------
		lbName = new JLabel("输入姓名：");
		lbPhone = new JLabel("输入电话：");
		lbBirth = new JLabel("输入生日：");
		lbYear = new JLabel("年");
		lbMonth = new JLabel("月");
		lbDay = new JLabel("日");
		tfName = new JTextField();
		tfPhone = new JTextField();
		tfYear = new JTextField();
		tfMonth = new JTextField();
		tfDay = new JTextField();
		btSave = new JButton("储存");
		tp.setBounds(210, 88, 765, 356);
		plChangePhone.setLayout(null);
		plAddMember.setLayout(null);
		lbName.setBounds(140, 40, 200, 20);		//(x坐标，y坐标，宽度，长度)
		lbPhone.setBounds(140, 60, 200, 20);
		lbBirth.setBounds(140, 80, 200, 20);
		lbYear.setBounds(280, 80, 50, 20);
		lbMonth.setBounds(350, 80, 50, 20);
		lbDay.setBounds(420, 80, 50, 20);
		
		tfName.setBounds(210, 40, 300, 20);
		tfPhone.setBounds(210, 60, 300, 20);
		tfYear.setBounds(210, 80, 60, 20);
		tfMonth.setBounds(300, 80, 40, 20);
		tfDay.setBounds(370, 80, 40, 20);
		
		btSave.setBounds(275, 200, 100, 20);
		plAddMember.add(lbName);
		plAddMember.add(lbPhone);
		plAddMember.add(lbBirth);
		plAddMember.add(lbYear);
		plAddMember.add(lbMonth);
		plAddMember.add(lbDay);
		plAddMember.add(tfName);
		plAddMember.add(tfPhone);
		plAddMember.add(tfYear);
		plAddMember.add(tfMonth);
		plAddMember.add(tfDay);
		plAddMember.add(btSave);
		//-------------------------------
		//-----------修改电话------------
		btModify = new JButton("修改");
		lbOldPhone = new JLabel("请输入旧电话：");
		lbNewPhone = new JLabel("请输入新电话：");
		tfOldPhone = new JTextField();
		tfNewPhone = new JTextField();
		
		
		lbOldPhone.setBounds(140, 40, 200, 20);
		lbNewPhone.setBounds(140, 60, 200, 20);
		tfOldPhone.setBounds(235, 40, 200, 20);
		tfNewPhone.setBounds(235, 60, 200, 20);
		btModify.setBounds(275, 200, 100, 20);
		plChangePhone.add(lbOldPhone);
		plChangePhone.add(lbNewPhone);
		plChangePhone.add(tfNewPhone);
		plChangePhone.add(tfOldPhone);
		plChangePhone.add(btModify);
		
		//------------------------------
		
		tp.addTab("新增会员", plAddMember);
		tp.addTab("更改电话", plChangePhone);
		/*
		DB.getDriver();
		DB.getConnection("hotel");*/
		String memAttri = "memId INTEGER NOT NULL PRIMARY KEY  AUTO_INCREMENT, "
						+ "memName VARCHAR(255), "
						+ "memPhone VARCHAR(255), "
						+ "memBirYear VARCHAR(255), "
						+ "memBirMonth VARCHAR(255), "
						+ "memBirDay VARCHAR(255)";
		DB.createTable("Member", memAttri);
		tp.setVisible(visible);
		
	}
	
	public void btSave_click(){
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String column = "memName, memPhone, memBirYear, memBirMonth, memBirDay";
				String value = "'" + tfName.getText() + "', '" + tfPhone.getText() + "', '" + tfYear.getText() + "', '" + tfMonth.getText() + "', '" + tfDay.getText() + "'";
				DB.addData("Member", column, value);
			}
		});
	}
	
	public void btModify_click(){
		btModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				DB.modifyData("Member", "memPhone", tfOldPhone.getText(), "memPhone", tfNewPhone.getText());
			}
		});
	}
}
