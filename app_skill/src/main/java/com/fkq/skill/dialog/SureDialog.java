package com.fkq.skill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fkq.skill.R;


public class SureDialog {
    private Context mContext;
    //
    private Dialog dialog;
    private TextView tv_sure;
    private TextView tv_cancel;
    private TextView tv_content;

    public SureDialog(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        dialog = BaseDialog.getIntance(mContext).getDialog(R.layout.dialog_sure,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        tv_sure = dialog.findViewById(R.id.tv_sure);
        tv_cancel = dialog.findViewById(R.id.tv_cancel);
        tv_content = dialog.findViewById(R.id.tv_content);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void show(String content) {
        tv_content.setText(content);
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setSureOnClickListener(String sureText, View.OnClickListener onClickListener) {
        if (!TextUtils.isEmpty(sureText)) {
            tv_sure.setText(sureText);
        }
        tv_sure.setOnClickListener(onClickListener);
    }

    public void setCancelText(String cancelText) {
        if (!TextUtils.isEmpty(cancelText)) {
            tv_cancel.setText(cancelText);
        }
    }

    public void setContextText(String content) {
        tv_content.setText(content);
    }

}
