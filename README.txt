Projeto de Gestão de Vendas
Descrição
Este é um projeto Java para gerenciar vendedores e departamentos, com persistência dos dados utilizando MySQL. O sistema faz uso da biblioteca JDBC para comunicação com o banco de dados e está sendo implementado através do padrão DAO (Data Access Object), permitindo a realização de operações de CRUD (Create, Read, Update, Delete) tanto para vendedores (“seller”) quanto para departamentos (“department”).

Funcionalidades
Cadastro, consulta, atualização e remoção de vendedores (Seller).
Cadastro, consulta, atualização e remoção de departamentos (Department).
Arquitetura baseada em DAO para facilitar a manutenção e a separação das regras de acesso ao banco.
Integração com banco de dados MySQL utilizando JDBC.
Pré-requisitos
Para executar o projeto corretamente, é necessário:

Java (JDK 8 ou superior)
MySQL Workbench instalado no computador
MySQL Server em execução
Biblioteca JDBC para MySQL (geralmente mysql-connector-java)
Script de criação do banco de dados disponível na pasta database ou informado no projeto
Instalação e Execução
Baixe o Projeto

Clone ou faça o download deste repositório para sua máquina.
Configure o Banco de Dados

Abra o MySQL Workbench.
Execute o script SQL fornecido na pasta /database para criar as tabelas e inserir dados iniciais no banco. (AINDA VOU ADICIONAR O SCRIPT)
Edite as Configurações de Conexão com o Banco

Altere as configurações de usuário, senha e nome do banco no arquivo de configuração do projeto (exemplo: db.properties ou dentro do código).
Adicione o Driver JDBC ao Projeto

Se estiver usando uma IDE como IntelliJ ou Eclipse, adicione o arquivo JAR do mysql-connector-java ao seu projeto.
Compile e Execute

Compile o projeto Java.
Execute o arquivo principal (Main.java, ou como for nomeado em seu projeto).
Estrutura do Projeto
src/
|-- application/         # Classe principal e métodos utilitários
|-- model/               # Entidades do sistema (Department, Seller)
|-- model/dao/           # Implementação dos DAOs

Principais Classes
Department: representa um departamento.
Seller: representa um vendedor.
DAO (DepartmentDao, SellerDao): realiza as operações de acesso ao banco de dados para cada entidade.
Como contribuir
Sinta-se à vontade para fazer um fork, abrir issues ou propor melhorias através de Pull Requests!

Observações
O sistema está em desenvolvimento, então novas funcionalidades e ajustes podem ser implementados em breve.
Certifique-se de que o MySQL Server está em funcionamento antes de executar o projeto.
