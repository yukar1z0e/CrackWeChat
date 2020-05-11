# 定位目标类（所有gms下的类）
# 打印com.google.android.gms.org.conscrypt.OpenSSLKey实例

import frida
import sys


def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)


jscode = """

    console.log("[*] Starting script");

    setTimeout(function (){

        Java.perform(function (){

            console.log("[*] enumerating classes...");

            Java.enumerateLoadedClasses({

                onMatch: function(instance){

                    if(instance.split(".")[0]=="gms"){

                        console.log("[->] "+instance);

                    }
                },

                onComplete: function(){

                    console.log("[*] class enuemration complete");

                }
            });

             Java.choose("com.google.android.gms.org.conscrypt.OpenSSLKey",
        {
            onMatch:function(instance)
            {
                console.log("[*]"+"com.google.android.gms.org.conscrypt.OpenSSLKey instance found"+" :=>' "+instance+"'");
            },
            onComplete:function()
            {
                console.log("[*] ------");
            }
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


# [*]com.google.android.gms.org.conscrypt.OpenSSLKey instance found :=>' com.google.android.gms.org.conscrypt.OpenSSLKey@a0000d00'
# [*]com.google.android.gms.org.conscrypt.OpenSSLKey instance found :=>' com.google.android.gms.org.conscrypt.OpenSSLKey@a00c00a0'
# [*]com.google.android.gms.org.conscrypt.OpenSSLKey instance found :=>' com.google.android.gms.org.conscrypt.OpenSSLKey@ae000d00'
# [*] ------