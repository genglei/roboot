package com.hehe.ui;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hehe.R;
import com.hehe.facecompare.FaceHelp;
import com.hehe.interfaceUtil.NetListener;
import com.hehe.utils.CusLogcat;
import com.hehe.utils.CusToast;
import com.hehe.utils.JsonUtils;
import com.hehe.utils.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseActivity extends Activity {
    private static final String TAG = "BaseActivity";
    protected static volatile String mCurrentMapName = "";
    protected NetHandler mNetHandler;
    protected NetListener mNetListener;
    protected OkHttpClient mOkHttpClient;
    protected String mPointToken;
    protected String mRobotId;
    private int mSoundID;
    protected SoundPool mSoundPool;
    protected String mToken;

    static /* synthetic */ String access$000() {
        return TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        initSound();
       // initValue();
    }

    private void initValue() {
        this.mOkHttpClient = new OkHttpClient();
        this.mRobotId = FaceHelp.getProductId();//tdo 怎么获取的robotId, 这个有啥用
        this.mNetHandler = new NetHandler(this);
        getPointToken();
        getToken();
    }

    private void initSound() {
        this.mSoundPool = new SoundPool.Builder().build();
        this.mSoundID = this.mSoundPool.load(this, R.raw.pressed, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void playSound() {
        this.mSoundPool.play(this.mSoundID, 0.1f, 0.5f, 0, 0, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getToken() {
        String str = "" + System.currentTimeMillis();
        //token 授权，这块看看文档有没有
        this.mOkHttpClient.newCall(new Request.Builder().post(new FormBody.Builder().add("appid", getResources().getString(R.string.appid)).add("timestamp", "" + str).add("sign", MD5Utils.getMD5(getResources().getString(R.string.appid) + getResources().getString(R.string.app_secret) + str)).build()).url("https://htkapi.higgsdynamics.com/auth/token").build()).enqueue(new Callback() { // from class: com.higgs.deliveryrobot.ui.BaseActivity.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String access$000 = BaseActivity.access$000();
                CusLogcat.showELog(access$000, "获取token报错：" + iOException.getMessage().toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string()).getJSONObject("data");
                    BaseActivity.this.mToken = jSONObject.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String access$000 = BaseActivity.access$000();
                CusLogcat.showELog(access$000, "TOKEN值为：" + BaseActivity.this.mToken);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void getPointToken() {
        String str = "" + System.currentTimeMillis();
        String string = getResources().getString(R.string.appid);
        String md5 = MD5Utils.getMD5(string + getResources().getString(R.string.app_secret) + str);
        CusLogcat.showDLog(TAG, "getPointToken() params = appid = " + string + "; timestamp = " + str + "; sign = " + md5);
        this.mOkHttpClient.newCall(new Request.Builder().post(new FormBody.Builder().add("appid", string).add("timestamp", str).add("sign", md5).build()).url("https://api.mk.higgsdynamics.com/auth/token").build()).enqueue(new Callback() { // from class: com.higgs.deliveryrobot.ui.BaseActivity.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String access$000 = BaseActivity.access$000();
                CusLogcat.showELog(access$000, "onFailure(Call call, IOException e) e" + iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String string2 = response.body().string();
                String access$000 = BaseActivity.access$000();
                CusLogcat.showDLog(access$000, "onResponse(Call call, Response response) resposeStr = " + string2);
                Message obtainMessage = BaseActivity.this.mNetHandler.obtainMessage();
                obtainMessage.what = 1004;
                obtainMessage.obj = string2;
                BaseActivity.this.mNetHandler.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNetListener(NetListener netListener) {
        this.mNetListener = netListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void get(String str, String str2) {
        String str3 = TAG;
        CusLogcat.showDLog(str3, "get(String url, String token) url = " + str + "; token = " + str2);
        Request.Builder url = new Request.Builder().url(str);
        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(str2);
        Request build = url.addHeader("Authorization", sb.toString()).get().build();
        String str4 = TAG;
        CusLogcat.showDLog(str4, "get(String url, String token) request head = " + build.headers().toString());
        this.mOkHttpClient.newCall(build).enqueue(new Callback() { // from class: com.higgs.deliveryrobot.ui.BaseActivity.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                String access$000 = BaseActivity.access$000();
                CusLogcat.showDLog(access$000, "get请求返回失败的值：" + iOException.getMessage());
                Message obtainMessage = BaseActivity.this.mNetHandler.obtainMessage();
                obtainMessage.obj = iOException;
                obtainMessage.what = 1001;
                BaseActivity.this.mNetHandler.sendMessage(obtainMessage);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                String access$000 = BaseActivity.access$000();
                CusLogcat.showDLog(access$000, "get请求返回成功的值：" + string);
                Message message = new Message();
                message.obj = string;
                message.what = 1000;
                BaseActivity.this.mNetHandler.sendMessage(message);
            }
        });
    }

    /* loaded from: classes.dex */
    private static class NetHandler extends Handler {
        private final WeakReference<BaseActivity> mActivity;

        public NetHandler(BaseActivity baseActivity) {
            this.mActivity = new WeakReference<>(baseActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            BaseActivity baseActivity = this.mActivity.get();
            if (baseActivity == null) {
                super.handleMessage(message);
            } else {
                baseActivity.cusHandleMessage(message);
            }
        }
    }

    protected void cusHandleMessage(Message message) {
        switch (message.what) {
            case 1000:
                String str = (String) message.obj;
                try {
                    if (JsonUtils.isJsonObject(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int i = jSONObject.getInt("code");
                            String str2 = "";
                            if (jSONObject.has("msg")) {
                                str2 = jSONObject.getString("msg");
                            }
                            if (i == 0) {
                                this.mNetListener.netSuccess(str);
                                return;
                            } else if (-1 == i) {
                                CusToast.showToast(str2);
                                return;
                            } else if (-2 == i) {
                                getPointToken();
                                CusToast.showToast(str2);
                                return;
                            } else {
                                return;
                            }
                        } catch (JSONException e) {
                            String str3 = TAG;
                            CusLogcat.showELog(str3, "post JSONException:" + e.getMessage());
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e2) {
                    String str4 = TAG;
                    CusLogcat.showELog(str4, "e:" + e2.getMessage());
                    return;
                }
            case 1001:
                this.mNetListener.netFail((Exception) message.obj);
                return;
            case 1002:
            case 1003:
            case 1005:
            default:
                return;
            case 1004:
                String str5 = (String) message.obj;
                if (JsonUtils.isJsonObject(str5)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str5);
                        int i2 = jSONObject2.getInt("code");
                        if (i2 == 0) {
                            this.mPointToken = jSONObject2.getJSONObject("data").getString("token");
                        } else if (-1 == i2) {
                            CusToast.showToast(jSONObject2.getString("msg"));
                        }
                    } catch (JSONException e3) {
                        String str6 = TAG;
                        CusLogcat.showELog(str6, "获取mPointToken报错：" + e3.getMessage());
                    }
                    String str7 = TAG;
                    CusLogcat.showDLog(str7, "mPointToken 值为：" + this.mPointToken);
                    return;
                }
                return;
        }
    }

    protected void xiaoPangTokenRequestSuccess(String str) {
        String str2 = TAG;
        CusLogcat.showDLog(str2, "xiaoPangTokenRequestSuccess(String content) content = " + str);
    }
}

