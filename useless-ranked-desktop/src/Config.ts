import { app } from 'electron';

export const API_URL = app.isPackaged ? 'https://minha-api-em-producao.com/api': 'http://localhost:8080/api'; 