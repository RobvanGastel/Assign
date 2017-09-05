<template>
  <section class="post">
    <img class="faceholder" src="../assets/img/faceholder.jpg" alt="Assign profile picture placeholder">
    <div class="profile" :style="{ 'background-image': 'url(' + 'https://api.assignapp.nl/v0.1/img/' + this.post.profileImage + ')' }" ></div>
    <h2>{{ post.name }}</h2>
    <h3>{{ post.dateCreated }}</h3>
    <h1>{{ post.title }}</h1>
    <p>{{ post.description }}</p>
    <!-- <h3 class="text-center">3 aanbiedingen  •  Nog 3 uur beschikbaar</h3> -->
  </section>
</template>

<script>
export default {
  props: ['post'],
  head () {
    return {
      title: this.post.title,
      titleTemplate: 'Assign – %s',
      meta: [
        { name: 'description', content: this.post.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'language', content: 'nl_NL' },
        { property: 'og:title', content: 'Assign – ' + this.post.title },
        { property: 'og:site_name', content: 'Assign – ' + this.post.title },
        { property: 'og:description', content: this.post.name + ' vraagt om hulp bij ' + this.post.title },
        { property: 'og:url', content: 'https://assignapp.nl' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image', content: '/share-image.jpg' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: 'Assign – ' + this.post.title },
        { name: 'twitter:site_name', content: 'Assign – ' + this.post.title },
        { name: 'twitter:description', content: this.post.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'twitter:url', content: 'https://assignapp.nl' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'twitter:domain', content: 'assignapp.nl' },
        { name: 'twitter:image', content: '/share-image.jpg' },
        { name: 'twitter:image:alt', content: 'Het middel voor een communicatieve leeromgeving' },
        { name: 'twitter:site', content: '@assignappnl' },
        { name: 'twitter:creator', content: '@assignappnl' }
      ]
    }
  }
}
</script>
