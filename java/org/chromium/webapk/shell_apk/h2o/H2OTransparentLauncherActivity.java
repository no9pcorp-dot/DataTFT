package org.chromium.webapk.shell_apk.h2o;

import android.content.ComponentName;
import android.content.Context;
import defpackage.b;
import defpackage.d;
import defpackage.d0;
import defpackage.e;
import defpackage.g;
import org.chromium.webapk.shell_apk.TransparentLauncherActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class H2OTransparentLauncherActivity extends TransparentLauncherActivity {
    /* JADX WARN: Code duplicated, block: B:20:0x0058  */
    @Override // org.chromium.webapk.shell_apk.TransparentLauncherActivity
    public final void a(e eVar) {
        ComponentName componentName;
        ComponentName componentName2;
        if (eVar != null) {
            d0.b(getApplicationContext(), eVar);
            boolean zB = g.b(eVar);
            Context applicationContext = getApplicationContext();
            boolean z = eVar.a;
            boolean z2 = true;
            if (zB) {
                boolean z3 = !z;
                int componentEnabledSetting = applicationContext.getPackageManager().getComponentEnabledSetting(new ComponentName(applicationContext, (Class<?>) H2OOpaqueMainActivity.class));
                if (componentEnabledSetting == 0) {
                    z2 = z3;
                } else if (componentEnabledSetting != 1) {
                    z2 = false;
                }
                if (z2) {
                    componentName2 = null;
                } else {
                    componentName = new ComponentName(applicationContext, (Class<?>) H2OMainActivity.class);
                    componentName2 = componentName;
                }
            } else {
                int componentEnabledSetting2 = applicationContext.getPackageManager().getComponentEnabledSetting(new ComponentName(applicationContext, (Class<?>) H2OMainActivity.class));
                if (componentEnabledSetting2 != 0) {
                    z = componentEnabledSetting2 == 1;
                }
                if (z) {
                    componentName2 = null;
                } else {
                    componentName = new ComponentName(applicationContext, (Class<?>) SplashActivity.class);
                    componentName2 = componentName;
                }
            }
            if (componentName2 != null) {
                b.a(getApplicationContext(), getIntent(), eVar.j, -1L, componentName2);
            } else if (!zB) {
                d.a(this, eVar, null, 268435456, false);
            } else {
                Context applicationContext2 = getApplicationContext();
                b.a(applicationContext2, getIntent(), eVar.j, eVar.h, new ComponentName(applicationContext2, (Class<?>) SplashActivity.class));
            }
        }
    }
}
