module.exports = {
  "publicPath": "./",
  "outputDir": "dist",
  "assetsDir": "static",
  "indexPath": "index.html",
  "filenameHashing": true,
  "pages": {
    "index": {
      "entry": "src/main.js",
      "template": "public/index.html",
      "filename": "index.html",
      "title": "Index Page",
      "chunks": [
        "chunk-vendors",
        "chunk-common",
        "index"
      ]
    }
  },
  "lintOnSave": true,
  "runtimeCompiler": false,
  "transpileDependencies": [
    "vuetify"
  ],
  "productionSourceMap": true,
  "crossorigin": "",
  "integrity": false,
  "devServer": {
    "open": false,
    "host": "0.0.0.0",
    "port": 8848,
    "https": false,
    "hotOnly": false,
    "proxy": {
      "/api": {
        "target": "http://app.rmsdmedia.com",
        "changeOrigin": true,
        "secure": false,
        "pathRewrite": {
          "^/api": ""
        }
      },
      "/foo": {
        "target": "<other_url>"
      }
    }
  },
  "css": {
    "sourceMap": false,
    "loaderOptions": {
      "css": {},
      "postcss": {}
    },
    "modules": false
  },
  "parallel": true,
  "pwa": {},
  "pluginOptions": {}
}