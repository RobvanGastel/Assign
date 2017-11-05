<template>
  <div class="container-fluid">
    <post-navigation></post-navigation>
    <div v-if="post.url != null">
      <post :post="post"></post>
      <div class="footer">
        <div class="overlay"></div>
        <!-- Change on launch -->
        <!-- <nuxt-link class="btn-opencta" title="Open in app" :to="'#appstore'">Open in de Assign App</nuxt-link> -->
      </div>
    </div>
    <div v-else>
      <not-found></not-found>
    </div>
  </div>
</template>

<script>
import PostNavigation from '../components/PostNavigation'
import NotFound from '../components/NotFound'
import Post from '../components/Post'
import axios from 'axios'

export default {
  components: {
    Post,
    PostNavigation,
    NotFound
  },
  data: () => ({
    post: {
      user: {
        name: 'Name'
      }
    }
  }),
  asyncData ({ params }) {
    return axios.get(process.env.baseUrl + '/page/post', { params: {
      url: params.url
    }})
      .then((response) => {
        return { post: response.data }
      })
      .catch((error) => {
        console.log(error.response.status)
      })
  },
  methods: {
    getPost (url) {
      axios.get(process.env.baseUrl + '/page/post', { params: { url } })
        .then((response) => {
          this.post = response.data
        })
        .catch((error) => {
          console.log(error.response.status)
        })
    }
  },
  head: () => ({
    title: 'Post',
    titleTemplate: 'Assign – %s',
    link: [
      { rel: 'shortcut icon', type: 'image/png', href: '/favicon.png' }
    ]
  })
}
</script>
