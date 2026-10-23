package defpackage;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static void a(Context context, Intent intent, String str, long j, ComponentName componentName) {
        Intent intent2 = new Intent("android.intent.action.VIEW", intent.getData());
        intent2.setComponent(componentName);
        intent2.setFlags(268435456);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            intent2.putExtras(extras);
        }
        if (str != null) {
            intent2.putExtra("org.chromium.webapk.selected_share_target_activity_class_name", str);
        }
        if (j != -1) {
            intent2.putExtra("org.chromium.chrome.browser.webapk_launch_time", j);
        }
        int i = 0;
        if (str != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayListA = d0.a(intent);
            int size = arrayListA.size();
            int i2 = 0;
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayListA.get(i3);
                i3++;
                Uri uri = (Uri) obj;
                if (!arrayList.contains(uri)) {
                    if (context.checkUriPermission(uri, Process.myPid(), Process.myUid(), 1) == 0) {
                        arrayList.add(uri);
                    } else {
                        i2++;
                    }
                }
            }
            if (i2 > 0) {
                Log.w("cr_WebApkUtils", "Not forwarding " + i2 + " shared URI(s) the WebAPK cannot read");
            }
            int size2 = arrayList.size();
            ClipData clipDataNewRawUri = null;
            while (i < size2) {
                Object obj2 = arrayList.get(i);
                i++;
                Uri uri2 = (Uri) obj2;
                if (uri2 != null) {
                    if (clipDataNewRawUri == null) {
                        clipDataNewRawUri = ClipData.newRawUri("webapk_share", uri2);
                    } else {
                        clipDataNewRawUri.addItem(new ClipData.Item(uri2));
                    }
                }
            }
            if (clipDataNewRawUri != null) {
                intent2.setClipData(clipDataNewRawUri);
                intent2.addFlags(1);
            }
            i = !arrayList.isEmpty() ? 1 : 0;
        }
        try {
            context.startActivity(intent2);
        } catch (SecurityException e) {
            if (i == 0) {
                throw e;
            }
            Log.w("cr_H2OLauncher", "Unable to pass shared URI grants along, launching without them.", e);
            intent2.setClipData(null);
            intent2.setFlags(intent2.getFlags() & (-2));
            context.startActivity(intent2);
        }
    }
}
