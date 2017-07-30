<template>
  <div class="container-fluid">
    <div class="row">
      <a href="/">
        <section class="brand-nav">
          <div class="container">
            <div class="row">
              <div class="col-xs-12 col-md-10 col-md-offset-1">
                <img class="logo" src="/share-logo.png">
                <h4>Assign</h4>
                <h5>Help jouw medestudenten</h5>
                <div class="button-info">Info</div>
              </div>
            </div>
          </div>
        </section>
      </a>
    </div>
    <div v-if="post.url != null">
      <post :post="post"></post>
    </div>
    <div v-else>
      <not-found></not-found>
    </div>
  </div>
</template>

<script>
import NotFound from '../components/NotFound.vue'
import Post from '../components/Post.vue'
import axios from 'axios'
export default {
  components: {
    Post,
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
