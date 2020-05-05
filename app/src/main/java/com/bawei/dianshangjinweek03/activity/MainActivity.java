package com.bawei.dianshangjinweek03.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.dianshangjinweek03.R;
import com.bawei.dianshangjinweek03.base.BaseActivity;
import com.bawei.dianshangjinweek03.bean.Login;
import com.bawei.dianshangjinweek03.contact.IContact;
import com.bawei.dianshangjinweek03.presenter.LoginPresenter;
import com.bawei.dianshangjinweek03.util.RetrofitUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IContact.IView<Login> {
    @BindView(R.id.phone)
    protected EditText phone;
    @BindView(R.id.pwd)
    protected EditText pwd;
    private LoginPresenter loginPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initData() {
        loginPresenter = new LoginPresenter(this);
    }
    @OnClick(R.id.login)
    protected void loginDo(){
        //判断是否有网
        if(RetrofitUtil.getRetrofitUtil().hasNet(this)){
            //获取文本
            String phone = this.phone.getText().toString();
            String pwd = this.pwd.getText().toString();
            //判断
            if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)){
                Toast.makeText(this,"请输入合法的用户名和密码",Toast.LENGTH_LONG).show();
            } else {
                //请求
                loginPresenter.request(phone,pwd);
            }
        } else {
            Toast.makeText(this,"请检查网络",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void initDestroy() {
        if(loginPresenter != null){
            loginPresenter.destroy();
            loginPresenter = null;
        }
    }
    @Override
    public void success(Login login) {
        //判断
        if(login.getStatus().equals("0000")){
            //提示
            Toast.makeText(this,login.getMessage(),Toast.LENGTH_LONG).show();
            //跳转并传值
            Intent intent = new Intent(this,OrderListActivity.class);
            //传值
            intent.putExtra("sessionId",login.getResult().getSessionId());
            intent.putExtra("userId",login.getResult().getUserId());
            intent.putExtra("phone",login.getResult().getPhone());
            intent.putExtra("headPic",login.getResult().getHeadPic());
            startActivity(intent);
            finish();
        } else {
            //提示
            Toast.makeText(this,login.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void fail(String err) {
        //提示
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("发生错误了，请检查应用！\n错误信息：\n" + err);
        builder.setTitle("很抱歉");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
}
