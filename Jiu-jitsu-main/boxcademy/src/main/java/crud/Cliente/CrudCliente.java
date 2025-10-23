package crud.Cliente;

import java.util.Scanner;

public class CrudCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU - CLIENTES ==========");
            System.out.println("1 - Cadastrar novo cliente");
            System.out.println("2 - Listar todos os clientes");
            System.out.println("3 - Atualizar dados de um cliente");
            System.out.println("4 - Remover cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        CreateCliente.executar(scanner);
                        break;
                    case 2:
                        ReadCliente.executar(scanner);
                        break;
                    case 3:
                        UpdateCliente.executar(scanner);
                        break;
                    case 4:
                        DeleteCliente.executar(scanner);
                        break;
                    case 0:
                        System.out.println("↩ Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida.");
                opcao = -1;
            }

        } while (opcao != 0);
    }
}
