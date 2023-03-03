<template>
  <div>
    <nav>
      <h1>Calculator App</h1>
      <div>
        <router-link to="/">Home</router-link>
        <router-link to="calculator"> Calculator </router-link>
        <router-link to="contact"> Contact </router-link>
      </div>
    </nav>
  </div>
</template>

<script lang="ts">
import { useRouter } from 'vue-router';
import { watchEffect } from 'vue';

export default {
  setup() {
    const router = useRouter();
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
</style>
