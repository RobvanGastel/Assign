<template>
  <section class="post">
    <p>Deze pagina of assignment is niet gevonden, de assignment is mogelijk verwijderd door de eigenaar.</p>
  </section>
</template>

<script>
export default {
  props: ['post'],
  head () {
    return {
      title: 'Deze assignment is niet gevonden',
      titleTemplate: 'Assign – %s',
      meta: [
        { name: 'language', content: 'nl_NL' },
        { name: 'description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { property: 'og:title', content: 'Assign – Deze assignment is niet gevonden' },
        { property: 'og:site_name', content: 'assignapp.nl' },
        { property: 'og:description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { property: 'og:url', content: 'https://assignapp.nl/' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: 'Assign – Deze assignment is niet gevonden' },
        { name: 'twitter:site_name', content: 'Assign – Deze assignment is niet gevonden' },
        { name: 'twitter:description', content: 'Deze assignment is niet gevonden, het kan dat de assignment is verwijderd door de eigenaar.' },
        { name: 'twitter:url', content: 'https://assignapp.nl/' },
        { name: 'twitter:card', content: 'summary' },
        { name: 'twitter:domain', content: 'assignapp.nl' },
        { name: 'twitter:image', content: 'https://assignapp.nl/share-logo.png' },
        { name: 'twitter:image:alt', content: 'Het middel voor een communicatieve leeromgeving' },
        { name: 'twitter:site', content: '@assignappnl' },
        { name: 'twitter:creator', content: '@assignappnl' }
      ]
    }
  }
}
</script>
