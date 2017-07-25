<template>
  <div class="container-fluid">
    <post-navigation></post-navigation>
    <div v-if="post.url != null">
      <post :post="post"></post>
      <div class="footer">
        <a href="#" class="button-open">Open in app</a>
      </div>
    </div>
    <div v-else>
      <not-found></not-found>
    </div>
  </div>
</template>

<script>
import PostNavigation from '../components/PostNavigation.vue'
import NotFound from '../components/NotFound.vue'
import Post from '../components/Post.vue'
import axios from 'axios'

export default {
  components: {
    Post,
    PostNavigation,
    NotFound
  },
  data () {
    return {
      post: {
        user: {
          name: 'Name'
        }
      }
    }
  },
  created () {
    this.getPost(this.$route.params.url)
  },
  methods: {
    getPost (url) {
      let self = this

      axios.get('http://84.26.134.115:8080/assign/api/page/post', { params: {
        url: url
      }})
        .then(function (response) {
          self.post = response.data
          self.profileImage = self.$apiurl + '/img/' + self.post.user.profileImage
        })
        .catch(function (error) {
          self.error = error
        })
    }
  },
  head () {
    return {
      title: 'Post',
      titleTemplate: '%s — Assign',
      link: [
        { rel: 'shortcut icon', type: 'image/png', href: '/favicon.png' }
      ]
    }
  }
}
</script>
