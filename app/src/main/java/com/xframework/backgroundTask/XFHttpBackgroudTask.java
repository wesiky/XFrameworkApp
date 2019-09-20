package com.xframework.backgroundTask;

import android.os.AsyncTask;

import com.xframework.util.HttpUtil;
import com.xframework.model.XFHttpModel;
import com.google.gson.Gson;

public abstract class XFHttpBackgroudTask extends AsyncTask<String, Integer, String> {
    String result = "";
    @Override
    protected String doInBackground(String... params) {
        try {
            Gson gson = new Gson();
            XFHttpModel model= gson.fromJson(params[0], XFHttpModel.class);
            result = HttpUtil.sendSoapPost(model.getMethodName(),model.getParams());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    //此方法可以在主线程改变UI
    protected void onPostExecute(String result) {
        callback(result);
    }

    public abstract void callback(String result);
}
