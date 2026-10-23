package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public boolean a;
    public f b;
    public boolean c;
    public Intent d;
    public String e;
    public int f;
    public boolean g;
    public long h;
    public long i;
    public String j;

    /* JADX WARN: Code duplicated, block: B:29:0x008f  */
    public static e a(Context context, Intent intent, f fVar, boolean z, long j, long j2) {
        String string;
        Bundle bundle;
        Bundle bundleE = d0.e(context);
        if (bundleE == null) {
            return null;
        }
        long longExtra = intent.getLongExtra("org.chromium.chrome.browser.webapk_launch_time", -1L);
        if (longExtra <= 0) {
            longExtra = j;
        }
        String stringExtra = intent.getStringExtra("org.chromium.webapk.selected_share_target_activity_class_name");
        if ("android.intent.action.SEND".equals(intent.getAction()) || "android.intent.action.SEND_MULTIPLE".equals(intent.getAction())) {
            intent.getComponent();
            stringExtra = intent.getComponent().getClassName();
        }
        boolean booleanExtra = true;
        int intExtra = 0;
        if (stringExtra != null) {
            try {
                ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), stringExtra), 128);
                bundle = activityInfo != null ? activityInfo.metaData : null;
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (bundle != null) {
                String string2 = bundle.getString("org.chromium.webapk.shell_apk.shareMethod");
                if (TextUtils.isEmpty(string2) ? false : "POST".equals(string2.toUpperCase(Locale.ENGLISH))) {
                    string = bundle.getString("org.chromium.webapk.shell_apk.shareAction");
                } else {
                    String string3 = bundle.getString("org.chromium.webapk.shell_apk.shareAction");
                    if (TextUtils.isEmpty(string3)) {
                        string = null;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new Pair(bundle.getString("org.chromium.webapk.shell_apk.shareParamTitle"), intent.getStringExtra("android.intent.extra.SUBJECT")));
                        arrayList.add(new Pair(bundle.getString("org.chromium.webapk.shell_apk.shareParamText"), intent.getStringExtra("android.intent.extra.TEXT")));
                        Uri.Builder builder = new Uri.Builder();
                        int size = arrayList.size();
                        while (intExtra < size) {
                            Object obj = arrayList.get(intExtra);
                            intExtra++;
                            Pair pair = (Pair) obj;
                            if (!TextUtils.isEmpty((CharSequence) pair.first) && !TextUtils.isEmpty((CharSequence) pair.second)) {
                                builder.appendQueryParameter((String) pair.first, (String) pair.second);
                            }
                        }
                        Uri.Builder builderBuildUpon = Uri.parse(string3).buildUpon();
                        String string4 = builder.build().toString();
                        if (TextUtils.isEmpty(string4)) {
                            string = string3;
                        } else {
                            builderBuildUpon.encodedQuery(string4.replace("%20", "+").substring(1));
                            string = builderBuildUpon.build().toString();
                        }
                    }
                }
            } else {
                string = null;
            }
            intExtra = 13;
        } else if (TextUtils.isEmpty(intent.getDataString())) {
            string = bundleE.getString("org.chromium.webapk.shell_apk.startUrl");
            booleanExtra = false;
        } else {
            string = intent.getDataString();
            intExtra = intent.getIntExtra("org.chromium.chrome.browser.webapp_source", 9);
            booleanExtra = intent.getBooleanExtra("org.chromium.chrome.browser.webapk_force_navigation", true);
        }
        if (string == null) {
            return null;
        }
        String string5 = bundleE.getString("org.chromium.webapk.shell_apk.startUrl");
        String string6 = bundleE.getString("org.chromium.webapk.shell_apk.loggedIntentUrlParam");
        if (!TextUtils.isEmpty(string6) && (!string.startsWith(string5) || TextUtils.isEmpty(Uri.parse(string).getQueryParameter(string6)))) {
            Uri.Builder builderBuildUpon2 = Uri.parse(string5).buildUpon();
            builderBuildUpon2.appendQueryParameter(string6, string);
            string = builderBuildUpon2.toString();
        }
        if (string == null) {
            return null;
        }
        if (!string.startsWith("http:") && !string.startsWith("https:")) {
            return null;
        }
        boolean z2 = bundleE.getBoolean("org.chromium.webapk.shell_apk.isArcChromeOs");
        e eVar = new e();
        eVar.a = z2;
        eVar.b = fVar;
        eVar.c = z;
        eVar.d = intent;
        eVar.e = string;
        eVar.f = intExtra;
        eVar.g = booleanExtra;
        eVar.h = longExtra;
        eVar.i = j2;
        eVar.j = stringExtra;
        return eVar;
    }
}
