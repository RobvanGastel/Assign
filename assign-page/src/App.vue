<template>
  <div class="container-fluid">
    <navigation></navigation>
    <router-view></router-view>
    <div class="col-xs-12 text-center">
      <a href="#" class="button-open">Open in app</a>
    </div>
  </div>
</template>

<script>
import Navigation from './main/navigation.vue'

export default {
  components: {
    Navigation
  }
}
</script>

<style scoped>
</style>
