<template>
  <div>
    <h2>Adaugă produs</h2>

      <ul>
        <li
          v-for="error in errors"
          :key="error"
        >
          {{ error }}
        </li>
      </ul>


    <form @submit.prevent="submitForm">

      <div>
        <label>Nume produs</label>
        <br>
        <input type="text" v-model="name">
      </div>

      <br>
      <div>
        <label>Preț</label>
        <br>
        <input type="number" v-model="price">
      </div>

      <br>
      <div>
        <label>Categorie</label>
        <br>
        <input
          type="text" v-model="category">
      </div>

      <br>
      <div>
        <label>Descriere</label>
        <br>
        <textarea v-model="description"></textarea>
      </div>

      <br>
      <button type="submit">
        Salvează
      </button>

    </form>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "ProductFormView",

  data() {
    return {
      name: "",
      price: "",
      category: "",
      description: "",
      errors: []
    }
  },

  methods: {
    ...mapMutations("products", [
      "ADD_PRODUCT"
    ]),

    submitForm() {

      this.errors = []

      if (!this.name) {
        this.errors.push("Numele este obligatoriu")
      }

      if (!this.price) {
        this.errors.push("Prețul este obligatoriu")
      }

      if (!this.category) {
        this.errors.push("Categoria este obligatorie")
      }

      if (this.errors.length > 0) {
        return
      }

      const newProduct = {
        id: Date.now(),
        name: this.name,
        price: Number(this.price),
        category: this.category,
        description: this.description
      }

      this.ADD_PRODUCT(newProduct)

      alert("Produs adăugat!")

      this.name = ""
      this.price = ""
      this.category = ""
      this.description = ""

      this.$router.push("/products")
    }
  }
}
</script>