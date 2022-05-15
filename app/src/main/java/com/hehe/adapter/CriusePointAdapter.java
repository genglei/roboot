package com.hehe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.bean.CriusePointModel;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class CriusePointAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<CriusePointModel> mData = new ArrayList<>();

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public CriusePointAdapter(Context context) {
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
            view = LayoutInflater.from(this.mCtx).inflate(R.layout.adapter_criuse_gv_point, viewGroup, false);
            holder = new Holder();
            holder.flUsherPoint = (FrameLayout) view.findViewById(R.id.fl_usher_point);
            holder.tvPoint = (TextView) view.findViewById(R.id.tv_point);
            holder.tv = (TextView) view.findViewById(R.id.tv_select_criuse);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        if (criusePointModel == null || !criusePointModel.isSelect) {
            holder.tv.setVisibility(View.GONE);
            holder.tvPoint.setTextColor(this.mCtx.getResources().getColor(R.color.textColorforItemTitle));
            holder.flUsherPoint.setBackgroundResource(R.mipmap.button_usher_set_no_select);
        } else {
            holder.tv.setVisibility(View.VISIBLE);
            holder.tvPoint.setTextColor(this.mCtx.getResources().getColor(R.color.white));
            holder.flUsherPoint.setBackgroundResource(R.mipmap.button_usher_set_select);
        }
        holder.tvPoint.setText(criusePointModel.pointAlias + "");
        if (criusePointModel.selectNum < 10) {
            str = "0" + criusePointModel.selectNum;
        } else {
            str = "" + criusePointModel.selectNum;
        }
        holder.tv.setText(str);
        return view;
    }

    /* loaded from: classes.dex */
    private static class Holder {
        FrameLayout flUsherPoint;
        TextView tv;
        TextView tvPoint;

        private Holder() {
        }
    }

    public void updatePoint(ArrayList<CriusePointModel> arrayList) {
        this.mData.clear();
        this.mData.addAll(arrayList);
        notifyDataSetChanged();
    }
}
