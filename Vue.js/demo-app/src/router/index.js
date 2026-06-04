import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import ProductsView from '../views/ProductsView.vue'
import ProductDetailView from '../views/ProductDetailView.vue'
import ProductCreateView from '../views/ProductCreateView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import EditProductView from "../views/EditProductView.vue";
import ApiProductsView from "../views/ApiProductsView.vue";
import ProductsInfoView from "../views/ProductsInfoView.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/products',
    name: 'Products',
    component: ProductsView,
    children: [
      {
        path: "info",
        component: ProductsInfoView
      }
    ]
  },
  {
    path: '/products/new',
    name: 'ProductForm',
    component: ProductCreateView
  },
  {
    path: '/products/:id',
    name: 'ProductDetail',
    component: ProductDetailView
  },
  {
    path: '/products/:id/edit',
    name: 'EditProduct',
    component: EditProductView
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/api/products',
    name: 'ApiProducts',
    component: ApiProductsView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router