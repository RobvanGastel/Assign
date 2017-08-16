<template>
  <v-app light toolbar footer>
    <v-navigation-drawer 
      v-if="$store.state.authUser"
      persistent
      :mini-variant="miniVariant"
      :clipped="clipped"
      :enable-resize-watcher= "enableResize"
      v-model="drawer"
    >
      <v-list>
        <v-list-tile 
          router
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
        >
          <v-list-tile-action>
            <v-icon v-html="item.icon"></v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title v-text="item.title"></v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar 
      v-if="$store.state.authUser"
      fixed class="orange-highlight">
      <v-toolbar-side-icon class="white--text" @click.native.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-btn class="white--text" 
        icon
        @click.native.stop="miniVariant = !miniVariant"
      >
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
      </v-btn>
      <v-toolbar-title class="white--text" v-text="title"></v-toolbar-title>
    </v-toolbar>
    <main
      v-if="$store.state.authUser"
      >
      <v-container fluid>
        <nuxt />
      </v-container>
    </main>
    <nuxt v-if="!$store.state.authUser" />
    <v-footer 
      v-if="$store.state.authUser"
      :fixed="fixed">
      <v-spacer></v-spacer>
      <span>&copy; 2017 - Assign</span>
    </v-footer>
  </v-app>
</template>

<script>
  export default {
    data () {
      return {
        clipped: true,
        drawer: true,
        fixed: false,
        items: [
          { icon: 'home', title: 'Home', to: '/' },
          { icon: 'network_check', title: 'Analytics', to: '/analytics' },
          { icon: 'school', title: 'Schools', to: '/schools' },
          { icon: 'people', title: 'Users', to: '/users' },
          { icon: 'assignment', title: 'Assignments', to: '/assignments' }
        ],
        miniVariant: false,
        right: false,
        rightDrawer: false,
        enableResize: true,
        title: 'Assign'
      }
    },

  }
</script>