<template>
  <div>
    <p class="mt-5" v-if="!create"><b>Veuillez compléter le formulaire suivant</b></p>
    <p class="mt-5" v-if="create"><b>Compte déjà créé</b></p>
  </div>
  <div class="mt-5 space-y-4 px-96"  id="create_account" >
    <label class="sr-only" for="name"> name : </label>
    <input class="w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Nom" type="text" name="Nom" v-model="name">
    <label class="sr-only" for="emailAdresse"> email : </label>
    <input class="w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Adresse e-mail" type="text" name="emailAdresse" v-model="emailAdresse">
    <label class="sr-only"  for="password"> password : </label>
    <input class="w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Mot de passe" type="password" name="password" v-model="password">
    <button class="w-full mt-1  py-2 text-sm font-medium rounded-md text-white bg-red-500 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"  v-on:click="submit_signup">Créer compte</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
        name: 'Create_account',
        components: {
        },
        data() {
            return {
                emailAdresse: '',
                password: '',
                name: '',
            }
        },
        methods: {
            ...mapActions("account",['signup']),
            submit_signup() {
                if ((this.emailAdresse != '')&&(this.password != '')) {
                    this.signup({"name":this.name,"email":this.emailAdresse,"password":this.password});
                }
            }
        },
        computed: {
            ...mapGetters("account",['getAccountToken']),
            create(){
              return this.getAccountToken.length > 0 ? true : false;
            }
        }
    }
</script>
