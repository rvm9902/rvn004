package crud.Cliente;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import model.Cadastro;

public class ReadCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== CONSULTA DE Alunos ==========");
            System.out.println("1 - Buscar aluno por nome");
            System.out.println("2 - Buscar aluno por CPF");
            System.out.println("3 - Listar todos os alunos");
            System.out.println("0 - Voltar ao menu de alunos");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        buscarPorNome(scanner);
                        break;
                    case 2:
                        buscarPorCPF(scanner);
                        break;
                    case 3:
                        listarTodosComPaginacao(scanner);
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

    public static void buscarPorNome(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();

        DAO<Cadastro> dao = new DAO<>(Cadastro.class);
        // Ajuste na consulta JPA para usar a entidade Cadastro e alias 'c'
        List<Cadastro> clientes = dao.consultar(
            "SELECT c FROM Cadastro c WHERE LOWER(c.nome) LIKE LOWER(?1)",
            "%" + nome + "%");

        if (clientes.isEmpty()) {
            System.out.println("❌ Nenhum aluno encontrado com esse nome.");
        } else {
            System.out.println("\n📋 Alunos  encontrados:");
            clientes.forEach(System.out::println);
        }
    }

    public static void buscarPorCPF(Scanner scanner) {
        System.out.print("Digite o CPF do aluno: ");
        String cpf = scanner.nextLine();

        DAO<Cadastro> dao = new DAO<>(Cadastro.class);
        List<Cadastro> clientes = dao.consultar(
            "SELECT c FROM Cadastro c WHERE c.cpf = ?1",
            cpf);

        if (clientes.isEmpty()) {
            System.out.println("❌ Nenhum aluno encontrado com esse CPF.");
        } else {
            System.out.println("\n📋 Aluno encontrado:");
            clientes.forEach(System.out::println);
        }
    }

    public static void listarTodosComPaginacao(Scanner scanner) {
        System.out.print("Quantos alunos deseja ver por página (ex: 10, 20, 50)? ");

        int tamanhoPagina = 0;
        while (tamanhoPagina <= 0) {
            try {
                tamanhoPagina = Integer.parseInt(scanner.nextLine());
                if (tamanhoPagina <= 0) {
                    System.out.print("⚠ Valor deve ser maior que zero. Digite novamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Número inválido. Digite novamente: ");
            }
        }

        int pagina = 0;
        int opcaoPag;

        DAO<Cadastro> dao = new DAO<>(Cadastro.class);

        do {
            List<Cadastro> clientes = dao.obterTodos(tamanhoPagina, pagina * tamanhoPagina);

            System.out.println("\n                                                              📄 Página " + (pagina + 1));
            System.out.println("                          ----------------------------------------------------------------------------------------------------");

            if (clientes.isEmpty()) {
                System.out.println("Nenhum aluno encontrado nesta página.");
            } else {
                // Cabeçalho da tabela
                System.out.printf("%-8s %-35s %-17s %-33s %-17s %-39s%n", "ID", "Nome", "CPF", "Email", "Telefone",
                        "Endereço");
                System.out.println(
                        "-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                // Dados de cada cliente
                for (Cadastro c : clientes) {
                    System.out.printf("%-8d %-35s %-17s %-33s %-17s %-39s%n",
                            c.getId(), c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone(), c.getEndereco());
                }
            }

            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("1 - Próxima página");
            System.out.println("2 - Página anterior");
            System.out.println("0 - Voltar ao menu de alunos");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPag = Integer.parseInt(scanner.nextLine());

                switch (opcaoPag) {
                    case 1:
                        pagina++;
                        break;
                    case 2:
                        if (pagina > 0) {
                            pagina--;
                        } else {
                            System.out.println("⚠ Já está na primeira página.");
                        }
                        break;
                    case 0:
                        System.out.println("↩ Saindo da listagem...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida.");
                opcaoPag = -1;
            }

        } while (opcaoPag != 0);
    }

}
