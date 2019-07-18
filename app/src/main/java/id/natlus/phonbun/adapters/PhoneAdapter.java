package id.natlus.phonbun.adapters;

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
import java.util.List;

import id.natlus.phonbun.R;
import id.natlus.phonbun.models.Phone;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {
    private List<Phone> phoneList = new ArrayList<>();

    private OnPhoneClickListener listener;
    public interface OnPhoneClickListener{
        public void onClick(View view, int position);
    }

    public void setListener(OnPhoneClickListener listener) {
        this.listener = listener;
    }

    public PhoneAdapter(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View phoneView = layoutInflater.inflate(R.layout.item_phone, parent, false);
        PhoneViewHolder viewHolder = new PhoneViewHolder(phoneView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone item = phoneList.get(position);
        holder.price.setText(item.getPrice());
        holder.type.setText(item.getType());
        holder.detail.setText(item.getDetail());
        Picasso.get().load(item.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imagePhone);
    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagePhone;
        public TextView price, type, detail;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhone = itemView.findViewById(R.id.imagePhone);
            price = itemView.findViewById(R.id.pricePhone_price);
            type = itemView.findViewById(R.id.typePhone);
            detail = itemView.findViewById(R.id.detailPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
