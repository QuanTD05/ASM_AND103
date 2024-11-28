package fpoly.md19304.asm_and103;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private TextView tvName, tvHang, tvNamSX, tvGia, tvmota;
    private ImageView imgAvatar;
    private Toolbar toolbarchitiet;
    private Spinner spinner;
    private Button btnAddToCart;

    private String carName;
    private double carGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        // Ánh xạ các view
        toolbarchitiet = findViewById(R.id.toolbarchitietsp);
        spinner = findViewById(R.id.spinnerchitietsp);
        tvName = findViewById(R.id.tvName);
        tvHang = findViewById(R.id.tvHang);
        tvNamSX = findViewById(R.id.tvNamSX);
        tvGia = findViewById(R.id.tvGia);
        imgAvatar = findViewById(R.id.imgAvatatr);
        tvmota = findViewById(R.id.txtmotachitietsp);
        btnAddToCart = findViewById(R.id.btnthemgiohang);

        // Cấu hình toolbar
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(view -> finish());

        // Gán số lượng vào Spinner
        Integer[] quantityOptions = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, quantityOptions);
        spinner.setAdapter(quantityAdapter);

        // Lấy thông tin từ Intent
        carName = getIntent().getStringExtra("ten");
        String carHang = getIntent().getStringExtra("hang");
        int carNamSX = getIntent().getIntExtra("namSX", 0);
        carGia = getIntent().getDoubleExtra("gia", 0.0);
        String carAnh = getIntent().getStringExtra("anh");
        String carMota = getIntent().getStringExtra("mota");

        // Cập nhật UI
        if (carName != null) {
            tvName.setText(carName);
        }

        if (carHang != null) {
            tvHang.setText(carHang);
        }

        tvNamSX.setText(String.valueOf(carNamSX));
        tvGia.setText(String.format("%,.0f VND", carGia));

        if (carAnh != null && !carAnh.isEmpty()) {
            Picasso.get().load(carAnh).into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.baseline_broken_image_24);
        }

        if (carMota != null) {
            tvmota.setText(carMota);
        }

        // Xử lý sự kiện thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(view -> {
            int quantity = (int) spinner.getSelectedItem();
            Cart item = new Cart(carName, quantity, carGia,carAnh);
            CartManager.getInstance().addItem(item);
            Toast.makeText(ChiTietSanPhamActivity.this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btn_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
