<template>
  <div>
    <h2>Înregistrare</h2>

    <form @submit.prevent="register">
      <div>
        <label>Username</label>
        <br>
        <input type="text" v-model="username">
      </div>

      <br>
      <div>
        <label>Email</label>
        <br>
        <input type="email" v-model="email">
      </div>

      <br>
      <div>
        <label>Parolă</label>
        <br>
        <input type="password" v-model="password">
      </div>

      <br>

      <button type="submit">
        Creează cont
      </button>

    </form>
  </div>
</template>

<script>
export default {
  name: "RegisterView",

  data() {
    return {
      username: "",
      email: "",
      password: "",
      errors: []
    }
  },

  methods: {
    register() {

      this.errors = []

      if (!this.username) {
        this.errors.push("Username obligatoriu")
      }

      if (!this.email) {
        this.errors.push("Email obligatoriu")
      }

      if (!this.password) {
        this.errors.push("Parola obligatorie")
      }

      if (this.errors.length > 0) {
        return
      }

      const users =
        JSON.parse(localStorage.getItem("users")) || []

      const existingUser = users.find(
        user => user.username === this.username
      )

      if (existingUser) {
        alert("Username deja existent")
        return
      }

      const newUser = {
        id: Date.now(),
        username: this.username,
        email: this.email,
        password: this.password
      }

      users.push(newUser)

      localStorage.setItem(
        "users",
        JSON.stringify(users)
      )

      alert("Cont creat cu succes!")

      this.$router.push("/login")
    }
  }
}
</script>