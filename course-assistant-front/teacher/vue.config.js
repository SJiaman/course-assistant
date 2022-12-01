'use strict'
const path = require('path')

function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  publicPath: './',
  outputDir: 'admin',
  assetsDir: 'static',
  lintOnSave: true,
  productionSourceMap: false,
  devServer: {
    open: true,
    host: 'localhost',
    port: 8002,
    https: false,
    hotOnly: false,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/' 
          //pathRewrite: {'^/api': '/'} 重写之后url为 http://192.168.1.16:8085/xxxx
          //pathRewrite: {'^/api': '/api'} 重写之后url为 http://192.168.1.16:8085/api/xxxx
        }
      }
    }
  },
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
      filename: 'index.html'
    }
  },
  chainWebpack (config) {
    // set svg-sprite-loader
    config.module
      .rule('svg')
      .exclude.add(resolve('src/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()
  }
}
