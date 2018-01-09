## Projeto ATM ##

## Contribuidor

- Alexandre Iwamoto - alex_iwamoto@hotmail.com

## Aplicação implementada

- Aplicação que simula a entrega de notas quando um cliente efetua um saque em um caixa eletrônico.

### Requisitos Básicos:

- Entregar o menor número de notas;
- É possível sacar o valor solicitado com as notas disponíveis;
- Saldo do cliente será cadastro;
- Quantidade de notas infinito;
- Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00;
- O Cliente não poderá entrar no negativo;
- Cadastro , Edição e Exclusão de Clientes ( saldo de cada cliente será cadastrado junto );
- Saque garantir que apenas que no máximo 5 usuário realizem o saque ao mesmo tempo.

## Desenvolvimento

- Utilizado o framework SpringBoot e AngularJS para o desenvolvimento de uma aplicação simples.
- Modelagem simples, com apenas uma entidade - User, que contém os dados de validação e monetários. Para tanto utilizou-se o banco me memória H2 database.
- Utilizado o liquibase para carga inicial da tabela. (Pré-inseridos 4 usuários logados);
    - admin - senha: admin
    - William - senha: senha1
    - Carlos - senha: senha2
    - Alexandre - senha: senha3
- Testes unitários.

## Tecnologias Utilizadas:

### Back-end
- Java 8
- Maven
- Spring-boot:1.5.9.RELEASE
- Spring-web:1.5.9.RELEASE
- Spring-test:1.5.9.RELEASE
- Spring-data-jpa:1.5.9.RELEASE
- Liquibase-core:3.5.3
- Lombok:1.16.18

### Front-end
- angular: 1.6.8
- Bower
- angular-route: 1.6.8
- angular-cookie: 4.1.0
- angular-input-masks: 2.6.0
- ngSweetAlertAsPromised: 2.0.0

## Executando a aplicação

### Requistos

- NodeJS instalado para utilizar o Bower.

Para baixar as dependencias de front-end executar o comando:
```shell
cd [DIRETORIO DO PROJETO]
bower install
```

Para executar a aplicação executar o comando:
```shell
cd [DIRETORIO DO PROJETO]
mvn spring-boot:run
```

## Melhorias Futuras

- Modelagem mais avançada, separando usuário, autorização, conta e transações;
- Utilização de um banco de dados relacional (eg.: MySql);
- Utilização do Spring Security para validação da navegação;
- Melhorias no front-end;
- Implementação de testes de integração.