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
  border: 1px solid rgba(15, 23, 42, 0.08);
  border-radius: 16px;
  padding: 18px;
  margin-bottom: 18px;
  background: #fff;
}
.product-card h3 {
  margin-top: 0;
  margin-bottom: 12px;
}
.product-card p {
  margin: 0 0 14px;
}
.product-card button {
  padding: 10px 16px;
  border-radius: 999px;
  background: #42b883;
  color: #fff;
  border: none;
  margin-right: 10px;
  margin-bottom: 10px;
  transition: background 0.2s ease;
}
.product-card button:hover {
  background: #2f8f6f;
}
.product-card button:last-child {
  background: #f59e0b;
}
.product-card button:last-child:hover {
  background: #d97706;
}
</style>