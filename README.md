# customer-system

## Plano de Desenvolvimento de Estagiários/Junior (Logística):  
 
### Requisitos do projeto

Necessito que seja criada uma app completa de cadastro de customer e address. 
Cada customer poderá ter no máximo 5 addresses cadastrados 
Cada customer poderá ter apenas 1 address principal ao mesmo tempo Cada customer deverá SEMPRE ter um address principal CRUD de cadastro de customer e address Opção de tornar o address como principal Validação/Máscaras dos campos de customer (email, cpf/cnpj, tipo (PJ ou PF), endereço, telefone) 
Validação dos campos de address (rua, número, bairro, cidade, cep, estado) RESTful API  Spring Boot App 100% testada (integrados, unitários) Respeitando conceitos SOLID 
GET com paginação e filtros Versionado no github 

#### Pontos extras (considerados apenas se os pontos principais estiverem 100%) 
* Lock 
* Sugerir mudança de contrato (endpoint) - version 
* Novo cliente precisa de informação diferente do que é retornado mas precisa manter o contrato antigo 
* Evidenciar o problema do endpoint statico (Cache) Consumir endpoint público 

