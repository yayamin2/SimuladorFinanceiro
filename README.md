# 💰 Simulador Financeiro

Aplicação de terminal em **Java** que projeta, mês a mês, o crescimento de um investimento em Renda Fixa (como CDB e Tesouro Direto).

Você informa o valor inicial, o prazo, o aporte mensal e a taxa de juros. O programa mostra a evolução do saldo e, depois, abre um menu interativo para comparar juros compostos com juros simples e ver o Imposto de Renda descontado automaticamente.

> ⚠️ **Projeto educacional.** Os resultados são simulações e não constituem recomendação de investimento.

---

## 🎯 Funcionalidades

- 🛡️ **Validação do prazo:** repete a pergunta com `while` enquanto o tempo for zero ou negativo
- 📅 **Projeção mês a mês:** imprime a evolução do saldo a cada mês
- 💵 **Aportes mensais:** simula o impacto de novos aportes ao longo do tempo
- 📊 **Comparativo de rendimentos:** juros compostos vs. juros simples
- 🏛️ **IR automático:** aplica a alíquota regressiva conforme o prazo
- 🎨 **Terminal colorido:** cores ANSI (verde, vermelho, amarelo e ciano)
- 🧭 **Menu com `switch/case`:** navega entre os relatórios sem rodar tudo de novo

---

## 🧭 Menu do programa

| Opção | O que mostra |
|:-----:|--------------|
| `1` | Total investido, saldo final e juros ganhos (compostos) |
| `2` | Total no juros simples e a diferença (juros sobre juros) |
| `3` | Imposto de Renda e valor líquido no resgate |
| `0` | Sair |

---

## ⚙️ Requisitos

- Java 8 ou superior (JDK)
- Terminal com suporte a cores ANSI (no Windows, use o Windows Terminal ou o terminal do IntelliJ/VS Code)

---

## ▶️ Como executar

A classe está no pacote `projetos.simulador`. A partir da **pasta raiz** do projeto (a que contém a pasta `projetos`):

```bash
javac projetos/simulador/SimuladorFinanceiro.java
java projetos.simulador.SimuladorFinanceiro
```

> 💡 **Atenção ao separador decimal:** o programa usa o `Scanner` com a configuração regional do sistema. Em computadores em português (Brasil), digite decimais com **vírgula** (`1,5`). Em sistemas em inglês, use **ponto** (`1.5`).

---

## 🖥️ Exemplo de uso

Entradas: valor inicial de R$ 1.000, 3 meses, aporte de R$ 100 e taxa de 1% ao mês.

```text
Digite o valor que você quer investir: 1000
Digite por quanto tempo (em meses) você quer deixar o dinheiro investido: 3
Qual será o valor do aporte mensal? 100
Digite a taxa de juros do seu banco: 1

=== EVOLUÇÃO MÊS A MÊS ===
Mês 1: R$ 1111,00
Mês 2: R$ 1223,11
Mês 3: R$ 1336,34
Total de juros ganhos: R$ 36,34

--- MENU DE OPÇÕES ---
1 - Ver juros compostos
2 - Ver juros simples e a diferença
3 - Ver imposto de renda
0 - Sair
Escolha: 3
Imposto de Renda: R$ 8,18
Valor líquido no resgate: R$ 1328,16
```

---

## 💼 Regras de negócio e fórmulas

### 1. Evolução patrimonial (juros compostos com aporte)

A cada mês *t*, o saldo anterior recebe o aporte e, sobre o total, incide a taxa:

$$
\text{Saldo}_t = (\text{Saldo}_{t-1} + \text{Aporte}) \times \left(1 + \frac{\text{taxa}}{100}\right)
$$

### 2. Tabela regressiva de Imposto de Renda

O IR incide **somente sobre o rendimento (lucro)**. O programa considera **1 mês = 30 dias**.

| Prazo da aplicação | Dias corridos | Alíquota de IR |
|--------------------|:-------------:|:--------------:|
| Até 6 meses | Até 180 dias | 22,5% |
| De 6 meses a 1 ano | De 181 a 360 dias | 20,0% |
| De 1 a 2 anos | De 361 a 720 dias | 17,5% |
| Acima de 2 anos | Mais de 720 dias | 15,0% |

$$
\text{Imposto} = \text{Rendimento} \times \text{Alíquota}
$$

$$
\text{Valor líquido} = \text{Saldo final} - \text{Imposto}
$$

### 3. Juros simples vs. juros compostos

$$
\text{Juros simples} = \text{Valor inicial} \times \frac{\text{taxa}}{100} \times \text{tempo}
$$

$$
\text{Diferença (juros sobre juros)} = \text{Rendimento composto} - \text{Juros simples}
$$

> ⚠️ **Limitação atual:** o cálculo de juros simples considera apenas o valor inicial e ignora os aportes mensais. Com aporte, a "diferença" aparece maior do que a real (veja o Roadmap).

---

## 📁 Estrutura do projeto

```text
projetos/
└── simulador/
    └── SimuladorFinanceiro.java
```

---

## 🚀 Roadmap

- [ ] **Juros simples com aportes:** corrigir o comparativo quando houver aporte mensal
- [ ] **Isenção fiscal (LCI/LCA):** escolher entre CDB tributado e LCI/LCA isento
- [ ] **Tratamento de entradas inválidas:** aceitar apenas números válidos (hoje, digitar letras encerra o programa com `InputMismatchException`)
- [ ] **Taxa anual:** converter para a taxa mensal equivalente
- [ ] **Comparar com a poupança**
- [ ] **POO:** separar responsabilidades em classes (`Simulador`, `CalculadoraTributaria`, `Investimento`)
- [ ] **BigDecimal:** trocar `double` para evitar imprecisões de arredondamento financeiro
- [ ] **Testes unitários** com JUnit 5

