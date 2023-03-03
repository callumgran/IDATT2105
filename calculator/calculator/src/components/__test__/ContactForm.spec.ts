import ContactFormVue from "../ContactForm.vue";
import { flushPromises, mount } from "@vue/test-utils";
import { describe, test, expect, beforeEach, vi } from "vitest";
import { createStore, Store } from 'vuex'
import axios from "axios";

describe("Contact Form", () => {

  let store: Store<{ feedback: { name: string; email: string; }; }>;

  beforeEach(() => {
    store = createStore({
      state: {
        feedback: {
          name: "",
          email: "",
        },
      },
    })
  })

  test("Form displays name, email and message inputs", () => {
    const wrapper = mount(ContactFormVue, {
      global: {
        plugins: [store]
      }
    });
    const nameInput = wrapper.find('[data-testid="name"]');
    const emailInput = wrapper.find('[data-testid="email"]');
    const messageInput = wrapper.find('[data-testid="message"]');
    expect(nameInput.exists()).toBe(true);
    expect(emailInput.exists()).toBe(true);
    expect(messageInput.exists()).toBe(true);
  });

  test("Form displays submit button", () => {
    const wrapper = mount(ContactFormVue, {
      global: {
        plugins: [store]
      }
    });
    const submitButton = wrapper.find('[data-testid="submit"]');
    expect(submitButton.exists()).toBe(true);
  });
});