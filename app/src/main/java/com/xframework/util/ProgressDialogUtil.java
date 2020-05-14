package com.xframework.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ProgressDialogUtil {
  private AlertDialog mAlertDialog;
  
  public void dismiss() {
    AlertDialog alertDialog = this.mAlertDialog;
    if (alertDialog != null && alertDialog.isShowing())
      this.mAlertDialog.dismiss(); 
  }
  
  public void showProgressDialog(Context paramContext) {
    if (this.mAlertDialog == null)
      this.mAlertDialog = (new AlertDialog.Builder(paramContext, 2131755256)).create(); 
    View view = LayoutInflater.from(paramContext).inflate(2131427387, null);
    this.mAlertDialog.setView(view, 0, 0, 0, 0);
    this.mAlertDialog.setCanceledOnTouchOutside(false);
    ((TextView)view.findViewById(2131231262)).setText("加载中...");
    this.mAlertDialog.show();
  }
  
  public void showProgressDialog(Context context, String paramString) {
    String str = paramString;
    if (paramString.equals(""))
      str = "加载中..."; 
    if (this.mAlertDialog == null)
      this.mAlertDialog = (new AlertDialog.Builder(context, 2131755256)).create();
    View view = LayoutInflater.from(context).inflate(2131427387, null);
    this.mAlertDialog.setView(view, 0, 0, 0, 0);
    this.mAlertDialog.setCanceledOnTouchOutside(false);
    ((TextView)view.findViewById(2131231262)).setText(str);
    this.mAlertDialog.show();
  }
}