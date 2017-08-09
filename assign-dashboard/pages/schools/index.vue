<template>
  <v-card>
    <v-card-title>
      schools
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

      axios.get('http://localhost:9080/v0.1/schools',{ params: {
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