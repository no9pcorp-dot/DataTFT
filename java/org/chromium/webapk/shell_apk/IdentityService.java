package org.chromium.webapk.shell_apk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.h;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class IdentityService extends Service {
    public final h a = new h(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.a;
    }
}
