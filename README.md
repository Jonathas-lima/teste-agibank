# Processamento de Dados de Venda  
  
## Sobre  
A aplicação desenvolvida é um processador de arquivos com dados de vendas que ao final do processamento é gerado um resumo de uma análise definida na proposta de negócio. Para desenvolvimento foram utlizadas as seguintes tecnologias:  
  
- Java 8  
- Maven  
- Spring Boot  
- Spring Batch  
- JUnit  
  
## Execução Local  
### Requisitos  
- Java 8 ou superior
- Docker
- Git
  
### Execução  
1. Clonar o repositório com o seguinte comando: `https://github.com/Jonathas-lima/teste-agibank.git`  
2. Criar uma variável de ambiente HOMEPATH, onde a mesma aponta para a pasta homepath contendo os arquivos data/in e data/out
3. Conceder permissão de escrita e leitura nas pastas dentro de homepath
4. Executar o comando na raiz do projeto: /mvnw clean spring-boot:run

ps. o processador ficará executando em loop, a cada minuto, tempo que pode ser alterado no arquivo de configuração do projeto.

5. Para rodar os testes automáticos executar o comando: ./mvnw clean test

##Informações

####Logs
Os logs foram implementados utilizando OAP. Os logs de processamento serão exibidos quando o nível for DEBUG e caso haja excecção o nível é ERROR.
O nível pode ser definido alterando o arquivo de propriedades da aplicação ou passando como argumento.
ex: ./mvnw clean spring-boot:run -Dspring-boot.run.arguments=--logging.level.root=DEBUG

####Executando com o Docker
Para criação da imagem docker: docker build -t agibank .

para instanciar um container docker: docker run --name agibank-teste --mount type=bind,source="HOMEPATH",target=/homepath agibank
                                     
 Onde HOMEPATH é o diretório no host que contém os diretórios de entrada e saída.                                  
 OBS: Conceder permissão de escrita e leitura no diretório HOMEPATH  
 
  
## Exemplos  
- O arquivo de dados deve ter a extensão .dat para ser lido, as demais extensões serão ignoradas, e deve conter linhas como no exemplo abaixo:  
```  
001ç1234567891234çPedroç50000  
001ç3245678865434çPauloç40000.99  
002ç2345675434544345çJose da SilvaçRural  
002ç2345675433444345çEduardo PereiraçRural  
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro  
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo  
```  
 O mesmo deve estar dentro de `homepath/data/in`
 
 
- O resultado do processamento gerará o seguinte conteúdo em um arquivo .dat localizado em:  `homepath/data/out`  
```  
**** Relatório Análise dados****
Quantidade de clientes: 2
Quantidade de vendedores: 2
Pior vendedor: João ** Número do documento: 1234567891234
ID venda mais cara: 8
```