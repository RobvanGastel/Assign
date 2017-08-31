module.exports = {

  /*
  ** Set environment viarbles
  */
  env: {
    baseUrl: 'http://localhost:9080/v0.1' //https://api.assignapp.nl
  },

  /*
  ** Headers of the page
  */
  head: {
    title: 'Assign',
    htmlAttrs: {
      lang: 'nl',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Help jouw medestudenten' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  
  /*
  ** Global CSS
  */
  css: [
    '~assets/css/bootstrap.min.css',
    '~assets/css/style.css'
  ],
  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#FF7F28' },
  /*
  ** Add axios globally
  */
  build: {
    vendor: ['axios'],
    /*
    ** Run ESLINT on save
    */
    extend (config, ctx) {
      if (ctx.isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}
