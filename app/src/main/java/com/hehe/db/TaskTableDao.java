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
public class TaskTableDao {
    private Dao<TaskTable, Integer> taskSendDao;

    public TaskTableDao(Context context) {
        this.taskSendDao = DatabaseHelper.getHelper(context).getDao(TaskTable.class);
    }

    public void add(TaskTable taskTable) {
        try {
            this.taskSendDao.create(taskTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(TaskTable taskTable) {
        try {
            this.taskSendDao.update(taskTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TaskTable> queryByTaskLayer(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<TaskTable, Integer> queryBuilder = this.taskSendDao.queryBuilder();
            Where<TaskTable, Integer> where = queryBuilder.where();
            where.eq("taskLayer", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<TaskTable> queryTaskSendByTaskSate(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<TaskTable, Integer> queryBuilder = this.taskSendDao.queryBuilder();
            Where<TaskTable, Integer> where = queryBuilder.where();
            where.eq("taskState", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<TaskTable> queryByTablePointId(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<TaskTable, Integer> queryBuilder = this.taskSendDao.queryBuilder();
            Where<TaskTable, Integer> where = queryBuilder.where();
            where.eq("tablePointId", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void deleteByPointName(String str) {
        try {
            DeleteBuilder<TaskTable, Integer> deleteBuilder = this.taskSendDao.deleteBuilder();
            Where<TaskTable, Integer> where = deleteBuilder.where();
            where.eq("tablePointName", str);
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int i) {
        try {
            DeleteBuilder<TaskTable, Integer> deleteBuilder = this.taskSendDao.deleteBuilder();
            Where<TaskTable, Integer> where = deleteBuilder.where();
            where.eq("id", Integer.valueOf(i));
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TaskTable> queryById(int i) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<TaskTable, Integer> queryBuilder = this.taskSendDao.queryBuilder();
            Where<TaskTable, Integer> where = queryBuilder.where();
            where.eq("id", Integer.valueOf(i));
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<TaskTable> queryByTablePointName(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            QueryBuilder<TaskTable, Integer> queryBuilder = this.taskSendDao.queryBuilder();
            Where<TaskTable, Integer> where = queryBuilder.where();
            where.eq("tablePointName", str);
            queryBuilder.setWhere(where);
            return queryBuilder.query() != null ? queryBuilder.query() : arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public List<TaskTable> queryAllTaskSend() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.taskSendDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public void deleteByTaskSendTaskLayer(int i) {
        try {
            DeleteBuilder<TaskTable, Integer> deleteBuilder = this.taskSendDao.deleteBuilder();
            Where<TaskTable, Integer> where = deleteBuilder.where();
            where.eq("taskLayer", Integer.valueOf(i));
            deleteBuilder.setWhere(where);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTaskSend() {
        try {
            this.taskSendDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
