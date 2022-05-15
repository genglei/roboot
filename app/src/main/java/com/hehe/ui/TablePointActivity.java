package com.hehe.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.hehe.R;
import com.hehe.adapter.PointGridAdapter;
import com.hehe.db.PointTable;
import com.hehe.db.PointTableDao;
import com.hehe.interfaceUtil.NetListener;
import com.hehe.utils.CusLogcat;
import com.hehe.utils.CusToast;
import com.hehe.utils.DipPxUtils;
import com.hehe.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class TablePointActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TablePointActivity";
    private Button mAddBT;
    private CloseKeyBordHandler mCloseKeyBordHandler;
    private Dialog mDeleteDialog;
    private ImageView mPointBackIV;
    private PointGridAdapter mPointGridAdapter;
    GridView mPointLayoutGV;
    private PointTableDao mPointTableDao;
    private RadioGroup mRadioGroup;
    private Button mSyncBT;
    private EditText mTableAliasNameET;
    private EditText mTablePointET;
    private ArrayList<PointTable> mTablePointList = new ArrayList<>();
    private Dialog mUpdateInfoDialog;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.higgs.deliveryrobot.ui.BaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_point);
        initView();
        initValue();
        setListener();
        reloadData();
    }

    private void initView() {
        this.mPointTableDao = new PointTableDao(this);
        this.mPointBackIV = (ImageView) findViewById(R.id.iv_point_back);
        this.mPointLayoutGV = (GridView) findViewById(R.id.gv_layout);
        this.mTableAliasNameET = (EditText) findViewById(R.id.et_tableAliasName);
        this.mTablePointET = (EditText) findViewById(R.id.et_tablePoint);
        this.mRadioGroup = (RadioGroup) findViewById(R.id.rg_layout);
        this.rb1 = (RadioButton) findViewById(R.id.rb1);
        this.rb2 = (RadioButton) findViewById(R.id.rb2);
        this.rb3 = (RadioButton) findViewById(R.id.rb3);
        this.mAddBT = (Button) findViewById(R.id.bt_add);
        this.mSyncBT = (Button) findViewById(R.id.bt_sync);
    }

    private void initValue() {
        this.mPointGridAdapter = new PointGridAdapter(this);
        this.mPointLayoutGV.setAdapter((ListAdapter) this.mPointGridAdapter);
        getPointToken();
        this.mCloseKeyBordHandler = new CloseKeyBordHandler(this);
    }

    protected void setListener() {
        //具体 位置 点击 调用删除
        this.mPointLayoutGV.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != 0 && i != 1) {
                    TablePointActivity tablePointActivity = TablePointActivity.this;
                    tablePointActivity.showDeleteDialog((PointTable) mTablePointList.get(i));
                }
            }
        });

        //具体点位长按事件响应
        this.mPointLayoutGV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!(i == 0 || i == 1)) {
                    TablePointActivity tablePointActivity = TablePointActivity.this;
                    tablePointActivity.showUpdateInfoDialog((PointTable) mTablePointList.get(i), null);
                }
                return true;
            }
        });
        setNetListener(new NetListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.3
            @Override // com.higgs.deliveryrobot.interfaceUtil.NetListener
            public void netSuccess(String str) {
                String access$100 = TAG;
                CusLogcat.showDLog(access$100, "netSuccess(String responseData) responseData = " + str);
                updateDBPoint(str);
            }

            @Override // com.higgs.deliveryrobot.interfaceUtil.NetListener
            public void netFail(Exception exc) {
                String access$100 = TAG;
                CusLogcat.showELog(access$100, "netFail(Exception e) e = " + exc.getMessage());
            }
        });
        this.mPointBackIV.setOnClickListener(this);
        this.mAddBT.setOnClickListener(this);
        this.mSyncBT.setOnClickListener(this);
    }

    private void updateDBPoint(String str) {
        String str2 = TAG;
        CusLogcat.showDLog(str2, "updateDBPoint(String responseData) = " + str);
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("data")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("data");
                    int length = jSONArray.length();
                    String str3 = TAG;
                    CusLogcat.showDLog(str3, "jaSize = " + length);
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String string = jSONObject2.getString("name");
                        String string2 = jSONObject2.getString("intro");
                        String str4 = "";
                        if (jSONObject2.has("bellNum")) {
                            str4 = jSONObject2.getString("bellNum");
                        }
                        String str5 = TAG;
                        CusLogcat.showDLog(str5, "realName = " + string + ";aliasName = " + string2 + ";ringName = " + str4);
                        if (this.mPointTableDao == null) {
                            this.mPointTableDao = new PointTableDao(this);
                        }
                        List<PointTable> queryByPointAliasName = this.mPointTableDao.queryByPointAliasName(string2);
                        String str6 = TAG;
                        CusLogcat.showDLog(str6, "pointTables = " + queryByPointAliasName.toString());
                        if (queryByPointAliasName == null || queryByPointAliasName.size() <= 0) {
                            String str7 = TAG;
                            CusLogcat.showDLog(str7, "在数据库中没有找到" + string2 + "该点位");
                            PointTable pointTable = new PointTable();
                            pointTable.tableAliasName = string2;
                            pointTable.pointType = 0;
                            pointTable.ringNum = Util.stringToByte(str4);
                            pointTable.tableRealPoint = string;
                            this.mPointTableDao.add(pointTable);
                        } else {
                            PointTable pointTable2 = queryByPointAliasName.get(0);
                            String str8 = TAG;
                            CusLogcat.showDLog(str8, "pointTable = " + pointTable2.toString());
                            pointTable2.ringNum = Util.stringToByte(str4);
                            this.mPointTableDao.update(pointTable2);
                        }
                    }
                    reloadData();
                    CusToast.showToast(Util.getStringByStringXml(R.string.sync_success));
                }
            } catch (Exception e) {
                CusToast.showToast(Util.getStringByStringXml(R.string.sync_fail) + e.getMessage());
                e.printStackTrace();
                String str9 = TAG;
                CusLogcat.showDLog(str9, "updateDBPoint Exception e = " + e.getMessage());
            }
        }
    }

    private void keyBoardCancle() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    /**
     * 重新加载数据
     * **/
    private void reloadData() {
        this.mTablePointList.clear();
        List<PointTable> queryAllPointByPointType = this.mPointTableDao.queryAllPointByPointType(0);
        List<PointTable> queryAllPointByPointType2 = this.mPointTableDao.queryAllPointByPointType(1);
        List<PointTable> queryAllPointByPointType3 = this.mPointTableDao.queryAllPointByPointType(2);
        if (queryAllPointByPointType2 != null && queryAllPointByPointType2.size() > 0) {
            this.mTablePointList.addAll(queryAllPointByPointType2);
        }
        if (queryAllPointByPointType3 != null && queryAllPointByPointType3.size() > 0) {
            this.mTablePointList.addAll(queryAllPointByPointType3);
        }
        if (queryAllPointByPointType != null && queryAllPointByPointType.size() > 0) {
            this.mTablePointList.addAll(queryAllPointByPointType);
        }
        String str = TAG;
        CusLogcat.showDLog(str, "所有点位：" + this.mTablePointList.toString());
        this.mPointGridAdapter.updataPoint(this.mTablePointList);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        keyBoardCancle();
        int id = view.getId();
        if (id == R.id.bt_add) {
            addPointToDatabase();
        } else if (id == R.id.bt_sync) {
            syncPoint();
        } else if (id == R.id.iv_point_back) {
            playSound();
            finish();
        }
    }

    private void syncPoint() {
        CusLogcat.showDLog(TAG, "syncPoint()");
        if (TextUtils.isEmpty(this.mPointToken)) {
            CusToast.showToast(Util.getStringByStringXml(R.string.mPointToken_is_empty));
            getPointToken();
        } else if (TextUtils.isEmpty(this.mRobotId)) {
            CusToast.showToast(Util.getStringByStringXml(R.string.mRobotId_is_empty));
        } else if (TextUtils.isEmpty(mCurrentMapName)) {
            CusToast.showToast(Util.getStringByStringXml(R.string.mCurrentMapName_is_empty));
        } else {
            get("https://api.mk.higgsdynamics.com/openapi/robots/maps/points?no=" + this.mRobotId + "&map=" + mCurrentMapName, this.mPointToken);
        }
    }

    private void addPointToDatabase() {
        String obj = this.mTableAliasNameET.getText().toString();
        String obj2 = this.mTablePointET.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            CusToast.showToast(Util.getStringByStringXml(R.string.dining_table_not_empty));
        } else if (TextUtils.isEmpty(obj2)) {
            CusToast.showToast(Util.getStringByStringXml(R.string.point_is_not_empty));
        } else {
            PointTable pointTable = null;
            if (this.rb1.isChecked()) {
                pointTable = new PointTable(obj, obj2, 0);
            } else if (this.rb2.isChecked()) {
                pointTable = new PointTable(obj, obj2, 1);
            } else if (this.rb3.isChecked()) {
                pointTable = new PointTable(obj, obj2, 2);
            }
            this.mTableAliasNameET.setText("");
            this.mTablePointET.setText("");
            //从数据库查询
            List<PointTable> queryByTableRealPoint = this.mPointTableDao.queryByTableRealPoint(obj2);
            List<PointTable> queryByPointAliasName = this.mPointTableDao.queryByPointAliasName(obj);
            if (queryByTableRealPoint == null || queryByTableRealPoint.size() > 0 || queryByPointAliasName == null || queryByPointAliasName.size() > 0) {
                showUpdateInfoDialog(queryByTableRealPoint.get(0), pointTable);
            } else {
                //像list 及数据库中穿插数据
                this.mTablePointList.add(0, pointTable);
                this.mPointTableDao.add(pointTable);
                //像远端上传数据
                addPointToRemote(obj, obj2);
                CusToast.showToast(Util.getStringByStringXml(R.string.save_point_success));
            }
            reloadData();
        }
    }

    //删除对话框
    public void showDeleteDialog(final PointTable pointTable) {
        if (this.mDeleteDialog == null) {
            this.mDeleteDialog = new Dialog(this, R.style.dialog_style);
        }
        this.mDeleteDialog.setContentView(View.inflate(this, R.layout.dialog_net_not, null), new ViewGroup.LayoutParams((DipPxUtils.getScreenWidth(this) * 2) / 3, -2));
        ((TextView) this.mDeleteDialog.findViewById(R.id.tv_title)).setText(Util.getStringByStringXml(R.string.delete_point));
        ((TextView) this.mDeleteDialog.findViewById(R.id.tv_content)).setText(String.format(Util.getStringByStringXml(R.string.confirm_delete_point), pointTable.tableRealPoint, pointTable.tableAliasName));
        ((Button) this.mDeleteDialog.findViewById(R.id.btn_net_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                keyBoardCancle();
                mDeleteDialog.dismiss();
            }
        });
        ((Button) this.mDeleteDialog.findViewById(R.id.btn_net_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {//删除点位
                keyBoardCancle();
                mPointTableDao.deleteByTablePoint(pointTable.id.intValue());
                TablePointActivity.this.delPoint(pointTable.tableRealPoint);
                reloadData();
                CusToast.showToast(Util.getStringByStringXml(2131558442));
                mDeleteDialog.dismiss();
                keyBoardCancle();
            }
        });
        if (!this.mDeleteDialog.isShowing()) {
            this.mDeleteDialog.show();
        }
    }

    //展示更新按钮
    public void showUpdateInfoDialog(final PointTable pointTable, final PointTable pointTable2) {
        if (this.mUpdateInfoDialog == null) {
            this.mUpdateInfoDialog = new Dialog(this, R.style.dialog_style);
        }
        this.mUpdateInfoDialog.setContentView(View.inflate(this, R.layout.dialog_point_update, null), new ViewGroup.LayoutParams((DipPxUtils.getScreenWidth(this) * 7) / 12, -2));
        TextView textView = (TextView) this.mUpdateInfoDialog.findViewById(R.id.tv_content);
        final EditText editText = (EditText) this.mUpdateInfoDialog.findViewById(R.id.et_tableName);
        final EditText editText2 = (EditText) this.mUpdateInfoDialog.findViewById(R.id.et_tablePoint);
        editText2.setVisibility(View.GONE);
        ((TextView) this.mUpdateInfoDialog.findViewById(R.id.tv_title)).setText(Util.getStringByStringXml(R.string.point_already_exist));
        String typeStr = typeStr(pointTable.pointType.intValue());
        String typeStr2 = pointTable2 != null ? typeStr(pointTable2.pointType.intValue()) : typeStr;
        if (pointTable2 != null) {
            editText.setText(pointTable2.tableAliasName);
            editText2.setText(pointTable2.tableRealPoint);
            textView.setText(String.format(Util.getStringByStringXml(R.string.already_exist_point_override), pointTable.tableRealPoint, typeStr, pointTable.tableAliasName, pointTable2.tableRealPoint, typeStr, pointTable2.tableAliasName));
        } else {
            editText.setText(pointTable.tableAliasName);
            editText2.setText(pointTable.tableRealPoint);
            textView.setText(String.format(Util.getStringByStringXml(R.string.update_already_exist_point), pointTable.tableRealPoint, typeStr2, pointTable.tableAliasName));
        }
        ((Button) this.mUpdateInfoDialog.findViewById(R.id.btn_net_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mUpdateInfoDialog.dismiss();
            }
        });
        ((Button) this.mUpdateInfoDialog.findViewById(R.id.btn_net_setting)).setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                pointTable.tableAliasName = editText.getText().toString();
                pointTable.pointType = 0;
                pointTable.tableRealPoint = editText2.getText().toString();
                mPointTableDao.update(pointTable);
                if (pointTable2 != null) {
                    CusToast.showToast(Util.getStringByStringXml(R.string.already_override));
                } else {
                    CusToast.showToast(Util.getStringByStringXml(R.string.already_update));
                }
                reloadData();
                mUpdateInfoDialog.dismiss();
            }
        });
        if (!this.mUpdateInfoDialog.isShowing()) {
            this.mUpdateInfoDialog.show();
        }
        this.mUpdateInfoDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.8
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                mCloseKeyBordHandler.sendEmptyMessageDelayed(1, 500L);
                CusLogcat.showDLog(TAG, "mUpdateInfoDialog.setOnDismissListener onDismiss(DialogInterface dialog)");
            }
        });
    }

    private String typeStr(int i) {
        String stringByStringXml = Util.getStringByStringXml(R.string.dining_table);
        switch (i) {
            case 0:
                return Util.getStringByStringXml(R.string.dining_table);
            case 1:
                return Util.getStringByStringXml(R.string.kitchen);
            case 2:
                return Util.getStringByStringXml(R.string.charging_pile);
            default:
                return stringByStringXml;
        }
    }

    /**
     * 向远端添加数据
     **/
    public void addPointToRemote(String str, String str2) {
        String str3 = TAG;
        CusLogcat.showDLog(str3, "添加的点位别名为：" + str + "点位名为：" + str2);
        String str4 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("添加的点位上传后台Token为: ");
        sb.append(this.mPointToken);
        CusLogcat.showDLog(str4, sb.toString());
        CusLogcat.showDLog(TAG, "添加的点位上传后台URL为：https://htkapi.higgsdynamics.com/openapi/robots/maps/points");
        String str5 = TAG;
        CusLogcat.showDLog(str5, "mRobotId = " + this.mRobotId + ";mCurrentMapName = " + mCurrentMapName + "; type: 0");
        if (this.mPointToken == null) {
            CusLogcat.showELog(TAG, "添加点位上报后台时token为空, 重新请求");
            CusToast.showToast(Util.getStringByStringXml(R.string.add_point_upload_remote_token_empty));
            getPointToken();
            return;
        }
        FormBody build = new FormBody.Builder().add("no", this.mRobotId).add("map", mCurrentMapName).add("name", str2).add("intro", str).add("type", "0").build();
        Request.Builder url = new Request.Builder().url("https://api.mk.higgsdynamics.com/openapi/robots/maps/points");
        this.mOkHttpClient.newCall(url.addHeader("Authorization", "Bearer " + this.mPointToken).post(build).build()).enqueue(new Callback() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.9
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                CusLogcat.showDLog(TAG, "添加点位上传后台失败");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                String access$100 = TAG;
                CusLogcat.showDLog(access$100, "添加点位上传后台成功的响应: " + string);
            }
        });
    }

    public void delPoint(String str) {
        String str2 = TAG;
        CusLogcat.showELog(str2, "删除的点位名为：" + str);
        if (this.mPointToken == null) {
            CusLogcat.showELog(TAG, "删除点位上报后台时token为空");
            getPointToken();
            CusToast.showToast(Util.getStringByStringXml(2131558480));
            return;
        }
        FormBody build = new FormBody.Builder().add("no", this.mRobotId).add("map", mCurrentMapName).add("name", str).build();
        Request.Builder url = new Request.Builder().url("https://api.mk.higgsdynamics.com/openapi/robots/maps/points");
        this.mOkHttpClient.newCall(url.addHeader("Authorization", "Bearer " + this.mPointToken).delete(build).build()).enqueue(new Callback() { // from class: com.higgs.deliveryrobot.ui.TablePointActivity.10
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                CusLogcat.showELog(TAG, "删除点位上传后台失败");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                String access$100 = TAG;
                CusLogcat.showELog(access$100, "删除点位上报后台响应response: " + string);
            }
        });
    }

    /* loaded from: classes.dex */
    private static class CloseKeyBordHandler extends Handler {
        private final WeakReference<TablePointActivity> mActivity;

        public CloseKeyBordHandler(TablePointActivity tablePointActivity) {
            this.mActivity = new WeakReference<>(tablePointActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            TablePointActivity tablePointActivity = this.mActivity.get();
            if (tablePointActivity == null) {
                super.handleMessage(message);
            } else {
                tablePointActivity.tablePointHandleMessage(message);
            }
        }
    }

    private void tablePointHandleMessage(Message message) {
        keyBoardCancle();
    }
}
