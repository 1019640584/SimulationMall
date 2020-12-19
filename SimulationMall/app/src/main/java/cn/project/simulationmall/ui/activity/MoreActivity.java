package cn.project.simulationmall.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.adapter.MoreAdapter;
import cn.project.simulationmall.bean.Commodity;

public class MoreActivity extends BaseActivity {

    private TextView mMTitle;
    private EditText mEditSearch;
    private RecyclerView mMoreRec;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initView();
        initData();
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), true, "商品列表");
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mEditSearch = (EditText) findViewById(R.id.edit_search);
        mMoreRec = (RecyclerView) findViewById(R.id.moreRec);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void initData() {
        List<Commodity> commodities = LitePal.findAll(Commodity.class);
        MoreAdapter adapter = new MoreAdapter(this, commodities);
        mMoreRec.setAdapter(adapter);
        mMoreRec.setLayoutManager(new LinearLayoutManager(this));
    }
}