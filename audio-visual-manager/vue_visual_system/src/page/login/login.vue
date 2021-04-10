<template>
  <div class="admin-login-box">
    <div class="admin-login-header-box">
      <div class="admin-login-header-center center">
        <div class="admin-login-logo">光子影音后台系统|登录</div>
      </div>
    </div>

    <div class="admin-login-center-box">
      <div class="login-center-box center">
        <el-row :gutter="20">
          <el-col :span="11">
            <el-form label-position="right" label-width="80px">
              <el-form-item label="账号" required>
                <el-input
                  v-model="User.name"
                  placeholder="用户名"
                ></el-input>
              </el-form-item>
              <el-form-item label="密码" required>
                <el-input
                  v-model="User.password"
                  placeholder="密码"
                  type="password"
                ></el-input>
              </el-form-item>
              <el-form-item label="验证码" required>
                <el-input
                  v-model="loginInfo.verifyCode"
                  placeholder="请输入验证码"
                  class="captcha-code-input-box"
                ></el-input>
                <img
                  :src="captchaPath"
                  @click="updataVerifyCode"
                  class="captcha_code"
                />
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="doLogin" size="small">
                  登 录
                </el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      User: {
        name: "",
        password: "",
      },

      loginInfo: {
        verifyCode: "",
        captcha_key: "",
      },
      captchaPath: "",
    };
  },

  methods: {
    toastError(msg) {
      this.$message({
        message: msg,
        center: true,
        type: "error",
      });
    },
    keyDown(e) {
      //如果是回车则执行登录方法
      if (e.keyCode == 13) {
        this.doLogin();
      }
    },

    doLogin() {
      if (this.User.name === "") {
        this.toastError("账号不能为空");
        return;
      }
      if (this.User.password === "") {
        this.toastError("密码不能为空");
        return;
      }
      if (this.loginInfo.verifyCode === "") {
        this.toastError("验证码不能为空");
        return;
      }
      this.$axios({
        method: "post",
        url:
          "/user/" +
          this.loginInfo.verifyCode +
          "/" +
          this.loginInfo.captcha_key,
        data: this.User,
      }).then((result) => {
        console.log("result =>" + result.data);
        let data = result.data;
        if (data.code === 20000) {
          this.$message({
            message: data.message,
            center: true,
            type: "success",
          });
          this.$router.push("/index");
        } else {
          this.updataVerifyCode();
          this.toastError(data.message);
        }
      });
    },
    updataVerifyCode() {
      this.captchaPath =
        "/user/captcha?captchaKey=" +
        this.loginInfo.captcha_key +
        "&random=" +
        Date.parse(new Date());
    },
  },
  mounted() {
    this.loginInfo.captcha_key = Date.parse(new Date());
    this.updataVerifyCode();

    window.addEventListener("keydown", this.keyDown);
  },
  destroyed() {
    window.removeEventListener("keydown", this.keyDown, false);
  }
};
</script>

<style >
.captcha_code {
  cursor: pointer;
  width: 100px;
  height: 40px;
  vertical-align: middle;
  margin-left: 10px;
  border: solid 1px #e6e6e6;
}

.admin-login-header-box {
  width: 100%;
  height: 46px;
  background:  #262d47;
  margin-bottom: 20px;
}

.center {
  margin: 0 auto;
  width: 1140px;
}

.admin-login-header-center {
  line-height: 46px;
}

.login-center-box .el-input {
  width: 70%;
}

.login-center-box {
  border-radius: 4px;
  width: 1100px;
  padding: 20px;
  background: #ffffff;
  box-shadow: 0 1px 10px #afafaf;
}

.admin-login-logo {
  color: #ffffff;
  font-size: 20px;
  font-weight: 600;
}

.login-cneter-box .el-input inner {
  border: solid 1px #e6e6e6;
  height: 42px;
  border-radius: 0;
}

.login-center-box .el-form-item label {
  background: #fbfbfb;
  border-left: solid 1px #e6e6e6;
  border-top: solid 1px #e6e6e6;
  border-bottom: solid 1px #e6e6e6;
  text-align: center;
}
</style>