# Desafio-Back-end-Junior

Esse é um repositório público feito para meu teste técnico em um processo seletivo.

Endpoints: <br>
http://localhost:8080/register POST - Registrar uma nova pessoa no sistema. Exemplo de payload:<br>
![image](https://user-images.githubusercontent.com/61751830/216532045-eda92ca3-11a6-4138-bdb8-a8ace66c10be.png)<br>
http://localhost:8080/login POST - Realizar Login no sistema (Deve usar BasicAuth com o Nome e Senha de usuário cadastrado)<br>
![image](https://user-images.githubusercontent.com/61751830/216532262-c756b058-d8b0-4a14-9541-f56b4c6d658e.png)<br>
http://localhost:8080/posts POST - Criar um novo post para o seu usuário. (Deve usar BasicAuth com o Nome e Senha de usuário cadastrado). Exemplo de payload:<br>
![image](https://user-images.githubusercontent.com/61751830/216532591-0030ed8a-0ee3-4cb0-9398-f34ee10f7b0e.png)<br>
http://localhost:8080/posts/{id} PUT - Atualizar as informações do post. (Deve usar BasicAuth com o Nome e Senha de usuário utilizados para criar o post). Exemplo de payload: <br>
![image](https://user-images.githubusercontent.com/61751830/216533025-64fba6c0-599d-434e-a981-45b079eac8b9.png)<br>
http://localhost:8080/posts GET - Visualizar todos os posts criados(Deve usar BasicAuth com o Nome e Senha de usuário cadastrado). <br>
http://localhost:8080/posts/{id} GET - Retornar um post específico(Deve usar BasicAuth com o Nome e Senha de usuário cadastrado). <br>
http://localhost:8080/posts/{id} DELETE - Deletar um post (Deve usar BasicAuth com o Nome e Senha de usuário utilizado para criar o post).
