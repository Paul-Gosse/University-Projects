import { createStore } from 'vuex'

import account from './account';
import todolist from './todolist';

export default createStore({
  debug : true,
  modules: {
    account,
    todolist    
  },
  state: {
  },
  mutations: {
  },
  actions: {
  }
})


