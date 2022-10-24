<template>
    <h1 class=" mb-8 flex justify-center tracking-wider font-thin items-center font-sans text-6xl subpixel-antialiased"><svg class="transform hover:scale-110 hover:text-green-500 transition duration-800 ease-in-out focus:outline-none" width=60 height=60 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" /></svg>Todolist</h1>
  <div id="nav" class="space-x-4 font-extralight">
    <router-link class="px-5 inline-flex block uppercase mx-auto shadow bg-indigo-800 hover:bg-indigo-700 focus:shadow-outline focus:outline-none text-white text-xs py-3 px-10 rounded" to="/">Accueil</router-link>
    <router-link class="px-5 inline-flex block uppercase mx-auto shadow bg-indigo-800 hover:bg-indigo-700 focus:shadow-outline focus:outline-none text-white text-xs py-3 px-10 rounded" to="/login">Se connecter</router-link>
  </div>
  <router-view/>

  
</template>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
ul {
  list-style-type: none;
}
</style>
