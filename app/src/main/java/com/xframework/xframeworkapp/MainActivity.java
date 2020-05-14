package com.xframework.xframeworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xframework.adapter.MenuAdapter;
import com.xframework.backgroundTask.XFHttpBackgroudTask;
import com.xframework.model.BaseData;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.Module;
import com.xframework.model.ModuleType;
import com.xframework.model.SystemInfo;
import com.xframework.model.WS.GetGenerateAllDataIn;
import com.xframework.model.WS.GetGenerateAllDataOut;
import com.xframework.model.WS.GetModulesIn;
import com.xframework.model.WS.GetModulesOut;
import com.xframework.model.XFHttpModel;
import com.xframework.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gv;//activity_main.xml里的ListView
    private ArrayList<Module> modules = new ArrayList<>();
    private MenuAdapter adapter_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetModulesIn in = new GetModulesIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setFrameworkId(SystemInfo.getFrameworkId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        XFHttpModel model = new XFHttpModel();
        model.setMethodName("API_GetModules");
        HashMap<String, String> params = new HashMap<>();
        params.put("strJson", "{'user_id': '" + in.getUserId() + "','framework_id': '" + in.getFrameworkId() + "','device_code': '" + in.getDeviceCode() + "'}");
        model.setParams(params);
        try {
            //启动后台异步线程进行连接webService操作，并且根据返回结果在主线程中改变UI
            MainActivity.ImpBackgroundTask queryAddressTask = new MainActivity.ImpBackgroundTask();
            //启动后台任务
            queryAddressTask.execute(model.toJson());
        } catch (Exception e) {
            throw e;
        }
    }

    class ImpBackgroundTask extends XFHttpBackgroudTask {
        @Override
        public void callback(String result) {
            try {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                GetModulesOut out = gson.fromJson(result, GetModulesOut.class);
                if (out.getStatus() == 0) {
                    modules.clear();
                    for(ModuleType module_type:out.getModuleTypes())
                    {
                        loadModules(module_type);
                    }
                    gv = (GridView) findViewById(R.id.gv_test);
                    adapter_menu = new MenuAdapter(MainActivity.this, R.layout.menu, modules);
                    gv.setAdapter(adapter_menu);
                    gv.setOnItemClickListener(MainActivity.this);
                } else {
                    //登录失败：提示错误信息
                    Toast.makeText(MainActivity.this, out.getStatus() + ":" + out.getMsg(), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
    }

    public void logoutOnClick(View v) {
        LoginUserInfo.clear();
        this.finish();
    }


    /**
     * 加载菜单
     * @param module_type
     */
    public void loadModules(ModuleType module_type) {
        appendModules(module_type);
        for (ModuleType child_module_type : module_type.getChildModuleTypes()) {
            loadModules(child_module_type);
        }
    }

    public void appendModules(ModuleType module_type) {
        for (Module module : module_type.getModules()) {
            modules.add(module);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Module module=(Module) adapter_menu.getItem(position);

        String className = getPackageName() + "." + module.getModuleUrl();
        try {
            Class activityClass = Class.forName(className);
            startActivity(new Intent(this, activityClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
