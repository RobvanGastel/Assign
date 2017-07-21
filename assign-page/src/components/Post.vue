<template>
  <section class="post">
    <img class="faceholder" src="../assets/img/faceholder.jpg">
    <div class="profile" :style="{ 'background-image': 'url(' + 'img/' + this.post.user.profileImage + ')' }" ></div>
    <h2>{{ post.user.name }}</h2>
    <h3>{{ post.dateCreated }}</h3>
    <h1>{{ post.title }}</h1>
    <p>{{ post.description }}</p>
    <!-- <h3 class="text-center">3 aanbiedingen  •  Nog 3 uur beschikbaar</h3> -->
  </section>
</template>

<script>

export default {
  props: ['post'],
  metaInfo () {
    return {
      title: this.post.title,
      titleTemplate: '%s — Assign',
      meta: [
        { name: 'description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'og:title', content: this.post.title + '— Assign' },
        { name: 'og:site_name', content: this.post.title + '— Assign' },
        { name: 'og:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'og:url', content: 'http://84.26.134.115:8080/assign/' },
        { name: 'og:locale', content: 'nl_NL' },
        { name: 'og:type', content: 'website' },
        { name: 'og:image', content: '/static/share-image.jpg' },
        { name: 'og:image:type', content: 'image/jpg' },
        { name: 'og:image:width', content: '1024' },
        { name: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: this.post.title + '— Assign' },
        { name: 'twitter:site_name', content: this.post.title + '— Assign' },
        { name: 'twitter:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'twitter:url', content: 'http://84.26.134.115:8080/assign/' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'twitter:domain', content: 'assignapp.co' },
        { name: 'twitter:image', content: '/static/share-image.jpg' },
        { name: 'twitter:image:alt', content: 'Help jouw medestudent' },
        { name: 'twitter:site', content: '@assignappco' },
        { name: 'twitter:creator', content: '@assignappco' }
      ],
      link: [
        { rel: 'shortcut icon', href: '/static/favicon.png' }
      ]
    }
  }
}
</script>
