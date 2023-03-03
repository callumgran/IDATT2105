<template>
  <div class="form-container">
    <form @submit.prevent="submitForm">
      <h1>Send feedback</h1>
      <div class="form-input-container">
        <label for="name">Name:</label>
        <input data-testid="name" type="text" id="name" v-model="name" required />
      </div>
      <div class="form-input-container">
        <label for="email">Email:</label>
        <input data-testid="email" type="email" id="email" v-model="email" required />
      </div>
      <div class="form-input-container">
        <label for="message">Message:</label>
        <textarea data-testid="message" id="message" v-model="message" required></textarea>
      </div>
      <button data-testid="submit" type="submit" id="submit" :disabled="email === '' || name === '' || message == ''">
        Submit
      </button>
      <div data-testid="success" v-if="success" id="success">
        <p>Thank you for your feedback!</p>
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

input,
textarea {
  display: block;
  width: 100%;
  padding: 10px;
  font-size: 18px;
  border-radius: 5px;
  border: none;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

input:focus,
textarea:focus {
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
import axios from 'axios';

export default {
  setup() {
    const name = ref('');
    const email = ref('');
    const message = ref('');
    const success = ref(false);

    const submitForm = async () => {
      const response = await axios
        .post('https://jsonplaceholder.typicode.com/posts', {
          name: name.value,
          email: email.value,
          message: message.value,
        })
        .then(() => {
          success.value = true;
        })
        .catch((error) => {
          console.log(error);
        });

      console.log(response);
    };

    return {
      name,
      email,
      message,
      success,
      submitForm,
    };
  },
};
</script>
