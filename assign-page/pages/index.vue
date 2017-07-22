<template>
  <div>
    <div>
      <post :post="post"></post>
    </div>
    <div>

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

      axios.get(this.$apiurl + '/page/post', { params: {
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

<style>
.container
{
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.title
{
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}
.subtitle
{
  font-weight: 300;
  font-size: 42px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
}
.links
{
  padding-top: 15px;
}
</style>
