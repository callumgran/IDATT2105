import { createStore, Store } from 'vuex';

import { InjectionKey } from 'vue';

export interface State {
  username: string;
  token: string;
}

export const key: InjectionKey<Store<State>> = Symbol();

export default createStore<State>({
  state: {
    username: '',
    token: '',
  },
  mutations: {
    setUsername(state, username: string) {
      state.username = username;
    },
    setToken(state, token: string) {
      state.token = token;
    },
  },
  actions: {},
  modules: {},
});
