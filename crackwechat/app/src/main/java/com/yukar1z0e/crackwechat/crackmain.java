package com.yukar1z0e.crackwechat;

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

public class crackmain implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam)throws Throwable{
        Log.d("Begin","Test Xposed--->"+lpparam.packageName);
        if (lpparam.packageName.contains("com.tencent.mm")){
            Log.d("Begin","Xposed Hooked--->"+lpparam.packageName);

            //勾取类
            final Class<?> FTSAddFriendUIClass=lpparam.classLoader.loadClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI");
            final Class<?> FTSAddFriendUI$5Class=lpparam.classLoader.loadClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$5");
            final Class<?> mClass=lpparam.classLoader.loadClass("com.tencent.mm.ah.m");
            final Class<?> aoClass=lpparam.classLoader.loadClass("com.tencent.mm.g.c.ao");
            final Class<?> ContactInfoUIClass=lpparam.classLoader.loadClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI");

            //微信号 但是一下会把所有人的打出来
            findAndHookMethod(aoClass, "ib", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","微信号为--->"+param.args[0]);
                }
            });

            //Hook FTSAddFriendUI.Mf
            findAndHookMethod(FTSAddFriendUIClass, "Mf", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d("FTSAddFriendUI","Mf--->"+param.args[0].toString());
                }
            });

            //Hook FTSAddFriendUI$5.onSceneEnd
            findAndHookMethod(FTSAddFriendUI$5Class, "onSceneEnd", int.class, int.class, String.class, mClass, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d("FTSAddFriendUI","r7--->"+param.args[0]+"->>>r8--->"+param.args[1]+"->>>r9--->"+param.args[2]+"->>>r10--->"+param.args[3].toString());
                }
            });

            //Hook FTSAssFriendUI.onCreate
            findAndHookMethod(FTSAddFriendUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d("FTSAddFriendUI","勾取到了onCreate");
                }
                @Override
                protected void afterHookedMethod(final MethodHookParam param)throws Throwable{
                    Log.d("FTSAddFriendUI","Try Call Mf Method");
                    Log.d("FTSAddFriendUI","--->"+XposedHelpers.findField(FTSAddFriendUIClass,"query").get(param.thisObject));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run(){
                            Log.d("FTSAddFriendUI","重载run");
                            setObjectField(param.thisObject,"query"," ");
                            callMethod(param.thisObject,"Mf"," ");
                        }
                    },2000);
                }
            });

            //建设性的方法，使用空的微信号哈哈哈哈哈我真的机智
            //微信号
            findAndHookMethod(aoClass, "ib", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","微信号为--->"+param.args[0]);
                }
            });
            //备注
            findAndHookMethod(aoClass, "ic", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","ic--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "m3if", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","m3if--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "ig", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","ig--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "ij", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","ij--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "ip", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","ip--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "iq", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","iq--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "ir", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","ir--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "is", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","is--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "it", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","it--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "iu", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","iu--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "iu", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","iu--->"+param.args[0]);
                }
            });

            findAndHookMethod(aoClass, "iv", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","iv--->"+param.args[0]);
                }
            });




        }
    }
}
