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
                  v-model="schoolname"
                  max="15"
                ></v-text-field>
              </v-flex>
              <v-flex xs6>
                <!-- <v-text-field
                  name="input-1"
                  label="Amount of students"
                  v-model="student"
                  id="students"
                ></v-text-field> -->
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-btn primary flat router nuxt @click.native.stop="addSchool">Add school</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
    <v-flex xs12 sm6 md3>
      <v-card>
        <v-card-title>
          <h6>School</h6>
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
      schoolname: null,
      student: null,
      schools: [],
      error: {}
    }
  },
  created () {
    this.getSchools()
  },
  methods: {
    getSchools () {
      this.loading = false

      axios.get('/schools', { params: {
        start: this.totalSchools
      }})
        .then((response) => {
          this.schools = response.data
          this.totalSchools = response.data.length
          this.loading = false
        })
        .catch(function (error) {
          this.error = error
        })
    },
    addSchool () {
      axios.post('/schools', { params: {
        name: this.schoolname
      }})
        .then((response) => {
          console.log(response)
          this.getSchools()
        })
        .catch(function (error) {
          this.error = error
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