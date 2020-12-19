package cn.project.simulationmall.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.RoundImageView;
import cn.project.simulationmall.adapter.RecAdapter;
import cn.project.simulationmall.bean.Commodity;
import cn.project.simulationmall.ui.activity.MoreActivity;


/**
 * Create by ankele
 * <p>
 * 2020/9/30 - 22:04
 */
public class HomeFragment extends Fragment {
    private static HomeFragment instance = null;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EditText mEditSearch;
    private Banner mTopBanner;
    private RecyclerView mRecommendRec;
    private TextView mTextMore;

    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    private HomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initBanner();
        initData();
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mEditSearch = (EditText) view.findViewById(R.id.edit_search);
        mTopBanner = (Banner) view.findViewById(R.id.topBanner);
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
        mRecommendRec = (RecyclerView) view.findViewById(R.id.recommendRec);
        mRecommendRec.setHasFixedSize(true);
        mRecommendRec.setNestedScrollingEnabled(false);
        mTextMore = (TextView) view.findViewById(R.id.textMore);
        mTextMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MoreActivity.class));
            }
        });
    }

    private void initBanner() {
        List<Integer> banners = new ArrayList<>();
        banners.add(R.mipmap.banner1);
        banners.add(R.mipmap.banner2);
        mTopBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mTopBanner.setIndicatorGravity(BannerConfig.CENTER);
        mTopBanner.setImageLoader(new BannerLoader());
        mTopBanner.setImages(banners);
        mTopBanner.setDelayTime(3000);
        mTopBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mTopBanner.start();
    }

    private void initData() {
        List<Commodity> commodities = LitePal.findAll(Commodity.class);
        RecAdapter adapter = new RecAdapter(getContext(), commodities);
        mRecommendRec.setAdapter(adapter);
        mRecommendRec.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public static class BannerLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, final ImageView imageView) {
            Glide.with(context.getApplicationContext()).load((int) path).into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new RoundImageView(context);
        }
    }


}
