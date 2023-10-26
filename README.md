# Desafio Cartas

## Descrição

O Desafio Cartas consiste em criar jogadores e iniciar uma partida usando o baralho presente na API deckofcardsapi.com/api. Em seguida, verificar o jogador que tem a maior somatória do valor das cartas e mostrar o vencedor.

## Endpoints

- Criação de um novo jogador.

    - **URL**: `/player`
    - **Método**: `POST`
    - **Modelo de Requisição**:

    ```json
    {
        "name": "jogador1"
    }
    ```

  **Resposta JSON**:
    ```json
    {
        "id": 1,
        "name": "jogador1"
    }
    ```

  **Código de Sucesso**: 201 - Created

- Recuperação de informações de um jogador por ID.

    - **URL**: `/player/{playerId}`
    - **Método**: `GET`

  **Resposta JSON**:
    ```json
    {
        "id": 1,
        "name": "jogador1"
    }
    ```

  **Código de Sucesso**: 200 - OK

- Exclusão de um jogador.

    - **URL**: `/player/{playerId}`
    - **Método**: `DELETE`

  **Código de Sucesso**: 204 - No Content

- Atualização das informações de um jogador.

    - **URL**: `/player/{playerId}`
    - **Método**: `PUT`

  **Modelo de Requisição**:

    ```json
    {
        "name": "player1"
    }
    ```

  **Resposta JSON**:
    ```json
    {
        "name": "player1"
    }
    ```

  **Código de Sucesso**: 202 - Accepted

- Iniciar uma nova partida com uma lista de jogadores e distribuir 5 cartas para cada player.

    - **URL**: `/match`
    - **Método**: `POST`
    - **Modelo de Requisição**:

    ```json
    [
        {
            "id": "1"
        },
        {
            "id": "2"
        },
        {
            "id": "3"
        },
        {
            "id": "4"
        }
    ]
    ```

  **Resposta JSON**:
    ```json
    {
        "id": 1,
        "winner": null,
        "deck": {
            "deckId": "1513a1dsd"
        }
    }
    ```

  **Código de Sucesso**: 201 - Created

- Obter o vencedor de uma partida por ID.

    - **URL**: `/match/{matchId}`
    - **Método**: `GET`

  **Resposta JSON**:
    ```json
    {
        "id": 1,
        "winner": "jogador2",
        "deck": {
            "deckId": "1513a1dsd"
        }
    }
    ```

  **Código de Sucesso**: 200 - OK

