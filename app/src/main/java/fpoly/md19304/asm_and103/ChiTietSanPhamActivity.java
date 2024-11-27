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

import java.text.DecimalFormat;
import java.util.ArrayList;
public class ChiTietSanPhamActivity extends AppCompatActivity {
    private TextView tvName, tvHang, tvNamSX, tvGia, tvmota;
    private ImageView imgAvatar;
    Toolbar toolbarchitiet;
    Spinner spinner;
    private Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        toolbarchitiet = findViewById(R.id.toolbarchitietsp);
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinner = findViewById(R.id.spinnerchitietsp);
        Integer[] quantityOptions = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, quantityOptions);
        spinner.setAdapter(quantityAdapter);

        tvName = findViewById(R.id.tvName);
        tvHang = findViewById(R.id.tvHang);
        tvNamSX = findViewById(R.id.tvNamSX);
        tvGia = findViewById(R.id.tvGia);
        imgAvatar = findViewById(R.id.imgAvatatr);
        tvmota = findViewById(R.id.txtmotachitietsp);
        btnAddToCart = findViewById(R.id.btnthemgiohang);


        // Nhận thông tin sản phẩm từ Intent
        CarModel car = (CarModel) getIntent().getSerializableExtra("thongtinsanpham");

        if (car != null) {
            tvName.setText(car.getTen());
            tvGia.setText(String.format("Giá: %.2f VND", car.getGia()));
            // Giả định bạn có thêm thuộc tính mô tả (description)
            tvHang.setText(car.getHang());

            btnAddToCart.setOnClickListener(v -> {
                CarModel cartItem = new CarModel(
                        car.getId(),
                        car.getTen(),
                        car.getHang(),
                        car.getGia(),
                        car.getAnh()
                );
                CartManager.getInstance().addToCart(cartItem);
                Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            });
        }





        // Lấy thông tin từ Intent
        String carName = getIntent().getStringExtra("ten");
        String carHang = getIntent().getStringExtra("hang");
        int carNamSX = getIntent().getIntExtra("namSX", 0);
        double carGia = getIntent().getDoubleExtra("gia", 0.0);
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
        if (carHang != null) {
            tvmota.setText(carMota);
        }
    }
}
