package com.xframework.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xframework.xframeworkapp.R;

/**
 * 耗时对话框工具类
 */
public class ProgressDialogUtil {
  private static AlertDialog mAlertDialog;

  /**
   * 弹出耗时对话框
   */
  public static void showProgressDialog(Context context) {
    if (mAlertDialog == null) {
      mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
    }

    View loadView = LayoutInflater.from(context).inflate(R.layout.custom_progress_dialog_view, null);
    mAlertDialog.setView(loadView, 0, 0, 0, 0);
    mAlertDialog.setCanceledOnTouchOutside(false);

    TextView tvTip = loadView.findViewById(R.id.tvTip);
    tvTip.setText("加载中...");

    mAlertDialog.show();
  }

  public static void showProgressDialog(Context context, String tip) {
    if (tip.equals("")) {
      tip = "加载中...";
    }

    if (mAlertDialog == null) {
      mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
    }

    View loadView = LayoutInflater.from(context).inflate(R.layout.custom_progress_dialog_view, null);
    mAlertDialog.setView(loadView, 0, 0, 0, 0);
    mAlertDialog.setCanceledOnTouchOutside(false);

    TextView tvTip = loadView.findViewById(R.id.tvTip);
    tvTip.setText(tip);

    mAlertDialog.show();
  }

  /**
   * 隐藏耗时对话框
   */
  public static void dismiss() {
    if (mAlertDialog != null && mAlertDialog.isShowing()) {
      mAlertDialog.dismiss();
    }
  }
}