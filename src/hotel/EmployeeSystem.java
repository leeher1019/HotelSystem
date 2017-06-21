package hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class EmployeeSystem {

	JTabbedPane tp;
	JPanel plOnWork,
		   plOffWork;
	JLabel lbId_OnWork,
		   lbId_OffWork;
	JTextField tfId_OnWork,
			   tfId_OffWork;
	JButton btOnWork,
			btOffWork;
	
	
	public EmployeeSystem(){
		tp = new JTabbedPane();
		plOnWork = new JPanel();
		plOffWork = new JPanel();
		lbId_OnWork = new JLabel("员工编号：");
		lbId_OffWork = new JLabel("员工编号：");
		tfId_OnWork = new JTextField();
		tfId_OffWork = new JTextField();
		btOnWork = new JButton("打卡上班");
		btOffWork = new JButton("打卡下班");
		
		tp.setBounds(210, 88, 765, 356);
		plOnWork.setLayout(null);				
		lbId_OnWork.setBounds(140, 40, 100, 20);
		tfId_OnWork.setBounds(210, 40, 100, 20);
		btOnWork.setBounds(275, 200, 100, 20);
		
		plOffWork.setLayout(null);
		lbId_OffWork.setBounds(140, 40, 100, 20);
		tfId_OffWork.setBounds(210, 40, 100, 20);
		btOffWork.setBounds(275, 200, 100, 20);
		
		plOnWork.add(lbId_OnWork);
		plOnWork.add(tfId_OnWork);
		plOnWork.add(btOnWork);
		plOffWork.add(lbId_OffWork);
		plOffWork.add(tfId_OffWork);
		plOffWork.add(btOffWork);
		
		tp.addTab("上班", plOnWork);
		tp.addTab("下班", plOffWork);
		/*
		DB.getDriver();
		DB.getConnection("Hotel");*/
		String attr = "Id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
					+ "empId VARCHAR(255), "
					+ "empDate DATE, "
					+ "empTime_On TIME, "
					+ "empTime_Off TIME";
		DB.createTable("EmpWorkTime", attr);
		tp.setVisible(false);
	}
	
	public void btOnWork_click(){
		btOnWork.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				Calendar cal = Calendar.getInstance();
				String day = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" +cal.get(Calendar.DATE);
				String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				String column = "empId, empDate, empTime_On";
				String value = "'" + tfId_OnWork.getText() + "', '" + day + "', '" + time + "'";
				DB.addData("empworktime", column, value);
				JOptionPane.showMessageDialog(null, "早安！", "确认信息", JOptionPane.INFORMATION_MESSAGE);			
				tfId_OnWork.setText("");
			}
		});
	}
	
	public void btOffWork_click(){
		btOffWork.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				Calendar cal = Calendar.getInstance();
				String day = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" +cal.get(Calendar.DATE);
				String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				DB.modifyDataCheckIn("empworktime", "empId", tfId_OffWork.getText(), "empDate", day, "empTime_Off", time);
				JOptionPane.showMessageDialog(null, "晚安！", "确认信息", JOptionPane.INFORMATION_MESSAGE);		
				tfId_OffWork.setText("");
			}
		});
	}
	
}
