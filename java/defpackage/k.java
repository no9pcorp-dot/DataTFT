package defpackage;

import android.content.DialogInterface;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class k implements DialogInterface.OnDismissListener {
    public /* synthetic */ l a;
    public /* synthetic */ m b;

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        if (this.a.a) {
            return;
        }
        this.b.a.a(null, true);
    }
}
