import * as axios from 'axios'

let options = {}
// The server-side needs a full url to works
if (process.server) {
  options.baseURL = 'https://api.assignapp.nl/v0.1/'
}

export default axios.create({
  baseURL: process.env.baseUrl
})