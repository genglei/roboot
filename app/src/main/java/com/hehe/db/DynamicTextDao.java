package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DynamicTextDao {
    private Dao<DynamicTextTable, Integer> mDynamicTextDao;

    public DynamicTextDao(Context context) {
        try {
            this.mDynamicTextDao = DatabaseHelper.getHelper(context).getDao(DynamicTextTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DynamicTextTable> queryByType(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<DynamicTextTable, Integer> queryBuilder = this.mDynamicTextDao.queryBuilder();
            Where<DynamicTextTable, Integer> where = queryBuilder.where();
            where.eq("type", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void add(DynamicTextTable dynamicTextTable) {
        try {
            this.mDynamicTextDao.create(dynamicTextTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllDynamicText() {
        try {
            this.mDynamicTextDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
