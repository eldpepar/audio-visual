import axios from 'axios'

axios.defaults.withCredentials = true;
axios.defaults.timeout = 10000;
axios.defaults.headers.post['Content-Type'] = 'application/x-www=form-urlencoded'

export default {
    // get请求
    requestGet(url, params = {}) {
        this.setUpCookie()
        return new Promise((resolve, reject) => {
            axios.get(url, params).then(res => {
                resolve(res.data)
            }).catch(error => {
                reject(error)
            })
        })
    },
    // post请求
    requestPost(url, params = {}) {
        this.setUpCookie()
        return new Promise((resolve, reject) => {
            axios.post(url, params).then(res => {
                resolve(res.data)
            }).catch(error => {
                reject(error)
            })
        })
    },
    // delete请求
    requestDelete(url, params = {}) {
        this.setUpCookie()
        return new Promise((resolve, reject) => {
            axios.delete(url, params).then(res => {
                resolve(res.data)
            }).catch(error => {
                reject(error)
            })
        })
    },
    // put请求
    requestPut(url, params = {}) {
        this.setUpCookie()
        return new Promise((resolve, reject) => {
            axios.put(url, params).then(res => {
                resolve(res.data)
            }).catch(error => {
                reject(error)
            })
        })
    }

}
