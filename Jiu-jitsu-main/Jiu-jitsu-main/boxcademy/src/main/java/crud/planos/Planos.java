package crud.planos;

import java.util.Scanner;

import crud.Cliente.CrudCliente;

public class Planos {

	public static void executar(Scanner scanner) {
		// TODO Auto-generated method stub
		
		
		   int opcao;
		do {
	            System.out.println("\n========== MENU - PLANOS  ==========");
	            System.out.println("1 - Plano mensal - R$ 80,00");
	            System.out.println("2 - Plano trimestral R$ 110,00");
	            System.out.println("3 - Plano semestral R$ 190,00");
	            System.out.println("4 - Plano anual R$ 300,00");
	            System.out.println("0 - Voltar ao menu principal");
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

	      
	       