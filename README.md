# Trabalho para Análise de HashTables

Este é um trabalho bimestral proposto na disciplina Estrutura de Dados II, do curso Análise e Desenvolvimento de Sistemas do IFTO - *Campus* Araguaína.

# Conceito

Criar uma aplicação simples, de linha de comando, que implemente conceitos de HashTable e permita realizar análises como: impacto de diferentes funções de espalhamento e estratégias de resolução de colisões no desempenho da HashTable.

# Restrições

Não é permitido o uso de bibliotecas ou quaisquer outras estruturas de dados prontas para este fim. A única exceção é o uso de ArrayLists apenas para manipulação de dados, não para uso na estrutura da HashTable.

# Integrantes

- Lidia Cruz de Araújo
- Paulo Ricardo Rodrigues Silva

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

## Código

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

## Análise

- [ ]  Fazer leitura do Fator de Carga quando *alfa* for maior que 1 e analisar o impacto disso no encadeamento.
- [ ]  Fazer leitura do tempo de busca em uma tabela 50% e 90% cheia.
- [ ]  Fazer leitura do tempo de inserção de uma tabela 50% e 90% cheia.
- [ ]  Gerar gráfico simples comparando o número de colisões que ocorrem utilizando os dois métodos de hashing e incluir ao documento, explicando brevemente o que se vê.
- [ ]  Explicar como a tabela deve se comportar para realizar o Rehash (redimensionamento) quando o fator de carga atingir um fator limite crítico arbitrário (Ex.: *alfa*=0,75).
- [ ]  Comparar os tempos de busca, remoção e inserção de uma tabela de dispersão que utiliza lista encadeada (como método de resolução de colisões) e a função hashing que teve melhor resultado.