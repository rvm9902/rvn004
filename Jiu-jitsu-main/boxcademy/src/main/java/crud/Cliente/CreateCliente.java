package crud.Cliente;

import java.util.Scanner;
import dao.DAO;
import model.Cadastro;

public class CreateCliente {

    public static void executar(Scanner scanner) {
        int escolha;

        do {
            System.out.println("\n========== CADASTRO DE CLIENTE ==========");
            System.out.println("1 - Iniciar cadastro de cliente");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        cadastrarClientes(scanner); // Método separado para organização
                        break;
                    case 0:
                        System.out.println("↩ Retornando ao menu de clientes...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite um número.");
                escolha = -1;
            }

        } while (escolha != 0);
    }

    // Método que realiza o cadastro de clientes no banco de dados
    private static void cadastrarClientes(Scanner scanner) {
        DAO<Cadastro> dao = new DAO<>(Cadastro.class);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n📝 Cadastro de Novo Cliente");

            // Captura os dados básicos
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.print("RG: ");
            String rg = scanner.nextLine();

            System.out.print("Data de Nascimento (dd/mm/aaaa): ");
            String dataNascimento = scanner.nextLine();

            System.out.print("Gênero (Masculino/Feminino/Outro): ");
            String genero = scanner.nextLine();
            
            System.out.print("Plano (Mensal, trimestral, semestral, anual: ");
            String Plano = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            // Endereço detalhado
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            System.out.print("Estado: ");
            String estado = scanner.nextLine();

            System.out.print("CEP: ");
            String cep = scanner.nextLine();

            // Campo adicional
            System.out.print("Observações: ");
            String observacoes = scanner.nextLine();

            // Cria o objeto Cadastro com os novos campos
            Cadastro novoCliente = new Cadastro(
                nome, cpf, rg, dataNascimento, genero, Plano, email,
                telefone, endereco, cidade, estado, cep, observacoes
            );

            dao.incluirTransacional(novoCliente);
            System.out.println("✅ Cliente cadastrado com sucesso!");

            System.out.print("\nDeseja cadastrar outro cliente? (s/n): ");
            String resposta = scanner.nextLine().toLowerCase();

            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("↩ Encerrando cadastro de clientes...");
            }
        }

        dao.fecharEm(); // Fecha o EntityManager
    }
}
