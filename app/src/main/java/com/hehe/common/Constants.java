package com.hehe.common;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class Constants {
    public static final String ANSWERS_DEFAULT = "/openapi/robots/configures/answers/cruise";
    public static final String ANSWERS_PROCESS = "/openapi/robots/configs/answers/process";
    public static final int API_CURRENTMAP_NAME = 10005;
    public static final int API_MOVE = 10001;
    public static final int API_MOVE_CANCEL = 10004;
    public static final int API_ROBOT_INFO = 10006;
    public static final int API_STATUS = 10002;
    public static final int API_STATUS_FREQUENCY = 10003;
    public static final int CHECK_ITEM_SELECTED = 205;
    public static final int CLOSE_INTERACTION_AM = 0;
    public static final int CLOSE_INTERACTION_END_HOUR = 6;
    public static final int CLOSE_INTERACTION_START_HOUR = 0;
    public static final String CONFIG_SHORTCUTS = "/openapi/robots/columbus/configs/shortcuts";
    public static final String CRUISES_POINTS = "/openapi/robots/columbus/configs/cruises/points";
    public static final int DB_VERSION = 114;
    public static final int DISMISS_ARRAIVE_DIALOG = 206;
    public static final int DISMISS_CANCEL_DIALOG = 311;
    public static final int DISMISS_CHARGE_FAIL_DIALOG = 314;
    public static final int DISMISS_CHARGING_DIALOG = 310;
    public static final int DISMISS_COMPLETE_DIALOG = 208;
    public static final int DISMISS_HOME_DIALOG = 312;
    public static final int DISMISS_HOSTESS_ARRIVED_DIALOG = 315;
    public static final int DISMISS_SETTING_DIALOG = 313;
    public static final int DISMISS_TASKING_DIALOG = 207;
    public static final String ENTIRE = "OHiggsRobot111";
    public static final String FAN_SWITCH_FLAG = "fan_switch_flag";
    public static final String FORM_FLAG = "from_flag";
    public static final String HIDE_BROADCAST = "com.higgs.hide.NavigationBar";
    public static final String HOST_LAO_ZHA = "https://htkapi.higgsdynamics.com";
    public static final String HOST_XIAO_PANG = "https://api.mk.higgsdynamics.com";
    public static final String ISLOCK = "isLock";
    public static final int IS_FIRST_TASKSENDZ = 101;
    public static final String IS_SETTING_LOCK = "is_setting_lock";
    public static final String LoginURL = "http://39.105.26.223/api/login";
    public static final String MAP_POINTS = "/openapi/robots/maps/points";
    public static final int MESSAGE_ACTIVE = -1;
    public static final long MESSAGE_DELAYED = 500;
    public static final int MESSAGE_EVENT = 0;
    public static final int MSG_CHARGING = 211;
    public static final int MSG_CHARGING_STATE = 210;
    public static final int MSG_CONNECT_SERIAL_SERVICE = 300;
    public static final int MSG_GO_CHARGING = 212;
    public static final int MSG_GO_CRIUSE = 216;
    public static final int MSG_GO_HOSTESS = 215;
    public static final int MSG_GO_KITCHEN = 213;
    public static final int MSG_GO_MARKET = 214;
    public static final int MSG_WHAT_DO_START_TASK = 202;
    public static final int MSG_WHAT_SETTINGS_CONNECT_SOCKET = 209;
    public static final int MSG_WHAT_SPEAK = 201;
    public static final int MSG_WHAT_UPDATE = 200;
    public static final int NOTIFY = 10000;
    public static final int NOT_REPEAT_SPEAK = 100;
    public static final String PASSWORD_BASE = "password_base";
    public static final String PASSWORD_USER = "password_user";
    public static final String REPORT_HANDLE_KEY = "com.higgs.deliveryrobot.robotmainactivity";
    public static final int ROBOT_CHARGE_STATUS_AUTO_FAILED = 16;
    public static final int ROBOT_CHARGE_STATUS_AUTO_START = 14;
    public static final int ROBOT_CHARGE_STATUS_AUTO_SUCCESS = 15;
    public static final int ROBOT_CHARGE_STATUS_ERROR = 17;
    public static final int ROBOT_CHARGE_STATUS_IDEL = 10;
    public static final int ROBOT_CHARGE_STATUS_LEAVE_FAILED = 13;
    public static final int ROBOT_CHARGE_STATUS_LEAVE_START = 11;
    public static final int ROBOT_CHARGE_STATUS_LEAVE_SUCCESS = 12;
    public static final int ROBOT_ESTOP_STATUS_OFF = 21;
    public static final int ROBOT_ESTOP_STATUS_ON = 20;
    public static final int ROBOT_EYE_CHARGING = 6;
    public static final int ROBOT_EYE_ESTOP = 7;
    public static final int ROBOT_EYE_KITCHEN = 5;
    public static final String ROBOT_EYE_LOCK_KEY = "com.higgs.setting.eyelock";
    public static final int ROBOT_EYE_MOVEFINISH = 4;
    public static final int ROBOT_EYE_MOVING = 3;
    public static final int ROBOT_EYE_NORMAL = 1;
    public static final int ROBOT_EYE_STARTMOVE = 2;
    public static final int ROBOT_MOVE_STATUS_CANCEL = 4;
    public static final int ROBOT_MOVE_STATUS_FAILED = 3;
    public static final int ROBOT_MOVE_STATUS_FINISH = 2;
    public static final int ROBOT_MOVE_STATUS_IDEL = 0;
    public static final int ROBOT_MOVE_STATUS_RETRY = 5;
    public static final int ROBOT_MOVE_STATUS_START = 1;
    public static final int ROBOT_MOVE_STATUS_TRAPPED = 6;
    public static final int ROBOT_STATE_AWAIT_ORDERS = 1;
    public static final int ROBOT_STATE_CHARGING = 2;
    public static final int ROBOT_STATE_REBOOT = 3;
    public static final int ROBOT_STATE_TASKING = 4;
    public static final boolean Release = true;
    public static final String SHOW_BROADCAST = "com.higgs.show.NavigationBar";
    public static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    public static final String SPEAK_STRING = "speak_string";
    public static final String SPEEK_KEY = "com.higgs.deliveryrobot.local_voice_library";
    public static final String SPEEK_KEY_VOICE_INTERACTION = "com.higgs.deliveryrobot.local_voice_interaction";
    public static final int TASK_STATE_0 = 0;
    public static final int TASK_STATE_1 = 1;
    public static final int TASK_STATE_2 = 2;
    public static final int TASK_STATE_3 = 3;
    public static final int TASK_STATE_4 = 4;
    public static final int TASK_STATE_5 = 5;
    public static final int TASK_STATE_6 = 6;
    public static final String TEXTS_DEFAULT = "/openapi/robots/configs/texts/default";
    public static final String TOKEN_PATH = "/auth/token";
    public static final String USHER_START_POSITION = "usher_start_position";
    public static final String alertorUpdateURL = "http://39.105.26.223/api/alertorUpdate";
    public static final boolean isShowToast = true;
    public static final String mssStartURL = "http://39.105.26.223/api/mssStart";
    public static final String qstUpdateURL = "http://39.105.26.223/api/qstUpdate";
    public static final String statUpdateURL = "http://39.105.26.223/api/statUpdate";
    public static final Pattern MATCH_CHINESE_CHARACTERS = Pattern.compile("[一-龥]");
    public static final Pattern MATCH_AZ_HEAD_AZ09_END = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]+$");
    public static final Pattern MATCH_ONLY_AZ = Pattern.compile("^[A-Za-z]+$");
    public static final Pattern MATCH_NUM = Pattern.compile("^[0-9]*$");
    public static final Pattern MATCH_NUM_LETTER = Pattern.compile("^[0-9A-Za-z]+$");

    /* loaded from: classes.dex */
    public interface AlarmSerial {
        public static final String ALARMSERIAL_PORT = "ttysWK3";
        public static final int BAUDRATE = 2400;
        public static final int SERIAL_SEND_COUNT = 100;
    }

    /* loaded from: classes.dex */
    public interface BroadCast {
        public static final String CHARGE_BROADCAST = "com.higgs.deliveryrobot.chargebroadcast";
        public static final String CHARGE_FAIL_BROADCAST_ACTION = "com.higgs.deliveryrobot.chargefailbroadcast";
        public static final String CLOSE_HOMEDIALOG_BROADCAST = "close.home.dialog";
        public static final String MUSIC_PLAY_START = "com.higgs.speechservice.play.start";
        public static final String MUSIC_PLAY_STOP = "com.higgs.speechservice.play.stop";
        public static final String OPEN_HOMEDIALOG_BROADCAST = "open.home.dialog";
        public static final String REPORT_SERVICE_ACTION = "com.higgs.droidsys.report.ReportService";
        public static final String REPORT_SERVICE_PACKAGE = "com.higgs.droidsys.report";
        public static final String SERIAL_SERVICE_BROADCAST = "com.higgs.higgsserialservice.SerialService";
        public static final String SPEECH_SERVICE_ACTION = "com.higgs.speechservice.SpeechService";
        public static final String SPEECH_SERVICE_PACKAGE = "com.higgs.speechservice";
        public static final String ZHAO_HUAN_BROADCAST = "com.higgs.mqttrecv.remotecommand";
    }

    /* loaded from: classes.dex */
    public interface BroadCastAction {
        public static final String CLOSE_VOICE_INTERACTION = "com.higgs.atartservice.close.voice_interaction";
        public static final String OPEN_VOICE_INTERACTION = "com.higgs.atartservice.open.voice_interaction";
    }

    /* loaded from: classes.dex */
    public interface ControlHardware {
        public static final byte[] eyesOpenNormal = {-85, 0, 1, 0, 1, -85};
        public static final byte[] eyesCloseNormal = {-85, 0, 1, 0, 2, -88};
        public static final byte[] eyesOpenXiaoXinXin = {-85, 0, 1, 0, 3, -87};
        public static final byte[] eyesOpenDaXinXin = {-85, 0, 1, 0, 4, -82};
        public static final byte[] eyesOpenZuoKan = {-85, 0, 1, 0, 5, -81};
        public static final byte[] eyesOpenYouKan = {-85, 0, 1, 0, 6, -84};
        public static final byte[] eyesOpenShy = {-85, 0, 1, 0, 7, -83};
        public static final byte[] eyesOpenAnger = {-85, 0, 1, 0, 8, -94};
        public static final byte[] lightOpenOne = {-85, 0, 2, 0, 1, -88};
        public static final byte[] lightCloseOne = {-85, 0, 2, 0, 2, -85};
        public static final byte[] lightOpenTwo = {-85, 0, 2, 0, 3, -86};
        public static final byte[] lightCloseTwo = {-85, 0, 2, 0, 4, -83};
        public static final byte[] ultravioletOpen = {-85, 0, 3, 0, 1, -87};
        public static final byte[] ultravioletClose = {-85, 0, 3, 0, 2, -86};
        public static final byte[] infraredOneYes = {-85, 0, 4, 0, 1, -82};
        public static final byte[] infraredOneNo = {-85, 0, 4, 0, 2, -83};
        public static final byte[] infraredTwoYes = {-85, 0, 4, 0, 3, -84};
        public static final byte[] infraredTwoNo = {-85, 0, 4, 0, 4, -85};
        public static final byte[] renovateOpen = {-85, 0, 5, 0, 1, -81};
        public static final byte[] renovateClose = {-85, 0, 5, 0, 2, -84};
        public static final byte[] fanOpenOne = {-85, 0, 6, 0, 1, -84};
        public static final byte[] fanCloseOne = {-85, 0, 6, 0, 2, -81};
        public static final byte[] fanOpenTwo = {-85, 0, 6, 0, 3, -82};
        public static final byte[] fanCloseTwo = {-85, 0, 6, 0, 4, -87};
    }

    /* loaded from: classes.dex */
    public interface CruisePoint {
        public static final String INTERVAL = "interval";
        public static final String NUM = "num";
        public static final String POINT = "point";
        public static final String SPLIT = ";";
        public static final String TEXTS = "texts";
        public static final String WAIT_TIME = "waitTime";
    }

    /* loaded from: classes.dex */
    public interface DataBase {
        public static final String DATA_BASE_DIR = "/sdcard/database";
    }

    /* loaded from: classes.dex */
    public interface DynamicCorpus {
        public static final int CIRCLE_NUM = 0;
        public static final int CRUISE = 3;
        public static final int FROM_DELIVERY_TO_KITCHEN = 2;
        public static final int FROM_INITIATIVE_TO_KITCHEN = 1;
        public static final int FUNCTION_ARRIVE_CRUISE_WHAT = 908;
        public static final int FUNCTION_ARRIVE_GUEST_WAIT_WHAT = 906;
        public static final int FUNCTION_ARRIVE_GUEST_WHAT = 905;
        public static final int FUNCTION_ARRIVE_USHER_WHAT = 910;
        public static final int FUNCTION_GOING_CRUISE_WHAT = 907;
        public static final int FUNCTION_GOING_GUEST_WHAT = 904;
        public static final int FUNCTION_GOING_USHER_WHAT = 909;
        public static final int GUEST = 2;
        public static final String INTERVAL = "interval";
        public static final String LAYER = "${layer}";
        public static final int MOVEMENT = 4;
        public static final int MOVEMENT_ARRIVE_CHARGE_POINT_WHAT = 903;
        public static final int MOVEMENT_GOING_CHARGE_POINT_WHAT = 902;
        public static final int MOVEMENT_GOING_WAIT_POINT_WHAT = 900;
        public static final int MOVEMENT_WAIT_POINT_WAIT_WHAT = 901;
        public static final String NUM = "num";
        public static final int ONE_NUM = 1;
        public static final int REQUEST_CRUISE_POINT = 4;
        public static final int REQUEST_MAP_POINT = 5;
        public static final int REQUEST_SHOUYE_PIC = 3;
        public static final int REQUEST_TEXTS_DEFAULT = 2;
        public static final int REQUEST_VOICE_LIBRARY = 1;
        public static final String SPLIT = ";";
        public static final String TARGET = "${target}";
        public static final String TEXT = "text";
        public static final int TRANSPORT = 1;
        public static final int TRANSPORT_FINISH_WHAT = 914;
        public static final int TRANSPORT_GOING_WHAT = 911;
        public static final int TRANSPORT_TARGET_ARRIVE_WAIT_WHAT = 913;
        public static final int TRANSPORT_TARGET_WAIT_WHAT = 912;
        public static final int TRANSPORT_WAIT_POINT_WAIT_WHAT = 915;
        public static final int USHER = 5;
    }

    /* loaded from: classes.dex */
    public interface DynamicText {
        public static final String CODE = "code";
        public static final int GO_WAIT_POINT = 2;
        public static final int HOSTESS = 3;
        public static final String LANG = "lang";
        public static final String TEXT = "text";
        public static final int TRANSPORT = 1;
        public static final int USHER = 4;
    }

    /* loaded from: classes.dex */
    public interface IntentData {
        public static final String ZHAO_HUAN_CONTENT = "recvmessage";
    }

    /* loaded from: classes.dex */
    public interface Net {
        public static final int FAIL_CODE = -1;
        public static final int GET_FAIL = 1001;
        public static final int GET_POINT_TOKEN_FAIL = 1005;
        public static final int GET_POINT_TOKEN_SUCCESS = 1004;
        public static final int GET_SUCCESS = 1000;
        public static final int POST_FAIL = 1003;
        public static final int POST_SUCCESS = 1002;
        public static final int SUCCESS_CODE = 0;
        public static final int TOKEN_FAIL_CODE = -2;
    }

    /* loaded from: classes.dex */
    public interface NetState {
        public static final String NET_FAIL = "0";
        public static final String NET_OK = "1";
        public static final String NET_STATE = "higgs.network.state";
    }

    /* loaded from: classes.dex */
    public interface OnActivityResult {
        public static final String MODIFY_TO_ROBOT_MAIN = "MODIFY_TO_ROBOT_MAIN";
        public static final int MODIFY_TO_ROBOT_MAIN_FAIL_CODE = 2;
        public static final int MODIFY_TO_ROBOT_MAIN_SUCCESS_CODE = 1;
    }

    /* loaded from: classes.dex */
    public interface PointInterface {
        public static final String POINT_ALIAS_NAME = "point_alias_name";
        public static final String TABLE_REAL_POINT = "table_real_point";
    }

    /* loaded from: classes.dex */
    public interface RobotCode {
        public static final int BACK_USHER_START_POSITION = 11;
        public static final int CANCEL_SONGCAN_TASK_FLAG = 6;
        public static final int CHARGING_FAIL_TASK_FLAG = 7;
        public static final int FAIL_LOW_CHARG_FLAG = 8;
        public static final int FAIL_TASK_FLAG = 9;
        public static final int FINISH_CANCEL_SINGLE_TASK = 4;
        public static final int FINISH_CHARGING_MAIN_TASK = 2;
        public static final int FINISH_GO_EXCUTE_TASK = 3;
        public static final int FINISH_GO_KITCHEN = 1;
        public static final int FINISH_ONE_CRIUSE = 12;
        public static final int GO_BACK_START_USHER_CODE = 7;
        public static final int HOSTESS_TASK_FLAG = 5;
        public static final int MOVE_CALL_CODE = 5;
        public static final int MOVE_CHARGE_CODE = 2;
        public static final int MOVE_CONTROL_CODE = 4;
        public static final int MOVE_CRIUSE_CODE = 8;
        public static final int MOVE_HOSTESS_CODE = 3;
        public static final int MOVE_KITCHEN_CODE = 1;
        public static final int MOVE_SONGCAN_CODE = 0;
        public static final int MOVE_USHER_CODE = 6;
        public static final int USHER_TASK_FLAG = 10;
    }

    /* loaded from: classes.dex */
    public interface RollLid {
        public static final int CLOSE_ALL_LID = 5;
        public static final int CLOSE_LOWER_LID = 4;
        public static final int CLOSE_LOWER_LID_DELAY_TIME = 4000;
        public static final int CLOSE_UPPER_LID = 2;
        public static final int CLOSE_UPPER_LID_DELAY_TIME = 4000;
        public static final int OPEN_ALL_LID = 6;
        public static final int OPEN_LOWER_LID = 3;
        public static final int OPEN_LOWER_LID_DELAY_TIME = 100;
        public static final int OPEN_UPPER_LID = 1;
        public static final int OPEN_UPPER_LID_DELAY_TIME = 100;
        public static final String SWITCH_FLAG = "roll_lid_flag";
    }

    /* loaded from: classes.dex */
    public interface SharedPreference {
        public static final String CRIUSE_ROUTE = "criuse_route";
        public static final String ROUTE_NAME = "route_name";
        public static final String ROUTE_NUM = "route_num";
    }

    /* loaded from: classes.dex */
    public interface SpeakRandom {
        public static final String avoidObstacle = "avoidobstacle";
        public static final String chargeToKitchen = "chargeToKitchen";
        public static final String delivering = "delivering";
        public static final String goCharging = "goCharging";
        public static final String kitchenWait = "kitchen_wait";
        public static final String takeFoodAfterGoKitchen = "take_food_after_go_kitchen";
        public static final String takeFoodCountDown = "take_food_count_down";
        public static final String waitInKitchen = "waitInKitchen";
    }

    /* loaded from: classes.dex */
    public interface Wifi {
        public static final int FREQUENCY_5G = 5000;
        public static final int HANDLER_CONNECTED = 0;
        public static final int HANDLER_NETWORKID = 2;
        public static final int HANDLER_PASSWORD_ERROR = 1;
        public static final int IP_ADDR_NUM = 4;
        public static final int PASSWORK_MIN_NUM = 8;
        public static final int SIGNAL_NORMAL = 70;
        public static final int SIGNAL_STRONG = 50;
        public static final int SIGNAL_WEAK = 80;
        public static final String SWITCH_STATUS = "wifi_switch_status";
        public static final int WIFICIPHER_NOPASS = 1;
        public static final int WIFICIPHER_WEP = 2;
        public static final int WIFICIPHER_WPA = 3;
    }

    /* loaded from: classes.dex */
    public interface XiaoPang {
        public static final String UPDATE_COLUMBUS_SHORTCUTS = "update_columbus_shortcuts";
        public static final String UPDATE_MAP_POINT = "update_map_point";
        public static final String UPDATE_PROCESS_CRUISE_POINT = "update_process_cruise_point";
        public static final String UPDATE_PROCESS_TEXT = "update_process_text";
        public static final String UPDATE_PROCESS_VOICE = "update_process_voice";
        public static final String UPDATE_UNLINE_DEPOT = "update_unline_depot";
    }

    public static final int TYPE_KITCHEN=1;
    public static final int TYPE_POINT=0;
}
