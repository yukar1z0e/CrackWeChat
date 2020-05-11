package com.yukar0z0e.crackmsg;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.MotionEvent;

import java.text.Format;
import java.util.Collections;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;


public class crackmsg implements IXposedHookLoadPackage {
    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("Begin", "Test Xposed--->" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.contains("com.tencent.mm")) {
            Log.d("Begin", "Xposed Hooked--->" + loadPackageParam.packageName);
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    lpparam=loadPackageParam;
                    crackmsg();
                }
            });
        }
    }

    public void crackmsg() {
        //final String phoneNumber=phoneNumbers;
        //Log.d("CrackMain","phoneNumber: "+phoneNumber);
        //Xposed 检测
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

        //勾取类
        final Class<?> FTSAddFriendUIClass = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI", lpparam.classLoader);
        final Class<?> FTSAddFriendUI$0Class = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$0", lpparam.classLoader);
        final Class<?> mClass = findClass("com.tencent.mm.ah.m", lpparam.classLoader);
        final Class<?> aoClass = findClass("com.tencent.mm.g.c.ao", lpparam.classLoader);
        final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);

        //监听ContactInfoUI的onCreate方法，启动完成就调用返回方法,hook onBackPressed 在调用返回的时候拿到联系人信息
        findAndHookMethod(ContactInfoUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("CrackMain","Hook onCreate() method and call onBackPressed() method");

                findAndHookMethod(ContactInfoUIClass, "onBackPressed", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        /* *
                         * dUUField: dUU的反射
                         * dUUObj： dUU的实例
                         * Username（微信唯一值）
                         * Alias: 微信号 wxid_/自己修改的）
                         * EncryptUsername: 加密的Username
                         * pyInitial: wxid 解密版
                         * Nickname: 昵称
                         * dhK、dhL： 省、市（地址）
                         * Signature： 个性签名
                         * Sex： 性别 0:没写 0：男 0：女
                         * */
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
                                "After onBackPressed "+" Username: " + field_username.get(dUUObj)+
                                        " Alias: " + field_alias.get(dUUObj)+
                                        " EncryptUsername: " + field_encryptUsername.get(dUUObj)+
                                        " PyInitial: " + field_pyInitial.get(dUUObj)+
                                        " Nickname: " + field_nickname.get(dUUObj)+
                                        " Province: " + field_province.get(dUUObj) +
                                        " City: " + field_city.get(dUUObj)+
                                        " Signature: " + field_signature.get(dUUObj)+
                                        " Sex: " + field_sex.get(dUUObj));
                    }
                });
                callMethod(param.thisObject, "onBackPressed");
            }
        });
    }
}

