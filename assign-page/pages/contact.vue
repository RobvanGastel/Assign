<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub :title="title" :description="description" :illustratie="illustratie" :firstbtn="firstbtn" :firstbtnlnk="firstbtnlnk" :firstbtntrgt="firstbtntrgt" :secondbtn="secondbtn" :secondbtnlnk="secondbtnlnk" :secondbtntrgt="secondbtntrgt"></masthead-sub>
    <banner></banner>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Navigation from '../components/Navigation'
import MastheadSub from '../components/MastheadSub'
import Banner from '../components/Banner'
import FooterPage from '../components/FooterPage'
export default {
  components: {
    Navigation,
    MastheadSub,
    Banner,
    FooterPage
  },
  data: () => ({
    title: 'Kom met ons in contact',
    description: 'Heb je vragen en/of suggesties? Laat het ons dan vooral weten! Alle vragen zijn welkom.',
    illustratie: 'contact',
    firstbtn: 'Bekijk ons Twitter',
    firstbtnlnk: 'https://twitter.com/assignappnl',
    firstbtntrgt: '_blank',
    secondbtn: 'Mail ons',
    secondbtnlnk: 'mailto:assignapp%2edev%40gmail%2ecom',
    secondbtntrgt: ''
  }),
  head: () => ({
    title: 'Contact',
    titleTemplate: 'Assign – %s',
    meta: [
      { name: 'language', content: 'nl_NL' },
      { name: 'description', content: 'Heb je vragen en/of suggesties? Laat het ons dan vooral weten! Alle vragen zijn welkom.' },
      { property: 'og:title', content: 'Assign – Contact' },
      { property: 'og:site_name', content: 'assignapp.nl' },
      { property: 'og:description', content: 'Heb je vragen en/of suggesties? Laat het ons dan vooral weten! Alle vragen zijn welkom.' },
      { property: 'og:url', content: 'https://assignapp.nl' },
      { property: 'og:locale', content: 'nl_NL' },
      { property: 'og:type', content: 'website' },
      { property: 'og:image:type', content: 'image/jpg' },
      { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
      { property: 'og:image:width', content: '1024' },
      { property: 'og:image:height', content: '512' },
      { name: 'twitter:title', content: 'Assign – Kom met ons in contact' },
      { name: 'twitter:site_name', content: 'Assign – Kom met ons in contact' },
      { name: 'twitter:description', content: 'Heb je vragen en/of suggesties? Laat het ons dan vooral weten! Alle vragen zijn welkom.' },
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
