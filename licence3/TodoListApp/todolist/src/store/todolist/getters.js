export function getAccountUserName(state) {
    return state.data["name"];
}

export function getAccountTodolist(state) {
    return state.todolist;
}

export function getAccountTodolistTask(state) {
    return state.todolisttask;
}

export function getAccountData(state) {
    return state.data;
}


