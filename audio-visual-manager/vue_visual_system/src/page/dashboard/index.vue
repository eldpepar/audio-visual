<template>
  <div class="index-box">
    <div class="index-top-info">
      <el-row type="flex" class="row-bg" justify="space-around">
        <el-col :span="5">
          <el-card class="box-card card-video">
            <div slot="header" class="clearfix">
              <b><span style="color: white">直播接口</span></b>
            </div>
            <div class="card-content">
              <font size="7" color="white">{{tvCount}}</font>
            </div>
          </el-card>
        </el-col>
        <el-col :span="5">
          <el-card class="box-card card-music">
            <div slot="header" class="clearfix">
              <b><span style="color: white">音乐接口</span></b>
            </div>
            <div class="card-content">
              <font size="7" color="white">{{ musicCount }}</font>
            </div>
          </el-card>
        </el-col>

        <el-col :span="5">
          <el-card class="box-card card-access">
            <div slot="header" class="clearfix">
              <b><span style="color: white">电影接口</span></b>
            </div>
            <div class="card-content">
              <font size="7" color="white">{{movieCount}}</font>
            </div>
          </el-card>
        </el-col>

        <el-col :span="5">
          <el-card class="box-card card-user">
            <div slot="header" class="clearfix">
              <b><span style="color: white">用户总量</span></b>
            </div>
            <div class="card-content">
              <font size="7" color="white">{{userCount}}</font>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div class="box-chart">
      <div class="box-chart-left" >

          <div class="box-main" id="main">

          </div>
      </div>
      <div class="box-chart-right">
        <div style="text-align: center; margin-top: 50px">
          <font size="5" color="white">系统内存使用情况</font>
        </div>
        <br />
        <div style="text-align: center; margin-top: 20px">
          <el-progress type="circle" :percentage="this.memCount"></el-progress>
        </div>
      </div>
    </div>
    <div class="box-motivational">

      <div class="box-text">
          <div style="font-size: 30px; font-weight: bold;font-family: 华文行楷; color: white" >----{{showWisdom}}----</div>
      </div>
    </div>


  </div>

</template>

<script>
import echarts from 'echarts'
export default {
  name: '',
  data() {
    return {
      charts: '',
      /*  opinion: ["1", "3", "3", "4", "5"],*/
      opinionData: ["30", "22", "43", "44", "55","32","45"],
      pearlsOfWisdom:["最灵繁的人也看不见自己的背脊",
                      "与肝胆人共事，无字句处读书",
                      "不管黑夜有多长，天亮总会到来",
                      "勇气通往天堂，怯懦通往地狱",
                      "土扶可城墙，积德为厚地",
                      "我们唯一不会改正的缺点是软弱",
                      "天行健，君子以自强不息",
                      "哀莫大于心死，愁莫大于无志",
                      "山高路遥不足惧，最怕贪图安逸心",
                      "有智者立长志，无志者长立志"],
      showWisdom:"",
      userCount:"",
      musicCount:"",
      tvCount:"",
      memCount:0,
      weatherInfo:[],
      movieCount:""

    }
  },
  methods: {
    drawLine(id) {
      this.showWisdom = this.pearlsOfWisdom[Math.floor(Math.random()*10)];
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['近七日访问量'],
          textStyle:{//图例文字的样式
            color:'#ccc',
            fontSize:20
          }
        },

        grid: {
          left: '3%',
          right: '8%',
          bottom: '6%',
          containLabel: true
        },

        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ["周一","周二","周三","周四","周五","周六","周日"],
          axisLine:{
            lineStyle:{
              color:'#fff',
              width:2,//这里是为了突出显示加上的
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLine:{
            lineStyle:{
              color:'#fff',
              width:2,//这里是为了突出显示加上的
            }
          }

        },

        series: [{
          name: '近七日访问量',
          type: 'line',
          stack: '总量',
          data: this.opinionData
        }]
      })
    }
  },
  //调用
  mounted() {

    this.$nextTick(function() {
      this.drawLine('main')
    })

    this.$axios.get("/admin/user-count").then(
        (response) => {
          this.userCount = response.data.data;
        },
        (response) => {
          console.log("error");
        }
    );

    this.$axios.get("/admin/tv-count").then(
        (response) => {
          this.tvCount = response.data.data;
        },
        (response) => {
          console.log("error");
        }
    );

    this.$axios.get("/admin/music-count").then(
        (response) => {
          this.musicCount = response.data.data;
        },
        (response) => {
          console.log("error");
        }
    );

    this.$axios.get("/admin/movie-count").then(
        (response) => {
          this.movieCount = response.data.data;
        },
        (response) => {
          console.log("error");
        }
    );

    this.$axios.get("/admin/memInfo").then(
        (response) => {
          this.memCount = response.data.data;
        },
        (response) => {
          console.log("error");
        }
    );
    this.weatherInfo = [];
    this.$axios.get("/weather_mini"+"?city=无锡").then(

        (response) => {
          this.weatherInfo = response.data.data.forecast;
          console.log("weatherInfo is ====>" + this.weatherInfo)
        },
        (response) => {
          console.log("error");
        }
    );
  }
}

</script>

<style >
body {
  overflow-x:hidden;
  overflow-y:hidden;
}

.index-top-info {
  margin-top: 15px;
}

.card-video {
  background-color: #682413;
}

.card-music {
  background-color: #6b480a;
}

.card-user {
  background-color: #11595e;
}
.card-access {
  background-color: #0d3a4d;
}

.box-card {
  margin-top: 50px;
  width: 285px;
}

.box-chart {
  margin-top: 60px;
  margin-left: 30px;
}

.box-motivational {
  position: relative;
  margin-top: 40px;
  clear: both;
  width: 100%;
  height: 150px;
}
.box-text {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

.card-content {
  height: 80px;
  line-height: 60px;
  text-align: center;
}

.box-chart-left {
  position: relative;
  background-color:#666666;
  border-radius: 10px;
  width: 820px;
  height: 320px;
  top: 0;
  left: 0;
  float: left;
}

.box-main {
  position: absolute;
  width: 100%;
  height: 80%;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

.box-chart-right {
  border-radius: 10px;
  background: #333366;
  width: 400px;
  height: 320px;
  top: 0;
  right: 0;
  margin-left: 65px;
  float: left;
}
</style>