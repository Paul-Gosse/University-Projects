<template>

    <button v-on:click="submit_getUserData" class="block uppercase mx-auto shadow px-4 mt-4 transition duration-500 ease-in-out bg-blue-400 hover:bg-blue-600 transform hover:-translate-y-1 hover:scale-110 text-white text-xs py-3 px-10 rounded ">Recharger vos données</button>
    <p v-if="!connected" class="my-6 mt-6 animate-bounce font-extrabold" >Vous devez vous connecter</p>
    <p class="my-6 font-semibold" v-if="connected ">Vous êtes connecté {{user}}</p>
    <div id="Todo" class="flex flex-wrap overflow-hidden divide-x-4 divide-gray ">
        <div id="List_todo" class="w-full overflow-hidden xl:w-1/2" >
            <ul>
                <li class=" flex justify-center mt-4  " v-for="elem_list_todo in getTodolist" :key="elem_list_todo.id"  >
                    <button class=" focus:outline-none transition duration-500 ease-in-out bg-white-600 hover:bg-gray-200 transform hover:-translate-y-1 hover:scale-110 mr-2 rounded-full py-2 px-3 border-black-500 border-2 focus:ring-2 focus:ring-indigo-800    " v-on:click="selectTodoList(elem_list_todo.id)"><todolist class="cursor-pointer"  :id="elem_list_todo.id" :name="elem_list_todo.name" ></todolist></button>
                    <button class="self-centerfocus:outline-none text-black text-sm py-2  px-1 rounded-md  hover:bg-red-600 hover:shadow-lg" v-on:click="deleteTodolist(elem_list_todo.id)"> <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" /></svg>
                    </button>
                </li>
            </ul>
            <div class="mt-4 flex justify-center" >
            <label  class="mt-2 mr-0.5" for="newTodoListName">Nom de la liste : </label>
            <input class="mr-1  w-36 focus:outline-none shadow-sm rounded-full py-2 px-3 border-black-500 border-2" type="text" name="newTodoListName" v-model="newTodoListName">
            <button class="transform hover:scale-110 transition duration-500 ease-in-out focus:outline-none text-black-500"  v-on:click="createTodoList"><svg width="25" height="25" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" /></svg></button>
            </div>
        </div>
        <div  id="List_task" class="w-full overflow-hidden xl:w-1/2">
            <div id="filters" class="mt-10 relative inline-flex mb-5 space-x-2 ">
                <a id="filter1"  class="focus:ring-2 focus:ring-red-800 rounded shadow-md block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 border-2 border-black-300" href="#" :class="{selected: filter === 'all'}" v-on:click="filter = 'all'">Toutes</a>
                <a id="filter2" class="focus:ring-2 focus:ring-red-800 rounded shadow-md block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 border-2 border-black-300" href="#" :class="{selected: filter === 'todo'}" v-on:click="filter = 'todo'">A faire</a>
                <a id="filter3" class="focus:ring-2 focus:ring-red-800 rounded shadow-md block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900 border-2 border-black-300" href="#" :class="{selected: filter === 'done'}" v-on:click="filter = 'done'">Faites</a>
            </div>
            <br>
            <input type="checkbox" class="" v-on:change="allDone()" v-model="allDoneBool">
            <span class="pl-4" id="toggle_all" v-show="allDoneBool"> Tout déselectionner</span>
            <span class="pl-4" id="toggle_all" v-show="!allDoneBool"> Tout sélectionner</span>
            <br>
            <span id="tache_a_faire"><strong>{{remaining}}</strong> tâches à faire</span>
            <ul class="mx-24 pb-4  focus:outline-none  rounded py-2 px-3 border-black-500 border-2">
                <p class="mt-2 opacity-75" v-if="this.idTodoSelected<=0">Aucune liste sélectionnée</p>
                <li class="" v-for="todo in filteredTodos" :key="todo.id" :style="colorStyle(todo.completed)">
                    <div class="flex items-baseline justify-start space-y-2  ">
                        <input class="mr-6" type="checkbox" v-model="todo.completed" v-on:click="changeBool(todo)">
                        <listask class="" :id="todo.id" :completed="todo.completed==1?true:false" :task="todo.name"></listask>
                        
                        <input class=" ml-1 mr-1 border-gray border-2" v-if="edit" type="text" name="modifyTodoName" v-model="modifyTodoName">
                        
                        <button v-if="edit" class="focus:outline-none text-black text-sm py-2.5 px-1 rounded-md bg-black-500  hover:bg-black-600 hover:shadow-lg" v-on:click="modifyTodoNameAction(todo)"><svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor"><path d="M13.586 3.586a2 2 0 112.828 2.828l-.793.793-2.828-2.828.793-.793zM11.379 5.793L3 14.172V17h2.828l8.38-8.379-2.83-2.828z" /></svg></button>
                        <button class="focus:outline-none text-black text-sm py-2  px-1 rounded-md  hover:bg-red-600 hover:shadow-lg" v-on:click="deleteTodoElement(todo.id)"> <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" /></svg> </button>
                        
                    </div>
                </li>
            </ul>
            <div >
                <div class="  ml-24 flex mb-5 space-x-2">
                    <div class="mt-4 flex items-center">
                        <label class="" for="newTodoName">Tâche : </label>
                        <input class=" ml-1 mr-1 border-gray border-2" type="text" name="newTodoName" v-model="newTodoName">
                        <button  class="transform hover:scale-110 transition duration-500 ease-in-out focus:outline-none rounded-full bg-black-500 hover:bg-black-600 hover:shadow-lg" v-on:click="createTodo(this.idTodoSelected)"><svg width="25" height="25" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" /></svg></button>
                    </div>
                    
                    <button class="  mt-4 rounded bg-indigo-800 hover:bg-indigo-700 focus:ring-2 focus:ring-indigo-800 shadow-md block px-4 py-2 text-sm text-gray-100 " v-on:click="showEdit()">Modifier tâches</button>
                    <button class=" mt-4 rounded bg-indigo-800 hover:bg-indigo-700 focus:ring-2 focus:ring-indigo-800 shadow-md block px-4 py-2 text-sm text-gray-100" v-on:click="deleteCompleted">Supprimer les tâches finies</button>
                </div>
            </div>
            <br>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

import todolist from '@/components/sidebar.vue';
import listask from '@/components/sidebaritem.vue';

export default {
        name: 'Todo',
        components: {
            todolist,
            listask
        },
        data() {
            return {
                idTodoSelected: -1,
                newTodoListName: '',
                newTodoName: '',
                currentUser: '',
                modifyTodoName: '',
                allDoneBool:false,
                filter:'all',
                edit:'false'
            }
        },
        methods: {
            ...mapActions("todolist",['getUser','getUserTodolist','getUserTodoInTodolist','creatUserTodolist','deleteUserTodolist','creatUserTodoInTodolist','deleteUserTodoInTodolist','changeCompleteTodoInTodolist','modifyTodoInTodolist']),
            submit_getUserData(){
                if (this.connected) {
                    this.getUser({"token":this.getUserToken});
                    this.getUserTodolist({"token":this.getUserToken});
                    this.getUserTodoInTodolist({"id":this.idTodoSelected,"token":this.getUserToken});
                }
            },
            selectTodoList(elem){
                this.idTodoSelected = elem;
                this.getUserTodoInTodolist({"id":this.idTodoSelected,"token":this.getUserToken});
                this.edit = false;
            },
            createTodoList(){
                if(this.newTodoListName != '' && this.connected){
                    this.creatUserTodolist({"name":this.newTodoListName,"token":this.getUserToken});
                    this.newTodoListName = '';
                }
            },
            deleteTodolist(id){
                this.deleteUserTodolist({"id":id,"token":this.getUserToken});
                this.idTodoSelected = -1;
            },
            createTodo(){
                if(this.newTodoName != '' && this.connected){
                    this.creatUserTodoInTodolist({"name":this.newTodoName,"completed":0,"todolistid":this.idTodoSelected,"token":this.getUserToken});
                    this.newTodoName = '';
                }
            },
            deleteTodoElement(id){
                this.deleteUserTodoInTodolist({"id":id,"token":this.getUserToken});
            },
            colorStyle(bool) {
                return bool ? "color:green" : "color:red";
            },
            changeBool(todo){
                todo.completed = todo.completed? false:true;
                this.changeCompleteTodoInTodolist({"id":todo.id,"name":todo.name,"completed":todo.completed?1:0,"todolistid":this.idTodoSelected,"token":this.getUserToken});
            },
            modifyTodoNameAction(todo){
                this.modifyTodoInTodolist({"id":todo.id,"name":this.modifyTodoName,"completed":todo.completed?1:0,"todolistid":this.idTodoSelected,"token":this.getUserToken});
                this.modifyTodoName='';
                
            },
            allDone(){
                this.getTodotask.forEach(todo => todo.completed = this.allDoneBool);
            },
            deleteCompleted(){
                this.getTodotask.filter(todo => todo.completed).forEach(todo => this.deleteTodoElement(todo.id));
            },
            showEdit(){
                this.edit=!this.edit;
            }
        },
        computed: {
            ...mapGetters("account",['getAccountToken']),
            ...mapGetters("todolist",['getAccountUserName','getAccountTodolist','getAccountTodolistTask']),
            connected(){
                return this.getAccountToken.length > 0 ? true : false;
            },
            getUserToken(){
                return this.getAccountToken;
            },
            user(){
                return this.getAccountUserName;
            },
            getTodolist(){
                return this.getAccountTodolist;
            },
            filteredTodos() {
                if (this.idTodoSelected != -1) {
                    if (this.filter === 'todo') {
                        return this.getTodotask.filter(todo => !todo.completed)
                    } else if (this.filter === 'done') {
                        return this.getTodotask.filter(todo => todo.completed)
                    }
                    return this.getTodotask;
                }
                return [];
            },
            getTodotask(){
                return this.getAccountTodolistTask;
            },
            iscompleted(todo){
                return todo.completed==1?true:false;
            },
            remaining() {
                return this.getTodotask.filter(todo => !todo.completed).length;
            }
        }
    }
</script>

<style>
@tailwind base;
@tailwind components;
@tailwind utilities;

/* .todo {padding: 5px; margin:2px; border: 1px solid black; padding: 7px; padding-left: 30px;padding-right: 30px; border-radius: 3px;}
.todo:hover {background: linear-gradient(#e8e8e8 , #f1f1f1 , #e8e8e8 );}
#Todo {width: 70%; margin: 0 auto;display:grid; grid-template-columns: 1fr 1fr;}
#List_todo ul li{margin:10px;}
#List_task ul li{text-align: left;}
#List_task input{margin-right: 30px}
#List_todo {padding-right: 20px;}
#List_task {border-left: 3px solid black}
#List_todo input{
    margin-right: 30px;
}
#toggle_all{
    padding-right: 10px;
}
#tache_a_faire{
    padding-left: 10px;
    border-left: 1px solid black;
}
#filter1{
    padding-right: 10px;
    border-right: 1px solid black;
}
#filter2{
    padding-right: 10px;
    padding-left: 10px;
    border-right: 1px solid black;
}
#filter3{
    padding-left: 10px;
} */


</style>
