export default {
  namespaced: true,

  state() {
    return {
      products: [
        {
          id: 1,
          name: "Laptop Asus",
          price: 3500
        },
        {
          id: 2,
          name: "Mouse Logitech",
          price: 120
        },
        {
          id: 3,
          name: "Monitor Samsung",
          price: 900
        },
        {
          id: 4,
          name: "Keyboard Razer",
          price: 200
        }
      ]
    }
  },

  getters: {
    allProducts(state) {
      return state.products
    }
  },

  mutations: {
    ADD_PRODUCT(state, product) {
      state.products.push(product)
    }
  },

  actions: {

  }
}