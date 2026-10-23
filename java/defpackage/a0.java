package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.chromium.webapk.a78285a08c75c412c_v2.R;
import org.chromium.webapk.shell_apk.NotificationPermissionRequestActivity;
import org.chromium.webapk.shell_apk.WebApkServiceFactory;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends Binder implements IInterface {
    public int a;
    public IBinder b;
    public WebApkServiceFactory c;

    public final int a(String str) {
        IBinder iBinder = this.b;
        if (iBinder == null) {
            return -1;
        }
        try {
            Field declaredField = iBinder.getClass().getSuperclass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return ((Integer) declaredField.get(null)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public final boolean b(int i, Parcel parcel, Parcel parcel2, int i2) {
        WebApkServiceFactory webApkServiceFactory = this.c;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("org.chromium.webapk.lib.runtime_library.IWebApkApi");
        }
        if (i != 1598968902) {
            PendingIntent activity = null;
            switch (i) {
                case 1:
                    Log.w("cr_WebApkServiceImplWrapper", "Should NOT reach WebApkServiceImplWrapper#getSmallIconId().");
                    parcel2.writeNoException();
                    parcel2.writeInt(-1);
                    break;
                case 2:
                    String string = parcel.readString();
                    int i3 = parcel.readInt();
                    Notification notification = (Notification) (parcel.readInt() != 0 ? Notification.CREATOR.createFromParcel(parcel) : null);
                    String channelId = notification.getChannelId();
                    if (channelId == null) {
                        channelId = "default_channel_id";
                    }
                    ((NotificationManager) webApkServiceFactory.getSystemService("notification")).createNotificationChannel(new NotificationChannel(channelId, webApkServiceFactory.getString(R.string.notification_channel_name), "default_channel_id_high".equals(channelId) ? 4 : 3));
                    Notification.Builder builderRecoverBuilder = Notification.Builder.recoverBuilder(webApkServiceFactory, notification);
                    builderRecoverBuilder.setChannelId(channelId);
                    Notification notificationBuild = builderRecoverBuilder.build();
                    IBinder iBinder = this.b;
                    if (iBinder != null) {
                        try {
                            Method method = iBinder.getClass().getMethod("notifyNotification", String.class, Integer.TYPE, Notification.class);
                            method.setAccessible(true);
                            method.invoke(iBinder, string, Integer.valueOf(i3), notificationBuild);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    parcel2.writeNoException();
                    break;
                case 3:
                    parcel.readString();
                    parcel.readInt();
                    Log.w("cr_WebApkServiceImplWrapper", "Should NOT reach WebApkServiceImplWrapper#cancelNotification(String, int).");
                    parcel2.writeNoException();
                    return true;
                case 4:
                    Log.w("cr_WebApkServiceImplWrapper", "Should NOT reach WebApkServiceImplWrapper#notificationPermissionEnabled().");
                    parcel2.writeNoException();
                    parcel2.writeInt(0);
                    return true;
                case 5:
                    parcel.readString();
                    parcel.readInt();
                    parcel.readString();
                    Log.w("cr_WebApkServiceImplWrapper", "Should NOT reach WebApkServiceImplWrapper#notifyNotificationWithChannel(String, int, Notification, String)");
                    parcel2.writeNoException();
                    return true;
                case 6:
                    Log.w("cr_WebApkServiceImplWrapper", "Should NOT reach WebApkServiceImplWrapper#finishAndRemoveTaskSdk23()");
                    parcel2.writeNoException();
                    parcel2.writeInt(0);
                    return true;
                case 7:
                    int i4 = !((NotificationManager) webApkServiceFactory.getSystemService("notification")).areNotificationsEnabled() ? 1 : 0;
                    if (i4 == 1 && !webApkServiceFactory.getApplicationContext().getSharedPreferences("org.chromium.webapk.shell_apk.PrefUtils", 0).getBoolean("HAS_REQUESTED_NOTIFICATION_PERMISSION", false) && Build.VERSION.SDK_INT >= 33) {
                        i4 = 2;
                    }
                    parcel2.writeNoException();
                    parcel2.writeInt(i4);
                    return true;
                case 8:
                    String string2 = parcel.readString();
                    String string3 = parcel.readString();
                    int i5 = Build.VERSION.SDK_INT;
                    if (i5 < 33) {
                        Log.w("cr_WebApkServiceImplWrapper", "Cannot request notification permission before Android T.");
                    } else {
                        int i6 = NotificationPermissionRequestActivity.d;
                        Intent intent = new Intent(webApkServiceFactory.getApplicationContext(), (Class<?>) NotificationPermissionRequestActivity.class);
                        intent.putExtra("notificationChannelName", string2);
                        intent.putExtra("notificationChannelId", string3);
                        activity = PendingIntent.getActivity(webApkServiceFactory.getApplicationContext(), 0, intent, i5 >= 31 ? 33554432 : 0);
                    }
                    parcel2.writeNoException();
                    if (activity == null) {
                        parcel2.writeInt(0);
                        return true;
                    }
                    parcel2.writeInt(1);
                    activity.writeToParcel(parcel2, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        } else {
            parcel2.writeString("org.chromium.webapk.lib.runtime_library.IWebApkApi");
        }
        return true;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        int callingUid = Binder.getCallingUid();
        int i3 = this.a;
        if (i3 != callingUid) {
            throw new RemoteException("Unauthorized caller " + callingUid + " does not match expected host=" + i3);
        }
        if (i == a("TRANSACTION_notifyNotification") || i == a("TRANSACTION_checkNotificationPermission") || i == a("TRANSACTION_requestNotificationPermission")) {
            return b(i, parcel, parcel2, i2);
        }
        IBinder iBinder = this.b;
        if (iBinder == null) {
            return false;
        }
        try {
            Class<?> cls = iBinder.getClass();
            Class cls2 = Integer.TYPE;
            Method method = cls.getMethod("onTransact", cls2, Parcel.class, Parcel.class, cls2);
            method.setAccessible(true);
            return ((Boolean) method.invoke(iBinder, Integer.valueOf(i), parcel, parcel2, Integer.valueOf(i2))).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
