package defpackage;

import android.util.Log;
import java.io.File;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(File file) {
        File[] fileArrListFiles;
        if (file != null) {
            if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    a(file2);
                }
            }
            if (file.delete()) {
                return;
            }
            Log.e("cr.DexLoader", "Failed to delete : " + file.getAbsolutePath());
        }
    }
}
