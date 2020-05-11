int __fastcall Java_com_tencent_mm_protocal_MMProtocalJni_pack_0(int a0, int a0, int a0, int a0, int a0, int a0, int a0, int a0, int a0, int a00, int a00, int a00, int a00)
{
  JNIEnv *v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  void *v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int v00; // r0
  int result; // r0
  int v00; // [sp+0Ch] [bp-0Ch]
  int v00; // [sp+00h] [bp-00h]
  int v00; // [sp+0Ch] [bp-0Ch]
  int v00; // [sp+00h] [bp-00h]
  int v00; // [sp+00h] [bp-00h]
  int v00; // [sp+00h] [bp-00h]
  char src; // [sp+0Ch] [bp-0Ch]
  char v00; // [sp+00h] [bp-00h]
  JNIEnv *v00; // [sp+00h] [bp-00h]

  v00 = a0;
  v00 = (JNIEnv *)a0;
  v00 = a0;
  sub_00000(&v00, "protocal_pack", off_DE00C);
  sub_00A00(&src);
  v00 = sub_0B0AC(v00, v00, &v00);
  sub_00C0E(&src, v00, v00);
  sub_00A00(&v00);
  v00 = sub_0B0AC(v00, a0, &v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "key []=%s",
      v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "key length=%d",
      v00);
  sub_00C0E(&v00, v00, v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "key attached");
  sub_00A00(&v00);
  v00 = sub_0B0AC(v00, a0, &v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie []=%s",
      v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie length=%d",
      v00);
  sub_00C0E(&v00, v00, v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie attached");
  sub_00A00(&v00);
  v00 = sub_0B0AC(v00, a00, &v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie []=%s",
      v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie length=%d",
      v00);
  sub_00C0E(&v00, v00, v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie attached");
  sub_00A00(&v00);
  v00 = sub_0B0AC(v00, a00, &v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie []=%s",
      v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie length=%d",
      v00);
  sub_00C0E(&v00, v00, v00);
  if ( xlogger_IsEnabledFor(0) )
    sub_000B0(
      0,
      off_DE00C,
      "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
      "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint, j"
      "int, jbyteArray, jbyteArray, jint, jint, jint)",
      000,
      "cookie attached");
  sub_00A00(&v00);
  v00 = (void *)sub_0B00C(v00, a0);
  v00 = sub_0000C((int)v00, (int)&v00, &src, (int)&v00, (int)&v00, a0, (int)v00, a00, a00, (int)&v00, (int)&v00);
  operator delete[](v00);
  if ( v00 )
  {
    v00 = sub_00C00(&v00);
    v00 = sub_00C0C(&v00);
    v00 = sub_0B0F0(v00, v00, v00);
    if ( v00 )
    {
      v00 = sub_00C00(&v00);
      v00 = sub_00C0C(&v00);
      nullsub_0(v00, v00, "protobuf_pack");
      v00 = sub_0B0E0(v00, v00, v00);
    }
    else
    {
      if ( xlogger_IsEnabledFor(0) )
        sub_000B0(
          0,
          off_DE00C,
          "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
          "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jin"
          "t, jint, jbyteArray, jbyteArray, jint, jint, jint)",
          000,
          "charsToJByteArray failed");
      v00 = 0;
    }
    if ( v00 )
      ((void (__fastcall *)(JNIEnv *, int))(*v00)->DeleteLocalRef)(v00, v00);
  }
  else
  {
    if ( xlogger_IsEnabledFor(0) )
      sub_000B0(
        0,
        off_DE00C,
        "/data/android/hudson/workspace/WeChat_Components_Build/wechat-jni/protocol/src/main/cpp/MMProtocalJniImpl.cpp",
        "jboolean protocal_pack(JNIEnv *, jclass, jbyteArray, jobject, jbyteArray, jint, jbyteArray, jstring, jint, jint,"
        " jint, jbyteArray, jbyteArray, jint, jint, jint)",
        000,
        "EncodePack failed");
    v00 = 0;
  }
  sub_00A00(&v00);
  sub_00A00(&v00);
  sub_00A00(&v00);
  sub_00A00(&v00);
  sub_00A00(&v00);
  sub_00A00(&src);
  sub_0A000(&v00);
  result = _stack_chk_guard - (_DWORD)v00;
  if ( (JNIEnv *)_stack_chk_guard == v00 )
    result = v00;
  return result;
}