<template>
  <div>
    <h2>Detalii produs</h2>
    <BaseCard v-if="product">
      <h3>Detalii produs</h3>
      <p><strong>ID:</strong> {{ product.id }}</p>
      <p><strong>Nume:</strong> {{ product.name }}</p>
      <p><strong>Preț:</strong> {{ product.price }} Lei</p>
      <p><strong>Categorie:</strong> {{ product.category }}</p>
      <p><strong>Descriere:</strong> {{ product.description }}</p>
    </BaseCard>
    <div v-else>Produsul nu există.</div>
  </div>
</template>

<script>
import BaseCard from "../components/BaseCard.vue";
import { mapGetters } from "vuex";

export default {
  name: "ProductDetailView",

  components: { BaseCard },

  computed: {
    ...mapGetters("products", ["allProducts"]),
    
    product() {
      const productId = Number(this.$route.params.id);
      return this.allProducts.find(product => product.id === productId);
    }
  }
}
</script>