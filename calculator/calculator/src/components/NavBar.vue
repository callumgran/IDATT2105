<template>
  <div>
    <nav>
      <h1>Calculator App</h1>
      <div id="welcome" v-if="loggedIn">Welcome {{ store.state.username }}</div>
      <div>
        <router-link to="/">Home</router-link>
        <router-link v-if="loggedIn" to="calculator"> Calculator </router-link>
        <router-link to="contact"> Contact </router-link>
        <router-link v-if="!loggedIn" to="login"> Login </router-link>
        <router-link v-if="!loggedIn" to="register"> Register </router-link>
        <router-link to="login" id="logout" v-if="loggedIn" @click="logout">Logout</router-link>
      </div>
    </nav>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { watchEffect } from 'vue';
import { useStore } from 'vuex';
import { useStorage } from 'vue3-storage';

export default {
  setup() {
    const router = useRouter();
    const store = useStore();
    const storage = useStorage();
    const loggedIn = ref(store.state.token !== '');

    watchEffect(() => {
      loggedIn.value = store.state.token !== '';
    });

    const logout = () => {
      store.commit('setToken', '');
      store.commit('setUsername', '');
      storage.removeStorageSync('token');
      storage.removeStorageSync('username');
      router.push({ name: 'Home' });
    };

    return {
      loggedIn,
      store,
      logout,
    };
  },
};
</script>

<style>
nav {
  display: flex;
  align-items: center;
}
nav h1 {
  margin-right: auto;
  margin-bottom: 0;
}
#welcome {
  margin-right: 16px;
}
nav a {
  margin-left: 16px;
  color: #2c3e50;
}
nav button {
  margin-left: 16px;
}
nav a.router-link-exact-active {
  color: #0ec58e;
}
nav + p {
  margin-top: 0;
  margin-bottom: 30px;
}
#logout {
  cursor: pointer;
  border: 2px solid #0ec58e;
  border-radius: 10px;
  padding: 10px;
}

#logout:hover {
  opacity: 0.5;
}
</style>
