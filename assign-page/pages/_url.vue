<template>
  <div class="container-fluid">
    <div class="row">
      <section class="brand-nav">
        <div class="col-xs-12">
          <img class="logo" src="../assets/img/share-logo.png">
          <h4>Assign</h4>
          <h5>Help jouw medestudenten</h5>
          <a href="#" class="button-install">Install</a>
        </div>
      </section>
    </div>
    <div>
      <post :post="post"></post>
    </div>
    <div>

    </div>
    <div class="footer">
      <a href="#" class="button-open">Open in app</a>
    </div>
  </div>
</template>

<script>
import Post from '../components/Post.vue'
import axios from 'axios'

export default {
  components: {
    Post
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
      title: this.post.title,
      titleTemplate: '%s — Assign',
      meta: [
        { name: 'description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { name: 'language', content: 'nl_NL' },
        { property: 'og:title', content: this.post.title + '— Assign' },
        { property: 'og:site_name', content: this.post.title + '— Assign' },
        { property: 'og:description', content: this.post.user.name + ' vraagt om hulp bij ' + this.post.title },
        { property: 'og:url', content: 'http://84.26.134.115:8080/assign/' },
        { property: 'og:locale', content: 'nl_NL' },
        { property: 'og:type', content: 'website' },
        { property: 'og:image', content: '/static/share-image.jpg' },
        { property: 'og:image:type', content: 'image/jpg' },
        { property: 'og:image:width', content: '1024' },
        { property: 'og:image:height', content: '512' },
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
        { rel: 'shortcut icon', type: 'image/png', href: '/static/favicon.png' }
      ]
    }
  }
}
</script>
