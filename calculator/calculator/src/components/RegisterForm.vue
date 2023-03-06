<template>
  <div class="form-container">
    <form @submit.prevent="register">
      <h1>Register</h1>
      <div class="form-input-container">
        <label for="username">Username:</label>
        <input data-testid="username" type="text" id="username" v-model="username" required />
      </div>
      <div class="form-input-container">
        <label for="password">Password:</label>
        <input data-testid="password" type="password" id="password" v-model="password" required />
      </div>
      <div class="form-input-container">
        <label for="rPassword">Repeat password:</label>
        <input data-testid="rPassword" type="password" id="rPassword" v-model="rPassword" required />
      </div>
      <button data-testid="submit" type="submit" id="submit" :disabled="password === '' || username === ''">
        Submit
      </button>
      <div data-testid="wrongInput" v-if="wrongInput" id="wrongInput">
        <p style="color: red">The passwords didn't match.</p>
      </div>
      <div data-testid="alreadyExists" v-if="alreadyExists" id="alreadyExists">
        <p style="color: red">Register failed, username is already taken.</p>
      </div>
    </form>
  </div>
</template>

<style>
.form-container {
  margin-top: 2%;
  display: flex;
  vertical-align: top;
  align-items: baseline;
  justify-content: center;
  height: 100vh;
}

form {
  min-width: 30vw;
  background-color: white;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.form-input-container {
  margin-bottom: 20px;
}

input {
  display: block;
  width: 100%;
  padding: 10px;
  font-size: 18px;
  border-radius: 5px;
  border: none;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

input:focus {
  background-color: #f2f2f2;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  outline: none;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

button:disabled {
  background-color: red;
  color: white;
  opacity: 0.5;
  cursor: not-allowed;
}

button:hover {
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
}

button:active {
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  transform: translateY(3px);
}
</style>

<script lang="ts">
import { ref } from 'vue';
import { registerUser } from '../api/api';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const rPassword = ref('');
    const wrongInput = ref(false);
    const alreadyExists = ref(false);
    const store = useStore();
    const router = useRouter();

    const register = async () => {
      wrongInput.value = false;
      alreadyExists.value = false;
      if (password.value !== rPassword.value) {
        wrongInput.value = true;
        return;
      }

      const token = await registerUser(username.value, password.value);
      if (token !== undefined) {
        store.commit('setToken', token.token);
        store.commit('setUsername', username.value);
        router.push({ name: 'Calculator' });
      } else {
        alreadyExists.value = true;
      }
    };

    return {
      username,
      password,
      rPassword,
      wrongInput,
      alreadyExists,
      register,
    };
  },
};
</script>
