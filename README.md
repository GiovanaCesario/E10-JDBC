# Atividade JDBC

## Instruções

Implemente uma classe para representar cada uma das seguintes entidades:
- Banco
- Agência
- Conta
- Cliente

### Relações

As seguintes relações devem estar evidentes:
- **Banco x Agência**: 1-N
- **Agência x Conta**: 1-N
- **Conta x Cliente**: N-M

### Métodos

Além dos *getters* e *setters* tradicionais, adicione métodos para fazer as associações e desassociações entre as entidades.

### Persistência

Implemente os DAOs necessários para realizar a persistência das entidades no banco de dados.

### Banco de Dados

- Caso queira montar o banco de dados usando o arquivo Docker disponibilizado, fique à vontade.
- Caso prefira usar seu próprio banco de dados, envie o arquivo **Dockerfile** para que ele possa ser testado.
