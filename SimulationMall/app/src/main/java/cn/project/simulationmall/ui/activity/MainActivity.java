package cn.project.simulationmall.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import cn.project.simulationmall.R;
import cn.project.simulationmall.ui.fragment.HomeFragment;
import cn.project.simulationmall.ui.fragment.MeFragment;
import cn.project.simulationmall.ui.fragment.ShopFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mMTitle;
    private FrameLayout mNavHostFragment;
    private BottomNavigationView mNavBottom;
    private Toolbar mToolbar;
    private HomeFragment mHomeFragment;
    private ShopFragment mShopFragment;
    private MeFragment mMeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        mMTitle = (TextView) findViewById(R.id.mTitle);
        mNavHostFragment = (FrameLayout) findViewById(R.id.nav_host_fragment);
        mNavBottom = (BottomNavigationView) findViewById(R.id.nav_bottom);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        showFragment(1);
    }

    private void initListener(){
        mNavBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        showFragment(1);
                        break;
                    case R.id.navigation_two:
                        showFragment(2);
                        break;
                    case R.id.navigation_notifications:
                        showFragment(3);
                        break;
                }
                return true;
            }
        });
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index){
            case 1:
                mMTitle.setText("首页");
                if (mHomeFragment==null){
                    mHomeFragment = HomeFragment.getInstance();
                    ft.add(R.id.nav_host_fragment,mHomeFragment,HomeFragment.class.getName());
                }else {
                    ft.show(mHomeFragment);
                }
                break;
            case 2:
                mMTitle.setText("购物车");
                if (mShopFragment==null){
                    mShopFragment = ShopFragment.getInstance();
                    ft.add(R.id.nav_host_fragment,mShopFragment,ShopFragment.class.getName());
                }else {
                    ft.show(mShopFragment);
                }
                break;
            case 3:
                mMTitle.setText("我的");
                if (mMeFragment==null){
                    mMeFragment = MeFragment.getInstance();
                    ft.add(R.id.nav_host_fragment,mMeFragment,MeFragment.class.getName());
                }else {
                    ft.show(mMeFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }
        if (mShopFragment != null) {
            ft.hide(mShopFragment);
        }
        if (mMeFragment!=null){
            ft.hide(mMeFragment);
        }
    }

    /**
     * 点击两次返回退出app
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new XPopup.Builder(this).asConfirm("提示", "确定退出嘛", new OnConfirmListener() {
                @Override
                public void onConfirm() {
                    finish();
                }
            }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}