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
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findField;
import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class crackmain implements IXposedHookLoadPackage {

    private XC_LoadPackage.LoadPackageParam lpparam = null;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("Begin", "Test Xposed--->" + loadPackageParam.packageName);
        if (loadPackageParam.packageName.contains("com.tencent.mm")) {
            Log.d("Begin", "Xposed Hooked--->" + loadPackageParam.packageName);
            findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String[] phoneNumber = {"13024661647",""};
                    lpparam=loadPackageParam;
                    crackWechat(phoneNumber[0]);
                    getPeerInfo();
                    onClickClearTextBtn();
//                    crackWechat(phoneNumber[1]);
//                    getPeerInfo();
                }
            });
        }
    }
    public void killXposedTest(){
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
    }

    public void crackWechat(final String phoneNumber) {
        //final String phoneNumber=phoneNumbers;
        //Log.d("CrackMain","phoneNumber: "+phoneNumber);
        //Xposed 检测
        killXposedTest();

        final Class<?> FTSAddFriendUIClass = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI", lpparam.classLoader);
        final Class<?> FTSAddFriendUI$5Class = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$5", lpparam.classLoader);
        final Class<?> mClass = findClass("com.tencent.mm.ah.m", lpparam.classLoader);

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


                //查找按钮 没一个是的
                /*Field meA=findField(param.thisObject.getClass(),"meA");
                Field meB=findField(param.thisObject.getClass(),"meB");
                Field mev=findField(param.thisObject.getClass(),"mev");
                Field mew=findField(param.thisObject.getClass(),"mew");
                Field mex=findField(param.thisObject.getClass(),"mex");
                Field mey=findField(param.thisObject.getClass(),"mey");
                Field mez=findField(param.thisObject.getClass(),"mez");
                Object meAobj=meA.get(param.thisObject);
                Log.d("CrackMain","meA "+meAobj.toString());
                Object meBobj=meB.get(param.thisObject);
                Log.d("CrackMain","meB "+meBobj.toString());
                Object mevobj=mev.get(param.thisObject);
                Log.d("CrackMain","mev "+mevobj.toString());
                Object mewobj=mew.get(param.thisObject);
                Log.d("CrackMain","mew "+mewobj.toString());
                Object mexobj=mex.get(param.thisObject);
                Log.d("CrackMain","mex "+mexobj.toString());
                Object meyobj=mey.get(param.thisObject);
                Log.d("CrackMain","meA "+meyobj.toString());
                Object mezobj=mez.get(param.thisObject);
                Log.d("CrackMain","mez "+mezobj.toString());

                Field meC =findField(param.thisObject.getClass(),"meC");
                Object meCobj=meC.get(param.thisObject);
                Log.d("CrackMain","meC "+meCobj.toString());

                Field meD=findField(param.thisObject.getClass(),"meD");
                Field meE=findField(param.thisObject.getClass(),"meE");
                Field meF=findField(param.thisObject.getClass(),"meF");
                Field meG=findField(param.thisObject.getClass(),"meG");
                Field meH=findField(param.thisObject.getClass(),"meH");

                Log.d("CrackMain","meD "+meD.get(param.thisObject).toString());
                Log.d("CrackMain","meE "+meE.get(param.thisObject).toString());
                Log.d("CrackMain","meF "+meF.get(param.thisObject).toString());
                Log.d("CrackMain","meG "+meG.get(param.thisObject).toString());
                Log.d("CrackMain","meH "+meH.get(param.thisObject).toString());*/

                //返回上一级页面
                callMethod(param.thisObject, "onBackPressed");
            }
        });
    }


//         /*
//         * 毫无用处，全局ao的函数只出始化一遍，然后值便传入field里了
//         * 失败
//         */
//        //建设性的方法，使用空的微信号哈哈哈哈哈我真的机智
//        //微信号
//            findAndHookMethod(aoClass, "ib", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d("ao","微信号为--->"+param.args[0]);
//                }
//            });
//            //备注
//            findAndHookMethod(aoClass, "ic", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    Log.d("ao","ic--->"+param.args[0]);
//                }
//                @Override
//                protected void afterHookedMethod(MethodHookParam param)throws Throwable{
//                    Field field_alias=aoClass.getDeclaredField("field_username");
//                    field_alias.setAccessible(true);
//                    String alias=field_alias.get(param.thisObject).toString();
//                    Log.d("ao","alias--->"+alias);
//                }
//            });
//
//        /*
//         * 全新的思路，反射获取ContactInfoUI的dUU的实例，然后反射获取dUU的field_username实例
//         * 成功
//         */
//        //在初始化ContactInfoUI view的时候 获取个人信息
//        //显然不能在这个时候 获取个人信息 因为onBackPressed函数退回上一级页面时候还会再调用一次
//        /*findAndHookMethod(ContactInfoUIClass, "initView", new XC_MethodHook() {
//            @Override
//            protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
//                //Log.d("CrackMain","调用了initView");
//            }
//        });*/

        public void getPeerInfo(){
            killXposedTest();

            final Class<?> aoClass = findClass("com.tencent.mm.g.c.ao", lpparam.classLoader);
            final Class<?> ContactInfoUIClass = findClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI", lpparam.classLoader);

            //监听ContactInfoUI的onCreate方法，启动完成就调用返回方法,hook onBackPressed 在调用返回的时候拿到联系人信息
            findAndHookMethod(ContactInfoUIClass, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("CrackMain","Hook onCreate() method and call onBackPressed() method");

                    findAndHookMethod(ContactInfoUIClass, "onBackPressed", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
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
                    });
                    callMethod(param.thisObject, "onBackPressed");
                }
            });
        }

        public void onClickClearTextBtn(){
            killXposedTest();

            final Class<?> FTSAddFriendUIClass = findClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI", lpparam.classLoader);

            findAndHookMethod(FTSAddFriendUIClass, "onClickClearTextBtn", View.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("CrackMain","调用了onClickTextBtn "+param.args[0]);
                }
            });

        }

}