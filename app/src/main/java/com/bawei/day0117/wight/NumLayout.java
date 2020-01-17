package com.bawei.day0117.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bawei.day0117.R;

/**
 * ProjectName: Day0117
 * PackageName: com.bawei.day0117.wight
 * ClassName:   NumLayout
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/17_9:46
 */
public class NumLayout extends LinearLayout {
    public NumLayout(Context context) {
        this(context,null);
    }

    public NumLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

        View view = View.inflate(context, R.layout.layout_num_linear,null);

        addView(view);
    }
}
