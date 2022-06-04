package com.hehe.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.hehe.R;
import com.hehe.bean.FoodLayer;
import com.hehe.interfaceUtil.MonitorListViewListener;
import com.hehe.interfaceUtil.OnItemTouchListener;
import com.hehe.utils.SharedPrefsUtil;
import com.hehe.utils.Util;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


/* loaded from: classes.dex */
public class LayersListViewdapter extends BaseAdapter {
    int index;
    private Activity mActivity;
    Handler mHandler;
    private OnItemTouchListener mOnItemTouchListener;
    private MonitorListViewListener monitorListViewListener;
    List<FoodLayer> mItemList = new ArrayList();
    boolean isFirst = true;
    private final long delayMillis = 300;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public LayersListViewdapter(Activity activity, List<FoodLayer> list, Handler handler) {
        this.mActivity = activity;
        setData(list);
        this.mHandler = handler;
    }

    public void setTabButton(int i) {
        this.index = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mItemList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mItemList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view2;
        int i2;
        int i3;
        int i4;
        String str = SharedPrefsUtil.get(i + "table_real_point", "");
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = ((LayoutInflater) this.mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listview_layer_item, (ViewGroup) null);
            viewHolder.tv_selectPoint = (TextView) view2.findViewById(R.id.tv_selectPoint);
            viewHolder.gif_turnshuru = (GifImageView) view2.findViewById(R.id.gif_layer_turnshuru);
            viewHolder.iv_layer_shuru = (ImageView) view2.findViewById(R.id.iv_layer_shuru);
            viewHolder.gif_guangbiao = (GifImageView) view2.findViewById(R.id.gif_guangbiao);
            viewHolder.iv_insert_zhuohao_bg = (ImageView) view2.findViewById(R.id.iv_insert_zhuohao_bg);
            view2.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        if (viewHolder.iv_layer_shuru.getVisibility() == View.VISIBLE) {
            this.isFirst = true;
        } else {
            this.isFirst = false;
        }
        FoodLayer foodLayer = this.mItemList.get(i);
        //foodLayer.status 默认是-1
        if (foodLayer.status == 1 && foodLayer.hasValue == 0) {//选中 并且 没有食物
            viewHolder.iv_layer_shuru.setVisibility(View.GONE);
            viewHolder.gif_turnshuru.setVisibility(View.GONE);
            viewHolder.iv_insert_zhuohao_bg.setVisibility(View.GONE);
            viewHolder.tv_selectPoint.setVisibility(View.GONE);
        }
        if ((foodLayer.status == 0 || foodLayer.status == -1) && foodLayer.hasValue == 0) {
            viewHolder.iv_layer_shuru.setVisibility(View.VISIBLE);
            viewHolder.gif_turnshuru.setVisibility(View.GONE);
            viewHolder.iv_insert_zhuohao_bg.setVisibility(View.GONE);
            viewHolder.tv_selectPoint.setVisibility(View.GONE);
        }
        if ((foodLayer.status == 0 || foodLayer.status == -1) && foodLayer.hasValue == 1) {
            viewHolder.iv_layer_shuru.setVisibility(View.GONE);
            viewHolder.iv_insert_zhuohao_bg.setVisibility(View.VISIBLE);
            viewHolder.tv_selectPoint.setVisibility(View.VISIBLE);
        }
        if (foodLayer.status == 1 && foodLayer.hasValue == 1) {
            viewHolder.iv_layer_shuru.setVisibility(View.GONE);
            viewHolder.iv_insert_zhuohao_bg.setVisibility(View.VISIBLE);
            viewHolder.tv_selectPoint.setVisibility(View.VISIBLE);
        }
        if (this.mOnItemTouchListener != null) {
            view2.setOnTouchListener(new View.OnTouchListener() { // from class: com.higgs.deliveryrobot.adapter.LayersListViewdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view3, MotionEvent motionEvent) {
                    mOnItemTouchListener.onTouch(i);
                    return false;
                }
            });
        }
        if (Build.VERSION.SDK_INT >= 23) {
            i3 = this.mActivity.getResources().getColor(R.color.layerText3, null);
            i2 = this.mActivity.getResources().getColor(R.color.layerText4, null);
            i4 = this.mActivity.getResources().getColor(R.color.layerText3, null);
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
        }
        if (i == this.index) {
            viewHolder.tv_selectPoint.setTextColor(i4);
            viewHolder.tv_selectPoint.setGravity(17);
        } else {
            viewHolder.tv_selectPoint.setTextColor(i3);
        }
        if (!TextUtils.isEmpty(str)) {
            foodLayer.hasValue = 1;
            viewHolder.tv_selectPoint.setText(str);
            viewHolder.tv_selectPoint.setTextColor(i2);
        } else {
            foodLayer.hasValue = 0;
            str = Util.getStringByStringXml(R.string.add_dining_table_name);
            viewHolder.tv_selectPoint.setText(str);
        }
        this.monitorListViewListener.onChangeValue();
        boolean isContainsChinese = Util.isContainsChinese(str);
//        if (isContainsChinese) {
//            viewHolder.tv_selectPoint.setTextSize(35.0f);
//        } else {
//            viewHolder.tv_selectPoint.setTextSize(40.0f);
//        }
        if (!isContainsChinese || foodLayer.status != 1) {
            viewHolder.gif_guangbiao.setVisibility(View.GONE);
        } else {
            viewHolder.gif_guangbiao.setVisibility(View.GONE);
        }
//        if (this.isFirst) {
//            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewHolder.iv_insert_zhuohao_bg, "scaleX", 0.3f, 1.0f);
//            ofFloat.setDuration(300L);
//            ofFloat.start();
//            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(viewHolder.tv_selectPoint, "scaleX", 0.3f, 1.0f);
//            ofFloat2.setDuration(200L);
//            ofFloat2.start();
//            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(viewHolder.gif_guangbiao, "alpha", 0.0f, 1.0f);
//            ofFloat3.setDuration(700L);
//            ofFloat3.start();
//        }
        return view2;
    }

    /* loaded from: classes.dex */
    class ViewHolder {
        GifImageView gif_guangbiao;
        GifImageView gif_turnshuru;
        ImageView iv_insert_zhuohao_bg;//桌号输入按钮
        ImageView iv_layer_shuru;//添加按钮
        TextView tv_selectPoint;

        ViewHolder() {
        }
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }

    public void setMonitorListViewListener(MonitorListViewListener monitorListViewListener) {
        this.monitorListViewListener = monitorListViewListener;
    }

    public void setData(List<FoodLayer> list) {
        this.mItemList.clear();
        this.mItemList.addAll(list);
        notifyDataSetChanged();
    }
}
