package com.yukar1z0e.crackwx;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.setObjectField;


public class wxpluginmain implements IXposedHookLoadPackage {
    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("Begin", "Test Xposed--->" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.contains("com.tencent.mm")) {
            Log.d("Begin", "Xposed Hooked--->" + loadPackageParam.packageName);
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String[] phoneNumber = {"手机号", "手机号"};
                    lpparam = loadPackageParam;
                    crackWechat(phoneNumber[0]);
                    crackWechat(phoneNumber[1]);
                }
            });
        }
    }

    public void crackWechat(final String phoneNumber) {
        findAndHookMethod("com.tencent.mm.app.t", lpparam.classLoader, "a", StackTraceElement[].class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if ((Boolean) param.getResult()) {
                    Log.d("CrackMain", "----检测到xposed");
                    param.setResult(false);
                }
            }
        });

        final Class<?> FTSAddFriendUIClass = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI", lpparam.classLoader);
        final Class<?> FTSAddFriendUI$5Class = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$5", lpparam.classLoader);
        final Class<?> mClass = findClass("com.tencent.mm.ah.m", lpparam.classLoader);
        final Class<?> aoClass = findClass("com.tencent.mm.g.c.ao", lpparam.classLoader);
        final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);

        findAndHookMethod(FTSAddFriendUI$5Class, "onSceneEnd", int.class, int.class, String.class, mClass, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Log.d("CrackMain",
                        "r7--->" + param.args[0] +
                                "->>>r8--->" + param.args[1] +
                                "->>>r9--->" + param.args[2] +
                                "->>>r10--->" + param.args[3].toString());
            }
        });

        findAndHookMethod(FTSAddFriendUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setObjectField(param.thisObject, "query", phoneNumber);
                        callMethod(param.thisObject, "Mf", phoneNumber);
                    }
                }, 2000);
                callMethod(param.thisObject, "onBackPressed");
            }
        });

        findAndHookMethod(ContactInfoUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("CrackMain", "Hook onCreate() method and call onBackPressed() method");

                findAndHookMethod(ContactInfoUIClass, "onBackPressed", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        Field dUUField = findField(param.thisObject.getClass(), "dUU");
                        Object dUUObj = dUUField.get(param.thisObject);
                        Field field_username = findField(aoClass, "field_username");
                        Field field_alias = findField(aoClass, "field_alias");
                        Field field_encryptUsername = findField(aoClass, "field_encryptUsername");
                        Field field_pyInitial = findField(aoClass, "field_pyInitial");
                        Field field_nickname = findField(aoClass, "field_nickname");
                        Field field_province = findField(aoClass, "dhK");
                        Field field_city = findField(aoClass, "dhL");
                        Field field_signature = findField(aoClass, "signature");
                        Field field_sex = findField(aoClass, "sex");
                        Log.d("CrackMain",
                                "After onBackPressed " + " Username: " + field_username.get(dUUObj) +
                                        " Alias: " + field_alias.get(dUUObj) +
                                        " EncryptUsername: " + field_encryptUsername.get(dUUObj) +
                                        " PyInitial: " + field_pyInitial.get(dUUObj) +
                                        " Nickname: " + field_nickname.get(dUUObj) +
                                        " Province: " + field_province.get(dUUObj) +
                                        " City: " + field_city.get(dUUObj) +
                                        " Signature: " + field_signature.get(dUUObj) +
                                        " Sex: " + field_sex.get(dUUObj));
                    }
                });
                callMethod(param.thisObject, "onBackPressed");
            }
        });
    }
}
