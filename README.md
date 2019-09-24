# Desafio B2W

## Sumário <!-- omit in toc -->

- [Desafio B2W](#desafio-b2w)
    - [Descrição](#descri%C3%A7%C3%A3o)
    - [Requisitos:](#requisitos)
    - [Funcionalidades desejadas](#funcionalidades-desejadas)
    - [Tecnologias utilizadas](#tecnologias-utilizadas)
    - [Instalação](#instala%C3%A7%C3%A3o)
        - [Baixando os arquivos da aplicação](#baixando-os-arquivos-da-aplica%C3%A7%C3%A3o)
        - [Banco de Dados mongodb](#banco-de-dados-mongodb)
        - [Aplicação](#aplica%C3%A7%C3%A3o)
            - [Diretamente pelo Maven](#diretamente-pelo-maven)
    - [API Rest](#api-rest)
        - [Adicionando um novo planeta](#adicionando-um-novo-planeta)
        - [Listando todos os planetas](#listando-todos-os-planetas)
        - [Buscando por nome](#buscando-por-nome)
        - [Buscando por ID](#buscando-por-id)
        - [Remover o planeta](#remover-o-planeta)
        - [Formato do Erro da API](#formato-do-erro-da-api)
        - [Resumo da API](#resumo-da-api)

## Descrição

Criar um jogo com algumas informações da franquia.

Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas. 

## Requisitos:

- A API deve ser REST
- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:

    ```
    Nome
    Clima
    Terreno
    ```
- Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars:  https://swapi.co/

## Funcionalidades desejadas

- Adicionar um planeta (com nome, clima e terreno)
- Listar planetas
- Buscar por nome
- Buscar por ID
- Remover planeta

## Tecnologias utilizadas

Nessa solução foi utilizada as seguintes tecnologias:

- Java 1.8
- Mogodb 4.2
- Maven Warp 3.6.2
- SpringBoot 2.1.0
- Postman para os testes de integração

## Instalação

Antes da instalação se pressupõe que o java 1.8 e o git já estão instalados, se não estiverem devem ser instalados antes de continuar

### Baixando os arquivos da aplicação

Para baixar os arquivos do aplicativo deve-se usar o comando:  

`git clone https://github.com/rdrg14/desafioB2W-StarWarsAPI.git`

Depois entrar na pasta do aplicativo:

`cd rest_StarWarsPlanetas`

Os demais comandos a seguir devem ser feito a partir da pasta do aplicativo

### Banco de Dados mongodb

O aplicativo está considerando que o mongodb local estará na sua porta padrão (27017), se não estiver
será necessário alterar a porta na linha `spring.data.mongodb.port=27017` no arquivo `src/main/resources/application.properties`

### Aplicação

Para executar a aplicação:

#### Diretamente pelo Maven

Para executar a aplicação diretamente pelo maven use o comando:

>No linux:  
> `./mvnw clean spring-boot:run`  
>
>No windows:  
>`mvnw.cmd clean spring-boot:run`  

## API Rest

### Adicionando um novo planeta

Para adicionar um novo planeta deve-se enviar um `POST` para o caminho `/api/planetas/`, com o seguinte formato:

```json
{
    "nome": "<NOME>",
    "clima": "<CLIMA>",
    "terreno": "<TERRENO>"
}
```

Todos os campos são obrigatórios, o campo nome deve ser único, ou seja, não pode existir na base de dados e também deve ser um nome de planeta pertencente a franquia *Star Wars*.

### Listando todos os planetas

Para listar todos os planetas cadastrados deve-se enviar um `GET` para o caminho `/planetas/`


E vai retornar uma lista de planetas no formato

```json
{
    "content": [
        {"id": "<id>", "nome": "<NOME>", "clima": "<CLIMA>", "terreno": "<TERRENO>", "filmes": "<QUANTIDADE DE APARIÇÕES EM FILMES>"},
        {"id": "<id>", "nome": "<NOME>", "clima": "<CLIMA>", "terreno": "<TERRENO>", "filmes": "<QUANTIDADE DE APARIÇÕES EM FILMES>"},
        {"id": "<id>", "nome": "<NOME>", "clima": "<CLIMA>", "terreno": "<TERRENO>", "filmes": "<QUANTIDADE DE APARIÇÕES EM FILMES>"},
        ...
    ]
}
```

### Buscando por nome

Para procurar por planetas por nome, deve-se enviar um `GET` para o caminho `/planetas?nome=<NOME>`, onde `<NOME>` é o nome do planeta desejado.  
Essa busca precisa do nome completo do planeta e não faz diferenciação de maiúsculas e minúsculas (case insensitive).

E retorna uma lista de planetas encontrados, no mesmo formato que a listagem dos planetas.

### Buscando por ID

Para procurar por planetas por id, deve-se enviar um `GET` para o caminho `/planetas/<ID>`, onde `<ID>` é o id do planeta desejado.  
E retorna o planeta desejado, no formato;

```json
{
    "id": "<ID>",
    "nome": "<NOME>",
    "clima": "<CLIMA>",
    "terreno": "<TERRENO>",
    "filmes": <QUANTIDADE DE APARIÇÕES EM FILMES>
}
```

### Remover o planeta

Para remover um planeta, deve-se enviar um `DELETE` para o caminho `/planetas/<ID>`, onde `<ID>` é o id do planeta desejado. Ou um `DELETE` para o caminho `/planetas?nome=<NOME>`, onde nome é o nome do planeta. 
E não retorna dado algum.


### Formato do Erro da API

Os erros retornados por essa API segue o seguinte formato:

```json
{
    "timestamp": "<DATA DO ERRO NO FORMATO YYYY-MM-DD HH:MM:SS>",
    "status": "CÓDIGO DO ERRO",
    "error": "<DESCRIÇÃO DO ERRO>",
	"errors": "<LISTA DE ERROS>"
    "message": "<MENSAGEM DE ERRO>",
    "path": "<CAMINHO CHAMADO>"
}
```

Nem todos os campos são retornados nas resposta com erros

### Resumo da API

|Ação|Caminho|Parâmetros do Request|Retorno|
|----|-------|---------------------|-------|
|Criar um planeta| POST /planetas/|`{ "nome": "<NOME>",  "clima": <CLIMA>",  "terreno": "<TERRENO>"  }`|`{ "id": <ID>,  "nome": "<NOME>",  "clima": <CLIMA>",  "terreno": "<TERRENO>",  "filmes" <NUM. FILMES> }`|
|Listar todos os planetas| GET /planetas/||`{"content": [{ "id": <ID>,  "nome": "<NOME>",  "clima": <CLIMA>",  "terreno": "<TERRENO>",  "filmes" <NUM. FILMES> }, ... ]`|
|Buscar planetas por nome| GET /planetas?nome=`<NOME>`||`{"content": [{ "id": <ID>,  "nome": "<NOME>",  "clima": <CLIMA>",  "terreno": "<TERRENO>",  "filmes" <NUM. FILMES> }]|
|Buscar planetas por id| GET /planetas/`<ID>`||`{ "id": <ID>,  "nome": "<NOME>",  "clima": <CLIMA>",  "terreno": "<TERRENO>",  "filmes" <NUM. FILMES> }`|
|Apagar planeta por nome|DELETE /planetas?nome=`<NOME>`|||
|Apagar um planeta por id| DELETE /planetas/`<ID>`|||


