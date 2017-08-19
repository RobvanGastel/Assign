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
        { name: 'description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { property: 'og:title', content: 'Deze assignment is niet gevonden — Assign' },
        { property: 'og:site_name', content: 'assignapp.nl' },
        { property: 'og:description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { property: 'og:url', content: 'https://assignapp.nl/' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image', content: '/share-image.jpg' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: 'Deze assignment is niet gevonden — Assign' },
        { name: 'twitter:site_name', content: 'Deze assignment is niet gevonden — Assign' },
        { name: 'twitter:description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { name: 'twitter:url', content: 'https://assignapp.nl/' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'twitter:domain', content: 'assignapp.nl' },
        { name: 'twitter:image', content: '/share-image.jpg' },
        { name: 'twitter:image:alt', content: 'Help jouw medestudent' },
        { name: 'twitter:site', content: '@assignappnl' },
        { name: 'twitter:creator', content: '@assignappnl' }
      ]
    }
  }
}
</script>
