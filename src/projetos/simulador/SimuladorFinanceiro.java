package projetos.simulador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

// Simulador de investimento: juros compostos mês a mês, com aporte mensal, comparação com juros simples e imposto de renda
// IMPORTANTE: Usamos BigDecimal em vez de double para cálculos financeiros porque:
// - double tem problemas de precisão (ex: 0.1 + 0.2 = 0.30000000000000004)
// - BigDecimal representa números exatos com precisão arbitrária
// - Essencial para cálculos monetários onde cada centavo importa

public class SimuladorFinanceiro {

    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AMARELO = "\u001B[33m";
    public static final String CIANO = "\u001B[36m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaração de variáveis usando BigDecimal para precisão financeira
        BigDecimal valor = BigDecimal.ZERO;           // Valor inicial do investimento
        BigDecimal juros = BigDecimal.ZERO;           // Taxa de juros (ex: 5.0 para 5%)
        int tempo;                                    // Tempo em meses (permanece int)
        BigDecimal aporte = BigDecimal.ZERO;          // Aporte mensal
        int opcao = 1;                                // Opção do menu (permanece int)

        System.out.print("Digite o valor que você quer investir: ");
        // Converte o double lido do Scanner para BigDecimal
        // valueOf é preferido pois cria BigDecimal com representação exata
        valor = BigDecimal.valueOf(sc.nextDouble());

        System.out.print("Digite por quanto tempo (em meses) você quer deixar o dinheiro investido: ");
        tempo = sc.nextInt();
        while (tempo <= 0) {
            System.out.print(VERMELHO + "Tempo inválido, digite um número maior que zero: " + RESET);
            tempo = sc.nextInt();
        }

        System.out.print("Qual será o valor do aporte mensal? ");
        aporte = BigDecimal.valueOf(sc.nextDouble());

        System.out.print("Digite a taxa de juros do seu banco: ");
        juros = BigDecimal.valueOf(sc.nextDouble());

        System.out.println("\n" + CIANO + "=== EVOLUÇÃO MÊS A MÊS ===" + RESET);
        BigDecimal saldo = valor;  // Saldo inicial é igual ao valor investido
        
        for (int i = 1; i <= tempo; i++) {
            // .add(): Soma dois BigDecimal
            // saldo = saldo + aporte
            saldo = saldo.add(aporte);
            
            // .divide(): Divisão com arredondamento para evitar dízima periódica
            // juros / 100 converte a taxa percentual (ex: 5% -> 0.05)
            // RoundingMode.HALF_EVEN: Arredonda para o número par mais próximo (padrão bancário)
            // 4 casas decimais de precisão intermediária para evitar erro acumulado
            BigDecimal taxaDecimal = juros.divide(new BigDecimal("100"), 4, RoundingMode.HALF_EVEN);
            
            // .multiply(): Multiplica dois BigDecimal
            // aumento = saldo * (juros / 100)
            BigDecimal aumento = saldo.multiply(taxaDecimal);
            
            // saldo = saldo + aumento (adiciona os juros ao saldo)
            saldo = saldo.add(aumento);
            
            System.out.printf("Mês %d: R$ %.2f%n", i, saldo);
        }
        
        // .multiply(): Multiplica o aporte pelo número de meses
        // BigDecimal.valueOf(tempo): converte int para BigDecimal
        BigDecimal valorInserido = aporte.multiply(BigDecimal.valueOf(tempo)).add(valor);
        
        // .subtract(): Subtrai dois BigDecimal
        // rendimento = saldo - valorInserido
        BigDecimal rendimento = saldo.subtract(valorInserido);
        System.out.printf(VERDE + "Total de juros ganhos: R$ %.2f%n" + RESET, rendimento);

        int dias = tempo * 30;
        BigDecimal aliquota = BigDecimal.ZERO;
        
        // Define a alíquota de imposto baseada no tempo de investimento
        // Usamos strings para criar BigDecimal com representação exata
        if (dias <= 180) {
            aliquota = new BigDecimal("0.225");  // 22.5% para até 180 dias
        } else if (dias <= 360) {
            aliquota = new BigDecimal("0.20");   // 20% para até 360 dias
        } else if (dias <= 720) {
            aliquota = new BigDecimal("0.175"); // 17.5% para até 720 dias
        } else {
            aliquota = new BigDecimal("0.15");   // 15% para mais de 720 dias
        }

        // .multiply(): Calcula o imposto = rendimento * aliquota
        //setScale(2, RoundingMode.HALF_EVEN): Arredonda para 2 casas decimais (centavos)
        BigDecimal imposto = rendimento.multiply(aliquota).setScale(2, RoundingMode.HALF_EVEN);
        
        // .subtract(): Calcula o valor líquido do resgate = saldo - imposto
        BigDecimal resgate = saldo.subtract(imposto);
        
        // Cálculo de juros simples: valor * juros * tempo / 100
        // Multiplica valor pelos juros e pelo tempo
        BigDecimal jurosSimples = valor.multiply(juros).multiply(BigDecimal.valueOf(tempo));
        // Divide por 100 com 4 casas decimais de precisão
        jurosSimples = jurosSimples.divide(new BigDecimal("100"), 4, RoundingMode.HALF_EVEN);
        
        // .add(): Soma os juros simples ao valor inicial
        BigDecimal totalSimples = jurosSimples.add(valor);
        
        // .subtract(): Calcula a diferença entre juros compostos e juros simples
        BigDecimal diferenca = rendimento.subtract(jurosSimples);

        while (opcao != 0) {
            System.out.println("\n" + CIANO + "--- MENU DE OPÇÕES ---" + RESET);
            System.out.println("1 - Ver juros compostos");
            System.out.println("2 - Ver juros simples e a diferença");
            System.out.println("3 - Ver imposto de renda");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.printf("Total investido: R$ %.2f%n", valorInserido);
                    System.out.printf(VERDE + "Saldo final: R$ %.2f%n" + RESET, saldo);
                    System.out.printf(VERDE + "Total de juros ganhos: R$ %.2f%n" + RESET, rendimento);
                    break;
                case 2:
                    System.out.printf("Total no juros simples: R$ %.2f%n", totalSimples);
                    System.out.printf(AMARELO + "Diferença (juros sobre juros): R$ %.2f%n" + RESET, diferenca);
                    break;
                case 3:
                    System.out.printf(VERMELHO + "Imposto de Renda: R$ %.2f%n" + RESET, imposto);
                    System.out.printf(VERDE + "Valor líquido no resgate: R$ %.2f%n" + RESET, resgate);
                    break;
                case 0:
                    System.out.println(AMARELO + "Até logo!" + RESET);
                    break;
                default:
                    System.out.println(VERMELHO + "Opção inválida." + RESET);
            }
        }

        sc.close();
    }
}

/*
 * =============================================================================
 * EXPLICAÇÃO DETALHADA SOBRE ROUNDINGMODE E DÍZIMA PERIÓDICA
 * =============================================================================
 * 
 * O QUE É DÍZIMA PERIÓDICA?
 * ------------------------
 * Dízima periódica é quando uma divisão resulta em um número decimal infinito.
 * Exemplos:
 * - 1 / 3 = 0.3333333333... (dízima periódica)
 * - 1 / 6 = 0.1666666666... (dízima periódica)
 * - 1 / 7 = 0.142857142857... (dízima periódica)
 * 
 * PROBLEMA: BigDecimal não pode armazenar números infinitos. Se tentar dividir
 * 1 por 3 sem especificar arredondamento, o Java lança ArithmeticException.
 * 
 * COMO RESOLVER?
 * -------------
 * Usamos o método .divide() com 3 parâmetros:
 * 
 * dividendo.divide(divisor, escala, modoArredondamento)
 * 
 * 1. DIVISOR: O número pelo qual estamos dividindo
 * 2. ESCALA: Quantidade de casas decimais que queremos manter
 *    - Ex: escala 4 = 0.3333 (4 casas após a vírgula)
 *    - Ex: escala 2 = 0.33 (2 casas após a vírgula, ideal para dinheiro)
 * 
 * 3. ROUNDINGMODE: Como arredondar quando o número tem mais dígitos que a escala
 * 
 * TIPOS DE ROUNDINGMODE MAIS COMUNS:
 * ----------------------------------
 * 
 * RoundingMode.HALF_EVEN (usado neste código):
 * - Arredonda para o número par mais próximo
 * - É o padrão bancário internacional (IEEE 754)
 * - Exemplos:
 *   * 2.5 -> 2 (arredonda para o par)
 *   * 3.5 -> 4 (arredonda para o par)
 *   * 2.4 -> 2 (arredonda para baixo)
 *   * 2.6 -> 3 (arredonda para cima)
 * - Vantagem: Evita viés estatístico em grandes somas
 * 
 * RoundingMode.HALF_UP:
 * - Arredonda para cima se a próxima casa >= 5
 * - É o que aprendemos na escola
 * - Exemplos:
 *   * 2.5 -> 3 (arredonda para cima)
 *   * 3.5 -> 4 (arredonda para cima)
 *   * 2.4 -> 2 (arredonda para baixo)
 * 
 * RoundingMode.UP:
 * - Sempre arredonda para cima (para zero positivo)
 * - Exemplos:
 *   * 2.1 -> 3
 *   * 2.9 -> 3
 * 
 * RoundingMode.DOWN:
 * - Sempre arredonda para baixo (truncamento)
 * - Exemplos:
 *   * 2.9 -> 2
 *   * 2.1 -> 2
 * 
 * POR QUE USAMOS ESCALA 4 NOS CÁLCULOS INTERMEDIÁRIOS?
 * --------------------------------------------------
 * - Usamos 4 casas decimais durante os cálculos para evitar erro acumulado
 * - Só arredondamos para 2 casas (centavos) no resultado final
 * - Exemplo prático:
 *   * Se arredondarmos cada operação para 2 casas: 0.005 + 0.005 = 0.01
 *   * Se mantivermos 4 casas: 0.0050 + 0.0050 = 0.0100 -> arredonda para 0.01
 *   * A precisão extra evita erros de arredondamento acumulados
 * 
 * EXEMPLO PRÁTICO NO CÓDIGO:
 * --------------------------
 * BigDecimal resultado = new BigDecimal("1").divide(new BigDecimal("3"), 4, RoundingMode.HALF_EVEN);
 * // resultado = 0.3333 (4 casas, arredondado)
 * 
 * BigDecimal dinheiro = resultado.setScale(2, RoundingMode.HALF_EVEN);
 * // dinheiro = 0.33 (2 casas, pronto para exibir como R$ 0,33)
 * 
 * RESUMO:
 * - Sempre especifique escala e RoundingMode em divisões
 * - Use HALF_EVEN para cálculos financeiros (padrão bancário)
 * - Mantenha precisão extra nos cálculos intermediários
 * - Arredonde apenas no resultado final para exibição
 * =============================================================================
 */
