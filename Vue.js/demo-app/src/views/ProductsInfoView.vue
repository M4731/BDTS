<template>
  <div>
    <h3>Informații magazin</h3>
    <p>Avem {{ productCount }} produse.</p>
  </div>
</template>

<script>
export default {
  computed: {
    productCount() {
      return this.$store.state.products.products.length
    }
  }
}
</script>