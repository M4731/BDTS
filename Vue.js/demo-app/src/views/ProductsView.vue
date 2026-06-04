<template>
  <div>
    <h2>Produse</h2>

    <div>
      <input type="text" placeholder="Caută produs..." v-model="searchTerm">

      <select v-model="sortOption">
        <option value="">Fără sortare</option>
        <option value="name">Nume A-Z</option>
        <option value="priceAsc">Preț crescător</option>
        <option value="priceDesc">Preț descrescător</option>
      </select>
    </div>

    <ProductList :products="filteredProducts" />
  </div>

  <router-view />
</template>

<script>
import ProductList from "../components/ProductList.vue";
import { mapGetters } from "vuex";

export default {
  name: "ProductsView",

  components: { ProductList },

  data() {
    return {
      searchTerm: "",
      sortOption: ""
    }
  },

  computed: {
    ...mapGetters("products", ["allProducts"]),

    filteredProducts() {
      let products = [...this.allProducts]

      if (this.searchTerm) {
        products = products.filter(product =>
          product.name.toLowerCase().includes(this.searchTerm.toLowerCase())
        )
      }

      if (this.sortOption === "name") {
        products.sort((a, b) => a.name.localeCompare(b.name))
      }

      if (this.sortOption === "priceAsc") {
        products.sort((a, b) => a.price - b.price)
      }

      if (this.sortOption === "priceDesc") {
        products.sort((a, b) => b.price - a.price)
      }

      return products
    }
  }
}
</script>