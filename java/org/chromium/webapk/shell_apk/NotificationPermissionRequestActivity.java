package org.chromium.webapk.shell_apk;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class NotificationPermissionRequestActivity extends Activity {
    public static final /* synthetic */ int d = 0;
    public String a;
    public String b;
    public Messenger c;

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 33) {
            Log.w("PermissionRequestActivity", "Cannot request notification permission before Android T.");
            finish();
            return;
        }
        String stringExtra = getIntent().getStringExtra("notificationChannelName");
        String stringExtra2 = getIntent().getStringExtra("notificationChannelId");
        Messenger messenger = (Messenger) getIntent().getParcelableExtra("messenger");
        if (stringExtra == null || stringExtra2 == null || messenger == null) {
            Log.w("PermissionRequestActivity", "Finishing because not all required extras were provided.");
            finish();
            return;
        }
        this.a = stringExtra;
        this.b = stringExtra2;
        this.c = messenger;
        if (getApplicationContext().getApplicationInfo().targetSdkVersion < 33) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel(this.b, this.a, "default_channel_id_high".equals(this.b) ? 4 : 3));
        }
        requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean zAreNotificationsEnabled = 0;
        zAreNotificationsEnabled = 0;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equals("android.permission.POST_NOTIFICATIONS")) {
                getApplicationContext().getSharedPreferences("org.chromium.webapk.shell_apk.PrefUtils", 0).edit().putBoolean("HAS_REQUESTED_NOTIFICATION_PERMISSION", true).apply();
                if (iArr[i2] != 0) {
                    break;
                }
                zAreNotificationsEnabled = 1;
                break;
            }
        }
        if (zAreNotificationsEnabled == 0) {
            zAreNotificationsEnabled = ((NotificationManager) getSystemService("notification")).areNotificationsEnabled();
        }
        Messenger messenger = this.c;
        Bundle bundle = new Bundle();
        bundle.putInt("permissionStatus", !zAreNotificationsEnabled);
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        finish();
    }
}
