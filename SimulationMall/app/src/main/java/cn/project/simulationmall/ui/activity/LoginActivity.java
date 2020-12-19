package cn.project.simulationmall.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.User;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditUserName;
    /**
     * 密码
     */
    private EditText mEditUserPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册
     */
    private TextView mTextRegister;
    /**
     * 忘记密码？
     */
    private TextView mTextForget;
    private CheckBox mCheckKeepPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar), false, "登录");
        mEditUserName = (EditText) findViewById(R.id.editUserName);
        mEditUserPwd = (EditText) findViewById(R.id.editUserPwd);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(this);
        mTextRegister = (TextView) findViewById(R.id.textRegister);
        mTextRegister.setOnClickListener(this);
        mTextForget = (TextView) findViewById(R.id.textForget);
        mCheckKeepPwd = (CheckBox) findViewById(R.id.check_keepPwd);
        SharedPreferences sp = this.getSharedPreferences("LoginInfo",MODE_PRIVATE);
        if (sp.getBoolean("isKeepPwd",false)){
            mCheckKeepPwd.setChecked(true);
            mEditUserName.setText(sp.getString("userName",""));
            mEditUserPwd.setText(sp.getString("userPwd",""));
        }else {
            mCheckKeepPwd.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnLogin:
                toLogin();
                break;
            case R.id.textRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void toLogin() {
        String userName = mEditUserName.getText().toString();
        String userPwd = mEditUserPwd.getText().toString();
        if (!isExits(userName)) {
            Toast.makeText(this, "用户不存在...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPwd.equals(getPwd(userName))) {
            Toast.makeText(this, "登录成功...", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = this.getSharedPreferences("LoginInfo", MODE_PRIVATE).edit();
            editor.putBoolean("isLogin", true);
            editor.putBoolean("isKeepPwd", mCheckKeepPwd.isChecked());
            editor.putString("userName",userName);
            editor.putString("userPwd",userPwd);
            editor.apply();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "密码错误...", Toast.LENGTH_SHORT).show();
        }

    }

    private String getPwd(String userName) {
        List<User> users = LitePal.findAll(User.class);
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user.getUserPwd();
            }
        }
        return "";
    }


    private boolean isExits(String userName) {
        List<User> users = LitePal.where("userName = ?", userName).find(User.class);
        if (users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
