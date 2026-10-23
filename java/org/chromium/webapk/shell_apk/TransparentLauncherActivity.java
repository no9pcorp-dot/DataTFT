package org.chromium.webapk.shell_apk;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import defpackage.d;
import defpackage.d0;
import defpackage.e;
import defpackage.o;
import defpackage.y;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class TransparentLauncherActivity extends Activity {
    public void a(e eVar) {
        if (eVar != null) {
            d0.b(getApplicationContext(), eVar);
            d.a(this, eVar, null, 268435456, false);
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        o oVar = new o(this);
        y yVar = new y();
        yVar.a = jElapsedRealtime;
        yVar.b = this;
        oVar.a(yVar);
    }
}
