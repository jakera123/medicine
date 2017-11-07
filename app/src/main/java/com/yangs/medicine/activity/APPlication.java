package com.yangs.medicine.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yangs.medicine.source.QuestionSource;
import com.yangs.medicine.util.CrashHandler;

/**
 * Created by yangs on 2017/9/23 0023.
 */

public class APPlication extends Application {
    private static Context context;
    public static SharedPreferences save;
    public static String grade;
    public static String subject;
    public static Boolean DEBUG;
    public static SQLiteDatabase db;
    public static QuestionSource questionSource;
    public static String user;
    private static String model;
    private static String version;

    @Override
    public void onCreate() {
        super.onCreate();
        DEBUG = true;
        context = getApplicationContext();
        Fresco.initialize(this);
        save = getSharedPreferences("Medicine", MODE_PRIVATE);
        db = context.openOrCreateDatabase("medicine.db", Context.MODE_PRIVATE, null);
        grade = save.getString("grade", "");
        subject = save.getString("subject", "");
        questionSource = new QuestionSource("yangs", "12345");
        if (!DEBUG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(getApplicationContext());
        }
        user = "yangs";
        model = android.os.Build.MODEL + ";" + android.os.Build.VERSION.SDK + ";"
                + android.os.Build.VERSION.RELEASE;
        try {
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName()
                    , 0);
            version = packageInfo.versionName;
        } catch (Exception e) {
            version = "1.0";
            e.printStackTrace();
        }
    }

    public static void showToast(String msg, int time) {
        Toast.makeText(context, msg, time).show();
    }

    public static Context getContext() {
        return context;
    }

    public static String getModel() {
        return model;
    }

    public static String getVersion() {
        return version;
    }
}
