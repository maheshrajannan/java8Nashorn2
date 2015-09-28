//INFO: Comment from here for execution in browser context.
//INFO:This is for execution in nashorn engine context only.
//INFO: in browser context, the implementation is provided by browser.
console = { 
    log: print,
    warn: print,
    error: print
};

//INFO: for browser window functions like timeout, below are java
//implementations in javascript.
//References:https://blogs.oracle.com/nashorn/entry/setinterval_and_settimeout_javascript_functions
//These are not necessary for implementation in browser context, as browser's Window object
//provides default implementations of time out.

var Platform = Java.type("javafx.application.Platform");
var Timer    = Java.type("java.util.Timer");

function setTimerRequest(handler, delay, interval, args) {
    handler = handler || function() {};
    delay = delay || 0;
    interval = interval || 0;

    var applyHandler = function() handler.apply(this, args);
    var runLater = function() Platform.runLater(applyHandler);

    var timer = new Timer("setTimerRequest", true);

    if (interval > 0) {
        timer.schedule(runLater, delay, interval);
    } else {
        timer.schedule(runLater, delay);
    }

    return timer;
}

function clearTimerRequest(timer) {
    timer.cancel();
}

function setInterval() {
    var args = Array.prototype.slice.call(arguments);
    var handler = args.shift();
    var ms = args.shift();

    return setTimerRequest(handler, ms, ms, args);
}

function clearInterval(timer) {
    clearTimerRequest(timer);
}

function setTimeout() {
    var args = Array.prototype.slice.call(arguments);
    var handler = args.shift();
    var ms = args.shift();

    return setTimerRequest(handler, ms, 0, args);
}

function clearTimeout(timer) {
    clearTimerRequest(timer);
}

function setImmediate() {
    var args = Array.prototype.slice.call(arguments);
    var handler = args.shift();

    return setTimerRequest(handler, 0, 0, args);
}

function clearImmediate(timer) {
    clearTimerRequest(timer);
}

//--INFO: Comment till here for execution in browser context.

function asynccall() {
console.log("Started at ");

setTimeout(function timeout() {
	//--INFO: This never gets called from nashorn javascript engine because the engine 
	//exits as soon as it gets evaluated.
	//In short, the execution context of nashorn javascript engine, is short lived
	//just like your java main thread.
	//It therefore behaves differently from browser(web) context, because browser context
	//is long lived than the nashorn(java) context.
	//So javascript is a single threaded event loop.Browser, plugs in a callback function,
	//to the task queue and javascript's event loop executes it.This does NOT happen
	//from javascript context.
	console.log("I just timed out!");
}, 0);

console.log("End Of Asynccall");
return "SUCCESS";
}

//INFO: Please comment this code below to run in browser context
//asynccall();