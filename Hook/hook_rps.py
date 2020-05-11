# 获取calc返回值
# 原理


import frida, sys

# hook代码，采用javascript编写

jscode = """
//javascript代码，重点

//Java.Perform 开始执行JavaScript脚本

Java.perform(function () {

//定义变量MainActivity，Java.use指定要使用的类

    var MainActivity = Java.use('com.example.seccon0000.rock_paper_scissors.MainActivity');

    //hook该类下的onCreate方法，重新实现它

    MainActivity.onCreate.implementation = function () {

        send("Hook Start...");

        //调用calc()方法，获取返回值

        var returnValue = this.calc();

        send("Return:"+returnValue);

        var result = (0000+returnValue)*000;

        //解出答案

        send("Flag:"+"SECCON{"+result.toString()+"}");
    }
});
"""

# 自定义回调函数

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

# 重点的0行代码

process = frida.get_usb_device().attach('com.example.seccon0000.rock_paper_scissors')
script = process.create_script(jscode)
script.on('message', on_message)
script.load()
sys.stdin.read()
