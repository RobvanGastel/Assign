<template>
  <div class="container-fluid">
    <navigation></navigation>
    <router-view></router-view>
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
