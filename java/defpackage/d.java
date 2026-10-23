package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.webapk.a78285a08c75c412c_v2.R;
import org.chromium.webapk.shell_apk.ManageDataLauncherActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static void a(Activity activity, e eVar, Bundle bundle, int i, boolean z) {
        boolean zEquals;
        boolean z2;
        Intent intent;
        Context applicationContext = activity.getApplicationContext();
        int i2 = ManageDataLauncherActivity.c;
        ShortcutManager shortcutManager = (ShortcutManager) applicationContext.getSystemService(ShortcutManager.class);
        f fVar = eVar.b;
        String str = eVar.e;
        if (ManageDataLauncherActivity.b(applicationContext, fVar.a)) {
            String str2 = fVar.a;
            Intent intent2 = new Intent(applicationContext, (Class<?>) ManageDataLauncherActivity.class);
            intent2.setAction("android.support.customtabs.action.ACTION_MANAGE_TRUSTED_WEB_ACTIVITY_DATA");
            intent2.putExtra("SITE_SETTINGS_URL", str);
            intent2.putExtra("PROVIDER_PACKAGE", str2);
            shortcutManager.addDynamicShortcuts(Collections.singletonList(new ShortcutInfo.Builder(applicationContext, "android.support.customtabs.action.SITE_SETTINGS_SHORTCUT").setShortLabel(applicationContext.getString(R.string.site_settings_short_label)).setLongLabel(applicationContext.getString(R.string.site_settings_long_label)).setIcon(Icon.createWithResource(applicationContext, R.drawable.ic_site_settings)).setIntent(intent2).build()));
        } else {
            shortcutManager.removeDynamicShortcuts(Collections.singletonList("android.support.customtabs.action.SITE_SETTINGS_SHORTCUT"));
        }
        Context applicationContext2 = activity.getApplicationContext();
        String str3 = fVar.a;
        HashSet hashSet = g.a;
        if (TextUtils.isEmpty(str3)) {
            zEquals = false;
        } else {
            Bundle bundleE = d0.e(applicationContext2);
            String str4 = null;
            String string = bundleE == null ? null : bundleE.getString("org.chromium.webapk.shell_apk.runtimeHost");
            if (!TextUtils.isEmpty(string)) {
                PackageManager packageManager = applicationContext2.getPackageManager();
                if (TextUtils.isEmpty(string)) {
                    z2 = false;
                } else {
                    try {
                        z2 = packageManager.getApplicationInfo(string, 0).enabled;
                    } catch (PackageManager.NameNotFoundException unused) {
                        z2 = false;
                    }
                }
                if (z2) {
                    str4 = string;
                }
            }
            zEquals = TextUtils.equals(str3, str4);
        }
        String str5 = fVar.a;
        ComponentName componentName = fVar.b;
        Uri uri = Uri.parse(str);
        if (zEquals) {
            intent = new Intent();
            intent.setAction("com.google.android.apps.chrome.webapps.WebappManager.ACTION_START_WEBAPP");
        } else {
            intent = new Intent("android.intent.action.VIEW", uri);
        }
        if (componentName != null) {
            intent.setSelector(new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("http://")));
            intent.setComponent(componentName);
        } else {
            intent.setPackage(str5);
        }
        intent.setFlags(i);
        Bundle extras = eVar.d.getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.removeExtra("org.chromium.webapk.relaunch");
        intent.putExtra("org.chromium.chrome.browser.webapp_url", str).putExtra("org.chromium.chrome.browser.webapp_source", eVar.f).putExtra("org.chromium.chrome.browser.webapk_package_name", activity.getPackageName()).putExtra("org.chromium.webapk.selected_share_target_activity_class_name", eVar.j).putExtra("org.chromium.chrome.browser.webapk_force_navigation", eVar.g);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!eVar.c) {
            long j = eVar.h;
            if (j >= 0) {
                intent.putExtra("org.chromium.chrome.browser.webapk_launch_time", j);
            }
        }
        long j2 = eVar.i;
        if (j2 >= 0) {
            intent.putExtra("org.chromium.webapk.new_style_splash_shown_time", j2);
        }
        try {
            if (z) {
                activity.startActivityForResult(intent, 0);
            } else {
                activity.startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Log.w("cr_HostBrowserLauncher", "Unable to launch browser in WebAPK mode.");
            e.printStackTrace();
        }
    }
}
