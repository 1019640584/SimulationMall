package cn.project.simulationmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.Commodity;
import cn.project.simulationmall.ui.activity.ComDetailsActivity;

/**
 * Create by ankele
 * <p>
 * 2020/12/19 - 11:06
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private Context context;
    private List<Commodity> commodities;

    public RecAdapter(Context context, List<Commodity> commodities) {
        this.context = context;
        this.commodities = commodities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rec, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Commodity commodity = commodities.get(position);
        holder.mComName.setText(commodity.getName());
        Glide.with(context).load(commodity.getImgUrl()).into(holder.mComPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ComDetailsActivity.class);
                intent.putExtra("name",commodity.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mComPic;
        private TextView mComName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mComPic = (ImageView) itemView.findViewById(R.id.comPic);
            mComName = (TextView) itemView.findViewById(R.id.comName);
        }
    }
}