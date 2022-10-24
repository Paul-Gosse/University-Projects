import axios from 'axios';

export function getUser({ commit }, values){
    axios.get('http://138.68.74.39/api/user',{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(response => {
                commit("loadUser", response.data);
            })
}

export function getUserTodolist({ commit }, values){
    axios.get('http://138.68.74.39/api/todolists',{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(response => {
                commit("loadTodolist", response.data);
            })
}

export function getUserTodoInTodolist({ commit }, values){
    axios.get('http://138.68.74.39/api/todos/'+values["id"],{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(response => {
                commit("loadTodoInTodolist", response.data);
            })
}

export function creatUserTodolist({ commit }, values){
    axios.post('http://138.68.74.39/api/todolist?name='+values["name"],null,{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todolists',{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodolist", response.data);
                    })
            )
}

export function deleteUserTodolist({ commit }, values){
    axios.delete('http://138.68.74.39/api/todolist/'+values["id"],{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todolists',{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodolist", response.data);
                    })
            )
}


export function creatUserTodoInTodolist({ commit }, values){
    axios.post('http://138.68.74.39/api/todo?name='+values["name"]+'&completed='+values["completed"]+'&todolist_id='+values["todolistid"],null,{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todos/'+values["todolistid"],{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodoInTodolist", response.data);
                    })
            )
            
}

export function deleteUserTodoInTodolist({ commit }, values){
    axios.delete('http://138.68.74.39/api/todo/'+values["id"],{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todos/'+values["todolistid"],{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodoInTodolist", response.data);
                    })
            )
}

export function changeCompleteTodoInTodolist({ commit }, values){
    axios.post('http://138.68.74.39/api/completeTodo/'+values["id"]+'?name='+values["name"]+'&completed='+values["completed"]+'&todolist_id='+values["todolistid"],null,{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todos/'+values["todolistid"],{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodoInTodolist", response.data);
                    })
            )
    }

export function modifyTodoInTodolist({ commit }, values){
    axios.patch('http://138.68.74.39/api/todo/'+values["id"]+'?name='+values["name"]+'&completed='+values["completed"]+'&todolist_id='+values["todolistid"],null,{headers: {'Authorization': 'Bearer '+values["token"]}})
            .then(
                axios.get('http://138.68.74.39/api/todos/'+values["todolistid"],{headers: {'Authorization': 'Bearer '+values["token"]}})
                    .then(response => {
                        commit("loadTodoInTodolist", response.data);
                    })
            )
}