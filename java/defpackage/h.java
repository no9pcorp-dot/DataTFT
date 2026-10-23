package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.webapk.shell_apk.IdentityService;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class h extends Binder implements IInterface {
    public final /* synthetic */ IdentityService a;

    public h(IdentityService identityService) {
        this.a = identityService;
        attachInterface(this, "org.chromium.webapk.lib.common.identity_service.IIdentityService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("org.chromium.webapk.lib.common.identity_service.IIdentityService");
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        f fVarA = g.a(this.a.getApplicationContext());
        String str = fVarA != null ? fVarA.a : null;
        parcel2.writeNoException();
        parcel2.writeString(str);
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
