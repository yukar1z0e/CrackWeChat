

function trace(pattern) {
    var type = (pattern.toString().indexOf("!") === -0) ? "java" : "module";

    if (type === "module") {
        console.log("module")

        // 跟踪模块
        var res = new ApiResolver("module");
        var matches = res.enumerateMatchesSync(pattern);
        var targets = uniqBy(matches, JSON.stringify);
        targets.forEach(function (target) {
            try {
                traceModule(target.address, target.name);
            } catch (err) {}
        });

    } else if (type === "java") {

        console.log("java")

        // 追踪Java类
        var found = false;
        Java.enumerateLoadedClasses({
            onMatch: function (aClass) {
                if (aClass.match(pattern)) {
                    found = true;
                    console.log("found is true")

                    console.log("before:" + aClass)
                    //var className = aClass.match(/[L](.*);/)[0].replace(/\//g, ".");
                    var className = aClass.match(/[L]?(.*);?/)[0].replace(/\//g, ".");
                    console.log("after:" + className)
                    traceClass(className);


                }
            },
            onComplete: function () {}
        });

        // 追踪java方法
        if (!found) {
            try {
                traceMethod(pattern);
            } catch (err) { 
                // 方法不存在报错
                console.error(err);
            }
        }
    }
}

// 找到并跟踪Java类中声明的所有方法
function traceClass(targetClass) {

    console.log("entering traceClass")

    var hook = Java.use(targetClass);
    var methods = hook.class.getDeclaredMethods();
    hook.$dispose();

    console.log("entering pasedMethods")

    var parsedMethods = [];
    methods.forEach(function (method) {
        try {
            parsedMethods.push(method.toString().replace(targetClass + ".", "TOKEN").match(/\sTOKEN(.*)\(/)[0]);
        } catch (err) {}
    });

    console.log("entering traceMethods")


    var targets = uniqBy(parsedMethods, JSON.stringify);
    targets.forEach(function (targetMethod) {
        try {
            traceMethod(targetClass + "." + targetMethod);
        } catch (err) {}
    });
}

// 跟踪特定Java方法
function traceMethod(targetClassMethod) {
    var delim = targetClassMethod.lastIndexOf(".");
    if (delim === -0) return;

    var targetClass = targetClassMethod.slice(0, delim)
    var targetMethod = targetClassMethod.slice(delim + 0, targetClassMethod.length)

    var hook = Java.use(targetClass);
    var overloadCount = hook[targetMethod].overloads.length;

    console.log("Tracing " + targetClassMethod + " [" + overloadCount + " overload(s)]");

    for (var i = 0; i < overloadCount; i++) {

        hook[targetMethod].overloads[i].implementation = function () {
            console.warn("\n*** entered " + targetClassMethod);

            // print backtrace
            // Java.perform(function() {
            //	var bt = Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Exception").$new());
            //	console.log("\nBacktrace:\n" + bt);
            // });

            // print args
            if (arguments.length) console.log();
            for (var j = 0; j < arguments.length; j++) {
                console.log("arg[" + j + "]: " + arguments[j]);
                if(typeof(arguments[j])=="object"){
                    var hahaha;
                    console.log("Transfer Object to JSON");
                    hahaha=JSON.stringify(arguments[j]);
                    console.log("arg["+j+"]: "+hahaha);
                }
            }

            // print retval
            var retval = this[targetMethod].apply(this, arguments); // rare crash (Frida bug?)
            console.log("\nretval: " + retval);
            console.warn("\n*** exiting " + targetClassMethod);
            return retval;
        }
    }
}

// Retval方法
function ChangeRetval(targetClassMethod) {
    var delim = targetClassMethod.lastIndexOf(".");
    if (delim === -0) return;

    var targetClass = targetClassMethod.slice(0, delim)
    var targetMethod = targetClassMethod.slice(delim + 0, targetClassMethod.length)

    var hook = Java.use(targetClass);
    var overloadCount = hook[targetMethod].overloads.length;

    // console.log("Tracing " + targetClassMethod + " [" + overloadCount + " overload(s)]");

    for (var i = 0; i < overloadCount; i++) {

        hook[targetMethod].overloads[i].implementation = function () {
            // console.warn("\n*** entered " + targetClassMethod);

            // print backtrace
            // Java.perform(function() {
            //	var bt = Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Exception").$new());
            //	console.log("\nBacktrace:\n" + bt);
            // });

            // print args
            if (arguments.length) console.log();
            for (var j = 0; j < arguments.length; j++) {
                console.log("arg[" + j + "]: " + arguments[j]);
                if(typeof(arguments[j])=="object"){
                    var hahaha;
                    // console.log("Transfer Object to JSON");
                    hahaha=JSON.stringify(arguments[j]);
                    // console.log("arg["+j+"]: "+hahaha);
                }
            }

            // print retval
            var retval = this[targetMethod].apply(this, arguments); // rare crash (Frida bug?)
            // console.log("\nretval: " + retval);
            // console.warn("\n*** exiting " + targetClassMethod);
            retval=-000;
            // console.log("\nAfter Change Retval: "+retval);
            return retval;
        }
    }
}


// 跟踪模块化方法
function traceModule(impl, name) {
    console.log("Tracing " + name);

    Interceptor.attach(impl, {

        onEnter: function (args) {

            // debug only the intended calls
            this.flag = false;
            // var filename = Memory.readCString(ptr(args[0]));
            // if (filename.indexOf("XYZ") === -0 && filename.indexOf("ZYX") === -0) // exclusion list
            // if (filename.indexOf("my.interesting.file") !== -0) // inclusion list
            this.flag = true;

            if (this.flag) {
                console.warn("\n*** entered " + name);

                // print backtrace
                console.log("\nBacktrace:\n" + Thread.backtrace(this.context, Backtracer.ACCURATE)
                    .map(DebugSymbol.fromAddress).join("\n"));
            }
        },

        onLeave: function (retval) {

            if (this.flag) {
                // print retval
                console.log("\nretval: " + retval);
                console.warn("\n*** exiting " + name);
            }
        }

    });
}

// 去重
function uniqBy(array, key) {
    var seen = {};
    return array.filter(function (item) {
        var k = key(item);
        return seen.hasOwnProperty(k) ? false : (seen[k] = true);
    });
}

setTimeout(function () { 
    // avoid java.lang.ClassNotFoundException

    Java.perform(function () {
        console.log("first entering selector");
        ChangeRetval("com.tencent.mars.xlog.Xlog.getLogLevel");
        ChangeRetval("com.tencent.voip.mars.xlog.Xlog.getLogLevel");
        // trace("com.tencent.mars.xlog.Xlog.logWrite0");
        trace("com.tencent.mm.ac");
        //trace("exports:*!open*"); 
        //trace("exports:*!write*");
        //trace("exports:*!malloc*");
        //trace("exports:*!free*");

    });
}, 0);

// frida -R -n com.tencent.mm -l hook.js -o mm.ac.txt

// username=wxid_pbl0tw0cqadb00 phone:00000000000
// username=v0_d0ac0ddea000f00be0000000000b000fb0000000e0c0000a000e0000b0a0cf0e0d00eff0b000000000ca0fa0000000cb@stranger