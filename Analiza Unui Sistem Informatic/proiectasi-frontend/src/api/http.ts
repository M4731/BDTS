import axios from 'axios';

const baseURL = (import.meta.env.VITE_API_BASE_URL?.toString().trim() || 'http://localhost:8080').replace(/\/+$/, '');

export const http = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
});
