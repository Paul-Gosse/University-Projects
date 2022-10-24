export function connexion(state, data) {
    state.token = data["token"];
}

export function deconnexion(state) {
    state.token = []
}
