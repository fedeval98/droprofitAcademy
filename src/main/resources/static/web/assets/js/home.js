const CLIENT = "/api/clients/current"

const { createApp } = Vue

const options = {
  data() {
    return {
      allcourses:[],
      allvideos:"",
      userID:"",
      isWideScreen:false,
    } // finaliza return
  }, // finaliza data
  created() {
    this.loadData()
  }, //finaliza created
  mounted(){
    this.checkScreenSize()
    window.addEventListener('resize', this.checkScreenSize)
    },
    beforeDestroy(){
    window.removeEventListener('resize', this.checkScreenSize)
    },

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
  }, //fin loadData
  checkScreenSize(){
    this.isWideScreen = window.innerWidth >=1024
    },
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')