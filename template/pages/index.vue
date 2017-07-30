<template>
  <div class="container-fluid">
    <div class="row">
      <section class="brand-nav">
        <div class="container">
          <div class="row">
            <div class="col-xs-12 col-md-10 col-md-offset-1">
              <a href="/">
                <img class="logo" src="/share-logo.png">
              </a>
              <h4>Assign</h4>
              <h5>Help jouw medestudenten</h5>
            </div>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      post: {
        user: {
          name: 'Name'
        }
      }
    }
  },
  head () {
    return {
      title: 'Home',
      titleTemplate: '%s — Assign',
      meta: [
        { name: 'language', content: 'nl_NL' },
        { name: 'description', content: 'Home — Assign' },
        { property: 'og:title', content: 'Home — Assign' },
        { property: 'og:site_name', content: 'assignapp.co' },
        { property: 'og:description', content: 'Assign app om medestudenten te helpen.' },
        { property: 'og:url', content: 'http://84.26.134.115:8080/assign/' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image', content: '/share-image.jpg' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: 'Home — Assign' },
        { name: 'twitter:site_name', content: 'Home — Assign' },
        { name: 'twitter:description', content: 'Home — Assign' },
        { name: 'twitter:url', content: 'http://84.26.134.115:8080/assign/' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'twitter:domain', content: 'assignapp.co' },
        { name: 'twitter:image', content: '/share-image.jpg' },
        { name: 'twitter:image:alt', content: 'Help jouw medestudent' },
        { name: 'twitter:site', content: '@assignappco' },
        { name: 'twitter:creator', content: '@assignappco' }
      ]
    }
  }
}
</script>
