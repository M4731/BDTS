<template>
  <div class="product-card">
    <h3>{{ product.name }}</h3>

    <p>Preț: {{ product.price }} Lei</p>

    <button @click="goToDetails">
      Vezi detalii
    </button>
  </div>
</template>

<script>
export default {
  name: "ProductItem",

  props: {
    product: {
      type: Object,
      required: true
    }
  },

  methods: {
    goToDetails() {
      this.$router.push(`/products/${this.product.id}`)
    }
  }
}
</script>

<style scoped>
.product-card {
  border: 1px solid #ddd;
  border-radius: 8px;

  padding: 15px;
  margin-bottom: 15px;

  background: white;
}

button {
  padding: 8px 15px;
  cursor: pointer;
}
</style>