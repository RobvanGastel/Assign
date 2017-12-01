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
    </v-flex>
  </v-layout>
</div>
</template>

<script>
import axios from '../../plugins/axios'

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

.float-right {
  float: right;
}

.btn-no-margin {
  margin: 0px;
}
</style>