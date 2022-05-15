package com.hehe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.hehe.utils.CusLogcat;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "heheRobot.db";
    private static final String TAG = "DatabaseHelper";
    public static  String TABLE_PATH="";
    private static DatabaseHelper instance;
    private Context context;
    private Map<String, Dao> daos = new HashMap();

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
    }

    static {
        TABLE_PATH=Environment.getExternalStorageDirectory().getPath()+"/database";
        File file = new File(Environment.getExternalStorageDirectory().getPath());
        if (!file.exists()) {
            boolean create= file.mkdir();
            CusLogcat.showDLog(TAG, "创建数据库成功======》"+file.canWrite()+"======"+create);
        }
    }

    private DatabaseHelper(Context context) {
        super(context, TABLE_PATH + File.separator + TABLE_NAME, null, 114);
        this.context = context;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PointTable.class);
            TableUtils.createTable(connectionSource, TaskTable.class);
            TableUtils.createTable(connectionSource, TimeFrame.class);
            TableUtils.createTable(connectionSource, DynamicCorpusTable.class);
            TableUtils.createTable(connectionSource, DynamicTextTable.class);
            TableUtils.createTable(connectionSource, DynamicHomePageTable.class);
            TableUtils.createTable(connectionSource, CruiseVoiceTable.class);
        } catch (SQLException e) {
            String str = TAG;
            CusLogcat.showDLog(str, "catch (SQLException e) e = " + e.getMessage());
        }
        initData();
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            if (instance == null) {
                synchronized (DatabaseHelper.class) {
                    if (instance == null) {
                        instance = new DatabaseHelper(context);
                    }
                }
            }
            databaseHelper = instance;
        }
        return databaseHelper;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public synchronized Dao getDao(Class cls) {
        Dao dao;
        dao = null;
        String name = cls.getName();
        if (this.daos.containsKey(name)) {
            dao = this.daos.get(name);
        }
        if (dao == null) {
            try {
                dao = super.getDao(cls);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.daos.put(name, dao);
        }
        return dao;
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper, android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public void close() {
        super.close();
        for (String str : this.daos.keySet()) {
            this.daos.get(str);
        }
    }

    public void initData() {
        PointTableDao pointTableDao = new PointTableDao(this.context);
        pointTableDao.add(new PointTable("充电桩", "charging_port", 2));
        pointTableDao.add(new PointTable("厨房", "厨房", 1));
    }
}
