import frida
import sys

rdev=frida.get_remote_device()
session =rdev.attach("com.tencent.mm")

scr="""
Java.perform(function(){
    var SQL=Java.use("com.tencent.ecdb.database.SQLiteDatabase");
    var Set=Java.use("java.util.Set");
    var ContentValues= Java.use("android.content.ContentValues");

    SQL.insert.implementation=function(arg1,arg2,arg3){
        this.insert(arg1,arg2,arg3);
        console.log("[insert]->arg1:"+arg1+"\r arg2: "+arg2);
        
        var values=Java.cast(arg3,ContentValues);
        var sets=Java.cast(values.keySet(),Set);

        var arr=sets.toArray().toString().split(",");
        for(var i=0;i<arr.length;i++){
            console.log("[insert]-> key: "+arr[i]+" value: "+values.get(arr[i]));
        }
    }
})
"""

script=session.create_script(scr)
def on_message(message,data):
    print(message)

script.on("message",on_message)
script.load()
sys.stdin.read()