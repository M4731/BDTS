<template>
  <div class="product-card">
    <h3>{{ product.name }}</h3>

    <p>Preț: {{ product.price }} Lei</p>

    <button @click="goToDetails">
      Vezi detalii
    </button>

    <button @click="goToEdit">
      Editează
    </button>

    <button @click="deleteProduct">
      Șterge
    </button>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "ProductItem",

  props: {
    product: {
      type: Object,
      required: true
    }
  },

  methods: {
    ...mapMutations("products", [
      "DELETE_PRODUCT"
    ]),

    goToDetails() {
      this.$router.push(`/products/${this.product.id}`)
    },

    goToEdit() {
      this.$router.push(
        `/products/${this.product.id}/edit`
      )
    },

    deleteProduct() {
      this.DELETE_PRODUCT(this.product.id);
    }

  },
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