package QLy;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class QL_Nha extends JFrame implements ActionListener {

	private JLabel lbuser, lbpass;
	private JTextField txtuser;
	private JPasswordField pass;
	private JButton btlog, btcan;
	Container con;

	public QL_Nha(String title) {
		super(title);
		con = this.getContentPane();
		try {
			JLabel lb = new JLabel(" Quản Lý Nhà Cho Thuê");
			
			lb.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 48));
			lb.setOpaque(true); // lam label trong suot
			lb.setForeground(Color.GREEN); // set mau chu lb
			lb.setBackground(Color.white);
			con.add(lb, "North");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		lbuser = new JLabel("Username:");
		lbpass = new JLabel("Password:");
		txtuser = new JTextField(10);
		pass = new JPasswordField(10);
		btlog = new JButton("LOG IN");
		btcan = new JButton("CANCEL");
		lbuser.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
		lbpass.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 25));
		pass.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtuser.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btlog.setPreferredSize(new Dimension(200, 30));
		btcan.setPreferredSize(new Dimension(200, 30));

		p1.add(lbuser);
		p1.add(txtuser);
		p1.add(lbpass);
		p1.add(pass);
		p2.add(btlog);
		p2.add(btcan);
		p3.add(p1);
		
		lbuser.setBackground(Color.white);
		lbpass.setBackground(Color.white);
		p2.setBackground(Color.white); 
		p3.setBackground(Color.white);
		con.add(p2, "South");
		con.add(p3, "Center");

		btlog.addActionListener(this);
		btcan.addActionListener(this);
		p1.setLayout(new GridLayout(2, 2));
		setVisible(true);
		setSize(620, 250);
		setLocationRelativeTo(null); // dua jframe ra giua man hinh
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("LOG IN")) {
			SignUp admin = getSignUp();
			if (SignUp.Check(admin)) { 
				new MainView("Quản Lý Nhà Cho Thuê");
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
			}
		}
		if (e.getActionCommand().equals("CANCEL"))
			this.dispose(); // tat jframe
	}

	public SignUp getSignUp() { // tra ve account da viet tren tf
		return new SignUp(txtuser.getText(), String.copyValueOf(pass.getPassword()));
	}

	public static void main(String[] args) {
		new QL_Nha("Log in");
	}
}

class MainView extends JFrame implements ActionListener, MouseListener, ItemListener {

	static List<House> lpa = new ArrayList<House>(); // add doi tuong

	private static final Component MainView = null;
	private JButton btall, btexit, btdelete, btsearch,btadd, btupd,btdem;

	JScrollPane tbRs; 
	static DefaultTableModel tbmodel; // bo sung doi tuong
	static JTable tb = new JTable(); // hien thi dl duoi dang bang

	String[] columns = { "ID", "TenNha", "SucChua", "TienDien", "TienNuoc", "GiaNha", "HoTen",
			"Tuoi", "GioiTinh", "SDT" };
	String[][] data;
	Container con; 
	static int selectedrow = 0; //vi tri item duoc chon

	public MainView(String s) {
		super(s);
		con = this.getContentPane();
		JPanel p1 = new JPanel();
		

		btsearch = new JButton("Search");
		btdelete = new JButton("Delete");
		btexit = new JButton("Exit");
		btall = new JButton("See all");
		btupd = new JButton("Update");
		btadd = new JButton("Add");
		btdem = new JButton("Dem");
	
		btsearch.addActionListener(this);
		btdelete.addActionListener(this);
		btall.addActionListener(this);
		btexit.addActionListener(this);
		btupd.addActionListener(this);
		btadd.addActionListener(this);
		btdem.addActionListener(this);

		p1.add(btall);
		p1.add(btadd);
		p1.add(btupd);
		p1.add(btsearch);
		p1.add(btdelete);
		p1.add(btdem);
		
		JPanel p3 = new JPanel(); 
		JLabel lbqly = new JLabel("Quản Lý Nhà Cho Thuê", JLabel.CENTER);
		lbqly.setFont(new Font("Times New Roman", Font.BOLD, 25));
		p3.add(lbqly);
		p3.setSize(1200, 100);

		tbRs = new JScrollPane(tb);

		tb.setModel(new DefaultTableModel(data, columns) {
			}); //cho dl vao bang
		tb.addMouseListener(this);
		tbmodel = (DefaultTableModel) tb.getModel(); // xd bang voi dl o tren
		showHouse();

		con.add(p3, "North");
		con.add(tbRs);
		con.add(p1, "South");

		this.setSize(1200, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setLocation(20, 20);
	}

	public static void showHouse() {
		lpa = HouseModify.SeeAll(); // lay tu dl
		tbmodel.setRowCount(0); // xoa dl da co trong table
		lpa.forEach((ho) -> {
			try {
				tbmodel.addRow(new Object[] { ho.getId(), ho.getTenNha(), ho.getSucChua(), ho.getDien(),
						ho.getNuoc(), ho.getGia(), ho.getHoten(), ho.getTuoi(), ho.getGioitinh(), ho.getSdt() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Dem")) {
			try {
				HouseModify.Dem();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getActionCommand().equals("See all")) {
			showHouse();
		}
		if (e.getActionCommand().equals("Exit")) {
			this.dispose();
		}
		if (e.getActionCommand().equals("Add")) {
			new HouseView("Add form");
		}
		if (e.getActionCommand().equals("Update")) {
			HouseView ho = new HouseView("Update form");
			boolean a = HouseView.btnUpdateForm(e);
			if(a) {
				ho.setVisible(false);
			}
		}
		if (e.getActionCommand().equals("Delete")) {
			HouseView.btnDeleteHouse(e);
		}
		if (e.getActionCommand().equals("Search")) {
			HouseView.btnSearchHouse(e);
		}

	}

	public void mouseClicked(MouseEvent e) {
	}
	
//tra ve so cua hang duoc chon. phuc vu cho  
	public static int select() {
		selectedrow = tb.getSelectedRow();
		return selectedrow;
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
