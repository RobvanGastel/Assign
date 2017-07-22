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
    if(this.$route.params.url != null) {
      this.getPost(this.$route.params.url)
    }
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
