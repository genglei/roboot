package com.hehe.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hehe.R;
import com.hehe.db.PointTable;
import com.hehe.interfaceUtil.OnItemTouchListener;
import com.hehe.utils.SharedPrefsUtil;

import java.util.List;

/* loaded from: classes.dex */
public class SelectPointGridAdapter extends BaseAdapter {
    private Context activity;
    List<PointTable> itemList;
    private int mLayerIndex;
    private OnItemTouchListener mOnItemTouchListener;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectPointGridAdapter(Context context, List<PointTable> list) {
        this.activity = null;
        this.activity = context;
        this.itemList = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.itemList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.itemList.get(i);
    }

    public void setLayerIndex(int i) {
        this.mLayerIndex = i;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            ViewHolder viewHolder2 = new ViewHolder();
            View inflate = ((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_gridview_select_point, (ViewGroup) null);
            viewHolder2.tv_name = (TextView) inflate.findViewById(R.id.tv_name);
            inflate.setTag(viewHolder2);
            viewHolder = viewHolder2;
            view = inflate;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (this.mOnItemTouchListener != null) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.higgs.deliveryrobot.adapter.SelectPointGridAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    mOnItemTouchListener.onTouch(i);
                    return false;
                }
            });
        }
        this.activity.getResources().getColor(R.color.gridText);
        int color = this.activity.getResources().getColor(R.color.white);
        String str = SharedPrefsUtil.get(this.mLayerIndex + "table_real_point", "");
        if (TextUtils.isEmpty(str) || !str.equals(this.itemList.get(i).tableRealPoint)) {
            viewHolder.tv_name.setTextColor(color);
            viewHolder.tv_name.setBackgroundResource(R.drawable.shuzi_bg);
        } else {
            viewHolder.tv_name.setTextColor(color);
            viewHolder.tv_name.setBackgroundResource(R.drawable.shizi_bg_press);
        }
        viewHolder.tv_name.setText(this.itemList.get(i).tableAliasName);
        return view;
    }

    /* loaded from: classes.dex */
    class ViewHolder {
        TextView tv_name;

        ViewHolder() {
        }
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }
}
