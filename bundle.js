/******/ (function(modules) { // webpackBootstrap
/******/ 	// install a JSONP callback for chunk loading
/******/ 	var parentJsonpFunction = window["webpackJsonp"];
/******/ 	window["webpackJsonp"] = function webpackJsonpCallback(chunkIds, moreModules, executeModules) {
/******/ 		// add "moreModules" to the modules object,
/******/ 		// then flag all "chunkIds" as loaded and fire callback
/******/ 		var moduleId, chunkId, i = 0, resolves = [], result;
/******/ 		for(;i < chunkIds.length; i++) {
/******/ 			chunkId = chunkIds[i];
/******/ 			if(installedChunks[chunkId]) {
/******/ 				resolves.push(installedChunks[chunkId][0]);
/******/ 			}
/******/ 			installedChunks[chunkId] = 0;
/******/ 		}
/******/ 		for(moduleId in moreModules) {
/******/ 			if(Object.prototype.hasOwnProperty.call(moreModules, moduleId)) {
/******/ 				modules[moduleId] = moreModules[moduleId];
/******/ 			}
/******/ 		}
/******/ 		if(parentJsonpFunction) parentJsonpFunction(chunkIds, moreModules, executeModules);
/******/ 		while(resolves.length) {
/******/ 			resolves.shift()();
/******/ 		}
/******/
/******/ 	};
/******/
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// objects to store loaded and loading chunks
/******/ 	var installedChunks = {
/******/ 		193: 0
/******/ 	};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/ 	// This file contains only the entry chunk.
/******/ 	// The chunk loading function for additional chunks
/******/ 	__webpack_require__.e = function requireEnsure(chunkId) {
/******/ 		var installedChunkData = installedChunks[chunkId];
/******/ 		if(installedChunkData === 0) {
/******/ 			return new Promise(function(resolve) { resolve(); });
/******/ 		}
/******/
/******/ 		// a Promise means "currently loading".
/******/ 		if(installedChunkData) {
/******/ 			return installedChunkData[2];
/******/ 		}
/******/
/******/ 		// setup Promise in chunk cache
/******/ 		var promise = new Promise(function(resolve, reject) {
/******/ 			installedChunkData = installedChunks[chunkId] = [resolve, reject];
/******/ 		});
/******/ 		installedChunkData[2] = promise;
/******/
/******/ 		// start chunk loading
/******/ 		var head = document.getElementsByTagName('head')[0];
/******/ 		var script = document.createElement('script');
/******/ 		script.type = 'text/javascript';
/******/ 		script.charset = 'utf-8';
/******/ 		script.async = true;
/******/ 		script.timeout = 120000;
/******/
/******/ 		if (__webpack_require__.nc) {
/******/ 			script.setAttribute("nonce", __webpack_require__.nc);
/******/ 		}
/******/ 		script.src = __webpack_require__.p + "" + chunkId + ".js?v=" + {"0":"278a569d","1":"c1ecf67e","2":"94ae6175","3":"a0f47ece","4":"014f8d34","5":"7aeebc4c","6":"a860718e","7":"f81177b9","8":"b73f40eb","9":"d2cb6bb4","10":"04b43442","11":"add92a5a","12":"92069597","13":"2cdd7aa2","14":"6a358b4a","15":"fc68866e","16":"9f12476e","17":"d97d38cc","18":"23396f42","19":"a6463de1","20":"ae5bba36","21":"efdfa823","22":"ed86819f","23":"6b8c2ec4","24":"363fbba5","25":"fc2d6d5f","26":"42d3adaa","27":"eb72f3a5","28":"c5374f0b","29":"e7817f78","30":"f5fe9882","31":"a6cce4ac","32":"70a37b2f","33":"10afc00b","34":"2c0b1e85","35":"1e2cdf07","36":"12c91ba4","37":"631d980a","38":"b9089d46","39":"e5c3fb38","40":"74f6b595","41":"2af02069","42":"7896f90b","43":"f9731d65","44":"7c004856","45":"29cea8f7","46":"7a3215a1","47":"685c9fc7","48":"0cf7b735","49":"e0293500","50":"75df2c43","51":"73453d17","52":"2dd9e21d","53":"500f774d","54":"6d0efdb5","55":"c5500a3c","56":"fb48d9ea","57":"59817b0a","58":"bcd54f98","59":"18e2206f","60":"33dc2d5e","61":"d77ab72f","62":"27bc5178","63":"1b687f9b","64":"04dc61e8","65":"050c9c59","66":"f435b9b6","67":"fe2498dd","68":"a92ba9db","69":"f7a4f26c","70":"55790ccb","71":"cd986bbe","72":"e36b6af2","73":"90cb8282","74":"12e76f74","75":"e39cb1ff","76":"5e81ee5b","77":"7e94ba22","78":"a088e723","79":"bf57b8a0","80":"288ef105","81":"3e61f184","82":"56f30920","83":"e9ed1c4a","84":"f4a2fe6a","85":"257365ba","86":"8bc5f5b8","87":"e82de795","88":"0d620187","89":"526db562","90":"d9a622c5","91":"6bcceae9","92":"957d2bae","93":"a0c65198","94":"115889cc","95":"b6dbb433","96":"0a4f8beb","97":"091cd724","98":"1e9e5069","99":"c1e9432a","100":"3a9b3bb8","101":"3e408759","102":"c4794c5b","103":"49cf2c49","104":"7358de4d","105":"aa42e9bb","106":"02caaa0a","107":"4cc0dc01","108":"e4133c1d","109":"32155f9d","110":"6139b882","111":"ec31e07b","112":"2b2028ba","113":"3d5e4eff","114":"bdb07d77","115":"4231ba5a","116":"686b8603","117":"0d1af573","118":"df4cd548","119":"962d27bc","120":"19b7a447","121":"5f677e38","122":"c69a7840","123":"f82d86e5","124":"3c774eba","125":"3c7b81fb","126":"9328b3da","127":"1528f61f","128":"152a6dcb","129":"b9be364f","130":"5b0002c3","131":"863be9a4","132":"20180f08","133":"53e895d8","134":"13de5b7b","135":"7bbf5d61","136":"68b0d00a","137":"efd690bf","138":"dc2b5062","139":"e5826199","140":"62c6be17","141":"b9148077","142":"f64e4ae6","143":"33919eda","144":"8da05268","145":"c2bb72dd","146":"dfc8bb21","147":"24e2e4c3","148":"b61ec7ee","149":"2507dd1d","150":"0afa8f3a","151":"5f585af3","152":"476a2dc7","153":"1f88dea4","154":"1df0a9ee","155":"a3f9e27e","156":"90dff3e8","157":"09c0d385","158":"7bc2851c","159":"7c3de2eb","160":"ad9dd5ee","161":"b889181f","162":"07862f69","163":"bab5fcc6","164":"8c3fa426","165":"4ffdbacb","166":"804ce295","167":"8dd1c766","168":"567c7deb","169":"a3414314","170":"477908f3","171":"d7e7c82e","172":"1fde442e","173":"ae79f7dd","174":"3e926079","175":"68c30301","176":"b45aee84","177":"90538279","178":"0890722b","179":"a8eb5ae4","180":"3b9b9f7a","181":"b5233d34","182":"39f51594","183":"effcc0fe","184":"9001c2e2","185":"7b4d8f2d","186":"59bb3bf5","187":"6c1cf5b1","188":"0dacf273","189":"df8baf23","190":"a7c170b6","191":"71f30250","192":"95ad3015"}[chunkId] + "";
/******/ 		var timeout = setTimeout(onScriptComplete, 120000);
/******/ 		script.onerror = script.onload = onScriptComplete;
/******/ 		function onScriptComplete() {
/******/ 			// avoid mem leaks in IE.
/******/ 			script.onerror = script.onload = null;
/******/ 			clearTimeout(timeout);
/******/ 			var chunk = installedChunks[chunkId];
/******/ 			if(chunk !== 0) {
/******/ 				if(chunk) {
/******/ 					chunk[1](new Error('Loading chunk ' + chunkId + ' failed.'));
/******/ 				}
/******/ 				installedChunks[chunkId] = undefined;
/******/ 			}
/******/ 		};
/******/ 		head.appendChild(script);
/******/
/******/ 		return promise;
/******/ 	};
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// on error function for async loading
/******/ 	__webpack_require__.oe = function(err) { console.error(err); throw err; };
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 113);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory();
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define([], factory);
	}
	else {
		// Global (browser)
		root.CryptoJS = factory();
	}
}(this, function () {

	/**
	 * CryptoJS core components.
	 */
	var CryptoJS = CryptoJS || (function (Math, undefined) {
	    /*
	     * Local polyfil of Object.create
	     */
	    var create = Object.create || (function () {
	        function F() {};

	        return function (obj) {
	            var subtype;

	            F.prototype = obj;

	            subtype = new F();

	            F.prototype = null;

	            return subtype;
	        };
	    }())

	    /**
	     * CryptoJS namespace.
	     */
	    var C = {};

	    /**
	     * Library namespace.
	     */
	    var C_lib = C.lib = {};

	    /**
	     * Base object for prototypal inheritance.
	     */
	    var Base = C_lib.Base = (function () {


	        return {
	            /**
	             * Creates a new object that inherits from this object.
	             *
	             * @param {Object} overrides Properties to copy into the new object.
	             *
	             * @return {Object} The new object.
	             *
	             * @static
	             *
	             * @example
	             *
	             *     var MyType = CryptoJS.lib.Base.extend({
	             *         field: 'value',
	             *
	             *         method: function () {
	             *         }
	             *     });
	             */
	            extend: function (overrides) {
	                // Spawn
	                var subtype = create(this);

	                // Augment
	                if (overrides) {
	                    subtype.mixIn(overrides);
	                }

	                // Create default initializer
	                if (!subtype.hasOwnProperty('init') || this.init === subtype.init) {
	                    subtype.init = function () {
	                        subtype.$super.init.apply(this, arguments);
	                    };
	                }

	                // Initializer's prototype is the subtype object
	                subtype.init.prototype = subtype;

	                // Reference supertype
	                subtype.$super = this;

	                return subtype;
	            },

	            /**
	             * Extends this object and runs the init method.
	             * Arguments to create() will be passed to init().
	             *
	             * @return {Object} The new object.
	             *
	             * @static
	             *
	             * @example
	             *
	             *     var instance = MyType.create();
	             */
	            create: function () {
	                var instance = this.extend();
	                instance.init.apply(instance, arguments);

	                return instance;
	            },

	            /**
	             * Initializes a newly created object.
	             * Override this method to add some logic when your objects are created.
	             *
	             * @example
	             *
	             *     var MyType = CryptoJS.lib.Base.extend({
	             *         init: function () {
	             *             // ...
	             *         }
	             *     });
	             */
	            init: function () {
	            },

	            /**
	             * Copies properties into this object.
	             *
	             * @param {Object} properties The properties to mix in.
	             *
	             * @example
	             *
	             *     MyType.mixIn({
	             *         field: 'value'
	             *     });
	             */
	            mixIn: function (properties) {
	                for (var propertyName in properties) {
	                    if (properties.hasOwnProperty(propertyName)) {
	                        this[propertyName] = properties[propertyName];
	                    }
	                }

	                // IE won't copy toString using the loop above
	                if (properties.hasOwnProperty('toString')) {
	                    this.toString = properties.toString;
	                }
	            },

	            /**
	             * Creates a copy of this object.
	             *
	             * @return {Object} The clone.
	             *
	             * @example
	             *
	             *     var clone = instance.clone();
	             */
	            clone: function () {
	                return this.init.prototype.extend(this);
	            }
	        };
	    }());

	    /**
	     * An array of 32-bit words.
	     *
	     * @property {Array} words The array of 32-bit words.
	     * @property {number} sigBytes The number of significant bytes in this word array.
	     */
	    var WordArray = C_lib.WordArray = Base.extend({
	        /**
	         * Initializes a newly created word array.
	         *
	         * @param {Array} words (Optional) An array of 32-bit words.
	         * @param {number} sigBytes (Optional) The number of significant bytes in the words.
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.lib.WordArray.create();
	         *     var wordArray = CryptoJS.lib.WordArray.create([0x00010203, 0x04050607]);
	         *     var wordArray = CryptoJS.lib.WordArray.create([0x00010203, 0x04050607], 6);
	         */
	        init: function (words, sigBytes) {
	            words = this.words = words || [];

	            if (sigBytes != undefined) {
	                this.sigBytes = sigBytes;
	            } else {
	                this.sigBytes = words.length * 4;
	            }
	        },

	        /**
	         * Converts this word array to a string.
	         *
	         * @param {Encoder} encoder (Optional) The encoding strategy to use. Default: CryptoJS.enc.Hex
	         *
	         * @return {string} The stringified word array.
	         *
	         * @example
	         *
	         *     var string = wordArray + '';
	         *     var string = wordArray.toString();
	         *     var string = wordArray.toString(CryptoJS.enc.Utf8);
	         */
	        toString: function (encoder) {
	            return (encoder || Hex).stringify(this);
	        },

	        /**
	         * Concatenates a word array to this word array.
	         *
	         * @param {WordArray} wordArray The word array to append.
	         *
	         * @return {WordArray} This word array.
	         *
	         * @example
	         *
	         *     wordArray1.concat(wordArray2);
	         */
	        concat: function (wordArray) {
	            // Shortcuts
	            var thisWords = this.words;
	            var thatWords = wordArray.words;
	            var thisSigBytes = this.sigBytes;
	            var thatSigBytes = wordArray.sigBytes;

	            // Clamp excess bits
	            this.clamp();

	            // Concat
	            if (thisSigBytes % 4) {
	                // Copy one byte at a time
	                for (var i = 0; i < thatSigBytes; i++) {
	                    var thatByte = (thatWords[i >>> 2] >>> (24 - (i % 4) * 8)) & 0xff;
	                    thisWords[(thisSigBytes + i) >>> 2] |= thatByte << (24 - ((thisSigBytes + i) % 4) * 8);
	                }
	            } else {
	                // Copy one word at a time
	                for (var i = 0; i < thatSigBytes; i += 4) {
	                    thisWords[(thisSigBytes + i) >>> 2] = thatWords[i >>> 2];
	                }
	            }
	            this.sigBytes += thatSigBytes;

	            // Chainable
	            return this;
	        },

	        /**
	         * Removes insignificant bits.
	         *
	         * @example
	         *
	         *     wordArray.clamp();
	         */
	        clamp: function () {
	            // Shortcuts
	            var words = this.words;
	            var sigBytes = this.sigBytes;

	            // Clamp
	            words[sigBytes >>> 2] &= 0xffffffff << (32 - (sigBytes % 4) * 8);
	            words.length = Math.ceil(sigBytes / 4);
	        },

	        /**
	         * Creates a copy of this word array.
	         *
	         * @return {WordArray} The clone.
	         *
	         * @example
	         *
	         *     var clone = wordArray.clone();
	         */
	        clone: function () {
	            var clone = Base.clone.call(this);
	            clone.words = this.words.slice(0);

	            return clone;
	        },

	        /**
	         * Creates a word array filled with random bytes.
	         *
	         * @param {number} nBytes The number of random bytes to generate.
	         *
	         * @return {WordArray} The random word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.lib.WordArray.random(16);
	         */
	        random: function (nBytes) {
	            var words = [];

	            var r = (function (m_w) {
	                var m_w = m_w;
	                var m_z = 0x3ade68b1;
	                var mask = 0xffffffff;

	                return function () {
	                    m_z = (0x9069 * (m_z & 0xFFFF) + (m_z >> 0x10)) & mask;
	                    m_w = (0x4650 * (m_w & 0xFFFF) + (m_w >> 0x10)) & mask;
	                    var result = ((m_z << 0x10) + m_w) & mask;
	                    result /= 0x100000000;
	                    result += 0.5;
	                    return result * (Math.random() > .5 ? 1 : -1);
	                }
	            });

	            for (var i = 0, rcache; i < nBytes; i += 4) {
	                var _r = r((rcache || Math.random()) * 0x100000000);

	                rcache = _r() * 0x3ade67b7;
	                words.push((_r() * 0x100000000) | 0);
	            }

	            return new WordArray.init(words, nBytes);
	        }
	    });

	    /**
	     * Encoder namespace.
	     */
	    var C_enc = C.enc = {};

	    /**
	     * Hex encoding strategy.
	     */
	    var Hex = C_enc.Hex = {
	        /**
	         * Converts a word array to a hex string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The hex string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var hexString = CryptoJS.enc.Hex.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            // Shortcuts
	            var words = wordArray.words;
	            var sigBytes = wordArray.sigBytes;

	            // Convert
	            var hexChars = [];
	            for (var i = 0; i < sigBytes; i++) {
	                var bite = (words[i >>> 2] >>> (24 - (i % 4) * 8)) & 0xff;
	                hexChars.push((bite >>> 4).toString(16));
	                hexChars.push((bite & 0x0f).toString(16));
	            }

	            return hexChars.join('');
	        },

	        /**
	         * Converts a hex string to a word array.
	         *
	         * @param {string} hexStr The hex string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Hex.parse(hexString);
	         */
	        parse: function (hexStr) {
	            // Shortcut
	            var hexStrLength = hexStr.length;

	            // Convert
	            var words = [];
	            for (var i = 0; i < hexStrLength; i += 2) {
	                words[i >>> 3] |= parseInt(hexStr.substr(i, 2), 16) << (24 - (i % 8) * 4);
	            }

	            return new WordArray.init(words, hexStrLength / 2);
	        }
	    };

	    /**
	     * Latin1 encoding strategy.
	     */
	    var Latin1 = C_enc.Latin1 = {
	        /**
	         * Converts a word array to a Latin1 string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The Latin1 string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var latin1String = CryptoJS.enc.Latin1.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            // Shortcuts
	            var words = wordArray.words;
	            var sigBytes = wordArray.sigBytes;

	            // Convert
	            var latin1Chars = [];
	            for (var i = 0; i < sigBytes; i++) {
	                var bite = (words[i >>> 2] >>> (24 - (i % 4) * 8)) & 0xff;
	                latin1Chars.push(String.fromCharCode(bite));
	            }

	            return latin1Chars.join('');
	        },

	        /**
	         * Converts a Latin1 string to a word array.
	         *
	         * @param {string} latin1Str The Latin1 string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Latin1.parse(latin1String);
	         */
	        parse: function (latin1Str) {
	            // Shortcut
	            var latin1StrLength = latin1Str.length;

	            // Convert
	            var words = [];
	            for (var i = 0; i < latin1StrLength; i++) {
	                words[i >>> 2] |= (latin1Str.charCodeAt(i) & 0xff) << (24 - (i % 4) * 8);
	            }

	            return new WordArray.init(words, latin1StrLength);
	        }
	    };

	    /**
	     * UTF-8 encoding strategy.
	     */
	    var Utf8 = C_enc.Utf8 = {
	        /**
	         * Converts a word array to a UTF-8 string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The UTF-8 string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var utf8String = CryptoJS.enc.Utf8.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            try {
	                return decodeURIComponent(escape(Latin1.stringify(wordArray)));
	            } catch (e) {
	                throw new Error('Malformed UTF-8 data');
	            }
	        },

	        /**
	         * Converts a UTF-8 string to a word array.
	         *
	         * @param {string} utf8Str The UTF-8 string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Utf8.parse(utf8String);
	         */
	        parse: function (utf8Str) {
	            return Latin1.parse(unescape(encodeURIComponent(utf8Str)));
	        }
	    };

	    /**
	     * Abstract buffered block algorithm template.
	     *
	     * The property blockSize must be implemented in a concrete subtype.
	     *
	     * @property {number} _minBufferSize The number of blocks that should be kept unprocessed in the buffer. Default: 0
	     */
	    var BufferedBlockAlgorithm = C_lib.BufferedBlockAlgorithm = Base.extend({
	        /**
	         * Resets this block algorithm's data buffer to its initial state.
	         *
	         * @example
	         *
	         *     bufferedBlockAlgorithm.reset();
	         */
	        reset: function () {
	            // Initial values
	            this._data = new WordArray.init();
	            this._nDataBytes = 0;
	        },

	        /**
	         * Adds new data to this block algorithm's buffer.
	         *
	         * @param {WordArray|string} data The data to append. Strings are converted to a WordArray using UTF-8.
	         *
	         * @example
	         *
	         *     bufferedBlockAlgorithm._append('data');
	         *     bufferedBlockAlgorithm._append(wordArray);
	         */
	        _append: function (data) {
	            // Convert string to WordArray, else assume WordArray already
	            if (typeof data == 'string') {
	                data = Utf8.parse(data);
	            }

	            // Append
	            this._data.concat(data);
	            this._nDataBytes += data.sigBytes;
	        },

	        /**
	         * Processes available data blocks.
	         *
	         * This method invokes _doProcessBlock(offset), which must be implemented by a concrete subtype.
	         *
	         * @param {boolean} doFlush Whether all blocks and partial blocks should be processed.
	         *
	         * @return {WordArray} The processed data.
	         *
	         * @example
	         *
	         *     var processedData = bufferedBlockAlgorithm._process();
	         *     var processedData = bufferedBlockAlgorithm._process(!!'flush');
	         */
	        _process: function (doFlush) {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;
	            var dataSigBytes = data.sigBytes;
	            var blockSize = this.blockSize;
	            var blockSizeBytes = blockSize * 4;

	            // Count blocks ready
	            var nBlocksReady = dataSigBytes / blockSizeBytes;
	            if (doFlush) {
	                // Round up to include partial blocks
	                nBlocksReady = Math.ceil(nBlocksReady);
	            } else {
	                // Round down to include only full blocks,
	                // less the number of blocks that must remain in the buffer
	                nBlocksReady = Math.max((nBlocksReady | 0) - this._minBufferSize, 0);
	            }

	            // Count words ready
	            var nWordsReady = nBlocksReady * blockSize;

	            // Count bytes ready
	            var nBytesReady = Math.min(nWordsReady * 4, dataSigBytes);

	            // Process blocks
	            if (nWordsReady) {
	                for (var offset = 0; offset < nWordsReady; offset += blockSize) {
	                    // Perform concrete-algorithm logic
	                    this._doProcessBlock(dataWords, offset);
	                }

	                // Remove processed words
	                var processedWords = dataWords.splice(0, nWordsReady);
	                data.sigBytes -= nBytesReady;
	            }

	            // Return processed words
	            return new WordArray.init(processedWords, nBytesReady);
	        },

	        /**
	         * Creates a copy of this object.
	         *
	         * @return {Object} The clone.
	         *
	         * @example
	         *
	         *     var clone = bufferedBlockAlgorithm.clone();
	         */
	        clone: function () {
	            var clone = Base.clone.call(this);
	            clone._data = this._data.clone();

	            return clone;
	        },

	        _minBufferSize: 0
	    });

	    /**
	     * Abstract hasher template.
	     *
	     * @property {number} blockSize The number of 32-bit words this hasher operates on. Default: 16 (512 bits)
	     */
	    var Hasher = C_lib.Hasher = BufferedBlockAlgorithm.extend({
	        /**
	         * Configuration options.
	         */
	        cfg: Base.extend(),

	        /**
	         * Initializes a newly created hasher.
	         *
	         * @param {Object} cfg (Optional) The configuration options to use for this hash computation.
	         *
	         * @example
	         *
	         *     var hasher = CryptoJS.algo.SHA256.create();
	         */
	        init: function (cfg) {
	            // Apply config defaults
	            this.cfg = this.cfg.extend(cfg);

	            // Set initial values
	            this.reset();
	        },

	        /**
	         * Resets this hasher to its initial state.
	         *
	         * @example
	         *
	         *     hasher.reset();
	         */
	        reset: function () {
	            // Reset data buffer
	            BufferedBlockAlgorithm.reset.call(this);

	            // Perform concrete-hasher logic
	            this._doReset();
	        },

	        /**
	         * Updates this hasher with a message.
	         *
	         * @param {WordArray|string} messageUpdate The message to append.
	         *
	         * @return {Hasher} This hasher.
	         *
	         * @example
	         *
	         *     hasher.update('message');
	         *     hasher.update(wordArray);
	         */
	        update: function (messageUpdate) {
	            // Append
	            this._append(messageUpdate);

	            // Update the hash
	            this._process();

	            // Chainable
	            return this;
	        },

	        /**
	         * Finalizes the hash computation.
	         * Note that the finalize operation is effectively a destructive, read-once operation.
	         *
	         * @param {WordArray|string} messageUpdate (Optional) A final message update.
	         *
	         * @return {WordArray} The hash.
	         *
	         * @example
	         *
	         *     var hash = hasher.finalize();
	         *     var hash = hasher.finalize('message');
	         *     var hash = hasher.finalize(wordArray);
	         */
	        finalize: function (messageUpdate) {
	            // Final message update
	            if (messageUpdate) {
	                this._append(messageUpdate);
	            }

	            // Perform concrete-hasher logic
	            var hash = this._doFinalize();

	            return hash;
	        },

	        blockSize: 512/32,

	        /**
	         * Creates a shortcut function to a hasher's object interface.
	         *
	         * @param {Hasher} hasher The hasher to create a helper for.
	         *
	         * @return {Function} The shortcut function.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var SHA256 = CryptoJS.lib.Hasher._createHelper(CryptoJS.algo.SHA256);
	         */
	        _createHelper: function (hasher) {
	            return function (message, cfg) {
	                return new hasher.init(cfg).finalize(message);
	            };
	        },

	        /**
	         * Creates a shortcut function to the HMAC's object interface.
	         *
	         * @param {Hasher} hasher The hasher to use in this HMAC helper.
	         *
	         * @return {Function} The shortcut function.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var HmacSHA256 = CryptoJS.lib.Hasher._createHmacHelper(CryptoJS.algo.SHA256);
	         */
	        _createHmacHelper: function (hasher) {
	            return function (message, key) {
	                return new C_algo.HMAC.init(hasher, key).finalize(message);
	            };
	        }
	    });

	    /**
	     * Algorithm namespace.
	     */
	    var C_algo = C.algo = {};

	    return C;
	}(Math));


	return CryptoJS;

}));

/***/ }),
/* 1 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(6));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./evpkdf"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Cipher core components.
	 */
	CryptoJS.lib.Cipher || (function (undefined) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Base = C_lib.Base;
	    var WordArray = C_lib.WordArray;
	    var BufferedBlockAlgorithm = C_lib.BufferedBlockAlgorithm;
	    var C_enc = C.enc;
	    var Utf8 = C_enc.Utf8;
	    var Base64 = C_enc.Base64;
	    var C_algo = C.algo;
	    var EvpKDF = C_algo.EvpKDF;

	    /**
	     * Abstract base cipher template.
	     *
	     * @property {number} keySize This cipher's key size. Default: 4 (128 bits)
	     * @property {number} ivSize This cipher's IV size. Default: 4 (128 bits)
	     * @property {number} _ENC_XFORM_MODE A constant representing encryption mode.
	     * @property {number} _DEC_XFORM_MODE A constant representing decryption mode.
	     */
	    var Cipher = C_lib.Cipher = BufferedBlockAlgorithm.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {WordArray} iv The IV to use for this operation.
	         */
	        cfg: Base.extend(),

	        /**
	         * Creates this cipher in encryption mode.
	         *
	         * @param {WordArray} key The key.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {Cipher} A cipher instance.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var cipher = CryptoJS.algo.AES.createEncryptor(keyWordArray, { iv: ivWordArray });
	         */
	        createEncryptor: function (key, cfg) {
	            return this.create(this._ENC_XFORM_MODE, key, cfg);
	        },

	        /**
	         * Creates this cipher in decryption mode.
	         *
	         * @param {WordArray} key The key.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {Cipher} A cipher instance.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var cipher = CryptoJS.algo.AES.createDecryptor(keyWordArray, { iv: ivWordArray });
	         */
	        createDecryptor: function (key, cfg) {
	            return this.create(this._DEC_XFORM_MODE, key, cfg);
	        },

	        /**
	         * Initializes a newly created cipher.
	         *
	         * @param {number} xformMode Either the encryption or decryption transormation mode constant.
	         * @param {WordArray} key The key.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @example
	         *
	         *     var cipher = CryptoJS.algo.AES.create(CryptoJS.algo.AES._ENC_XFORM_MODE, keyWordArray, { iv: ivWordArray });
	         */
	        init: function (xformMode, key, cfg) {
	            // Apply config defaults
	            this.cfg = this.cfg.extend(cfg);

	            // Store transform mode and key
	            this._xformMode = xformMode;
	            this._key = key;

	            // Set initial values
	            this.reset();
	        },

	        /**
	         * Resets this cipher to its initial state.
	         *
	         * @example
	         *
	         *     cipher.reset();
	         */
	        reset: function () {
	            // Reset data buffer
	            BufferedBlockAlgorithm.reset.call(this);

	            // Perform concrete-cipher logic
	            this._doReset();
	        },

	        /**
	         * Adds data to be encrypted or decrypted.
	         *
	         * @param {WordArray|string} dataUpdate The data to encrypt or decrypt.
	         *
	         * @return {WordArray} The data after processing.
	         *
	         * @example
	         *
	         *     var encrypted = cipher.process('data');
	         *     var encrypted = cipher.process(wordArray);
	         */
	        process: function (dataUpdate) {
	            // Append
	            this._append(dataUpdate);

	            // Process available blocks
	            return this._process();
	        },

	        /**
	         * Finalizes the encryption or decryption process.
	         * Note that the finalize operation is effectively a destructive, read-once operation.
	         *
	         * @param {WordArray|string} dataUpdate The final data to encrypt or decrypt.
	         *
	         * @return {WordArray} The data after final processing.
	         *
	         * @example
	         *
	         *     var encrypted = cipher.finalize();
	         *     var encrypted = cipher.finalize('data');
	         *     var encrypted = cipher.finalize(wordArray);
	         */
	        finalize: function (dataUpdate) {
	            // Final data update
	            if (dataUpdate) {
	                this._append(dataUpdate);
	            }

	            // Perform concrete-cipher logic
	            var finalProcessedData = this._doFinalize();

	            return finalProcessedData;
	        },

	        keySize: 128/32,

	        ivSize: 128/32,

	        _ENC_XFORM_MODE: 1,

	        _DEC_XFORM_MODE: 2,

	        /**
	         * Creates shortcut functions to a cipher's object interface.
	         *
	         * @param {Cipher} cipher The cipher to create a helper for.
	         *
	         * @return {Object} An object with encrypt and decrypt shortcut functions.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var AES = CryptoJS.lib.Cipher._createHelper(CryptoJS.algo.AES);
	         */
	        _createHelper: (function () {
	            function selectCipherStrategy(key) {
	                if (typeof key == 'string') {
	                    return PasswordBasedCipher;
	                } else {
	                    return SerializableCipher;
	                }
	            }

	            return function (cipher) {
	                return {
	                    encrypt: function (message, key, cfg) {
	                        return selectCipherStrategy(key).encrypt(cipher, message, key, cfg);
	                    },

	                    decrypt: function (ciphertext, key, cfg) {
	                        return selectCipherStrategy(key).decrypt(cipher, ciphertext, key, cfg);
	                    }
	                };
	            };
	        }())
	    });

	    /**
	     * Abstract base stream cipher template.
	     *
	     * @property {number} blockSize The number of 32-bit words this cipher operates on. Default: 1 (32 bits)
	     */
	    var StreamCipher = C_lib.StreamCipher = Cipher.extend({
	        _doFinalize: function () {
	            // Process partial blocks
	            var finalProcessedBlocks = this._process(!!'flush');

	            return finalProcessedBlocks;
	        },

	        blockSize: 1
	    });

	    /**
	     * Mode namespace.
	     */
	    var C_mode = C.mode = {};

	    /**
	     * Abstract base block cipher mode template.
	     */
	    var BlockCipherMode = C_lib.BlockCipherMode = Base.extend({
	        /**
	         * Creates this mode for encryption.
	         *
	         * @param {Cipher} cipher A block cipher instance.
	         * @param {Array} iv The IV words.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var mode = CryptoJS.mode.CBC.createEncryptor(cipher, iv.words);
	         */
	        createEncryptor: function (cipher, iv) {
	            return this.Encryptor.create(cipher, iv);
	        },

	        /**
	         * Creates this mode for decryption.
	         *
	         * @param {Cipher} cipher A block cipher instance.
	         * @param {Array} iv The IV words.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var mode = CryptoJS.mode.CBC.createDecryptor(cipher, iv.words);
	         */
	        createDecryptor: function (cipher, iv) {
	            return this.Decryptor.create(cipher, iv);
	        },

	        /**
	         * Initializes a newly created mode.
	         *
	         * @param {Cipher} cipher A block cipher instance.
	         * @param {Array} iv The IV words.
	         *
	         * @example
	         *
	         *     var mode = CryptoJS.mode.CBC.Encryptor.create(cipher, iv.words);
	         */
	        init: function (cipher, iv) {
	            this._cipher = cipher;
	            this._iv = iv;
	        }
	    });

	    /**
	     * Cipher Block Chaining mode.
	     */
	    var CBC = C_mode.CBC = (function () {
	        /**
	         * Abstract base CBC mode.
	         */
	        var CBC = BlockCipherMode.extend();

	        /**
	         * CBC encryptor.
	         */
	        CBC.Encryptor = CBC.extend({
	            /**
	             * Processes the data block at offset.
	             *
	             * @param {Array} words The data words to operate on.
	             * @param {number} offset The offset where the block starts.
	             *
	             * @example
	             *
	             *     mode.processBlock(data.words, offset);
	             */
	            processBlock: function (words, offset) {
	                // Shortcuts
	                var cipher = this._cipher;
	                var blockSize = cipher.blockSize;

	                // XOR and encrypt
	                xorBlock.call(this, words, offset, blockSize);
	                cipher.encryptBlock(words, offset);

	                // Remember this block to use with next block
	                this._prevBlock = words.slice(offset, offset + blockSize);
	            }
	        });

	        /**
	         * CBC decryptor.
	         */
	        CBC.Decryptor = CBC.extend({
	            /**
	             * Processes the data block at offset.
	             *
	             * @param {Array} words The data words to operate on.
	             * @param {number} offset The offset where the block starts.
	             *
	             * @example
	             *
	             *     mode.processBlock(data.words, offset);
	             */
	            processBlock: function (words, offset) {
	                // Shortcuts
	                var cipher = this._cipher;
	                var blockSize = cipher.blockSize;

	                // Remember this block to use with next block
	                var thisBlock = words.slice(offset, offset + blockSize);

	                // Decrypt and XOR
	                cipher.decryptBlock(words, offset);
	                xorBlock.call(this, words, offset, blockSize);

	                // This block becomes the previous block
	                this._prevBlock = thisBlock;
	            }
	        });

	        function xorBlock(words, offset, blockSize) {
	            // Shortcut
	            var iv = this._iv;

	            // Choose mixing block
	            if (iv) {
	                var block = iv;

	                // Remove IV for subsequent blocks
	                this._iv = undefined;
	            } else {
	                var block = this._prevBlock;
	            }

	            // XOR blocks
	            for (var i = 0; i < blockSize; i++) {
	                words[offset + i] ^= block[i];
	            }
	        }

	        return CBC;
	    }());

	    /**
	     * Padding namespace.
	     */
	    var C_pad = C.pad = {};

	    /**
	     * PKCS #5/7 padding strategy.
	     */
	    var Pkcs7 = C_pad.Pkcs7 = {
	        /**
	         * Pads data using the algorithm defined in PKCS #5/7.
	         *
	         * @param {WordArray} data The data to pad.
	         * @param {number} blockSize The multiple that the data should be padded to.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     CryptoJS.pad.Pkcs7.pad(wordArray, 4);
	         */
	        pad: function (data, blockSize) {
	            // Shortcut
	            var blockSizeBytes = blockSize * 4;

	            // Count padding bytes
	            var nPaddingBytes = blockSizeBytes - data.sigBytes % blockSizeBytes;

	            // Create padding word
	            var paddingWord = (nPaddingBytes << 24) | (nPaddingBytes << 16) | (nPaddingBytes << 8) | nPaddingBytes;

	            // Create padding
	            var paddingWords = [];
	            for (var i = 0; i < nPaddingBytes; i += 4) {
	                paddingWords.push(paddingWord);
	            }
	            var padding = WordArray.create(paddingWords, nPaddingBytes);

	            // Add padding
	            data.concat(padding);
	        },

	        /**
	         * Unpads data that had been padded using the algorithm defined in PKCS #5/7.
	         *
	         * @param {WordArray} data The data to unpad.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     CryptoJS.pad.Pkcs7.unpad(wordArray);
	         */
	        unpad: function (data) {
	            // Get number of padding bytes from last byte
	            var nPaddingBytes = data.words[(data.sigBytes - 1) >>> 2] & 0xff;

	            // Remove padding
	            data.sigBytes -= nPaddingBytes;
	        }
	    };

	    /**
	     * Abstract base block cipher template.
	     *
	     * @property {number} blockSize The number of 32-bit words this cipher operates on. Default: 4 (128 bits)
	     */
	    var BlockCipher = C_lib.BlockCipher = Cipher.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {Mode} mode The block mode to use. Default: CBC
	         * @property {Padding} padding The padding strategy to use. Default: Pkcs7
	         */
	        cfg: Cipher.cfg.extend({
	            mode: CBC,
	            padding: Pkcs7
	        }),

	        reset: function () {
	            // Reset cipher
	            Cipher.reset.call(this);

	            // Shortcuts
	            var cfg = this.cfg;
	            var iv = cfg.iv;
	            var mode = cfg.mode;

	            // Reset block mode
	            if (this._xformMode == this._ENC_XFORM_MODE) {
	                var modeCreator = mode.createEncryptor;
	            } else /* if (this._xformMode == this._DEC_XFORM_MODE) */ {
	                var modeCreator = mode.createDecryptor;
	                // Keep at least one block in the buffer for unpadding
	                this._minBufferSize = 1;
	            }

	            if (this._mode && this._mode.__creator == modeCreator) {
	                this._mode.init(this, iv && iv.words);
	            } else {
	                this._mode = modeCreator.call(mode, this, iv && iv.words);
	                this._mode.__creator = modeCreator;
	            }
	        },

	        _doProcessBlock: function (words, offset) {
	            this._mode.processBlock(words, offset);
	        },

	        _doFinalize: function () {
	            // Shortcut
	            var padding = this.cfg.padding;

	            // Finalize
	            if (this._xformMode == this._ENC_XFORM_MODE) {
	                // Pad data
	                padding.pad(this._data, this.blockSize);

	                // Process final blocks
	                var finalProcessedBlocks = this._process(!!'flush');
	            } else /* if (this._xformMode == this._DEC_XFORM_MODE) */ {
	                // Process final blocks
	                var finalProcessedBlocks = this._process(!!'flush');

	                // Unpad data
	                padding.unpad(finalProcessedBlocks);
	            }

	            return finalProcessedBlocks;
	        },

	        blockSize: 128/32
	    });

	    /**
	     * A collection of cipher parameters.
	     *
	     * @property {WordArray} ciphertext The raw ciphertext.
	     * @property {WordArray} key The key to this ciphertext.
	     * @property {WordArray} iv The IV used in the ciphering operation.
	     * @property {WordArray} salt The salt used with a key derivation function.
	     * @property {Cipher} algorithm The cipher algorithm.
	     * @property {Mode} mode The block mode used in the ciphering operation.
	     * @property {Padding} padding The padding scheme used in the ciphering operation.
	     * @property {number} blockSize The block size of the cipher.
	     * @property {Format} formatter The default formatting strategy to convert this cipher params object to a string.
	     */
	    var CipherParams = C_lib.CipherParams = Base.extend({
	        /**
	         * Initializes a newly created cipher params object.
	         *
	         * @param {Object} cipherParams An object with any of the possible cipher parameters.
	         *
	         * @example
	         *
	         *     var cipherParams = CryptoJS.lib.CipherParams.create({
	         *         ciphertext: ciphertextWordArray,
	         *         key: keyWordArray,
	         *         iv: ivWordArray,
	         *         salt: saltWordArray,
	         *         algorithm: CryptoJS.algo.AES,
	         *         mode: CryptoJS.mode.CBC,
	         *         padding: CryptoJS.pad.PKCS7,
	         *         blockSize: 4,
	         *         formatter: CryptoJS.format.OpenSSL
	         *     });
	         */
	        init: function (cipherParams) {
	            this.mixIn(cipherParams);
	        },

	        /**
	         * Converts this cipher params object to a string.
	         *
	         * @param {Format} formatter (Optional) The formatting strategy to use.
	         *
	         * @return {string} The stringified cipher params.
	         *
	         * @throws Error If neither the formatter nor the default formatter is set.
	         *
	         * @example
	         *
	         *     var string = cipherParams + '';
	         *     var string = cipherParams.toString();
	         *     var string = cipherParams.toString(CryptoJS.format.OpenSSL);
	         */
	        toString: function (formatter) {
	            return (formatter || this.formatter).stringify(this);
	        }
	    });

	    /**
	     * Format namespace.
	     */
	    var C_format = C.format = {};

	    /**
	     * OpenSSL formatting strategy.
	     */
	    var OpenSSLFormatter = C_format.OpenSSL = {
	        /**
	         * Converts a cipher params object to an OpenSSL-compatible string.
	         *
	         * @param {CipherParams} cipherParams The cipher params object.
	         *
	         * @return {string} The OpenSSL-compatible string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var openSSLString = CryptoJS.format.OpenSSL.stringify(cipherParams);
	         */
	        stringify: function (cipherParams) {
	            // Shortcuts
	            var ciphertext = cipherParams.ciphertext;
	            var salt = cipherParams.salt;

	            // Format
	            if (salt) {
	                var wordArray = WordArray.create([0x53616c74, 0x65645f5f]).concat(salt).concat(ciphertext);
	            } else {
	                var wordArray = ciphertext;
	            }

	            return wordArray.toString(Base64);
	        },

	        /**
	         * Converts an OpenSSL-compatible string to a cipher params object.
	         *
	         * @param {string} openSSLStr The OpenSSL-compatible string.
	         *
	         * @return {CipherParams} The cipher params object.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var cipherParams = CryptoJS.format.OpenSSL.parse(openSSLString);
	         */
	        parse: function (openSSLStr) {
	            // Parse base64
	            var ciphertext = Base64.parse(openSSLStr);

	            // Shortcut
	            var ciphertextWords = ciphertext.words;

	            // Test for salt
	            if (ciphertextWords[0] == 0x53616c74 && ciphertextWords[1] == 0x65645f5f) {
	                // Extract salt
	                var salt = WordArray.create(ciphertextWords.slice(2, 4));

	                // Remove salt from ciphertext
	                ciphertextWords.splice(0, 4);
	                ciphertext.sigBytes -= 16;
	            }

	            return CipherParams.create({ ciphertext: ciphertext, salt: salt });
	        }
	    };

	    /**
	     * A cipher wrapper that returns ciphertext as a serializable cipher params object.
	     */
	    var SerializableCipher = C_lib.SerializableCipher = Base.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {Formatter} format The formatting strategy to convert cipher param objects to and from a string. Default: OpenSSL
	         */
	        cfg: Base.extend({
	            format: OpenSSLFormatter
	        }),

	        /**
	         * Encrypts a message.
	         *
	         * @param {Cipher} cipher The cipher algorithm to use.
	         * @param {WordArray|string} message The message to encrypt.
	         * @param {WordArray} key The key.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {CipherParams} A cipher params object.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var ciphertextParams = CryptoJS.lib.SerializableCipher.encrypt(CryptoJS.algo.AES, message, key);
	         *     var ciphertextParams = CryptoJS.lib.SerializableCipher.encrypt(CryptoJS.algo.AES, message, key, { iv: iv });
	         *     var ciphertextParams = CryptoJS.lib.SerializableCipher.encrypt(CryptoJS.algo.AES, message, key, { iv: iv, format: CryptoJS.format.OpenSSL });
	         */
	        encrypt: function (cipher, message, key, cfg) {
	            // Apply config defaults
	            cfg = this.cfg.extend(cfg);

	            // Encrypt
	            var encryptor = cipher.createEncryptor(key, cfg);
	            var ciphertext = encryptor.finalize(message);

	            // Shortcut
	            var cipherCfg = encryptor.cfg;

	            // Create and return serializable cipher params
	            return CipherParams.create({
	                ciphertext: ciphertext,
	                key: key,
	                iv: cipherCfg.iv,
	                algorithm: cipher,
	                mode: cipherCfg.mode,
	                padding: cipherCfg.padding,
	                blockSize: cipher.blockSize,
	                formatter: cfg.format
	            });
	        },

	        /**
	         * Decrypts serialized ciphertext.
	         *
	         * @param {Cipher} cipher The cipher algorithm to use.
	         * @param {CipherParams|string} ciphertext The ciphertext to decrypt.
	         * @param {WordArray} key The key.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {WordArray} The plaintext.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var plaintext = CryptoJS.lib.SerializableCipher.decrypt(CryptoJS.algo.AES, formattedCiphertext, key, { iv: iv, format: CryptoJS.format.OpenSSL });
	         *     var plaintext = CryptoJS.lib.SerializableCipher.decrypt(CryptoJS.algo.AES, ciphertextParams, key, { iv: iv, format: CryptoJS.format.OpenSSL });
	         */
	        decrypt: function (cipher, ciphertext, key, cfg) {
	            // Apply config defaults
	            cfg = this.cfg.extend(cfg);

	            // Convert string to CipherParams
	            ciphertext = this._parse(ciphertext, cfg.format);

	            // Decrypt
	            var plaintext = cipher.createDecryptor(key, cfg).finalize(ciphertext.ciphertext);

	            return plaintext;
	        },

	        /**
	         * Converts serialized ciphertext to CipherParams,
	         * else assumed CipherParams already and returns ciphertext unchanged.
	         *
	         * @param {CipherParams|string} ciphertext The ciphertext.
	         * @param {Formatter} format The formatting strategy to use to parse serialized ciphertext.
	         *
	         * @return {CipherParams} The unserialized ciphertext.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var ciphertextParams = CryptoJS.lib.SerializableCipher._parse(ciphertextStringOrParams, format);
	         */
	        _parse: function (ciphertext, format) {
	            if (typeof ciphertext == 'string') {
	                return format.parse(ciphertext, this);
	            } else {
	                return ciphertext;
	            }
	        }
	    });

	    /**
	     * Key derivation function namespace.
	     */
	    var C_kdf = C.kdf = {};

	    /**
	     * OpenSSL key derivation function.
	     */
	    var OpenSSLKdf = C_kdf.OpenSSL = {
	        /**
	         * Derives a key and IV from a password.
	         *
	         * @param {string} password The password to derive from.
	         * @param {number} keySize The size in words of the key to generate.
	         * @param {number} ivSize The size in words of the IV to generate.
	         * @param {WordArray|string} salt (Optional) A 64-bit salt to use. If omitted, a salt will be generated randomly.
	         *
	         * @return {CipherParams} A cipher params object with the key, IV, and salt.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var derivedParams = CryptoJS.kdf.OpenSSL.execute('Password', 256/32, 128/32);
	         *     var derivedParams = CryptoJS.kdf.OpenSSL.execute('Password', 256/32, 128/32, 'saltsalt');
	         */
	        execute: function (password, keySize, ivSize, salt) {
	            // Generate random salt
	            if (!salt) {
	                salt = WordArray.random(64/8);
	            }

	            // Derive key and IV
	            var key = EvpKDF.create({ keySize: keySize + ivSize }).compute(password, salt);

	            // Separate key and IV
	            var iv = WordArray.create(key.words.slice(keySize), ivSize * 4);
	            key.sigBytes = keySize * 4;

	            // Return params
	            return CipherParams.create({ key: key, iv: iv, salt: salt });
	        }
	    };

	    /**
	     * A serializable cipher wrapper that derives the key from a password,
	     * and returns ciphertext as a serializable cipher params object.
	     */
	    var PasswordBasedCipher = C_lib.PasswordBasedCipher = SerializableCipher.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {KDF} kdf The key derivation function to use to generate a key and IV from a password. Default: OpenSSL
	         */
	        cfg: SerializableCipher.cfg.extend({
	            kdf: OpenSSLKdf
	        }),

	        /**
	         * Encrypts a message using a password.
	         *
	         * @param {Cipher} cipher The cipher algorithm to use.
	         * @param {WordArray|string} message The message to encrypt.
	         * @param {string} password The password.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {CipherParams} A cipher params object.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var ciphertextParams = CryptoJS.lib.PasswordBasedCipher.encrypt(CryptoJS.algo.AES, message, 'password');
	         *     var ciphertextParams = CryptoJS.lib.PasswordBasedCipher.encrypt(CryptoJS.algo.AES, message, 'password', { format: CryptoJS.format.OpenSSL });
	         */
	        encrypt: function (cipher, message, password, cfg) {
	            // Apply config defaults
	            cfg = this.cfg.extend(cfg);

	            // Derive key and other params
	            var derivedParams = cfg.kdf.execute(password, cipher.keySize, cipher.ivSize);

	            // Add IV to config
	            cfg.iv = derivedParams.iv;

	            // Encrypt
	            var ciphertext = SerializableCipher.encrypt.call(this, cipher, message, derivedParams.key, cfg);

	            // Mix in derived params
	            ciphertext.mixIn(derivedParams);

	            return ciphertext;
	        },

	        /**
	         * Decrypts serialized ciphertext using a password.
	         *
	         * @param {Cipher} cipher The cipher algorithm to use.
	         * @param {CipherParams|string} ciphertext The ciphertext to decrypt.
	         * @param {string} password The password.
	         * @param {Object} cfg (Optional) The configuration options to use for this operation.
	         *
	         * @return {WordArray} The plaintext.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var plaintext = CryptoJS.lib.PasswordBasedCipher.decrypt(CryptoJS.algo.AES, formattedCiphertext, 'password', { format: CryptoJS.format.OpenSSL });
	         *     var plaintext = CryptoJS.lib.PasswordBasedCipher.decrypt(CryptoJS.algo.AES, ciphertextParams, 'password', { format: CryptoJS.format.OpenSSL });
	         */
	        decrypt: function (cipher, ciphertext, password, cfg) {
	            // Apply config defaults
	            cfg = this.cfg.extend(cfg);

	            // Convert string to CipherParams
	            ciphertext = this._parse(ciphertext, cfg.format);

	            // Derive key and other params
	            var derivedParams = cfg.kdf.execute(password, cipher.keySize, cipher.ivSize, ciphertext.salt);

	            // Add IV to config
	            cfg.iv = derivedParams.iv;

	            // Decrypt
	            var plaintext = SerializableCipher.decrypt.call(this, cipher, ciphertext, derivedParams.key, cfg);

	            return plaintext;
	        }
	    });
	}());


}));

/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "cbb373a511e5f18b4239591f18cd6cac.png";

/***/ }),
/* 3 */
/***/ (function(module, exports) {

/*
	MIT License http://www.opensource.org/licenses/mit-license.php
	Author Tobias Koppers @sokra
*/
// css base code, injected by the css-loader
module.exports = function(useSourceMap) {
	var list = [];

	// return the list of modules as css string
	list.toString = function toString() {
		return this.map(function (item) {
			var content = cssWithMappingToString(item, useSourceMap);
			if(item[2]) {
				return "@media " + item[2] + "{" + content + "}";
			} else {
				return content;
			}
		}).join("");
	};

	// import a list of modules into the list
	list.i = function(modules, mediaQuery) {
		if(typeof modules === "string")
			modules = [[null, modules, ""]];
		var alreadyImportedModules = {};
		for(var i = 0; i < this.length; i++) {
			var id = this[i][0];
			if(typeof id === "number")
				alreadyImportedModules[id] = true;
		}
		for(i = 0; i < modules.length; i++) {
			var item = modules[i];
			// skip already imported module
			// this implementation is not 100% perfect for weird media query combinations
			//  when a module is imported multiple times with different media queries.
			//  I hope this will never occur (Hey this way we have smaller bundles)
			if(typeof item[0] !== "number" || !alreadyImportedModules[item[0]]) {
				if(mediaQuery && !item[2]) {
					item[2] = mediaQuery;
				} else if(mediaQuery) {
					item[2] = "(" + item[2] + ") and (" + mediaQuery + ")";
				}
				list.push(item);
			}
		}
	};
	return list;
};

function cssWithMappingToString(item, useSourceMap) {
	var content = item[1] || '';
	var cssMapping = item[3];
	if (!cssMapping) {
		return content;
	}

	if (useSourceMap && typeof btoa === 'function') {
		var sourceMapping = toComment(cssMapping);
		var sourceURLs = cssMapping.sources.map(function (source) {
			return '/*# sourceURL=' + cssMapping.sourceRoot + source + ' */'
		});

		return [content].concat(sourceURLs).concat([sourceMapping]).join('\n');
	}

	return [content].join('\n');
}

// Adapted from convert-source-map (MIT)
function toComment(sourceMap) {
	// eslint-disable-next-line no-undef
	var base64 = btoa(unescape(encodeURIComponent(JSON.stringify(sourceMap))));
	var data = 'sourceMappingURL=data:application/json;charset=utf-8;base64,' + base64;

	return '/*# ' + data + ' */';
}


/***/ }),
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

/*
	MIT License http://www.opensource.org/licenses/mit-license.php
	Author Tobias Koppers @sokra
*/

var stylesInDom = {};

var	memoize = function (fn) {
	var memo;

	return function () {
		if (typeof memo === "undefined") memo = fn.apply(this, arguments);
		return memo;
	};
};

var isOldIE = memoize(function () {
	// Test for IE <= 9 as proposed by Browserhacks
	// @see http://browserhacks.com/#hack-e71d8692f65334173fee715c222cb805
	// Tests for existence of standard globals is to allow style-loader
	// to operate correctly into non-standard environments
	// @see https://github.com/webpack-contrib/style-loader/issues/177
	return window && document && document.all && !window.atob;
});

var getElement = (function (fn) {
	var memo = {};

	return function(selector) {
		if (typeof memo[selector] === "undefined") {
			var styleTarget = fn.call(this, selector);
			// Special case to return head of iframe instead of iframe itself
			if (styleTarget instanceof window.HTMLIFrameElement) {
				try {
					// This will throw an exception if access to iframe is blocked
					// due to cross-origin restrictions
					styleTarget = styleTarget.contentDocument.head;
				} catch(e) {
					styleTarget = null;
				}
			}
			memo[selector] = styleTarget;
		}
		return memo[selector]
	};
})(function (target) {
	return document.querySelector(target)
});

var singleton = null;
var	singletonCounter = 0;
var	stylesInsertedAtTop = [];

var	fixUrls = __webpack_require__(24);

module.exports = function(list, options) {
	if (typeof DEBUG !== "undefined" && DEBUG) {
		if (typeof document !== "object") throw new Error("The style-loader cannot be used in a non-browser environment");
	}

	options = options || {};

	options.attrs = typeof options.attrs === "object" ? options.attrs : {};

	// Force single-tag solution on IE6-9, which has a hard limit on the # of <style>
	// tags it will allow on a page
	if (!options.singleton) options.singleton = isOldIE();

	// By default, add <style> tags to the <head> element
	if (!options.insertInto) options.insertInto = "head";

	// By default, add <style> tags to the bottom of the target
	if (!options.insertAt) options.insertAt = "bottom";

	var styles = listToStyles(list, options);

	addStylesToDom(styles, options);

	return function update (newList) {
		var mayRemove = [];

		for (var i = 0; i < styles.length; i++) {
			var item = styles[i];
			var domStyle = stylesInDom[item.id];

			domStyle.refs--;
			mayRemove.push(domStyle);
		}

		if(newList) {
			var newStyles = listToStyles(newList, options);
			addStylesToDom(newStyles, options);
		}

		for (var i = 0; i < mayRemove.length; i++) {
			var domStyle = mayRemove[i];

			if(domStyle.refs === 0) {
				for (var j = 0; j < domStyle.parts.length; j++) domStyle.parts[j]();

				delete stylesInDom[domStyle.id];
			}
		}
	};
};

function addStylesToDom (styles, options) {
	for (var i = 0; i < styles.length; i++) {
		var item = styles[i];
		var domStyle = stylesInDom[item.id];

		if(domStyle) {
			domStyle.refs++;

			for(var j = 0; j < domStyle.parts.length; j++) {
				domStyle.parts[j](item.parts[j]);
			}

			for(; j < item.parts.length; j++) {
				domStyle.parts.push(addStyle(item.parts[j], options));
			}
		} else {
			var parts = [];

			for(var j = 0; j < item.parts.length; j++) {
				parts.push(addStyle(item.parts[j], options));
			}

			stylesInDom[item.id] = {id: item.id, refs: 1, parts: parts};
		}
	}
}

function listToStyles (list, options) {
	var styles = [];
	var newStyles = {};

	for (var i = 0; i < list.length; i++) {
		var item = list[i];
		var id = options.base ? item[0] + options.base : item[0];
		var css = item[1];
		var media = item[2];
		var sourceMap = item[3];
		var part = {css: css, media: media, sourceMap: sourceMap};

		if(!newStyles[id]) styles.push(newStyles[id] = {id: id, parts: [part]});
		else newStyles[id].parts.push(part);
	}

	return styles;
}

function insertStyleElement (options, style) {
	var target = getElement(options.insertInto)

	if (!target) {
		throw new Error("Couldn't find a style target. This probably means that the value for the 'insertInto' parameter is invalid.");
	}

	var lastStyleElementInsertedAtTop = stylesInsertedAtTop[stylesInsertedAtTop.length - 1];

	if (options.insertAt === "top") {
		if (!lastStyleElementInsertedAtTop) {
			target.insertBefore(style, target.firstChild);
		} else if (lastStyleElementInsertedAtTop.nextSibling) {
			target.insertBefore(style, lastStyleElementInsertedAtTop.nextSibling);
		} else {
			target.appendChild(style);
		}
		stylesInsertedAtTop.push(style);
	} else if (options.insertAt === "bottom") {
		target.appendChild(style);
	} else if (typeof options.insertAt === "object" && options.insertAt.before) {
		var nextSibling = getElement(options.insertInto + " " + options.insertAt.before);
		target.insertBefore(style, nextSibling);
	} else {
		throw new Error("[Style Loader]\n\n Invalid value for parameter 'insertAt' ('options.insertAt') found.\n Must be 'top', 'bottom', or Object.\n (https://github.com/webpack-contrib/style-loader#insertat)\n");
	}
}

function removeStyleElement (style) {
	if (style.parentNode === null) return false;
	style.parentNode.removeChild(style);

	var idx = stylesInsertedAtTop.indexOf(style);
	if(idx >= 0) {
		stylesInsertedAtTop.splice(idx, 1);
	}
}

function createStyleElement (options) {
	var style = document.createElement("style");

	options.attrs.type = "text/css";

	addAttrs(style, options.attrs);
	insertStyleElement(options, style);

	return style;
}

function createLinkElement (options) {
	var link = document.createElement("link");

	options.attrs.type = "text/css";
	options.attrs.rel = "stylesheet";

	addAttrs(link, options.attrs);
	insertStyleElement(options, link);

	return link;
}

function addAttrs (el, attrs) {
	Object.keys(attrs).forEach(function (key) {
		el.setAttribute(key, attrs[key]);
	});
}

function addStyle (obj, options) {
	var style, update, remove, result;

	// If a transform function was defined, run it on the css
	if (options.transform && obj.css) {
	    result = options.transform(obj.css);

	    if (result) {
	    	// If transform returns a value, use that instead of the original css.
	    	// This allows running runtime transformations on the css.
	    	obj.css = result;
	    } else {
	    	// If the transform function returns a falsy value, don't add this css.
	    	// This allows conditional loading of css
	    	return function() {
	    		// noop
	    	};
	    }
	}

	if (options.singleton) {
		var styleIndex = singletonCounter++;

		style = singleton || (singleton = createStyleElement(options));

		update = applyToSingletonTag.bind(null, style, styleIndex, false);
		remove = applyToSingletonTag.bind(null, style, styleIndex, true);

	} else if (
		obj.sourceMap &&
		typeof URL === "function" &&
		typeof URL.createObjectURL === "function" &&
		typeof URL.revokeObjectURL === "function" &&
		typeof Blob === "function" &&
		typeof btoa === "function"
	) {
		style = createLinkElement(options);
		update = updateLink.bind(null, style, options);
		remove = function () {
			removeStyleElement(style);

			if(style.href) URL.revokeObjectURL(style.href);
		};
	} else {
		style = createStyleElement(options);
		update = applyToTag.bind(null, style);
		remove = function () {
			removeStyleElement(style);
		};
	}

	update(obj);

	return function updateStyle (newObj) {
		if (newObj) {
			if (
				newObj.css === obj.css &&
				newObj.media === obj.media &&
				newObj.sourceMap === obj.sourceMap
			) {
				return;
			}

			update(obj = newObj);
		} else {
			remove();
		}
	};
}

var replaceText = (function () {
	var textStore = [];

	return function (index, replacement) {
		textStore[index] = replacement;

		return textStore.filter(Boolean).join('\n');
	};
})();

function applyToSingletonTag (style, index, remove, obj) {
	var css = remove ? "" : obj.css;

	if (style.styleSheet) {
		style.styleSheet.cssText = replaceText(index, css);
	} else {
		var cssNode = document.createTextNode(css);
		var childNodes = style.childNodes;

		if (childNodes[index]) style.removeChild(childNodes[index]);

		if (childNodes.length) {
			style.insertBefore(cssNode, childNodes[index]);
		} else {
			style.appendChild(cssNode);
		}
	}
}

function applyToTag (style, obj) {
	var css = obj.css;
	var media = obj.media;

	if(media) {
		style.setAttribute("media", media)
	}

	if(style.styleSheet) {
		style.styleSheet.cssText = css;
	} else {
		while(style.firstChild) {
			style.removeChild(style.firstChild);
		}

		style.appendChild(document.createTextNode(css));
	}
}

function updateLink (link, options, obj) {
	var css = obj.css;
	var sourceMap = obj.sourceMap;

	/*
		If convertToAbsoluteUrls isn't defined, but sourcemaps are enabled
		and there is no publicPath defined then lets turn convertToAbsoluteUrls
		on by default.  Otherwise default to the convertToAbsoluteUrls option
		directly
	*/
	var autoFixUrls = options.convertToAbsoluteUrls === undefined && sourceMap;

	if (options.convertToAbsoluteUrls || autoFixUrls) {
		css = fixUrls(css);
	}

	if (sourceMap) {
		// http://stackoverflow.com/a/26603875
		css += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(sourceMap)))) + " */";
	}

	var blob = new Blob([css], { type: "text/css" });

	var oldSrc = link.href;

	link.href = URL.createObjectURL(blob);

	if(oldSrc) URL.revokeObjectURL(oldSrc);
}


/***/ }),
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 工具类
 */

__webpack_require__(25);
var md5 = __webpack_require__(17);
var Fingerprint = __webpack_require__(29);
var platform = __webpack_require__(30);
var Toast = __webpack_require__(32);
var Global = __webpack_require__(7);
var CryptoJS = __webpack_require__(33);

var util = {
  addFrame: function (src, name, callback) {
    var iframe = document.createElement('iframe');
    iframe.id = name;
    iframe.id = name;
    iframe.scrolling = 'no';
    iframe.src = src;
    iframe.style.width = '100%';
    iframe.style.height = '100%';
    iframe.style.position = 'absolute';
    iframe.style.top = 0;
    iframe.style.left = 0;
    iframe.style.border = 0;
    iframe.setAttribute('allowtransparency', 'yes');
    iframe.setAttribute('frameborder', 'no');
    document.body.appendChild(iframe);
    // callback用于监控iframe的变化
    iframe.onload = callback(iframe);
  },

  // 动态引入js文件
  import: function (src, callback) {
    var script = document.createElement('script');
    script.src = src;
    document.body.appendChild(script);
    if (script.readyState) {
      script.onreadystatechange = function () {
        if (script.readyState == 'complete' || script.readyState == 'loaded') {
          script.onreadystatechange = null;
          callback();
        }
      };
    } else {
      script.onload = function () {
        callback();
      };
    }
  },

  importCss: function (path) {
    var head = document.getElementsByTagName('head')[0];
    var link = document.createElement('link');
    link.href = path;
    link.rel = 'stylesheet';
    link.type = 'text/css';
    head.appendChild(link);
  },

  cookie: function (name, value, days) {
    // if value is undefined, get the cookie value
    if (value === undefined) {
      var cookiestring = '; ' + document.cookie;
      var cookies = cookiestring.split('; ' + name + '=');
      if (cookies.length > 1) {
        return cookies.pop().split(';').shift();
      }
      return null;
    } else {
      // if value is a false boolean, we'll treat that as a delete
      if (value === false) {
        days = -1;
      }
      var expires = '';
      if (days) {
        var date = new Date();
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        expires = '; expires=' + date.toGMTString();
      }
      document.cookie = name + '=' + value + expires + '; path=/';
    }
  },

  parseUrl: function (url) {
    var parser = document.createElement('a'),
      searchObject = {},
      queries,
      split,
      i;

    parser.href = url;
    var queryStr = parser.search.replace(/^\?/, '');
    // 防止url中还有其他的?号
    queryStr = queryStr.replace('?', '&');
    queries = queryStr.split('&');
    for (i = 0; i < queries.length; i++) {
      split = queries[i].split('=');
      if (searchObject[split[0]]) {
        continue;
      }
      searchObject[split[0]] = split[1];
    }
    return {
      protocol: parser.protocol,
      host: parser.host,
      hostname: parser.hostname,
      port: parser.port,
      pathname: parser.pathname,
      search: parser.search,
      searchObject: searchObject,
      hash: parser.hash,
    };
  },

  buildQuery: function (params) {
    var queryString = '';
    var keys = this.getKeys(params);
    var length = keys.length;
    for (var i = 0; i < length; i++) {
      queryString +=
        encodeURIComponent(keys[i]) + '=' + encodeURIComponent(params[keys[i]]);
      if (i != length - 1) {
        queryString += '&';
      }
    }
    return queryString;
  },

  getKeys: function (obj) {
    var keys = [];
    for (var key in obj) {
      if (obj.hasOwnProperty(key)) {
        keys.push(key);
      }
    }
    return keys;
  },

  /**
   * 抛出异常
   * @param message
   */
  throw: function (message) {
    alert(message);
  },

  /**
   *
   * @returns {number}
   */
  getTime: function () {
    return Date.parse(new Date()) / 1000;
  },

  /**
   * 合并对象
   * @param a
   * @param b
   * @returns {*}
   */
  mixin: function (a, b) {
    for (var key in b) a[key] = b[key];
    return a;
  },

  sortObject: function (o) {
    var sorted = {},
      key,
      keys = [];

    for (key in o) {
      if (o.hasOwnProperty(key)) {
        keys.push(key);
      }
    }

    keys.sort();

    for (key = 0; key < keys.length; key++) {
      sorted[keys[key]] = o[keys[key]];
    }
    return sorted;
  },

  getSign: function (params, key) {
    var str = ''; //待签名字符串
    //先将参数以其参数名的字典序升序进行排序
    params = this.sortObject(params);
    //遍历排序后的参数数组中的每一个key/value对
    for (var k in params) {
      //为key/value对生成一个key=value格式的字符串，并拼接到待签名字符串后面
      var pair = k + '=' + params[k];
      str += pair;
    }
    //将签名密钥拼接到签名字符串最后面
    str += key;
    //通过md5算法为签名字符串生成一个md5签名，该签名就是我们要追加的sign参数值
    return md5(str);
  },

  getDeviceInfo: function (callback) {
    new Fingerprint().get(function (fingerprint, components) {
      // console.log(result); //a hash, representing your device fingerprint
      // console.log(components); // an array of FP components
      var result = {
        fingerprint: fingerprint,
        name: platform.name,
        version: platform.version,
        os: platform.os,
        description: platform.description,
      };
      callback(result);
    });
  },

  /**
   * 简单的加密解密算法
   * @param str
   * @returns {string}
   */
  encode: function (code) {
    var c = String.fromCharCode(code.charCodeAt(0) + code.length);
    for (var i = 1; i < code.length; i++) {
      c += String.fromCharCode(code.charCodeAt(i) + code.charCodeAt(i - 1));
    }
    return encodeURIComponent(c);
  },

  decode: function (code) {
    code = decodeURIComponent(code);
    var c = String.fromCharCode(code.charCodeAt(0) - code.length);
    for (var i = 1; i < code.length; i++) {
      c += String.fromCharCode(code.charCodeAt(i) - c.charCodeAt(i - 1));
    }
    return c;
  },

  /**
   * 提示消息
   * @param message
   * @param type ['success', 'info', 'warning', 'error']
   */
  toast: function (message, type) {
    // todo
    Toast({
      message: message,
      position: 'bottom',
      timeout: 2000,
      type: type,
    });
  },
  minortoast: function (message, type) {
    // todo
    Toast({
      message: message,
      position: 'center',
      timeout: 10000,
      type: 'warning',
      icon: true,
      closeOnClick: true,
    });
  },

  loading: function (show) {
    var dom = document.getElementById('loading');
    if (dom) {
      show ? (dom.style.display = 'block') : (dom.style.display = 'none');
    }
  },

  isWechat: function () {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
      return true;
    } else {
      return false;
    }
  },
  checkPhone: function (str) {
    var exp =
      /^1\d{10}$/;
    return exp.test(str);
  },

  // aes加密解密
  encrypt: function (plainText, key) {
    var key = CryptoJS.enc.Utf8.parse(key);
    var srcs = CryptoJS.enc.Utf8.parse(plainText);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    return encrypted.toString();
  },

  decrypt: function (encryptText, key) {
    var key = CryptoJS.enc.Utf8.parse(key);
    var decrypt = CryptoJS.AES.decrypt(encryptText.toString(), key, {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
  },

  isInApp: function () {
    //根据链接附带的参数判断游戏是否在webview中打开
    if (Global.urlInfo.searchObject.wv) {
      return true;
    }
    return false;
  },
  //判断链接是不是图片
  isImg: function (src) {
    var img = document.createElement('img');
    img.src = src;
    return new Promise(function (resolve, reject) {
      img.onerror = function () {
        resolve(undefined);
      };
      img.onload = function () {
        resolve(src);
      };
    });
  },
  /**
 * @function parseUrl
 * @return {object} args 为 对象，为键值对形式
 * @desc 解析 url 所带的参数
 */
  urlStringToObj: function (str) {
    var args = {}
    var pairs = str.split('&')
    for (var i = 0; i < pairs.length; i++) {
      var pos = pairs[i].indexOf('=')
      if (pos === -1) {
        continue
      }
      var name = pairs[i].substring(0, pos)
      var val = pairs[i].substring(pos + 1)
      val = decodeURIComponent(val)
      args[name] = val
    }
    return args
  },
};

module.exports = util;


/***/ }),
/* 6 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(14), __webpack_require__(15));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./sha1", "./hmac"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Base = C_lib.Base;
	    var WordArray = C_lib.WordArray;
	    var C_algo = C.algo;
	    var MD5 = C_algo.MD5;

	    /**
	     * This key derivation function is meant to conform with EVP_BytesToKey.
	     * www.openssl.org/docs/crypto/EVP_BytesToKey.html
	     */
	    var EvpKDF = C_algo.EvpKDF = Base.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {number} keySize The key size in words to generate. Default: 4 (128 bits)
	         * @property {Hasher} hasher The hash algorithm to use. Default: MD5
	         * @property {number} iterations The number of iterations to perform. Default: 1
	         */
	        cfg: Base.extend({
	            keySize: 128/32,
	            hasher: MD5,
	            iterations: 1
	        }),

	        /**
	         * Initializes a newly created key derivation function.
	         *
	         * @param {Object} cfg (Optional) The configuration options to use for the derivation.
	         *
	         * @example
	         *
	         *     var kdf = CryptoJS.algo.EvpKDF.create();
	         *     var kdf = CryptoJS.algo.EvpKDF.create({ keySize: 8 });
	         *     var kdf = CryptoJS.algo.EvpKDF.create({ keySize: 8, iterations: 1000 });
	         */
	        init: function (cfg) {
	            this.cfg = this.cfg.extend(cfg);
	        },

	        /**
	         * Derives a key from a password.
	         *
	         * @param {WordArray|string} password The password.
	         * @param {WordArray|string} salt A salt.
	         *
	         * @return {WordArray} The derived key.
	         *
	         * @example
	         *
	         *     var key = kdf.compute(password, salt);
	         */
	        compute: function (password, salt) {
	            // Shortcut
	            var cfg = this.cfg;

	            // Init hasher
	            var hasher = cfg.hasher.create();

	            // Initial values
	            var derivedKey = WordArray.create();

	            // Shortcuts
	            var derivedKeyWords = derivedKey.words;
	            var keySize = cfg.keySize;
	            var iterations = cfg.iterations;

	            // Generate key
	            while (derivedKeyWords.length < keySize) {
	                if (block) {
	                    hasher.update(block);
	                }
	                var block = hasher.update(password).finalize(salt);
	                hasher.reset();

	                // Iterations
	                for (var i = 1; i < iterations; i++) {
	                    block = hasher.finalize(block);
	                    hasher.reset();
	                }

	                derivedKey.concat(block);
	            }
	            derivedKey.sigBytes = keySize * 4;

	            return derivedKey;
	        }
	    });

	    /**
	     * Derives a key from a password.
	     *
	     * @param {WordArray|string} password The password.
	     * @param {WordArray|string} salt A salt.
	     * @param {Object} cfg (Optional) The configuration options to use for this computation.
	     *
	     * @return {WordArray} The derived key.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var key = CryptoJS.EvpKDF(password, salt);
	     *     var key = CryptoJS.EvpKDF(password, salt, { keySize: 8 });
	     *     var key = CryptoJS.EvpKDF(password, salt, { keySize: 8, iterations: 1000 });
	     */
	    C.EvpKDF = function (password, salt, cfg) {
	        return EvpKDF.create(cfg).compute(password, salt);
	    };
	}());


	return CryptoJS.EvpKDF;

}));

/***/ }),
/* 7 */
/***/ (function(module, exports) {

/**
 * 全局信息存储
 */
var Global = {
  config: {
    'pid': 1,
    'gid': 0,
    'refer': ''
  },
  urlInfo: {}, // 输入参数
  game: {},   // 游戏信息，由服务端接口返回
  user: {},   // 用户信息
  device: {}, // 设备信息
  api: {},    // 接口信息,由服务端接口返回
  anti_addiction: {}, //防近视公告配置信息,由服务端接口返回

  frame: {
    protocolFrame: 'protocolFrame',
    protocolWrapper: 'protocolWrapper',
    gameFrame: 'gameFrame',
    payFrame: 'payFrame',
    payFrameWrapper: 'payWrapper',
  },

  // 内部消息定义
  event: {
    PARTNER_READY: 'PARTNER_READY',
    PARTNER_LOGIN_SUCCESS: 'SQSDK_PLOGIN_SUCCESS',
    PARTNER_PAY_SUCCESS: 'SQSDK_PAY_SUCCESS',
    LOGIN_SUCCESS: 'SQSDK_LOGIN_SUCCESS',
    LOGOUT_SUCCESS: 'SQSDK_LOGOUT_SUCCESS',
    ENTER: 'SQSDK_ENTER',
    GET_EARINFO_SUCCESS: 'SQSDK_GET_EARINFO_SUCCESS',
      /*关注*/
    FOCUS_SUCCESS:"SQSDK_FOCUS_SUCCESS",
      /*分享*/
    SHARE_SUCCESS:"SQSDK_SHARE_SUCCESS",
      /*扩展功能*/
    EXTERNAL_SUCCESS: 'SQSDK_EXTERNAL_SUCCESS'
  },

  // client消息定义
  message: {
    MSG_LOGIN: 'MSG_LOGIN',
    MSG_ENTER_GAME: 'MSG_ENTER_GAME',
    MSG_CREATE_ROLE: 'MSG_CREATE_ROLE',
    MSG_ROLE_LEVEL_UP: 'MSG_ROLE_LEVEL_UP',
    MSG_PAY: 'MSG_PAY',
    MSG_LOGOUT: 'MSG_LOGOUT',
    GET_EARINFO: 'MSG_GET_EARINFO',
    MSG_FOCUS:'MSG_FOCUS',
    MSG_SHARE:'MSG_SHARE',
    MSG_EXTERNAL:'MSG_EXTERNAL',
    /*到达创角场景时消息定义*/
    MSG_PRE_CREATE_ROLE:'MSG_PRE_CREATE_ROLE',
    // 提供方法让子窗口可以直接用父窗口跳转
    MSG_LOCATION: 'MSG_LOCATION',
  },

  // 回调给client消息定义
  notify: {
    LOGIN_SUCCESS: 'NOTIFY_LOGIN_SUCCESS',
    ENTER_SUCCESS: 'NOTIFY_ENTER_SUCCESS',
    PAY_SUCCESS: 'NOTIFY_PAY_SUCCESS',
    GET_EARINFO_SUCCESS: 'NOTIFY_GET_EARINFO_SUCCESS',
    FOCUS_SUCCESS:' NOTIFY_FOCUS_SUCCESS',
    SHARE_SUCCESS:'NOTIFY_SHARE_SUCCESS',
    EXTERNAL_SUCCESS: 'NOTIFY_EXTERNAL_SUCCESS',
    LOGOUT_SUCCESS: 'NOTIFY_LOGOUT_SUCCESS',
  },
}

module.exports = Global


/***/ }),
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var C_enc = C.enc;

	    /**
	     * Base64 encoding strategy.
	     */
	    var Base64 = C_enc.Base64 = {
	        /**
	         * Converts a word array to a Base64 string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The Base64 string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var base64String = CryptoJS.enc.Base64.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            // Shortcuts
	            var words = wordArray.words;
	            var sigBytes = wordArray.sigBytes;
	            var map = this._map;

	            // Clamp excess bits
	            wordArray.clamp();

	            // Convert
	            var base64Chars = [];
	            for (var i = 0; i < sigBytes; i += 3) {
	                var byte1 = (words[i >>> 2]       >>> (24 - (i % 4) * 8))       & 0xff;
	                var byte2 = (words[(i + 1) >>> 2] >>> (24 - ((i + 1) % 4) * 8)) & 0xff;
	                var byte3 = (words[(i + 2) >>> 2] >>> (24 - ((i + 2) % 4) * 8)) & 0xff;

	                var triplet = (byte1 << 16) | (byte2 << 8) | byte3;

	                for (var j = 0; (j < 4) && (i + j * 0.75 < sigBytes); j++) {
	                    base64Chars.push(map.charAt((triplet >>> (6 * (3 - j))) & 0x3f));
	                }
	            }

	            // Add padding
	            var paddingChar = map.charAt(64);
	            if (paddingChar) {
	                while (base64Chars.length % 4) {
	                    base64Chars.push(paddingChar);
	                }
	            }

	            return base64Chars.join('');
	        },

	        /**
	         * Converts a Base64 string to a word array.
	         *
	         * @param {string} base64Str The Base64 string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Base64.parse(base64String);
	         */
	        parse: function (base64Str) {
	            // Shortcuts
	            var base64StrLength = base64Str.length;
	            var map = this._map;
	            var reverseMap = this._reverseMap;

	            if (!reverseMap) {
	                    reverseMap = this._reverseMap = [];
	                    for (var j = 0; j < map.length; j++) {
	                        reverseMap[map.charCodeAt(j)] = j;
	                    }
	            }

	            // Ignore padding
	            var paddingChar = map.charAt(64);
	            if (paddingChar) {
	                var paddingIndex = base64Str.indexOf(paddingChar);
	                if (paddingIndex !== -1) {
	                    base64StrLength = paddingIndex;
	                }
	            }

	            // Convert
	            return parseLoop(base64Str, base64StrLength, reverseMap);

	        },

	        _map: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/='
	    };

	    function parseLoop(base64Str, base64StrLength, reverseMap) {
	      var words = [];
	      var nBytes = 0;
	      for (var i = 0; i < base64StrLength; i++) {
	          if (i % 4) {
	              var bits1 = reverseMap[base64Str.charCodeAt(i - 1)] << ((i % 4) * 2);
	              var bits2 = reverseMap[base64Str.charCodeAt(i)] >>> (6 - (i % 4) * 2);
	              words[nBytes >>> 2] |= (bits1 | bits2) << (24 - (nBytes % 4) * 8);
	              nBytes++;
	          }
	      }
	      return WordArray.create(words, nBytes);
	    }
	}());


	return CryptoJS.enc.Base64;

}));

/***/ }),
/* 9 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function (Math) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var Hasher = C_lib.Hasher;
	    var C_algo = C.algo;

	    // Constants table
	    var T = [];

	    // Compute constants
	    (function () {
	        for (var i = 0; i < 64; i++) {
	            T[i] = (Math.abs(Math.sin(i + 1)) * 0x100000000) | 0;
	        }
	    }());

	    /**
	     * MD5 hash algorithm.
	     */
	    var MD5 = C_algo.MD5 = Hasher.extend({
	        _doReset: function () {
	            this._hash = new WordArray.init([
	                0x67452301, 0xefcdab89,
	                0x98badcfe, 0x10325476
	            ]);
	        },

	        _doProcessBlock: function (M, offset) {
	            // Swap endian
	            for (var i = 0; i < 16; i++) {
	                // Shortcuts
	                var offset_i = offset + i;
	                var M_offset_i = M[offset_i];

	                M[offset_i] = (
	                    (((M_offset_i << 8)  | (M_offset_i >>> 24)) & 0x00ff00ff) |
	                    (((M_offset_i << 24) | (M_offset_i >>> 8))  & 0xff00ff00)
	                );
	            }

	            // Shortcuts
	            var H = this._hash.words;

	            var M_offset_0  = M[offset + 0];
	            var M_offset_1  = M[offset + 1];
	            var M_offset_2  = M[offset + 2];
	            var M_offset_3  = M[offset + 3];
	            var M_offset_4  = M[offset + 4];
	            var M_offset_5  = M[offset + 5];
	            var M_offset_6  = M[offset + 6];
	            var M_offset_7  = M[offset + 7];
	            var M_offset_8  = M[offset + 8];
	            var M_offset_9  = M[offset + 9];
	            var M_offset_10 = M[offset + 10];
	            var M_offset_11 = M[offset + 11];
	            var M_offset_12 = M[offset + 12];
	            var M_offset_13 = M[offset + 13];
	            var M_offset_14 = M[offset + 14];
	            var M_offset_15 = M[offset + 15];

	            // Working varialbes
	            var a = H[0];
	            var b = H[1];
	            var c = H[2];
	            var d = H[3];

	            // Computation
	            a = FF(a, b, c, d, M_offset_0,  7,  T[0]);
	            d = FF(d, a, b, c, M_offset_1,  12, T[1]);
	            c = FF(c, d, a, b, M_offset_2,  17, T[2]);
	            b = FF(b, c, d, a, M_offset_3,  22, T[3]);
	            a = FF(a, b, c, d, M_offset_4,  7,  T[4]);
	            d = FF(d, a, b, c, M_offset_5,  12, T[5]);
	            c = FF(c, d, a, b, M_offset_6,  17, T[6]);
	            b = FF(b, c, d, a, M_offset_7,  22, T[7]);
	            a = FF(a, b, c, d, M_offset_8,  7,  T[8]);
	            d = FF(d, a, b, c, M_offset_9,  12, T[9]);
	            c = FF(c, d, a, b, M_offset_10, 17, T[10]);
	            b = FF(b, c, d, a, M_offset_11, 22, T[11]);
	            a = FF(a, b, c, d, M_offset_12, 7,  T[12]);
	            d = FF(d, a, b, c, M_offset_13, 12, T[13]);
	            c = FF(c, d, a, b, M_offset_14, 17, T[14]);
	            b = FF(b, c, d, a, M_offset_15, 22, T[15]);

	            a = GG(a, b, c, d, M_offset_1,  5,  T[16]);
	            d = GG(d, a, b, c, M_offset_6,  9,  T[17]);
	            c = GG(c, d, a, b, M_offset_11, 14, T[18]);
	            b = GG(b, c, d, a, M_offset_0,  20, T[19]);
	            a = GG(a, b, c, d, M_offset_5,  5,  T[20]);
	            d = GG(d, a, b, c, M_offset_10, 9,  T[21]);
	            c = GG(c, d, a, b, M_offset_15, 14, T[22]);
	            b = GG(b, c, d, a, M_offset_4,  20, T[23]);
	            a = GG(a, b, c, d, M_offset_9,  5,  T[24]);
	            d = GG(d, a, b, c, M_offset_14, 9,  T[25]);
	            c = GG(c, d, a, b, M_offset_3,  14, T[26]);
	            b = GG(b, c, d, a, M_offset_8,  20, T[27]);
	            a = GG(a, b, c, d, M_offset_13, 5,  T[28]);
	            d = GG(d, a, b, c, M_offset_2,  9,  T[29]);
	            c = GG(c, d, a, b, M_offset_7,  14, T[30]);
	            b = GG(b, c, d, a, M_offset_12, 20, T[31]);

	            a = HH(a, b, c, d, M_offset_5,  4,  T[32]);
	            d = HH(d, a, b, c, M_offset_8,  11, T[33]);
	            c = HH(c, d, a, b, M_offset_11, 16, T[34]);
	            b = HH(b, c, d, a, M_offset_14, 23, T[35]);
	            a = HH(a, b, c, d, M_offset_1,  4,  T[36]);
	            d = HH(d, a, b, c, M_offset_4,  11, T[37]);
	            c = HH(c, d, a, b, M_offset_7,  16, T[38]);
	            b = HH(b, c, d, a, M_offset_10, 23, T[39]);
	            a = HH(a, b, c, d, M_offset_13, 4,  T[40]);
	            d = HH(d, a, b, c, M_offset_0,  11, T[41]);
	            c = HH(c, d, a, b, M_offset_3,  16, T[42]);
	            b = HH(b, c, d, a, M_offset_6,  23, T[43]);
	            a = HH(a, b, c, d, M_offset_9,  4,  T[44]);
	            d = HH(d, a, b, c, M_offset_12, 11, T[45]);
	            c = HH(c, d, a, b, M_offset_15, 16, T[46]);
	            b = HH(b, c, d, a, M_offset_2,  23, T[47]);

	            a = II(a, b, c, d, M_offset_0,  6,  T[48]);
	            d = II(d, a, b, c, M_offset_7,  10, T[49]);
	            c = II(c, d, a, b, M_offset_14, 15, T[50]);
	            b = II(b, c, d, a, M_offset_5,  21, T[51]);
	            a = II(a, b, c, d, M_offset_12, 6,  T[52]);
	            d = II(d, a, b, c, M_offset_3,  10, T[53]);
	            c = II(c, d, a, b, M_offset_10, 15, T[54]);
	            b = II(b, c, d, a, M_offset_1,  21, T[55]);
	            a = II(a, b, c, d, M_offset_8,  6,  T[56]);
	            d = II(d, a, b, c, M_offset_15, 10, T[57]);
	            c = II(c, d, a, b, M_offset_6,  15, T[58]);
	            b = II(b, c, d, a, M_offset_13, 21, T[59]);
	            a = II(a, b, c, d, M_offset_4,  6,  T[60]);
	            d = II(d, a, b, c, M_offset_11, 10, T[61]);
	            c = II(c, d, a, b, M_offset_2,  15, T[62]);
	            b = II(b, c, d, a, M_offset_9,  21, T[63]);

	            // Intermediate hash value
	            H[0] = (H[0] + a) | 0;
	            H[1] = (H[1] + b) | 0;
	            H[2] = (H[2] + c) | 0;
	            H[3] = (H[3] + d) | 0;
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;

	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x80 << (24 - nBitsLeft % 32);

	            var nBitsTotalH = Math.floor(nBitsTotal / 0x100000000);
	            var nBitsTotalL = nBitsTotal;
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 15] = (
	                (((nBitsTotalH << 8)  | (nBitsTotalH >>> 24)) & 0x00ff00ff) |
	                (((nBitsTotalH << 24) | (nBitsTotalH >>> 8))  & 0xff00ff00)
	            );
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 14] = (
	                (((nBitsTotalL << 8)  | (nBitsTotalL >>> 24)) & 0x00ff00ff) |
	                (((nBitsTotalL << 24) | (nBitsTotalL >>> 8))  & 0xff00ff00)
	            );

	            data.sigBytes = (dataWords.length + 1) * 4;

	            // Hash final blocks
	            this._process();

	            // Shortcuts
	            var hash = this._hash;
	            var H = hash.words;

	            // Swap endian
	            for (var i = 0; i < 4; i++) {
	                // Shortcut
	                var H_i = H[i];

	                H[i] = (((H_i << 8)  | (H_i >>> 24)) & 0x00ff00ff) |
	                       (((H_i << 24) | (H_i >>> 8))  & 0xff00ff00);
	            }

	            // Return final computed hash
	            return hash;
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);
	            clone._hash = this._hash.clone();

	            return clone;
	        }
	    });

	    function FF(a, b, c, d, x, s, t) {
	        var n = a + ((b & c) | (~b & d)) + x + t;
	        return ((n << s) | (n >>> (32 - s))) + b;
	    }

	    function GG(a, b, c, d, x, s, t) {
	        var n = a + ((b & d) | (c & ~d)) + x + t;
	        return ((n << s) | (n >>> (32 - s))) + b;
	    }

	    function HH(a, b, c, d, x, s, t) {
	        var n = a + (b ^ c ^ d) + x + t;
	        return ((n << s) | (n >>> (32 - s))) + b;
	    }

	    function II(a, b, c, d, x, s, t) {
	        var n = a + (c ^ (b | ~d)) + x + t;
	        return ((n << s) | (n >>> (32 - s))) + b;
	    }

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.MD5('message');
	     *     var hash = CryptoJS.MD5(wordArray);
	     */
	    C.MD5 = Hasher._createHelper(MD5);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacMD5(message, key);
	     */
	    C.HmacMD5 = Hasher._createHmacHelper(MD5);
	}(Math));


	return CryptoJS.MD5;

}));

/***/ }),
/* 10 */
/***/ (function(module, exports, __webpack_require__) {

var $ = __webpack_require__(63)
window.$ = window.Zepto = $
__webpack_require__(81)

var User = __webpack_require__(80)

var showPage = 1
var disX, moveX, L, T, starX, starY, starXEnd, starYEnd

var Floatball = {
  init: function () {
    $('.floatball').show()
    // 事件监听
    $('.floatball').on('touchstart', function (e) {

      disX = e.touches[0].clientX - this.offsetLeft
      disY = e.touches[0].clientY - this.offsetTop
      //手指按下时的坐标
      starX = e.touches[0].clientX
      starY = e.touches[0].clientY
    })
    $('.floatball').on('touchmove', function (e) {

      L = e.touches[0].clientX - disX
      T = e.touches[0].clientY - disY
      //移动时 当前位置与起始位置之间的差值
      starXEnd = e.touches[0].clientX - starX
      starYEnd = e.touches[0].clientY - starY
      if (L < 0) { //限制拖拽的X范围，不能拖出屏幕
        L = 0
      } else if (L > document.documentElement.clientWidth - this.offsetWidth) {
        L = document.documentElement.clientWidth - this.offsetWidth
      }
      if (T < 0) { //限制拖拽的Y范围，不能拖出屏幕
        T = 0
      } else if (T > document.documentElement.clientHeight - this.offsetHeight) {
        T = document.documentElement.clientHeight - this.offsetHeight
      }
      moveX = L + 'px'
      moveY = T + 'px'
      this.style.left = moveX
      this.style.top = moveY
    })

    $('.floatball').on('click', function () {

      //0.5秒内不能重复点击
      // $('.floatball').off('click');
      // setTimeout(function() {
      // $('.floatball').on('click', false);
      // }, 500);

      if (showPage === 1) {
        showPage = 0
        $('.unfold').show()
        $('.unfold').animate({width: '50px'}, 'fast')
        setTimeout(function () {
          $('.unfold').html('注销')
        }, 200)
      }
      else {
        showPage = 1
        $('.unfold').html('')
        $('.unfold').animate({width: '0px'})

      }
    })

    $('.unfold').on('click', function () {
      User.logOut(function () {
        window.location.reload()
      })
    })
  }
}
Floatball.init()

/***/ }),
/* 11 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function (undefined) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Base = C_lib.Base;
	    var X32WordArray = C_lib.WordArray;

	    /**
	     * x64 namespace.
	     */
	    var C_x64 = C.x64 = {};

	    /**
	     * A 64-bit word.
	     */
	    var X64Word = C_x64.Word = Base.extend({
	        /**
	         * Initializes a newly created 64-bit word.
	         *
	         * @param {number} high The high 32 bits.
	         * @param {number} low The low 32 bits.
	         *
	         * @example
	         *
	         *     var x64Word = CryptoJS.x64.Word.create(0x00010203, 0x04050607);
	         */
	        init: function (high, low) {
	            this.high = high;
	            this.low = low;
	        }

	        /**
	         * Bitwise NOTs this word.
	         *
	         * @return {X64Word} A new x64-Word object after negating.
	         *
	         * @example
	         *
	         *     var negated = x64Word.not();
	         */
	        // not: function () {
	            // var high = ~this.high;
	            // var low = ~this.low;

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Bitwise ANDs this word with the passed word.
	         *
	         * @param {X64Word} word The x64-Word to AND with this word.
	         *
	         * @return {X64Word} A new x64-Word object after ANDing.
	         *
	         * @example
	         *
	         *     var anded = x64Word.and(anotherX64Word);
	         */
	        // and: function (word) {
	            // var high = this.high & word.high;
	            // var low = this.low & word.low;

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Bitwise ORs this word with the passed word.
	         *
	         * @param {X64Word} word The x64-Word to OR with this word.
	         *
	         * @return {X64Word} A new x64-Word object after ORing.
	         *
	         * @example
	         *
	         *     var ored = x64Word.or(anotherX64Word);
	         */
	        // or: function (word) {
	            // var high = this.high | word.high;
	            // var low = this.low | word.low;

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Bitwise XORs this word with the passed word.
	         *
	         * @param {X64Word} word The x64-Word to XOR with this word.
	         *
	         * @return {X64Word} A new x64-Word object after XORing.
	         *
	         * @example
	         *
	         *     var xored = x64Word.xor(anotherX64Word);
	         */
	        // xor: function (word) {
	            // var high = this.high ^ word.high;
	            // var low = this.low ^ word.low;

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Shifts this word n bits to the left.
	         *
	         * @param {number} n The number of bits to shift.
	         *
	         * @return {X64Word} A new x64-Word object after shifting.
	         *
	         * @example
	         *
	         *     var shifted = x64Word.shiftL(25);
	         */
	        // shiftL: function (n) {
	            // if (n < 32) {
	                // var high = (this.high << n) | (this.low >>> (32 - n));
	                // var low = this.low << n;
	            // } else {
	                // var high = this.low << (n - 32);
	                // var low = 0;
	            // }

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Shifts this word n bits to the right.
	         *
	         * @param {number} n The number of bits to shift.
	         *
	         * @return {X64Word} A new x64-Word object after shifting.
	         *
	         * @example
	         *
	         *     var shifted = x64Word.shiftR(7);
	         */
	        // shiftR: function (n) {
	            // if (n < 32) {
	                // var low = (this.low >>> n) | (this.high << (32 - n));
	                // var high = this.high >>> n;
	            // } else {
	                // var low = this.high >>> (n - 32);
	                // var high = 0;
	            // }

	            // return X64Word.create(high, low);
	        // },

	        /**
	         * Rotates this word n bits to the left.
	         *
	         * @param {number} n The number of bits to rotate.
	         *
	         * @return {X64Word} A new x64-Word object after rotating.
	         *
	         * @example
	         *
	         *     var rotated = x64Word.rotL(25);
	         */
	        // rotL: function (n) {
	            // return this.shiftL(n).or(this.shiftR(64 - n));
	        // },

	        /**
	         * Rotates this word n bits to the right.
	         *
	         * @param {number} n The number of bits to rotate.
	         *
	         * @return {X64Word} A new x64-Word object after rotating.
	         *
	         * @example
	         *
	         *     var rotated = x64Word.rotR(7);
	         */
	        // rotR: function (n) {
	            // return this.shiftR(n).or(this.shiftL(64 - n));
	        // },

	        /**
	         * Adds this word with the passed word.
	         *
	         * @param {X64Word} word The x64-Word to add with this word.
	         *
	         * @return {X64Word} A new x64-Word object after adding.
	         *
	         * @example
	         *
	         *     var added = x64Word.add(anotherX64Word);
	         */
	        // add: function (word) {
	            // var low = (this.low + word.low) | 0;
	            // var carry = (low >>> 0) < (this.low >>> 0) ? 1 : 0;
	            // var high = (this.high + word.high + carry) | 0;

	            // return X64Word.create(high, low);
	        // }
	    });

	    /**
	     * An array of 64-bit words.
	     *
	     * @property {Array} words The array of CryptoJS.x64.Word objects.
	     * @property {number} sigBytes The number of significant bytes in this word array.
	     */
	    var X64WordArray = C_x64.WordArray = Base.extend({
	        /**
	         * Initializes a newly created word array.
	         *
	         * @param {Array} words (Optional) An array of CryptoJS.x64.Word objects.
	         * @param {number} sigBytes (Optional) The number of significant bytes in the words.
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.x64.WordArray.create();
	         *
	         *     var wordArray = CryptoJS.x64.WordArray.create([
	         *         CryptoJS.x64.Word.create(0x00010203, 0x04050607),
	         *         CryptoJS.x64.Word.create(0x18191a1b, 0x1c1d1e1f)
	         *     ]);
	         *
	         *     var wordArray = CryptoJS.x64.WordArray.create([
	         *         CryptoJS.x64.Word.create(0x00010203, 0x04050607),
	         *         CryptoJS.x64.Word.create(0x18191a1b, 0x1c1d1e1f)
	         *     ], 10);
	         */
	        init: function (words, sigBytes) {
	            words = this.words = words || [];

	            if (sigBytes != undefined) {
	                this.sigBytes = sigBytes;
	            } else {
	                this.sigBytes = words.length * 8;
	            }
	        },

	        /**
	         * Converts this 64-bit word array to a 32-bit word array.
	         *
	         * @return {CryptoJS.lib.WordArray} This word array's data as a 32-bit word array.
	         *
	         * @example
	         *
	         *     var x32WordArray = x64WordArray.toX32();
	         */
	        toX32: function () {
	            // Shortcuts
	            var x64Words = this.words;
	            var x64WordsLength = x64Words.length;

	            // Convert
	            var x32Words = [];
	            for (var i = 0; i < x64WordsLength; i++) {
	                var x64Word = x64Words[i];
	                x32Words.push(x64Word.high);
	                x32Words.push(x64Word.low);
	            }

	            return X32WordArray.create(x32Words, this.sigBytes);
	        },

	        /**
	         * Creates a copy of this word array.
	         *
	         * @return {X64WordArray} The clone.
	         *
	         * @example
	         *
	         *     var clone = x64WordArray.clone();
	         */
	        clone: function () {
	            var clone = Base.clone.call(this);

	            // Clone "words" array
	            var words = clone.words = this.words.slice(0);

	            // Clone each X64Word object
	            var wordsLength = words.length;
	            for (var i = 0; i < wordsLength; i++) {
	                words[i] = words[i].clone();
	            }

	            return clone;
	        }
	    });
	}());


	return CryptoJS;

}));

/***/ }),
/* 12 */,
/* 13 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "280b44c4c61aff7ea975c800fe662e8c.png";

/***/ }),
/* 14 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var Hasher = C_lib.Hasher;
	    var C_algo = C.algo;

	    // Reusable object
	    var W = [];

	    /**
	     * SHA-1 hash algorithm.
	     */
	    var SHA1 = C_algo.SHA1 = Hasher.extend({
	        _doReset: function () {
	            this._hash = new WordArray.init([
	                0x67452301, 0xefcdab89,
	                0x98badcfe, 0x10325476,
	                0xc3d2e1f0
	            ]);
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcut
	            var H = this._hash.words;

	            // Working variables
	            var a = H[0];
	            var b = H[1];
	            var c = H[2];
	            var d = H[3];
	            var e = H[4];

	            // Computation
	            for (var i = 0; i < 80; i++) {
	                if (i < 16) {
	                    W[i] = M[offset + i] | 0;
	                } else {
	                    var n = W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16];
	                    W[i] = (n << 1) | (n >>> 31);
	                }

	                var t = ((a << 5) | (a >>> 27)) + e + W[i];
	                if (i < 20) {
	                    t += ((b & c) | (~b & d)) + 0x5a827999;
	                } else if (i < 40) {
	                    t += (b ^ c ^ d) + 0x6ed9eba1;
	                } else if (i < 60) {
	                    t += ((b & c) | (b & d) | (c & d)) - 0x70e44324;
	                } else /* if (i < 80) */ {
	                    t += (b ^ c ^ d) - 0x359d3e2a;
	                }

	                e = d;
	                d = c;
	                c = (b << 30) | (b >>> 2);
	                b = a;
	                a = t;
	            }

	            // Intermediate hash value
	            H[0] = (H[0] + a) | 0;
	            H[1] = (H[1] + b) | 0;
	            H[2] = (H[2] + c) | 0;
	            H[3] = (H[3] + d) | 0;
	            H[4] = (H[4] + e) | 0;
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;

	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x80 << (24 - nBitsLeft % 32);
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 14] = Math.floor(nBitsTotal / 0x100000000);
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 15] = nBitsTotal;
	            data.sigBytes = dataWords.length * 4;

	            // Hash final blocks
	            this._process();

	            // Return final computed hash
	            return this._hash;
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);
	            clone._hash = this._hash.clone();

	            return clone;
	        }
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA1('message');
	     *     var hash = CryptoJS.SHA1(wordArray);
	     */
	    C.SHA1 = Hasher._createHelper(SHA1);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA1(message, key);
	     */
	    C.HmacSHA1 = Hasher._createHmacHelper(SHA1);
	}());


	return CryptoJS.SHA1;

}));

/***/ }),
/* 15 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Base = C_lib.Base;
	    var C_enc = C.enc;
	    var Utf8 = C_enc.Utf8;
	    var C_algo = C.algo;

	    /**
	     * HMAC algorithm.
	     */
	    var HMAC = C_algo.HMAC = Base.extend({
	        /**
	         * Initializes a newly created HMAC.
	         *
	         * @param {Hasher} hasher The hash algorithm to use.
	         * @param {WordArray|string} key The secret key.
	         *
	         * @example
	         *
	         *     var hmacHasher = CryptoJS.algo.HMAC.create(CryptoJS.algo.SHA256, key);
	         */
	        init: function (hasher, key) {
	            // Init hasher
	            hasher = this._hasher = new hasher.init();

	            // Convert string to WordArray, else assume WordArray already
	            if (typeof key == 'string') {
	                key = Utf8.parse(key);
	            }

	            // Shortcuts
	            var hasherBlockSize = hasher.blockSize;
	            var hasherBlockSizeBytes = hasherBlockSize * 4;

	            // Allow arbitrary length keys
	            if (key.sigBytes > hasherBlockSizeBytes) {
	                key = hasher.finalize(key);
	            }

	            // Clamp excess bits
	            key.clamp();

	            // Clone key for inner and outer pads
	            var oKey = this._oKey = key.clone();
	            var iKey = this._iKey = key.clone();

	            // Shortcuts
	            var oKeyWords = oKey.words;
	            var iKeyWords = iKey.words;

	            // XOR keys with pad constants
	            for (var i = 0; i < hasherBlockSize; i++) {
	                oKeyWords[i] ^= 0x5c5c5c5c;
	                iKeyWords[i] ^= 0x36363636;
	            }
	            oKey.sigBytes = iKey.sigBytes = hasherBlockSizeBytes;

	            // Set initial values
	            this.reset();
	        },

	        /**
	         * Resets this HMAC to its initial state.
	         *
	         * @example
	         *
	         *     hmacHasher.reset();
	         */
	        reset: function () {
	            // Shortcut
	            var hasher = this._hasher;

	            // Reset
	            hasher.reset();
	            hasher.update(this._iKey);
	        },

	        /**
	         * Updates this HMAC with a message.
	         *
	         * @param {WordArray|string} messageUpdate The message to append.
	         *
	         * @return {HMAC} This HMAC instance.
	         *
	         * @example
	         *
	         *     hmacHasher.update('message');
	         *     hmacHasher.update(wordArray);
	         */
	        update: function (messageUpdate) {
	            this._hasher.update(messageUpdate);

	            // Chainable
	            return this;
	        },

	        /**
	         * Finalizes the HMAC computation.
	         * Note that the finalize operation is effectively a destructive, read-once operation.
	         *
	         * @param {WordArray|string} messageUpdate (Optional) A final message update.
	         *
	         * @return {WordArray} The HMAC.
	         *
	         * @example
	         *
	         *     var hmac = hmacHasher.finalize();
	         *     var hmac = hmacHasher.finalize('message');
	         *     var hmac = hmacHasher.finalize(wordArray);
	         */
	        finalize: function (messageUpdate) {
	            // Shortcut
	            var hasher = this._hasher;

	            // Compute HMAC
	            var innerHash = hasher.finalize(messageUpdate);
	            hasher.reset();
	            var hmac = hasher.finalize(this._oKey.clone().concat(innerHash));

	            return hmac;
	        }
	    });
	}());


}));

/***/ }),
/* 16 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * SDK接口封装 联运渠道
 */

var Util = __webpack_require__(5);
var Config = __webpack_require__(23);
var Http = __webpack_require__(58);
var Global = __webpack_require__(7);
var Oauth = __webpack_require__(64);
var notification = __webpack_require__(59);
var md5 = __webpack_require__(17);

var SDK = {
  isEncrypt: false,
  preInit: function () {
    /**
     * 切换逻辑设置
     * 1. 激活接口切换为当前域名，去掉https
     * 2. sversion 增加 S 后缀
     */
    if (Global.urlInfo.hostname !== Config.getConfig('HOST')) {
      Global.config.switch = true;
    }
    // 多包域名进行加密处理
    var encryptHost = Config.getConfig('ENCRYPT_HOST');
    if (
      encryptHost &&
      encryptHost.length > 0 &&
      encryptHost.indexOf(Global.urlInfo.hostname) !== -1
    ) {
      console.log('encrypted!');
      this.isEncrypt = true;
    }
  },
  postInit: function () {
    if (Global.config.switch) {
      console.log('switch：' + Global.config.switch);
      /*Global.api.purl += 's/' 20181205 去掉跳转，支付页后台配置需求*/
    }
  },
  /**
   * 激活接口，获取游戏配置
   */
  init: function (params, callback, callbackFail) {
    this.request(Config.getConfig('INIT_API'), params, function (response) {
      if (response.state === 1) {
        callback(response.data);
      } else {
        Util.toast(response.msg);
      }
    });
  },

  getApi: function (api) {
    /* 新域名添加规则，不做协议头和 ./的替换，只做域名替换 */
    var newApi = {
      role_report: 'report-api',
    };
    var isNewApi = api.indexOf(newApi.role_report) > -1 ? true : false;
    if (Global.config.switch) {
      var currentHost = Global.urlInfo.hostname;
      if (isNewApi) {
        api = api.replace(Config.getConfig('HOST'), currentHost);
      } else {
        if (currentHost === 'u9g9h0.com') {
          api = api
            .replace('-', '.')
            .replace(Config.getConfig('HOST'), currentHost);
        } else if (
          currentHost === '9gt6fyu.com' ||
          currentHost === '39ej7e.com' ||
          currentHost === 's92h9s.com' ||
          currentHost === 'iwjhg2.com' ||
          currentHost === '29dq0.com' ||
          currentHost === '98ghik.com' ||
          currentHost === 'ihdjie.com' ||
          currentHost === 'js2i8h.com' ||
          currentHost === '320hjd.com'
        ) {
          api = api.replace(Config.getConfig('HOST'), currentHost);
        } else if (
          currentHost === 'odchqpto.com' ||
          currentHost === 'pb1771hp.com' ||
          currentHost === 'r6ajactz.com' ||
          currentHost === 'uv1oxjrf.com' ||
          currentHost === 'vfad5msw.com' ||
          currentHost === 'liyuanhn.com'
        ) {
          api = api.replace(Config.getConfig('HOST'), currentHost);
        } else {
          // 不能直接把https换成http,会造成跨域，需要根据当前url协议是否是http替换
          api = api.replace(Config.getConfig("HOST"), currentHost);
          if (location.protocol === "http:") {
            // 只能替换开头，防止api中间有https
            api = api.replace(/^https/, "http");
          }
        }
      }
    }
    return api;
  },

  /**
   * 联运登录回调
   * 校验联运用户信息
   * 不同联运商的登录验证方式不一样
   * @param options
   * @param callback
   * @param callbackFail 可选
   */
  verify: function (params, callback, callbackFail) {
    Global.hasVerifiedLogin = false;
    // 新增state!=1的回调函数
    function callbackFailDefault() {}

    callbackFail = callbackFail || callbackFailDefault;
    this.request(
      Global.api.ptoken,
      params,
      function (response) {
        if (response.state === 1) {
          var data = response.data;
          // 校验通过后，广播事件到sdk
          Global.user = data;
          Global.hasVerifiedLogin = true;
          var ret = {
            pid: Global.config.pid,
            gid: Global.config.gid,
            token: Global.user.token,
            sign: data.sign,
            time: data.time,
            user: data,
          };
          if (Global.user && Global.user.nurl) {
            notification.init(Global.user.nurl);
          }
          callback(ret);
        } else {
          Util.toast(response.msg, error);
        }
      },
      function (response) {
        callbackFail(response);
      }
    );
  },

  /**
   * 进入游戏,记录用户信息
   * @param options
   * @param callback
   */
  entergame: function (params, callback) {
    this.request(Global.api.enter, params, function (response) {
      callback(response);
    });
  },

  /**
   * 进入创角页面，上报用户信息
   * @param {Object} params
   * @param {Function} callback
   */
  preCreateRole: function (params, callback) {
    if (Global.api.process_action) {
      this.request(Global.api.process_action, params, function (response) {
        callback(response);
      });
    } else {
      callback();
    }
  },

  /**
   * 创角成功，上报用户信息
   * @param {Object} params
   * @param {Function} callback
   */
  createRole: function (params, callback) {
    params.pdata = JSON.stringify(Global.urlInfo.searchObject);
    this.request(Global.api.role_report, params, function (response) {
      callback(response);
    });
  },
  /**
   * 统一下单接口
   * 1. 获取订单号
   * 2. 调用联运商收银台
   * @param options
   * @param callback
   */
  order: function (params, callback, callbackFail) {
    // 1. 调用统一下单接口
    this.request(
      Global.api.order,
      params,
      function (response) {
        // 2. 调用联运商的支付接口
        if (response.state == 1 && response.data) {
          callback(response.data, params);
        }
      },
      function (response) {
        if (response.state == -1) {
          // 屏蔽充值时，不显示充值界面
        } else {
          callbackFail(response);
        }
      }
    );
  },
  /**
   * 渠道下单接口
   * 1. 获取商品id（苹果商品id）
   * 2.
   * @param options
   * @param callback
   */
  sorder: function (params, callback, callbackFail) {
    // 1. 调用统一下单接口
    this.requestForSorderSign(
      Global.api.sorder,
      params,
      function (response) {
        // 2. 调用联运商的支付接口
        if (response.state == 1 && response.data) {
          callback(response.data, params);
        }
      },
      function (response) {
        callbackFail(response);
      }
    );
  },
  doOauthLogin: function () {
    var oauthInfo = Oauth.isLogin(Global.config.pid);
    if (oauthInfo) {
      SQH5SDK.onloginSuccess(oauthInfo);
    } else {
      var params = {
        callback: window.location.href,
        pid: Global.config.pid,
        gid: Global.config.gid,
      };
      Oauth.login(params);
    }
  },

  /**
   * 每次请求带上系统参数
   * @returns {{pid: number, gid: number, version: string, time: (*|number)}}
   */
  getSystemParams: function () {
    var params = {
      pid: Global.config.pid,
      gid: Global.config.gid,
      sversion: Global.config.sversion,
      version: Config.config.version,
      time: Util.getTime(),
    };
    // 切换判断
    if (Global.config.switch) {
      params.sversion += 'S';
      params.scut = 1;
    }

    // 已获取设备信息的，带上设备信息
    if (Global.device) {
      params.dev = Global.device.fingerprint || '';
      params.os = Global.device.os.family || '';
      params.over = Global.device.os.version || '';
      if (Global.device.idfa) {
        params.idfa = Global.device.idfa;
      }
    }

    // 自营渠道（链接包）透传app传递的dev设备号参数，以app的设备号为准
    if (
      (Global.config.pid > 100000 ||
        Global.config.pid == 1 ||
        Global.config.pid == 46 ||
        Global.config.pid == 357) &&
      Global.urlInfo.searchObject.dev
    ) {
      params.dev = Global.urlInfo.searchObject.dev;
    }
    // 自营渠道（链接包）透传app传递的idfa设备号参数，以app的idfa为准
    if (
      (Global.config.pid > 100000 ||
        Global.config.pid == 1 ||
        Global.config.pid == 46 ||
        Global.config.pid == 357) &&
      Global.urlInfo.searchObject.idfa
    ) {
      params.idfa = Global.urlInfo.searchObject.idfa;
      // ios包，如果传了idfa，没传dev,则使用该idfa计算dev；todo 后续数据没有问题，可以去掉判断
      if (
        Global.urlInfo.searchObject.cdev &&
        Global.urlInfo.searchObject.cdev == 'ios'
      ) {
        params.dev = md5(params.idfa);
      }
    }

    // 已登录状态需要加上登录态
    if (Global.user && Global.user.token) {
      params.token = Global.user.token;
      params.uid = Global.user.uid || '';
      params.uname = Global.user.uname || '';
    }
    // 推广refer
    if (Global.config.refer) {
      params.refer = Global.config.refer;
    }
    // 如果链接带有scut3，带上scut3
    if (Global.urlInfo.searchObject && Global.urlInfo.searchObject.scut3) {
      params.scut3 = Global.urlInfo.searchObject.scut3;
    }
    return params;
  },

  request: function (api, params, callback, callbackFail) {
    // 新增state!=1的回调函数,callbackFail可选
    function callbackFailDefault() {}

    callbackFail = callbackFail || callbackFailDefault;
    // 带上系统参数
    params = Util.mixin(params, this.getSystemParams());
    if (api.indexOf('antiindulge/pcheck') != -1) {
      delete params.scut3;
    }
    params.sign = Util.getSign(params, Config.getConfig('API_KEY'));

    var _that = this;
    if (this.isEncrypt) {
      Http.jsonpEncrypt(
        this.getApi(api),
        params,
        function (response) {
          _that.requestCallback(response, callback, callbackFail);
        },
        Config.getConfig('ENCRYPT_KEY')
      );
    } else {
      Http.jsonp(this.getApi(api), params, function (response) {
        _that.requestCallback(response, callback, callbackFail);
      });
    }
  },

  // 该请求不会自动附带参数，使用场景如用于签名原串拼接和request函数不同的情况
  requestSimple: function (api, params, callback, callbackFail) {
    // 新增state!=1的回调函数,callbackFail可选
    function callbackFailDefault() {}

    callbackFail = callbackFail || callbackFailDefault;

    var _that = this;
    if (this.isEncrypt) {
      Http.jsonpEncrypt(
        this.getApi(api),
        params,
        function (response) {
          _that.requestCallback(response, callback, callbackFail);
        },
        Config.getConfig('ENCRYPT_KEY')
      );
    } else {
      Http.jsonp(this.getApi(api), params, function (response) {
        _that.requestCallback(response, callback, callbackFail);
      });
    }
  },

  requestForSorderSign: function (api, params, callback, callbackFail) {
    // 新增state!=1的回调函数,callbackFail可选
    function callbackFailDefault() {}

    callbackFail = callbackFail || callbackFailDefault;
    // 带上系统参数
    params = Util.mixin(params, this.getSystemParams());
    // 签名校验
    var signParams = {
      pid: params.pid,
      gid: params.gid,
      moid: params.moid,
      uid: params.uid,
      dev: params.dev,
      time: params.time,
    };

    params.sign = Util.getSign(signParams, Config.getConfig('API_KEY'));

    var _that = this;
    if (this.isEncrypt) {
      Http.jsonpEncrypt(
        this.getApi(api),
        params,
        function (response) {
          _that.requestCallback(response, callback, callbackFail);
        },
        Config.getConfig('ENCRYPT_KEY')
      );
    } else {
      Http.jsonp(this.getApi(api), params, function (response) {
        _that.requestCallback(response, callback, callbackFail);
      });
    }
  },
  requestCallback: function (response, callback, callbackFail) {
    if (response.state == 1) {
      callback(response);
    } else if (response.state == -1) {
      callbackFail(response);
    } else {
      callbackFail(response);
      Util.toast(response.msg, 'error');
    }
  },

  // xhr 请求
  xhrRequest: function (options) {
    if (!options.url) return;
    options = options || {};
    options.type = (options.type || 'GET').toUpperCase();
    options.dataType = options.dataType || 'json';
    var params = formatParams(options.data);
    var xhr;
    if (window.XMLHttpRequest) {
      xhr = new XMLHttpRequest();
    } else {
      xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        var status = xhr.status;
        if (status >= 200 && status < 300) {
          options.success && options.success(xhr.responseText, xhr.responseXML);
        } else {
          options.fail && options.fail(status);
        }
      }
    };

    if (options.type == 'GET') {
      xhr.open('GET', options.url + '?' + params, true);
      xhr.send(null);
    } else if (options.type == 'POST') {
      xhr.open('POST', options.url, true);
      // 设置表单提交时的内容类型
      for (var key in options.header) {
        xhr.setRequestHeader(key, options.header[key]);
      }
      for (var keyXhr in options.xhrFields) {
        xhr[keyXhr] = options.xhrFields[keyXhr];
      }
      xhr.send(params);
    }

    function formatParams(data) {
      var arr = [];
      for (var name in data) {
        arr.push(
          encodeURIComponent(name) + '=' + encodeURIComponent(data[name])
        );
      }
      arr.push(('v=' + Math.random()).replace('.', ''));
      return arr.join('&');
    }
  },

  debug: function (message, force) {
    force = force || false;
    if (this.config.debug || force) {
      alert(message);
    }
    console.log(message);
  },
};

module.exports = SDK;


/***/ }),
/* 17 */
/***/ (function(module, exports, __webpack_require__) {

(function(){
  var crypt = __webpack_require__(27),
      utf8 = __webpack_require__(18).utf8,
      isBuffer = __webpack_require__(28),
      bin = __webpack_require__(18).bin,

  // The core
  md5 = function (message, options) {
    // Convert to byte array
    if (message.constructor == String)
      if (options && options.encoding === 'binary')
        message = bin.stringToBytes(message);
      else
        message = utf8.stringToBytes(message);
    else if (isBuffer(message))
      message = Array.prototype.slice.call(message, 0);
    else if (!Array.isArray(message))
      message = message.toString();
    // else, assume byte array already

    var m = crypt.bytesToWords(message),
        l = message.length * 8,
        a =  1732584193,
        b = -271733879,
        c = -1732584194,
        d =  271733878;

    // Swap endian
    for (var i = 0; i < m.length; i++) {
      m[i] = ((m[i] <<  8) | (m[i] >>> 24)) & 0x00FF00FF |
             ((m[i] << 24) | (m[i] >>>  8)) & 0xFF00FF00;
    }

    // Padding
    m[l >>> 5] |= 0x80 << (l % 32);
    m[(((l + 64) >>> 9) << 4) + 14] = l;

    // Method shortcuts
    var FF = md5._ff,
        GG = md5._gg,
        HH = md5._hh,
        II = md5._ii;

    for (var i = 0; i < m.length; i += 16) {

      var aa = a,
          bb = b,
          cc = c,
          dd = d;

      a = FF(a, b, c, d, m[i+ 0],  7, -680876936);
      d = FF(d, a, b, c, m[i+ 1], 12, -389564586);
      c = FF(c, d, a, b, m[i+ 2], 17,  606105819);
      b = FF(b, c, d, a, m[i+ 3], 22, -1044525330);
      a = FF(a, b, c, d, m[i+ 4],  7, -176418897);
      d = FF(d, a, b, c, m[i+ 5], 12,  1200080426);
      c = FF(c, d, a, b, m[i+ 6], 17, -1473231341);
      b = FF(b, c, d, a, m[i+ 7], 22, -45705983);
      a = FF(a, b, c, d, m[i+ 8],  7,  1770035416);
      d = FF(d, a, b, c, m[i+ 9], 12, -1958414417);
      c = FF(c, d, a, b, m[i+10], 17, -42063);
      b = FF(b, c, d, a, m[i+11], 22, -1990404162);
      a = FF(a, b, c, d, m[i+12],  7,  1804603682);
      d = FF(d, a, b, c, m[i+13], 12, -40341101);
      c = FF(c, d, a, b, m[i+14], 17, -1502002290);
      b = FF(b, c, d, a, m[i+15], 22,  1236535329);

      a = GG(a, b, c, d, m[i+ 1],  5, -165796510);
      d = GG(d, a, b, c, m[i+ 6],  9, -1069501632);
      c = GG(c, d, a, b, m[i+11], 14,  643717713);
      b = GG(b, c, d, a, m[i+ 0], 20, -373897302);
      a = GG(a, b, c, d, m[i+ 5],  5, -701558691);
      d = GG(d, a, b, c, m[i+10],  9,  38016083);
      c = GG(c, d, a, b, m[i+15], 14, -660478335);
      b = GG(b, c, d, a, m[i+ 4], 20, -405537848);
      a = GG(a, b, c, d, m[i+ 9],  5,  568446438);
      d = GG(d, a, b, c, m[i+14],  9, -1019803690);
      c = GG(c, d, a, b, m[i+ 3], 14, -187363961);
      b = GG(b, c, d, a, m[i+ 8], 20,  1163531501);
      a = GG(a, b, c, d, m[i+13],  5, -1444681467);
      d = GG(d, a, b, c, m[i+ 2],  9, -51403784);
      c = GG(c, d, a, b, m[i+ 7], 14,  1735328473);
      b = GG(b, c, d, a, m[i+12], 20, -1926607734);

      a = HH(a, b, c, d, m[i+ 5],  4, -378558);
      d = HH(d, a, b, c, m[i+ 8], 11, -2022574463);
      c = HH(c, d, a, b, m[i+11], 16,  1839030562);
      b = HH(b, c, d, a, m[i+14], 23, -35309556);
      a = HH(a, b, c, d, m[i+ 1],  4, -1530992060);
      d = HH(d, a, b, c, m[i+ 4], 11,  1272893353);
      c = HH(c, d, a, b, m[i+ 7], 16, -155497632);
      b = HH(b, c, d, a, m[i+10], 23, -1094730640);
      a = HH(a, b, c, d, m[i+13],  4,  681279174);
      d = HH(d, a, b, c, m[i+ 0], 11, -358537222);
      c = HH(c, d, a, b, m[i+ 3], 16, -722521979);
      b = HH(b, c, d, a, m[i+ 6], 23,  76029189);
      a = HH(a, b, c, d, m[i+ 9],  4, -640364487);
      d = HH(d, a, b, c, m[i+12], 11, -421815835);
      c = HH(c, d, a, b, m[i+15], 16,  530742520);
      b = HH(b, c, d, a, m[i+ 2], 23, -995338651);

      a = II(a, b, c, d, m[i+ 0],  6, -198630844);
      d = II(d, a, b, c, m[i+ 7], 10,  1126891415);
      c = II(c, d, a, b, m[i+14], 15, -1416354905);
      b = II(b, c, d, a, m[i+ 5], 21, -57434055);
      a = II(a, b, c, d, m[i+12],  6,  1700485571);
      d = II(d, a, b, c, m[i+ 3], 10, -1894986606);
      c = II(c, d, a, b, m[i+10], 15, -1051523);
      b = II(b, c, d, a, m[i+ 1], 21, -2054922799);
      a = II(a, b, c, d, m[i+ 8],  6,  1873313359);
      d = II(d, a, b, c, m[i+15], 10, -30611744);
      c = II(c, d, a, b, m[i+ 6], 15, -1560198380);
      b = II(b, c, d, a, m[i+13], 21,  1309151649);
      a = II(a, b, c, d, m[i+ 4],  6, -145523070);
      d = II(d, a, b, c, m[i+11], 10, -1120210379);
      c = II(c, d, a, b, m[i+ 2], 15,  718787259);
      b = II(b, c, d, a, m[i+ 9], 21, -343485551);

      a = (a + aa) >>> 0;
      b = (b + bb) >>> 0;
      c = (c + cc) >>> 0;
      d = (d + dd) >>> 0;
    }

    return crypt.endian([a, b, c, d]);
  };

  // Auxiliary functions
  md5._ff  = function (a, b, c, d, x, s, t) {
    var n = a + (b & c | ~b & d) + (x >>> 0) + t;
    return ((n << s) | (n >>> (32 - s))) + b;
  };
  md5._gg  = function (a, b, c, d, x, s, t) {
    var n = a + (b & d | c & ~d) + (x >>> 0) + t;
    return ((n << s) | (n >>> (32 - s))) + b;
  };
  md5._hh  = function (a, b, c, d, x, s, t) {
    var n = a + (b ^ c ^ d) + (x >>> 0) + t;
    return ((n << s) | (n >>> (32 - s))) + b;
  };
  md5._ii  = function (a, b, c, d, x, s, t) {
    var n = a + (c ^ (b | ~d)) + (x >>> 0) + t;
    return ((n << s) | (n >>> (32 - s))) + b;
  };

  // Package private blocksize
  md5._blocksize = 16;
  md5._digestsize = 16;

  module.exports = function (message, options) {
    if (message === undefined || message === null)
      throw new Error('Illegal argument ' + message);

    var digestbytes = crypt.wordsToBytes(md5(message, options));
    return options && options.asBytes ? digestbytes :
        options && options.asString ? bin.bytesToString(digestbytes) :
        crypt.bytesToHex(digestbytes);
  };

})();


/***/ }),
/* 18 */
/***/ (function(module, exports) {

var charenc = {
  // UTF-8 encoding
  utf8: {
    // Convert a string to a byte array
    stringToBytes: function(str) {
      return charenc.bin.stringToBytes(unescape(encodeURIComponent(str)));
    },

    // Convert a byte array to a string
    bytesToString: function(bytes) {
      return decodeURIComponent(escape(charenc.bin.bytesToString(bytes)));
    }
  },

  // Binary encoding
  bin: {
    // Convert a string to a byte array
    stringToBytes: function(str) {
      for (var bytes = [], i = 0; i < str.length; i++)
        bytes.push(str.charCodeAt(i) & 0xFF);
      return bytes;
    },

    // Convert a byte array to a string
    bytesToString: function(bytes) {
      for (var str = [], i = 0; i < bytes.length; i++)
        str.push(String.fromCharCode(bytes[i]));
      return str.join('');
    }
  }
};

module.exports = charenc;


/***/ }),
/* 19 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function (Math) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var Hasher = C_lib.Hasher;
	    var C_algo = C.algo;

	    // Initialization and round constants tables
	    var H = [];
	    var K = [];

	    // Compute constants
	    (function () {
	        function isPrime(n) {
	            var sqrtN = Math.sqrt(n);
	            for (var factor = 2; factor <= sqrtN; factor++) {
	                if (!(n % factor)) {
	                    return false;
	                }
	            }

	            return true;
	        }

	        function getFractionalBits(n) {
	            return ((n - (n | 0)) * 0x100000000) | 0;
	        }

	        var n = 2;
	        var nPrime = 0;
	        while (nPrime < 64) {
	            if (isPrime(n)) {
	                if (nPrime < 8) {
	                    H[nPrime] = getFractionalBits(Math.pow(n, 1 / 2));
	                }
	                K[nPrime] = getFractionalBits(Math.pow(n, 1 / 3));

	                nPrime++;
	            }

	            n++;
	        }
	    }());

	    // Reusable object
	    var W = [];

	    /**
	     * SHA-256 hash algorithm.
	     */
	    var SHA256 = C_algo.SHA256 = Hasher.extend({
	        _doReset: function () {
	            this._hash = new WordArray.init(H.slice(0));
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcut
	            var H = this._hash.words;

	            // Working variables
	            var a = H[0];
	            var b = H[1];
	            var c = H[2];
	            var d = H[3];
	            var e = H[4];
	            var f = H[5];
	            var g = H[6];
	            var h = H[7];

	            // Computation
	            for (var i = 0; i < 64; i++) {
	                if (i < 16) {
	                    W[i] = M[offset + i] | 0;
	                } else {
	                    var gamma0x = W[i - 15];
	                    var gamma0  = ((gamma0x << 25) | (gamma0x >>> 7))  ^
	                                  ((gamma0x << 14) | (gamma0x >>> 18)) ^
	                                   (gamma0x >>> 3);

	                    var gamma1x = W[i - 2];
	                    var gamma1  = ((gamma1x << 15) | (gamma1x >>> 17)) ^
	                                  ((gamma1x << 13) | (gamma1x >>> 19)) ^
	                                   (gamma1x >>> 10);

	                    W[i] = gamma0 + W[i - 7] + gamma1 + W[i - 16];
	                }

	                var ch  = (e & f) ^ (~e & g);
	                var maj = (a & b) ^ (a & c) ^ (b & c);

	                var sigma0 = ((a << 30) | (a >>> 2)) ^ ((a << 19) | (a >>> 13)) ^ ((a << 10) | (a >>> 22));
	                var sigma1 = ((e << 26) | (e >>> 6)) ^ ((e << 21) | (e >>> 11)) ^ ((e << 7)  | (e >>> 25));

	                var t1 = h + sigma1 + ch + K[i] + W[i];
	                var t2 = sigma0 + maj;

	                h = g;
	                g = f;
	                f = e;
	                e = (d + t1) | 0;
	                d = c;
	                c = b;
	                b = a;
	                a = (t1 + t2) | 0;
	            }

	            // Intermediate hash value
	            H[0] = (H[0] + a) | 0;
	            H[1] = (H[1] + b) | 0;
	            H[2] = (H[2] + c) | 0;
	            H[3] = (H[3] + d) | 0;
	            H[4] = (H[4] + e) | 0;
	            H[5] = (H[5] + f) | 0;
	            H[6] = (H[6] + g) | 0;
	            H[7] = (H[7] + h) | 0;
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;

	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x80 << (24 - nBitsLeft % 32);
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 14] = Math.floor(nBitsTotal / 0x100000000);
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 15] = nBitsTotal;
	            data.sigBytes = dataWords.length * 4;

	            // Hash final blocks
	            this._process();

	            // Return final computed hash
	            return this._hash;
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);
	            clone._hash = this._hash.clone();

	            return clone;
	        }
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA256('message');
	     *     var hash = CryptoJS.SHA256(wordArray);
	     */
	    C.SHA256 = Hasher._createHelper(SHA256);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA256(message, key);
	     */
	    C.HmacSHA256 = Hasher._createHmacHelper(SHA256);
	}(Math));


	return CryptoJS.SHA256;

}));

/***/ }),
/* 20 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(11));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./x64-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Hasher = C_lib.Hasher;
	    var C_x64 = C.x64;
	    var X64Word = C_x64.Word;
	    var X64WordArray = C_x64.WordArray;
	    var C_algo = C.algo;

	    function X64Word_create() {
	        return X64Word.create.apply(X64Word, arguments);
	    }

	    // Constants
	    var K = [
	        X64Word_create(0x428a2f98, 0xd728ae22), X64Word_create(0x71374491, 0x23ef65cd),
	        X64Word_create(0xb5c0fbcf, 0xec4d3b2f), X64Word_create(0xe9b5dba5, 0x8189dbbc),
	        X64Word_create(0x3956c25b, 0xf348b538), X64Word_create(0x59f111f1, 0xb605d019),
	        X64Word_create(0x923f82a4, 0xaf194f9b), X64Word_create(0xab1c5ed5, 0xda6d8118),
	        X64Word_create(0xd807aa98, 0xa3030242), X64Word_create(0x12835b01, 0x45706fbe),
	        X64Word_create(0x243185be, 0x4ee4b28c), X64Word_create(0x550c7dc3, 0xd5ffb4e2),
	        X64Word_create(0x72be5d74, 0xf27b896f), X64Word_create(0x80deb1fe, 0x3b1696b1),
	        X64Word_create(0x9bdc06a7, 0x25c71235), X64Word_create(0xc19bf174, 0xcf692694),
	        X64Word_create(0xe49b69c1, 0x9ef14ad2), X64Word_create(0xefbe4786, 0x384f25e3),
	        X64Word_create(0x0fc19dc6, 0x8b8cd5b5), X64Word_create(0x240ca1cc, 0x77ac9c65),
	        X64Word_create(0x2de92c6f, 0x592b0275), X64Word_create(0x4a7484aa, 0x6ea6e483),
	        X64Word_create(0x5cb0a9dc, 0xbd41fbd4), X64Word_create(0x76f988da, 0x831153b5),
	        X64Word_create(0x983e5152, 0xee66dfab), X64Word_create(0xa831c66d, 0x2db43210),
	        X64Word_create(0xb00327c8, 0x98fb213f), X64Word_create(0xbf597fc7, 0xbeef0ee4),
	        X64Word_create(0xc6e00bf3, 0x3da88fc2), X64Word_create(0xd5a79147, 0x930aa725),
	        X64Word_create(0x06ca6351, 0xe003826f), X64Word_create(0x14292967, 0x0a0e6e70),
	        X64Word_create(0x27b70a85, 0x46d22ffc), X64Word_create(0x2e1b2138, 0x5c26c926),
	        X64Word_create(0x4d2c6dfc, 0x5ac42aed), X64Word_create(0x53380d13, 0x9d95b3df),
	        X64Word_create(0x650a7354, 0x8baf63de), X64Word_create(0x766a0abb, 0x3c77b2a8),
	        X64Word_create(0x81c2c92e, 0x47edaee6), X64Word_create(0x92722c85, 0x1482353b),
	        X64Word_create(0xa2bfe8a1, 0x4cf10364), X64Word_create(0xa81a664b, 0xbc423001),
	        X64Word_create(0xc24b8b70, 0xd0f89791), X64Word_create(0xc76c51a3, 0x0654be30),
	        X64Word_create(0xd192e819, 0xd6ef5218), X64Word_create(0xd6990624, 0x5565a910),
	        X64Word_create(0xf40e3585, 0x5771202a), X64Word_create(0x106aa070, 0x32bbd1b8),
	        X64Word_create(0x19a4c116, 0xb8d2d0c8), X64Word_create(0x1e376c08, 0x5141ab53),
	        X64Word_create(0x2748774c, 0xdf8eeb99), X64Word_create(0x34b0bcb5, 0xe19b48a8),
	        X64Word_create(0x391c0cb3, 0xc5c95a63), X64Word_create(0x4ed8aa4a, 0xe3418acb),
	        X64Word_create(0x5b9cca4f, 0x7763e373), X64Word_create(0x682e6ff3, 0xd6b2b8a3),
	        X64Word_create(0x748f82ee, 0x5defb2fc), X64Word_create(0x78a5636f, 0x43172f60),
	        X64Word_create(0x84c87814, 0xa1f0ab72), X64Word_create(0x8cc70208, 0x1a6439ec),
	        X64Word_create(0x90befffa, 0x23631e28), X64Word_create(0xa4506ceb, 0xde82bde9),
	        X64Word_create(0xbef9a3f7, 0xb2c67915), X64Word_create(0xc67178f2, 0xe372532b),
	        X64Word_create(0xca273ece, 0xea26619c), X64Word_create(0xd186b8c7, 0x21c0c207),
	        X64Word_create(0xeada7dd6, 0xcde0eb1e), X64Word_create(0xf57d4f7f, 0xee6ed178),
	        X64Word_create(0x06f067aa, 0x72176fba), X64Word_create(0x0a637dc5, 0xa2c898a6),
	        X64Word_create(0x113f9804, 0xbef90dae), X64Word_create(0x1b710b35, 0x131c471b),
	        X64Word_create(0x28db77f5, 0x23047d84), X64Word_create(0x32caab7b, 0x40c72493),
	        X64Word_create(0x3c9ebe0a, 0x15c9bebc), X64Word_create(0x431d67c4, 0x9c100d4c),
	        X64Word_create(0x4cc5d4be, 0xcb3e42b6), X64Word_create(0x597f299c, 0xfc657e2a),
	        X64Word_create(0x5fcb6fab, 0x3ad6faec), X64Word_create(0x6c44198c, 0x4a475817)
	    ];

	    // Reusable objects
	    var W = [];
	    (function () {
	        for (var i = 0; i < 80; i++) {
	            W[i] = X64Word_create();
	        }
	    }());

	    /**
	     * SHA-512 hash algorithm.
	     */
	    var SHA512 = C_algo.SHA512 = Hasher.extend({
	        _doReset: function () {
	            this._hash = new X64WordArray.init([
	                new X64Word.init(0x6a09e667, 0xf3bcc908), new X64Word.init(0xbb67ae85, 0x84caa73b),
	                new X64Word.init(0x3c6ef372, 0xfe94f82b), new X64Word.init(0xa54ff53a, 0x5f1d36f1),
	                new X64Word.init(0x510e527f, 0xade682d1), new X64Word.init(0x9b05688c, 0x2b3e6c1f),
	                new X64Word.init(0x1f83d9ab, 0xfb41bd6b), new X64Word.init(0x5be0cd19, 0x137e2179)
	            ]);
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcuts
	            var H = this._hash.words;

	            var H0 = H[0];
	            var H1 = H[1];
	            var H2 = H[2];
	            var H3 = H[3];
	            var H4 = H[4];
	            var H5 = H[5];
	            var H6 = H[6];
	            var H7 = H[7];

	            var H0h = H0.high;
	            var H0l = H0.low;
	            var H1h = H1.high;
	            var H1l = H1.low;
	            var H2h = H2.high;
	            var H2l = H2.low;
	            var H3h = H3.high;
	            var H3l = H3.low;
	            var H4h = H4.high;
	            var H4l = H4.low;
	            var H5h = H5.high;
	            var H5l = H5.low;
	            var H6h = H6.high;
	            var H6l = H6.low;
	            var H7h = H7.high;
	            var H7l = H7.low;

	            // Working variables
	            var ah = H0h;
	            var al = H0l;
	            var bh = H1h;
	            var bl = H1l;
	            var ch = H2h;
	            var cl = H2l;
	            var dh = H3h;
	            var dl = H3l;
	            var eh = H4h;
	            var el = H4l;
	            var fh = H5h;
	            var fl = H5l;
	            var gh = H6h;
	            var gl = H6l;
	            var hh = H7h;
	            var hl = H7l;

	            // Rounds
	            for (var i = 0; i < 80; i++) {
	                // Shortcut
	                var Wi = W[i];

	                // Extend message
	                if (i < 16) {
	                    var Wih = Wi.high = M[offset + i * 2]     | 0;
	                    var Wil = Wi.low  = M[offset + i * 2 + 1] | 0;
	                } else {
	                    // Gamma0
	                    var gamma0x  = W[i - 15];
	                    var gamma0xh = gamma0x.high;
	                    var gamma0xl = gamma0x.low;
	                    var gamma0h  = ((gamma0xh >>> 1) | (gamma0xl << 31)) ^ ((gamma0xh >>> 8) | (gamma0xl << 24)) ^ (gamma0xh >>> 7);
	                    var gamma0l  = ((gamma0xl >>> 1) | (gamma0xh << 31)) ^ ((gamma0xl >>> 8) | (gamma0xh << 24)) ^ ((gamma0xl >>> 7) | (gamma0xh << 25));

	                    // Gamma1
	                    var gamma1x  = W[i - 2];
	                    var gamma1xh = gamma1x.high;
	                    var gamma1xl = gamma1x.low;
	                    var gamma1h  = ((gamma1xh >>> 19) | (gamma1xl << 13)) ^ ((gamma1xh << 3) | (gamma1xl >>> 29)) ^ (gamma1xh >>> 6);
	                    var gamma1l  = ((gamma1xl >>> 19) | (gamma1xh << 13)) ^ ((gamma1xl << 3) | (gamma1xh >>> 29)) ^ ((gamma1xl >>> 6) | (gamma1xh << 26));

	                    // W[i] = gamma0 + W[i - 7] + gamma1 + W[i - 16]
	                    var Wi7  = W[i - 7];
	                    var Wi7h = Wi7.high;
	                    var Wi7l = Wi7.low;

	                    var Wi16  = W[i - 16];
	                    var Wi16h = Wi16.high;
	                    var Wi16l = Wi16.low;

	                    var Wil = gamma0l + Wi7l;
	                    var Wih = gamma0h + Wi7h + ((Wil >>> 0) < (gamma0l >>> 0) ? 1 : 0);
	                    var Wil = Wil + gamma1l;
	                    var Wih = Wih + gamma1h + ((Wil >>> 0) < (gamma1l >>> 0) ? 1 : 0);
	                    var Wil = Wil + Wi16l;
	                    var Wih = Wih + Wi16h + ((Wil >>> 0) < (Wi16l >>> 0) ? 1 : 0);

	                    Wi.high = Wih;
	                    Wi.low  = Wil;
	                }

	                var chh  = (eh & fh) ^ (~eh & gh);
	                var chl  = (el & fl) ^ (~el & gl);
	                var majh = (ah & bh) ^ (ah & ch) ^ (bh & ch);
	                var majl = (al & bl) ^ (al & cl) ^ (bl & cl);

	                var sigma0h = ((ah >>> 28) | (al << 4))  ^ ((ah << 30)  | (al >>> 2)) ^ ((ah << 25) | (al >>> 7));
	                var sigma0l = ((al >>> 28) | (ah << 4))  ^ ((al << 30)  | (ah >>> 2)) ^ ((al << 25) | (ah >>> 7));
	                var sigma1h = ((eh >>> 14) | (el << 18)) ^ ((eh >>> 18) | (el << 14)) ^ ((eh << 23) | (el >>> 9));
	                var sigma1l = ((el >>> 14) | (eh << 18)) ^ ((el >>> 18) | (eh << 14)) ^ ((el << 23) | (eh >>> 9));

	                // t1 = h + sigma1 + ch + K[i] + W[i]
	                var Ki  = K[i];
	                var Kih = Ki.high;
	                var Kil = Ki.low;

	                var t1l = hl + sigma1l;
	                var t1h = hh + sigma1h + ((t1l >>> 0) < (hl >>> 0) ? 1 : 0);
	                var t1l = t1l + chl;
	                var t1h = t1h + chh + ((t1l >>> 0) < (chl >>> 0) ? 1 : 0);
	                var t1l = t1l + Kil;
	                var t1h = t1h + Kih + ((t1l >>> 0) < (Kil >>> 0) ? 1 : 0);
	                var t1l = t1l + Wil;
	                var t1h = t1h + Wih + ((t1l >>> 0) < (Wil >>> 0) ? 1 : 0);

	                // t2 = sigma0 + maj
	                var t2l = sigma0l + majl;
	                var t2h = sigma0h + majh + ((t2l >>> 0) < (sigma0l >>> 0) ? 1 : 0);

	                // Update working variables
	                hh = gh;
	                hl = gl;
	                gh = fh;
	                gl = fl;
	                fh = eh;
	                fl = el;
	                el = (dl + t1l) | 0;
	                eh = (dh + t1h + ((el >>> 0) < (dl >>> 0) ? 1 : 0)) | 0;
	                dh = ch;
	                dl = cl;
	                ch = bh;
	                cl = bl;
	                bh = ah;
	                bl = al;
	                al = (t1l + t2l) | 0;
	                ah = (t1h + t2h + ((al >>> 0) < (t1l >>> 0) ? 1 : 0)) | 0;
	            }

	            // Intermediate hash value
	            H0l = H0.low  = (H0l + al);
	            H0.high = (H0h + ah + ((H0l >>> 0) < (al >>> 0) ? 1 : 0));
	            H1l = H1.low  = (H1l + bl);
	            H1.high = (H1h + bh + ((H1l >>> 0) < (bl >>> 0) ? 1 : 0));
	            H2l = H2.low  = (H2l + cl);
	            H2.high = (H2h + ch + ((H2l >>> 0) < (cl >>> 0) ? 1 : 0));
	            H3l = H3.low  = (H3l + dl);
	            H3.high = (H3h + dh + ((H3l >>> 0) < (dl >>> 0) ? 1 : 0));
	            H4l = H4.low  = (H4l + el);
	            H4.high = (H4h + eh + ((H4l >>> 0) < (el >>> 0) ? 1 : 0));
	            H5l = H5.low  = (H5l + fl);
	            H5.high = (H5h + fh + ((H5l >>> 0) < (fl >>> 0) ? 1 : 0));
	            H6l = H6.low  = (H6l + gl);
	            H6.high = (H6h + gh + ((H6l >>> 0) < (gl >>> 0) ? 1 : 0));
	            H7l = H7.low  = (H7l + hl);
	            H7.high = (H7h + hh + ((H7l >>> 0) < (hl >>> 0) ? 1 : 0));
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;

	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x80 << (24 - nBitsLeft % 32);
	            dataWords[(((nBitsLeft + 128) >>> 10) << 5) + 30] = Math.floor(nBitsTotal / 0x100000000);
	            dataWords[(((nBitsLeft + 128) >>> 10) << 5) + 31] = nBitsTotal;
	            data.sigBytes = dataWords.length * 4;

	            // Hash final blocks
	            this._process();

	            // Convert hash to 32-bit word array before returning
	            var hash = this._hash.toX32();

	            // Return final computed hash
	            return hash;
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);
	            clone._hash = this._hash.clone();

	            return clone;
	        },

	        blockSize: 1024/32
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA512('message');
	     *     var hash = CryptoJS.SHA512(wordArray);
	     */
	    C.SHA512 = Hasher._createHelper(SHA512);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA512(message, key);
	     */
	    C.HmacSHA512 = Hasher._createHmacHelper(SHA512);
	}());


	return CryptoJS.SHA512;

}));

/***/ }),
/* 21 */
/***/ (function(module, exports) {

var g;

// This works in non-strict mode
g = (function() {
	return this;
})();

try {
	// This works if eval is allowed (see CSP)
	g = g || Function("return this")() || (1,eval)("this");
} catch(e) {
	// This works if the window reference is available
	if(typeof window === "object")
		g = window;
}

// g can still be undefined, but nothing to do about it...
// We return undefined, instead of nothing here, so it's
// easier to handle this case. if(!global) { ...}

module.exports = g;


/***/ }),
/* 22 */,
/* 23 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 基础配置
 */
var config = {
  config: {},
  init: function () {
    var tmp = __webpack_require__(66)
    this.config.config = JSON.parse(this.decode(tmp.config))
    this.config.version = tmp.version
  },

  getConfig: function (key) {
    return this.config.config[key]
  },

  /**
   * 简单的加密解密算法
   * @param str
   * @returns {string}
   */
  decode: function (code) {
    code = decodeURIComponent(code)
    var c = String.fromCharCode(code.charCodeAt(0) - code.length)
    for (var i = 1; i < code.length; i++) {
      c += String.fromCharCode(code.charCodeAt(i) - c.charCodeAt(i - 1))
    }
    return c
  },
}
config.init()

module.exports = config

/***/ }),
/* 24 */
/***/ (function(module, exports) {


/**
 * When source maps are enabled, `style-loader` uses a link element with a data-uri to
 * embed the css on the page. This breaks all relative urls because now they are relative to a
 * bundle instead of the current page.
 *
 * One solution is to only use full urls, but that may be impossible.
 *
 * Instead, this function "fixes" the relative urls to be absolute according to the current page location.
 *
 * A rudimentary test suite is located at `test/fixUrls.js` and can be run via the `npm test` command.
 *
 */

module.exports = function (css) {
  // get current location
  var location = typeof window !== "undefined" && window.location;

  if (!location) {
    throw new Error("fixUrls requires window.location");
  }

	// blank or null?
	if (!css || typeof css !== "string") {
	  return css;
  }

  var baseUrl = location.protocol + "//" + location.host;
  var currentDir = baseUrl + location.pathname.replace(/\/[^\/]*$/, "/");

	// convert each url(...)
	/*
	This regular expression is just a way to recursively match brackets within
	a string.

	 /url\s*\(  = Match on the word "url" with any whitespace after it and then a parens
	   (  = Start a capturing group
	     (?:  = Start a non-capturing group
	         [^)(]  = Match anything that isn't a parentheses
	         |  = OR
	         \(  = Match a start parentheses
	             (?:  = Start another non-capturing groups
	                 [^)(]+  = Match anything that isn't a parentheses
	                 |  = OR
	                 \(  = Match a start parentheses
	                     [^)(]*  = Match anything that isn't a parentheses
	                 \)  = Match a end parentheses
	             )  = End Group
              *\) = Match anything and then a close parens
          )  = Close non-capturing group
          *  = Match anything
       )  = Close capturing group
	 \)  = Match a close parens

	 /gi  = Get all matches, not the first.  Be case insensitive.
	 */
	var fixedCss = css.replace(/url\s*\(((?:[^)(]|\((?:[^)(]+|\([^)(]*\))*\))*)\)/gi, function(fullMatch, origUrl) {
		// strip quotes (if they exist)
		var unquotedOrigUrl = origUrl
			.trim()
			.replace(/^"(.*)"$/, function(o, $1){ return $1; })
			.replace(/^'(.*)'$/, function(o, $1){ return $1; });

		// already a full url? no change
		if (/^(#|data:|http:\/\/|https:\/\/|file:\/\/\/)/i.test(unquotedOrigUrl)) {
		  return fullMatch;
		}

		// convert the url to a full url
		var newUrl;

		if (unquotedOrigUrl.indexOf("//") === 0) {
		  	//TODO: should we add protocol?
			newUrl = unquotedOrigUrl;
		} else if (unquotedOrigUrl.indexOf("/") === 0) {
			// path should be relative to the base url
			newUrl = baseUrl + unquotedOrigUrl; // already starts with '/'
		} else {
			// path should be relative to current directory
			newUrl = currentDir + unquotedOrigUrl.replace(/^\.\//, ""); // Strip leading './'
		}

		// send back the fixed url(...)
		return "url(" + JSON.stringify(newUrl) + ")";
	});

	// send back the fixed css
	return fixedCss;
};


/***/ }),
/* 25 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(26);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../css-loader/index.js!./native-toast.css", function() {
			var newContent = require("!!../../css-loader/index.js!./native-toast.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 26 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".native-toast {\n  position: fixed;\n  background-color: rgba(50, 50, 50, .8);\n  border-radius: 33px;\n  color: white;\n  left: 50%;\n  text-align: center;\n  padding: 10px 20px;\n  opacity: 0;\n  z-index: 99999;\n  transition: transform .25s, opacity .25s, top .25s;\n  box-sizing: border-box;\n}\n\n.native-toast-bottom {\n  bottom: 50px;\n  -ms-transform: translateX(-50%) translateY(50px);\n      transform: translateX(-50%) translateY(50px)\n}\n\n.native-toast-bottom.native-toast-shown {\n  opacity: 1;\n  -ms-transform: translateX(-50%) translateY(0);\n      transform: translateX(-50%) translateY(0);\n}\n\n.native-toast-bottom.native-toast-edge {\n  bottom: 0;\n}\n\n.native-toast-top {\n  top: 50px;\n  -ms-transform: translateX(-50%) translateY(-50px);\n      transform: translateX(-50%) translateY(-50px)\n}\n\n.native-toast-top.native-toast-shown {\n  opacity: 1;\n  -ms-transform: translateX(-50%) translateY(0);\n      transform: translateX(-50%) translateY(0);\n}\n\n.native-toast-top.native-toast-edge {\n  top: 0;\n}\n\n.native-toast-center {\n  top: 0;\n  -ms-transform: translateX(-50%) translateY(-50px);\n      transform: translateX(-50%) translateY(-50px)\n}\n\n.native-toast-center.native-toast-shown {\n  opacity: 1;\n  top: 50%;\n  -ms-transform: translateX(-50%) translateY(-50%);\n      transform: translateX(-50%) translateY(-50%);\n}\n\n.native-toast-edge {\n  border-radius: 0;\n  width: 100%;\n  text-align: left;\n}\n\n@media screen and (min-width: 40rem) {\n  .native-toast:not(.native-toast-edge) {\n    max-width: 18rem;\n  }\n}\n\n/*\n  max-width does not seem to work in small screen?\n*/\n\n/*@media screen and (max-width: 768px) {\n  .native-toast:not(.native-toast-edge) {\n    max-width: 400px;\n  }\n}\n\n@media screen and (max-width: 468px) {\n  .native-toast:not(.native-toast-edge) {\n    max-width: 300px;\n  }\n}*/\n\n/* types */\n\n.native-toast-error {\n  background-color: #d92727;\n  color: white;\n}\n\n.native-toast-success {\n  background-color: #62a465;\n  color: white;\n}\n\n.native-toast-warning {\n  background-color: #fdaf17;\n  color: white;\n}\n\n.native-toast-info {\n  background-color: #5060ba;\n  color: white;\n}\n\n[class^=\"native-toast-icon-\"] {\n  vertical-align: middle;\n  margin-right: 8px\n}\n\n[class^=\"native-toast-icon-\"] svg {\n  width: 16px;\n  height: 16px;\n}\n", ""]);

// exports


/***/ }),
/* 27 */
/***/ (function(module, exports) {

(function() {
  var base64map
      = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',

  crypt = {
    // Bit-wise rotation left
    rotl: function(n, b) {
      return (n << b) | (n >>> (32 - b));
    },

    // Bit-wise rotation right
    rotr: function(n, b) {
      return (n << (32 - b)) | (n >>> b);
    },

    // Swap big-endian to little-endian and vice versa
    endian: function(n) {
      // If number given, swap endian
      if (n.constructor == Number) {
        return crypt.rotl(n, 8) & 0x00FF00FF | crypt.rotl(n, 24) & 0xFF00FF00;
      }

      // Else, assume array and swap all items
      for (var i = 0; i < n.length; i++)
        n[i] = crypt.endian(n[i]);
      return n;
    },

    // Generate an array of any length of random bytes
    randomBytes: function(n) {
      for (var bytes = []; n > 0; n--)
        bytes.push(Math.floor(Math.random() * 256));
      return bytes;
    },

    // Convert a byte array to big-endian 32-bit words
    bytesToWords: function(bytes) {
      for (var words = [], i = 0, b = 0; i < bytes.length; i++, b += 8)
        words[b >>> 5] |= bytes[i] << (24 - b % 32);
      return words;
    },

    // Convert big-endian 32-bit words to a byte array
    wordsToBytes: function(words) {
      for (var bytes = [], b = 0; b < words.length * 32; b += 8)
        bytes.push((words[b >>> 5] >>> (24 - b % 32)) & 0xFF);
      return bytes;
    },

    // Convert a byte array to a hex string
    bytesToHex: function(bytes) {
      for (var hex = [], i = 0; i < bytes.length; i++) {
        hex.push((bytes[i] >>> 4).toString(16));
        hex.push((bytes[i] & 0xF).toString(16));
      }
      return hex.join('');
    },

    // Convert a hex string to a byte array
    hexToBytes: function(hex) {
      for (var bytes = [], c = 0; c < hex.length; c += 2)
        bytes.push(parseInt(hex.substr(c, 2), 16));
      return bytes;
    },

    // Convert a byte array to a base-64 string
    bytesToBase64: function(bytes) {
      for (var base64 = [], i = 0; i < bytes.length; i += 3) {
        var triplet = (bytes[i] << 16) | (bytes[i + 1] << 8) | bytes[i + 2];
        for (var j = 0; j < 4; j++)
          if (i * 8 + j * 6 <= bytes.length * 8)
            base64.push(base64map.charAt((triplet >>> 6 * (3 - j)) & 0x3F));
          else
            base64.push('=');
      }
      return base64.join('');
    },

    // Convert a base-64 string to a byte array
    base64ToBytes: function(base64) {
      // Remove non-base-64 characters
      base64 = base64.replace(/[^A-Z0-9+\/]/ig, '');

      for (var bytes = [], i = 0, imod4 = 0; i < base64.length;
          imod4 = ++i % 4) {
        if (imod4 == 0) continue;
        bytes.push(((base64map.indexOf(base64.charAt(i - 1))
            & (Math.pow(2, -2 * imod4 + 8) - 1)) << (imod4 * 2))
            | (base64map.indexOf(base64.charAt(i)) >>> (6 - imod4 * 2)));
      }
      return bytes;
    }
  };

  module.exports = crypt;
})();


/***/ }),
/* 28 */
/***/ (function(module, exports) {

/*!
 * Determine if an object is a Buffer
 *
 * @author   Feross Aboukhadijeh <https://feross.org>
 * @license  MIT
 */

// The _isBuffer check is for Safari 5-7 support, because it's missing
// Object.prototype.constructor. Remove this eventually
module.exports = function (obj) {
  return obj != null && (isBuffer(obj) || isSlowBuffer(obj) || !!obj._isBuffer)
}

function isBuffer (obj) {
  return !!obj.constructor && typeof obj.constructor.isBuffer === 'function' && obj.constructor.isBuffer(obj)
}

// For Node v0.10 support. Remove this eventually.
function isSlowBuffer (obj) {
  return typeof obj.readFloatLE === 'function' && typeof obj.slice === 'function' && isBuffer(obj.slice(0, 0))
}


/***/ }),
/* 29 */
/***/ (function(module, exports, __webpack_require__) {

var __WEBPACK_AMD_DEFINE_FACTORY__, __WEBPACK_AMD_DEFINE_RESULT__;!function(e,t,i){"use strict"; true?!(__WEBPACK_AMD_DEFINE_FACTORY__ = (i),
				__WEBPACK_AMD_DEFINE_RESULT__ = (typeof __WEBPACK_AMD_DEFINE_FACTORY__ === 'function' ?
				(__WEBPACK_AMD_DEFINE_FACTORY__.call(exports, __webpack_require__, exports, module)) :
				__WEBPACK_AMD_DEFINE_FACTORY__),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__)):"undefined"!=typeof module&&module.exports?module.exports=i():t.exports?t.exports=i():t[e]=i()}("Fingerprint2",this,function(){"use strict";var e=function(t){if(!(this instanceof e))return new e(t);var i={swfContainerId:"fingerprintjs2",swfPath:"flash/compiled/FontList.swf",detectScreenOrientation:!0,sortPluginsFor:[/palemoon/i],userDefinedFonts:[]};this.options=this.extend(t,i),this.nativeForEach=Array.prototype.forEach,this.nativeMap=Array.prototype.map};return e.prototype={extend:function(e,t){if(null==e)return t;for(var i in e)null!=e[i]&&t[i]!==e[i]&&(t[i]=e[i]);return t},get:function(e){var t=[];t=this.userAgentKey(t),t=this.languageKey(t),t=this.colorDepthKey(t),t=this.pixelRatioKey(t),t=this.hardwareConcurrencyKey(t),t=this.screenResolutionKey(t),t=this.availableScreenResolutionKey(t),t=this.timezoneOffsetKey(t),t=this.sessionStorageKey(t),t=this.localStorageKey(t),t=this.indexedDbKey(t),t=this.addBehaviorKey(t),t=this.openDatabaseKey(t),t=this.cpuClassKey(t),t=this.platformKey(t),t=this.doNotTrackKey(t),t=this.pluginsKey(t),t=this.canvasKey(t),t=this.webglKey(t),t=this.adBlockKey(t),t=this.hasLiedLanguagesKey(t),t=this.hasLiedResolutionKey(t),t=this.hasLiedOsKey(t),t=this.hasLiedBrowserKey(t),t=this.touchSupportKey(t),t=this.customEntropyFunction(t);var i=this;this.fontsKey(t,function(t){var a=[];i.each(t,function(e){var t=e.value;"undefined"!=typeof e.value.join&&(t=e.value.join(";")),a.push(t)});var r=i.x64hash128(a.join("~~~"),31);return e(r,t)})},customEntropyFunction:function(e){return"function"==typeof this.options.customFunction&&e.push({key:"custom",value:this.options.customFunction()}),e},userAgentKey:function(e){return this.options.excludeUserAgent||e.push({key:"user_agent",value:this.getUserAgent()}),e},getUserAgent:function(){return navigator.userAgent},languageKey:function(e){return this.options.excludeLanguage||e.push({key:"language",value:navigator.language||navigator.userLanguage||navigator.browserLanguage||navigator.systemLanguage||""}),e},colorDepthKey:function(e){return this.options.excludeColorDepth||e.push({key:"color_depth",value:screen.colorDepth||-1}),e},pixelRatioKey:function(e){return this.options.excludePixelRatio||e.push({key:"pixel_ratio",value:this.getPixelRatio()}),e},getPixelRatio:function(){return window.devicePixelRatio||""},screenResolutionKey:function(e){return this.options.excludeScreenResolution?e:this.getScreenResolution(e)},getScreenResolution:function(e){var t;return t=this.options.detectScreenOrientation&&screen.height>screen.width?[screen.height,screen.width]:[screen.width,screen.height],"undefined"!=typeof t&&e.push({key:"resolution",value:t}),e},availableScreenResolutionKey:function(e){return this.options.excludeAvailableScreenResolution?e:this.getAvailableScreenResolution(e)},getAvailableScreenResolution:function(e){var t;return screen.availWidth&&screen.availHeight&&(t=this.options.detectScreenOrientation?screen.availHeight>screen.availWidth?[screen.availHeight,screen.availWidth]:[screen.availWidth,screen.availHeight]:[screen.availHeight,screen.availWidth]),"undefined"!=typeof t&&e.push({key:"available_resolution",value:t}),e},timezoneOffsetKey:function(e){return this.options.excludeTimezoneOffset||e.push({key:"timezone_offset",value:(new Date).getTimezoneOffset()}),e},sessionStorageKey:function(e){return!this.options.excludeSessionStorage&&this.hasSessionStorage()&&e.push({key:"session_storage",value:1}),e},localStorageKey:function(e){return!this.options.excludeSessionStorage&&this.hasLocalStorage()&&e.push({key:"local_storage",value:1}),e},indexedDbKey:function(e){return!this.options.excludeIndexedDB&&this.hasIndexedDB()&&e.push({key:"indexed_db",value:1}),e},addBehaviorKey:function(e){return document.body&&!this.options.excludeAddBehavior&&document.body.addBehavior&&e.push({key:"add_behavior",value:1}),e},openDatabaseKey:function(e){return!this.options.excludeOpenDatabase&&window.openDatabase&&e.push({key:"open_database",value:1}),e},cpuClassKey:function(e){return this.options.excludeCpuClass||e.push({key:"cpu_class",value:this.getNavigatorCpuClass()}),e},platformKey:function(e){return this.options.excludePlatform||e.push({key:"navigator_platform",value:this.getNavigatorPlatform()}),e},doNotTrackKey:function(e){return this.options.excludeDoNotTrack||e.push({key:"do_not_track",value:this.getDoNotTrack()}),e},canvasKey:function(e){return!this.options.excludeCanvas&&this.isCanvasSupported()&&e.push({key:"canvas",value:this.getCanvasFp()}),e},webglKey:function(e){return this.options.excludeWebGL?e:this.isWebGlSupported()?(e.push({key:"webgl",value:this.getWebglFp()}),e):e},adBlockKey:function(e){return this.options.excludeAdBlock||e.push({key:"adblock",value:this.getAdBlock()}),e},hasLiedLanguagesKey:function(e){return this.options.excludeHasLiedLanguages||e.push({key:"has_lied_languages",value:this.getHasLiedLanguages()}),e},hasLiedResolutionKey:function(e){return this.options.excludeHasLiedResolution||e.push({key:"has_lied_resolution",value:this.getHasLiedResolution()}),e},hasLiedOsKey:function(e){return this.options.excludeHasLiedOs||e.push({key:"has_lied_os",value:this.getHasLiedOs()}),e},hasLiedBrowserKey:function(e){return this.options.excludeHasLiedBrowser||e.push({key:"has_lied_browser",value:this.getHasLiedBrowser()}),e},fontsKey:function(e,t){return this.options.excludeJsFonts?this.flashFontsKey(e,t):this.jsFontsKey(e,t)},flashFontsKey:function(e,t){return this.options.excludeFlashFonts?t(e):this.hasSwfObjectLoaded()&&this.hasMinFlashInstalled()?"undefined"==typeof this.options.swfPath?t(e):void this.loadSwfAndDetectFonts(function(i){e.push({key:"swf_fonts",value:i.join(";")}),t(e)}):t(e)},jsFontsKey:function(e,t){var i=this;return setTimeout(function(){var a=["monospace","sans-serif","serif"],r=["Andale Mono","Arial","Arial Black","Arial Hebrew","Arial MT","Arial Narrow","Arial Rounded MT Bold","Arial Unicode MS","Bitstream Vera Sans Mono","Book Antiqua","Bookman Old Style","Calibri","Cambria","Cambria Math","Century","Century Gothic","Century Schoolbook","Comic Sans","Comic Sans MS","Consolas","Courier","Courier New","Garamond","Geneva","Georgia","Helvetica","Helvetica Neue","Impact","Lucida Bright","Lucida Calligraphy","Lucida Console","Lucida Fax","LUCIDA GRANDE","Lucida Handwriting","Lucida Sans","Lucida Sans Typewriter","Lucida Sans Unicode","Microsoft Sans Serif","Monaco","Monotype Corsiva","MS Gothic","MS Outlook","MS PGothic","MS Reference Sans Serif","MS Sans Serif","MS Serif","MYRIAD","MYRIAD PRO","Palatino","Palatino Linotype","Segoe Print","Segoe Script","Segoe UI","Segoe UI Light","Segoe UI Semibold","Segoe UI Symbol","Tahoma","Times","Times New Roman","Times New Roman PS","Trebuchet MS","Verdana","Wingdings","Wingdings 2","Wingdings 3"],n=["Abadi MT Condensed Light","Academy Engraved LET","ADOBE CASLON PRO","Adobe Garamond","ADOBE GARAMOND PRO","Agency FB","Aharoni","Albertus Extra Bold","Albertus Medium","Algerian","Amazone BT","American Typewriter","American Typewriter Condensed","AmerType Md BT","Andalus","Angsana New","AngsanaUPC","Antique Olive","Aparajita","Apple Chancery","Apple Color Emoji","Apple SD Gothic Neo","Arabic Typesetting","ARCHER","ARNO PRO","Arrus BT","Aurora Cn BT","AvantGarde Bk BT","AvantGarde Md BT","AVENIR","Ayuthaya","Bandy","Bangla Sangam MN","Bank Gothic","BankGothic Md BT","Baskerville","Baskerville Old Face","Batang","BatangChe","Bauer Bodoni","Bauhaus 93","Bazooka","Bell MT","Bembo","Benguiat Bk BT","Berlin Sans FB","Berlin Sans FB Demi","Bernard MT Condensed","BernhardFashion BT","BernhardMod BT","Big Caslon","BinnerD","Blackadder ITC","BlairMdITC TT","Bodoni 72","Bodoni 72 Oldstyle","Bodoni 72 Smallcaps","Bodoni MT","Bodoni MT Black","Bodoni MT Condensed","Bodoni MT Poster Compressed","Bookshelf Symbol 7","Boulder","Bradley Hand","Bradley Hand ITC","Bremen Bd BT","Britannic Bold","Broadway","Browallia New","BrowalliaUPC","Brush Script MT","Californian FB","Calisto MT","Calligrapher","Candara","CaslonOpnface BT","Castellar","Centaur","Cezanne","CG Omega","CG Times","Chalkboard","Chalkboard SE","Chalkduster","Charlesworth","Charter Bd BT","Charter BT","Chaucer","ChelthmITC Bk BT","Chiller","Clarendon","Clarendon Condensed","CloisterBlack BT","Cochin","Colonna MT","Constantia","Cooper Black","Copperplate","Copperplate Gothic","Copperplate Gothic Bold","Copperplate Gothic Light","CopperplGoth Bd BT","Corbel","Cordia New","CordiaUPC","Cornerstone","Coronet","Cuckoo","Curlz MT","DaunPenh","Dauphin","David","DB LCD Temp","DELICIOUS","Denmark","DFKai-SB","Didot","DilleniaUPC","DIN","DokChampa","Dotum","DotumChe","Ebrima","Edwardian Script ITC","Elephant","English 111 Vivace BT","Engravers MT","EngraversGothic BT","Eras Bold ITC","Eras Demi ITC","Eras Light ITC","Eras Medium ITC","EucrosiaUPC","Euphemia","Euphemia UCAS","EUROSTILE","Exotc350 Bd BT","FangSong","Felix Titling","Fixedsys","FONTIN","Footlight MT Light","Forte","FrankRuehl","Fransiscan","Freefrm721 Blk BT","FreesiaUPC","Freestyle Script","French Script MT","FrnkGothITC Bk BT","Fruitger","FRUTIGER","Futura","Futura Bk BT","Futura Lt BT","Futura Md BT","Futura ZBlk BT","FuturaBlack BT","Gabriola","Galliard BT","Gautami","Geeza Pro","Geometr231 BT","Geometr231 Hv BT","Geometr231 Lt BT","GeoSlab 703 Lt BT","GeoSlab 703 XBd BT","Gigi","Gill Sans","Gill Sans MT","Gill Sans MT Condensed","Gill Sans MT Ext Condensed Bold","Gill Sans Ultra Bold","Gill Sans Ultra Bold Condensed","Gisha","Gloucester MT Extra Condensed","GOTHAM","GOTHAM BOLD","Goudy Old Style","Goudy Stout","GoudyHandtooled BT","GoudyOLSt BT","Gujarati Sangam MN","Gulim","GulimChe","Gungsuh","GungsuhChe","Gurmukhi MN","Haettenschweiler","Harlow Solid Italic","Harrington","Heather","Heiti SC","Heiti TC","HELV","Herald","High Tower Text","Hiragino Kaku Gothic ProN","Hiragino Mincho ProN","Hoefler Text","Humanst 521 Cn BT","Humanst521 BT","Humanst521 Lt BT","Imprint MT Shadow","Incised901 Bd BT","Incised901 BT","Incised901 Lt BT","INCONSOLATA","Informal Roman","Informal011 BT","INTERSTATE","IrisUPC","Iskoola Pota","JasmineUPC","Jazz LET","Jenson","Jester","Jokerman","Juice ITC","Kabel Bk BT","Kabel Ult BT","Kailasa","KaiTi","Kalinga","Kannada Sangam MN","Kartika","Kaufmann Bd BT","Kaufmann BT","Khmer UI","KodchiangUPC","Kokila","Korinna BT","Kristen ITC","Krungthep","Kunstler Script","Lao UI","Latha","Leelawadee","Letter Gothic","Levenim MT","LilyUPC","Lithograph","Lithograph Light","Long Island","Lydian BT","Magneto","Maiandra GD","Malayalam Sangam MN","Malgun Gothic","Mangal","Marigold","Marion","Marker Felt","Market","Marlett","Matisse ITC","Matura MT Script Capitals","Meiryo","Meiryo UI","Microsoft Himalaya","Microsoft JhengHei","Microsoft New Tai Lue","Microsoft PhagsPa","Microsoft Tai Le","Microsoft Uighur","Microsoft YaHei","Microsoft Yi Baiti","MingLiU","MingLiU_HKSCS","MingLiU_HKSCS-ExtB","MingLiU-ExtB","Minion","Minion Pro","Miriam","Miriam Fixed","Mistral","Modern","Modern No. 20","Mona Lisa Solid ITC TT","Mongolian Baiti","MONO","MoolBoran","Mrs Eaves","MS LineDraw","MS Mincho","MS PMincho","MS Reference Specialty","MS UI Gothic","MT Extra","MUSEO","MV Boli","Nadeem","Narkisim","NEVIS","News Gothic","News GothicMT","NewsGoth BT","Niagara Engraved","Niagara Solid","Noteworthy","NSimSun","Nyala","OCR A Extended","Old Century","Old English Text MT","Onyx","Onyx BT","OPTIMA","Oriya Sangam MN","OSAKA","OzHandicraft BT","Palace Script MT","Papyrus","Parchment","Party LET","Pegasus","Perpetua","Perpetua Titling MT","PetitaBold","Pickwick","Plantagenet Cherokee","Playbill","PMingLiU","PMingLiU-ExtB","Poor Richard","Poster","PosterBodoni BT","PRINCETOWN LET","Pristina","PTBarnum BT","Pythagoras","Raavi","Rage Italic","Ravie","Ribbon131 Bd BT","Rockwell","Rockwell Condensed","Rockwell Extra Bold","Rod","Roman","Sakkal Majalla","Santa Fe LET","Savoye LET","Sceptre","Script","Script MT Bold","SCRIPTINA","Serifa","Serifa BT","Serifa Th BT","ShelleyVolante BT","Sherwood","Shonar Bangla","Showcard Gothic","Shruti","Signboard","SILKSCREEN","SimHei","Simplified Arabic","Simplified Arabic Fixed","SimSun","SimSun-ExtB","Sinhala Sangam MN","Sketch Rockwell","Skia","Small Fonts","Snap ITC","Snell Roundhand","Socket","Souvenir Lt BT","Staccato222 BT","Steamer","Stencil","Storybook","Styllo","Subway","Swis721 BlkEx BT","Swiss911 XCm BT","Sylfaen","Synchro LET","System","Tamil Sangam MN","Technical","Teletype","Telugu Sangam MN","Tempus Sans ITC","Terminal","Thonburi","Traditional Arabic","Trajan","TRAJAN PRO","Tristan","Tubular","Tunga","Tw Cen MT","Tw Cen MT Condensed","Tw Cen MT Condensed Extra Bold","TypoUpright BT","Unicorn","Univers","Univers CE 55 Medium","Univers Condensed","Utsaah","Vagabond","Vani","Vijaya","Viner Hand ITC","VisualUI","Vivaldi","Vladimir Script","Vrinda","Westminster","WHITNEY","Wide Latin","ZapfEllipt BT","ZapfHumnst BT","ZapfHumnst Dm BT","Zapfino","Zurich BlkEx BT","Zurich Ex BT","ZWAdobeF"];i.options.extendedJsFonts&&(r=r.concat(n)),r=r.concat(i.options.userDefinedFonts);var o="mmmmmmmmmmlli",s="72px",l=document.getElementsByTagName("body")[0],h=document.createElement("div"),u=document.createElement("div"),c={},d={},g=function(){var e=document.createElement("span");return e.style.position="absolute",e.style.left="-9999px",e.style.fontSize=s,e.style.lineHeight="normal",e.innerHTML=o,e},p=function(e,t){var i=g();return i.style.fontFamily="'"+e+"',"+t,i},f=function(){for(var e=[],t=0,i=a.length;t<i;t++){var r=g();r.style.fontFamily=a[t],h.appendChild(r),e.push(r)}return e},m=function(){for(var e={},t=0,i=r.length;t<i;t++){for(var n=[],o=0,s=a.length;o<s;o++){var l=p(r[t],a[o]);u.appendChild(l),n.push(l)}e[r[t]]=n}return e},T=function(e){for(var t=!1,i=0;i<a.length;i++)if(t=e[i].offsetWidth!==c[a[i]]||e[i].offsetHeight!==d[a[i]])return t;return t},S=f();l.appendChild(h);for(var x=0,v=a.length;x<v;x++)c[a[x]]=S[x].offsetWidth,d[a[x]]=S[x].offsetHeight;var E=m();l.appendChild(u);for(var M=[],A=0,y=r.length;A<y;A++)T(E[r[A]])&&M.push(r[A]);l.removeChild(u),l.removeChild(h),e.push({key:"js_fonts",value:M}),t(e)},1)},pluginsKey:function(e){return this.options.excludePlugins||(this.isIE()?this.options.excludeIEPlugins||e.push({key:"ie_plugins",value:this.getIEPlugins()}):e.push({key:"regular_plugins",value:this.getRegularPlugins()})),e},getRegularPlugins:function(){for(var e=[],t=0,i=navigator.plugins.length;t<i;t++)e.push(navigator.plugins[t]);return this.pluginsShouldBeSorted()&&(e=e.sort(function(e,t){return e.name>t.name?1:e.name<t.name?-1:0})),this.map(e,function(e){var t=this.map(e,function(e){return[e.type,e.suffixes].join("~")}).join(",");return[e.name,e.description,t].join("::")},this)},getIEPlugins:function(){var e=[];if(Object.getOwnPropertyDescriptor&&Object.getOwnPropertyDescriptor(window,"ActiveXObject")||"ActiveXObject"in window){var t=["AcroPDF.PDF","Adodb.Stream","AgControl.AgControl","DevalVRXCtrl.DevalVRXCtrl.1","MacromediaFlashPaper.MacromediaFlashPaper","Msxml2.DOMDocument","Msxml2.XMLHTTP","PDF.PdfCtrl","QuickTime.QuickTime","QuickTimeCheckObject.QuickTimeCheck.1","RealPlayer","RealPlayer.RealPlayer(tm) ActiveX Control (32-bit)","RealVideo.RealVideo(tm) ActiveX Control (32-bit)","Scripting.Dictionary","SWCtl.SWCtl","Shell.UIHelper","ShockwaveFlash.ShockwaveFlash","Skype.Detection","TDCCtl.TDCCtl","WMPlayer.OCX","rmocx.RealPlayer G2 Control","rmocx.RealPlayer G2 Control.1"];e=this.map(t,function(e){try{return new ActiveXObject(e),e}catch(t){return null}})}return navigator.plugins&&(e=e.concat(this.getRegularPlugins())),e},pluginsShouldBeSorted:function(){for(var e=!1,t=0,i=this.options.sortPluginsFor.length;t<i;t++){var a=this.options.sortPluginsFor[t];if(navigator.userAgent.match(a)){e=!0;break}}return e},touchSupportKey:function(e){return this.options.excludeTouchSupport||e.push({key:"touch_support",value:this.getTouchSupport()}),e},hardwareConcurrencyKey:function(e){return this.options.excludeHardwareConcurrency||e.push({key:"hardware_concurrency",value:this.getHardwareConcurrency()}),e},hasSessionStorage:function(){try{return!!window.sessionStorage}catch(e){return!0}},hasLocalStorage:function(){try{return!!window.localStorage}catch(e){return!0}},hasIndexedDB:function(){try{return!!window.indexedDB}catch(e){return!0}},getHardwareConcurrency:function(){return navigator.hardwareConcurrency?navigator.hardwareConcurrency:"unknown"},getNavigatorCpuClass:function(){return navigator.cpuClass?navigator.cpuClass:"unknown"},getNavigatorPlatform:function(){return navigator.platform?navigator.platform:"unknown"},getDoNotTrack:function(){return navigator.doNotTrack?navigator.doNotTrack:navigator.msDoNotTrack?navigator.msDoNotTrack:window.doNotTrack?window.doNotTrack:"unknown"},getTouchSupport:function(){var e=0,t=!1;"undefined"!=typeof navigator.maxTouchPoints?e=navigator.maxTouchPoints:"undefined"!=typeof navigator.msMaxTouchPoints&&(e=navigator.msMaxTouchPoints);try{document.createEvent("TouchEvent"),t=!0}catch(i){}var a="ontouchstart"in window;return[e,t,a]},getCanvasFp:function(){var e=[],t=document.createElement("canvas");t.width=2e3,t.height=200,t.style.display="inline";var i=t.getContext("2d");return i.rect(0,0,10,10),i.rect(2,2,6,6),e.push("canvas winding:"+(i.isPointInPath(5,5,"evenodd")===!1?"yes":"no")),i.textBaseline="alphabetic",i.fillStyle="#f60",i.fillRect(125,1,62,20),i.fillStyle="#069",this.options.dontUseFakeFontInCanvas?i.font="11pt Arial":i.font="11pt no-real-font-123",i.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03",2,15),i.fillStyle="rgba(102, 204, 0, 0.2)",i.font="18pt Arial",i.fillText("Cwm fjordbank glyphs vext quiz, \ud83d\ude03",4,45),i.globalCompositeOperation="multiply",i.fillStyle="rgb(255,0,255)",i.beginPath(),i.arc(50,50,50,0,2*Math.PI,!0),i.closePath(),i.fill(),i.fillStyle="rgb(0,255,255)",i.beginPath(),i.arc(100,50,50,0,2*Math.PI,!0),i.closePath(),i.fill(),i.fillStyle="rgb(255,255,0)",i.beginPath(),i.arc(75,100,50,0,2*Math.PI,!0),i.closePath(),i.fill(),i.fillStyle="rgb(255,0,255)",i.arc(75,75,75,0,2*Math.PI,!0),i.arc(75,75,25,0,2*Math.PI,!0),i.fill("evenodd"),e.push("canvas fp:"+t.toDataURL()),e.join("~")},getWebglFp:function(){var e,t=function(t){return e.clearColor(0,0,0,1),e.enable(e.DEPTH_TEST),e.depthFunc(e.LEQUAL),e.clear(e.COLOR_BUFFER_BIT|e.DEPTH_BUFFER_BIT),"["+t[0]+", "+t[1]+"]"},i=function(e){var t,i=e.getExtension("EXT_texture_filter_anisotropic")||e.getExtension("WEBKIT_EXT_texture_filter_anisotropic")||e.getExtension("MOZ_EXT_texture_filter_anisotropic");return i?(t=e.getParameter(i.MAX_TEXTURE_MAX_ANISOTROPY_EXT),0===t&&(t=2),t):null};if(e=this.getWebglCanvas(),!e)return null;var a=[],r="attribute vec2 attrVertex;varying vec2 varyinTexCoordinate;uniform vec2 uniformOffset;void main(){varyinTexCoordinate=attrVertex+uniformOffset;gl_Position=vec4(attrVertex,0,1);}",n="precision mediump float;varying vec2 varyinTexCoordinate;void main() {gl_FragColor=vec4(varyinTexCoordinate,0,1);}",o=e.createBuffer();e.bindBuffer(e.ARRAY_BUFFER,o);var s=new Float32Array([-.2,-.9,0,.4,-.26,0,0,.732134444,0]);e.bufferData(e.ARRAY_BUFFER,s,e.STATIC_DRAW),o.itemSize=3,o.numItems=3;var l=e.createProgram(),h=e.createShader(e.VERTEX_SHADER);e.shaderSource(h,r),e.compileShader(h);var u=e.createShader(e.FRAGMENT_SHADER);e.shaderSource(u,n),e.compileShader(u),e.attachShader(l,h),e.attachShader(l,u),e.linkProgram(l),e.useProgram(l),l.vertexPosAttrib=e.getAttribLocation(l,"attrVertex"),l.offsetUniform=e.getUniformLocation(l,"uniformOffset"),e.enableVertexAttribArray(l.vertexPosArray),e.vertexAttribPointer(l.vertexPosAttrib,o.itemSize,e.FLOAT,!1,0,0),e.uniform2f(l.offsetUniform,1,1),e.drawArrays(e.TRIANGLE_STRIP,0,o.numItems),null!=e.canvas&&a.push(e.canvas.toDataURL()),a.push("extensions:"+e.getSupportedExtensions().join(";")),a.push("webgl aliased line width range:"+t(e.getParameter(e.ALIASED_LINE_WIDTH_RANGE))),a.push("webgl aliased point size range:"+t(e.getParameter(e.ALIASED_POINT_SIZE_RANGE))),a.push("webgl alpha bits:"+e.getParameter(e.ALPHA_BITS)),a.push("webgl antialiasing:"+(e.getContextAttributes().antialias?"yes":"no")),a.push("webgl blue bits:"+e.getParameter(e.BLUE_BITS)),a.push("webgl depth bits:"+e.getParameter(e.DEPTH_BITS)),a.push("webgl green bits:"+e.getParameter(e.GREEN_BITS)),a.push("webgl max anisotropy:"+i(e)),a.push("webgl max combined texture image units:"+e.getParameter(e.MAX_COMBINED_TEXTURE_IMAGE_UNITS)),a.push("webgl max cube map texture size:"+e.getParameter(e.MAX_CUBE_MAP_TEXTURE_SIZE)),a.push("webgl max fragment uniform vectors:"+e.getParameter(e.MAX_FRAGMENT_UNIFORM_VECTORS)),a.push("webgl max render buffer size:"+e.getParameter(e.MAX_RENDERBUFFER_SIZE)),a.push("webgl max texture image units:"+e.getParameter(e.MAX_TEXTURE_IMAGE_UNITS)),a.push("webgl max texture size:"+e.getParameter(e.MAX_TEXTURE_SIZE)),a.push("webgl max varying vectors:"+e.getParameter(e.MAX_VARYING_VECTORS)),a.push("webgl max vertex attribs:"+e.getParameter(e.MAX_VERTEX_ATTRIBS)),a.push("webgl max vertex texture image units:"+e.getParameter(e.MAX_VERTEX_TEXTURE_IMAGE_UNITS)),a.push("webgl max vertex uniform vectors:"+e.getParameter(e.MAX_VERTEX_UNIFORM_VECTORS)),a.push("webgl max viewport dims:"+t(e.getParameter(e.MAX_VIEWPORT_DIMS))),a.push("webgl red bits:"+e.getParameter(e.RED_BITS)),a.push("webgl renderer:"+e.getParameter(e.RENDERER)),a.push("webgl shading language version:"+e.getParameter(e.SHADING_LANGUAGE_VERSION)),a.push("webgl stencil bits:"+e.getParameter(e.STENCIL_BITS)),a.push("webgl vendor:"+e.getParameter(e.VENDOR)),a.push("webgl version:"+e.getParameter(e.VERSION));try{var c=e.getExtension("WEBGL_debug_renderer_info");c&&(a.push("webgl unmasked vendor:"+e.getParameter(c.UNMASKED_VENDOR_WEBGL)),a.push("webgl unmasked renderer:"+e.getParameter(c.UNMASKED_RENDERER_WEBGL)))}catch(d){}return e.getShaderPrecisionFormat?(a.push("webgl vertex shader high float precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_FLOAT).precision),a.push("webgl vertex shader high float precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_FLOAT).rangeMin),a.push("webgl vertex shader high float precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_FLOAT).rangeMax),a.push("webgl vertex shader medium float precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_FLOAT).precision),a.push("webgl vertex shader medium float precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_FLOAT).rangeMin),a.push("webgl vertex shader medium float precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_FLOAT).rangeMax),a.push("webgl vertex shader low float precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_FLOAT).precision),a.push("webgl vertex shader low float precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_FLOAT).rangeMin),a.push("webgl vertex shader low float precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_FLOAT).rangeMax),a.push("webgl fragment shader high float precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_FLOAT).precision),a.push("webgl fragment shader high float precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_FLOAT).rangeMin),a.push("webgl fragment shader high float precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_FLOAT).rangeMax),a.push("webgl fragment shader medium float precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_FLOAT).precision),a.push("webgl fragment shader medium float precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_FLOAT).rangeMin),a.push("webgl fragment shader medium float precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_FLOAT).rangeMax),a.push("webgl fragment shader low float precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_FLOAT).precision),a.push("webgl fragment shader low float precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_FLOAT).rangeMin),a.push("webgl fragment shader low float precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_FLOAT).rangeMax),a.push("webgl vertex shader high int precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_INT).precision),a.push("webgl vertex shader high int precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_INT).rangeMin),a.push("webgl vertex shader high int precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.HIGH_INT).rangeMax),a.push("webgl vertex shader medium int precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_INT).precision),a.push("webgl vertex shader medium int precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_INT).rangeMin),a.push("webgl vertex shader medium int precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.MEDIUM_INT).rangeMax),a.push("webgl vertex shader low int precision:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_INT).precision),a.push("webgl vertex shader low int precision rangeMin:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_INT).rangeMin),a.push("webgl vertex shader low int precision rangeMax:"+e.getShaderPrecisionFormat(e.VERTEX_SHADER,e.LOW_INT).rangeMax),a.push("webgl fragment shader high int precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_INT).precision),a.push("webgl fragment shader high int precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_INT).rangeMin),a.push("webgl fragment shader high int precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.HIGH_INT).rangeMax),a.push("webgl fragment shader medium int precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_INT).precision),a.push("webgl fragment shader medium int precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_INT).rangeMin),a.push("webgl fragment shader medium int precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.MEDIUM_INT).rangeMax),a.push("webgl fragment shader low int precision:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_INT).precision),a.push("webgl fragment shader low int precision rangeMin:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_INT).rangeMin),a.push("webgl fragment shader low int precision rangeMax:"+e.getShaderPrecisionFormat(e.FRAGMENT_SHADER,e.LOW_INT).rangeMax),a.join("~")):a.join("~")},getAdBlock:function(){var e=document.createElement("div");e.innerHTML="&nbsp;",e.className="adsbox";var t=!1;try{document.body.appendChild(e),t=0===document.getElementsByClassName("adsbox")[0].offsetHeight,document.body.removeChild(e)}catch(i){t=!1}return t},getHasLiedLanguages:function(){if("undefined"!=typeof navigator.languages)try{var e=navigator.languages[0].substr(0,2);if(e!==navigator.language.substr(0,2))return!0}catch(t){return!0}return!1},getHasLiedResolution:function(){return screen.width<screen.availWidth||screen.height<screen.availHeight},getHasLiedOs:function(){var e,t=navigator.userAgent.toLowerCase(),i=navigator.oscpu,a=navigator.platform.toLowerCase();e=t.indexOf("windows phone")>=0?"Windows Phone":t.indexOf("win")>=0?"Windows":t.indexOf("android")>=0?"Android":t.indexOf("linux")>=0?"Linux":t.indexOf("iphone")>=0||t.indexOf("ipad")>=0?"iOS":t.indexOf("mac")>=0?"Mac":"Other";var r;if(r="ontouchstart"in window||navigator.maxTouchPoints>0||navigator.msMaxTouchPoints>0,r&&"Windows Phone"!==e&&"Android"!==e&&"iOS"!==e&&"Other"!==e)return!0;if("undefined"!=typeof i){if(i=i.toLowerCase(),i.indexOf("win")>=0&&"Windows"!==e&&"Windows Phone"!==e)return!0;if(i.indexOf("linux")>=0&&"Linux"!==e&&"Android"!==e)return!0;if(i.indexOf("mac")>=0&&"Mac"!==e&&"iOS"!==e)return!0;if(0===i.indexOf("win")&&0===i.indexOf("linux")&&i.indexOf("mac")>=0&&"other"!==e)return!0}return a.indexOf("win")>=0&&"Windows"!==e&&"Windows Phone"!==e||((a.indexOf("linux")>=0||a.indexOf("android")>=0||a.indexOf("pike")>=0)&&"Linux"!==e&&"Android"!==e||((a.indexOf("mac")>=0||a.indexOf("ipad")>=0||a.indexOf("ipod")>=0||a.indexOf("iphone")>=0)&&"Mac"!==e&&"iOS"!==e||(0===a.indexOf("win")&&0===a.indexOf("linux")&&a.indexOf("mac")>=0&&"other"!==e||"undefined"==typeof navigator.plugins&&"Windows"!==e&&"Windows Phone"!==e)))},getHasLiedBrowser:function(){var e,t=navigator.userAgent.toLowerCase(),i=navigator.productSub;if(e=t.indexOf("firefox")>=0?"Firefox":t.indexOf("opera")>=0||t.indexOf("opr")>=0?"Opera":t.indexOf("chrome")>=0?"Chrome":t.indexOf("safari")>=0?"Safari":t.indexOf("trident")>=0?"Internet Explorer":"Other",("Chrome"===e||"Safari"===e||"Opera"===e)&&"20030107"!==i)return!0;var a=eval.toString().length;if(37===a&&"Safari"!==e&&"Firefox"!==e&&"Other"!==e)return!0;if(39===a&&"Internet Explorer"!==e&&"Other"!==e)return!0;if(33===a&&"Chrome"!==e&&"Opera"!==e&&"Other"!==e)return!0;var r;try{throw"a"}catch(n){try{n.toSource(),r=!0}catch(o){r=!1}}return!(!r||"Firefox"===e||"Other"===e)},isCanvasSupported:function(){var e=document.createElement("canvas");return!(!e.getContext||!e.getContext("2d"))},isWebGlSupported:function(){if(!this.isCanvasSupported())return!1;var e,t=document.createElement("canvas");try{e=t.getContext&&(t.getContext("webgl")||t.getContext("experimental-webgl"))}catch(i){e=!1}return!!window.WebGLRenderingContext&&!!e},isIE:function(){return"Microsoft Internet Explorer"===navigator.appName||!("Netscape"!==navigator.appName||!/Trident/.test(navigator.userAgent))},hasSwfObjectLoaded:function(){return"undefined"!=typeof window.swfobject},hasMinFlashInstalled:function(){return swfobject.hasFlashPlayerVersion("9.0.0")},addFlashDivNode:function(){var e=document.createElement("div");e.setAttribute("id",this.options.swfContainerId),document.body.appendChild(e)},loadSwfAndDetectFonts:function(e){var t="___fp_swf_loaded";window[t]=function(t){e(t)};var i=this.options.swfContainerId;this.addFlashDivNode();var a={onReady:t},r={allowScriptAccess:"always",menu:"false"};swfobject.embedSWF(this.options.swfPath,i,"1","1","9.0.0",!1,a,r,{})},getWebglCanvas:function(){var e=document.createElement("canvas"),t=null;try{t=e.getContext("webgl")||e.getContext("experimental-webgl")}catch(i){}return t||(t=null),t},each:function(e,t,i){if(null!==e)if(this.nativeForEach&&e.forEach===this.nativeForEach)e.forEach(t,i);else if(e.length===+e.length){for(var a=0,r=e.length;a<r;a++)if(t.call(i,e[a],a,e)==={})return}else for(var n in e)if(e.hasOwnProperty(n)&&t.call(i,e[n],n,e)==={})return},map:function(e,t,i){var a=[];return null==e?a:this.nativeMap&&e.map===this.nativeMap?e.map(t,i):(this.each(e,function(e,r,n){a[a.length]=t.call(i,e,r,n)}),a)},x64Add:function(e,t){e=[e[0]>>>16,65535&e[0],e[1]>>>16,65535&e[1]],t=[t[0]>>>16,65535&t[0],t[1]>>>16,65535&t[1]];var i=[0,0,0,0];return i[3]+=e[3]+t[3],i[2]+=i[3]>>>16,i[3]&=65535,i[2]+=e[2]+t[2],i[1]+=i[2]>>>16,i[2]&=65535,i[1]+=e[1]+t[1],i[0]+=i[1]>>>16,i[1]&=65535,i[0]+=e[0]+t[0],i[0]&=65535,[i[0]<<16|i[1],i[2]<<16|i[3]]},x64Multiply:function(e,t){e=[e[0]>>>16,65535&e[0],e[1]>>>16,65535&e[1]],t=[t[0]>>>16,65535&t[0],t[1]>>>16,65535&t[1]];var i=[0,0,0,0];return i[3]+=e[3]*t[3],i[2]+=i[3]>>>16,i[3]&=65535,i[2]+=e[2]*t[3],i[1]+=i[2]>>>16,i[2]&=65535,i[2]+=e[3]*t[2],i[1]+=i[2]>>>16,i[2]&=65535,i[1]+=e[1]*t[3],i[0]+=i[1]>>>16,i[1]&=65535,i[1]+=e[2]*t[2],i[0]+=i[1]>>>16,i[1]&=65535,i[1]+=e[3]*t[1],i[0]+=i[1]>>>16,i[1]&=65535,i[0]+=e[0]*t[3]+e[1]*t[2]+e[2]*t[1]+e[3]*t[0],i[0]&=65535,[i[0]<<16|i[1],i[2]<<16|i[3]]},x64Rotl:function(e,t){return t%=64,32===t?[e[1],e[0]]:t<32?[e[0]<<t|e[1]>>>32-t,e[1]<<t|e[0]>>>32-t]:(t-=32,[e[1]<<t|e[0]>>>32-t,e[0]<<t|e[1]>>>32-t])},x64LeftShift:function(e,t){return t%=64,0===t?e:t<32?[e[0]<<t|e[1]>>>32-t,e[1]<<t]:[e[1]<<t-32,0]},x64Xor:function(e,t){return[e[0]^t[0],e[1]^t[1]]},x64Fmix:function(e){return e=this.x64Xor(e,[0,e[0]>>>1]),e=this.x64Multiply(e,[4283543511,3981806797]),e=this.x64Xor(e,[0,e[0]>>>1]),e=this.x64Multiply(e,[3301882366,444984403]),e=this.x64Xor(e,[0,e[0]>>>1])},x64hash128:function(e,t){e=e||"",t=t||0;for(var i=e.length%16,a=e.length-i,r=[0,t],n=[0,t],o=[0,0],s=[0,0],l=[2277735313,289559509],h=[1291169091,658871167],u=0;u<a;u+=16)o=[255&e.charCodeAt(u+4)|(255&e.charCodeAt(u+5))<<8|(255&e.charCodeAt(u+6))<<16|(255&e.charCodeAt(u+7))<<24,255&e.charCodeAt(u)|(255&e.charCodeAt(u+1))<<8|(255&e.charCodeAt(u+2))<<16|(255&e.charCodeAt(u+3))<<24],
s=[255&e.charCodeAt(u+12)|(255&e.charCodeAt(u+13))<<8|(255&e.charCodeAt(u+14))<<16|(255&e.charCodeAt(u+15))<<24,255&e.charCodeAt(u+8)|(255&e.charCodeAt(u+9))<<8|(255&e.charCodeAt(u+10))<<16|(255&e.charCodeAt(u+11))<<24],o=this.x64Multiply(o,l),o=this.x64Rotl(o,31),o=this.x64Multiply(o,h),r=this.x64Xor(r,o),r=this.x64Rotl(r,27),r=this.x64Add(r,n),r=this.x64Add(this.x64Multiply(r,[0,5]),[0,1390208809]),s=this.x64Multiply(s,h),s=this.x64Rotl(s,33),s=this.x64Multiply(s,l),n=this.x64Xor(n,s),n=this.x64Rotl(n,31),n=this.x64Add(n,r),n=this.x64Add(this.x64Multiply(n,[0,5]),[0,944331445]);switch(o=[0,0],s=[0,0],i){case 15:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+14)],48));case 14:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+13)],40));case 13:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+12)],32));case 12:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+11)],24));case 11:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+10)],16));case 10:s=this.x64Xor(s,this.x64LeftShift([0,e.charCodeAt(u+9)],8));case 9:s=this.x64Xor(s,[0,e.charCodeAt(u+8)]),s=this.x64Multiply(s,h),s=this.x64Rotl(s,33),s=this.x64Multiply(s,l),n=this.x64Xor(n,s);case 8:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+7)],56));case 7:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+6)],48));case 6:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+5)],40));case 5:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+4)],32));case 4:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+3)],24));case 3:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+2)],16));case 2:o=this.x64Xor(o,this.x64LeftShift([0,e.charCodeAt(u+1)],8));case 1:o=this.x64Xor(o,[0,e.charCodeAt(u)]),o=this.x64Multiply(o,l),o=this.x64Rotl(o,31),o=this.x64Multiply(o,h),r=this.x64Xor(r,o)}return r=this.x64Xor(r,[0,e.length]),n=this.x64Xor(n,[0,e.length]),r=this.x64Add(r,n),n=this.x64Add(n,r),r=this.x64Fmix(r),n=this.x64Fmix(n),r=this.x64Add(r,n),n=this.x64Add(n,r),("00000000"+(r[0]>>>0).toString(16)).slice(-8)+("00000000"+(r[1]>>>0).toString(16)).slice(-8)+("00000000"+(n[0]>>>0).toString(16)).slice(-8)+("00000000"+(n[1]>>>0).toString(16)).slice(-8)}},e.VERSION="1.5.1",e});

/***/ }),
/* 30 */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(module, global) {var __WEBPACK_AMD_DEFINE_RESULT__;/*!
 * Platform.js <https://mths.be/platform>
 * Copyright 2014-2016 Benjamin Tan <https://demoneaux.github.io/>
 * Copyright 2011-2013 John-David Dalton <http://allyoucanleet.com/>
 * Available under MIT license <https://mths.be/mit>
 */
;(function() {
  'use strict';

  /** Used to determine if values are of the language type `Object`. */
  var objectTypes = {
    'function': true,
    'object': true
  };

  /** Used as a reference to the global object. */
  var root = (objectTypes[typeof window] && window) || this;

  /** Backup possible global object. */
  var oldRoot = root;

  /** Detect free variable `exports`. */
  var freeExports = objectTypes[typeof exports] && exports;

  /** Detect free variable `module`. */
  var freeModule = objectTypes[typeof module] && module && !module.nodeType && module;

  /** Detect free variable `global` from Node.js or Browserified code and use it as `root`. */
  var freeGlobal = freeExports && freeModule && typeof global == 'object' && global;
  if (freeGlobal && (freeGlobal.global === freeGlobal || freeGlobal.window === freeGlobal || freeGlobal.self === freeGlobal)) {
    root = freeGlobal;
  }

  /**
   * Used as the maximum length of an array-like object.
   * See the [ES6 spec](http://people.mozilla.org/~jorendorff/es6-draft.html#sec-tolength)
   * for more details.
   */
  var maxSafeInteger = Math.pow(2, 53) - 1;

  /** Regular expression to detect Opera. */
  var reOpera = /\bOpera/;

  /** Possible global object. */
  var thisBinding = this;

  /** Used for native method references. */
  var objectProto = Object.prototype;

  /** Used to check for own properties of an object. */
  var hasOwnProperty = objectProto.hasOwnProperty;

  /** Used to resolve the internal `[[Class]]` of values. */
  var toString = objectProto.toString;

  /*--------------------------------------------------------------------------*/

  /**
   * Capitalizes a string value.
   *
   * @private
   * @param {string} string The string to capitalize.
   * @returns {string} The capitalized string.
   */
  function capitalize(string) {
    string = String(string);
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

  /**
   * A utility function to clean up the OS name.
   *
   * @private
   * @param {string} os The OS name to clean up.
   * @param {string} [pattern] A `RegExp` pattern matching the OS name.
   * @param {string} [label] A label for the OS.
   */
  function cleanupOS(os, pattern, label) {
    // Platform tokens are defined at:
    // http://msdn.microsoft.com/en-us/library/ms537503(VS.85).aspx
    // http://web.archive.org/web/20081122053950/http://msdn.microsoft.com/en-us/library/ms537503(VS.85).aspx
    var data = {
      '10.0': '10',
      '6.4':  '10 Technical Preview',
      '6.3':  '8.1',
      '6.2':  '8',
      '6.1':  'Server 2008 R2 / 7',
      '6.0':  'Server 2008 / Vista',
      '5.2':  'Server 2003 / XP 64-bit',
      '5.1':  'XP',
      '5.01': '2000 SP1',
      '5.0':  '2000',
      '4.0':  'NT',
      '4.90': 'ME'
    };
    // Detect Windows version from platform tokens.
    if (pattern && label && /^Win/i.test(os) && !/^Windows Phone /i.test(os) &&
        (data = data[/[\d.]+$/.exec(os)])) {
      os = 'Windows ' + data;
    }
    // Correct character case and cleanup string.
    os = String(os);

    if (pattern && label) {
      os = os.replace(RegExp(pattern, 'i'), label);
    }

    os = format(
      os.replace(/ ce$/i, ' CE')
        .replace(/\bhpw/i, 'web')
        .replace(/\bMacintosh\b/, 'Mac OS')
        .replace(/_PowerPC\b/i, ' OS')
        .replace(/\b(OS X) [^ \d]+/i, '$1')
        .replace(/\bMac (OS X)\b/, '$1')
        .replace(/\/(\d)/, ' $1')
        .replace(/_/g, '.')
        .replace(/(?: BePC|[ .]*fc[ \d.]+)$/i, '')
        .replace(/\bx86\.64\b/gi, 'x86_64')
        .replace(/\b(Windows Phone) OS\b/, '$1')
        .replace(/\b(Chrome OS \w+) [\d.]+\b/, '$1')
        .split(' on ')[0]
    );

    return os;
  }

  /**
   * An iteration utility for arrays and objects.
   *
   * @private
   * @param {Array|Object} object The object to iterate over.
   * @param {Function} callback The function called per iteration.
   */
  function each(object, callback) {
    var index = -1,
        length = object ? object.length : 0;

    if (typeof length == 'number' && length > -1 && length <= maxSafeInteger) {
      while (++index < length) {
        callback(object[index], index, object);
      }
    } else {
      forOwn(object, callback);
    }
  }

  /**
   * Trim and conditionally capitalize string values.
   *
   * @private
   * @param {string} string The string to format.
   * @returns {string} The formatted string.
   */
  function format(string) {
    string = trim(string);
    return /^(?:webOS|i(?:OS|P))/.test(string)
      ? string
      : capitalize(string);
  }

  /**
   * Iterates over an object's own properties, executing the `callback` for each.
   *
   * @private
   * @param {Object} object The object to iterate over.
   * @param {Function} callback The function executed per own property.
   */
  function forOwn(object, callback) {
    for (var key in object) {
      if (hasOwnProperty.call(object, key)) {
        callback(object[key], key, object);
      }
    }
  }

  /**
   * Gets the internal `[[Class]]` of a value.
   *
   * @private
   * @param {*} value The value.
   * @returns {string} The `[[Class]]`.
   */
  function getClassOf(value) {
    return value == null
      ? capitalize(value)
      : toString.call(value).slice(8, -1);
  }

  /**
   * Host objects can return type values that are different from their actual
   * data type. The objects we are concerned with usually return non-primitive
   * types of "object", "function", or "unknown".
   *
   * @private
   * @param {*} object The owner of the property.
   * @param {string} property The property to check.
   * @returns {boolean} Returns `true` if the property value is a non-primitive, else `false`.
   */
  function isHostType(object, property) {
    var type = object != null ? typeof object[property] : 'number';
    return !/^(?:boolean|number|string|undefined)$/.test(type) &&
      (type == 'object' ? !!object[property] : true);
  }

  /**
   * Prepares a string for use in a `RegExp` by making hyphens and spaces optional.
   *
   * @private
   * @param {string} string The string to qualify.
   * @returns {string} The qualified string.
   */
  function qualify(string) {
    return String(string).replace(/([ -])(?!$)/g, '$1?');
  }

  /**
   * A bare-bones `Array#reduce` like utility function.
   *
   * @private
   * @param {Array} array The array to iterate over.
   * @param {Function} callback The function called per iteration.
   * @returns {*} The accumulated result.
   */
  function reduce(array, callback) {
    var accumulator = null;
    each(array, function(value, index) {
      accumulator = callback(accumulator, value, index, array);
    });
    return accumulator;
  }

  /**
   * Removes leading and trailing whitespace from a string.
   *
   * @private
   * @param {string} string The string to trim.
   * @returns {string} The trimmed string.
   */
  function trim(string) {
    return String(string).replace(/^ +| +$/g, '');
  }

  /*--------------------------------------------------------------------------*/

  /**
   * Creates a new platform object.
   *
   * @memberOf platform
   * @param {Object|string} [ua=navigator.userAgent] The user agent string or
   *  context object.
   * @returns {Object} A platform object.
   */
  function parse(ua) {

    /** The environment context object. */
    var context = root;

    /** Used to flag when a custom context is provided. */
    var isCustomContext = ua && typeof ua == 'object' && getClassOf(ua) != 'String';

    // Juggle arguments.
    if (isCustomContext) {
      context = ua;
      ua = null;
    }

    /** Browser navigator object. */
    var nav = context.navigator || {};

    /** Browser user agent string. */
    var userAgent = nav.userAgent || '';

    ua || (ua = userAgent);

    /** Used to flag when `thisBinding` is the [ModuleScope]. */
    var isModuleScope = isCustomContext || thisBinding == oldRoot;

    /** Used to detect if browser is like Chrome. */
    var likeChrome = isCustomContext
      ? !!nav.likeChrome
      : /\bChrome\b/.test(ua) && !/internal|\n/i.test(toString.toString());

    /** Internal `[[Class]]` value shortcuts. */
    var objectClass = 'Object',
        airRuntimeClass = isCustomContext ? objectClass : 'ScriptBridgingProxyObject',
        enviroClass = isCustomContext ? objectClass : 'Environment',
        javaClass = (isCustomContext && context.java) ? 'JavaPackage' : getClassOf(context.java),
        phantomClass = isCustomContext ? objectClass : 'RuntimeObject';

    /** Detect Java environments. */
    var java = /\bJava/.test(javaClass) && context.java;

    /** Detect Rhino. */
    var rhino = java && getClassOf(context.environment) == enviroClass;

    /** A character to represent alpha. */
    var alpha = java ? 'a' : '\u03b1';

    /** A character to represent beta. */
    var beta = java ? 'b' : '\u03b2';

    /** Browser document object. */
    var doc = context.document || {};

    /**
     * Detect Opera browser (Presto-based).
     * http://www.howtocreate.co.uk/operaStuff/operaObject.html
     * http://dev.opera.com/articles/view/opera-mini-web-content-authoring-guidelines/#operamini
     */
    var opera = context.operamini || context.opera;

    /** Opera `[[Class]]`. */
    var operaClass = reOpera.test(operaClass = (isCustomContext && opera) ? opera['[[Class]]'] : getClassOf(opera))
      ? operaClass
      : (opera = null);

    /*------------------------------------------------------------------------*/

    /** Temporary variable used over the script's lifetime. */
    var data;

    /** The CPU architecture. */
    var arch = ua;

    /** Platform description array. */
    var description = [];

    /** Platform alpha/beta indicator. */
    var prerelease = null;

    /** A flag to indicate that environment features should be used to resolve the platform. */
    var useFeatures = ua == userAgent;

    /** The browser/environment version. */
    var version = useFeatures && opera && typeof opera.version == 'function' && opera.version();

    /** A flag to indicate if the OS ends with "/ Version" */
    var isSpecialCasedOS;

    /* Detectable layout engines (order is important). */
    var layout = getLayout([
      { 'label': 'EdgeHTML', 'pattern': 'Edge' },
      'Trident',
      { 'label': 'WebKit', 'pattern': 'AppleWebKit' },
      'iCab',
      'Presto',
      'NetFront',
      'Tasman',
      'KHTML',
      'Gecko'
    ]);

    /* Detectable browser names (order is important). */
    var name = getName([
      'Adobe AIR',
      'Arora',
      'Avant Browser',
      'Breach',
      'Camino',
      'Electron',
      'Epiphany',
      'Fennec',
      'Flock',
      'Galeon',
      'GreenBrowser',
      'iCab',
      'Iceweasel',
      'K-Meleon',
      'Konqueror',
      'Lunascape',
      'Maxthon',
      { 'label': 'Microsoft Edge', 'pattern': 'Edge' },
      'Midori',
      'Nook Browser',
      'PaleMoon',
      'PhantomJS',
      'Raven',
      'Rekonq',
      'RockMelt',
      { 'label': 'Samsung Internet', 'pattern': 'SamsungBrowser' },
      'SeaMonkey',
      { 'label': 'Silk', 'pattern': '(?:Cloud9|Silk-Accelerated)' },
      'Sleipnir',
      'SlimBrowser',
      { 'label': 'SRWare Iron', 'pattern': 'Iron' },
      'Sunrise',
      'Swiftfox',
      'Waterfox',
      'WebPositive',
      'Opera Mini',
      { 'label': 'Opera Mini', 'pattern': 'OPiOS' },
      'Opera',
      { 'label': 'Opera', 'pattern': 'OPR' },
      'Chrome',
      { 'label': 'Chrome Mobile', 'pattern': '(?:CriOS|CrMo)' },
      { 'label': 'Firefox', 'pattern': '(?:Firefox|Minefield)' },
      { 'label': 'Firefox for iOS', 'pattern': 'FxiOS' },
      { 'label': 'IE', 'pattern': 'IEMobile' },
      { 'label': 'IE', 'pattern': 'MSIE' },
      'Safari'
    ]);

    /* Detectable products (order is important). */
    var product = getProduct([
      { 'label': 'BlackBerry', 'pattern': 'BB10' },
      'BlackBerry',
      { 'label': 'Galaxy S', 'pattern': 'GT-I9000' },
      { 'label': 'Galaxy S2', 'pattern': 'GT-I9100' },
      { 'label': 'Galaxy S3', 'pattern': 'GT-I9300' },
      { 'label': 'Galaxy S4', 'pattern': 'GT-I9500' },
      { 'label': 'Galaxy S5', 'pattern': 'SM-G900' },
      { 'label': 'Galaxy S6', 'pattern': 'SM-G920' },
      { 'label': 'Galaxy S6 Edge', 'pattern': 'SM-G925' },
      { 'label': 'Galaxy S7', 'pattern': 'SM-G930' },
      { 'label': 'Galaxy S7 Edge', 'pattern': 'SM-G935' },
      'Google TV',
      'Lumia',
      'iPad',
      'iPod',
      'iPhone',
      'Kindle',
      { 'label': 'Kindle Fire', 'pattern': '(?:Cloud9|Silk-Accelerated)' },
      'Nexus',
      'Nook',
      'PlayBook',
      'PlayStation Vita',
      'PlayStation',
      'TouchPad',
      'Transformer',
      { 'label': 'Wii U', 'pattern': 'WiiU' },
      'Wii',
      'Xbox One',
      { 'label': 'Xbox 360', 'pattern': 'Xbox' },
      'Xoom'
    ]);

    /* Detectable manufacturers. */
    var manufacturer = getManufacturer({
      'Apple': { 'iPad': 1, 'iPhone': 1, 'iPod': 1 },
      'Archos': {},
      'Amazon': { 'Kindle': 1, 'Kindle Fire': 1 },
      'Asus': { 'Transformer': 1 },
      'Barnes & Noble': { 'Nook': 1 },
      'BlackBerry': { 'PlayBook': 1 },
      'Google': { 'Google TV': 1, 'Nexus': 1 },
      'HP': { 'TouchPad': 1 },
      'HTC': {},
      'LG': {},
      'Microsoft': { 'Xbox': 1, 'Xbox One': 1 },
      'Motorola': { 'Xoom': 1 },
      'Nintendo': { 'Wii U': 1,  'Wii': 1 },
      'Nokia': { 'Lumia': 1 },
      'Samsung': { 'Galaxy S': 1, 'Galaxy S2': 1, 'Galaxy S3': 1, 'Galaxy S4': 1 },
      'Sony': { 'PlayStation': 1, 'PlayStation Vita': 1 }
    });

    /* Detectable operating systems (order is important). */
    var os = getOS([
      'Windows Phone',
      'Android',
      'CentOS',
      { 'label': 'Chrome OS', 'pattern': 'CrOS' },
      'Debian',
      'Fedora',
      'FreeBSD',
      'Gentoo',
      'Haiku',
      'Kubuntu',
      'Linux Mint',
      'OpenBSD',
      'Red Hat',
      'SuSE',
      'Ubuntu',
      'Xubuntu',
      'Cygwin',
      'Symbian OS',
      'hpwOS',
      'webOS ',
      'webOS',
      'Tablet OS',
      'Tizen',
      'Linux',
      'Mac OS X',
      'Macintosh',
      'Mac',
      'Windows 98;',
      'Windows '
    ]);

    /*------------------------------------------------------------------------*/

    /**
     * Picks the layout engine from an array of guesses.
     *
     * @private
     * @param {Array} guesses An array of guesses.
     * @returns {null|string} The detected layout engine.
     */
    function getLayout(guesses) {
      return reduce(guesses, function(result, guess) {
        return result || RegExp('\\b' + (
          guess.pattern || qualify(guess)
        ) + '\\b', 'i').exec(ua) && (guess.label || guess);
      });
    }

    /**
     * Picks the manufacturer from an array of guesses.
     *
     * @private
     * @param {Array} guesses An object of guesses.
     * @returns {null|string} The detected manufacturer.
     */
    function getManufacturer(guesses) {
      return reduce(guesses, function(result, value, key) {
        // Lookup the manufacturer by product or scan the UA for the manufacturer.
        return result || (
          value[product] ||
          value[/^[a-z]+(?: +[a-z]+\b)*/i.exec(product)] ||
          RegExp('\\b' + qualify(key) + '(?:\\b|\\w*\\d)', 'i').exec(ua)
        ) && key;
      });
    }

    /**
     * Picks the browser name from an array of guesses.
     *
     * @private
     * @param {Array} guesses An array of guesses.
     * @returns {null|string} The detected browser name.
     */
    function getName(guesses) {
      return reduce(guesses, function(result, guess) {
        return result || RegExp('\\b' + (
          guess.pattern || qualify(guess)
        ) + '\\b', 'i').exec(ua) && (guess.label || guess);
      });
    }

    /**
     * Picks the OS name from an array of guesses.
     *
     * @private
     * @param {Array} guesses An array of guesses.
     * @returns {null|string} The detected OS name.
     */
    function getOS(guesses) {
      return reduce(guesses, function(result, guess) {
        var pattern = guess.pattern || qualify(guess);
        if (!result && (result =
              RegExp('\\b' + pattern + '(?:/[\\d.]+|[ \\w.]*)', 'i').exec(ua)
            )) {
          result = cleanupOS(result, pattern, guess.label || guess);
        }
        return result;
      });
    }

    /**
     * Picks the product name from an array of guesses.
     *
     * @private
     * @param {Array} guesses An array of guesses.
     * @returns {null|string} The detected product name.
     */
    function getProduct(guesses) {
      return reduce(guesses, function(result, guess) {
        var pattern = guess.pattern || qualify(guess);
        if (!result && (result =
              RegExp('\\b' + pattern + ' *\\d+[.\\w_]*', 'i').exec(ua) ||
              RegExp('\\b' + pattern + ' *\\w+-[\\w]*', 'i').exec(ua) ||
              RegExp('\\b' + pattern + '(?:; *(?:[a-z]+[_-])?[a-z]+\\d+|[^ ();-]*)', 'i').exec(ua)
            )) {
          // Split by forward slash and append product version if needed.
          if ((result = String((guess.label && !RegExp(pattern, 'i').test(guess.label)) ? guess.label : result).split('/'))[1] && !/[\d.]+/.test(result[0])) {
            result[0] += ' ' + result[1];
          }
          // Correct character case and cleanup string.
          guess = guess.label || guess;
          result = format(result[0]
            .replace(RegExp(pattern, 'i'), guess)
            .replace(RegExp('; *(?:' + guess + '[_-])?', 'i'), ' ')
            .replace(RegExp('(' + guess + ')[-_.]?(\\w)', 'i'), '$1 $2'));
        }
        return result;
      });
    }

    /**
     * Resolves the version using an array of UA patterns.
     *
     * @private
     * @param {Array} patterns An array of UA patterns.
     * @returns {null|string} The detected version.
     */
    function getVersion(patterns) {
      return reduce(patterns, function(result, pattern) {
        return result || (RegExp(pattern +
          '(?:-[\\d.]+/|(?: for [\\w-]+)?[ /-])([\\d.]+[^ ();/_-]*)', 'i').exec(ua) || 0)[1] || null;
      });
    }

    /**
     * Returns `platform.description` when the platform object is coerced to a string.
     *
     * @name toString
     * @memberOf platform
     * @returns {string} Returns `platform.description` if available, else an empty string.
     */
    function toStringPlatform() {
      return this.description || '';
    }

    /*------------------------------------------------------------------------*/

    // Convert layout to an array so we can add extra details.
    layout && (layout = [layout]);

    // Detect product names that contain their manufacturer's name.
    if (manufacturer && !product) {
      product = getProduct([manufacturer]);
    }
    // Clean up Google TV.
    if ((data = /\bGoogle TV\b/.exec(product))) {
      product = data[0];
    }
    // Detect simulators.
    if (/\bSimulator\b/i.test(ua)) {
      product = (product ? product + ' ' : '') + 'Simulator';
    }
    // Detect Opera Mini 8+ running in Turbo/Uncompressed mode on iOS.
    if (name == 'Opera Mini' && /\bOPiOS\b/.test(ua)) {
      description.push('running in Turbo/Uncompressed mode');
    }
    // Detect IE Mobile 11.
    if (name == 'IE' && /\blike iPhone OS\b/.test(ua)) {
      data = parse(ua.replace(/like iPhone OS/, ''));
      manufacturer = data.manufacturer;
      product = data.product;
    }
    // Detect iOS.
    else if (/^iP/.test(product)) {
      name || (name = 'Safari');
      os = 'iOS' + ((data = / OS ([\d_]+)/i.exec(ua))
        ? ' ' + data[1].replace(/_/g, '.')
        : '');
    }
    // Detect Kubuntu.
    else if (name == 'Konqueror' && !/buntu/i.test(os)) {
      os = 'Kubuntu';
    }
    // Detect Android browsers.
    else if ((manufacturer && manufacturer != 'Google' &&
        ((/Chrome/.test(name) && !/\bMobile Safari\b/i.test(ua)) || /\bVita\b/.test(product))) ||
        (/\bAndroid\b/.test(os) && /^Chrome/.test(name) && /\bVersion\//i.test(ua))) {
      name = 'Android Browser';
      os = /\bAndroid\b/.test(os) ? os : 'Android';
    }
    // Detect Silk desktop/accelerated modes.
    else if (name == 'Silk') {
      if (!/\bMobi/i.test(ua)) {
        os = 'Android';
        description.unshift('desktop mode');
      }
      if (/Accelerated *= *true/i.test(ua)) {
        description.unshift('accelerated');
      }
    }
    // Detect PaleMoon identifying as Firefox.
    else if (name == 'PaleMoon' && (data = /\bFirefox\/([\d.]+)\b/.exec(ua))) {
      description.push('identifying as Firefox ' + data[1]);
    }
    // Detect Firefox OS and products running Firefox.
    else if (name == 'Firefox' && (data = /\b(Mobile|Tablet|TV)\b/i.exec(ua))) {
      os || (os = 'Firefox OS');
      product || (product = data[1]);
    }
    // Detect false positives for Firefox/Safari.
    else if (!name || (data = !/\bMinefield\b/i.test(ua) && /\b(?:Firefox|Safari)\b/.exec(name))) {
      // Escape the `/` for Firefox 1.
      if (name && !product && /[\/,]|^[^(]+?\)/.test(ua.slice(ua.indexOf(data + '/') + 8))) {
        // Clear name of false positives.
        name = null;
      }
      // Reassign a generic name.
      if ((data = product || manufacturer || os) &&
          (product || manufacturer || /\b(?:Android|Symbian OS|Tablet OS|webOS)\b/.test(os))) {
        name = /[a-z]+(?: Hat)?/i.exec(/\bAndroid\b/.test(os) ? os : data) + ' Browser';
      }
    }
    // Add Chrome version to description for Electron.
    else if (name == 'Electron' && (data = (/\bChrome\/([\d.]+)\b/.exec(ua) || 0)[1])) {
      description.push('Chromium ' + data);
    }
    // Detect non-Opera (Presto-based) versions (order is important).
    if (!version) {
      version = getVersion([
        '(?:Cloud9|CriOS|CrMo|Edge|FxiOS|IEMobile|Iron|Opera ?Mini|OPiOS|OPR|Raven|SamsungBrowser|Silk(?!/[\\d.]+$))',
        'Version',
        qualify(name),
        '(?:Firefox|Minefield|NetFront)'
      ]);
    }
    // Detect stubborn layout engines.
    if ((data =
          layout == 'iCab' && parseFloat(version) > 3 && 'WebKit' ||
          /\bOpera\b/.test(name) && (/\bOPR\b/.test(ua) ? 'Blink' : 'Presto') ||
          /\b(?:Midori|Nook|Safari)\b/i.test(ua) && !/^(?:Trident|EdgeHTML)$/.test(layout) && 'WebKit' ||
          !layout && /\bMSIE\b/i.test(ua) && (os == 'Mac OS' ? 'Tasman' : 'Trident') ||
          layout == 'WebKit' && /\bPlayStation\b(?! Vita\b)/i.test(name) && 'NetFront'
        )) {
      layout = [data];
    }
    // Detect Windows Phone 7 desktop mode.
    if (name == 'IE' && (data = (/; *(?:XBLWP|ZuneWP)(\d+)/i.exec(ua) || 0)[1])) {
      name += ' Mobile';
      os = 'Windows Phone ' + (/\+$/.test(data) ? data : data + '.x');
      description.unshift('desktop mode');
    }
    // Detect Windows Phone 8.x desktop mode.
    else if (/\bWPDesktop\b/i.test(ua)) {
      name = 'IE Mobile';
      os = 'Windows Phone 8.x';
      description.unshift('desktop mode');
      version || (version = (/\brv:([\d.]+)/.exec(ua) || 0)[1]);
    }
    // Detect IE 11 identifying as other browsers.
    else if (name != 'IE' && layout == 'Trident' && (data = /\brv:([\d.]+)/.exec(ua))) {
      if (name) {
        description.push('identifying as ' + name + (version ? ' ' + version : ''));
      }
      name = 'IE';
      version = data[1];
    }
    // Leverage environment features.
    if (useFeatures) {
      // Detect server-side environments.
      // Rhino has a global function while others have a global object.
      if (isHostType(context, 'global')) {
        if (java) {
          data = java.lang.System;
          arch = data.getProperty('os.arch');
          os = os || data.getProperty('os.name') + ' ' + data.getProperty('os.version');
        }
        if (isModuleScope && isHostType(context, 'system') && (data = [context.system])[0]) {
          os || (os = data[0].os || null);
          try {
            data[1] = context.require('ringo/engine').version;
            version = data[1].join('.');
            name = 'RingoJS';
          } catch(e) {
            if (data[0].global.system == context.system) {
              name = 'Narwhal';
            }
          }
        }
        else if (
          typeof context.process == 'object' && !context.process.browser &&
          (data = context.process)
        ) {
          if (typeof data.versions == 'object') {
            if (typeof data.versions.electron == 'string') {
              description.push('Node ' + data.versions.node);
              name = 'Electron';
              version = data.versions.electron;
            } else if (typeof data.versions.nw == 'string') {
              description.push('Chromium ' + version, 'Node ' + data.versions.node);
              name = 'NW.js';
              version = data.versions.nw;
            }
          } else {
            name = 'Node.js';
            arch = data.arch;
            os = data.platform;
            version = /[\d.]+/.exec(data.version)
            version = version ? version[0] : 'unknown';
          }
        }
        else if (rhino) {
          name = 'Rhino';
        }
      }
      // Detect Adobe AIR.
      else if (getClassOf((data = context.runtime)) == airRuntimeClass) {
        name = 'Adobe AIR';
        os = data.flash.system.Capabilities.os;
      }
      // Detect PhantomJS.
      else if (getClassOf((data = context.phantom)) == phantomClass) {
        name = 'PhantomJS';
        version = (data = data.version || null) && (data.major + '.' + data.minor + '.' + data.patch);
      }
      // Detect IE compatibility modes.
      else if (typeof doc.documentMode == 'number' && (data = /\bTrident\/(\d+)/i.exec(ua))) {
        // We're in compatibility mode when the Trident version + 4 doesn't
        // equal the document mode.
        version = [version, doc.documentMode];
        if ((data = +data[1] + 4) != version[1]) {
          description.push('IE ' + version[1] + ' mode');
          layout && (layout[1] = '');
          version[1] = data;
        }
        version = name == 'IE' ? String(version[1].toFixed(1)) : version[0];
      }
      // Detect IE 11 masking as other browsers.
      else if (typeof doc.documentMode == 'number' && /^(?:Chrome|Firefox)\b/.test(name)) {
        description.push('masking as ' + name + ' ' + version);
        name = 'IE';
        version = '11.0';
        layout = ['Trident'];
        os = 'Windows';
      }
      os = os && format(os);
    }
    // Detect prerelease phases.
    if (version && (data =
          /(?:[ab]|dp|pre|[ab]\d+pre)(?:\d+\+?)?$/i.exec(version) ||
          /(?:alpha|beta)(?: ?\d)?/i.exec(ua + ';' + (useFeatures && nav.appMinorVersion)) ||
          /\bMinefield\b/i.test(ua) && 'a'
        )) {
      prerelease = /b/i.test(data) ? 'beta' : 'alpha';
      version = version.replace(RegExp(data + '\\+?$'), '') +
        (prerelease == 'beta' ? beta : alpha) + (/\d+\+?/.exec(data) || '');
    }
    // Detect Firefox Mobile.
    if (name == 'Fennec' || name == 'Firefox' && /\b(?:Android|Firefox OS)\b/.test(os)) {
      name = 'Firefox Mobile';
    }
    // Obscure Maxthon's unreliable version.
    else if (name == 'Maxthon' && version) {
      version = version.replace(/\.[\d.]+/, '.x');
    }
    // Detect Xbox 360 and Xbox One.
    else if (/\bXbox\b/i.test(product)) {
      if (product == 'Xbox 360') {
        os = null;
      }
      if (product == 'Xbox 360' && /\bIEMobile\b/.test(ua)) {
        description.unshift('mobile mode');
      }
    }
    // Add mobile postfix.
    else if ((/^(?:Chrome|IE|Opera)$/.test(name) || name && !product && !/Browser|Mobi/.test(name)) &&
        (os == 'Windows CE' || /Mobi/i.test(ua))) {
      name += ' Mobile';
    }
    // Detect IE platform preview.
    else if (name == 'IE' && useFeatures) {
      try {
        if (context.external === null) {
          description.unshift('platform preview');
        }
      } catch(e) {
        description.unshift('embedded');
      }
    }
    // Detect BlackBerry OS version.
    // http://docs.blackberry.com/en/developers/deliverables/18169/HTTP_headers_sent_by_BB_Browser_1234911_11.jsp
    else if ((/\bBlackBerry\b/.test(product) || /\bBB10\b/.test(ua)) && (data =
          (RegExp(product.replace(/ +/g, ' *') + '/([.\\d]+)', 'i').exec(ua) || 0)[1] ||
          version
        )) {
      data = [data, /BB10/.test(ua)];
      os = (data[1] ? (product = null, manufacturer = 'BlackBerry') : 'Device Software') + ' ' + data[0];
      version = null;
    }
    // Detect Opera identifying/masking itself as another browser.
    // http://www.opera.com/support/kb/view/843/
    else if (this != forOwn && product != 'Wii' && (
          (useFeatures && opera) ||
          (/Opera/.test(name) && /\b(?:MSIE|Firefox)\b/i.test(ua)) ||
          (name == 'Firefox' && /\bOS X (?:\d+\.){2,}/.test(os)) ||
          (name == 'IE' && (
            (os && !/^Win/.test(os) && version > 5.5) ||
            /\bWindows XP\b/.test(os) && version > 8 ||
            version == 8 && !/\bTrident\b/.test(ua)
          ))
        ) && !reOpera.test((data = parse.call(forOwn, ua.replace(reOpera, '') + ';'))) && data.name) {
      // When "identifying", the UA contains both Opera and the other browser's name.
      data = 'ing as ' + data.name + ((data = data.version) ? ' ' + data : '');
      if (reOpera.test(name)) {
        if (/\bIE\b/.test(data) && os == 'Mac OS') {
          os = null;
        }
        data = 'identify' + data;
      }
      // When "masking", the UA contains only the other browser's name.
      else {
        data = 'mask' + data;
        if (operaClass) {
          name = format(operaClass.replace(/([a-z])([A-Z])/g, '$1 $2'));
        } else {
          name = 'Opera';
        }
        if (/\bIE\b/.test(data)) {
          os = null;
        }
        if (!useFeatures) {
          version = null;
        }
      }
      layout = ['Presto'];
      description.push(data);
    }
    // Detect WebKit Nightly and approximate Chrome/Safari versions.
    if ((data = (/\bAppleWebKit\/([\d.]+\+?)/i.exec(ua) || 0)[1])) {
      // Correct build number for numeric comparison.
      // (e.g. "532.5" becomes "532.05")
      data = [parseFloat(data.replace(/\.(\d)$/, '.0$1')), data];
      // Nightly builds are postfixed with a "+".
      if (name == 'Safari' && data[1].slice(-1) == '+') {
        name = 'WebKit Nightly';
        prerelease = 'alpha';
        version = data[1].slice(0, -1);
      }
      // Clear incorrect browser versions.
      else if (version == data[1] ||
          version == (data[2] = (/\bSafari\/([\d.]+\+?)/i.exec(ua) || 0)[1])) {
        version = null;
      }
      // Use the full Chrome version when available.
      data[1] = (/\bChrome\/([\d.]+)/i.exec(ua) || 0)[1];
      // Detect Blink layout engine.
      if (data[0] == 537.36 && data[2] == 537.36 && parseFloat(data[1]) >= 28 && layout == 'WebKit') {
        layout = ['Blink'];
      }
      // Detect JavaScriptCore.
      // http://stackoverflow.com/questions/6768474/how-can-i-detect-which-javascript-engine-v8-or-jsc-is-used-at-runtime-in-androi
      if (!useFeatures || (!likeChrome && !data[1])) {
        layout && (layout[1] = 'like Safari');
        data = (data = data[0], data < 400 ? 1 : data < 500 ? 2 : data < 526 ? 3 : data < 533 ? 4 : data < 534 ? '4+' : data < 535 ? 5 : data < 537 ? 6 : data < 538 ? 7 : data < 601 ? 8 : '8');
      } else {
        layout && (layout[1] = 'like Chrome');
        data = data[1] || (data = data[0], data < 530 ? 1 : data < 532 ? 2 : data < 532.05 ? 3 : data < 533 ? 4 : data < 534.03 ? 5 : data < 534.07 ? 6 : data < 534.10 ? 7 : data < 534.13 ? 8 : data < 534.16 ? 9 : data < 534.24 ? 10 : data < 534.30 ? 11 : data < 535.01 ? 12 : data < 535.02 ? '13+' : data < 535.07 ? 15 : data < 535.11 ? 16 : data < 535.19 ? 17 : data < 536.05 ? 18 : data < 536.10 ? 19 : data < 537.01 ? 20 : data < 537.11 ? '21+' : data < 537.13 ? 23 : data < 537.18 ? 24 : data < 537.24 ? 25 : data < 537.36 ? 26 : layout != 'Blink' ? '27' : '28');
      }
      // Add the postfix of ".x" or "+" for approximate versions.
      layout && (layout[1] += ' ' + (data += typeof data == 'number' ? '.x' : /[.+]/.test(data) ? '' : '+'));
      // Obscure version for some Safari 1-2 releases.
      if (name == 'Safari' && (!version || parseInt(version) > 45)) {
        version = data;
      }
    }
    // Detect Opera desktop modes.
    if (name == 'Opera' &&  (data = /\bzbov|zvav$/.exec(os))) {
      name += ' ';
      description.unshift('desktop mode');
      if (data == 'zvav') {
        name += 'Mini';
        version = null;
      } else {
        name += 'Mobile';
      }
      os = os.replace(RegExp(' *' + data + '$'), '');
    }
    // Detect Chrome desktop mode.
    else if (name == 'Safari' && /\bChrome\b/.exec(layout && layout[1])) {
      description.unshift('desktop mode');
      name = 'Chrome Mobile';
      version = null;

      if (/\bOS X\b/.test(os)) {
        manufacturer = 'Apple';
        os = 'iOS 4.3+';
      } else {
        os = null;
      }
    }
    // Strip incorrect OS versions.
    if (version && version.indexOf((data = /[\d.]+$/.exec(os))) == 0 &&
        ua.indexOf('/' + data + '-') > -1) {
      os = trim(os.replace(data, ''));
    }
    // Add layout engine.
    if (layout && !/\b(?:Avant|Nook)\b/.test(name) && (
        /Browser|Lunascape|Maxthon/.test(name) ||
        name != 'Safari' && /^iOS/.test(os) && /\bSafari\b/.test(layout[1]) ||
        /^(?:Adobe|Arora|Breach|Midori|Opera|Phantom|Rekonq|Rock|Samsung Internet|Sleipnir|Web)/.test(name) && layout[1])) {
      // Don't add layout details to description if they are falsey.
      (data = layout[layout.length - 1]) && description.push(data);
    }
    // Combine contextual information.
    if (description.length) {
      description = ['(' + description.join('; ') + ')'];
    }
    // Append manufacturer to description.
    if (manufacturer && product && product.indexOf(manufacturer) < 0) {
      description.push('on ' + manufacturer);
    }
    // Append product to description.
    if (product) {
      description.push((/^on /.test(description[description.length - 1]) ? '' : 'on ') + product);
    }
    // Parse the OS into an object.
    if (os) {
      data = / ([\d.+]+)$/.exec(os);
      isSpecialCasedOS = data && os.charAt(os.length - data[0].length - 1) == '/';
      os = {
        'architecture': 32,
        'family': (data && !isSpecialCasedOS) ? os.replace(data[0], '') : os,
        'version': data ? data[1] : null,
        'toString': function() {
          var version = this.version;
          return this.family + ((version && !isSpecialCasedOS) ? ' ' + version : '') + (this.architecture == 64 ? ' 64-bit' : '');
        }
      };
    }
    // Add browser/OS architecture.
    if ((data = /\b(?:AMD|IA|Win|WOW|x86_|x)64\b/i.exec(arch)) && !/\bi686\b/i.test(arch)) {
      if (os) {
        os.architecture = 64;
        os.family = os.family.replace(RegExp(' *' + data), '');
      }
      if (
          name && (/\bWOW64\b/i.test(ua) ||
          (useFeatures && /\w(?:86|32)$/.test(nav.cpuClass || nav.platform) && !/\bWin64; x64\b/i.test(ua)))
      ) {
        description.unshift('32-bit');
      }
    }
    // Chrome 39 and above on OS X is always 64-bit.
    else if (
        os && /^OS X/.test(os.family) &&
        name == 'Chrome' && parseFloat(version) >= 39
    ) {
      os.architecture = 64;
    }

    ua || (ua = null);

    /*------------------------------------------------------------------------*/

    /**
     * The platform object.
     *
     * @name platform
     * @type Object
     */
    var platform = {};

    /**
     * The platform description.
     *
     * @memberOf platform
     * @type string|null
     */
    platform.description = ua;

    /**
     * The name of the browser's layout engine.
     *
     * The list of common layout engines include:
     * "Blink", "EdgeHTML", "Gecko", "Trident" and "WebKit"
     *
     * @memberOf platform
     * @type string|null
     */
    platform.layout = layout && layout[0];

    /**
     * The name of the product's manufacturer.
     *
     * The list of manufacturers include:
     * "Apple", "Archos", "Amazon", "Asus", "Barnes & Noble", "BlackBerry",
     * "Google", "HP", "HTC", "LG", "Microsoft", "Motorola", "Nintendo",
     * "Nokia", "Samsung" and "Sony"
     *
     * @memberOf platform
     * @type string|null
     */
    platform.manufacturer = manufacturer;

    /**
     * The name of the browser/environment.
     *
     * The list of common browser names include:
     * "Chrome", "Electron", "Firefox", "Firefox for iOS", "IE",
     * "Microsoft Edge", "PhantomJS", "Safari", "SeaMonkey", "Silk",
     * "Opera Mini" and "Opera"
     *
     * Mobile versions of some browsers have "Mobile" appended to their name:
     * eg. "Chrome Mobile", "Firefox Mobile", "IE Mobile" and "Opera Mobile"
     *
     * @memberOf platform
     * @type string|null
     */
    platform.name = name;

    /**
     * The alpha/beta release indicator.
     *
     * @memberOf platform
     * @type string|null
     */
    platform.prerelease = prerelease;

    /**
     * The name of the product hosting the browser.
     *
     * The list of common products include:
     *
     * "BlackBerry", "Galaxy S4", "Lumia", "iPad", "iPod", "iPhone", "Kindle",
     * "Kindle Fire", "Nexus", "Nook", "PlayBook", "TouchPad" and "Transformer"
     *
     * @memberOf platform
     * @type string|null
     */
    platform.product = product;

    /**
     * The browser's user agent string.
     *
     * @memberOf platform
     * @type string|null
     */
    platform.ua = ua;

    /**
     * The browser/environment version.
     *
     * @memberOf platform
     * @type string|null
     */
    platform.version = name && version;

    /**
     * The name of the operating system.
     *
     * @memberOf platform
     * @type Object
     */
    platform.os = os || {

      /**
       * The CPU architecture the OS is built for.
       *
       * @memberOf platform.os
       * @type number|null
       */
      'architecture': null,

      /**
       * The family of the OS.
       *
       * Common values include:
       * "Windows", "Windows Server 2008 R2 / 7", "Windows Server 2008 / Vista",
       * "Windows XP", "OS X", "Ubuntu", "Debian", "Fedora", "Red Hat", "SuSE",
       * "Android", "iOS" and "Windows Phone"
       *
       * @memberOf platform.os
       * @type string|null
       */
      'family': null,

      /**
       * The version of the OS.
       *
       * @memberOf platform.os
       * @type string|null
       */
      'version': null,

      /**
       * Returns the OS string.
       *
       * @memberOf platform.os
       * @returns {string} The OS string.
       */
      'toString': function() { return 'null'; }
    };

    platform.parse = parse;
    platform.toString = toStringPlatform;

    if (platform.version) {
      description.unshift(version);
    }
    if (platform.name) {
      description.unshift(name);
    }
    if (os && name && !(os == String(os).split(' ')[0] && (os == name.split(' ')[0] || product))) {
      description.push(product ? '(' + os + ')' : 'on ' + os);
    }
    if (description.length) {
      platform.description = description.join(' ');
    }
    return platform;
  }

  /*--------------------------------------------------------------------------*/

  // Export platform.
  var platform = parse();

  // Some AMD build optimizers, like r.js, check for condition patterns like the following:
  if (true) {
    // Expose platform on the global object to prevent errors when platform is
    // loaded by a script tag in the presence of an AMD loader.
    // See http://requirejs.org/docs/errors.html#mismatch for more details.
    root.platform = platform;

    // Define as an anonymous module so platform can be aliased through path mapping.
    !(__WEBPACK_AMD_DEFINE_RESULT__ = function() {
      return platform;
    }.call(exports, __webpack_require__, exports, module),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));
  }
  // Check for `exports` after `define` in case a build optimizer adds an `exports` object.
  else if (freeExports && freeModule) {
    // Export for CommonJS support.
    forOwn(platform, function(value, key) {
      freeExports[key] = value;
    });
  }
  else {
    // Export to the global object.
    root.platform = platform;
  }
}.call(this));

/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(31)(module), __webpack_require__(21)))

/***/ }),
/* 31 */
/***/ (function(module, exports) {

module.exports = function(module) {
	if(!module.webpackPolyfill) {
		module.deprecate = function() {};
		module.paths = [];
		// module.parent = undefined by default
		if(!module.children) module.children = [];
		Object.defineProperty(module, "loaded", {
			enumerable: true,
			get: function() {
				return module.l;
			}
		});
		Object.defineProperty(module, "id", {
			enumerable: true,
			get: function() {
				return module.i;
			}
		});
		module.webpackPolyfill = 1;
	}
	return module;
};


/***/ }),
/* 32 */
/***/ (function(module, exports, __webpack_require__) {

(function (global, factory) {
	 true ? module.exports = factory() :
	typeof define === 'function' && define.amd ? define(factory) :
	(global.nativeToast = factory());
}(this, (function () { 'use strict';

/*
object-assign
(c) Sindre Sorhus
@license MIT
*/

/* eslint-disable no-unused-vars */
var getOwnPropertySymbols = Object.getOwnPropertySymbols;
var hasOwnProperty = Object.prototype.hasOwnProperty;
var propIsEnumerable = Object.prototype.propertyIsEnumerable;

function toObject(val) {
	if (val === null || val === undefined) {
		throw new TypeError('Object.assign cannot be called with null or undefined');
	}

	return Object(val);
}

function shouldUseNative() {
	try {
		if (!Object.assign) {
			return false;
		}

		// Detect buggy property enumeration order in older V8 versions.

		// https://bugs.chromium.org/p/v8/issues/detail?id=4118
		var test1 = new String('abc');  // eslint-disable-line no-new-wrappers
		test1[5] = 'de';
		if (Object.getOwnPropertyNames(test1)[0] === '5') {
			return false;
		}

		// https://bugs.chromium.org/p/v8/issues/detail?id=3056
		var test2 = {};
		for (var i = 0; i < 10; i++) {
			test2['_' + String.fromCharCode(i)] = i;
		}
		var order2 = Object.getOwnPropertyNames(test2).map(function (n) {
			return test2[n];
		});
		if (order2.join('') !== '0123456789') {
			return false;
		}

		// https://bugs.chromium.org/p/v8/issues/detail?id=3056
		var test3 = {};
		'abcdefghijklmnopqrst'.split('').forEach(function (letter) {
			test3[letter] = letter;
		});
		if (Object.keys(Object.assign({}, test3)).join('') !==
				'abcdefghijklmnopqrst') {
			return false;
		}

		return true;
	} catch (err) {
		// We don't expect any of the above to throw, but better to be safe.
		return false;
	}
}

var index = shouldUseNative() ? Object.assign : function (target, source) {
	var arguments$1 = arguments;

	var from;
	var to = toObject(target);
	var symbols;

	for (var s = 1; s < arguments.length; s++) {
		from = Object(arguments$1[s]);

		for (var key in from) {
			if (hasOwnProperty.call(from, key)) {
				to[key] = from[key];
			}
		}

		if (getOwnPropertySymbols) {
			symbols = getOwnPropertySymbols(from);
			for (var i = 0; i < symbols.length; i++) {
				if (propIsEnumerable.call(from, symbols[i])) {
					to[symbols[i]] = from[symbols[i]];
				}
			}
		}
	}

	return to;
};

var prevToast = null;

var icons = {
  warning: "<svg viewBox=\"0 0 32 32\" width=\"32\" height=\"32\" fill=\"none\" stroke=\"currentcolor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"6.25%\"><path d=\"M8 17 C8 12 9 6 16 6 23 6 24 12 24 17 24 22 27 25 27 25 L5 25 C5 25 8 22 8 17 Z M20 25 C20 25 20 29 16 29 12 29 12 25 12 25 M16 3 L16 6\" /></svg>",
  success: "<svg viewBox=\"0 0 32 32\" width=\"32\" height=\"32\" fill=\"none\" stroke=\"currentcolor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"6.25%\"><path d=\"M2 20 L12 28 30 4\" /></svg>",
  info: "<svg viewBox=\"0 0 32 32\" width=\"32\" height=\"32\" fill=\"none\" stroke=\"currentcolor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"6.25%\"><path d=\"M16 14 L16 23 M16 8 L16 10\" /><circle cx=\"16\" cy=\"16\" r=\"14\" /></svg>",
  error: "<svg viewBox=\"0 0 32 32\" width=\"32\" height=\"32\" fill=\"none\" stroke=\"currentcolor\" stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"6.25%\"><path d=\"M16 3 L30 29 2 29 Z M16 11 L16 19 M16 23 L16 25\" /></svg>"
};

var Toast = function Toast(ref) {
  if ( ref === void 0 ) ref = {};
  var message = ref.message; if ( message === void 0 ) message = '';
  var position = ref.position; if ( position === void 0 ) position = 'bottom';
  var timeout = ref.timeout; if ( timeout === void 0 ) timeout = 3000;
  var el = ref.el; if ( el === void 0 ) el = document.body;
  var square = ref.square; if ( square === void 0 ) square = false;
  var type = ref.type; if ( type === void 0 ) type = '';
  var debug = ref.debug; if ( debug === void 0 ) debug = false;
  var edge = ref.edge; if ( edge === void 0 ) edge = false;
  var icon = ref.icon; if ( icon === void 0 ) icon = true;

  if (prevToast) {
    prevToast.destroy();
  }

  this.message = message;
  this.position = position;
  this.el = el;
  this.timeout = timeout;

  this.toast = document.createElement('div');
  this.toast.className = "native-toast native-toast-" + (this.position);

  if (type) {
    this.toast.className += " native-toast-" + type;

    if (icon) {
      this.message = "<span class=\"native-toast-icon-" + type + "\">" + (icons[type] || '') + "</span>" + (this.message);
    }
  }

  this.toast.innerHTML = this.message;

  if (edge) {
    this.toast.className += ' native-toast-edge';
  } else if (square) {
    this.toast.style.borderRadius = '3px';
  }

  this.el.appendChild(this.toast);

  prevToast = this;

  this.show();
  if (!debug && timeout > 0) {
    this.hide();
  }
};

Toast.prototype.show = function show () {
    var this$1 = this;

  setTimeout(function () {
    this$1.toast.classList.add('native-toast-shown');
  }, 300);
};

Toast.prototype.hide = function hide () {
    var this$1 = this;

  setTimeout(function () {
    this$1.destroy();
  }, this.timeout);
};

Toast.prototype.destroy = function destroy () {
    var this$1 = this;

  if (!this.toast) { return }

  this.toast.classList.remove('native-toast-shown');

  setTimeout(function () {
    if (this$1.toast) {
      this$1.el.removeChild(this$1.toast);
      this$1.toast = null;
    }
  }, 300);
};

function toast(options) {
  return new Toast(options)
}

var loop = function () {
  var type = list[i];

  toast[type] = function (options) { return toast(index({}, {type: type}, options)); };
};

for (var i = 0, list = ['success', 'info', 'warning', 'error']; i < list.length; i += 1) loop();

return toast;

})));


/***/ }),
/* 33 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(11), __webpack_require__(34), __webpack_require__(35), __webpack_require__(8), __webpack_require__(9), __webpack_require__(14), __webpack_require__(19), __webpack_require__(36), __webpack_require__(20), __webpack_require__(37), __webpack_require__(38), __webpack_require__(39), __webpack_require__(15), __webpack_require__(40), __webpack_require__(6), __webpack_require__(1), __webpack_require__(41), __webpack_require__(42), __webpack_require__(43), __webpack_require__(44), __webpack_require__(45), __webpack_require__(46), __webpack_require__(47), __webpack_require__(48), __webpack_require__(49), __webpack_require__(50), __webpack_require__(51), __webpack_require__(52), __webpack_require__(53), __webpack_require__(54), __webpack_require__(55), __webpack_require__(56));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./x64-core", "./lib-typedarrays", "./enc-utf16", "./enc-base64", "./md5", "./sha1", "./sha256", "./sha224", "./sha512", "./sha384", "./sha3", "./ripemd160", "./hmac", "./pbkdf2", "./evpkdf", "./cipher-core", "./mode-cfb", "./mode-ctr", "./mode-ctr-gladman", "./mode-ofb", "./mode-ecb", "./pad-ansix923", "./pad-iso10126", "./pad-iso97971", "./pad-zeropadding", "./pad-nopadding", "./format-hex", "./aes", "./tripledes", "./rc4", "./rabbit", "./rabbit-legacy"], factory);
	}
	else {
		// Global (browser)
		root.CryptoJS = factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	return CryptoJS;

}));

/***/ }),
/* 34 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Check if typed arrays are supported
	    if (typeof ArrayBuffer != 'function') {
	        return;
	    }

	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;

	    // Reference original init
	    var superInit = WordArray.init;

	    // Augment WordArray.init to handle typed arrays
	    var subInit = WordArray.init = function (typedArray) {
	        // Convert buffers to uint8
	        if (typedArray instanceof ArrayBuffer) {
	            typedArray = new Uint8Array(typedArray);
	        }

	        // Convert other array views to uint8
	        if (
	            typedArray instanceof Int8Array ||
	            (typeof Uint8ClampedArray !== "undefined" && typedArray instanceof Uint8ClampedArray) ||
	            typedArray instanceof Int16Array ||
	            typedArray instanceof Uint16Array ||
	            typedArray instanceof Int32Array ||
	            typedArray instanceof Uint32Array ||
	            typedArray instanceof Float32Array ||
	            typedArray instanceof Float64Array
	        ) {
	            typedArray = new Uint8Array(typedArray.buffer, typedArray.byteOffset, typedArray.byteLength);
	        }

	        // Handle Uint8Array
	        if (typedArray instanceof Uint8Array) {
	            // Shortcut
	            var typedArrayByteLength = typedArray.byteLength;

	            // Extract bytes
	            var words = [];
	            for (var i = 0; i < typedArrayByteLength; i++) {
	                words[i >>> 2] |= typedArray[i] << (24 - (i % 4) * 8);
	            }

	            // Initialize this word array
	            superInit.call(this, words, typedArrayByteLength);
	        } else {
	            // Else call normal init
	            superInit.apply(this, arguments);
	        }
	    };

	    subInit.prototype = WordArray;
	}());


	return CryptoJS.lib.WordArray;

}));

/***/ }),
/* 35 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var C_enc = C.enc;

	    /**
	     * UTF-16 BE encoding strategy.
	     */
	    var Utf16BE = C_enc.Utf16 = C_enc.Utf16BE = {
	        /**
	         * Converts a word array to a UTF-16 BE string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The UTF-16 BE string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var utf16String = CryptoJS.enc.Utf16.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            // Shortcuts
	            var words = wordArray.words;
	            var sigBytes = wordArray.sigBytes;

	            // Convert
	            var utf16Chars = [];
	            for (var i = 0; i < sigBytes; i += 2) {
	                var codePoint = (words[i >>> 2] >>> (16 - (i % 4) * 8)) & 0xffff;
	                utf16Chars.push(String.fromCharCode(codePoint));
	            }

	            return utf16Chars.join('');
	        },

	        /**
	         * Converts a UTF-16 BE string to a word array.
	         *
	         * @param {string} utf16Str The UTF-16 BE string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Utf16.parse(utf16String);
	         */
	        parse: function (utf16Str) {
	            // Shortcut
	            var utf16StrLength = utf16Str.length;

	            // Convert
	            var words = [];
	            for (var i = 0; i < utf16StrLength; i++) {
	                words[i >>> 1] |= utf16Str.charCodeAt(i) << (16 - (i % 2) * 16);
	            }

	            return WordArray.create(words, utf16StrLength * 2);
	        }
	    };

	    /**
	     * UTF-16 LE encoding strategy.
	     */
	    C_enc.Utf16LE = {
	        /**
	         * Converts a word array to a UTF-16 LE string.
	         *
	         * @param {WordArray} wordArray The word array.
	         *
	         * @return {string} The UTF-16 LE string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var utf16Str = CryptoJS.enc.Utf16LE.stringify(wordArray);
	         */
	        stringify: function (wordArray) {
	            // Shortcuts
	            var words = wordArray.words;
	            var sigBytes = wordArray.sigBytes;

	            // Convert
	            var utf16Chars = [];
	            for (var i = 0; i < sigBytes; i += 2) {
	                var codePoint = swapEndian((words[i >>> 2] >>> (16 - (i % 4) * 8)) & 0xffff);
	                utf16Chars.push(String.fromCharCode(codePoint));
	            }

	            return utf16Chars.join('');
	        },

	        /**
	         * Converts a UTF-16 LE string to a word array.
	         *
	         * @param {string} utf16Str The UTF-16 LE string.
	         *
	         * @return {WordArray} The word array.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var wordArray = CryptoJS.enc.Utf16LE.parse(utf16Str);
	         */
	        parse: function (utf16Str) {
	            // Shortcut
	            var utf16StrLength = utf16Str.length;

	            // Convert
	            var words = [];
	            for (var i = 0; i < utf16StrLength; i++) {
	                words[i >>> 1] |= swapEndian(utf16Str.charCodeAt(i) << (16 - (i % 2) * 16));
	            }

	            return WordArray.create(words, utf16StrLength * 2);
	        }
	    };

	    function swapEndian(word) {
	        return ((word << 8) & 0xff00ff00) | ((word >>> 8) & 0x00ff00ff);
	    }
	}());


	return CryptoJS.enc.Utf16;

}));

/***/ }),
/* 36 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(19));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./sha256"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var C_algo = C.algo;
	    var SHA256 = C_algo.SHA256;

	    /**
	     * SHA-224 hash algorithm.
	     */
	    var SHA224 = C_algo.SHA224 = SHA256.extend({
	        _doReset: function () {
	            this._hash = new WordArray.init([
	                0xc1059ed8, 0x367cd507, 0x3070dd17, 0xf70e5939,
	                0xffc00b31, 0x68581511, 0x64f98fa7, 0xbefa4fa4
	            ]);
	        },

	        _doFinalize: function () {
	            var hash = SHA256._doFinalize.call(this);

	            hash.sigBytes -= 4;

	            return hash;
	        }
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA224('message');
	     *     var hash = CryptoJS.SHA224(wordArray);
	     */
	    C.SHA224 = SHA256._createHelper(SHA224);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA224(message, key);
	     */
	    C.HmacSHA224 = SHA256._createHmacHelper(SHA224);
	}());


	return CryptoJS.SHA224;

}));

/***/ }),
/* 37 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(11), __webpack_require__(20));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./x64-core", "./sha512"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_x64 = C.x64;
	    var X64Word = C_x64.Word;
	    var X64WordArray = C_x64.WordArray;
	    var C_algo = C.algo;
	    var SHA512 = C_algo.SHA512;

	    /**
	     * SHA-384 hash algorithm.
	     */
	    var SHA384 = C_algo.SHA384 = SHA512.extend({
	        _doReset: function () {
	            this._hash = new X64WordArray.init([
	                new X64Word.init(0xcbbb9d5d, 0xc1059ed8), new X64Word.init(0x629a292a, 0x367cd507),
	                new X64Word.init(0x9159015a, 0x3070dd17), new X64Word.init(0x152fecd8, 0xf70e5939),
	                new X64Word.init(0x67332667, 0xffc00b31), new X64Word.init(0x8eb44a87, 0x68581511),
	                new X64Word.init(0xdb0c2e0d, 0x64f98fa7), new X64Word.init(0x47b5481d, 0xbefa4fa4)
	            ]);
	        },

	        _doFinalize: function () {
	            var hash = SHA512._doFinalize.call(this);

	            hash.sigBytes -= 16;

	            return hash;
	        }
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA384('message');
	     *     var hash = CryptoJS.SHA384(wordArray);
	     */
	    C.SHA384 = SHA512._createHelper(SHA384);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA384(message, key);
	     */
	    C.HmacSHA384 = SHA512._createHmacHelper(SHA384);
	}());


	return CryptoJS.SHA384;

}));

/***/ }),
/* 38 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(11));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./x64-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function (Math) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var Hasher = C_lib.Hasher;
	    var C_x64 = C.x64;
	    var X64Word = C_x64.Word;
	    var C_algo = C.algo;

	    // Constants tables
	    var RHO_OFFSETS = [];
	    var PI_INDEXES  = [];
	    var ROUND_CONSTANTS = [];

	    // Compute Constants
	    (function () {
	        // Compute rho offset constants
	        var x = 1, y = 0;
	        for (var t = 0; t < 24; t++) {
	            RHO_OFFSETS[x + 5 * y] = ((t + 1) * (t + 2) / 2) % 64;

	            var newX = y % 5;
	            var newY = (2 * x + 3 * y) % 5;
	            x = newX;
	            y = newY;
	        }

	        // Compute pi index constants
	        for (var x = 0; x < 5; x++) {
	            for (var y = 0; y < 5; y++) {
	                PI_INDEXES[x + 5 * y] = y + ((2 * x + 3 * y) % 5) * 5;
	            }
	        }

	        // Compute round constants
	        var LFSR = 0x01;
	        for (var i = 0; i < 24; i++) {
	            var roundConstantMsw = 0;
	            var roundConstantLsw = 0;

	            for (var j = 0; j < 7; j++) {
	                if (LFSR & 0x01) {
	                    var bitPosition = (1 << j) - 1;
	                    if (bitPosition < 32) {
	                        roundConstantLsw ^= 1 << bitPosition;
	                    } else /* if (bitPosition >= 32) */ {
	                        roundConstantMsw ^= 1 << (bitPosition - 32);
	                    }
	                }

	                // Compute next LFSR
	                if (LFSR & 0x80) {
	                    // Primitive polynomial over GF(2): x^8 + x^6 + x^5 + x^4 + 1
	                    LFSR = (LFSR << 1) ^ 0x71;
	                } else {
	                    LFSR <<= 1;
	                }
	            }

	            ROUND_CONSTANTS[i] = X64Word.create(roundConstantMsw, roundConstantLsw);
	        }
	    }());

	    // Reusable objects for temporary values
	    var T = [];
	    (function () {
	        for (var i = 0; i < 25; i++) {
	            T[i] = X64Word.create();
	        }
	    }());

	    /**
	     * SHA-3 hash algorithm.
	     */
	    var SHA3 = C_algo.SHA3 = Hasher.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {number} outputLength
	         *   The desired number of bits in the output hash.
	         *   Only values permitted are: 224, 256, 384, 512.
	         *   Default: 512
	         */
	        cfg: Hasher.cfg.extend({
	            outputLength: 512
	        }),

	        _doReset: function () {
	            var state = this._state = []
	            for (var i = 0; i < 25; i++) {
	                state[i] = new X64Word.init();
	            }

	            this.blockSize = (1600 - 2 * this.cfg.outputLength) / 32;
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcuts
	            var state = this._state;
	            var nBlockSizeLanes = this.blockSize / 2;

	            // Absorb
	            for (var i = 0; i < nBlockSizeLanes; i++) {
	                // Shortcuts
	                var M2i  = M[offset + 2 * i];
	                var M2i1 = M[offset + 2 * i + 1];

	                // Swap endian
	                M2i = (
	                    (((M2i << 8)  | (M2i >>> 24)) & 0x00ff00ff) |
	                    (((M2i << 24) | (M2i >>> 8))  & 0xff00ff00)
	                );
	                M2i1 = (
	                    (((M2i1 << 8)  | (M2i1 >>> 24)) & 0x00ff00ff) |
	                    (((M2i1 << 24) | (M2i1 >>> 8))  & 0xff00ff00)
	                );

	                // Absorb message into state
	                var lane = state[i];
	                lane.high ^= M2i1;
	                lane.low  ^= M2i;
	            }

	            // Rounds
	            for (var round = 0; round < 24; round++) {
	                // Theta
	                for (var x = 0; x < 5; x++) {
	                    // Mix column lanes
	                    var tMsw = 0, tLsw = 0;
	                    for (var y = 0; y < 5; y++) {
	                        var lane = state[x + 5 * y];
	                        tMsw ^= lane.high;
	                        tLsw ^= lane.low;
	                    }

	                    // Temporary values
	                    var Tx = T[x];
	                    Tx.high = tMsw;
	                    Tx.low  = tLsw;
	                }
	                for (var x = 0; x < 5; x++) {
	                    // Shortcuts
	                    var Tx4 = T[(x + 4) % 5];
	                    var Tx1 = T[(x + 1) % 5];
	                    var Tx1Msw = Tx1.high;
	                    var Tx1Lsw = Tx1.low;

	                    // Mix surrounding columns
	                    var tMsw = Tx4.high ^ ((Tx1Msw << 1) | (Tx1Lsw >>> 31));
	                    var tLsw = Tx4.low  ^ ((Tx1Lsw << 1) | (Tx1Msw >>> 31));
	                    for (var y = 0; y < 5; y++) {
	                        var lane = state[x + 5 * y];
	                        lane.high ^= tMsw;
	                        lane.low  ^= tLsw;
	                    }
	                }

	                // Rho Pi
	                for (var laneIndex = 1; laneIndex < 25; laneIndex++) {
	                    // Shortcuts
	                    var lane = state[laneIndex];
	                    var laneMsw = lane.high;
	                    var laneLsw = lane.low;
	                    var rhoOffset = RHO_OFFSETS[laneIndex];

	                    // Rotate lanes
	                    if (rhoOffset < 32) {
	                        var tMsw = (laneMsw << rhoOffset) | (laneLsw >>> (32 - rhoOffset));
	                        var tLsw = (laneLsw << rhoOffset) | (laneMsw >>> (32 - rhoOffset));
	                    } else /* if (rhoOffset >= 32) */ {
	                        var tMsw = (laneLsw << (rhoOffset - 32)) | (laneMsw >>> (64 - rhoOffset));
	                        var tLsw = (laneMsw << (rhoOffset - 32)) | (laneLsw >>> (64 - rhoOffset));
	                    }

	                    // Transpose lanes
	                    var TPiLane = T[PI_INDEXES[laneIndex]];
	                    TPiLane.high = tMsw;
	                    TPiLane.low  = tLsw;
	                }

	                // Rho pi at x = y = 0
	                var T0 = T[0];
	                var state0 = state[0];
	                T0.high = state0.high;
	                T0.low  = state0.low;

	                // Chi
	                for (var x = 0; x < 5; x++) {
	                    for (var y = 0; y < 5; y++) {
	                        // Shortcuts
	                        var laneIndex = x + 5 * y;
	                        var lane = state[laneIndex];
	                        var TLane = T[laneIndex];
	                        var Tx1Lane = T[((x + 1) % 5) + 5 * y];
	                        var Tx2Lane = T[((x + 2) % 5) + 5 * y];

	                        // Mix rows
	                        lane.high = TLane.high ^ (~Tx1Lane.high & Tx2Lane.high);
	                        lane.low  = TLane.low  ^ (~Tx1Lane.low  & Tx2Lane.low);
	                    }
	                }

	                // Iota
	                var lane = state[0];
	                var roundConstant = ROUND_CONSTANTS[round];
	                lane.high ^= roundConstant.high;
	                lane.low  ^= roundConstant.low;;
	            }
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;
	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;
	            var blockSizeBits = this.blockSize * 32;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x1 << (24 - nBitsLeft % 32);
	            dataWords[((Math.ceil((nBitsLeft + 1) / blockSizeBits) * blockSizeBits) >>> 5) - 1] |= 0x80;
	            data.sigBytes = dataWords.length * 4;

	            // Hash final blocks
	            this._process();

	            // Shortcuts
	            var state = this._state;
	            var outputLengthBytes = this.cfg.outputLength / 8;
	            var outputLengthLanes = outputLengthBytes / 8;

	            // Squeeze
	            var hashWords = [];
	            for (var i = 0; i < outputLengthLanes; i++) {
	                // Shortcuts
	                var lane = state[i];
	                var laneMsw = lane.high;
	                var laneLsw = lane.low;

	                // Swap endian
	                laneMsw = (
	                    (((laneMsw << 8)  | (laneMsw >>> 24)) & 0x00ff00ff) |
	                    (((laneMsw << 24) | (laneMsw >>> 8))  & 0xff00ff00)
	                );
	                laneLsw = (
	                    (((laneLsw << 8)  | (laneLsw >>> 24)) & 0x00ff00ff) |
	                    (((laneLsw << 24) | (laneLsw >>> 8))  & 0xff00ff00)
	                );

	                // Squeeze state to retrieve hash
	                hashWords.push(laneLsw);
	                hashWords.push(laneMsw);
	            }

	            // Return final computed hash
	            return new WordArray.init(hashWords, outputLengthBytes);
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);

	            var state = clone._state = this._state.slice(0);
	            for (var i = 0; i < 25; i++) {
	                state[i] = state[i].clone();
	            }

	            return clone;
	        }
	    });

	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.SHA3('message');
	     *     var hash = CryptoJS.SHA3(wordArray);
	     */
	    C.SHA3 = Hasher._createHelper(SHA3);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacSHA3(message, key);
	     */
	    C.HmacSHA3 = Hasher._createHmacHelper(SHA3);
	}(Math));


	return CryptoJS.SHA3;

}));

/***/ }),
/* 39 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/** @preserve
	(c) 2012 by Cédric Mesnil. All rights reserved.

	Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

	    - Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
	    - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	*/

	(function (Math) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var Hasher = C_lib.Hasher;
	    var C_algo = C.algo;

	    // Constants table
	    var _zl = WordArray.create([
	        0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15,
	        7,  4, 13,  1, 10,  6, 15,  3, 12,  0,  9,  5,  2, 14, 11,  8,
	        3, 10, 14,  4,  9, 15,  8,  1,  2,  7,  0,  6, 13, 11,  5, 12,
	        1,  9, 11, 10,  0,  8, 12,  4, 13,  3,  7, 15, 14,  5,  6,  2,
	        4,  0,  5,  9,  7, 12,  2, 10, 14,  1,  3,  8, 11,  6, 15, 13]);
	    var _zr = WordArray.create([
	        5, 14,  7,  0,  9,  2, 11,  4, 13,  6, 15,  8,  1, 10,  3, 12,
	        6, 11,  3,  7,  0, 13,  5, 10, 14, 15,  8, 12,  4,  9,  1,  2,
	        15,  5,  1,  3,  7, 14,  6,  9, 11,  8, 12,  2, 10,  0,  4, 13,
	        8,  6,  4,  1,  3, 11, 15,  0,  5, 12,  2, 13,  9,  7, 10, 14,
	        12, 15, 10,  4,  1,  5,  8,  7,  6,  2, 13, 14,  0,  3,  9, 11]);
	    var _sl = WordArray.create([
	         11, 14, 15, 12,  5,  8,  7,  9, 11, 13, 14, 15,  6,  7,  9,  8,
	        7, 6,   8, 13, 11,  9,  7, 15,  7, 12, 15,  9, 11,  7, 13, 12,
	        11, 13,  6,  7, 14,  9, 13, 15, 14,  8, 13,  6,  5, 12,  7,  5,
	          11, 12, 14, 15, 14, 15,  9,  8,  9, 14,  5,  6,  8,  6,  5, 12,
	        9, 15,  5, 11,  6,  8, 13, 12,  5, 12, 13, 14, 11,  8,  5,  6 ]);
	    var _sr = WordArray.create([
	        8,  9,  9, 11, 13, 15, 15,  5,  7,  7,  8, 11, 14, 14, 12,  6,
	        9, 13, 15,  7, 12,  8,  9, 11,  7,  7, 12,  7,  6, 15, 13, 11,
	        9,  7, 15, 11,  8,  6,  6, 14, 12, 13,  5, 14, 13, 13,  7,  5,
	        15,  5,  8, 11, 14, 14,  6, 14,  6,  9, 12,  9, 12,  5, 15,  8,
	        8,  5, 12,  9, 12,  5, 14,  6,  8, 13,  6,  5, 15, 13, 11, 11 ]);

	    var _hl =  WordArray.create([ 0x00000000, 0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC, 0xA953FD4E]);
	    var _hr =  WordArray.create([ 0x50A28BE6, 0x5C4DD124, 0x6D703EF3, 0x7A6D76E9, 0x00000000]);

	    /**
	     * RIPEMD160 hash algorithm.
	     */
	    var RIPEMD160 = C_algo.RIPEMD160 = Hasher.extend({
	        _doReset: function () {
	            this._hash  = WordArray.create([0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0]);
	        },

	        _doProcessBlock: function (M, offset) {

	            // Swap endian
	            for (var i = 0; i < 16; i++) {
	                // Shortcuts
	                var offset_i = offset + i;
	                var M_offset_i = M[offset_i];

	                // Swap
	                M[offset_i] = (
	                    (((M_offset_i << 8)  | (M_offset_i >>> 24)) & 0x00ff00ff) |
	                    (((M_offset_i << 24) | (M_offset_i >>> 8))  & 0xff00ff00)
	                );
	            }
	            // Shortcut
	            var H  = this._hash.words;
	            var hl = _hl.words;
	            var hr = _hr.words;
	            var zl = _zl.words;
	            var zr = _zr.words;
	            var sl = _sl.words;
	            var sr = _sr.words;

	            // Working variables
	            var al, bl, cl, dl, el;
	            var ar, br, cr, dr, er;

	            ar = al = H[0];
	            br = bl = H[1];
	            cr = cl = H[2];
	            dr = dl = H[3];
	            er = el = H[4];
	            // Computation
	            var t;
	            for (var i = 0; i < 80; i += 1) {
	                t = (al +  M[offset+zl[i]])|0;
	                if (i<16){
		            t +=  f1(bl,cl,dl) + hl[0];
	                } else if (i<32) {
		            t +=  f2(bl,cl,dl) + hl[1];
	                } else if (i<48) {
		            t +=  f3(bl,cl,dl) + hl[2];
	                } else if (i<64) {
		            t +=  f4(bl,cl,dl) + hl[3];
	                } else {// if (i<80) {
		            t +=  f5(bl,cl,dl) + hl[4];
	                }
	                t = t|0;
	                t =  rotl(t,sl[i]);
	                t = (t+el)|0;
	                al = el;
	                el = dl;
	                dl = rotl(cl, 10);
	                cl = bl;
	                bl = t;

	                t = (ar + M[offset+zr[i]])|0;
	                if (i<16){
		            t +=  f5(br,cr,dr) + hr[0];
	                } else if (i<32) {
		            t +=  f4(br,cr,dr) + hr[1];
	                } else if (i<48) {
		            t +=  f3(br,cr,dr) + hr[2];
	                } else if (i<64) {
		            t +=  f2(br,cr,dr) + hr[3];
	                } else {// if (i<80) {
		            t +=  f1(br,cr,dr) + hr[4];
	                }
	                t = t|0;
	                t =  rotl(t,sr[i]) ;
	                t = (t+er)|0;
	                ar = er;
	                er = dr;
	                dr = rotl(cr, 10);
	                cr = br;
	                br = t;
	            }
	            // Intermediate hash value
	            t    = (H[1] + cl + dr)|0;
	            H[1] = (H[2] + dl + er)|0;
	            H[2] = (H[3] + el + ar)|0;
	            H[3] = (H[4] + al + br)|0;
	            H[4] = (H[0] + bl + cr)|0;
	            H[0] =  t;
	        },

	        _doFinalize: function () {
	            // Shortcuts
	            var data = this._data;
	            var dataWords = data.words;

	            var nBitsTotal = this._nDataBytes * 8;
	            var nBitsLeft = data.sigBytes * 8;

	            // Add padding
	            dataWords[nBitsLeft >>> 5] |= 0x80 << (24 - nBitsLeft % 32);
	            dataWords[(((nBitsLeft + 64) >>> 9) << 4) + 14] = (
	                (((nBitsTotal << 8)  | (nBitsTotal >>> 24)) & 0x00ff00ff) |
	                (((nBitsTotal << 24) | (nBitsTotal >>> 8))  & 0xff00ff00)
	            );
	            data.sigBytes = (dataWords.length + 1) * 4;

	            // Hash final blocks
	            this._process();

	            // Shortcuts
	            var hash = this._hash;
	            var H = hash.words;

	            // Swap endian
	            for (var i = 0; i < 5; i++) {
	                // Shortcut
	                var H_i = H[i];

	                // Swap
	                H[i] = (((H_i << 8)  | (H_i >>> 24)) & 0x00ff00ff) |
	                       (((H_i << 24) | (H_i >>> 8))  & 0xff00ff00);
	            }

	            // Return final computed hash
	            return hash;
	        },

	        clone: function () {
	            var clone = Hasher.clone.call(this);
	            clone._hash = this._hash.clone();

	            return clone;
	        }
	    });


	    function f1(x, y, z) {
	        return ((x) ^ (y) ^ (z));

	    }

	    function f2(x, y, z) {
	        return (((x)&(y)) | ((~x)&(z)));
	    }

	    function f3(x, y, z) {
	        return (((x) | (~(y))) ^ (z));
	    }

	    function f4(x, y, z) {
	        return (((x) & (z)) | ((y)&(~(z))));
	    }

	    function f5(x, y, z) {
	        return ((x) ^ ((y) |(~(z))));

	    }

	    function rotl(x,n) {
	        return (x<<n) | (x>>>(32-n));
	    }


	    /**
	     * Shortcut function to the hasher's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     *
	     * @return {WordArray} The hash.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hash = CryptoJS.RIPEMD160('message');
	     *     var hash = CryptoJS.RIPEMD160(wordArray);
	     */
	    C.RIPEMD160 = Hasher._createHelper(RIPEMD160);

	    /**
	     * Shortcut function to the HMAC's object interface.
	     *
	     * @param {WordArray|string} message The message to hash.
	     * @param {WordArray|string} key The secret key.
	     *
	     * @return {WordArray} The HMAC.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var hmac = CryptoJS.HmacRIPEMD160(message, key);
	     */
	    C.HmacRIPEMD160 = Hasher._createHmacHelper(RIPEMD160);
	}(Math));


	return CryptoJS.RIPEMD160;

}));

/***/ }),
/* 40 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(14), __webpack_require__(15));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./sha1", "./hmac"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var Base = C_lib.Base;
	    var WordArray = C_lib.WordArray;
	    var C_algo = C.algo;
	    var SHA1 = C_algo.SHA1;
	    var HMAC = C_algo.HMAC;

	    /**
	     * Password-Based Key Derivation Function 2 algorithm.
	     */
	    var PBKDF2 = C_algo.PBKDF2 = Base.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {number} keySize The key size in words to generate. Default: 4 (128 bits)
	         * @property {Hasher} hasher The hasher to use. Default: SHA1
	         * @property {number} iterations The number of iterations to perform. Default: 1
	         */
	        cfg: Base.extend({
	            keySize: 128/32,
	            hasher: SHA1,
	            iterations: 1
	        }),

	        /**
	         * Initializes a newly created key derivation function.
	         *
	         * @param {Object} cfg (Optional) The configuration options to use for the derivation.
	         *
	         * @example
	         *
	         *     var kdf = CryptoJS.algo.PBKDF2.create();
	         *     var kdf = CryptoJS.algo.PBKDF2.create({ keySize: 8 });
	         *     var kdf = CryptoJS.algo.PBKDF2.create({ keySize: 8, iterations: 1000 });
	         */
	        init: function (cfg) {
	            this.cfg = this.cfg.extend(cfg);
	        },

	        /**
	         * Computes the Password-Based Key Derivation Function 2.
	         *
	         * @param {WordArray|string} password The password.
	         * @param {WordArray|string} salt A salt.
	         *
	         * @return {WordArray} The derived key.
	         *
	         * @example
	         *
	         *     var key = kdf.compute(password, salt);
	         */
	        compute: function (password, salt) {
	            // Shortcut
	            var cfg = this.cfg;

	            // Init HMAC
	            var hmac = HMAC.create(cfg.hasher, password);

	            // Initial values
	            var derivedKey = WordArray.create();
	            var blockIndex = WordArray.create([0x00000001]);

	            // Shortcuts
	            var derivedKeyWords = derivedKey.words;
	            var blockIndexWords = blockIndex.words;
	            var keySize = cfg.keySize;
	            var iterations = cfg.iterations;

	            // Generate key
	            while (derivedKeyWords.length < keySize) {
	                var block = hmac.update(salt).finalize(blockIndex);
	                hmac.reset();

	                // Shortcuts
	                var blockWords = block.words;
	                var blockWordsLength = blockWords.length;

	                // Iterations
	                var intermediate = block;
	                for (var i = 1; i < iterations; i++) {
	                    intermediate = hmac.finalize(intermediate);
	                    hmac.reset();

	                    // Shortcut
	                    var intermediateWords = intermediate.words;

	                    // XOR intermediate with block
	                    for (var j = 0; j < blockWordsLength; j++) {
	                        blockWords[j] ^= intermediateWords[j];
	                    }
	                }

	                derivedKey.concat(block);
	                blockIndexWords[0]++;
	            }
	            derivedKey.sigBytes = keySize * 4;

	            return derivedKey;
	        }
	    });

	    /**
	     * Computes the Password-Based Key Derivation Function 2.
	     *
	     * @param {WordArray|string} password The password.
	     * @param {WordArray|string} salt A salt.
	     * @param {Object} cfg (Optional) The configuration options to use for this computation.
	     *
	     * @return {WordArray} The derived key.
	     *
	     * @static
	     *
	     * @example
	     *
	     *     var key = CryptoJS.PBKDF2(password, salt);
	     *     var key = CryptoJS.PBKDF2(password, salt, { keySize: 8 });
	     *     var key = CryptoJS.PBKDF2(password, salt, { keySize: 8, iterations: 1000 });
	     */
	    C.PBKDF2 = function (password, salt, cfg) {
	        return PBKDF2.create(cfg).compute(password, salt);
	    };
	}());


	return CryptoJS.PBKDF2;

}));

/***/ }),
/* 41 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Cipher Feedback block mode.
	 */
	CryptoJS.mode.CFB = (function () {
	    var CFB = CryptoJS.lib.BlockCipherMode.extend();

	    CFB.Encryptor = CFB.extend({
	        processBlock: function (words, offset) {
	            // Shortcuts
	            var cipher = this._cipher;
	            var blockSize = cipher.blockSize;

	            generateKeystreamAndEncrypt.call(this, words, offset, blockSize, cipher);

	            // Remember this block to use with next block
	            this._prevBlock = words.slice(offset, offset + blockSize);
	        }
	    });

	    CFB.Decryptor = CFB.extend({
	        processBlock: function (words, offset) {
	            // Shortcuts
	            var cipher = this._cipher;
	            var blockSize = cipher.blockSize;

	            // Remember this block to use with next block
	            var thisBlock = words.slice(offset, offset + blockSize);

	            generateKeystreamAndEncrypt.call(this, words, offset, blockSize, cipher);

	            // This block becomes the previous block
	            this._prevBlock = thisBlock;
	        }
	    });

	    function generateKeystreamAndEncrypt(words, offset, blockSize, cipher) {
	        // Shortcut
	        var iv = this._iv;

	        // Generate keystream
	        if (iv) {
	            var keystream = iv.slice(0);

	            // Remove IV for subsequent blocks
	            this._iv = undefined;
	        } else {
	            var keystream = this._prevBlock;
	        }
	        cipher.encryptBlock(keystream, 0);

	        // Encrypt
	        for (var i = 0; i < blockSize; i++) {
	            words[offset + i] ^= keystream[i];
	        }
	    }

	    return CFB;
	}());


	return CryptoJS.mode.CFB;

}));

/***/ }),
/* 42 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Counter block mode.
	 */
	CryptoJS.mode.CTR = (function () {
	    var CTR = CryptoJS.lib.BlockCipherMode.extend();

	    var Encryptor = CTR.Encryptor = CTR.extend({
	        processBlock: function (words, offset) {
	            // Shortcuts
	            var cipher = this._cipher
	            var blockSize = cipher.blockSize;
	            var iv = this._iv;
	            var counter = this._counter;

	            // Generate keystream
	            if (iv) {
	                counter = this._counter = iv.slice(0);

	                // Remove IV for subsequent blocks
	                this._iv = undefined;
	            }
	            var keystream = counter.slice(0);
	            cipher.encryptBlock(keystream, 0);

	            // Increment counter
	            counter[blockSize - 1] = (counter[blockSize - 1] + 1) | 0

	            // Encrypt
	            for (var i = 0; i < blockSize; i++) {
	                words[offset + i] ^= keystream[i];
	            }
	        }
	    });

	    CTR.Decryptor = Encryptor;

	    return CTR;
	}());


	return CryptoJS.mode.CTR;

}));

/***/ }),
/* 43 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/** @preserve
	 * Counter block mode compatible with  Dr Brian Gladman fileenc.c
	 * derived from CryptoJS.mode.CTR
	 * Jan Hruby jhruby.web@gmail.com
	 */
	CryptoJS.mode.CTRGladman = (function () {
	    var CTRGladman = CryptoJS.lib.BlockCipherMode.extend();

		function incWord(word)
		{
			if (((word >> 24) & 0xff) === 0xff) { //overflow
			var b1 = (word >> 16)&0xff;
			var b2 = (word >> 8)&0xff;
			var b3 = word & 0xff;

			if (b1 === 0xff) // overflow b1
			{
			b1 = 0;
			if (b2 === 0xff)
			{
				b2 = 0;
				if (b3 === 0xff)
				{
					b3 = 0;
				}
				else
				{
					++b3;
				}
			}
			else
			{
				++b2;
			}
			}
			else
			{
			++b1;
			}

			word = 0;
			word += (b1 << 16);
			word += (b2 << 8);
			word += b3;
			}
			else
			{
			word += (0x01 << 24);
			}
			return word;
		}

		function incCounter(counter)
		{
			if ((counter[0] = incWord(counter[0])) === 0)
			{
				// encr_data in fileenc.c from  Dr Brian Gladman's counts only with DWORD j < 8
				counter[1] = incWord(counter[1]);
			}
			return counter;
		}

	    var Encryptor = CTRGladman.Encryptor = CTRGladman.extend({
	        processBlock: function (words, offset) {
	            // Shortcuts
	            var cipher = this._cipher
	            var blockSize = cipher.blockSize;
	            var iv = this._iv;
	            var counter = this._counter;

	            // Generate keystream
	            if (iv) {
	                counter = this._counter = iv.slice(0);

	                // Remove IV for subsequent blocks
	                this._iv = undefined;
	            }

				incCounter(counter);

				var keystream = counter.slice(0);
	            cipher.encryptBlock(keystream, 0);

	            // Encrypt
	            for (var i = 0; i < blockSize; i++) {
	                words[offset + i] ^= keystream[i];
	            }
	        }
	    });

	    CTRGladman.Decryptor = Encryptor;

	    return CTRGladman;
	}());




	return CryptoJS.mode.CTRGladman;

}));

/***/ }),
/* 44 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Output Feedback block mode.
	 */
	CryptoJS.mode.OFB = (function () {
	    var OFB = CryptoJS.lib.BlockCipherMode.extend();

	    var Encryptor = OFB.Encryptor = OFB.extend({
	        processBlock: function (words, offset) {
	            // Shortcuts
	            var cipher = this._cipher
	            var blockSize = cipher.blockSize;
	            var iv = this._iv;
	            var keystream = this._keystream;

	            // Generate keystream
	            if (iv) {
	                keystream = this._keystream = iv.slice(0);

	                // Remove IV for subsequent blocks
	                this._iv = undefined;
	            }
	            cipher.encryptBlock(keystream, 0);

	            // Encrypt
	            for (var i = 0; i < blockSize; i++) {
	                words[offset + i] ^= keystream[i];
	            }
	        }
	    });

	    OFB.Decryptor = Encryptor;

	    return OFB;
	}());


	return CryptoJS.mode.OFB;

}));

/***/ }),
/* 45 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Electronic Codebook block mode.
	 */
	CryptoJS.mode.ECB = (function () {
	    var ECB = CryptoJS.lib.BlockCipherMode.extend();

	    ECB.Encryptor = ECB.extend({
	        processBlock: function (words, offset) {
	            this._cipher.encryptBlock(words, offset);
	        }
	    });

	    ECB.Decryptor = ECB.extend({
	        processBlock: function (words, offset) {
	            this._cipher.decryptBlock(words, offset);
	        }
	    });

	    return ECB;
	}());


	return CryptoJS.mode.ECB;

}));

/***/ }),
/* 46 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * ANSI X.923 padding strategy.
	 */
	CryptoJS.pad.AnsiX923 = {
	    pad: function (data, blockSize) {
	        // Shortcuts
	        var dataSigBytes = data.sigBytes;
	        var blockSizeBytes = blockSize * 4;

	        // Count padding bytes
	        var nPaddingBytes = blockSizeBytes - dataSigBytes % blockSizeBytes;

	        // Compute last byte position
	        var lastBytePos = dataSigBytes + nPaddingBytes - 1;

	        // Pad
	        data.clamp();
	        data.words[lastBytePos >>> 2] |= nPaddingBytes << (24 - (lastBytePos % 4) * 8);
	        data.sigBytes += nPaddingBytes;
	    },

	    unpad: function (data) {
	        // Get number of padding bytes from last byte
	        var nPaddingBytes = data.words[(data.sigBytes - 1) >>> 2] & 0xff;

	        // Remove padding
	        data.sigBytes -= nPaddingBytes;
	    }
	};


	return CryptoJS.pad.Ansix923;

}));

/***/ }),
/* 47 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * ISO 10126 padding strategy.
	 */
	CryptoJS.pad.Iso10126 = {
	    pad: function (data, blockSize) {
	        // Shortcut
	        var blockSizeBytes = blockSize * 4;

	        // Count padding bytes
	        var nPaddingBytes = blockSizeBytes - data.sigBytes % blockSizeBytes;

	        // Pad
	        data.concat(CryptoJS.lib.WordArray.random(nPaddingBytes - 1)).
	             concat(CryptoJS.lib.WordArray.create([nPaddingBytes << 24], 1));
	    },

	    unpad: function (data) {
	        // Get number of padding bytes from last byte
	        var nPaddingBytes = data.words[(data.sigBytes - 1) >>> 2] & 0xff;

	        // Remove padding
	        data.sigBytes -= nPaddingBytes;
	    }
	};


	return CryptoJS.pad.Iso10126;

}));

/***/ }),
/* 48 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * ISO/IEC 9797-1 Padding Method 2.
	 */
	CryptoJS.pad.Iso97971 = {
	    pad: function (data, blockSize) {
	        // Add 0x80 byte
	        data.concat(CryptoJS.lib.WordArray.create([0x80000000], 1));

	        // Zero pad the rest
	        CryptoJS.pad.ZeroPadding.pad(data, blockSize);
	    },

	    unpad: function (data) {
	        // Remove zero padding
	        CryptoJS.pad.ZeroPadding.unpad(data);

	        // Remove one more byte -- the 0x80 byte
	        data.sigBytes--;
	    }
	};


	return CryptoJS.pad.Iso97971;

}));

/***/ }),
/* 49 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * Zero padding strategy.
	 */
	CryptoJS.pad.ZeroPadding = {
	    pad: function (data, blockSize) {
	        // Shortcut
	        var blockSizeBytes = blockSize * 4;

	        // Pad
	        data.clamp();
	        data.sigBytes += blockSizeBytes - ((data.sigBytes % blockSizeBytes) || blockSizeBytes);
	    },

	    unpad: function (data) {
	        // Shortcut
	        var dataWords = data.words;

	        // Unpad
	        var i = data.sigBytes - 1;
	        while (!((dataWords[i >>> 2] >>> (24 - (i % 4) * 8)) & 0xff)) {
	            i--;
	        }
	        data.sigBytes = i + 1;
	    }
	};


	return CryptoJS.pad.ZeroPadding;

}));

/***/ }),
/* 50 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	/**
	 * A noop padding strategy.
	 */
	CryptoJS.pad.NoPadding = {
	    pad: function () {
	    },

	    unpad: function () {
	    }
	};


	return CryptoJS.pad.NoPadding;

}));

/***/ }),
/* 51 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function (undefined) {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var CipherParams = C_lib.CipherParams;
	    var C_enc = C.enc;
	    var Hex = C_enc.Hex;
	    var C_format = C.format;

	    var HexFormatter = C_format.Hex = {
	        /**
	         * Converts the ciphertext of a cipher params object to a hexadecimally encoded string.
	         *
	         * @param {CipherParams} cipherParams The cipher params object.
	         *
	         * @return {string} The hexadecimally encoded string.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var hexString = CryptoJS.format.Hex.stringify(cipherParams);
	         */
	        stringify: function (cipherParams) {
	            return cipherParams.ciphertext.toString(Hex);
	        },

	        /**
	         * Converts a hexadecimally encoded ciphertext string to a cipher params object.
	         *
	         * @param {string} input The hexadecimally encoded string.
	         *
	         * @return {CipherParams} The cipher params object.
	         *
	         * @static
	         *
	         * @example
	         *
	         *     var cipherParams = CryptoJS.format.Hex.parse(hexString);
	         */
	        parse: function (input) {
	            var ciphertext = Hex.parse(input);
	            return CipherParams.create({ ciphertext: ciphertext });
	        }
	    };
	}());


	return CryptoJS.format.Hex;

}));

/***/ }),
/* 52 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(8), __webpack_require__(9), __webpack_require__(6), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./enc-base64", "./md5", "./evpkdf", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var BlockCipher = C_lib.BlockCipher;
	    var C_algo = C.algo;

	    // Lookup tables
	    var SBOX = [];
	    var INV_SBOX = [];
	    var SUB_MIX_0 = [];
	    var SUB_MIX_1 = [];
	    var SUB_MIX_2 = [];
	    var SUB_MIX_3 = [];
	    var INV_SUB_MIX_0 = [];
	    var INV_SUB_MIX_1 = [];
	    var INV_SUB_MIX_2 = [];
	    var INV_SUB_MIX_3 = [];

	    // Compute lookup tables
	    (function () {
	        // Compute double table
	        var d = [];
	        for (var i = 0; i < 256; i++) {
	            if (i < 128) {
	                d[i] = i << 1;
	            } else {
	                d[i] = (i << 1) ^ 0x11b;
	            }
	        }

	        // Walk GF(2^8)
	        var x = 0;
	        var xi = 0;
	        for (var i = 0; i < 256; i++) {
	            // Compute sbox
	            var sx = xi ^ (xi << 1) ^ (xi << 2) ^ (xi << 3) ^ (xi << 4);
	            sx = (sx >>> 8) ^ (sx & 0xff) ^ 0x63;
	            SBOX[x] = sx;
	            INV_SBOX[sx] = x;

	            // Compute multiplication
	            var x2 = d[x];
	            var x4 = d[x2];
	            var x8 = d[x4];

	            // Compute sub bytes, mix columns tables
	            var t = (d[sx] * 0x101) ^ (sx * 0x1010100);
	            SUB_MIX_0[x] = (t << 24) | (t >>> 8);
	            SUB_MIX_1[x] = (t << 16) | (t >>> 16);
	            SUB_MIX_2[x] = (t << 8)  | (t >>> 24);
	            SUB_MIX_3[x] = t;

	            // Compute inv sub bytes, inv mix columns tables
	            var t = (x8 * 0x1010101) ^ (x4 * 0x10001) ^ (x2 * 0x101) ^ (x * 0x1010100);
	            INV_SUB_MIX_0[sx] = (t << 24) | (t >>> 8);
	            INV_SUB_MIX_1[sx] = (t << 16) | (t >>> 16);
	            INV_SUB_MIX_2[sx] = (t << 8)  | (t >>> 24);
	            INV_SUB_MIX_3[sx] = t;

	            // Compute next counter
	            if (!x) {
	                x = xi = 1;
	            } else {
	                x = x2 ^ d[d[d[x8 ^ x2]]];
	                xi ^= d[d[xi]];
	            }
	        }
	    }());

	    // Precomputed Rcon lookup
	    var RCON = [0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36];

	    /**
	     * AES block cipher algorithm.
	     */
	    var AES = C_algo.AES = BlockCipher.extend({
	        _doReset: function () {
	            // Skip reset of nRounds has been set before and key did not change
	            if (this._nRounds && this._keyPriorReset === this._key) {
	                return;
	            }

	            // Shortcuts
	            var key = this._keyPriorReset = this._key;
	            var keyWords = key.words;
	            var keySize = key.sigBytes / 4;

	            // Compute number of rounds
	            var nRounds = this._nRounds = keySize + 6;

	            // Compute number of key schedule rows
	            var ksRows = (nRounds + 1) * 4;

	            // Compute key schedule
	            var keySchedule = this._keySchedule = [];
	            for (var ksRow = 0; ksRow < ksRows; ksRow++) {
	                if (ksRow < keySize) {
	                    keySchedule[ksRow] = keyWords[ksRow];
	                } else {
	                    var t = keySchedule[ksRow - 1];

	                    if (!(ksRow % keySize)) {
	                        // Rot word
	                        t = (t << 8) | (t >>> 24);

	                        // Sub word
	                        t = (SBOX[t >>> 24] << 24) | (SBOX[(t >>> 16) & 0xff] << 16) | (SBOX[(t >>> 8) & 0xff] << 8) | SBOX[t & 0xff];

	                        // Mix Rcon
	                        t ^= RCON[(ksRow / keySize) | 0] << 24;
	                    } else if (keySize > 6 && ksRow % keySize == 4) {
	                        // Sub word
	                        t = (SBOX[t >>> 24] << 24) | (SBOX[(t >>> 16) & 0xff] << 16) | (SBOX[(t >>> 8) & 0xff] << 8) | SBOX[t & 0xff];
	                    }

	                    keySchedule[ksRow] = keySchedule[ksRow - keySize] ^ t;
	                }
	            }

	            // Compute inv key schedule
	            var invKeySchedule = this._invKeySchedule = [];
	            for (var invKsRow = 0; invKsRow < ksRows; invKsRow++) {
	                var ksRow = ksRows - invKsRow;

	                if (invKsRow % 4) {
	                    var t = keySchedule[ksRow];
	                } else {
	                    var t = keySchedule[ksRow - 4];
	                }

	                if (invKsRow < 4 || ksRow <= 4) {
	                    invKeySchedule[invKsRow] = t;
	                } else {
	                    invKeySchedule[invKsRow] = INV_SUB_MIX_0[SBOX[t >>> 24]] ^ INV_SUB_MIX_1[SBOX[(t >>> 16) & 0xff]] ^
	                                               INV_SUB_MIX_2[SBOX[(t >>> 8) & 0xff]] ^ INV_SUB_MIX_3[SBOX[t & 0xff]];
	                }
	            }
	        },

	        encryptBlock: function (M, offset) {
	            this._doCryptBlock(M, offset, this._keySchedule, SUB_MIX_0, SUB_MIX_1, SUB_MIX_2, SUB_MIX_3, SBOX);
	        },

	        decryptBlock: function (M, offset) {
	            // Swap 2nd and 4th rows
	            var t = M[offset + 1];
	            M[offset + 1] = M[offset + 3];
	            M[offset + 3] = t;

	            this._doCryptBlock(M, offset, this._invKeySchedule, INV_SUB_MIX_0, INV_SUB_MIX_1, INV_SUB_MIX_2, INV_SUB_MIX_3, INV_SBOX);

	            // Inv swap 2nd and 4th rows
	            var t = M[offset + 1];
	            M[offset + 1] = M[offset + 3];
	            M[offset + 3] = t;
	        },

	        _doCryptBlock: function (M, offset, keySchedule, SUB_MIX_0, SUB_MIX_1, SUB_MIX_2, SUB_MIX_3, SBOX) {
	            // Shortcut
	            var nRounds = this._nRounds;

	            // Get input, add round key
	            var s0 = M[offset]     ^ keySchedule[0];
	            var s1 = M[offset + 1] ^ keySchedule[1];
	            var s2 = M[offset + 2] ^ keySchedule[2];
	            var s3 = M[offset + 3] ^ keySchedule[3];

	            // Key schedule row counter
	            var ksRow = 4;

	            // Rounds
	            for (var round = 1; round < nRounds; round++) {
	                // Shift rows, sub bytes, mix columns, add round key
	                var t0 = SUB_MIX_0[s0 >>> 24] ^ SUB_MIX_1[(s1 >>> 16) & 0xff] ^ SUB_MIX_2[(s2 >>> 8) & 0xff] ^ SUB_MIX_3[s3 & 0xff] ^ keySchedule[ksRow++];
	                var t1 = SUB_MIX_0[s1 >>> 24] ^ SUB_MIX_1[(s2 >>> 16) & 0xff] ^ SUB_MIX_2[(s3 >>> 8) & 0xff] ^ SUB_MIX_3[s0 & 0xff] ^ keySchedule[ksRow++];
	                var t2 = SUB_MIX_0[s2 >>> 24] ^ SUB_MIX_1[(s3 >>> 16) & 0xff] ^ SUB_MIX_2[(s0 >>> 8) & 0xff] ^ SUB_MIX_3[s1 & 0xff] ^ keySchedule[ksRow++];
	                var t3 = SUB_MIX_0[s3 >>> 24] ^ SUB_MIX_1[(s0 >>> 16) & 0xff] ^ SUB_MIX_2[(s1 >>> 8) & 0xff] ^ SUB_MIX_3[s2 & 0xff] ^ keySchedule[ksRow++];

	                // Update state
	                s0 = t0;
	                s1 = t1;
	                s2 = t2;
	                s3 = t3;
	            }

	            // Shift rows, sub bytes, add round key
	            var t0 = ((SBOX[s0 >>> 24] << 24) | (SBOX[(s1 >>> 16) & 0xff] << 16) | (SBOX[(s2 >>> 8) & 0xff] << 8) | SBOX[s3 & 0xff]) ^ keySchedule[ksRow++];
	            var t1 = ((SBOX[s1 >>> 24] << 24) | (SBOX[(s2 >>> 16) & 0xff] << 16) | (SBOX[(s3 >>> 8) & 0xff] << 8) | SBOX[s0 & 0xff]) ^ keySchedule[ksRow++];
	            var t2 = ((SBOX[s2 >>> 24] << 24) | (SBOX[(s3 >>> 16) & 0xff] << 16) | (SBOX[(s0 >>> 8) & 0xff] << 8) | SBOX[s1 & 0xff]) ^ keySchedule[ksRow++];
	            var t3 = ((SBOX[s3 >>> 24] << 24) | (SBOX[(s0 >>> 16) & 0xff] << 16) | (SBOX[(s1 >>> 8) & 0xff] << 8) | SBOX[s2 & 0xff]) ^ keySchedule[ksRow++];

	            // Set output
	            M[offset]     = t0;
	            M[offset + 1] = t1;
	            M[offset + 2] = t2;
	            M[offset + 3] = t3;
	        },

	        keySize: 256/32
	    });

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.AES.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.AES.decrypt(ciphertext, key, cfg);
	     */
	    C.AES = BlockCipher._createHelper(AES);
	}());


	return CryptoJS.AES;

}));

/***/ }),
/* 53 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(8), __webpack_require__(9), __webpack_require__(6), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./enc-base64", "./md5", "./evpkdf", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var WordArray = C_lib.WordArray;
	    var BlockCipher = C_lib.BlockCipher;
	    var C_algo = C.algo;

	    // Permuted Choice 1 constants
	    var PC1 = [
	        57, 49, 41, 33, 25, 17, 9,  1,
	        58, 50, 42, 34, 26, 18, 10, 2,
	        59, 51, 43, 35, 27, 19, 11, 3,
	        60, 52, 44, 36, 63, 55, 47, 39,
	        31, 23, 15, 7,  62, 54, 46, 38,
	        30, 22, 14, 6,  61, 53, 45, 37,
	        29, 21, 13, 5,  28, 20, 12, 4
	    ];

	    // Permuted Choice 2 constants
	    var PC2 = [
	        14, 17, 11, 24, 1,  5,
	        3,  28, 15, 6,  21, 10,
	        23, 19, 12, 4,  26, 8,
	        16, 7,  27, 20, 13, 2,
	        41, 52, 31, 37, 47, 55,
	        30, 40, 51, 45, 33, 48,
	        44, 49, 39, 56, 34, 53,
	        46, 42, 50, 36, 29, 32
	    ];

	    // Cumulative bit shift constants
	    var BIT_SHIFTS = [1,  2,  4,  6,  8,  10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28];

	    // SBOXes and round permutation constants
	    var SBOX_P = [
	        {
	            0x0: 0x808200,
	            0x10000000: 0x8000,
	            0x20000000: 0x808002,
	            0x30000000: 0x2,
	            0x40000000: 0x200,
	            0x50000000: 0x808202,
	            0x60000000: 0x800202,
	            0x70000000: 0x800000,
	            0x80000000: 0x202,
	            0x90000000: 0x800200,
	            0xa0000000: 0x8200,
	            0xb0000000: 0x808000,
	            0xc0000000: 0x8002,
	            0xd0000000: 0x800002,
	            0xe0000000: 0x0,
	            0xf0000000: 0x8202,
	            0x8000000: 0x0,
	            0x18000000: 0x808202,
	            0x28000000: 0x8202,
	            0x38000000: 0x8000,
	            0x48000000: 0x808200,
	            0x58000000: 0x200,
	            0x68000000: 0x808002,
	            0x78000000: 0x2,
	            0x88000000: 0x800200,
	            0x98000000: 0x8200,
	            0xa8000000: 0x808000,
	            0xb8000000: 0x800202,
	            0xc8000000: 0x800002,
	            0xd8000000: 0x8002,
	            0xe8000000: 0x202,
	            0xf8000000: 0x800000,
	            0x1: 0x8000,
	            0x10000001: 0x2,
	            0x20000001: 0x808200,
	            0x30000001: 0x800000,
	            0x40000001: 0x808002,
	            0x50000001: 0x8200,
	            0x60000001: 0x200,
	            0x70000001: 0x800202,
	            0x80000001: 0x808202,
	            0x90000001: 0x808000,
	            0xa0000001: 0x800002,
	            0xb0000001: 0x8202,
	            0xc0000001: 0x202,
	            0xd0000001: 0x800200,
	            0xe0000001: 0x8002,
	            0xf0000001: 0x0,
	            0x8000001: 0x808202,
	            0x18000001: 0x808000,
	            0x28000001: 0x800000,
	            0x38000001: 0x200,
	            0x48000001: 0x8000,
	            0x58000001: 0x800002,
	            0x68000001: 0x2,
	            0x78000001: 0x8202,
	            0x88000001: 0x8002,
	            0x98000001: 0x800202,
	            0xa8000001: 0x202,
	            0xb8000001: 0x808200,
	            0xc8000001: 0x800200,
	            0xd8000001: 0x0,
	            0xe8000001: 0x8200,
	            0xf8000001: 0x808002
	        },
	        {
	            0x0: 0x40084010,
	            0x1000000: 0x4000,
	            0x2000000: 0x80000,
	            0x3000000: 0x40080010,
	            0x4000000: 0x40000010,
	            0x5000000: 0x40084000,
	            0x6000000: 0x40004000,
	            0x7000000: 0x10,
	            0x8000000: 0x84000,
	            0x9000000: 0x40004010,
	            0xa000000: 0x40000000,
	            0xb000000: 0x84010,
	            0xc000000: 0x80010,
	            0xd000000: 0x0,
	            0xe000000: 0x4010,
	            0xf000000: 0x40080000,
	            0x800000: 0x40004000,
	            0x1800000: 0x84010,
	            0x2800000: 0x10,
	            0x3800000: 0x40004010,
	            0x4800000: 0x40084010,
	            0x5800000: 0x40000000,
	            0x6800000: 0x80000,
	            0x7800000: 0x40080010,
	            0x8800000: 0x80010,
	            0x9800000: 0x0,
	            0xa800000: 0x4000,
	            0xb800000: 0x40080000,
	            0xc800000: 0x40000010,
	            0xd800000: 0x84000,
	            0xe800000: 0x40084000,
	            0xf800000: 0x4010,
	            0x10000000: 0x0,
	            0x11000000: 0x40080010,
	            0x12000000: 0x40004010,
	            0x13000000: 0x40084000,
	            0x14000000: 0x40080000,
	            0x15000000: 0x10,
	            0x16000000: 0x84010,
	            0x17000000: 0x4000,
	            0x18000000: 0x4010,
	            0x19000000: 0x80000,
	            0x1a000000: 0x80010,
	            0x1b000000: 0x40000010,
	            0x1c000000: 0x84000,
	            0x1d000000: 0x40004000,
	            0x1e000000: 0x40000000,
	            0x1f000000: 0x40084010,
	            0x10800000: 0x84010,
	            0x11800000: 0x80000,
	            0x12800000: 0x40080000,
	            0x13800000: 0x4000,
	            0x14800000: 0x40004000,
	            0x15800000: 0x40084010,
	            0x16800000: 0x10,
	            0x17800000: 0x40000000,
	            0x18800000: 0x40084000,
	            0x19800000: 0x40000010,
	            0x1a800000: 0x40004010,
	            0x1b800000: 0x80010,
	            0x1c800000: 0x0,
	            0x1d800000: 0x4010,
	            0x1e800000: 0x40080010,
	            0x1f800000: 0x84000
	        },
	        {
	            0x0: 0x104,
	            0x100000: 0x0,
	            0x200000: 0x4000100,
	            0x300000: 0x10104,
	            0x400000: 0x10004,
	            0x500000: 0x4000004,
	            0x600000: 0x4010104,
	            0x700000: 0x4010000,
	            0x800000: 0x4000000,
	            0x900000: 0x4010100,
	            0xa00000: 0x10100,
	            0xb00000: 0x4010004,
	            0xc00000: 0x4000104,
	            0xd00000: 0x10000,
	            0xe00000: 0x4,
	            0xf00000: 0x100,
	            0x80000: 0x4010100,
	            0x180000: 0x4010004,
	            0x280000: 0x0,
	            0x380000: 0x4000100,
	            0x480000: 0x4000004,
	            0x580000: 0x10000,
	            0x680000: 0x10004,
	            0x780000: 0x104,
	            0x880000: 0x4,
	            0x980000: 0x100,
	            0xa80000: 0x4010000,
	            0xb80000: 0x10104,
	            0xc80000: 0x10100,
	            0xd80000: 0x4000104,
	            0xe80000: 0x4010104,
	            0xf80000: 0x4000000,
	            0x1000000: 0x4010100,
	            0x1100000: 0x10004,
	            0x1200000: 0x10000,
	            0x1300000: 0x4000100,
	            0x1400000: 0x100,
	            0x1500000: 0x4010104,
	            0x1600000: 0x4000004,
	            0x1700000: 0x0,
	            0x1800000: 0x4000104,
	            0x1900000: 0x4000000,
	            0x1a00000: 0x4,
	            0x1b00000: 0x10100,
	            0x1c00000: 0x4010000,
	            0x1d00000: 0x104,
	            0x1e00000: 0x10104,
	            0x1f00000: 0x4010004,
	            0x1080000: 0x4000000,
	            0x1180000: 0x104,
	            0x1280000: 0x4010100,
	            0x1380000: 0x0,
	            0x1480000: 0x10004,
	            0x1580000: 0x4000100,
	            0x1680000: 0x100,
	            0x1780000: 0x4010004,
	            0x1880000: 0x10000,
	            0x1980000: 0x4010104,
	            0x1a80000: 0x10104,
	            0x1b80000: 0x4000004,
	            0x1c80000: 0x4000104,
	            0x1d80000: 0x4010000,
	            0x1e80000: 0x4,
	            0x1f80000: 0x10100
	        },
	        {
	            0x0: 0x80401000,
	            0x10000: 0x80001040,
	            0x20000: 0x401040,
	            0x30000: 0x80400000,
	            0x40000: 0x0,
	            0x50000: 0x401000,
	            0x60000: 0x80000040,
	            0x70000: 0x400040,
	            0x80000: 0x80000000,
	            0x90000: 0x400000,
	            0xa0000: 0x40,
	            0xb0000: 0x80001000,
	            0xc0000: 0x80400040,
	            0xd0000: 0x1040,
	            0xe0000: 0x1000,
	            0xf0000: 0x80401040,
	            0x8000: 0x80001040,
	            0x18000: 0x40,
	            0x28000: 0x80400040,
	            0x38000: 0x80001000,
	            0x48000: 0x401000,
	            0x58000: 0x80401040,
	            0x68000: 0x0,
	            0x78000: 0x80400000,
	            0x88000: 0x1000,
	            0x98000: 0x80401000,
	            0xa8000: 0x400000,
	            0xb8000: 0x1040,
	            0xc8000: 0x80000000,
	            0xd8000: 0x400040,
	            0xe8000: 0x401040,
	            0xf8000: 0x80000040,
	            0x100000: 0x400040,
	            0x110000: 0x401000,
	            0x120000: 0x80000040,
	            0x130000: 0x0,
	            0x140000: 0x1040,
	            0x150000: 0x80400040,
	            0x160000: 0x80401000,
	            0x170000: 0x80001040,
	            0x180000: 0x80401040,
	            0x190000: 0x80000000,
	            0x1a0000: 0x80400000,
	            0x1b0000: 0x401040,
	            0x1c0000: 0x80001000,
	            0x1d0000: 0x400000,
	            0x1e0000: 0x40,
	            0x1f0000: 0x1000,
	            0x108000: 0x80400000,
	            0x118000: 0x80401040,
	            0x128000: 0x0,
	            0x138000: 0x401000,
	            0x148000: 0x400040,
	            0x158000: 0x80000000,
	            0x168000: 0x80001040,
	            0x178000: 0x40,
	            0x188000: 0x80000040,
	            0x198000: 0x1000,
	            0x1a8000: 0x80001000,
	            0x1b8000: 0x80400040,
	            0x1c8000: 0x1040,
	            0x1d8000: 0x80401000,
	            0x1e8000: 0x400000,
	            0x1f8000: 0x401040
	        },
	        {
	            0x0: 0x80,
	            0x1000: 0x1040000,
	            0x2000: 0x40000,
	            0x3000: 0x20000000,
	            0x4000: 0x20040080,
	            0x5000: 0x1000080,
	            0x6000: 0x21000080,
	            0x7000: 0x40080,
	            0x8000: 0x1000000,
	            0x9000: 0x20040000,
	            0xa000: 0x20000080,
	            0xb000: 0x21040080,
	            0xc000: 0x21040000,
	            0xd000: 0x0,
	            0xe000: 0x1040080,
	            0xf000: 0x21000000,
	            0x800: 0x1040080,
	            0x1800: 0x21000080,
	            0x2800: 0x80,
	            0x3800: 0x1040000,
	            0x4800: 0x40000,
	            0x5800: 0x20040080,
	            0x6800: 0x21040000,
	            0x7800: 0x20000000,
	            0x8800: 0x20040000,
	            0x9800: 0x0,
	            0xa800: 0x21040080,
	            0xb800: 0x1000080,
	            0xc800: 0x20000080,
	            0xd800: 0x21000000,
	            0xe800: 0x1000000,
	            0xf800: 0x40080,
	            0x10000: 0x40000,
	            0x11000: 0x80,
	            0x12000: 0x20000000,
	            0x13000: 0x21000080,
	            0x14000: 0x1000080,
	            0x15000: 0x21040000,
	            0x16000: 0x20040080,
	            0x17000: 0x1000000,
	            0x18000: 0x21040080,
	            0x19000: 0x21000000,
	            0x1a000: 0x1040000,
	            0x1b000: 0x20040000,
	            0x1c000: 0x40080,
	            0x1d000: 0x20000080,
	            0x1e000: 0x0,
	            0x1f000: 0x1040080,
	            0x10800: 0x21000080,
	            0x11800: 0x1000000,
	            0x12800: 0x1040000,
	            0x13800: 0x20040080,
	            0x14800: 0x20000000,
	            0x15800: 0x1040080,
	            0x16800: 0x80,
	            0x17800: 0x21040000,
	            0x18800: 0x40080,
	            0x19800: 0x21040080,
	            0x1a800: 0x0,
	            0x1b800: 0x21000000,
	            0x1c800: 0x1000080,
	            0x1d800: 0x40000,
	            0x1e800: 0x20040000,
	            0x1f800: 0x20000080
	        },
	        {
	            0x0: 0x10000008,
	            0x100: 0x2000,
	            0x200: 0x10200000,
	            0x300: 0x10202008,
	            0x400: 0x10002000,
	            0x500: 0x200000,
	            0x600: 0x200008,
	            0x700: 0x10000000,
	            0x800: 0x0,
	            0x900: 0x10002008,
	            0xa00: 0x202000,
	            0xb00: 0x8,
	            0xc00: 0x10200008,
	            0xd00: 0x202008,
	            0xe00: 0x2008,
	            0xf00: 0x10202000,
	            0x80: 0x10200000,
	            0x180: 0x10202008,
	            0x280: 0x8,
	            0x380: 0x200000,
	            0x480: 0x202008,
	            0x580: 0x10000008,
	            0x680: 0x10002000,
	            0x780: 0x2008,
	            0x880: 0x200008,
	            0x980: 0x2000,
	            0xa80: 0x10002008,
	            0xb80: 0x10200008,
	            0xc80: 0x0,
	            0xd80: 0x10202000,
	            0xe80: 0x202000,
	            0xf80: 0x10000000,
	            0x1000: 0x10002000,
	            0x1100: 0x10200008,
	            0x1200: 0x10202008,
	            0x1300: 0x2008,
	            0x1400: 0x200000,
	            0x1500: 0x10000000,
	            0x1600: 0x10000008,
	            0x1700: 0x202000,
	            0x1800: 0x202008,
	            0x1900: 0x0,
	            0x1a00: 0x8,
	            0x1b00: 0x10200000,
	            0x1c00: 0x2000,
	            0x1d00: 0x10002008,
	            0x1e00: 0x10202000,
	            0x1f00: 0x200008,
	            0x1080: 0x8,
	            0x1180: 0x202000,
	            0x1280: 0x200000,
	            0x1380: 0x10000008,
	            0x1480: 0x10002000,
	            0x1580: 0x2008,
	            0x1680: 0x10202008,
	            0x1780: 0x10200000,
	            0x1880: 0x10202000,
	            0x1980: 0x10200008,
	            0x1a80: 0x2000,
	            0x1b80: 0x202008,
	            0x1c80: 0x200008,
	            0x1d80: 0x0,
	            0x1e80: 0x10000000,
	            0x1f80: 0x10002008
	        },
	        {
	            0x0: 0x100000,
	            0x10: 0x2000401,
	            0x20: 0x400,
	            0x30: 0x100401,
	            0x40: 0x2100401,
	            0x50: 0x0,
	            0x60: 0x1,
	            0x70: 0x2100001,
	            0x80: 0x2000400,
	            0x90: 0x100001,
	            0xa0: 0x2000001,
	            0xb0: 0x2100400,
	            0xc0: 0x2100000,
	            0xd0: 0x401,
	            0xe0: 0x100400,
	            0xf0: 0x2000000,
	            0x8: 0x2100001,
	            0x18: 0x0,
	            0x28: 0x2000401,
	            0x38: 0x2100400,
	            0x48: 0x100000,
	            0x58: 0x2000001,
	            0x68: 0x2000000,
	            0x78: 0x401,
	            0x88: 0x100401,
	            0x98: 0x2000400,
	            0xa8: 0x2100000,
	            0xb8: 0x100001,
	            0xc8: 0x400,
	            0xd8: 0x2100401,
	            0xe8: 0x1,
	            0xf8: 0x100400,
	            0x100: 0x2000000,
	            0x110: 0x100000,
	            0x120: 0x2000401,
	            0x130: 0x2100001,
	            0x140: 0x100001,
	            0x150: 0x2000400,
	            0x160: 0x2100400,
	            0x170: 0x100401,
	            0x180: 0x401,
	            0x190: 0x2100401,
	            0x1a0: 0x100400,
	            0x1b0: 0x1,
	            0x1c0: 0x0,
	            0x1d0: 0x2100000,
	            0x1e0: 0x2000001,
	            0x1f0: 0x400,
	            0x108: 0x100400,
	            0x118: 0x2000401,
	            0x128: 0x2100001,
	            0x138: 0x1,
	            0x148: 0x2000000,
	            0x158: 0x100000,
	            0x168: 0x401,
	            0x178: 0x2100400,
	            0x188: 0x2000001,
	            0x198: 0x2100000,
	            0x1a8: 0x0,
	            0x1b8: 0x2100401,
	            0x1c8: 0x100401,
	            0x1d8: 0x400,
	            0x1e8: 0x2000400,
	            0x1f8: 0x100001
	        },
	        {
	            0x0: 0x8000820,
	            0x1: 0x20000,
	            0x2: 0x8000000,
	            0x3: 0x20,
	            0x4: 0x20020,
	            0x5: 0x8020820,
	            0x6: 0x8020800,
	            0x7: 0x800,
	            0x8: 0x8020000,
	            0x9: 0x8000800,
	            0xa: 0x20800,
	            0xb: 0x8020020,
	            0xc: 0x820,
	            0xd: 0x0,
	            0xe: 0x8000020,
	            0xf: 0x20820,
	            0x80000000: 0x800,
	            0x80000001: 0x8020820,
	            0x80000002: 0x8000820,
	            0x80000003: 0x8000000,
	            0x80000004: 0x8020000,
	            0x80000005: 0x20800,
	            0x80000006: 0x20820,
	            0x80000007: 0x20,
	            0x80000008: 0x8000020,
	            0x80000009: 0x820,
	            0x8000000a: 0x20020,
	            0x8000000b: 0x8020800,
	            0x8000000c: 0x0,
	            0x8000000d: 0x8020020,
	            0x8000000e: 0x8000800,
	            0x8000000f: 0x20000,
	            0x10: 0x20820,
	            0x11: 0x8020800,
	            0x12: 0x20,
	            0x13: 0x800,
	            0x14: 0x8000800,
	            0x15: 0x8000020,
	            0x16: 0x8020020,
	            0x17: 0x20000,
	            0x18: 0x0,
	            0x19: 0x20020,
	            0x1a: 0x8020000,
	            0x1b: 0x8000820,
	            0x1c: 0x8020820,
	            0x1d: 0x20800,
	            0x1e: 0x820,
	            0x1f: 0x8000000,
	            0x80000010: 0x20000,
	            0x80000011: 0x800,
	            0x80000012: 0x8020020,
	            0x80000013: 0x20820,
	            0x80000014: 0x20,
	            0x80000015: 0x8020000,
	            0x80000016: 0x8000000,
	            0x80000017: 0x8000820,
	            0x80000018: 0x8020820,
	            0x80000019: 0x8000020,
	            0x8000001a: 0x8000800,
	            0x8000001b: 0x0,
	            0x8000001c: 0x20800,
	            0x8000001d: 0x820,
	            0x8000001e: 0x20020,
	            0x8000001f: 0x8020800
	        }
	    ];

	    // Masks that select the SBOX input
	    var SBOX_MASK = [
	        0xf8000001, 0x1f800000, 0x01f80000, 0x001f8000,
	        0x0001f800, 0x00001f80, 0x000001f8, 0x8000001f
	    ];

	    /**
	     * DES block cipher algorithm.
	     */
	    var DES = C_algo.DES = BlockCipher.extend({
	        _doReset: function () {
	            // Shortcuts
	            var key = this._key;
	            var keyWords = key.words;

	            // Select 56 bits according to PC1
	            var keyBits = [];
	            for (var i = 0; i < 56; i++) {
	                var keyBitPos = PC1[i] - 1;
	                keyBits[i] = (keyWords[keyBitPos >>> 5] >>> (31 - keyBitPos % 32)) & 1;
	            }

	            // Assemble 16 subkeys
	            var subKeys = this._subKeys = [];
	            for (var nSubKey = 0; nSubKey < 16; nSubKey++) {
	                // Create subkey
	                var subKey = subKeys[nSubKey] = [];

	                // Shortcut
	                var bitShift = BIT_SHIFTS[nSubKey];

	                // Select 48 bits according to PC2
	                for (var i = 0; i < 24; i++) {
	                    // Select from the left 28 key bits
	                    subKey[(i / 6) | 0] |= keyBits[((PC2[i] - 1) + bitShift) % 28] << (31 - i % 6);

	                    // Select from the right 28 key bits
	                    subKey[4 + ((i / 6) | 0)] |= keyBits[28 + (((PC2[i + 24] - 1) + bitShift) % 28)] << (31 - i % 6);
	                }

	                // Since each subkey is applied to an expanded 32-bit input,
	                // the subkey can be broken into 8 values scaled to 32-bits,
	                // which allows the key to be used without expansion
	                subKey[0] = (subKey[0] << 1) | (subKey[0] >>> 31);
	                for (var i = 1; i < 7; i++) {
	                    subKey[i] = subKey[i] >>> ((i - 1) * 4 + 3);
	                }
	                subKey[7] = (subKey[7] << 5) | (subKey[7] >>> 27);
	            }

	            // Compute inverse subkeys
	            var invSubKeys = this._invSubKeys = [];
	            for (var i = 0; i < 16; i++) {
	                invSubKeys[i] = subKeys[15 - i];
	            }
	        },

	        encryptBlock: function (M, offset) {
	            this._doCryptBlock(M, offset, this._subKeys);
	        },

	        decryptBlock: function (M, offset) {
	            this._doCryptBlock(M, offset, this._invSubKeys);
	        },

	        _doCryptBlock: function (M, offset, subKeys) {
	            // Get input
	            this._lBlock = M[offset];
	            this._rBlock = M[offset + 1];

	            // Initial permutation
	            exchangeLR.call(this, 4,  0x0f0f0f0f);
	            exchangeLR.call(this, 16, 0x0000ffff);
	            exchangeRL.call(this, 2,  0x33333333);
	            exchangeRL.call(this, 8,  0x00ff00ff);
	            exchangeLR.call(this, 1,  0x55555555);

	            // Rounds
	            for (var round = 0; round < 16; round++) {
	                // Shortcuts
	                var subKey = subKeys[round];
	                var lBlock = this._lBlock;
	                var rBlock = this._rBlock;

	                // Feistel function
	                var f = 0;
	                for (var i = 0; i < 8; i++) {
	                    f |= SBOX_P[i][((rBlock ^ subKey[i]) & SBOX_MASK[i]) >>> 0];
	                }
	                this._lBlock = rBlock;
	                this._rBlock = lBlock ^ f;
	            }

	            // Undo swap from last round
	            var t = this._lBlock;
	            this._lBlock = this._rBlock;
	            this._rBlock = t;

	            // Final permutation
	            exchangeLR.call(this, 1,  0x55555555);
	            exchangeRL.call(this, 8,  0x00ff00ff);
	            exchangeRL.call(this, 2,  0x33333333);
	            exchangeLR.call(this, 16, 0x0000ffff);
	            exchangeLR.call(this, 4,  0x0f0f0f0f);

	            // Set output
	            M[offset] = this._lBlock;
	            M[offset + 1] = this._rBlock;
	        },

	        keySize: 64/32,

	        ivSize: 64/32,

	        blockSize: 64/32
	    });

	    // Swap bits across the left and right words
	    function exchangeLR(offset, mask) {
	        var t = ((this._lBlock >>> offset) ^ this._rBlock) & mask;
	        this._rBlock ^= t;
	        this._lBlock ^= t << offset;
	    }

	    function exchangeRL(offset, mask) {
	        var t = ((this._rBlock >>> offset) ^ this._lBlock) & mask;
	        this._lBlock ^= t;
	        this._rBlock ^= t << offset;
	    }

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.DES.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.DES.decrypt(ciphertext, key, cfg);
	     */
	    C.DES = BlockCipher._createHelper(DES);

	    /**
	     * Triple-DES block cipher algorithm.
	     */
	    var TripleDES = C_algo.TripleDES = BlockCipher.extend({
	        _doReset: function () {
	            // Shortcuts
	            var key = this._key;
	            var keyWords = key.words;

	            // Create DES instances
	            this._des1 = DES.createEncryptor(WordArray.create(keyWords.slice(0, 2)));
	            this._des2 = DES.createEncryptor(WordArray.create(keyWords.slice(2, 4)));
	            this._des3 = DES.createEncryptor(WordArray.create(keyWords.slice(4, 6)));
	        },

	        encryptBlock: function (M, offset) {
	            this._des1.encryptBlock(M, offset);
	            this._des2.decryptBlock(M, offset);
	            this._des3.encryptBlock(M, offset);
	        },

	        decryptBlock: function (M, offset) {
	            this._des3.decryptBlock(M, offset);
	            this._des2.encryptBlock(M, offset);
	            this._des1.decryptBlock(M, offset);
	        },

	        keySize: 192/32,

	        ivSize: 64/32,

	        blockSize: 64/32
	    });

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.TripleDES.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.TripleDES.decrypt(ciphertext, key, cfg);
	     */
	    C.TripleDES = BlockCipher._createHelper(TripleDES);
	}());


	return CryptoJS.TripleDES;

}));

/***/ }),
/* 54 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(8), __webpack_require__(9), __webpack_require__(6), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./enc-base64", "./md5", "./evpkdf", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var StreamCipher = C_lib.StreamCipher;
	    var C_algo = C.algo;

	    /**
	     * RC4 stream cipher algorithm.
	     */
	    var RC4 = C_algo.RC4 = StreamCipher.extend({
	        _doReset: function () {
	            // Shortcuts
	            var key = this._key;
	            var keyWords = key.words;
	            var keySigBytes = key.sigBytes;

	            // Init sbox
	            var S = this._S = [];
	            for (var i = 0; i < 256; i++) {
	                S[i] = i;
	            }

	            // Key setup
	            for (var i = 0, j = 0; i < 256; i++) {
	                var keyByteIndex = i % keySigBytes;
	                var keyByte = (keyWords[keyByteIndex >>> 2] >>> (24 - (keyByteIndex % 4) * 8)) & 0xff;

	                j = (j + S[i] + keyByte) % 256;

	                // Swap
	                var t = S[i];
	                S[i] = S[j];
	                S[j] = t;
	            }

	            // Counters
	            this._i = this._j = 0;
	        },

	        _doProcessBlock: function (M, offset) {
	            M[offset] ^= generateKeystreamWord.call(this);
	        },

	        keySize: 256/32,

	        ivSize: 0
	    });

	    function generateKeystreamWord() {
	        // Shortcuts
	        var S = this._S;
	        var i = this._i;
	        var j = this._j;

	        // Generate keystream word
	        var keystreamWord = 0;
	        for (var n = 0; n < 4; n++) {
	            i = (i + 1) % 256;
	            j = (j + S[i]) % 256;

	            // Swap
	            var t = S[i];
	            S[i] = S[j];
	            S[j] = t;

	            keystreamWord |= S[(S[i] + S[j]) % 256] << (24 - n * 8);
	        }

	        // Update counters
	        this._i = i;
	        this._j = j;

	        return keystreamWord;
	    }

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.RC4.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.RC4.decrypt(ciphertext, key, cfg);
	     */
	    C.RC4 = StreamCipher._createHelper(RC4);

	    /**
	     * Modified RC4 stream cipher algorithm.
	     */
	    var RC4Drop = C_algo.RC4Drop = RC4.extend({
	        /**
	         * Configuration options.
	         *
	         * @property {number} drop The number of keystream words to drop. Default 192
	         */
	        cfg: RC4.cfg.extend({
	            drop: 192
	        }),

	        _doReset: function () {
	            RC4._doReset.call(this);

	            // Drop
	            for (var i = this.cfg.drop; i > 0; i--) {
	                generateKeystreamWord.call(this);
	            }
	        }
	    });

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.RC4Drop.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.RC4Drop.decrypt(ciphertext, key, cfg);
	     */
	    C.RC4Drop = StreamCipher._createHelper(RC4Drop);
	}());


	return CryptoJS.RC4;

}));

/***/ }),
/* 55 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(8), __webpack_require__(9), __webpack_require__(6), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./enc-base64", "./md5", "./evpkdf", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var StreamCipher = C_lib.StreamCipher;
	    var C_algo = C.algo;

	    // Reusable objects
	    var S  = [];
	    var C_ = [];
	    var G  = [];

	    /**
	     * Rabbit stream cipher algorithm
	     */
	    var Rabbit = C_algo.Rabbit = StreamCipher.extend({
	        _doReset: function () {
	            // Shortcuts
	            var K = this._key.words;
	            var iv = this.cfg.iv;

	            // Swap endian
	            for (var i = 0; i < 4; i++) {
	                K[i] = (((K[i] << 8)  | (K[i] >>> 24)) & 0x00ff00ff) |
	                       (((K[i] << 24) | (K[i] >>> 8))  & 0xff00ff00);
	            }

	            // Generate initial state values
	            var X = this._X = [
	                K[0], (K[3] << 16) | (K[2] >>> 16),
	                K[1], (K[0] << 16) | (K[3] >>> 16),
	                K[2], (K[1] << 16) | (K[0] >>> 16),
	                K[3], (K[2] << 16) | (K[1] >>> 16)
	            ];

	            // Generate initial counter values
	            var C = this._C = [
	                (K[2] << 16) | (K[2] >>> 16), (K[0] & 0xffff0000) | (K[1] & 0x0000ffff),
	                (K[3] << 16) | (K[3] >>> 16), (K[1] & 0xffff0000) | (K[2] & 0x0000ffff),
	                (K[0] << 16) | (K[0] >>> 16), (K[2] & 0xffff0000) | (K[3] & 0x0000ffff),
	                (K[1] << 16) | (K[1] >>> 16), (K[3] & 0xffff0000) | (K[0] & 0x0000ffff)
	            ];

	            // Carry bit
	            this._b = 0;

	            // Iterate the system four times
	            for (var i = 0; i < 4; i++) {
	                nextState.call(this);
	            }

	            // Modify the counters
	            for (var i = 0; i < 8; i++) {
	                C[i] ^= X[(i + 4) & 7];
	            }

	            // IV setup
	            if (iv) {
	                // Shortcuts
	                var IV = iv.words;
	                var IV_0 = IV[0];
	                var IV_1 = IV[1];

	                // Generate four subvectors
	                var i0 = (((IV_0 << 8) | (IV_0 >>> 24)) & 0x00ff00ff) | (((IV_0 << 24) | (IV_0 >>> 8)) & 0xff00ff00);
	                var i2 = (((IV_1 << 8) | (IV_1 >>> 24)) & 0x00ff00ff) | (((IV_1 << 24) | (IV_1 >>> 8)) & 0xff00ff00);
	                var i1 = (i0 >>> 16) | (i2 & 0xffff0000);
	                var i3 = (i2 << 16)  | (i0 & 0x0000ffff);

	                // Modify counter values
	                C[0] ^= i0;
	                C[1] ^= i1;
	                C[2] ^= i2;
	                C[3] ^= i3;
	                C[4] ^= i0;
	                C[5] ^= i1;
	                C[6] ^= i2;
	                C[7] ^= i3;

	                // Iterate the system four times
	                for (var i = 0; i < 4; i++) {
	                    nextState.call(this);
	                }
	            }
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcut
	            var X = this._X;

	            // Iterate the system
	            nextState.call(this);

	            // Generate four keystream words
	            S[0] = X[0] ^ (X[5] >>> 16) ^ (X[3] << 16);
	            S[1] = X[2] ^ (X[7] >>> 16) ^ (X[5] << 16);
	            S[2] = X[4] ^ (X[1] >>> 16) ^ (X[7] << 16);
	            S[3] = X[6] ^ (X[3] >>> 16) ^ (X[1] << 16);

	            for (var i = 0; i < 4; i++) {
	                // Swap endian
	                S[i] = (((S[i] << 8)  | (S[i] >>> 24)) & 0x00ff00ff) |
	                       (((S[i] << 24) | (S[i] >>> 8))  & 0xff00ff00);

	                // Encrypt
	                M[offset + i] ^= S[i];
	            }
	        },

	        blockSize: 128/32,

	        ivSize: 64/32
	    });

	    function nextState() {
	        // Shortcuts
	        var X = this._X;
	        var C = this._C;

	        // Save old counter values
	        for (var i = 0; i < 8; i++) {
	            C_[i] = C[i];
	        }

	        // Calculate new counter values
	        C[0] = (C[0] + 0x4d34d34d + this._b) | 0;
	        C[1] = (C[1] + 0xd34d34d3 + ((C[0] >>> 0) < (C_[0] >>> 0) ? 1 : 0)) | 0;
	        C[2] = (C[2] + 0x34d34d34 + ((C[1] >>> 0) < (C_[1] >>> 0) ? 1 : 0)) | 0;
	        C[3] = (C[3] + 0x4d34d34d + ((C[2] >>> 0) < (C_[2] >>> 0) ? 1 : 0)) | 0;
	        C[4] = (C[4] + 0xd34d34d3 + ((C[3] >>> 0) < (C_[3] >>> 0) ? 1 : 0)) | 0;
	        C[5] = (C[5] + 0x34d34d34 + ((C[4] >>> 0) < (C_[4] >>> 0) ? 1 : 0)) | 0;
	        C[6] = (C[6] + 0x4d34d34d + ((C[5] >>> 0) < (C_[5] >>> 0) ? 1 : 0)) | 0;
	        C[7] = (C[7] + 0xd34d34d3 + ((C[6] >>> 0) < (C_[6] >>> 0) ? 1 : 0)) | 0;
	        this._b = (C[7] >>> 0) < (C_[7] >>> 0) ? 1 : 0;

	        // Calculate the g-values
	        for (var i = 0; i < 8; i++) {
	            var gx = X[i] + C[i];

	            // Construct high and low argument for squaring
	            var ga = gx & 0xffff;
	            var gb = gx >>> 16;

	            // Calculate high and low result of squaring
	            var gh = ((((ga * ga) >>> 17) + ga * gb) >>> 15) + gb * gb;
	            var gl = (((gx & 0xffff0000) * gx) | 0) + (((gx & 0x0000ffff) * gx) | 0);

	            // High XOR low
	            G[i] = gh ^ gl;
	        }

	        // Calculate new state values
	        X[0] = (G[0] + ((G[7] << 16) | (G[7] >>> 16)) + ((G[6] << 16) | (G[6] >>> 16))) | 0;
	        X[1] = (G[1] + ((G[0] << 8)  | (G[0] >>> 24)) + G[7]) | 0;
	        X[2] = (G[2] + ((G[1] << 16) | (G[1] >>> 16)) + ((G[0] << 16) | (G[0] >>> 16))) | 0;
	        X[3] = (G[3] + ((G[2] << 8)  | (G[2] >>> 24)) + G[1]) | 0;
	        X[4] = (G[4] + ((G[3] << 16) | (G[3] >>> 16)) + ((G[2] << 16) | (G[2] >>> 16))) | 0;
	        X[5] = (G[5] + ((G[4] << 8)  | (G[4] >>> 24)) + G[3]) | 0;
	        X[6] = (G[6] + ((G[5] << 16) | (G[5] >>> 16)) + ((G[4] << 16) | (G[4] >>> 16))) | 0;
	        X[7] = (G[7] + ((G[6] << 8)  | (G[6] >>> 24)) + G[5]) | 0;
	    }

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.Rabbit.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.Rabbit.decrypt(ciphertext, key, cfg);
	     */
	    C.Rabbit = StreamCipher._createHelper(Rabbit);
	}());


	return CryptoJS.Rabbit;

}));

/***/ }),
/* 56 */
/***/ (function(module, exports, __webpack_require__) {

;(function (root, factory, undef) {
	if (true) {
		// CommonJS
		module.exports = exports = factory(__webpack_require__(0), __webpack_require__(8), __webpack_require__(9), __webpack_require__(6), __webpack_require__(1));
	}
	else if (typeof define === "function" && define.amd) {
		// AMD
		define(["./core", "./enc-base64", "./md5", "./evpkdf", "./cipher-core"], factory);
	}
	else {
		// Global (browser)
		factory(root.CryptoJS);
	}
}(this, function (CryptoJS) {

	(function () {
	    // Shortcuts
	    var C = CryptoJS;
	    var C_lib = C.lib;
	    var StreamCipher = C_lib.StreamCipher;
	    var C_algo = C.algo;

	    // Reusable objects
	    var S  = [];
	    var C_ = [];
	    var G  = [];

	    /**
	     * Rabbit stream cipher algorithm.
	     *
	     * This is a legacy version that neglected to convert the key to little-endian.
	     * This error doesn't affect the cipher's security,
	     * but it does affect its compatibility with other implementations.
	     */
	    var RabbitLegacy = C_algo.RabbitLegacy = StreamCipher.extend({
	        _doReset: function () {
	            // Shortcuts
	            var K = this._key.words;
	            var iv = this.cfg.iv;

	            // Generate initial state values
	            var X = this._X = [
	                K[0], (K[3] << 16) | (K[2] >>> 16),
	                K[1], (K[0] << 16) | (K[3] >>> 16),
	                K[2], (K[1] << 16) | (K[0] >>> 16),
	                K[3], (K[2] << 16) | (K[1] >>> 16)
	            ];

	            // Generate initial counter values
	            var C = this._C = [
	                (K[2] << 16) | (K[2] >>> 16), (K[0] & 0xffff0000) | (K[1] & 0x0000ffff),
	                (K[3] << 16) | (K[3] >>> 16), (K[1] & 0xffff0000) | (K[2] & 0x0000ffff),
	                (K[0] << 16) | (K[0] >>> 16), (K[2] & 0xffff0000) | (K[3] & 0x0000ffff),
	                (K[1] << 16) | (K[1] >>> 16), (K[3] & 0xffff0000) | (K[0] & 0x0000ffff)
	            ];

	            // Carry bit
	            this._b = 0;

	            // Iterate the system four times
	            for (var i = 0; i < 4; i++) {
	                nextState.call(this);
	            }

	            // Modify the counters
	            for (var i = 0; i < 8; i++) {
	                C[i] ^= X[(i + 4) & 7];
	            }

	            // IV setup
	            if (iv) {
	                // Shortcuts
	                var IV = iv.words;
	                var IV_0 = IV[0];
	                var IV_1 = IV[1];

	                // Generate four subvectors
	                var i0 = (((IV_0 << 8) | (IV_0 >>> 24)) & 0x00ff00ff) | (((IV_0 << 24) | (IV_0 >>> 8)) & 0xff00ff00);
	                var i2 = (((IV_1 << 8) | (IV_1 >>> 24)) & 0x00ff00ff) | (((IV_1 << 24) | (IV_1 >>> 8)) & 0xff00ff00);
	                var i1 = (i0 >>> 16) | (i2 & 0xffff0000);
	                var i3 = (i2 << 16)  | (i0 & 0x0000ffff);

	                // Modify counter values
	                C[0] ^= i0;
	                C[1] ^= i1;
	                C[2] ^= i2;
	                C[3] ^= i3;
	                C[4] ^= i0;
	                C[5] ^= i1;
	                C[6] ^= i2;
	                C[7] ^= i3;

	                // Iterate the system four times
	                for (var i = 0; i < 4; i++) {
	                    nextState.call(this);
	                }
	            }
	        },

	        _doProcessBlock: function (M, offset) {
	            // Shortcut
	            var X = this._X;

	            // Iterate the system
	            nextState.call(this);

	            // Generate four keystream words
	            S[0] = X[0] ^ (X[5] >>> 16) ^ (X[3] << 16);
	            S[1] = X[2] ^ (X[7] >>> 16) ^ (X[5] << 16);
	            S[2] = X[4] ^ (X[1] >>> 16) ^ (X[7] << 16);
	            S[3] = X[6] ^ (X[3] >>> 16) ^ (X[1] << 16);

	            for (var i = 0; i < 4; i++) {
	                // Swap endian
	                S[i] = (((S[i] << 8)  | (S[i] >>> 24)) & 0x00ff00ff) |
	                       (((S[i] << 24) | (S[i] >>> 8))  & 0xff00ff00);

	                // Encrypt
	                M[offset + i] ^= S[i];
	            }
	        },

	        blockSize: 128/32,

	        ivSize: 64/32
	    });

	    function nextState() {
	        // Shortcuts
	        var X = this._X;
	        var C = this._C;

	        // Save old counter values
	        for (var i = 0; i < 8; i++) {
	            C_[i] = C[i];
	        }

	        // Calculate new counter values
	        C[0] = (C[0] + 0x4d34d34d + this._b) | 0;
	        C[1] = (C[1] + 0xd34d34d3 + ((C[0] >>> 0) < (C_[0] >>> 0) ? 1 : 0)) | 0;
	        C[2] = (C[2] + 0x34d34d34 + ((C[1] >>> 0) < (C_[1] >>> 0) ? 1 : 0)) | 0;
	        C[3] = (C[3] + 0x4d34d34d + ((C[2] >>> 0) < (C_[2] >>> 0) ? 1 : 0)) | 0;
	        C[4] = (C[4] + 0xd34d34d3 + ((C[3] >>> 0) < (C_[3] >>> 0) ? 1 : 0)) | 0;
	        C[5] = (C[5] + 0x34d34d34 + ((C[4] >>> 0) < (C_[4] >>> 0) ? 1 : 0)) | 0;
	        C[6] = (C[6] + 0x4d34d34d + ((C[5] >>> 0) < (C_[5] >>> 0) ? 1 : 0)) | 0;
	        C[7] = (C[7] + 0xd34d34d3 + ((C[6] >>> 0) < (C_[6] >>> 0) ? 1 : 0)) | 0;
	        this._b = (C[7] >>> 0) < (C_[7] >>> 0) ? 1 : 0;

	        // Calculate the g-values
	        for (var i = 0; i < 8; i++) {
	            var gx = X[i] + C[i];

	            // Construct high and low argument for squaring
	            var ga = gx & 0xffff;
	            var gb = gx >>> 16;

	            // Calculate high and low result of squaring
	            var gh = ((((ga * ga) >>> 17) + ga * gb) >>> 15) + gb * gb;
	            var gl = (((gx & 0xffff0000) * gx) | 0) + (((gx & 0x0000ffff) * gx) | 0);

	            // High XOR low
	            G[i] = gh ^ gl;
	        }

	        // Calculate new state values
	        X[0] = (G[0] + ((G[7] << 16) | (G[7] >>> 16)) + ((G[6] << 16) | (G[6] >>> 16))) | 0;
	        X[1] = (G[1] + ((G[0] << 8)  | (G[0] >>> 24)) + G[7]) | 0;
	        X[2] = (G[2] + ((G[1] << 16) | (G[1] >>> 16)) + ((G[0] << 16) | (G[0] >>> 16))) | 0;
	        X[3] = (G[3] + ((G[2] << 8)  | (G[2] >>> 24)) + G[1]) | 0;
	        X[4] = (G[4] + ((G[3] << 16) | (G[3] >>> 16)) + ((G[2] << 16) | (G[2] >>> 16))) | 0;
	        X[5] = (G[5] + ((G[4] << 8)  | (G[4] >>> 24)) + G[3]) | 0;
	        X[6] = (G[6] + ((G[5] << 16) | (G[5] >>> 16)) + ((G[4] << 16) | (G[4] >>> 16))) | 0;
	        X[7] = (G[7] + ((G[6] << 8)  | (G[6] >>> 24)) + G[5]) | 0;
	    }

	    /**
	     * Shortcut functions to the cipher's object interface.
	     *
	     * @example
	     *
	     *     var ciphertext = CryptoJS.RabbitLegacy.encrypt(message, key, cfg);
	     *     var plaintext  = CryptoJS.RabbitLegacy.decrypt(ciphertext, key, cfg);
	     */
	    C.RabbitLegacy = StreamCipher._createHelper(RabbitLegacy);
	}());


	return CryptoJS.RabbitLegacy;

}));

/***/ }),
/* 57 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(126);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_5.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_5.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 58 */
/***/ (function(module, exports, __webpack_require__) {

var Util = __webpack_require__(5)

var SQHttp = {
  jsonp: function (url, params, callback) {
    var callbackName = 'jsonp_callback_' + Math.round(100000 * Math.random())
    params.callback = callbackName

    if (typeof params == 'object') {
      params = Util.buildQuery(params)
    }
    url += '?' + params
    this.addDom(url, callbackName, callback)
  },

  // 加密请求
  jsonpEncrypt: function (url, params, callback, key) {
    var callbackName = 'jsonp_callback_' + Math.round(100000 * Math.random())
    params.callback = callbackName

    if (typeof params === 'object') {
      params = Util.buildQuery(params)
    }
    if (key !== '') {
      params = Util.encrypt(params, key)
    }
    url += '?' + params

    this.addDom(url, callbackName, callback)
  },

  addDom: function (url, callbackName, callback) {
    window[callbackName] = function (data) {
      delete window[callbackName]
      document.body.removeChild(script)
      callback(data)
    }

    var script = document.createElement('script')
    script.src = url
    document.body.appendChild(script)
  }
}

module.exports = SQHttp

/***/ }),
/* 59 */
/***/ (function(module, exports, __webpack_require__) {


/**
 * 文字公告
 * @desc 仅作公告弹窗显示
 */
var Util = __webpack_require__(5);
var notification = {
  type:false,
  dom: {
    staticNotice: document.getElementById('staticNotice') || '',
    mask: document.getElementById('mask') || '',
    notify: document.getElementById('notify') || '',
    urlDom: document.getElementById('noticeUrl') || '', //链接公告
    imgDom: document.getElementById('noticeImg') || '',
    closeButton: document.getElementById('closeNotice') || '',
  },

  init: function (Url) {
    // 先判断是否是链接 再判断是图片链接 还是公告链接
    var Expression = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
    var objExp = new RegExp(Expression);
    if (objExp.test(Url)) {   //是链接
      var that=this
      Util.isImg(Url).then(function(res){
      if (res) {   //是图片链接
        that.dom.imgDom.style.display = 'block';
        that.dom.urlDom.style.display = 'none';
        that.type = true;
        that.initNotification(Url);
      } else { //是链接 html
        that.dom.imgDom.style.display = 'none';
        that.dom.urlDom.style.display = 'block';
           that.type  = false;
        that.initNotification(Url);
      }
      })
      window.addEventListener('message', function (event) {
        console.log('iframe收到调用' + event.data)
        var obj = {}
        if (typeof event.data === 'string') {
          obj = Util.urlStringToObj(event.data)
        }
        if (obj.type === 'close') {
          staticNotice.style.display = 'none';
        }
      })
    } else {
      //非链接
      console.error('非链接');
    }
  },

  initNotification: function (Url) {
    if (this.dom.staticNotice) {
      if (this.dom.imgDom) {
        this.setCSS();
        this.setImgUrl(Url);
        /* 绑定手机弹框处理 */
        if(Url.includes('/wp/sdk/games/index.html')){
          var iframeDom = this.dom
          window.addEventListener('message',function(e){
            console.log('绑定手机弹框:',e);
            var message = e.data
            var origin = e.origin
            /* 关闭弹框 */
            if(message.action==='IFRAME_CLOSE' && origin.includes('37nqy.com')){
              iframeDom.urlDom.removeAttribute('src')
              iframeDom.urlDom.style.display = 'none'
              iframeDom.staticNotice.style.display = 'none'
            }
          })
        }
      }
      this.dom.closeButton.onclick = function () {
        staticNotice.style.display = 'none';
      };
      this.dom.mask.onclick = function () {
        staticNotice.style.display = 'none';
      };
    } else return;
  },

  setCSS: function () {
    var staticNoticeCSS ='position: fixed; width: 100%; height: 100%;z-index: 3;';
    var  maskCSS = 'position: fixed; width: 100%; height: 100%;z-index: -1;background: rgba(0,0,0,0.5)';
    var notifyCSS ='position:absolute;width: 100%; height: 100%;z-index: 2;overflow: hidden;top:0;left: 0;';
    var DomCSS = 'position:absolute;width:100%;height:100%;object-fit: contain;';
    var closeButtonCSS = 'position: absolute;width: 1.2rem;height:1.2rem; right: 0;right: 0;z-index: 2;background: url(https://imgcs.s98s2.com/aicc/imgs/1730169709538.png) no-repeat;background-size: contain;';
    this.dom.staticNotice.style.cssText = staticNoticeCSS;
    this.dom.mask.style.cssText = maskCSS;
    this.dom.notify.style.cssText = notifyCSS;
    this.type ?this.dom.imgDom.style.cssText = DomCSS:''
    this.dom.closeButton.style.cssText = closeButtonCSS;
  },
  // 设置url
  setImgUrl: function (Url) {
    this.dom.staticNotice.style.display = 'block';
    this.type ?this.dom.imgDom.setAttribute('src', Url):this.dom.urlDom.setAttribute('src', Url);
    if(Url.includes('/wp/sdk/games/index.html')){
      var notifyCSS ='position:absolute;width: 100%; height: 100%;z-index: 2;overflow: hidden;';
      this.dom.notify.style.cssText=notifyCSS
      this.dom.mask.style.display = 'none';
    }
  }
};

module.exports = notification;


/***/ }),
/* 60 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 游戏框
 */

var Global = __webpack_require__(7)
var $ = __webpack_require__(63)

var GameFrame = {
  gameWrapperId: 'gameWrapper',
  gameFrameId: 'gameFrame',
  gameFrameElement: null,
  gameWrapperElement: null,
  // 兼容无iframe模式
  useIframe: true,

  /**
   * 初始化游戏框
   * @param useIframe 是否使用
   */
  init: function (useIframe) {
    if (useIframe === false) {
      this.useIframe = useIframe
    }
    this.gameWrapperElement = document.getElementById(this.gameWrapperId)
    this.initFrameContent()
    // 充值屏蔽处理
    this.checkPay()
  },

  show: function (src) {
    if (this.useIframe) {
      this.gameFrameElement.src = src
    } else {
      this.gameFrameElement.data = src
    }
  },
  getFrame: function () {
    return this.gameFrameElement.contentWindow
  },

  initFrameContent: function () {
    var html = ''
    if (this.useIframe) {
      html = '<iframe id="' + this.gameFrameId + '" style="width: 100%;height:100%;border:none;" src="" ></iframe>'
    } else {
      // 兼容无iframe模式
      html = '<object id="' + this.gameFrameId + '" style="width:100%;height:100%" type="text/html" data=""></object>'
    }
    this.gameWrapperElement.innerHTML = html
    this.gameFrameElement = document.getElementById(this.gameFrameId)
  },

  checkPay: function () {
    // 如果有设置 _mask 字段，且 != 1，则不增加遮罩层
    if(Global.urlInfo.searchObject._mask && Global.urlInfo.searchObject._mask == 0){
      return false;
    }
    // 屏蔽充值按钮
    if (Global.game.pon != 1) {
      var div = document.createElement('div')
      div.onclick = function () {
        return false
      }
      div.className = 'paymask'
      var div2 = document.createElement('div')
      div2.onclick = function () {
        return false
      }
      div2.className = 'paymask2'
      document.body.appendChild(div)
      document.body.appendChild(div2)
    }
  }
}

module.exports = GameFrame

/***/ }),
/* 61 */,
/* 62 */
/***/ (function(module, exports) {

/**
 * 简单的消息系统
 */
var Event = {
  events: {},
  // 异步队列-条件具备自动调用
  queues:{},
  /**
   * 广播事件
   * @param type 事件类型
   */
  broadcast: function (type) {
    var listeners = this.events[type]
    if (!listeners) {
      return
    }
    var context = this
    var args = Array.prototype.slice.call(arguments, 1)
    for (var i = 0; i < listeners.length; i++) {
      debounce(listeners[i])
    }

    // 可异步运行
    function debounce (e) {
      setTimeout(function () {
        e.apply(context, args)
      }, 0)
    }
  },

  /**
   * bind event to a listener , and will run asynchronous
   * @param event
   */
  bind: function (event, listener) {
    if (typeof this.events[event] === 'undefined') {
      this.events[event] = []
    }

    this.events[event].push(listener)
  },

  /**
   * remove events
   * @param event
   * @param fn
   */
  remove: function (event, fn) {
    var c = arguments.length
    if (c === 1) {
      delete this.events[type]
    }
    else if (c === 0) {
      this.events = {}
    }
    else {
      // specify a listener
      var listeners = this.events[type]
      if (listeners) {
        listeners.splice(listeners.indexOf(fn), 1)
      }
    }
  },
}

module.exports = Event

/***/ }),
/* 63 */
/***/ (function(module, exports) {

/* Zepto v1.1.6 - zepto event ajax form ie - zeptojs.com/license */

var Zepto = module.exports = (function() {
  var undefined, key, $, classList, emptyArray = [], slice = emptyArray.slice, filter = emptyArray.filter,
    document = window.document,
    elementDisplay = {}, classCache = {},
    cssNumber = { 'column-count': 1, 'columns': 1, 'font-weight': 1, 'line-height': 1,'opacity': 1, 'z-index': 1, 'zoom': 1 },
    fragmentRE = /^\s*<(\w+|!)[^>]*>/,
    singleTagRE = /^<(\w+)\s*\/?>(?:<\/\1>|)$/,
    tagExpanderRE = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,
    rootNodeRE = /^(?:body|html)$/i,
    capitalRE = /([A-Z])/g,

    // special attributes that should be get/set via method calls
    methodAttributes = ['val', 'css', 'html', 'text', 'data', 'width', 'height', 'offset'],

    adjacencyOperators = [ 'after', 'prepend', 'before', 'append' ],
    table = document.createElement('table'),
    tableRow = document.createElement('tr'),
    containers = {
      'tr': document.createElement('tbody'),
      'tbody': table, 'thead': table, 'tfoot': table,
      'td': tableRow, 'th': tableRow,
      '*': document.createElement('div')
    },
    readyRE = /complete|loaded|interactive/,
    simpleSelectorRE = /^[\w-]*$/,
    class2type = {},
    toString = class2type.toString,
    zepto = {},
    camelize, uniq,
    tempParent = document.createElement('div'),
    propMap = {
      'tabindex': 'tabIndex',
      'readonly': 'readOnly',
      'for': 'htmlFor',
      'class': 'className',
      'maxlength': 'maxLength',
      'cellspacing': 'cellSpacing',
      'cellpadding': 'cellPadding',
      'rowspan': 'rowSpan',
      'colspan': 'colSpan',
      'usemap': 'useMap',
      'frameborder': 'frameBorder',
      'contenteditable': 'contentEditable'
    },
    isArray = Array.isArray ||
      function(object){ return object instanceof Array }

  zepto.matches = function(element, selector) {
    if (!selector || !element || element.nodeType !== 1) return false
    var matchesSelector = element.webkitMatchesSelector || element.mozMatchesSelector ||
                          element.oMatchesSelector || element.matchesSelector
    if (matchesSelector) return matchesSelector.call(element, selector)
    // fall back to performing a selector:
    var match, parent = element.parentNode, temp = !parent
    if (temp) (parent = tempParent).appendChild(element)
    match = ~zepto.qsa(parent, selector).indexOf(element)
    temp && tempParent.removeChild(element)
    return match
  }

  function type(obj) {
    return obj == null ? String(obj) :
      class2type[toString.call(obj)] || "object"
  }

  function isFunction(value) { return type(value) == "function" }
  function isWindow(obj)     { return obj != null && obj == obj.window }
  function isDocument(obj)   { return obj != null && obj.nodeType == obj.DOCUMENT_NODE }
  function isObject(obj)     { return type(obj) == "object" }
  function isPlainObject(obj) {
    return isObject(obj) && !isWindow(obj) && Object.getPrototypeOf(obj) == Object.prototype
  }
  function likeArray(obj) { return typeof obj.length == 'number' }

  function compact(array) { return filter.call(array, function(item){ return item != null }) }
  function flatten(array) { return array.length > 0 ? $.fn.concat.apply([], array) : array }
  camelize = function(str){ return str.replace(/-+(.)?/g, function(match, chr){ return chr ? chr.toUpperCase() : '' }) }
  function dasherize(str) {
    return str.replace(/::/g, '/')
           .replace(/([A-Z]+)([A-Z][a-z])/g, '$1_$2')
           .replace(/([a-z\d])([A-Z])/g, '$1_$2')
           .replace(/_/g, '-')
           .toLowerCase()
  }
  uniq = function(array){ return filter.call(array, function(item, idx){ return array.indexOf(item) == idx }) }

  function classRE(name) {
    return name in classCache ?
      classCache[name] : (classCache[name] = new RegExp('(^|\\s)' + name + '(\\s|$)'))
  }

  function maybeAddPx(name, value) {
    return (typeof value == "number" && !cssNumber[dasherize(name)]) ? value + "px" : value
  }

  function defaultDisplay(nodeName) {
    var element, display
    if (!elementDisplay[nodeName]) {
      element = document.createElement(nodeName)
      document.body.appendChild(element)
      display = getComputedStyle(element, '').getPropertyValue("display")
      element.parentNode.removeChild(element)
      display == "none" && (display = "block")
      elementDisplay[nodeName] = display
    }
    return elementDisplay[nodeName]
  }

  function children(element) {
    return 'children' in element ?
      slice.call(element.children) :
      $.map(element.childNodes, function(node){ if (node.nodeType == 1) return node })
  }

  // `$.zepto.fragment` takes a html string and an optional tag name
  // to generate DOM nodes nodes from the given html string.
  // The generated DOM nodes are returned as an array.
  // This function can be overriden in plugins for example to make
  // it compatible with browsers that don't support the DOM fully.
  zepto.fragment = function(html, name, properties) {
    var dom, nodes, container

    // A special case optimization for a single tag
    if (singleTagRE.test(html)) dom = $(document.createElement(RegExp.$1))

    if (!dom) {
      if (html.replace) html = html.replace(tagExpanderRE, "<$1></$2>")
      if (name === undefined) name = fragmentRE.test(html) && RegExp.$1
      if (!(name in containers)) name = '*'

      container = containers[name]
      container.innerHTML = '' + html
      dom = $.each(slice.call(container.childNodes), function(){
        container.removeChild(this)
      })
    }

    if (isPlainObject(properties)) {
      nodes = $(dom)
      $.each(properties, function(key, value) {
        if (methodAttributes.indexOf(key) > -1) nodes[key](value)
        else nodes.attr(key, value)
      })
    }

    return dom
  }

  // `$.zepto.Z` swaps out the prototype of the given `dom` array
  // of nodes with `$.fn` and thus supplying all the Zepto functions
  // to the array. Note that `__proto__` is not supported on Internet
  // Explorer. This method can be overriden in plugins.
  zepto.Z = function(dom, selector) {
    dom = dom || []
    dom.__proto__ = $.fn
    dom.selector = selector || ''
    return dom
  }

  // `$.zepto.isZ` should return `true` if the given object is a Zepto
  // collection. This method can be overriden in plugins.
  zepto.isZ = function(object) {
    return object instanceof zepto.Z
  }

  // `$.zepto.init` is Zepto's counterpart to jQuery's `$.fn.init` and
  // takes a CSS selector and an optional context (and handles various
  // special cases).
  // This method can be overriden in plugins.
  zepto.init = function(selector, context) {
    var dom
    // If nothing given, return an empty Zepto collection
    if (!selector) return zepto.Z()
    // Optimize for string selectors
    else if (typeof selector == 'string') {
      selector = selector.trim()
      // If it's a html fragment, create nodes from it
      // Note: In both Chrome 21 and Firefox 15, DOM error 12
      // is thrown if the fragment doesn't begin with <
      if (selector[0] == '<' && fragmentRE.test(selector))
        dom = zepto.fragment(selector, RegExp.$1, context), selector = null
      // If there's a context, create a collection on that context first, and select
      // nodes from there
      else if (context !== undefined) return $(context).find(selector)
      // If it's a CSS selector, use it to select nodes.
      else dom = zepto.qsa(document, selector)
    }
    // If a function is given, call it when the DOM is ready
    else if (isFunction(selector)) return $(document).ready(selector)
    // If a Zepto collection is given, just return it
    else if (zepto.isZ(selector)) return selector
    else {
      // normalize array if an array of nodes is given
      if (isArray(selector)) dom = compact(selector)
      // Wrap DOM nodes.
      else if (isObject(selector))
        dom = [selector], selector = null
      // If it's a html fragment, create nodes from it
      else if (fragmentRE.test(selector))
        dom = zepto.fragment(selector.trim(), RegExp.$1, context), selector = null
      // If there's a context, create a collection on that context first, and select
      // nodes from there
      else if (context !== undefined) return $(context).find(selector)
      // And last but no least, if it's a CSS selector, use it to select nodes.
      else dom = zepto.qsa(document, selector)
    }
    // create a new Zepto collection from the nodes found
    return zepto.Z(dom, selector)
  }

  // `$` will be the base `Zepto` object. When calling this
  // function just call `$.zepto.init, which makes the implementation
  // details of selecting nodes and creating Zepto collections
  // patchable in plugins.
  $ = function(selector, context){
    return zepto.init(selector, context)
  }

  function extend(target, source, deep) {
    for (key in source)
      if (deep && (isPlainObject(source[key]) || isArray(source[key]))) {
        if (isPlainObject(source[key]) && !isPlainObject(target[key]))
          target[key] = {}
        if (isArray(source[key]) && !isArray(target[key]))
          target[key] = []
        extend(target[key], source[key], deep)
      }
      else if (source[key] !== undefined) target[key] = source[key]
  }

  // Copy all but undefined properties from one or more
  // objects to the `target` object.
  $.extend = function(target){
    var deep, args = slice.call(arguments, 1)
    if (typeof target == 'boolean') {
      deep = target
      target = args.shift()
    }
    args.forEach(function(arg){ extend(target, arg, deep) })
    return target
  }

  // `$.zepto.qsa` is Zepto's CSS selector implementation which
  // uses `document.querySelectorAll` and optimizes for some special cases, like `#id`.
  // This method can be overriden in plugins.
  zepto.qsa = function(element, selector){
    var found,
        maybeID = selector[0] == '#',
        maybeClass = !maybeID && selector[0] == '.',
        nameOnly = maybeID || maybeClass ? selector.slice(1) : selector, // Ensure that a 1 char tag name still gets checked
        isSimple = simpleSelectorRE.test(nameOnly)
    return (isDocument(element) && isSimple && maybeID) ?
      ( (found = element.getElementById(nameOnly)) ? [found] : [] ) :
      (element.nodeType !== 1 && element.nodeType !== 9) ? [] :
      slice.call(
        isSimple && !maybeID ?
          maybeClass ? element.getElementsByClassName(nameOnly) : // If it's simple, it could be a class
          element.getElementsByTagName(selector) : // Or a tag
          element.querySelectorAll(selector) // Or it's not simple, and we need to query all
      )
  }

  function filtered(nodes, selector) {
    return selector == null ? $(nodes) : $(nodes).filter(selector)
  }

  $.contains = document.documentElement.contains ?
    function(parent, node) {
      return parent !== node && parent.contains(node)
    } :
    function(parent, node) {
      while (node && (node = node.parentNode))
        if (node === parent) return true
      return false
    }

  function funcArg(context, arg, idx, payload) {
    return isFunction(arg) ? arg.call(context, idx, payload) : arg
  }

  function setAttribute(node, name, value) {
    value == null ? node.removeAttribute(name) : node.setAttribute(name, value)
  }

  // access className property while respecting SVGAnimatedString
  function className(node, value){
    var klass = node.className || '',
        svg   = klass && klass.baseVal !== undefined

    if (value === undefined) return svg ? klass.baseVal : klass
    svg ? (klass.baseVal = value) : (node.className = value)
  }

  // "true"  => true
  // "false" => false
  // "null"  => null
  // "42"    => 42
  // "42.5"  => 42.5
  // "08"    => "08"
  // JSON    => parse if valid
  // String  => self
  function deserializeValue(value) {
    try {
      return value ?
        value == "true" ||
        ( value == "false" ? false :
          value == "null" ? null :
          +value + "" == value ? +value :
          /^[\[\{]/.test(value) ? $.parseJSON(value) :
          value )
        : value
    } catch(e) {
      return value
    }
  }

  $.type = type
  $.isFunction = isFunction
  $.isWindow = isWindow
  $.isArray = isArray
  $.isPlainObject = isPlainObject

  $.isEmptyObject = function(obj) {
    var name
    for (name in obj) return false
    return true
  }

  $.inArray = function(elem, array, i){
    return emptyArray.indexOf.call(array, elem, i)
  }

  $.camelCase = camelize
  $.trim = function(str) {
    return str == null ? "" : String.prototype.trim.call(str)
  }

  // plugin compatibility
  $.uuid = 0
  $.support = { }
  $.expr = { }

  $.map = function(elements, callback){
    var value, values = [], i, key
    if (likeArray(elements))
      for (i = 0; i < elements.length; i++) {
        value = callback(elements[i], i)
        if (value != null) values.push(value)
      }
    else
      for (key in elements) {
        value = callback(elements[key], key)
        if (value != null) values.push(value)
      }
    return flatten(values)
  }

  $.each = function(elements, callback){
    var i, key
    if (likeArray(elements)) {
      for (i = 0; i < elements.length; i++)
        if (callback.call(elements[i], i, elements[i]) === false) return elements
    } else {
      for (key in elements)
        if (callback.call(elements[key], key, elements[key]) === false) return elements
    }

    return elements
  }

  $.grep = function(elements, callback){
    return filter.call(elements, callback)
  }

  if (window.JSON) $.parseJSON = JSON.parse

  // Populate the class2type map
  $.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), function(i, name) {
    class2type[ "[object " + name + "]" ] = name.toLowerCase()
  })

  // Define methods that will be available on all
  // Zepto collections
  $.fn = {
    // Because a collection acts like an array
    // copy over these useful array functions.
    forEach: emptyArray.forEach,
    reduce: emptyArray.reduce,
    push: emptyArray.push,
    sort: emptyArray.sort,
    indexOf: emptyArray.indexOf,
    concat: emptyArray.concat,

    // `map` and `slice` in the jQuery API work differently
    // from their array counterparts
    map: function(fn){
      return $($.map(this, function(el, i){ return fn.call(el, i, el) }))
    },
    slice: function(){
      return $(slice.apply(this, arguments))
    },

    ready: function(callback){
      // need to check if document.body exists for IE as that browser reports
      // document ready when it hasn't yet created the body element
      if (readyRE.test(document.readyState) && document.body) callback($)
      else document.addEventListener('DOMContentLoaded', function(){ callback($) }, false)
      return this
    },
    get: function(idx){
      return idx === undefined ? slice.call(this) : this[idx >= 0 ? idx : idx + this.length]
    },
    toArray: function(){ return this.get() },
    size: function(){
      return this.length
    },
    remove: function(){
      return this.each(function(){
        if (this.parentNode != null)
          this.parentNode.removeChild(this)
      })
    },
    each: function(callback){
      emptyArray.every.call(this, function(el, idx){
        return callback.call(el, idx, el) !== false
      })
      return this
    },
    filter: function(selector){
      if (isFunction(selector)) return this.not(this.not(selector))
      return $(filter.call(this, function(element){
        return zepto.matches(element, selector)
      }))
    },
    add: function(selector,context){
      return $(uniq(this.concat($(selector,context))))
    },
    is: function(selector){
      return this.length > 0 && zepto.matches(this[0], selector)
    },
    not: function(selector){
      var nodes=[]
      if (isFunction(selector) && selector.call !== undefined)
        this.each(function(idx){
          if (!selector.call(this,idx)) nodes.push(this)
        })
      else {
        var excludes = typeof selector == 'string' ? this.filter(selector) :
          (likeArray(selector) && isFunction(selector.item)) ? slice.call(selector) : $(selector)
        this.forEach(function(el){
          if (excludes.indexOf(el) < 0) nodes.push(el)
        })
      }
      return $(nodes)
    },
    has: function(selector){
      return this.filter(function(){
        return isObject(selector) ?
          $.contains(this, selector) :
          $(this).find(selector).size()
      })
    },
    eq: function(idx){
      return idx === -1 ? this.slice(idx) : this.slice(idx, + idx + 1)
    },
    first: function(){
      var el = this[0]
      return el && !isObject(el) ? el : $(el)
    },
    last: function(){
      var el = this[this.length - 1]
      return el && !isObject(el) ? el : $(el)
    },
    find: function(selector){
      var result, $this = this
      if (!selector) result = $()
      else if (typeof selector == 'object')
        result = $(selector).filter(function(){
          var node = this
          return emptyArray.some.call($this, function(parent){
            return $.contains(parent, node)
          })
        })
      else if (this.length == 1) result = $(zepto.qsa(this[0], selector))
      else result = this.map(function(){ return zepto.qsa(this, selector) })
      return result
    },
    closest: function(selector, context){
      var node = this[0], collection = false
      if (typeof selector == 'object') collection = $(selector)
      while (node && !(collection ? collection.indexOf(node) >= 0 : zepto.matches(node, selector)))
        node = node !== context && !isDocument(node) && node.parentNode
      return $(node)
    },
    parents: function(selector){
      var ancestors = [], nodes = this
      while (nodes.length > 0)
        nodes = $.map(nodes, function(node){
          if ((node = node.parentNode) && !isDocument(node) && ancestors.indexOf(node) < 0) {
            ancestors.push(node)
            return node
          }
        })
      return filtered(ancestors, selector)
    },
    parent: function(selector){
      return filtered(uniq(this.pluck('parentNode')), selector)
    },
    children: function(selector){
      return filtered(this.map(function(){ return children(this) }), selector)
    },
    contents: function() {
      return this.map(function() { return slice.call(this.childNodes) })
    },
    siblings: function(selector){
      return filtered(this.map(function(i, el){
        return filter.call(children(el.parentNode), function(child){ return child!==el })
      }), selector)
    },
    empty: function(){
      return this.each(function(){ this.innerHTML = '' })
    },
    // `pluck` is borrowed from Prototype.js
    pluck: function(property){
      return $.map(this, function(el){ return el[property] })
    },
    show: function(){
      return this.each(function(){
        this.style.display == "none" && (this.style.display = '')
        if (getComputedStyle(this, '').getPropertyValue("display") == "none")
          this.style.display = defaultDisplay(this.nodeName)
      })
    },
    replaceWith: function(newContent){
      return this.before(newContent).remove()
    },
    wrap: function(structure){
      var func = isFunction(structure)
      if (this[0] && !func)
        var dom   = $(structure).get(0),
            clone = dom.parentNode || this.length > 1

      return this.each(function(index){
        $(this).wrapAll(
          func ? structure.call(this, index) :
            clone ? dom.cloneNode(true) : dom
        )
      })
    },
    wrapAll: function(structure){
      if (this[0]) {
        $(this[0]).before(structure = $(structure))
        var children
        // drill down to the inmost element
        while ((children = structure.children()).length) structure = children.first()
        $(structure).append(this)
      }
      return this
    },
    wrapInner: function(structure){
      var func = isFunction(structure)
      return this.each(function(index){
        var self = $(this), contents = self.contents(),
            dom  = func ? structure.call(this, index) : structure
        contents.length ? contents.wrapAll(dom) : self.append(dom)
      })
    },
    unwrap: function(){
      this.parent().each(function(){
        $(this).replaceWith($(this).children())
      })
      return this
    },
    clone: function(){
      return this.map(function(){ return this.cloneNode(true) })
    },
    hide: function(){
      return this.css("display", "none")
    },
    toggle: function(setting){
      return this.each(function(){
        var el = $(this)
        ;(setting === undefined ? el.css("display") == "none" : setting) ? el.show() : el.hide()
      })
    },
    prev: function(selector){ return $(this.pluck('previousElementSibling')).filter(selector || '*') },
    next: function(selector){ return $(this.pluck('nextElementSibling')).filter(selector || '*') },
    html: function(html){
      return 0 in arguments ?
        this.each(function(idx){
          var originHtml = this.innerHTML
          $(this).empty().append( funcArg(this, html, idx, originHtml) )
        }) :
        (0 in this ? this[0].innerHTML : null)
    },
    text: function(text){
      return 0 in arguments ?
        this.each(function(idx){
          var newText = funcArg(this, text, idx, this.textContent)
          this.textContent = newText == null ? '' : ''+newText
        }) :
        (0 in this ? this[0].textContent : null)
    },
    attr: function(name, value){
      var result
      return (typeof name == 'string' && !(1 in arguments)) ?
        (!this.length || this[0].nodeType !== 1 ? undefined :
          (!(result = this[0].getAttribute(name)) && name in this[0]) ? this[0][name] : result
        ) :
        this.each(function(idx){
          if (this.nodeType !== 1) return
          if (isObject(name)) for (key in name) setAttribute(this, key, name[key])
          else setAttribute(this, name, funcArg(this, value, idx, this.getAttribute(name)))
        })
    },
    removeAttr: function(name){
      return this.each(function(){ this.nodeType === 1 && name.split(' ').forEach(function(attribute){
        setAttribute(this, attribute)
      }, this)})
    },
    prop: function(name, value){
      name = propMap[name] || name
      return (1 in arguments) ?
        this.each(function(idx){
          this[name] = funcArg(this, value, idx, this[name])
        }) :
        (this[0] && this[0][name])
    },
    data: function(name, value){
      var attrName = 'data-' + name.replace(capitalRE, '-$1').toLowerCase()

      var data = (1 in arguments) ?
        this.attr(attrName, value) :
        this.attr(attrName)

      return data !== null ? deserializeValue(data) : undefined
    },
    val: function(value){
      return 0 in arguments ?
        this.each(function(idx){
          this.value = funcArg(this, value, idx, this.value)
        }) :
        (this[0] && (this[0].multiple ?
           $(this[0]).find('option').filter(function(){ return this.selected }).pluck('value') :
           this[0].value)
        )
    },
    offset: function(coordinates){
      if (coordinates) return this.each(function(index){
        var $this = $(this),
            coords = funcArg(this, coordinates, index, $this.offset()),
            parentOffset = $this.offsetParent().offset(),
            props = {
              top:  coords.top  - parentOffset.top,
              left: coords.left - parentOffset.left
            }

        if ($this.css('position') == 'static') props['position'] = 'relative'
        $this.css(props)
      })
      if (!this.length) return null
      var obj = this[0].getBoundingClientRect()
      return {
        left: obj.left + window.pageXOffset,
        top: obj.top + window.pageYOffset,
        width: Math.round(obj.width),
        height: Math.round(obj.height)
      }
    },
    css: function(property, value){
      if (arguments.length < 2) {
        var computedStyle, element = this[0]
        if(!element) return
        computedStyle = getComputedStyle(element, '')
        if (typeof property == 'string')
          return element.style[camelize(property)] || computedStyle.getPropertyValue(property)
        else if (isArray(property)) {
          var props = {}
          $.each(property, function(_, prop){
            props[prop] = (element.style[camelize(prop)] || computedStyle.getPropertyValue(prop))
          })
          return props
        }
      }

      var css = ''
      if (type(property) == 'string') {
        if (!value && value !== 0)
          this.each(function(){ this.style.removeProperty(dasherize(property)) })
        else
          css = dasherize(property) + ":" + maybeAddPx(property, value)
      } else {
        for (key in property)
          if (!property[key] && property[key] !== 0)
            this.each(function(){ this.style.removeProperty(dasherize(key)) })
          else
            css += dasherize(key) + ':' + maybeAddPx(key, property[key]) + ';'
      }

      return this.each(function(){ this.style.cssText += ';' + css })
    },
    index: function(element){
      return element ? this.indexOf($(element)[0]) : this.parent().children().indexOf(this[0])
    },
    hasClass: function(name){
      if (!name) return false
      return emptyArray.some.call(this, function(el){
        return this.test(className(el))
      }, classRE(name))
    },
    addClass: function(name){
      if (!name) return this
      return this.each(function(idx){
        if (!('className' in this)) return
        classList = []
        var cls = className(this), newName = funcArg(this, name, idx, cls)
        newName.split(/\s+/g).forEach(function(klass){
          if (!$(this).hasClass(klass)) classList.push(klass)
        }, this)
        classList.length && className(this, cls + (cls ? " " : "") + classList.join(" "))
      })
    },
    removeClass: function(name){
      return this.each(function(idx){
        if (!('className' in this)) return
        if (name === undefined) return className(this, '')
        classList = className(this)
        funcArg(this, name, idx, classList).split(/\s+/g).forEach(function(klass){
          classList = classList.replace(classRE(klass), " ")
        })
        className(this, classList.trim())
      })
    },
    toggleClass: function(name, when){
      if (!name) return this
      return this.each(function(idx){
        var $this = $(this), names = funcArg(this, name, idx, className(this))
        names.split(/\s+/g).forEach(function(klass){
          (when === undefined ? !$this.hasClass(klass) : when) ?
            $this.addClass(klass) : $this.removeClass(klass)
        })
      })
    },
    scrollTop: function(value){
      if (!this.length) return
      var hasScrollTop = 'scrollTop' in this[0]
      if (value === undefined) return hasScrollTop ? this[0].scrollTop : this[0].pageYOffset
      return this.each(hasScrollTop ?
        function(){ this.scrollTop = value } :
        function(){ this.scrollTo(this.scrollX, value) })
    },
    scrollLeft: function(value){
      if (!this.length) return
      var hasScrollLeft = 'scrollLeft' in this[0]
      if (value === undefined) return hasScrollLeft ? this[0].scrollLeft : this[0].pageXOffset
      return this.each(hasScrollLeft ?
        function(){ this.scrollLeft = value } :
        function(){ this.scrollTo(value, this.scrollY) })
    },
    position: function() {
      if (!this.length) return

      var elem = this[0],
        // Get *real* offsetParent
        offsetParent = this.offsetParent(),
        // Get correct offsets
        offset       = this.offset(),
        parentOffset = rootNodeRE.test(offsetParent[0].nodeName) ? { top: 0, left: 0 } : offsetParent.offset()

      // Subtract element margins
      // note: when an element has margin: auto the offsetLeft and marginLeft
      // are the same in Safari causing offset.left to incorrectly be 0
      offset.top  -= parseFloat( $(elem).css('margin-top') ) || 0
      offset.left -= parseFloat( $(elem).css('margin-left') ) || 0

      // Add offsetParent borders
      parentOffset.top  += parseFloat( $(offsetParent[0]).css('border-top-width') ) || 0
      parentOffset.left += parseFloat( $(offsetParent[0]).css('border-left-width') ) || 0

      // Subtract the two offsets
      return {
        top:  offset.top  - parentOffset.top,
        left: offset.left - parentOffset.left
      }
    },
    offsetParent: function() {
      return this.map(function(){
        var parent = this.offsetParent || document.body
        while (parent && !rootNodeRE.test(parent.nodeName) && $(parent).css("position") == "static")
          parent = parent.offsetParent
        return parent
      })
    }
  }

  // for now
  $.fn.detach = $.fn.remove

  // Generate the `width` and `height` functions
  ;['width', 'height'].forEach(function(dimension){
    var dimensionProperty =
      dimension.replace(/./, function(m){ return m[0].toUpperCase() })

    $.fn[dimension] = function(value){
      var offset, el = this[0]
      if (value === undefined) return isWindow(el) ? el['inner' + dimensionProperty] :
        isDocument(el) ? el.documentElement['scroll' + dimensionProperty] :
        (offset = this.offset()) && offset[dimension]
      else return this.each(function(idx){
        el = $(this)
        el.css(dimension, funcArg(this, value, idx, el[dimension]()))
      })
    }
  })

  function traverseNode(node, fun) {
    fun(node)
    for (var i = 0, len = node.childNodes.length; i < len; i++)
      traverseNode(node.childNodes[i], fun)
  }

  // Generate the `after`, `prepend`, `before`, `append`,
  // `insertAfter`, `insertBefore`, `appendTo`, and `prependTo` methods.
  adjacencyOperators.forEach(function(operator, operatorIndex) {
    var inside = operatorIndex % 2 //=> prepend, append

    $.fn[operator] = function(){
      // arguments can be nodes, arrays of nodes, Zepto objects and HTML strings
      var argType, nodes = $.map(arguments, function(arg) {
            argType = type(arg)
            return argType == "object" || argType == "array" || arg == null ?
              arg : zepto.fragment(arg)
          }),
          parent, copyByClone = this.length > 1
      if (nodes.length < 1) return this

      return this.each(function(_, target){
        parent = inside ? target : target.parentNode

        // convert all methods to a "before" operation
        target = operatorIndex == 0 ? target.nextSibling :
                 operatorIndex == 1 ? target.firstChild :
                 operatorIndex == 2 ? target :
                 null

        var parentInDocument = $.contains(document.documentElement, parent)

        nodes.forEach(function(node){
          if (copyByClone) node = node.cloneNode(true)
          else if (!parent) return $(node).remove()

          parent.insertBefore(node, target)
          if (parentInDocument) traverseNode(node, function(el){
            if (el.nodeName != null && el.nodeName.toUpperCase() === 'SCRIPT' &&
               (!el.type || el.type === 'text/javascript') && !el.src)
              window['eval'].call(window, el.innerHTML)
          })
        })
      })
    }

    // after    => insertAfter
    // prepend  => prependTo
    // before   => insertBefore
    // append   => appendTo
    $.fn[inside ? operator+'To' : 'insert'+(operatorIndex ? 'Before' : 'After')] = function(html){
      $(html)[operator](this)
      return this
    }
  })

  zepto.Z.prototype = $.fn

  // Export internal API functions in the `$.zepto` namespace
  zepto.uniq = uniq
  zepto.deserializeValue = deserializeValue
  $.zepto = zepto

  return $
})()

;(function($){
  var _zid = 1, undefined,
      slice = Array.prototype.slice,
      isFunction = $.isFunction,
      isString = function(obj){ return typeof obj == 'string' },
      handlers = {},
      specialEvents={},
      focusinSupported = 'onfocusin' in window,
      focus = { focus: 'focusin', blur: 'focusout' },
      hover = { mouseenter: 'mouseover', mouseleave: 'mouseout' }

  specialEvents.click = specialEvents.mousedown = specialEvents.mouseup = specialEvents.mousemove = 'MouseEvents'

  function zid(element) {
    return element._zid || (element._zid = _zid++)
  }
  function findHandlers(element, event, fn, selector) {
    event = parse(event)
    if (event.ns) var matcher = matcherFor(event.ns)
    return (handlers[zid(element)] || []).filter(function(handler) {
      return handler
        && (!event.e  || handler.e == event.e)
        && (!event.ns || matcher.test(handler.ns))
        && (!fn       || zid(handler.fn) === zid(fn))
        && (!selector || handler.sel == selector)
    })
  }
  function parse(event) {
    var parts = ('' + event).split('.')
    return {e: parts[0], ns: parts.slice(1).sort().join(' ')}
  }
  function matcherFor(ns) {
    return new RegExp('(?:^| )' + ns.replace(' ', ' .* ?') + '(?: |$)')
  }

  function eventCapture(handler, captureSetting) {
    return handler.del &&
      (!focusinSupported && (handler.e in focus)) ||
      !!captureSetting
  }

  function realEvent(type) {
    return hover[type] || (focusinSupported && focus[type]) || type
  }

  function add(element, events, fn, data, selector, delegator, capture){
    var id = zid(element), set = (handlers[id] || (handlers[id] = []))
    events.split(/\s/).forEach(function(event){
      if (event == 'ready') return $(document).ready(fn)
      var handler   = parse(event)
      handler.fn    = fn
      handler.sel   = selector
      // emulate mouseenter, mouseleave
      if (handler.e in hover) fn = function(e){
        var related = e.relatedTarget
        if (!related || (related !== this && !$.contains(this, related)))
          return handler.fn.apply(this, arguments)
      }
      handler.del   = delegator
      var callback  = delegator || fn
      handler.proxy = function(e){
        e = compatible(e)
        if (e.isImmediatePropagationStopped()) return
        e.data = data
        var result = callback.apply(element, e._args == undefined ? [e] : [e].concat(e._args))
        if (result === false) e.preventDefault(), e.stopPropagation()
        return result
      }
      handler.i = set.length
      set.push(handler)
      if ('addEventListener' in element)
        element.addEventListener(realEvent(handler.e), handler.proxy, eventCapture(handler, capture))
    })
  }
  function remove(element, events, fn, selector, capture){
    var id = zid(element)
    ;(events || '').split(/\s/).forEach(function(event){
      findHandlers(element, event, fn, selector).forEach(function(handler){
        delete handlers[id][handler.i]
      if ('removeEventListener' in element)
        element.removeEventListener(realEvent(handler.e), handler.proxy, eventCapture(handler, capture))
      })
    })
  }

  $.event = { add: add, remove: remove }

  $.proxy = function(fn, context) {
    var args = (2 in arguments) && slice.call(arguments, 2)
    if (isFunction(fn)) {
      var proxyFn = function(){ return fn.apply(context, args ? args.concat(slice.call(arguments)) : arguments) }
      proxyFn._zid = zid(fn)
      return proxyFn
    } else if (isString(context)) {
      if (args) {
        args.unshift(fn[context], fn)
        return $.proxy.apply(null, args)
      } else {
        return $.proxy(fn[context], fn)
      }
    } else {
      throw new TypeError("expected function")
    }
  }

  $.fn.bind = function(event, data, callback){
    return this.on(event, data, callback)
  }
  $.fn.unbind = function(event, callback){
    return this.off(event, callback)
  }
  $.fn.one = function(event, selector, data, callback){
    return this.on(event, selector, data, callback, 1)
  }

  var returnTrue = function(){return true},
      returnFalse = function(){return false},
      ignoreProperties = /^([A-Z]|returnValue$|layer[XY]$)/,
      eventMethods = {
        preventDefault: 'isDefaultPrevented',
        stopImmediatePropagation: 'isImmediatePropagationStopped',
        stopPropagation: 'isPropagationStopped'
      }

  function compatible(event, source) {
    if (source || !event.isDefaultPrevented) {
      source || (source = event)

      $.each(eventMethods, function(name, predicate) {
        var sourceMethod = source[name]
        event[name] = function(){
          this[predicate] = returnTrue
          return sourceMethod && sourceMethod.apply(source, arguments)
        }
        event[predicate] = returnFalse
      })

      if (source.defaultPrevented !== undefined ? source.defaultPrevented :
          'returnValue' in source ? source.returnValue === false :
          source.getPreventDefault && source.getPreventDefault())
        event.isDefaultPrevented = returnTrue
    }
    return event
  }

  function createProxy(event) {
    var key, proxy = { originalEvent: event }
    for (key in event)
      if (!ignoreProperties.test(key) && event[key] !== undefined) proxy[key] = event[key]

    return compatible(proxy, event)
  }

  $.fn.delegate = function(selector, event, callback){
    return this.on(event, selector, callback)
  }
  $.fn.undelegate = function(selector, event, callback){
    return this.off(event, selector, callback)
  }

  $.fn.live = function(event, callback){
    $(document.body).delegate(this.selector, event, callback)
    return this
  }
  $.fn.die = function(event, callback){
    $(document.body).undelegate(this.selector, event, callback)
    return this
  }

  $.fn.on = function(event, selector, data, callback, one){
    var autoRemove, delegator, $this = this
    if (event && !isString(event)) {
      $.each(event, function(type, fn){
        $this.on(type, selector, data, fn, one)
      })
      return $this
    }

    if (!isString(selector) && !isFunction(callback) && callback !== false)
      callback = data, data = selector, selector = undefined
    if (isFunction(data) || data === false)
      callback = data, data = undefined

    if (callback === false) callback = returnFalse

    return $this.each(function(_, element){
      if (one) autoRemove = function(e){
        remove(element, e.type, callback)
        return callback.apply(this, arguments)
      }

      if (selector) delegator = function(e){
        var evt, match = $(e.target).closest(selector, element).get(0)
        if (match && match !== element) {
          evt = $.extend(createProxy(e), {currentTarget: match, liveFired: element})
          return (autoRemove || callback).apply(match, [evt].concat(slice.call(arguments, 1)))
        }
      }

      add(element, event, callback, data, selector, delegator || autoRemove)
    })
  }
  $.fn.off = function(event, selector, callback){
    var $this = this
    if (event && !isString(event)) {
      $.each(event, function(type, fn){
        $this.off(type, selector, fn)
      })
      return $this
    }

    if (!isString(selector) && !isFunction(callback) && callback !== false)
      callback = selector, selector = undefined

    if (callback === false) callback = returnFalse

    return $this.each(function(){
      remove(this, event, callback, selector)
    })
  }

  $.fn.trigger = function(event, args){
    event = (isString(event) || $.isPlainObject(event)) ? $.Event(event) : compatible(event)
    event._args = args
    return this.each(function(){
      // handle focus(), blur() by calling them directly
      if (event.type in focus && typeof this[event.type] == "function") this[event.type]()
      // items in the collection might not be DOM elements
      else if ('dispatchEvent' in this) this.dispatchEvent(event)
      else $(this).triggerHandler(event, args)
    })
  }

  // triggers event handlers on current element just as if an event occurred,
  // doesn't trigger an actual event, doesn't bubble
  $.fn.triggerHandler = function(event, args){
    var e, result
    this.each(function(i, element){
      e = createProxy(isString(event) ? $.Event(event) : event)
      e._args = args
      e.target = element
      $.each(findHandlers(element, event.type || event), function(i, handler){
        result = handler.proxy(e)
        if (e.isImmediatePropagationStopped()) return false
      })
    })
    return result
  }

  // shortcut methods for `.bind(event, fn)` for each event type
  ;('focusin focusout focus blur load resize scroll unload click dblclick '+
  'mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave '+
  'change select keydown keypress keyup error').split(' ').forEach(function(event) {
    $.fn[event] = function(callback) {
      return (0 in arguments) ?
        this.bind(event, callback) :
        this.trigger(event)
    }
  })

  $.Event = function(type, props) {
    if (!isString(type)) props = type, type = props.type
    var event = document.createEvent(specialEvents[type] || 'Events'), bubbles = true
    if (props) for (var name in props) (name == 'bubbles') ? (bubbles = !!props[name]) : (event[name] = props[name])
    event.initEvent(type, bubbles, true)
    return compatible(event)
  }

})(Zepto)

;(function($){
  var jsonpID = 0,
      document = window.document,
      key,
      name,
      rscript = /<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,
      scriptTypeRE = /^(?:text|application)\/javascript/i,
      xmlTypeRE = /^(?:text|application)\/xml/i,
      jsonType = 'application/json',
      htmlType = 'text/html',
      blankRE = /^\s*$/,
      originAnchor = document.createElement('a')

  originAnchor.href = window.location.href

  // trigger a custom event and return false if it was cancelled
  function triggerAndReturn(context, eventName, data) {
    var event = $.Event(eventName)
    $(context).trigger(event, data)
    return !event.isDefaultPrevented()
  }

  // trigger an Ajax "global" event
  function triggerGlobal(settings, context, eventName, data) {
    if (settings.global) return triggerAndReturn(context || document, eventName, data)
  }

  // Number of active Ajax requests
  $.active = 0

  function ajaxStart(settings) {
    if (settings.global && $.active++ === 0) triggerGlobal(settings, null, 'ajaxStart')
  }
  function ajaxStop(settings) {
    if (settings.global && !(--$.active)) triggerGlobal(settings, null, 'ajaxStop')
  }

  // triggers an extra global event "ajaxBeforeSend" that's like "ajaxSend" but cancelable
  function ajaxBeforeSend(xhr, settings) {
    var context = settings.context
    if (settings.beforeSend.call(context, xhr, settings) === false ||
        triggerGlobal(settings, context, 'ajaxBeforeSend', [xhr, settings]) === false)
      return false

    triggerGlobal(settings, context, 'ajaxSend', [xhr, settings])
  }
  function ajaxSuccess(data, xhr, settings, deferred) {
    var context = settings.context, status = 'success'
    settings.success.call(context, data, status, xhr)
    if (deferred) deferred.resolveWith(context, [data, status, xhr])
    triggerGlobal(settings, context, 'ajaxSuccess', [xhr, settings, data])
    ajaxComplete(status, xhr, settings)
  }
  // type: "timeout", "error", "abort", "parsererror"
  function ajaxError(error, type, xhr, settings, deferred) {
    var context = settings.context
    settings.error.call(context, xhr, type, error)
    if (deferred) deferred.rejectWith(context, [xhr, type, error])
    triggerGlobal(settings, context, 'ajaxError', [xhr, settings, error || type])
    ajaxComplete(type, xhr, settings)
  }
  // status: "success", "notmodified", "error", "timeout", "abort", "parsererror"
  function ajaxComplete(status, xhr, settings) {
    var context = settings.context
    settings.complete.call(context, xhr, status)
    triggerGlobal(settings, context, 'ajaxComplete', [xhr, settings])
    ajaxStop(settings)
  }

  // Empty function, used as default callback
  function empty() {}

  $.ajaxJSONP = function(options, deferred){
    if (!('type' in options)) return $.ajax(options)

    var _callbackName = options.jsonpCallback,
      callbackName = ($.isFunction(_callbackName) ?
        _callbackName() : _callbackName) || ('jsonp' + (++jsonpID)),
      script = document.createElement('script'),
      originalCallback = window[callbackName],
      responseData,
      abort = function(errorType) {
        $(script).triggerHandler('error', errorType || 'abort')
      },
      xhr = { abort: abort }, abortTimeout

    if (deferred) deferred.promise(xhr)

    $(script).on('load error', function(e, errorType){
      clearTimeout(abortTimeout)
      $(script).off().remove()

      if (e.type == 'error' || !responseData) {
        ajaxError(null, errorType || 'error', xhr, options, deferred)
      } else {
        ajaxSuccess(responseData[0], xhr, options, deferred)
      }

      window[callbackName] = originalCallback
      if (responseData && $.isFunction(originalCallback))
        originalCallback(responseData[0])

      originalCallback = responseData = undefined
    })

    if (ajaxBeforeSend(xhr, options) === false) {
      abort('abort')
      return xhr
    }

    window[callbackName] = function(){
      responseData = arguments
    }

    script.src = options.url.replace(/\?(.+)=\?/, '?$1=' + callbackName)
    document.head.appendChild(script)

    if (options.timeout > 0) abortTimeout = setTimeout(function(){
      abort('timeout')
    }, options.timeout)

    return xhr
  }

  $.ajaxSettings = {
    // Default type of request
    type: 'GET',
    // Callback that is executed before request
    beforeSend: empty,
    // Callback that is executed if the request succeeds
    success: empty,
    // Callback that is executed the the server drops error
    error: empty,
    // Callback that is executed on request complete (both: error and success)
    complete: empty,
    // The context for the callbacks
    context: null,
    // Whether to trigger "global" Ajax events
    global: true,
    // Transport
    xhr: function () {
      return new window.XMLHttpRequest()
    },
    // MIME types mapping
    // IIS returns Javascript as "application/x-javascript"
    accepts: {
      script: 'text/javascript, application/javascript, application/x-javascript',
      json:   jsonType,
      xml:    'application/xml, text/xml',
      html:   htmlType,
      text:   'text/plain'
    },
    // Whether the request is to another domain
    crossDomain: false,
    // Default timeout
    timeout: 0,
    // Whether data should be serialized to string
    processData: true,
    // Whether the browser should be allowed to cache GET responses
    cache: true
  }

  function mimeToDataType(mime) {
    if (mime) mime = mime.split(';', 2)[0]
    return mime && ( mime == htmlType ? 'html' :
      mime == jsonType ? 'json' :
      scriptTypeRE.test(mime) ? 'script' :
      xmlTypeRE.test(mime) && 'xml' ) || 'text'
  }

  function appendQuery(url, query) {
    if (query == '') return url
    return (url + '&' + query).replace(/[&?]{1,2}/, '?')
  }

  // serialize payload and append it to the URL for GET requests
  function serializeData(options) {
    if (options.processData && options.data && $.type(options.data) != "string")
      options.data = $.param(options.data, options.traditional)
    if (options.data && (!options.type || options.type.toUpperCase() == 'GET'))
      options.url = appendQuery(options.url, options.data), options.data = undefined
  }

  $.ajax = function(options){
    var settings = $.extend({}, options || {}),
        deferred = $.Deferred && $.Deferred(),
        urlAnchor
    for (key in $.ajaxSettings) if (settings[key] === undefined) settings[key] = $.ajaxSettings[key]

    ajaxStart(settings)

    if (!settings.crossDomain) {
      urlAnchor = document.createElement('a')
      urlAnchor.href = settings.url
      urlAnchor.href = urlAnchor.href
      settings.crossDomain = (originAnchor.protocol + '//' + originAnchor.host) !== (urlAnchor.protocol + '//' + urlAnchor.host)
    }

    if (!settings.url) settings.url = window.location.toString()
    serializeData(settings)

    var dataType = settings.dataType, hasPlaceholder = /\?.+=\?/.test(settings.url)
    if (hasPlaceholder) dataType = 'jsonp'

    if (settings.cache === false || (
         (!options || options.cache !== true) &&
         ('script' == dataType || 'jsonp' == dataType)
        ))
      settings.url = appendQuery(settings.url, '_=' + Date.now())

    if ('jsonp' == dataType) {
      if (!hasPlaceholder)
        settings.url = appendQuery(settings.url,
          settings.jsonp ? (settings.jsonp + '=?') : settings.jsonp === false ? '' : 'callback=?')
      return $.ajaxJSONP(settings, deferred)
    }

    var mime = settings.accepts[dataType],
        headers = { },
        setHeader = function(name, value) { headers[name.toLowerCase()] = [name, value] },
        protocol = /^([\w-]+:)\/\//.test(settings.url) ? RegExp.$1 : window.location.protocol,
        xhr = settings.xhr(),
        nativeSetHeader = xhr.setRequestHeader,
        abortTimeout

    if (deferred) deferred.promise(xhr)

    if (!settings.crossDomain) setHeader('X-Requested-With', 'XMLHttpRequest')
    setHeader('Accept', mime || '*/*')
    if (mime = settings.mimeType || mime) {
      if (mime.indexOf(',') > -1) mime = mime.split(',', 2)[0]
      xhr.overrideMimeType && xhr.overrideMimeType(mime)
    }
    if (settings.contentType || (settings.contentType !== false && settings.data && settings.type.toUpperCase() != 'GET'))
      setHeader('Content-Type', settings.contentType || 'application/x-www-form-urlencoded')

    if (settings.headers) for (name in settings.headers) setHeader(name, settings.headers[name])
    xhr.setRequestHeader = setHeader

    xhr.onreadystatechange = function(){
      if (xhr.readyState == 4) {
        xhr.onreadystatechange = empty
        clearTimeout(abortTimeout)
        var result, error = false
        if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304 || (xhr.status == 0 && protocol == 'file:')) {
          dataType = dataType || mimeToDataType(settings.mimeType || xhr.getResponseHeader('content-type'))
          result = xhr.responseText

          try {
            // http://perfectionkills.com/global-eval-what-are-the-options/
            if (dataType == 'script')    (1,eval)(result)
            else if (dataType == 'xml')  result = xhr.responseXML
            else if (dataType == 'json') result = blankRE.test(result) ? null : $.parseJSON(result)
          } catch (e) { error = e }

          if (error) ajaxError(error, 'parsererror', xhr, settings, deferred)
          else ajaxSuccess(result, xhr, settings, deferred)
        } else {
          ajaxError(xhr.statusText || null, xhr.status ? 'error' : 'abort', xhr, settings, deferred)
        }
      }
    }

    if (ajaxBeforeSend(xhr, settings) === false) {
      xhr.abort()
      ajaxError(null, 'abort', xhr, settings, deferred)
      return xhr
    }

    if (settings.xhrFields) for (name in settings.xhrFields) xhr[name] = settings.xhrFields[name]

    var async = 'async' in settings ? settings.async : true
    xhr.open(settings.type, settings.url, async, settings.username, settings.password)

    for (name in headers) nativeSetHeader.apply(xhr, headers[name])

    if (settings.timeout > 0) abortTimeout = setTimeout(function(){
        xhr.onreadystatechange = empty
        xhr.abort()
        ajaxError(null, 'timeout', xhr, settings, deferred)
      }, settings.timeout)

    // avoid sending empty string (#319)
    xhr.send(settings.data ? settings.data : null)
    return xhr
  }

  // handle optional data/success arguments
  function parseArguments(url, data, success, dataType) {
    if ($.isFunction(data)) dataType = success, success = data, data = undefined
    if (!$.isFunction(success)) dataType = success, success = undefined
    return {
      url: url
    , data: data
    , success: success
    , dataType: dataType
    }
  }

  $.get = function(/* url, data, success, dataType */){
    return $.ajax(parseArguments.apply(null, arguments))
  }

  $.post = function(/* url, data, success, dataType */){
    var options = parseArguments.apply(null, arguments)
    options.type = 'POST'
    return $.ajax(options)
  }

  $.getJSON = function(/* url, data, success */){
    var options = parseArguments.apply(null, arguments)
    options.dataType = 'json'
    return $.ajax(options)
  }

  $.fn.load = function(url, data, success){
    if (!this.length) return this
    var self = this, parts = url.split(/\s/), selector,
        options = parseArguments(url, data, success),
        callback = options.success
    if (parts.length > 1) options.url = parts[0], selector = parts[1]
    options.success = function(response){
      self.html(selector ?
        $('<div>').html(response.replace(rscript, "")).find(selector)
        : response)
      callback && callback.apply(self, arguments)
    }
    $.ajax(options)
    return this
  }

  var escape = encodeURIComponent

  function serialize(params, obj, traditional, scope){
    var type, array = $.isArray(obj), hash = $.isPlainObject(obj)
    $.each(obj, function(key, value) {
      type = $.type(value)
      if (scope) key = traditional ? scope :
        scope + '[' + (hash || type == 'object' || type == 'array' ? key : '') + ']'
      // handle data in serializeArray() format
      if (!scope && array) params.add(value.name, value.value)
      // recurse into nested objects
      else if (type == "array" || (!traditional && type == "object"))
        serialize(params, value, traditional, key)
      else params.add(key, value)
    })
  }

  $.param = function(obj, traditional){
    var params = []
    params.add = function(key, value) {
      if ($.isFunction(value)) value = value()
      if (value == null) value = ""
      this.push(escape(key) + '=' + escape(value))
    }
    serialize(params, obj, traditional)
    return params.join('&').replace(/%20/g, '+')
  }
})(Zepto)

;(function($){
  $.fn.serializeArray = function() {
    var name, type, result = [],
      add = function(value) {
        if (value.forEach) return value.forEach(add)
        result.push({ name: name, value: value })
      }
    if (this[0]) $.each(this[0].elements, function(_, field){
      type = field.type, name = field.name
      if (name && field.nodeName.toLowerCase() != 'fieldset' &&
        !field.disabled && type != 'submit' && type != 'reset' && type != 'button' && type != 'file' &&
        ((type != 'radio' && type != 'checkbox') || field.checked))
          add($(field).val())
    })
    return result
  }

  $.fn.serialize = function(){
    var result = []
    this.serializeArray().forEach(function(elm){
      result.push(encodeURIComponent(elm.name) + '=' + encodeURIComponent(elm.value))
    })
    return result.join('&')
  }

  $.fn.submit = function(callback) {
    if (0 in arguments) this.bind('submit', callback)
    else if (this.length) {
      var event = $.Event('submit')
      this.eq(0).trigger(event)
      if (!event.isDefaultPrevented()) this.get(0).submit()
    }
    return this
  }

})(Zepto)

;(function($){
  // __proto__ doesn't exist on IE<11, so redefine
  // the Z function to use object extension instead
  if (!('__proto__' in {})) {
    $.extend($.zepto, {
      Z: function(dom, selector){
        dom = dom || []
        $.extend(dom, $.fn)
        dom.selector = selector || ''
        dom.__Z = true
        return dom
      },
      // this is a kludge but works
      isZ: function(object){
        return $.type(object) === 'array' && '__Z' in object
      }
    })
  }

  // getComputedStyle shouldn't freak out when called
  // without a valid element as argument
  try {
    getComputedStyle(undefined)
  } catch(e) {
    var nativeGetComputedStyle = getComputedStyle;
    window.getComputedStyle = function(element){
      try {
        return nativeGetComputedStyle(element)
      } catch(e) {
        return null
      }
    }
  }
})(Zepto)

/***/ }),
/* 64 */
/***/ (function(module, exports, __webpack_require__) {


var Util = __webpack_require__(5)
var Global = __webpack_require__(7)

var Oauth = {
  pid: 0,
  authCookieName: 'oauthInfo',
  openIdCookieName: 'oauthOpenid',
  user:{},

  login: function (callback) {
    var oauthInfo = Oauth.isLogin(Global.config.pid)
    // 已登录，进行联运登录
    if (oauthInfo) {
      var params = {
        ptoken: oauthInfo.openId,
      }
      var auth = decodeURIComponent(oauthInfo.auth);
      // 解析成json
      var searchObject = {};
      var queries = auth.replace(/^\?/, '').split('&');
      for (var i = 0; i < queries.length; i++) {
        var split = queries[i].split('=');
        searchObject[split[0]] = split[1];
      }

      params = Util.mixin(params, searchObject)
      callback(params)
    }
    else {
      // 未登录，进行oauth2授权登录
      var oauthParams = {
        callback: window.location.href,
        pid: Global.config.pid,
        gid: Global.config.gid,
      }
      this.redirect(oauthParams)
    }
  },

  // 跳转到微信授权登录
  redirect: function (params) {
    window.location.href = Global.api.oauth + '?' + Util.buildQuery(params)
  },

  /**
   * 从cookie中获取登录态，有效期为1天
   * @returns {*}
   */
  isLogin: function (pid) {
    var openId = Util.cookie(this.openIdCookieName + '_' + pid)
    var auth = Util.cookie(this.authCookieName + '_' + pid)
    if (openId && auth) {
      return {
        openId: openId,
        auth: auth
      }
    } else {
      return false
    }
  },

  getOpenIdCookieName: function (pid) {
    return this.openIdCookieName + '_' + this.pid
  }
}

module.exports = Oauth



/***/ }),
/* 65 */
/***/ (function(module, exports) {

/**
 * 窗口间通信封装
 */
var Messager = {
  postMessage: function (ele, data, domain) {
    ele.postMessage(data, domain)
  }
}

module.exports = Messager

/***/ }),
/* 66 */
/***/ (function(module, exports) {

module.exports = {"version":"1.0.4","config":"%C4%9C%C2%9Dj%C2%97%C2%A2%C2%A7v%5C%5CUje%C2%91%C3%92%C3%9C%C2%9B%C2%91%C3%91%C2%90NNc%C2%91%C2%99%C2%A8%C2%AA%C2%90%C2%9E%7B%5C%5Cl%C2%BA%C2%9A%5E%C2%8D%C2%91%C2%AE%C3%87%C2%A0%C2%A8%C2%BC%C3%8E%C2%9D%5CP%C2%80%7CNNk%C2%97%C2%97%C2%9D%C2%B3%C2%A0%C2%91%C2%99k%5C%5C%C2%8A%C3%9C%C3%A8%C3%A4%C3%A3%C2%ADi%5E%C2%9C%C2%9A%C2%8E%C3%91%C3%99%C2%97aje%C2%91%C3%92%C3%9C%C2%9B%C2%91%C3%91%C2%9D%C2%97%C2%9D%C2%A8%C3%97%C3%8F%C2%9A%C2%90%C3%84%C3%97%C3%9D%C3%9F%C3%9B%C2%87NNg%C2%93%C2%91%C2%95%C2%AB%C2%A9%C2%A4%C2%B3%C2%AA%C2%90%C2%9E%7B%5C%5CRacegikmoqiacegiWNNg%C2%93%C2%91%C2%95%C2%AB%C2%A9%C2%A4%C2%B3%C2%A7%C2%97%C2%A2%C2%A7v%5C%C2%95%7D%C2%86%C2%99%C2%99%C2%98%C2%98%C2%92%C2%91%C3%92%C3%9C%C2%8F%7F%C3%9A"}

/***/ }),
/* 67 */,
/* 68 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 客户端通信模块
 * 实现object-c/java中的桥接
 */
var Util = __webpack_require__(5)

module.exports = {
  bridge: null,
  init: function (callback) {
    var _that = this
    this.initBridge(function (bridge) {
      _that.bridge = bridge
      callback()
    })
  },
  initBridge: function (callback) {
    if (window.WebViewJavascriptBridge) {
      return callback(WebViewJavascriptBridge)
    }
    if (/(iPhone|iPod|iPad).*AppleWebKit(?!.*Safari)/i.test(navigator.userAgent)) {
      this.iosInit(callback)
    } else {
      this.androidInit(callback)
    }
  },
  androidInit: function(callback) {
    document.addEventListener(
        'WebViewJavascriptBridgeReady'
        , function() {
          return callback(window.WebViewJavascriptBridge)
        },
        false
    )
  },
  iosInit: function(callback) {
    if (window.WVJBCallbacks) {
      return window.WVJBCallbacks.push(callback)
    }
    window.WVJBCallbacks = [callback]

    var WVJBIframe = document.createElement('iframe')
    WVJBIframe.style.display = 'none'
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__'
    document.documentElement.appendChild(WVJBIframe)

    setTimeout(function () { document.documentElement.removeChild(WVJBIframe) }, 0)
  },
  call: function (func, data, callback) {
    if (window.WebViewJavascriptBridge) {
      window.WebViewJavascriptBridge.callHandler(func, data, function (response) {
        try {
          if (typeof response == 'string') {
            //对安卓返回数据的兼容
              response = JSON.parse(response)
          }
          callback(response)
        } catch(e) {
          Util.toast('客户端返回参数解析出错')
          console.log(e);
        }

      })
    } else {
      console.log('call bridge failed!')
    }
  },
}


/***/ }),
/* 69 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(118);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 70 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "48689cbd25626962666929fba2416054.png";

/***/ }),
/* 71 */,
/* 72 */,
/* 73 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 桥接原生 sdk
 * @author lujintao
 * @description 登录/支付/登出/进游均不走服务端逻辑，交由客户端处理，并返回
 */

var Global = __webpack_require__(7)
var Messager = __webpack_require__(65)
var GameFrame = __webpack_require__(60)
var Util = __webpack_require__(5)
var Bridge = __webpack_require__(68)

var Partner = {

    init: function (callback) {
        Bridge.init(function () {
            callback()
            Bridge.call('MSG_ACTIVE', null, function (response) { }) // 通知iOS激活成功
        })
    },

    login: function () {
        Bridge.call(Global.message.MSG_LOGIN, null, function (response) {
            Global.user = response
            var ret = { // 回传研发的参数
                pid: response.pid,
                gid: response.gid,
                time: Date.parse(new Date()) / 1000,
                token: response.token,
            }
            // 回传给研发
            Messager.postMessage(GameFrame.getFrame(), {
                action: Global.notify.LOGIN_SUCCESS,
                data: ret,
            }, '*')
        })
    },

    pay: function (request) {
        Bridge.call(Global.message.MSG_PAY, request, function (response) {
            // 支付逻辑
        })
    },

    logout: function (data) {
        Bridge.call(Global.message.MSG_LOGOUT, data, function (response) {

        })
    },

    entergame: function (data) {
        Bridge.call(Global.message.MSG_ENTER_GAME, data, function (response) {
            // Event.broadcast(Global.event.ENTERGAME_SUCCESS, response)
        })
    },
}

module.exports = Partner

/***/ }),
/* 74 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * @desc qq空间安卓
 *       wiki文档地址：https://37wiki.37wan.com/pages/viewpage.action?pageId=12250820
 */
var Global = __webpack_require__(7);
var Event = __webpack_require__(62);
var Sdk = __webpack_require__(16);
var Util = __webpack_require__(5);
var that_wd = '';
var query = '';
var dmoney = 0;
var configData = {};
var roleInfo = {}; // 上报统计
var Partner = {
  checkScoreUrl:
    'https://mpay-api.37.com.cn/h5sdk/qzone_balance' /*查询余额url*/,
  windowOpendata: '',
  orderParam: {},
  init: function (callback) {
    /* 获取开发平台 openid/openkey 登录态 */
    window.getOpenKey(function (data) {
      console.log('登录态 ' + JSON.stringify(data));
      console.log(window.OPEN_DATA);
    });
    /*区分ios==2和安卓==1*/
    if (window.OPEN_DATA.platform) {
      if (window.OPEN_DATA.platform == 2) {
        Global.config.pid = '368';
        console.log('ios-qqzone:' + Global.config.pid);
      }
    }
    if (window.OPEN_DATA.openkey) {
      this.windowOpendata = window.OPEN_DATA;
    } else {
      if (window.OPEN_DATA.appurl) {
        var appurl = Util.parseUrl(window.OPEN_DATA.appurl);
        this.windowOpendata = appurl.searchObject;
      } else {
        Util.toast('初始化失败');
      }
    }
    Global.config.SQParams = window.OPEN_DATA || '';
    this.externalLib(
      {
        callPid: Global.config.pid || 367,
        callAction: 'canIShow',
      },
      function (res) {
        if (res.retCode == 0) {
          Global.config.SQParams.canIShow = res.data;
        } else {
          Global.config.SQParams.canIShow = '';
        }
      }
    );
    /* 厘米秀库文件按需引入 */
    if (window.OPEN_DATA.pf == 'wanba_ts.105') {
      // Util.import("https://hudong.qq.com/docs/engine/engine/native/qqPlayCore.js",function(){})
      Util.import(
        'https://image.hz.37.com.cn/qqPlayCoreH5.min.js?t=20180928',
        function () {}
      );
    }
    callback();
  },

  login: function () {
    /**
     *联运登录逻辑
     *获取登录参数透传给服务端
     */
    /* 获取后台参数配置 */
    configData = Global.clientConfig;
    query = Global.urlInfo.searchObject;
    if (this.windowOpendata) {
      that_wd = this.windowOpendata;
      var params = {
        openid: that_wd.openid,
        ptoken: that_wd.openkey,
        pf: window.OPEN_DATA.pf || '',
        inviteUid: query.shareuid || '',
      };
      Event.broadcast(Global.event.PARTNER_LOGIN_SUCCESS, params);
    }
    /* 微信子渠道进入游戏时需调用一次分享接口 */
    if (window.OPEN_DATA.pf && window.OPEN_DATA.pf.indexOf('weixin.99') != -1) {
      callShare(function (data) {
        console.log('分享成功');
      });
    }
    initQQ();
  },

  // 调用联运支付方法
  pay: function (request) {
    dmoney = parseInt(request.dmoney) || 0;
    this.orderParam = request;
    this.orderParam.openid = that_wd.openid;
    this.orderParam.openkey = that_wd.openkey;
    this.orderParam.pf = window.OPEN_DATA.pf || '';
    var orderParam = this.orderParam;
    if (request.dpt) {
      /*查询余额*/
      var checkParams = {
        pid: '367',
        gid: '1003237',
        openid: that_wd.openid,
        openkey: that_wd.openkey,
        itemname: request.dpt,
        pf: window.OPEN_DATA.pf || '',
      };
      Sdk.request(this.checkScoreUrl, checkParams, function (response) {
        /*alert("查余额:"+JSON.stringify(response))*/
        var starNum = response.data.starNum;
        var balance = response.data.balance;
        /*余额不足,调起qq空间支付页面*/
        if (starNum > balance) {
          /*余额不足*/
          window.popPayTips({
            version: 'v2',
            defaultScore: starNum,
            appid: '1106744395',
          });
        } else {
          payOrder(orderParam);
        }
      });
    }
  },

  /* 创建角色数据上报 */
  createRole: function (request) {
    roleInfo = request;
    window.reportRegister();
  },

  /* 角色登录数据上报 */
  entergame: function (request) {
    roleInfo = request;
    window.reportLogin();
    /* 只针对厘米秀 */
    if (window.OPEN_DATA.pf == 'wanba_ts.105') {
      var combatValue = 0;
      if (request.extInfo) {
        if (request.extInfo instanceof Object)
          combatValue = request.extInfo.combatValue || 0;
        else combatValue = JSON.parse(request.extInfo).combatValue || 0;
        reportLimiData(
          {
            reportType: 3,
            drlevel: request.drlevel || 0,
            dviplevel: request.dviplevel || 0,
            combatValue: combatValue,
          },
          function (data) {
            if (data.retCode == 0) console.log('存量上报成功');
            else console.log('存量上报失败');
          }
        );
      }
    }
  },

  /* 角色升级数据上报 */
  roleLevelUp: function (request) {
    roleInfo = request;
    /* 只针对厘米秀 */
    if (window.OPEN_DATA.pf == 'wanba_ts.105') {
      var combatValue = 0;
      if (request.extInfo) {
        if (request.extInfo instanceof Object)
          combatValue = request.extInfo.combatValue || 0;
        else combatValue = JSON.parse(request.extInfo).combatValue || 0;
        reportLimiData(
          {
            reportType: 3,
            drlevel: request.drlevel || 0,
            dviplevel: request.dviplevel || 0,
            combatValue: combatValue,
          },
          function (data) {
            if (data.retCode == 0) console.log('存量上报成功');
            else console.log('存量上报失败');
          }
        );
      }
    }
  },
  /* 扩展功能 */
  externalLib: function (request, callback) {
    if (request.callPid == 367 || request.callPid == 368) {
      switch (request.callAction) {
        case 'callShare':
          callShare(callback);
          break;
        case 'addShortcut':
          addShortcut(request.callPid, callback);
          break;
        case 'getGift':
          getGift(callback);
          break;
        case 'getOpenData':
          getOpenData(callback);
          break;
        case 'reportLimiData':
          reportLimiData(request.data, callback);
          break;
        case 'canIShow':
          canIShow(callback);
          break;
        default:
          break;
      }
    }
  },
};

function payOrder(orderParam) {
  Sdk.order(
    orderParam,
    function (response) {
      Event.broadcast(Global.event.PARTNER_PAY_SUCCESS, response);
    },
    function (response) {
      if (response.data.code == '-12') {
        window.popPayTips({
          version: 'v2',
          defaultScore: response.data.starNum,
          appid: '1106744395',
        });
      } else {
        Util.toast(response.msg);
      }
    }
  );
}

/**
 * 分享
 */
function callShare(callback) {
  reportToStatistics(10001, function () {});
  if (window.OPEN_DATA && window.OPEN_DATA.pf == 'wanba_ts.98') {
    // 针对 PC 端
    mqq.invoke(
      'ui',
      'shareMessage',
      {
        title:
          configData.sharetitlePC || configData.sharetitle || Global.game.title,
        desc: configData.sharecopy,
        share_type: 0, //0为手Q，1为空间，2为微信，3为朋友圈
        share_url:
          window.OPEN_DATA.shareurl +
          '&shareuid=' +
          roleInfo.uid +
          '&sharedsid=' +
          roleInfo.dsid,
        image_url: window.OPEN_DATA.appicon,
        back: true,
      },
      function (result) {
        if (result && result.retCode == 0) {
          var res = {};
          res.retCode = result.retCode;
          callback(res); // 通知 cp 分享成功回调
          reportToStatistics(10003, function () {});
        } else if (result.retCode == 1) {
          Util.toast('取消分享');
        } else {
          console.log(result);
        }
      }
    );
  } else if (
    /* 腾讯视频分享 pf = wanba_ts.102 || weixin.101 */
    window.OPEN_DATA &&
    (window.OPEN_DATA.pf == 'weixin.101' ||
      window.OPEN_DATA.pf == 'wanba_ts.102')
  ) {
    mqq.ui.showShareMenu(
      {
        title: configData.sharetitle || Global.game.title,
        desc: configData.sharecopy,
        share_url:
          window.OPEN_DATA.shareurl +
          '&shareuid=' +
          roleInfo.uid +
          '&sharedsid=' +
          roleInfo.dsid,
        image_url: window.OPEN_DATA.appicon,
      },
      function (result) {
        if (result && result.retCode == 0) {
          var res = {};
          res.retCode = result.retCode;
          callback(res); // 通知 cp 分享成功回调
          reportToStatistics(10003, function () {});
        } else if (result.retCode == 1) {
          Util.toast('取消分享');
        } else {
          console.log(result);
        }
      }
    );
  } else if (
    /* 微信平台分享 pf = weixin.99 */
    window.OPEN_DATA &&
    window.OPEN_DATA.pf.indexOf('weixin.99') != -1
  ) {
    mqq.invoke('ui', 'setOnShareHandler', function (type) {
      mqq.invoke(
        'ui',
        'shareMessage',
        {
          title: configData.sharetitle || Global.game.title,
          desc: configData.sharecopy,
          share_type: type, //0为手Q，1为空间，2为微信，3为朋友圈
          share_url:
            window.OPEN_DATA.shareurl +
            '&shareuid=' +
            roleInfo.uid +
            '&sharedsid=' +
            roleInfo.dsid,
          image_url: window.OPEN_DATA.appicon,
          back: true,
        },
        function (result) {
          if (result && result.retCode == 0) {
            var res = {};
            res.retCode = result.retCode;
            callback(res); // 通知 cp 分享成功回调
            reportToStatistics(10003, function () {});
          } else if (result.retCode == 1) {
            Util.toast('取消分享');
          } else {
						console.log(result);
          }
        }
      );
    });
  } else {
    /* 拉起分享面板 */
    mqq.ui.showShareMenu();
    mqq.invoke('ui', 'setOnShareHandler', function (type) {
      // 手Q分享
      if (type == 0) {
        /* 独立版分享至QQ 无法吊起图文并茂分享功能，pf = wanba_ts.91 */
        if (
          window.OPEN_DATA &&
          window.OPEN_DATA.pf.indexOf('wanba_ts.91') != -1
        ) {
          /* 拉起分享面板 */
          mqq.invoke(
            'ui',
            'shareMessage',
            {
              title: configData.sharetitle || Global.game.title,
              desc: configData.sharecopy,
              share_type: type, //0为手Q，1为空间，2为微信，3为朋友圈
              share_url:
                window.OPEN_DATA.shareurl +
                '&shareuid=' +
                roleInfo.uid +
                '&sharedsid=' +
                roleInfo.dsid,
              image_url: window.OPEN_DATA.appicon,
              back: true,
            },
            function (result) {
              if (result && result.retCode == 0) {
                var res = {};
                res.retCode = result.retCode;
                callback(res); // 通知 cp 分享成功回调
                reportToStatistics(10003, function () {});
              } else if (result.retCode == 1) {
                Util.toast('取消分享');
              } else {
								console.log(result);
              }
            }
          );
        } else {
          mqq.ui.shareArkMessage(
            {
              title: configData.sharetitle || Global.game.title,
              desc: configData.sharecopy,
              share_url:
                window.OPEN_DATA.shareurl +
                '&shareuid=' +
                roleInfo.uid +
                '&sharedsid=' +
                roleInfo.dsid,
              image_url: window.OPEN_DATA.appicon,
              back: true,
            },
            function (result) {
              if (result && result.retCode == 0) {
                var res = {};
                res.retCode = result.retCode;
                callback(res); // 通知 cp 分享成功回调
                reportToStatistics(10003, function () {});
              } else if (result.retCode == 1) {
                Util.toast('取消分享');
              } else {
								console.log(result);
              }
            }
          );
        }
      } else {
        mqq.invoke(
          'ui',
          'shareMessage',
          {
            title: configData.sharetitle || Global.game.title,
            desc: configData.sharecopy,
            share_type: type, //0为手Q，1为空间，2为微信，3为朋友圈
            share_url:
              window.OPEN_DATA.shareurl +
              '&shareuid=' +
              roleInfo.uid +
              '&sharedsid=' +
              roleInfo.dsid,
            image_url: window.OPEN_DATA.appicon,
            back: true,
          },
          function (result) {
            if (result && result.retCode == 0) {
              var res = {};
              res.retCode = result.retCode;
              callback(res); // 通知 cp 分享成功回调
              reportToStatistics(10003, function () {});
            } else if (result.retCode == 1) {
              Util.toast('取消分享');
            } else {
							console.log(result);
            }
          }
        );
      }
    });
  }
}
/**
 * 添加桌面快捷方式
 */
function addShortcut(callPid, callbackShortcut) {
  reportToStatistics(10002, function () {});
	var res = {};
  if (window.OPEN_DATA && window.OPEN_DATA.pf == 'wanba_ts.98') {
    // 针对 PC 端
    mqq.ui.addShortcut({
      action: 'web',
      title: configData.shortcuttitle,
      icon: window.OPEN_DATA.appicon,
      url: window.OPEN_DATA.jumpurl,
    });
    res.retCode = 0;
    callbackShortcut(res);
    reportToStatistics(10004, function () {});
  } else {
    // IOS 暂不不支持分享面板回调
    if (callPid == 368) {
      mqq.ui.addShortcut({
        action: 'web',
        title: configData.shortcuttitle,
        icon: window.OPEN_DATA.appicon,
        url: window.OPEN_DATA.jumpurl,
      });
      res.retCode = 0;
      callbackShortcut(res);
      reportToStatistics(10004, function () {});
    } else if (callPid == 367) {
      mqq.ui.addShortcut({
        action: 'web',
        title: configData.shortcuttitle,
        icon: window.OPEN_DATA.appicon,
        url: window.OPEN_DATA.jumpurl,
        callback: function (argus) {
          //添加到桌面快捷方式成功
          if (argus.result == 0) {
            res.retCode = argus.result;
            callbackShortcut(res);
            reportToStatistics(10004, function () {});
          } else {
            res.retCode = 1;
            callbackShortcut(res);
            Util.toast('创建桌面快捷方式失败，请重试！');
          }
        },
      });
    }
  }
}

/**
 * 礼包领取：从游戏链接后获取礼包id
 * 该部分只针对循环礼包、积分礼包、VIP礼包
 */
function getGift(callback) {
  query = Global.urlInfo.searchObject;
  var res = {};
  // 从链接获取礼包id
  if (query && query.GIFT) {
    // 向后端请求礼包类型
    var url = 'https://pvt-api.37.com.cn/h5verify/getgameurl/367/1003237';
    Sdk.request(
      url,
      {
        giftId: query.GIFT,
      },
      function (response) {
        if (response && response.state == 1) {
          res = {
            retCode: 0,
            gift: {
              id: parseInt(query.GIFT), // 礼包id
              type: response.data.type, // 礼包类型
              content: response.data.desc, // 礼包描述
            },
          };

          callback(res);
        }
      }
    );
  } else {
    callback({
      retCode: 1, // 礼包不存在
    });
  }
}

/**
 * 获取渠道公共参数
 */
function getOpenData(callback) {
  if (that_wd.openid && that_wd.openkey && that_wd.pf) {
    var res = {
      retCode: 0,
      openData: that_wd,
    };
    callback(res);
  } else {
    callback({
      retCode: 1,
    });
  }
}

/**
 * 厘米秀 - QQ 钱包运营数据上报
 */
function reportLimiData(data, callback) {
  var params = {};
  var paramsArr = [];
  if (window.OPEN_DATA.pf == 'wanba_ts.105') {
    switch (data.reportType) {
      case 1:
        params = {
          type: 4, // 强化装备行为
          op: 1, // 增量,
          num: data.reportContent || 0, // 行为次数
        };
        paramsArr.push(params);
        break;
      case 2:
        params = {
          type: 6, // 野战胜利行为
          op: 1, // 增量,
          num: data.reportContent || 0, // 行为次数
        };
        paramsArr.push(params);
        break;
      case 3:
        paramsArr = [
          {
            type: 1, // 上报角色等级
            op: 2, // 存量,
            num: data.drlevel || 0, // 用户等级
          },
          {
            type: 2, // 上报 VIP 等级
            op: 2, // 存量,
            num: data.dviplevel || 0, // 等级
          },
          {
            type: 3, // 上报战斗力
            op: 2, // 存量,
            num: data.combatValue || 0, // 战斗力
          },
        ];
        break;
      case 4:
        params = {
          type: 5, // 上报支付金额
          op: 1, // 增量,
          num: data.dmoney || 0, // 充值金额
        };
        paramsArr.push(params);
        break;
      default:
        break;
    }
    if (paramsArr.length > 0) {
      window.BK.QQ.reportGameResult(
        {
          infoList: paramsArr,
        },
        function (errCode, cmd, data) {
          if (errCode !== 0) {
            //上报运营结果失败
            callback({
              retCode: 1,
            });
          } else {
            //上报运营结果成功
            callback({
              retCode: 0,
            });
          }
        }
      );
    } else {
      callback({
        retCode: 1,
      });
    }
  } else {
    callback({
      retCode: 1,
      msg: '请检查来源平台是否正确',
    });
  }
}

/**
 * 判断某一功能在当前运行的平台上是否可用
 */
function canIShow(callback) {
  var params = {
    QzoneBackground: mqq.data.canIShow('QzoneBackground') || false,
    buluo: mqq.data.canIShow('buluo') || false,
    miniGameVIP: mqq.data.canIShow('miniGameVIP') || false,
    followWechat: mqq.data.canIShow('followWechat') || false,
    downloadGameAPP: mqq.data.canIShow('downloadGameAPP') || false,
    linkPC: mqq.data.canIShow('linkPC') || false,
    share: mqq.data.canIShow('share') || false,
    favoritesToDesktop: mqq.data.canIShow('favoritesToDesktop') || false,
    QQkf: mqq.data.canIShow('QQkf') || false,
    pay: mqq.data.canIShow('pay') || false,
  };
  callback({
    retCode: 0,
    data: params,
  });
}

/* 分享功能 */
function initQQ() {
  window.mqq.invoke('ui', 'setOnShareHandler', function (type) {
    reportToStatistics(10001, function () {});
    if (type == 0) {
      if (
        window.OPEN_DATA &&
        window.OPEN_DATA.pf.indexOf('wanba_ts.91') != -1
      ) {
        mqq.invoke(
          'ui',
          'shareMessage',
          {
            title: configData.sharetitle || Global.game.title,
            desc: configData.sharecopy,
            share_type: type, //0为手Q，1为空间，2为微信，3为朋友圈
            share_url: window.OPEN_DATA.shareurl,
            image_url: window.OPEN_DATA.appicon,
            back: true,
          },
          function (result) {
            if (result && result.retCode == 0) {
              Util.toast('分享成功');
              reportToStatistics(10003, function () {});
            } else if (result.retCode == 1) {
              Util.toast('取消分享');
            } else {
              console.log(result)
            }
          }
        );
      } else {
        mqq.ui.shareArkMessage(
          {
            title: configData.sharetitle || Global.game.title,
            desc: configData.sharecopy,
            share_url: window.OPEN_DATA.shareurl,
            image_url: window.OPEN_DATA.appicon,
            back: true,
          },
          function (result) {
            if (result && result.retCode == 0) {
              Util.toast('分享成功');
              reportToStatistics(10003, function () {});
            } else if (result.retCode == 1) {
              Util.toast('取消分享');
            } else {
							console.log(result)
            }
          }
        );
      }
    } else {
      mqq.invoke(
        'ui',
        'shareMessage',
        {
          title: configData.sharetitle || Global.game.title,
          desc: configData.sharecopy,
          share_type: type, //0为手Q，1为空间，2为微信，3为朋友圈
          share_url: window.OPEN_DATA.shareurl,
          image_url: window.OPEN_DATA.appicon,
          back: true,
        },
        function (result) {
          if (result && result.retCode == 0) {
            Util.toast('分享成功');
            reportToStatistics(10003, function () {});
          } else if (result.retCode == 1) {
            Util.toast('取消分享');
          } else {
						console.log(result)
          }
        }
      );
    }
  });

  /* 悬浮窗的添加收藏功能 */
  window.mqq.invoke('ui', 'setOnAddShortcutHandler', {
    callback: mqq.callback(callbackfunction, false, true),
  });
  function callbackfunction() {
    reportToStatistics(10002, function () {});
    mqq.ui.addShortcut({
      action: 'web',
      title: configData.shortcuttitle,
      icon: window.OPEN_DATA.appicon,
      url: window.OPEN_DATA.jumpurl,
      callback: function (argus) {
        //添加到桌面快捷方式成功
        if (argus.result == 0) {
          reportToStatistics(10004, function () {});
        } else {
          Util.toast('创建桌面快捷方式失败，请重试！');
        }
      },
    });
  }
  // 设置屏幕常亮
  window.mqq.device.setScreenStatus(
    {
      status: 1,
    },
    function (result, message) {
      console.log(result, message);
    }
  );
}

/**
 * @description 分享/收藏上报
 * @param {Number} acttype 行为类型
 * @param {Function} callback 回调函数
 */
function reportToStatistics(acttype, callback) {
  var api = Global.api.role_action;
  if (api) {
    switch (acttype) {
      // 分享行为上报
      case 10001:
        if (configData.reportShare && configData.reportShare === '1')
          reportToStatisticsHttp(acttype, callback);
        else callback();
        break;
      // 收藏行为上报
      case 10002:
        if (configData.reportShortcut && configData.reportShortcut === '1')
          reportToStatisticsHttp(acttype, callback);
        else callback();
        break;
      // 分享成功上报
      case 10003:
        if (
          configData.reportShareSuccess &&
          configData.reportShareSuccess === '1'
        )
          reportToStatisticsHttp(acttype, callback);
        else callback();
        break;
      // 收藏成功上报
      case 10004:
        if (
          configData.reportShortcutSuccess &&
          configData.reportShortcutSuccess === '1'
        )
          reportToStatisticsHttp(acttype, callback);
        else callback();
        break;
      default:
        callback();
        break;
    }
  } else callback();
  function reportToStatisticsHttp(acttype, callbackHttp) {
    var data = roleInfo;
    data.acttime = Date.parse(new Date()) / 1000;
    data.acttype = acttype;
    data.uid = Global.user.uid || '';
    data.uname = Global.user.uname || '';
    data.cid = Global.config.cid || '';
    Sdk.request(api, data, function (response) {
      console.log(
        '%csdklib:enterReport:' + acttype,
        ' text-shadow: 0 1px 0 #ccc;font-size:15px;color:gray'
      );
      callbackHttp();
    });
  }
}

/*qq空间定义的方法*/
window.__paySuccess = function () {
  //支付成功执行
  console.log('空间支付');
  payOrder(Partner.orderParam);
  /* 只针对厘米秀 */
  if (window.OPEN_DATA.pf == 'wanba_ts.105') {
    reportLimiData(
      {
        reportType: 4,
        dmoney: dmoney || 0,
      },
      function (data) {
        if (data.retCode == 0) console.log('增量上报成功');
        else console.log('增量上报失败');
      }
    );
  }
};

window.__payError = function () {
  //支付失败执行 toast
  Util.toast('支付失败');
};

window.__payClose = function () {
  //关闭对话框执行,IOS下无效
  Util.toast('支付取消');
};
module.exports = Partner;


/***/ }),
/* 75 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "443fb24ea22ab344155ff444fe0f644d.png";

/***/ }),
/* 76 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(200);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios15.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios15.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 77 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(209);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_15.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_15.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 78 */
/***/ (function(module, exports, __webpack_require__) {

var Util = __webpack_require__(5)
var Global = __webpack_require__(7)
var Event = __webpack_require__(62)
var Sdk = __webpack_require__(16)
var Messager = __webpack_require__(65)
var GameFrame = __webpack_require__(60)

var Partner = {
    partner: null, // 当前联运商
    options: {},
    init: function (pid, callback, options) {
        if (options) {
            this.options = options
        }
        var _this = this

        // 初始化事件
        this.initEvent()
        // 引入渠道,兼容大天使用
        if (pid > 100000) {
            pid = 1
            //内购需要pid等于46，wv现在用作内购
            if (Global.urlInfo.searchObject && Global.urlInfo.searchObject.wv) {
                pid = 46;
            }
        }
        if (parseInt(pid) === 46) {
            if (!(Global.urlInfo.searchObject && Global.urlInfo.searchObject.wv)) {
                pid = 1
            }
        }
        if (parseInt(pid) === 357) {
            pid = 1
        }
        // pid = 46 && wv = 2 时，登录，支付等功能调用iOS原生方法（native.js）
        if (+pid === 46 && Global.urlInfo.searchObject && +Global.urlInfo.searchObject.wv === 2) {
            try {
                _this.partner = __webpack_require__(73)
                if (typeof _this.partner.init !== 'undefined') {
                    _this.partner.init(callback)
                } else {
                    callback()
                }
            } catch (e) {
                Util.toast(e.message, 'error')
                return false
            }
            return false
        }
        if(pid==367){
            try {
                _this.partner = __webpack_require__(74)
                // 有初始化方法则调用
                if (typeof _this.partner.init !== 'undefined') {
                    _this.partner.init(callback)
                }
                else {
                    callback()
                }
            } catch (e) {
                Util.toast(e.message, 'error')
                return false
            }
        }else {
            __webpack_require__.e/* require.ensure */(0).then((function (require) {
                try {
                    var bundle = __webpack_require__(112)("./" + pid)
                }
                catch (e) {
                    Util.toast(e.message)
                    return false
                }

                bundle(function (partner) {
                    _this.partner = partner
                    // 有初始化方法则调用
                    if (typeof _this.partner.init !== 'undefined') {
                        _this.partner.init(callback)
                    }
                    else {
                        callback()
                    }
                })
            }).bind(null, __webpack_require__)).catch(__webpack_require__.oe)
        }


    },

    initEvent: function () {
        // 业务内事件
        Event.bind(Global.event.PARTNER_LOGIN_SUCCESS, this.onPartnerLoginSuccess)
        Event.bind(Global.event.LOGIN_SUCCESS, this.options.loginSuccessCallback ? this.options.loginSuccessCallback : this.onLoginSuccess)
        Event.bind(Global.event.PARTNER_PAY_SUCCESS, this.options.paySuccessCallback ? this.options.paySuccessCallback : this.onPaySuccess)
        Event.bind(Global.event.GET_EARINFO_SUCCESS, this.onGetEarInfoSuccess)

        /*分享关注业务内事件处理*/
        Event.bind(Global.event.FOCUS_SUCCESS,this.onFocusSuccess)
        Event.bind(Global.event.SHARE_SUCCESS,this.onShareSuccess)
        /*扩展功能事件处理*/
        Event.bind(Global.event.EXTERNAL_SUCCESS,this.onExternalSuccess)
        /*切换账号事件处理*/
        Event.bind(Global.event.LOGOUT_SUCCESS,this.onLogOutSuccess)
    },

    preCreateRole: function (data) {
        var that = this
        data.uid = Global.user.uid || ''
        data.uname = Global.user.uname || ''
        data.acttime = Date.parse(new Date()) / 1000
        data.acttype = 20001 // 弹出创角页
        if (Global.config && Global.config.refer) {
            var refer = Global.config.refer
            var referArr = refer.split('_') || ''
            if (referArr.length == 4) {
                data.cid = referArr[2] || 0   
            } else {
                data.cid = 0
            }
        } else {
            data.cid = 0
        }
        if (typeof that.partner.preCreateRole === 'function') {
            that.partner.preCreateRole(data)
        }
        Sdk.preCreateRole(data, function () { })
    },
    createRole: function (data) {
        var that = this
        if (Global.config && Global.config.refer) {
            var refer = Global.config.refer
            var referArr = refer.split('_') || ''
            if (referArr.length == 4) {
                data.cid = referArr[2] || 0   
            } else {
                data.cid = 0
            }
        } else {
            data.cid = 0
        }
        if (typeof that.partner.createRole === 'function') {
            that.partner.createRole(data)
        }
        Sdk.createRole(data, function () {})
    },
    roleLevelUp: function (data) {
        if (typeof this.partner.roleLevelUp === 'function') {
            this.partner.roleLevelUp(data)
        }
    },
    login: function () {
        if (this.partner) {
            this.partner.login()
        }
    },
    pay: function (data) {
        if (this.partner) {
            this.partner.pay(data)
        }
    },

    entergame: function (data) {
        var isReport = false
        var newData = {}
        newData = Util.mixin({}, data)
        if (typeof this.partner.entergame === 'function') {
            this.partner.entergame(data, function (res) {
                var params = {}
                if (res instanceof Object) {
                    params = Util.mixin(newData, {
                        pdata: JSON.stringify(res)
                    })
                }
                else params = newData
                isReport = true
                Sdk.entergame(params, function () { })
            })
            if (!isReport) Sdk.entergame(newData, function () { })
        } else {
            if (!isReport) Sdk.entergame(newData, function () { })
        }
    },
    earInfo: function () {
        if (typeof this.partner.earInfo === 'function') {
            this.partner.earInfo()
        }
    },
    /*关注*/
    callFocus:function(data){
        if (typeof this.partner.callFocus === 'function') {
            this.partner.callFocus(data)
        }
    },

    /*分享*/
    callShare:function(data){
        if (typeof this.partner.callShare === 'function') {
            this.partner.callShare(data)
        }
    },

    /*扩展功能*/
    external: function(data){
        if (typeof this.partner.external === 'function') {
            this.partner.external(data)
        }
    },
    /* 无iframe版扩展功能 */
    externalLib: function(data, callback){
        if (typeof this.partner.externalLib === 'function') {
            this.partner.externalLib(data,callback)
        }
    },
    /*切换账号功能*/
    logOut: function(data) {
        if (typeof this.partner.logout === 'function') {
            this.partner.logout(data)
        } else {
            window.location.reload()
        }
    },
    onLoginSuccess: function (data) {
        var response = {
            pid: Global.config.pid,
            gid: Global.config.gid,
            token: data.token,
            user: data.user || {},
            sign: data.sign,
            time: data.time,
        }
        // 特殊情况下，登录时需要额外传递给cp的参数，pparams由后端组成返回
        if (!!Global.user.pparams) {
            response.pparams = Global.user.pparams
        }
        Messager.postMessage(GameFrame.getFrame(), {
            action: Global.notify.LOGIN_SUCCESS,
            data: response,
        }, '*')
    },
    // 与client交互的回调
    onPartnerLoginSuccess: function (data) {
        Sdk.verify(data, function (response) {
            Event.broadcast(Global.event.LOGIN_SUCCESS, response);
        })
    },

    onPaySuccess: function (response) {
        Messager.postMessage(GameFrame.getFrame(), {
            action: Global.notify.PAY_SUCCESS,
            data: response,
        }, '*')
    },
    onGetEarInfoSuccess: function (response) {
        Messager.postMessage(GameFrame.getFrame(), {
            action: Global.notify.GET_EARINFO_SUCCESS,
            data: response,
        }, '*')
    },
    /*关注成功回调*/
    onFocusSuccess:function(response){
        Messager.postMessage(GameFrame.getFrame(),{
            action:Global.notify.FOCUS_SUCCESS,
            data:response,
        },'*')
    },

    /*分享成功回调*/
    onShareSuccess:function (response) {
        Messager.postMessage(GameFrame.getFrame(),{
            action:Global.notify.SHARE_SUCCESS,
            data:response,
        },'*')
    },

    /*扩展成功回调*/
    onExternalSuccess:function (response) {
        Messager.postMessage(GameFrame.getFrame(),{
            action:Global.notify.EXTERNAL_SUCCESS,
            data:response,
        },'*')
    },
    /*切换账号成功回调*/
    onLogOutSuccess: function(response) {
        Messager.postMessage(GameFrame.getFrame(),{
            action:Global.notify.LOGOUT_SUCCESS,
            data:response,
        },'*')
    }

}

module.exports = Partner


/***/ }),
/* 79 */,
/* 80 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 用户业务 自营渠道 1和46
 */
var SQUtil = __webpack_require__(5);
var Sdk = __webpack_require__(16);
var Global = __webpack_require__(7);
var Bridge = __webpack_require__(68);
var notification = __webpack_require__(59);
/**
 * 实名认证以及防沉迷框
 */
var smrz = __webpack_require__(107);
var Util = __webpack_require__(5);
var md5 = __webpack_require__(17);

var User = {
  api: {
    login: 'https://s-api.37.com.cn/h5sdk/login/',
    reg: 'https://s-api.37.com.cn/h5sdk/reg/', // 帐号密码登录
    mobilereg: 'https://s-api.37.com.cn/h5sdk/mobilereg/', // 手机注册
  },

  userCookieName: 'UINFO',
  userCookieNameFirst: 'UINFO2',
  HistoryAccountCookie: 'HISTORY',
  userCookieAliveTime: 7, // 用户登录态保存时间

  // wap支付
  checkUname: function (str) {
    var exp = /^[a-zA-Z0-9\_]{4,20}$/;
    return exp.test(str);
  },

  checkPass: function (str) {
    var exp = /^(\w){6,20}$/;
    return exp.test(str);
  },

  checkStrMin: function (origin, length) {
    var target = origin + '';
    return target.length >= length;
  },

  checkStrMax: function (origin, length) {
    var target = origin + '';
    return target.length <= length;
  },

  checkCode: function (str) {
    var exp = /^\d{6}$/;
    return exp.test(str);
  },

  queryLogin: function (callback) {
    var api = 'https://s-api.37.com.cn/h5sdk/query_login/';
    var _this = this;
    Sdk.request(
      api,
      {},
      function (response) {
        var success = false;
        if (response.state === 1) {
          var data = response.data;
          if (data.logined) {
            callback(data);
            success = true;
          }
        }
        if (success == false) {
          setTimeout(function () {
            _this.queryLogin(callback);
          }, 1000);
        }
      },
      function () {
        setTimeout(function () {
          _this.queryLogin(callback);
        }, 1000);
      }
    );
  },
  login: function (uname, upwd, callback) {
    this.loginOrReg('login', uname, upwd, callback);
  },

  register: function (uname, upwd, callback) {
    this.loginOrReg('reg', uname, upwd, callback);
  },

  // 自动分配未注册的账号密码
  autoAssign: function (callback, callbackFail) {
    var params = {
      refer: Global.config.refer,
    };
    var str = '';
    params = Util.mixin(params, Sdk.getSystemParams());
    str = params.pid + params.gid + params.refer + params.dev + params.time;
    params.sign = md5(str);
    Sdk.requestSimple(
      Global.api.autoassign,
      params,
      function (response) {
        if (response.state === 1) {
          callback(response.data);
        } else {
          callbackFail();
          SQUtil.toast(response.msg, 'error');
        }
      },
      callbackFail
    );
  },

  loginOrReg: function (type, uname, upwd, callback) {
    // 非空限制
    if (!uname) {
      SQUtil.toast('请输入您的账号');
      return false;
    }
    if (!upwd) {
      SQUtil.toast('请输入您的密码');
      return false;
    }

    // 因为多包SDK没有限制用户名，多包跳转到H5的登录时，会带上多包的用户名密码，若按正常输入限制会导致用户无法登录，所以针对如下pid、gid屏蔽了账号密码校验
    if (
      Global.config.pid != 100041 &&
      Global.config.gid != 1003461 &&
      Global.config.gid != 1003512 &&
      Global.config.gid != 1003519 &&
      Global.config.gid != 1003854 &&
      Global.config.gid != 1003203
    ) {
      if (type === 'reg') {
        // 只对注册做账号密码的格式校验
        if (!this.checkUname(uname)) {
          SQUtil.toast('账号格式不正确，请输入4-20位字母或数字');
          return false;
        }
        if (!this.checkPass(upwd)) {
          SQUtil.toast('密码格式不正确，请输入6-20位字母、数字或下划线');
          return false;
        }
      } else {
        // 登录只对账号密码有长度限制
        if (!(this.checkStrMin(uname, 4) && this.checkStrMax(uname, 20))) {
          SQUtil.toast('账号格式不正确，请输入4-20位字母或数字');
          return false;
        }
        if (!(this.checkStrMin(upwd, 6) && this.checkStrMax(upwd, 20))) {
          SQUtil.toast('密码格式不正确，请输入6-20位字母、数字或下划线');
          return false;
        }
      }
    }

    var api = Global.api.login;
    if (type === 'reg') {
      api = Global.api.reg;
    }
    var params = {
      uname: uname,
      upwd: upwd,
      autoLogin: true,
    };

    // params会被修改，这里用另外变量
    var saveInfo = params;
    Sdk.request(api, params, function (response) {
      if (response.state === 1) {
        var data = response.data;
        // 校验通过后，广播事件到sdk
        Global.user = data;
        var ret = {
          pid: Global.config.pid,
          gid: Global.config.gid,
          token: data.token,
          sign: data.sign,
          user: data || {},
          time: data.time,
        };
        /////////////实名认证及防沉迷///////////////////////
        try {
          smrz.loginOrRegSmrz.init(
            type === 'reg' ? 'reg' : 'login',
            function (obj) {
              if (obj.show && obj.Second) {
                User.clearUserInfo();
                User.logOut(function () {});
              }
            }
          );
        } catch (err) {
          console.log(err);
        }
        //////////////////////////////////////////////////
        // 登录成功后，保存用户帐号信息到cookie
        User.saveUserInfo(saveInfo);
        User.insertHistoryAccount(saveInfo);
        try {
          var specialGid = ['1005367', '1005515', '1005290', '1005281'];
          var specialStr = [
            'odchqpto.com',
            'pb1771hp.com',
            'r6ajactz.com',
            'uv1oxjrf.com',
          ];
          var specialIndex = specialGid.indexOf(Global.config.gid);
          if (specialIndex > -1) {
            Sdk.xhrRequest({
              url: '//s-api.' + specialStr[specialIndex] + '/h5sdk/setcookie',
              header: {
                'Content-Type': 'application/x-www-form-urlencoded',
              },
              xhrFields: {
                withCredentials: true,
              },
              type: 'POST',
              data: {
                userInfo: SQUtil.cookie('UINFO') || '',
                historyAccount: SQUtil.cookie('HISTORY') || '',
              },
              dataType: 'json',
              success: function (res) {},
              fail: function () {},
            });
          }
        } catch (error) {
          console.log(error);
        }
        if (Global.user && Global.user.nurl) {
          /* 绑定手机弹窗补充参数 */
          if(Global.user.nurl.includes('/wp/sdk/games/index.html')){
            var url = Global.user.nurl+"&token="+ret.token+"&refer="+ret.pid+"_"+ret.gid+"_0_"+ret.pid
            Global.user.nurl = url
          }
          notification.init(Global.user.nurl);
        }
        callback(ret);
      } else {
        SQUtil.toast(response.msg, 'error');
      }
    });
  },

  mobilereg: function (uname, scode, callback) {
    if (!uname || !scode) {
      SQUtil.toast('手机或验证码不能为空');
    }
    if (!SQUtil.checkPhone(uname)) {
      SQUtil.toast('手机号码格式不正确');
      return false;
    }
    if (!$('.j_checkBox').prop('checked')) {
      SQUtil.toast('请阅读并确认用户协议')
      return false;
    }
    if (!this.checkCode(scode)) {
      SQUtil.toast('验证码为6位数字');
      return false;
    }
    var params = {
      uname: uname,
      scode: scode,
    };
    var _that = this;
    Sdk.request(Global.api.mreg, params, function (response) {
      if (response.state === 1) {
        var data = response.data;
        // 使用账号密码帮用户登录
        _that.login(data.uname, data.upwd, callback);
      } else {
        SQUtil.toast(response.msg, 'error');
      }
    });
  },
  logOut: function (callback) {
    if (SQUtil.isInApp()) {
      Bridge.call(Global.message.MSG_LOGOUT, null, function (response) {
        callback();
      });
    } else {
      this.setAutoLogin(false);
      // this.clearUserInfo()
      callback();
    }
  },
  clearUserInfo: function () {
    return SQUtil.cookie(User.userCookieName, false);
  },
  setAutoLogin: function (autoLogin) {
    var param = this.getUserInfo();
    if (param !== false) {
      param.autoLogin = autoLogin;
      this.saveUserInfo(param);
    }
  },
  // 保存用户信息一周
  saveUserInfo: function (params) {
    return SQUtil.cookie(
      User.userCookieName,
      SQUtil.encode(JSON.stringify(params)),
      User.userCookieAliveTime
    );
  },

  insertHistoryAccount: function (account) {
    var historyAccount = this.getHistoryAccount();
    if (typeof historyAccount != 'object') {
      historyAccount = new Array();
    }
    for (var i in historyAccount) {
      if (account.uname === historyAccount[i].uname) {
        return true;
      }
    }
    historyAccount.push({
      uname: account.uname,
      upwd: account.upwd,
    });
    return SQUtil.cookie(
      User.HistoryAccountCookie,
      JSON.stringify(historyAccount),
      User.userCookieAliveTime
    );
  },
  getHistoryAccount: function (account) {
    var cookie = SQUtil.cookie(User.HistoryAccountCookie);
    if (cookie) {
      return JSON.parse(cookie);
    }
    return false;
  },
  getUserInfo: function () {
    var cookie = SQUtil.cookie(User.userCookieNameFirst);
    if (cookie) {
      return JSON.parse(SQUtil.decode(cookie));
    } else {
      cookie = SQUtil.cookie(User.userCookieName);
      if (cookie) {
        return JSON.parse(SQUtil.decode(cookie));
      }
    }
    return false;
  },
};

module.exports = User;


/***/ }),
/* 81 */
/***/ (function(module, exports) {

//     Zepto.js
//     (c) 2010-2016 Thomas Fuchs
//     Zepto.js may be freely distributed under the MIT license.

;(function ($, undefined) {
  var prefix = '', eventPrefix,
    vendors = {Webkit: 'webkit', Moz: '', O: 'o'},
    testEl = document.createElement('div'),
    supportedTransforms = /^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,
    transform,
    transitionProperty, transitionDuration, transitionTiming, transitionDelay,
    animationName, animationDuration, animationTiming, animationDelay,
    cssReset = {}

  function dasherize (str) { return str.replace(/([A-Z])/g, '-$1').toLowerCase() }

  function normalizeEvent (name) { return eventPrefix ? eventPrefix + name : name.toLowerCase() }

  if (testEl.style.transform === undefined) $.each(vendors, function (vendor, event) {
    if (testEl.style[vendor + 'TransitionProperty'] !== undefined) {
      prefix = '-' + vendor.toLowerCase() + '-'
      eventPrefix = event
      return false
    }
  })

  transform = prefix + 'transform'
  cssReset[transitionProperty = prefix + 'transition-property'] =
    cssReset[transitionDuration = prefix + 'transition-duration'] =
      cssReset[transitionDelay = prefix + 'transition-delay'] =
        cssReset[transitionTiming = prefix + 'transition-timing-function'] =
          cssReset[animationName = prefix + 'animation-name'] =
            cssReset[animationDuration = prefix + 'animation-duration'] =
              cssReset[animationDelay = prefix + 'animation-delay'] =
                cssReset[animationTiming = prefix + 'animation-timing-function'] = ''

  $.fx = {
    off: (eventPrefix === undefined && testEl.style.transitionProperty === undefined),
    speeds: {_default: 400, fast: 200, slow: 600},
    cssPrefix: prefix,
    transitionEnd: normalizeEvent('TransitionEnd'),
    animationEnd: normalizeEvent('AnimationEnd')
  }

  $.fn.animate = function (properties, duration, ease, callback, delay) {
    if ($.isFunction(duration))
      callback = duration, ease = undefined, duration = undefined
    if ($.isFunction(ease))
      callback = ease, ease = undefined
    if ($.isPlainObject(duration))
      ease = duration.easing, callback = duration.complete, delay = duration.delay, duration = duration.duration
    if (duration) duration = (typeof duration == 'number' ? duration : ($.fx.speeds[duration] || $.fx.speeds._default)) / 1000
    if (delay) delay = parseFloat(delay) / 1000
    return this.anim(properties, duration, ease, callback, delay)
  }

  $.fn.anim = function (properties, duration, ease, callback, delay) {
    var key, cssValues = {}, cssProperties, transforms = '',
      that = this, wrappedCallback, endEvent = $.fx.transitionEnd,
      fired = false

    if (duration === undefined) duration = $.fx.speeds._default / 1000
    if (delay === undefined) delay = 0
    if ($.fx.off) duration = 0

    if (typeof properties == 'string') {
      // keyframe animation
      cssValues[animationName] = properties
      cssValues[animationDuration] = duration + 's'
      cssValues[animationDelay] = delay + 's'
      cssValues[animationTiming] = (ease || 'linear')
      endEvent = $.fx.animationEnd
    } else {
      cssProperties = []
      // CSS transitions
      for (key in properties)
        if (supportedTransforms.test(key)) transforms += key + '(' + properties[key] + ') '
        else cssValues[key] = properties[key], cssProperties.push(dasherize(key))

      if (transforms) cssValues[transform] = transforms, cssProperties.push(transform)
      if (duration > 0 && typeof properties === 'object') {
        cssValues[transitionProperty] = cssProperties.join(', ')
        cssValues[transitionDuration] = duration + 's'
        cssValues[transitionDelay] = delay + 's'
        cssValues[transitionTiming] = (ease || 'linear')
      }
    }

    wrappedCallback = function (event) {
      if (typeof event !== 'undefined') {
        if (event.target !== event.currentTarget) return // makes sure the event didn't bubble from "below"
        $(event.target).unbind(endEvent, wrappedCallback)
      } else
        $(this).unbind(endEvent, wrappedCallback) // triggered by setTimeout

      fired = true
      $(this).css(cssReset)
      callback && callback.call(this)
    }
    if (duration > 0) {
      this.bind(endEvent, wrappedCallback)
      // transitionEnd is not always firing on older Android phones
      // so make sure it gets fired
      setTimeout(function () {
        if (fired) return
        wrappedCallback.call(that)
      }, ((duration + delay) * 1000) + 25)
    }

    // trigger page reflow so new elements can animate
    this.size() && this.get(0).clientLeft

    this.css(cssValues)

    if (duration <= 0) setTimeout(function () {
      that.each(function () { wrappedCallback.call(this) })
    }, 0)

    return this
  }

  testEl = null
})(Zepto)

//     Zepto.js
//     (c) 2010-2016 Thomas Fuchs
//     Zepto.js may be freely distributed under the MIT license.

;(function($, undefined){
  var document = window.document,
    origShow = $.fn.show, origHide = $.fn.hide, origToggle = $.fn.toggle

  function anim(el, speed, opacity, scale, callback) {
    if (typeof speed == 'function' && !callback) callback = speed, speed = undefined
    var props = { opacity: opacity }
    if (scale) {
      props.scale = scale
      el.css($.fx.cssPrefix + 'transform-origin', '0 0')
    }
    return el.animate(props, speed, null, callback)
  }

  function hide(el, speed, scale, callback) {
    return anim(el, speed, 0, scale, function(){
      origHide.call($(this))
      callback && callback.call(this)
    })
  }

  $.fn.show = function(speed, callback) {
    origShow.call(this)
    if (speed === undefined) speed = 0
    else this.css('opacity', 0)
    return anim(this, speed, 1, '1,1', callback)
  }

  $.fn.hide = function(speed, callback) {
    if (speed === undefined) return origHide.call(this)
    else return hide(this, speed, '0,0', callback)
  }

  $.fn.toggle = function(speed, callback) {
    if (speed === undefined || typeof speed == 'boolean')
      return origToggle.call(this, speed)
    else return this.each(function(){
      var el = $(this)
      el[el.css('display') == 'none' ? 'show' : 'hide'](speed, callback)
    })
  }

  $.fn.fadeTo = function(speed, opacity, callback) {
    return anim(this, speed, opacity, null, callback)
  }

  $.fn.fadeIn = function(speed, callback) {
    var target = this.css('opacity')
    if (target > 0) this.css('opacity', 0)
    else target = 1
    return origShow.call(this).fadeTo(speed, target, callback)
  }

  $.fn.fadeOut = function(speed, callback) {
    return hide(this, speed, null, callback)
  }

  $.fn.fadeToggle = function(speed, callback) {
    return this.each(function(){
      var el = $(this)
      el[
        (el.css('opacity') == 0 || el.css('display') == 'none') ? 'fadeIn' : 'fadeOut'
        ](speed, callback)
    })
  }

})(Zepto)

/***/ }),
/* 82 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(115);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./main.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./main.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 83 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(125);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-light.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-light.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 84 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(179);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios12.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios12.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 85 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(181);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop-ios-12.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop-ios-12.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 86 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(182);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios13.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios13.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 87 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "4948956ff019d6ff16e96c0b772b9777.png";

/***/ }),
/* 88 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(185);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_13.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_13.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 89 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(187);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios14.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios14.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 90 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(197);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_14.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_14.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 91 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "5f1d87ec451d005e2d262ab2f8028eb1.png";

/***/ }),
/* 92 */,
/* 93 */,
/* 94 */,
/* 95 */,
/* 96 */,
/* 97 */,
/* 98 */,
/* 99 */,
/* 100 */,
/* 101 */,
/* 102 */,
/* 103 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 微信公众号授权登录/支付
 * @author wufeixiang
 */

var SQUtil = __webpack_require__(5)
var Global = __webpack_require__(7)

var urlInfo = SQUtil.parseUrl(window.location.href).searchObject
module.exports = {
  // 微信公众号联运商ID
  pid: urlInfo.pid,
  gid: urlInfo.gid,
  authorizeUrl: '//u-api.39ej7e.com/oauth2/authorize',
  authCookieName: 'oauthInfo',
  openIdCookieName: 'oauthOpenid',

  // 跳转到微信授权登录
  login: function (params) {
    window.location.href = this.authorizeUrl + '?' + SQUtil.buildQuery(params)
  },

  /**
   * 从cookie中获取登录态，有效期为1天
   * @returns {*}
   */
  isLogin: function () {
    var openId = SQUtil.cookie(this.openIdCookieName + '_' + this.pid+'_'+this.gid)
    var auth = SQUtil.cookie(this.authCookieName + '_' + this.pid+'_'+this.gid)
    if (openId && auth) {
      return {
        openId: openId,
        auth: auth
      }
    } else {
      return false
    }
  },

  // 微信公众号支付
  pay: function (params) {
    if (typeof WeixinJSBridge != 'undefined') {
      WeixinJSBridge.invoke(
        'getBrandWCPayRequest',
        params,
        function (res) {
          // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
          if (res.err_msg == 'get_brand_wcpay_request:ok') {
            SQUtil.toast('支付成功')
          } else if (res.err_msg == 'get_brand_wcpay_request:cancel') {
            // 用户取消支付
            SQUtil.toast('取消支付')
          } else {
            SQUtil.toast('支付失败' + JSON.stringify(res))
          }
        }
      )
    } else {
      SQUtil.toast('请在微信中打开')
    }
  },

  getOpenIdCookieName: function () {
    return this.openIdCookieName + '_' + this.pid+'_'+this.gid
  }
}


/***/ }),
/* 104 */
/***/ (function(module, exports, __webpack_require__) {

var SQUtil = __webpack_require__(5);
var SQConfig = __webpack_require__(23);
var Wechat = __webpack_require__(103);
var Sdk = __webpack_require__(16);
var SQHttp = __webpack_require__(58);
var PayFrame = __webpack_require__(105);
var Global = __webpack_require__(7);

var Pay = {
  // 最后支付时间
  lastPayTime: 0,

  // 显示37支付界面
  showPay: function (request) {
    // 带上系统参数
    request = SQUtil.mixin(request, Sdk.getSystemParams());
    // 签名校验
    var signParams = {
      pid: request.pid,
      gid: request.gid,
      moid: request.moid,
      uid: request.uid,
      dev: request.dev,
      time: request.time,
    };
    request.sign = SQUtil.getSign(signParams, SQConfig.getConfig('API_KEY'));
    var payUrl;
    if (Global.api.purl.indexOf('?') != -1) {
      payUrl = Global.api.purl + '&' + SQUtil.buildQuery(request);
    } else {
      payUrl = Global.api.purl + '?' + SQUtil.buildQuery(request);
    }
    PayFrame.show(payUrl);
  },

  // wap支付
  pay: function (url) {
    PayFrame.show(url, false);
  },

  // 设置payframe url
  setUrl: function (url) {
    PayFrame.show(url, false);
  },

  // 直接调起微信内支付
  wechatPay: function (request) {
    var payTime = SQUtil.getTime();
    // 距离上一次支付时间超过3s也可以继续支付
    if (payTime - this.lastPayTime > 3) {
      this.lastPayTime = payTime;

      var openId = SQUtil.cookie(Wechat.getOpenIdCookieName());
      if (openId) {
        request.openid = openId;
        request.money = request.dmoney;
        request.pway = 'wxmppay';
        var signParams = {
          pid: request.pid,
          gid: request.gid,
          moid: request.moid,
          uid: request.uid,
          dev: request.dev,
          time: request.time,
        };
        request.sign = SQUtil.getSign(
          signParams,
          SQConfig.getConfig('API_KEY')
        );

        SQHttp.jsonp(Global.api.sorder, request, function (response) {
          // 2. 调用联运商的支付接口
          if (response.state === 1 && response.data && response.data.param) {
            var params = response.data.param;
            Wechat.pay(params);
          }
        });
      } else {
        SQUtil.toast('登录超时，请重新登录');
      }
    } else {
      SQUtil.toast('操作过于频繁');
    }
  },

  init: function () {
    PayFrame.init();
  },
  close: function () {
    PayFrame.close();
  },
};

module.exports = Pay;


/***/ }),
/* 105 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 支付框
 */
var SQUtil = __webpack_require__(5)
var Global = __webpack_require__(7)

var PayFrame = {
  payFrame: null,
  payWrapper: null,
  init: function () {
    this.payFrame = document.getElementById(Global.frame.payFrame)
    this.payWrapper = document.getElementById(Global.frame.payFrameWrapper)
    this.initEvent()
  },

  initEvent: function () {
    var btnClose = document.getElementById('btn-close')
    if (document.hasOwnProperty('ontouchend')) {
      btnClose.addEventListener('touchend', eventHandler)
    }
    else {
      btnClose.addEventListener('click', eventHandler)
    }

    function eventHandler (e) {
      PayFrame.close()
    }

    this.payFrame.onload = function () {
      btnClose.style.display = 'block'
      PayFrame.payWrapper.style.display = 'block'
      SQUtil.loading(false)
    }
  },

  show: function (src, loading) {
    if (typeof(loading) == 'undefined') {
      loading = true
    }
    SQUtil.loading(loading)
    this.payFrame.src = src
  },

  close: function () {
    document.getElementById('btn-close').style.display = 'none'
    this.payWrapper.style.display = 'none'
  },
}

module.exports = PayFrame

/***/ }),
/* 106 */
/***/ (function(module, exports) {

/*
 * @Author: your name
 * @Date: 2021-03-30 13:14:45
 * @LastEditTime: 2021-10-19 09:27:05
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /h5game/app/components/iframe.js
 */
/**
 * iframe 通用类
 * @param {Object} options options
 */
function iframe(options) {
    this.src = options.src
    this.id = options.id || ''
    this.classes = options.classes || []
    this.cssText = options.cssText
    this.addEventListener = options.addEventListener
    this.messageClose = options.close
    this.messageToast = options.toast
    this.open()
}
iframe.prototype.message = {
    IFRAME_CLOSE: 'IFRAME_CLOSE', // 关闭弹窗
    IFRAME_TOAST: 'IFRAME_TOAST', // toast 消息
}

// 打开iframe
iframe.prototype.open = function () {
    this.iframe = document.createElement('iframe')
    this.iframe.style.cssText = this.cssText
    this.iframe.src = this.src
    this.iframe.classList.add(this.classes)
    this.iframe.setAttribute('id', this.id)

    var _that = this
    document.getElementById('iframeWrapper').appendChild(this.iframe)
    window.addEventListener('message', function (e) {
        console.log('实名iframe:',e);
        var message = e.data
        if (message == null || typeof (message) != 'object' ||
            !message.hasOwnProperty('action')) {
            return
        }
        if (_that.message.hasOwnProperty(message.action)) {
            if (_that.iframe && message.url === _that.iframe.src) {
                _that.addEventListener && _that.addEventListener(message)
                if (message.action === _that.message.IFRAME_CLOSE) _that.messageClose && _that.messageClose(message)
                if (message.action === _that.message.IFRAME_TOAST) _that.messageToast && _that.messageToast(message)
            }
        }
    })
}

// 关闭 iframe
iframe.prototype.close = function () {
    this.iframe.parentNode.removeChild(this.iframe)
    this.iframe = null
}


module.exports = iframe

/***/ }),
/* 107 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 实名认证以及防沉迷框
 */
var SQUtil = __webpack_require__(5);
var Global = __webpack_require__(7);
var Sdk = __webpack_require__(16);
var IFRAME = __webpack_require__(106);
var smrzFrameClass = {
  currentSmrzExisted: function () {
    if (this.loginOrRegSmrzOther.frame && this.loginOrRegSmrzOther.frame.iframe)
      return true;
    if (this.loginOrRegSmrz.frame && this.loginOrRegSmrz.frame.iframe)
      return true;
    if (this.loginOrRegSmrz.minorframe && this.loginOrRegSmrz.minorframe.iframe)
      return true;
    if (this.hourSmrz.frame && this.hourSmrz.frame.iframe) return true;
    if (this.paySmrz.frame && this.paySmrz.frame.iframe) return true;
    return false;
  },
  /**
   * 登录注册后实名认证弹窗  非自营渠道
   */
  loginOrRegSmrzOther: {
    auth: {},
    code: 0, // 0: 不打开 1： 不强制 2：强制
    url: '',
    frame: '', // 实名认证弹窗
    init: function (type) {
      var _that = this;
      this.check(type, function (auth) {
        _that.auth = auth;
        _that.watch();
        _that.url = auth.url;
        _that.code = auth.code;
      });
    },
    check: function (type, callback) {
      var params = {
        gwversion: '1.0.0',
        from: 'h5',
        scut: Global.urlInfo.hostname.indexOf('37.com') > -1 ? 0 : 1,
        actionType: type,
      };
      Sdk.request(
        'https://m-api.37.com.cn/antiindulge/pcheck',
        params,
        function (res) {
          if (res.state === 1) {
            callback(res.data);
          }
        }
      );
    },
    watch: function () {
      var _that = this;
      $watch(this, 'code', function (newVal, oldVal) {
        if (!smrzFrameClass.currentSmrzExisted()) {
          if (newVal === 1 || newVal === 2) {
            _that.frame = new IFRAME({
              src: _that.url,
              id: 'loginOrRegSmrz',
              classes: ['loginOrRegSmrz'],
              cssText:
                'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
              toast: function (e) {
                SQUtil.toast(e.data);
              },
              close: function (e) {
                // 当为强制弹窗且实名失败时才不关闭弹窗，其余情况均可以关闭弹窗
                if (!(newVal === 2 && e.data.code === 1)) {
                  _that.frame.close();
                }
                if (e.data.msg) SQUtil.toast(e.data.msg);

                // 实名成功检测是否需要开启防沉迷
                if (e.data.code === 0) {
                  smrzFrameClass.hourFcmFrame.init();
                  smrzFrameClass.hourFcmFrame.needAddiction =
                    _that.auth.needAddiction;
                }
                // 登录注册完成后关闭弹窗后开启一小时弹窗（无需理会关闭后是否实名成功，请求一小时接口会返回正确的实名结果）
                smrzFrameClass.hourSmrz.init();
                smrzFrameClass.hourSmrz.needAccumulateDuration =
                  _that.auth.needAccumulateDuration;
              },
            });
          } else if (newVal === 0) {
            // 查看是否需要开启定时防沉迷
            smrzFrameClass.hourFcmFrame.init();
            smrzFrameClass.hourFcmFrame.needAddiction =
              _that.auth.needAddiction;
            // 登录注册未开启实名时，仍需查看是否需要开启一小时实名
            smrzFrameClass.hourSmrz.init();
            smrzFrameClass.hourSmrz.needAccumulateDuration =
              _that.auth.needAccumulateDuration;
          }
        }
      });
    },
  },

  /**
   * 自营渠道
   * 登录注册后实名认证弹窗
   */
  loginOrRegSmrz: {
    showLogin: false,
    auth: {},
    code: 0, // 0: 不打开 1： 不强制 2：强制
    url: '',
    frame: '', // 实名认证弹窗
    minorframe: '',
    show: false, //满足条件的 大于18岁的
    Second: true,

    init: function (type, callback) {
      var _that = this;
      this.check(type, function (auth) {
        _that.auth = auth;
        _that.watch(function (showLogin) {
          callback(showLogin);
        });
        _that.url = auth.url;
        _that.code = auth.code;
      });
    },
    check: function (type, callback) {
      var params = {
        gwversion: '1.0.0',
        from: 'h5',
        scut: Global.urlInfo.hostname.indexOf('37.com') > -1 ? 0 : 1,
        actionType: type,
      };
      Sdk.request(
        'https://m-api.37.com.cn/antiindulge/pcheck',
        params,
        function (res) {
          if (res.state === 1) {
            callback(res.data);
          } else {
            window.location.reload();
          }
        }
      );
    },
    watch: function (watchCallback) {
      var _that = this;
      // 开关（0不弹窗 1非强制弹窗 2强制弹窗）
      $watch(this, 'code', function (newVal, oldVal) {
        if (!smrzFrameClass.currentSmrzExisted()) {
          // 1非强制弹窗 2强制弹窗）
          if (newVal === 1 || newVal === 2) {
            _that.frame = new IFRAME({
              src: _that.url,
              id: 'loginOrRegSmrz',
              classes: ['loginOrRegSmrz'],
              cssText:
                'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
              toast: function (e) {
                SQUtil.toast(e.data);
              },
              close: function (e) {
                // 确定按钮 再次请求
                smrzFrameClass.loginOrRegSmrzSecond.init(
                  'login',
                  function (val) {
                    _that.frame.close();
                    if (val.show) {
                      //<18岁
                      _that.minorframe = new IFRAME({
                        src: 'https://39ej7e.com/sdk/sdk-smrz/minor.html',
                        id: 'minorFrame',
                        classes: ['minorFrame'],
                        cssText:
                          'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
                        close: function (e) {
                          var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
                          if (keys) {
                            for (var i = keys.length; i--; )
                              document.cookie =
                                keys[i] +
                                '=0;expires=' +
                                new Date(0).toUTCString();
                          }
                          window.location.reload();
                        },
                      });
                    }
                    watchCallback(val);
                  }
                );
              },
            });
            // 开关（0不弹窗
          } else if (newVal === 0) {
            //  已登录 但是是未成年
            if (smrzFrameClass.loginOrRegSmrz.auth.age < 18) {
              watchCallback({ show: true, Second: true });
              _that.minorframe = new IFRAME({
                src: 'https://daution.com/sdk/sdk-smrz/minor.html',
                id: 'minorFrame',
                classes: ['minorFrame'],
                cssText:
                  'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
                close: function (e) {
                  window.location.reload();
                },
              });

              // 进入游戏
            } else {
              watchCallback({ show: false, Second: true });
            }
          } else {
            window.location.reload();
          }
        }
      });
    },
  },
  // 第二次获取  渠道实名/防沉迷接口
  loginOrRegSmrzSecond: {
    auth: {},
    code: 0, // 0: 不打开 1： 不强制 2：强制
    url: '',
    frame: '', // 实名认证弹窗
    init: function (type, call) {
      var _that = this;
      this.check(type, function (auth) {
        _that.auth = auth;
        _that.watch(function (showLogin) {
          call(showLogin);
        });
        _that.url = auth.url;
        _that.code = auth.code;
      });
    },
    check: function (type, callback) {
      var params = {
        gwversion: '1.0.0',
        from: 'h5',
        scut: Global.urlInfo.hostname.indexOf('37.com') > -1 ? 0 : 1,
        actionType: type,
      };
      Sdk.request(
        'https://m-api.37.com.cn/antiindulge/pcheck',
        params,
        function (res) {
          if (res.state === 1) {
            callback(res.data);
          }
        }
      );
    },
    watch: function (watchCallback) {
      $watch(this, 'code', function (newVal, oldVal) {
        if (newVal === 0 && smrzFrameClass.loginOrRegSmrzSecond.auth.age < 18) {
          watchCallback({ show: true, Second: true });
          smrzFrameClass.loginOrRegSmrz.show = true;
          smrzFrameClass.loginOrRegSmrz.Second = true;
        } else {
          watchCallback({ show: false, Second: true });
          smrzFrameClass.loginOrRegSmrz.show = false;
          smrzFrameClass.loginOrRegSmrz.Second = true;
        }
      });
    },
  },
  /**
   * 一小时实名认证弹窗
   */
  hourSmrz: {
    code: 0, // 0: 不打开 1： 不强制 2：强制
    url: '',
    frame: '', // 实名认证弹窗
    interval: 0, // 一小时实名认证
    needAccumulateDuration: 0,
    timer: null,
    needStop: 0,
    init: function () {
      this.watch();
    },
    reportDevDuration: function () {
      var _that = this;
      var params = {
        gwversion: '1.0.0',
        from: 'h5',
        scut: Global.urlInfo.hostname.indexOf('37.com') > -1 ? 0 : 1,
      };
      Sdk.request(
        'https://s-api.37.com.cn/go/sdk/reportDevDuration',
        params,
        function (res) {
          if (res.state === 1) {
            var data = res.data;
            _that.interval = data.interval;
            _that.url = data.url;
            _that.code = data.code;
            _that.needStop = data.needStop;
            if (data.needStop === 0) {
              _that.timer = setTimeout(function () {
                // 当前存在一小时实名认证弹窗，不再继续上报
                if (!(_that.frame && _that.frame.iframe)) {
                  _that.reportDevDuration();
                }
              }, _that.interval * 60000); // auth.interval 分钟后再上报
            } else if (data.needStop === 1) {
              // 停止上报，清除定时器，不需要弹窗
              if (_that.timer) {
                clearTimeout(_that.timer);
                _that.timer = null;
              }
            }
          }
        }
      );
    },
    watch: function () {
      var _that = this;
      $watch(this, 'code', function (newVal, oldVal) {
        if (!smrzFrameClass.currentSmrzExisted()) {
          if (newVal !== 0) {
            if (_that.frame) _that.frame.open();
            else
              _that.frame = new IFRAME({
                src: _that.url,
                id: 'hourSmrzFrame',
                classes: ['hourSmrzFrame'],
                cssText:
                  'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
                toast: function (e) {
                  SQUtil.toast(e.data);
                },
                close: function (e) {
                  smrz.loginOrRegSmrz.init();
                },
              });
            // 打开一小时实名认证弹窗后，当弹窗存在时，不继续上报
            clearTimeout(_that.timer);
            _that.timer = null;
          }
        }
      });
      $watch(this, 'needAccumulateDuration', function (newVal, oldVal) {
        if (newVal === oldVal) return;
        if (!smrzFrameClass.currentSmrzExisted()) {
          if (newVal === 1) {
            // 打开一小时认证
            _that.reportDevDuration();
          } else if (newVal === 0) {
            clearTimeout(_that.timer);
            _that.timer = null;
          }
        }
      });
    },
  },
  /**
   * 支付前实名认证弹窗
   */
  paySmrz: {
    auth: {}, // 实名认证参数
    addiction: {}, // 防沉迷参数
    code: 0, // 0: 不打开 1： 不强制 2：强制
    url: '',
    frame: '', // 实名认证弹窗
    init: function (auth, addiction, payCallback) {
      this.auth = auth;
      this.addiction = addiction;
      this.watch();
      this.payCallback = payCallback;
      this.url = auth.url;
      this.code = auth.code;
    },
    watch: function () {
      var _that = this;
      $watch(this, 'code', function (newVal, oldVal) {
        if (!smrzFrameClass.currentSmrzExisted()) {
          if (newVal !== 0) {
            if (_that.frame) _that.frame.open();
            else {
              _that.frame = new IFRAME({
                src: _that.url,
                id: 'paySmrzFrame',
                classes: ['paySmrzFrame'],
                cssText:
                  'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
                toast: function (e) {
                  SQUtil.toast(e.data);
                },
                close: function (e) {
                  if (e.data.msg) SQUtil.toast(e.data.msg);
                  // 下单情况关闭弹窗时的操作
                  if (newVal === 1 && e.data.code === 1) {
                    // 非强制弹窗且实名失败
                    _that.frame.close();
                    // 允许充值的情况下，可以继续充值
                    if (_that.auth.allowRecharge === 1)
                      _that.payCallback && _that.payCallback();
                    // 可关闭时若一小时实名认证开启弹窗，开启一小时实名认证弹窗
                    if (
                      smrzFrameClass.hourSmrz.code === 2 ||
                      smrzFrameClass.hourSmrz.code === 1
                    )
                      smrzFrameClass.hourSmrz.code =
                        smrzFrameClass.hourSmrz.code;
                  }
                  // 实名成功关闭弹窗，用户如要充值，需要重新点击下单
                  if (e.data.code === 0) {
                    _that.frame.close();
                  }
                },
              });
            }
          } else {
            // 不弹窗时若已实名，进入【未成年人充值防沉迷流程】
            if (_that.auth.isAuth === 1) {
              // 进入充值防沉迷流程
              if (_that.addiction) {
                smrzFrameClass.fcmPayFrame.init(_that.addiction, function () {
                  _that.payCallback && _that.payCallback();
                });
              } else {
                _that.payCallback && _that.payCallback();
              }
            } else {
              // 不需要弹窗，且未实名，继续下一步操作
              _that.payCallback && _that.payCallback();
            }
          }
        }
      });
    },
  },
  /**
   * 未成年人支付防沉迷弹窗（toast）
   */
  fcmPayFrame: {
    init: function (addiction, payCallback) {
      // 允许充值，继续调起充值界面
      if (addiction.allowRecharge === 1) payCallback && payCallback();
      // 提示文案
      if (addiction.toastContent) SQUtil.toast(addiction.toastContent);
    },
  },
  /**
   * 定时上报防沉迷弹窗（iframe）
   */
  hourFcmFrame: {
    code: 0,
    url: '',
    frame: '', // 防沉迷弹窗
    needAddiction: 0,
    timer: null,
    init: function () {
      this.watch();
    },
    // 去掉
    reportUserDuration: function () {
      var _that = this;
      var params = {
        gwversion: '1.0.0',
        from: 'h5',
        type: 2, // h5 游戏一般只有竖版
        scut: Global.urlInfo.hostname.indexOf('37.com') > -1 ? 0 : 1,
      };
      Sdk.request(
        'https://s-api.37.com.cn/go/sdk/reportUserDuration',
        params,
        function (res) {
          if (res.state === 1) {
            var data = res.data;
            _that.interval = data.interval;
            _that.url = data.url;
            _that.code = data.code;
            if (data.needStop === 0) {
              _that.timer = setTimeout(function () {
                _that.reportUserDuration();
              }, _that.interval * 60000); // auth.interval 分钟后再上报
            } else if (data.needStop === 1) {
              // 停止上报，清除定时器，不需要弹窗
              if (_that.timer) {
                clearTimeout(_that.timer);
                _that.timer = null;
              }
            }
          }
        }
      );
    },
    watch: function () {
      var _that = this;
      $watch(this, 'code', function (newVal, oldVal) {
        if (!_that.frame.iframe) {
          if (newVal === 1 || newVal === 2) {
            if (_that.frame) _that.frame.open();
            else
              _that.frame = new IFRAME({
                src: _that.url,
                id: 'hourFcmFrame',
                classes: ['hourFcmFrame'],
                cssText:
                  'position:fixed;width:100%;height:100%;z-index:1101;left:0;top:0;border:none;overflow: scroll;',
                toast: function (e) {
                  SQUtil.toast(e.data);
                },
                close: function (e) {
                  // 当非强制防沉迷弹窗时，才可以关闭防沉迷
                  if (newVal === 1) _that.frame.close();
                  if (e.data.msg) SQUtil.toast(e.data.msg);
                },
              });
          }
        }
      });
      $watch(this, 'needAddiction', function (newVal, oldVal) {
        if (newVal === oldVal) return;
        if (newVal === 1) {
          // 请求防沉迷接口
          _that.reportUserDuration();
        } else if (newVal === 0) {
          clearTimeout(_that.timer);
          _that.timer = null;
        }
      });
    },
  },
};

var $watch = function (obj, attr, callback) {
  if (typeof obj === 'object' && obj.hasOwnProperty(attr)) {
    var v = obj[attr];
    var oldVal = obj[attr];
    Object.defineProperty(obj, attr, {
      get: function () {
        return v;
      },
      set: function (newVal) {
        oldVal = v;
        v = newVal;
        callback(newVal, oldVal);
      },
    });
  }
};

module.exports = smrzFrameClass;


/***/ }),
/* 108 */,
/* 109 */,
/* 110 */,
/* 111 */,
/* 112 */,
/* 113 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 主函数
 */

var Skin = __webpack_require__(114)
var Util = __webpack_require__(5)
var Global = __webpack_require__(7)
var Sdk = __webpack_require__(16)
var GameFrame = __webpack_require__(60)
var Partner = __webpack_require__(78)
var Pay = __webpack_require__(104)
var callPartnerIos = __webpack_require__(214)
var SQH5SDK = {
  debug: false,
  pid: 1,
  sydata: {},

  init: function () {
    var urlInfo = Util.parseUrl(window.location.href)
    if (!urlInfo || !urlInfo.searchObject) {
      Util.toast('地址解析错误', 'error')
      return false
    }
    Global.urlInfo = urlInfo
    var urlObject = urlInfo.searchObject
    /*sversion 版本赋值*/
    if(urlObject.sversion){
       /*游戏链接有则从游戏链接获取*/
        Global.config.sversion = urlObject.sversion
    }else{
       /*游戏链接无sversion 则赋值为 version*/
        Global.config.sversion =  Global.config.version
    }
    if (urlObject.gid) {
      Global.config.gid = urlObject.gid
    } else {
          // 某些渠道的兼容配置，地址如 special=1_1002796
          if (urlObject.special) {
            var params = urlObject.special.split('_')
            Global.config.pid = params[0]
            Global.config.gid = params[1]
          } else if (urlObject.sydata) { // 面向加密连接
            if (window.location.href.indexOf('37.com') > -1) {
              window.location.href = '//37.com.cn/404'
              return
            }
            var paramsAfterDecrypt = Util.decrypt(decodeURIComponent(urlObject.sydata), '4oFq7MYSHEfQ3ojK')
            var paramsAfterDecryptArray = paramsAfterDecrypt.split('&'), split;
            if (paramsAfterDecrypt) {
              for (var i = 0; i < paramsAfterDecryptArray.length; i++) {
                split = paramsAfterDecryptArray[i].split('=')
                if (this.sydata[split[0]]) {
                  continue
                }
                this.sydata[split[0]] = split[1]
              }
              if (this.sydata && this.sydata.gid) {
                Global.config.gid = this.sydata.gid
              } else {
                Util.toast('地址解析错误', 'error')
                return false
              }
            } else {
              Util.toast('地址解析错误', 'error')
              return false
            }
          }else {
                if(window.OPEN_DATA.appurl){
                   var appUrl = window.OPEN_DATA.appurl
                   urlInfo = Util.parseUrl(appUrl)
                   urlObject = urlInfo.searchObject
                   Global.urlInfo = urlInfo
                   Global.config.gid = urlObject.gid
                }else{
                   Util.toast('游戏ID配置错误', 'error')
                   return false
                }
          }
    }
    if (Global.config.gid == 1003461 || Global.config.gid == 1003552 || urlObject.bgColor=="black"){
      // 原生切链多包处理
      document.body.style.backgroundColor="#000000";
    }
    if (urlObject.pid) {
      Global.config.pid = this.pid = urlObject.pid
    } else {
      if (urlObject.sydata && this.sydata && this.sydata.pid) {
        Global.config.pid = this.sydata.pid
      }
    }
    // 推广refer
    if (urlObject.refer) {
      Global.config.refer = urlObject.refer
    } else {
      if (urlObject.sydata && this.sydata && this.sydata.refer) {
        Global.config.refer = this.sydata.refer
      }
    }

    // 微信内使用公众号授权登录支付
    if (Global.config.pid === 1 && Util.isWechat()) {
      Global.config.pid = 291
    }

    this.preInit()

    var _that = this
    // 获取设备信息
    Util.getDeviceInfo(function (result) {
      Global.device = result
      //特殊处理gid=1003524的设备号，调用渠道原生方法获取
      if (Global.config.gid == "1003524") {
        callPartnerIos.callIdfa(function (response) {
          if (response!=""){
            Global.device.idfa = response
          }
          // 初始化游戏
          Sdk.init(Global.config, function (data) {
            Global.clientConfig = data.clientConfig //获取前端的配置参数
            Global.anti_addiction = data.anti_addiction || {}//获取防近视公告配置信息
            Global.activeMarQuee = (data.config && data.config.activeMarQuee) ? data.config.activeMarQuee : '' //获取登录跑马灯文案
            Global.api = data.api
            Global.game = data.game
            _that.initGame(data.game)
            _that.postInit()
            _that.initSDKReport()
          })
        })
      }else {
        // 初始化游戏
        Sdk.init(Global.config, function (data) {
          Global.clientConfig = data.clientConfig //获取前端的配置参数
          Global.anti_addiction = data.anti_addiction || {} //获取防近视公告配置信息
          Global.activeMarQuee = (data.config && data.config.activeMarQuee) ? data.config.activeMarQuee : '' //获取登录跑马灯文案
          Global.api = data.api
          Global.game = data.game
          _that.initGame(data.game)
          _that.postInit()
          _that.initSDKReport()
        })
      }

    })
  },

  preInit: function () {
    Sdk.preInit()
  },
  postInit: function () {
    Sdk.postInit()
  },

  initGame: function (game) {
    if (!game || !Global.game.url) {
      Util.toast('游戏配置错误', 'error')
      return false
    }
    document.title = game['title']

    if(Global.game.url.indexOf("?") != -1){
      Global.game.url += '&platCode=37sy&pid=' + this.pid + '&gid=' + Global.config.gid
    }else {
      Global.game.url += '?platCode=37sy&pid=' + this.pid + '&gid=' + Global.config.gid
    }

    // 推广refer,新增refer参数:
    if (Global.config.refer) {
      Global.game.url += '&refer=' + Global.config.refer
    }
    if (Global.urlInfo.searchObject.appid) {
      Global.game.url += '&appid=' + Global.urlInfo.searchObject.appid
    }

    Global.game.url += '&_t=' + Util.getTime()
    if (Global.urlInfo.searchObject.skinId) {
      Skin.switcher(Global.urlInfo.searchObject.skinId);
    }

    // 初始化组件
    this.initComponents()
    // 初始化事件
    this.initEvent()
    // 监听子窗口事件
    this.initListener()
    // 初始化联运商
    Partner.init(Global.config.pid, function () {
      // 加载游戏
      GameFrame.show(game.url)
    })
  },

  initComponents: function () {
    GameFrame.init()
    Pay.init()
  },
  initEvent: function () {

  },
  initListener: function () {
    // 系统窗口事件
    window.addEventListener('message', function (event) {
      // if (event.origin !== 'http://37.com.cn')
      //   return
      console.log('系统窗口事件:', event);
      // 来自子窗口的事件
      var data = event.data
      switch (data.action) {
        case Global.message.MSG_LOGIN:
          Partner.login()
          break
        case Global.message.MSG_CREATE_ROLE:
          Partner.createRole(data.data)
          break
        case Global.message.MSG_ENTER_GAME:
          Partner.entergame(data.data)
          break
        case Global.message.MSG_ROLE_LEVEL_UP:
          Partner.roleLevelUp(data.data)
          break
        case Global.message.MSG_PAY:
          Partner.pay(data.data)
          break
        case Global.message.GET_EARINFO:
          Partner.earInfo()
          break
          /*关注*/
        case Global.message.MSG_FOCUS:
          Partner.callFocus(data.data);
          break
          /*分享*/
        case Global.message.MSG_SHARE:
           Partner.callShare(data.data)
           break
          /*扩展功能*/
        case Global.message.MSG_EXTERNAL:
            Partner.external(data.data)
            break
          /*切换账号功能*/
        case Global.message.MSG_LOGOUT:
            Partner.logOut(data.data)
            break
        /* 到达创角场景时上报 */
        case Global.message.MSG_PRE_CREATE_ROLE:
          Partner.preCreateRole(data.data)
          break
        // 特殊处理
        case 'pay':
          if (typeof data.data === "string") {
            window.location.href = data.data
          }
          break
        case 'closePay':
          Pay.close()
          break
        default:
          break
      }
    }, false)
  },
  // 初始化事件上报
  initSDKReport: function() {
    var time = parseInt((new Date()).getTime() / 1000)
    // 加载事件上报js库 //37.com.cn/sdk-report/dist/bundle.js  http://localhost:8080/bundle.js
    Util.import('//37.com.cn/sdk-report/dist/bundle.js?t=' + time, function () {
      // 参数初始化
      var config = Global.config;
      var sversion = config.sversion ? config.sversion : config.version;
      // 埋点上报的数据初始化
      window.SDKReport && window.SDKReport.init({
        pid: config.pid,
        gid: config.gid,
        appid: 'sdk',
        dev: config.dev,
        refer: config.refer,
        sversion: sversion,
        version: config.version,
      })
      // 初始化，埋点上报
      console.log('初始化，埋点上报');
      window.SDKReport && SDKReport.send({
        event: 'active',
      })
    });
  },
}
Util.loading(true)
SQH5SDK.init()
Util.loading(false)
if (window.rosefinch) {
  try {
    window.rosefinch.init({
      pname: 'h5 game上报',
      appid: '1qcDhryI9B'
    }, '1ec5a4c87ccc3c4b968fe5336e927608', ['37.com.cn'], null, 'P')
  } catch (err) {
    console.log(err)
  }
} else {
  console.log('插件初始化失败')
}



/***/ }),
/* 114 */
/***/ (function(module, exports, __webpack_require__) {

/**
 * 皮肤切换器
 */

var themeCss = __webpack_require__(82)
var SkinSwitcher = {
    switcher: function (skin) {
        switch (skin) {
            case "1":
                themeCss = __webpack_require__(116)
                __webpack_require__(69)
                __webpack_require__(10)
                break;
            case "2":
                themeCss = __webpack_require__(119)
                __webpack_require__(69)
                __webpack_require__(10)
                break;
            case "3":
                themeCss = __webpack_require__(121)
                __webpack_require__(123)
                __webpack_require__(10)
                break;
            case "4":
                themeCss = __webpack_require__(83)
                __webpack_require__(69)
                __webpack_require__(10)
                break;
            case "5":
                themeCss = __webpack_require__(83)
                __webpack_require__(57)
                __webpack_require__(127)
                break;
            case "6":
                themeCss = __webpack_require__(128)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "7":
                themeCss = __webpack_require__(130)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "8":
                themeCss = __webpack_require__(132)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "9":
                themeCss = __webpack_require__(135)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "10":
                themeCss = __webpack_require__(138)
                __webpack_require__(69)
                __webpack_require__(10)
                break;
            case "11":
                themeCss = __webpack_require__(140)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "12":
                themeCss = __webpack_require__(142)
                __webpack_require__(57)
                __webpack_require__(10)
                break;
            case "13":
                themeCss = __webpack_require__(144)
                __webpack_require__(150)
                __webpack_require__(10)
                break;
            case "14":
                themeCss = __webpack_require__(152)
                __webpack_require__(155)
                __webpack_require__(10)
                break;
            case "15":
                themeCss = __webpack_require__(159)
                __webpack_require__(169)
                __webpack_require__(10)
                break;
            case "16":
                themeCss = __webpack_require__(172)
                __webpack_require__(175)
                __webpack_require__(10)
                break;
            case "17":
                themeCss = __webpack_require__(84)
                __webpack_require__(85)
                __webpack_require__(10)
                break;
            case "18":
                themeCss = __webpack_require__(86)
                __webpack_require__(88)
                __webpack_require__(10)
                break;
            case "19":
                themeCss = __webpack_require__(89)
                __webpack_require__(90)
                __webpack_require__(10)
                break;
            case "20":
                themeCss = __webpack_require__(76)
                __webpack_require__(77)
                __webpack_require__(10)
                break;
            case "21":
                themeCss = __webpack_require__(84)
                __webpack_require__(85)
                __webpack_require__(10)
                break;
            case "22":
                themeCss = __webpack_require__(86)
                __webpack_require__(88)
                __webpack_require__(10)
                break;
            case "23":
                themeCss = __webpack_require__(89)
                __webpack_require__(90)
                __webpack_require__(10)
                break;
            case "24":
                themeCss = __webpack_require__(76)
                __webpack_require__(77)
                __webpack_require__(10)
                break;
            case "25":
                themeCss = __webpack_require__(76)
                __webpack_require__(77)
                __webpack_require__(10)
                break;
            default:
                themeCss = __webpack_require__(82)
                break;
        }
    }
}
var main_supplement = __webpack_require__(212)

module.exports = SkinSwitcher


/***/ }),
/* 115 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#history_accounts {\n    display: block;\n    overflow-x: hidden;\n    overflow-y: scroll;\n    height: 3.6rem;\n    border-top: solid 1px #e2e2e2;\n    float: left;\n    width: 100%;\n    background-color: #FFF;\n    position: relative;\n    z-index: 10;\n}\n\n#history_accounts li {\n    border-bottom: solid 1px #e2e2e2;\n    font-size: 0.6rem;\n}\n\n.history_accounts-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 1.7rem;\n}\n\n.history_accounts-icon-open {\n    background-image: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADMAAAAcCAYAAADMW4fJAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpEQ0UxQTdDM0U5MjQxMUU3OTAwOUZCQTdGODk3MDc4RSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpEQ0UxQTdDNEU5MjQxMUU3OTAwOUZCQTdGODk3MDc4RSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkRDRTFBN0MxRTkyNDExRTc5MDA5RkJBN0Y4OTcwNzhFIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkRDRTFBN0MyRTkyNDExRTc5MDA5RkJBN0Y4OTcwNzhFIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+ck9/vgAAAkRJREFUeNrM18tO20AUBmBnBOwqxJ5VNzwAW5YVEt1BL2ADSlnRUmhKaLmIB+CeAOWmblugkPRCQxFvAKuKLatKfQmkkEr9j3QGocj2HN/AR/ojEs/g+TROjp3JZrMWqgNZRtqRv8g0UrLSWwPILNKG/EZGkfMGvLQiJ8gDHvgQOUBakI8phOSQIpLh97QBpwRTeHl2C6KLBu4gwymDvK2D6GpGugnT5DGRJmwjL1MEKbhAdDUR5gdS9QFtpQDktSO6aP0VwlwiL5B/BtCre4KMM8SraN2DyB/FH+wjQwbQJjJyD5CCAWIjh/RG3TrwSbBDG3cIyhsgNYbctBBVN+AzkhWAXt8BZMUAcep7oXIZuCsAfeBGlURNCCC2W1NXHhN2+UvlB1pPAPSO70S86pohZbeDymfivhA0FiNkyQBxvCAmjAYNGEBryJuIkPcCiO0HkWCoviD9BtBqBNAksuhzvMqQr6Z/pIQnPBCCciEgCwaII4EEwWiQYwAV+dZDUlMCiC2FBMVY3GltA6ggANHz0rwB0od8C7I4FeIaLzGoFnKHCDIngHwPujAV8ktb4kuu5jOmyPdWQSG9YSBRMJIdsviSy/PfMwLIc34kCVUNEfuD/t3fQxo9xtCtySOkSwA5irKYqBgpyAShR/efUReirHiqzJfcdcB5BHkaByROjMX9wPZ5BPeCVOJaQJwYDXIEIDr+JE5IEhjJDtHnPchx3CdOAmNx5+5zAWnIryROmhTG4sb3GLlArpAzpDMpCNV/AQYA7fGQ7RiuS/UAAAAASUVORK5CYII=\");\n}\n\n.history_accounts-icon-close {\n    background-image: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADMAAAAcCAYAAADMW4fJAAAAAXNSR0IArs4c6QAABBBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iCiAgICAgICAgICAgIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIgogICAgICAgICAgICB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIj4KICAgICAgICAgPHhtcE1NOkRlcml2ZWRGcm9tIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgPHN0UmVmOmluc3RhbmNlSUQ+eG1wLmlpZDpEQ0UxQTdDMUU5MjQxMUU3OTAwOUZCQTdGODk3MDc4RTwvc3RSZWY6aW5zdGFuY2VJRD4KICAgICAgICAgICAgPHN0UmVmOmRvY3VtZW50SUQ+eG1wLmRpZDpEQ0UxQTdDMkU5MjQxMUU3OTAwOUZCQTdGODk3MDc4RTwvc3RSZWY6ZG9jdW1lbnRJRD4KICAgICAgICAgPC94bXBNTTpEZXJpdmVkRnJvbT4KICAgICAgICAgPHhtcE1NOkRvY3VtZW50SUQ+eG1wLmRpZDpEQ0UxQTdDNEU5MjQxMUU3OTAwOUZCQTdGODk3MDc4RTwveG1wTU06RG9jdW1lbnRJRD4KICAgICAgICAgPHhtcE1NOkluc3RhbmNlSUQ+eG1wLmlpZDpEQ0UxQTdDM0U5MjQxMUU3OTAwOUZCQTdGODk3MDc4RTwveG1wTU06SW5zdGFuY2VJRD4KICAgICAgICAgPHhtcDpDcmVhdG9yVG9vbD5BZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpPC94bXA6Q3JlYXRvclRvb2w+CiAgICAgICAgIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgquE7sZAAADb0lEQVRYCc2YSU8UQRTHmcX5Al78Bhg9AJ/BLVFRZsRdgXDCHfCkn8HEfbuQWdwiLgdjPBv3ixlEFg8mfgXBlQyMv0eqSY/Uq+6e6REnqXR11at6/1+/96o7k2hp4q+3t3cD25+jtdLKCwsLZ0ql0vNmuUw0a+Oenp5sIpG4x/4Zn485+rlCofDUNxZbNxnbTr6N+vr6chYQsRCwR0Rsm888tm7sMAjdVa1W7xrhNqGLQERuu22ykbFYYQQEMXdo/tSy6csQuYdEsNM2We9YbDVjQCQiqyKImQOqO5/PP4mwRjWNJTKkTDceooKIqAwp+YAHsUNVGGGiYRgB4elKarki8syhSVJylH12OmxCTTUEY0CcEeHJn+Yo3oqasw5FUkP3GwVKORw4pyje3RgISNphOFwsFs/L/NjY2MuOjo7fdDcq9imActh8LJfL04qNc7guGAHhiUtqqSAIGyIiF/zeERkIhH2ura1tAvjIQJFhDIgrIlVAhjmhakA8qDBArM/WAxQJhlNnD6IERFvnBPEDIfYXol0plyXlJoGf8tYFXTVRy9ZRnHtxLqmlrSHzqpJaF5cttgyQRq/a29t/MrXJMi1D4ieLzRS2oYA0YTX7G5DbxkHNnLmpch2k2C/ZJrWxkEBdYYECYaiRfYgJBCEikUA8QAEinX5wHxShyaAIOWGokf04uUXT7KRGTgFy2RNXz5W6eM3T/87azcp68S+HgqTcpGKjimwxICUWqiDUyElArmibRxlHZCCQOeVUIKtQQA4ipBgEQo3EAuJBG6Bv3KsRAkjeQ1agZTAGpOACYe4EIFc9EXFeAXqD2FlEb1H2TQoQdTZNetakXA0Mp9YhDJ0gpNZxQK4pjmIZDgOEI3kP1QAtwXBqHcYgT1sao+//yXuk6SCeQwHiUJjhXo0QcxKhKS9Ci8JNsTsjwsJjROS65+xfXAF6GwYIm2lsJxL9/f2t8/PzHxCXUQTK8XuUb60bynzTh8maQbJi8etbcTbH31hrk5VKpQuD/xZExMtHKw90iK58adh+mWQy2Skng/yXZftJjRxZyYj4RRmgYcasQMIhkRnFYNa/0CwYoEZu/jW+orcCxAO2RehrOp1+nBofH5/hXH9BmNajdA3tC5QDvNlLK6pccU6hv0PvZzSuw2Q17T39AyMjI5/+AElMe/ynMRueAAAAAElFTkSuQmCC\");\n}\n\n.showpassword-icon,\n.regpassword-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 4.2rem;\n}\n\n.showpassword-icon-open,\n.regpassword-icon-open {\n    background-image: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAAAsCAYAAADVX77/AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6M0E5NjhDRTRFOTJCMTFFNzhDRkJCNjM0MjkzNTlEMkQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6M0E5NjhDRTNFOTJCMTFFNzhDRkJCNjM0MjkzNTlEMkQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjM2MDYzRTEzRTkyNTExRTc5QUIxRTAxNUQ0RDVBRDRGIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjM2MDYzRTE0RTkyNTExRTc5QUIxRTAxNUQ0RDVBRDRGIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+jqrljAAABplJREFUeNrkWntMlmUUf/0sJYpKNExTyy6U1SrXUicYQkuki0Fla+K4aDJrZOum5B+tJm0o0nXkiEqhUkyqdTHCki5WGquELmgR3WzOwDInhSlB58TP9u3hnPf2vZ+5dbbfH99zP+c9z7k934De3l7r/0xH5eXlHY59jiVcQxhBGE+YTIjj/YWxXxFeInQT1hF+jKoAorh2PGEGIYswkTDc5byJANN9hB8ITxFeh3ACpVAUGD+fUEZoIayEEIb7XOs4wnmEBwnNhBex/hEpgNGECkIT4Y4ImNZoMLSpCfuMPlKuwADCYsJCwvEOY/8itAE/E/YSthAm4UsPI8QSEm3WGEgoINwITWPt6PyvBJBOKCFcZDOmi/AW4TXCh4QvhDE1xu9kwoVg8mLCMcIcFvb9hHzCvYRnDrcAyqDqGvFXriZUwZB5ofeBcsKphFxCDuEMYexp2CeTUERo9aS+ubm5Xhm/gLACrkyib6GWlYQDypWZjC8cY/TtJtQS/hDmDSLMA5OjlL3bCTcQ3o2WBlwBSzxY6GNmiwmlhP2Ke5uFLzXGZo+luCqlsA/h65cjNngCcYVJCYR3CIsIy4L2ArzoeoX5j8DgEoH5IdAYZmaBA/NMJxOuJWzGvCHCV86EfWi1EeLjQQpgOYydRCWw4k1CH6v6Z4T5Pu3MfMxPEfrW4jouV+bejDERC4DV7U6hfR/u2z3KvCuhjqMc3OI3hB6bMTx/I7TCJNa2uwkczx8U+vl8dQjFfQmgBobHpCbE9OuUeZfA7R2teAeOG2bCfZ6FtfLR3qb4/hcI2cp+VTCq24W+6TCKcV69wPM4pEn1hOttgg++s18jqDHpdsKjDl+cP8pt8CQScVzwqdJ3IhKpqcpHuxSa66gBTyrMr4VEOx3shcl8BzZ/2IF5C/0PETLgFk16xGbub4RUJE4msba94uYK8CHnCu0rYHnt6BzCHOGes9A2eTSAbxCuFtqTEYHaEduf1UL7VLhxVQALoH4mcTp6i4tDFwpti21U1om2INw1aaGLudmKjcpCMtVPANMU9WJ1usnlgc3g5CcENJHQA0icwilFM2qCF2gQ2gvwYf4VwOmmaoDeU9TQUu7YKcK1ibTmdlAIagYqNkqiDESWkmAnheCqXhV85ecIfXtcbpSGOD+cNgdUC2gU2s52OfcAbNCXQl9dCDHzuUbHDkz63WP1Jpz+xDpB0PdYz9QMt7QP17PDdJshJChSLL3T4yFNVe9Usjo/9Kvpv31crTbEIP3c4EZh8FxUZiIRwCAlcfJD8T7OI62RLQlgiTB4PEJPK4IrEOci8/NSb4wVBOyFahCn9BPANuUaTPdYZvpFaDshIAFImtThYT7zcbnQPuuQG1wTHhyE0WylXaLnhLY5AQlAikNWu5xbAT6k9jUhI/feoAQNboIZNprNQmA0JULmpwgBVrNLI12K85u04VCNwgyF01HdMekum4JIOBUrCVSMT+ZjlKJGsYu5JTi3VL1Kt0uGOGFoUUpilQ6b1gpaMAKltHgfVns95ptfv9ZhbiXOa1KLmSqHlCoLG8Bdyl2s8nFf0xCOprpkPhXj01yubxZHpDG7wNd+N/WAHcjf24W+HKSqml/+2Op7M5BC1wYUWtJxt4eibyh+p6O/QQl1y7C+RLE4V47Q1w5++kWmdmXxVkzi0taZgq3YCuMklaGKoML5Qt/MsERmJ9bhuGOkw5ddiXW1OsTLlvykxjXHqyylguxUE+Tn6AmK1BNRa8sU+rrhAosc1h+J4oUT80VYr1voy8Q5EhVtnGDZPKu7qQrvwZ2sF/oSUIOrUK4E5xSXWXLJ3A01Yf5SReUrsH+C0F+Pc++x28Dtu0AnDEi50l8AVc4S+hqg4oUwbN0Oe3VjXCHmNShVna2Kj7dwTqfa5T/k523wOqvvrUBzaxxkPAbboan9bCVP4L/DPGsT5PBdvhXVKy1rLPCSx/h5HebF+bVmlSU/kE4DNsEfs8/uMiLGZR7246dxLsPPc4gqWWvyLI+vw37/IcKbJFl9r0I9NiFsNSoxVbjLbrPDMRhfhfnVNsz34BxJXpn3qwFmuPmm1VdMTVLGjAXYP++Fxe5CNscu6m0YK3a1J+GLp7jMJD+w+qrYn/hlIIi/yPDmyVC/RVLObaTHMwLYczs8w6pIFwryT1J8mHGoJjVa0aFGrD8uCOaD0gCTngYyEMuzZgyLYL3dYJbdYV3Qh43mHyXrgGJ8sZywStNYm3nfIaa3YPy2wXZEhf4WYACOpXG7kIJbGwAAAABJRU5ErkJggg==\");\n}\n\n.showpassword-icon-close,\n.regpassword-icon-close {\n    background-image: url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAAAyCAYAAADsg90UAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NjI0QjdGNzRFOTJCMTFFNzg1RUZFNEM1QUFENTk1MzEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NjI0QjdGNzNFOTJCMTFFNzg1RUZFNEM1QUFENTk1MzEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjBCQjUxQ0QzRTkyNTExRTc4RkJGOTU2MzA4NkJCREMyIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjBCQjUxQ0Q0RTkyNTExRTc4RkJGOTU2MzA4NkJCREMyIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+2rAKbgAAB0NJREFUeNrkWglsVUUUfXQBrIpQRBGJ0YJSEQJUFGjFJQoIaNoG1yhUaXGLrVgUFyIaETSlxqgQQIGgsgQUo21VShGl0MXixiLgUkGsAQFZQhcrLXhven6cTGfezPz/i1hvckL/vP/fvHvnLufeR5u0tDSvFUs24S/CLN0Xolqx8umEl/H3IcIS1ZciWqnyGYT5wufFhKz/iwH45N9UrA84lUKgKyGa0JEQSfiD0EjYSzgewn3vlU4+ILw24d8ywJmEfoSRhDhCL8JFhLaEGHynjnCMUEXYgX8/JGwmHHBQfqFifYFO+ZY2wHDCrVD8fMN3TwN6Ax5ilpPXasIyQhGhVvP78VBUpXyG38bhNkAkHobjcFAY7teJcDvwCyGBcNBS+TcI95s2CKcB7iNMJvRw/F0jDGeSXEKNpfJzCQ/abB4OAwwjvITT8ZMthI2EbYRvCNWE/XBrToqxhHhgMOFK4bd3EJYrsr0q4c0mPGz78KEYoAMhx+BmlYR38fDf+nzvd/z7mbAWjxPebKn8CTC+LBclgjXAEJCLOJ/TZpddQfgzyD12IKQ8y5Nf56p8sAbIFiimLFy+piMGVXIGjHcFoQ/hXMEDfiJUwFOqHOt8gOhMwf4tZoD5OAGVcB6YQTiquNad8BghlXCBYQ/OCf0JP1rW+YCcRXiBMJSQYut5tlT4bEKJRvlNONGnNMpnErYSHrFQnuVJwm5L5VWeMgJedGm4DMBlrZyQqCEaXO+/1JCbPMJrOB0bGUt4nVAvlTqV8rPAKqcprvH6BhxMSCHArriG0FlxbYJPPHKsF+saEFSHMsIeuDyHyKdgfDZ1fh48i2UqyusS0O6AxMIIHHYfB2MAVn49lBGFufkYKKiTFRrl85BASwkNBuOrlOdGaY6izucTriKsJPQU1rnf+IhwM6HAJQT6aJTfjVDwU34S+L8odaCzyfitSfl0zclX+JCczXD5CsW1fPQmVgbg0lSoUJ7j/HJFdpbb3BnSGo+kroVXBDPMEOUyxLdODhOScOoqI/Q3GaA9TqibtP4FTt7Umk6G24lyt+ZUXIYZYmudY7gHe9dNhA+kdX6utcg3WgNwjF4irX1PuA79uqmlHSutFYEKhzLM2C99HgVPM0kqwljuLlfhoJsZYB4aG1F4QnM9YtgkSeALojwb4jDjRSTTBilxj7S87yjwFDmMVsoGeADtrCj1SBy/WW6WpEiYZRa/G+8zyXka+5dI1wZbPlM1DnWvwjA5AQMMRGmRJRlNja3ES5+LLZXXDTPESc5axSnaCofQDQovfpzDJAJupiI5hY59Qowhdm2Vn6tosQ9Jnzs6Ptt34AJy+c2NQPLyFGQnVDkRRJ2frZnkRCqmSK6i0qkdG+A5DZNLcNxA7r56+ig/X8PtdSQnVpGfXOQ8VCSZ+U6JANd/XroQjXLR1WGTSunzcgfl5wjcXjeAEWWnw3OxLjxZ7iKtv8WIEMrVJ9IXusAI7Sw3Khf+HkdYalnnee0hn/tyvF8jrZU6GCAP1F6Urwj3yDwgFQNLUfrBerYGqMXE6B3LOu/70kJgl9HS2irLZ+JDuFGRC0ariFA9SI+cva8mvGex2T70Cq9YKr/QM7y08JreIGVLa+vBTk3Cc4g7FYl5hPfPELYZFd6LxqVaWh+DBqOtxSDThuT4jdZE3l+oCMFnLJRfoMkp7A1fm7rBbaKLSOypTEF3Xep8DbpFk9sPwF4XS+vcV6wzTLgKsLdq2rTadh5QDBos1/IEPFjvIEnOYYy8dMLvEGfilGS296vBcN1BmUdrqs9i14lQEYyQL3ZPqO+bkOmXOZS6gII/Y7ZQitlCW7TfiZgvnq4xHHP6I5r78iDkfbnVFXLQomBngmuQBIukwWaUZtCZYejnA23zUMBGdqK/1yW+TCQ8TzE+S9aNwkwhIMpGEBFxBH2X1/zlh26YUWIxAtMJl9O+ivLM0hnXVcofhccUmDawfS+wHYnpc69pvm9LcuZhWNkXPXiN5X6FiOVxmt9wQtuKaZMsW8Bf1tps5PJm6AAmQzbDjHr021OF8ngL4UKUWY73HmCbMeAQgVH5Bgw4VTIItH245vrbYJW2hg759bgu2zdqBpO7kJAWOe7DITiRcJvPd7IMFSbsBtApH5gNlCPTL0WG3uN4/05QOEVBZ0VhXjAJ/N47WQbwU16URCAXFJZd/Aev6T9I1ML1G9B1MvPj93m9UNY43Dr43PsgwuHVUFw4GAPo6vxqdF3dFNfaIysPE0rUMVDu41CeKW8bi/0bUIGmK2Z9LW4Av2FGJhSZiFYzzlB92jm02oFmaxHmhZVemCQiDMqLwwyuv9PgCekgUvUhPiPT8kdBv58Ip/IuHpDuOMyoQ2lciHI3BHU9DtA1VEdAj3ej5y9DvW8xsTXALiStGMdhRmBUVik0I+eAD0SBzUViBtEItlnlnUSxNQC/ux+IstbRcpjhF8v7vFNEXJLgdjQXKYopzX9W/hZgAFxXr8L5WH+3AAAAAElFTkSuQmCC\");\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 7%;\n    height: 7%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: #fff;\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n    box-shadow: 0 0 2em rgba(0, 0, 0, .6);\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle,\n.register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput,\ninput:focus,\ninput:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span,\n.register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button,\n.register-button {\n    margin: 6%;\n}\n\n.loginbtn1,\n.registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2,\n.registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner>div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n\n    0%,\n    40%,\n    100% {\n        -webkit-transform: scaleY(0.4)\n    }\n\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n\n    0%,\n    40%,\n    100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody,\ndiv,\nul,\nli,\nh3,\nh4,\nh5,\nform,\ninput,\nbutton,\ntextarea,\np {\n    margin: 0;\n    padding: 0;\n    font-family: \"Microsoft YaHei\";\n    font-size: 12px;\n}\n\naddress,\nem,\nstrong,\nh3,\nh4,\nh5,\ni {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul,\nli {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #F27241;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .7rem;\n    border: 1px solid #F0F0F0;\n}\n\n.notice-box {\n    display: inline-block;\n    position: relative;\n    width: 80%;\n    height: 1.5rem;\n    line-height: 1.5rem;\n    text-align: center;\n    font-size: .7rem;\n}\n\n.notice-box img {\n    width: 1.3rem;\n    height: 1.3rem;\n    position: absolute;\n    top: 0.1rem;\n    left: 0;\n}\n\n.notice-box .notice-text {\n    width: calc(100% - 1.4rem);\n    height: 1.5rem;\n    position: absolute;\n    top: 0;\n    right: 0;\n    overflow: hidden;\n}\n\n.notice-box .notice-text .text {\n    overflow: visible;\n    height: 1.5rem;\n    width: 32.2rem;\n    line-height: 1.5rem;\n    font-size: .68rem;\n    white-space: nowrap;\n    color: #7F7F7F;\n    text-align: left;\n}\n\n@keyframes scrollMove {\n    0% {\n        transform: translateX(55vw);\n    }\n\n    100% {\n        transform: translateX(-100%);\n    }\n}\n\n.box-label {\n    float: left;\n    width: 25%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .68rem;\n}\n\n.box-input-phone {\n    width: 55%;\n    padding-left: 5%;\n}\n\n.box-code {\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    border-radius: 2px;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    border-radius: 2px;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n    width: 80%;\n    display: inline-flex;\n    justify-content: flex-start;\n    align-items: center;\n}\n\n.cont-link-protocol .text-check {\n    float: left;\n    /* margin-left: 6%; */\n    opacity: 0.8;\n    width: 25px;\n    height: 25px;\n    margin-top: 0.32rem;\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: 0.2rem 0 0.2rem 0.2rem;\n    color: #737373;\n    text-align: left;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.6rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #737373;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 116 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(117);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-metro.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-metro.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 117 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 7%;\n    height: 7%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 400px;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    background: #444749;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #fff;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: .6rem;\n    border-radius: 5px;\n    border: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    width: 95%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    border-top-left-radius:3px;\n    border-bottom-left-radius:3px;\n}\n\n.box-code {\n    border-left: 1px solid #e0e0e0;\n    background: #fff;\n    color:#404241;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border-top-right-radius:3px;\n    border-bottom-right-radius:3px;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n    border-radius: 3px;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    font-weight: 400;\n    border-radius: 2px;\n}\n\n.cont-btn-two .btn-two:first-child {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    font-weight: 400;\n    border-radius: 2px;\n    background: #609A69;\n    color: #fff;\n}\n\n.cont-btn-two .btn-two:last-child {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    font-weight: 400;\n    border-radius: 2px;\n    background: #F1CA6E;\n    color: #fff;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background: #608A69;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background: #F1BA6E;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    border-radius: 2px;\n    background: #609A69;\n    color: #fff;\n}\n\n.btn-one:active {\n    outline: none;\n    background: #608A69;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: 0.2rem 0 0.2rem 0.2rem;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.5rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 118 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 30px;\n    height: 30px;\n    position: absolute;\n    top: 50px;\n    left: 5px;\n    border: 10px solid #E7CA86;\n    border-radius: 50px 50px 50px 50px;\n    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    z-index:3;\n}\n\n.unfold {\n    width: 0px;\n    height: 40px;\n    position: absolute;\n    background: rgba(231,202,134, 0.7);\n    top: -5px;\n    left: 30px;\n    color: #f0f0f0;\n    text-align:center;\n    line-height:40px;\n    -moz-border-radius: 50px 50px 0;\n    border-radius: 0px 50px 50px 0px;\n    z-index:3;\n}", ""]);

// exports


/***/ }),
/* 119 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(120);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-facebook.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-facebook.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 120 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    background: #5170ad;\n    background: -moz-radial-gradient(center, ellipse cover, #5170ad 0%, #355493 100%);\n    background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%, #5170ad), color-stop(100%, #355493));\n    background: -webkit-radial-gradient(center, ellipse cover, #5170ad 0%, #355493 100%);\n    background: -o-radial-gradient(center, ellipse cover, #5170ad 0%, #355493 100%);\n    background: -ms-radial-gradient(center, ellipse cover, #5170ad 0%, #355493 100%);\n    background: radial-gradient(ellipse at center, #5170ad 0%, #355493 100%);\n    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#5170ad', endColorstr='#355493',GradientType=1 );\n    border: 1px solid #2d416d;\n    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n    /*box-shadow: 0 1px #5670a4 inset, 10px 10px 10px 10px rgba(0, 0, 0, 0.7);*/\n    border-radius: 12px;\n    position: absolute;\n    margin: 10px auto 20px auto;\n    text-align: center;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title { \n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #fff;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    border-radius: 5px;\n    border: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background: #e0e0e0;\n    background: -moz-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #e0e0e0), color-stop(100%, #cecece));\n    background: -webkit-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -o-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -ms-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: linear-gradient(to bottom, #e0e0e0 0%, #cecece 100%);\n    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e0e0e0', endColorstr='#cecece',GradientType=0 );\n    display: block;\n    color: #636363;\n    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.45);\n    font-weight: 700;\n    box-shadow: 0 1px 3px 1px rgba(0, 0, 0, 0.17), 10px 10px 10px rgba(255, 255, 255, 0.6) inset;\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    font-weight: 400;\n    border-radius: 2px;\n    background: #e0e0e0;\n    background: -moz-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #e0e0e0), color-stop(100%, #cecece));\n    background: -webkit-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -o-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -ms-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: linear-gradient(to bottom, #e0e0e0 0%, #cecece 100%);\n    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e0e0e0', endColorstr='#cecece',GradientType=0 );\n    display: block;\n    color: #636363;\n    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.45);\n    font-weight: 700;\n    box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.5), 10px 10px 10px rgba(255, 255, 255, 0.6) inset;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background: #C9C9C9;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background: #C9C9C9;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    border-radius: 2px;\n    background: #e0e0e0;\n    background: -moz-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #e0e0e0), color-stop(100%, #cecece));\n    background: -webkit-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -o-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: -ms-linear-gradient(top, #e0e0e0 0%, #cecece 100%);\n    background: linear-gradient(to bottom, #e0e0e0 0%, #cecece 100%);\n    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#e0e0e0', endColorstr='#cecece',GradientType=0 );\n    display: block;\n    color: #636363;\n    text-shadow: 0 1px 0 rgba(255, 255, 255, 0.45);\n    font-weight: 700;\n    box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.5), 10px 10px 10px rgba(255, 255, 255, 0.6) inset;\n}\n\n.btn-one:active {\n    outline: none;\n    background: #C9C9C9;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 121 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(122);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-translucent.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-translucent.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 122 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: rgba(0, 0, 0, 0.7);\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #fff;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    border-radius: 5px;\n    border: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    border-left: 1px solid #e0e0e0;\n    background: #fff;\n    color:#404241;\n    float: left;\n    width: 39.85%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #E44F3F;\n    border-radius: 2px;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #E43F3F;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #E43F3F;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #E44F3F;\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #E43F3F;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 123 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(124);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./smile.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./smile.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 124 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    position: absolute;\n    top: 50px;\n    left: 5px;\n    width:30px; height:30px; \n    border:4px solid #000; \n    border-radius:3px;\n    -webkit-border-radius:3px;\n    -moz-border-radius:3px;\n    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    z-index:3;\n}\n\n.floatball:before {\n    content:''; \n    height:5px; \n    width:20px; \n    background:#000; \n    display:block; \n    position:absolute; \n    top:5px; \n    left:5px; \n    box-shadow:0 8px 0 #000, 0 16px 0 #000;\n    -webkit-box-shadow:0 8px 0 #000, 0 16px 0 #000;\n    -moz-box-shadow:0 8px 0 #000, 0 16px 0 #000;\n}\n\n.unfold {\n    width: 0px;\n    height: 38px;\n    position: absolute;\n    background: #000;\n    top: -4px;\n    left: 30px;\n    color: #f0f0f0;\n    text-align:center;\n    line-height:40px;\n    z-index:3;\n}", ""]);

// exports


/***/ }),
/* 125 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: rgba(252,240,171, 0.8);\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #4f4f4f;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    border-radius: 5px;\n    border: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #FFA07A;\n    border-radius: 2px;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #FFA07A;\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 126 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 30px;\n    height: 30px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    border: 10px solid #e7ca8659;\n    border-radius: 50px 50px 50px 50px;\n    box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    z-index:3;\n}\n\n.unfold {\n    width: 0px;\n    height: 40px;\n    position: absolute;\n    background: rgba(231,202,134, 0.2);\n    top: -5px;\n    left: 30px;\n    color: #f0f0f0;\n    text-align:center;\n    line-height:40px;\n    -moz-border-radius: 50px 50px 0;\n    border-radius: 0px 50px 50px 0px;\n    z-index:3;\n}\n", ""]);

// exports


/***/ }),
/* 127 */
/***/ (function(module, exports, __webpack_require__) {

var $ = __webpack_require__(63)
window.$ = window.Zepto = $
__webpack_require__(81)

var User = __webpack_require__(80)

var showPage = 1
var disX, moveX, L, T, starX, starY, starXEnd, starYEnd

var Floatball = {
  init: function () {
    $('.floatball').show()
    // 事件监听
    $('.floatball').on('touchstart', function (e) {

      disX = e.touches[0].clientX - this.offsetLeft
      disY = e.touches[0].clientY - this.offsetTop
      //手指按下时的坐标
      starX = e.touches[0].clientX
      starY = e.touches[0].clientY
    })
    $('.floatball').on('touchmove', function (e) {
      e.preventDefault();
      L = e.touches[0].clientX - disX
      T = e.touches[0].clientY - disY
      //移动时 当前位置与起始位置之间的差值
      starXEnd = e.touches[0].clientX - starX
      starYEnd = e.touches[0].clientY - starY
      if (L < 0) { //限制拖拽的X范围，不能拖出屏幕
        L = 0
      } else if (L > document.documentElement.clientWidth - this.offsetWidth) {
        L = document.documentElement.clientWidth - this.offsetWidth
      }
      if (T < 0) { //限制拖拽的Y范围，不能拖出屏幕
        T = 0
      } else if (T > document.documentElement.clientHeight - this.offsetHeight) {
        T = document.documentElement.clientHeight - this.offsetHeight
      }
      moveX = L + 'px'
      moveY = T + 'px'
      this.style.left = moveX
      this.style.top = moveY
    })

    $('.floatball').on('click', function () {

      //0.5秒内不能重复点击
      // $('.floatball').off('click');
      // setTimeout(function() {
      // $('.floatball').on('click', false);
      // }, 500);

      if (showPage === 1) {
        showPage = 0
        $('.unfold').show()
        $('.unfold').animate({width: '50px'}, 'fast')
        setTimeout(function () {
          $('.unfold').html('刷新')
        }, 200)
      }
      else {
        showPage = 1
        $('.unfold').html('')
        $('.unfold').animate({width: '0px'})

      }
    })

    $('.unfold').on('click', function () {
        window.location.reload()
    })
  }
}
Floatball.init()

/***/ }),
/* 128 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(129);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios1.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios1.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 129 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 5px;\n    background: white;\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    height: 20%;\n    text-align: center;\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    /* background-image: url(../assets/bt.png); */\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: left;\n    text-indent: 1.5em;\n    margin: 0 0 0.5rem;\n    color: #4f4f4f;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    /* border-radius: 5px; */\n    border-bottom: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.6rem;\n    color: white;\n    background: #56599d;\n    border-radius: 15px;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #282e9c;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #282e9c;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.6rem;\n    color: white;\n    background: #56599d;\n    border-radius: 15px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #282e9c;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;\n    width: 25px;\n    height: 25px;\n    margin-top: -0.4rem;\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: black;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: black;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}\n", ""]);

// exports


/***/ }),
/* 130 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(131);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios2.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios2.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 131 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\n\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  background: rgba(0, 0, 0, 0.4);\n  display: none;\n}\n.logincon {\n  width: 85%;\n  left: 7.5%;\n  top: 25%;\n  font-size: 5px;\n  background: #e9892fc4;\n  border-radius: 1px;\n  position: absolute;\n  padding: 1em;\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  box-sizing: border-box;\n}\n\n.logintop {\n  height: 20%;\n  text-align: center;\n}\n\n.logo {\n  width: 30%;\n  height: 1rem;\n  display: inline-block;\n  background-image: url(" + __webpack_require__(13) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.loginmiddle, .register {\n  margin-top: 2%;\n  width: 80%;\n  margin-left: 10%;\n}\n\n.loginmiddle1 {\n  text-align: center;\n  color: #ee7639;\n  font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n  margin-top: 3%;\n  border: 1px solid #ddd;\n  padding: 2%;\n  position: relative;\n  border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n  border: none;\n  height: 92%;\n  position: absolute;\n  top: 0;\n  width: 77%;\n  padding-left: 1%;\n  border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n  user-select: text;\n}\n\n.loginmiddle-span {\n  width: 20%;\n  display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n  display: inline-block;\n  width: 44%;\n  color: #fff;\n  text-align: center;\n  padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n  margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n  background: #f0703f;\n  margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n  background: #3e8cd4;\n  margin-left: 5%\n}\n\n.hide {\n  display: none;\n}\n\n#loading {\n  z-index: 999;\n  position: relative;\n  display: none;\n}\n\n.spinner {\n  margin: 100px auto;\n  width: 50px;\n  height: 60px;\n  text-align: center;\n  font-size: 10px;\n}\n\n.spinner > div {\n  background-color: #f0703f;\n  height: 100%;\n  width: 6px;\n  display: inline-block;\n\n  -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n  animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n  -webkit-animation-delay: -1.1s;\n  animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n  -webkit-animation-delay: -1.0s;\n  animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n  -webkit-animation-delay: -0.9s;\n  animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n  -webkit-animation-delay: -0.8s;\n  animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n  0%, 40%, 100% {\n      -webkit-transform: scaleY(0.4)\n  }\n  20% {\n      -webkit-transform: scaleY(1.0)\n  }\n}\n\n@keyframes stretchdelay {\n  0%, 40%, 100% {\n      transform: scaleY(0.4);\n      -webkit-transform: scaleY(0.4);\n  }\n  20% {\n      transform: scaleY(1.0);\n      -webkit-transform: scaleY(1.0);\n  }\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  font-weight: bold;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  color: #3a1308;\n  font-weight: 400;\n  font-size: .8rem;\n}\n\n.cont-box {\n  display: inline-block;\n  width: 90%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  text-align: center;\n  margin: 0 0 1rem;\n  font-size: .6rem;\n  border-radius: 8px;\n  /* overflow: hidden; */\n  background: white;\n  -moz-box-shadow:2px 5px 5px rgb(151, 146, 146);\n  -webkit-box-shadow:2px 5px 5px rgb(151, 146, 146);\n  box-shadow: 2px 5px 5px rgb(151, 146, 146);\n  border-bottom: 1px solid #F0F0F0;\n}\n.showpassword-icon, .regpassword-icon{\n  top: 4.8rem;\n}\n.box-input {\n  border-radius: 8px;\n  padding-left: 5%;\n  float: left;\n  width: 95.1%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n}\n\n.box-input-phone {\n  width: 54.5%;\n  padding-left: 5%;\n}\n\n.box-code {\n  background-color: #fbd083;\n  float: left;\n  width: 40%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 80%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n  display: inline-block;\n  width: 45%;\n  height: 1.4rem;\n  line-height: 1.4rem;\n  text-align: center;\n  font-size: 0.7rem;\n  color: #f82432;\n  font-weight: 400;\n  background: #fbd183be;\n  /* border-radius: 15px; */\n  border: double 1px #707070;\n  -moz-box-shadow:2px 3px 3px rgb(151, 146, 146);\n  -webkit-box-shadow:2px 3px 3px rgb(151, 146, 146);\n  box-shadow: 2px 3px 3px rgb(151, 146, 146);\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  background-color: #f3af30;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  background-color: #f3af30;\n}\n\n.cont-btn-two .btn-two:first-child {\n  float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n  float: right;\n}\n\n.cont-btn-two .btn-one {\n  display: inline-block;\n  width: 100%;\n  height: 1.4rem;\n  line-height: 1.4rem;\n  text-align: center;\n  font-size: 0.7rem;\n  color: #f82432;\n  font-weight: 400;\n  background: #fbd083;\n  /* border-radius: 15px; */\n  border: double 1px #707070;\n  -moz-box-shadow:2px 3px 3px rgb(151, 146, 146);\n  -webkit-box-shadow:2px 3px 3px rgb(151, 146, 146);\n  box-shadow: 2px 3px 3px rgb(151, 146, 146);\n}\n\n.btn-one:active {\n  outline: none;\n  background-color: #f3af30;\n}\n\n.c-orange {\n  background: #F27241;\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  font-size: 0.7rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 10%;\n  opacity: 0.8;       \n  width: 25px;   \n  height: 25px;\n  margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n  font-size: 0.4rem;\n  margin: -0.2rem 0 0.2rem 0;\n  color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.4rem;\n  color: #1E90FF;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 80%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-weight: 400;\n  font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 45%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  font-size: 0.7rem;\n  color: #3a1308;\n  border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n  float: left;\n  text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n  float: right;\n  text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 132 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(133);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios3.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios3.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 133 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 15.83rem;\n    height: 12.5rem;\n    left: 50%;\n    top: 25%;\n    font-size: 14px;\n\n\n    margin-left: -7.9rem;\n    /*380*263*/\n\n    background: url(" + __webpack_require__(134) + ") no-repeat;\n    background-size:cover;\n\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n    box-shadow:none;\n}\n\n.logintop {\n    text-align: center;\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: none;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #0b3255;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 82%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: -0.1rem 0 0.5rem 0.3rem;\n    font-size: .6rem;\n    border-radius: 5px;\n    border:none;\n\n\n}\n.cont-login .cont-box:nth-of-type(2){\n    margin: 0 0 0.4rem !important;\n\n}\n.cont-reg-account .cont-box:nth-of-type(2){\n    margin: 0 0 0.4rem !important;\n}\n.history_accounts-icon{\n    top:0.1rem;\n}\n.showpassword-icon{\n    top:2.4rem;\n}\n.regpassword-icon{\n    top:2.4rem;\n}\n\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n    color: #3ebbeb;\n    background: rgba(13, 49, 83, 1);\n}\n.box-input::-webkit-input-placeholder {\n     color: #3ebbeb;\n     /*font-size: 12px;\n     text-align: right;*/\n  }\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: right;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n.cont-reg-account .cont-btn-two .btn-one{\n    background: none;\n}\n.cont-reg-account .cont-btn-two{\n    position: relative;\n    top:0.8rem;\n}\n.cont-reg-phone .cont-btn-two{\n    position: relative;\n    top:2.1rem;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n   /* background: #FFA07A;*/\n    border-radius: 2px;\n    position: relative;\n    top:1.2rem;\n    left:-2.4rem;\n\n}\n.cont-btn-two .btn-two:nth-of-type(2){\n    left:2.4rem;\n}\n.cont-reg-account .cont-link-protocol{\n    margin-top: 0.8rem;\n}\n\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n   /* background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n   /* background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    /*background: #FFA07A;*/\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.c-orange {\n    background: none;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: -0.4rem;      \n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    position: absolute;\n    display: inline-block;\n    width: 100%;\n    top:5.6rem;\n    left:0;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 134 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "6db8abd84ac85a5e29679c31ebd7c1a3.png";

/***/ }),
/* 135 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(136);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios4.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios4.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 136 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n\n\n    width: 14.25rem;\n    height: 10.875rem;\n    left: 50%;\n    top: 30%;\n    margin-left: -7.125rem;\n\n    font-size: 14px;\n    background: rgba(252,240,171, 0.8);\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n\n    background: url(" + __webpack_require__(137) + ") no-repeat;\n    background-size:cover;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n.history_accounts-icon{\n    top:0.2rem;\n}\n.showpassword-icon{\n    top:2.6rem;\n}\n.regpassword-icon{\n    top:2.6rem;\n}\n\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: none;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #4f4f4f;\n    font-size: .8rem;\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    border-radius: 5px;\n    border: 1px solid #F0F0F0;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n}\n\n.box-input-phone {\n    width: 55%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: right;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #048bbb;\n    border-radius: 2px;\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #048bbb;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #048bbb;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #048bbb;\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #048bbb;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-top: 2px;\n    opacity: 0.8;       \n    width: 20px;\n    height: 20px;\n\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n.cont-link-protocol p{\n    width: 200px;\n}\n.cont-reg-account .cont-btn-two{\n    width: 90px;\n\n}\n.cont-reg-account .cont-link-two{\n\n    position: absolute;\n    top: 200px;\n    left: 30px;\n}\n.cont-reg-phone .cont-link-two{\n    position: absolute;\n    top: 205px;\n    left: 30px;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 30%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n    margin-top: -55px;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n    margin-top: -55px;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 137 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "50d7060b10207115e6c7b88b7cf32fa3.png";

/***/ }),
/* 138 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(139);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios5.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios5.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 139 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: rgba(0,0,0, 1);\n\n    border-radius: 12px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    text-align: center;\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    color: #bac4ce;\n    font-size: .8rem;\n\n\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n\n    border: 1px solid #66b4ab;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n    color: #3e8180;\n\n    background-color: #101e26;\n}\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: right;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n\n\n\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n\n\n\n\n    border-radius: 2px;\n\n\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background: #FFA07A;\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.c-orange {\n   /* background: #F27241;*/\n    background: linear-gradient(#5cddf9, #196372) !important; /* 标准的语法 */\n    background: -webkit-linear-gradient(#5cddf9, #196372); /* Safari 5.1 - 6.0 */\n    background: -o-linear-gradient(#5cddf9, #196372); /* Opera 11.1 - 12.0 */\n    background: -moz-linear-gradient(#5cddf9, #196372); /* Firefox 3.6 - 15 */\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 20px;\n    height: 20px;\n    margin-top: -0.2rem;\n\n\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.4rem;\n    margin: -0.2rem 0 0.2rem 0;\n    color: #fff;\n    width: 220px;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.4rem;\n    font-size: 0.7rem;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    color: #fff;\n    border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 140 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(141);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios6.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios6.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 141 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: #ffffff;\n    border-radius: 10px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    height: 20%;\n    text-align: center;\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    /*margin: 0 0 0.5rem;*/\n    margin: 0.4rem 0 0.5rem;\n    font-size: .8rem;\n    letter-spacing: 2px;\n    color: #ec414d;\n    font-weight: bold;\n\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    background-color: #ffffff !important;\n    border-radius: 8px;\n    -moz-box-shadow:3px 3px 3px #969696;\n    -webkit-box-shadow:3px 3px 3px #969696;\n    box-shadow:3px 3px 3px #969696;\n    margin-top:0.1rem;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    /*border: none;*/\n    outline: none;\n    font-size: .6rem;\n    border-radius: 8px;\n    border: solid 0px #929292;\n    background-color: #ffffff !important;\n}\n\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.history_accounts-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 1.85rem;\n}\n.showpassword-icon, .regpassword-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 4.3rem;\n}\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.2rem;\n    margin-bottom: 2.4rem;\n    position: relative;\n    /*top:-2rem;*/\n}\n\n.cont-btn-two .btn-two {\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    /*background-color: #ec414d;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    /*background-color: #ec414d;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n    background:#ec414d;\n    border-radius: 8px;\n    margin-top: -0.2rem;\n    /*color: #ec414d;*/\n    color:#ffffff;\n    width: 100%;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n}\n\n.cont-btn-two .btn-two:last-child {\n    width: 100%;\n    /*float: right;*/\n    background: #ec414d;\n    margin-top: 0.4rem;\n    border-radius: 8px;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background:  #ec414d;;\n    /*color: #f5f5f5;*/\n    border-radius: 8px;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n}\n\n.btn-one:active {\n    outline: none;\n    /*background-color: #FF907A;*/\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;\n    width: 25px;\n    height: 25px;\n    margin-top: 0.05rem;\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.2rem;\n    /* color: #fff; */\n    margin: 0.2rem 0 0.2rem 0;\n    color: #413b3b;\n    width: 45%;\n    text-align: center;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.1rem;\n    font-size: 0.7rem;\n    color: #ec414d;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    /*color: #fff;*/\n    /*color: #ec414d;*/\n    /*border-radius: 2px;*/\n    /*text-decoration: underline;*/\n}\n\n.cont-reg-phone .cont-link-two .link-two:first-child {\n    background: #ec414d;\n    text-align: center;\n    border-radius: 10px;\n    color: #ffffff;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n    position: relative;\n    top: -2rem;\n    width: 45%;\n    float: left;\n}\n.cont-reg-account .cont-link-two .link-two:first-child {\n    text-decoration: underline;\n    color: #ec414d;\n    position: relative;\n    float: right;\n    margin-top: -6.4rem;\n    width: 40%;\n    text-align: right;\n}\n\n.cont-reg-phone .cont-link-two .link-two:last-child {\n    background: #ec414d;\n    text-align: center;\n    border-radius: 10px;\n    color: #ffffff;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n    top: -2rem;\n    width: 45%;\n    position: relative;\n}\n.cont-reg-account .cont-link-two .link-two:last-child {\n    /*float: right;*/\n    /*text-align: right;*/\n    background:#ec414d ;\n    text-align: center;\n    border-radius: 10px;\n    color: #ffffff;\n    background-image: linear-gradient(0deg,\n    #6e4a2a 0%,\n    #ec414d 100%);\n    position: relative;\n    top:-2rem;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 142 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(143);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios7.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios7.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 143 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background: rgba(0, 0, 0, 0.4);\n    display: none;\n}\n\n.logincon {\n    width: 90%;\n    left: 5%;\n    top: 30%;\n    font-size: 14px;\n    background: #ffffff;\n    border-radius: 0px;\n    position: absolute;\n    padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box;\n}\n\n.logintop {\n    height: 20%\n}\n\n.logo {\n    width: 30%;\n    height: 1rem;\n    display: inline-block;\n    background-image: url(" + __webpack_require__(13) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    user-select: text;\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n    background: #f0703f;\n    margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n    background: #3e8cd4;\n    margin-left: 5%\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    margin: 0 0 0.5rem;\n    font-size: .8rem;\n    letter-spacing: 0px;\n    color: #ec414d;\n\n}\n\n.cont-box {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    margin: 0 0 0.4rem;\n    font-size: .6rem;\n    background-color: #ffffff !important;\n    border-radius: 8px;\n    -moz-box-shadow:3px 3px 3px #969696;\n    -webkit-box-shadow:3px 3px 3px #969696;\n    box-shadow:3px 3px 3px #969696;\n    margin-top:0.2rem;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 95.1%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    /*border: none;*/\n    outline: none;\n    font-size: .6rem;\n    border-radius: 8px;\n    border: solid 0px #929292;\n    background-color: #ffffff !important;\n}\n\n\n.box-input-phone {\n    width: 54.5%;\n    padding-left: 5%;\n}\n\n.box-code {\n    background-color: #e0e0e0;\n    float: left;\n    width: 40%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.history_accounts-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 1.9rem;\n}\n.showpassword-icon, .regpassword-icon {\n    background-repeat: no-repeat;\n    background-size: 0.8rem;\n    display: inline-block;\n    width: 40px;\n    height: 40px;\n    background-position: center center;\n    cursor: pointer;\n    position: absolute;\n    left: 78%;\n    top: 4.5rem;\n}\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.5rem;\n    margin-bottom: 0.2rem;\n\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background:#ec414d;\n    border-radius: 2px;\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: #ec414d;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: #ec414d;\n}\n\n.cont-btn-two .btn-two:first-child {\n    float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n    float: right;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    width: 100%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    text-align: center;\n    font-size: 0.7rem;\n    color: white;\n    background:  #ec414d;;\n    /*color: #f5f5f5;*/\n    border-radius: 2px;\n}\n\n.btn-one:active {\n    outline: none;\n    background-color: #FF907A;\n}\n\n.c-orange {\n    background: #F27241;\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    font-size: 0.7rem;\n    text-align: center;\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 10%;\n    opacity: 0.8;       \n    width: 25px;   \n    height: 25px;\n    margin-top: 0.05rem;\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.2rem;\n    /* color: #fff; */\n    margin: 0.2rem 0 0.2rem 0;\n    color: #413b3b;\n    width: 100%;\n    text-align: center;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.4rem;\n    color: #1E90FF;\n}\n\n.cont-link-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin-top: 0.1rem;\n    font-size: 0.7rem;\n    color: #ec414d;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    width: 45%;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    font-size: 0.7rem;\n    /*color: #fff;*/\n    color: #ec414d;\n    border-radius: 2px;\n    text-decoration: underline;\n}\n\n.cont-link-two .link-two:first-child {\n    float: left;\n    text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n    float: right;\n    text-align: right;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 144 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(145);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios8.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios8.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 145 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\n\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  background: rgba(0, 0, 0, 0.4);\n  display: none;\n}\n\n.logincon {\n  width: 13.2rem;\n  height: 8.8rem;\n  left: 50%;\n  margin-left: -6.6rem;\n  top: 30%;\n  padding-top: 1.1rem;\n  font-size: 5px;\n  border-radius: 12px;\n  position: absolute;\n  background-color: rgba(255, 255, 255, 0);\n  /* padding: 1em;\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  box-sizing: border-box; */\n  background-image: url(" + __webpack_require__(146) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\n  height: 2rem;\n  width: 8rem;\n  text-align: center;\n  position: absolute;\n  top: .6rem;\n  left: 10%;\n}\n\n.logo {\n  width: 30%;\n  height: 1rem;\n  display: inline-block;\n  float: left;\n  /* background-image: url(../assets/bt.png); */\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.loginmiddle, .register {\n  margin-top: 2%;\n  width: 80%;\n  margin-left: 10%;\n}\n\n.loginmiddle1 {\n  text-align: center;\n  color: #ee7639;\n  font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n  margin-top: 3%;\n  border: 1px solid #ddd;\n  padding: 2%;\n  position: relative;\n  border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n  border: none;\n  height: 92%;\n  position: absolute;\n  top: 0;\n  width: 77%;\n  padding-left: 1%;\n  border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n  user-select: text;\n}\n\n.loginmiddle-span {\n  width: 20%;\n  display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n  display: inline-block;\n  width: 44%;\n  color: #fff;\n  text-align: center;\n  padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n  margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n  background: #f0703f;\n  margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n  background: #3e8cd4;\n  margin-left: 5%\n}\n\n.hide {\n  display: none;\n}\n\n#loading {\n  z-index: 999;\n  position: relative;\n  display: none;\n}\n\n.spinner {\n  margin: 100px auto;\n  width: 50px;\n  height: 60px;\n  text-align: center;\n  font-size: 10px;\n}\n\n.spinner > div {\n  background-color: #f0703f;\n  height: 100%;\n  width: 6px;\n  display: inline-block;\n\n  -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n  animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n  -webkit-animation-delay: -1.1s;\n  animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n  -webkit-animation-delay: -1.0s;\n  animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n  -webkit-animation-delay: -0.9s;\n  animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n  -webkit-animation-delay: -0.8s;\n  animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n  0%, 40%, 100% {\n      -webkit-transform: scaleY(0.4)\n  }\n  20% {\n      -webkit-transform: scaleY(1.0)\n  }\n}\n\n@keyframes stretchdelay {\n  0%, 40%, 100% {\n      transform: scaleY(0.4);\n      -webkit-transform: scaleY(0.4);\n  }\n  20% {\n      transform: scaleY(1.0);\n      -webkit-transform: scaleY(1.0);\n  }\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: left;\n  color: black;\n}\n\n.cont-title {\n  display: block;\n  text-align: left;\n  text-indent: 1.6em;\n  margin: 0 0 0.2rem;\n  margin-top: 0.1rem;\n  color: #d8bc98;\n  font-size: 0rem;\n}\n\n.cont-login .cont-title{\n  width: 147px;\n  height: 28px;\n  margin: auto;\n  margin-top: -0.9rem;\n  margin-bottom: .8rem;\n\n  background-image: url(" + __webpack_require__(147) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.cont-reg-phone .cont-title{\n  width: 147px;\n  height: 28px;\n  margin: auto;\n  margin-top: -0.9rem;\n  margin-bottom: .8rem;\n\n  background-image: url(" + __webpack_require__(148) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n.cont-reg-account .cont-title{\n  width: 147px;\n  height: 28px;\n  margin: auto;\n  margin-top: -0.9rem;\n  margin-bottom: .8rem;\n\n  background-image: url(" + __webpack_require__(149) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n}\n\n\n.cont-box {\n  display: inline-block;\n  width: 60%;\n  height: 1.2rem;\n  line-height: 1.2rem;\n  text-align: left;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n  margin-left: 1rem;\n  background: rgb(36, 28, 37);\n  border-radius: 10px;\n  /* background: white; */\n  border: 0;\n  /* border-radius: 5px; */\n}\n\n.history_accounts-icon {\n  top: 1.8rem;\n  left: 6.95rem;\n}\n\n.showpassword-icon, .regpassword-icon{\n  top: 3.55rem;\n  left: 6.9rem;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  width: 80%;\n  height: 1.2rem;\n  line-height: 1.2rem;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  background-color: transparent;\n  color: #a8a5a5;\n}\n\n.box-input-phone {\n  width: 90%;\n  padding-left: 5%;\n  background-color: transparent;\n  color: #a8a5a5;\n}\n\n.box-code {\n  position: absolute;\n  right: 1rem;\n  top: 2.2rem;\n  color: white;\n  background-color: rgba(35, 33, 34, 0.7);\n  width: 2.7rem;\n  height: 1rem;\n  text-align: center;\n  line-height: 1rem;\n  font-size: .28rem;\n  border-radius: 6px;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 80%;\n  height: 1.3rem;\n  line-height: 1.3rem;\n  margin-left: -0.5rem;\n}\n\n.cont-btn-two .btn-two {\n  display: inline-block;\n  /* width: 45%; */\n  /* height: 1.8rem;\n  line-height: 1.8rem; */\n  text-align: center;\n  font-size: 0.6rem;\n  color: white;\n  margin-left: 1rem;\n  margin-top: 0.3rem;\n  /* background: #56599d;\n  border-radius: 15px; */\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  background-color: none;\n\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  background-color: none;\n}\n\n.cont-btn-two .btn-two:first-child {\n  font-size: .6rem;\n  background-color: rgba(0, 0, 0, 0);\n  display: inline-block;\n  width: 2.4rem;\n  height: 1rem;\n  line-height: 1rem;\n  text-align: center;\n  color: #d8bc98;\n  position: absolute;\n  left: 5rem;\n  margin-top: .43rem;\n}\n\n.cont-btn-two .btn-two:last-child {\n  float: left;\n  background-image: url(" + __webpack_require__(70) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  width: 4rem;\n  height: 1.3rem;\n  line-height: 1.45rem;\n  margin-left: 1.3rem;\n  background-color: rgba(0, 0, 0, 0);\n}\n\n.cont-btn-two .btn-one {\n  display: inline-block;\n  width: 100%;\n  text-align: center;\n  font-size: 0.6rem;\n  color: white;\n  background: rgba(255, 255, 255, 0);\n  background-image: url(" + __webpack_require__(70) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  width: 4rem;\n  height: 1.3rem;\n  margin-left: 1.3rem;\n  line-height: 1.45rem;\n  background-color: rgba(0, 0, 0, 0);\n}\n\n.btn-one:active {\n  outline: none;\n  background-color: none;\n}\n\n.c-orange {\n  background: #F27241;\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  font-size: 0.7rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 10%;\n  opacity: 0.8;\n  width: 15px;\n  height: 15px;\n  margin-top: -0.4rem;\n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n  font-size: 0.4rem;\n  margin: -0.7rem 0 0.2rem 2rem;\n  color: white;\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.4rem;\n  color: #1E90FF;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 80%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.25rem;\n  font-size: 0.7rem;\n  position: relative;\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 2.4rem;\n  height: 1.3rem;\n  line-height: 1.8rem;\n  font-size: 0.6rem;\n  color: #d8bc98;\n  border-radius: 2px;\n  position: absolute;\n  top: -1.7rem;\n}\n\n.cont-link-two .link-two:first-child {\n  position: absolute;\n  text-align: left;\n  right: 1rem;\n}\n\n.cont-link-two .link-two:last-child {\n  position: absolute;\n  text-align: right;\n  right: -1.8rem\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}\n", ""]);

// exports


/***/ }),
/* 146 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "df334ed63d5262745125dcc1784f9f96.png";

/***/ }),
/* 147 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "343ab40bbc0f16f2f5c5f2a04eda6ad5.png";

/***/ }),
/* 148 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "246ae4c967ab88699f86d4d50b20d258.png";

/***/ }),
/* 149 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "14595469aed94762092e272907ada83b.png";

/***/ }),
/* 150 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(151);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop-ios-8.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop-ios-8.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 151 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n  width: 25px;\n  height: 25px;\n  position: absolute;\n  top: 100px;\n  left: 5px;\n  border: 8px inset #924d0f9e;\n  border-radius: 50px 50px 50px 50px;\n  box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n  /*-webkit-transform: rotate(-45deg);\n  -moz-transform: rotate(-45deg);\n  -ms-transform: rotate(-45deg);\n  -o-transform: rotate(-45deg);\n  transform: rotate(-45deg);*/\n  z-index:10;\n}\n\n.unfold {\n  width: 0px;\n  height: 30px;\n  position: absolute;\n  background: #924d0f78;\n  top: -3px;\n  left: 25px;\n  color: #f0f0f0;\n  text-align: center;\n  line-height: 30px;\n  -moz-border-radius: 50px 50px 0;\n  border-radius: 0px 50px 50px 0px;\n  z-index: 3;\n}", ""]);

// exports


/***/ }),
/* 152 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(153);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios9.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios9.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 153 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\ninput.checkbox{\n  background-color:#506bda;\n}\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  /*background: rgba(0, 0, 0, 0.4);*/\n  background: rgba(0, 0, 0, 0.81);\n  display: none;\n}\n\n.logincon {\n  width: 13.333rem;\n  height: 10.25rem;\n  transform:translateX(-50%);\n  top:25%;\n  left: 50%;\n  font-size: 14px;\n  background: none;\n  border-radius: 12px;\n  position: absolute;\n  box-shadow: none;\n  background-image: url(" + __webpack_require__(154) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\ntext-align: center;\nheight: 20%;\ndisplay:none;\n}\n\n.logo {\nwidth: 30%;\nheight: 1rem;\ndisplay: inline-block;\nbackground-image: url(" + __webpack_require__(13) + ");\nbackground-size: contain;\nbackground-repeat: no-repeat;\nbackground-position: center;\n}\n\n.loginmiddle, .register {\nmargin-top: 2%;\nwidth: 80%;\nmargin-left: 10%;\n}\n\n.loginmiddle1 {\ntext-align: center;\ncolor: #ee7639;\nfont-size: 14px;\n}\n\n.loginmiddle-input-wrap {\nmargin-top: 3%;\nborder: 1px solid #ddd;\npadding: 2%;\nposition: relative;\nborder-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\nborder: none;\nheight: 92%;\nposition: absolute;\ntop: 0;\nwidth: 77%;\npadding-left: 1%;\nborder-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\nuser-select: text;\n}\n\n.loginmiddle-span {\nwidth: 20%;\ndisplay: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\ndisplay: inline-block;\nwidth: 44%;\ncolor: #fff;\ntext-align: center;\npadding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\nmargin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\nbackground: #f0703f;\nmargin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\nbackground: #3e8cd4;\nmargin-left: 5%\n}\n\n.hide {\ndisplay: none;\n}\n\n#loading {\nz-index: 999;\nposition: relative;\ndisplay: none;\n}\n\n.spinner {\nmargin: 100px auto;\nwidth: 50px;\nheight: 60px;\ntext-align: center;\nfont-size: 10px;\n}\n\n.spinner > div {\nbackground-color: #f0703f;\nheight: 100%;\nwidth: 6px;\ndisplay: inline-block;\n\n-webkit-animation: stretchdelay 1.2s infinite ease-in-out;\nanimation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n-webkit-animation-delay: -1.1s;\nanimation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n-webkit-animation-delay: -1.0s;\nanimation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n-webkit-animation-delay: -0.9s;\nanimation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n-webkit-animation-delay: -0.8s;\nanimation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n0%, 40%, 100% {\n-webkit-transform: scaleY(0.4)\n}\n20% {\n-webkit-transform: scaleY(1.0)\n}\n}\n\n@keyframes stretchdelay {\n0%, 40%, 100% {\ntransform: scaleY(0.4);\n-webkit-transform: scaleY(0.4);\n}\n20% {\ntransform: scaleY(1.0);\n-webkit-transform: scaleY(1.0);\n}\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  position: relative;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  /*color: #afbfff;*/\n  font-size: .8rem;\n  position: absolute;\n  top: 80px;\n  left: 200px;\n  /*color: #0c1573;*/\n  color: #1d2e73;\n\n\n\n\n}\n\n.cont-box {\n  position: absolute;\n  display: inline-block;\n  width: 220px;\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n\n  border: none;\n}\n.pop-cont .cont-box:nth-of-type(1){\n  top:112px;\n  left:110px;\n\n}\n.pop-cont .cont-box:nth-of-type(2){\n  top: 158px;\n  left: 110px;\n}\n\n\n\n.history_accounts-icon{\n  left: 150px;\n  top: 5px;\n  width: 30px;\n  height: 30px;\n\n}\n.showpassword-icon{\n  left: 145px;\n  top: 0px;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  width: 200px;\n  height: 40px;\n  line-height: 40px;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  color: #bcc4cf;\n\n  background-color: transparent;\n\n}\n.box-input::-webkit-input-placeholder{\n  color: #bcc4cf;\n}\n\n\n.box-input-phone {\n  width: 54.5%;\n  padding-left: 5%;\n}\n\n.box-code {\n  /*background-color: #e0e0e0;*/\n  float: right;\n  /*width: 40%;*/\n  /*height: 30px;*/\n  line-height: 30px;\n  position: relative;\n  left:-250px;\n  top: 4px;\n  width: 72px;\n  height: 28px;\n  background-image: url(" + __webpack_require__(75) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  text-align: center;\n   z-index: 100;\n}\n.cont-reg-account .cont-box .showpassword-icon,.cont-reg-account .cont-box .regpassword-icon {\n  background-repeat: no-repeat;\n  background-size: 0.8rem;\n  display: inline-block;\n  width: 40px;\n  height: 40px;\n  background-position: center center;\n  cursor: pointer;\n  position: absolute;\n  left: 65%;\n  top: 0rem;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 80px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  position: absolute;\n\n  top: 116px;\n  left: 10px;\n\n\n}\n\n.cont-btn-two .btn-two {\n  display: block;\n  width: 72px;\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n  color: #1f222b;\n\n  background-image: url(" + __webpack_require__(75) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  border-radius: 2px;\n\n\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  /*background-color: none;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n  float: none;\n}\n\n.cont-btn-two .btn-two:last-child {\n /* float: right;*/\n  float: none;\n  margin-top:18px;\n}\n\n.cont-btn-two .btn-one {\n  position: absolute;\n  top:48px;\n  left: 0;\n\n  display: inline-block;\n  color: #1f222b;\n\n  width: 72px;\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n\n  background-image: url(" + __webpack_require__(75) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  border-radius: 2px;\n}\n\n.btn-one:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.c-orange {\n  background:none;\n  /* background: #F27241;\n  background: linear-gradient(#5cddf9, #196372) !important;\n  background: -webkit-linear-gradient(#5cddf9, #196372);\n  background: -o-linear-gradient(#5cddf9, #196372);\n  background: -moz-linear-gradient(#5cddf9, #196372);*/\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  position: absolute;\n  top:245px;\n  left: 8px;\n  width: 100%;\n  font-size: 0.7rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 2%;\n  opacity: 0.8;\n  width: 20px;\n  height: 20px;\n  margin-top: -0.2rem;\n  background: #506bda;\n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n  font-size: 0.4rem;\n  margin: -0.2rem 0 0.2rem 0;\n  color: #fff;\n  /*width: 220px;*/\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.5rem;\n  /*color: #1d2e73;*/\n  color: #506bda;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 220px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-size: 0.7rem;\n\n  position: absolute;\n  top: 182px;\n  left: 108px;\n\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 2.4rem;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  font-size: 0.5rem;\n  color: #fff;\n  border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n  text-align: left;\n}\n\n.cont-link-two .link-two:last-child {\n  float:none;\n  text-align: right;\n  margin-left: 30px;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 154 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "6f182a0869c45df0e88bb437f2ec148f.png";

/***/ }),
/* 155 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(156);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_9.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_9.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 156 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 35px;\n    height: 35px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    /*border: 10px solid #e7ca8659;*/\n    border-radius: 50px 50px 50px 50px;\n    /*box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;*/\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    background: url(" + __webpack_require__(157) + ") center no-repeat;\n    background-size: 100% 100%;\n    z-index:3;\n}\n\n.unfold {\n    /*width: 129px;*/\n    height: 35px;\n    /*width: 0px;*/\n    /*height: 40px;*/\n    position: absolute;\n    /*background: rgba(231,202,134, 0.2);*/\n    top: 7px;\n    left: 30px;\n    color: #f0f0f0;\n    text-align:center;\n    line-height:5px;\n    -moz-border-radius: 80px 80px 0;\n    border-radius: 0px 80px 80px 0px;\n    z-index:3;\n    background: url(" + __webpack_require__(158) + ") right no-repeat;\n    background-size:130%;\n}", ""]);

// exports


/***/ }),
/* 157 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "17a6fdd6393356717d983263e636ffd8.png";

/***/ }),
/* 158 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "e9937133e62016d5b47de1d74068759e.png";

/***/ }),
/* 159 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(160);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios10.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios10.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 160 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\n\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  background: rgba(0, 0, 0, 0.8);\n  display: none;\n}\n\n.logincon {\n  width: 13.333rem;\n  height: 10.25rem;\n  transform:translateX(-50%);\n  top:20%;\n  left: 50%;\n  font-size: 14px;\n  background: none;\n  border-radius: 12px;\n  position: absolute;\n  box-shadow: none;\n  background-image: url(" + __webpack_require__(161) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\ntext-align: center;\nheight: 20%;\ndisplay:none;\n}\n\n.logo {\nwidth: 30%;\nheight: 1rem;\ndisplay: inline-block;\nbackground-image: url(" + __webpack_require__(13) + ");\nbackground-size: contain;\nbackground-repeat: no-repeat;\nbackground-position: center;\n}\n\n.loginmiddle, .register {\nmargin-top: 2%;\nwidth: 80%;\nmargin-left: 10%;\n}\n\n.loginmiddle1 {\ntext-align: center;\ncolor: #ee7639;\nfont-size: 14px;\n}\n\n.loginmiddle-input-wrap {\nmargin-top: 3%;\nborder: 1px solid #ddd;\npadding: 2%;\nposition: relative;\nborder-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\nborder: none;\nheight: 92%;\nposition: absolute;\ntop: 0;\nwidth: 77%;\npadding-left: 1%;\nborder-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\nuser-select: text;\n}\n\n.loginmiddle-span {\nwidth: 20%;\ndisplay: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\ndisplay: inline-block;\nwidth: 44%;\ncolor: #fff;\ntext-align: center;\npadding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\nmargin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\nbackground: #f0703f;\nmargin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\nbackground: #3e8cd4;\nmargin-left: 5%\n}\n\n.hide {\ndisplay: none;\n}\n\n#loading {\nz-index: 999;\nposition: relative;\ndisplay: none;\n}\n\n.spinner {\nmargin: 100px auto;\nwidth: 50px;\nheight: 60px;\ntext-align: center;\nfont-size: 10px;\n}\n\n.spinner > div {\nbackground-color: #f0703f;\nheight: 100%;\nwidth: 6px;\ndisplay: inline-block;\n\n-webkit-animation: stretchdelay 1.2s infinite ease-in-out;\nanimation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n-webkit-animation-delay: -1.1s;\nanimation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n-webkit-animation-delay: -1.0s;\nanimation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n-webkit-animation-delay: -0.9s;\nanimation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n-webkit-animation-delay: -0.8s;\nanimation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n0%, 40%, 100% {\n-webkit-transform: scaleY(0.4)\n}\n20% {\n-webkit-transform: scaleY(1.0)\n}\n}\n\n@keyframes stretchdelay {\n0%, 40%, 100% {\ntransform: scaleY(0.4);\n-webkit-transform: scaleY(0.4);\n}\n20% {\ntransform: scaleY(1.0);\n-webkit-transform: scaleY(1.0);\n}\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  position: relative;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  color: transparent;\n  font-size: .8rem;\n  /*position: absolute;\n  top: 80px;\n  left: 200px;*/\n}\n\n.cont-login .cont-title{\n  width: 147px;\n  height: 32px;\n  margin: auto;\n  margin-top: 42px;\n\n  background-image: url(" + __webpack_require__(162) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-reg-phone .cont-title{\n  width: 147px;\n  height: 32px;\n  margin: auto;\n  margin-top: 42px;\n\n  background-image: url(" + __webpack_require__(163) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n.cont-reg-account .cont-title{\n  width: 147px;\n  height: 32px;\n  margin: auto;\n  margin-top: 42px;\n\n  background-image: url(" + __webpack_require__(164) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n}\n\n\n.cont-box {\n  position: absolute;\n  display: inline-block;\n  width: 220px;\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n\n  border: none;\n}\n.pop-cont .cont-box:nth-of-type(1){\n  top:60px;\n  left:30px;\n\n  background-image: url(" + __webpack_require__(165) + ");\n  background-size: 35px;\n  background-repeat: no-repeat;\n  background-position:0 center;\n}\n.pop-cont .cont-box:nth-of-type(2){\n  top: 100px;\n  left: 30px;\n\n  background-image: url(" + __webpack_require__(166) + ");\n  background-size: 35px;\n  background-repeat: no-repeat;\n  background-position:0 center;\n}\n\n.pop-cont.j_popCont.cont-reg-phone .cont-box{\n  background-image:none;\n}\n/*cont-reg-account*/\n\n.history_accounts-icon{\n  left: 190px;\n  top: 0px;\n\n\n}\n.showpassword-icon{\n  left: 190px;\n  top: -5px;\n}\n\n.box-input {\n  padding-left: 42px;\n  float: left;\n  width: 200px;\n  height: 40px;\n  line-height: 40px;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  color: #eeeeee;\n\n  background-color: transparent;\n\n}\n.box-input::-webkit-input-placeholder{\n  color: transparent;\n}\n.box-input.box-input-phone.j_data_regPhone::-webkit-input-placeholder{\n  color: #777675;\n}\n.box-input.box-input-code.j_data_code::-webkit-input-placeholder{\n  color: #777675;\n}\n\n\n.box-input-phone {\n  width: 54.5%;\n  padding-left:5px;\n}\n\n.box-code {\n /* background-color: #e0e0e0;*/\n  color: #fab707;\n  /*float: none;*/\n  width: 40%;\n  height: 30px;\n  line-height: 30px;\n  position: absolute;\n  left:150px;\n  top: 4px;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5px;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 220px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  position: absolute;\n\n  top: 140px;\n  left: 36px;\n\n}\n.j_linkRegAccount{\n  margin: auto;\n}\n.btn-two.c-orange.j_submitLogin {\n  width:93px ;\n  height: 32px;\n  margin: auto;\n  color: transparent;\n\n  background-image: url(" + __webpack_require__(167) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-btn-two .btn-two {\n  display: block;\n  width: 72px;\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n  color: #ffc221;\n\n  /*background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;*/\n\n  border-radius: 2px;\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  /*background-color: none;*/\n}\n\n.regpassword-icon {\n  background-repeat: no-repeat;\n  background-size: 0.8rem;\n  display: inline-block;\n  width: 40px;\n  height: 40px;\n  background-position: center center;\n  cursor: pointer;\n  position: absolute;\n  left: 78%;\n  top: -0.2rem;\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n  float: none;\n  margin-top: 0.4rem;\n  font-size: 0.65rem;\n}\n\n.cont-btn-two .btn-two:last-child {\n /* float: right;*/\n  float: none;\n  margin-top:10px;\n}\n.j_submitRegAccount{\n\n}\n.cont-btn-two .btn-one {\n  position: absolute;\n  top:10px;\n  left: 100px;\n\n  display: inline-block;\n  color: transparent;\n\n  width: 144px;\n  height: 32px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n\n  background-image: url(" + __webpack_require__(168) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  border-radius: 2px;\n}\n\n.btn-one:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.c-orange {\n  background:none;\n  /* background: #F27241;\n  background: linear-gradient(#5cddf9, #196372) !important;\n  background: -webkit-linear-gradient(#5cddf9, #196372);\n  background: -o-linear-gradient(#5cddf9, #196372);\n  background: -moz-linear-gradient(#5cddf9, #196372);*/\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  position: absolute;\n  top: 220px;\n  left: 0px;\n  width: 300px;\n  height: 20px;\n  font-size: 0.7rem;\n  text-align: center;\n  background: rgba(250, 171, 20, 0.5);\n  padding: 16px 5px 20px 5px;\n  box-sizing: border-box;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 0;\n  opacity: 0.8;\n  width: 20px;\n  height: 20px;\n  /* margin-top: -0.2rem; */\n\n\n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n  font-size: 0.4rem;\n  margin: 0 0 0 5px;\n  color: #f8a31b;\n  /* width: 220px; */\n  line-height: 5px;\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.4rem;\n  color: #f8a31b;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 220px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-size: 0.7rem;\n  font-style: italic;\n  position: absolute;\n  top: 166px;\n  left: 30px;\n\n  color:#ffc221 ;\n\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 2.4rem;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  font-size: 0.5rem;\n  color:#ffc221 ;\n  border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n\n  text-align: center;\n}\n\n.cont-link-two .link-two:last-child {\n  float: right;\n  text-align: center;\n\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 161 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "89657b9cad0adc7318359e470ac79acc.png";

/***/ }),
/* 162 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "cd419bc950938ff8d89fc8b702218e47.png";

/***/ }),
/* 163 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "65c6eb91f57d4bbe20cb3fa6ed06e5b9.png";

/***/ }),
/* 164 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "655c7ea8f074086c7e6017f2cbe51d13.png";

/***/ }),
/* 165 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "5ddc81a715ec2dcb38bb742ac955ffbd.png";

/***/ }),
/* 166 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "6f56ea23824d81658de2f8b69c7f6163.png";

/***/ }),
/* 167 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "c770a4a33b9b81e0cdf9224c2a394cb1.png";

/***/ }),
/* 168 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "32f1d150d3c358970fa4c8f3b0de22d2.png";

/***/ }),
/* 169 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(170);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop-ios-10.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop-ios-10.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 170 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n  width: 40px;\n  height: 40px;\n  position: absolute;\n  top: 100px;\n  left: 5px;\n  /* border: 8px inset #924d0f9e; */\n  /* border-radius: 50px 50px 50px 50px; */\n  box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n  /*-webkit-transform: rotate(-45deg);\n  -moz-transform: rotate(-45deg);\n  -ms-transform: rotate(-45deg);\n  -o-transform: rotate(-45deg);\n  transform: rotate(-45deg);*/\n  background-image: url(" + __webpack_require__(171) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  z-index:10;\n}\n\n.unfold {\n  width: 0px;\n  height: 25px;\n  position: absolute;\n  background: #3f0201;\n  top: 8px;\n  left: 38px;\n  font-size: 13px;\n  color: #fab707;\n  text-align: center;\n  line-height: 25px;\n  -moz-border-radius: 20px 20px 0;\n  border-radius: 0px 20px 20px 0px;\n  z-index: 3;\n}", ""]);

// exports


/***/ }),
/* 171 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "1d53a7b793f1062df6b84d936f79fadd.png";

/***/ }),
/* 172 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(173);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./theme-ios11.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./theme-ios11.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 173 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\n\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  background: rgba(0, 0, 0, 0.4);\n  display: none;\n}\n\n.logincon {\n  width: 13.333rem;\n  height: 12.541rem;\n  transform:translateX(-50%);\n  top:25%;\n  left: 50%;\n  font-size: 14px;\n  background: none;\n  border-radius: 12px;\n  position: absolute;\n  box-shadow: none;\n  background-image: url(" + __webpack_require__(174) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\ntext-align: center;\nheight: 20%;\ndisplay:none;\n}\n\n.logo {\nwidth: 30%;\nheight: 1rem;\ndisplay: inline-block;\nbackground-image: url(" + __webpack_require__(13) + ");\nbackground-size: contain;\nbackground-repeat: no-repeat;\nbackground-position: center;\n}\n\n.loginmiddle, .register {\nmargin-top: 2%;\nwidth: 80%;\nmargin-left: 10%;\n}\n\n.loginmiddle1 {\ntext-align: center;\ncolor: #ee7639;\nfont-size: 14px;\n}\n\n.loginmiddle-input-wrap {\nmargin-top: 3%;\nborder: 1px solid #ddd;\npadding: 2%;\nposition: relative;\nborder-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\nborder: none;\nheight: 92%;\nposition: absolute;\ntop: 0;\nwidth: 77%;\npadding-left: 1%;\nborder-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\nuser-select: text;\n}\n\n.loginmiddle-span {\nwidth: 20%;\ndisplay: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\ndisplay: inline-block;\nwidth: 44%;\ncolor: #fff;\ntext-align: center;\npadding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\nmargin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\nbackground: #f0703f;\nmargin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\nbackground: #3e8cd4;\nmargin-left: 5%\n}\n\n.hide {\ndisplay: none;\n}\n\n#loading {\nz-index: 999;\nposition: relative;\ndisplay: none;\n}\n\n.spinner {\nmargin: 100px auto;\nwidth: 50px;\nheight: 60px;\ntext-align: center;\nfont-size: 10px;\n}\n\n.spinner > div {\nbackground-color: #f0703f;\nheight: 100%;\nwidth: 6px;\ndisplay: inline-block;\n\n-webkit-animation: stretchdelay 1.2s infinite ease-in-out;\nanimation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n-webkit-animation-delay: -1.1s;\nanimation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n-webkit-animation-delay: -1.0s;\nanimation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n-webkit-animation-delay: -0.9s;\nanimation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n-webkit-animation-delay: -0.8s;\nanimation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n0%, 40%, 100% {\n-webkit-transform: scaleY(0.4)\n}\n20% {\n-webkit-transform: scaleY(1.0)\n}\n}\n\n@keyframes stretchdelay {\n0%, 40%, 100% {\ntransform: scaleY(0.4);\n-webkit-transform: scaleY(0.4);\n}\n20% {\ntransform: scaleY(1.0);\n-webkit-transform: scaleY(1.0);\n}\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  position: relative;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  color: #afbfff;\n  font-size: .8rem;\n position: absolute;\n  top:60px;\n  left: 112px;\n\n\n\n\n}\n\n.cont-box {\n  position: absolute;\n  display: inline-block;\n  width: 220px;\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n\n  border: none;\n}\n.pop-cont .cont-box:nth-of-type(1){\n  top:88px;\n  left:60px;\n\n}\n.pop-cont .cont-box:nth-of-type(2){\n  top: 121px;\n  left: 60px;\n}\n\n\n\n.history_accounts-icon{\n  left: 150px;\n  top: 5px;\n  width: 30px;\n  height: 30px;\n\n}\n.showpassword-icon{\n  left: 145px;\n  top: 0px;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  width: 200px;\n  height: 40px;\n  line-height: 40px;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  color: #bcc4cf;\n\n  background-color: transparent;\n\n}\n.box-input::-webkit-input-placeholder{\n  color: #bcc4cf;\n}\n\n\n.box-input-phone {\n  width: 44.5%;\n  padding-left: 5%;\n}\n\n.box-code {\n  background-color: #abc4f5;\n  float: right;\n  width: 30%;\n  height: 20px;\n  line-height: 20px;\n  position: relative;\n  left: -40px;\n  top: 9px;\n  font-size: 0.2rem;\n  border-radius: 6px;\n  color: #fff;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 180px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  position: absolute;\n\n  top: 156px;\n  left: 62px;\n\n\n}\n\n.cont-btn-two .btn-two {\n  display: inline-block;\n  width: 72px;\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n  color: #4e76af;\n\n  /*background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;*/\n\n  border-radius: 2px;\n\n\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  /*background-color: none;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n  float: left;\n}\n\n.cont-btn-two .btn-two:last-child {\n  float: right;\n\n  margin-top:0px;\n}\n\n.cont-btn-two .btn-one {\n\n\n  display: inline-block;\n  color: #fff;\n  width: 80px;\n  height: 32px;\n  line-height: 32px;\n  text-align: center;\n  font-size: 0.6rem;\n  border-radius: 2px;\n  background-color: #abc4f5;\n\n  /*background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;*/\n\n\n}\n\n.btn-one:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.c-orange {\n  background:none;\n  /* background: #F27241;\n  background: linear-gradient(#5cddf9, #196372) !important;\n  background: -webkit-linear-gradient(#5cddf9, #196372);\n  background: -o-linear-gradient(#5cddf9, #196372);\n  background: -moz-linear-gradient(#5cddf9, #196372);*/\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  position: absolute;\n  top:240px;\n  left: 0px;\n  width: 310px;\n  font-size: 0.7rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float: left;\n  margin-left: 0;\n  opacity: 0.8;\n  width: 15px;\n  height: 15px;\n  margin-top: -0.2rem;\n  background-color: #fff;\n  -webkit-appearance: none;\n  border: 1px solid #c9c9c9;\n  border-radius: 50%;\n  outline: none;\n\n}\n.cont-link-protocol .text-check:checked{\n  background-color: #abc4f5;\n}\n.cont-link-protocol .text-protocol {\n  float: left;\n  font-size: 0.4rem;\n  margin: -0.2rem 0 0.2rem 0.2rem;\n  color: #fff;\n  /* width: 220px; */\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.4rem;\n  color: #ffffff;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 220px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-size: 0.7rem;\n\n  position: absolute;\n  top: 172px;\n  left: 60px;\n\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 2.4rem;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  font-size: 0.5rem;\n  color: #abc4f5;\n  border-radius: 2px;\n}\n\n.cont-link-two .link-two:first-child {\n\n  text-align: center;\n}\n\n.cont-link-two .link-two:last-child {\n  float: none;\n  text-align: center;\n  margin-left: 20px;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 174 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "d842aaee41db705d8d274650ef878809.png";

/***/ }),
/* 175 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(176);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../../node_modules/css-loader/index.js!./drop_11.css", function() {
			var newContent = require("!!../../../node_modules/css-loader/index.js!./drop_11.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 176 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 70px;\n    height: 70px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    /*border: 10px solid #e7ca8659;*/\n    border-radius: 50px 50px 50px 50px;\n    /*box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;*/\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    background: url(" + __webpack_require__(177) + ") center no-repeat;\n    background-size: 100%;\n    z-index:3;\n}\n\n.unfold {\n    /*width: 129px;*/\n    height: 70px;\n    /*height: 40px;*/\n    position: absolute;\n    /*background: rgba(231,202,134, 0.2);*/\n    top: 1px;\n    left: 40px;\n    text-align:center;\n    line-height: 65px;\n    -moz-border-radius: 80px 80px 0;\n    border-radius: 0px 80px 80px 0px;\n    z-index:3;\n    background: url(" + __webpack_require__(178) + ") right no-repeat;\n    background-size:200%;\n    color: #181b20;\n\n}", ""]);

// exports


/***/ }),
/* 177 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "208ae5d7255996201ef84dae70809458.png";

/***/ }),
/* 178 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "3c3c4a114abc88c65a2867f28b1e5b80.png";

/***/ }),
/* 179 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\n\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  background: rgba(0, 0, 0, 0.4);\n  display: none;\n}\n\n.logincon {\n  width: 100%;\n  height: 100%;\n  left: 0;\n  margin-left: 0;\n  top: 0;\n  padding-top: 25vh;\n  font-size: 5px;\n  border-radius: 0px;\n  position: absolute;\n  background-color: rgba(255, 255, 255, 0);\n  /* padding: 1em;\n  -webkit-box-sizing: border-box;\n  -moz-box-sizing: border-box;\n  box-sizing: border-box; */\n  background-image: url(" + __webpack_require__(180) + ");\n  background-size: cover;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\n  width: 92vw;\n  text-align: center;\n  position: absolute;\n  top: 20vh;\n  height: auto;\n}\n\n.logo {\n  width: 30%;\n  text-align: center;\n  /* height: 10rem; */\n  display: inline-block;\n  /* background-image: url(../assets/bt.png); */\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.loginmiddle, .register {\n  margin-top: 2%;\n  width: 80%;\n  margin-left: 10%;\n}\n\n.loginmiddle1 {\n  text-align: center;\n  color: #ee7639;\n  font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n  margin-top: 3%;\n  border: 1px solid #ddd;\n  padding: 2%;\n  position: relative;\n  border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n  border: none;\n  height: 92%;\n  position: absolute;\n  top: 0;\n  width: 77%;\n  padding-left: 1%;\n  border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n  user-select: text;\n}\n\n.loginmiddle-span {\n  width: 20%;\n  display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n  display: inline-block;\n  width: 44%;\n  color: #fff;\n  text-align: center;\n  padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n  margin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\n  background: #f0703f;\n  margin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\n  background: #3e8cd4;\n  margin-left: 5%\n}\n\n.hide {\n  display: none;\n}\n\n#loading {\n  z-index: 999;\n  position: relative;\n  display: none;\n}\n\n.spinner {\n  margin: 100px auto;\n  width: 50px;\n  height: 60px;\n  text-align: center;\n  font-size: 10px;\n}\n\n.spinner > div {\n  background-color: #f0703f;\n  height: 100%;\n  width: 6px;\n  display: inline-block;\n\n  -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n  animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n  -webkit-animation-delay: -1.1s;\n  animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n  -webkit-animation-delay: -1.0s;\n  animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n  -webkit-animation-delay: -0.9s;\n  animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n  -webkit-animation-delay: -0.8s;\n  animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n  0%, 40%, 100% {\n      -webkit-transform: scaleY(0.4)\n  }\n  20% {\n      -webkit-transform: scaleY(1.0)\n  }\n}\n\n@keyframes stretchdelay {\n  0%, 40%, 100% {\n      transform: scaleY(0.4);\n      -webkit-transform: scaleY(0.4);\n  }\n  20% {\n      transform: scaleY(1.0);\n      -webkit-transform: scaleY(1.0);\n  }\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  margin-top: 0.1rem;\n  color: rgb(127, 102, 73);\n  font-size: .8rem;\n  font-weight: 700;\n}\n\n.cont-box {\n  display: inline-block;\n  display: inline-block;\n  position: relative;\n  width: 60%;\n  height: 1.2rem;\n  line-height: 1.2rem;\n  text-align: left;\n  margin: 0 0 0.7rem;\n  font-size: .6rem;\n  background: white;\n  border-radius: 5px;\n  border: 0;\n  background: rgb(123, 103, 77);\n  color: white;\n}\n\n.history_accounts-icon {\n  top: -0.2rem;\n  left: 84%;\n}\n\n.showpassword-icon, .regpassword-icon{\n  top: -0.2rem;\n  left: 84%;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  position: relative;\n  width: 80%;\n  height: 1.2rem;\n  line-height: 1.2rem;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  border-radius: 5px;\n  color: #fff;\n  background-color: transparent;\n}\n\n.box-input::-webkit-input-placeholder, .box-input-phone::-webkit-input-placeholder {\n  color: rgb(189, 186, 186);\n}\n\n.box-input-phone {\n  width: 90%;\n  padding-left: 5%;\n  border-radius: 5px;\n  color: #fff;\n  background-color: transparent;\n}\n\n.box-code {\n  background-color: rgba(35, 33, 34, 1);\n  width: 2.7rem;\n  height: 1rem;\n  text-align: center;\n  line-height: 1rem;\n  position: absolute;\n  right: 1%;\n  top: 0.1rem;\n  color: white;\n  font-size: .28rem;\n  border-radius: 6px;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 80%;\n  height: 1.3rem;\n  line-height: 1.3rem;\n}\n\n.cont-btn-two .btn-two {\n  display: inline-block;\n  /* width: 45%; */\n  /* height: 1.8rem;\n  line-height: 1.8rem; */\n  text-align: center;\n  font-size: 0.6rem;\n  color: white;\n  margin-left: 1rem;\n  /* background: #56599d;\n  border-radius: 15px; */\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  background-color: none;\n\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  background-color: none;\n}\n\n.cont-btn-two .btn-two:first-child {\n  background-color: rgba(0, 0, 0, 0);\n  display: inline-block;\n  width: 4rem;\n  height: 1rem;\n  line-height: 1rem;\n  text-align: center;\n  color: white;\n  font-weight: 800;\n  font-size: .65rem;\n  text-stroke-width: 0.02em;\n  text-stroke-color: #f79218;\n  -webkit-text-stroke-width: 0.02em;\n  -webkit-text-stroke-color: #f79218;\n  position: absolute;\n  left: 50%;\n  margin-left: -2rem;\n  top: 7.5rem;\n}\n\n.cont-btn-two .btn-two:last-child {\n  background-image: url(" + __webpack_require__(70) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  width: 4rem;\n  height: 1.3rem;\n  font-size: 0.58rem;\n  font-weight: 800;\n  line-height: 1.45rem;\n  text-stroke-width: 0.02em;\n  text-stroke-color: #f79218;\n  -webkit-text-stroke-width: 0.02em;\n  -webkit-text-stroke-color: #f79218;\n  position: absolute;\n  left: 50%;\n  margin-left: -2rem;\n  background-color: rgba(0, 0, 0, 0);\n}\n\n.cont-btn-two .btn-one {\n  display: inline-block;\n  width: 100%;\n  text-align: center;\n  color: white;\n  background: rgba(255, 255, 255, 0);\n  background-image: url(" + __webpack_require__(70) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  width: 4rem;\n  height: 1.3rem;\n  font-size: 0.6rem;\n  font-weight: 800;\n  line-height: 1.45rem;\n  background-color: rgba(0, 0, 0, 0);\n  text-stroke-width: 0.02em;\n  text-stroke-color: #f79218;\n  -webkit-text-stroke-width: 0.02em;\n  -webkit-text-stroke-color: #f79218;\n}\n\n.btn-one:active {\n  outline: none;\n  background-color: none;\n}\n\n.c-orange {\n  background: #F27241;\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  font-size: 0.7rem;\n  height: 1rem;\n  width: 100%;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 20%;\n  opacity: 0.8;\n  width: 0.8rem;\n  height: 0.8rem;\n  background-color: #fff;\n  -webkit-appearance: none;\n  border: 2px solid white;\n  border-radius: 50%;\n  /* margin-top: -0.4rem; */\n}\n\n.cont-link-protocol .text-check:checked{\n  background-color: rgb(141, 77, 3);\n}\n\n.cont-link-protocol .text-protocol {\n  /* float: left; */\n  font-size: 0.4rem;\n  /* margin: -0.7rem 0 0.2rem 2rem; */\n  color: white;\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.4rem;\n  color: #1E90FF;\n}\n\n.cont-link-two {\n  display: block;\n  width: 80%;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin: auto;\n  margin-top: 0.3rem;\n  font-size: 0.7rem;\n  position: relative;\n  text-align: center;\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  height: 1.3rem;\n  line-height: 1.3rem;\n  margin-top: .3rem;\n  color: #d8bc98;\n  border-radius: 2px;\n  display: block;\n  width: 100%;\n  color: white;\n  text-stroke-width: 0.02em;\n  text-stroke-color: #f79218;\n  -webkit-text-stroke-width: 0.02em;\n  -webkit-text-stroke-color: #f79218;\n  font-size: .65rem;\n  font-weight: 800;\n}\n\n.cont-link-two .link-two:first-child {\n  /* position: absolute; */\n  text-align: center;\n}\n\n.cont-link-two .link-two:last-child {\n  /* position: absolute; */\n  text-align: center;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}\n", ""]);

// exports


/***/ }),
/* 180 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "0b60decfa232162e1321f0d8d321b904.png";

/***/ }),
/* 181 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n  width: 30px;\n  height: 30px;\n  position: absolute;\n  top: 100px;\n  left: 5px;\n  border: 10px groove #f5b907a3;\n  border-radius: 50px 50px 50px 50px;\n  box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;\n  /*-webkit-transform: rotate(-45deg);\n  -moz-transform: rotate(-45deg);\n  -ms-transform: rotate(-45deg);\n  -o-transform: rotate(-45deg);\n  transform: rotate(-45deg);*/\n  z-index:10;\n}\n\n.unfold {\n  width: 0px;\n  height: 40px;\n  position: absolute;\n  background: #f5b90775;\n  top: -5px;\n  left: 30px;\n  color: #f0f0f0;\n  text-align:center;\n  line-height:40px;\n  -moz-border-radius: 50px 50px 0;\n  border-radius: 0px 50px 50px 0px;\n  z-index:3;\n}", ""]);

// exports


/***/ }),
/* 182 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n    position: absolute;\n    width: 100%;\n    height: 100%;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    z-index: 1;\n}\n\n#protocol-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n#btn-close {\n    position: absolute;\n    right: 2%;\n    top: 2%;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n}\n\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n}\n\n.login37-wrap {\n    z-index: 2;\n    width: 100%;\n    height: 100%;\n    position: absolute;\n    top: 0;\n    left: 0;\n    background:rgba(0, 0, 0, 0.86);\n    display: none;\n}\n\n.logincon {\n    width: 100vw;\n    height: 118vw;\n    /*left: 0;*/\n    /*margin-left: 0;*/\n    /*top: 0;*/\n    /*padding-top: 25vh;*/\n    /*font-size: 5px;*/\n    /*border-radius: 0px;*/\n    /*position: absolute;*/\n    /*background-color: rgba(255, 255, 255, 0);*/\n    /* padding: 1em;\n    -webkit-box-sizing: border-box;\n    -moz-box-sizing: border-box;\n    box-sizing: border-box; */\n    /*width: 15.65rem;*/\n    /*!*width: 100%;*!*/\n    /*height:18.36266rem;*/\n    background: url(" + __webpack_require__(183) + ") center no-repeat;\n    background-size: 100%;\n    left:0;\n    border-radius: 0px;\n    box-shadow:none;\n    position: absolute;\n    bottom: 0;\n}\n\n.logintop {\n    /*width: 92vw;*/\n    /*text-align: center;*/\n    /*position: absolute;*/\n    /*top: 20vh;*/\n    /*height: auto;*/\n}\n\n.logo {\n    width: 30%;\n    text-align: center;\n    /* height: 10rem; */\n    display: inline-block;\n    /* background-image: url(../assets/bt.png); */\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n    position: relative;\n    top: -1.2rem;\n}\n\n.loginmiddle, .register {\n    margin-top: 2%;\n    width: 80%;\n    margin-left: 10%;\n}\n\n.loginmiddle1 {\n    text-align: center;\n    color: #ee7639;\n    font-size: 14px;\n}\n\n.loginmiddle-input-wrap {\n    margin-top: 3%;\n    border: 1px solid #ddd;\n    padding: 2%;\n    position: relative;\n    border-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\n    border: none;\n    height: 92%;\n    position: absolute;\n    top: 0;\n    width: 77%;\n    padding-left: 1%;\n    border-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\n    /*user-select: text;*/\n}\n\n.loginmiddle-span {\n    width: 20%;\n    display: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\n    display: inline-block;\n    width: 44%;\n    color: #fff;\n    text-align: center;\n    padding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\n    /*margin: 6%;*/\n}\n\n.loginbtn1, .registerbtn1 {\n    /*background: #f0703f;*/\n    /*margin-right: 3%;*/\n}\n\n.loginbtn2, .registerbtn2 {\n    /*background: #3e8cd4;*/\n    /*margin-left: 5%*/\n}\n\n.hide {\n    display: none;\n}\n\n#loading {\n    z-index: 999;\n    position: relative;\n    display: none;\n}\n\n.spinner {\n    margin: 100px auto;\n    width: 50px;\n    height: 60px;\n    text-align: center;\n    font-size: 10px;\n}\n\n.spinner > div {\n    background-color: #f0703f;\n    height: 100%;\n    width: 6px;\n    display: inline-block;\n\n    -webkit-animation: stretchdelay 1.2s infinite ease-in-out;\n    animation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n    -webkit-animation-delay: -1.1s;\n    animation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n    -webkit-animation-delay: -1.0s;\n    animation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n    -webkit-animation-delay: -0.9s;\n    animation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n    -webkit-animation-delay: -0.8s;\n    animation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n    0%, 40%, 100% {\n        -webkit-transform: scaleY(0.4)\n    }\n    20% {\n        -webkit-transform: scaleY(1.0)\n    }\n}\n\n@keyframes stretchdelay {\n    0%, 40%, 100% {\n        transform: scaleY(0.4);\n        -webkit-transform: scaleY(0.4);\n    }\n    20% {\n        transform: scaleY(1.0);\n        -webkit-transform: scaleY(1.0);\n    }\n}\n\n/* 新版登录*/\nhtml {\n    font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n    margin: 0;\n    padding: 0;\n    font-family: 'Lato', sans-serif;\n    font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n    font-style: normal;\n    font-weight: normal;\n}\n\nul, li {\n    list-style: none;\n}\n\na {\n    color: black;\n    text-decoration: none;\n}\n\nimg {\n    border: 0;\n}\n\n.pop-bg {\n    display: none;\n    z-index: 2;\n    position: absolute;\n    top: 0;\n    left: 0;\n    width: 100%;\n    height: 100%;\n    background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n    width: 100%;\n    text-align: center;\n    color: black;\n}\n\n.cont-title {\n    display: block;\n    text-align: center;\n    font-size: .75rem;\n    /*font-weight: 700;*/\n    background: url(" + __webpack_require__(184) + ") center no-repeat;\n    background-size: 100% 100%;\n    width: 8.43rem;\n    height: 1.3354rem;\n    line-height: 1.3354rem;\n    color: #ffffff;\n    margin: 0 auto;\n    margin-top: -3rem;\n\n\n}\n\n.cont-box {\n    display: inline-block;\n    width: 75%;\n    height: 1.2rem;\n    line-height: 1.2rem;\n    text-align: left;\n    margin: 0 0 0.7rem;\n    font-size: .6rem;\n    /*background: white;*/\n    border-radius: 5px;\n    border: 0;\n    background: inherit;\n    /* margin: 0 0 0.7rem; */\n    /* background: white; */\n    margin-left: -2.0rem;\n}\n\n.history_accounts-icon {\n    top: 2.0rem;\n    left: 8.7rem;\n}\n.cont-box .j_data_loginAccount{\n    margin-top: 0.9rem;\n}\n.cont-box .j_data_regPhone{\n    margin-top: 0.9rem;\n}\n.cont-box .j_data_code{\n    margin-top: 0.2rem;\n}\n\n\n.cont-box .j_data_loginPass{\n    margin-top: 0.2rem;\n}\n.cont-box .j_data_regAccount{\n    margin-top: 0.9rem;\n}\n.cont-box .j_data_regPass{\n    margin-top: 0.2rem;\n}\n.showpassword-icon, .regpassword-icon{\n    top: 3.3rem;\n    left: 8.6rem;\n}\n\n.box-input {\n    padding-left: 5%;\n    float: left;\n    width: 80%;\n    height: 1.2rem;\n    line-height: 1.2rem;\n    border: none;\n    outline: none;\n    font-size: .6rem;\n    border-radius: 5px;\n    background: inherit;\n}\n\n.box-input-phone {\n    width: 90%;\n    padding-left: 5%;\n    border-radius: 5px;\n}\n\n.box-code {\n    width: 2.7rem;\n    height: 1rem;\n    text-align: center;\n    line-height: 1rem;\n    position: absolute;\n    top: 4.8rem;\n    left: 50%;\n    color: #4371ae;\n    font-size: .5rem;\n    border-radius: 6px;\n    margin-left: -0.2rem;\n}\n\n.box-input-code {\n    width: 95%;\n    padding-left: 5%;\n}\n\n.cont-btn-two {\n    display: inline-block;\n    width: 80%;\n    height: 1.3rem;\n    line-height: 1.3rem;\n}\n\n.cont-btn-two .btn-two {\n    display: inline-block;\n    /* width: 45%; */\n    /* height: 1.8rem;\n    line-height: 1.8rem; */\n    text-align: center;\n    font-size: 0.6rem;\n    color: white;\n    margin-left: 1rem;\n    /* background: #56599d;\n    border-radius: 15px; */\n}\n\n.cont-btn-two .btn-two:first-child:active {\n    outline: none;\n    background-color: none;\n\n}\n\n.cont-btn-two .btn-two:last-child:active {\n    outline: none;\n    background-color: none;\n}\n\n.cont-btn-two .btn-two:first-child {\n    background-color: rgba(0, 0, 0, 0);\n    display: inline-block;\n    width: 4rem;\n    height: 1rem;\n    line-height: 1rem;\n    text-align: center;\n    /* font-weight: 800; */\n    position: absolute;\n    left: 50%;\n    margin-left: 3.5rem;\n    top: 2.10rem;\n    color: #4371ae;\n    font-size: 0.5rem;\n}\n\n.cont-btn-two .btn-two:last-child {\n    background-image: url(" + __webpack_require__(87) + ");\n    background-size: contain;\n    background-repeat: no-repeat;\n    background-position: center;\n    width: 4.6115rem;\n    height: 1.2937rem;\n    font-size: 0.58rem;\n    font-weight: 800;\n    line-height: 1.45rem;\n    position: absolute;\n    left: 50%;\n    margin-left: -4rem;\n    background-color: rgba(0, 0, 0, 0);\n    top:5.6rem;\n}\n\n.cont-btn-two .btn-one {\n    display: inline-block;\n    text-align: center;\n    color: white;\n    background: url(" + __webpack_require__(87) + ") center no-repeat;\n    background-size: 100% 100%;\n    width: 4.6115rem;\n    height: 1.2937rem;\n    font-size: 0.6rem;\n    font-weight: 800;\n    line-height: 1.45rem;\n    position: absolute;\n    left: 50%;\n    margin-left: -4rem;\n    background-color: rgba(0, 0, 0, 0);\n    top: 5.1rem;\n}\n.cont-btn-two .j_submitRegPhone{\n    top: 5.9rem;\n}\n.btn-one:active {\n    outline: none;\n    background-color: none;\n}\n\n.c-orange {\n    /*background: #F27241;*/\n}\n\n.c-blue {\n    background: #3F8ED6;\n}\n\n.cont-link-protocol {\n    text-align: center;\n    position: absolute;\n    top: 7.0rem;\n    margin-left: 0.9rem;\n    font-size: 0.4rem;\n\n}\n\n.cont-link-protocol .text-check {\n    float:left;\n    margin-left: 0.2rem;\n    opacity: 0.8;\n    width: 0.7rem;\n    height:0.7rem;\n    color: #4371ae;\n    background: #4371ae;\n    border-radius:10px;\n    padding: 4px;\n    z-index: 4;\n    /*box-sizing: content-box;*/\n    border: none;\n    position: relative;\n    /*top:-0.4rem;*/\n}\n\n.cont-link-protocol .text-protocol {\n    float: left;\n    font-size: 0.34rem;\n    margin: -0.3rem 0 0.2rem 0.2rem;\n    color: #4371ae;\n}\n\n.cont-link-protocol .link-protocol {\n    font-size: 0.34rem;\n    color: #4371ae;\n}\n\n.cont-link-two {\n    display: block;\n    height: 1.8rem;\n    line-height: 1.8rem;\n    margin: auto;\n    margin-top: 0.3rem;\n    width: 4.0rem;\n    font-size: 0.7rem;\n    position: absolute;\n    top: 0;\n    left: 50%;\n    margin-left: 3.5rem;\n    text-align: left;\n}\n\n.cont-link-two .link-two {\n    display: inline-block;\n    height: 0.8rem;\n    line-height: 0.8rem;\n    border-radius: 2px;\n    /*width: 100%;*/\n    font-size: .4rem;\n    letter-spacing: 0px;\n    color: #4371ae;\n    position: absolute;\n    top:0;\n    width:4.0rem;\n\n}\n.cont-link-protocol input{\n    width: 32px;\n    height: 16px;\n}\n.cont-link-two .link-two:first-child {\n    /* position: absolute; */\n    text-align: center;\n    position: absolute;\n    top:2.8rem;\n    width:4.0rem;\n    font-size: 0.5rem;\n}\n\n.cont-link-two .link-two:last-child {\n    /* position: absolute; */\n    text-align: center;\n    position: absolute;\n    top:1.9rem;\n    width:4.0rem;\n    font-size: 0.5rem;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n    position: absolute;\n    top: 0;\n    right: 0;\n    height: 12%;\n    width: 100%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\n.paymask2 {\n    position: absolute;\n    top: 0;\n    left: 0;\n    height: 15%;\n    width: 25%;\n    /*background: red;*/\n    z-index: 2;\n    opacity: 0;\n}\n\ndiv {\n    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}\ninput[type=checkbox]{\n    width: 20px;\n    height: 16px;\n    color: white;\n    background-color: #3a5c94;\n    border-radius: 10px;\n}\n/*!*修格checkbox input 的样式*!*/\n/*input[type=checkbox]::before {*/\n    /*content: '';*/\n    /*position: absolute;*/\n    /*top: 0;*/\n    /*left: 0;*/\n    /*width: 15px;*/\n    /*height: 15px;*/\n    /*line-height: 15px;*/\n    /*text-align: center;*/\n    /*color: white;*/\n    /*font-size: 15px;*/\n    /*background-color: #999;*/\n    /*border-radius: 4px;*/\n/*}*/\n\n/*input[type=checkbox]:checked::before {*/\n    /*color: white;*/\n    /*background-color: #26D09F;*/\n    /*content: '*';*/\n/*}*/", ""]);

// exports


/***/ }),
/* 183 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "1cbe59fa144fd8e0e8bfc20138cb0428.png";

/***/ }),
/* 184 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "f8970668bf4ea022ee6f7c6f76738d4b.png";

/***/ }),
/* 185 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 40px;\n    height: 40px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    /*border: 10px solid #e7ca8659;*/\n    border-radius: 50px 50px 50px 50px;\n    /*box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;*/\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    background: url(" + __webpack_require__(186) + ") center no-repeat;\n    background-size: 100%;\n    z-index:3;\n}\n\n.unfold {\n    width: 0px;\n    height: 30px;\n    position: absolute;\n    background: #3d6dad;\n    top: 6px;\n    left: 35px;\n    color: #f0f0f0;\n    text-align: center;\n    line-height: 30px;\n    -moz-border-radius: 50px 50px 0;\n    border-radius: 5px 20px 20px 5px;\n    z-index: 2;\n}", ""]);

// exports


/***/ }),
/* 186 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "784b88ed4148e6422649ed1da0a2265a.png";

/***/ }),
/* 187 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\ninput.checkbox{\n  background-color:#fe7d2c;\n}\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  /*background: rgba(0, 0, 0, 0.4);*/\n  background: rgba(0, 0, 0, 0.81);\n  display: none;\n}\n\n.logincon {\n  width: 13.333rem;\n  height: 10.25rem;\n  transform:translateX(-50%);\n  top:25%;\n  left: 50%;\n  font-size: 14px;\n  background: none;\n  border-radius: 12px;\n  position: absolute;\n  box-shadow: none;\n  background-image: url(" + __webpack_require__(188) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\ntext-align: center;\nheight: 20%;\ndisplay:none;\n}\n\n.logo {\nwidth: 30%;\nheight: 1rem;\ndisplay: inline-block;\nbackground-image: url(" + __webpack_require__(13) + ");\nbackground-size: contain;\nbackground-repeat: no-repeat;\nbackground-position: center;\n}\n\n.loginmiddle, .register {\nmargin-top: 2%;\nwidth: 80%;\nmargin-left: 10%;\n}\n\n.loginmiddle1 {\ntext-align: center;\ncolor: #ee7639;\nfont-size: 14px;\n}\n\n.loginmiddle-input-wrap {\nmargin-top: 3%;\nborder: 1px solid #ddd;\npadding: 2%;\nposition: relative;\nborder-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\nborder: none;\nheight: 92%;\nposition: absolute;\ntop: 0;\nwidth: 77%;\npadding-left: 1%;\nborder-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\nuser-select: text;\n}\n\n.loginmiddle-span {\nwidth: 20%;\ndisplay: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\ndisplay: inline-block;\nwidth: 44%;\ncolor: #fff;\ntext-align: center;\npadding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\nmargin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\nbackground: #f0703f;\nmargin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\nbackground: #3e8cd4;\nmargin-left: 5%\n}\n\n.hide {\ndisplay: none;\n}\n\n#loading {\nz-index: 999;\nposition: relative;\ndisplay: none;\n}\n\n.spinner {\nmargin: 100px auto;\nwidth: 50px;\nheight: 60px;\ntext-align: center;\nfont-size: 10px;\n}\n\n.spinner > div {\nbackground-color: #f0703f;\nheight: 100%;\nwidth: 6px;\ndisplay: inline-block;\n\n-webkit-animation: stretchdelay 1.2s infinite ease-in-out;\nanimation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n-webkit-animation-delay: -1.1s;\nanimation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n-webkit-animation-delay: -1.0s;\nanimation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n-webkit-animation-delay: -0.9s;\nanimation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n-webkit-animation-delay: -0.8s;\nanimation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n0%, 40%, 100% {\n-webkit-transform: scaleY(0.4)\n}\n20% {\n-webkit-transform: scaleY(1.0)\n}\n}\n\n@keyframes stretchdelay {\n0%, 40%, 100% {\ntransform: scaleY(0.4);\n-webkit-transform: scaleY(0.4);\n}\n20% {\ntransform: scaleY(1.0);\n-webkit-transform: scaleY(1.0);\n}\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  position: relative;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  /*color: #afbfff;*/\n  font-size: .8rem;\n  position: absolute;\n\n  top: 52px;\n  left:70px;\n  /*color: #0c1573;*/\n  color: transparent;\n}\n.cont-login .cont-title{\n  width: 6.58rem;\n  height: 0.625rem;\n  background-image: url(" + __webpack_require__(189) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-reg-account .cont-title,.cont-reg-phone .cont-title{\n  width: 6.58rem;\n  height: 0.625rem;\n  background-image: url(" + __webpack_require__(190) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n\n.cont-box {\n  position: absolute;\n  display: inline-block;\n  width: 290px;\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n\n  border: none;\n}\n.pop-cont .cont-box:nth-of-type(1){\n  top:106px;\n  left:10px;\n\n}\n.pop-cont .cont-box:nth-of-type(2){\n  top: 174px;\n  left: 10px;\n}\n\n\n\n.history_accounts-icon{\n  left: 240px;\n  top: 5px;\n  width: 30px;\n  height: 30px;\n\n}\n.showpassword-icon{\n  left: 235px;\n  top: 0px;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  width: 200px;\n  height: 40px;\n  line-height: 40px;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  color: #bcc4cf;\n\n  background-color: transparent;\n\n}\n.box-input::-webkit-input-placeholder{\n  color: #bcc4cf;\n}\n\n\n.box-input-phone {\n  width: 54.5%;\n  padding-left: 5%;\n}\n\n.box-code {\n  line-height: 38px;\n  margin-top: 3px;\n  width: 110px;\n  height: 38px;\n  color: white;\n  border-radius: 19px;\n  background-color: #fe7d2c;\n  text-align: center;\n  z-index: 100;\n  position: absolute;\n  left: 183px;\n  top: 65px;\n}\n.cont-reg-account .cont-box .showpassword-icon,.cont-reg-account .cont-box .regpassword-icon {\n  background-repeat: no-repeat;\n  background-size: 0.8rem;\n  display: inline-block;\n  width: 40px;\n  height: 40px;\n  background-position: center center;\n  cursor: pointer;\n  position: absolute;\n  left: 240px;\n  top: 0rem;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 260px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  position: absolute;\n\n  top: 236px;\n  left: 18px;\n\n\n}\n\n.cont-btn-two .btn-two {\n  display: block;\n  width: 110px;\n  height: 36px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n  color: transparent;\n\n  /*background-image: url(../assets/ios-14/ios_btnbg.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  border-radius: 2px;*/\n}\n.btn-two.c-orange.j_linkRegAccount{\n  background-image: url(" + __webpack_require__(191) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.btn-two.c-orange.j_submitLogin{\n  background-image: url(" + __webpack_require__(192) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  /*background-color: none;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n  /*float: none;*/\n}\n\n.cont-btn-two .btn-two:last-child {\n  float: right;\n  /*float: none;\n  margin-top:18px;*/\n}\n\n.cont-btn-two .btn-one {\n  position: absolute;\n  top:0px;\n  left: 150px;\n\n  display: inline-block;\n  color: transparent;\n\n  width: 110px;\n  height: 36px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n\n  background-image: url(" + __webpack_require__(193) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  border-radius: 2px;\n}\n\n.btn-one:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.c-orange {\n  background:none;\n  /* background: #F27241;\n  background: linear-gradient(#5cddf9, #196372) !important;\n  background: -webkit-linear-gradient(#5cddf9, #196372);\n  background: -o-linear-gradient(#5cddf9, #196372);\n  background: -moz-linear-gradient(#5cddf9, #196372);*/\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  position: absolute;\n  top:285px;\n  left: 8px;\n  width: 100%;\n  font-size: 0.5rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 2%;\n  opacity: 0.8;\n  width: 20px;\n  height: 20px;\n  margin-top: -0.2rem;\n  background: #fe7d2c;\n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n\n  font-size: 0.5rem;\n  margin: -0.2rem 0 0.2rem 0;\n  color: #fe7d2c;\n  /*width: 220px;*/\n}\n.cont-reg-account .cont-link-two,.cont-reg-phone .cont-link-two{\n  width: 150px;\n}\n.cont-link-protocol .link-protocol {\n  font-size: 0.5rem;\n  /*color: #1d2e73;*/\n  color: #fe7d2c;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 260px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-size: 0.7rem;\n\n  position: absolute;\n  top: 222px;\n  left: 18px;\n\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 110px;\n  height: 36px;\n  line-height: 1.8rem;\n  font-size: 0.5rem;\n  color: transparent;\n  border-radius: 2px;\n\n  position: absolute;\n}\n.btn-one.c-orange.j_submitRegAccount{\n\n}\n.cont-link-two .link-two:first-child {\n /* text-align: left;*/\n  background-image: url(" + __webpack_require__(194) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  float: none;\n  left: 160px;\n  top: 72px;\n}\n\n.cont-link-two .link-two:last-child {\n\n  /*text-align: right;\n  margin-left: 30px;*/\n  float: none;\n  left: 0px;\n  top:4px;\n  background-image: url(" + __webpack_require__(195) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-link-two .link-two.j_linkRegAccount{\n  background-image: url(" + __webpack_require__(196) + ");\n}\n.cont-reg-phone .cont-link-two .link-two.j_linkHadAccount{\n  top: 52px;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 188 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "9b8ca401d66bc8ee9dc2915f10fde134.png";

/***/ }),
/* 189 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "abad2788059dac5e9f1d5b6c770cf046.png";

/***/ }),
/* 190 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "7d3603006b3d3c4a6d4b0bbe331c6b34.png";

/***/ }),
/* 191 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "bfa356df677aec65106e3496c6f75c96.png";

/***/ }),
/* 192 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "8eda03813d131e80dcbb927fd1b5e112.png";

/***/ }),
/* 193 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "0cc7ed5b70da3fbdde0ac437b80d12f6.png";

/***/ }),
/* 194 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "780b56adbadd103182def3d6c4806cdf.png";

/***/ }),
/* 195 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "6538af97c767c89d40e5ee7809cc90a8.png";

/***/ }),
/* 196 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "dafd7fcffdd833e31e465c155b418d4e.png";

/***/ }),
/* 197 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 40px;\n    height: 36px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    /*border: 10px solid #e7ca8659;*/\n    border-radius: 50px 50px 50px 50px;\n    /*box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;*/\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    background: url(" + __webpack_require__(198) + ") center no-repeat;\n    background-size: 100% 100%;\n    z-index:3;\n}\n\n.unfold {\n    /*width: 129px;*/\n    height: 22.8px;\n    /*width: 0px;*/\n    /*height: 40px;*/\n    position: absolute;\n    /*background: rgba(231,202,134, 0.2);*/\n    color: transparent;\n    top: 8px;\n    left: 39px;\n\n    text-align:center;\n    line-height:5px;\n    -moz-border-radius: 80px 80px 0;\n    border-radius: 0px 80px 80px 0px;\n    z-index:3;\n    background: url(" + __webpack_require__(199) + ") right no-repeat;\n    background-size:100%;\n}", ""]);

// exports


/***/ }),
/* 198 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "e78b3f50a209268f4650507ae0b13b33.png";

/***/ }),
/* 199 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "c5332cb0310fdb68cf5d29e47cd74dc9.png";

/***/ }),
/* 200 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "#gameFrame {\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  right: 0;\n  bottom: 0;\n  z-index: 1;\n}\ninput.checkbox{\n  background-color:#506bda;\n}\n#protocol-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n#btn-close {\n  position: absolute;\n  right: 2%;\n  top: 2%;\n  width: 10%;\n  height: 10%;\n  background: url(" + __webpack_require__(2) + ") no-repeat;\n  background-size: contain;\n}\n\n.protocolWrapper {\n  background: #ffffff;\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.payWrapper {\n  width: 80%;\n  height: 20rem;\n  position: absolute;\n  top: 50%;\n  margin-top: -10rem;\n  left: 10%;\n  z-index: 999;\n  display: none;\n}\n\n.login37-wrap {\n  z-index: 2;\n  width: 100%;\n  height: 100%;\n  position: absolute;\n  top: 0;\n  left: 0;\n  /*background: rgba(0, 0, 0, 0.4);*/\n  background: rgba(0, 0, 0, 0.81);\n  display: none;\n}\n\n.logincon {\n  width: 320px;\n  height:308px;\n  transform:translateX(-50%);\n  top:25%;\n  left: 50%;\n  font-size: 14px;\n  background: none;\n  border-radius: 12px;\n  position: absolute;\n  box-shadow: none;\n  background-image: url(" + __webpack_require__(201) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.logintop {\ntext-align: center;\nheight: 20%;\ndisplay:none;\n}\n\n.logo {\nwidth: 30%;\nheight: 1rem;\ndisplay: inline-block;\nbackground-image: url(" + __webpack_require__(13) + ");\nbackground-size: contain;\nbackground-repeat: no-repeat;\nbackground-position: center;\n}\n\n.loginmiddle, .register {\nmargin-top: 2%;\nwidth: 80%;\nmargin-left: 10%;\n}\n\n.loginmiddle1 {\ntext-align: center;\ncolor: #ee7639;\nfont-size: 14px;\n}\n\n.loginmiddle-input-wrap {\nmargin-top: 3%;\nborder: 1px solid #ddd;\npadding: 2%;\nposition: relative;\nborder-radius: 5px;\n}\n\n.loginmiddle-input-wrap input {\nborder: none;\nheight: 92%;\nposition: absolute;\ntop: 0;\nwidth: 77%;\npadding-left: 1%;\nborder-left: 1px solid #ddd;\n}\n\ninput, input:focus, input:active {\nuser-select: text;\n}\n\n.loginmiddle-span {\nwidth: 20%;\ndisplay: inline-block;\n}\n\n.loginmiddle-button span, .register-button span {\ndisplay: inline-block;\nwidth: 44%;\ncolor: #fff;\ntext-align: center;\npadding: 2% 0;\n}\n\n.loginmiddle-button, .register-button {\nmargin: 6%;\n}\n\n.loginbtn1, .registerbtn1 {\nbackground: #f0703f;\nmargin-right: 3%;\n}\n\n.loginbtn2, .registerbtn2 {\nbackground: #3e8cd4;\nmargin-left: 5%\n}\n\n.hide {\ndisplay: none;\n}\n\n#loading {\nz-index: 999;\nposition: relative;\ndisplay: none;\n}\n\n.spinner {\nmargin: 100px auto;\nwidth: 50px;\nheight: 60px;\ntext-align: center;\nfont-size: 10px;\n}\n\n.spinner > div {\nbackground-color: #f0703f;\nheight: 100%;\nwidth: 6px;\ndisplay: inline-block;\n\n-webkit-animation: stretchdelay 1.2s infinite ease-in-out;\nanimation: stretchdelay 1.2s infinite ease-in-out;\n}\n\n.spinner .rect2 {\n-webkit-animation-delay: -1.1s;\nanimation-delay: -1.1s;\n}\n\n.spinner .rect3 {\n-webkit-animation-delay: -1.0s;\nanimation-delay: -1.0s;\n}\n\n.spinner .rect4 {\n-webkit-animation-delay: -0.9s;\nanimation-delay: -0.9s;\n}\n\n.spinner .rect5 {\n-webkit-animation-delay: -0.8s;\nanimation-delay: -0.8s;\n}\n\n@-webkit-keyframes stretchdelay {\n0%, 40%, 100% {\n-webkit-transform: scaleY(0.4)\n}\n20% {\n-webkit-transform: scaleY(1.0)\n}\n}\n\n@keyframes stretchdelay {\n0%, 40%, 100% {\ntransform: scaleY(0.4);\n-webkit-transform: scaleY(0.4);\n}\n20% {\ntransform: scaleY(1.0);\n-webkit-transform: scaleY(1.0);\n}\n}\n\n/* 新版登录*/\nhtml {\n  font-size: 24px;\n}\n\nbody, div, ul, li, h3, h4, h5, form, input, button, textarea, p {\n  margin: 0;\n  padding: 0;\n  font-family: 'Lato', sans-serif;\n  font-size: 12px;\n}\n\naddress, em, strong, h3, h4, h5, i {\n  font-style: normal;\n  font-weight: normal;\n}\n\nul, li {\n  list-style: none;\n}\n\na {\n  color: black;\n  text-decoration: none;\n}\n\nimg {\n  border: 0;\n}\n\n.pop-bg {\n  display: none;\n  z-index: 2;\n  position: absolute;\n  top: 0;\n  left: 0;\n  width: 100%;\n  height: 100%;\n  background: rgba(0, 0, 0, 0.4);\n}\n\n.pop-cont {\n  width: 100%;\n  text-align: center;\n  color: black;\n  position: relative;\n}\n\n.cont-title {\n  display: block;\n  text-align: center;\n  margin: 0 0 0.5rem;\n  /*color: #afbfff;*/\n  font-size: .8rem;\n  position: absolute;\n  top: 8px;\n  left: 85px;\n  /*color: #0c1573;*/\n  color: transparent;\n}\n.cont-login .cont-title{\n  width: 110px;\n  height: 19.5px;\n  background-image: url(" + __webpack_require__(202) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-reg-account .cont-title{\n  width: 110px;\n  height: 19.5px;\n  background-image: url(" + __webpack_require__(203) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.cont-reg-phone .cont-title{\n  width: 110px;\n  height: 19.5px;\n  background-image: url(" + __webpack_require__(204) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n.cont-box {\n  position: absolute;\n  display: inline-block;\n  width: 220px;\n  height: 40px;\n  line-height: 40px;\n  text-align: center;\n  margin: 0 0 0.4rem;\n  font-size: .6rem;\n\n  border: none;\n}\n.pop-cont .cont-box:nth-of-type(1){\n  top:42px;\n  left:20px;\n\n}\n.pop-cont .cont-box:nth-of-type(2){\n  top: 85px;\n  left: 20px;\n}\n\n\n\n.history_accounts-icon{\n  left: 210px;\n  top: 5px;\n  width: 30px;\n  height: 30px;\n\n}\n.showpassword-icon{\n  left: 205px;\n  top: 0px;\n}\n\n.box-input {\n  padding-left: 5%;\n  float: left;\n  width: 200px;\n  height: 40px;\n  line-height: 40px;\n  border: none;\n  outline: none;\n  font-size: .6rem;\n  color: #ffffff;\n\n  background-color: transparent;\n\n}\n.box-input::-webkit-input-placeholder{\n  color: #ffffff;\n}\n\n\n.box-input-phone {\n  width: 70%;\n  padding-left: 5%;\n}\n\n.box-code {\n  color: white;\n /* background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n  text-align: center;*/\n   z-index: 100;\n  line-height: 28px;\n  position: absolute;\n  left: 167px;\n  top: 49px;\n  width: 90px;\n\n  height: 28px;\n  border-radius: 14px;\n  background-color: #3a1360;\n  font-size: 12px;\n}\n.cont-reg-account .cont-box .showpassword-icon,.cont-reg-account .cont-box .regpassword-icon {\n  background-repeat: no-repeat;\n  background-size: 0.8rem;\n  display: inline-block;\n  width: 40px;\n  height: 40px;\n  background-position: center center;\n  cursor: pointer;\n  position: absolute;\n  left: 210px;\n  top: 0rem;\n}\n\n.box-input-code {\n  width: 95%;\n  padding-left: 5%;\n}\n\n.cont-btn-two {\n  display: inline-block;\n  width: 80px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  position: absolute;\n\n  top: 116px;\n  left: 10px;\n\n\n}\n\n.cont-btn-two .btn-two {\n  display: block;\n  width: 72px;\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n  color: transparent;\n\n  /*background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;*/\n\n  border-radius: 2px;\n\n\n\n}\n\n.cont-btn-two .btn-two:first-child:active {\n  outline: none;\n  /*background-color: none;*/\n}\n\n.cont-btn-two .btn-two:last-child:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.cont-btn-two .btn-two:first-child {\n  position: absolute;\n  left: 80px;\n  top: 30px;\n  float: none;\n  width: 94px;\n  height: 17px;\n  background-image: url(" + __webpack_require__(205) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n.cont-btn-two .btn-two:last-child {\n /* float: right;*/\n\n  position: absolute;\n\n  left: 58px;\n  top: 70px;\n\n  float: none;\n  width: 153px;\n  height: 28px;\n  background-image: url(" + __webpack_require__(206) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n\n}\n\n.cont-btn-two .btn-one {\n  position: absolute;\n  top:90px;\n  left: 60px;\n\n  display: inline-block;\n  color: transparent;\n\n  width: 153px;\n\n  height: 28px;\n  line-height: 28px;\n  text-align: center;\n  font-size: 0.6rem;\n\n  /*background-image: url(../assets/ios-9/ios9_btn.png);\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;*/\n  border-radius: 2px;\n}\n.btn-one.c-orange.j_submitRegAccount{\n  background-image: url(" + __webpack_require__(91) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n.btn-one.c-orange.j_submitRegPhone{\n  background-image: url(" + __webpack_require__(91) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n}\n\n\n.btn-one:active {\n  outline: none;\n  /*background-color: #FF907A;*/\n}\n\n.c-orange {\n  background:none;\n  /* background: #F27241;\n  background: linear-gradient(#5cddf9, #196372) !important;\n  background: -webkit-linear-gradient(#5cddf9, #196372);\n  background: -o-linear-gradient(#5cddf9, #196372);\n  background: -moz-linear-gradient(#5cddf9, #196372);*/\n}\n\n.c-blue {\n  background: #3F8ED6;\n}\n\n.cont-link-protocol {\n  position: absolute;\n  top:135px;\n  left: 8px;\n  width: 100%;\n  font-size: 0.7rem;\n  text-align: center;\n}\n\n.cont-link-protocol .text-check {\n  float:left;\n  margin-left: 2%;\n  opacity: 0.8;\n  width: 16px;\n  font-size: 0.5rem;\n  height: 16px;\n  margin-top: -0.2rem;\n  background: #506bda;\n}\n\n.cont-link-protocol .text-protocol {\n  float: left;\n\n  margin: -0.2rem 0 0.2rem 0;\n  color: #000000;\n  font-size: 0.5rem;\n  /*width: 220px;*/\n}\n\n.cont-link-protocol .link-protocol {\n  font-size: 0.5rem;\n  /*color: #1d2e73;*/\n  color: #000000;\n}\n\n.cont-link-two {\n  display: inline-block;\n  width: 220px;\n  height: 1.8rem;\n  line-height: 1.8rem;\n  margin-top: 0.4rem;\n  font-size: 0.7rem;\n\n  position: absolute;\n  top: 140px;\n  left: 10px;\n\n}\n\n.cont-link-two .link-two {\n  display: inline-block;\n  width: 70px;\n  height:15px;\n  line-height: 1.8rem;\n  font-size: 0.5rem;\n  color: transparent;\n  border-radius: 2px;\n\n  position: absolute;\n}\n\n.cont-link-two .link-two:first-child {\n  /*text-align: left;*/\n\n  background-image: url(" + __webpack_require__(207) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  float: none;\n\n\n  left: 190px;\n  top: 15px;\n\n\n}\n\n.cont-link-two .link-two:last-child {\n  float:none;\n\n  background-image: url(" + __webpack_require__(208) + ");\n  background-size: contain;\n  background-repeat: no-repeat;\n  background-position: center;\n\n  float: none;\n  left: 10px;\n  top: 15px;\n}\n\n/*充值按钮遮挡*/\n.paymask {\n  position: absolute;\n  top: 0;\n  right: 0;\n  height: 12%;\n  width: 100%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\n.paymask2 {\n  position: absolute;\n  top: 0;\n  left: 0;\n  height: 15%;\n  width: 25%;\n  /*background: red;*/\n  z-index: 2;\n  opacity: 0;\n}\n\ndiv {\n  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n}", ""]);

// exports


/***/ }),
/* 201 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "961b6b1258d85943e08dd5e69dbdba1a.png";

/***/ }),
/* 202 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "c50f6ed8741f4b86e6719f3cf61fbccb.png";

/***/ }),
/* 203 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "af728701221ae00bed87d8dba033a035.png";

/***/ }),
/* 204 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "46434d5ce91ebb129e1e8e357453f49e.png";

/***/ }),
/* 205 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "801b8e0d154777842745e5216727768d.png";

/***/ }),
/* 206 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "58806daf18f8dbed4d0133d5ec12e9c2.png";

/***/ }),
/* 207 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "33a6e75e03b8e720dcbb63c28687ee4d.png";

/***/ }),
/* 208 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "c5652c6c838a914729094ca1c92aa9e6.png";

/***/ }),
/* 209 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, ".floatball {\n    width: 40px;\n    height: 40px;\n    position: absolute;\n    top: 100px;\n    left: 5px;\n    /*border: 10px solid #e7ca8659;*/\n    border-radius: 50px 50px 50px 50px;\n    /*box-shadow: 3px 3px 3px 3px rgba(0, 0, 0, 0.1), 10px 10px 10px rgba(0, 0, 0, 0.3) inset;*/\n    /*-webkit-transform: rotate(-45deg);\n    -moz-transform: rotate(-45deg);\n    -ms-transform: rotate(-45deg);\n    -o-transform: rotate(-45deg);\n    transform: rotate(-45deg);*/\n    background: url(" + __webpack_require__(210) + ") center no-repeat;\n    background-size: 100%;\n    z-index:3;\n}\n\n.unfold {\n    /*width: 129px;*/\n    height: 70px;\n    /*height: 40px;*/\n    position: absolute;\n    /*background: rgba(231,202,134, 0.2);*/\n    top: -10px;\n    left: 40px;\n    text-align:center;\n    line-height: 65px;\n   /* -moz-border-radius: 80px 80px 0;\n    border-radius: 0px 80px 80px 0px;*/\n    z-index:3;\n    background: url(" + __webpack_require__(211) + ") right no-repeat;\n    background-size:130%;\n    color: transparent;\n\n}", ""]);

// exports


/***/ }),
/* 210 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "6f975bd15954e319ff28102bd6829999.png";

/***/ }),
/* 211 */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__.p + "ca0150adfb951765e1139d8f059bedf9.png";

/***/ }),
/* 212 */
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(213);
if(typeof content === 'string') content = [[module.i, content, '']];
// Prepare cssTransformation
var transform;

var options = {"hmr":true}
options.transform = transform
// add the styles to the DOM
var update = __webpack_require__(4)(content, options);
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../../node_modules/css-loader/index.js!./main-supplement.css", function() {
			var newContent = require("!!../../node_modules/css-loader/index.js!./main-supplement.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),
/* 213 */
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(3)(undefined);
// imports


// module
exports.push([module.i, "\n.protocolWrapper {\n    background: #ffffff;\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n    overflow: scroll !important; \n    -webkit-overflow-scrolling: touch !important; \n}\n\n\n#protocol-close {\n    display: none;\n    position: absolute;\n    right: 13% !important;\n    top: 52% !important;\n    margin-top: -10rem !important;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n    z-index: 1100;\n}\n\n#protocolFrame{\n}\n\n.payWrapper {\n    width: 80%;\n    height: 20rem;\n    position: absolute;\n    top: 50%;\n    margin-top: -10rem;\n    left: 10%;\n    z-index: 999;\n    display: none;\n    overflow: scroll !important;\n    -webkit-overflow-scrolling: touch !important;\n}\n\n#btn-close {\n    display: none;\n    position: absolute;\n    right: 13% !important;\n    top: 52% !important;\n    margin-top: -10rem !important;\n    width: 10%;\n    height: 10%;\n    background: url(" + __webpack_require__(2) + ") no-repeat;\n    background-size: contain;\n    z-index: 1100;\n}", ""]);

// exports


/***/ }),
/* 214 */
/***/ (function(module, exports, __webpack_require__) {

/*
* 调用其他渠道的原生app接口，兼容特殊gid：1003461
* 对方ios与js通讯使用的是拦截url scheme的方式
* */
var Util = __webpack_require__(215)
var isFinished = false
window.CTCallBackList = {}
String.prototype.hashCode = function () {
    var hash = 0;
    if (this.length == 0) return hash;
    for (var index = 0; index < this.length; index++) {
        var charactor = this.charCodeAt(index);
        hash = ((hash << 5) - hash) + charactor;
        hash = hash & hash;
    }
    return hash
}

window.Callback = function (identifier, resultStatus, resultData) {
    callBackDict = window.CTCallBackList[identifier];
    if (callBackDict) {
        isFinished = true;
        if (resultStatus == "success") {
            callBackDict.success(resultData)
        }
        if (resultStatus == "fail") {
            callBackDict.fail(resultData)
        }
        if (resultStatus == "progress") {
            isFinished = false;
            callBackDict.progress(resultData)
        }
        if (isFinished) {
            window.CTCallBackList[identifier] = null
            delete window.CTCallBackList[identifier]
        }
    }
}

var callPartnerIos = {
    loadMethod: function (methodName, data, callback) {
        dataString = JSON.stringify(data);
        identifier = (methodName + dataString).hashCode().toString();
        window.CTCallBackList[identifier] = callback;
        url = "pywapp://nativeapi?callbackId=" + identifier + "&data=" + dataString + "&serverName=" + methodName;
        window.location = url;
    },
    callIdfa: function (callback) {
        var ua = navigator.userAgent;
        if (ua == "pyw_ios" || /is_app_ios_2/.test(ua)) {
            // 获取APP提供的参数
            this.loadMethod('getInitDataService', {}, {
                success: function (res) {
                    var idfa = res.imei
                    callback(idfa)
                },
                fail: function (data) {
                    Util.toast('获取设备信息失败', 'error')
                }
            })
        }else{
          callback("")
        }
    }
}
module.exports = callPartnerIos






/***/ }),
/* 215 */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(process) {// Copyright Joyent, Inc. and other Node contributors.
//
// Permission is hereby granted, free of charge, to any person obtaining a
// copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to permit
// persons to whom the Software is furnished to do so, subject to the
// following conditions:
//
// The above copyright notice and this permission notice shall be included
// in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
// USE OR OTHER DEALINGS IN THE SOFTWARE.

var getOwnPropertyDescriptors = Object.getOwnPropertyDescriptors ||
  function getOwnPropertyDescriptors(obj) {
    var keys = Object.keys(obj);
    var descriptors = {};
    for (var i = 0; i < keys.length; i++) {
      descriptors[keys[i]] = Object.getOwnPropertyDescriptor(obj, keys[i]);
    }
    return descriptors;
  };

var formatRegExp = /%[sdj%]/g;
exports.format = function(f) {
  if (!isString(f)) {
    var objects = [];
    for (var i = 0; i < arguments.length; i++) {
      objects.push(inspect(arguments[i]));
    }
    return objects.join(' ');
  }

  var i = 1;
  var args = arguments;
  var len = args.length;
  var str = String(f).replace(formatRegExp, function(x) {
    if (x === '%%') return '%';
    if (i >= len) return x;
    switch (x) {
      case '%s': return String(args[i++]);
      case '%d': return Number(args[i++]);
      case '%j':
        try {
          return JSON.stringify(args[i++]);
        } catch (_) {
          return '[Circular]';
        }
      default:
        return x;
    }
  });
  for (var x = args[i]; i < len; x = args[++i]) {
    if (isNull(x) || !isObject(x)) {
      str += ' ' + x;
    } else {
      str += ' ' + inspect(x);
    }
  }
  return str;
};


// Mark that a method should not be used.
// Returns a modified function which warns once by default.
// If --no-deprecation is set, then it is a no-op.
exports.deprecate = function(fn, msg) {
  if (typeof process !== 'undefined' && process.noDeprecation === true) {
    return fn;
  }

  // Allow for deprecating things in the process of starting up.
  if (typeof process === 'undefined') {
    return function() {
      return exports.deprecate(fn, msg).apply(this, arguments);
    };
  }

  var warned = false;
  function deprecated() {
    if (!warned) {
      if (process.throwDeprecation) {
        throw new Error(msg);
      } else if (process.traceDeprecation) {
        console.trace(msg);
      } else {
        console.error(msg);
      }
      warned = true;
    }
    return fn.apply(this, arguments);
  }

  return deprecated;
};


var debugs = {};
var debugEnviron;
exports.debuglog = function(set) {
  if (isUndefined(debugEnviron))
    debugEnviron = process.env.NODE_DEBUG || '';
  set = set.toUpperCase();
  if (!debugs[set]) {
    if (new RegExp('\\b' + set + '\\b', 'i').test(debugEnviron)) {
      var pid = process.pid;
      debugs[set] = function() {
        var msg = exports.format.apply(exports, arguments);
        console.error('%s %d: %s', set, pid, msg);
      };
    } else {
      debugs[set] = function() {};
    }
  }
  return debugs[set];
};


/**
 * Echos the value of a value. Trys to print the value out
 * in the best way possible given the different types.
 *
 * @param {Object} obj The object to print out.
 * @param {Object} opts Optional options object that alters the output.
 */
/* legacy: obj, showHidden, depth, colors*/
function inspect(obj, opts) {
  // default options
  var ctx = {
    seen: [],
    stylize: stylizeNoColor
  };
  // legacy...
  if (arguments.length >= 3) ctx.depth = arguments[2];
  if (arguments.length >= 4) ctx.colors = arguments[3];
  if (isBoolean(opts)) {
    // legacy...
    ctx.showHidden = opts;
  } else if (opts) {
    // got an "options" object
    exports._extend(ctx, opts);
  }
  // set default options
  if (isUndefined(ctx.showHidden)) ctx.showHidden = false;
  if (isUndefined(ctx.depth)) ctx.depth = 2;
  if (isUndefined(ctx.colors)) ctx.colors = false;
  if (isUndefined(ctx.customInspect)) ctx.customInspect = true;
  if (ctx.colors) ctx.stylize = stylizeWithColor;
  return formatValue(ctx, obj, ctx.depth);
}
exports.inspect = inspect;


// http://en.wikipedia.org/wiki/ANSI_escape_code#graphics
inspect.colors = {
  'bold' : [1, 22],
  'italic' : [3, 23],
  'underline' : [4, 24],
  'inverse' : [7, 27],
  'white' : [37, 39],
  'grey' : [90, 39],
  'black' : [30, 39],
  'blue' : [34, 39],
  'cyan' : [36, 39],
  'green' : [32, 39],
  'magenta' : [35, 39],
  'red' : [31, 39],
  'yellow' : [33, 39]
};

// Don't use 'blue' not visible on cmd.exe
inspect.styles = {
  'special': 'cyan',
  'number': 'yellow',
  'boolean': 'yellow',
  'undefined': 'grey',
  'null': 'bold',
  'string': 'green',
  'date': 'magenta',
  // "name": intentionally not styling
  'regexp': 'red'
};


function stylizeWithColor(str, styleType) {
  var style = inspect.styles[styleType];

  if (style) {
    return '\u001b[' + inspect.colors[style][0] + 'm' + str +
           '\u001b[' + inspect.colors[style][1] + 'm';
  } else {
    return str;
  }
}


function stylizeNoColor(str, styleType) {
  return str;
}


function arrayToHash(array) {
  var hash = {};

  array.forEach(function(val, idx) {
    hash[val] = true;
  });

  return hash;
}


function formatValue(ctx, value, recurseTimes) {
  // Provide a hook for user-specified inspect functions.
  // Check that value is an object with an inspect function on it
  if (ctx.customInspect &&
      value &&
      isFunction(value.inspect) &&
      // Filter out the util module, it's inspect function is special
      value.inspect !== exports.inspect &&
      // Also filter out any prototype objects using the circular check.
      !(value.constructor && value.constructor.prototype === value)) {
    var ret = value.inspect(recurseTimes, ctx);
    if (!isString(ret)) {
      ret = formatValue(ctx, ret, recurseTimes);
    }
    return ret;
  }

  // Primitive types cannot have properties
  var primitive = formatPrimitive(ctx, value);
  if (primitive) {
    return primitive;
  }

  // Look up the keys of the object.
  var keys = Object.keys(value);
  var visibleKeys = arrayToHash(keys);

  if (ctx.showHidden) {
    keys = Object.getOwnPropertyNames(value);
  }

  // IE doesn't make error fields non-enumerable
  // http://msdn.microsoft.com/en-us/library/ie/dww52sbt(v=vs.94).aspx
  if (isError(value)
      && (keys.indexOf('message') >= 0 || keys.indexOf('description') >= 0)) {
    return formatError(value);
  }

  // Some type of object without properties can be shortcutted.
  if (keys.length === 0) {
    if (isFunction(value)) {
      var name = value.name ? ': ' + value.name : '';
      return ctx.stylize('[Function' + name + ']', 'special');
    }
    if (isRegExp(value)) {
      return ctx.stylize(RegExp.prototype.toString.call(value), 'regexp');
    }
    if (isDate(value)) {
      return ctx.stylize(Date.prototype.toString.call(value), 'date');
    }
    if (isError(value)) {
      return formatError(value);
    }
  }

  var base = '', array = false, braces = ['{', '}'];

  // Make Array say that they are Array
  if (isArray(value)) {
    array = true;
    braces = ['[', ']'];
  }

  // Make functions say that they are functions
  if (isFunction(value)) {
    var n = value.name ? ': ' + value.name : '';
    base = ' [Function' + n + ']';
  }

  // Make RegExps say that they are RegExps
  if (isRegExp(value)) {
    base = ' ' + RegExp.prototype.toString.call(value);
  }

  // Make dates with properties first say the date
  if (isDate(value)) {
    base = ' ' + Date.prototype.toUTCString.call(value);
  }

  // Make error with message first say the error
  if (isError(value)) {
    base = ' ' + formatError(value);
  }

  if (keys.length === 0 && (!array || value.length == 0)) {
    return braces[0] + base + braces[1];
  }

  if (recurseTimes < 0) {
    if (isRegExp(value)) {
      return ctx.stylize(RegExp.prototype.toString.call(value), 'regexp');
    } else {
      return ctx.stylize('[Object]', 'special');
    }
  }

  ctx.seen.push(value);

  var output;
  if (array) {
    output = formatArray(ctx, value, recurseTimes, visibleKeys, keys);
  } else {
    output = keys.map(function(key) {
      return formatProperty(ctx, value, recurseTimes, visibleKeys, key, array);
    });
  }

  ctx.seen.pop();

  return reduceToSingleString(output, base, braces);
}


function formatPrimitive(ctx, value) {
  if (isUndefined(value))
    return ctx.stylize('undefined', 'undefined');
  if (isString(value)) {
    var simple = '\'' + JSON.stringify(value).replace(/^"|"$/g, '')
                                             .replace(/'/g, "\\'")
                                             .replace(/\\"/g, '"') + '\'';
    return ctx.stylize(simple, 'string');
  }
  if (isNumber(value))
    return ctx.stylize('' + value, 'number');
  if (isBoolean(value))
    return ctx.stylize('' + value, 'boolean');
  // For some reason typeof null is "object", so special case here.
  if (isNull(value))
    return ctx.stylize('null', 'null');
}


function formatError(value) {
  return '[' + Error.prototype.toString.call(value) + ']';
}


function formatArray(ctx, value, recurseTimes, visibleKeys, keys) {
  var output = [];
  for (var i = 0, l = value.length; i < l; ++i) {
    if (hasOwnProperty(value, String(i))) {
      output.push(formatProperty(ctx, value, recurseTimes, visibleKeys,
          String(i), true));
    } else {
      output.push('');
    }
  }
  keys.forEach(function(key) {
    if (!key.match(/^\d+$/)) {
      output.push(formatProperty(ctx, value, recurseTimes, visibleKeys,
          key, true));
    }
  });
  return output;
}


function formatProperty(ctx, value, recurseTimes, visibleKeys, key, array) {
  var name, str, desc;
  desc = Object.getOwnPropertyDescriptor(value, key) || { value: value[key] };
  if (desc.get) {
    if (desc.set) {
      str = ctx.stylize('[Getter/Setter]', 'special');
    } else {
      str = ctx.stylize('[Getter]', 'special');
    }
  } else {
    if (desc.set) {
      str = ctx.stylize('[Setter]', 'special');
    }
  }
  if (!hasOwnProperty(visibleKeys, key)) {
    name = '[' + key + ']';
  }
  if (!str) {
    if (ctx.seen.indexOf(desc.value) < 0) {
      if (isNull(recurseTimes)) {
        str = formatValue(ctx, desc.value, null);
      } else {
        str = formatValue(ctx, desc.value, recurseTimes - 1);
      }
      if (str.indexOf('\n') > -1) {
        if (array) {
          str = str.split('\n').map(function(line) {
            return '  ' + line;
          }).join('\n').substr(2);
        } else {
          str = '\n' + str.split('\n').map(function(line) {
            return '   ' + line;
          }).join('\n');
        }
      }
    } else {
      str = ctx.stylize('[Circular]', 'special');
    }
  }
  if (isUndefined(name)) {
    if (array && key.match(/^\d+$/)) {
      return str;
    }
    name = JSON.stringify('' + key);
    if (name.match(/^"([a-zA-Z_][a-zA-Z_0-9]*)"$/)) {
      name = name.substr(1, name.length - 2);
      name = ctx.stylize(name, 'name');
    } else {
      name = name.replace(/'/g, "\\'")
                 .replace(/\\"/g, '"')
                 .replace(/(^"|"$)/g, "'");
      name = ctx.stylize(name, 'string');
    }
  }

  return name + ': ' + str;
}


function reduceToSingleString(output, base, braces) {
  var numLinesEst = 0;
  var length = output.reduce(function(prev, cur) {
    numLinesEst++;
    if (cur.indexOf('\n') >= 0) numLinesEst++;
    return prev + cur.replace(/\u001b\[\d\d?m/g, '').length + 1;
  }, 0);

  if (length > 60) {
    return braces[0] +
           (base === '' ? '' : base + '\n ') +
           ' ' +
           output.join(',\n  ') +
           ' ' +
           braces[1];
  }

  return braces[0] + base + ' ' + output.join(', ') + ' ' + braces[1];
}


// NOTE: These type checking functions intentionally don't use `instanceof`
// because it is fragile and can be easily faked with `Object.create()`.
function isArray(ar) {
  return Array.isArray(ar);
}
exports.isArray = isArray;

function isBoolean(arg) {
  return typeof arg === 'boolean';
}
exports.isBoolean = isBoolean;

function isNull(arg) {
  return arg === null;
}
exports.isNull = isNull;

function isNullOrUndefined(arg) {
  return arg == null;
}
exports.isNullOrUndefined = isNullOrUndefined;

function isNumber(arg) {
  return typeof arg === 'number';
}
exports.isNumber = isNumber;

function isString(arg) {
  return typeof arg === 'string';
}
exports.isString = isString;

function isSymbol(arg) {
  return typeof arg === 'symbol';
}
exports.isSymbol = isSymbol;

function isUndefined(arg) {
  return arg === void 0;
}
exports.isUndefined = isUndefined;

function isRegExp(re) {
  return isObject(re) && objectToString(re) === '[object RegExp]';
}
exports.isRegExp = isRegExp;

function isObject(arg) {
  return typeof arg === 'object' && arg !== null;
}
exports.isObject = isObject;

function isDate(d) {
  return isObject(d) && objectToString(d) === '[object Date]';
}
exports.isDate = isDate;

function isError(e) {
  return isObject(e) &&
      (objectToString(e) === '[object Error]' || e instanceof Error);
}
exports.isError = isError;

function isFunction(arg) {
  return typeof arg === 'function';
}
exports.isFunction = isFunction;

function isPrimitive(arg) {
  return arg === null ||
         typeof arg === 'boolean' ||
         typeof arg === 'number' ||
         typeof arg === 'string' ||
         typeof arg === 'symbol' ||  // ES6 symbol
         typeof arg === 'undefined';
}
exports.isPrimitive = isPrimitive;

exports.isBuffer = __webpack_require__(217);

function objectToString(o) {
  return Object.prototype.toString.call(o);
}


function pad(n) {
  return n < 10 ? '0' + n.toString(10) : n.toString(10);
}


var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep',
              'Oct', 'Nov', 'Dec'];

// 26 Feb 16:19:34
function timestamp() {
  var d = new Date();
  var time = [pad(d.getHours()),
              pad(d.getMinutes()),
              pad(d.getSeconds())].join(':');
  return [d.getDate(), months[d.getMonth()], time].join(' ');
}


// log is just a thin wrapper to console.log that prepends a timestamp
exports.log = function() {
  console.log('%s - %s', timestamp(), exports.format.apply(exports, arguments));
};


/**
 * Inherit the prototype methods from one constructor into another.
 *
 * The Function.prototype.inherits from lang.js rewritten as a standalone
 * function (not on Function.prototype). NOTE: If this file is to be loaded
 * during bootstrapping this function needs to be rewritten using some native
 * functions as prototype setup using normal JavaScript does not work as
 * expected during bootstrapping (see mirror.js in r114903).
 *
 * @param {function} ctor Constructor function which needs to inherit the
 *     prototype.
 * @param {function} superCtor Constructor function to inherit prototype from.
 */
exports.inherits = __webpack_require__(218);

exports._extend = function(origin, add) {
  // Don't do anything if add isn't an object
  if (!add || !isObject(add)) return origin;

  var keys = Object.keys(add);
  var i = keys.length;
  while (i--) {
    origin[keys[i]] = add[keys[i]];
  }
  return origin;
};

function hasOwnProperty(obj, prop) {
  return Object.prototype.hasOwnProperty.call(obj, prop);
}

var kCustomPromisifiedSymbol = typeof Symbol !== 'undefined' ? Symbol('util.promisify.custom') : undefined;

exports.promisify = function promisify(original) {
  if (typeof original !== 'function')
    throw new TypeError('The "original" argument must be of type Function');

  if (kCustomPromisifiedSymbol && original[kCustomPromisifiedSymbol]) {
    var fn = original[kCustomPromisifiedSymbol];
    if (typeof fn !== 'function') {
      throw new TypeError('The "util.promisify.custom" argument must be of type Function');
    }
    Object.defineProperty(fn, kCustomPromisifiedSymbol, {
      value: fn, enumerable: false, writable: false, configurable: true
    });
    return fn;
  }

  function fn() {
    var promiseResolve, promiseReject;
    var promise = new Promise(function (resolve, reject) {
      promiseResolve = resolve;
      promiseReject = reject;
    });

    var args = [];
    for (var i = 0; i < arguments.length; i++) {
      args.push(arguments[i]);
    }
    args.push(function (err, value) {
      if (err) {
        promiseReject(err);
      } else {
        promiseResolve(value);
      }
    });

    try {
      original.apply(this, args);
    } catch (err) {
      promiseReject(err);
    }

    return promise;
  }

  Object.setPrototypeOf(fn, Object.getPrototypeOf(original));

  if (kCustomPromisifiedSymbol) Object.defineProperty(fn, kCustomPromisifiedSymbol, {
    value: fn, enumerable: false, writable: false, configurable: true
  });
  return Object.defineProperties(
    fn,
    getOwnPropertyDescriptors(original)
  );
}

exports.promisify.custom = kCustomPromisifiedSymbol

function callbackifyOnRejected(reason, cb) {
  // `!reason` guard inspired by bluebird (Ref: https://goo.gl/t5IS6M).
  // Because `null` is a special error value in callbacks which means "no error
  // occurred", we error-wrap so the callback consumer can distinguish between
  // "the promise rejected with null" or "the promise fulfilled with undefined".
  if (!reason) {
    var newReason = new Error('Promise was rejected with a falsy value');
    newReason.reason = reason;
    reason = newReason;
  }
  return cb(reason);
}

function callbackify(original) {
  if (typeof original !== 'function') {
    throw new TypeError('The "original" argument must be of type Function');
  }

  // We DO NOT return the promise as it gives the user a false sense that
  // the promise is actually somehow related to the callback's execution
  // and that the callback throwing will reject the promise.
  function callbackified() {
    var args = [];
    for (var i = 0; i < arguments.length; i++) {
      args.push(arguments[i]);
    }

    var maybeCb = args.pop();
    if (typeof maybeCb !== 'function') {
      throw new TypeError('The last argument must be of type Function');
    }
    var self = this;
    var cb = function() {
      return maybeCb.apply(self, arguments);
    };
    // In true node style we process the callback on `nextTick` with all the
    // implications (stack, `uncaughtException`, `async_hooks`)
    original.apply(this, args)
      .then(function(ret) { process.nextTick(cb, null, ret) },
            function(rej) { process.nextTick(callbackifyOnRejected, rej, cb) });
  }

  Object.setPrototypeOf(callbackified, Object.getPrototypeOf(original));
  Object.defineProperties(callbackified,
                          getOwnPropertyDescriptors(original));
  return callbackified;
}
exports.callbackify = callbackify;

/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(216)))

/***/ }),
/* 216 */
/***/ (function(module, exports) {

// shim for using process in browser
var process = module.exports = {};

// cached from whatever global is present so that test runners that stub it
// don't break things.  But we need to wrap it in a try catch in case it is
// wrapped in strict mode code which doesn't define any globals.  It's inside a
// function because try/catches deoptimize in certain engines.

var cachedSetTimeout;
var cachedClearTimeout;

function defaultSetTimout() {
    throw new Error('setTimeout has not been defined');
}
function defaultClearTimeout () {
    throw new Error('clearTimeout has not been defined');
}
(function () {
    try {
        if (typeof setTimeout === 'function') {
            cachedSetTimeout = setTimeout;
        } else {
            cachedSetTimeout = defaultSetTimout;
        }
    } catch (e) {
        cachedSetTimeout = defaultSetTimout;
    }
    try {
        if (typeof clearTimeout === 'function') {
            cachedClearTimeout = clearTimeout;
        } else {
            cachedClearTimeout = defaultClearTimeout;
        }
    } catch (e) {
        cachedClearTimeout = defaultClearTimeout;
    }
} ())
function runTimeout(fun) {
    if (cachedSetTimeout === setTimeout) {
        //normal enviroments in sane situations
        return setTimeout(fun, 0);
    }
    // if setTimeout wasn't available but was latter defined
    if ((cachedSetTimeout === defaultSetTimout || !cachedSetTimeout) && setTimeout) {
        cachedSetTimeout = setTimeout;
        return setTimeout(fun, 0);
    }
    try {
        // when when somebody has screwed with setTimeout but no I.E. maddness
        return cachedSetTimeout(fun, 0);
    } catch(e){
        try {
            // When we are in I.E. but the script has been evaled so I.E. doesn't trust the global object when called normally
            return cachedSetTimeout.call(null, fun, 0);
        } catch(e){
            // same as above but when it's a version of I.E. that must have the global object for 'this', hopfully our context correct otherwise it will throw a global error
            return cachedSetTimeout.call(this, fun, 0);
        }
    }


}
function runClearTimeout(marker) {
    if (cachedClearTimeout === clearTimeout) {
        //normal enviroments in sane situations
        return clearTimeout(marker);
    }
    // if clearTimeout wasn't available but was latter defined
    if ((cachedClearTimeout === defaultClearTimeout || !cachedClearTimeout) && clearTimeout) {
        cachedClearTimeout = clearTimeout;
        return clearTimeout(marker);
    }
    try {
        // when when somebody has screwed with setTimeout but no I.E. maddness
        return cachedClearTimeout(marker);
    } catch (e){
        try {
            // When we are in I.E. but the script has been evaled so I.E. doesn't  trust the global object when called normally
            return cachedClearTimeout.call(null, marker);
        } catch (e){
            // same as above but when it's a version of I.E. that must have the global object for 'this', hopfully our context correct otherwise it will throw a global error.
            // Some versions of I.E. have different rules for clearTimeout vs setTimeout
            return cachedClearTimeout.call(this, marker);
        }
    }



}
var queue = [];
var draining = false;
var currentQueue;
var queueIndex = -1;

function cleanUpNextTick() {
    if (!draining || !currentQueue) {
        return;
    }
    draining = false;
    if (currentQueue.length) {
        queue = currentQueue.concat(queue);
    } else {
        queueIndex = -1;
    }
    if (queue.length) {
        drainQueue();
    }
}

function drainQueue() {
    if (draining) {
        return;
    }
    var timeout = runTimeout(cleanUpNextTick);
    draining = true;

    var len = queue.length;
    while(len) {
        currentQueue = queue;
        queue = [];
        while (++queueIndex < len) {
            if (currentQueue) {
                currentQueue[queueIndex].run();
            }
        }
        queueIndex = -1;
        len = queue.length;
    }
    currentQueue = null;
    draining = false;
    runClearTimeout(timeout);
}

process.nextTick = function (fun) {
    var args = new Array(arguments.length - 1);
    if (arguments.length > 1) {
        for (var i = 1; i < arguments.length; i++) {
            args[i - 1] = arguments[i];
        }
    }
    queue.push(new Item(fun, args));
    if (queue.length === 1 && !draining) {
        runTimeout(drainQueue);
    }
};

// v8 likes predictible objects
function Item(fun, array) {
    this.fun = fun;
    this.array = array;
}
Item.prototype.run = function () {
    this.fun.apply(null, this.array);
};
process.title = 'browser';
process.browser = true;
process.env = {};
process.argv = [];
process.version = ''; // empty string to avoid regexp issues
process.versions = {};

function noop() {}

process.on = noop;
process.addListener = noop;
process.once = noop;
process.off = noop;
process.removeListener = noop;
process.removeAllListeners = noop;
process.emit = noop;
process.prependListener = noop;
process.prependOnceListener = noop;

process.listeners = function (name) { return [] }

process.binding = function (name) {
    throw new Error('process.binding is not supported');
};

process.cwd = function () { return '/' };
process.chdir = function (dir) {
    throw new Error('process.chdir is not supported');
};
process.umask = function() { return 0; };


/***/ }),
/* 217 */
/***/ (function(module, exports) {

module.exports = function isBuffer(arg) {
  return arg && typeof arg === 'object'
    && typeof arg.copy === 'function'
    && typeof arg.fill === 'function'
    && typeof arg.readUInt8 === 'function';
}

/***/ }),
/* 218 */
/***/ (function(module, exports) {

if (typeof Object.create === 'function') {
  // implementation from standard node.js 'util' module
  module.exports = function inherits(ctor, superCtor) {
    ctor.super_ = superCtor
    ctor.prototype = Object.create(superCtor.prototype, {
      constructor: {
        value: ctor,
        enumerable: false,
        writable: true,
        configurable: true
      }
    });
  };
} else {
  // old school shim for old browsers
  module.exports = function inherits(ctor, superCtor) {
    ctor.super_ = superCtor
    var TempCtor = function () {}
    TempCtor.prototype = superCtor.prototype
    ctor.prototype = new TempCtor()
    ctor.prototype.constructor = ctor
  }
}


/***/ })
/******/ ]);