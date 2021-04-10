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
        <el-table-column prop="id" label="id" width="200">
          <template slot-scope="scope">
             <span
                 v-text="scope.row.id"
                 style="color: white; font-weight: bold">
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="用户名" width="200">
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

        <el-table-column label="状态" width="200">
          <template slot-scope="scope">
            <div v-if="scope.row.state === '0'">
              <el-tag type="danger" size="medium">禁用</el-tag>
            </div>
            <div v-if="scope.row.state === '1'">
              <el-tag type="success" size="medium">正常</el-tag>
            </div>
          </template>
        </el-table-column>

        <el-table-column label="操作" width="159">
          <template slot-scope="scope">
            <el-button type="warning" size="mini" @click="edit(scope.row)">
                修改
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

          <el-dialog title="修改用户" :visible.sync="editUser" width="600px">
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
              <el-button type="primary" @click="editItem" size="mini">确 定</el-button>
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
      editUser: false,
      userId:null,
      userText:null,
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

    sendMessage: function (msg, msgType) {
      this.$message({
        message: msg,
        center: true,
        type: msgType,
      });
    },

    cancelAddItem: function () {
      this.editUser = false;
    },

    cancelItem: function () {
      this.deleteDialogShow = false;
    },

    edit: function (item) {
      this.UsersItems.name = item.name;
      this.UsersItems.password = item.password
      this.UsersItems.email = item.email
      this.UsersItems.avatar = item.avatar

      this.userId = item.id;
      this.userText = "修改电影接口";
      this.editUser = true;
    },

    editItem: function () {
      console.log("执行的编辑方法");
      this.$axios
          .put("/root/user/" + this.userId, {
            name: this.UsersItems.name,
            password:  this.UsersItems.password,
            avatar: this.UsersItems.avatar,
            email:this.UsersItems.email
          })
          .then((result) => {
            console.log("result =>" + result);
            if (result.data.code === 20000) {
              this.sendMessage("修改成功", "success");
              this.getData();
            } else {
              this.sendMessage("修改失败", "error");
            }
          }),
          (response) => {
            this.sendMessage("请求失败", "error");
          };
      this.userId = null;
      this.lablesItems = {};
      this.editUser = false;
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