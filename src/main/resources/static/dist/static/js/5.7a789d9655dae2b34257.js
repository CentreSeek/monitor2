webpackJsonp([5],{"4dfQ":function(t,e){},Kv3u:function(t,e,a){t.exports=a.p+"static/img/router.5f3b39a.png"},MPiK:function(t,e){},aj52:function(t,e,a){t.exports=a.p+"static/img/router1.988b1c1.png"},mrOq:function(t,e){},oRZv:function(t,e){},r5g0:function(t,e){},wJM7:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("mvHQ"),r=a.n(s),n=(a("NYxO"),a("LSqF")),o=a("3pLw");function i(t){return Object(o.a)({url:"/hospital/rooms",method:"get",params:t})}var l=a("5XFP"),c={},u={name:"qylyq",data:function(){return{qyRepeater:{ip:c.currentRepeater.ip,departmentId:c.currentRepeater.departmentId,roomId:c.currentRepeater.roomId},roomList:[],language:c.$store.state.login.language,departmentList:[]}},props:{qylyqStatus:{type:Boolean},getRepeaterListFn:{type:Function,default:null},currentRepeater:{type:Object}},beforeCreate:function(){c=this},mounted:function(){c.getRoomsListFn();Object(l.e)({flag:!0}).then(function(t){200==t.code&&t.success&&(c.departmentList=t.data)})},methods:{getRoomsListFn:function(){i({departmentId:c.qyRepeater.departmentId}).then(function(t){200==t.code&&t.success&&(c.roomList=t.data,0!=c.roomList.length?c.qyRepeater.roomId=c.roomList[0].roomId:c.qyRepeater.roomId="")})},selectDepartment:function(t){c.qyRepeater.departmentId=t,c.getRoomsListFn()},selectRoom:function(t){c.qyRepeater.roomId=t},openRepeater:function(){if(""==c.qyRepeater.ip)c.error(c.$t("router.err.ipnull"));else if(""==c.qyRepeater.departmentId)c.error(c.$t("router.err.ksnull"));else if(""==c.qyRepeater.roomId)c.error(c.$t("router.err.fjnull"));else{(function(t){return Object(o.a)({url:"/repeater/start",method:"put",data:t})})({ip:c.qyRepeater.ip,departmentId:c.qyRepeater.departmentId,roomId:c.qyRepeater.roomId,id:c.currentRepeater.id}).then(function(t){200==t.code&&t.success&&(c.success(c.$t("message.OperSuccess")),c.$emit("getqylyqStatus",!c.qylyqStatus),c.getRepeaterListFn())})}},closeQylyq:function(){c.$emit("getqylyqStatus",!c.qylyqStatus)}},components:{PopUp:n.a}},p={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("PopUp",{attrs:{flow:t.qylyqStatus}},[s("div",{staticClass:"box"},[s("div",{staticClass:"container"},[s("i",{staticClass:"iconfont close",on:{click:t.closeQylyq}},[t._v("")]),t._v(" "),s("div",{staticClass:"title clearfix"},[s("i",{staticClass:"iconfont title-img"},[t._v("")]),t._v(" "),s("span",{staticClass:"title-span"},[t._v(t._s(t.$t("router.qylyq")))])]),t._v(" "),s("div",{staticClass:"content"},[s("div",{staticClass:"form"},[s("div",{staticClass:"sb-form form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.sbmc"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("span",[t._v(t._s(t.currentRepeater.repeaterName))])])]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.sbxh"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("span",[t._v(t._s(t.currentRepeater.repeaterNum))])])]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.mac"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("span",[t._v(t._s(t.currentRepeater.mac))])])]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.ip"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-input",{staticClass:"select",attrs:{placeholder:"ip"},model:{value:t.qyRepeater.ip,callback:function(e){t.$set(t.qyRepeater,"ip",e)},expression:"qyRepeater.ip"}})],1)]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.syks"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-select",{staticClass:"select",staticStyle:{width:"133px",float:"left"},attrs:{filterable:"",placeholder:t.$t("select")},on:{change:t.selectDepartment},model:{value:t.qyRepeater.departmentId,callback:function(e){t.$set(t.qyRepeater,"departmentId",e)},expression:"qyRepeater.departmentId"}},t._l(t.departmentList,function(t){return s("el-option",{key:t.departmentId,attrs:{label:t.name,value:t.departmentId}})}),1),t._v(" "),s("el-select",{staticClass:"select",staticStyle:{width:"133px",float:"left"},attrs:{filterable:"",placeholder:t.$t("select")},on:{change:t.selectRoom},model:{value:t.qyRepeater.roomId,callback:function(e){t.$set(t.qyRepeater,"roomId",e)},expression:"qyRepeater.roomId"}},t._l(t.roomList,function(t){return s("el-option",{key:t.roomId,attrs:{label:t.name,value:t.roomId}})}),1)],1)])]),t._v(" "),s("div",{staticClass:"form-img"},["zh"==t.language?s("img",{attrs:{src:a("Kv3u"),alt:""}}):t._e(),t._v(" "),"en"==t.language?s("img",{attrs:{src:a("aj52"),alt:""}}):t._e()])]),t._v(" "),s("div",{staticClass:"ipt-group clearfix"},[s("a",{staticClass:"on",attrs:{href:"javascript:void(0)"},on:{click:t.openRepeater}},[t._v(t._s(t.$t("button.confirm")))]),t._v(" "),s("a",{staticClass:"off",attrs:{href:"javascript:void(0)"},on:{click:t.closeQylyq}},[t._v(t._s(t.$t("button.close")))])])])])])},staticRenderFns:[]};var d=a("VU/8")(u,p,!1,function(t){a("oRZv")},"data-v-0a4dd73a",null).exports,m={},v={name:"tjlyq",data:function(){return{machineTypeList:[],machineNumList:[],departmentList:[],newRepeater:{departmentId:"",id:"",machineNumId:"",roomId:"",mac:"",ip:""},language:m.$store.state.login.language,roomList:[]}},props:{tjlyqStatus:{type:Boolean},getRepeaterListFn:{type:Function,default:null}},beforeCreate:function(){m=this},mounted:function(){m.getMachineTypeFn(),Object(l.e)().then(function(t){200==t.code&&t.success&&(m.departmentList=t.data)})},methods:{getRoomsListFn:function(){i({departmentId:m.newRepeater.departmentId}).then(function(t){200==t.code&&t.success&&(m.roomList=t.data,0!=m.roomList.length?m.newRepeater.roomId=m.roomList[0].roomId:m.newRepeater.roomId=null)})},selectDepartment:function(t){m.newRepeater.departmentId=t,m.getRoomsListFn()},selectRoom:function(t){m.newRepeater.roomId=t},getMachineTypeFn:function(){var t;Object(o.a)({url:"/repeater/machineType",method:"get",params:t}).then(function(t){200==t.code&&t.success&&(m.machineTypeList=t.data)})},selectMachineType:function(t){m.newRepeater.id=t,m.getMachineNumFn()},getMachineNumFn:function(){(function(t){return Object(o.a)({url:"/repeater/machineNum",method:"get",params:t})})({id:m.newRepeater.id}).then(function(t){200==t.code&&t.success&&(m.machineNumList=t.data,m.newRepeater.machineNumId=m.machineNumList[0].id)})},addRepeater:function(){if(""==m.newRepeater.id)m.error(m.$t("router.err.sbmc"));else if(""==m.newRepeater.machineNumId)m.error(m.$t("router.err.sbxh"));else if(""==m.newRepeater.mac)m.error(m.$t("router.err.macnull"));else if(""==m.newRepeater.ip)m.error(m.$t("router.err.ipnull"));else if(""==m.newRepeater.departmentId)m.error(m.$t("router.err.ksnull"));else if(""==m.newRepeater.roomId)m.error(m.$t("router.err.fjnull"));else{(function(t){return Object(o.a)({url:"/repeater/repeater",method:"post",data:t})})({machineTypeId:m.newRepeater.machineNumId,mac:m.newRepeater.mac,departmentId:m.newRepeater.departmentId,roomId:m.newRepeater.roomId,ip:m.newRepeater.ip}).then(function(t){200==t.code&&t.success&&(m.success(m.$t("message.OperSuccess")),m.closeTjlyq(),m.getRepeaterListFn(),m.newRepeater={departmentId:"",id:"",machineNumId:"",roomId:"",mac:"",ip:""})})}},closeTjlyq:function(){m.$emit("gettjlyqStatus",!m.tjlyqStatus)}},components:{PopUp:n.a}},f={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("PopUp",{attrs:{flow:t.tjlyqStatus}},[s("div",{staticClass:"box"},[s("div",{staticClass:"container"},[s("i",{staticClass:"iconfont close",on:{click:t.closeTjlyq}},[t._v("")]),t._v(" "),s("div",{staticClass:"title clearfix"},[s("i",{staticClass:"iconfont title-img"},[t._v("")]),t._v(" "),s("span",{staticClass:"title-span"},[t._v(t._s(t.$t("router.tjlyq")))])]),t._v(" "),s("div",{staticClass:"content"},[s("div",{staticClass:"form"},[s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.sbmc"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-select",{staticClass:"select",attrs:{filterable:"",placeholder:t.$t("message.select")},on:{change:t.selectMachineType},model:{value:t.newRepeater.id,callback:function(e){t.$set(t.newRepeater,"id",e)},expression:"newRepeater.id"}},t._l(t.machineTypeList,function(t){return s("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}),1)],1)]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.sbxh"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-select",{staticClass:"select",attrs:{filterable:"",placeholder:t.$t("message.select")},model:{value:t.newRepeater.machineNumId,callback:function(e){t.$set(t.newRepeater,"machineNumId",e)},expression:"newRepeater.machineNumId"}},t._l(t.machineNumList,function(t){return s("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}),1)],1)]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.mac"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-input",{staticClass:"select",attrs:{placeholder:"mac"},model:{value:t.newRepeater.mac,callback:function(e){t.$set(t.newRepeater,"mac",e)},expression:"newRepeater.mac"}})],1)]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name"},[s("span",[t._v(t._s(t.$t("router.ip"))+"：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-input",{staticClass:"select",attrs:{placeholder:"ip"},model:{value:t.newRepeater.ip,callback:function(e){t.$set(t.newRepeater,"ip",e)},expression:"newRepeater.ip"}})],1)]),t._v(" "),s("div",{staticClass:"form-item"},[s("div",{staticClass:"form-item-name form-item-name-em"},[s("span",[t._v(t._s(t.$t("router.bsks")))]),s("em",[t._v("：")])]),t._v(" "),s("div",{staticClass:"form-item-value"},[s("el-select",{staticClass:"select",staticStyle:{width:"133px",float:"left"},attrs:{filterable:"",placeholder:t.$t("message.select")},on:{change:t.selectDepartment},model:{value:t.newRepeater.departmentId,callback:function(e){t.$set(t.newRepeater,"departmentId",e)},expression:"newRepeater.departmentId"}},t._l(t.departmentList,function(t){return s("el-option",{key:t.departmentId,attrs:{label:t.name,value:t.departmentId}})}),1),t._v(" "),s("el-select",{staticClass:"select",staticStyle:{width:"133px",float:"left"},attrs:{filterable:"",placeholder:t.$t("message.select")},on:{change:t.selectRoom},model:{value:t.newRepeater.roomId,callback:function(e){t.$set(t.newRepeater,"roomId",e)},expression:"newRepeater.roomId"}},t._l(t.roomList,function(t){return s("el-option",{key:t.roomId,attrs:{label:t.name,value:t.roomId}})}),1)],1)])]),t._v(" "),s("div",{staticClass:"form-img"},["zh"==t.language?s("img",{attrs:{src:a("Kv3u"),alt:""}}):t._e(),t._v(" "),"en"==t.language?s("img",{attrs:{src:a("aj52"),alt:""}}):t._e()])]),t._v(" "),s("div",{staticClass:"ipt-group clearfix"},[s("a",{staticClass:"on",attrs:{href:"javascript:void(0)"},on:{click:t.addRepeater}},[t._v(t._s(t.$t("button.confirm")))]),t._v(" "),s("a",{staticClass:"off",attrs:{href:"javascript:void(0)"},on:{click:t.closeTjlyq}},[t._v(t._s(t.$t("button.close")))])])])])])},staticRenderFns:[]};var _=a("VU/8")(v,f,!1,function(t){a("mrOq")},"data-v-9d2b967a",null).exports,y={},g={name:"ghsb",data:function(){return{gzType:0}},props:{stopId:{type:[Number,String]},tylyqStatus:{type:Boolean},getRepeaterListFn:{type:Function,default:null}},beforeCreate:function(){y=this},mounted:function(){},methods:{stopRepeaterFn:function(){var t;0==y.gzType?t=y.$t("router.reason1"):1==y.gzType?t=y.$t("router.reason2"):2==y.gzType&&(t=y.$t("router.reason3"));var e={id:y.stopId,remark:t};(function(t){return Object(o.a)({url:"/repeater/stop",method:"put",data:t})})(e).then(function(t){200==t.code&&t.success&&(y.success(y.$t("message.OperSuccess")),y.$emit("gettylyqStatus",!y.tylyqStatus),y.getRepeaterListFn())})},closeTylyq:function(){y.$emit("gettylyqStatus",!y.tylyqStatus)}},components:{PopUp:n.a}},b={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("PopUp",{attrs:{flow:t.tylyqStatus}},[a("div",{staticClass:"box"},[a("div",{staticClass:"container"},[a("i",{staticClass:"iconfont close",on:{click:t.closeTylyq}},[t._v("")]),t._v(" "),a("div",{staticClass:"title clearfix"},[a("i",{staticClass:"iconfont title-img"},[t._v("")]),t._v(" "),a("span",{staticClass:"title-span"},[t._v(t._s(t.$t("router.tylyq")))])]),t._v(" "),a("div",{staticClass:"content"},[a("div",{staticClass:"form"},[a("div",{staticClass:"form-item"},[a("div",{staticClass:"form-item-name"},[a("span",[t._v(t._s(t.$t("router.tyyy"))+"：")])]),t._v(" "),a("div",{staticClass:"form-item-value"},[a("a",{staticClass:"resaon",class:0==t.gzType?"a-active":"",attrs:{href:"javascript:void(0)"},on:{click:function(e){t.gzType=0}}},[t._v(t._s(t.$t("router.reason1")))]),t._v(" "),a("a",{staticClass:"resaon",class:1==t.gzType?"a-active":"",attrs:{href:"javascript:void(0)"},on:{click:function(e){t.gzType=1}}},[t._v(t._s(t.$t("router.reason2")))]),t._v(" "),a("a",{staticClass:"resaon",class:2==t.gzType?"a-active":"",attrs:{href:"javascript:void(0)"},on:{click:function(e){t.gzType=2}}},[t._v(t._s(t.$t("router.reason3")))])])])])]),t._v(" "),a("div",{staticClass:"ipt-group clearfix"},[a("a",{staticClass:"on",attrs:{href:"javascript:void(0)"},on:{click:t.stopRepeaterFn}},[t._v(t._s(t.$t("button.confirm")))]),t._v(" "),a("a",{staticClass:"off",attrs:{href:"javascript:void(0)"},on:{click:t.closeTylyq}},[t._v(t._s(t.$t("button.close")))])])])])])},staticRenderFns:[]};var h=a("VU/8")(g,b,!1,function(t){a("r5g0")},"data-v-0dd3ec22",null).exports,C={},R={data:function(){return{value:"",routerObj:{departmentId:""},rooms:[],unBindRepeaterList:[],bindList:[],unBindList:[],departmentList:[]}},props:["allotRouterStatus"],beforeCreate:function(){C=this},mounted:function(){Object(l.e)().then(function(t){200==t.code&&t.success&&(C.departmentList=t.data)})},components:{PopUp:n.a},methods:{closePop:function(){this.$emit("gettAllotRouter",!1),C.routerObj={departmentId:"",list:[]},C.bindList=[]},selectDepartment:function(){var t;(t={departmentId:C.routerObj.departmentId},Object(o.a)({url:"/repeater/room",method:"get",params:t})).then(function(t){t.success&&200==t.code&&(C.rooms=t.data.rooms,C.bindList=JSON.parse(r()(C.rooms)),C.unBindRepeaterList=t.data.unBindRepeaterList,C.unBindList=JSON.parse(r()(C.unBindRepeaterList)))})},save:function(){var t,e={};e.departmentId=C.routerObj.departmentId,e.list=[],C.bindList.map(function(t){var a={};null==t.list&&(t.list=[]),a.roomId=t.roomId,a.repeaterIds=t.list,e.list.push(a)}),(t=e,Object(o.a)({url:"/repeater/room",method:"put",data:t})).then(function(t){t.success&&200==t.code&&(C.success(C.$t("router.fpcg")),C.closePop())})},removeSelect:function(t){for(var e=C.unBindList,a=0;a<e.length;a++)for(var s=0;s<t.length;s++)e[a].id==t[s]&&C.unBindList.splice(a,1)},deleteTag:function(t){C.unBindRepeaterList.map(function(e){e.id==t&&C.unBindList.push(e)})}}},q={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("PopUp",{attrs:{flow:t.allotRouterStatus}},[a("div",{staticClass:"box"},[a("div",{staticClass:"container"},[a("i",{staticClass:"iconfont close",on:{click:t.closePop}},[t._v("")]),t._v(" "),a("div",{staticClass:"title clearfix"},[a("i",{staticClass:"iconfont title-img"},[t._v("")]),t._v(" "),a("span",{staticClass:"title-span"},[t._v(t._s(t.$t("router.fply")))])]),t._v(" "),a("div",{staticClass:"selectDepartment-warp"},[a("div",{staticClass:"left"},[t._v(t._s(t.$t("router.syks"))+":")]),t._v(" "),a("el-select",{attrs:{placeholder:t.$t("message.select")},on:{change:t.selectDepartment},model:{value:t.routerObj.departmentId,callback:function(e){t.$set(t.routerObj,"departmentId",e)},expression:"routerObj.departmentId"}},t._l(t.departmentList,function(t){return a("el-option",{key:t.departmentId,attrs:{label:t.name,value:t.departmentId}})}),1)],1),t._v(" "),a("div",{staticClass:"content"},[t.routerObj.departmentId?a("div",{staticClass:"room-box"},[a("div",{staticClass:"room-item"},[a("div",{staticClass:"left"},[a("div",{staticClass:"left-item"},[t._v(t._s(t.$t("router.room")))])]),t._v(" "),a("div",{staticClass:"right"},[a("div",{staticClass:"right-item"},[t._v(t._s(t.$t("router.ip")))])])]),t._v(" "),t._l(t.bindList,function(e,s){return a("div",{key:s,staticClass:"room-item"},[a("div",{staticClass:"left"},[a("div",{staticClass:"left-item"},[t._v(t._s(e.roomName))])]),t._v(" "),a("div",{staticClass:"right"},[a("div",{staticClass:"right-item"},[a("el-select",{staticStyle:{border:"none"},attrs:{multiple:"",placeholder:t.$t("message.select")},on:{change:t.removeSelect,"remove-tag":t.deleteTag},model:{value:e.list,callback:function(a){t.$set(e,"list",a)},expression:"item.list"}},t._l(t.unBindList,function(t,e){return a("el-option",{key:t.value,attrs:{label:t.value,value:t.id}})}),1)],1)])])})],2):t._e(),t._v(" "),null==t.routerObj.departmentId||""==t.routerObj.departmentId?a("div",{staticClass:"room-box1"},[a("p",{staticClass:"tip"},[t._v(t._s(t.$t("router.qxszks")))])]):t._e(),t._v(" "),t.routerObj.departmentId&&0==t.bindList.length?a("div",{staticClass:"room-box1"},[a("p",{staticClass:"tip"},[t._v(t._s(t.$t("message.noData")))])]):t._e()]),t._v(" "),a("div",{staticClass:"bottom-tip"},[t._v("\n        "+t._s(t.$t("router.zly"))+"\n      ")]),t._v(" "),a("div",{staticClass:"ipt-group clearfix"},[a("a",{staticClass:"on",attrs:{href:"javascript:void(0)"},on:{click:t.save}},[t._v(t._s(t.$t("router.baocun")))]),t._v(" "),a("a",{staticClass:"off",attrs:{href:"javascript:void(0)"},on:{click:t.closePop}},[t._v(t._s(t.$t("button.close")))])])])])])},staticRenderFns:[]};var $=a("VU/8")(R,q,!1,function(t){a("4dfQ")},"data-v-63ce80b8",null).exports,I={},S={name:"router",data:function(){return{qylyqStatus:!1,tjlyqStatus:!1,tylyqStatus:!1,allotRouterStatus:!1,userInfo:{},stopId:"",pageSize:I.global.pageSize,currentPage:I.global.currentPage,currentDepartmentId:"",departmentList:[],repeaterList:[],roomList:[],currentRepeater:{},repeaterData:[],leftDepartmentList:[]}},beforeCreate:function(){I=this},mounted:function(){var t=this;Object(l.e)().then(function(e){if(200==e.code&&e.success){I.departmentList=e.data;var a=[],s=JSON.parse(r()(I.departmentList));a.push({departmentId:null,name:"全部"}),a=a.concat(s),I.leftDepartmentList=a,I.userInfo=t.$store.state.login.userInfo,I.currentDepartmentId=I.leftDepartmentList[0].departmentId,I.getRepeaterListFn()}})},methods:{handleCurrentChange:function(t){I.currentPage=t,I.getRepeaterListFn()},gettylyqStatus:function(t){I.tylyqStatus=t},getqylyqStatus:function(t){I.qylyqStatus=t},gettjlyqStatus:function(t){I.tjlyqStatus=t},gettAllotRouter:function(t){this.allotRouterStatus=t,I.getRepeaterListFn()},stopLyq:function(t){I.stopId=t.id,I.tylyqStatus=!I.tylyqStatus},openLyq:function(t){I.currentRepeater=t,I.qylyqStatus=!I.qylyqStatus},selectDepart:function(t){I.currentDepartmentId=t,I.currentPage=I.global.currentPage,I.getRepeaterListFn()},getRepeaterListFn:function(){(function(t){return Object(o.a)({url:"/repeater/repeater",method:"get",params:t})})({departmentId:I.currentDepartmentId,page:I.currentPage,pageSize:I.global.pageSize}).then(function(t){200==t.code&&t.success&&(I.repeaterData=t.data,I.repeaterList=t.data.list)})}},components:{qylyq:d,tjlyq:_,tylyq:h,Topbar:a("looZ").a,allotRouter:$}},L={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"router-all"},[s("img",{staticClass:"bg",attrs:{src:a("yEeP"),alt:""}}),t._v(" "),s("Topbar",[s("i",{staticClass:"iconfont"},[t._v("")]),t._v(" "),s("span",[t._v(t._s(t.$t("router.title")))])]),t._v(" "),s("div",{staticClass:"router-main"},[s("div",{staticClass:"router-choose"},[s("div",{staticClass:"router-row"},[s("div",{staticClass:"row-left"},[s("span",{staticClass:"choose-title"},[t._v(t._s(t.$t("router.ksxz"))+":")]),t._v(" "),s("el-select",{staticClass:"select",attrs:{filterable:"",placeholder:t.$t("message.select")},on:{change:t.selectDepart},model:{value:t.currentDepartmentId,callback:function(e){t.currentDepartmentId=e},expression:"currentDepartmentId"}},t._l(t.leftDepartmentList,function(t){return s("el-option",{key:t.departmentId,attrs:{label:t.name,value:t.departmentId}})}),1)],1),t._v(" "),s("div",{staticClass:"row-right"},[s("div",{staticClass:"buttons"},[s("a",{attrs:{href:"javascript:void(0)"},on:{click:function(e){t.tjlyqStatus=!t.tjlyqStatus}}},[t._v(t._s(t.$t("router.tjly")))])]),t._v(" "),s("div",{staticClass:"buttons",staticStyle:{"margin-left":"12px"}},[s("a",{on:{click:function(e){t.allotRouterStatus=!t.allotRouterStatus}}},[t._v(t._s(t.$t("router.fply")))])])])])]),t._v(" "),s("div",{staticClass:"main"},[s("el-table",{staticStyle:{width:"100%"},attrs:{data:t.repeaterList,"empty-text":t.$t("message.noData")}},[s("el-table-column",{attrs:{prop:"id",label:t.$t("router.xh"),width:"100"}}),t._v(" "),s("el-table-column",{attrs:{prop:"repeaterName",label:t.$t("router.sbmc")}}),t._v(" "),s("el-table-column",{attrs:{prop:"repeaterNum",label:t.$t("router.sbxh")}}),t._v(" "),s("el-table-column",{attrs:{prop:"mac",label:t.$t("router.mac")}}),t._v(" "),s("el-table-column",{attrs:{prop:"ip",label:"IP"}}),t._v(" "),s("el-table-column",{attrs:{prop:"departmentName",label:t.$t("router.bsks")},scopedSlots:t._u([{key:"default",fn:function(e){return[2==e.row.bindStatus?s("span",{staticStyle:{color:"rgba(0, 145, 255, 1)"}},[t._v(t._s(e.row.departmentName))]):t._e(),t._v(" "),2!=e.row.bindStatus?s("span",[t._v(t._s(e.row.departmentName))]):t._e()]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"roomName",label:t.$t("router.azwz")},scopedSlots:t._u([{key:"default",fn:function(e){return[1==e.row.bindStatus||2==e.row.bindStatus?s("span",{staticStyle:{color:"rgba(0, 145, 255, 1)"}},[t._v(t._s(e.row.roomName))]):t._e(),t._v(" "),0==e.row.bindStatus?s("span",[t._v(t._s(e.row.roomName))]):t._e()]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"linkstatus",label:t.$t("router.state")},scopedSlots:t._u([{key:"default",fn:function(e){return[1==t.repeaterList[e.$index].linkStatus?s("span",{staticStyle:{color:"rgb(224, 32, 32)"}},[t._v("OFF")]):t._e(),t._v(" "),0==t.repeaterList[e.$index].linkStatus?s("span",{staticStyle:{cursor:"pointer"}},[t._v("ON")]):t._e(),t._v(" "),2==t.repeaterList[e.$index].linkStatus?s("span",{staticStyle:{cursor:"pointer"}},[t._v(t._s(t.$t("router.stop")))]):t._e()]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"remark",label:t.$t("router.remark")}}),t._v(" "),s("el-table-column",{attrs:{prop:"operation",label:t.$t("router.option")},scopedSlots:t._u([{key:"default",fn:function(e){return[2==t.repeaterList[e.$index].linkStatus?s("span",{staticStyle:{cursor:"pointer",color:"rgb(0, 145, 255)"},on:{click:function(a){return t.openLyq(e.row)}}},[t._v(t._s(t.$t("router.enable")))]):t._e(),t._v(" "),2!=t.repeaterList[e.$index].linkStatus?s("span",{staticStyle:{cursor:"pointer"},on:{click:function(a){return t.stopLyq(e.row)}}},[t._v(t._s(t.$t("router.stop")))]):t._e()]}}])})],1)],1),t._v(" "),s("el-pagination",{attrs:{background:"","page-size":t.pageSize,"current-page":t.currentPage,layout:"prev, pager, next",total:t.repeaterData.records},on:{"current-change":t.handleCurrentChange}})],1),t._v(" "),t.qylyqStatus?s("qylyq",{attrs:{qylyqStatus:t.qylyqStatus,currentRepeater:t.currentRepeater,getRepeaterListFn:t.getRepeaterListFn},on:{getqylyqStatus:t.getqylyqStatus}}):t._e(),t._v(" "),t.tjlyqStatus?s("tjlyq",{attrs:{tjlyqStatus:t.tjlyqStatus,getRepeaterListFn:t.getRepeaterListFn},on:{gettjlyqStatus:t.gettjlyqStatus}}):t._e(),t._v(" "),t.tylyqStatus?s("tylyq",{attrs:{tylyqStatus:t.tylyqStatus,stopId:t.stopId,getRepeaterListFn:t.getRepeaterListFn},on:{gettylyqStatus:t.gettylyqStatus}}):t._e(),t._v(" "),s("allotRouter",{attrs:{allotRouterStatus:t.allotRouterStatus,departmentList:t.departmentList},on:{gettAllotRouter:t.gettAllotRouter}})],1)},staticRenderFns:[]};var w=a("VU/8")(S,L,!1,function(t){a("MPiK")},null,null);e.default=w.exports}});
//# sourceMappingURL=5.7a789d9655dae2b34257.js.map