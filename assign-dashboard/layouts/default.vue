<template>
  <v-app light>
    <v-navigation-drawer 
      :mini-variant="miniVariant"
      :clipped="clipped"
      v-model="drawer"
      fixed
      app>
      <v-list>
        <v-list-tile 
          router
          :to="item.to"
          :key="i"
          v-for="(item, i) in items"
          exact>
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
        fixed 
        app  
        :clipped-left="clipped" 
        class="orange-highlight"> 
      <v-toolbar-side-icon 
        class="white--text"  @
        click="drawer = !drawer"></v-toolbar-side-icon>
      <v-btn class="white--text" 
        icon
        @click.native.stop="miniVariant = !miniVariant">
        <v-icon v-html="miniVariant ? 'chevron_right' : 'chevron_left'"></v-icon>
      </v-btn>
      <v-toolbar-title class="white--text" v-text="title"></v-toolbar-title>
    </v-toolbar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>
        <v-footer :fixed="fixed" app>
      <v-spacer></v-spacer>
      <span>&copy; 2017 - Assign</span>
    </v-footer>
  </v-app>
</template>


    <!--       v-if="$store.state.authUser" -->
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