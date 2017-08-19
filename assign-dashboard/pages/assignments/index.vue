<template>
  <v-card>
    <v-card-title>
      <h6>Assignments</h6>
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
        :total-items="totalPosts"
        :loading="loading"
        :items="posts"
        hide-actions
        class="elevation-1"
      >
      <template slot="items" scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-right">{{ props.item.user.name }}</td>
        <td class="text-xs-right">{{ props.item.user.specialisation }}</td>
        <td class="text-xs-right">{{ props.item.title }}</td>
        <td class="text-xs-right">{{ props.item.dateCreated }}</td>
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
      totalPosts: 0,
      loading: true,
      pagination: {},
      headers: [
        {
          text: '#',
          align: 'left',
          sortable: false,
          value: 'id'
        },
        { text: 'Studentname', value: 'name' },
        { text: 'Study', value: 'specialisation' },
        { text: 'question', value: 'title' },
        { text: 'DateCreated', value: 'DateCreated' }
      ],
      posts: [],
      error: {}
    }
  },
  created () {
    this.getPosts()
  },
  methods: {
    getPosts () {
      var self = this
      self.loading = false

      axios.get('http://84.26.134.115:8080/v0.1/dashboard/posts',{ params: {
        start: self.totalPosts
      }})
        .then((response) => {
          self.posts = response.data
          self.totalPosts = response.data.length
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
      this.getPosts()
      },
      deep: true
    }
  }
}
</script>
