<template>  
    <div id="login">
        <v-layout row justify-center>
            <v-flex xs10 sm6 md4>
                <v-card height="100%">
                    <v-card-text>
                        <v-layout row justify-center>    
                            <v-flex xs8>
                                <br />
                                <p>Welcome to the Webpack Assign dashboard.</p> 
                                <div v-if="errorMessage">
                                    <v-alert error value="true">
                                    {{ errorMessage }}
                                    </v-alert>
                                </div>
                                <br />         
                                <v-text-field
                                name="username"
                                label="email"
                                id="username"
                                v-model="email"
                                ></v-text-field>
                                <v-text-field
                                type="password"
                                name="password"
                                label="password"
                                id="password"
                                v-model="password"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                        <div class="float-right">
                            <v-btn class="btn-no-margin" @click.native="login()" primary flat router nuxt>Login</v-btn>
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
    padding-top: 300px;
    background-image: url( "../assets/img/esecqzP.jpg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center;
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
}
</style>