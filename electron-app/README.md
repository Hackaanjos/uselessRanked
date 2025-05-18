# UselessRanked - Aplicativo Desktop

Aplicativo desktop desenvolvido em Electron que coleta dados do usuário e os armazena em um banco de dados SQLite. Periodicamente, envia os dados para a API backend para atualização do ranking.

## Tecnologias Utilizadas

- Electron
- SQLite
- Node.js
- TypeScript

### API Local (SQLite)

O aplicativo utiliza um banco de dados SQLite local para armazenar os dados do usuário. Os dados são sincronizados periodicamente com o backend.

### API Backend

O aplicativo se comunica com a API backend para:
- Enviar dados do usuário
- Atualizar o ranking
- Sincronizar informações

Para mais detalhes sobre as APIs, consulte a documentação do backend.

## Instalação

1. Clone o repositório e acesse a pasta `electron-app`

3. Execute o aplicativo em modo de desenvolvimento
```bash
npm run start
```

4. Para criar o executável
```bash
npm run make
```
