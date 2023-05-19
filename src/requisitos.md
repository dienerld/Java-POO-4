## conta
 - [x] saque
 - [x] deposito
 - [x] transferência
 - [x] saldo inicial em 0
 - [x] gerente aleatório
 - [x] idade minima 18 anos
 - [x] mínimo saque R$ 10


### corrente
 - [ ] empréstimo
 - [x] taxa de serviço
 - [ ] limite de 5 mil empréstimo
 - [ ] cheque especial - inicial 500
 - [ ] juros cheque especial

### poupança
 - [x] deposito - adicional de juros



## Classes
- [x] Pessoa {nome, cpf, nascimento} 
- [x] <Pessoa>Gerente
- [x] <Pessoa>Cliente
- [x] Conta {num, agencia, saldo}
- [x] <Conta>Conta Corrente 
- [x] <Conta>Conta Poupança

---


### Banco

#### abrirConta
    - pessoa
    - tipo da conta

    banco -> gerente | abre conta
    gerente -> criou conta {new conta()}, salva a conta como seu gerenciamento, pessoa | salvar conta 