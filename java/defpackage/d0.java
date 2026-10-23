package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import org.chromium.webapk.shell_apk.h2o.SplashActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d0 {
    public static ArrayList a(Intent intent) {
        ArrayList arrayList = new ArrayList();
        int i = Build.VERSION.SDK_INT;
        ArrayList parcelableArrayListExtra = i >= 34 ? intent.getParcelableArrayListExtra("android.intent.extra.STREAM", Uri.class) : intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        if (parcelableArrayListExtra == null) {
            parcelableArrayListExtra = new ArrayList(1);
            parcelableArrayListExtra.add((Uri) (i >= 34 ? (Parcelable) intent.getParcelableExtra("android.intent.extra.STREAM", Uri.class) : intent.getParcelableExtra("android.intent.extra.STREAM")));
        }
        int size = parcelableArrayListExtra.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = parcelableArrayListExtra.get(i2);
            i2++;
            Uri uri = (Uri) obj;
            if (uri != null) {
                arrayList.add(uri);
            }
        }
        return arrayList;
    }

    public static void b(Context context, e eVar) {
        if (eVar.j != null) {
            ArrayList arrayListA = a(eVar.d);
            int size = arrayListA.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayListA.get(i);
                i++;
                try {
                    context.grantUriPermission(eVar.b.a, (Uri) obj, 1);
                } catch (SecurityException e) {
                    Log.w("cr_WebApkUtils", "Unable to grant Uri permission", e);
                }
            }
        }
    }

    public static boolean c(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static boolean d(SplashActivity splashActivity) {
        try {
            return splashActivity.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive");
        } catch (SecurityException e) {
            Log.e("cr_WebApkUtils", "Unable to query for Automotive system feature", e);
            return false;
        }
    }

    public static Bundle e(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r11v14, types: [boolean, int] */
    public static boolean f(int i) {
        float fRed = Color.red(i) / 255.0f;
        float fGreen = Color.green(i) / 255.0f;
        float fBlue = Color.blue(i) / 255.0f;
        return (~(Math.abs(1.05f / ((((fBlue < 0.03928f ? fBlue / 12.92f : (float) Math.pow((fBlue + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f) + (((fGreen < 0.03928f ? fGreen / 12.92f : (float) Math.pow((fGreen + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f) + ((fRed < 0.03928f ? fRed / 12.92f : (float) Math.pow((fRed + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f))) + 0.05f)) > 3.0f ? 1 : (Math.abs(1.05f / ((((fBlue < 0.03928f ? fBlue / 12.92f : (float) Math.pow((fBlue + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.0722f) + (((fGreen < 0.03928f ? fGreen / 12.92f : (float) Math.pow((fGreen + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.7152f) + ((fRed < 0.03928f ? fRed / 12.92f : (float) Math.pow((fRed + 0.055f) / 1.055f, 2.4000000953674316d)) * 0.2126f))) + 0.05f)) == 3.0f ? 0 : -1))) >>> 31;
    }
}
