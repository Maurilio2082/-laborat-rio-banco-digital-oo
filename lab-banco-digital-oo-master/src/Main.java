import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.setNome("Will Bank");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Imprimir Extrato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarConta(scanner, banco, "corrente");
                    break;
                case 2:
                    criarConta(scanner, banco, "poupanca");
                    break;
                case 3:
                    depositar(scanner, banco);
                    break;
                case 4:
                    sacar(scanner, banco);
                    break;
                case 5:
                    transferir(scanner, banco);
                    break;
                case 6:
                    imprimirExtrato(scanner, banco);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void criarConta(Scanner scanner, Banco banco, String tipo) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.next();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        Conta conta;
        if (tipo.equals("corrente")) {
            conta = new ContaCorrente(cliente);
        } else {
            conta = new ContaPoupanca(cliente);
        }

        banco.adicionarConta(conta);
        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumero());
    }

    private static void depositar(Scanner scanner, Banco banco) {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do depósito: ");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void sacar(Scanner scanner, Banco banco) {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do saque: ");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void transferir(Scanner scanner, Banco banco) {
        System.out.print("Número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        Conta contaOrigem = banco.buscarConta(numeroOrigem);
        if (contaOrigem != null) {
            System.out.print("Número da conta de destino: ");
            int numeroDestino = scanner.nextInt();
            Conta contaDestino = banco.buscarConta(numeroDestino);
            if (contaDestino != null) {
                System.out.print("Valor da transferência: ");
                double valor = scanner.nextDouble();
                contaOrigem.transferir(valor, contaDestino);
                System.out.println("Transferência realizada com sucesso.");
            } else {
                System.out.println("Conta de destino não encontrada.");
            }
        } else {
            System.out.println("Conta de origem não encontrada.");
        }
    }

    private static void imprimirExtrato(Scanner scanner, Banco banco) {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            conta.imprimirExtrato();
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}