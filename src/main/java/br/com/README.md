<img src="https://github.com/S4-2024/crud_academia/blob/master/images/Cópia%20de%20Cópia%20de%20ESTRUTURAS%20DE%20DADOS%20E%20ALGORITMOS%20(1).png">

<h3 align="center"> 
  
  [![principal](https://img.shields.io/badge/principal-558BB3?style=for-the-badge&logo=github)](https://github.com/S4-2024/crud_academia) 
</h3>

### ｡𖦹° Padrão DAO

O projeto "CRUD Academia" faz uso do padrão de design DAO (Data Access Object) para abstrair e encapsular o acesso aos dados, proporcionando uma interface simplificada para operações de persistência em um banco de dados. Esse padrão permite separar a lógica de negócios da lógica de acesso a dados, garantindo que as operações no banco de dados sejam realizadas de forma desacoplada do restante da aplicação.

### ｡𖦹° Estrutura do DAO no Projeto

No "CRUD Academia", o padrão DAO é aplicado para gerenciar a interação com o banco de dados para diversas entidades, como Cliente, Funcionário, Avaliação, entre outras. A estrutura do DAO no projeto é organizada da seguinte maneira:

1. **Interfaces DAO**: Definem os métodos que serão implementados pelas classes DAO, padronizando a forma de interação com os dados.
2. **Classes DAO**: Implementam as interfaces DAO e contêm a lógica específica para acessar e manipular os dados no banco de dados, seguindo as regras de negócios estabelecidas.
3. **Modelos**: Representam as entidades do banco de dados como objetos Java, facilitando o mapeamento entre o banco de dados e a aplicação.

Dessa forma, o projeto mantém um design modular e escalável, facilitando a manutenção e a evolução do sistema.







<details>
<summary> <h2> 🔹 Estrutura do Projeto </h2></summary>



- **Raiz do Projeto**
  - `.gitignore`: Arquivo para ignorar arquivos/diretórios no controle de versão.
  - `pom.xml`: Arquivo de configuração do Maven.
  - `banco_dados/`: Diretório contendo scripts SQL para o banco de dados.
    - `banco_dados_crud.sql`: Script para criar o banco de dados.
    - `bd_preenchido.sql`: Script para preencher o banco de dados com dados de exemplo.
  - `src/main/java/br/com/`: Diretório principal do código-fonte Java.
    - `Main.java`: Classe principal para execução do projeto.
    - `dao/`: Diretório contendo as classes DAO.
      - **AgendamentosDAO.java**: Classe DAO para agendamentos.
      - **AvaliacaoDAO.java**: Classe DAO para avaliações.
      - **ClienteDAO.java**: Classe DAO para clientes.
      - **ExerciciosDAO.java**: Classe DAO para exercícios.
      - **ExerciciosFichaClienteDAO.java**: Classe DAO para exercícios de ficha de cliente.
      - **FichasClienteDAO.java**: Classe DAO para fichas de cliente.
      - **FuncionarioDAO.java**: Classe DAO para funcionários.
    - `enums/`: Diretório contendo enums utilizadas no projeto.
      - **Categoria.java**: Enum para categorias.
      - **Pagamento.java**: Enum para tipos de pagamento.
      - **Sexo.java**: Enum para sexos.
    - `interfaces/`: Diretório contendo as interfaces DAO.
      - **IAgendamentosDAO.java**: Interface para AgendamentosDAO.
      - **IAvaliacaoDAO.java**: Interface para AvaliacaoDAO.
      - **IClienteDAO.java**: Interface para ClienteDAO.
      - **IExerciciosDAO.java**: Interface para ExerciciosDAO.
      - **IExerciciosFichaClienteDAO.java**: Interface para ExerciciosFichaClienteDAO.
      - **IFichasClienteDAO.java**: Interface para FichasClienteDAO.
      - **IFuncionarioDAO.java**: Interface para FuncionarioDAO.
    - `models/`: Diretório contendo os modelos de dados.
      - **Cliente.java**: Modelo para clientes.
      - **Funcionario.java**: Modelo para funcionários.
      - **Pessoa.java**: Modelo para pessoas.
    - `testes/`: Diretório contendo classes de teste.
      - **AdicionarCliente.java**: Classe de teste para adicionar cliente.
      - **AdicionarFuncionario.java**: Classe de teste para adicionar funcionário.
      - **DeleteCliente.java**: Classe de teste para deletar cliente.
      - **Listagens.java**: Classe de teste para listar entidades.
      - **UpdateCliente.java**: Classe de teste para atualizar cliente.
  - `src/main/resources/`: Diretório contendo recursos do projeto.
    - `META-INF/maven/archetype.xml`: Arquivo de configuração do Maven Archetype.
    - `archetype-resources/`: Diretório contendo arquivos de recursos do Maven Archetype.
      - `pom.xml`: Arquivo de configuração do Maven.
      - `src/main/java/App.java`: Aplicação principal exemplo.
      - `src/test/java/AppTest.java`: Teste para a aplicação exemplo.
  - `.idea/`: Diretório de configuração do IntelliJ IDEA.

  
</details>
