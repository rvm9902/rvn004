package crud.Cliente;

import java.util.Scanner;

import dao.DAO;
import model.Cadastro;

public class DeleteCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU DE EXCLUSÃO DE CLIENTES ==========");
            System.out.println("1 - Buscar cliente por nome");
            System.out.println("2 - Buscar cliente por CPF");
            System.out.println("3 - Listar todos os clientes");
            System.out.println("4 - Excluir cliente por ID");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        ReadCliente.buscarPorNome(scanner);
                        break;
                    case 2:
                        ReadCliente.buscarPorCPF(scanner);
                        break;
                    case 3:
                        ReadCliente.listarTodosComPaginacao(scanner);
                        break;
                    case 4:
                        excluirCliente(scanner);
                        break;
                    case 0:
                        System.out.println("↩ Retornando ao menu anterior...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);
    }

    // Método que exclui o cliente por ID após confirmação
    private static void excluirCliente(Scanner scanner) {
        DAO<Cadastro> dao = new DAO<>(Cadastro.class);
        Cadastro cliente = null;
        Long id = null;

        // Loop para garantir que o ID exista ou o usuário queira sair
        while (cliente == null) {
            System.out.print("Digite o ID do cliente que deseja excluir (ou 0 para voltar): ");
            try {
                id = Long.parseLong(scanner.nextLine());
                if (id == 0) {
                    System.out.println("↩ Voltando ao menu anterior...");
                    return;
                }
                cliente = dao.obterPorID(id);
                if (cliente == null) {
                    System.out.println("❌ Cliente com ID " + id + " não encontrado. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Digite um número válido.");
            }
        }

        System.out.println("\nCliente encontrado:");
        System.out.println(cliente);

        System.out.print("Tem certeza que deseja excluir este cliente? (S/N): ");
        String confirmar = scanner.nextLine().trim().toUpperCase();

        if (confirmar.equals("S")) {
            dao.removerPorIdTransacional(cliente.getId());
            System.out.println("✅ Cliente excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }

        dao.fecharEm();
    }
}
