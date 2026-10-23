package org.chromium.webapk.shell_apk.h2o;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import defpackage.b0;
import defpackage.d;
import defpackage.d0;
import defpackage.e;
import defpackage.o;
import defpackage.p;
import defpackage.q;
import defpackage.r;
import defpackage.s;
import defpackage.w;
import defpackage.x;
import defpackage.z;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends Activity {
    public static final /* synthetic */ int g = 0;
    public AsyncTask a;
    public View b;
    public Bitmap c;
    public e d;
    public byte e;
    public final p f;

    public SplashActivity() {
        q qVar = new q();
        qVar.a = this;
        p pVar = new p();
        pVar.a = qVar;
        this.f = pVar;
    }

    public final void a(byte[] bArr, Bitmap.CompressFormat compressFormat) {
        int width = this.b.getWidth();
        int height = this.b.getHeight();
        Bitmap.CompressFormat compressFormat2 = SplashContentProvider.b;
        SharedPreferences.Editor editorEdit = b0.a(this).edit();
        editorEdit.putInt("org.chromium.webapk.shell_apk.splash_width", width);
        editorEdit.putInt("org.chromium.webapk.shell_apk.splash_height", height);
        editorEdit.apply();
        SplashContentProvider.b = compressFormat;
        SplashContentProvider.a(bArr);
        e eVar = this.d;
        Log.v("cr_H2OLauncher", "WebAPK Launch URL: " + eVar.e);
        Bundle bundle = new Bundle();
        bundle.putBoolean("org.chromium.chrome.browser.webapk.splash_provided_by_webapk", true);
        d.a(this, eVar, bundle, 65536, true);
        this.d = null;
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (this.e == 2 || i2 != 0) {
            return;
        }
        this.e = (byte) 1;
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        ComponentName componentName;
        int i;
        View viewA;
        super.onCreate(bundle);
        if (this.b == null) {
            Bundle bundleE = d0.e(this);
            int iA = (int) z.a(bundleE, "org.chromium.webapk.shell_apk.themeColor", -1L);
            int iA2 = (int) z.a(bundleE, "org.chromium.webapk.shell_apk.darkThemeColor", (int) z.a(bundleE, "org.chromium.webapk.shell_apk.themeColor", -16777216L));
            if (!d0.c(this)) {
                iA2 = iA;
            }
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            if (d0.d(this)) {
                window.setStatusBarColor(-16777216);
            } else {
                window.setStatusBarColor(iA2);
            }
            boolean zF = d0.f(iA);
            View rootView = getWindow().getDecorView().getRootView();
            int systemUiVisibility = rootView.getSystemUiVisibility();
            rootView.setSystemUiVisibility((zF || d0.d(this)) ? systemUiVisibility & (-8193) : systemUiVisibility | 8192);
            String string = bundleE.getString("org.chromium.webapk.shell_apk.orientation");
            if (string == null || !string.equals("natural")) {
                i = -1;
            } else {
                Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                int rotation = defaultDisplay.getRotation();
                i = 0;
                if (rotation == 0 || rotation == 2 ? defaultDisplay.getHeight() >= defaultDisplay.getWidth() : defaultDisplay.getHeight() < defaultDisplay.getWidth()) {
                    i = 1;
                }
            }
            if (i != -1) {
                setRequestedOrientation(i);
            }
            if (Build.VERSION.SDK_INT >= 31) {
                viewA = x.a(this);
                this.b = viewA;
            } else {
                viewA = w.a(this);
                this.b = viewA;
            }
            ViewTreeObserver viewTreeObserver = viewA.getViewTreeObserver();
            s sVar = new s();
            sVar.a = this;
            viewTreeObserver.addOnGlobalLayoutListener(sVar);
            setContentView(this.b);
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ComponentName componentName2 = new ComponentName(this, (Class<?>) SplashActivity.class);
        int taskId = getTaskId();
        Iterator<ActivityManager.AppTask> it = ((ActivityManager) getSystemService("activity")).getAppTasks().iterator();
        while (true) {
            if (!it.hasNext()) {
                componentName = null;
                break;
            }
            try {
                ActivityManager.RecentTaskInfo taskInfo = it.next().getTaskInfo();
                if (taskInfo != null && taskInfo.id == taskId) {
                    componentName = taskInfo.topActivity;
                    break;
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        if (componentName2.equals(componentName)) {
            o oVar = new o(this);
            r rVar = new r();
            rVar.a = jElapsedRealtime;
            rVar.b = this;
            oVar.a(rVar);
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        SplashContentProvider.a(null);
        AsyncTask asyncTask = this.a;
        if (asyncTask != null) {
            asyncTask.cancel(false);
            this.a = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.e = (byte) 2;
        p pVar = this.f;
        pVar.e = false;
        pVar.d = false;
        pVar.c = false;
        o oVar = new o(this);
        r rVar = new r();
        rVar.a = -1L;
        rVar.b = this;
        oVar.a(rVar);
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        if (this.e == 1) {
            finish();
            return;
        }
        this.e = (byte) 0;
        p pVar = this.f;
        pVar.c = true;
        pVar.a();
    }
}
