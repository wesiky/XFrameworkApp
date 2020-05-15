package com.xframework.xframeworkapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.xframework.backgroundTask.XFHttpBackgroudTask;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.GetUserInfoIn;
import com.xframework.model.WS.GetUserInfoOut;
import com.xframework.model.WS.LoginIn;
import com.xframework.model.WS.LoginOut;
import com.xframework.model.XFHttpModel;
import com.xframework.util.HttpUtil;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

import static com.xframework.util.MD5Util.MD5Encode;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        try {
            loadLoginInfo();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("InitError", e.getMessage());
        }
        try {
            loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "配置初始化失败，请重新安装APP", Toast.LENGTH_LONG).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void exitOnClick(View v) {
        android.os.Process.killProcess(android.os.Process.myPid());    //获取PID
        System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
    }

    @SuppressLint("StaticFieldLeak")
    class ImpBackgroundTask extends XFHttpBackgroudTask {
        @Override
        public void callback(String result) {
            try {
                Gson gson = new Gson();
                LoginOut out = gson.fromJson(result, LoginOut.class);
                if (out.getStatus() == 0) {
                    //登录成功：读取并保存用户信息
                    //设置参数
                    GetUserInfoIn in = new GetUserInfoIn();
                    in.setUserId(out.getUserId());
                    in.setDeviceCode(SystemInfo.getDeviceCode());
                    GetUserInfoOut ws_out = XFrameworkWebServiceUtil.API_GetUserInfo(in);
                    if (ws_out.getStatus() == 0) {
                        //保存用户信息
                        LoginUserInfo.setUserId(out.getUserId());
                        LoginUserInfo.setEmail(ws_out.getData().getEmail());
                        LoginUserInfo.setLgort(ws_out.getData().getLgort());
                        LoginUserInfo.setMrp(ws_out.getData().getMrp());
                        LoginUserInfo.setPhone(ws_out.getData().getPhone());
                        LoginUserInfo.setRealName(ws_out.getData().getRealName());
                        LoginUserInfo.setSuperiorId(ws_out.getData().getSuperiorId());
                        LoginUserInfo.setRoleName(ws_out.getData().getRoleName());
                        LoginUserInfo.setUserName(ws_out.getData().getUserName());
                        LoginUserInfo.setWarehouseId(ws_out.getData().getWarehouseId());

                        //设置默认账号和密码
                        try {
                            saveLoginInfo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //跳转到主界面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //启动Intent
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    } else {
                        //用户信息获取失败：提示错误信息
                        Toast.makeText(LoginActivity.this, out.getStatus() + ":" + out.getMsg(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    //登录失败：提示错误信息
                    Toast.makeText(LoginActivity.this, out.getStatus() + ":" + out.getMsg(), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //登录异常：提示错误
                Toast.makeText(LoginActivity.this, "登录异常，请联系管理员", Toast.LENGTH_LONG).show();
            } finally {
                Button button_login = findViewById(R.id.button_login);
                button_login.setEnabled(true);
            }
        }
    }


    public void loginOnClick(View v) {
        Button button_login = findViewById(R.id.button_login);
        EditText edit_username = findViewById(R.id.et_username);
        EditText edit_password = findViewById(R.id.et_password);
        button_login.setEnabled(false);
        LoginIn in = new LoginIn();
        in.setUserName(edit_username.getText().toString());
        in.setPassword(edit_password.getText().toString());
        if (in.getUserName().equals("") || in.getPassword().equals("")) {
            Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_LONG).show();
            button_login.setEnabled(true);
            return;
        }
        in.setPassword(MD5Encode(in.getPassword(), ""));
        in.setDeviceCode(SystemInfo.getDeviceCode());
        XFHttpModel model = new XFHttpModel();
        model.setMethodName("API_Login");
        HashMap<String, String> params = new HashMap<>();
        params.put("strJson", "{'user_name': '" + in.getUserName() + "','password': '" + in.getPassword() + "','device_code': '" + in.getDeviceCode() + "'}");
        model.setParams(params);
        try {
            //启动后台异步线程进行连接webService操作，并且根据返回结果在主线程中改变UI
            ImpBackgroundTask queryAddressTask = new ImpBackgroundTask();
            //启动后台任务
            queryAddressTask.execute(model.toJson());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @SuppressLint("HardwareIds")
    private void loadProperties() throws IOException {
        Properties pro = new Properties();
        try {
            InputStream in = getApplicationContext().getAssets().open("applicationConfig.properties");
            pro.load(new InputStreamReader(in, StandardCharsets.UTF_8));
//            WebServiceUtil.setTimeout(Integer.parseInt(pro.getProperty("webservice_timeout")));
//            WebServiceUtil.setWsdl_url(pro.getProperty("xframework_webservice_uri"));
//            WebServiceUtil.setName_sapce(pro.getProperty("xframework_webservice_namespace"));
//            WebServiceUtil.setSoap_version(Integer.parseInt(pro.getProperty("xframework_webservice_soap_version")));
            HttpUtil.setCharset(pro.getProperty("xframework_http_charset"));
            HttpUtil.setContentType(pro.getProperty("xframework_http_content_type"));
            HttpUtil.setNamespace(pro.getProperty("xframework_http_namespace"));
            HttpUtil.setResquestType(pro.getProperty("xframework_http_resquest_type"));
            HttpUtil.setUrl(pro.getProperty("xframework_http_url"));
            SystemInfo.setFrameworkId(Integer.parseInt(pro.getProperty("framework_id")));
            SystemInfo.setDeviceCode(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void loadLoginInfo() throws IOException {
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        SharedPreferences pref = getSharedPreferences("login_info", MODE_PRIVATE);
        etUserName.setText(pref.getString("login_name", ""));
        etPassword.setText(pref.getString("password", ""));
    }

    private void saveLoginInfo() throws IOException {
        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        SharedPreferences.Editor editor = getSharedPreferences("login_info",MODE_PRIVATE).edit();
        editor.putString("login_name",etUserName.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.apply();
    }
}
