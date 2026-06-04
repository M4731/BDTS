<template>
  <div>
    <h2>Detalii produs</h2>

    <p>ID produs: {{ productId }}</p>
  </div>
</template>

<script>
export default {
  name: "ProductDetailView",

  computed: {
    productId() {
      return this.$route.params.id
    }
  }
}
</script>