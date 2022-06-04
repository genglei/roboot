package com.hehe.ui;


import static com.hehe.common.Constants.DISMISS_COMPLETE_DIALOG;
import static com.hehe.common.Constants.HOST_XIAO_PANG;
import static com.hehe.common.Constants.MAP_POINTS;
import static com.hehe.common.Constants.MSG_GO_CRIUSE;
import static com.hehe.common.Constants.MSG_GO_MARKET;
import static com.hehe.common.Constants.TYPE_KITCHEN;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.hehe.R;
import com.hehe.adapter.CriusePointAdapter;
import com.hehe.adapter.CriuseRouteAdapter;
import com.hehe.adapter.LayersListViewdapter;
import com.hehe.adapter.SelectPointGridAdapter;
import com.hehe.adapter.SettingsGridAdapter;
import com.hehe.adapter.TaskCategoryAdapter;
import com.hehe.bean.CriusePointModel;
import com.hehe.bean.CriuseRouteCompare;
import com.hehe.bean.FoodLayer;
import com.hehe.bean.SwitchMode;
import com.hehe.bean.TaskCategoryBean;
import com.hehe.common.Constants;
import com.hehe.db.CruiseVoiceDao;
import com.hehe.db.CruiseVoiceTable;
import com.hehe.db.DynamicHomePageDao;
import com.hehe.db.DynamicHomePageTable;
import com.hehe.db.PointTable;
import com.hehe.db.PointTableDao;
import com.hehe.db.TaskTable;
import com.hehe.db.TaskTableDao;
import com.hehe.interfaceUtil.MonitorListViewListener;
import com.hehe.interfaceUtil.NetListener;
import com.hehe.interfaceUtil.OnItemTouchListener;
import com.hehe.utils.CusFullDialog;
import com.hehe.utils.CusLogcat;
import com.hehe.utils.CusToast;
import com.hehe.utils.SharedPrefsUtil;
import com.hehe.utils.Util;
import com.hehe.view.KeyboardNormol;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.yunji.water.callback.RobotMoveCallback;
import ai.yunji.water.entity.Marker;
import ai.yunji.water.task.RobotConnectAction;

public class RobotMainActivity extends BaseActivity implements View.OnClickListener {
    View mDecorView;
    public static final String TAG = "RobotMainActivity";
    private DoMainTaskHandler doMainTaskHandler;
    private int mDynamicLibraryNetRequestFlag;
    private int mRobotStatus;

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

        initHandler();
        initView();
        initValue();
        initListener();
    }

    private static class DoMainTaskHandler extends Handler {//主要任务handler
        private final WeakReference<RobotMainActivity> mActivity;

        public DoMainTaskHandler(RobotMainActivity robotMainActivity, Looper looper) {
            super(looper);
            this.mActivity = new WeakReference<>(robotMainActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RobotMainActivity robotMainActivity = this.mActivity.get();
            if (robotMainActivity == null) {
                super.handleMessage(message);
            } else {
                robotMainActivity.doMainTaskHandleMessage(message);
            }
        }
    }

    private int mCurrentType = -1;
    private int mCruisePointIndex;
    private int mMoveFlag;
    private boolean mFunctionCruiseFlag = true;

    /**
     * 子线程执行消息
     * */
    private void doMainTaskHandleMessage(Message message) {//主要任务处理
        CusLogcat.showDLog(TAG, "doMainTaskHandleMessage(Message msg)");
        switch (message.what) {
            case 212:
//                goChargeTask();
                return;
            case 213:
//                CusLogcat.showShangbaoELog(TAG, "mChargeState = " + this.mChargeState);
//                goChuFangTask();
                return;
            case MSG_GO_MARKET:
//                this.mTaskSendDao.deleteAllTaskSend();
//                this.mCusHandler.removeCallbacks(this.runnableRandomStr);
//                startExcuteTask();
                return;
            case 215:
//                goHostess();
                return;
            case MSG_GO_CRIUSE://巡游模式
                int size = this.mCruiseRouteModels.size();
                ArrayList<Marker> markers  = new ArrayList<>();
                this.mCurrentType = 5;
                for (int i = 0; i < size; i++) {
                    CriusePointModel criusePointModel = this.mCruiseRouteModels.get(i);
                    this.mCriuseSB.append(criusePointModel.pointReal);
                    this.mTaskSendDao.add(new TaskTable(Integer.valueOf(i), 8, Integer.valueOf(i), criusePointModel.pointReal, 0, false, true));
                    this.mCriuseSB.append(",");
                    // todo  巡游模式的数据结构封装成marker
                }
                CusLogcat.showDLog(TAG, "巡游路线:" + this.mCriuseSB.toString());

                RobotConnectAction.init(this).sendMoveMarkers(markers,-1,new RobotMoveCallback(){

                });
                if (this.mCruisePointIndex < size) {
                    CusLogcat.showDLog(TAG, "巡游路线 mCruiseRouteModels: " + this.mCruiseRouteModels.toString());
//                    this.mRemote.transact(2, obtain, obtain2, 0);

//                    Util.moveTarget(this.mSocketInterface, this.mCruiseRouteModels.get(this.mCruisePointIndex).pointReal);
                }
                this.mMoveFlag = 8;
                this.mFunctionCruiseFlag = true;
//               声音播放
//                List<CruiseVoiceTable> queryAllCriuseVoice = this.mCruiseVoiceDao.queryAllCriuseVoice();
//                if (queryAllCriuseVoice != null) {
//                    int size2 = queryAllCriuseVoice.size();
//                    for (int i2 = 0; i2 < size2; i2++) {
//                        this.mCruiseVoiceTables.add(queryAllCriuseVoice.get(i2));
//                    }
//                    return;
//                }
                return;
            default:
                return;
        }
    }

    private long mStartTime = System.currentTimeMillis();
    private List<String> tableNameList = new ArrayList();
    Map<String, String> mTaskMap = new HashMap();
    /**
     * 开始配送模式，先送到餐桌、然后送会厨房
     * */
    private void startExcuteTask() {
        List<PointTable> queryByPointAliasName;
        PointTable pointTable;
        List<PointTable> queryByPointAliasName2;
        PointTable pointTable2;
        CusLogcat.showELog(TAG, "startExcuteTask()");
        this.mCurrentType = 4;
        boolean z = false;
        this.mMoveFlag = 0;
        this.mStartTime = System.currentTimeMillis();
        checkPointAndKitchen();
        String str = SharedPrefsUtil.get("0table_real_point", "");
        String str2 = SharedPrefsUtil.get("1table_real_point", "");
        this.tableNameList.clear();
        this.tableNameList.add(str);
        this.tableNameList.add(str2);
        for (int i = 0; i < this.tableNameList.size(); i++) {//根据别名进行查询
            if (!TextUtils.isEmpty(this.tableNameList.get(i)) && (queryByPointAliasName2 = this.mPointTableDao.queryByPointAliasName(this.tableNameList.get(i))) != null && queryByPointAliasName2.size() > 0 && (pointTable2 = queryByPointAliasName2.get(0)) != null && !TextUtils.isEmpty(pointTable2.tableRealPoint)) {
                CusLogcat.showDLog(TAG, "把选中的点位 " + pointTable2.tableRealPoint + " 放入TaskTable");
                this.mTaskSendDao.add(new TaskTable(Integer.valueOf(i), Integer.valueOf(i), pointTable2.id, pointTable2.tableRealPoint, 0, false, true));
                this.mTaskMap.put("" + i, pointTable2.tableRealPoint);
            }
        }
        List<PointTable> queryAllPointByPointType = this.mPointTableDao.queryAllPointByPointType(TYPE_KITCHEN);
        if (queryAllPointByPointType != null && queryAllPointByPointType.size() > 0 && (pointTable = queryAllPointByPointType.get(0)) != null && !TextUtils.isEmpty(pointTable.tableRealPoint)) {
            CusLogcat.showLog("增加厨房的点位 " + pointTable.tableRealPoint + " 到TaskTable");
            this.mTaskSendDao.add(new TaskTable(3, 3, queryAllPointByPointType.get(0).id, pointTable.tableRealPoint, 0, false, false));
            if (this.mTaskMap != null) {
                CusLogcat.showELog(TAG, "taskMap" + this.mTaskMap.size());
                if (!pointTable.tableRealPoint.equals(this.mTaskMap.get("" + (this.mTaskMap.size() - 1)))) {
                    this.mTaskMap.put("" + this.mTaskMap.size(), queryAllPointByPointType.get(0).tableRealPoint);
                }
            } else {
                CusLogcat.showELog(TAG, "taskMap为空");
            }
        }
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            for (int i2 = 0; i2 < this.tableNameList.size(); i2++) {
                String str3 = this.tableNameList.get(i2);
                if (!TextUtils.isEmpty(str3) && ((queryByPointAliasName = this.mPointTableDao.queryByPointAliasName(str3)) == null || queryByPointAliasName.size() <= 0)) {
                    //startSpeak("您输入的桌号不存在，请输入正确的桌号");
                    CusToast.showToast(Util.getStringByStringXml(R.string.position_no_exist));
                    z = true;
                    break;
                }
            }
            if (z) {
                CusLogcat.showLog("输入的桌号不存在");
                return;
            }
           // doStartTask(true);
            this.mRobotStatus = 4;
            return;
        }
        CusToast.showToast(Util.getStringByStringXml(R.string.please_select_objective_position));
//       startSpeak(Util.getStringByStringXml(R.string.please_select_objective_position));
    }

//    private void doStartTask(Boolean bool) {
//        CusFullDialog cusFullDialog;
//        CusLogcat.showELog(TAG, "doStartTask isFirst = " + bool);
//        if (!isFinishing() && (cusFullDialog = this.mTaskingDialog) != null && cusFullDialog.isShowing()) {
//            this.mTaskingDialog.dismiss();
//        }
//        Message obtain = Message.obtain();
//        obtain.what = DISMISS_COMPLETE_DIALOG;
//        this.mCusHandler.sendMessageDelayed(obtain, 500L);
//        List<TaskTable> taskListByTaskState = getTaskListByTaskState(0);
//        if (taskListByTaskState.size() > 0) {
//            int i = 0;
//            while (true) {
//                if (i >= taskListByTaskState.size()) {
//                    break;
//                }
//                TaskTable taskTable = taskListByTaskState.get(i);
//                if (taskTable != null && taskTable.taskState.intValue() == 0) {
//                    CusLogcat.showELog(TAG, "未到达的任务：" + taskTable.toString());
//                    List<PointTable> queryByTablePointId = this.mPointTableDao.queryByTablePointId(taskListByTaskState.get(i).tablePointId.intValue());
//                    if (queryByTablePointId != null && queryByTablePointId.size() > 0) {
//                        deliveryFood(queryByTablePointId.get(0).tableRealPoint);
//                        this.mIsFirst = bool.booleanValue();
//                        this.mTaskSend = taskListByTaskState.get(i);
//                        this.mTablePoint = queryByTablePointId.get(0);
//                        break;
//                    }
//                }
//                i++;
//            }
//        } else {
//            CusLogcat.showDLog(TAG, "mTaskSendList is null");
//        }
//        int i2 = this.mMoveFlag;
//        if (i2 != 6 && i2 != 7) {
//            taskListByTaskState.size();
//            if (taskListByTaskState.size() == 3) {
//                CusLogcat.showDLog(TAG, "mSwitchFan = " + this.mSwitchFan);
//                if (this.mSwitchFan) {
//                    this.mCusHandler.postDelayed(new Runnable() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.13
//                        @Override // java.lang.Runnable
//                        public void run() {
//                            try {
//                                RobotMainActivity.access$2600(RobotMainActivity.this).SendMsg(205);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, 1000L);
//                }
//            }
//            if (taskListByTaskState.size() == 1) {
//                try {
//                    this.mSerialSerivce.SendMsg(204);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /**
     * 检测是否有厨房点位、是否有餐桌点位
     * **/
    private void checkPointAndKitchen() {
        List<PointTable> queryAllPointByPointType = this.mPointTableDao.queryAllPointByPointType(0);
        if (queryAllPointByPointType == null || queryAllPointByPointType.size() > 0) {
            List<PointTable> queryAllPointByPointType2 = this.mPointTableDao.queryAllPointByPointType(1);
            if (queryAllPointByPointType2 != null && queryAllPointByPointType2.size() <= 0) {
                CusToast.showToast(Util.getStringByStringXml(R.string.add_kitchen_backstage));
                return;
            }
            return;
        }
        CusToast.showToast(Util.getStringByStringXml(R.string.add_point_backstage));
    }

    private DynamicCorpusHandler mDynamicCorpusHandler;

    private void initHandler() {
        HandlerThread thread = new HandlerThread();
        thread.start();
        try {
            doMainTaskHandler = new DoMainTaskHandler(this,thread.getLooper());
        } catch (Exception e) {

        }
        this.mDynamicCorpusHandler = new DynamicCorpusHandler(this);
    }

    private static class DynamicCorpusHandler extends Handler {
        private final WeakReference<RobotMainActivity> mActivity;

        public DynamicCorpusHandler(RobotMainActivity robotMainActivity) {
            this.mActivity = new WeakReference<>(robotMainActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RobotMainActivity robotMainActivity = this.mActivity.get();
            if (robotMainActivity == null) {
                super.handleMessage(message);
            } else {
                //cusDynamicCorpusHandleMessage(message);
            }
        }
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
    private DynamicHomePageDao mDynamicHomePageDao;
    private DynamicHomePageTable mDynamicHomePageTable;

    private TaskTableDao mTaskSendDao;
    private CruiseVoiceDao mCruiseVoiceDao;

    private void initValue() {
//        Util.showDorbar(false);
        this.mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        this.mUsherPointAdapter = new UsherPointAdapter(this);
        this.mCriusePointAdapter = new CriusePointAdapter(this);
        this.mCriuseRouteAdapter = new CriuseRouteAdapter(this);
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
        this.mTaskSendDao = new TaskTableDao(this);
        this.mPointTableDao = new PointTableDao(this);
        this.mCruiseVoiceDao = new CruiseVoiceDao(this);
//        this.mDefaultReplyDao = new DefaultReplyDao(this);
//        this.mDynamicCorpusDao = new DynamicCorpusDao(this);
//        this.mDynamicTextDao = new DynamicTextDao(this);
        this.mDynamicHomePageDao = new DynamicHomePageDao(this);
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

        @Override
        public void netSuccess(String str) {
            RobotMainActivity robotMainActivity = this.mActivity.get();
            if (robotMainActivity != null) {
                robotMainActivity.cusNetSuccess(str);
            }
        }

        @Override // com.higgs.deliveryrobot.interfaceUtil.NetListener
        public void netFail(Exception exc) {
            String str = RobotMainActivity.TAG;
            CusLogcat.showELog(str, "netFail(Exception e) e = " + exc.toString());
        }
    }

    /**
     * 网络请求数据成功
     * **/
    private void cusNetSuccess(String str) {
        String str2 = TAG;
        CusLogcat.showDLog(str2, "netSuccess(String responseData) responseData = " + str);
        String str3 = TAG;
        CusLogcat.showDLog(str3, "netSuccess(String responseData) mDynamicLibraryNetRequestFlag = " + this.mDynamicLibraryNetRequestFlag);
        switch (this.mDynamicLibraryNetRequestFlag) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                updateDBPoint(str);
                break;
        }
//        try {
//            //上报
//            this.mReportHandler.CommandResult("com.higgs.deliveryrobot.robotmainactivity", this.mMqttCommand, 0, "任务成功", "");
//        } catch (RemoteException e) {
//            String str4 = TAG;
//            CusLogcat.showDLog(str4, "else if (\"13\".equals(type)) catch (RemoteException e) e = " + e.getMessage());
//        }
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
        if (view.getId() == mStartTaskLL.getId()) {
            CusLogcat.showDLog(TAG, "开始送物");
            this.doMainTaskHandler.removeMessages(MSG_GO_MARKET);
            playSound();
            if (this.mIsHardEstop2) {
               // startSpeak("请取消急停按钮后，再次点击您想去的目的地");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = MSG_GO_MARKET;
            this.doMainTaskHandler.sendMessageDelayed(obtain, 1000L);
        } else  if (view.getId() == R.id.tv_back_home) {
            homeDialogExhibition();
            for (int i = 0; i < this.mLayerList.size(); i++) {
                FoodLayer foodLayer = this.mLayerList.get(i);
                foodLayer.status = -1;
                foodLayer.hasValue = 0;
            }
        }
    }

    private CusFullDialog mHomeDialog;
    private int mDynamicCorpusMoveFlag;
    private boolean mIsHardEstop2 = false;
    private StringBuilder mCriuseSB = new StringBuilder();


    private void homeDialogExhibition() {//首页dialog
        CusFullDialog cusFullDialog;
        CusLogcat.showDLog(TAG, "homeDialogExhibition()");
        if (this.mHomeDialog == null) {
            this.mHomeDialog = new CusFullDialog(this);
        }
        if (!isFinishing() && (cusFullDialog = this.mHomeDialog) != null && !cusFullDialog.isShowing()) {
            this.mHomeDialog.show();
        }
        View inflate = getLayoutInflater().inflate(R.layout.dialog_main, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_deliver);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.iv_back_kitchen);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.iv_charging);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(R.id.iv_settting);
        final FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.fl_up_scroll);
        final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_lock_status);
        final FrameLayout frameLayout2 = (FrameLayout) inflate.findViewById(R.id.fl_main_lock_bg);
        final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_lock_status);
        ImageButton imageButton4 = (ImageButton) inflate.findViewById(R.id.iv_hostess);
        ImageButton imageButton5 = (ImageButton) inflate.findViewById(R.id.iv_usher);
        ImageButton imageButton6 = (ImageButton) inflate.findViewById(R.id.iv_criuse);
        List<DynamicHomePageTable> queryAllPoint = this.mDynamicHomePageDao.queryAllPoint();
        if (queryAllPoint != null && queryAllPoint.size() > 0) {
            this.mDynamicHomePageTable = queryAllPoint.get(0);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_deliver_food).error(R.mipmap.home_deliver_food).into(imageView);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(1);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_back_kitchen).error(R.mipmap.home_back_kitchen).into(imageButton);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(2);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_charge).error(R.mipmap.home_charge).into(imageButton2);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(3);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_usher).error(R.mipmap.home_usher).into(imageButton5);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(4);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_hostess).error(R.mipmap.home_hostess).into(imageButton4);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(5);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_cruise).error(R.mipmap.home_cruise).into(imageButton6);
            }
            this.mDynamicHomePageTable = queryAllPoint.get(6);
            if (this.mDynamicHomePageTable != null) {
                Glide.with((Activity) this).load(this.mDynamicHomePageTable.icon).placeholder(R.mipmap.home_setting).error(R.mipmap.home_setting).into(imageButton3);
            }
        }
        //送餐模式，回到送餐页面
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.78
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RobotMainActivity.this.playSound();
                if (!RobotMainActivity.this.isFinishing() && mHomeDialog != null && mHomeDialog.isShowing()) {
                    mHomeDialog.dismiss();
                }
                CusLogcat.showLockELog(RobotMainActivity.TAG, "iv_deliver.setOnClickListener");
                clickDevilerFoodItem(0);
            }
        });
        //巡游模式
        imageButton6.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.77
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RobotMainActivity.this.playSound();
                mDynamicCorpusMoveFlag = -1;
                if (mIsHardEstop2) {
//                    startSpeak("请取消急停按钮后，再次点击您想去的目的地");
                    return;
                }
                shareLoadCruiseRouteData();
                int size = mCruiseRouteModels.size();
                mCriuseSB.setLength(0);
                mTaskSendDao.deleteAllTaskSend();
                if (size > 0) {
                    CusToast.showToast(Util.getStringByStringXml(R.string.start_cruise));
                    doMainTaskHandler.sendEmptyMessageDelayed(MSG_GO_CRIUSE, 500L);
                    return;
                }
                showCruiseStartDialog();
            }
        });
        //设置按钮点击
        imageButton3.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.81
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CusLogcat.showClickButtonELog(RobotMainActivity.TAG, "iv_setting.setOnClickListener");
                RobotMainActivity.this.playSound();
                showSettingsNewDialog();//展示设置弹窗
            }
        });
        this.mHomeDialog.setView(inflate);
        this.mHomeDialog.setContentView(inflate);
        this.mHomeDialog.setCancelable(false);
    }

    private CusFullDialog mCriusePosSetDialog;
    private CriusePointAdapter mCriusePointAdapter;
    private CriuseRouteAdapter mCriuseRouteAdapter;
    private ArrayList<CriusePointModel> mCriusePointModels = new ArrayList<>(10);

    /**
     * 展示巡游对话？巡游是什么模式
     **/
    private void showCruiseStartDialog() {
        CusFullDialog cusFullDialog;
        CusLogcat.showDLog(TAG, "showCruiseStartDialog()");
        if (this.mCriusePosSetDialog == null) {
            this.mCriusePosSetDialog = new CusFullDialog(this);
        }
        if (!isFinishing() && (cusFullDialog = this.mCriusePosSetDialog) != null && !cusFullDialog.isShowing()) {
            this.mCriusePosSetDialog.show();
        }
        View inflate = getLayoutInflater().inflate(R.layout.dialog_criuse_pos_set, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_back);
        final TextView textView = (TextView) inflate.findViewById(R.id.tv_set_route);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.ll_set);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.ll_criuse_set);
        final GridView gridView = (GridView) inflate.findViewById(R.id.gv_criuse_route);
        final GridView gridView2 = (GridView) inflate.findViewById(R.id.gv_criuse_point);
        final Button button = (Button) inflate.findViewById(R.id.bt_set_criuse_route);
        gridView2.setAdapter((ListAdapter) this.mCriusePointAdapter);
        gridView.setAdapter((ListAdapter) this.mCriuseRouteAdapter);
        shareLoadCruiseRouteData();
        this.mCriuseRouteAdapter.updatePoint(this.mCruiseRouteModels);
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(Util.getStringByStringXml(R.string.cruise_route));
        if (this.mCruiseRouteModels.size() > 0) {
            gridView.setVisibility(View.VISIBLE);
            textView.setText(Util.getStringByStringXml(R.string.modify_route));
        } else {
            gridView.setVisibility(View.GONE);
            textView.setText(Util.getStringByStringXml(R.string.set_route));
        }
        linearLayout.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        final int[] iArr = {0};
        final CriusePointModel[] criusePointModelArr = {new CriusePointModel("", "", false)};
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.102
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                CriusePointModel criusePointModel = mCriusePointModels.get(i);
                if (criusePointModel != null) {
                    if (criusePointModel.isSelect) {
                        CriusePointModel criusePointModel2 = criusePointModelArr[0];
                        if (!(criusePointModel2 == null || criusePointModel2.pointAlias == null || criusePointModel2.pointAlias.equals(criusePointModel.pointAlias))) {
                            minusPointSelectNum(criusePointModel);
                        }
                        criusePointModelArr[0] = criusePointModel;
                        criusePointModel.isSelect = false;
                        int[] iArr2 = iArr;
                        int i2 = iArr2[0] - 1;
                        iArr2[0] = i2;
                        iArr2[0] = i2;
                    } else {
                        int[] iArr3 = iArr;
                        int i3 = iArr3[0] + 1;
                        iArr3[0] = i3;
                        iArr3[0] = i3;
                        criusePointModel.isSelect = true;
                    }
                }
                if (checkSelectPoint()) {
                    button.setBackgroundResource(R.mipmap.button_usher_selected);
                } else {
                    button.setBackgroundResource(R.mipmap.button_usher_no_select);
                }
                criusePointModel.selectNum = iArr[0];
                mCriusePointAdapter.updatePoint(mCriusePointModels);
            }
        });
        loadCriuseData();
        this.mCriusePointAdapter.updatePoint(this.mCriusePointModels);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.103
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RobotMainActivity.this.playSound();
                if (!RobotMainActivity.this.isFinishing() && mCriusePosSetDialog != null && mCriusePosSetDialog.isShowing()) {
                    mCriusePosSetDialog.dismiss();
                    mCriusePointModels.clear();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.104
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (checkSelectPoint()) {
                    initCruiseRouteData();
                    mCriuseRouteAdapter.updatePoint(mCruiseRouteModels);
                    button.setVisibility(View.GONE);
                    gridView2.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.VISIBLE);
                    textView.setText(Util.getStringByStringXml(R.string.modify_route));
                    iArr[0] = 0;
                    CusToast.showToast(Util.getStringByStringXml(R.string.set_success));
                    return;
                }
                CusToast.showToast(Util.getStringByStringXml(R.string.select_cruise_route));
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.105
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mCruiseRouteModels.clear();
                loadCriuseData();
                mCriusePointAdapter.updatePoint(mCriusePointModels);
                button.setVisibility(View.VISIBLE);
                gridView2.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
            }
        });
        this.mCriusePosSetDialog.setContentView(inflate);
    }

    private void initCruiseRouteData() {
        this.mCruiseRouteModels.clear();
        SharedPrefsUtil.put("criuse_route", "");
        CusLogcat.showDLog(TAG, "initCruiseRouteData()");
        String str = TAG;
        CusLogcat.showDLog(str, "initCruiseRouteData() SharedPrefsUtil.get(CRIUSE_ROUTE,\"\") = " + SharedPrefsUtil.get("criuse_route", ""));
        int size = this.mCriusePointModels.size();
        for (int i = 0; i < size; i++) {
            CriusePointModel criusePointModel = this.mCriusePointModels.get(i);
            if (criusePointModel.selectNum > 0) {
                this.mCruiseRouteModels.add(criusePointModel);
            }
        }
        Collections.sort(this.mCruiseRouteModels, new CriuseRouteCompare());
        String str2 = TAG;
        CusLogcat.showDLog(str2, "initCruiseRouteData() mCruiseRouteModels = " + this.mCruiseRouteModels.toString());
        JSONArray jSONArray = new JSONArray();
        int size2 = this.mCruiseRouteModels.size();
        for (int i2 = 0; i2 < size2; i2++) {
            CriusePointModel criusePointModel2 = this.mCruiseRouteModels.get(i2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("route_num", criusePointModel2.selectNum);
                jSONObject.put("route_name", criusePointModel2.pointAlias);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String str3 = TAG;
        CusLogcat.showDLog(str3, "initCruiseRouteData() criuseRouteJsonArray = " + jSONArray);
        SharedPrefsUtil.put("criuse_route", jSONArray.toString());
    }

    private void loadCriuseData() {
        this.mCriusePointModels.clear();
        CusLogcat.showDLog(TAG, "loadCriuseData()");
        if (this.mPointTableDao == null) {
            this.mPointTableDao = new PointTableDao(this);
        }
        List<PointTable> queryAllPointByPointType = this.mPointTableDao.queryAllPointByPointType(0);
        String str = TAG;
        CusLogcat.showDLog(str, "loadCriuseData 中所有点位：" + queryAllPointByPointType.toString());
        int size = queryAllPointByPointType.size();
        for (int i = 0; i < size; i++) {
            PointTable pointTable = queryAllPointByPointType.get(i);
            this.mCriusePointModels.add(new CriusePointModel(pointTable.tableAliasName, pointTable.tableRealPoint, false));
        }
    }

    private void minusPointSelectNum(CriusePointModel criusePointModel) {
        CusLogcat.showDLog(TAG, "minusPointSelectNum(CriusePointModel c)");
        int size = this.mCriusePointModels.size();
        for (int i = 0; i < size; i++) {
            CriusePointModel criusePointModel2 = this.mCriusePointModels.get(i);
            if (criusePointModel2 != null && criusePointModel2.selectNum > criusePointModel.selectNum) {
                criusePointModel2.selectNum--;
            }
        }
    }

    private boolean checkSelectPoint() {
        CusLogcat.showDLog(TAG, "checkSelectPoint()");
        String str = TAG;
        CusLogcat.showDLog(str, "mCriusePointModels = " + this.mCriusePointModels.toString());
        int size = this.mCriusePointModels.size();
        for (int i = 0; i < size; i++) {
            if (this.mCriusePointModels.get(i).isSelect) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<CriusePointModel> mCruiseRouteModels = new ArrayList<>(10);

    private void shareLoadCruiseRouteData() {
//        CusLogcat.showDLog(TAG, "shareLoadCruiseRouteData()");
//        this.mCruiseRouteModels.clear();
//        String str = SharedPrefsUtil.get("criuse_route", "");
//        String str2 = TAG;
//        CusLogcat.showDLog(str2, "shareLoadCruiseRouteData() cruiseRoute = " + str);
//        if (!TextUtils.isEmpty(str) && Util.isJsonArray(str)) {
//            try {
//                JSONArray jSONArray = new JSONArray(str);
//                int length = jSONArray.length();
//                for (int i = 0; i < length; i++) {
//                    JSONObject jSONObject = jSONArray.getJSONObject(i);
//                    CriusePointModel criusePointModel = new CriusePointModel();
//                    int i2 = jSONObject.getInt("route_num");
//                    String string = jSONObject.getString("route_name");
//                    criusePointModel.isSelect = true;
//                    criusePointModel.selectNum = i2;
//                    criusePointModel.pointReal = string;
//                    this.mCruiseRouteModels.add(criusePointModel);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        Collections.sort(this.mCruiseRouteModels, new CriuseRouteCompare());
//        String str3 = TAG;
//        CusLogcat.showDLog(str3, "shareLoadCruiseRouteData() mCruiseRouteModels = " + this.mCruiseRouteModels.toString());
    }

    private TextView mElectricityTV;
    private CusFullDialog mSettingsDialog;
    private ImageView mChargingIV;

    /**
     * 展示设置弹窗
     **/
    private void showSettingsNewDialog() {//展示设置窗口
        CusFullDialog cusFullDialog;
        if (this.mSettingsDialog == null) {
            this.mSettingsDialog = new CusFullDialog(this);
        }
        this.mSettingsDialog.setWidthAndHeight(-1, -1);
        if (!isFinishing() && (cusFullDialog = this.mSettingsDialog) != null && !cusFullDialog.isShowing()) {
            this.mSettingsDialog.show();
        }
        View inflate = getLayoutInflater().inflate(R.layout.activity_setting, (ViewGroup) null);
        this.mSettingsDialog.setContentView(inflate);
        this.mSettingsDialog.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(String.format(Util.getStringByStringXml(R.string.set_interface), 3));
        this.mElectricityTV = (TextView) inflate.findViewById(R.id.tv_electricity);
//        this.mBatteryView = (BatteryView) inflate.findViewById(2131230867);
        this.mElectricityTV.setVisibility(View.VISIBLE);
//        this.mBatteryView.setVisibility(0);
        this.mChargingIV = (ImageView) inflate.findViewById(R.id.iv_charging);
        //返回按钮点击；
        ((ImageView) inflate.findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (mSettingsDialog.isShowing()) {
                    mSettingsDialog.dismiss();
                    ;
                }
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.volume_set), R.drawable.volume_ctrl));//音量
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.function_set), R.drawable.function));//功能设置
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.authority_set), R.drawable.authority_manage));//权限设置
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.devices_control), R.drawable.device_on_off));//设备开关机
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.net_set), R.drawable.net_set));//网络设置
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.point_save), R.drawable.point_record));//点位录入
        arrayList.add(new SwitchMode(Util.getStringByStringXml(R.string.about_us), R.drawable.about_us));//关于我们
        final SettingsGridAdapter settingsGridAdapter = new SettingsGridAdapter(this, arrayList);
        ((GridView) inflate.findViewById(R.id.gv_layout)).setAdapter((ListAdapter) settingsGridAdapter);
        settingsGridAdapter.setOnItemTouchListener(new OnItemTouchListener() { // from class: com.higgs.deliveryrobot.ui.RobotMainActivity.15
            @Override // com.higgs.deliveryrobot.interfaceUtil.OnItemTouchListener
            public void onTouch(int i) {
                doSettingsItemClick(settingsGridAdapter, i);
            }
        });
    }

    /**
     * 设置页面的点击事件
     * */
    private void doSettingsItemClick(SettingsGridAdapter settingsGridAdapter, int i) {
        keyBoardCancle();
        playSound();
        switch (i) {
            case 0:
//                showVolumeDialog();
                return;
            case 1:
//                showFunctionDialog();
                return;
            case 2:
//                startActivityForResult(new Intent(this, ModifyPassActivity.class), 200);
                return;
            case 3:
//                showControlHardwareDialog();
                return;
            case 4:
//                startActivity(new Intent(this, WifiActivity.class));
                return;
            case 5:
                keyBoardCancle();
                startActivity(new Intent(this, TablePointActivity.class));
                return;
            case 6:
//                showVersionUpdateDialog();
                return;
            default:
                return;
        }
    }

    public void keyBoardCancle() {
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public List<FoodLayer> getLayerList() {
        return this.mLayerList;
    }

    /**
     * 请求点位,应该都走同步刷新机制吧，每次上游有数据更新以后，每个机器手动点击一下更新，直接更新，暂时不每次可见重启启动
     **/
    private void requestMapPoint() {//请求 固定地点
        mDynamicLibraryNetRequestFlag=5;
        CusLogcat.showDLog(TAG, "requestMapPoint()");
        get(HOST_XIAO_PANG + MAP_POINTS + "no=" + this.mRobotId + "&map=" + mCurrentMapName, this.mPointToken);
    }
}
