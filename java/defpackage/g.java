package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static final HashSet a = new HashSet(Arrays.asList("com.google.android.apps.chrome", "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.chrome.canary", "org.chromium.chrome", "org.chromium.chrome.tests", "org.chromium.arc.intent_helper", "org.chromium.arc.webapk"));

    /* JADX WARN: Code duplicated, block: B:14:0x002a  */
    public static f a(Context context) {
        ComponentName componentName;
        boolean z;
        Bundle bundleE = d0.e(context);
        String string = bundleE == null ? null : bundleE.getString("org.chromium.webapk.shell_apk.runtimeHost");
        boolean z2 = false;
        if (TextUtils.isEmpty(string)) {
            string = null;
        } else {
            PackageManager packageManager = context.getPackageManager();
            if (TextUtils.isEmpty(string)) {
                z = false;
            } else {
                try {
                    z = packageManager.getApplicationInfo(string, 0).enabled;
                } catch (PackageManager.NameNotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                string = null;
            }
        }
        if (!TextUtils.isEmpty(string)) {
            f fVar = new f();
            fVar.a = string;
            fVar.b = null;
            return fVar;
        }
        PackageManager packageManager2 = context.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager2.resolveActivity(new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("http://")), 65536);
        if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) {
            componentName = null;
        } else {
            ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
            componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
        }
        if (componentName == null || TextUtils.isEmpty(componentName.getPackageName())) {
            return null;
        }
        String packageName = componentName.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            try {
                z2 = packageManager2.getApplicationInfo(packageName, 0).enabled;
            } catch (PackageManager.NameNotFoundException unused2) {
            }
        }
        if (!z2) {
            return null;
        }
        f fVar2 = new f();
        fVar2.a = componentName.getPackageName();
        fVar2.b = componentName;
        return fVar2;
    }

    public static boolean b(e eVar) {
        boolean z = eVar.a;
        f fVar = eVar.b;
        return (z || fVar.a.equals("org.chromium.arc.intent_helper") || fVar.a.equals("org.chromium.arc.webapk")) ? false : true;
    }
}
