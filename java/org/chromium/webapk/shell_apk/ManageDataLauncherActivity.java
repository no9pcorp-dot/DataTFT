package org.chromium.webapk.shell_apk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import defpackage.d0;
import org.chromium.webapk.a78285a08c75c412c_v2.R;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class ManageDataLauncherActivity extends Activity {
    public static final /* synthetic */ int c = 0;
    public String a;
    public Uri b;

    public static boolean b(Context context, String str) {
        Bundle bundleE = d0.e(context);
        if (bundleE == null || !bundleE.getBoolean("org.chromium.webapk.shell_apk.enableSiteSettingsShortcut", false)) {
            return false;
        }
        Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
        intent.addCategory("androidx.browser.trusted.category.LaunchWebApkSiteSettings");
        intent.setPackage(str);
        return context.getPackageManager().queryIntentServices(intent, 64).size() > 0;
    }

    public final void a() {
        String string;
        try {
            string = getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(this.a, 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            string = this.a;
        }
        Toast.makeText(this, getString(R.string.no_support_for_launch_settings, string), 1).show();
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = getIntent().getStringExtra("PROVIDER_PACKAGE");
        this.b = Uri.parse(getIntent().getStringExtra("SITE_SETTINGS_URL"));
        if (!b(this, this.a)) {
            a();
            return;
        }
        View progressBar = new ProgressBar(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        progressBar.setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(progressBar);
        setContentView(frameLayout);
        Intent intent = new Intent();
        intent.setAction("android.support.customtabs.action.ACTION_MANAGE_TRUSTED_WEB_ACTIVITY_DATA");
        intent.setPackage(this.a);
        intent.setData(this.b);
        intent.putExtra("org.chromium.webapk.is_webapk", true);
        try {
            startActivityForResult(intent, 0);
            finish();
        } catch (ActivityNotFoundException unused) {
            a();
        }
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        finish();
    }
}
