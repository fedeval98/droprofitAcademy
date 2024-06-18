const CLIENTS = "/api/clients"
const COURSES = "/api/courses"
const VIDEOS = "/api/videos"
const CREATE_CLIENT = "/api/clients/register"
const REMOVE_CLIENT = "/api/clients/remove?id="
const CREATE_COURSE = "/api/courses/create"
const ADD_COURSE_CLIENT = "/api/clients/addCourse?"
const UPDATE_COURSE = "/api/courses/update?id="
const DELETE_COURSE = "/api/courses/delete?id="
const CREATE_VIDEO = "/api/videos/create"
const ADD_VIDEOS_COURSES = "/api/courses/addVideos?"
const UPDATE_VIDEO = "/api/videos/update?id="
const DELETE_VIDEO = "/api/videos/remove?id="


const { createApp } = Vue

const options = {
  data() {
    return {
      name:"",
      lastName:"",
      email:"",
      password:"",
      form:"0",
      userID:null,
      courses:{},
      courseID:null,
      client:"",
      courseName:"",
      courseIMG:"",
      videoName:"",
      videoURL:"",
      videos:{},
      videoID:"",
      success:"",
      error:"",
    } // finaliza return
  }, // finaliza data
  created() {
    this.getCourses()
    this.getVideos()
  }, //finaliza created
  methods: {
    getClient(){
      this.clean()
      axios.get(CLIENTS)
      .then(clients => {
        this.client = clients.data.find(client => client.uid === parseInt(this.userID))
        this.name = this.client.firstName
        this.lastName = this.client.lastName
        this.email = this.client.email
        console.log("client:",this.client)})
      .catch(error => console.log("Error:", error))
    },
    getCourses(){
      axios.get(COURSES)
      .then(courses => {
        this.courses = courses.data.filter(course => course.active == true ).sort((a,b)=> a.id - b.id)
        console.log("Cursos:",courses.data)})
      .catch(error => console.log("Error:", error))
    },
    getVideos(){
      axios.get(VIDEOS)
      .then(videos => {
        this.videos = videos.data.sort((a,b)=> a.id - b.id)
        console.log("videos:",videos.data)})
      .catch(error => console.log("Error:", error))
    },
    createClient(){
      const body ={
        firstName : this.name,
        lastName: this.lastName,
        email:this.email,
        password:this.password,
      }
      this.clean()
      axios.post(CREATE_CLIENT,body)
      .then(response => this.success = response.data)
      .catch(error => this.error = error.response.data)
    },
    addCourseToClient(){
      this.clean()
      axios.patch(ADD_COURSE_CLIENT+"userId=" + this.userID + "&CourseId=" + this.courseID)
      .then(response => {
        this.success = response.data
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },
    removeClient(){
      this.clean()
      axios.delete(REMOVE_CLIENT + this.userID)
      .then(response => this.success = response.data)
      .catch(error => this.error = error.response.data)
    },
    createCourse(){
      const body ={
        name : this.courseName,
        img: this.courseIMG,
      }
      this.clean()
      axios.post(CREATE_COURSE,body)
      .then(response => {
        this.success = response.data
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
      
    },
    updateCourse(){
      const body ={
        name : this.courseName,
        img: this.courseIMG,
      }
      this.clean()
      axios.patch(UPDATE_COURSE + this.courseID,body)
      .then(response => {
        this.success = response.data
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },
    removeCourse(){
      this.clean()
      axios.patch(DELETE_COURSE + this.courseID)
      .then(response => {
        this.success = response.data
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },
    createVideo(){
      const body ={
        videoName : this.videoName,
        url: this.videoURL,
      }
      this.clean()
      axios.post(CREATE_VIDEO,body)
      .then(response => {
        this.success = response.data
        this.getVideos()
      })
      .catch(error => this.error = error.response.data)
    },
    addVideoToCourse(){
      this.clean()
      axios.patch(ADD_VIDEOS_COURSES+"videoId=" + this.videoID + "&CourseId=" + this.courseID)
      .then(response => {
        this.success = response.data
        this.getVideos()
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },
    updateVideo(){
      const body ={
        videoName : this.videoName,
        url: this.videoURL,
        courses:{
          id: this.courseID
        }
      }
      this.clean()
      axios.patch(UPDATE_VIDEO+ this.videoID, body)
      .then(response => {
        this.success = response.data
        this.getVideos()
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },
    removeVideo(){
      this.clean()
      axios.delete(DELETE_VIDEO + this.videoID)
      .then(response => {
        this.success = response.data
        this.getVideos()
        this.getCourses()
      })
      .catch(error => this.error = error.response.data)
    },

    test(event){
      console.log(event.target.name + ": " + event.target.value)
    },
    changeForm(event){
      this.clean()
    },
    clean(){
      this.error = ""
      this.success = ""
    }
    
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')