package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/* loaded from: classes.dex */
public class DefaultReplyDao {
    private Dao<DefaultReplyTable, Integer> mDefaultReplyDao;

    public DefaultReplyDao(Context context) {
        try {
            this.mDefaultReplyDao = DatabaseHelper.getHelper(context).getDao(DefaultReplyTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DefaultReplyTable> queryAllDefaultReply() {
        try {
            return this.mDefaultReplyDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
