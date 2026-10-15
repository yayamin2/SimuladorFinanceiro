<div align="center">

# 📈 Simulador Financeiro de Renda Fixa

**Simulação de juros compostos com aportes mensais, comparação com juros simples e Imposto de Renda pela tabela regressiva.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Status](https://img.shields.io/badge/status-em_evolu%C3%A7%C3%A3o-brightgreen?style=for-the-badge)
![Tipo](https://img.shields.io/badge/projeto-educacional-blue?style=for-the-badge)

</div>

---

## 📌 Sobre o projeto

O **Simulador Financeiro** é uma aplicação de terminal em **Java** que projeta o crescimento de um investimento em **Renda Fixa** (como CDB e Tesouro Direto), mês a mês.

Você informa o valor inicial, o prazo, o aporte mensal e a taxa de juros. O programa mostra a evolução do saldo e, depois, abre um **menu interativo** para comparar juros compostos com juros simples e ver o Imposto de Renda descontado automaticamente.

> ⚠️ **Projeto educacional.** Os resultados são simulações e não constituem recomendação de investimento.

<!--
Dica: tire um print do terminal rodando, salve em docs/demo.png e tire este comentário:

## 🖼️ Demonstração

![Simulador rodando no terminal](docs/demo.png)
-->

---

## 🎯 Funcionalidades

- 🛡️ **Validação do prazo:** repete a pergunta com `while` enquanto o tempo for zero ou negativo
- 📅 **Projeção mês a mês:** imprime a evolução do saldo a cada mês
- 💵 **Aportes mensais:** simula o impacto de novos aportes ao longo do tempo
- 📊 **Comparativo de rendimentos:** juros compostos vs. juros simples
- 🏛️ **IR automático:** aplica a alíquota regressiva conforme o prazo
- 🎨 **Terminal colorido:** cores ANSI (verde, vermelho, amarelo e ciano)
- 🧭 **Menu com `switch/case`:** navega entre os relatórios sem rodar tudo de novo

### Menu do programa

| Opção | O que mostra |
|:-----:|--------------|
| `1` | Total investido, saldo final e juros ganhos (compostos) |
| `2` | Total no juros simples e a diferença (juros sobre juros) |
| `3` | Imposto de Renda e valor líquido no resgate |
| `0` | Sair |

---

## 💼 Regras de negócio e fórmulas

<details>
<summary><b>1. Evolução patrimonial (juros compostos com aporte)</b></summary>

A cada mês $t$, o saldo anterior recebe o aporte e, sobre o total, incide a taxa:

$$\text{Saldo}_t = (\text{Saldo}_{t-1} + \text{Aporte}) \times \left(1 + \frac{\text{taxa}}{100}\right)$$

</details>

<details>
<summary><b>2. Tabela regressiva de Imposto de Renda</b></summary>

O IR incide **somente sobre o rendimento (lucro)**. O programa considera 1 mês = 30 dias.

| Prazo da aplicação | Dias corridos | Alíquota de IR |
| :--- | :--- | :--- |
| Até 6 meses | Até 180 dias | **22,5%** |
| De 6 meses a 1 ano | De 181 a 360 dias | **20,0%** |
| De 1 a 2 anos | De 361 a 720 dias | **17,5%** |
| Acima de 2 anos | Mais de 720 dias | **15,0%** |

$$\text{Imposto} = \text{Rendimento} \times \text{Alíquota}$$

$$\text{Valor líquido} = \text{Saldo final} - \text{Imposto}$$

</details>

<details>
<summary><b>3. Juros simples vs. juros compostos</b></summary>

$$\text{Juros simples} = \text{Valor inicial} \times \frac{\text{taxa}}{100} \times \text{tempo}$$

$$\text{Diferença (juros sobre juros)} = \text{Rendimento composto} - \text{Juros simples}$$

</details>

---

## 🖥️ Exemplo prático

**Entradas:**

| Campo | Valor |
|-------|------:|
| Valor inicial | R$ 1.000,00 |
| Prazo | 3 meses |
| Aporte mensal | R$ 0,00 |
| Taxa mensal | 10,0% |

**Resultado:**

| Item | Valor |
|------|------:|
| Saldo final (composto) | R$ 1.331,00 |
| Rendimento composto | R$ 331,00 |
| Total no juros simples | R$ 1.300,00 |
| Ganho adicional do composto | R$ 31,00 |
| Imposto de Renda (22,5%) | R$ 74,47 |
| **Valor líquido para resgate** | **R$ 1.256,53** |

---

## 🛠️ Tecnologias

- **Java 17+**
- **Entrada e saída:** `java.util.Scanner` e `System.out.printf`
- **Controle de fluxo:** `while`, `for`, `if/else`, `switch/case`
- **Interface:** códigos ANSI para cores no terminal

---

## 💻 Como executar

**Pelo IntelliJ:** abra o projeto, abra `src/SimuladorFinanceiro.java` e clique no botão verde ▶ ao lado do `main`.

**Pelo terminal**, a partir da raiz do repositório:

```bash
# compilar
javac -d out src/SimuladorFinanceiro.java

# executar
java -cp out projetos.simulador.SimuladorFinanceiro
```

> 💡 As cores aparecem bem no IntelliJ e no Windows Terminal. Em terminais mais antigos, os códigos de cor podem aparecer como símbolos estranhos.

---

## ⚠️ Limitações atuais

- A taxa é informada como **mensal** e fica fixa durante todo o período
- Não considera **IOF** (cobrado nos primeiros 30 dias) nem inflação
- O **juros simples** é calculado só sobre o valor inicial, sem os aportes. Por isso, com aporte mensal, a "diferença" mostrada superestima o ganho do composto
- Não trata entradas inválidas como letras no lugar de números

---

## 🚀 Roadmap

- [ ] **Juros simples com aportes:** corrigir o comparativo quando houver aporte mensal
- [ ] **Isenção fiscal (LCI/LCA):** escolher entre CDB tributado e LCI/LCA isento
- [ ] **Tratamento de entradas inválidas:** aceitar apenas números válidos
- [ ] **Taxa anual:** converter para a taxa mensal equivalente
- [ ] **Comparar com a poupança**
- [ ] **POO:** separar responsabilidades em classes (`Simulador`, `CalculadoraTributaria`, `Investimento`)
- [ ] **`BigDecimal`:** trocar `double` para evitar imprecisões de arredondamento financeiro
- [ ] **Testes unitários** com JUnit 5

---

## 👩‍💻 Autora

**Yasmin**

Graduanda em Engenharia de Software | Trainee Fourcamp (Foursys) | Foco em Back-end, Java e FinTechs

[![GitHub](https://img.shields.io/badge/GitHub-yayamin2-181717?style=flat-square&logo=github)](https://github.com/yayamin2)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-yasminmorenomendes-0077B5?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/yasminmorenomendes)
