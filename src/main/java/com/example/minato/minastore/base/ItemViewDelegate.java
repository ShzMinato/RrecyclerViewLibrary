package com.example.minato.minastore.base;

/**
 * Created by minato on 2018/7/24.
 *
 * Item的接口规范  泛型是item实体
 *    包装itemView,提供辅助函数
 *    布局文件，布局类型，绑定控件
 */

public interface ItemViewDelegate<T> {
    /**
     * 返回该Item的布局资源
     */
    int getItemViewLayoutId() ;

    /**
     * 判断position位置上是否是指定类型的条目
     */
    boolean isForViewType(T item, int position) ;


    /**
     * 绑定item控件
     *    也就是赋值
     */
    void convert(ViewHolder holder, T t, int position) ;
}
