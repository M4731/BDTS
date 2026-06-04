<template>
  <div>
    <h2>Autentificare</h2>

    <form @submit.prevent="login">
      <div>
        <label>Username</label>
        <br>
        <input type="text" v-model="username">
      </div>

      <br>
      <div>
        <label>Parolă</label>
        <br>
        <input type="password" v-model="password">
      </div>

      <br>
      <button type="submit">
        Login
      </button>

    </form>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "LoginView",

  data() {
    return {
      username: "",
      password: ""
    }
  },

  methods: {

    ...mapMutations("user", [
      "SET_USER"
    ]),

    login() {

      const users =
        JSON.parse(localStorage.getItem("users")) || []

      const foundUser = users.find(
        user =>
          user.username === this.username &&
          user.password === this.password
      )

      if (!foundUser) {
        alert("User inexistent")
        return
      }

      const currentUser = {
        id: foundUser.id,
        username: foundUser.username,
        email: foundUser.email
      }

      this.SET_USER(currentUser)

      alert("Autentificare reușită")

      this.$router.push("/")
    }
  }
}
</script>