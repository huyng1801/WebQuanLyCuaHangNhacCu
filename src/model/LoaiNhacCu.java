package model;

public class LoaiNhacCu {
    private int maLoaiNhacCu;
    private String tenLoaiNhacCu;
    public LoaiNhacCu() {}
	public LoaiNhacCu(int maLoaiNhacCu, String tenLoaiNhacCu) {
		super();
		this.maLoaiNhacCu = maLoaiNhacCu;
		this.tenLoaiNhacCu = tenLoaiNhacCu;
	}
	public int getMaLoaiNhacCu() {
		return maLoaiNhacCu;
	}
	public void setMaLoaiNhacCu(int maLoaiNhacCu) {
		this.maLoaiNhacCu = maLoaiNhacCu;
	}
	public String getTenLoaiNhacCu() {
		return tenLoaiNhacCu;
	}
	public void setTenLoaiNhacCu(String tenLoaiNhacCu) {
		this.tenLoaiNhacCu = tenLoaiNhacCu;
	}

    
}
