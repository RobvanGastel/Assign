<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub-contact title="Communicatie manieren" description="Wij beantwoorden graag jouw vragen, je kunt op verschillende manieren met ons in contact komen."></masthead-sub-contact>
    <functie-content></functie-content>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Navigation from '../../components/Navigation.vue'
import MastheadSubContact from '../../components/MastheadSubContact.vue'
import FunctieContent from '../../components/FunctieContent.vue'
import FooterPage from '../../components/FooterPage.vue'
export default {
  components: {
    Navigation,
    MastheadSubContact,
    FunctieContent,
    FooterPage
  },
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
      title: 'Contact',
      titleTemplate: 'Assign – %s',
      meta: [
        { name: 'language', content: 'nl_NL' },
        { name: 'description', content: 'Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.' },
        { property: 'og:title', content: 'Assign – Het middel voor een communicatieve leeromgeving' },
        { property: 'og:site_name', content: 'assignapp.nl' },
        { property: 'og:description', content: 'Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.' },
        { property: 'og:url', content: 'https://assignapp.nl' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: 'Assign – Het middel voor een communicatieve leeromgeving' },
        { name: 'twitter:site_name', content: 'Assign – Het middel voor een communicatieve leeromgeving' },
        { name: 'twitter:description', content: 'Assign helpt bij het creëren van een communicatieve leeromgeving waar studenten zelfstandig en samenwerkend leren doormiddel van een iOS App.' },
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
    }
  }
}
</script>
