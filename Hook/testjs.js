console.log("[*] Starting script");

setTimeout(function () {
    Java.perform(function () {
        console.log("[*] enumerating classes...");
        Java.enumerateLoadedClasses({
            onMatch: function (_className) {
                console.log("[*] found instance of '" + _className + "'");
            },
            onComplete: function () {
                console.log("[*] class enuemration complete");
            }
        });
    });
});