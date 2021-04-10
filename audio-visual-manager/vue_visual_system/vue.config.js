module.exports = {
  publicPath: '/',
  devServer: {
    proxy: {
      '/user': {
        target: 'http://localhost:2020',
        changeOrigin: true,
        disableHostCheck: true
      },
      '/root': {
        target: 'http://localhost:2020',
        changeOrigin: true,
        disableHostCheck: true
      },
      '/admin': {
        target: 'http://localhost:2020',
        changeOrigin: true,
        disableHostCheck: true
      }
    }
  }
}