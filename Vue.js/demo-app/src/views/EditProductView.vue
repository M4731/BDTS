<template>
  <div>

    <h2>Editează produs</h2>

    <ProductForm
      v-if="product"
      :initialProduct="product"
      :isEdit="true"
      @submit="updateProduct"
    />

  </div>
</template>

<script>
import ProductForm from "../components/ProductForm.vue";
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "EditProductView",

  components: {
    ProductForm
  },

  data() {
    return {
      product: null
    }
  },

  computed: {
    ...mapGetters("products", [
      "allProducts"
    ])
  },

  methods: {

    ...mapMutations("products", [
      "UPDATE_PRODUCT"
    ]),

    updateProduct(product) {

      this.UPDATE_PRODUCT(product)

      alert("Produs actualizat!")

      this.$router.push("/products")
    }
  },

  mounted() {

    const productId =
      Number(this.$route.params.id)

    this.product =
      this.allProducts.find(
        product => product.id === productId
      )
  }
}
</script>