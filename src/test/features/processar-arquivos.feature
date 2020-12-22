# language: pt

Funcionalidade: Ler dados de arquivo e fazer análise.

	Cenário: Realizar análise dados do arquivo

		Dado que exista um arquivo com os seguintes dados:
			| 001ç1234567891234çPedroç50000 |
			| 001ç3245678865434çPauloç40000.99 |
			| 002ç2345675434544345çJose da SilvaçRural |
			| 002ç2345675433444345çEduardo PereiraçRural |
			| 003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro |
			| 003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo |

		Quando quando executar a rotina de processamento

		Então o sistema gerará um aquivo de resumo com os seguintes dados:
			| Quantidade de clientes: 2 |
			| Quantidade de vendedores: 2 |
			| Pior vendedor: Pedro ** Número do documento: 1234567891234|
			| ID venda mais cara: 10 |




