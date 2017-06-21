package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Query {

	public JFrame frame;
	private JTextField tfRoomId;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Query() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	JLabel lbShowId,
		   lbShowPhone,
		   lbShowName,
		   lbShowMoney,
		   lbClientName,
		   lbClientPhone,
		   lbClientId,
		   lbShowRoomAmount;
	JTabbedPane tabbedPane;
	JComboBox cbRoomType,
			  cbYear,
			  cbMonth;
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u67E5\u8BE2\u7CFB\u7EDF");
		frame.setBounds(100, 100, 720, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 702, 400);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("客房客户资料", null, panel, null);
		panel.setLayout(null);
		
		JButton btClientData = new JButton("\u67E5\u8BE2");
		btClientData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DB.queryRowData("checkin", "clientName, clientPhone, clientId", "roomId", tfRoomId.getText());
				try{
					if (DB.rlt.next()){					
						lbShowName.setText(DB.rlt.getString(1));
						lbShowPhone.setText(DB.rlt.getString(2));
						lbShowId.setText(DB.rlt.getString(3));
					}
				}catch(Exception e){
					
				}
			}
		});
		btClientData.setBounds(287, 294, 99, 27);
		panel.add(btClientData);
		
		JLabel lblNewLabel = new JLabel("\u5BA2\u623F\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(214, 62, 84, 19);
		panel.add(lblNewLabel);
		
		tfRoomId = new JTextField();
		tfRoomId.setBounds(291, 59, 116, 25);
		panel.add(tfRoomId);
		
		
		lbClientName = new JLabel("\u5BA2\u6237\u59D3\u540D\uFF1A");
		lbClientName.setBounds(214, 110, 84, 19);
		panel.add(lbClientName);
		
		lbClientPhone = new JLabel("\u5BA2\u6237\u7535\u8BDD\uFF1A");
		lbClientPhone.setBounds(214, 161, 84, 19);
		panel.add(lbClientPhone);
		
		lbClientId = new JLabel("\u5BA2\u6237\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		lbClientId.setBounds(214, 208, 116, 19);
		panel.add(lbClientId);
		
		lbShowName = new JLabel("");
		lbShowName.setBounds(291, 110, 182, 19);
		panel.add(lbShowName);
		
		lbShowPhone = new JLabel("");
		lbShowPhone.setBounds(291, 161, 182, 19);
		panel.add(lbShowPhone);
		
		lbShowId = new JLabel("");
		lbShowId.setBounds(321, 208, 182, 19);
		panel.add(lbShowId);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("查询收入、单量", null, panel_1, null);
		panel_1.setLayout(null);
		
		cbMonth = new JComboBox();
		cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbMonth.setBounds(112, 85, 44, 25);
		panel_1.add(cbMonth);
		
		cbRoomType = new JComboBox();
		cbRoomType.setModel(new DefaultComboBoxModel(new String[] {"所有房间", "A1", "A2", "A4", "B1", "B2", "B4"}));
		cbRoomType.setBounds(170, 121, 87, 25);
		panel_1.add(cbRoomType);
		
		cbYear = new JComboBox();
		cbYear.setModel(new DefaultComboBoxModel(new String[] {"2016", "2017", "2018", "2019"}));
		cbYear.setBounds(112, 46, 57, 25);
		panel_1.add(cbYear);
		
		JLabel label = new JLabel("月");
		label.setBounds(170, 88, 57, 19);
		panel_1.add(label);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (String.valueOf(cbRoomType.getSelectedItem()).equals("所有房间")){
					int income = 0;
					int roomCheckinAmount = 0;
					try{//会员						
						String sql = "select roomPrice.roomPrice_mem, book.checkInDate, book.checkOutDate "
								   + "from roomPrice, book "
								   + "where book.memOrNot = 'Yes' AND "							   
								   +       "book.balancePayed = 'Yes' AND "
								   +       "book.roomType = roomPrice.roomType AND "
								   +       "year(book.checkInDate) = " + String.valueOf(cbYear.getSelectedItem()) + " AND "
								   +       "month(book.checkInDate) = " + String.valueOf(cbMonth.getSelectedItem());
					
						DB.rlt = DB.stmt.executeQuery(sql);
						while (DB.rlt.next()){/*
							int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
							income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;*/
							roomCheckinAmount++;
							String[] a = DB.rlt.getString(2).split("-");
							String[] b = DB.rlt.getString(3).split("-");
							if (a[1].equals("12") && b[1].equals("01")){
								Calendar cal = Calendar.getInstance();
								cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())), 11, 31);
								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
								String strDate = sf.format(cal.getTime());
								Date date = new Date();
								date = sf.parse(strDate);
								int daydiff = (int)(date.getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
								cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())) + 1, 0, 1);
								strDate = sf.format(cal.getTime());
								date = sf.parse(strDate);
								daydiff += (int)(DB.rlt.getDate(3).getTime() - date.getTime()) / 1000 / 60 / 60 / 24 + 1;
								income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
							}
							else{
								int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
								income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
							}
						}
						sql = "select roomPrice.roomPrice, book.checkInDate, book.checkOutDate "
								   + "from roomPrice, book "
								   + "where book.memOrNot = 'No' AND "							   
								   +       "book.balancePayed = 'Yes' AND "
								   +       "book.roomType = roomPrice.roomType AND "
								   +       "year(book.checkInDate) = " + String.valueOf(cbYear.getSelectedItem()) + " AND "
								   +       "month(book.checkInDate) = " + String.valueOf(cbMonth.getSelectedItem());
						DB.rlt = DB.stmt.executeQuery(sql);
						while (DB.rlt.next()){/*
							int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
							income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;*/
							roomCheckinAmount++;
							String[] a = DB.rlt.getString(2).split("-");
							String[] b = DB.rlt.getString(3).split("-");
							if (a[1].equals("12") && b[1].equals("01")){
								Calendar cal = Calendar.getInstance();
								cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())), 11, 31);
								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
								String strDate = sf.format(cal.getTime());
								Date date = new Date();
								date = sf.parse(strDate);
								int daydiff = (int)(date.getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
								cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())) + 1, 0, 1);
								strDate = sf.format(cal.getTime());
								date = sf.parse(strDate);
								daydiff += (int)(DB.rlt.getDate(3).getTime() - date.getTime()) / 1000 / 60 / 60 / 24 + 1;
								income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
							}
							else{
								int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
								income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
							}
						}
						lbShowMoney.setText(String.valueOf(income));						
						lbShowRoomAmount.setText(String.valueOf(roomCheckinAmount));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("A1"))
					QueryRoomIncome("A1");
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("A2"))
					QueryRoomIncome("A2");
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("A4"))
					QueryRoomIncome("A4");
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("B1"))
					QueryRoomIncome("B1");
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("B2"))
					QueryRoomIncome("B2");
				else if (String.valueOf(cbRoomType.getSelectedItem()).equals("B4"))
					QueryRoomIncome("B4");
					
				
			}
		});
		button.setBounds(246, 275, 99, 27);
		panel_1.add(button);
		
		JLabel label_1 = new JLabel("营收：");
		label_1.setBounds(112, 159, 57, 19);
		panel_1.add(label_1);
		
		lbShowMoney = new JLabel("");
		lbShowMoney.setBounds(160, 159, 57, 19);
		panel_1.add(lbShowMoney);
		
		JLabel label_2 = new JLabel("房型：");
		label_2.setBounds(112, 127, 57, 19);
		panel_1.add(label_2);
		
		
		
		JLabel label_3 = new JLabel("年");
		label_3.setBounds(186, 49, 57, 19);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("房间住宿量：");
		label_4.setBounds(112, 195, 115, 19);
		panel_1.add(label_4);
		
		lbShowRoomAmount = new JLabel("");
		lbShowRoomAmount.setBounds(203, 195, 57, 19);
		panel_1.add(lbShowRoomAmount);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("房间月住量", null, panel_3, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("退出", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("退出");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);				
				BossSystem.btQQuery.setEnabled(true);
				tabbedPane.setSelectedIndex(0);
				tfRoomId.setText("");
				cbRoomType.setSelectedIndex(0);
				lbShowName.setText("");
				lbShowPhone.setText("");
				lbShowId.setText("");
				lbShowMoney.setText("");
			}
		});
		btnNewButton.setBounds(264, 153, 99, 27);
		panel_2.add(btnNewButton);
		
		frame.setVisible(false);
	}
	
	
	
	public void QueryRoomIncome(String roomtype){
		int income = 0;
		int roomCheckinAmount = 0;
		try{//会员
			String sql = "select roomPrice.roomPrice_mem, book.checkInDate, book.checkOutDate "
					   + "from roomPrice, book "
					   + "where book.memOrNot = 'Yes' AND "							   
					   +       "book.balancePayed = 'Yes' AND "
					   +       "book.roomType = roomPrice.roomType AND "
					   +       "book.roomType = '" + roomtype + "' AND "
					   +       "year(book.checkInDate) = " + String.valueOf(cbYear.getSelectedItem()) + " AND "
					   +       "month(book.checkInDate) = " + String.valueOf(cbMonth.getSelectedItem());
		
			DB.rlt = DB.stmt.executeQuery(sql);
			while (DB.rlt.next()){
				//最初的
				/*int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
				income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;*/
				roomCheckinAmount++;
				
				String[] a = DB.rlt.getString(2).split("-");
				String[] b = DB.rlt.getString(3).split("-");
				if (a[1].equals("12") && b[1].equals("01")){
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())), 11, 31);
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String strDate = sf.format(cal.getTime());
					Date date = new Date();
					date = sf.parse(strDate);
					int daydiff = (int)(date.getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
					cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())) + 1, 0, 1);
					strDate = sf.format(cal.getTime());
					date = sf.parse(strDate);
					daydiff += (int)(DB.rlt.getDate(3).getTime() - date.getTime()) / 1000 / 60 / 60 / 24 + 1;
					income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
				}
				else{
					int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
					income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
				}
			}
			sql = "select roomPrice.roomPrice, book.checkInDate, book.checkOutDate "
					   + "from roomPrice, book "
					   + "where book.memOrNot = 'No' AND "							   
					   +       "book.balancePayed = 'Yes' AND "
					   +       "book.roomType = roomPrice.roomType AND "
					   +       "book.roomType = '" + roomtype + "' AND "
					   +       "year(book.checkInDate) = " + String.valueOf(cbYear.getSelectedItem()) + " AND "
					   +       "month(book.checkInDate) = " + String.valueOf(cbMonth.getSelectedItem());
			DB.rlt = DB.stmt.executeQuery(sql);
			while (DB.rlt.next()){
				roomCheckinAmount++;
				String[] a = DB.rlt.getString(2).split("-");
				String[] b = DB.rlt.getString(3).split("-");
				if (a[1].equals("12") && b[1].equals("01")){
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())), 11, 31);
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					String strDate = sf.format(cal.getTime());
					Date date = new Date();
					date = sf.parse(strDate);
					int daydiff = (int)(date.getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
					cal.set(Integer.valueOf(String.valueOf(cbYear.getSelectedItem())) + 1, 0, 1);
					strDate = sf.format(cal.getTime());
					date = sf.parse(strDate);
					daydiff += (int)(DB.rlt.getDate(3).getTime() - date.getTime()) / 1000 / 60 / 60 / 24 + 1;
					income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
				}
				else{
					int daydiff = (int)(DB.rlt.getDate(3).getTime() - DB.rlt.getDate(2).getTime()) / 1000 / 60 / 60 / 24;
					income += Integer.valueOf(DB.rlt.getString(1)) * daydiff;
				}
			}
			lbShowMoney.setText(String.valueOf(income));	
			lbShowRoomAmount.setText(String.valueOf(roomCheckinAmount));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
