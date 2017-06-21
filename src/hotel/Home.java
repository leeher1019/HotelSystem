package hotel;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import java.awt.Font;


public class Home {

	private JFrame frame;
	public DB db;
	JLabel lbTime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame("客房管理系统");
		frame.getContentPane().setForeground(Color.GREEN);
		frame.setBounds(100, 100, 1032, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		lbTime = new JLabel("时间");
		lbTime.setHorizontalAlignment(SwingConstants.LEFT);
		lbTime.setBounds(70, 42, 600, 19);
		frame.getContentPane().add(lbTime);		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
				Calendar cal = Calendar.getInstance();
				String t = "";
				t = cal.get(Calendar.HOUR_OF_DAY) + "：" + cal.get(Calendar.MINUTE) + "：" + cal.get(Calendar.SECOND);
				lbTime.setText(t);
			}			
		};
		timer.schedule(task, 0, 1000);
		
		JButton btnEmployee = new JButton("打卡");		
		btnEmployee.setBounds(51, 141, 99, 27);
		frame.getContentPane().add(btnEmployee);
		
		JButton btnMember = new JButton("会员管理");		
		btnMember.setBounds(51, 181, 99, 27);
		frame.getContentPane().add(btnMember);
		
		JButton btnBook = new JButton("客房管理");		
		btnBook.setBounds(51, 221, 99, 27);
		frame.getContentPane().add(btnBook);
		
		JButton btnQuit = new JButton("退出系统");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(51, 261, 99, 27);
		frame.getContentPane().add(btnQuit);
		
		JButton btnBoss = new JButton("主管系统");		
		btnBoss.setBounds(51, 101, 99, 27);
		frame.getContentPane().add(btnBoss);		
		
		DB.getDriver();
		DB.getConnection("hotel");
		
		JLabel lblNewLabel = new JLabel("未名大酒店客房管理系统");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 48));
		lblNewLabel.setBounds(345, 120, 529, 230);
		frame.getContentPane().add(lblNewLabel);
		
		//------会员系统----------------------------
		
		MemberSystem ms = new MemberSystem();
		frame.getContentPane().add(ms.tp);
		ms.btSave_click();
		ms.btModify_click();
		ms.tp.setVisible(false);
		
		//-----------------------------------------
		//------客房系统----------------------------
		
		RoomSystem rs = new RoomSystem();
		frame.getContentPane().add(rs.tp);
		rs.btQueryRoom_click(rs.btBook);
		rs.btBook_click();
		rs.cbRoomType_select();
		for (int i = 0; i < rs.roomList.size(); i++)		
			rs.btCheckIn_click(rs.roomList.get(i).button);
		
		/*
		rs.btCheckIn_click(rs.A1.button);
		rs.btCheckIn_click(rs.A2.button);
		rs.btCheckIn_click(rs.A4.button);
		rs.btCheckIn_click(rs.B1.button);
		rs.btCheckIn_click(rs.B2.button);
		rs.btCheckIn_click(rs.B4.button);*/
		rs.btCheckOut_click();
		rs.cbHasBooked_select();
		rs.tp.setVisible(false);
		//-----------------------------------------
		//------员工系统---------------------------
		
		EmployeeSystem es = new EmployeeSystem();
		frame.getContentPane().add(es.tp);
		es.btOnWork_click();
		es.btOffWork_click();
		es.tp.setVisible(false);
		//------------------------------------------
		//------主管系统----------------------------
		
		BossSystem bs = new BossSystem();
		frame.getContentPane().add(bs.tp);
		frame.getContentPane().add(bs.plAccess);
		
		bs.btAddEmp_click();
		bs.btChangeTitle_click();
		bs.btQuery_del_click();
		bs.btDelEmp_click();
		bs.btAddRoom_click(rs);
		bs.btQuery_modifyprice();
		bs.btModifyPrice_click();
		bs.btAddTitle_click();
		bs.btLogin_click();
		bs.btLogout_click();
		bs.cbTitle_select();
		bs.cbChangeTitle_select();
		bs.btQQuery_click();
		bs.tp.setVisible(false);
		bs.plAccess.setVisible(false);	
		
		//-----------------------------------------
		
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setVisible(false);				
				es.tp.setSelectedIndex(0);
				es.tp.setVisible(true);
				ms.tp.setVisible(false);
				rs.tp.setVisible(false);
				bs.tp.setVisible(false);
				btnEmployee.setEnabled(false);
				btnMember.setEnabled(true);
				btnBook.setEnabled(true);
				btnBoss.setEnabled(true);
			}
		});
		btnMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);				
				ms.tp.setSelectedIndex(0);
				es.tp.setVisible(false);
				ms.tp.setVisible(true);
				rs.tp.setVisible(false);
				bs.tp.setVisible(false);
				btnEmployee.setEnabled(true);
				btnMember.setEnabled(false);
				btnBook.setEnabled(true);
				btnBoss.setEnabled(true);
			}
		});
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setVisible(false);				
				rs.tp.setSelectedIndex(0);
				es.tp.setVisible(false);
				ms.tp.setVisible(false);
				rs.tp.setVisible(true);
				bs.tp.setVisible(false);
				btnEmployee.setEnabled(true);
				btnMember.setEnabled(true);
				btnBook.setEnabled(false);
				btnBoss.setEnabled(true);
			}
		});
		btnBoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				lblNewLabel.setVisible(false);
				bs.tp.setSelectedIndex(0);
				es.tp.setVisible(false);
				ms.tp.setVisible(false);
				rs.tp.setVisible(false);
				bs.tp.setVisible(false);
				bs.plAccess.setVisible(true);
				bs.tfEmpId_access.setText("");
				bs.pfPassword.setText("");
				bs.lbAccess.setText("");
				btnEmployee.setEnabled(true);
				btnMember.setEnabled(true);
				btnBook.setEnabled(true);
				btnBoss.setEnabled(false);
			}
		});
	}
}

class DB {
	static Connection con;
	static Statement stmt;
	static ResultSet rlt;
	
	public static void getDriver(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			System.out.println("找不到驅動程式類別");
		}
	}
	
	public static void getConnection(String dbName){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useSSL=true", "root", "herlee0916");
			stmt = con.createStatement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void createTable(String tbName, String attribute){
		String sql = "create table if not exists " + tbName + "(" + attribute + ")";
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addData(String tbName, String colName, String values){
		String sql = "insert into " + tbName + " (" + colName + ") values (" +  values + ")";					
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void modifyData(String tbName, String colName, String value, String newColName, String newValue){
		String sql = "update " + tbName + " set " + newColName + "='" + newValue + "' where " + colName + "='" + value + "'";
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void modifyData2Null(String tbName, String colName, String value, String newColName){
		String sql = "update " + tbName + " set " + newColName + "= NULL where " + colName + "='" + value + "'";
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void modifyDataCheckIn(String tbName, String colName_1, String value_1, String colName_2, String value_2, String newColName, String newValue){
		String sql = "update " + tbName + " set " + newColName + "='" + newValue + "' where " + colName_1 + "='" + value_1 + "' AND " + colName_2 + "='" + value_2 + "'" ;
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void delData(String tbName, String column, String value){
		String sql = "delete from " + tbName + " where " + column + "='" + value + "'";
		try{
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void queryRowData(String tbName, String column, String keyCol, String value){
		String sql = "select " + column + " from " + tbName + " where " + keyCol + "='" + value + "'";
		try{
			rlt = stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void queryNullData(String tbName, String column, String keyCol){
		String sql = "select " + column + " from " + tbName + " where " + keyCol + " IS NOT NULL";
		try{
			rlt = stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void queryData(String tbName, String retrieveColumn, String conditionColumn_1, String conditionValue_1, String conditionColumn_2, String conditionValue_2){
		String sql = "select " + retrieveColumn + " from " + tbName + " where " + conditionColumn_1 + "='" + conditionValue_1 + "' AND conditionColumn_2 ='" + conditionValue_2 + "'";
		try{
			rlt = stmt.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
} 