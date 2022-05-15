package com.hehe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.db.PointTable;
import com.hehe.utils.Util;


import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PointGridAdapter extends BaseAdapter {
    private Context mActivity;
    List<PointTable> mItemList = new ArrayList();

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PointGridAdapter(Context context) {
        this.mActivity = context;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = ((LayoutInflater) this.mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_gridview_point, (ViewGroup) null);
            viewHolder.tv_title = (TextView) view2.findViewById(R.id.tv_title);
            viewHolder.tv_alias = (TextView) view2.findViewById(R.id.tv_alias);
            viewHolder.tv_point = (TextView) view2.findViewById(R.id.tv_point);
            view2.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        int color = this.mActivity.getResources().getColor(R.color.blue);
        int color2 = this.mActivity.getResources().getColor(R.color.color_point_canzhuo);
        int color3 = this.mActivity.getResources().getColor(R.color.color_point);
        switch (this.mItemList.get(i).pointType.intValue()) {
            case 0:
                viewHolder.tv_title.setText(Util.getStringByStringXml(R.string.dining_table));
                viewHolder.tv_title.setBackgroundColor(color2);
                break;
            case 1:
                viewHolder.tv_title.setText(Util.getStringByStringXml(R.string.kitchen));
                viewHolder.tv_title.setBackgroundColor(color3);
                break;
            case 2:
                viewHolder.tv_title.setText(Util.getStringByStringXml(R.string.charging_pile));
                viewHolder.tv_title.setBackgroundColor(color);
                break;
        }
        viewHolder.tv_alias.setText(this.mItemList.get(i).tableAliasName);
        viewHolder.tv_point.setText(this.mItemList.get(i).tableRealPoint);
        return view2;
    }

    /* loaded from: classes.dex */
    class ViewHolder {
        TextView tv_alias;
        TextView tv_point;
        TextView tv_title;

        ViewHolder() {
        }
    }

    public void updataPoint(ArrayList<PointTable> arrayList) {
        this.mItemList.clear();
        this.mItemList.addAll(arrayList);
        notifyDataSetChanged();
    }
}
