<template>
  <div class="card">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "BaseCard"
}
</script>

<style scoped>
.card {
  background: #fff;
  border: 1px solid rgba(15, 23, 42, 0.08);
  border-radius: 18px;
  padding: 22px;
  margin-top: 15px;
  box-shadow: 0 14px 30px rgba(15, 23, 42, 0.06);
}
</style>