package fpoly.md19304.asm_and103;

public class CarModel {

    private String _id;
    private String ten;

    private int namSX;

    private String hang;

    private double gia;
    private String anh;

    public CarModel(String _id, String ten, int namSX, String hang, double gia, String anh) {
        this._id = _id;
        this.ten = ten;
        this.namSX = namSX;
        this.hang = hang;
        this.gia = gia;
        this.anh = anh;
    }

    public CarModel(String ten, int namSX, String hang, double gia, String anh) {
        this.ten = ten;
        this.namSX = namSX;
        this.hang = hang;
        this.gia = gia;
        this.anh = anh;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public CarModel(String ten, int namSX, String hang, double gia) {
        this.ten = ten;
        this.namSX = namSX;
        this.hang = hang;
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anhXe) {
        this.anh = anhXe;
    }
}