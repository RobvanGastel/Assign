<template>
  <div>
    <p>
      {{response}}
    </p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      param: '',
      response: ''
    }
  },
  created () {
    this.getPost(this.$route.params.url)
  },
  methods: {
    getPost (url) {

      let self = this;

      axios.get('http://localhost:9080/assign/api/page/post?url=' + url)
      .then(function (response) {
        console.log(response);
        self.response = response.data
      })
      .catch(function (error) {
        self.response = error
      });
    }
  }
}
</script>
