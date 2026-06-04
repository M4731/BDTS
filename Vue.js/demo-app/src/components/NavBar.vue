<template>
  <nav class="navbar">
    <h2>Magazin Online</h2>

    <div class="links">
      <router-link to="/">Home</router-link>

      <router-link v-if="isAuthenticated" to="/products"> Produse </router-link>

      <router-link v-if="isAuthenticated" to="/products/new"> Adaugă Produs </router-link>

      <router-link v-if="isAuthenticated" to="/api/products"> API Produse </router-link>

      <template v-if="!isAuthenticated">
        <router-link to="/login"> Login </router-link>
        <router-link to="/register"> Register </router-link>
      </template>

      <template v-if="isAuthenticated">

        <span>
          Salut, {{ currentUser.username }}
        </span>

        <a href="#" @click.prevent="logout"> Logout </a>

      </template>


    </div>
  </nav>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "NavBar",

  computed: {
    ...mapGetters("user", [
      "currentUser",
      "isAuthenticated"
    ])
  },

  methods: {
    ...mapMutations("user", [
      "LOGOUT"
    ]),

    logout() {
      this.LOGOUT()

      this.$router.push("/")
    }
  }
}
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 15px 30px;

  background-color: #2c3e50;
}

.navbar h2 {
  color: white;
  margin: 0;
}

.links {
  display: flex;
  gap: 20px;
}

.links a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}

.links a.router-link-active {
  color: #42b983;
}
</style>