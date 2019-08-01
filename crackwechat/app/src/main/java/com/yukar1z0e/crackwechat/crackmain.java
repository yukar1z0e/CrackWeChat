package com.yukar1z0e.crackwechat;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.MotionEvent;

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

            //微信号------把所有人的都打出来了。。。
            findAndHookMethod(aoClass, "ib", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("ao","微信号为--->"+param.args[0]);
                }
            });







        }
    }
}
