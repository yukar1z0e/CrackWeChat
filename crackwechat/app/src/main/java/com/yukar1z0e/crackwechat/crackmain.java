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

            //Xposed 检测
            XposedHelpers.findAndHookMethod("com.tencent.mm.app.t", lpparam.classLoader, "a", StackTraceElement[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    if ((Boolean) param.getResult()) {
                        Log.d("Begin","----检测到xposed");
                        param.setResult(false);
                    }
                }
            });

            //勾取类
            final Class<?> FTSAddFriendUIClass=lpparam.classLoader.loadClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI");
            final Class<?> FTSAddFriendUI$5Class=lpparam.classLoader.loadClass("com.tencent.mm.plugin.fts.ui.FTSAddFriendUI$5");
            final Class<?> mClass=lpparam.classLoader.loadClass("com.tencent.mm.ah.m");
            final Class<?> aoClass=lpparam.classLoader.loadClass("com.tencent.mm.g.c.ao");
            final Class<?> ContactInfoUIClass=lpparam.classLoader.loadClass("com.tencent.mm.plugin.profile.ui.ContactInfoUI");



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


                            /*//获取个人信息
                            findAndHookMethod(ContactInfoUIClass, "initView", new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(final MethodHookParam param)throws Throwable{
                                    Field dUUField=findField(param.thisObject.getClass(),"dUU");
                                    //Log.d("initView","dUU--->"+dUUField.getName()+"--->type"+dUUField.getType());
                                    Object dUUObj=dUUField.get(param.thisObject);
                                    //Log.d("initView","Object duu--->"+dUUObj.toString());

                                    //Username（微信唯一值）
                                    Field field_username=findField(aoClass,"field_username");
                                    Log.d("FTSAddFriendUI/initView","username--->"+field_username.get(dUUObj));
                                    //Alias（微信号 wxid_/自己修改的）
                                    Field field_alias=findField(aoClass,"field_alias");
                                    Log.d("FTSAddFriendUI/initView","alias--->"+field_alias.get(dUUObj));
                                    //加密的Username
                                    Field field_encryptUsername=findField(aoClass,"field_encryptUsername");
                                    Log.d("FTSAddFriendUI/initView","encryptUsername--->"+field_encryptUsername.get(dUUObj));
                                    //wxid 解密版
                                    Field field_pyInitial=findField(aoClass,"field_pyInitial");
                                    Log.d("FTSAddFriendUI/initView","pyInitial--->"+field_pyInitial.get(dUUObj));
                                    //昵称
                                    Field field_nickname=findField(aoClass,"field_nickname");
                                    Log.d("FTSAddFriendUI/initView","nickname--->"+field_nickname.get(dUUObj));
                                    //地址
                                    Field field_province=findField(aoClass,"dhK");
                                    Field field_city=findField(aoClass,"dhL");
                                    Log.d("FTSAddFriendUI/initView","province--->"+field_province.get(dUUObj)+" city--->"+field_city.get(dUUObj));
                                    //个性签名
                                    Field field_signature=findField(aoClass,"signature");
                                    Log.d("FTSAddFriendUI/initView","signature--->"+field_signature.get(dUUObj));
                                    //性别 0:没写 1：男 2：女
                                    Field field_sex=findField(aoClass,"sex");
                                    Log.d("FTSAddFriendUI/initView","sex--->"+field_sex.get(dUUObj));

                                    //无用信息 dic、field_conRemark、field_descWordingId、field_domainList、field_openImAppid、dhB...还有一大帮不想试了
                                    //测试模板
                                    //Field demo=findField(aoClass,"dhB");
                                    //Log.d("FTSAddFriendUI/initView","tmp--->"+demo.get(dUUObj));

                                    //返回上一级页面
                                    Log.d("FTSAddFriendUI/initView", "prepare to destory");
                                    callMethod(param.thisObject, "onBackPressed");
                                }
                            });
*/

                            //返回上一级页面
                            callMethod(param.thisObject,"onBackPressed");

                        }
                    },2000);

                }
            });



            /*
             * 毫无用处，全局ao的函数只出始化一遍，然后值便传入field里了
            * 失败
            */
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
                @Override
                protected void afterHookedMethod(MethodHookParam param)throws Throwable{
                    Field field_alias=aoClass.getDeclaredField("field_username");
                    field_alias.setAccessible(true);
                    String alias=field_alias.get(param.thisObject).toString();
                    Log.d("ao","alias--->"+alias);
                }
            });

            /*
            * 全新的思路，反射获取ContactInfoUI的dUU的实例，然后反射获取dUU的field_username实例
            * 成功
             */
            //在初始化view的时候 获取个人信息
            findAndHookMethod(ContactInfoUIClass, "initView", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(final MethodHookParam param)throws Throwable{
                    Field dUUField=findField(param.thisObject.getClass(),"dUU");
                    //Log.d("initView","dUU--->"+dUUField.getName()+"--->type"+dUUField.getType());
                    Object dUUObj=dUUField.get(param.thisObject);
                    //Log.d("initView","Object duu--->"+dUUObj.toString());

                    //Username（微信唯一值）
                    Field field_username=findField(aoClass,"field_username");
                    Log.d("initView","username--->"+field_username.get(dUUObj));
                    //Alias（微信号 wxid_/自己修改的）
                    Field field_alias=findField(aoClass,"field_alias");
                    Log.d("initView","alias--->"+field_alias.get(dUUObj));
                    //加密的Username
                    Field field_encryptUsername=findField(aoClass,"field_encryptUsername");
                    Log.d("initView","encryptUsername--->"+field_encryptUsername.get(dUUObj));
                    //wxid 解密版
                    Field field_pyInitial=findField(aoClass,"field_pyInitial");
                    Log.d("initView","pyInitial--->"+field_pyInitial.get(dUUObj));
                    //昵称
                    Field field_nickname=findField(aoClass,"field_nickname");
                    Log.d("initView","nickname--->"+field_nickname.get(dUUObj));
                    //地址
                    Field field_province=findField(aoClass,"dhK");
                    Field field_city=findField(aoClass,"dhL");
                    Log.d("initView","province--->"+field_province.get(dUUObj)+" city--->"+field_city.get(dUUObj));
                    //个性签名
                    Field field_signature=findField(aoClass,"signature");
                    Log.d("initView","signature--->"+field_signature.get(dUUObj));
                    //性别 0:没写 1：男 2：女
                    Field field_sex=findField(aoClass,"sex");
                    Log.d("initView","sex--->"+field_sex.get(dUUObj));

                    //无用信息 dic、field_conRemark、field_descWordingId、field_domainList、field_openImAppid、dhB...还有一大帮不想试了
                    //测试模板
                    //Field demo=findField(aoClass,"dhB");
                    //Log.d("initView","tmp--->"+demo.get(dUUObj));

                    //返回页面
                    Log.d("initView", "prepare to destory");
                    callMethod(param.thisObject, "onBackPressed");
                }
            });

        }
    }
}
