<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub :title="title" :description="description" :illustratie="illustratie" :firstbtn="firstbtn" :firstbtnlnk="firstbtnlnk" :firstbtntrgt="firstbtntrgt" :secondbtn="secondbtn" :secondbtnlnk="secondbtnlnk" :secondbtntrgt="secondbtntrgt"></masthead-sub>
    <content-delen></content-delen>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Navigation from '../../components/Navigation'
import MastheadSub from '../../components/mast/MastheadSub'
import ContentDelen from '../../components/content/ContentDelen'
import FooterPage from '../../components/FooterPage'
export default {
  components: {
    Navigation,
    MastheadSub,
    ContentDelen,
    FooterPage
  },
  data: () => ({
    title: 'Het delen van vragen',
    description: 'Deel vragen van jou of een ander om sneller geholpen te worden of om in een groepsgesprek hulp te vragen.',
    illustratie: 'contact',
    firstbtn: '',
    firstbtnlnk: '',
    firstbtntrgt: '',
    secondbtn: '',
    secondbtnlnk: '',
    secondbtntrgt: ''
  }),
  head: () => ({
    title: 'Het delen van vragen',
    titleTemplate: 'Assign – %s',
    meta: [
      { name: 'language', content: 'nl_NL' },
      { name: 'description', content: 'Deel vragen van jou of een ander om sneller geholpen te worden of om in een groepsgesprek hulp te vragen.' },
      { property: 'og:title', content: 'Assign – Het delen van vragen' },
      { property: 'og:site_name', content: 'assignapp.nl' },
      { property: 'og:description', content: 'Deel vragen van jou of een ander om sneller geholpen te worden of om in een groepsgesprek hulp te vragen.' },
      { property: 'og:url', content: 'https://assignapp.nl' },
      { property: 'og:locale', content: 'nl_NL' },
      { property: 'og:type', content: 'website' },
      { property: 'og:image:type', content: 'image/jpg' },
      { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
      { property: 'og:image:width', content: '1024' },
      { property: 'og:image:height', content: '512' },
      { name: 'twitter:title', content: 'Assign – Het delen van vragen' },
      { name: 'twitter:site_name', content: 'Assign – Het delen van vragen' },
      { name: 'twitter:description', content: 'Deel vragen van jou of een ander om sneller geholpen te worden of om in een groepsgesprek hulp te vragen.' },
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
