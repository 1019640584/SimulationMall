package cn.project.simulationmall.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.adapter.CartAdapter;
import cn.project.simulationmall.bean.Cart;


/**
 * Create by ankele
 * <p>
 * 2020/9/30 - 22:04
 */
public class ShopFragment extends Fragment {

    private static ShopFragment instance = null;
    private RecyclerView mCartRec;

    public static ShopFragment getInstance() {
        if (instance == null) {
            instance = new ShopFragment();
        }
        return instance;
    }

    private ShopFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mCartRec = (RecyclerView) view.findViewById(R.id.cartRec);
    }

    private void initData(){
        List<Cart> carts = LitePal.findAll(Cart.class);
        CartAdapter cartAdapter = new CartAdapter(getContext(),carts);
        mCartRec.setAdapter(cartAdapter);
        mCartRec.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initData();
    }
}
