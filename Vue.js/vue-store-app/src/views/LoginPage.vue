<template>
  <div>
    <h1>Login</h1>

    <input
      v-model="email"
      placeholder="Email"
    >

    <input
      v-model="password"
      type="password"
      placeholder="Parola"
    >

    <button @click="login">
      Login
    </button>
  </div>
</template>

<script>
export default {
  name: "LoginPage",

  data() {
    return {
      email: "",
      password: ""
    }
  },

  methods: {
    login() {
      alert("Login apăsat")
    }
  }
}
</script>