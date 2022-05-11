package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PointTableDao {
    private Dao<PointTable, Integer> mPointDao;

    public PointTableDao(Context context) {
        try {
            this.mPointDao = DatabaseHelper.getHelper(context).getDao(PointTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(PointTable pointTable) {
        try {
            this.mPointDao.create(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(PointTable pointTable) {
        try {
            this.mPointDao.update(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PointTable> queryByPointAliasName(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<PointTable, Integer> queryBuilder = this.mPointDao.queryBuilder();
            Where<PointTable, Integer> where = queryBuilder.where();
            where.eq("tableAliasName", str);
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<PointTable> queryByTableRealPoint(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<PointTable, Integer> queryBuilder = this.mPointDao.queryBuilder();
            Where<PointTable, Integer> where = queryBuilder.where();
            where.eq("tableRealPoint", str);
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<PointTable> queryByTablePointId(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<PointTable, Integer> queryBuilder = this.mPointDao.queryBuilder();
            Where<PointTable, Integer> where = queryBuilder.where();
            where.eq("id", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<PointTable> queryAllPointByPointType(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<PointTable, Integer> queryBuilder = this.mPointDao.queryBuilder();
            Where<PointTable, Integer> where = queryBuilder.where();
            where.eq("pointType", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<PointTable> queryAllPoint() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.mPointDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void deleteByTablePoint(int i) {
        try {
            DeleteBuilder<PointTable, Integer> deleteBuilder = this.mPointDao.deleteBuilder();
            Where<PointTable, Integer> where = deleteBuilder.where();
            where.eq("id", Integer.valueOf(i));
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTablePointByName(String str) {
        try {
            DeleteBuilder<PointTable, Integer> deleteBuilder = this.mPointDao.deleteBuilder();
            Where<PointTable, Integer> where = deleteBuilder.where();
            where.eq("tableRealPoint", str);
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTablePoint() {
        try {
            this.mPointDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
