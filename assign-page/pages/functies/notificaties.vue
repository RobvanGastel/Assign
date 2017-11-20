<template>
  <div class="container-fluid">
    <navigation></navigation>
    <masthead-sub :title="title" :description="description" :illustratie="illustratie" :firstbtn="firstbtn" :firstbtnlnk="firstbtnlnk" :firstbtntrgt="firstbtntrgt" :secondbtn="secondbtn" :secondbtnlnk="secondbtnlnk" :secondbtntrgt="secondbtntrgt"></masthead-sub>
    <content-notificaties></content-notificaties>
    <footer-page></footer-page>
  </div>
</template>

<script>
import Navigation from '../../components/Navigation'
import MastheadSub from '../../components/mast/MastheadSub'
import ContentNotificaties from '../../components/content/ContentNotificaties'
import FooterPage from '../../components/FooterPage'
export default {
  components: {
    Navigation,
    MastheadSub,
    ContentNotificaties,
    FooterPage
  },
  data: () => ({
    title: 'Notificaties',
    description: 'Ontvang meldingen wanneer iemand jou wilt helpen of iemand jouw hulp goed kan gebruiken.',
    illustratie: 'notification',
    firstbtn: '',
    firstbtnlnk: '',
    firstbtntrgt: '',
    secondbtn: '',
    secondbtnlnk: '',
    secondbtntrgt: ''
  }),
  head: () => ({
    title: 'Notificaties',
    titleTemplate: 'Assign – %s',
    meta: [
      { name: 'language', content: 'nl_NL' },
      { name: 'description', content: 'Krijg notificaties wanneer iemand jou wilt helpen.' },
      { property: 'og:title', content: 'Assign – Notificaties' },
      { property: 'og:site_name', content: 'assignapp.nl' },
      { property: 'og:description', content: 'Krijg notificaties wanneer iemand jou wilt helpen.' },
      { property: 'og:url', content: 'https://assignapp.nl' },
      { property: 'og:locale', content: 'nl_NL' },
      { property: 'og:type', content: 'website' },
      { property: 'og:image:type', content: 'image/jpg' },
      { property: 'og:image', content: 'https://assignapp.nl/share-image.jpg' },
      { property: 'og:image:width', content: '1024' },
      { property: 'og:image:height', content: '512' },
      { name: 'twitter:title', content: 'Assign – Notificaties' },
      { name: 'twitter:site_name', content: 'Assign – Notificaties' },
      { name: 'twitter:description', content: 'Krijg notificaties wanneer iemand jou wilt helpen.' },
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
