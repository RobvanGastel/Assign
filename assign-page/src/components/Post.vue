<template>
  <section class="post">
    <img class="profile" src="#">
    <h2>Jan Pieter</h2>
    <h3>2 days ago</h3>
    <h1>Aanpasbaar menukaart voor een restaurant</h1>
    <p>Ik ben bezig met een website waar de klant een menukaart regelmatig iets aanpast. Om de klant tevreden te houden wil ik dit voor hunzelf aanpasbaar maken met iets als Custom Fields. Zo kunnen ze dit in een categorie zetten, bijvoorbeeld; wijn. Is dit goed</p>
    <p>
      {{response}}
    </p>
    <h3 class="text-center">3 aanbiedingen  •  Nog 3 uur beschikbaar</h3>
  </section>
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
      let self = this

      axios.get(this.$apiurl + '/page/post', { params: {
          url: url
        }})
        .then(function (response) {
          self.response = response.data
        })
        .catch(function (error) {
          self.response = error
      });
    }
  }
}
</script>
