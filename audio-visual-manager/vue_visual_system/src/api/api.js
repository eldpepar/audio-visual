import http from './http'


//登录
export const doLogin = (verifyCode,captcha_key,sobUser) => {
    return http.requestPost('/user/' + verifyCode+'/' + captcha_key,sobUser);
};


//标签列表
export const listLable = () => {
    return http.requestGet('/test/label/list/1/10');
};
