export default {
  namespaced: true,

  state() {
    return {
      products: [
        {
          id: 1,
          name: "Laptop Asus",
          price: 3500,
          category: "Electronice"
        },
        {
          id: 2,
          name: "Mouse Logitech",
          price: 120,
          category: "Accesorii"
        },
        {
          id: 3,
          name: "Monitor Samsung",
          price: 900,
          category: "Electronice"
        },
        {
          id: 4,
          name: "Keyboard Razer",
          price: 200,
          category: "Accesorii"
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
    },

    UPDATE_PRODUCT(state, updatedProduct) 
    {
      const index = state.products.findIndex(
        product => product.id === updatedProduct.id
      )

      if (index !== -1) {
        state.products.splice(
          index,
          1,
          updatedProduct
        )
      }
    },

    DELETE_PRODUCT(state, productId) 
    {
      state.products = state.products.filter(
        product => product.id !== productId
      )
    },

    SET_PRODUCTS(state, products) {
      state.products = products
    }
  },

  actions: {
    
    importProducts({ commit }, products) {
      commit(
        "SET_PRODUCTS",
        products
      )
    }
  }
}