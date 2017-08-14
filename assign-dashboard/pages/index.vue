<template>  
  <v-layout row justify-center>
    <v-flex xs11 sm11 md11>
      <div id="home">
        <v-layout row wrap>
          <v-flex xs12 sm12 md8>
            <v-card height="100%">
              <v-card-text>
                <p>Welcome to the Webpack SSR template.</p>
                <p>Vuetify is a progressive Material Design component framework for Vue.js. It was designed to empower developers to create amazing applications. For more information on Vuetify, check out the <a href="https://vuetifyjs.com" target="_blank">documentation</a>. If you have questions, please join the official <a href="https://gitter.im/vuetifyjs/Lobby" target="_blank" title="chat">gitter</a>. Find a bug? Report it on the github <a href="https://github.com/vuetifyjs/vuetify/issues" target="_blank" title="contribute">issue board</a>.</p>
                <p>Thank you for developing with Vuetify and I look forward to bringing more exciting features in the future.</p>
                <div class="text-xs-right">
                  <em><small>&mdash; John Leider</small></em>
                </div>
              </v-card-text>
              <v-card-actions>
                <v-btn primary flat router nuxt>Continue</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
          <v-flex xs12 sm6 md2>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <h4 class="white--text">42</h4>
                <p>Daily Users</p>
                <v-divider></v-divider>
              </v-card-text>
              <v-card-actions>
                <v-btn class="white--text" primary flat router nuxt>View users</v-btn>
              </v-card-actions>
              </v-card>
          </v-flex>
          <v-flex xs12 sm6 md2>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <h4 class="white--text">63</h4>
                <p>Daily posts created</p>
                <v-divider></v-divider>
              </v-card-text>
              <v-card-actions>
                <v-btn class="white--text" right primary flat router nuxt>View posts</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </div>    
    </v-flex>
  </v-layout>
</template>

<style scoped>
#home .flex {
  margin-bottom: 16px;
}

#home .divider {
  background-color: rgba(255, 255, 255, 0.8);
}
</style>