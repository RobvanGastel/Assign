<<<<<<< HEAD
<template>
  <div id="schools">
    <v-flex xs12 sm6 md3>
      <v-card>
        <v-card-text>
        <h6>Create school</h6>
          <v-container fluid>
            <v-layout row>
              <v-flex xs6>
                <v-text-field
                  name="input-1"
                      counter
                  label="School name"
                  id="schoolname"
                  max="15"
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <v-text-field
                  name="input-1"
                  label="Amount of students"
                  id="students"
                ></v-text-field>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <div style="float:right !important">

          <v-btn primary flat router nuxt>Add school</v-btn>
                    </div>
        </v-card-actions>
      </v-card>
=======
<template>  
  <v-layout row justify-center>
    <v-flex xs11 sm11 md11>
      <div id="schools">
        <v-flex xs12 sm6 md12>
          <v-card>
            <v-card-text>
            <h6>Create school</h6>
              <v-container fluid>
                <v-flex>
                  <v-layout row>
                    <v-flex xs6>
                      <v-text-field
                        name="schoolname"
                        label="School name"
                        id="schoolname"
                        v-model="schoolname"
                        counter
                        max="15"
                      ></v-text-field>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex xs5>
                      <v-text-field
                        name="amount"
                        label="Amount of students"
                        id="students"
                      ></v-text-field>
                    </v-flex>
                  </v-layout>
                  <div class="float-right">
                    <v-btn class="btn-no-margin" primary flat router nuxt>Add school</v-btn>
                  </div>
                </v-flex>
              </v-container>
            </v-card-text>
          </v-card>
        </v-flex>
        <v-card>
          <v-card-title>
            <h6>Schools</h6>
            <v-spacer></v-spacer>
            <v-text-field
              append-icon="search"
              label="Search"
              single-line
              hide-details
              v-model="search"
            ></v-text-field>
          </v-card-title>
          <v-data-table
              v-bind:headers="headers"
              v-bind:search="search"
              v-bind:pagination.sync="pagination"
              :total-items="totalSchools"
              :loading="loading"
              :items="schools"
              hide-actions
              class="elevation-1"
            >
            <template slot="items" scope="props">
              <td>{{ props.item.id }}</td>
              <td class="text-xs-right">{{ props.item.name }}</td>
              <td class="text-xs-right">{{ props.item.schoolCode }}</td>
              <td class="text-xs-right">{{ props.item.DateCreated }}</td>
            </template>
          </v-data-table>
        </v-card>
      </div>
>>>>>>> origin/feature_dashboard
    </v-flex>
  </v-layout>
</template>

<script>
import axios from '~plugins/axios'

export default {
  data () {
    return {
      search: '',
      totalSchools: 0,
      loading: true,
      pagination: {},
      headers: [
        {
          text: '#',
          align: 'left',
          sortable: false,
          value: 'id'
        },
        { text: 'name', value: 'name' },
        { text: 'schoolCode', value: 'schoolCode' },
        { text: 'DateCreated', value: 'DateCreated' }
      ],
      schoolname: '',
      schools: [],
      error: {}
    }
  },
  created () {
    this.getSchools()
  },
  methods: {
    getSchools () {
      var self = this
      self.loading = false

      axios.get('http://84.26.134.115:8080/v0.1/schools',{ params: {
        start: self.totalSchools
      }})
        .then((response) => {
          self.schools = response.data
          self.totalSchools = response.data.length
          self.loading = false
        })
        .catch(function (error) {
          self.error = error
        })
    }
  },
  watch: {
    pagination: {
      handler () {
      this.getSchools()
      },
      deep: true
    }
  }
}
</script>

<style scoped>
#schools .flex {
  margin-bottom: 16px;
}

#schools .divider {
  background-color: rgba(255, 255, 255, 0.8);
}
<<<<<<< HEAD
</style>
=======

.float-right {
  float: right;
}

.btn-no-margin {
  margin: 0px;
}
</style>
>>>>>>> origin/feature_dashboard
