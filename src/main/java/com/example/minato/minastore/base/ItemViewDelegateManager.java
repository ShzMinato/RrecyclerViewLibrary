package com.example.minato.minastore.base;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by minato on 2018/7/24.
 *
 * ItemView的管理类 泛型是item实体
 *      添加指定类型的item
 *
 * ItemTypeView的原理
 *      RecyclerView中 每种类型会有一个编号比如  1 对应  itemView1
 *      本类中mItemDelegate容器存的是类型编号和itemView
 *      如果只有一种类型 就不需要维护key键
 */
@SuppressWarnings("unchecked")
public class ItemViewDelegateManager<T> {
    SparseArrayCompat<ItemViewDelegate<T>> mItemDelegate = new SparseArrayCompat<>();

    /**
     * 返回条目类型的个数
     */
    public int getItemViewDelegateCount() {
        return mItemDelegate.size();
    }

    /**
     * 添加条目
     * 如果是单一条目类型的recycler直接调用一次就好
     * 相当于 容器中维护了一个类型是0的itemView
     */
    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = mItemDelegate.size();
        if (delegate != null) {
            mItemDelegate.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    /**
     * 添加指定类型的条目
     * 如果该类型的条目已经存在 则添加不成功
     * 相当于 容器中维护了多个类型的itemView
     *     0-----itemView0
     *     1-----itemView1
     *     ···
     */
    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (mItemDelegate.get(viewType) != null) {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + mItemDelegate.get(viewType));
        }
        mItemDelegate.put(viewType, delegate);
        return this;
    }

    /**
     * 根据item
     * 移除item，同时容器也会改变
     */
    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = mItemDelegate.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            mItemDelegate.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 根据type 移除item
     * 同时容器也会改变
     */
    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = mItemDelegate.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            mItemDelegate.removeAt(indexToRemove);
        }
        return this;
    }

    /**
     * 获取某一position的条目类型
     */
    public int getItemViewType(T item, int position) {
        int delegatesCount = mItemDelegate.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = mItemDelegate.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return mItemDelegate.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    /**
     * 绑定itemView
     *    控件的赋值
     */
    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = mItemDelegate.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = mItemDelegate.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    /**
     * 返回指定type的布局文件
     */
    public int getItemViewLayoutId(int viewType) {
        return mItemDelegate.get(viewType).getItemViewLayoutId();
    }

    /**
     * 根据布局返回其type
     */
    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        return mItemDelegate.indexOfValue(itemViewDelegate);
    }
}
