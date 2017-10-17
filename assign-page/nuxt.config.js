module.exports = {

  /*
  ** Set environment viarbles
  */
  env: {
    baseUrl: 'https://api.assignapp.nl/v0.1' //https://api.assignapp.nl
  },

  /*
  ** Headers of the page
  */
  head: {
    title: 'Assign – Het middel voor een communicatieve leeromgeving',
    htmlAttrs: {
      lang: 'nl',
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: 'Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.' }
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
  ** Add plugins and configure ssr
  */
  plugins: [
    { src: '~/plugins/clipboard', ssr: false}, 
    { src: '~plugins/ga.js', ssr: true }],
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
