<template>
  <div>
    <h2>Produse</h2>

    <ProductList :products="allProducts" />

  </div>
</template>

<script>
import ProductList from "../components/ProductList.vue";
import { mapGetters } from "vuex";

export default {
  name: "ProductsView",

  components: {
    ProductList
  },

  computed: {
    ...mapGetters("products", [
      "allProducts"
    ])
  }
}
</script>