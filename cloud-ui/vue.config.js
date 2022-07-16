module.exports = {
  publicPath: "/",
  outputDir: 'dist',
  assetsDir: 'static',
  productionSourceMap: false,
  devServer: {
    host: '127.0.0.1',
    port: 8081,
    proxy: {
      "/api": {
        target: `http://localhost:8081`,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/'
        }
      }
    },
  },
}
