<template>
  <div v-if="show" class="list-box">
    <div class="category-list-box">
      <el-table
          :data="
          musics.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
          style="width: 100%"
          class="table_color"
          :row-class-name="tableRowClassName"
          :header-cell-style="getRowClass">
        <el-table-column width="20"></el-table-column>
        <el-table-column prop="id" label="id" width="300">
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
        <el-table-column prop="url" label="接口地址" width="500">
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

        <el-table-column prop="user" label="添加人" width="139">
          <template slot-scope="scope">
           <span
               v-text="scope.row.user"
               style="color: white; font-weight: bold">
             </span>
          </template>
        </el-table-column>

        <el-table-column label="" width="200">

        </el-table-column>

      </el-table>

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
      musicText: "",
      musicId: null,
      musicItems: [
        {
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

    sendMessage: function (msg, msgType) {
      this.$message({
        message: msg,
        center: true,
        type: msgType,
      });
    },

    getData() {
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
  }
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