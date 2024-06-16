# CRUD Academia

Este projeto implementa um sistema CRUD (Create, Read, Update, Delete) para gerenciar uma academia. O sistema é desenvolvido em Java e utiliza uma estrutura baseada em DAO (Data Access Object) para gerenciar as operações no banco de dados.

## Visão Geral

O sistema permite a gestão de clientes, funcionários, agendamentos, avaliações, exercícios e fichas de clientes. A estrutura do projeto inclui modelos, interfaces, DAOs e scripts SQL para a criação e preenchimento do banco de dados.

## Estrutura do Projeto

A estrutura do projeto é a seguinte:

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
      - **CartoesCreditoDAO.java**: Classe DAO para cartões de crédito.
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
      - **ICartoesCreditoDAO.java**: Interface para CartoesCreditoDAO.
      - **IClienteDAO.java**: Interface para ClienteDAO.
      - **IExerciciosDAO.java**: Interface para ExerciciosDAO.
      - **IExerciciosFichaClienteDAO.java**: Interface para ExerciciosFichaClienteDAO.
      - **IFichasClienteDAO.java**: Interface para FichasClienteDAO.
      - **IFuncionarioDAO.java**: Interface para FuncionarioDAO.
    - `models/`: Diretório contendo os modelos de dados.
      - **CartaoCredito.java**: Modelo para cartões de crédito.
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

## Banco de Dados

O projeto inclui dois scripts SQL no diretório `banco_dados/`:

1. `banco_dados_crud.sql`: Cria as tabelas necessárias no banco de dados.
2. `bd_preenchido.sql`: Preenche as tabelas com dados de exemplo.


## Introdução ao Padrão DAO
O padrão DAO (Data Access Object) é um padrão de design que abstrai e encapsula o acesso a dados, oferecendo uma interface simples para executar operações de persistência em um banco de dados. Ele separa a lógica de negócios da lógica de acesso a dados, permitindo que as operações no banco de dados sejam realizadas de maneira desacoplada do restante da aplicação.

### Estrutura do DAO no Projeto

No projeto "CRUD Academia", o padrão DAO é utilizado para gerenciar a interação com o banco de dados para diferentes entidades, como Cliente, Funcionário, Avaliação, etc. A estrutura do DAO no projeto é composta pelas seguintes partes principais:

1. **Interfaces DAO**: Definem os métodos que serão implementados pelas classes DAO.
2. **Classes DAO**: Implementam as interfaces DAO e contêm a lógica para acessar e manipular os dados no banco de dados.
3. **Modelos**: Representam as entidades do banco de dados como objetos Java.

### Interfaces DAO

As interfaces DAO definem os métodos que devem ser implementados pelas classes DAO para cada entidade. Elas garantem que todas as classes DAO sigam um contrato consistente, facilitando a manutenção e a extensibilidade do código.

Exemplo de interface DAO para Cliente:

```java
package br.com.interfaces;

import br.com.models.Cliente;
import java.util.List;

public interface IClienteDAO {
    void adicionarCliente(Cliente cliente);
    Cliente buscarClientePorId(int id);
    List<Cliente> listarClientes();
    void atualizarCliente(Cliente cliente);
    void deletarCliente(int id);
}
```

## Como Executar o Projeto

1. **Pré-requisitos**:
   - JDK 8 ou superior.
   - Maven.
   - Um banco de dados SQL (por exemplo, MySQL).

2. **Configuração do Banco de Dados**:
   - Execute o script `banco_dados/banco_dados_crud.sql` para criar as tabelas.
   - Execute o script `banco_dados/bd_preenchido.sql` para preencher o banco de dados com dados de exemplo.

3. **Compilar e Executar**:
   - Navegue até o diretório do projeto.
   - Execute `mvn clean install` para compilar o projeto.
   - Execute `java -cp target/crud_academia-master-1.0-SNAPSHOT.jar br.com.Main` para iniciar a aplicação.

## Conclusão

Este projeto fornece uma implementação básica de um sistema CRUD para uma academia, utilizando Java e uma arquitetura baseada em DAOs. A estrutura modular facilita a manutenção e expansão do sistema conforme necessário.
