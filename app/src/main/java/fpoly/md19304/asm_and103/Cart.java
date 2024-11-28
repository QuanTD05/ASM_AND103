package fpoly.md19304.asm_and103;


public class Cart {
    private String _id;
    private String name;
    private int quantity;
    private double price;
    private String anh;

    public Cart(String name, int quantity, double price, String anh) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.anh = anh;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
