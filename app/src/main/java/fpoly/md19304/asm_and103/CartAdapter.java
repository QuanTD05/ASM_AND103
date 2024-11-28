package fpoly.md19304.asm_and103;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Cart> cartItems;

    public CartAdapter(Context context, ArrayList<Cart> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart item = cartItems.get(position);

        holder.tvCartItemName.setText(item.getName());
        holder.tvCartItemQuantity.setText("Số lượng: " + item.getQuantity());
        holder.tvCartItemPrice.setText(String.format("Giá: %,.0f VND", item.getPrice()));

        Picasso.get()
                .load(item.getImageUrl())
                .placeholder(R.drawable.baseline_broken_image_24)
                .into(holder.imgCartItem);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartItem;
        TextView tvCartItemName, tvCartItemQuantity, tvCartItemPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCartItem = itemView.findViewById(R.id.imgCartItem);
            tvCartItemName = itemView.findViewById(R.id.tvCartItemName);
            tvCartItemQuantity = itemView.findViewById(R.id.tvCartItemQuantity);
            tvCartItemPrice = itemView.findViewById(R.id.tvCartItemPrice);
        }
    }
}
