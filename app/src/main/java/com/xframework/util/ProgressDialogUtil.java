package com.xframework.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xframework.xframeworkapp.R;

public class ProgressDialogUtil {
  private AlertDialog mAlertDialog;
  
  public void dismiss() {
    AlertDialog alertDialog = this.mAlertDialog;
    if (alertDialog != null && alertDialog.isShowing())
      this.mAlertDialog.dismiss(); 
  }
  
  public void showProgressDialog(Context builder) {
    if (this.mAlertDialog == null)
      this.mAlertDialog = (new AlertDialog.Builder(builder, R.style.CustomProgressDialog)).create();
    View view = LayoutInflater.from(builder).inflate(R.layout.custom_progress_dialog_view, null);
    this.mAlertDialog.setView(view, 0, 0, 0, 0);
    this.mAlertDialog.setCanceledOnTouchOutside(false);
    ((TextView)view.findViewById(R.id.tvTip)).setText("加载中...");
    this.mAlertDialog.show();
  }
  
  public void showProgressDialog(Context context, String tip) {
    String str = tip;
    if (tip.equals(""))
      str = "加载中..."; 
    if (this.mAlertDialog == null)
      this.mAlertDialog = (new AlertDialog.Builder(context, R.style.CustomProgressDialog)).create();
    View view = LayoutInflater.from(context).inflate(R.layout.custom_progress_dialog_view, null);
    this.mAlertDialog.setView(view, 0, 0, 0, 0);
    this.mAlertDialog.setCanceledOnTouchOutside(false);
    ((TextView)view.findViewById(R.id.tvTip)).setText(str);
    this.mAlertDialog.show();
  }
}