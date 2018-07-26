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
 * 上拉的加载的脚布局
 */

public class DefaultFooter extends BaseIndicator {
    private TextView mStringIndicator;
    private ProgressWheel progress_wheell;
    private int default_rim_color;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.prj_ptr_footer_default, parent, true);
        View child = v.getChildAt(v.getChildCount() - 1);
        mStringIndicator = (TextView) child.findViewById(R.id.tv_footer);
        progress_wheell = (ProgressWheel) v.findViewById(R.id.progress_wheell);
        default_rim_color = progress_wheell.getRimColor();
        return child;
    }

    @Override
    public void onAction() {
        mStringIndicator.setText(R.string.string_refresh_down_pull_more);
    }

    @Override
    public void onUnAction() {
        mStringIndicator.setText(R.string.string_refresh_up_pull_more);
    }

    @Override
    public void onRestore() {
        mStringIndicator.setText(R.string.string_refresh_up_pull_more);
        progress_wheell.setRimColor(default_rim_color);
        progress_wheell.stopSpinning();
    }

    @Override
    public void onLoading() {
        mStringIndicator.setText(R.string.string_refresh_loading);
        progress_wheell.setRimColor(Color.parseColor("#00000000"));
        progress_wheell.spin();
    }
}
