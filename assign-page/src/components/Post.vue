<template>
  <section class="post">
    <div class="profile" :style="{ 'background-image': 'url(' + profileImage + ')' }" ></div>
    <h2>{{ post.user.name }}</h2>
    <h3>{{ post.dateCreated }}</h3>
    <h1>{{ post.title }}</h1>
    <p>{{ post.description }}</p>
    <h3 class="text-center">3 aanbiedingen  •  Nog 3 uur beschikbaar</h3>
  </section>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      param: '',
      post: '',
      profileImage: '',
      error: ''
    }
  },
  created () {
    this.getPost(this.$route.params.url)
  },
  methods: {
    getPost (url) {
      let self = this

      axios.get(this.$apiurl + '/page/post', { params: {
          url: url
        }})
        .then(function (response) {
          self.post = response.data
          self.profileImage = self.$apiurl + "/img/" + self.post.user.profileImage
        })
        .catch(function (error) {
          self.error = error
      });
    }
  }
}
</script>
