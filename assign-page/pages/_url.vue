<template>
  <div class="container-fluid">
    <post-navigation></post-navigation>
    <div v-if="post.url != null">
      <post :post="post"></post>
      <div class="footer">
        <div class="overlay"></div>
        <a href="#" class="button-open">Download app</a>
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
  asyncData ({ params }) {
    return axios.get('https://api.assignapp.nl/assign/api/page/post', { params: {
      url: params.url
    }})
      .then((response) => {
        return { post: response.data }
      })
      .catch(function (error) {
        console.log(error)
      })
  },
  methods: {
    getPost (url) {
      let self = this
      axios.get('https://api.assignapp.nl/assign/api/page/post', { params: {
        url: url
      }})
        .then(function (response) {
          self.post = response.data
          // self.profileImage = self.$apiurl + '/img/' + self.post.user.profileImage
        })
        .catch(function (error) {
          console.log(error)
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
