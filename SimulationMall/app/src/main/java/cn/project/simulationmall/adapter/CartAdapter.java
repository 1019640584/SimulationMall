package cn.project.simulationmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.Cart;

/**
 * Create by ankele
 * <p>
 * 2020/12/19 - 21:47
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    private Context context;
    private List<Cart> carts;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Glide.with(context).load(cart.getImgUrl()).into(holder.mMorePic);
        holder.mMoreName.setText(cart.getName());
        holder.mItemNum.setText(cart.getNum()+"");
        holder.mMorePrice.setText("¥"+cart.getPrice());
        holder.mItemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.setNum(cart.getNum()+1);
                cart.save();
                notifyDataSetChanged();
            }
        });
        holder.mItemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cart.getNum()==1){
                    LitePal.deleteAll(Cart.class,"name=?",cart.getName());
                    carts.remove(cart);
                    notifyDataSetChanged();
                }else {
                    cart.setNum(cart.getNum()-1);
                    cart.save();
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMorePic;
        private TextView mMoreName;
        private TextView mMorePrice;
        private ImageView mItemAdd;
        private TextView mItemNum;
        private ImageView mItemRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMorePic = (ImageView) itemView.findViewById(R.id.morePic);
            mMoreName = (TextView) itemView.findViewById(R.id.moreName);
            mMorePrice = (TextView) itemView.findViewById(R.id.morePrice);
            mItemAdd = (ImageView) itemView.findViewById(R.id.itemAdd);
            mItemNum = (TextView) itemView.findViewById(R.id.itemNum);
            mItemRemove = (ImageView) itemView.findViewById(R.id.itemRemove);
        }
    }
}