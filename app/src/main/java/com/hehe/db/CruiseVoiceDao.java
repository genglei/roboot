package com.hehe.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/* loaded from: classes.dex */
public class CruiseVoiceDao {
    private Dao<CruiseVoiceTable, Integer> mCruiseVoiceDao;

    public CruiseVoiceDao(Context context) {
        try {
            this.mCruiseVoiceDao = DatabaseHelper.getHelper(context).getDao(CruiseVoiceTable.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCruiseVoice() {
        try {
            this.mCruiseVoiceDao.deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(CruiseVoiceTable cruiseVoiceTable) {
        try {
            this.mCruiseVoiceDao.create(cruiseVoiceTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CruiseVoiceTable> queryAllCriuseVoice() {
        try {
            return this.mCruiseVoiceDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
