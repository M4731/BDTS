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
:root {
  --bg: #f0f6fb;
  --surface: #ffffff;
  --text: #1f2937;
  --muted: #4b5563;
  --primary: #42b883;
  --primary-dark: #2f8f6f;
  --accent: #ffd166;
  --border: rgba(15, 23, 42, 0.12);
}
* {
  box-sizing: border-box;
}
html {
  font-family: Inter, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
  color: var(--text);
  background: var(--bg);
}
body {
  margin: 0;
  background: var(--bg);
}
button,
input,
select,
textarea {
  font: inherit;
}
button {
  cursor: pointer;
  border: none;
  border-radius: 999px;
  padding: 10px 18px;
  background: var(--primary);
  color: white;
  transition: background 0.2s ease, transform 0.2s ease;
}
button:hover {
  background: var(--primary-dark);
  transform: translateY(-1px);
}
a {
  color: inherit;
  text-decoration: none;
}
main.container {
  max-width: 1100px;
  margin: 0 auto;
  padding: 28px 20px;
  min-height: calc(100vh - 180px);
}
h2 {
  margin: 0 0 18px;
  color: var(--text);
}
p,
label,
li {
  color: var(--muted);
}
input,
select,
textarea {
  width: 100%;
  padding: 12px 14px;
  border: 1px solid var(--border);
  border-radius: 12px;
  background: #fff;
  margin-top: 8px;
}
textarea {
  min-height: 110px;
  resize: vertical;
}
.card,
.product-card {
  background: var(--surface);
  border: 1px solid var(--border);
  border-radius: 18px;
  box-shadow: 0 18px 40px rgba(15, 23, 42, 0.06);
  padding: 22px;
  margin-bottom: 22px;
}
.product-card h3 {
  margin-top: 0;
}
.error-list {
  list-style: none;
  padding: 0;
  margin: 0 0 16px;
  color: #dc2626;
}
@media (max-width: 768px) {
  main.container {
    padding: 20px 14px;
  }
  button {
    width: 100%;
  }
}
</style>