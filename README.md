# Microservico-rabbitMQ
Criação de um microserviço do cliente ao consumidor utilizando rabbitMQ e JAVA.


# Como utilizar?

###  ConsumerSuscription

- "Bom, este nada mais é que o nosso microserviço, ele quem irá consumir as mensagens da fila do RabbitMQ e salvará automaticamente no banco H2, um banco de dados a nível de memória."

Acessar Console H2
server.port=8049
path:/h2
jdbc_url: jdbc:h2:mem:fed4f1f7-b59c-46bb-8438-c4ba815bfc85🏻

---
<br>


###  SubscriptionAPI

- "Como o próprio nome ja diz, essa será nossa Api que receberá as requisições do cliente e direcionará para uma fila através da exchange."

server.port=8030

#### exemplo de requisição (POST):

{
    "name": "eduarda maria almeida",               (obrigatório)
    "subscriptionNumber": 10,                      (opcional)
    "date": "10/12/2022"                           (opcional)
}

---
<br>

###  librabbitmq

- "Esta é apenas uma lib que foi criada para evitar replicação de código ja que a classe e constantes armazenadas nesta lib são utilizadas tanto pela api quanto pelo microserviço"

Caso não esteja sendo encontrada pelo arquivo de configuração apenas utilize o comando abaixo no diretório em que esta localizada:
- mvn install 

---
<br>

###  rabbitMQ

- "Aqui fica o arquivo de configuração do Docker para que seja levantado um contâiner para o rabbitMq e possa ser feita a conexão com a api e consumidor"

Para levantar o container basta acessar o diretório do arquivo e mandar o comando abaixo:
- docker compose up -d

Acessar o console do RabbitMq:
url : localhost:15672
user: admin
password:admin

OBS:
" -d para detach, separar o painel de comando do contâiner"


