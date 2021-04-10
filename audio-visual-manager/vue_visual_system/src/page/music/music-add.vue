<template>
  <div v-if="show" class="list-box">

    <div class="category-action-bar">
      <el-button type="info" size="mini" @click="addItemMusic">添加音乐解析接口</el-button>
    </div>

    <div class="category-list-box">
      <el-table
          :data="
          musics.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
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
        <el-table-column prop="name" label="接口名称" width="200">
          <template slot-scope="scope">
             <span
                 v-text="scope.row.name"
                 style="color: white; font-weight: bold">
             </span>
          </template>
        </el-table-column>
        <el-table-column prop="url" label="接口地址" width="539">
          <template slot-scope="scope">
            <span
                v-text="scope.row.url"
                style="
                width: 520px;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                color: white;
                font-weight: bold;">
            </span>
          </template>
        </el-table-column>

        <el-table-column prop="user" label="添加人" width="200">
          <template slot-scope="scope">
           <span
               v-text="scope.row.user"
               style="color: white; font-weight: bold">
             </span>
          </template>
        </el-table-column>

        <el-table-column width="200"></el-table-column>
      </el-table>

      <div class="lables-dialog-box">
        <el-dialog :title="musicText" :visible.sync="editMusic" width="600px">
          <div class="lable-add_box">
            <el-form label-width="80px">
              <el-form-item label="接口名称">
                <el-input v-model="musicItems.name" @input="change($event)" ></el-input>
              </el-form-item>
              <el-form-item label="接口地址">
                <el-input v-model="musicItems.url" @input="change($event)" ></el-input>
              </el-form-item>
            </el-form>
          </div>

          <span slot="footer" class="dialog-footer">
            <el-button @click="cancelAddItem" size="mini" type="danger">取 消</el-button>
            <el-button type="primary" @click="addItem" size="mini">确 定</el-button>
          </span>
        </el-dialog>
      </div>
      <div class="pagination">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[8, 9, 10]"
            :page-size="pagesize"
            layout="total, sizes,prev, pager, next"
            :total="musics.length">
        </el-pagination>
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
      musics: [], //需要data定义一些，tableData定义一个空数组，请求的数据都是存放这里面
      editMusic: false,
      musicText: "",
      username:null,
      movieId: null,
      musicItems: [
        {
          id: "",
          name: "",
          url: "",
          user:""
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

    edit: function (item) {
      this.musicItems.name = item.name;
      this.musicItems.url = item.url;
      this.musicItems.user = item.user;
      this.movieId = item.id;
      this.musicText = "修改音乐接口";
      this.editMusic = true;
    },

    addItem: function () {
      console.log("执行的添加方法");
      this.$axios
          .post("/admin/music-add", {
            name: this.musicItems.name,
            url:  this.musicItems.url,
            user: this.username
          })
          .then((result) => {
            if (result.data.code === 20000) {
              this.sendMessage("添加成功", "success");
              this.getData();
            } else {
              this.sendMessage("添加失败", "error");
            }
          }),
          (response) => {
            this.sendMessage("请求失败", "error");
          };
      this.editMusic = false;
    },

    addItemMusic: function () {
      this.editMusic = true;
    },

    cancelAddItem: function () {
      this.editMusic = false;
    },

    sendMessage: function (msg, msgType) {
      this.$message({
        message: msg,
        center: true,
        type: msgType,
      });
    },

    getData() {
      this.$axios.get("/user/check-token").then((response) => {
        let res = response.data;
        if (res.code === 20000) {
          console.log("username is ===>"+res.data.name)
          this.username = res.data.name
        }
      }), (response) => {console.log("error");};

      this.show = true;
      this.$axios.get("/user/music-list").then(
          (response) => {
            console.log(response.data.data);
            console.log(response.data.data.content);
            this.musics = response.data.data;
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