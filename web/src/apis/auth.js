import axios from 'axios';

export function authUserApi(username, password) {
    return axios.post('/api/auth/signin', { username, password })
}