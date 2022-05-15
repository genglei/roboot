package com.hehe.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.hehe.HiggsApplication;
import com.hehe.ISocketInterface;
import com.hehe.bean.SocketMessageBean;
import com.hehe.soketjar.common_bean.RobotStatus.CurrentPoseBean;
import com.hehe.soketjar.common_bean.RobotStatus.StatusResultBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class Util {
    public static final String LOCAL = "SoundMeter";
    public static final String LOCAL_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;
    public static final String REC_PATH = LOCAL_PATH + "SoundMeter" + File.separator;
    private static final String TAG = "Util";

    static {
        File file = new File(LOCAL_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(REC_PATH);
        if (!file2.exists()) {
            file2.mkdirs();
        }
    }

    public static boolean isExitSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    private static boolean hasFile(String str) {
        File createFile = createFile(str);
        return createFile != null && createFile.exists();
    }

    public static File createFile(String str) {
        File file = new File(REC_PATH + str);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean isServiceRunning(Context context, String str) {
        if ("".equals(str) || str == null) {
            return false;
        }
        ArrayList arrayList = (ArrayList) ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(30);
        for (int i = 0; i < arrayList.size(); i++) {
            if (((ActivityManager.RunningServiceInfo) arrayList.get(i)).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isContainsChinese(String str) {
        return Pattern.compile("[一-龥]").matcher(str).find();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }

    public static String JSONTokener(String str) {
        return (str == null || !str.startsWith("\ufeff")) ? str : str.substring(1);
    }

    public static String updateTableNameStr(String str) {
        if (TextUtils.isEmpty(str) || str.contains(getStringByStringXml(2131558484))) {
            return str;
        }
        return str + getStringByStringXml(2131558484);
    }

    public static String parseMessage(String str) {
        try {
            return new JSONObject(JSONTokener(str)).optString("code");
        } catch (Exception e) {
            e.printStackTrace();
            CusLogcat.showLog("解析异常==" + e.getMessage().toString());
            return "";
        }
    }

    public static void showDorbar(boolean z) {
        if (z) {
            HiggsApplication.getInstance().sendBroadcast(new Intent("com.higgs.show.NavigationBar"));
            return;
        }
        HiggsApplication.getInstance().sendBroadcast(new Intent("com.higgs.hide.NavigationBar"));
    }

    public static JSONObject initJsonObject(SocketMessageBean socketMessageBean) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (socketMessageBean.type != -1) {
                jSONObject.put("message", socketMessageBean.message);
            } else {
                jSONObject.put("message", "");
            }
            jSONObject.put("type", socketMessageBean.type);
            jSONObject.put("message", socketMessageBean.message);
            jSONObject.put("userId", socketMessageBean.userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static SocketMessageBean parseJson(String str) {
        SocketMessageBean socketMessageBean = new SocketMessageBean();
        try {
            JSONObject jSONObject = new JSONObject(str);
            socketMessageBean.type = jSONObject.optInt("type");
            if (socketMessageBean.type == 0) {
                socketMessageBean.message = jSONObject.optString("message");
            }
            socketMessageBean.userId = jSONObject.optString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return socketMessageBean;
    }

    public static void NavigationBarStatusBar(Activity activity, boolean z) {
        if (z && Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public static String checkCode(String str) {
        int length = str.length() / 2;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            strArr[i] = str.substring(i2, i2 + 2);
        }
        String str2 = "00";
        for (String str3 : strArr) {
            str2 = xor(str2, str3);
        }
        return str2;
    }

    private static String xor(String str, String str2) {
        String binaryString = Integer.toBinaryString(Integer.valueOf(str, 16).intValue());
        String binaryString2 = Integer.toBinaryString(Integer.valueOf(str2, 16).intValue());
        String str3 = "";
        if (binaryString.length() != 8) {
            for (int length = binaryString.length(); length < 8; length++) {
                binaryString = "0" + binaryString;
            }
        }
        if (binaryString2.length() != 8) {
            for (int length2 = binaryString2.length(); length2 < 8; length2++) {
                binaryString2 = "0" + binaryString2;
            }
        }
        for (int i = 0; i < binaryString.length(); i++) {
            str3 = binaryString2.charAt(i) == binaryString.charAt(i) ? str3 + "0" : str3 + "1";
        }
        Log.e("code", str3);
        return Integer.toHexString(Integer.parseInt(str3, 2));
    }

    public static String randomStringReportSign() {
        return MD5Utils.md5Encryption32((System.currentTimeMillis() + "") + generateString(new Random(), "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890", 15));
    }

    private static String generateString(Random random, String str, int i) {
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = str.charAt(random.nextInt(str.length()));
        }
        return new String(cArr);
    }

    public static boolean isJsonObject(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Object obj = null;
        try {
            obj = new JSONTokener(str).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj instanceof JSONObject;
    }

    public static boolean isJsonArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Object obj = null;
        try {
            obj = new JSONTokener(str).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj instanceof JSONArray;
    }

    public static void stringToJsonObject(JSONObject jSONObject, StatusResultBean statusResultBean) {
        jSONObject.remove("x");
        jSONObject.remove("y");
        jSONObject.remove("z");
        jSONObject.remove("angle");
        try {
            if (statusResultBean != null) {
                CurrentPoseBean current_pose = statusResultBean.getCurrent_pose();
                if (current_pose != null) {
                    jSONObject.put("x", current_pose.getX());
                    jSONObject.put("y", current_pose.getY());
                    jSONObject.put("z", 0);
                    jSONObject.put("angle", current_pose.getTheta());
                } else {
                    CusLogcat.showDLog(TAG, "currentPoseBean = null");
                }
            } else {
                CusLogcat.showDLog(TAG, "statusMessage = null");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String reportString(String str, String str2, String str3, int i, int i2, int i3, int i4, double d, String str4, String str5, JSONObject jSONObject, JSONObject jSONObject2, long j, long j2, String str6, long j3) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("path", "tasks_detail");
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("taskId", str);
            jSONObject4.put("no", str2);
            jSONObject4.put("map", str3);
            jSONObject4.put("order", i);
            jSONObject4.put("type", i2);
            jSONObject4.put("status", i3);
            jSONObject4.put("retry", i4);
            jSONObject4.put("mileage", d);
            jSONObject4.put("startPoint", str4);
            jSONObject4.put("endPoint", str5);
            jSONObject4.put("startPosition", jSONObject);
            jSONObject4.put("endPosition", jSONObject2);
            jSONObject4.put("startTime", j);
            jSONObject4.put("endTime", j2);
            jSONObject4.put("note", str6);
            jSONObject4.put("ts", j3);
            jSONObject3.put("data", jSONObject4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject3.toString();
    }

    public static void getLocalVersion(Context context) {
        String str;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            str = null;
        }
        String str2 = TAG;
        CusLogcat.showELog(str2, "name = " + str);
        saveVersionToSD(str);
    }

    public static void saveVersionToSD(String str) {
        PrintStream printStream = null;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String str2 = externalStorageDirectory.getPath() + "/version";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            printStream = new PrintStream(new FileOutputStream(new File(str2, System.currentTimeMillis() + ".txt")));
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        if (printStream!=null){
            printStream.println(str);
            printStream.close();
        }
    }

    /**
     * 移动到某个点位
     * **/
    public static void moveTarget(ISocketInterface iSocketInterface, String str) {
        CusLogcat.showDLog(TAG, "moveTarget(ISocketInterface socketInterface, String point)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
        } else if (!TextUtils.isEmpty(str)) {
            try {
                iSocketInterface.moveTarget(str);
            } catch (RemoteException e) {
                String str2 = TAG;
                CusLogcat.showDLog(str2, "moveTarget point e = " + e.getMessage());
            }
        } else {
            CusToast.showToast(getStringByStringXml(2131558481));
        }
    }

    public static void getRobotStatus(ISocketInterface iSocketInterface) {
        CusLogcat.showDLog(TAG, "getRobotStatus(ISocketInterface socketInterface)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
            return;
        }
        try {
            iSocketInterface.getRobotStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void moveCriuse(ISocketInterface iSocketInterface, String str) {
        CusLogcat.showDLog(TAG, "moveCriuse(ISocketInterface socketInterface, String criusePoint)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
        } else if (!TextUtils.isEmpty(str)) {
            try {
                iSocketInterface.moveCruise(str, 0.5f, -1);
            } catch (RemoteException e) {
                String str2 = TAG;
                CusLogcat.showDLog(str2, "moveTarget point e = " + e.getMessage());
            }
        } else {
            CusToast.showToast(getStringByStringXml(2131558481));
        }
    }

    public static void getCurrentMap(ISocketInterface iSocketInterface) {
        CusLogcat.showDLog(TAG, "getCurrentMap(ISocketInterface socketInterface)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
            return;
        }
        try {
            iSocketInterface.getCurrentMap();
        } catch (RemoteException e) {
            String str = TAG;
            CusLogcat.showDLog(str, "socketInterface.getCurrentMap() e = " + e.getMessage());
        }
    }

    public static void moveCancel(ISocketInterface iSocketInterface) {
        CusLogcat.showDLog(TAG, "moveCancel(ISocketInterface socketInterface)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
            return;
        }
        try {
            iSocketInterface.moveCancel();
        } catch (RemoteException e) {
            String str = TAG;
            CusLogcat.showDLog(str, "socketInterface.getCurrentMap() e = " + e.getMessage());
        }
    }

    public static void eStop(ISocketInterface iSocketInterface, boolean z) {
        CusLogcat.showDLog(TAG, "eStop(ISocketInterface socketInterface, boolean stop)");
        if (iSocketInterface == null) {
            CusToast.showToast("socketInterface == null");
            return;
        }
        try {
            iSocketInterface.eStop(z);
        } catch (RemoteException e) {
            String str = TAG;
            CusLogcat.showDLog(str, "eStop(ISocketInterface socketInterface, boolean stop) e = " + e.getMessage());
        }
    }

    public static byte stringToByte(String str) {
        if (TextUtils.isEmpty(str)) {
            return (byte) -1;
        }
        try {
            return (byte) Integer.parseInt(str.substring(2), 16);
        } catch (Exception e) {
            e.printStackTrace();
            return (byte) -1;
        }
    }

    public static String getPropValue(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int dip2px(float f) {
        return (int) ((f * HiggsApplication.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f) {
        return (int) ((f / HiggsApplication.getInstance().getResources().getDisplayMetrics().density) + 0.5f);
    }

//    public static void report(ReportHander reportHander, String str) {
//        CusLogcat.showDLog(TAG, "report(ReportHander reportHander, String param) param = " + str);
//        if (reportHander == null || str == null) {
//            String str2 = TAG;
//            StringBuilder sb = new StringBuilder();
//            sb.append("(reportHander == null) = ");
//            boolean z = true;
//            sb.append(reportHander == null);
//            sb.append("(param == null) = ");
//            if (str != null) {
//                z = false;
//            }
//            sb.append(z);
//            CusLogcat.showDLog(str2, sb.toString());
//            return;
//        }
//        try {
//            reportHander.report("com.higgs.deliveryrobot.robotmainactivity", str);
//        } catch (RemoteException e) {
//            CusLogcat.showDLog(TAG, "e.getMessage() = " + e.getMessage());
//        }
//    }

    public static String jsonArrayToString(JSONArray jSONArray) {
        if (jSONArray == null) {
            return "";
        }
        int length = jSONArray.length();
        String str = "";
        for (int i = 0; i < length; i++) {
            try {
                str = str + jSONArray.get(i) + ";";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return length > 0 ? str.substring(0, str.length() - 1) : str;
    }

    public static String[] stringToArray(String str) {
        if (str == null) {
            return null;
        }
        return str.split(";");
    }

    public static String stringToRandomString(String str) {
        String[] stringToArray = stringToArray(str);
        String str2 = "";
        if (stringToArray != null) {
            str2 = stringToArray[new Random().nextInt(stringToArray.length)];
        }
        String str3 = TAG;
        CusLogcat.showDLog(str3, "stringToRandomString(String text) text = " + str);
        return str2;
    }

    public static String nameReplaceFlag(String str, String str2) {
        return (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) ? "" : str.replace("${target}", str2);
    }

    public static String nameLayerReplaceFlag(String str, String str2, String str3) {
        return (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) ? "" : str.replace("${target}", str2).replace("${layer}", str3);
    }

//    public static void serialSendMsg(ISerialService iSerialService, int i) {
//        String str = TAG;
//        CusLogcat.showDLog(str, "serialSendMsg(ISerialService serialService, int flag) flag = " + i);
//        if (iSerialService == null) {
//            CusLogcat.showDLog(TAG, "serialSendMsg(ISerialService serialService, int flag) serialService == null");
//            return;
//        }
//        try {
//            iSerialService.SendMsg(i);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    public static String jsonObjectGetStringValue(JSONObject jSONObject, String str) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return "";
        }
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean jsonObjectGetBooleanValue(JSONObject jSONObject, String str) {
        if (jSONObject == null || jSONObject.isNull(str)) {
            return false;
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getStringByStringXml(int i) {
        String string = HiggsApplication.getInstance().getResources().getString(i);
        return !TextUtils.isEmpty(string) ? string : "";
    }
}
