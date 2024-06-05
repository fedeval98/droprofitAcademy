const CLIENT = "/api/clients/current"

const { createApp } = Vue

const options = {
  data() {
    return {
      allcourses:[],
      allvideos:"",
      userID:"",
    } // finaliza return
  }, // finaliza data
  created() {
    this.loadData()
  }, //finaliza created

  methods: {
    loadData(){
    axios.get(CLIENT)
    .then(data => {
      this.userID = data.data.uid
      this.allcourses = data.data.courses
      console.log(data)
      console.log(this.allcourses)
      console.log(this.userID)
    })
    .catch(error => {console.log(error)})
  }//fin loadData
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')