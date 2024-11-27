package fpoly.md19304.asm_and103;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CarModel> cartItems;

    // Constructor riêng để đảm bảo singleton
    private CartManager() {
        cartItems = new ArrayList<>();
    }

    // Singleton pattern để tạo một instance duy nhất
    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(CarModel item) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (CarModel cartItem : cartItems) {
            if (cartItem.getId().equals(item.getId())) {
                // Nếu có, tăng số lượng
                cartItem.setSoluong(cartItem.getSoluong() + item.getSoluong());
                return;
            }
        }
        // Nếu chưa, thêm sản phẩm mới vào giỏ hàng
        cartItems.add(item);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeFromCart(String itemId) {
        cartItems.removeIf(cartItem -> cartItem.getId().equals(itemId));
    }

    // Lấy danh sách tất cả sản phẩm trong giỏ hàng
    public List<CarModel> getCartItems() {
        return cartItems;
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        cartItems.clear();
    }

    // Tính tổng giá trị giỏ hàng
    public double getTotalPrice() {
        double total = 0;
        for (CarModel item : cartItems) {
            total += item.getSoluong() * item.getSoluong();
        }
        return total;
    }
}
