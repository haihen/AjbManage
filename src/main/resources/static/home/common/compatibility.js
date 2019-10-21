/**
 * Created by Darex on 2014/8/12.
 */
;
'use strict';

var ArrayExtend = function () {

    Array.prototype.removeAt = function (index) {
        this.splice(index, 1);
        return this;
    };

    Array.prototype.insertAt = function (index, item) {
        this.splice(index, 0, item);
        return this;
    };

    Array.prototype.insertFirst = function (item) {
        return this.insertAt(0, item);
    };

    Array.prototype.addById = function (id) {
        var isContains = false;
        for (var i = 0; i < this.length; i++) {
            if (this[i] == id) {
                isContains = true;
                break;
            }
        }

        if (!isContains) {
            this.push(id)
        }
    };

    Array.prototype.removeById = function (id) {
        var _self = this;
        for (var i = 0; i < this.length; i++) {
            if (this[i] == id) {
                this.splice(i, 1);
                break;
            }
        }
    };

    Array.prototype.distinct = function () {
        var _self = angular.copy(this),
            hash = {};
        this.length = 0;

        for (var i = 0, elem; (elem = _self[i]) != null; i++) {
            if (!hash[elem]) {
                this.push(elem);
                hash[elem] = true;
            }
        }
        return this;
    };

    Array.prototype.distinctObj = function () {
        var _self = angular.copy(this);
        this.length = 0;
        label:  for (var i = 0, n = _self.length; i < n; i++) {
            for (var j = 0, m = this.length; j < m; j++) {
                if (angular.equals(_self[i], this[j])) {
                    continue label;
                }
            }
            this.push(_self[i]);
        }
        return this;
    };

    Array.prototype.contains = function (item) {
        var isContains = false;
        for (var i = 0, n = this.length; i < n; i++) {
            if (angular.equals(this[i], item)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    };

    if (!Array.isArray) {
        Array.isArray = function (vArg) {
            return Object.prototype.toString.call(vArg) === "[object Array]";
        };
    }

    if (typeof Array.prototype.forEach != "function") {
        Array.prototype.forEach = function (fn, scope) {
            var i, len;
            for (i = 0, len = this.length; i < len; ++i) {
                if (i in this) {
                    fn.call(scope, this[i], i, this);
                }
            }
        };
    }

    if (typeof Array.prototype.map != "function") {
        Array.prototype.map = function (fn, context) {
            var arr = [];
            if (typeof fn === "function") {
                for (var k = 0, length = this.length; k < length; k++) {
                    arr.push(fn.call(context, this[k], k, this));
                }
            }
            return arr;
        };
    }

    if (typeof Array.prototype.filter != "function") {
        Array.prototype.filter = function (fn, context) {
            var arr = [];
            if (typeof fn === "function") {
                for (var k = 0, length = this.length; k < length; k++) {
                    fn.call(context, this[k], k, this) && arr.push(this[k]);
                }
            }
            return arr;
        };
    }

    if (typeof Array.prototype.some != "function") {
        Array.prototype.some = function (fn, context) {
            var passed = false;
            if (typeof fn === "function") {
                for (var k = 0, length = this.length; k < length; k++) {
                    if (passed === true) break;
                    passed = !!fn.call(context, this[k], k, this);
                }
            }
            return passed;
        };
    }

    if (typeof Array.prototype.every != "function") {
        Array.prototype.every = function (fn, context) {
            var passed = true;
            if (typeof fn === "function") {
                for (var k = 0, length = this.length; k < length; k++) {
                    if (passed === false) break;
                    passed = !!fn.call(context, this[k], k, this);
                }
            }
            return passed;
        };
    }

    if (typeof Array.prototype.indexOf != "function") {
        Array.prototype.indexOf = function (searchElement, fromIndex) {
            var index = -1;
            fromIndex = fromIndex * 1 || 0;

            for (var k = 0, length = this.length; k < length; k++) {
                if (k >= fromIndex && this[k] === searchElement) {
                    index = k;
                    break;
                }
            }
            return index;
        };
    }

    if (typeof Array.prototype.lastIndexOf != "function") {
        Array.prototype.lastIndexOf = function (searchElement, fromIndex) {
            var index = -1, length = this.length;
            fromIndex = fromIndex * 1 || length - 1;

            for (var k = length - 1; k > -1; k -= 1) {
                if (k <= fromIndex && this[k] === searchElement) {
                    index = k;
                    break;
                }
            }
            return index;
        };
    }

    if (typeof Array.prototype.reduce != "function") {
        Array.prototype.reduce = function (callback, initialValue) {
            var previous = initialValue, k = 0, length = this.length;
            if (typeof initialValue === "undefined") {
                previous = this[0];
                k = 1;
            }

            if (typeof callback === "function") {
                for (k; k < length; k++) {
                    this.hasOwnProperty(k) && (previous = callback(previous, this[k], k, this));
                }
            }
            return previous;
        };
    }

    if (typeof Array.prototype.reduceRight != "function") {
        Array.prototype.reduceRight = function (callback, initialValue) {
            var length = this.length, k = length - 1, previous = initialValue;
            if (typeof initialValue === "undefined") {
                previous = this[length - 1];
                k--;
            }
            if (typeof callback === "function") {
                for (k; k > -1; k -= 1) {
                    this.hasOwnProperty(k) && (previous = callback(previous, this[k], k, this));
                }
            }
            return previous;
        };
    }

}();

if (!Date.now) {
    Date.now = function now() {
        return (new Date).valueOf();
    };
}

if (!Object.create) {
    Object.create = function (o) {
        if (arguments.length > 1) {
            throw new Error('Object.create implementation only accepts the first parameter.');
        }
        function F() {
        }

        F.prototype = o;
        return new F();
    };
}

if (!Object.keys) {
    Object.keys = function (o) {
        if (o !== Object(o)) {
            throw new TypeError('Object.keys called on a non-object');
        }
        var k = [], p;
        for (p in o) {
            if (Object.prototype.hasOwnProperty.call(o, p)) {
                k.push(p);
            }
        }
        return k;
    };
}

if (!window.console) {
    var noop = function () {
    };
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = window.console || {};

    while (length--) {
        // Only stub undefined methods.
        console[methods[length]] = console[methods[length]] || noop;
    }
}

if (!String.prototype.format) {
    String.prototype.format = function (args) {
        var result = this;
        if (arguments.length > 0) {
            if (arguments.length == 1 && typeof (args) == "object") {
                for (var key in args) {
                    if (args[key] != undefined) {
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            }
            else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        var reg = new RegExp("({)" + i + "(})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
    }
}

function bindEvent(target, noOnEventName, handler, useCapture) {
    useCapture = useCapture || false;

    if (window.addEventListener) {
        target.addEventListener(noOnEventName, handler, useCapture);
    } else if (window.attachEvent) {
        // IE
        target.attachEvent("on" + noOnEventName, handler);
    } else {
        target["on" + noOnEventName] = handler;
    }
};

/**
 * 动态加载js
 */
function jsLoad(url, func) {
    var script = document.createElement("script");
    script.src = url;
    script.charset = "utf-8";
    script.type = "text/javascript";

    var head = window.document.getElementsByTagName("head").item(0);
    head.appendChild(script);

    script.onload = script.onreadystatechange = function () {
        if (!this.readyState || this.readyState == 'loaded' || this.readyState == 'complete') {
            if (func) {
                func();
            }
        }
        script.onload = script.onreadystatechange = null;
    };
}


function getUrlParam(name) {
    var query = location.search.substring(1);//获取查询串
    var pairs = query.split("&");//在逗号处断开
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');//查找name=value
        if (pos == -1)//如果没有找到就跳过
            continue;
        var argname = pairs[i].substring(0, pos);//提取name
        var value = pairs[i].substring(pos + 1);//提取value
        if (argname.toLowerCase() == name.toLowerCase())
            return decodeURIComponent(value);
    }
    return "";
}

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}