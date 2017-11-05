<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub :title="title" :description="description" :illustratie="illustratie" :firstbtn="firstbtn" :firstbtnlnk="firstbtnlnk" :firstbtntrgt="firstbtntrgt" :secondbtn="secondbtn" :secondbtnlnk="secondbtnlnk" :secondbtntrgt="secondbtntrgt"></masthead-sub>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Navigation from '../../components/Navigation'
import MastheadSub from '../../components/mast/MastheadSub'
import FooterPage from '../../components/FooterPage'
export default {
  components: {
    Navigation,
    MastheadSub,
    FooterPage
  },
  data: () => ({
    title: 'Bevestig je e-mailadres',
    description: 'We moeten je e-mailadres bevestigen. Om het inschrijfproces te voltooien klik je op de link in de e-mail die we net naar je toe hebben gestuurd.',
    illustratie: 'mailbijnaklaar',
    firstbtn: 'Ga terug naar onze website',
    firstbtnlnk: '/',
    firstbtntrgt: '',
    secondbtn: '',
    secondbtnlnk: '',
    secondbtntrgt: ''
  }),
  head: () => ({
    title: 'Bevestig je e-mailadres',
    titleTemplate: 'Assign – %s',
    meta: [
      { name: 'language', content: 'nl_NL' },
      { name: 'description', content: 'We moeten je e-mailadres bevestigen. Om het inschrijfproces te voltooien klik je op de link in de e-mail die we net naar je toe hebben gestuurd.' },
      { property: 'og:title', content: 'Assign – Bevestig je e-mailadres' },
      { property: 'og:site_name', content: 'assignapp.nl' },
      { property: 'og:description', content: 'We moeten je e-mailadres bevestigen. Om het inschrijfproces te voltooien klik je op de link in de e-mail die we net naar je toe hebben gestuurd.' },
      { property: 'og:url', content: 'https://assignapp.nl' },
      { property: 'og:locale', content: 'nl_NL' },
      { property: 'og:type', content: 'website' },
      { property: 'og:image:type', content: 'image/jpg' },
      { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
      { property: 'og:image:width', content: '1024' },
      { property: 'og:image:height', content: '512' },
      { name: 'twitter:title', content: 'Assign – Bevestig je e-mailadres' },
      { name: 'twitter:site_name', content: 'Assign – Bevestig je e-mailadres' },
      { name: 'twitter:description', content: 'We moeten je e-mailadres bevestigen. Om het inschrijfproces te voltooien klik je op de link in de e-mail die we net naar je toe hebben gestuurd.' },
      { name: 'twitter:url', content: 'https://assignapp.nl' },
      { name: 'twitter:card', content: 'summary' },
      { name: 'twitter:domain', content: 'assignapp.nl' },
      { name: 'twitter:image', content: 'https://assignapp.nl/share-logo.png' },
      { name: 'twitter:image:alt', content: 'Het middel voor een communicatieve leeromgeving' },
      { name: 'twitter:site', content: '@assignappnl' },
      { name: 'twitter:creator', content: '@assignappnl' }
    ],
    link: [
      { rel: 'shortcut icon', type: 'image/png', href: '/favicon.png' }
    ]
  })
}
</script>
