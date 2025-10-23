package main;

import java.util.Scanner;
import crud.Cliente.CrudCliente;
import crud.planos.Planos;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\n=======================================");
			System.out.println("SISTEMA DE CADASTRO DE Alunos");
			System.out.println("=========================================");
			System.out.println("1 - Gerenciar Alunos");
			System.out.println("2 - Opções de planos");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = Integer.parseInt(scanner.nextLine());

				switch (opcao) {
				case 1:
					CrudCliente.executar(scanner);
					break;
					
					case 2:
						Planos.executar(scanner);
						break;

				case 0:
					System.out.println("👋 Encerrando o sistema. Até logo!");
					break;
				default:
					System.out.println("❌ Opção inválida. Tente novamente.");
				}

			} catch (NumberFormatException e) {
				System.out.println("❌ Entrada inválida. Digite apenas números.");
				opcao = -1;
			}

		} while (opcao != 0);

		scanner.close();
	}
}
