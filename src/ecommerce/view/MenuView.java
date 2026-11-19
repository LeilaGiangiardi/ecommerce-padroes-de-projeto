package src.ecommerce.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import src.ecommerce.controller.EcommerceController;
import src.ecommerce.controller.commands.AbandonarCarrinhoCommand;
import src.ecommerce.controller.commands.AdicionarAoCarrinhoCommand;
import src.ecommerce.controller.commands.Command;
import src.ecommerce.controller.commands.FinalizarCompraCommand;
import src.ecommerce.controller.commands.ListarCatalogoCommand;
import src.ecommerce.controller.commands.RestaurarCarrinhoCommand;
import src.ecommerce.controller.commands.VerCarrinhoCommand;


//Camada: View (MVC)
//Exibe o menu no terminal e lê as opções digitadas pelo usuário.
//Graças ao Padrão Command, a View NÃO tem um switch/case
// não conhece regras de negócio
//apenas associa cada número do menu a um objeto Command dentro de um Map e chama comando.executar().

public class MenuView {

    private Scanner scanner;

    // Mapa que liga o número da opção (1 a 6) ao seu respectivo Command
    private Map<Integer, Command> comandos;

    public MenuView(EcommerceController controller) {
        this.scanner = new Scanner(System.in);
        this.comandos = new HashMap<>();

        // Registra cada "botão" do menu com o objeto Command correspondente
        comandos.put(1, new ListarCatalogoCommand(controller));
        comandos.put(2, new AdicionarAoCarrinhoCommand(controller, scanner));
        comandos.put(3, new VerCarrinhoCommand(controller));
        comandos.put(4, new AbandonarCarrinhoCommand(controller));
        comandos.put(5, new RestaurarCarrinhoCommand(controller));
        comandos.put(6, new FinalizarCompraCommand(controller, scanner));
    }

    // Loop principal que mantém a loja aberta até o usuário escolher 0 (Sair)
    public void iniciar() {
        boolean executando = true;

        System.out.println("===========================================");
        System.out.println("   🛒 BEM-VINDO AO E-COMMERCE DE LIVROS    ");
        System.out.println("===========================================");

        while (executando) {
            exibirOpcoes();
            String entrada = scanner.nextLine();

            try {
                // Converte o texto digitado para número inteiro
                int opcao = Integer.parseInt(entrada);

                if (opcao == 0) {
                    System.out.println("👋 Encerrando a loja. Até a próxima!");
                    executando = false;
                } else if (comandos.containsKey(opcao)) {
                    // A MÁGICA DO PADRÃO COMMAND: executa a ação certa sem if/else!
                    comandos.get(opcao).executar();
                } else {
                    System.out.println("⚠️ Opção inválida! Escolha um número entre 0 e 6.");
                }

            } catch (NumberFormatException e) {
                // Evita que o programa feche se o usuário digitar uma letra por engano
                System.out.println("⚠️ Entrada inválida! Digite apenas números.");
            }
        }

        // Fecha o leitor do teclado ao encerrar o programa
        scanner.close();
    }

    // Método auxiliar privado apenas para desenhar o menu na tela
    private void exibirOpcoes() {
        System.out.println("\n----------- MENU PRINCIPAL -----------");
        System.out.println("1 - Listar Catálogo de Livros");
        System.out.println("2 - Adicionar Livro ao Carrinho");
        System.out.println("3 - Ver Carrinho Atual");
        System.out.println("4 - Abandonar Carrinho (Salvar com Memento)");
        System.out.println("5 - Restaurar Carrinho Abandonado (Memento)");
        System.out.println("6 - Finalizar Compra (Checkout Completo)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}