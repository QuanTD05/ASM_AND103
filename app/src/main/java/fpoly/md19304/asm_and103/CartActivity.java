package fpoly.md19304.asm_and103;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Lấy danh sách sản phẩm từ CartManager
        ArrayList<Cart> cartItems = CartManager.getInstance().getCartItems();

        // Gán adapter cho RecyclerView
        CartAdapter adapter = new CartAdapter(this, cartItems);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(adapter);

        // Tính tổng tiền
        double totalPrice = 0;
        for (Cart item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        tvTotalPrice.setText(String.format("Tổng tiền: %,.0f VND", totalPrice));

        // Xử lý nút thanh toán
        btnCheckout.setOnClickListener(view -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(CartActivity.this, "Giỏ hàng trống!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CartActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                CartManager.getInstance().clearCart(); // Xóa giỏ hàng sau khi thanh toán
                finish();
            }
        });
    }
}
