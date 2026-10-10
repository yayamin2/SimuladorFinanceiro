# 📈 Simulador Financeiro de Renda Fixa em Java

> **Aplicação interativa em Java para simulação de juros compostos com aportes mensais, comparação com juros simples e apuração da tabela regressiva de Imposto de Renda (IR).**

---

## 📌 Sobre o Projeto

O **Simulador Financeiro** é uma aplicação desenvolvida em **Java** focada na resolução de um problema real do ecossistema financeiro: a projeção patrimonial e cálculo de rentabilidade em investimentos de **Renda Fixa** (como CDB, Tesouro Direto, LCI e LCA).

O programa calcula a curva de crescimento do dinheiro mês a mês, considerando o aporte inicial e aportes recorrentes. Ao final, gera relatórios interativos para comparar o ganho em **juros compostos** versus **juros simples**, além de descontar automaticamente o **Imposto de Renda retido na fonte** com base na tabela regressiva oficial da Receita Federal.

---

## 🎯 Funcionalidades

- 🛡️ **Validação de Entradas:** Impede prazos inválidos ou zerados através de laço de repetição `while`.
- 📅 **Projeção Mês a Mês:** Calcula e imprime no console a evolução do saldo a cada mês.
- 💵 **Aportes Mensais:** Simula o impacto de novos aportes ao longo do tempo.
- 📊 **Comparativo de Rendimentos:** Exibe o poder dos juros sobre juros em relação aos juros simples.
- 🏛️ **Cálculo Tributário Automático:** Aplica a alíquota regressiva do Imposto de Renda conforme o prazo do investimento.
- 🎨 **Console Interativo Colorido:** Interface de terminal estilizada com códigos ANSI (Verde, Vermelho, Amarelo e Ciano) e navegação via `switch/case`.

---

## 💼 Regras de Negócio e Fórmulas Financeiras

### 1. Evolução Patrimonial (Juros Compostos com Aporte)
A cada mês ($t$), o saldo do período anterior recebe o aporte mensal e, sobre o total, incide a taxa de juros informada:
$$\text{Saldo}_t = (\text{Saldo}_{t-1} + \text{Aporte}) \times \left(1 + \frac{\text{taxa}}{100}\right)$$

### 2. Tabela Regressiva de Imposto de Renda
O Imposto de Renda incide **exclusivamente sobre o rendimento (lucro)**:

| Prazo da Aplicação | Dias Corridos | Alíquota de IR |
| :--- | :--- | :--- |
| Até 6 meses | Até 180 dias | **22,5%** |
| De 6 meses a 1 ano | De 181 a 360 dias | **20,0%** |
| De 1 a 2 anos | De 361 a 720 dias | **17,5%** |
| Acima de 2 anos | Mais de 720 dias | **15,0%** |

$$\text{Imposto} = \text{Rendimento} \times \text{Alíquota}$$
$$\text{Valor Líquido} = \text{Saldo Final} - \text{Imposto}$$

### 3. Juros Simples vs. Juros Compostos
$$\text{Juros Simples} = \text{Valor Inicial} \times \frac{\text{taxa}}{100} \times \text{tempo}$$
$$\text{Diferença (Juros sobre Juros)} = \text{Rendimento Composto} - \text{Juros Simples}$$

---

## 🖥️ Exemplo Prático de Simulação

**Entradas:**
- Valor inicial: **R$ 1.000,00**
- Prazo: **3 meses**
- Aporte mensal: **R$ 0,00**
- Taxa mensal: **10,0%**

**Saída no Console:**
- **Saldo Final (Composto):** R$ 1.331,00 (Rendimento: R$ 331,00)
- **Total Juros Simples:** R$ 1.300,00 (Ganho adicional composto: R$ 31,00)
- **Imposto de Renda (22,5%):** R$ 74,47
- **Valor Líquido para Resgate:** R$ 1.256,53

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17+
- **Entrada/Saída:** `java.util.Scanner` e `System.out.printf` formatado
- **Controle de Fluxo:** `while`, `for`, `if/else`, `switch/case`
- **Interface:** ANSI Escape Codes para cores no terminal

---

## 💻 Como Executar

### Estrutura do Pacote
Certifique-se de que o arquivo esteja em: `src/projetos/simulador/SimuladorFinanceiro.java`.

### Compilar o código a partir da raiz do projeto:
javac src/projetos/simulador/SimuladorFinanceiro.java

### Executar a aplicação:
java -cp src projetos.simulador.SimuladorFinanceiro

---

## 🚀 Próximas Evoluções (Roadmap)

- [ ] Isenção Fiscal (LCI/LCA): Permitir a escolha do tipo de produto (CDB tributado vs LCI/LCA isento).
- [ ] Refatoração para Programação Orientada a Objetos (POO): Separar as responsabilidades em classes (Simulador, CalculadoraTributaria, Investimento).
- [ ] Tratamento de Precisão Monetária: Substituir tipos primitivos double por BigDecimal para evitar imprecisões de arredondamento financeiro.
- [ ] Testes Unitários: Implementar testes automatizados com JUnit 5.

---

## 👩‍💻 Autora

**Yasmin**

Graduanda em Engenharia de Software | Trainee Fourcamp (Foursys) | Foco em Backend, Java & FinTechs

GitHub: [github.com/yayamin2](https://github.com/yayamin2)  
LinkedIn: [yasminmorenomendes](https://www.linkedin.com/in/yasminmorenomendes)
