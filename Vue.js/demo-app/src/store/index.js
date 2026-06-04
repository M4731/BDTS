import { createStore } from "vuex";

import products from "./modules/products";
import user from "./modules/user";

export default createStore({
  modules: {
    products,
    user
  }
});