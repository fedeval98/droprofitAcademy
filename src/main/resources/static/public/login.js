const { createApp } = Vue

const options = {
  data() {
    return {
    } // finaliza return
  }, // finaliza data
  created() {

  }, //finaliza created

  methods: {
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
  }, //fin methods
} //finaliza createApp

const app = createApp(options)
app.mount('#app')