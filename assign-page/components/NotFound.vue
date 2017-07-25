<template>
  <section class="post">
    <p>Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.</p>
  </section>
</template>

<script>

export default {
  props: ['post'],
  head () {
    return {
      title: 'Deze assignment is niet gevonden',
      titleTemplate: '%s — Assign',
      meta: [
        { name: 'language', content: 'nl_NL' },
        { name: 'description', content: 'Deze assignment is niet gevonden — Assign' },
        { property: 'og:title', content: 'Deze assignment is niet gevonden — Assign' },
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
