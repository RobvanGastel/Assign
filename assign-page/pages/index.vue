<template>
  <div class="container-fluid">
    <masthead></masthead>
    <steps></steps>
    <features></features>
    <!-- <download></download> -->
    <banner></banner>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Masthead from '../components/mast/Masthead'
import Steps from '../components/Steps'
import Features from '../components/Features'
// import Download from '../components/Download'
import Banner from '../components/Banner'
import FooterPage from '../components/FooterPage'
export default {
  components: {
    Masthead,
    Steps,
    Features,
    // Download,
    Banner,
    FooterPage
  },
  data: () => ({
    showModal: false
  }),
  head: () => ({
    title: 'Assign maakt samen leren leuk en makkelijk',
    titleTemplate: 'Assign – %s',
    meta: [
      { name: 'language', content: 'nl_NL' },
      { name: 'description', content: 'Elke leerling heeft unieke eigenschappen en kennis die een ander niet heeft. Met Assign brengen wij leerlingen bij elkaar om elkaar te helpen en van elkaar te leren.' },
      { property: 'og:title', content: 'Assign – Assign maakt samen leren leuk en makkelijk' },
      { property: 'og:site_name', content: 'assignapp.nl' },
      { property: 'og:description', content: 'Elke leerling heeft unieke eigenschappen en kennis die een ander niet heeft. Met Assign brengen wij leerlingen bij elkaar om elkaar te helpen en van elkaar te leren.' },
      { property: 'og:url', content: 'https://assignapp.nl' },
      { property: 'og:locale', content: 'nl_NL' },
      { property: 'og:type', content: 'website' },
      { property: 'og:image:type', content: 'image/jpg' },
      { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
      { property: 'og:image:width', content: '1024' },
      { property: 'og:image:height', content: '512' },
      { name: 'twitter:title', content: 'Assign – Assign maakt samen leren leuk en makkelijk' },
      { name: 'twitter:site_name', content: 'Assign – Assign maakt samen leren leuk en makkelijk' },
      { name: 'twitter:description', content: 'Elke leerling heeft unieke eigenschappen en kennis die een ander niet heeft. Met Assign brengen wij leerlingen bij elkaar om elkaar te helpen en van elkaar te leren.' },
      { name: 'twitter:url', content: 'https://assignapp.nl' },
      { name: 'twitter:card', content: 'summary' },
      { name: 'twitter:domain', content: 'assignapp.nl' },
      { name: 'twitter:image', content: 'https://assignapp.nl/share-logo.png' },
      { name: 'twitter:image:alt', content: 'Assign maakt samen leren leuk en makkelijk' },
      { name: 'twitter:site', content: '@assignappnl' },
      { name: 'twitter:creator', content: '@assignappnl' }
    ],
    link: [
      { rel: 'shortcut icon', type: 'image/png', href: '/favicon.png' }
    ]
  })
}
</script>
