<template>
  <div>
    <ul>
      <li
        v-for="error in errors"
        :key="error"
      >
        {{ error }}
      </li>
    </ul>

    <form @submit.prevent.stop="submitForm">

      <div>
        <label>Nume produs</label>
        <br>
        <input
          ref="nameInput"
          type="text"
          v-model="product.name"
        >
      </div>

      <br>

      <div>
        <label>Preț</label>
        <br>
        <input
          type="number"
          step="0.01"
          v-model="product.price"
        >
      </div>

      <br>

      <div>
        <label>Categorie</label>
        <br>
        <input
          type="text"
          v-model="product.category"
        >
      </div>

      <br>

      <div>
        <label>Descriere</label>
        <br>
        <textarea
          v-model="product.description"
        ></textarea>
      </div>

      <br>

      <button type="submit">
        {{ isEdit ? "Actualizează" : "Salvează" }}
      </button>

    </form>
  </div>
</template>

<script>
export default {
  name: "ProductForm",

  props: {
    initialProduct: {
      type: Object,
      required: true
    },

    isEdit: {
      type: Boolean,
      default: false
    }
  },

  data() {
    return {
      product: {
        ...this.initialProduct
      },

      errors: []
    }
  },

  methods: {
    submitForm() {

      this.errors = []

      if (!this.product.name) {
        this.errors.push("Numele este obligatoriu")
      }

      if (!this.product.price) {
        this.errors.push("Prețul este obligatoriu")
      }

      if (!this.product.category) {
        this.errors.push("Categoria este obligatorie")
      }

      if (this.errors.length > 0) {
        return
      }

      this.$emit("submit", this.product)
    }
  },

  //cand apare elementul in DOM, focus pe nameInput
  mounted() {
    this.$nextTick(() => {
      const el = this.$refs.nameInput
      if (el) {
        el.focus()
        if (typeof el.select === "function") el.select()
      }
    })
  }
}
</script>