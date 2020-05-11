// frida -U -f com.tencent.mm -l testhookwx.js --no-pause

// 微信号、微信名、地址、个性签名

// 包 com.tencent.p000mm.p000g.p000c.C0000ap.java

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

        console.log("first entering selector")
        // trace("com.tencent.mm.g.c.bo");
        trace("com.tencent.mm.g.c.bo.Hl");
        // trace("com.tencent.mm.g.c.bo.Hl");
        // trace("com.tencent.mm.g.c.bo.d");
        // trace("android.content.ContentValues.put");
        //trace("exports:*!open*");
        //trace("exports:*!write*");
        //trace("exports:*!malloc*");
        //trace("exports:*!free*");
    });
}, 0);


// frida -U -f com.tencent.mm -l testhookwx.js --no-pause


// // logs



// *** entered com.tencent.mm.g.c.ap.getProvince

// retval: åäº¬

// *** exiting com.tencent.mm.g.c.ap.getProvince



// *** entered com.tencent.mm.g.c.ap.getCity

// retval: ä¸å

// *** exiting com.tencent.mm.g.c.ap.getCity



// *** entered com.tencent.mm.g.c.ap.iW

// arg[0]: CN_Beijing_East

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iW



// *** entered com.tencent.mm.g.c.ap.iD

// arg[0]: wxidnw00rwngq00e00

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iD



// *** entered com.tencent.mm.g.c.ap.iC

// arg[0]: WXIDNW00RWNGQ00E00

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iC


// *** entered com.tencent.mm.g.c.ap.iH

// arg[0]: v0_0cf0cf000000c0f00cb0e00000c0a0000c00ab000f0f0f0000dd0a0cd0e0e00a0dd0000000000fcd0b0c0a0bbac000e0@stranger

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iH



// *** entered com.tencent.mm.g.c.ap.iQ

// arg[0]: ä¸è¦è®©å«äººæä¸æä»¬å¤ªè¿ã

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iQ



// *** entered com.tencent.mm.g.c.ap.iB

// arg[0]: phantom

// retval: undefined

// *** exiting com.tencent.mm.g.c.ap.iB
