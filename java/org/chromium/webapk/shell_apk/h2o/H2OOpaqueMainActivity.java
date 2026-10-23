package org.chromium.webapk.shell_apk.h2o;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import defpackage.b;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class H2OOpaqueMainActivity extends Activity {
    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        if (!getIntent().getBooleanExtra("org.chromium.webapk.bring_to_front", false)) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            super.onCreate(bundle);
            Context applicationContext = getApplicationContext();
            overridePendingTransition(0, 0);
            b.a(applicationContext, getIntent(), null, jElapsedRealtime, new ComponentName(applicationContext, (Class<?>) SplashActivity.class));
            finish();
            return;
        }
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager != null) {
            try {
                activityManager.moveTaskToFront(getTaskId(), 0);
            } catch (NullPointerException e) {
                Log.w("H2OOpaqueMainActivity", "Caught expected NPE from Android API, see crbug.com/471434499", e);
            }
        }
        finish();
    }
}
