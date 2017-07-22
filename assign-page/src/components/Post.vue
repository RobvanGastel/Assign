<template>
  <section class="post">
    <img class="faceholder" src="../assets/img/faceholder.jpg">
    <div class="profile" :style="{ 'background-image': 'url(' + 'api/img/' + this.post.user.profileImage + ')' }" ></div>
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
        { property: 'og:title', content: this.post.title + '— Assign' },
        { property: 'og:site_name', content: this.post.title + '— Assign' },
        { property: 'og:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'twitter:title', content: this.post.title + '— Assign' },
        { name: 'twitter:site_name', content: this.post.title + '— Assign' },
        { name: 'twitter:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title }
      ]
    }
  }
}
</script>
