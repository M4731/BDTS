<!-- <template>
  <div>
    <h1>{{ title }}</h1>
    <p>{{ description }}</p>
    <p>{{ concatTitleAndHeader }}</p>

    <input v-model="title" placeholder="Schimba titlul aici" />
    <input v-model="description" placeholder="Schimba desc aici" />
    <button @click="schimbaText">Schimba textul</button>


    <button  @click="increaseClickCount">{{ count_clicks }}</button>

    <ButtonWithPopup
      message="Acesta este un mesaj din componenta copil!"
      buttonText="Arata Popup"
    />

    <ButtonWithPopup
      message="ALT MESAJ DIN COMPONENTA COPIL!"
      buttonText="Arata Popup nr 2"
    />

  </div>

</template>

<script>
import ButtonWithPopup from "./components/ButtonWithPopup.vue";
export default{
  components:{
    ButtonWithPopup
  },
  data(){
    return{
      title:"My first Vue APP",
      description:"This is my first Vue app",
      count_clicks: 0
    }
  },

  methods:{
    schimbaText(){
      this.title="Textul a fost schimbat"
      this.description="Desc schimbata"
    },
    increaseClickCount(){
      this.count_clicks++;
    }
  },

  computed:{
    concatTitleAndHeader(){
      return this.title + " - " + this.description;
    }
  },

  watch:{
    count_clicks(newValue){
      if(newValue > 10){
        alert("Ai apasat butonul de prea multe ori!");
        this.count_clicks = 0;
      }
    }
  },

  mounted(){
    console.log("Componenta a fost montata");
  }
};
</script>


<style>
div {
  font-family: Arial, sans-serif;
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h1 {
  color: #333;
  margin-bottom: 10px;
}

p {
  color: #666;
  line-height: 1.6;
}

input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

button {
  background-color: #42b983;
  color: white;
  padding: 10px 20px;
  margin: 10px 5px 10px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #36a372;
}
</style> -->


<template>
  <form @submit.prevent="validateForm">
    <label for="name">Nume:</label>
    <input type="text" placeholder="Nume" v-model="name" />
    <p v-if="nameError"> {{ nameError }} </p>

    <label for="email">Email:</label>
    <input type="text" placeholder="Email" v-model="email" />
    <p v-if="errorMail"> {{ errorMail }} </p>

    <button type="submit">Submit</button>
  </form>

</template>

<script>
  export default 
  {
    data() 
    {
      return {
        name: "",
        email: "",
        errorMail: "",
        nameError: ""
      };
    },

    methods: 
    {
      submit() {
        // Logica pentru trimiterea datelor formularului
        console.log("Nume:", this.name);
        console.log("Email:", this.email);
      },
      validateForm() 
      {
        this.errorMail = "";
        this.nameError = "";
        if (this.name === "" || this.email === "") {
          this.errorMail = "Toate câmpurile sunt obligatorii.";
          this.nameError = "Numele este obligatoriu.";
        } else {
          this.submit();
        }
      }
    }
  };
</script>

<style>
</style>