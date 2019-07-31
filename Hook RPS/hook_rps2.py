# 修改cnt的值为1000

import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode = """

Java.perform(function () {

    var MainActivity = Java.use('com.example.seccon2015.rock_paper_scissors.MainActivity');

    //hook onClick方法，此处要注意的是onClick方法是传递了一个View参数v

    MainActivity.onClick.implementation = function (v) {

        send("Hook Start...");

        //调用onClick,模拟点击事件

        this.onClick(v);

        //修改参数

        this.n.value = 0;
        this.m.value = 2;
        this.cnt.value = 999;
        send("Success!")
    }
});

"""

process = frida.get_usb_device().attach('com.example.seccon2015.rock_paper_scissors')
script = process.create_script(jscode)
script.on('message', on_message)
script.load()
sys.stdin.read()
