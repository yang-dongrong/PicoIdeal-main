module.exports = {
  publicPath: "/",
  outputDir: 'dist',
  assetsDir: 'static',
  productionSourceMap: false,
  devServer: {
    host: '0.0.0.0',
    port: 8089,
    proxy: {
      "/api": {
        target: `http://127.0.0.1:8089`,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/'
        }
      }
    },
  },
}
