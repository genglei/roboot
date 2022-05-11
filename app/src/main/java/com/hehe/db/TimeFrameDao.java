package com.hehe.db;

import android.content.Context;

import com.hehe.utils.CusLogcat;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TimeFrameDao {
    private static final String TAG = "TimeFrameDao";
    private Dao<TimeFrame, Integer> mTimeFrameDao;

    public TimeFrameDao(Context context) {
        try {
            this.mTimeFrameDao = DatabaseHelper.getHelper(context).getDao(TimeFrame.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(TimeFrame timeFrame) {
        try {
            this.mTimeFrameDao.create( timeFrame);
        } catch (SQLException e) {
            String str = TAG;
            CusLogcat.showELog(str, "插入TimeFrame数据抛异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(TimeFrame timeFrame) {
        try {
            this.mTimeFrameDao.update( timeFrame);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TimeFrame> queryAllTimeFrame() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.mTimeFrameDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void deleteByTimeFrame(int i) {
        try {
            DeleteBuilder<TimeFrame, Integer> deleteBuilder = this.mTimeFrameDao.deleteBuilder();
            Where<TimeFrame, Integer> where = deleteBuilder.where();
            where.eq("id", Integer.valueOf(i));
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
