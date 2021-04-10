module.exports = {
  publicPath: '/',
  devServer: {
    proxy: {
      '/user': {
        target: 'http://192.168.1.101:2020',
        changeOrigin: true,
        disableHostCheck: true
      },
      '/root': {
        target: 'http://192.168.1.101:2020',
        changeOrigin: true,
        disableHostCheck: true
      },
      '/admin': {
        target: 'http://192.168.1.101:2020',
        changeOrigin: true,
        disableHostCheck: true
      }
    }
  }
}