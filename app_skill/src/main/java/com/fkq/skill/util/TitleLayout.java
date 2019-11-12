package com.fkq.skill.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fkq.skill.R;


@SuppressWarnings("ALL")
public class TitleLayout extends RelativeLayout {

    private TextView tv_title;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_title, this);
        ImageView iv_back = view.findViewById(R.id.iv_back);
        tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_tool = view.findViewById(R.id.iv_tool);
        TextView tv_tool = view.findViewById(R.id.tv_tool);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        tv_title.setText(a.getString(R.styleable.TitleLayout_title));

        iv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    public void setTitle(String title) {
        if (tv_title != null) {
            tv_title.setText(title);
        }
    }

}
