<template>
  <section class="post">
    <img class="faceholder" src="../assets/img/faceholder.jpg">
    <div class="profile" :style="{ 'background-image': 'url(' + this.post.user.profileImage + ')' }" ></div>
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
        { name: 'og:image', content: 'data:image/png;base64,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' },
        { name: 'og:image:type', content: 'image/jpg' },
        { name: 'og:image:width', content: '1024' },
        { name: 'og:image:height', content: '512' },
        { name: 'twitter:title', content: this.post.title + '— Assign' },
        { name: 'twitter:site_name', content: this.post.title + '— Assign' },
        { name: 'twitter:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'twitter:url', content: 'http://84.26.134.115:8080/assign/' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'twitter:domain', content: 'assignapp.co' },
        { name: 'twitter:image', content: 'data:image/png;base64,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' },
        { name: 'twitter:image:alt', content: 'Help jouw medestudent' },
        { name: 'twitter:site', content: '@assignappco' },
        { name: 'twitter:creator', content: '@assignappco' }
      ],
      link: [
        { rel: 'shortcut icon', href: 'data:image/png;base64,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' },
      ]
    }
  }
}
</script>
