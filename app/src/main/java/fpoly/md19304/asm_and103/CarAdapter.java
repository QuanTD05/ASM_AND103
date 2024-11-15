package fpoly.md19304.asm_and103;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
public class CarAdapter extends BaseAdapter {
    private List<CarModel> carModelList;
    private Context context;
    private APIService apiService;

    public CarAdapter(Context context, List<CarModel> carModelList) {
        this.context = context;
        this.carModelList = carModelList;
        apiService = RetrofitClient.getClient().create(APIService.class); // Ensure RetrofitClient is properly created
    }

    @Override
    public int getCount() {
        return carModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return carModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_car, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvName);
        TextView tvNamSX = rowView.findViewById(R.id.tvNamSX);
        TextView tvHang = rowView.findViewById(R.id.tvHang);
        TextView tvGia = rowView.findViewById(R.id.tvGia);
        ImageView imgAvatar = rowView.findViewById(R.id.imgAvatatr);

        ImageButton btnDelete = rowView.findViewById(R.id.btn_delete);

        final CarModel car = carModelList.get(position);

        tvName.setText(car.getTen());
        tvNamSX.setText(String.valueOf(car.getNamSX()));
        tvHang.setText(car.getHang());
        tvGia.setText(String.valueOf(car.getGia()));

        // Load image if the URL is available
        String imageUrl = car.getAnh();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.baseline_broken_image_24)
                    .error(R.drawable.baseline_broken_image_24)
                    .into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.baseline_broken_image_24);
        }

        // Handle edit button
        ImageButton btnEdit = rowView.findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditCarActivity.class);
            intent.putExtra("car_id", car.getId());  // Pass car ID through Intent
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        // Handle delete button
        btnDelete.setOnClickListener(v -> deleteCar(car.getId(), position));

        return rowView;
    }

    // Delete car method
    private void deleteCar(String carId, int position) {
        if (carId == null || carId.isEmpty()) {
            Log.e("CarAdapter", "Car ID is null or empty. Cannot delete.");
            Toast.makeText(context, "Error: Invalid car ID", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Void> call = apiService.deleteCar(carId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    carModelList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Car deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("CarAdapter", "Error response: " + response.code() + " - " + response.message());
                    Toast.makeText(context, "Error deleting car", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CarAdapter", "API call failed: " + t.getMessage());
                Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
