// frida -U -f com.tencent.mm -l hook_com_tencent_mm_sdk_platformtools.js --no-pause

function trace(pattern) {
    var type = (pattern.toString().indexOf("!") === -1) ? "java" : "module";

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
                    //var className = aClass.match(/[L](.*);/)[1].replace(/\//g, ".");
                    var className = aClass.match(/[L]?(.*);?/)[1].replace(/\//g, ".");
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
            parsedMethods.push(method.toString().replace(targetClass + ".", "TOKEN").match(/\sTOKEN(.*)\(/)[1]);
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
    if (delim === -1) return;

    var targetClass = targetClassMethod.slice(0, delim)
    var targetMethod = targetClassMethod.slice(delim + 1, targetClassMethod.length)

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
            }

            // print retval
            var retval = this[targetMethod].apply(this, arguments); // rare crash (Frida bug?)
            console.log("\nretval: " + retval);
            console.warn("\n*** exiting " + targetClassMethod);
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
            // if (filename.indexOf("XYZ") === -1 && filename.indexOf("ZYX") === -1) // exclusion list
            // if (filename.indexOf("my.interesting.file") !== -1) // inclusion list
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

        console.log("first entering selector")
        trace("com.tencent.mm.sdk.platformtools");
        //trace("exports:*!open*");
        //trace("exports:*!write*");
        //trace("exports:*!malloc*");
        //trace("exports:*!free*");

    });
}, 0);
