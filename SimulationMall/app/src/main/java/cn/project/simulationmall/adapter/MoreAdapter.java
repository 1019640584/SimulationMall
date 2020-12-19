package cn.project.simulationmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.Cart;
import cn.project.simulationmall.bean.Commodity;
import cn.project.simulationmall.ui.activity.ComDetailsActivity;

/**
 * Create by ankele
 * <p>
 * 2020/12/19 - 16:03
 */
public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder> {

    private Context context;
    private List<Commodity> commodities;


    public MoreAdapter(Context context, List<Commodity> commodities) {
        this.context = context;
        this.commodities = commodities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_more, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Commodity commodity = commodities.get(position);
        holder.mMoreName.setText(commodity.getName());
        Glide.with(context).load(commodity.getImgUrl()).into(holder.mMorePic);
        holder.mMorePrice.setText("¥"+commodity.getPrice());
        holder.mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Cart> carts = LitePal.where("name = ?",commodity.getName()).find(Cart.class);
                if (carts.size()==0){
                    Cart cart =  new Cart();
                    cart.setName(commodity.getName());
                    cart.setImgUrl(commodity.getImgUrl());
                    cart.setDescribe(commodity.getDescribe());
                    cart.setRec(commodity.isRec());
                    cart.setNum(1);
                    cart.setPrice(commodity.getPrice());
                    cart.save();
                }else {
                    Cart cart = carts.get(0);
                    cart.setNum(cart.getNum()+1);
                    cart.save();
                }
                Toast.makeText(context, "成功！", Toast.LENGTH_SHORT).show();
            }
        });
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
        private TextView mMoreName;
        private TextView mMorePrice;
        private Button mBtnAdd;
        private ImageView mMorePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoreName = (TextView) itemView.findViewById(R.id.moreName);
            mMorePrice = (TextView) itemView.findViewById(R.id.morePrice);
            mBtnAdd = (Button) itemView.findViewById(R.id.btnAdd);
            mMorePic = (ImageView) itemView.findViewById(R.id.morePic);
        }
    }
}