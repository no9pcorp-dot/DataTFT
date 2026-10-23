package defpackage;

import org.chromium.webapk.shell_apk.TransparentLauncherActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class y implements n {
    public /* synthetic */ long a;
    public /* synthetic */ TransparentLauncherActivity b;

    @Override // defpackage.n
    public final void a(f fVar, boolean z) {
        TransparentLauncherActivity transparentLauncherActivity = this.b;
        if (fVar == null) {
            transparentLauncherActivity.finish();
        } else {
            transparentLauncherActivity.a(e.a(transparentLauncherActivity, transparentLauncherActivity.getIntent(), fVar, z, this.a, -1L));
            transparentLauncherActivity.finish();
        }
    }
}
