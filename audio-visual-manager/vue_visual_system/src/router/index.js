import VueRouter from 'vue-router'
import Vue from 'vue'

Vue.use(VueRouter)

// 登录页面
const login = () => import("@/page/login/login")

// 电影部分
const movieAddManager = () => import("@/page/movie/movie-add")
const movieList = () => import("@/page/movie/movie-list")
const movieDelete = () => import("@/page/movie/movie-delete")
const movieModifyManager = () => import("@/page/movie/movie-modify")

// 电视部分
const tvAddManager = () => import("@/page/tv/tv-add")
const tvList = () => import("@/page/tv/tv-list")
const tvDelete = () => import("@/page/tv/tv-delete")
const tvModifyManager = () => import("@/page/tv/tv-modify")

//  首页
const index = () => import("@/page/dashboard/index")

//  音乐
const musicAddManager = () => import("@/page/music/music-add")
const musicList = () => import("@/page/music/music-list")
const musicDelete = () => import("@/page/music/music-delete")
const musicModifyManager = () => import("@/page/music/music-modify")

//  用户
const userAdd = () => import("@/page/user/user-add")
const userSet = () => import("@/page/user/user-set")
const list = () => import("@/page/user/list")
const userInfoManager = () => import("@/page/user/user-info-manager")

//  布局
const baseView = () => import("@/layout/base-view")
const rightView = () => import("@/layout/right-content")

//  错误页面
const forbidden = () => import("@/page/errorPage/forbidden")

export const routes = [
    {
        path: '',
        component: baseView,
        redirect: '/index',
        children:[
            {
                path: "/index",
                hidden: false,
                name:'首页',
                icon:'el-icon-s-home',
                component:index
            },
            {
                path: '/movie',
                hidden: false,
                name:'电影',
                icon:'el-icon-video-play',
                component:rightView,
                children:[
                    {
                        path:'movie-modify-manager',
                        hidden: false,
                        name:'修改电影接口',
                        icon:'el-icon-edit',
                        component:movieModifyManager
                    },
                    {
                        path:'manager-movie-add',
                        hidden: false,
                        name:'添加电影接口',
                        icon:'el-icon-circle-plus-outline',
                        component:movieAddManager
                    },{
                        path:'movie-list',
                        hidden: false,
                        name:'电影接口列表',
                        icon:'el-icon-tickets',
                        component:movieList
                    },{
                        path:'movie-delete',
                        hidden: false,
                        name:'删除电影接口',
                        icon:'el-icon-delete',
                        component:movieDelete
                    }
                ]
            },
            {
                path: '/music',
                hidden: false,
                name:'音乐',
                icon:'el-icon-headset',
                component:rightView,
                children:[
                    {
                        path:'music-modify-manager',
                        hidden: false,
                        name:'修改音乐接口',
                        icon:'el-icon-edit',
                        component:musicModifyManager
                    },
                    {
                        path:'manager-music-add',
                        hidden: false,
                        name:'添加音乐接口',
                        icon:'el-icon-circle-plus-outline',
                        component:musicAddManager
                    },{
                        path:'music-list',
                        hidden: false,
                        name:'音乐接口列表',
                        icon:'el-icon-tickets',
                        component:musicList
                    },{
                        path:'music-delete',
                        hidden: false,
                        name:'删除音乐接口',
                        icon:'el-icon-delete',
                        component:musicDelete
                    }
                ]
            },
            {
                path: '/tv',
                hidden: false,
                name:'电视',
                icon:'el-icon-video-camera',
                component:rightView,
                children:[
                    {
                        path:'tv-modify-manager',
                        hidden: false,
                        name:'修改电视接口',
                        icon:'el-icon-edit',
                        component:tvModifyManager
                    },
                    {
                        path:'manager-tv-add',
                        hidden: false,
                        name:'添加电视接口',
                        icon:'el-icon-circle-plus-outline',
                        component:tvAddManager
                    },{
                        path:'tv-list',
                        hidden: false,
                        name:'电视接口列表',
                        icon:'el-icon-tickets',
                        component:tvList
                    },{
                        path:'tv-delete',
                        hidden: false,
                        name:'删除电视接口',
                        icon:'el-icon-delete',
                        component:tvDelete
                    }
                ]
            },
            {
                path: '/user',
                hidden: true,
                name:'用户',
                icon:'el-icon-user',
                component:rightView,
                children:[
                    {
                        path:'list',
                        hidden: false,
                        name:'用户列表',
                        icon:'el-icon-s-data',
                        component:list
                    },
                    {
                        path:'user-add',
                        hidden: false,
                        name:'添加用户',
                        icon:'el-icon-document-add',
                        component:userAdd
                    },{
                        path:'user-set',
                        hidden: false,
                        name:'用户管理',
                        icon:'el-icon-box',
                        component:userSet
                    },{
                        path:'user-info-manager',
                        hidden: false,
                        name:'用户信息',
                        icon:'el-icon-setting',
                        component:userInfoManager
                    }
                ]
            }
        ]
    },
    {
        path:'/login',
        component:login
    },
    {
        path: "/forbidden",
        component:forbidden
    }
]

export const async = [
    {
        path: '',
        component: baseView,
        redirect: '/index',
        children:[
            {
                path: "/index",
                hidden: false,
                name:'首页',
                icon:'el-icon-s-home',
                component:index
            },
            {
                path: '/movie',
                hidden: false,
                name:'电影',
                icon:'el-icon-video-play',
                component:rightView,
                children:[
                    {
                        path:'movie-modify-manager',
                        hidden: false,
                        name:'修改电影接口',
                        icon:'el-icon-edit',
                        component:movieModifyManager
                    },
                    {
                        path:'manager-movie-add',
                        hidden: false,
                        name:'添加电影接口',
                        icon:'el-icon-circle-plus-outline',
                        component:movieAddManager
                    },{
                        path:'movie-list',
                        hidden: false,
                        name:'电影接口列表',
                        icon:'el-icon-tickets',
                        component:movieList
                    },{
                        path:'movie-delete',
                        hidden: false,
                        name:'删除电影接口',
                        icon:'el-icon-delete',
                        component:movieDelete
                    }
                ]
            },
            {
                path: '/music',
                hidden: false,
                name:'音乐',
                icon:'el-icon-headset',
                component:rightView,
                children:[
                    {
                        path:'music-modify-manager',
                        hidden: false,
                        name:'修改音乐接口',
                        icon:'el-icon-edit',
                        component:musicModifyManager
                    },
                    {
                        path:'manager-music-add',
                        hidden: false,
                        name:'添加音乐接口',
                        icon:'el-icon-circle-plus-outline',
                        component:musicAddManager
                    },{
                        path:'music-list',
                        hidden: false,
                        name:'音乐接口列表',
                        icon:'el-icon-tickets',
                        component:musicList
                    },{
                        path:'music-delete',
                        hidden: false,
                        name:'删除音乐接口',
                        icon:'el-icon-delete',
                        component:musicDelete
                    }
                ]
            },
            {
                path: '/tv',
                hidden: false,
                name:'电视',
                icon:'el-icon-video-camera',
                component:rightView,
                children:[
                    {
                        path:'tv-modify-manager',
                        hidden: false,
                        name:'修改电视接口',
                        icon:'el-icon-edit',
                        component:tvModifyManager
                    },
                    {
                        path:'manager-tv-add',
                        hidden: false,
                        name:'添加电视接口',
                        icon:'el-icon-circle-plus-outline',
                        component:tvAddManager
                    },{
                        path:'tv-list',
                        hidden: false,
                        name:'电视接口列表',
                        icon:'el-icon-tickets',
                        component:tvList
                    },{
                        path:'tv-delete',
                        hidden: false,
                        name:'删除电视接口',
                        icon:'el-icon-delete',
                        component:tvDelete
                    }
                ]
            },
            {
                path: '/user',
                hidden: false,
                name:'用户',
                icon:'el-icon-user',
                component:rightView,
                children:[
                    {
                        path:'list',
                        hidden: false,
                        name:'用户列表',
                        icon:'el-icon-s-data',
                        component:list
                    },
                    {
                        path:'user-add',
                        hidden: false,
                        name:'添加用户',
                        icon:'el-icon-document-add',
                        component:userAdd
                    },{
                        path:'user-set',
                        hidden: false,
                        name:'用户管理',
                        icon:'el-icon-box',
                        component:userSet
                    },{
                        path:'user-info-manager',
                        hidden: false,
                        name:'用户信息',
                        icon:'el-icon-setting',
                        component:userInfoManager
                    }
                ]
            }
        ]
    },
    {
        path:'/login',
        component:login
    },
    {
        path: "/forbidden",
        component:forbidden
    }
]

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
    async,routes
})

export default router