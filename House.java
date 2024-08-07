package QLy;

public class House {

	String id,tenNha,sucChua ,dien,nuoc,gia,hoten,gioitinh,sdt;
	int tuoi;
	
	public House () {
		
	}
	public House(String id, String tenNha, String sucChua, String dien, String nuoc, String gia, String hoten,
			int tuoi,String gioitinh,String sdt ) {

		this.id = id;
		this.tenNha = tenNha;
		this.sucChua = sucChua;
		this.dien = dien;
		this.nuoc = nuoc;
		this.gia = gia;
		this.hoten = hoten;
		this.tuoi = tuoi;
		this.gioitinh = gioitinh;
		this.sdt = sdt;
	
		}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenNha() {
		return tenNha;
	}
	public void setTenNha(String tenNha) {
		this.tenNha = tenNha;
	}
	public String getSucChua() {
		return sucChua;
	}
	public void setSucChua(String sucChua) {
		this.sucChua = sucChua;
	}
	public String getDien() {
		return dien;
	}
	public void setDien(String dien) {
		this.dien = dien;
	}
	public String getNuoc() {
		return nuoc;
	}
	public void setNuoc(String nuoc) {
		this.nuoc = nuoc;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	
	
	
}