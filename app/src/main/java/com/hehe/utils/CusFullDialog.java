package com.hehe.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.hehe.R;

/* loaded from: classes.dex */
public class CusFullDialog extends AlertDialog {
    private View mContentView;
    private Context mContext;
    private int width = -1;
    private int height = -1;

    public CusFullDialog(Context context) {
        super(context, R.style.MyDialog);
        this.mContext = context;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void setWidthAndHeight(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = this.width;
        attributes.height = this.height;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(attributes);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    @Override // android.app.AlertDialog
    public void setView(View view) {
        this.mContentView = view;
    }

    public View getView() {
        return this.mContentView;
    }
}
