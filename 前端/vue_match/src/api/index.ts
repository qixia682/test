import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080', // 你的API基础地址
});

export default http;
