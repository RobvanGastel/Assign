import * as axios from 'axios'

let options = {}
// The server-side needs a full url to works
if (process.server) {
  options.baseURL = `http://${process.env.HOST || '84.26.134.115:8080/v0.1/'}`
}

export default axios.create(options)