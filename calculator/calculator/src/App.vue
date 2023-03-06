<script lang="ts">
import Calculator from './views/Calculator.vue';
import NavBar from './components/NavBar.vue';
import ContactForm from './components/ContactForm.vue';
import { useStore } from 'vuex';
import { useStorage } from 'vue3-storage';

export default {
  name: 'app',
  components: {
    Calculator,
    ContactForm,
    NavBar,
  },

  setup() {
    const store = useStore();
    const storage = useStorage();
    if (storage.getStorageSync('token') !== undefined) {
      store.commit('setToken', storage.getStorageSync('token'));
      store.commit('setUsername', storage.getStorageSync('username'));
    }
  },
};
</script>

<style>
body {
  background: #f2f2f2;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.container {
  max-width: 560px;
  margin: 0 auto;
}
form label,
form input {
  display: block;
}
form input {
  padding: 4px;
  margin-top: 10px;
  margin-bottom: 20px;
}

button {
  background: #0ec58e;
  color: #fff;
  border: 0;
  border-radius: 6px;
  padding: 6px;
  cursor: pointer;
}
</style>

<template>
  <NavBar />
  <router-view />
  <!-- <div>
    <Calculator />
  </div> -->
</template>
