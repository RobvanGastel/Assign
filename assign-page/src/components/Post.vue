<template>
  <div>
    <p>
      {{param}}
    </p>
  </div>
</template>

<script>
export default {
  data () {
    return {
      param: ''
    }
  },
  created () {
    this.param = this.$route.params.url
  },
  methods: {

  }
}
</script>
