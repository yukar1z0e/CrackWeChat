setImmediat(function(){
    Java.perform(function(){
        //start hook
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
})