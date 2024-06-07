const CLIENT = "/api/clients/current"
const COURSE = "/api/courses/"
const search = location.search
const params = new URLSearchParams(search)

const { createApp } = Vue

const options = {
  data() {
    return {
      id:"",
      course:{},
      allvideos:"",
      userID:"",
      isWideScreen:false,
      showVideo:"",
    } // finaliza return
  }, // finaliza data
  created() {
    this.id = params.get('id')
    this.loadData()
    this.loadCourse()
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
    })
    .catch(error => {console.log(error)})
  }, //fin loadData
    loadCourse(){
      axios.get(COURSE + this.id)
      .then(courseData => {
        this.course = courseData.data
        this.allvideos = courseData.data.videos.sort((a,b)=> a.id - b.id)
        console.log("objeto cursos",courseData)
        console.log("todos los cursos",this.course)
        console.log("todos los videos",this.allvideos)
      })
      .catch(error => {console.log(error)})
    },
    catchCourseID(courseID){
      let video = this.allvideos.find(video => video.id == courseID)
      console.log("video",video)
      let videoURL = video.url
      console.log("videoURL",videoURL)
      let regex = /(?:https?:\/\/)?(?:www\.)?(?:youtube\.com\/(?:[^\/\n\s]+\/\S+\/|(?:v|e(?:mbed)?)\/|\S*?[?&]v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})/
      let match = videoURL.match(regex)
      
      if (match && match[1]) {
          let videoID = match[1]
          
          let embedURL = `https://www.youtube.com/embed/${videoID}`
          
          this.showVideo = embedURL
          console.log(this.showVideo)
      }
    },
  checkScreenSize(){
    this.isWideScreen = window.innerWidth >=1024
    },
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')