<template>
  <div>
    <NavBar />

    <main class="container">
      <router-view />
    </main>

    <AppFooter />
  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import AppFooter from "./components/AppFooter.vue";

export default {
  name: "App",

  components: {
    NavBar,
    AppFooter
  }
}
</script>

<style>
body {
  margin: 0;
}

.container {
  padding: 20px;
}
</style>