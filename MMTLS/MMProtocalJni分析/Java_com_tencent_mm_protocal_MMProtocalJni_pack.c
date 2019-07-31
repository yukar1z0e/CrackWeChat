int __fastcall Java_com_tencent_mm_protocal_MMProtocalJni_pack_0(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13)
{
  JNIEnv *v13; // r8
  int v14; // r5
  int v15; // r0
  int v16; // r5
  int v17; // r5
  int v18; // r6
  int v19; // r6
  void *v20; // r4
  int v21; // r5
  int v22; // r5
  int v23; // r0
  int v24; // r6
  int v25; // r5
  int v26; // r0
  int v27; // r5
  int result; // r0
  int v29; // [sp+2Ch] [bp-4Ch]
  int v30; // [sp+38h] [bp-40h]
  int v31; // [sp+3Ch] [bp-3Ch]
  int v32; // [sp+40h] [bp-38h]
  int v33; // [sp+44h] [bp-34h]
  int v34; // [sp+48h] [bp-30h]
  char src; // [sp+4Ch] [bp-2Ch]
  char v36; // [sp+50h] [bp-28h]
  JNIEnv *v37; // [sp+58h] [bp-20h]

  v29 = a4;
  v13 = (JNIEnv *)a1;
  v14 = a3;
  sub_19844(&v36, "protocal_pack", off_DE01C);
  sub_22A58(&src);
  v15 = sub_2B4AC(v13, v14, &v34);
  sub_22C5E(&src, v15, v34);
  sub_22A58(&v34);
  v16 = sub_2B4AC(v13, a5, &v33);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      111,
      "key []=%s",
      v16);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      112,
      "key length=%d",
      v33);
  sub_22C5E(&v34, v16, v33);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      114,
      "key attached");
  sub_22A58(&v33);
  v17 = sub_2B4AC(v13, a7, &v32);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      121,
      "cookie []=%s",
      v17);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      122,
      "cookie length=%d",
      v32);
  sub_22C5E(&v33, v17, v32);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      124,
      "cookie attached");
  sub_22A58(&v32);
  v18 = sub_2B4AC(v13, a12, &v31);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      131,
      "cookie []=%s",
      v18);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      132,
      "cookie length=%d",
      v31);
  sub_22C5E(&v32, v18, v31);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      134,
      "cookie attached");
  sub_22A58(&v31);
  v19 = sub_2B4AC(v13, a13, &v30);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      141,
      "cookie []=%s",
      v19);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      142,
      "cookie length=%d",
      v30);
  sub_22C5E(&v31, v19, v30);
  if ( xlogger_IsEnabledFor(0) )
    sub_281B0(
      0,
      off_DE01C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      144,
      "cookie attached");
  sub_22A58(&v30);
  v20 = (void *)sub_2B38C(v13, a8);
  v21 = sub_1927C((int)v13, (int)&v30, &src, (int)&v34, (int)&v33, a9, (int)v20, a10, a11, (int)&v32, (int)&v31);
  operator delete[](v20);
  if ( v21 )
  {
    v22 = sub_22C32(&v30);
    v23 = sub_22C2C(&v30);
    v24 = sub_2B3F0(v13, v22, v23);
    if ( v24 )
    {
      v25 = sub_22C32(&v30);
      v26 = sub_22C2C(&v30);
      nullsub_1(v25, v26, "protobuf_pack");
      v27 = sub_2B5E0(v13, v29, v24);
    }
    else
    {
      if ( xlogger_IsEnabledFor(4) )
        sub_281B0(
          4,
          off_DE01C,
          "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
          "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jin"
          "t, jint, jbyteArray, jbyteArray, jint, jint, jint)",
          156,
          "charsToJByteArray failed");
      v27 = 0;
    }
    if ( v13 )
      ((void (__fastcall *)(JNIEnv *, int))(*v13)->DeleteLocalRef)(v13, v24);
  }
  else
  {
    if ( xlogger_IsEnabledFor(4) )
      sub_281B0(
        4,
        off_DE01C,
        "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
        "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint,"
        " jint, jbyteArray, jbyteArray, jint, jint, jint)",
        153,
        "EncodePack failed");
    v27 = 0;
  }
  sub_22A70(&v30);
  sub_22A70(&v31);
  sub_22A70(&v32);
  sub_22A70(&v33);
  sub_22A70(&v34);
  sub_22A70(&src);
  sub_1A010(&v36);
  result = _stack_chk_guard - (_DWORD)v37;
  if ( (JNIEnv *)_stack_chk_guard == v37 )
    result = v27;
  return result;
}