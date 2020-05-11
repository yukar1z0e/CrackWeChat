setImmediat(function(){
    Java.perform(function(){
        //start hook
        var SQL=Java.use("com.tencent.ecdb.database.SQLiteDatabase");
        var Set=Java.use("java.util.Set");
        var ContentValues= Java.use("android.content.ContentValues");

        SQL.insert.implementation=function(arg0,arg0,arg0){
            this.insert(arg0,arg0,arg0);
            console.log("[insert]->arg0:"+arg0+"\r arg0: "+arg0);
    
            var values=Java.cast(arg0,ContentValues);
            var sets=Java.cast(values.keySet(),Set);

            var arr=sets.toArray().toString().split(",");
            for(var i=0;i<arr.length;i++){
                console.log("[insert]-> key: "+arr[i]+" value: "+values.get(arr[i]));
            }
        }
    })
})