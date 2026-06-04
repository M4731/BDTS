<template>
  <div>
    <ProductItem
      v-for="product in products"
      :key="product.id"
      :product="product"
    />
  </div>
</template>

<script>
import ProductItem from "./ProductItem.vue";

export default {
  name: "ProductList",

  components: {
    ProductItem
  },

  props: {
    products: {
      type: Array,
      required: true
    }
  }
}
</script>