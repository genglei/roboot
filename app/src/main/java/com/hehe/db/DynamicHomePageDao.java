package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DynamicHomePageDao {
    private Dao<DynamicHomePageTable, Integer> mDynamicHomePageDao;

    public DynamicHomePageDao(Context context) {
        try {
            this.mDynamicHomePageDao = DatabaseHelper.getHelper(context).getDao(DynamicHomePageTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DynamicHomePageTable> queryByType(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<DynamicHomePageTable, Integer> queryBuilder = this.mDynamicHomePageDao.queryBuilder();
            Where<DynamicHomePageTable, Integer> where = queryBuilder.where();
            where.eq("type", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<DynamicHomePageTable> queryAllPoint() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.mDynamicHomePageDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void add(DynamicHomePageTable dynamicHomePageTable) {
        try {
            this.mDynamicHomePageDao.create(dynamicHomePageTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllDynamicHomePage() {
        try {
            this.mDynamicHomePageDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
