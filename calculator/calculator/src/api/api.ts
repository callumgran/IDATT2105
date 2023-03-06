import axios from "axios";

const BASE_API_URL = "http://localhost:8888/api";

export const registerUser = async (name: string, password: string) => {
    return await axios.post(`${BASE_API_URL}/auth/register`, {
        username: name,
        password: password,
    }).then((response) => {
            return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}

export const loginUser = async (username: string, password: string) => {
    return axios.post(`${BASE_API_URL}/auth/authenticate`, {
        username: username,
        password: password,
    }).then((response) => {
            return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}

export const calculate = async (token: string, equation: string) => {
    return axios.post(`${BASE_API_URL}/calculate`, {
        equation: equation,
    },
    {
        headers: {
            Authorization: `Bearer ${token}`
        },
    }).then((response) => {
            return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}

export const getHistory = async (token: string) => {
    return axios.get(`${BASE_API_URL}/calculate`, {
        headers: {
            Authorization: `Bearer ${token}`
        },
    }).then((response) => {
            return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}
