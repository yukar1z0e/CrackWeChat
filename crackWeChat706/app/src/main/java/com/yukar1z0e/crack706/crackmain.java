package com.yukar0z0e.crack000;

import android.app.Application;
import android.content.Context;
import android.os.FileObserver;
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

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.findFieldIfExists;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class crackmain implements IXposedHookLoadPackage {

    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.contains("com.tencent")) {
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String[] phoneNumber = {" ", " "};
                    lpparam = loadPackageParam;

                    killXposedTest();
//                    getPhoneNumber();
//                    getInfo();
//                    isExist();
//                    backContactInfoUI();
                }
            });
        }
    }

    public void killAppMethodBeat(){
        final Class<?> AppMethodBeatClass=findClass("com.tencent.matrix.trace.core.AppMethodBeat",lpparam.classLoader);
        findAndHookMethod(AppMethodBeatClass, "i", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0]=000000;
            }
        });
        findAndHookMethod(AppMethodBeatClass, "o", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0]=000000;
            }
        });
    }

    //解决xposed检测
    public void killXposedTest() {
        killAppMethodBeat();
        //Xposed 检测
        findAndHookMethod("com.tencent.mm.app.x",lpparam.classLoader,"b", StackTraceElement[].class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if ((Boolean) param.getResult()) {
                    Log.d("000","000");
                    param.setResult(false);
                }
            }
        });
    }

    //获取手机号
    public void getPhoneNumber(){
        killXposedTest();

        final Class<?> FTSAddFriendUIClass = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI", lpparam.classLoader);

        findAndHookMethod(FTSAddFriendUIClass, "Qz", String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("CrackMain","PhoneNumber: "+param.args[0]);
            }
        });
    }

    //获取搜索结果
    public void getInfo() {
        killXposedTest();

        final Class<?> aqClass = findClass("com.tencent.mm.g.c.aq", lpparam.classLoader);
        final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);

        //监听ContactInfoUI的onCreate方法，启动完成就调用返回方法,hook onBackPressed 在调用返回的时候拿到联系人信息
        findAndHookMethod(ContactInfoUIClass, "onBackPressed", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
                /* *
                 * contactField: contact的反射
                 * contactObj： contact的实例
                 * Username（微信唯一值）
                 * Alias: 微信号 wxid_/自己修改的）
                 * EncryptUsername: 加密的Username
                 * pyInitial: wxid 解密版
                 * Nickname: 昵称
                 * dhK、dhL： 省、市（地址）
                 * Signature： 个性签名
                 * Sex： 性别 0:没写 0：男 0：女
                 * */

//                Field contactField = findFieldIfExists(param.thisObject.getClass(), "contact");
                Object contactObj =getObjectField(param.thisObject,"contact");
                Field field_username = findField(aqClass, "field_username");
                Field field_alias = findField(aqClass, "field_alias");
                Field field_pyInitial = findField(aqClass, "field_pyInitial");
                Field field_nickname = findField(aqClass, "field_nickname");
                Field province = findField(aqClass, "province");
                Field field_signature = findField(aqClass, "signature");


                Log.d("CrackMain",
                        "Username: " + field_username.get(contactObj) +
                                ";Alias: " + field_alias.get(contactObj) +
                                ";PyInitial: " + field_pyInitial.get(contactObj) +
                                ";Nickname: " + field_nickname.get(contactObj) +
                                ";Province: " + province.get(contactObj) +
                                ";Signature: " + field_signature.get(contactObj) +
                                ";END");
            }
        });
    }

    //是否有结果
    public void isExist(){
        killXposedTest();
        final Class<?> FTSAddFriendUI$0Class = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$0", lpparam.classLoader);
        final Class<?> mClass = findClass("com.tencent.mm.ak.m", lpparam.classLoader);
        //Hook FTSAddFriendUI$0.onSceneEnd
        findAndHookMethod(FTSAddFriendUI$0Class, "onSceneEnd", int.class, int.class, String.class, mClass, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Log.d("CrackMain", param.args[0].toString());
            }
        });
    }

    //ContactInfoUI界面返回
    public void backContactInfoUI() {
        final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);
        findAndHookMethod(ContactInfoUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        callMethod(param.thisObject, "onBackPressed");
                    }
                });
            }
        });
    }

}