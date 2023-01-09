# 基于Vue及Springboot的影音播放软件
### 技术栈
![](https://img.shields.io/badge/%E5%89%8D%E7%AB%AF%E6%A1%86%E6%9E%B6-Vue.js-brightgreen)
![](https://img.shields.io/badge/%E5%90%8E%E7%AB%AF%E6%A1%86%E6%9E%B6-SpringBoot-orange)
![](https://img.shields.io/badge/%E7%A7%BB%E5%8A%A8%E7%AB%AF-Android-yellow)

### 详细介绍
项目从完成到最后上交也没有写一个完整的项目介绍，因为之前录制过一个项目视频的缘故，所以在今年基于之前完成的项目视频在[阳光沙滩](https://www.sunofbeach.net)平台写完了项目的详细介绍。之所以在阳光沙滩来发布项目的介绍，是因为当时的毕业设计大量的参考了阳光沙滩拉大锯老师的博客系统。项目介绍最详细的地方其实在我的毕业论文里，但论文涉及到太多隐私信息，所以并不能公布。

项目介绍: https://www.sunofbeach.net/a/1555015351274573826


### 项目运行指南

1.在数据库中建立一个名为**audio-visual**的数据库，数据库的**字符集**选择为**utf8mb4**，**排列规则**选择为**utf8mb4_general_ci** ，并且运行**audio-visual.sql**文件，**导入数据表**


2.在**audio-visual-server**中的**application.yml**配置**mysql数据库地址**，数据库的**账户密码**，以及**redis中的地址**和**密码**
3.启动后台项目audio-visual-server

4.如果没有安装vue的运行环境需要安装，使用**npm install**安装a**udio-visual-manager**项目需要的**依赖**，安装完成后**运行npm run dev**

5.运行完成后，使用浏览器访问http://localhost:8080/进入管理系统


6.在**tb_user**表中插入以下数据，保存后可以使用账号为**admin**，密码为**123456**的账号登录

```
id:825505787236319232
name:admin
password：$10$59LBEWYkwOT5mixzfulVrOrvahdgiJ6kt5C8soA4JXT0pgFequtKG
role:role_root
avatar：图片的地址
email：管理员的邮箱
state：1
```

7.在后端项目中配置了邮箱发送的工具类EmailSender，但项目中没有使用，可以自行申请邮箱服务，加入到项目中

8.在**audio-visul-android**项目中，找到**utils**包下的**Constants**，**配置URL**为后端部署的地址

9.项目中需要视频解析地址，请自行添加。

