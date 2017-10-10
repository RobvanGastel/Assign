<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub :title="title" :description="description" :illustratie="illustratie" :firstbtn="firstbtn" :firstbtnlnk="firstbtnlnk" :firstbtntrgt="firstbtntrgt" :secondbtn="secondbtn" :secondbtnlnk="secondbtnlnk" :secondbtntrgt="secondbtntrgt"></masthead-sub>
    <banner></banner>
    <footer-page></footer-page>
  </div>
</template>


<script>
import Navigation from '../components/Navigation.vue'
import MastheadSub from '../components/MastheadSub.vue'
import Banner from '../components/Banner.vue'
import FooterPage from '../components/FooterPage.vue'
export default {
  components: {
    Navigation,
    MastheadSub,
    Banner,
    FooterPage
  },
  data () {
    return {
      title: 'Deze pagina is niet gevonden.',
      description: 'Kom met ons in contact zodat wij dit kunnen oplossen.',
      illustratie: '404',
      firstbtn: 'Ga naar contact',
      firstbtnlnk: '/contact',
      firstbtntrgt: '',
      secondbtn: '',
      secondbtnlnk: '',
      secondbtntrgt: ''
    }
  },
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
