package com.hehe.facecompare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.FaceDetector;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FaceHelp {
    private static final String DIR_PATH = "/sdcard/picture_pad/";
    private static final String ROBOT_FILE_PATH = "/deviceinfo/robotinfo.txt";
    public static final double SIMILARITY = 0.7d;
    private static final String TAG = "FaceHelp";
    public static final long TIMEING_DELETE = 15000;
    public static final long TIME_INTERVAL = 10000;

    public static String getProductId() {
        String str = "DEFAULT";
        String str2 = "V0A0";
        String str3 = "00000";
        File file = new File("/deviceinfo/robotinfo.txt");
        if (file.isFile() && !file.exists()) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(":");
                    if (split[0].equals("RobotProduct")) {
                        str = split[1];
                    } else if (split[0].equals("RobotVersion")) {
                        str2 = split[1];
                    } else if (split[0].equals("RobotSerialNumber")) {
                        str3 = split[1];
                    }
                } else {
                    return str + "-" + str2 + "-" + str3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
