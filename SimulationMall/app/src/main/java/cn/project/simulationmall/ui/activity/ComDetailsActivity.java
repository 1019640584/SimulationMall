package cn.project.simulationmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.Cart;
import cn.project.simulationmall.bean.Commodity;

public class ComDetailsActivity extends BaseActivity {

    private ImageView mDetailsPic;
    private TextView mDetailsName;
    private TextView mDetailsDes;
    private Button mBtnAdd;
    private Commodity commodity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_details);
        initView();
        initData();
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true, "商品详情");
        mDetailsPic = (ImageView) findViewById(R.id.detailsPic);
        mDetailsName = (TextView) findViewById(R.id.detailsName);
        mDetailsDes = (TextView) findViewById(R.id.detailsDes);
        mBtnAdd = (Button) findViewById(R.id.btnAdd);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        commodity = LitePal.where("name = ?",name).find(Commodity.class).get(0);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(ComDetailsActivity.this, "成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData(){
        Glide.with(this).load(commodity.getImgUrl()).into(mDetailsPic);
        mDetailsDes.setText(commodity.getDescribe());
        mDetailsName.setText(commodity.getName());

    }
}