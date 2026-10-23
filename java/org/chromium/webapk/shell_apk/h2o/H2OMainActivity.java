package org.chromium.webapk.shell_apk.h2o;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import defpackage.b0;
import defpackage.d;
import defpackage.e;
import defpackage.g;
import org.chromium.webapk.shell_apk.TransparentLauncherActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class H2OMainActivity extends TransparentLauncherActivity {
    @Override // org.chromium.webapk.shell_apk.TransparentLauncherActivity
    public final void a(e eVar) {
        if (eVar != null) {
            Context applicationContext = getApplicationContext();
            if (g.b(eVar)) {
                if (System.currentTimeMillis() - b0.a(applicationContext).getLong("org.chromium.webapk.shell_apk.request_host_browser_relaunch_timestamp", -1L) > 20000) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    SharedPreferences.Editor editorEdit = getSharedPreferences("org.chromium.webapk.shell_apk", 0).edit();
                    editorEdit.putLong("org.chromium.webapk.shell_apk.request_host_browser_relaunch_timestamp", jCurrentTimeMillis);
                    editorEdit.commit();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("org.chromium.webapk.relaunch", true);
                    d.a(this, eVar, bundle, 268435456, false);
                    ComponentName componentName = new ComponentName(applicationContext, (Class<?>) H2OOpaqueMainActivity.class);
                    ComponentName componentName2 = getComponentName();
                    b0.a(applicationContext).edit().commit();
                    PackageManager packageManager = applicationContext.getPackageManager();
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                    packageManager.setComponentEnabledSetting(componentName2, 2, 0);
                    return;
                }
            }
            d.a(this, eVar, null, 268435456, false);
        }
    }
}
