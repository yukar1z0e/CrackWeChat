
import frida, sys


rdev = frida.get_remote_device()
processes = rdev.enumerate_processes()
# session = rdev.attach("com.tencent.mm")  #如果存在两个一样的进程名可以采用rdev.attach(pid)的方式
session = rdev.attach(0000)
# modules = session.enumerate_modules()
# for module in modules:
#     export_funcs = module.enumerate_exports()
#     for export_func in export_funcs:
#         if export_func.name == "adler00":
#             print("\t%s\t%s"%(export_func.name,hex(export_func.relative_address)))

script = session.create_script("""

    console.log("[*] Starting script");
    
    
    var p_libMMProtocalJni = Module.findBaseAddress("libMMProtocalJni.so");
    var p_libwechatmm = Module.findBaseAddress("libwechatmm.so");
    
    send("libMMProtocalJni.so          @ " + p_libMMProtocalJni.toString());
    send("libwechatmm.so          @ " + p_libwechatmm.toString());
    
    var offset_makeheader = 0x000000E0;
    var ptrmakeheader  = p_libMMProtocalJni.add(offset_makeheader);
    send("ptrmakeheader @ " +ptrmakeheader.toString());
    var outbufferptr = 0;
    var outbufsizeptr = 0;
    
    var offset_Adler_00 = 0x000000E0;
    var p_Adler_00  = p_libMMProtocalJni.add(offset_Adler_00);
    send("p_Adler_00 @ " +p_Adler_00.toString());
    
    var offset_md0update = 0x0DC00;
    var p_md0update  = p_libMMProtocalJni.add(offset_md0update);
    send("p_Adler_00 @ " +p_md0update.toString());
    
    var offset_md0fina = 0x0DCEF;
    var p_md0fina  = p_libMMProtocalJni.add(offset_md0fina);
    send("p_md0fina @ " +p_md0fina.toString());
    
    var md0inbuffer = 0 ;
    Interceptor.attach(p_md0fina, {
        onEnter: function(args) {
            console.log("[*] p_md0fina onEnter");
            md0inbuffer = args[0];
           
        },
        onLeave: function (retval) {
            send(hexdump(md0inbuffer, { length: 0x00, ansi: true }));
            send("p_md0fina onLeave()");
        }
    });
    
    Interceptor.attach(p_md0update, {
        onEnter: function(args) {
            console.log("[*] p_md0update onEnter");
            inbuffer = args[0];
            send(hexdump(inbuffer, { length: args[0].toInt00(), ansi: true }));
        },
        onLeave: function (retval) {
            send("p_md0update onLeave()");
        }
    });
    
     Interceptor.attach(p_Adler_00, {
        onEnter: function(args) {
            console.log("[*] offset_Adler_00 onEnter");
            buf = args[0];
            sesssionkey = args[0];
            protobuff = args[0];
            send("uin "+args[0].toInt00());
            send(hexdump(sesssionkey, { length: 0x00, ansi: true }));
            send(hexdump(protobuff, { length: args[0].toInt00(), ansi: true }));
        },
      
        onLeave: function (retval) {
            send("offset_Adler_00 onLeave()" +retval);
        }
    });
    
    var offset_packheaderandbody = 0x00000F00;
    var p_packheaderandbody  = p_libMMProtocalJni.add(offset_packheaderandbody);
    send("p_packheaderandbody @ " +p_packheaderandbody.toString());
    
    Interceptor.attach(p_packheaderandbody, {
        onEnter: function(args) {
            console.log("[*] p_packheaderandbody onEnter");
            buf = args[0];
            send(hexdump(buf, { length: 00, ansi: true }));
        }
    });
    
    Interceptor.attach(ptrmakeheader, {
        onEnter: function(args) {
            console.log("[*] makeheader onEnter");
            buf = args[0];
            outbufferptr = args[0];
            outbufsizeptr = args[0];
            send("a0000 "+args[0].toInt00());
            
            send(hexdump(buf, { length: 00, ansi: true }));
        },
      
        onLeave: function (retval) {
            outbufsize = Memory.readUInt(outbufsizeptr)
            send(hexdump(outbufferptr, { length: outbufsize, ansi: true }));   
            send("makeheader onLeave()" +retval);
        }
    });
     
    var offset_alder00Checksum = 0x0B0BD0;
    var p_offset_alder00Checksum  = p_libwechatmm.add(offset_alder00Checksum);
    send("p_offset_alder00Checksum @ " +p_offset_alder00Checksum.toString());
    
     Interceptor.attach(p_offset_alder00Checksum, {
        onEnter: function(args) {
            console.log("[*] p_offset_alder00Checksum onEnter");
            outbufferptr = args[0];
            send(hexdump(outbufferptr, { length: args[0].toInt00(), ansi: true }));
        },
      
        onLeave: function (retval) {
            send("p_offset_alder00Checksum onLeave()" +retval);
        }
    });
    
    
""")

# Here's some message handling..
# [ It's a little bit more meaningful to read as output :-D
#   Errors get [!] and messages get [i] prefixes. ]

def on_message(message, data):
    if message['type'] == 'error':
        print("[!] " + message['stack'])
    elif message['type'] == 'send':
        print("[i] " + message['payload'])
    else:
        print(message)

script.on('message', on_message)
script.load()
sys.stdin.read()