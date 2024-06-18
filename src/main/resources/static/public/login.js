const { createApp } = Vue

const options = {
  data() {
    return {
      email:"",
      password:"",
      modalVisibleAlert:false,
      modalRegistered:false,
      signupactive:false
    } // finaliza return
  }, // finaliza data
  created() {
  }, //finaliza created

  methods: {
    clearData(){
      this.email = ""
      this.password = ""
    },
    abrirAlert() {
      this.modalVisibleAlert = true
      if (this.modalVisibleAlert) {
        document.body.classList.add('overflow-y-hidden')
      }
    }, // finaliza showModal
    cerrarAlert() {
      this.modalVisibleAlert = false
      if (this.modalVisibleAlert == false) {
        document.body.classList.remove('overflow-y-hidden')
      }
    },// finaliza cerrarModal
    cerrarModalRegistered() {
      this.modalRegistered = false
      if (this.modalRegistered == false) {
        document.body.classList.remove('overflow-y-hidden')
      }
    },// finaliza cerrarModal
    closeModalandSignUp(){
      this.cerrarModalRegistered()
      this.signupactive = true
    },
    login(){
      axios.post("/api/login?email="+this.email+"&password="+this.password, { withCredentials: true })
        .then(response => {
            console.log(response)
            setTimeout(() => {
              axios.get("/api/clients/current", { withCredentials: true })
                .then(response => {
                  if(response.data.rol == "ADMIN"){
                    this.clearData()
                    window.location.href = "/web/admin/admin.html"
                  } else {
                    this.clearData()
                    window.location.href = "/web/assets/html/inicio.html"
                  }
                })
                .catch(error => {
                  console.log("Login error", error)
                })
            },2000)
        })
        .catch(error => {
        console.log("Error", error)
        if(error.response.status.toString().startsWith('4')){
          this.modalRegistered = true
        }

        })
    },
    togglePasswordVisibility() {
      // Obtén referencias a los elementos del DOM
      const passwordInput = document.querySelector(".id_password");
      const eye = document.querySelector(".togglePassword");

      // Cambia el icono del ojo
      eye.classList.toggle("fa-eye-slash");

      // Verifica el tipo actual del input
      const currentInputType = passwordInput.getAttribute("type");

      // Cambia entre tipo text y tipo password del input
      if (currentInputType === "password") {
        passwordInput.setAttribute("type", "text");
      } else if (currentInputType === "text") {
        passwordInput.setAttribute("type", "password");
      }
    },// fin togglePassword
    updateEmail(event) {
      this.email = event.target.value;
      console.log(this.email)
    },
    updatePassword(event) {
      this.password = event.target.value;
      console.log(this.password)
    },
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')