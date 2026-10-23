package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.webapk.a78285a08c75c412c_v2.R;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class o {
    public final Context a;
    public final Activity b;

    public o(Activity activity) {
        this.b = activity;
        this.a = activity.getApplicationContext();
    }

    public final void a(n nVar) {
        Context context = this.a;
        Bundle bundleE = d0.e(context);
        if (bundleE == null) {
            nVar.a(null, false);
            return;
        }
        Log.v("cr_LaunchHostBrowserSelector", "Package name of the WebAPK:" + context.getPackageName());
        f fVarA = g.a(context);
        if (fVarA != null && !TextUtils.isEmpty(fVarA.a)) {
            nVar.a(fVarA, false);
            return;
        }
        String string = bundleE.getString("org.chromium.webapk.shell_apk.runtimeHost");
        String string2 = bundleE.getString("org.chromium.webapk.shell_apk.runtimeHostApplicationName");
        if (TextUtils.isEmpty(string)) {
            string = "com.android.chrome";
            string2 = "Google Chrome";
        }
        m mVar = new m();
        mVar.a = nVar;
        mVar.b = this;
        String string3 = context.getString(R.string.name);
        Activity activity = this.b;
        View viewInflate = LayoutInflater.from(activity).inflate(R.layout.host_browser_list_item, (ViewGroup) null);
        TextView textView = new TextView(activity);
        textView.setText(activity.getString(R.string.install_host_browser_dialog_title, string3));
        Resources resources = activity.getResources();
        textView.setTextColor(resources.getColor(R.color.webapk_black_alpha_87, null));
        textView.setTextSize(0, resources.getDimension(R.dimen.headline_size_medium));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dialog_content_padding);
        textView.setPaddingRelative(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.title_bottom_padding));
        viewInflate.setPaddingRelative(dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.dialog_content_top_padding), dimensionPixelSize, dimensionPixelSize);
        ((ImageView) viewInflate.findViewById(R.id.browser_icon)).setImageResource(R.drawable.last_resort_runtime_host_logo);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.browser_name);
        textView2.setText(string2);
        textView2.setPaddingRelative(activity.getResources().getDimensionPixelSize(R.dimen.list_column_padding), 0, 0, 0);
        l lVar = new l();
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, android.R.style.Theme.DeviceDefault.Light.Dialog));
        AlertDialog.Builder negativeButton = builder.setCustomTitle(textView).setView(viewInflate).setNegativeButton(R.string.choose_host_browser_dialog_quit, new j());
        i iVar = new i();
        iVar.a = lVar;
        iVar.b = mVar;
        iVar.c = string;
        negativeButton.setPositiveButton(R.string.install_host_browser_dialog_install_button, iVar);
        AlertDialog alertDialogCreate = builder.create();
        k kVar = new k();
        kVar.a = lVar;
        kVar.b = mVar;
        alertDialogCreate.setOnDismissListener(kVar);
        alertDialogCreate.show();
    }
}
