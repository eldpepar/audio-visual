(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6c184987"],{1985:function(e,t,a){},aea9:function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.show?a("div",{staticClass:"list-box"},[a("div",{staticClass:"category-list-box"},[a("el-table",{staticClass:"table_color",staticStyle:{width:"100%"},attrs:{data:e.users.slice((e.currentPage-1)*e.pagesize,e.currentPage*e.pagesize),"row-class-name":e.tableRowClassName,"header-cell-style":e.getRowClass}},[a("el-table-column",{attrs:{width:"20"}}),a("el-table-column",{attrs:{prop:"id",label:"id",width:"220"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{staticStyle:{color:"white","font-weight":"bold"},domProps:{textContent:e._s(t.row.id)}})]}}],null,!1,3795576427)}),a("el-table-column",{attrs:{prop:"name",label:"用户名",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{staticStyle:{color:"white","font-weight":"bold"},domProps:{textContent:e._s(t.row.name)}})]}}],null,!1,4042567009)}),a("el-table-column",{attrs:{prop:"email",label:"邮箱",width:"299"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{staticStyle:{color:"white","font-weight":"bold"},domProps:{textContent:e._s(t.row.email)}})]}}],null,!1,3315409834)}),a("el-table-column",{attrs:{label:"头像",width:"190"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("div",[a("el-avatar",{attrs:{src:e.row.avatar}})],1)]}}],null,!1,3254357438)}),a("el-table-column",{attrs:{label:"状态",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return["0"===t.row.state?a("div",[a("el-tag",{attrs:{type:"danger",size:"medium"}},[e._v("禁用")])],1):e._e(),"1"===t.row.state?a("div",[a("el-tag",{attrs:{type:"success",size:"medium"}},[e._v("正常")])],1):e._e()]}}],null,!1,1294392959)}),a("el-table-column",{attrs:{label:"操作",width:"239"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"warning",size:"mini"},on:{click:function(a){return e.forbidden(t.row)}}},[e._v(" 禁用 ")]),a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){return e.deleteItem(t.row)}}},[e._v(" 删除 ")])]}}],null,!1,4210611359)}),a("el-table-column",{attrs:{width:"90"}})],1),a("div",{staticClass:"pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-sizes":[8,9,10],layout:"total, sizes,prev, pager, next",total:e.users.length},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),a("div",{staticClass:"lables-dialog-box"},[a("el-dialog",{attrs:{title:"删除提示",visible:e.deleteDialogShow,width:"400px"},on:{"update:visible":function(t){e.deleteDialogShow=t}}},[a("span",[e._v("你确定要删除"),a("strong",[a("font",{attrs:{color:"red"}},[e._v(e._s(e.deleleteMessage))])],1),e._v("这个用户")]),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:e.cancelItem}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.doDeleteItem}},[e._v("确 定")])],1)]),a("el-dialog",{attrs:{title:"添加用户",visible:e.editUser,width:"600px"},on:{"update:visible":function(t){e.editUser=t}}},[a("div",{staticClass:"lable-add_box"},[a("el-form",{attrs:{"label-width":"60px"}},[a("el-form-item",{attrs:{label:"用户名"}},[a("el-input",{on:{input:function(t){return e.change(t)}},model:{value:e.UsersItems.name,callback:function(t){e.$set(e.UsersItems,"name",t)},expression:"UsersItems.name"}})],1),a("el-form-item",{attrs:{label:"密码"}},[a("el-input",{attrs:{type:"password"},on:{input:function(t){return e.change(t)}},model:{value:e.UsersItems.password,callback:function(t){e.$set(e.UsersItems,"password",t)},expression:"UsersItems.password"}})],1),a("el-form-item",{attrs:{label:"邮箱"}},[a("el-input",{on:{input:function(t){return e.change(t)}},model:{value:e.UsersItems.email,callback:function(t){e.$set(e.UsersItems,"email",t)},expression:"UsersItems.email"}})],1),a("el-form-item",{attrs:{label:"头像"}},[a("el-input",{on:{input:function(t){return e.change(t)}},model:{value:e.UsersItems.avatar,callback:function(t){e.$set(e.UsersItems,"avatar",t)},expression:"UsersItems.avatar"}})],1)],1)],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:e.cancelAddItem}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.addItem}},[e._v("确 定")])],1)])],1)],1)],1)]):e._e()},n=[],o=(a("b0c0"),{data:function(){return{show:!1,currentPage:1,pagesize:8,users:[],deleleteMessage:"",editUser:!1,deleteTargeId:"",deleteDialogShow:!1,UsersItems:[{name:"",password:"",email:"",avatar:""}]}},methods:{tableRowClassName:function(e){e.row;var t=e.rowIndex;return(t+1)%2===0?"double":"single"},getRowClass:function(e){var t=e.rowIndex;return 0==t?"background:#2d3a5c":""},keyDown:function(e){13==e.keyCode&&this.addItem()},deleteItem:function(e){this.deleteDialogShow=!0,this.deleleteMessage=e.name,this.deleteTargeId=e.id,console.log(e)},cancelAddItem:function(){this.editUser=!1},cancelItem:function(){this.deleteDialogShow=!1},forbidden:function(e){var t=this;this.$axios.get("/root/user/"+e.id).then((function(e){var a=e.data;2e4===a.code?(t.$message({message:"禁用(解禁)成功",center:!0,type:"success"}),t.getData()):t.$message({message:e.data.message,center:!0,type:"error"})}))},doDeleteItem:function(){var e=this;this.$axios.delete("/root/user/"+this.deleteTargeId).then((function(t){2e4===t.data.code?(e.getData(),e.$message({message:t.data.message,center:!0,type:"success"})):(e.$message({message:t.data.message,center:!0,type:"error"}),e.getData())}),(function(e){})),this.getData(),this.deleteDialogShow=!1},getData:function(){var e=this;this.show=!0,this.$axios.get("/root/list").then((function(t){console.log(t.data.data),e.users=t.data.data}),(function(e){console.log("error")}))},handleSizeChange:function(e){this.pagesize=e},handleCurrentChange:function(e){this.currentPage=e},change:function(e){this.$forceUpdate()}},mounted:function(){this.getData(),window.addEventListener("keydown",this.keyDown)},destroyed:function(){window.removeEventListener("keydown",this.keyDown,!1)}}),l=o,i=(a("cfb4"),a("2877")),r=Object(i["a"])(l,s,n,!1,null,null,null);t["default"]=r.exports},b0c0:function(e,t,a){var s=a("83ab"),n=a("9bf2").f,o=Function.prototype,l=o.toString,i=/^\s*function ([^ (]*)/,r="name";s&&!(r in o)&&n(o,r,{configurable:!0,get:function(){try{return l.call(this).match(i)[1]}catch(e){return""}}})},cfb4:function(e,t,a){"use strict";a("1985")}}]);
//# sourceMappingURL=chunk-6c184987.4df70547.js.map