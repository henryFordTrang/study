(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d3cd382"],{b37c:function(e,t,o){"use strict";o.r(t);var n=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"wrapper"},[n("div",{staticClass:"board"},[n("ul",{staticClass:"board__ul"},e._l(e.receiveInfo,(function(t,c){return n("li",{key:c,class:t.id!=e.user?"board__li":"board__limi"},[n("img",{directives:[{name:"show",rawName:"v-show",value:t.id!=e.user,expression:"item.id != user"}],staticClass:"board__li__icon",attrs:{src:o("c121"),alt:""}}),n("div",{class:t.id!=e.user?"board__li__message":"board__limi__messagemi"},[e._v(" "+e._s(t.message)+" ")]),n("img",{directives:[{name:"show",rawName:"v-show",value:t.id==e.user,expression:"item.id == user"}],staticClass:"board__limi__iconmi",attrs:{src:o("c121"),alt:""}})])})),0)]),n("van-field",{staticClass:"chart__border",attrs:{placeholder:"请输入用户名"},model:{value:e.chat,callback:function(t){e.chat=t},expression:"chat"}},[n("van-button",{attrs:{slot:"button",type:"primary",size:"small"},on:{click:e.publish},slot:"button"},[e._v("publish")])],1)],1)},c=[],s=o("2f62"),i={computed:Object(s["b"])({location:"generator/location"}),data:function(){return{address:"",client:"",chat:"",user:"",wsurl:"ws://49.233.85.23:8080/study/websocket/sniper/",lockReconnect:!1,heartCheck:"",receiveInfo:[],usersList:[]}},methods:{websocketCheck:function(){var e=this;"WebSocket"in window?(console.log("您的浏览器支持 WebSocket!"),e.client&&(e.client.close(),e.client=""),e.user=1e4*Math.random(),e.user=Math.round(e.user),e.client=new WebSocket("ws://49.233.85.23:8080/study/websocket/sniper/"+e.user),e.client.onopen=function(){console.log(e.client,"websocket连接上了")},e.client.onmessage=function(t){console.log("-------------数据已接收...",t),console.log(t.data),e.receiveInfo.push(JSON.parse(t.data))},e.client.onclose=function(){e.reconnect(),console.log("连接已关闭...")},e.client.onerror=function(){e.reconnect()}):alert("您的浏览器不支持 WebSocket!")},reconnect:function(){var e=this;e.lockReconnect||(e.lockReconnect=!0,setTimeout((function(){console.info("尝试重连..."),e.websocketCheck(),e.lockReconnect=!1}),1e3))},heartBeat:function(){var e=this;e.heartCheck={timeout:5e3,timeoutObj:null,serverTimeoutObj:null,reset:function(){return clearTimeout(this.timeoutObj),clearTimeout(this.serverTimeoutObj),this},start:function(){var t=this;this.timeoutObj=setTimeout((function(){e.client.send("HeartBeat"+(new Date).format("yyyy-MM-dd hh:mm:ss")),console.info("客户端发送心跳："+(new Date).format("yyyy-MM-dd hh:mm:ss")),t.serverTimeoutObj=setTimeout((function(){e.client.close()}),t.timeout)}),this.timeout)}}},publish:function(){var e=this;console.log("数据发送中..."),e.client.send(e.chat),e.chat=""},closeWs:function(){var e=this;console.log("关闭websocket..."),e.client.close()},onClickLeft:function(){var e=this;console.log(e,"8888888"),e.client.close(),window.$utils.exit()},onClickRight:function(){var e=this;e.$toast(99)}},created:function(){console.log("creating");var e=this;e.client&&(e.client.close(),e.client="")},mounted:function(){var e=this;e.websocketCheck(),document.addEventListener("keyup",(function(t){"Enter"==t.key&&e.publish()}))},destroyed:function(){var e=this;console.log("destroyed.........."),e.client.close(),e.client=""}},l=i,r=(o("cfce"),o("2877")),a=Object(r["a"])(l,n,c,!1,null,"75f16a88",null);t["default"]=a.exports},b979:function(e,t,o){},c121:function(e,t,o){e.exports=o.p+"img/user.91d28fda.png"},cfce:function(e,t,o){"use strict";var n=o("b979"),c=o.n(n);c.a}}]);