package defpackage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import org.chromium.webapk.shell_apk.h2o.SplashActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q implements Runnable {
    public /* synthetic */ SplashActivity a;

    @Override // java.lang.Runnable
    public final void run() {
        SplashActivity splashActivity = this.a;
        int i = SplashActivity.g;
        Bitmap bitmap = splashActivity.c;
        if (bitmap == null) {
            splashActivity.a(null, Bitmap.CompressFormat.PNG);
        } else {
            splashActivity.a = new t(splashActivity, bitmap).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
