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
1. Clonar o repositório: `https://github.com/Jonathas-lima/teste-agibank.git`  
2. Criar um diretório com o nome `data` em sua home.
3. Criar um diretório `in` dentro da pasta do passo 1 e salvar o arquivo com dados de teste nela.
3. Conceder permissão de escrita e leitura nas pastas criadas
4. Executar o comando na raiz do projeto: `./mvnw clean spring-boot:run`

ps. o processador ficará executando em loop, a cada minuto, tempo que pode ser alterado no arquivo de configuração do projeto.

5. Para rodar os testes automáticos executar o comando: `./mvnw clean test`

#### Executando com o Docker
1. Entrar no diretório raiz do projeto
1. Compilar o projeto com o comando: `./mvnw clean package`

2. Para criação da imagem docker: `docker build -t agibank .`

3. para instanciar um container docker: `docker run --name agibank-teste --mount type=bind,source="$HOME/data",target=/root/data/ agibank`

## Informações

#### Logs
Os logs foram implementados utilizando OAP. Os logs de processamento serão exibidos quando o nível for `DEBUG` e caso haja excecção o nível é `ERROR`.
O nível pode ser definido alterando o arquivo de propriedades da aplicação ou passando como argumento.
ex: `./mvnw clean spring-boot:run -Dspring-boot.run.arguments=--logging.level.root=DEBUG`

  
#### Exemplos  
- O arquivo de dados deve ter a extensão .dat para ser lido, as demais extensões serão ignoradas, e deve conter linhas como no exemplo abaixo:  
```  
001ç1234567891234çJoãoç50000
001ç3245678865434çMatheusç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çJoão
003ç08ç[1-34-100,2-33-1.50,3-40-0.10]çMatheus 
```  
 O mesmo deve estar dentro de `"$HOME"/data/in`
 
 OBS: Caso não exista um arquivo de entrada na pasta, acima citada, o processador lancará uma exceção e continuará a execução.
 
- O resultado do processamento gerará o seguinte conteúdo em um arquivo .dat localizado em:  `"$HOME"/data/out`  
```  
**** Relatório Análise dados****
Quantidade de clientes: 2
Quantidade de vendedores: 2
Pior vendedor: João ** Número do documento: 1234567891234
ID venda mais cara: 8
```
