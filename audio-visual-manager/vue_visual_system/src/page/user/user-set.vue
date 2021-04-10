<template>
  <div v-if="show" class="list-box">
    <div class="category-list-box">
      <el-table
          :data="
          users.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
          style="width: 100%"
          class="table_color"
          :row-class-name="tableRowClassName"
          :header-cell-style="getRowClass">
        <el-table-column width="20"></el-table-column>
        <el-table-column prop="id" label="id" width="220">
          <template slot-scope="scope">
             <span
                 v-text="scope.row.id"
                 style="color: white; font-weight: bold">
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="用户名" width="180">
          <template slot-scope="scope">
            <span
                v-text="scope.row.name"
                style="color: white; font-weight: bold">
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="299">
          <template slot-scope="scope">
           <span
               v-text="scope.row.email"
               style="color: white; font-weight: bold">
            </span>
          </template>
        </el-table-column>

        <el-table-column label="头像" width="190">
          <template slot-scope="scope">
            <div>
              <el-avatar :src="scope.row.avatar"></el-avatar>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="状态" width="120">
          <template slot-scope="scope">
            <div v-if="scope.row.state === '0'">
              <el-tag type="danger" size="medium">禁用</el-tag>
            </div>
            <div v-if="scope.row.state === '1'">
              <el-tag type="success" size="medium">正常</el-tag>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="239">
          <template slot-scope="scope">
            <el-button type="warning" size="mini" @click="forbidden(scope.row)">
              禁用
            </el-button>

            <el-button type="danger" size="mini" @click="deleteItem(scope.row)">
              删除
            </el-button>
          </template>
        </el-table-column>

        <el-table-column width="90"></el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[8, 9, 10]"
            layout="total, sizes,prev, pager, next"
            :total="users.length">
        </el-pagination>

        <div class="lables-dialog-box">
          <el-dialog
              title="删除提示"
              :visible.sync="deleteDialogShow"
              width="400px">
            <span>你确定要删除<strong><font color="red">{{ deleleteMessage }}</font> </strong>这个用户</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="cancelItem" size="mini" type="danger"
              >取 消</el-button
              >
              <el-button type="primary" @click="doDeleteItem" size="mini"
              >确 定</el-button
              >
            </span>
          </el-dialog>

          <el-dialog title="添加用户" :visible.sync="editUser" width="600px">
            <div class="lable-add_box">
              <el-form label-width="60px">
                <el-form-item label="用户名">
                  <el-input
                      v-model="UsersItems.name"
                      @input="change($event)"
                  ></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input
                      v-model="UsersItems.password"
                      @input="change($event)"
                      type="password"
                  ></el-input>
                </el-form-item>

                <el-form-item label="邮箱">
                  <el-input
                      v-model="UsersItems.email"
                      @input="change($event)"
                  ></el-input>
                </el-form-item>

                <el-form-item label="头像">
                  <el-input
                      v-model="UsersItems.avatar"
                      @input="change($event)"
                  ></el-input>
                </el-form-item>
              </el-form>
            </div>

            <span slot="footer" class="dialog-footer">
              <el-button @click="cancelAddItem" size="mini" type="danger">取 消</el-button>
              <el-button type="primary" @click="addItem" size="mini">确 定</el-button>
            </span>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      show: false,
      currentPage: 1, //默认显示页面为1
      pagesize: 8, //    每页的数据条数
      users: [], //需要data定义一些，tableData定义一个空数组，请求的数据都是存放这里面
      deleleteMessage: "",
      editUser: false,
      deleteTargeId: "",
      deleteDialogShow: false,
      UsersItems: [
        {
          name: "",
          password: "",
          email: "",
          avatar:""
        },
      ],
    };
  },
  methods: {
    //控制行颜色
    tableRowClassName({ row, rowIndex }) {
      if ((rowIndex + 1) % 2 === 0) {
        return "double"; //对应的类
      } else {
        return "single"; //对应的类
      }
    },
    //控制表格头部颜色
    getRowClass({ rowIndex }) {
      if (rowIndex == 0) {
        return "background:#2d3a5c";
      } else {
        return "";
      }
    },

    keyDown(e) {
      //如果是回车则执行登录方法
      if (e.keyCode == 13) {
        this.addItem();
      }
    },

    deleteItem: function (item) {
      this.deleteDialogShow = true;
      this.deleleteMessage = item.name;
      this.deleteTargeId = item.id;
      console.log(item);
    },

    cancelAddItem: function () {
      this.editUser = false;
    },

    cancelItem: function () {
      this.deleteDialogShow = false;
    },

    forbidden: function (item) {
      this.$axios.get("/root/user/" +  item.id).then((response) => {
        let res = response.data;
        if (res.code === 20000) {
          this.$message({
            message: "禁用(解禁)成功",
            center: true,
            type: "success",
          });
          this.getData();
        } else {
          this.$message({
            message: response.data.message,
            center: true,
            type: "error",
          });
        }
      }), (response) => {console.log("error");};
    },

    doDeleteItem: function () {
      this.$axios.delete("/root/user/" + this.deleteTargeId).then((response) => {
            if (response.data.code === 20000) {
              this.getData();
              this.$message({
                message: response.data.message,
                center: true,
                type: "success",
              });
            } else {
              this.$message({
                message: response.data.message,
                center: true,
                type: "error",
              });
              this.getData();
            }
          },
          (response) => {}
      );
      this.getData();
      this.deleteDialogShow = false;
    },

    getData() {
      this.show = true;
      this.$axios.get("/root/list").then(
          (response) => {
            // console.log(response.data);
            console.log(response.data.data);
            this.users = response.data.data;
          },
          (response) => {
            console.log("error");
          }
      );
    },
    //每页下拉显示数据
    handleSizeChange: function (size) {
      this.pagesize = size;
      /*console.log(this.pagesize) */
    },
    //点击第几页
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
      /*console.log(this.currentPage) */
    },

    change(e) {
      this.$forceUpdate();
    },
  },
  mounted() {
    this.getData();
    window.addEventListener("keydown", this.keyDown);
  },
  destroyed() {
    window.removeEventListener("keydown", this.keyDown, false);
  },
};
</script>

<style>
.table {
  width: 900px;
  margin: 100px auto;
}
/*更改表格颜色*/
.double {
  background: #262d47 !important;
}
.single {
  background: #2a314d !important;
}

.category-list-box {
  margin-top: 20px;
  margin-right: 4px;
}

.el-table__body tr:hover > td {
  background-color: #134087 !important;
}

.el-table__body-wrapper::-webkit-scrollbar {
  /*width: 0;宽度为0隐藏*/
  width: 0px;
}

body {
  overflow-y: auto;
}
</style>