package org.chromium.webapk.shell_apk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import defpackage.a;
import defpackage.a0;
import defpackage.c;
import defpackage.f;
import defpackage.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import org.chromium.webapk.a78285a08c75c412c_v2.R;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class WebApkServiceFactory extends Service {
    /* JADX WARN: Code duplicated, block: B:129:0x01a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x01b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x0128 A[EDGE_INSN: B:149:0x0128->B:70:0x0128 BREAK  A[LOOP:0: B:67:0x011e->B:69:0x0124], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0067  */
    /* JADX WARN: Code duplicated, block: B:32:0x008a  */
    /* JADX WARN: Code duplicated, block: B:33:0x008c  */
    /* JADX WARN: Code duplicated, block: B:51:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:54:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:59:0x0101  */
    /* JADX WARN: Code duplicated, block: B:61:0x0107  */
    /* JADX WARN: Code duplicated, block: B:69:0x0124 A[Catch: IOException -> 0x017f, LOOP:0: B:67:0x011e->B:69:0x0124, LOOP_END, TryCatch #16 {IOException -> 0x017f, blocks: (B:66:0x011c, B:67:0x011e, B:69:0x0124, B:70:0x0128), top: B:141:0x011c }] */
    /* JADX WARN: Code duplicated, block: B:73:0x013b  */
    /* JADX WARN: Code duplicated, block: B:75:0x0141  */
    /* JADX WARN: Code duplicated, block: B:97:0x019d  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v27, types: [a0, android.os.Binder, android.os.IBinder, android.os.IInterface] */
    /* JADX WARN: Type inference failed for: r2v28, types: [android.os.IBinder] */
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) throws Throwable {
        Context contextCreatePackageContext;
        SharedPreferences sharedPreferences;
        int i;
        int i2;
        Scanner scanner;
        int iNextInt;
        Scanner scanner2;
        File dir;
        String str;
        File file;
        BaseDexClassLoader baseDexClassLoader;
        InputStream inputStreamOpen;
        FileOutputStream fileOutputStream;
        byte[] bArr;
        int i3;
        BaseDexClassLoader baseDexClassLoader2;
        File file2;
        BaseDexClassLoader baseDexClassLoader3;
        int i4;
        f fVarA = g.a(this);
        String str2 = fVarA != null ? fVarA.a : null;
        if (!g.a.contains(str2)) {
            Log.w("cr_WebApkServiceFactory", "Host browser does not support WebAPK notification delegation.");
            return null;
        }
        try {
            contextCreatePackageContext = getApplicationContext().createPackageContext(str2, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            contextCreatePackageContext = null;
        }
        int i5 = -1;
        if (contextCreatePackageContext == null) {
            Log.w("cr_HostBrowserClassLoader", "Failed to get remote context.");
            baseDexClassLoader3 = null;
            baseDexClassLoader = null;
        } else {
            if (c.a != null) {
                int i6 = getSharedPreferences("org.chromium.webapk.shell_apk", 0).getInt("org.chromium.webapk.shell_apk.version_code", -1);
                try {
                    i4 = contextCreatePackageContext.getPackageManager().getPackageInfo(contextCreatePackageContext.getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e("cr_HostBrowserClassLoader", "Failed to get remote package info.");
                    i4 = -1;
                }
                if (i4 == i6) {
                    baseDexClassLoader = null;
                } else {
                    sharedPreferences = getSharedPreferences("org.chromium.webapk.shell_apk", 0);
                    i = sharedPreferences.getInt("org.chromium.webapk.shell_apk.dex_version", -1);
                    try {
                        i2 = contextCreatePackageContext.getPackageManager().getPackageInfo(contextCreatePackageContext.getPackageName(), 0).versionCode;
                    } catch (PackageManager.NameNotFoundException unused2) {
                        Log.e("cr_HostBrowserClassLoader", "Failed to get remote package info.");
                        i2 = -1;
                    }
                    if (sharedPreferences.getInt("org.chromium.webapk.shell_apk.version_code", -1) == i2) {
                        iNextInt = -1;
                    } else {
                        try {
                            scanner2 = new Scanner(contextCreatePackageContext.getAssets().open("webapk_dex_version.txt"));
                            try {
                                try {
                                    iNextInt = scanner2.nextInt();
                                    try {
                                        scanner2.close();
                                    } catch (Exception unused3) {
                                        if (scanner2 != null) {
                                        }
                                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                        editorEdit.putInt("org.chromium.webapk.shell_apk.version_code", i2);
                                        editorEdit.putInt("org.chromium.webapk.shell_apk.dex_version", iNextInt);
                                        editorEdit.apply();
                                        if (iNextInt == -1) {
                                            iNextInt = i;
                                        }
                                        dir = getDir("dex", 0);
                                        if (iNextInt != i) {
                                            Log.w("cr_HostBrowserClassLoader", "Delete cached dex files.");
                                            a.a(dir);
                                        }
                                        str = "webapk" + iNextInt + ".dex";
                                        file = new File(dir, str);
                                        if (!file.exists()) {
                                            if (dir.exists()) {
                                            }
                                            try {
                                                inputStreamOpen = contextCreatePackageContext.getAssets().open(str);
                                                try {
                                                    fileOutputStream = new FileOutputStream(file);
                                                    try {
                                                        bArr = new byte[16384];
                                                        while (true) {
                                                            i3 = inputStreamOpen.read(bArr, 0, 16384);
                                                            if (i3 == -1) {
                                                                break;
                                                            }
                                                            fileOutputStream.write(bArr, 0, i3);
                                                        }
                                                        inputStreamOpen.close();
                                                        fileOutputStream.close();
                                                        file2 = new File(dir, "optimized");
                                                        if (file2.exists()) {
                                                        }
                                                        try {
                                                            baseDexClassLoader = null;
                                                            try {
                                                                baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                                                                baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                                                            } catch (Exception e2) {
                                                                e = e2;
                                                                String path = file2.getPath();
                                                                Log.w("cr.DexLoader", "Could not load dex from " + file.getPath() + " with optimized directory " + path);
                                                                e.printStackTrace();
                                                                baseDexClassLoader2 = baseDexClassLoader;
                                                            }
                                                        } catch (Exception e3) {
                                                            e = e3;
                                                            baseDexClassLoader = null;
                                                        }
                                                    } catch (IOException unused4) {
                                                        baseDexClassLoader = null;
                                                        if (inputStreamOpen != null) {
                                                            try {
                                                                inputStreamOpen.close();
                                                            } catch (IOException unused5) {
                                                            }
                                                        }
                                                        if (fileOutputStream != null) {
                                                            try {
                                                                fileOutputStream.close();
                                                            } catch (IOException unused6) {
                                                            }
                                                        }
                                                        Log.w("cr.DexLoader", "Could not extract dex from assets");
                                                    }
                                                } catch (IOException unused7) {
                                                    baseDexClassLoader = null;
                                                    fileOutputStream = null;
                                                }
                                            } catch (IOException unused8) {
                                                baseDexClassLoader = null;
                                                inputStreamOpen = null;
                                                fileOutputStream = null;
                                            }
                                            c.a = baseDexClassLoader2;
                                            baseDexClassLoader3 = c.a;
                                        } else {
                                            if (dir.exists()) {
                                            }
                                            inputStreamOpen = contextCreatePackageContext.getAssets().open(str);
                                            fileOutputStream = new FileOutputStream(file);
                                            bArr = new byte[16384];
                                            while (true) {
                                                i3 = inputStreamOpen.read(bArr, 0, 16384);
                                                if (i3 == -1) {
                                                    break;
                                                    break;
                                                }
                                                fileOutputStream.write(bArr, 0, i3);
                                            }
                                            inputStreamOpen.close();
                                            fileOutputStream.close();
                                            file2 = new File(dir, "optimized");
                                            if (file2.exists()) {
                                            }
                                            baseDexClassLoader = null;
                                            baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                                            baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                                            c.a = baseDexClassLoader2;
                                            baseDexClassLoader3 = c.a;
                                        }
                                        if (baseDexClassLoader3 != null) {
                                            Log.w("cr_WebApkServiceFactory", "Unable to create ClassLoader.");
                                        } else {
                                            try {
                                                Constructor<?> constructor = baseDexClassLoader3.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl").getConstructor(Context.class, Bundle.class);
                                                if (str2 != null) {
                                                    try {
                                                        i5 = getPackageManager().getApplicationInfo(str2, 128).uid;
                                                    } catch (PackageManager.NameNotFoundException e4) {
                                                        e4.printStackTrace();
                                                    }
                                                }
                                                Bundle bundle = new Bundle();
                                                bundle.putInt("small_icon_id", R.drawable.notification_badge);
                                                bundle.putInt("host_browser_uid", i5);
                                                IBinder iBinder = (IBinder) constructor.newInstance(this, bundle);
                                                ?? a0Var = new a0();
                                                a0Var.attachInterface(a0Var, "org.chromium.webapk.lib.runtime_library.IWebApkApi");
                                                a0Var.c = this;
                                                a0Var.b = iBinder;
                                                a0Var.a = i5;
                                                return a0Var;
                                            } catch (Exception e5) {
                                                Log.w("cr_WebApkServiceFactory", "Unable to create WebApkServiceImpl.");
                                                e5.printStackTrace();
                                            }
                                        }
                                        return baseDexClassLoader;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    scanner = scanner2;
                                    if (scanner != null) {
                                        try {
                                            scanner.close();
                                        } catch (Exception unused9) {
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Exception unused10) {
                                iNextInt = -1;
                            }
                        } catch (Exception unused11) {
                            iNextInt = -1;
                            scanner2 = null;
                        } catch (Throwable th2) {
                            th = th2;
                            scanner = null;
                        }
                        try {
                            scanner2.close();
                        } catch (Exception unused12) {
                        }
                        SharedPreferences.Editor editorEdit2 = sharedPreferences.edit();
                        editorEdit2.putInt("org.chromium.webapk.shell_apk.version_code", i2);
                        editorEdit2.putInt("org.chromium.webapk.shell_apk.dex_version", iNextInt);
                        editorEdit2.apply();
                    }
                    if (iNextInt == -1) {
                        iNextInt = i;
                    }
                    dir = getDir("dex", 0);
                    if (iNextInt != i) {
                        Log.w("cr_HostBrowserClassLoader", "Delete cached dex files.");
                        a.a(dir);
                    }
                    str = "webapk" + iNextInt + ".dex";
                    file = new File(dir, str);
                    if (!file.exists() && file.length() != 0) {
                        file2 = new File(dir, "optimized");
                        if (file2.exists()) {
                        }
                        baseDexClassLoader = null;
                        baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                        baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                    } else if (!dir.exists() || dir.mkdirs()) {
                        inputStreamOpen = contextCreatePackageContext.getAssets().open(str);
                        fileOutputStream = new FileOutputStream(file);
                        bArr = new byte[16384];
                        while (true) {
                            i3 = inputStreamOpen.read(bArr, 0, 16384);
                            if (i3 == -1) {
                                break;
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i3);
                            baseDexClassLoader2 = baseDexClassLoader;
                        }
                        inputStreamOpen.close();
                        fileOutputStream.close();
                        file2 = new File(dir, "optimized");
                        if (!file2.exists() || file2.mkdirs()) {
                            baseDexClassLoader = null;
                            baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                            baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                        } else {
                            baseDexClassLoader2 = null;
                            baseDexClassLoader = null;
                        }
                    } else {
                        baseDexClassLoader2 = null;
                        baseDexClassLoader = null;
                    }
                    c.a = baseDexClassLoader2;
                }
            } else {
                sharedPreferences = getSharedPreferences("org.chromium.webapk.shell_apk", 0);
                i = sharedPreferences.getInt("org.chromium.webapk.shell_apk.dex_version", -1);
                i2 = contextCreatePackageContext.getPackageManager().getPackageInfo(contextCreatePackageContext.getPackageName(), 0).versionCode;
                if (sharedPreferences.getInt("org.chromium.webapk.shell_apk.version_code", -1) == i2) {
                    iNextInt = -1;
                } else {
                    scanner2 = new Scanner(contextCreatePackageContext.getAssets().open("webapk_dex_version.txt"));
                    iNextInt = scanner2.nextInt();
                    scanner2.close();
                    scanner2.close();
                    SharedPreferences.Editor editorEdit3 = sharedPreferences.edit();
                    editorEdit3.putInt("org.chromium.webapk.shell_apk.version_code", i2);
                    editorEdit3.putInt("org.chromium.webapk.shell_apk.dex_version", iNextInt);
                    editorEdit3.apply();
                }
                if (iNextInt == -1) {
                    iNextInt = i;
                }
                dir = getDir("dex", 0);
                if (iNextInt != i) {
                    Log.w("cr_HostBrowserClassLoader", "Delete cached dex files.");
                    a.a(dir);
                }
                str = "webapk" + iNextInt + ".dex";
                file = new File(dir, str);
                if (!file.exists()) {
                    if (dir.exists()) {
                    }
                    inputStreamOpen = contextCreatePackageContext.getAssets().open(str);
                    fileOutputStream = new FileOutputStream(file);
                    bArr = new byte[16384];
                    while (true) {
                        i3 = inputStreamOpen.read(bArr, 0, 16384);
                        if (i3 == -1) {
                            break;
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i3);
                        baseDexClassLoader2 = baseDexClassLoader;
                        c.a = baseDexClassLoader2;
                    }
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    file2 = new File(dir, "optimized");
                    if (file2.exists()) {
                    }
                    baseDexClassLoader = null;
                    baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                    baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                    c.a = baseDexClassLoader2;
                } else {
                    if (dir.exists()) {
                    }
                    inputStreamOpen = contextCreatePackageContext.getAssets().open(str);
                    fileOutputStream = new FileOutputStream(file);
                    bArr = new byte[16384];
                    while (true) {
                        i3 = inputStreamOpen.read(bArr, 0, 16384);
                        if (i3 == -1) {
                            break;
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i3);
                        baseDexClassLoader2 = baseDexClassLoader;
                        c.a = baseDexClassLoader2;
                    }
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    file2 = new File(dir, "optimized");
                    if (file2.exists()) {
                    }
                    baseDexClassLoader = null;
                    baseDexClassLoader2 = new BaseDexClassLoader(file.getPath(), file2, null, ClassLoader.getSystemClassLoader());
                    baseDexClassLoader2.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl");
                    c.a = baseDexClassLoader2;
                }
            }
            baseDexClassLoader3 = c.a;
        }
        if (baseDexClassLoader3 != null) {
            Constructor<?> constructor2 = baseDexClassLoader3.loadClass("org.chromium.webapk.lib.runtime_library.WebApkServiceImpl").getConstructor(Context.class, Bundle.class);
            if (str2 != null) {
                i5 = getPackageManager().getApplicationInfo(str2, 128).uid;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt("small_icon_id", R.drawable.notification_badge);
            bundle2.putInt("host_browser_uid", i5);
            IBinder iBinder2 = (IBinder) constructor2.newInstance(this, bundle2);
            ?? a0Var2 = new a0();
            a0Var2.attachInterface(a0Var2, "org.chromium.webapk.lib.runtime_library.IWebApkApi");
            a0Var2.c = this;
            a0Var2.b = iBinder2;
            a0Var2.a = i5;
            return a0Var2;
        }
        Log.w("cr_WebApkServiceFactory", "Unable to create ClassLoader.");
        return baseDexClassLoader;
    }
}
