setImmediat(function(){
    Java.perform(function(){
        //start hook
        console.log("[*] Starting script");
    
    
        var p_libMMProtocalJni = Module.findBaseAddress("libMMProtocalJni.so");
        var p_libwechatmm = Module.findBaseAddress("libwechatmm.so");
        
        send("libMMProtocalJni.so          @ " + p_libMMProtocalJni.toString());
        send("libwechatmm.so          @ " + p_libwechatmm.toString());
        
        var offset_makeheader = 0x000159E1;
        var ptrmakeheader  = p_libMMProtocalJni.add(offset_makeheader);
        send("ptrmakeheader @ " +ptrmakeheader.toString());
        var outbufferptr = 0;
        var outbufsizeptr = 0;
        
        var offset_Adler_32 = 0x000239E5;
        var p_Adler_32  = p_libMMProtocalJni.add(offset_Adler_32);
        send("p_Adler_32 @ " +p_Adler_32.toString());
        
        var offset_md5update = 0x2DC51;
        var p_md5update  = p_libMMProtocalJni.add(offset_md5update);
        send("p_Adler_32 @ " +p_md5update.toString());
        
        var offset_md5fina = 0x2DCEF;
        var p_md5fina  = p_libMMProtocalJni.add(offset_md5fina);
        send("p_md5fina @ " +p_md5fina.toString());
        
        var md5inbuffer = 0 ;
        Interceptor.attach(p_md5fina, {
            onEnter: function(args) {
                console.log("[*] p_md5fina onEnter");
                md5inbuffer = args[0];
               
            },
            onLeave: function (retval) {
                send(hexdump(md5inbuffer, { length: 0x10, ansi: true }));
                send("p_md5fina onLeave()");
            }
        });
        
        Interceptor.attach(p_md5update, {
            onEnter: function(args) {
                console.log("[*] p_md5update onEnter");
                inbuffer = args[1];
                send(hexdump(inbuffer, { length: args[2].toInt32(), ansi: true }));
            },
            onLeave: function (retval) {
                send("p_md5update onLeave()");
            }
        });
        
         Interceptor.attach(p_Adler_32, {
            onEnter: function(args) {
                console.log("[*] offset_Adler_32 onEnter");
                buf = args[0];
                sesssionkey = args[1];
                protobuff = args[2];
                send("uin "+args[0].toInt32());
                send(hexdump(sesssionkey, { length: 0x10, ansi: true }));
                send(hexdump(protobuff, { length: args[3].toInt32(), ansi: true }));
            },
          
            onLeave: function (retval) {
                send("offset_Adler_32 onLeave()" +retval);
            }
        });
        
        var offset_packheaderandbody = 0x00014F71;
        var p_packheaderandbody  = p_libMMProtocalJni.add(offset_packheaderandbody);
        send("p_packheaderandbody @ " +p_packheaderandbody.toString());
        
        Interceptor.attach(p_packheaderandbody, {
            onEnter: function(args) {
                console.log("[*] p_packheaderandbody onEnter");
                buf = args[0];
                send(hexdump(buf, { length: 60, ansi: true }));
            }
        });
        
        Interceptor.attach(ptrmakeheader, {
            onEnter: function(args) {
                console.log("[*] makeheader onEnter");
                buf = args[0];
                outbufferptr = args[1];
                outbufsizeptr = args[2];
                send("a1024 "+args[3].toInt32());
                
                send(hexdump(buf, { length: 55, ansi: true }));
            },
          
            onLeave: function (retval) {
                outbufsize = Memory.readUInt(outbufsizeptr)
                send(hexdump(outbufferptr, { length: outbufsize, ansi: true }));   
                send("makeheader onLeave()" +retval);
            }
        });
         
        var offset_alder32Checksum = 0x1B1BD1;
        var p_offset_alder32Checksum  = p_libwechatmm.add(offset_alder32Checksum);
        send("p_offset_alder32Checksum @ " +p_offset_alder32Checksum.toString());
        
         Interceptor.attach(p_offset_alder32Checksum, {
            onEnter: function(args) {
                console.log("[*] p_offset_alder32Checksum onEnter");
                outbufferptr = args[1];
                send(hexdump(outbufferptr, { length: args[2].toInt32(), ansi: true }));
            },
          
            onLeave: function (retval) {
                send("p_offset_alder32Checksum onLeave()" +retval);
            }
        });


    })
})