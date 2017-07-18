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
      title: this.post.user.name,
      titleTemplate: '%s — Assign',
      description: this.post.user.name + ' vraagt om hulp bij ' + this.post.description,
      favicon: "../assets/img/favicon.png"
    }
  }
}
</script>
