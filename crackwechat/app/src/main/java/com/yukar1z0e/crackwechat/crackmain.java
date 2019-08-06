package com.yukar1z0e.crackwechat;

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

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class crackmain implements IXposedHookLoadPackage {

    private XC_LoadPackage.LoadPackageParam lpparam = null;
    public final String phoneNumber="";
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("Begin", "Test Xposed--->" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.contains("com.tencent.mm")) {
            Log.d("Begin", "Xposed Hooked--->" + loadPackageParam.packageName);
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String[] phoneNumbers = {"13024661647"};
                    lpparam=loadPackageParam;
                    crackWechat(phoneNumbers[0]);
//                    crackWechat(phoneNumbers[1]);
//                    crackWechat(phoneNumbers[2]);
                }
            });
        }
    }

    private void crackWechat(String phoneNumbers) {
        final String phoneNumber=phoneNumbers;
        //Log.d("CrackMain","phoneNumber: "+phoneNumber);
        //Xposed 检测
        XposedHelpers.findAndHookMethod("com.tencent.mm.app.t", lpparam.classLoader, "a", StackTraceElement[].class, new XC_MethodHook() {
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
        final Class<?> FTSAddFriendUI$5Class = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$5", lpparam.classLoader);
        final Class<?> mClass = findClass("com.tencent.mm.ah.m", lpparam.classLoader);
        final Class<?> aoClass = findClass("com.tencent.mm.g.c.ao", lpparam.classLoader);
        final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);


        //Hook FTSAddFriendUI.Mf
        /*findAndHookMethod(FTSAddFriendUIClass, "Mf", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                Log.d("CrackMain", "Mf--->" + param.args[0].toString());
            }
        });*/

        //Hook FTSAddFriendUI$5.onSceneEnd
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

        //Hook FTSAddFriendUI.onCreate
        findAndHookMethod(FTSAddFriendUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                //Log.d("CrackMain", "勾取到了onCreate");
            }

            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                //Log.d("CrackMain", "Try Call Mf Method");
                //Log.d("CrackMain", "--->" + XposedHelpers.findField(FTSAddFriendUIClass, "query").get(param.thisObject));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("CrackMain", "重载run--->查询的电话号码为 "+phoneNumber);
                        setObjectField(param.thisObject, "query", phoneNumber);
                        callMethod(param.thisObject, "Mf", phoneNumber);
                    }
                }, 2000);

                //返回上一级页面
                callMethod(param.thisObject, "onBackPressed");
            }
        });

        /*
         * 毫无用处，全局ao的函数只出始化一遍，然后值便传入field里了
         * 失败
         */
        //建设性的方法，使用空的微信号哈哈哈哈哈我真的机智
        //微信号
            /*findAndHookMethod(aoClass, "ib", String.class, new XC_MethodHook() {
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
                @Override
                protected void afterHookedMethod(MethodHookParam param)throws Throwable{
                    Field field_alias=aoClass.getDeclaredField("field_username");
                    field_alias.setAccessible(true);
                    String alias=field_alias.get(param.thisObject).toString();
                    Log.d("ao","alias--->"+alias);
                }
            });*/

        /*
         * 全新的思路，反射获取ContactInfoUI的dUU的实例，然后反射获取dUU的field_username实例
         * 成功
         */
        //在初始化ContactInfoUI view的时候 获取个人信息
        //显然不能在这个时候 获取个人信息 因为onBackPressed函数退回上一级页面时候还会再调用一次
        /*findAndHookMethod(ContactInfoUIClass, "initView", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                //Log.d("CrackMain","调用了initView");
            }
        });*/

        //监听ContactInfoUI的onCreate方法，启动完成就调用返回方法,hook onBackPressed 在调用返回的时候拿到联系人信息
        findAndHookMethod(ContactInfoUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Log.d("CrackMain","Hook onCreate() method and call onBackPressed() method");

                    findAndHookMethod(ContactInfoUIClass, "onBackPressed", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("CrackMain","Before onBackPressed");
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
                             * Sex： 性别 0:没写 1：男 2：女
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
                                    "Before onBackPressed "+" Username: " + field_username.get(dUUObj)+
                                            " Alias: " + field_alias.get(dUUObj)+
                                            " EncryptUsername: " + field_encryptUsername.get(dUUObj)+
                                            " PyInitial: " + field_pyInitial.get(dUUObj)+
                                            " Nickname: " + field_nickname.get(dUUObj)+
                                            " Province: " + field_province.get(dUUObj) +
                                            " City: " + field_city.get(dUUObj)+
                                            " Signature: " + field_signature.get(dUUObj)+
                                            " Sex: " + field_sex.get(dUUObj));
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.d("CrackMain","After onBackPressed");

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
                             * Sex： 性别 0:没写 1：男 2：女
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