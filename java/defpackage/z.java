package defpackage;

import android.os.Bundle;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {
    public static long a(Bundle bundle, String str, long j) {
        String string = bundle.getString(str);
        if (string == null || !string.endsWith("L")) {
            return j;
        }
        try {
            return Long.parseLong(string.substring(0, string.length() - 1));
        } catch (NumberFormatException unused) {
            return j;
        }
    }
}
