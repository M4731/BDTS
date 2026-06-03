<template>

  <div>
    <button @click="showPopup" > {{ buttonText }} </button>
  </div>

  <div v-if="isPopupVisible" class="popup">
    <p>{{ message }}</p>
    <button @click="closePopup">Închide</button>
  </div>


</template>


<script>

export default {
  name: "ButtonWithPopup",
  props: {
    message: {
      type: String,
      required: true
    },
    buttonText: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      isPopupVisible: false
    };
  },
  methods: {
    showPopup() {
      this.isPopupVisible = true;
    },
    closePopup() {
      this.isPopupVisible = false;
    }
  }
};

</script>

<style scoped>
  /* Stiluri pentru buton */
  button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  }
  button:hover {
  background-color: #0056b3;
  }
  /* Stiluri pentru popup */
  .popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  }
  .popup p {
  margin-bottom: 20px;
  font-size: 18px;
  }
</style>