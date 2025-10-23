package crud.Cliente;

import java.util.Scanner;

import dao.DAO;
import model.Cadastro;

public class UpdateCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU DE ATUALIZAÇÃO DE CLIENTES ==========");
            System.out.println("1 - Buscar cliente por nome");
            System.out.println("2 - Buscar cliente por CPF");
            System.out.println("3 - Listar todos os clientes");
            System.out.println("4 - Atualizar cliente por ID");
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
                        atualizarCliente(scanner);
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

    private static void atualizarCliente(Scanner scanner) {
        DAO<Cadastro> dao = new DAO<>(Cadastro.class);
        Cadastro cliente = null;
        Long id = null;

        // Loop para garantir que o ID exista ou o usuário queira sair
        while (cliente == null) {
            System.out.print("Digite o ID do cliente que deseja atualizar (ou 0 para voltar): ");
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

        System.out.println("\nQuais campos deseja atualizar?");
        System.out.println("Digite os números separados por vírgula, ou '0' para atualizar todos:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        System.out.println("4 - Endereço");
        System.out.print("Opções: ");
        String opcao = scanner.nextLine();

        boolean atualizarTodos = opcao.trim().equals("0");

        if (atualizarTodos || opcao.contains("1")) {
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            cliente.setNome(novoNome);
        }

        if (atualizarTodos || opcao.contains("2")) {
            System.out.print("Novo CPF: ");
            String novoCPF = scanner.nextLine();
            cliente.setCpf(novoCPF);
        }

        if (atualizarTodos || opcao.contains("3")) {
            System.out.print("Novo telefone: ");
            String novoTelefone = scanner.nextLine();
            cliente.setTelefone(novoTelefone);
        }

        if (atualizarTodos || opcao.contains("4")) {
            System.out.print("Novo endereço: ");
            String novoEndereco = scanner.nextLine();
            cliente.setEndereco(novoEndereco);
        }

        dao.atualizarTransacional(cliente);
        System.out.println("\n✅ Cliente atualizado com sucesso!");
        System.out.println("Novo estado do cliente:");
        System.out.println(cliente);

        dao.fecharEm();
    }
}
