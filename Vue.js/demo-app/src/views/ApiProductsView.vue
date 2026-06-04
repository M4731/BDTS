<template>
  <div>

    <h2>Produse din API Axios</h2>

    <button @click="importProducts">
      Importă produsele din API în magazin
    </button>
    <br><br>
    
    <ProductList
      :products="products"
    />

  </div>
</template>

<script>
import ProductList from "../components/ProductList.vue";
import axios from "axios";

export default {
  name: "ApiProductsView",

  components: {
    ProductList
  },

  data() {
    return {
      products: []
    }
  },

  async created() {

    const response =
      await axios.get(
        "https://dummyjson.com/products"
      )

    this.products =
      response.data.products.map(product => ({
        id: product.id,
        name: product.title,
        price: product.price,
        category: product.category,
        description: product.description
      }))
  },

  methods: {

    importProducts() {
      this.$store.dispatch("products/importProducts",this.products)
      alert("Produsele au fost importate în magazin!")
    }
  },
}
</script>