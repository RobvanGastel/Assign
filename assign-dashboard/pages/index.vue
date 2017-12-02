<template>
  <v-layout row justify-center>
    <v-flex xs11 sm11 md11>
      <div id="home">
        <v-layout row wrap>
          <v-flex xs12 sm6 md4>
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
          <v-flex xs12 sm6 md4>
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

<script>
export default {
  middleware: 'auth'
}
</script>

<style scoped>
#home .flex {
  margin-bottom: 16px;
}

#home .divider {
  background-color: rgba(255, 255, 255, 0.8);
}
</style>
