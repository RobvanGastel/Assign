<template>
  <div class="container-fluid">
    <navigation></navigation>
    <router-view></router-view>
    <div class="footer">
      <a href="#" class="button-open">Open in app</a>
    </div>
  </div>
</template>

<script>
import Navigation from './main/navigation.vue'

export default {
  components: {
    Navigation
  },
  metaInfo: {
    // if no subcomponents specify a metaInfo.title, this title will be used
    title: 'Default Title',
    // all titles will be injected into this template
    titleTemplate: '%s | My Awesome Webapp'
  }
}
</script>

<style scoped>
</style>
