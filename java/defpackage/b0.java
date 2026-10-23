package defpackage;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b0 {
    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("org.chromium.webapk.shell_apk", 0);
    }
}
