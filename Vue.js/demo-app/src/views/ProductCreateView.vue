<template>
  <div>
    <h2>Adaugă produs</h2>

    <ProductForm
      :initialProduct="newProduct"
      @submit="createProduct"
    />
  </div>
</template>

<script>
import ProductForm from "../components/ProductForm.vue";
import { mapMutations } from "vuex";

export default {
  name: "ProductCreateView",

  components: {
    ProductForm
  },

  data() {
    return {
      newProduct: {
        name: "",
        price: "",
        category: "",
        description: ""
      }
    }
  },

  methods: {

    ...mapMutations("products", [
      "ADD_PRODUCT"
    ]),

    createProduct(product) {

      const newProduct = {
        ...product,
        id: Date.now(),
        price: Number(product.price)
      }

      this.ADD_PRODUCT(newProduct)

      alert("Produs adăugat!")

      this.$router.push("/products")
    }
  }
}
</script>



<style>
</style>