# 打印目标进程中加载的所有类

import frida, sys

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
                onMatch: function(_className){
                    console.log("[*] found instance of '"+_className+"'");
                    },
                onComplete: function(){
                    console.log("[*] class enuemration complete");
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