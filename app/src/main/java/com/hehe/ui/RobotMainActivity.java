package com.hehe.ui;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hehe.R;
import com.hehe.adapter.LayersListViewdapter;
import com.hehe.adapter.SelectPointGridAdapter;
import com.hehe.adapter.TaskCategoryAdapter;
import com.hehe.bean.FoodLayer;
import com.hehe.bean.TaskCategoryBean;
import com.hehe.common.Constants;
import com.hehe.db.PointTable;
import com.hehe.db.PointTableDao;
import com.hehe.interfaceUtil.MonitorListViewListener;
import com.hehe.interfaceUtil.NetListener;
import com.hehe.interfaceUtil.OnItemTouchListener;
import com.hehe.utils.CusLogcat;
import com.hehe.utils.SharedPrefsUtil;
import com.hehe.utils.Util;
import com.hehe.view.KeyboardNormol;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RobotMainActivity extends BaseActivity implements View.OnClickListener {
    View mDecorView;
    public static final String TAG = "RobotMainActivity";

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
//        Util.getLocalVersion(this);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.mDecorView = getWindow().getDecorView();
        this.mDecorView.setSystemUiVisibility(4102);
        setContentView(R.layout.activity_robot);
        Util.NavigationBarStatusBar(this, true);
        initPermission();
        initView();
        initValue();
        initListener();
    }

    //授权
    private void initPermission() {
        String[] strArr;
        ArrayList arrayList = new ArrayList();
        for (String str : new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.WRITE_SETTINGS", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CAMERA", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.RECORD_AUDIO", "android.permission.WAKE_LOCK", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.KILL_BACKGROUND_PROCESSES", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_SETTINGS"}) {
            if (ContextCompat.checkSelfPermission(this, str) != 0) {
                arrayList.add(str);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        if (!arrayList.isEmpty()) {
            ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(strArr2), 123);
        }
    }

    private ListView mLayersList;
    private GridView mLayerCategoryGV;//所有已添加位置
    private GridView mPointLayoutGV;
    private LinearLayout mStartTaskLL;
    private TextView mStartDeliverTV;
    private ImageView mArrow0IV;
    private ImageView mArrow1IV;
    private ImageView mStopIV;
    private EditText mTmp0ET;
    private EditText mTmp1ET;
    private EditText mTmp2ET;
    private TextView mBackHomeTV;
    private TextView mRobotTaskCountTV;

    private void initView() {
        this.mLayersList = (ListView) findViewById(R.id.lv_layers);
        this.mLayerCategoryGV = (GridView) findViewById(R.id.gv_main_category);
        this.mPointLayoutGV = (GridView) findViewById(R.id.gv_layout);
        this.mStartTaskLL = (LinearLayout) findViewById(R.id.ll_start_task);
        this.mStartDeliverTV = (TextView) findViewById(R.id.tv_start_deliver);
        this.mArrow0IV = (ImageView) findViewById(R.id.iv_arrow0);
        this.mArrow1IV = (ImageView) findViewById(R.id.iv_arrow1);
        this.mStopIV = (ImageView) findViewById(R.id.iv_stop);
        this.mTmp0ET = (EditText) findViewById(R.id.editText1);
        mTmp0ET.setInputType(InputType.TYPE_NULL);
        this.mTmp1ET = (EditText) findViewById(R.id.editText2);
        this.mTmp2ET = (EditText) findViewById(R.id.editText3);
        this.mBackHomeTV = (TextView) findViewById(R.id.tv_back_home);
        this.mRobotTaskCountTV = (TextView) findViewById(R.id.tv_robot_task_count);
    }

    private ActivityManager mActivityManager;
    private List<FoodLayer> mLayerList = new ArrayList();
    private LayersListViewdapter mLayersListViewdapter;
    private List<TaskCategoryBean> taskCategories = new ArrayList();
    private List<PointTable> mModeList = new ArrayList();
    private TaskCategoryAdapter mTaskCategoryAdapter;
    //可直接选择位置的adapter
    private SelectPointGridAdapter mSelectGridAdapter;
    //桌号
    private PointTableDao mPointTableDao;
    private void initValue() {
//        Util.showDorbar(false);
        this.mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        this.mUsherPointAdapter = new UsherPointAdapter(this);
//        this.mCriusePointAdapter = new CriusePointAdapter(this);
//        this.mCriuseRouteAdapter = new CriuseRouteAdapter(this);
//        this.mUsherStartPointAdapter = new UsherStartPointAdapter(this);
//        this.mPosStart = "";
//        this.mRobotAnimationDialog = new RobotAnimationDialog(this);
//        this.mFirstChargePort = true;
//        this.mPermissionsResult = true;
//        this.mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        SharedPrefsUtil.put("com.higgs.setting.eyelock", true);//todo 替换
//        this.isEyeOpen = SharedPrefsUtil.get("com.higgs.setting.eyelock", true);
//        EventBus.getDefault().register(this);
//        this.mSwitchFan = SharedPrefsUtil.get("fan_switch_flag", true);
//        this.mRollLidSwitch = SharedPrefsUtil.get("roll_lid_flag", true);
//        this.mTaskSendDao = new TaskTableDao(this);
        this.mPointTableDao = new PointTableDao(this);
//        this.mCruiseVoiceDao = new CruiseVoiceDao(this);
//        this.mDefaultReplyDao = new DefaultReplyDao(this);
//        this.mDynamicCorpusDao = new DynamicCorpusDao(this);
//        this.mDynamicTextDao = new DynamicTextDao(this);
//        this.mDynamicHomePageDao = new DynamicHomePageDao(this);
//        clearSharedPrefs();
//        SharedPrefsUtil.put("password_base", "9986");
//        CusLogcat.showLockELog(TAG, "init lock");
//        SharedPrefsUtil.put("isLock", false);
//        SharedPrefsUtil.put("is_setting_lock", false);
//        SharedPrefsUtil.put("password_user", "0");
//        this.mSwitchVoiceInteraction = false;
//        this.mRobotStatus = 1;
        this.mLayerList.clear();
        this.mLayerList.add(new FoodLayer("F1", Util.getStringByStringXml(R.string.first_service_plate)));//第一层
        this.mLayerList.add(new FoodLayer("F2", Util.getStringByStringXml(R.string.second_service_plate)));//第二层
        this.mLayersListViewdapter = new LayersListViewdapter(this, this.mLayerList, null);//this.mCusHandler
        this.mLayersList.setAdapter((ListAdapter) this.mLayersListViewdapter);
        this.taskCategories.add(new TaskCategoryBean(Util.getStringByStringXml(R.string.direct_select)));
        this.taskCategories.add(new TaskCategoryBean(Util.getStringByStringXml(R.string.keyboard_input)));
        this.mTaskCategoryAdapter = new TaskCategoryAdapter(this, this.taskCategories);
        this.mLayerCategoryGV.setAdapter((ListAdapter) this.mTaskCategoryAdapter);
        this.mLayerCategoryGV.setSelection(0);
        this.mSelectGridAdapter = new SelectPointGridAdapter(getApplicationContext(), this.mModeList);
        this.mPointLayoutGV.setAdapter((ListAdapter) this.mSelectGridAdapter);
//        this.mTtsUtils = new TtsUtils();
//        this.mAboutUsNameAdapter = new AboutUsNameAdapter(this);
//        this.mAboutUsValueAdapter = new AboutUsValueAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CusLogcat.showLifeCycleELog(TAG, "onResume");
        resetSelectData();
    }

    private void resetSelectData() {
        this.mModeList.clear();
        List<PointTable> queryAllPointByPointType = this.mPointTableDao.queryAllPointByPointType(0);
        if (queryAllPointByPointType != null && queryAllPointByPointType.size() > 0) {
            this.mModeList.addAll(getModeOrderList(queryAllPointByPointType));
        }
        this.mSelectGridAdapter.notifyDataSetChanged();
    }

    private List<PointTable> getModeOrderList(List<PointTable> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (PointTable pointTable : list) {
            if (Constants.MATCH_CHINESE_CHARACTERS.matcher(pointTable.getTableAliasName()).find()) {
                arrayList.add(pointTable);
            } else if (Constants.MATCH_AZ_HEAD_AZ09_END.matcher(pointTable.getTableAliasName()).find()) {
                arrayList2.add(pointTable);
            } else if (Constants.MATCH_ONLY_AZ.matcher(pointTable.getTableAliasName()).find()) {
                arrayList2.add(pointTable);
            } else if (Constants.MATCH_NUM.matcher(pointTable.getTableAliasName()).find()) {
                arrayList3.add(pointTable);
            } else if (Constants.MATCH_NUM_LETTER.matcher(pointTable.getTableAliasName()).find()) {
                arrayList4.add(pointTable);
            }
        }
        Collections.sort(arrayList, new Comparator<PointTable>() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.94
            public int compare(PointTable pointTable2, PointTable pointTable3) {
                return pointTable2.getTableAliasName().length() - pointTable3.getTableAliasName().length();
            }
        });
        Collections.sort(arrayList2, new Comparator<PointTable>() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.95
            public int compare(PointTable pointTable2, PointTable pointTable3) {
                return pointTable2.getTableAliasName().toLowerCase().charAt(0) - pointTable3.getTableAliasName().toLowerCase().charAt(0);
            }
        });
        Collections.sort(arrayList3, new Comparator<PointTable>() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.96
            public int compare(PointTable pointTable2, PointTable pointTable3) {
                return Integer.parseInt(pointTable2.getTableAliasName()) - Integer.parseInt(pointTable3.getTableAliasName());
            }
        });
        Collections.sort(arrayList4, new Comparator<PointTable>() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.97
            public int compare(PointTable pointTable2, PointTable pointTable3) {
                return pointTable2.getTableAliasName().charAt(0) - pointTable3.getTableAliasName().charAt(0);
            }
        });
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        return arrayList;
    }

    private void initListener() {
//        this.mTtsUtils.initialTts(this);
        this.mTaskCategoryAdapter.setOnItemTouchListener(new OnItemTouchListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.6
            @Override // com.higgs.deliveryrobot.interfaceUtil.OnItemTouchListener
            public void onTouch(int i) {
                RobotMainActivity.this.playSound();
                selectCategory(i);
            }
        });
        this.mSelectGridAdapter.setOnItemTouchListener(new OnItemTouchListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.8
            @Override // com.higgs.deliveryrobot.interfaceUtil.OnItemTouchListener
            public void onTouch(int i) {//grid view 点击
                RobotMainActivity.this.playSound();
                clearSystemKeyBoard();
                selectItem(i);
                closeAllKeyBoard();
            }
        });
        this.mLayersListViewdapter.setMonitorListViewListener(new MonitorListViewListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.9
            @Override // com.higgs.deliveryrobot.interfaceUtil.MonitorListViewListener
            public void onChangeValue() {
                isStartDeviler();
            }
        });
        this.mLayersListViewdapter.setOnItemTouchListener(new OnItemTouchListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.10
            @Override // com.higgs.deliveryrobot.interfaceUtil.OnItemTouchListener
            public void onTouch(int i) {
                clickDevilerFoodItem(i);
            }
        });
        this.mBackHomeTV.setOnClickListener(this);
        this.mStartTaskLL.setOnClickListener(this);
        setNetListener(new CusNetListener(this));//网络
//        this.mTtsUtils.setTtsCallback(new CusTtsCallback(this));
    }

    private static class CusNetListener implements NetListener {
        private WeakReference<RobotMainActivity> mActivity;

        public CusNetListener(RobotMainActivity robotMainActivity) {
            this.mActivity = new WeakReference<>(robotMainActivity);
        }

        @Override // com.higgs.deliveryrobot.interfaceUtil.NetListener
        public void netSuccess(String str) {
            RobotMainActivity robotMainActivity = this.mActivity.get();
            if (robotMainActivity != null) {
                cusNetSuccess(str);
            }
        }

        @Override // com.higgs.deliveryrobot.interfaceUtil.NetListener
        public void netFail(Exception exc) {
            String str = RobotMainActivity.TAG;
            CusLogcat.showELog(str, "netFail(Exception e) e = " + exc.toString());
        }
    }

    private int mDynamicLibraryNetRequestFlag;

    private void cusNetSuccess(String str) {
        String str2 = TAG;
        CusLogcat.showDLog(str2, "netSuccess(String responseData) responseData = " + str);
        String str3 = TAG;
        CusLogcat.showDLog(str3, "netSuccess(String responseData) mDynamicLibraryNetRequestFlag = " + this.mDynamicLibraryNetRequestFlag);
        switch (this.mDynamicLibraryNetRequestFlag) {
            case 1:
                this.mDynamicCorpusDao.deleteAllDynamicCorpus();
                initDynamicCorpus(str);
                break;
            case 2:
                this.mDynamicTextDao.deleteAllDynamicText();
                initDynamicText(str);
                break;
            case 3:
                this.mDynamicHomePageDao.deleteAllDynamicHomePage();
                initDynamicHomePage(str);
                break;
            case 4:
                this.mCruiseVoiceDao.deleteAllCruiseVoice();
                initCruisePoint(str);
                break;
            case 5:
                updateDBPoint(str);
                break;
        }
        try {
            this.mReportHandler.CommandResult("com.higgs.deliveryrobot.robotmainactivity", this.mMqttCommand, 0, "任务成功", "");
        } catch (RemoteException e) {
            String str4 = TAG;
            CusLogcat.showDLog(str4, "else if (\"13\".equals(type)) catch (RemoteException e) e = " + e.getMessage());
        }
    }

    private void clickDevilerFoodItem(int i) {
        int size = this.mLayerList.size();

        for (int i2 = 0; i2 < size; i2++) {
            this.mLayerList.get(i2).status = 0;
        }
        this.mLayerList.get(i).status = 1;
        if (i == 0) {//上层点击
            this.mArrow0IV.setVisibility(View.VISIBLE);
            this.mArrow1IV.setVisibility(View.GONE);
        } else {//下层点击，箭头位置
            this.mArrow0IV.setVisibility(View.GONE);
            this.mArrow1IV.setVisibility(View.VISIBLE);
        }
        playSound();
        this.mLayersListViewdapter.notifyDataSetChanged();
        doClickLayerListView(i);
    }

    //选中的是第几层
    private int mLayerIndex;

    private void doClickLayerListView(int i) {
        this.mLayerIndex = i;
        this.mLayersListViewdapter.setTabButton(i);
        String str = TAG;
        CusLogcat.showShangbaoELog(str, "doClickLayerListView mLayerIndex = " + this.mLayerIndex);
        SharedPrefsUtil.put(this.mLayerIndex + "table_real_point", "");
        this.mLayersListViewdapter.notifyDataSetChanged();
        SelectPointGridAdapter selectPointGridAdapter = this.mSelectGridAdapter;
        if (selectPointGridAdapter != null) {
            selectPointGridAdapter.setLayerIndex(this.mLayerIndex);
            this.mSelectGridAdapter.notifyDataSetChanged();
        }
        clearSystemKeyBoard();
        showCusKeybordLayout();
    }

    private void showCusKeybordLayout() {
        if (this.mCategoryIndex == 0) {
            this.mPointLayoutGV.setVisibility(View.VISIBLE);
        } else {
            showKeyBoard(this.mLayerIndex);
        }
        this.mStartTaskLL.setVisibility(View.VISIBLE);
    }

    /***右侧第几个选种模式**/
    private int mCategoryIndex;

    private void selectCategory(int i) {
        this.mCategoryIndex = i;
        this.mTaskCategoryAdapter.setmCategoryIndex(i);
        if (this.mCategoryIndex == 0) {
            this.mPointLayoutGV.setVisibility(View.VISIBLE);
            closeAllKeyBoard();
            return;
        }
        this.mPointLayoutGV.setVisibility(View.GONE);
        showKeyBoard(this.mLayerIndex);
    }

    private KeyboardNormol mKeyboardNormol1;
    private KeyboardNormol mKeyboardNormol2;
    private KeyboardNormol mKeyboardNormol3;

    //展示键盘
    private void showKeyBoard(int i) {
        int i2 = this.mLayerIndex;
        closeAllKeyBoard();
        selectEditTextListener();
        switch (i2) {
            case 0:
                if (mKeyboardNormol1 == null) {
                    this.mKeyboardNormol1 = new KeyboardNormol(this, this, this.mTmp0ET, R.id.keyboard_view1);
                } else {
                    mKeyboardNormol1.hideKeyboard();
                    if (mKeyboardNormol2 != null) {
                        mKeyboardNormol2.hideKeyboard();
                    }
                    if (mKeyboardNormol3 != null) {
                        mKeyboardNormol3.hideKeyboard();
                    }
                }
                this.mKeyboardNormol1.showKeyboard();
                this.mStartTaskLL.setVisibility(View.VISIBLE);
                return;
            case 1:
                if (mKeyboardNormol2 == null) {
                    this.mKeyboardNormol2 = new KeyboardNormol(this, this, this.mTmp1ET, R.id.keyboard_view2);
                } else {
                    mKeyboardNormol2.hideKeyboard();
                    if (mKeyboardNormol1 != null) {
                        mKeyboardNormol1.hideKeyboard();
                    }
                    if (mKeyboardNormol3 != null) {
                        mKeyboardNormol3.hideKeyboard();
                    }
                }
                this.mStartTaskLL.setVisibility(View.VISIBLE);
                this.mKeyboardNormol2.showKeyboard();
                return;
            case 2:
                if (mKeyboardNormol3 == null) {
                    this.mKeyboardNormol3 = new KeyboardNormol(this, this, this.mTmp2ET, R.id.keyboard_view3);
                } else {
                    mKeyboardNormol3.hideKeyboard();
                    if (mKeyboardNormol1 != null) {
                        mKeyboardNormol1.hideKeyboard();
                    }
                    if (mKeyboardNormol2 != null) {
                        mKeyboardNormol2.hideKeyboard();
                    }
                }
                this.mStartTaskLL.setVisibility(View.VISIBLE);
                this.mKeyboardNormol3.showKeyboard();
                return;
            default:
                return;
        }
    }

    private CusTextChangedListener cusTextChangedListener;

    private void selectEditTextListener() {
        this.cusTextChangedListener = new CusTextChangedListener();
        EditText editText = this.mTmp0ET;
        if (editText != null) {
            editText.addTextChangedListener(this.cusTextChangedListener);
        }
        EditText editText2 = this.mTmp1ET;
        if (editText2 != null) {
            editText2.addTextChangedListener(this.cusTextChangedListener);
        }
        EditText editText3 = this.mTmp2ET;
        if (editText3 != null) {
            editText3.addTextChangedListener(this.cusTextChangedListener);
        }
    }

    private class CusTextChangedListener implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private CusTextChangedListener() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (mCategoryIndex == 1) {
                FoodLayer foodLayer = null;
                String str = RobotMainActivity.TAG;
                CusLogcat.showShangbaoELog(str, "afterTextChanged mLayerIndex = " + mLayerIndex);
                switch (mLayerIndex) {
                    case 0:
                        SharedPrefsUtil.put("0table_real_point", mTmp0ET.getText().toString());
                        foodLayer = (FoodLayer) mLayerList.get(0);
                        break;
                    case 1:
                        SharedPrefsUtil.put("1table_real_point", mTmp1ET.getText().toString());
                        foodLayer = (FoodLayer) mLayerList.get(1);
                        break;
                    case 2:
                        SharedPrefsUtil.put("2table_real_point", mTmp2ET.getText().toString());
                        break;
                }
                if (foodLayer.status != -1) {
                    foodLayer.hasValue = 1;
                    isStartDeviler();
                    mLayersListViewdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void clearSystemKeyBoard() {
        switch (this.mLayerIndex) {
            case 0:
                this.mTmp0ET.setText("");
                return;
            case 1:
                this.mTmp1ET.setText("");
                return;
            case 2:
                this.mTmp2ET.setText("");
                return;
            default:
                return;
        }
    }

    private void selectItem(int i) {
        String str = TAG;
        CusLogcat.showShangbaoELog(str, "selectItem mLayerIndex = " + this.mLayerIndex);
        FoodLayer foodLayer = this.mLayerList.get(this.mLayerIndex);
        String str2 = TAG;
        CusLogcat.showShangbaoELog(str2, "selectItem layer.status = " + foodLayer.status);
        if (foodLayer.status != -1) {
            String str3 = "";
            SharedPrefsUtil.get(this.mLayerIndex + "table_real_point", "");
            String str4 = this.mModeList.get(i).tableAliasName;
            if (TextUtils.isEmpty(str3) || !str4.equals(str3)) {
                foodLayer.hasValue = 1;
                SharedPrefsUtil.put(this.mLayerIndex + "table_real_point", str4);
            } else {
                foodLayer.hasValue = 0;
                SharedPrefsUtil.put(this.mLayerIndex + "table_real_point", "");
            }
            this.mSelectGridAdapter.notifyDataSetChanged();
            isStartDeviler();
            this.mLayersListViewdapter.notifyDataSetChanged();
        }
    }

    private void closeAllKeyBoard() {
        KeyboardNormol keyboardNormol = this.mKeyboardNormol1;
        if (keyboardNormol != null) {
            keyboardNormol.hideKeyboard();
        }
        KeyboardNormol keyboardNormol2 = this.mKeyboardNormol2;
        if (keyboardNormol2 != null) {
            keyboardNormol2.hideKeyboard();
        }
        KeyboardNormol keyboardNormol3 = this.mKeyboardNormol3;
        if (keyboardNormol3 != null) {
            keyboardNormol3.hideKeyboard();
        }
    }

    private void isStartDeviler() {//
        boolean z = false;
        for (int i = 0; i < this.mLayerList.size(); i++) {
            z = this.mLayerList.get(i).hasValue == 1;
            if (z) {
                break;
            }
        }
        if (z) {
            this.mStartDeliverTV.setBackgroundResource(R.drawable.start_deliver);
        } else {
            this.mStartDeliverTV.setBackgroundResource(R.drawable.start_deliver_before);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public List<FoodLayer> getLayerList() {
        return this.mLayerList;
    }
}
