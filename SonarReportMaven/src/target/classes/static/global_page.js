!function(n){var r={};function o(e){if(r[e])return r[e].exports;var t=r[e]={i:e,l:!1,exports:{}};return n[e].call(t.exports,t,t.exports,o),t.l=!0,t.exports}o.m=n,o.c=r,o.d=function(e,t,n){o.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},o.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},o.t=function(t,e){if(1&e&&(t=o(t)),8&e)return t;if(4&e&&"object"==typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(o.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var r in t)o.d(n,r,function(e){return t[e]}.bind(null,r));return n},o.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return o.d(t,"a",t),t},o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},o.p="",o(o.s=2)}([function(e,t){e.exports=React},function(e,t){e.exports=SonarComponents},function(e,t,n){e.exports=n(7)},function(e,t,n){var r=n(4);"string"==typeof r&&(r=[[e.i,r,""]]);n(6)(r,{});r.locals&&(e.exports=r.locals)},function(e,t,n){(e.exports=n(5)()).push([e.i,".forminput select, .forminput input{\r\n    width:100%;\r\n}\r\n\r\n.login-label{\r\n    display: block;\r\n}\r\n\r\n.info-message{\r\n    color:grey;\r\n}",""])},function(e,t){e.exports=function(){var a=[];return a.toString=function(){for(var e=[],t=0;t<this.length;t++){var n=this[t];n[2]?e.push("@media "+n[2]+"{"+n[1]+"}"):e.push(n[1])}return e.join("")},a.i=function(e,t){"string"==typeof e&&(e=[[null,e,""]]);for(var n={},r=0;r<this.length;r++){var o=this[r][0];"number"==typeof o&&(n[o]=!0)}for(r=0;r<e.length;r++){var i=e[r];"number"==typeof i[0]&&n[i[0]]||(t&&!i[2]?i[2]=t:t&&(i[2]="("+i[2]+") and ("+t+")"),a.push(i))}},a}},function(e,t){function n(e){var t;return function(){return void 0===t&&(t=e.apply(this,arguments)),t}}var c={},r=n(function(){return/msie [6-9]\b/.test(window.navigator.userAgent.toLowerCase())}),o=n(function(){return document.head||document.getElementsByTagName("head")[0]}),l=null,s=0,i=[];function f(e,t){for(var n=0;n<e.length;n++){var r=e[n],o=c[r.id];if(o){o.refs++;for(var i=0;i<o.parts.length;i++)o.parts[i](r.parts[i]);for(;i<r.parts.length;i++)o.parts.push(u(r.parts[i],t))}else{for(var a=[],i=0;i<r.parts.length;i++)a.push(u(r.parts[i],t));c[r.id]={id:r.id,refs:1,parts:a}}}}function p(e){for(var t=[],n={},r=0;r<e.length;r++){var o=e[r],i=o[0],a={css:o[1],media:o[2],sourceMap:o[3]};n[i]?n[i].parts.push(a):t.push(n[i]={id:i,parts:[a]})}return t}function d(e,t){var n=o(),r=i[i.length-1];if("top"===e.insertAt)r?r.nextSibling?n.insertBefore(t,r.nextSibling):n.appendChild(t):n.insertBefore(t,n.firstChild),i.push(t);else{if("bottom"!==e.insertAt)throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");n.appendChild(t)}}function y(e){e.parentNode.removeChild(e);var t=i.indexOf(e);0<=t&&i.splice(t,1)}function b(e){var t=document.createElement("style");return t.type="text/css",d(e,t),t}function u(t,e){var n,r,o,i,a,u;return i=e.singleton?(n=s++,r=l=l||b(e),o=h.bind(null,r,n,!1),h.bind(null,r,n,!0)):t.sourceMap&&"function"==typeof URL&&"function"==typeof URL.createObjectURL&&"function"==typeof URL.revokeObjectURL&&"function"==typeof Blob&&"function"==typeof btoa?(a=e,(u=document.createElement("link")).rel="stylesheet",d(a,u),o=function(e,t){var n=t.css,r=(t.media,t.sourceMap);r&&(n+="\n/*# sourceMappingURL=data:application/json;base64,"+btoa(unescape(encodeURIComponent(JSON.stringify(r))))+" */");var o=new Blob([n],{type:"text/css"}),i=e.href;e.href=URL.createObjectURL(o),i&&URL.revokeObjectURL(i)}.bind(null,r=u),function(){y(r),r.href&&URL.revokeObjectURL(r.href)}):(r=b(e),o=function(e,t){var n=t.css,r=t.media;t.sourceMap;r&&e.setAttribute("media",r);if(e.styleSheet)e.styleSheet.cssText=n;else{for(;e.firstChild;)e.removeChild(e.firstChild);e.appendChild(document.createTextNode(n))}}.bind(null,r),function(){y(r)}),o(t),function(e){if(e){if(e.css===t.css&&e.media===t.media&&e.sourceMap===t.sourceMap)return;o(t=e)}else i()}}e.exports=function(e,a){if("undefined"!=typeof DEBUG&&DEBUG&&"object"!=typeof document)throw new Error("The style-loader cannot be used in a non-browser environment");void 0===(a=a||{}).singleton&&(a.singleton=r()),void 0===a.insertAt&&(a.insertAt="bottom");var u=p(e);return f(u,a),function(e){for(var t=[],n=0;n<u.length;n++){var r=u[n];(o=c[r.id]).refs--,t.push(o)}e&&f(p(e),a);for(var o,n=0;n<t.length;n++){if(0===(o=t[n]).refs){for(var i=0;i<o.parts.length;i++)o.parts[i]();delete c[o.id]}}}};var a,m=(a=[],function(e,t){return a[e]=t,a.filter(Boolean).join("\n")});function h(e,t,n,r){var o,i,a=n?"":r.css;e.styleSheet?e.styleSheet.cssText=m(t,a):(o=document.createTextNode(a),(i=e.childNodes)[t]&&e.removeChild(i[t]),i.length?e.insertBefore(o,i[t]):e.appendChild(o))}},function(e,t,n){"use strict";n.r(t);var r=n(0),o=n.n(r),i=(n(3),n(1));function u(e){return(u="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function a(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}function l(e,t){return(l=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}function s(i){var a=function(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],function(){})),!0}catch(e){return!1}}();return function(){var e,t,n,r,o=c(i);return t=a?(e=c(this).constructor,Reflect.construct(o,arguments,e)):o.apply(this,arguments),n=this,!(r=t)||"object"!==u(r)&&"function"!=typeof r?f(n):r}}function f(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}function c(e){return(c=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}var p=function(){!function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&l(e,t)}(c,o.a.PureComponent);var e,t,n,u=s(c);function c(){var e;!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,c);for(var t,n,r,o=arguments.length,i=new Array(o),a=0;a<o;a++)i[a]=arguments[a];return e=u.call.apply(u,[this].concat(i)),t=f(e),r={loading:!0,data:[]},(n="state")in t?Object.defineProperty(t,n,{value:r,enumerable:!0,configurable:!0,writable:!0}):t[n]=r,e}return e=c,(t=[{key:"componentDidMount",value:function(){var t=this;loadPage(this.props.project).then(function(e){t.setState({loading:!1})})}},{key:"render",value:function(){return this.state.loading?o.a.createElement("div",{className:"page page-limited"},o.a.createElement(i.DeferredSpinner,null)):o.a.createElement("div",{class:"page-wrapper-simple"},o.a.createElement("div",{class:"page-simple"},o.a.createElement("h1",{class:"maintenance-title text-center"},"Generate a report"),o.a.createElement("form",{id:"generation-form"},o.a.createElement("div",{class:"forminput"},o.a.createElement("label",{for:"key",id:"keyLabel",class:"login-label"},o.a.createElement("strong",null,"Project key"))),o.a.createElement("br",null),o.a.createElement("input",{id:"generation",name:"generation",type:"submit",value:"Generate"}),o.a.createElement("br",null))))}}])&&a(e.prototype,t),n&&a(e,n),c}();window.registerExtension("reportindaba/global_page",function(){return o.a.createElement(p,null)})}]);