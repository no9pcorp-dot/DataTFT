package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements DialogInterface.OnClickListener {
    public /* synthetic */ l a;
    public /* synthetic */ m b;
    public /* synthetic */ String c;

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a = true;
        m mVar = this.b;
        String str = this.c;
        try {
            Activity activity = mVar.b.b;
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
            intent.addFlags(268435456);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
        mVar.a.a(null, true);
    }
}
