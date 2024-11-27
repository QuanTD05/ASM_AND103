package fpoly.md19304.asm_and103;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private TextView tvTotalPrice;
    private Button btnCheckout;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartAdapter = new CartAdapter(this, CartManager.getInstance().getCartItems());
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> {
            // Thêm logic thanh toán
            Toast.makeText(this, "Chức năng thanh toán đang được phát triển", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTotalPrice() {
        tvTotalPrice.setText(String.format("Tổng tiền: %.2f VND", CartManager.getInstance().getTotalPrice()));
    }
}