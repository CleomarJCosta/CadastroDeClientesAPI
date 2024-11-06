API Cadastro de Clientes :

Tecnologias usadas :
  - Spring boot 2.7.12
  - Spring data JPA
  - Spring Security
  - PostgresSQL
  - Token JWT
  - swagger
  - Lombok
  - Postman para testes de requisições

Passos para usar a api.

* Criar um usuario com o seguinte endpoint : http://localhost:8080/registrar
![image](https://github.com/user-attachments/assets/6535b1a9-ba7b-42db-81d7-292fa579108e)

* Realizar o login no usuario registrado acima que você criou : http://localhost:8080/login
![image](https://github.com/user-attachments/assets/bc6a5c9e-a9ef-4a55-85b6-ef69e60aafd6)

* Para criar usuario e login não necessita de autenticação e autorização. Demais operações necessita de autorização e autenticação
  
*  Ao logar na api será gerado um token que você usará no local da figura abaixo no postman :
  ![image](https://github.com/user-attachments/assets/438e3913-edbd-4abe-a3b9-1db6cfa9d26b)

*Listar todos os usuarios : http://localhost:8080/usuarios
  ![image](https://github.com/user-attachments/assets/b77809b2-45e5-4663-9488-fe80b46cc5cc)

* Listar Usuario por id :  http://localhost:8080/usuarios/id
 ![image](https://github.com/user-attachments/assets/a9b06566-7293-4ce2-8d02-9923088548b1)

* Deletar usuario . Você só pode deletar seu próprio usuário :http://localhost:8080/usuarios/id
  ![image](https://github.com/user-attachments/assets/1b543f60-2d79-47c9-9c0f-33a6a34fd973)

*Buscar cliente por id :http://localhost:8080/api/clientes/id
![image](https://github.com/user-attachments/assets/8f0c3224-3cec-44eb-957c-9b3385bee671)

* Atualizar Cliente :http://localhost:8080/api/clientes/id
  ![image](https://github.com/user-attachments/assets/c71984b4-4db8-4159-8a47-575d68932e8d)

* Deletar Cliente : http://localhost:8080/api/clientes/id
  ![image](https://github.com/user-attachments/assets/d483e7cc-c9e9-4d52-b4cf-28159c31b80d)

* Buscar Todos Clientes  normalmente sem ordernar por nome : http://localhost:8080/api/clientes
  ![image](https://github.com/user-attachments/assets/297168ef-ed37-4966-9993-8eb78ec5883b)

* Buscar Todos Clientes  normalmente sem ordernar por nome : http://localhost:8080/api/clientes/ordenado
  ![image](https://github.com/user-attachments/assets/2e0f3e71-26ee-4f8c-a7d2-8df8337bd3af)

* Buscar Todos os clientes por estado : http://localhost:8080/api/clientes/estado/PR
  ![image](https://github.com/user-attachments/assets/7786e168-6957-4a3e-9bfa-6aa964a1a3cc)


A Api conta com a rastreabilidade de alterações e criações automática :
![image](https://github.com/user-attachments/assets/d025fb7d-414e-49c5-ad25-db0d92d92f89)




Recursos : 
 - Collection de teste segue na raiz do projeto.
 - Documentação Swwager : http://localhost:8080/swagger-ui/index.html









