package QLy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class HouseView extends JFrame implements ActionListener, MouseListener {

	private JButton btnexit;
	private JButton btnreset;
	private JButton btnok;

	private JLabel lbid;
	private JLabel lbnha;
	private JLabel lbtuoi;
	private JLabel lbhoten;
	private JLabel lbnuoc;
	private JLabel lbgia;
	private JLabel lbgt;
	private JLabel lbchua;
	private JLabel lbdien,lbsdt;

	public static JTextField txtid;
	public static JTextField txtnha;
	public static JTextField txtdien;
	public static JTextField txttuoi;
	public static JTextField txtchua;
	public static JTextField txtnuoc;
	public static JTextField txtgia;
	public static JTextField txthoten,txtgt,txtsdt;
	public static JComboBox<String> choice;

	Container con;

	public HouseView(String s) {
		super(s);
		con = this.getContentPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		lbid = new JLabel("  ID	");
		lbnha = new JLabel("  Tên Nhà");
		lbchua = new JLabel("  Sức chứa ");
		lbdien = new JLabel(" Tiền Điện");
		lbnuoc = new JLabel("  Tiền Nước");
		lbgia = new JLabel("  Giá Nhà");
		lbhoten = new JLabel("  Họ Tên");		
		lbtuoi = new JLabel(" Tuổi ");
		lbsdt = new JLabel("SDT");
		lbgt = new JLabel(" Giới Tính");
		
		btnok = new JButton("OK");
		btnexit = new JButton("Cancel");
		btnreset = new JButton("Reset");

		txtid = new JTextField();
		txtnha = new JTextField();
		txtchua = new JTextField();
		txtdien = new JTextField();
		txtnuoc = new JTextField();
		txtgia = new JTextField();
		txthoten = new JTextField();
		txttuoi = new JTextField();
		txtsdt = new JTextField();
		txtgt = new JTextField();
		choice = new JComboBox<String>();
		choice.addItem("Nam");
		choice.addItem("Nữ");
		JLabel lblinf = new JLabel("House Information", JLabel.CENTER);
		lblinf.setFont(new Font("Times New Roman", Font.BOLD, 25));
		p1.setLayout(new GridLayout(10, 2));
		p2.setLayout(new FlowLayout());

		p1.add(lbid);
		p1.add(txtid);
		p1.add(lbnha);
		p1.add(txtnha);
		p1.add(lbchua);
		p1.add(txtchua);
		p1.add(lbdien);
		p1.add(txtdien);
		p1.add(lbnuoc);
		p1.add(txtnuoc);
		p1.add(lbgia);
		p1.add(txtgia);
        p1.add(lbhoten);
		p1.add(txthoten);
		p1.add(lbtuoi);
		p1.add(txttuoi);
		p1.add(lbsdt);
		p1.add(txtsdt);
		p1.add(lbgt);
		p1.add(choice);
		
		con.add(lblinf, "North");
		p2.add(btnok);
		p2.add(btnreset);
		p2.add(btnexit);
		btnok.addActionListener(this);
		btnreset.addActionListener(this);
		btnexit.addActionListener(this);
		con.add(p1, "Center");
		con.add(p2, "South");

		setSize(500, 400);
		this.setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
		if (e.getActionCommand().equals("OK")) {
			if(this.getTitle().equals("Add form")) {
				try {
					btnAddHouse(e);
				} catch (checkException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					btnUpdateHouse(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			this.setVisible(false);
		}
		if (e.getActionCommand().equals("Reset")) {
			btnReset(e);
		}

	}
	public static boolean btnUpdateForm(ActionEvent e) {
		try {
			int selectedIndex = MainView.select();
			House ho = MainView.lpa.get(selectedIndex);
			txtid.setText(ho.getId());
			txtnha.setText(ho.getTenNha());
			txtchua.setText(ho.getSucChua());
			txtdien.setText(ho.getDien());
			txtnuoc.setText(ho.getNuoc());
			txtgia.setText(ho.getGia());
			txthoten.setText(ho.getHoten());
			txttuoi.setText(String.valueOf(ho.getTuoi()));
			txtsdt.setText(ho.getSdt());
			choice.setSelectedItem(ho.getGioitinh());
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No houses were selected");
			return true;
		}
		return false; 
	}

	public static void btnReset(ActionEvent e) {
		txtid.setText("");
		txtnha.setText("");
		txtchua.setText("");
		txtdien.setText("");
		txtnuoc.setText("");
		txtgia.setText("");
		txthoten.setText("");
		txttuoi.setText("");
		txtsdt.setText("");
		choice.setSelectedIndex(0);
	}

	public static void btnAddHouse(ActionEvent e) throws checkException {
		try {
			String id = txtid.getText();
			if (txtid.getText().length()==0)
				throw new checkException("Chua nhap ID");
			String tenNha = txtnha.getText();
			if (txtnha.getText().length()==0)
				throw new checkException("Chua nhap Ten Nha");
			String sucChua = txtchua.getText();
			if (txtchua.getText().length()==0)
				throw new checkException("Chua nhap Suc Chua");
			String nuoc = txtnuoc.getText();
			if (txtnuoc.getText().length()==0)
				throw new checkException("Chua nhap Tien Nuoc");
			String dien = txtdien.getText();
			if (txtdien.getText().length()==0)
				throw new checkException("Chua nhap Tien Dien");
			String giaNha = txtgia.getText();
			if (txtgia.getText().length()==0)
				throw new checkException("Chua nhap Gia Nha");
			String hoten = txthoten.getText();
			if (txthoten.getText().length()==0)
				throw new checkException("Chua nhap Ho Ten");
			int tuoi = Integer.parseInt(txttuoi.getText());
			if (txttuoi.getText().length()==0)
				throw new checkException("Chua nhap Tuoi");
			if(tuoi < 0) throw new checkException("Tuoi khong duoc nho hon 10");
			String gioitinh = choice.getSelectedItem().toString();
			String sdt = txtsdt.getText();
			if (txtsdt.getText().length()==0)
				throw new checkException("Chua nhap SDT");
			
			
		
			House ho = new House(id, tenNha, sucChua, dien, nuoc, giaNha, hoten,tuoi,  gioitinh,sdt);
			
			boolean a = HouseModify.AddHouse(ho);
			if(a) throw new Exception();
			MainView.showHouse();
			JOptionPane.showMessageDialog(null, "Added successfully");
		} catch (checkException ce) {
			JOptionPane.showMessageDialog(null, ce.getMessage());
		} catch (Exception e1) {

		}
	}

	public static void btnSearchHouse(ActionEvent e) {
		String input = JOptionPane.showInputDialog(null, "Nhap thong tin muon tim kiem");
		if (input != null && input.length() > 0) {

		
			MainView.lpa = HouseModify.find(input);
			MainView.tbmodel.setRowCount(0);

			MainView.lpa.forEach((ho) -> {
				MainView.tbmodel.addRow(new Object[] { ho.getId(), ho.getTenNha(), ho.getSucChua(), ho.getDien(),
						ho.getNuoc(), ho.getGia(), ho.getHoten(), ho.getTuoi(), ho.getGioitinh() ,ho.getSdt()});
		
			});
		} else {

			MainView.showHouse();
		}
	}

	public static void btnUpdateHouse(ActionEvent e) throws Exception {
		int selectedIndex = MainView.select();
		if (selectedIndex >= 0) {
			try {
				String id = txtid.getText();
				if (txtid.getText().length()==0)
					throw new checkException("Chua nhap ID");
				String tenNha = txtnha.getText();
				if (txtnha.getText().length()==0)
					throw new checkException("Chua nhap Ten Nha");
				String sucChua = txtchua.getText();
				if (txtchua.getText().length()==0)
					throw new checkException("Chua nhap Suc Chua");
				String nuoc = txtnuoc.getText();
				if (txtnuoc.getText().length()==0)
					throw new checkException("Chua nhap Tien Nuoc");
				String dien = txtdien.getText();
				if (txtdien.getText().length()==0)
					throw new checkException("Chua nhap Tien Dien");
				String giaNha = txtgia.getText();
				if (txtgia.getText().length()==0)
					throw new checkException("Chua nhap Gia Nha");
				String hoten = txthoten.getText();
				if (txthoten.getText().length()==0)
					throw new checkException("Chua nhap Ho Ten");
				int tuoi = Integer.parseInt(txttuoi.getText());
				if (txttuoi.getText().length()==0)
					throw new checkException("Chua nhap Tuoi");
				if(tuoi < 0) throw new checkException("Tuoi khong duoc nho hon 10");
				String sdt = txtsdt.getText();
				if (txtsdt.getText().length()==0)
					throw new checkException("Chua nhap SDT");
				String gioitinh = choice.getSelectedItem().toString();
				
				House newho = new House(id, tenNha , sucChua, dien,  nuoc,  giaNha ,  hoten, tuoi, sdt, gioitinh);
				
				int option = JOptionPane.showConfirmDialog(null, "Ban co muon cap nhat bang khong?");
			
				if (option == 0) {
					boolean a = HouseModify.Update(newho);
					if(a) throw new Exception();
					MainView.showHouse();
					JOptionPane.showMessageDialog(null, "Cap nhat thanh cong");
				}
			} catch (checkException ce) {
				JOptionPane.showMessageDialog(null, ce.getMessage());
			} catch (Exception e1) {

			}
		}
	}

	public static void btnDeleteHouse(ActionEvent e) {
		int selectedIndex = MainView.tb.getSelectedRow();
		if (selectedIndex >= 0) {
			House ho = MainView.lpa.get(selectedIndex);
			int option = JOptionPane.showConfirmDialog(null, "Ban co muon xoa muc nay khong?");

			if (option == 0) {
				HouseModify.Delete(ho.getId());
				MainView.showHouse();
				JOptionPane.showMessageDialog(null, "Xoa thanh cong");
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}

class checkException extends Exception {
	public checkException(String str) {
		super(str);
	}
}




