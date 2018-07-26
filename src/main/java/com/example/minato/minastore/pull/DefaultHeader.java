package com.example.minato.minastore.pull;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minato.minastore.R;
import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by minato on 2018/7/24.
 * 下拉加载更多的头布局
 */

public class DefaultHeader extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheel;
    private int default_rim_color;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.prj_ptr_header_default, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_header);
        progress_wheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);
        default_rim_color = progress_wheel.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText(R.string.string_refresh_down_to_fresh);
    }

    @Override
    public void onUnAction() {
        mStringIndicator.setText(R.string.string_refresh_down_to_fresh);
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText(R.string.string_refresh_down_to_fresh);
        progress_wheel.setRimColor(default_rim_color);
        progress_wheel.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText(R.string.string_refresh_loading);
        progress_wheel.setRimColor(Color.parseColor("#00000000"));
        progress_wheel.spin();
    }
}


