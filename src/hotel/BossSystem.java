package hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BossSystem {

	JTabbedPane tp;
	JPanel plAddEmp,
		   plChangeJobTitle,
		   plQuery,
		   plModifyPrice,
		   plAddRoom,
		   plAddTitle,
		   plDelEmp,
		   plAccess,
		   plLogout;
	JLabel lbName,
		   lbPhone,
		   lbBirth,
		   lbTitle,
		   lbYear,
		   lbMonth,
		   lbDay,
		   lbNewPw_addemp,
	   	   lbAgainPw_addemp,
		   lbEmpId,
		   lbTitle_changetitle,
		   lbNewPw_changetitle,
		   lbAgainPw_changetitle,
		   lbEmpId_del,
		   lbAddRoom,
		   lbRoomType_Modify,
		   lbPrice_Modify,
		   lbMemOrNot,
		   lbAddTitle,
		   lbTitlePay,
		   lbPermissions,
		   lbEmpId_access,
		   lbPassword,
		   lbAccess,
		   lbDelEmp,
		   lbChangeTitle,
		   lbModifyPrice,
		   lbAddTitleState,
		   lbQuerySystem;
	
	JTextField tfName,
			   tfPhone,
			   tfYear,
			   tfMonth,
			   tfDay,
			   tfNewPw_addemp,
		   	   tfAgainPw_addemp,
			   tfEmpId,
			   tfNewPw_changetitle,
			   tfAgainPw_changetitle,
			   tfEmpId_del,
			   tfPrice_Modify,
			   tfTitleName,
			   tfTitlePay,
			   tfEmpId_access;
	JButton btAddEmp,
			btChangeTitle,
			btDelEmp,
			btQuery_del,
			btQuery_modifyprice,
			btAddRoom,
			btModifyPrice,
			btAddTitle,
			btLogin,
			btLogout;
	static JButton btQQuery;
	JComboBox<String> cbTitle,
					  cbChangeTitle,
					  cbAddRoom,
					  cbRoom,
					  cbRoomType,
					  cbMemOrNot,
					  cbPermissions;
	JPasswordField pfPassword;
	Query q;
	boolean NewPw_addemp = false,
			NewPw_changetitle = false;
	
	public BossSystem(){
		String attri = "empId INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
				+ "empName VARCHAR(255), "
				+ "empPhone VARCHAR(255), "
				+ "empBirth DATE, "
				+ "empTitle VARCHAR(255)";
		DB.createTable("empData", attri);
		attri = "roomId INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
				+ "roomType VARCHAR(255)";
		DB.createTable("Room", attri);
		attri = "empTitle VARCHAR(255) PRIMARY KEY, "
				+ "empPay VARCHAR(255), "
				+ "empPermissions VARCHAR(45)";
		DB.createTable("empTitle", attri);
		
		
		tp = new JTabbedPane();
		plAddEmp = new JPanel();
		plChangeJobTitle = new JPanel();
		plQuery = new JPanel();
		plModifyPrice = new JPanel();
		plAddRoom = new JPanel();
		plDelEmp = new JPanel();
		plAddTitle = new JPanel();
		plLogout = new JPanel();
		//------新增员工面板控件--------------------------------
		lbName = new JLabel("员工姓名：");
		lbPhone = new JLabel("员工电话：");
		lbBirth = new JLabel("员工生日：");
		lbYear = new JLabel("年");
		lbMonth = new JLabel("月");
		lbDay = new JLabel("日");
		lbTitle = new JLabel("职称：");
		lbNewPw_addemp = new JLabel("请输入您的密码：");
		lbAgainPw_addemp = new JLabel("请再输入您的密码：");
		tfName = new JTextField();
		tfPhone = new JTextField();
		tfYear = new JTextField();
		tfMonth = new JTextField();
		tfDay = new JTextField();
		tfNewPw_addemp = new JTextField();
		tfAgainPw_addemp = new JTextField();
		btAddEmp = new JButton("储存");
		cbTitle = new JComboBox<String>();
		tp.setBounds(210, 88, 765, 356);
		plAddEmp.setLayout(null);
		plChangeJobTitle.setLayout(null);
		plQuery.setLayout(null);
		plModifyPrice.setLayout(null);
		plAddRoom.setLayout(null);
		plDelEmp.setLayout(null);
		plAddTitle.setLayout(null);
		plLogout.setLayout(null);
		lbName.setBounds(140, 40, 200, 20);		//(x坐标，y坐标，宽度，长度)
		lbPhone.setBounds(140, 60, 200, 20);
		lbBirth.setBounds(140, 80, 200, 20);
		lbYear.setBounds(280, 80, 50, 20);
		lbMonth.setBounds(350, 80, 50, 20);
		lbDay.setBounds(420, 80, 50, 20);
		lbTitle.setBounds(140, 100, 200, 20);
		lbNewPw_addemp.setBounds(140, 140, 200, 20);
		lbAgainPw_addemp.setBounds(140, 160, 200, 20);
		tfName.setBounds(210, 40, 300, 20);
		tfPhone.setBounds(210, 60, 300, 20);
		tfYear.setBounds(210, 80, 60, 20);
		tfMonth.setBounds(300, 80, 40, 20);
		tfDay.setBounds(370, 80, 40, 20);
		tfNewPw_addemp.setBounds(260, 140, 200, 20);
		tfAgainPw_addemp.setBounds(260, 160, 200, 20);
		btAddEmp.setBounds(275, 200, 100, 20);		
		cbTitle.setBounds(210, 100, 80, 20);		
		cbTitle.addItem(new String("工读生"));
		cbTitle.addItem(new String("小主管"));
		cbTitle.addItem(new String("经理"));		
		lbNewPw_addemp.setVisible(false);
	    lbAgainPw_addemp.setVisible(false);
	    tfNewPw_addemp.setVisible(false);
	   	tfAgainPw_addemp.setVisible(false);
		
		plAddEmp.add(lbName);
		plAddEmp.add(lbPhone);
		plAddEmp.add(lbBirth);
		plAddEmp.add(lbYear);
		plAddEmp.add(lbMonth);
		plAddEmp.add(lbDay);
		plAddEmp.add(lbTitle);
		plAddEmp.add(lbNewPw_addemp);
		plAddEmp.add(lbAgainPw_addemp);
		plAddEmp.add(tfName);
		plAddEmp.add(tfPhone);
		plAddEmp.add(tfYear);
		plAddEmp.add(tfMonth);
		plAddEmp.add(tfDay);
		plAddEmp.add(tfNewPw_addemp);
		plAddEmp.add(tfAgainPw_addemp);
		plAddEmp.add(btAddEmp);
		plAddEmp.add(cbTitle);
		//---------------------------------------------
		//------删除员工控件--------------------------------
		lbEmpId_del = new JLabel("输入员工编号：");
		lbDelEmp = new JLabel();		   
		tfEmpId_del = new JTextField();
		btDelEmp = new JButton("删除");
		btQuery_del = new JButton("查询");
		lbEmpId_del.setBounds(140, 40, 200, 20);
		lbDelEmp.setBounds(75, 120, 500, 20);
		tfEmpId_del.setBounds(235, 40, 200, 20);
		btDelEmp.setBounds(275, 200, 100, 20);
		btQuery_del.setBounds(275, 160, 100, 20);
		lbDelEmp.setHorizontalAlignment(SwingConstants.CENTER);
		plDelEmp.add(lbEmpId_del);
		plDelEmp.add(lbDelEmp);
		plDelEmp.add(tfEmpId_del);
		plDelEmp.add(btDelEmp);
		plDelEmp.add(btQuery_del);
		//---------------------------------------------
		//------更改职位控件--------------------
		lbTitle_changetitle = new JLabel("选择欲变更职位：");
		lbEmpId = new JLabel("输入员工编号：");
		lbNewPw_changetitle = new JLabel("请输入您的密码：");
		lbAgainPw_changetitle = new JLabel("请再输入您的密码：");
		lbChangeTitle = new JLabel();		
		tfEmpId = new JTextField();
		tfNewPw_changetitle = new JTextField();
		tfAgainPw_changetitle = new JTextField();
		cbChangeTitle = new JComboBox<String>();
		btChangeTitle = new JButton("储存变更");
		lbTitle_changetitle.setBounds(140, 60, 200, 20);
		lbEmpId.setBounds(140, 40, 200, 20);
		lbNewPw_changetitle.setBounds(140, 140, 200, 20);
		lbAgainPw_changetitle.setBounds(140, 160, 200, 20);
		tfEmpId.setBounds(245, 40, 200, 20);
		tfNewPw_changetitle.setBounds(260, 140, 200, 20);
		tfAgainPw_changetitle.setBounds(260, 160, 200, 20);
		cbChangeTitle.setBounds(245, 60, 80, 20);
		btChangeTitle.setBounds(275, 200, 100, 20);
		cbChangeTitle.addItem(new String("工读生"));
		cbChangeTitle.addItem(new String("小主管"));
		cbChangeTitle.addItem(new String("经理"));
		lbNewPw_changetitle.setVisible(false);
		lbAgainPw_changetitle.setVisible(false);
	    tfNewPw_changetitle.setVisible(false);
	   	tfAgainPw_changetitle.setVisible(false);
		plChangeJobTitle.add(lbEmpId);
		plChangeJobTitle.add(lbTitle_changetitle);
		plChangeJobTitle.add(lbNewPw_changetitle);
		plChangeJobTitle.add(lbAgainPw_changetitle);
		plChangeJobTitle.add(tfEmpId);
		plChangeJobTitle.add(tfNewPw_changetitle);
		plChangeJobTitle.add(tfAgainPw_changetitle);
		plChangeJobTitle.add(cbChangeTitle);
		plChangeJobTitle.add(btChangeTitle);
		//---------------------------------------------
		//------修改价格控件-----------------------------
		lbRoomType_Modify = new JLabel("选择房型：");
		lbPrice_Modify = new JLabel("输入新价格：");
		lbMemOrNot = new JLabel("会员价与否：");
		lbModifyPrice = new JLabel();		
		tfPrice_Modify = new JTextField();
		cbRoomType = new JComboBox<String>();
		cbMemOrNot = new JComboBox<String>();
		btModifyPrice = new JButton("修改价格");
		btQuery_modifyprice = new JButton("查询");
		lbRoomType_Modify.setBounds(140, 60, 200, 20);
		lbPrice_Modify.setBounds(140, 80, 200, 20);
		lbMemOrNot.setBounds(140, 100, 200, 20);
		lbModifyPrice.setBounds(175, 140, 300, 20);
		cbRoomType.setBounds(220, 60, 80, 20);
		tfPrice_Modify.setBounds(220, 80, 200, 20);
		cbMemOrNot.setBounds(220, 100, 80, 20);
		btModifyPrice.setBounds(275, 200, 100, 20);
		btQuery_modifyprice.setBounds(275, 170, 100, 20);
		lbModifyPrice.setHorizontalAlignment(SwingConstants.CENTER);
		cbRoomType.addItem(new String("A1"));
		cbRoomType.addItem(new String("A2"));
		cbRoomType.addItem(new String("A4"));
		cbRoomType.addItem(new String("B1"));
		cbRoomType.addItem(new String("B2"));
		cbRoomType.addItem(new String("B4"));
		cbMemOrNot.addItem(new String("否"));
		cbMemOrNot.addItem(new String("是"));		
		plModifyPrice.add(lbRoomType_Modify);
		plModifyPrice.add(lbPrice_Modify);
		plModifyPrice.add(lbMemOrNot);
		plModifyPrice.add(tfPrice_Modify);
		plModifyPrice.add(cbRoomType);
		plModifyPrice.add(cbMemOrNot);
		plModifyPrice.add(btModifyPrice);
		plModifyPrice.add(btQuery_modifyprice);
		plModifyPrice.add(lbModifyPrice);
		//----------------------------------------------
		//------新增房间控件--------------------------------
		lbAddRoom = new JLabel("选择房间类型：");
		cbAddRoom = new JComboBox<String>();
		btAddRoom = new JButton("新增房间");
		lbAddRoom.setBounds(140, 60, 200, 20);
		cbAddRoom.setBounds(245, 60, 80, 20);
		btAddRoom.setBounds(275, 200, 100, 20);
		cbAddRoom.addItem(new String("A1"));
		cbAddRoom.addItem(new String("A2"));
		cbAddRoom.addItem(new String("A4"));
		cbAddRoom.addItem(new String("B1"));
		cbAddRoom.addItem(new String("B2"));
		cbAddRoom.addItem(new String("B4"));
		plAddRoom.add(lbAddRoom);
		plAddRoom.add(cbAddRoom);
		plAddRoom.add(btAddRoom);
		//---------------------------------------------
		//------新增职称控件----------------------------
		lbAddTitle = new JLabel("请输入新职称：");
		lbTitlePay = new JLabel("薪水：");		
		lbPermissions = new JLabel("权限与否：");
		lbAddTitleState = new JLabel();		
		tfTitleName = new JTextField();
		tfTitlePay = new JTextField();
		cbPermissions = new JComboBox<String>();
		btAddTitle = new JButton("新增职称");
		lbAddTitle.setBounds(140, 60, 200, 20);
		lbTitlePay.setBounds(140, 80, 200, 20);
		lbPermissions.setBounds(140,100, 200, 20);
		tfTitleName.setBounds(235, 60, 200, 20);
		tfTitlePay.setBounds(235, 80, 200, 20);
		cbPermissions.setBounds(235, 100, 80, 20);
		btAddTitle.setBounds(275, 200, 100, 20);
		cbPermissions.addItem(new String("Yes"));
		cbPermissions.addItem(new String("No"));
		plAddTitle.add(lbAddTitle);
		plAddTitle.add(lbTitlePay);
		plAddTitle.add(lbPermissions);		
		plAddTitle.add(tfTitleName);
		plAddTitle.add(tfTitlePay);
		plAddTitle.add(cbPermissions);
		plAddTitle.add(btAddTitle);		
		//---------------------------------------------
		//------登入系统-------------------------------
		plAccess = new JPanel();
		lbEmpId_access = new JLabel("员工编号：");
		lbPassword = new JLabel("密码：");
		lbAccess = new JLabel();
		tfEmpId_access = new JTextField();
		pfPassword = new JPasswordField();
		btLogin = new JButton("登入");
		plAccess.setLayout(null);
		plAccess.setBounds(210, 118, 765, 356);
		lbEmpId_access.setBounds(140, 40, 200, 20);
		lbPassword.setBounds(140, 60, 200, 20);
		lbAccess.setBounds(280, 120, 200, 20);
		tfEmpId_access.setBounds(235, 40, 200, 20);
		pfPassword.setBounds(235, 60, 200, 20);
		btLogin.setBounds(275, 200, 100, 20);
		
		plAccess.add(lbEmpId_access);
		plAccess.add(lbPassword);
		plAccess.add(lbAccess);
		plAccess.add(tfEmpId_access);
		plAccess.add(pfPassword);
		plAccess.add(btLogin);
		//--------------------------------------------
		//------查询控件------------------------------
		btQQuery = new JButton("查询");
		btQQuery.setBounds(275, 200, 100, 20);
		plQuery.add(btQQuery);
		//--------------------------------------------
		//------退出系统控件--------------------------
		btLogout = new JButton("退出系统");
		btLogout.setBounds(275, 200, 100, 20);
		plLogout.add(btLogout);
		//-------------------------------------------
		tp.addTab("新增员工", plAddEmp);
		tp.addTab("删除员工", plDelEmp);
		tp.addTab("变更职称", plChangeJobTitle);		
		tp.addTab("修改房间价格", plModifyPrice);
		tp.addTab("新增房间", plAddRoom);
		tp.addTab("新增职称", plAddTitle);
		tp.addTab("查询", plQuery);
		tp.addTab("退出系统", plLogout);
		
		
		q = new Query();
	}
	//done
	public void btAddEmp_click(){
		btAddEmp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				String column = "empName, empPhone, empBirth, empTitle";
				String value = "'" + tfName.getText() + "', '" + tfPhone.getText() + "', '" 
							 + tfYear.getText() + "-" + tfMonth.getText() + "-" + tfDay.getText() + "', '" 
							 + String.valueOf(cbTitle.getSelectedItem()) + "'";				
				if (NewPw_addemp){
					if (!tfNewPw_addemp.getText().equals("") && !tfAgainPw_addemp.getText().equals("") && tfNewPw_addemp.getText().equals(tfAgainPw_addemp.getText())){
						DB.addData("empData", column, value);
						column = "empId, password";
						DB.queryRowData("empData", "empId", "empPhone", tfPhone.getText());
						try{
							if(DB.rlt.next()){					
								value = "'" + DB.rlt.getString(1) + "', '" + tfAgainPw_addemp.getText() + "'";
								DB.addData("bossaccount", column, value);
								JOptionPane.showMessageDialog(null, "添加成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
								tfName.setText("");
								tfPhone.setText("");
								tfYear.setText("");
								tfDay.setText("");
								tfMonth.setText("");
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					else if (!tfNewPw_addemp.getText().equals("") && !tfAgainPw_addemp.getText().equals("") && !tfNewPw_addemp.getText().equals(tfAgainPw_addemp.getText()))
						System.out.println("请检查输入密码是否相同！");
					else if ((tfNewPw_addemp.getText().equals("") && tfAgainPw_addemp.getText().equals("")) || (!tfNewPw_addemp.getText().equals("") && tfAgainPw_addemp.getText().equals("")) ||
							 (tfNewPw_addemp.getText().equals("") && !tfAgainPw_addemp.getText().equals("")))
						System.out.println("请检查输入密码是否空白！");
				}
				else{
					DB.addData("empData", column, value);
					JOptionPane.showMessageDialog(null, "添加成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
					tfName.setText("");
					tfPhone.setText("");
					tfYear.setText("");
					tfDay.setText("");
					tfMonth.setText("");
				}
			}
		});
	}
	//done
	public void btChangeTitle_click(){
		btChangeTitle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){				
				if (NewPw_changetitle){
					if (!tfNewPw_changetitle.getText().equals("") && !tfAgainPw_changetitle.getText().equals("") && tfNewPw_changetitle.getText().equals(tfAgainPw_changetitle.getText())){
						DB.modifyData("empData", "empId", tfEmpId.getText(), "empTitle", String.valueOf(cbChangeTitle.getSelectedItem()));
						String column = "empId, password";
						String value = "'" + tfEmpId.getText() + "', '" + tfNewPw_changetitle.getText() + "'";
						DB.addData("bossaccount", column, value);
					}
					else if (!tfNewPw_changetitle.getText().equals("") && !tfAgainPw_changetitle.getText().equals("") && !tfNewPw_changetitle.getText().equals(tfAgainPw_changetitle.getText()))
						System.out.println("请检查输入密码是否相同！");
					else if ((tfNewPw_changetitle.getText().equals("") && tfAgainPw_changetitle.getText().equals("")) || (!tfNewPw_changetitle.getText().equals("") && tfAgainPw_changetitle.getText().equals("")) ||
							 (tfNewPw_changetitle.getText().equals("") && !tfAgainPw_changetitle.getText().equals("")))
						System.out.println("请检查输入密码是否空白！");					
				}
				else
					DB.modifyData("empData", "empId", tfEmpId.getText(), "empTitle", String.valueOf(cbChangeTitle.getSelectedItem()));
			}
		});
	}
	//done
	public void btDelEmp_click(){
		btDelEmp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				DB.delData("empData", "empId", tfEmpId_del.getText());
				JOptionPane.showMessageDialog(null, "删除成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
				tfEmpId_del.setText("");
				lbDelEmp.setText("");
			}
		});
	}
	//done
	public void btQuery_del_click(){
		btQuery_del.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				if (!tfEmpId_del.getText().equals("")){
					DB.queryRowData("empData", "empName, empPhone, empBirth, empTitle", "empId", tfEmpId_del.getText());
					try{
						DB.rlt.next();
						lbDelEmp.setText("员工姓名：" + DB.rlt.getString(1) + 
								         ", 员工电话：" + DB.rlt.getString(2) + 
								         ", 员工生日：" + DB.rlt.getString(3) +
								         ", 员工职位：" + DB.rlt.getString(4));
					}catch(Exception e){
						e.printStackTrace();
					}
				}else
					lbDelEmp.setText("请输入员工编号");
			}
		});
	}
	//done
	public void btQuery_modifyprice(){
		btQuery_modifyprice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){				
				DB.queryRowData("roomprice", "roomType, roomPrice, roomPrice_mem", "roomType", String.valueOf(cbRoomType.getSelectedItem()));
				try{
					DB.rlt.next();
					lbModifyPrice.setText("房间类型：" + DB.rlt.getString(1) + 
							         ", 价格：" + DB.rlt.getString(2) + 
							         ", 会员价：" + DB.rlt.getString(3));								         
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	//done
	public void btModifyPrice_click(){
		btModifyPrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				if (!tfPrice_Modify.getText().equals("")){
					if (String.valueOf(cbMemOrNot.getSelectedItem()).equals("否")){
						DB.modifyData("roomPrice", "roomType", String.valueOf(cbRoomType.getSelectedItem()), "roomPrice", tfPrice_Modify.getText());
						JOptionPane.showMessageDialog(null, "修改成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
						tfPrice_Modify.setText("");
						lbModifyPrice.setText("");
					}
					else
						DB.modifyData("roomPrice", "roomType", String.valueOf(cbRoomType.getSelectedItem()), "roomPrice_mem", tfPrice_Modify.getText());
				}
				else
					lbModifyPrice.setText("请输入价格");
			}
		});
	}
	//done
	
	public void btAddRoom_click(RoomSystem rs){
		btAddRoom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				DB.addData("Room", "roomType", "'" + String.valueOf(cbAddRoom.getSelectedItem()) + "'");
				String sql = "select roomId from room";
				try{
					DB.rlt = DB.stmt.executeQuery(sql);
					int count = 0;
					while(DB.rlt.next())
						count++;					
					rs.roomList.add(new Room(count));
					if (String.valueOf(cbAddRoom.getSelectedItem()).equals("A1")){											
						rs.plA1.add(rs.roomList.get(count - 1).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					else if (String.valueOf(cbAddRoom.getSelectedItem()).equals("A2")){						
						rs.plA2.add(rs.roomList.get(count - 1).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					else if (String.valueOf(cbAddRoom.getSelectedItem()).equals("A4")){						
						rs.plA4.add(rs.roomList.get(count - 1).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					else if (String.valueOf(cbAddRoom.getSelectedItem()).equals("B1")){						
						rs.plB1.add(rs.roomList.get(count - 1).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					else if (String.valueOf(cbAddRoom.getSelectedItem()).equals("B2")){						
						rs.plB2.add(rs.roomList.get(count - 1).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					else if (String.valueOf(cbAddRoom.getSelectedItem()).equals("B4")){						
						rs.plB4.add(new Room(count).button);
						rs.btCheckIn_click(rs.roomList.get(count - 1).button);
					}
					JOptionPane.showMessageDialog(null, "新增房间成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
					DB.addData("checkin", "roomId", String.valueOf(count));
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
	}
	//done
	public void btAddTitle_click(){
		btAddTitle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				String value = "'" + tfTitleName.getText() + "', '" + tfTitlePay.getText() + "', '" + String.valueOf(cbPermissions.getSelectedItem()) + "'";
				DB.addData("empTitle", "empTitle, empPay, empPermissions", value);
				JOptionPane.showMessageDialog(null, "新增成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
				tfTitleName.setText("");
				tfTitlePay.setText("");
			}
		});
	}

	public void btLogin_click(){
		btLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				DB.queryRowData("bossaccount", "empId, password", "empId", tfEmpId_access.getText());
				String account = " ",
					   password = "";
				try{
					if (DB.rlt.next()){
					account = DB.rlt.getString(1);	//1为开始		
					password = DB.rlt.getString(2);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				if (tfEmpId_access.getText().equals(account) && String.valueOf(pfPassword.getPassword()).equals(password)){
					tp.setSelectedIndex(0);
					tp.setVisible(true);
					plAccess.setVisible(false);
				}
				else if (tfEmpId_access.getText().equals(account) && !String.valueOf(pfPassword.getPassword()).equals(password))
					lbAccess.setText("密码错误！");
				else if (!tfEmpId_access.getText().equals(""))
					lbAccess.setText("此员工没有权限！");
				else if (tfEmpId_access.getText().equals("") || String.valueOf(pfPassword.getPassword()).equals(""))
					lbAccess.setText("账号、不能为空！");				
			} 
		});
	}
	//done
	public void btLogout_click(){
		btLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				btQQuery.setEnabled(true);
				q.frame.setVisible(false);
				q.tabbedPane.setSelectedIndex(0);
				tp.setVisible(false);
				plAccess.setVisible(true);
				tfEmpId_access.setText("");
				pfPassword.setText("");
			}
		});
	}
	//done
	public void cbTitle_select(){
		cbTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				DB.queryRowData("empTitle", "empPermissions", "empTitle", String.valueOf(cbTitle.getSelectedItem()));
				String permissions = new String();
				try{
					if(DB.rlt.next())
						permissions = DB.rlt.getString(1);
				}catch(Exception e){
					e.printStackTrace();
				}
				if (permissions.equals("Yes")){
					lbNewPw_addemp.setVisible(true);
					lbAgainPw_addemp.setVisible(true);
					tfNewPw_addemp.setVisible(true);
					tfAgainPw_addemp.setVisible(true);
					NewPw_addemp = true;
				}
				else{
					lbNewPw_addemp.setVisible(false);
					lbAgainPw_addemp.setVisible(false);
					tfNewPw_addemp.setVisible(false);
					tfAgainPw_addemp.setVisible(false);
					NewPw_addemp = false;
				}
			}
		});
	}
	//done
	public void cbChangeTitle_select(){
		cbChangeTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				DB.queryRowData("empTitle", "empPermissions", "empTitle", String.valueOf(cbChangeTitle.getSelectedItem()));
				String permissions = new String();
				try{
					DB.rlt.next();
					permissions = DB.rlt.getString(1);
				}catch(Exception e){
					e.printStackTrace();
				}
				if (permissions.equals("Yes")){
					lbNewPw_changetitle.setVisible(true);
					lbAgainPw_changetitle.setVisible(true);
					tfNewPw_changetitle.setVisible(true);
					tfAgainPw_changetitle.setVisible(true);
					NewPw_changetitle = true;
				}
				else{
					lbNewPw_changetitle.setVisible(false);
					lbAgainPw_changetitle.setVisible(false);
					tfNewPw_changetitle.setVisible(false);
					tfAgainPw_changetitle.setVisible(false);
					NewPw_changetitle = false;
				}
			}
		});
	}
	
	public void btQQuery_click(){
		btQQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				q.frame.setVisible(true);
				btQQuery.setEnabled(false);
			}
		});
	}

}
