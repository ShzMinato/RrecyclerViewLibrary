package com.example.minato.minastore.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.minato.minastore.base.ItemViewDelegate;
import com.example.minato.minastore.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minato on 2018/7/24.
 * 单一类型的Item的Adapter 泛型是item的实体
 *    其manger容器中的键值对是0-----类型
 *    在构造函数中 已经添加了item
 */

public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {


    public CommonAdapter(final Context context, final int layoutId) {
        this(context, layoutId, new ArrayList<T>());
    }

    //构造函数中添加item
    public CommonAdapter(final Context context, final int layoutId, List<T> data) {
        super(context, data);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);
}


