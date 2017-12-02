<template>
  <v-layout row justify-center>
    <v-flex xs12 sm11 md11>
      <div id="analytics">
        <v-layout row wrap>

          <v-flex xs12 sm6 md4 lg3>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <p>Maandelijkse gebruikers</p>
                <h4 class="white--text">1428</h4>
              </v-card-text>
              <v-card-actions>
                <v-btn class="white--text" primary flat router nuxt>Bekijk leerlingen</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>

          <v-flex xs12 sm6 md4 lg3>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <p>Wekelijkse gebruikers</p>
                <h4 class="white--text">839</h4>
              </v-card-text>
            </v-card>
          </v-flex>

          <v-flex xs12 sm6 md4 lg3>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <p>Totaal aantal gebruikers</p>
                <h4 class="white--text">2107</h4>
              </v-card-text>
            </v-card>
          </v-flex>

          <v-flex xs12 sm6 md4 lg3>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <p>Maandelijkse vragen</p>
                <h4 class="white--text">481</h4>
              </v-card-text>
              <v-card-actions>
                <v-btn class="white--text" primary flat router nuxt>Bekijk alle vragen</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>

          <v-flex xs12 sm6 md4 lg3>
            <v-card height="100%" class="orange darken-2 white--text">
              <v-card-text>
                <p>Maandelijkse vragen opgelost</p>
                <h4 class="white--text">481</h4>
              </v-card-text>
              <v-card-actions>
                <v-btn class="white--text" primary flat router nuxt>Bekijk alle vragen</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>

        </v-layout>
      </div>
    </v-flex>
  </v-layout>
</template>

<style scoped>
#analytics .flex {
  margin-bottom: 16px;
}
</style>
