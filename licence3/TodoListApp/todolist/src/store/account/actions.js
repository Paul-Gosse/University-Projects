import axios from 'axios';

export function signup({ commit }, values){
    axios.post('http://138.68.74.39/api/register?name='+values["name"]+'&email='+values["email"]+'&password='+values["password"])
            .then(response => {
                commit("connexion", response.data);
            })
}

export function login({ commit }, values){
    axios.post('http://138.68.74.39/api/login?email='+values["email"]+'&password='+values["password"])
            .then(response => {
                commit("connexion", response.data);
            })
}

export function logout({ commit }){
    commit("deconnexion");
}

