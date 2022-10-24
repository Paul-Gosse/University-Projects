export function loadUser(state, data) {
    state.data = data;
}

export function loadTodolist(state, data) {
    state.todolist = data;
}

export function loadTodoInTodolist(state, data){
    state.todolisttask = data;
}
