package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import org.chromium.webapk.shell_apk.h2o.H2OMainActivity;
import org.chromium.webapk.shell_apk.h2o.H2OOpaqueMainActivity;
import org.chromium.webapk.shell_apk.h2o.SplashActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class r implements n {
    public /* synthetic */ long a;
    public /* synthetic */ SplashActivity b;

    @Override // defpackage.n
    public final void a(f fVar, boolean z) {
        SplashActivity splashActivity = this.b;
        if (fVar == null) {
            splashActivity.finish();
            return;
        }
        e eVarA = e.a(splashActivity, splashActivity.getIntent(), fVar, z, -1L, this.a);
        int i = SplashActivity.g;
        if (eVarA == null) {
            splashActivity.finish();
            return;
        }
        Context applicationContext = splashActivity.getApplicationContext();
        if (g.b(eVarA)) {
            splashActivity.d = eVarA;
            p pVar = splashActivity.f;
            pVar.d = true;
            pVar.a();
            return;
        }
        d.a(splashActivity, eVarA, null, 268435456, false);
        ComponentName componentName = new ComponentName(applicationContext, (Class<?>) H2OMainActivity.class);
        ComponentName componentName2 = new ComponentName(applicationContext, (Class<?>) H2OOpaqueMainActivity.class);
        b0.a(applicationContext).edit().commit();
        PackageManager packageManager = applicationContext.getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, 1, 1);
        packageManager.setComponentEnabledSetting(componentName2, 2, 0);
        splashActivity.finish();
    }
}
