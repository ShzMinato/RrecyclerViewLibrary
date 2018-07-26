package com.example.minato.minastore.pull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by minato on 2018/7/24.
 * Header 和 Footer 的状态
 */

public abstract class BaseIndicator {
    public abstract View createView(LayoutInflater inflater, ViewGroup parent);
    public abstract void onAction();
    public abstract void onUnAction();
    public abstract void onRestore();
    public abstract void onLoading();
}
