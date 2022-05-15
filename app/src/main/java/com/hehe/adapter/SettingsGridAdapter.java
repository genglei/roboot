package com.hehe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.bean.SwitchMode;
import com.hehe.interfaceUtil.OnItemTouchListener;


import java.util.List;

/* loaded from: classes.dex */
public class SettingsGridAdapter extends BaseAdapter {
    private final String TAG = SettingsGridAdapter.class.getSimpleName();
    private Context activity;
    List<SwitchMode> itemList;
    private OnItemTouchListener mOnItemTouchListener;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    static /* synthetic */ OnItemTouchListener access$000(SettingsGridAdapter settingsGridAdapter) {
        return settingsGridAdapter.mOnItemTouchListener;
    }

    public SettingsGridAdapter(Context context, List<SwitchMode> list) {
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

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            ViewHolder viewHolder2 = new ViewHolder();
            View inflate = ((LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.adapter_settings_gridview, (ViewGroup) null);
            viewHolder2.ll_item1 = (LinearLayout) inflate.findViewById(R.id.ll_item1);
            viewHolder2.iv_icon = (ImageView) inflate.findViewById(R.id.iv_icon);
            viewHolder2.tv_name = (TextView) inflate.findViewById(R.id.tv_name);
            inflate.setTag(viewHolder2);
            viewHolder = viewHolder2;
            view = inflate;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (this.mOnItemTouchListener != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.adapter.SettingsGridAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SettingsGridAdapter.access$000(SettingsGridAdapter.this).onTouch(i);
                }
            });
        }
        if (i == -1) {
            viewHolder.ll_item1.setVisibility(View.GONE);
        } else {
            viewHolder.ll_item1.setVisibility(View.VISIBLE);
        }
        viewHolder.iv_icon.setBackgroundResource(this.itemList.get(i).imgId);
        viewHolder.tv_name.setText(this.itemList.get(i).name);
        return view;
    }

    /* loaded from: classes.dex */
    class ViewHolder {
        ImageView iv_icon;
        LinearLayout ll_item1;
        TextView tv_name;

        ViewHolder() {
        }
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }
}
