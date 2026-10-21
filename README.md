O Simulador Financeiro é uma aplicação de terminal em Java que projeta o crescimento de um investimento em Renda Fixa (como CDB e Tesouro Direto), mês a mês.

Você informa o valor inicial, o prazo, o aporte mensal e a taxa de juros. O programa mostra a evolução do saldo e, depois, abre um menu interativo para comparar juros compostos com juros simples e ver o Imposto de Renda descontado automaticamente.

⚠️ Projeto educacional. Os resultados são simulações e não constituem recomendação de investimento.
🎯 Funcionalidades

🛡️ Validação do prazo: repete a pergunta com while enquanto o tempo for zero ou negativo
📅 Projeção mês a mês: imprime a evolução do saldo a cada mês
💵 Aportes mensais: simula o impacto de novos aportes ao longo do tempo
📊 Comparativo de rendimentos: juros compostos vs. juros simples
🏛️ IR automático: aplica a alíquota regressiva conforme o prazo
🎨 Terminal colorido: cores ANSI (verde, vermelho, amarelo e ciano)
🧭 Menu com switch/case: navega entre os relatórios sem rodar tudo de novo
Menu do programa

Opção	O que mostra
1	Total investido, saldo final e juros ganhos (compostos)
2	Total no juros simples e a diferença (juros sobre juros)
3	Imposto de Renda e valor líquido no resgate
0	Sair

💼 Regras de negócio e fórmulas

1. Evolução patrimonial (juros compostos com aporte)
A cada mês $t$, o saldo anterior recebe o aporte e, sobre o total, incide a taxa:

$$\text{Saldo}t = (\text{Saldo}{t-1} + \text{Aporte}) \times \left(1 + \frac{\text{taxa}}{100}\right)$$

2. Tabela regressiva de Imposto de Renda
O IR incide somente sobre o rendimento (lucro). O programa considera 1 mês = 30 dias.

Prazo da aplicação	Dias corridos	Alíquota de IR
Até 6 meses	Até 180 dias	22,5%
De 6 meses a 1 ano	De 181 a 360 dias	20,0%
De 1 a 2 anos	De 361 a 720 dias	17,5%
Acima de 2 anos	Mais de 720 dias	15,0%
$$\text{Imposto} = \text{Rendimento} \times \text{Alíquota}$$

$$\text{Valor líquido} = \text{Saldo final} - \text{Imposto}$$

3. Juros simples vs. juros compostos
$$\text{Juros simples} = \text{Valor inicial} \times \frac{\text{taxa}}{100} \times \text{tempo}$$

$$\text{Diferença (juros sobre juros)} = \text{Rendimento composto} - \text{Juros simples}$$

🚀 Roadmap

 Juros simples com aportes: corrigir o comparativo quando houver aporte mensal
 Isenção fiscal (LCI/LCA): escolher entre CDB tributado e LCI/LCA isento
 Tratamento de entradas inválidas: aceitar apenas números válidos
 Taxa anual: converter para a taxa mensal equivalente
 Comparar com a poupança
 POO: separar responsabilidades em classes (Simulador, CalculadoraTributaria, Investimento)
 BigDecimal: trocar double para evitar imprecisões de arredondamento financeiro
 Testes unitários com JUnit 5