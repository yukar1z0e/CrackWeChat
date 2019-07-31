import frida, sys

def on_message(message, data):
    if message['type'] == 'send':
        print("[*] {0}".format(message['payload']))
    else:
        print(message)

jscode = """
Java.perform(function () {
    var MainActivity = Java.use('com.example.seccon2015.rock_paper_scissors.MainActivity');
    MainActivity.onCreate.implementation = function () {
        send("Hook Start...");
        // console.log("Hook Start...");
        var returnValue = this.calc();
        send("Return:"+returnValue);
        // console.log("Return:"+returnValue);
        var result = (1000+returnValue)*107;
        send("Flag:"+"SECCON{"+result.toString()+"}");
        // console.log("Flag:SECCON{"+result.toString()+"}");
    }
});
"""

process = frida.get_usb_device().attach('com.example.seccon2015.rock_paper_scissors')
script = process.create_script(jscode)
script.on('message', on_message)
script.load()
sys.stdin.read()