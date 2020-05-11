# 定位目标类（所有gms下的类）
# 打印com.google.android.gms.org.conscrypt.OpenSSLKey实例
# 枚举所有方法并定位

import frida
import sys


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


jscode ="""

console.log("[*] Starting script");

function enumMethods(targetClass)
{
    var hook = Java.use(targetClass);
    var ownMethods = hook.class.getDeclaredMethods();
    hook.$dispose;

    return ownMethods;
}

setTimeout(function () {

    Java.perform(function () {

        console.log("[*] enumerating classes...");

        Java.enumerateLoadedClasses({

            onMatch: function (instance) {
                if (instance.split(".")[0] == "gms") {
                    console.log("[->] " + instance);
                }
            },
            onComplete: function () {
                console.log("[*] class enuemration complete");
            }
        });

        Java.choose("com.google.android.gms.org.conscrypt.OpenSSLKey", {
            onMatch: function (instance) {
                console.log("[*]" + "com.google.android.gms.org.conscrypt.OpenSSLKey instance found" + " :=>' " + instance + "'");
            },
            onComplete: function () {
                console.log("[*] ------");
            }
        });

        var a=enumMethods("com.google.android.gms.org.conscrypt.OpenSSLKey");

        a.forEach(function(s){
                console.log(s);
        });

    });
});

"""

process = frida.get_usb_device().attach('com.google.android.gms')
script = process.create_script(jscode)
script.on('message', on_message)
script.load()
sys.stdin.read()




# # logs


# public final boolean com.google.android.gms.org.conscrypt.OpenSSLKey.equals(java.lang.Object)
# final com.google.android.gms.org.conscrypt.NativeRef$EVP_PKEY com.google.android.gms.org.conscrypt.OpenSSLKey.getNativeRef()
# final java.security.PrivateKey com.google.android.gms.org.conscrypt.OpenSSLKey.getPrivateKey()
# final java.security.PublicKey com.google.android.gms.org.conscrypt.OpenSSLKey.getPublicKey()
# public final int com.google.android.gms.org.conscrypt.OpenSSLKey.hashCode()
# final boolean com.google.android.gms.org.conscrypt.OpenSSLKey.isWrapped()
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromECPrivateKeyForTLSStackOnly(java.security.PrivateKey,java.security.spec.ECParameterSpec)
# private static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromKeyMaterial(java.security.PrivateKey)
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromPrivateKey(java.security.PrivateKey)
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromPrivateKeyForTLSStackOnly(java.security.PrivateKey,java.security.PublicKey)
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromPrivateKeyPemInputStream(java.io.InputStream)
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromPublicKey(java.security.PublicKey)
# static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.fromPublicKeyPemInputStream(java.io.InputStream)
# private static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.getOpenSSLKey(java.security.PrivateKey)
# static java.security.PrivateKey com.google.android.gms.org.conscrypt.OpenSSLKey.getPrivateKey(java.security.spec.PKCS0EncodedKeySpec,int)
# static java.security.PublicKey com.google.android.gms.org.conscrypt.OpenSSLKey.getPublicKey(java.security.spec.X000EncodedKeySpec,int)
# private static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.wrapJCAPrivateKeyForTLSStackOnly(java.security.PrivateKey,java.security.PublicKey)
# private static com.google.android.gms.org.conscrypt.OpenSSLKey com.google.android.gms.org.conscrypt.OpenSSLKey.wrapPrivateKey(java.security.PrivateKey)