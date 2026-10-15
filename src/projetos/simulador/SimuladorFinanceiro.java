package projetos.simulador;

import java.util.Scanner;

// Simulador de investimento: juros compostos mês a mês, com aporte mensal, comparação com juros simples e imposto de renda

public class SimuladorFinanceiro {

    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AMARELO = "\u001B[33m";
    public static final String CIANO = "\u001B[36m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double valor = 0;
        double juros = 0;
        int tempo;
        double aporte = 0;
        int opcao = 1;

        System.out.print("Digite o valor que você quer investir: ");
        valor = sc.nextDouble();

        System.out.print("Digite por quanto tempo (em meses) você quer deixar o dinheiro investido: ");
        tempo = sc.nextInt();
        while (tempo <= 0) {
            System.out.print(VERMELHO + "Tempo inválido, digite um número maior que zero: " + RESET);
            tempo = sc.nextInt();
        }

        System.out.print("Qual será o valor do aporte mensal? ");
        aporte = sc.nextDouble();

        System.out.print("Digite a taxa de juros do seu banco: ");
        juros = sc.nextDouble();

        System.out.println("\n" + CIANO + "=== EVOLUÇÃO MÊS A MÊS ===" + RESET);
        double saldo = valor;
        for (int i = 1; i <= tempo; i++) {
            saldo = saldo + aporte;
            double aumento = saldo * (juros / 100);
            saldo = saldo + aumento;
            System.out.printf("Mês %d: R$ %.2f%n", i, saldo);
        }
        double valorInserido = aporte * tempo + valor;
        double rendimento = saldo - valorInserido;
        System.out.printf(VERDE + "Total de juros ganhos: R$ %.2f%n" + RESET, rendimento);

        int dias = tempo * 30;
        double aliquota = 0;
        if (dias <= 180) {
            aliquota = 0.225;
        } else if (dias <= 360) {
            aliquota = 0.20;
        } else if (dias <= 720) {
            aliquota = 0.175;
        } else {
            aliquota = 0.15;
        }

        double imposto = rendimento * aliquota;
        double resgate = saldo - imposto;
        double jurosSimples = valor * juros * tempo / 100;
        double totalSimples = jurosSimples + valor;
        double diferenca = rendimento - jurosSimples;

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
