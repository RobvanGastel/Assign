<template>
  <div class="container-fluid">
    <masthead-school></masthead-school>
    <steps-school></steps-school>
    <contact></contact>
    <banner></banner>
    <footer-page></footer-page>
  </div>
</template>

<script>
import MastheadSchool from '../components/mast/MastheadSchool'
import StepsSchool from '../components/StepsSchool'
import Contact from '../components/Contact'
import Banner from '../components/Banner'
import FooterPage from '../components/FooterPage'
// import headway from '../assets/js/headway'
export default {
  components: {
    MastheadSchool,
    StepsSchool,
    Contact,
    Banner,
    FooterPage
  },
  data: () => ({
    showModal: false
    // ,
    // config: {
    //   selector: '.headway-widget',
    //   account: 'J5Kp6x'
    // }
  }),
  // created () {
  //   headway.init(this.config)
  // },
  head: () => ({
    title: 'Het middel voor een communicatieve leeromgeving',
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
  })
}
</script>
