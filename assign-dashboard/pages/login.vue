<template>
  <div id="login">
    <v-layout row justify-center>
      <v-flex xs12 sm12 md10 lg8 xl6>
        <v-card height="100%">
          <v-card-text>
            <v-layout row>
              <v-flex xs6 hidden-sm-and-down>
    						<img src="/illustratie/illustration-dashboard.svg" alt="Assign Dashboard" draggable="false">
              </v-flex>
              <v-flex xs12 md6>
                <br />
                <h1 class="login-title">Assign School Dashboard</h1>
                <p class="login-message">Log in om alle gegevens en statistieken te bekijken en overzicht te krijgen.</p>
                <div v-if="errorMessage">
                  <v-alert error value="true">
                  {{ errorMessage }}
                  </v-alert>
                </div>
                <br />
                <v-text-field name="username" label="Email" id="username" v-model="email"></v-text-field>
                <v-text-field type="password" name="password" label="Wachtwoord" id="password" v-model="password"></v-text-field>
              </v-flex>
            </v-layout>
            <div class="float-right">
              <nuxt-link :to="'/'">
                <v-btn class="" @click.native="login()" flat router nuxt>Hulp bij inloggen?</v-btn>
                <v-btn class="btn-no-margin" @click.native="login()" primary router nuxt>Log in</v-btn>
              </nuxt-link>
            </div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  data () {
    return {
      errorMessage: null,
      email: '',
      password: ''
    }
  },
  methods: {
    async login () {
      try {
        await this.$store.dispatch('login', {
          username: this.email,
          password: this.password
        })

        this.email = ''
        this.password = ''
        this.errorMessage = null
      } catch(e) {
        this.errorMessage = e.message
      }
    }
  }
}
</script>

<style scoped>
.float-right {
  float: right;
  padding-bottom: 10px;
}

.btn-no-margin {
  margin: 0px;
}

#login {
  padding-top: calc(50vh - 300px);
}

.login-title {
  font-size: 28px;
  color: #4A4A4A;
  font-weight: 500;
}

.login-message {
  font-size: 18px;
  color: #5E5E5E;
}

@media screen and (min-width: 480px) {
  #login .card__text {
    padding: 80px 40px;
  }
}

</style>
