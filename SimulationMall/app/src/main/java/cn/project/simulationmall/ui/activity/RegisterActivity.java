package cn.project.simulationmall.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.litepal.LitePal;

import java.util.List;

import cn.project.simulationmall.R;
import cn.project.simulationmall.bean.User;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 学号/工号
     */
    private EditText mEditRegName;
    /**
     * 密码
     */
    private EditText mEditRegPwd;
    /**
     * 确认密码
     */
    private EditText mEditRegConPwd;
    /**
     * 注册
     */
    private Button mBtnReg;
    /**
     * 返回登录
     */
    private TextView mTextBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initToolBar(findViewById(R.id.toolbar),true,"注册");
        mEditRegName = (EditText) findViewById(R.id.editRegName);
        mEditRegPwd = (EditText) findViewById(R.id.editRegPwd);
        mEditRegConPwd = (EditText) findViewById(R.id.editRegConPwd);
        mBtnReg = (Button) findViewById(R.id.btnReg);
        mBtnReg.setOnClickListener(this);
        mTextBack = (TextView) findViewById(R.id.textBack);
        mTextBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnReg:
                toRegister();
                break;
            case R.id.textBack:
                finish();
                break;

        }
    }

    private void toRegister() {
        String userName = mEditRegName.getText().toString();
        String userPwd = mEditRegPwd.getText().toString();
        String conPwd = mEditRegConPwd.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入用户名...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            Toast.makeText(this, "请输入密码...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(conPwd)) {
            Toast.makeText(this, "请再次输入密码...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!userPwd.equals(conPwd)) {
            Toast.makeText(this, "两次密码不一样...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isExits(userName)) {
            saveUser(userName, userPwd);
            Toast.makeText(this, "注册成功...", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "用户名已经存在...", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveUser(String userName, String userPwd) {
        User user = new User();
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        user.save();
    }

    private boolean isExits(String userName) {
        List<User> users = LitePal.where("userName = ?", userName).find(User.class);
        if (users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


}
