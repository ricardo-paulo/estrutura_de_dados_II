# Trabalho para Análise de HashTables

Este é um trabalho bimestral proposto na disciplina Estrutura de Dados II, do curso Análise e Desenvolvimento de Sistemas do IFTO - *Campus* Araguaína.

# Conceito

Criar uma aplicação simples, de linha de comando, que implemente conceitos de HashTable e permita realizar análises como: impacto de diferentes funções de espalhamento e estratégias de resolução de colisões no desempenho da HashTable.

# Restrições

Não é permitido o uso de bibliotecas ou quaisquer outras estruturas de dados prontas para este fim. A única exceção é o uso de ArrayLists apenas para manipulação de dados, não para uso na estrutura da HashTable.

# Integrantes

- Lidia Cruz de Araújo
- Paulo Ricardo Rodrigues Silva

# Modo de Utilização

## Pacote JAR

Para utilizar o pacote JAR, basta baixar a versão mais recente e executar o seguinte comando no terminal, no mesmo diretório do arquivo *.jar*:

```bash
java -jar *nome_do_pacote*.jar
```

## Código Fonte

Para utilização da aplicação diretamente com o código fonte, é necessário fazer o clone do repositório, importar as dependências utilizando Gradle e rodar o seguinte comando no terminal, dentro da raiz do projeto:

```bash
.\gradlew run
```

# Definições

## Fonte de dados

A única fonte de dados se dá através do arquivo CSV encaminhado. Que é essencialmente um dicionário de inglês com três colunas: palavra, função e definição.

O arquivo foi convertido no formato JSON, para maior praticidade na leitura do arquivo via código.

## Objetos e atributos

| Node | DictionaryLists |
| --- | --- |
| chave | palavras |
| função | funções |
| definição | definições |

## Algoritmos utilizados

- Método da Multiplicação; e
- Algoritmo DJB2.

# Tarefas

- [x]  Converter o arquivo CSV para JSON.
- [x]  Tradução do código base.
- [x]  Leitura da Base de Dados.
- [x]  Fazer o código base utilizar os dados reais para gerar as HashTables.
- [x]  Criar método para carregar determinado percentual, passado por parâmetro, dos dados e retorná-los.
- [x]  Criar método para obter o tempo de execução de uma operação (busca, inserção e deleção) da tabela.
- [x]  Implementar as duas funções hashing de modo separado. (Ex. cada método é um método separado dentro do código).
    - [x]  Método da Multiplicação.
    - [x]  Algoritmo DJB2.
- [x]  Implementar um método para rehashing.
    - [x]  Criar forma de ser identificada a necessidade de rehashing quando for inserido um novo elemento.
    - [x]  Criar método que redimensiona a tabela, redistribui (reinsere) os elementos nessa tabela nova e substitui a tabela antiga pela tabela nova (com o dobro do tamanho da antiga).
    - [x]  Adaptar o rehashing para fazer a redistribuição em endereçamento aberto.
- [x]  Implementar resolução de colisões.
    - [x]  Encadeamento Separado (listas encadeadas); e
    - [x]  Endereçamento Aberto (por sondagem linear ou sondagem quadrática).
- [x]  Calcular fator de carga e exibir no terminal.
- [x]  Obter o tempo de busca e de inserção da tabela hash quando ela estiver 50% e 90% cheia. Ou seja quando estiver com metade dos dados carregados e quando estiver com 90% dos dados carregados.
- [x]  Criar método para retorno de quantas colisões ocorreram. Incluir contador logo no método de inserção e armazenar a contagem em uma variável privada da classe.