<template>
    <post :post="post"></post>
</template>

<script>
import Post from './Post'
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
          self.profileImage = self.$apiurl + "/img/" + self.post.user.profileImage
        })
        .catch(function (error) {
          self.error = error
      });
    }
  },
  metaInfo: {
    title: 'Post title | Assign',
    // override the parent template and just use the above title only
    titleTemplate: null
  }
}
</script>
