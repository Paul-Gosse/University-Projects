<template>
<div>
  <div>
    <p  class="mt-6 text-center text-sm font-bold text-gray-900" v-if="!connected">Non connecté</p>
    <p class="mt-6 text-center text-sm font-bold text-gray-900" v-if="connected">Connecté</p>
  </div>
  <p class="mt-5" v-bind:style="style" v-if="this.emailAdresse=='' && this.password==''"><b>Veuillez entrer votre adresse mail et votre mot de passe</b></p>
  <div class="mt-5 space-y-4 px-96" id="connection" >
    <label for="emailAdresse" class="sr-only"> email : </label>
    <input class="w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Email address" type="text" name="emailAdresse" v-model="emailAdresse" @keyup.enter="submit_login">
    <label for="password" class="sr-only" > password : </label>
    <input class="w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Password" type="password" name="password" v-model="password" @keyup.enter="submit_login">
    <button  v-on:click="submit_login" v-if="!connected" class="w-full py-2 text-sm font-medium rounded-md text-white bg-indigo-800 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-800">Connexion</button>
  </div>
  <div class="px-96" v-if="connected">
    <button v-on:click="submit_logout" class="mt-1 w-full py-2 text-sm font-medium rounded-md text-white bg-indigo-800 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-800">Déconnexion</button>
  </div>
  <div class="px-96" id="signup" v-if="!connected">
    <button class="w-full mt-1  py-2 text-sm font-medium rounded-md text-white bg-red-500 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500" >
    <router-link  to="/Create_account">Créer un compte</router-link>
    </button>
  </div>
</div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
    
export default {
        name: 'Login',
        components: {
        },
        data() {
            return {
                emailAdresse: '',
                password: '',
                name: '',
                style: ''
            }
        },
        methods: {
            ...mapActions("account",['login','logout']),
            submit_login() {
                if ((this.emailAdresse != '')&&(this.password != '')) {
                    this.login({"email":this.emailAdresse,"password":this.password});
                    this.emailAdresse = '';
                    this.password = '';
                }
                else  {
                    this.style= 'color:red; font-size: larger;';
            }

            },
            submit_logout(){
                this.logout();
            }
        },
        computed: {
            ...mapGetters("account",['getAccountToken']),
            connected(){
              return this.getAccountToken.length > 0 ? true : false;
            }
        }
    }
</script>
