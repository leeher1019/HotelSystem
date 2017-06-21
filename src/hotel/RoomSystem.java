package hotel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RoomSystem {
	JTabbedPane tp;
	JPanel plBook,
		   plCheckIn,
		   plCheckOut,
		   plA1,
		   plA2,
		   plA4,
		   plB1,
		   plB2,
		   plB4;
	JLabel lbRoomType_book,
		   lbDate,
		   lbStartY,
		   lbStartM,
		   lbStartD,
		   lbEndY,
		   lbEndM,
		   lbEndD,
		   lbQueryrlt,
		   lbClientName_book,
		   lbClientPhone_book,
		   lbClientId_book,
		   lbBookAmount,
		   lbRoomType_checkin,
		   lbClientName_checkin,
		   lbClientPhone_checkin,
		   lbClientId_checkin,
		   lbHasBooked,
		   lbDate_checkin,
		   lbStartY_checkin,
		   lbStartM_checkin,
		   lbStartD_checkin,
		   lbEndY_checkin,
		   lbEndM_checkin,
		   lbEndD_checkin,
		   lbBalancePayed,
		   lbRoomId;
	JTextField tfClientName_book,
			   tfClientPhone_book,
			   tfBookAmount,
			   
	   		   tfStartM,
	   		   tfStartD,
	   		   
	   		   tfEndM,
	   		   tfEndD,
			   tfClientName_checkin,
			   tfClientPhone_checkin,
			   tfClientId_checkin,
			   tfStartM_checkin,
	   		   tfStartD_checkin,
	   		   
	   		   tfEndM_checkin,
	   		   tfEndD_checkin,
			   tfRoomId;
	JComboBox<String> cbRoomType_checkin,
					  cbHasBooked,
					  cbBalancePayed,
					  cbStartY,
					  cbEndY,
					  cbRoomType_book,
					  cbStartY_checkin,
					  cbEndY_checkin;
					  
	JButton btQuery,
			btBook,
			btCheckOut;/*
			btA1_1,
			btA2_1,
			btA4_1,
			btB1_1,
			btB2_1,
			btB4_1;*/
	
	Room A1,
		 A2,
		 A4,
		 B1,
		 B2,
		 B4;
	
	
	ArrayList<Room> roomLList,
					roomList;
	
	static Boolean visible = false;
	
	public RoomSystem(){
		//------创建checkin表------------------------
		String attri = "roomId INTEGER PRIMARY KEY NOT NULL, "
				     + "clientName VARCHAR(255), "
				     + "clientPhone VARCHAR(255), "
				     + "clientId VARCHAR(255)";
				     
		DB.createTable("checkIn", attri);
		
		//------创建book表---------------------------
		attri = "bookId INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
			  + "roomType VARCHAR(255), "
			  + "clientName VARCHAR(255), "
			  + "clientPhone VARCHAR(255), "
			  + "clientId VARCHAR(255), "
			  + "checkInDate DATE, "
			  + "checkOutDate DATE, "
			  //+ "depositPayed VARCHAR(255), "
			  + "memOrNot VARCHAR(255), "
			  + "balancePayed VARCHAR(255)";
		DB.createTable("Book", attri);
				
		tp = new JTabbedPane();
		plBook = new JPanel();
		plCheckIn = new JPanel();
		plCheckOut = new JPanel();
		tp.setBounds(210, 88, 765, 356);
		plBook.setLayout(null);
		plCheckIn.setLayout(null);
		plCheckOut.setLayout(null);
		roomLList = new ArrayList<Room>();
		roomList = new ArrayList<Room>();
		
		BookInitial();
		CheckInInitial();
		CheckOutInitial();
		
		roomLList.add(A1);
		roomLList.add(A2);
		roomLList.add(A4);
		roomLList.add(B1);
		roomLList.add(B2);
		roomLList.add(B4);
		
		checkRoom();
		
		
		
		
		
		tp.addTab("预订", plBook);
		tp.addTab("入住", plCheckIn);
		tp.addTab("退房", plCheckOut);
		tp.setVisible(visible);
		
		
	}
	
	public void BookInitial(){
		//------label-------------------------------------
		lbRoomType_book = new JLabel("房间类型：");
		lbDate = new JLabel("日期：");
		lbStartY = new JLabel("年");
		lbStartM = new JLabel("月");
		lbStartD = new JLabel("日");
		lbEndY = new JLabel("年");
		lbEndM = new JLabel("月");
		lbEndD = new JLabel("日");
		lbClientName_book = new JLabel("姓名：");
		lbClientPhone_book = new JLabel("电话：");
		lbBookAmount = new JLabel("房间数：");
		lbQueryrlt = new JLabel();
		lbRoomType_book.setBounds(20, 20, 100, 20);
		lbDate.setBounds(20, 50, 100, 20);
		lbStartY.setBounds(155, 50, 50, 20);
		lbStartM.setBounds(205, 50, 50, 20);
		lbStartD.setBounds(255, 50, 50, 20);
		lbEndY.setBounds(155, 90, 50, 20);
		lbEndM.setBounds(205, 90, 50, 20);
		lbEndD.setBounds(255, 90, 50, 20);
		lbClientName_book.setBounds(400, 20, 100, 20);
		lbClientPhone_book.setBounds(400, 40, 100, 20);
		lbBookAmount.setBounds(400, 60, 100, 20);
		lbQueryrlt.setBounds(40, 150, 200, 20);
		lbQueryrlt.setHorizontalAlignment(SwingConstants.CENTER);
		
		plBook.add(lbRoomType_book);
		plBook.add(lbDate);
		plBook.add(lbStartY);
		plBook.add(lbStartM);
		plBook.add(lbStartD);
		plBook.add(lbEndY);
		plBook.add(lbEndM);
		plBook.add(lbEndD);
		plBook.add(lbClientName_book);
		plBook.add(lbClientPhone_book);
		plBook.add(lbBookAmount);
		plBook.add(lbQueryrlt);
		//------------------------------------------
		//------------textfield---------------------
		tfStartM = new JTextField();
		tfStartD = new JTextField();		
		tfEndM = new JTextField();
		tfEndD = new JTextField();		
		tfClientName_book = new JTextField();
		tfClientPhone_book = new JTextField();
		tfBookAmount = new JTextField();
		tfStartM.setBounds(175, 50, 25, 20);
		tfStartD.setBounds(225, 50, 25, 20);
		tfEndM.setBounds(175, 90, 25, 20);
		tfEndD.setBounds(225, 90, 25, 20);
		tfClientName_book.setBounds(455, 20, 200, 20);
		tfClientPhone_book.setBounds(455, 40, 200, 20);
		tfBookAmount.setBounds(455, 60, 200, 20);
		
		plBook.add(tfStartM);
		plBook.add(tfStartD);
		plBook.add(tfEndM);
		plBook.add(tfEndD);
		plBook.add(tfClientName_book);
		plBook.add(tfClientPhone_book);
		plBook.add(tfBookAmount);
		//------------------------------------------
		//------combobox-----------------------------
		cbStartY = new JComboBox<String>();
		cbEndY = new JComboBox<String>();
		cbRoomType_book = new JComboBox<String>();
		cbRoomType_book.addItem(new String("A1"));
		cbRoomType_book.addItem(new String("A2"));
		cbRoomType_book.addItem(new String("A4"));
		cbRoomType_book.addItem(new String("B1"));
		cbRoomType_book.addItem(new String("B2"));
		cbRoomType_book.addItem(new String("B4"));
		cbStartY.addItem(new String("2016"));
		cbStartY.addItem(new String("2017"));
		cbStartY.addItem(new String("2018"));
		cbStartY.addItem(new String("2019"));		
		cbEndY.addItem(new String("2016"));
		cbEndY.addItem(new String("2017"));
		cbEndY.addItem(new String("2018"));
		cbEndY.addItem(new String("2019"));
		cbStartY.setBounds(85, 50, 60, 20);
		cbEndY.setBounds(85, 90, 60, 20);
		cbRoomType_book.setBounds(85, 20, 60, 20);
		plBook.add(cbStartY);
		plBook.add(cbEndY);
		plBook.add(cbRoomType_book);
		//------------------------------------------
		//-----button-------------------------------
		btQuery = new JButton("查询");
		btBook = new JButton("预定");
		btQuery.setBounds(100, 250, 80, 20);
		btBook.setBounds(500, 250, 80, 20);
		plBook.add(btQuery);
		plBook.add(btBook);
		//-------------------------------------------
	}
	
	public void CheckInInitial(){
		plA1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plA2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plA4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plB1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plB2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		plB4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lbClientName_checkin = new JLabel("客户姓名：");
		lbClientPhone_checkin = new JLabel("客户电话：");
		lbClientId_checkin = new JLabel("客户身份证号：");
		lbBalancePayed = new JLabel("结清：");
		lbRoomType_checkin = new JLabel("房间类型：");
		lbHasBooked = new JLabel("预定与否：");
		lbDate_checkin = new JLabel("住至：");
		lbStartY_checkin = new JLabel("年");
		lbStartM_checkin = new JLabel("月");
		lbStartD_checkin = new JLabel("日");
		lbEndY_checkin = new JLabel("年");
		lbEndM_checkin = new JLabel("月");
		lbEndD_checkin = new JLabel("日");
		tfClientName_checkin = new JTextField();
		tfClientPhone_checkin = new JTextField();
		tfClientId_checkin = new JTextField();
		tfStartM_checkin = new JTextField();
		tfStartD_checkin = new JTextField();
		tfEndM_checkin = new JTextField();
		tfEndD_checkin = new JTextField();
		cbRoomType_checkin = new JComboBox<String>();
		cbBalancePayed = new JComboBox<String>();
		cbStartY_checkin = new JComboBox<String>();
		cbEndY_checkin = new JComboBox<String>();
		cbHasBooked = new JComboBox<String>();
		
		
		//初始化房间按钮
		String sql = "select * from room"; 
		try{
			DB.rlt = DB.stmt.executeQuery(sql);
			while(DB.rlt.next()){
				roomList.add(new Room(DB.rlt.getInt(1)));
				JButton test = roomList.get(DB.rlt.getInt(1) - 1).button;
				if (String.valueOf(DB.rlt.getString(2)).equals("A1"))
					plA1.add(test);				
				else if (String.valueOf(DB.rlt.getString(2)).equals("A2"))
					plA2.add(test);
				else if (String.valueOf(DB.rlt.getString(2)).equals("A4"))
					plA4.add(test);
				else if (String.valueOf(DB.rlt.getString(2)).equals("B1"))
					plB1.add(test);
				else if (String.valueOf(DB.rlt.getString(2)).equals("B2"))
					plB2.add(test);
				else if (String.valueOf(DB.rlt.getString(2)).equals("B4"))
					plB4.add(test);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		lbRoomType_checkin.setBounds(20, 20, 100, 20);
		lbClientName_checkin.setBounds(200, 40, 100, 20);
		lbClientPhone_checkin.setBounds(200, 60, 100, 20);
		lbClientId_checkin.setBounds(200, 80, 100, 20);
		lbBalancePayed.setBounds(200, 100, 100, 20);
		lbHasBooked.setBounds(200, 140, 100, 20);
		lbDate_checkin.setBounds(200, 180, 100, 20);
		lbStartY_checkin.setBounds(375, 140, 50, 20);
		lbStartM_checkin.setBounds(425, 140, 50, 20);
		lbStartD_checkin.setBounds(475, 140, 50, 20);
		lbEndY_checkin.setBounds(375, 180, 50, 20);
		lbEndM_checkin.setBounds(425, 180, 50, 20);
		lbEndD_checkin.setBounds(475, 180, 50, 20);
		
		tfClientName_checkin.setBounds(295, 40, 200, 20);
		tfClientPhone_checkin.setBounds(295, 60, 200, 20);
		tfClientId_checkin.setBounds(295, 80, 200, 20);
		tfStartM_checkin.setBounds(395, 140, 25, 20);
		tfStartD_checkin.setBounds(445, 140, 25, 20);		
		tfEndM_checkin.setBounds(395, 180, 25, 20);
		tfEndD_checkin.setBounds(445, 180, 25, 20);
		
		cbHasBooked.setBounds(295, 140, 50, 20);
		cbRoomType_checkin.setBounds(90, 20, 50, 20);
		cbBalancePayed.setBounds(295, 100, 50, 20);
		cbStartY_checkin.setBounds(295, 140, 60, 20);
		cbEndY_checkin.setBounds(295, 180, 60, 20);
		
		cbHasBooked.addItem("Yes");
		cbHasBooked.addItem("No");
		cbBalancePayed.addItem("Yes");
		cbBalancePayed.addItem("No");
		cbStartY_checkin.addItem("2016");
		cbStartY_checkin.addItem("2017");
		cbStartY_checkin.addItem("2018");
		cbStartY_checkin.addItem("2019");
		cbEndY_checkin.addItem("2016");
		cbEndY_checkin.addItem("2017");
		cbEndY_checkin.addItem("2018");
		cbEndY_checkin.addItem("2019");
		
		plCheckIn.add(lbClientName_checkin);
		plCheckIn.add(lbClientPhone_checkin);
		plCheckIn.add(lbClientId_checkin);
		plCheckIn.add(lbHasBooked);
		plCheckIn.add(lbBalancePayed);
		plCheckIn.add(tfClientName_checkin);
		plCheckIn.add(tfClientPhone_checkin);
		plCheckIn.add(tfClientId_checkin);
		plCheckIn.add(cbBalancePayed);
		plCheckIn.add(cbHasBooked);
		plCheckIn.add(lbDate_checkin);
		//plCheckIn.add(lbStartY_checkin);
		//plCheckIn.add(lbStartM_checkin);
		//plCheckIn.add(lbStartD_checkin);
		plCheckIn.add(lbEndY_checkin);
		plCheckIn.add(lbEndM_checkin);
		plCheckIn.add(lbEndD_checkin);
		//plCheckIn.add(tfStartM_checkin);
		//plCheckIn.add(tfStartD_checkin);
		plCheckIn.add(tfEndM_checkin);
		plCheckIn.add(tfEndD_checkin);
		//plCheckIn.add(cbStartY_checkin);
		plCheckIn.add(cbEndY_checkin);
		
		//------A1房间按钮-----------------
		plA1.setBounds(20, 60, 125, 250);
		//A1.button.setBounds(0, 0, 50, 20);
		//plA1.setBorder(new LineBorder(Color.CYAN, 14));
		//A1.button.setVisible(false);
		//plA1.add(A1.button);
		//plA1.add(btA2_1);
		//---------------------------------
		//------A2房间按钮------------------
		plA2.setBounds(20, 60, 125, 250);
		//A2.button.setBounds(0, 0, 50, 20);
		//A2.button.setVisible(false);
		//plA2.add(A2.button);
		//----------------------------------
		//------A4房间按钮------------------
		plA4.setBounds(20, 60, 125, 250);
		//A4.button.setBounds(0, 0, 50, 20);
		//A4.button.setVisible(false);
		//plA4.add(A4.button);
		//----------------------------------
		//------B1房间按钮------------------
		plB1.setBounds(20, 60, 125, 250);
		//B1.button.setBounds(0, 0, 50, 20);
		//B1.button.setVisible(false);
		//plB1.add(B1.button);
		//----------------------------------
		//------B2房间按钮------------------
		plB2.setBounds(20, 60, 125, 250);
		//B2.button.setBounds(0, 0, 50, 20);
		//B2.button.setVisible(false);
		//plB2.add(B2.button);
		//----------------------------------
		//------B4房间按钮------------------
		plB4.setBounds(20, 60, 125, 250);
		//B4.button.setBounds(0, 0, 50, 20);
		//B4.button.setVisible(false);
		//plB4.add(B4.button);
		//----------------------------------
				
		lbDate_checkin.setVisible(false);
		lbEndY_checkin.setVisible(false);
		lbEndM_checkin.setVisible(false);
		lbEndD_checkin.setVisible(false);
		cbEndY_checkin.setVisible(false);
		tfEndM_checkin.setVisible(false);
		tfEndD_checkin.setVisible(false);
		
		
		cbRoomType_checkin.addItem(new String("A1"));
		cbRoomType_checkin.addItem(new String("A2"));
		cbRoomType_checkin.addItem(new String("A4"));
		cbRoomType_checkin.addItem(new String("B1"));
		cbRoomType_checkin.addItem(new String("B2"));
		cbRoomType_checkin.addItem(new String("B4"));
		
		plCheckIn.add(lbRoomType_checkin);
		plCheckIn.add(cbRoomType_checkin);
		plCheckIn.add(plA1);
		plCheckIn.add(plA2);
		plCheckIn.add(plA4);
		plCheckIn.add(plB1);
		plCheckIn.add(plB2);
		plCheckIn.add(plB4);
	}
	
	public void CheckOutInitial(){
		lbRoomId = new JLabel("房间编号：");
		tfRoomId = new JTextField();
		btCheckOut = new JButton("退房");
		
		lbRoomId.setBounds(220, 50, 80, 20);
		tfRoomId.setBounds(290, 50, 200, 20);
		btCheckOut.setBounds(330, 200, 80, 20);
		plCheckOut.add(lbRoomId);
		plCheckOut.add(tfRoomId);
		plCheckOut.add(btCheckOut);
	}
	int remainRoom;
	public void btQueryRoom_click(JButton button){
		btQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				try{
					String sql = "select * from room where roomType='" + String.valueOf(cbRoomType_book.getSelectedItem()) + "'";
					DB.rlt = DB.stmt.executeQuery(sql);
					int roomCount = 0;
					while(DB.rlt.next())
						roomCount++;
					//---------------------------------------					
					DB.queryRowData("book", "checkInDate, checkOutDate", "roomType", String.valueOf(cbRoomType_book.getSelectedItem()));
					String bookQueryBegin = "",
						   bookQueryEnd = "";
					boolean couldBook = true;
					int queryRoomBooked = 0;
					while (DB.rlt.next()){
						bookQueryBegin = DB.rlt.getString(1);
						bookQueryEnd = DB.rlt.getString(2);
						couldBook = false;	//资料不为空
						
						String bookBegin = "",
							   bookEnd = "";
						if (!couldBook){
							if (Integer.valueOf(tfStartM.getText()) < 10)
								bookBegin = String.valueOf(cbStartY.getSelectedItem()) + "-0" + tfStartM.getText() + "-";
							else
								bookBegin = String.valueOf(cbStartY.getSelectedItem()) + "-" + tfStartM.getText() + "-";
							if (Integer.valueOf(tfStartD.getText()) < 10)
								bookBegin += ("0" +  tfStartD.getText());
							else
								bookBegin += tfStartD.getText();				
							if (Integer.valueOf(tfEndM.getText()) < 10)
								bookEnd = String.valueOf(cbEndY.getSelectedItem()) + "-0" + tfEndM.getText() + "-";
							else
								bookEnd = String.valueOf(cbEndY.getSelectedItem()) + "-" + tfEndM.getText() + "-";
							if (Integer.valueOf(tfEndD.getText()) < 10)
								bookEnd += ("0" +  tfEndD.getText());
							else
								bookEnd += tfEndD.getText();
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							Date dtQueryBegin = df.parse(bookQueryBegin);
							Date dtQueryEnd = df.parse(bookQueryEnd);
							Date dtBookBegin = df.parse(bookBegin);
							Date dtBookEnd = df.parse(bookEnd);
							if ((dtQueryBegin.getTime() <= dtBookBegin.getTime() && dtBookBegin.getTime() <= dtQueryEnd.getTime()) || 
								(dtQueryBegin.getTime() <= dtBookEnd.getTime() && dtBookEnd.getTime() <= dtQueryEnd.getTime()) ||
								 dtBookBegin.getTime() <= dtQueryBegin.getTime() && dtBookEnd.getTime() >= dtQueryEnd.getTime()){
								//lbQueryrlt.setText("请择他日~");
								//button.setEnabled(false);
								queryRoomBooked++;
							}
						}
					}
					if (couldBook){
						lbQueryrlt.setText("尚有 " + roomCount + " 间可预定！");
						remainRoom = roomCount;
						button.setEnabled(true);
					}else if (roomCount > queryRoomBooked){
						lbQueryrlt.setText("尚有 " + String.valueOf(roomCount - queryRoomBooked) + " 间可预定！");
						remainRoom = roomCount - queryRoomBooked;
						button.setEnabled(true);
					}else if (roomCount <= queryRoomBooked){
						lbQueryrlt.setText("已无空房");
						remainRoom = 0;
						button.setEnabled(true);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	public void btBook_click(){
		btBook.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				String column = "roomType, clientName, clientPhone, checkInDate, checkOutDate, memOrNot";
				String value = "";
				DB.queryRowData("member", "memPhone", "memPhone", tfClientPhone_book.getText());
				try{
					if (DB.rlt.next())
						value = "'" + String.valueOf(cbRoomType_book.getSelectedItem()) + "', '"
						      + tfClientName_book.getText() + "', '"
							  + tfClientPhone_book.getText() + "', '"
							  + String.valueOf(cbStartY.getSelectedItem()) + "-" + tfStartM.getText() + "-" + tfStartD.getText() + "', '"
							  + String.valueOf(cbEndY.getSelectedItem()) + "-" + tfEndM.getText() + "-" + tfEndD.getText() + "', 'Yes'";
					else{
						value = "'" + String.valueOf(cbRoomType_book.getSelectedItem()) + "', '"
							      + tfClientName_book.getText() + "', '"
								  + tfClientPhone_book.getText() + "', '"
								  + String.valueOf(cbStartY.getSelectedItem()) + "-" + tfStartM.getText() + "-" + tfStartD.getText() + "', '"
								  + String.valueOf(cbEndY.getSelectedItem()) + "-" + tfEndM.getText() + "-" + tfEndD.getText() + "', 'No'";
					}
					if (remainRoom >= Integer.valueOf(tfBookAmount.getText())){
						for (int i = 0; i < Integer.valueOf(tfBookAmount.getText()); i++)
							DB.addData("book", column, value);
						JOptionPane.showMessageDialog(null, "预定成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
						tfStartM.setText("");
						cbRoomType_book.setSelectedIndex(0);
						cbStartY.setSelectedIndex(0);
						tfStartD.setText("");
						cbEndY.setSelectedIndex(0);
						tfEndM.setText("");
						tfEndD.setText("");
						tfClientName_book.setText("");
						tfClientPhone_book.setText("");
						lbQueryrlt.setText("");
						tfBookAmount.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, "重新输入房间数！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
						tfBookAmount.setText("");
					}
				}catch(Exception e){
					e.printStackTrace();
				}		     
			}
		});
	}

	public void cbRoomType_select(){
		cbRoomType_checkin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("A1")){
					plA1.setVisible(true);
					plA2.setVisible(false);
					plA4.setVisible(false);
					plB1.setVisible(false);
					plB2.setVisible(false);
					plB4.setVisible(false);
				}
				else if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("A2")){
					plA1.setVisible(false);
					plA2.setVisible(true);
					plA4.setVisible(false);
					plB1.setVisible(false);
					plB2.setVisible(false);
					plB4.setVisible(false);
				}
				else if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("A4")){
					plA1.setVisible(false);
					plA2.setVisible(false);
					plA4.setVisible(true);
					plB1.setVisible(false);
					plB2.setVisible(false);
					plB4.setVisible(false);
				}
				else if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("B1")){
					plA1.setVisible(false);
					plA2.setVisible(false);
					plA4.setVisible(false);
					plB1.setVisible(true);
					plB2.setVisible(false);
					plB4.setVisible(false);
				}
				else if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("B2")){
					plA1.setVisible(false);
					plA2.setVisible(false);
					plA4.setVisible(false);
					plB1.setVisible(false);
					plB2.setVisible(true);
					plB4.setVisible(false);
				}
				else if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals("B4")){
					plA1.setVisible(false);
					plA2.setVisible(false);
					plA4.setVisible(false);
					plB1.setVisible(false);
					plB2.setVisible(false);
					plB4.setVisible(true);
				}
			}
		});
	}

	public void btCheckIn_click(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				boolean hasBooked = false;
				Date dt = new Date();
				String time = "";
				time = new SimpleDateFormat("yyyy-MM-dd").format(dt);				
				String column = "roomType, clientName, clientPhone";
				String value;
				DB.queryRowData("book", column, "checkInDate", time);
				try{
					while (DB.rlt.next()){
						if (String.valueOf(cbRoomType_checkin.getSelectedItem()).equals(DB.rlt.getString(1)) && 
							tfClientName_checkin.getText().equals(DB.rlt.getString(2)) && tfClientPhone_checkin.getText().equals(DB.rlt.getString(3)))
							hasBooked = true;
						else 
							hasBooked = false;						
					}					
				}catch(Exception e){
					e.printStackTrace();
				}
				//-------------------------------------------------------------------------------------------------------------------------------------------
				if (!hasBooked){					
					DB.queryRowData("member", "memPhone", "memPhone", tfClientPhone_checkin.getText());
					column = "roomType, clientName, clientPhone, clientId, checkInDate, checkOutDate, memOrNot, balancePayed";
					try{
						if (DB.rlt.next()){						
							value = "'" + String.valueOf(cbRoomType_checkin.getSelectedItem()) + "', '"
							             + tfClientName_checkin.getText() + "', '" 
									     + tfClientPhone_checkin.getText() + "', '" 
							             + tfClientId_checkin.getText() +  "', '" 
									     + time + "', '" 
							             + String.valueOf(cbEndY_checkin.getSelectedItem()) + "-" + tfEndM_checkin.getText() + "-" + tfEndD_checkin.getText() 
							             + "', 'Yes', '" 
							             + String.valueOf(cbBalancePayed.getSelectedItem()) + "'";
							DB.addData("Book", column, value);
						}else{
							value = "'" + String.valueOf(cbRoomType_checkin.getSelectedItem()) + "', '"
						             + tfClientName_checkin.getText() + "', '" 
								     + tfClientPhone_checkin.getText() + "', '" 
						             + tfClientId_checkin.getText() +  "', '" 
								     + time + "', '" 
						             + String.valueOf(cbEndY_checkin.getSelectedItem()) + "-" + tfEndM_checkin.getText() + "-" + tfEndD_checkin.getText() 
						             + "', 'No', '" 
						             + String.valueOf(cbBalancePayed.getSelectedItem()) + "'";
							DB.addData("Book", column, value);							
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					DB.modifyData("checkIn", "roomId", button.getText(), "clientName", tfClientName_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "clientPhone", tfClientPhone_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "clientId", tfClientId_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "balancePayed", String.valueOf(cbBalancePayed.getSelectedItem()));
					JOptionPane.showMessageDialog(null, "入住成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
					tfClientName_checkin.setText("");
					tfClientPhone_checkin.setText("");
					tfClientId_checkin.setText("");
					tfEndM_checkin.setText("");
					tfEndD_checkin.setText("");
					button.setEnabled(false);
				}
				else{
					column = "roomType, clientName, clientPhone";
					value = "'" + String.valueOf(cbRoomType_checkin.getSelectedItem()) + "', '"
					      + tfClientName_checkin.getText() + "', '"
						  + tfClientPhone_checkin.getText() + "'";
					DB.modifyDataCheckIn("book", "roomType", String.valueOf(cbRoomType_checkin.getSelectedItem()), "clientPhone", tfClientPhone_checkin.getText(), "clientId", tfClientId_checkin.getText());
					DB.modifyDataCheckIn("book", "roomType", String.valueOf(cbRoomType_checkin.getSelectedItem()), "clientPhone", tfClientPhone_checkin.getText(), "balancePayed", String.valueOf(cbBalancePayed.getSelectedItem()));
					DB.modifyData("checkIn", "roomId", button.getText(), "clientName", tfClientName_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "clientPhone", tfClientPhone_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "clientId", tfClientId_checkin.getText());
					DB.modifyData("checkIn", "roomId", button.getText(), "balancePayed", String.valueOf(cbBalancePayed.getSelectedItem()));
					JOptionPane.showMessageDialog(null, "入住成功！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
					tfClientName_checkin.setText("");
					tfClientPhone_checkin.setText("");
					tfClientId_checkin.setText("");
					tfEndM_checkin.setText("");
					tfEndD_checkin.setText("");
					button.setEnabled(false);
				}
					
			}
		});
	}
	
	public void btCheckOut_click(){
		btCheckOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				DB.modifyData2Null("checkIn", "roomId", tfRoomId.getText(), "clientName");
				DB.modifyData2Null("checkIn", "roomId", tfRoomId.getText(), "clientPhone");
				DB.modifyData2Null("checkIn", "roomId", tfRoomId.getText(), "clientId");
				DB.modifyData2Null("checkIn", "roomId", tfRoomId.getText(), "balancePayed");
				
				JOptionPane.showMessageDialog(null, "完成退房！", "确认信息", JOptionPane.INFORMATION_MESSAGE);
				for (int i = 0; i < roomList.size(); i++){
					if (String.valueOf(roomList.get(i).roomId).equals(tfRoomId.getText()))
						roomList.get(i).button.setEnabled(true);
				}
				tfRoomId.setText("");
			}
		});
	}
	
	public void checkRoom(){
		DB.queryNullData("checkIn", "roomId", "clientName");
		try{			
			while(DB.rlt.next())	
				for (int i = 0; i < roomList.size(); i++)
					if (roomList.get(i).roomId == DB.rlt.getInt(1)){
						roomList.get(i).button.setEnabled(false);
						break;
					}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cbHasBooked_select(){
		cbHasBooked.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				if (String.valueOf(cbHasBooked.getSelectedItem()).equals("Yes")){					
					lbDate_checkin.setVisible(false);
					lbEndY_checkin.setVisible(false);
					lbEndM_checkin.setVisible(false);
					lbEndD_checkin.setVisible(false);
					cbEndY_checkin.setVisible(false);
					tfEndM_checkin.setVisible(false);
					tfEndD_checkin.setVisible(false);
				}
				else if (String.valueOf(cbHasBooked.getSelectedItem()).equals("No")){
					lbDate_checkin.setVisible(true);
					lbEndY_checkin.setVisible(true);
					lbEndM_checkin.setVisible(true);
					lbEndD_checkin.setVisible(true);
					cbEndY_checkin.setVisible(true);
					tfEndM_checkin.setVisible(true);
					tfEndD_checkin.setVisible(true);
				}
				
			}
		});
	}
}


class Room{
	
	int roomId;
	JButton button;
	String clientId,
		   clientName,
		   clientPhone;
	
	public Room(int roomId ){
		this.roomId = roomId;
		button = new JButton(String.valueOf(roomId));
		clientId = "";
		clientName = "";
		clientPhone = "";
	}
	
}
