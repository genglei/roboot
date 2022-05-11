package com.hehe.adapter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hehe.R;
import com.hehe.bean.TaskCategoryBean;
import com.hehe.interfaceUtil.OnItemTouchListener;


import java.util.List;

/* loaded from: classes.dex */
public class TaskCategoryAdapter extends BaseAdapter {
    private Context context;
    private int mCategoryIndex;
    private OnItemTouchListener mOnItemTouchListener;
    List<TaskCategoryBean> sList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }


    public TaskCategoryAdapter(Context context, List<TaskCategoryBean> list) {
        this.context = context;
        this.sList = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.sList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.sList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(this.context, R.layout.task_item_category, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_task_category_item);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.v_line);
        textView.setText(this.sList.get(i).title);
        if (this.mCategoryIndex == i) {
            relativeLayout.setBackgroundResource(R.drawable.ic_v_line);
            textView.setTextColor(this.context.getResources().getColor(R.color.taskCgText_C));
        } else {
            relativeLayout.setVisibility(View.GONE);
            textView.setTextColor(this.context.getResources().getColor(R.color.taskCgText));
        }
        if (this.mOnItemTouchListener != null) {
            inflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.higgs.deliveryrobot.adapter.TaskCategoryAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                  mOnItemTouchListener.onTouch(i);
                    return false;
                }
            });
        }
        return inflate;
    }

    public void setmCategoryIndex(int i) {
        this.mCategoryIndex = i;
        notifyDataSetChanged();
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }
}
