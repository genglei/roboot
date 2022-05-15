package com.hehe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.bean.CriusePointModel;
import com.hehe.utils.Util;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class CriuseRouteAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<CriusePointModel> mData = new ArrayList<>();

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public CriuseRouteAdapter(Context context) {
        this.mCtx = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mData.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        String str;
        CriusePointModel criusePointModel = this.mData.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.mCtx).inflate(R.layout.adapter_criuse_route_gv, viewGroup, false);
            holder = new Holder();
            holder.tv_select_num = (TextView) view.findViewById(R.id.tv_select_num);
            holder.tv_select_name = (TextView) view.findViewById(R.id.tv_select_name);
            holder.iv_criuse_route_arrow = (ImageView) view.findViewById(R.id.iv_criuse_route_arrow);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        if (criusePointModel.selectNum < 10) {
            str = "0" + criusePointModel.selectNum;
        } else {
            str = "" + criusePointModel.selectNum;
        }
        if (criusePointModel.selectNum > 99) {
            holder.tv_select_num.setPadding(Util.dip2px(8.0f), Util.dip2px(10.0f), Util.dip2px(8.0f), Util.dip2px(10.0f));
        } else {
            holder.tv_select_num.setPadding(Util.dip2px(10.0f), Util.dip2px(10.0f), Util.dip2px(10.0f), Util.dip2px(10.0f));
        }
        holder.tv_select_num.setText(str);
        holder.tv_select_name.setText(criusePointModel.pointReal);
        if (i == this.mData.size() - 1) {
            holder.iv_criuse_route_arrow.setVisibility(View.GONE);
        } else {
            holder.iv_criuse_route_arrow.setVisibility(View.VISIBLE);
        }
        return view;
    }

    /* loaded from: classes.dex */
    private static class Holder {
        ImageView iv_criuse_route_arrow;
        TextView tv_select_name;
        TextView tv_select_num;

        private Holder() {
        }
    }

    public void updatePoint(ArrayList<CriusePointModel> arrayList) {
        this.mData.clear();
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }
}
