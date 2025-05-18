# API Backend

API Backend que é responsável por guardar as informações dos clientes.

## Tecnologias utilizadas

* Spring Boot
* Java 21
* MySQL
* Gradle

## Instalação

### Pré-requisitos
- Java 21 (JDK)
- MySQL 8.0 ou superior
- Gradle 8.0 ou superior

### Configuração do Ambiente

1. Clone o repositório e acesse a pasta `backend-api`

2. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `hackanjos`
   - O usuário padrão é `root` com senha `root`
     - Ou configure suas próprias credenciais no arquivo `src/main/resources/application.properties`

3. Instale as dependências:
```bash
./gradlew build
```

4. Execute a aplicação em modo desenvolvimento:
```bash
./gradlew bootRun
```

A aplicação estará disponível em `http://localhost:8080`

### Configuração do OAuth2 (Google)
Para autenticação funcionar, você precisa configurar as credenciais do OAuth2 do Google:

1. Acesse o [Google Cloud Console](https://console.cloud.google.com)
2. Crie um novo projeto ou use um existente
3. Configure as credenciais OAuth2:
   - Tipo: Aplicação Web
   - URIs de redirecionamento autorizados: `http://localhost:8080/login/oauth2/code/google`
4. Copie o Client ID e Client Secret
5. Configure no arquivo `src/main/resources/application.properties`:
```properties
spring.security.oauth2.client.registration.google.client-id=SEU_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=SEU_CLIENT_SECRET
```

## Documentação das APIs

### Autenticação
- `GET /api/auth/success` - Endpoint de sucesso na autenticação
- `GET /api/auth/user` - Retorna informações do usuário autenticado
- `GET /api/auth/logout` - Realiza o logout do usuário

### Eventos do Mouse
- `POST /api/mouseclick` - Registra um clique do mouse
  ```json
  {
    "count": 1  // Número de cliques registrados
  }
  ```
- `POST /api/mousemovement` - Registra um movimento do mouse
  ```json
  {
    "distance": 100  // Distância do movimento em pixels
  }
  ```

### Eventos do Teclado
- `POST /api/keypressed` - Registra uma tecla pressionada
  ```json
  {
    "keyCode": 65,  // Código da tecla pressionada
    "count": 1      // Número de vezes que a tecla foi pressionada
  }
  ```

### Rankings
- `GET /api/ranking/keypressed/{key}/findByKey/{intervalFilterString}` - Ranking de teclas pressionadas por tecla específica
  - `key`: Código da tecla (ex: 65 para 'A')
  - `intervalFilterString`: Intervalo de tempo (ex: "TODAY", "WEEK", "MONTH", "YEAR")
- `GET /api/ranking/keypressed/{intervalFilterString}` - Ranking geral de teclas pressionadas
  - `intervalFilterString`: Intervalo de tempo (ex: "TODAY", "WEEK", "MONTH", "YEAR")
- `GET /api/ranking/mousemovement/{intervalFilterString}` - Ranking de movimentos do mouse
  - `intervalFilterString`: Intervalo de tempo (ex: "TODAY", "WEEK", "MONTH", "YEAR")
- `GET /api/ranking/mouseclick/{intervalFilterString}` - Ranking de cliques do mouse
  - `intervalFilterString`: Intervalo de tempo (ex: "TODAY", "WEEK", "MONTH", "YEAR")
- `GET /api/ranking/user/{intervalFilterString}` - Ranking geral de usuários
  - `intervalFilterString`: Intervalo de tempo (ex: "TODAY", "WEEK", "MONTH", "YEAR")

### Conquistas
- `GET /api/achievements` - Lista todas as conquistas disponíveis

### Páginas de Erro
- `GET /api/error/unauthorized` - Página de erro para acesso não autorizado
- `GET /api/error/forbidden` - Página de erro para acesso proibido
- `GET /api/error/not-found` - Página de erro para recurso não encontrado

### Observações
- Todos os endpoints de ranking aceitam um parâmetro `intervalFilterString` que define o intervalo de tempo para o ranking
  - Valores possíveis: "TODAY", "WEEK", "MONTH", "YEAR"
- Os endpoints de eventos (mouse e teclado) requerem autenticação
- A maioria dos endpoints retorna dados em formato JSON
- Os códigos das teclas seguem o padrão JavaScript KeyCode
  - Exemplos:
    - 65: Tecla 'A'
    - 13: Tecla Enter
    - 32: Tecla Espaço
    - 8: Tecla Backspace
