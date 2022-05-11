package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DynamicCorpusDao {
    private Dao<DynamicCorpusTable, Integer> mDynamicCorpusDao;

    public DynamicCorpusDao(Context context) {
        try {
            this.mDynamicCorpusDao = DatabaseHelper.getHelper(context).getDao(DynamicCorpusTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DynamicCorpusTable> queryByType(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<DynamicCorpusTable, Integer> queryBuilder = this.mDynamicCorpusDao.queryBuilder();
            Where<DynamicCorpusTable, Integer> where = queryBuilder.where();
            where.eq("type", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void add(DynamicCorpusTable dynamicCorpusTable) {
        try {
            this.mDynamicCorpusDao.create(dynamicCorpusTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllDynamicCorpus() {
        try {
            this.mDynamicCorpusDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
