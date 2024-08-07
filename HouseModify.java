package QLy;




import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HouseModify {

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/qlnha", "root", "");
			return conn; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<House> SeeAll() {
		List<House> houselist = new ArrayList<House>();

		Connection connection = getConnection();
		Statement statement = null;
		try {
			String sql = "select * from House";
			statement = connection.createStatement();
//nhan bang dl tu sql
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				House ho = new House(rs.getString("ID"), rs.getString("TenNha"),
						rs.getString("SucChua"), rs.getString("TienDien"), rs.getString("TienNuoc"),
						rs.getString("GiaNha"), rs.getString("HoTen"),rs.getInt("Tuoi"), rs.getString("GioiTinh"),rs.getString("SDT"));
				houselist.add(ho);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return houselist; 
	}

	public static boolean AddHouse(House ho) {

		Connection connection = getConnection();
		PreparedStatement ps = null;
		try { 

				String sql1 = "insert into House (ID, TenNha, SucChua, TienDien, TienNuoc, GiaNha, HoTen, Tuoi,  GioiTinh,SDT)"
						+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				ps = connection.prepareCall(sql1);
				ps.setString(1, ho.getId());
				ps.setString(2, ho.getTenNha());
				ps.setString(3, ho.getSucChua());
				ps.setString(4, ho.getDien());
				ps.setString(5, ho.getNuoc());
				ps.setString(6, ho.getGia());
				ps.setString(7, ho.getHoten());
				ps.setInt(8, ho.getTuoi());
				ps.setString(9,ho.getGioitinh());
				ps.setString(10, ho.getSdt());
				
		
			    ps.execute();
			JOptionPane.showMessageDialog(null, "Them thanh cong");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Khong the them them bang");
			return true;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static int Dem () throws SQLException {
		
		Connection conn = getConnection();
		Statement s = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet r = s.executeQuery("SELECT * FROM House ");
		// di chuyển về dòng cuối cùng
		r.last();
		// lấy vị trí dòng cuối cũng chính là số lượng dòng trong kết quả
		int count = r.getRow();
		r.beforeFirst();
		JOptionPane.showMessageDialog(null, count);
		return count;
		
	}

	public static boolean Update(House ho) throws Exception {
		Connection connection = getConnection();
		PreparedStatement ps = null;
		int selectedIndex = MainView.select(); //vi tri pa da chon
		House oldpa = MainView.lpa.get(selectedIndex); 
		String oldpaid = oldpa.getId();
		try {

				String sql1 = "update House set ID=?, TenNha=?,SucChua=?,TienDien=?,TienNuoc=?,GiaNha=?,"
						+ "HoTen=?,Tuoi=?, GioiTinh=?,  SDT=? where id = ?";
				ps = connection.prepareCall(sql1);
				ps.setString(1, ho.getId());
				ps.setString(2, ho.getTenNha());
				ps.setString(3, ho.getSucChua());
				ps.setString(4, ho.getDien());
				ps.setString(5, ho.getNuoc());
				ps.setString(6, ho.getGia());
				ps.setString(7, ho.getHoten());
				ps.setInt(8, ho.getTuoi());
				ps.setString(9, ho.getSdt());
				ps.setString(10, ho.getGioitinh());
				ps.setString(11, oldpaid);
			
		ps.execute();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Khong the sua");
			return true;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return false; 
	}

	public static List<House> find(String s) {

		List<House> hlist1 = new ArrayList<>();

		Connection connection =getConnection();
		PreparedStatement statement = null;
		try {
			String sql1 = "select * from House where ID like ?";
			statement = connection.prepareCall(sql1);
			statement.setString(1, "%" + s + "%");
			ResultSet r1 = statement.executeQuery();
			while (r1.next()) {
				House ho = new House (r1.getString("ID"), r1.getString("TenNha"), r1.getString("SucChua"),
						r1.getString("TienDien"), r1.getString("TienNuoc"), r1.getString("GiaNha"),
						r1.getString("HoTen"), r1.getInt("Tuoi"),r1.getString("GioiTinh"),r1.getString("SDT"));
				hlist1.add(ho);
			}
		
			String sql2 = "select * from House where TenNha like ?";
			statement = connection.prepareCall(sql2);
			statement.setString(1, "%" + s + "%");
			ResultSet r2 = statement.executeQuery();
			while (r2.next()) {
				House ho = new House(r2.getString("ID"), r2.getString("TenNha"), r2.getString("SucChua"),
						r2.getString("TienDien"), r2.getString("TienNuoc"), r2.getString("GiaNha"),
						r2.getString("HoTen"), r2.getInt("Tuoi"),r2.getString("GioiTinh"),r2.getString("SDT"));
				hlist1.add(ho);
			}
				
			String sql3 = "select * from House where Tuoi like ?";
			statement = connection.prepareCall(sql3);
			statement.setString(1, "%" + s + "%");
			ResultSet r3 = statement.executeQuery();
			while (r3.next()) {
				House ho = new House(r3.getString("ID"), r3.getString("TenNha"), r3.getString("SucChua"),
						r3.getString("TienDien"), r3.getString("TienNuoc"), r3.getString("GiaNha"),
						r3.getString("HoTen"), r3.getInt("Tuoi"),r3.getString("GioiTinh"),r3.getString("SDT"));
				hlist1.add(ho);
			}
				
			String sql4 = "select * from House where HoTen like ?";
			statement = connection.prepareCall(sql4);
			statement.setString(1, s + "%");
			ResultSet r4 = statement.executeQuery();
			while (r4.next()) {
				House ho = new House(r4.getString("ID"), r4.getString("TenNha"), r4.getString("SucChua"),
						r4.getString("TienDien"), r4.getString("TienNuoc"), r4.getString("GiaNha"),
						r4.getString("HoTen"), r4.getInt("Tuoi"),r4.getString("GioiTinh"),r4.getString("SDT"));
				hlist1.add(ho);
			}
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return hlist1;
	}

	public static void Delete(String id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "delete from House where id = ?";
			statement = connection.prepareCall(sql);
			statement.setString(1, id);
			statement.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	
	


}
