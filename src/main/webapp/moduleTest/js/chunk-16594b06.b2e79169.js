(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-16594b06"],{"4c11":function(e,s,t){"use strict";t.r(s);var o=function(){var e=this,s=e.$createElement,o=e._self._c||s;return o("div",{staticClass:"wrapper"},[o("div",{staticClass:"board"},[o("ul",{staticClass:"board__ul"},e._l(e.receiveInfo,(function(s,i){return o("li",{key:i,class:s.id!=e.user?"board__li":"board__limi"},[o("img",{directives:[{name:"show",rawName:"v-show",value:s.id!=e.user,expression:"item.id!=user"}],staticClass:"board__li__icon",attrs:{src:t("c121"),alt:""}}),o("div",{class:s.id!=e.user?"board__li__message":"board__limi__messagemi"},[e._v(e._s(s.message))]),o("img",{directives:[{name:"show",rawName:"v-show",value:s.id==e.user,expression:"item.id==user"}],staticClass:"board__limi__iconmi",attrs:{src:t("c121"),alt:""}})])})),0)]),o("van-field",{staticClass:"chart__border",attrs:{placeholder:"请输入用户名"},model:{value:e.chat,callback:function(s){e.chat=s},expression:"chat"}},[o("van-button",{attrs:{slot:"button",type:"primary",size:"small"},on:{click:e.publish},slot:"button"},[e._v("publish")])],1)],1)},i=[],n=t("2f62"),c={computed:Object(n["b"])({location:"generator/location"}),data:function(){return{address:"",client:"",chat:"",user:"",receiveInfo:[],usersList:[]}},methods:{websocketCheck:function(){var e=this;"WebSocket"in window?(console.log("您的浏览器支持 WebSocket!"),e.user=1e4*Math.random(),e.user=Math.round(e.user),e.client=new WebSocket("ws://"+window.location.host+"/study/websocket/sniper/"+e.user),e.client.onopen=function(){console.log("连接websocket...")},e.client.onmessage=function(s){console.log("-------------数据已接收...",s),console.log(s.data),e.receiveInfo.push(JSON.parse(s.data))},e.client.onclose=function(){console.log("连接已关闭...")}):alert("您的浏览器不支持 WebSocket!")},publish:function(){var e=this;console.log("数据发送中..."),e.client.send(e.chat),e.chat=""},closeWs:function(){var e=this;console.log("关闭websocket..."),e.client.close()},onClickLeft:function(){var e=this;console.log(e,"8888888"),window.$utils.exit()},onClickRight:function(){var e=this;e.$toast(99)}},mounted:function(){var e=this;console.log(e.serializeToUrl("woshinima"),"----",window),e.websocketCheck()}},a=c,l=(t("7860"),t("2877")),r=Object(l["a"])(a,o,i,!1,null,"1dad030a",null);s["default"]=r.exports},7860:function(e,s,t){"use strict";var o=t("e468"),i=t.n(o);i.a},c121:function(e,s,t){e.exports=t.p+"img/user.91d28fda.png"},e468:function(e,s,t){}}]);