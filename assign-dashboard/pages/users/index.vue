<template>
  <v-layout row justify-center>
    <v-flex xs11 sm11 md11>
      <v-card>
        <v-card-title>
          <h6>Users</h6>
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
            :total-items="totalUsers"
            :loading="loading"
            :items="users"
            hide-actions
            class="elevation-1"
          >
          <template slot="items" scope="props">
            <td>{{ props.item.id }}</td>
            <td class="text-xs-right">{{ props.item.name }}</td>
            <td class="text-xs-right">{{ props.item.email }}</td>
            <td class="text-xs-right">{{ props.item.specialisation }}</td>
            <td class="text-xs-right">{{ props.item.study }}</td>
            <td class="text-xs-right">{{ props.item.dateCreated }}</td>
          </template>
        </v-data-table>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import axios from '../../plugins/axios'

export default {
  data () {
    return {
      search: '',
      totalUsers: 0,
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
        { text: 'email', value: 'email' },
        { text: 'specialisation', value: 'specialisation' },
        { text: 'study', value: 'study' },
        { text: 'date created', value: 'dateCreated' },
      ],
      users: [],
      error: {}
    }
  },
  created () {
    this.getUsers()
  },
  methods: {
    getUsers () {
      var self = this
      self.loading = false

      axios.get('http://84.26.134.115:8080/v0.1/dashboard/users',{ params: {
        start: self.totalUsers
      }})
        .then((response) => {
          self.users = response.data
          self.totalUsers = response.data.length
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
      this.getUsers()
      },
      deep: true
    }
  }
}
</script>
