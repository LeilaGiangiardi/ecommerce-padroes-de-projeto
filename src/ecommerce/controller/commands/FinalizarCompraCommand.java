package src.ecommerce.controller.commands;

import src.ecommerce.controller.EcommerceController;
import src.ecommerce.patterns.strategy.PagamentoCartao;
import src.ecommerce.patterns.strategy.PagamentoPix;
import src.ecommerce.patterns.strategy.PagamentoStrategy;
import java.util.Scanner;

//Comando 6: Coleta o cupom e a forma de pagamento (Strategy) e dispara o Checkout na Facade.
 
public class FinalizarCompraCommand implements Command {

    private EcommerceController controller;
    private Scanner scanner;

    public FinalizarCompraCommand(EcommerceController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void executar() {
        System.out.print("\nPossui cupom de desconto? (Digite LIVRO10 ou aperte Enter para pular): ");
        String cupom = scanner.nextLine().trim(); // trim() remove espaços acidentais nas pontas

        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Pix (Strategy Direta)");
        System.out.println("2 - Cartão de Crédito (Strategy + Adapter Mercado Pago)");
        System.out.print("Opção: ");
        String opcaoPag = scanner.nextLine();

        // Escolhe qual Strategy plugar na CheckoutFacade
        PagamentoStrategy estrategia;
        if (opcaoPag.equals("2")) {
            estrategia = new PagamentoCartao();
        } else {
            estrategia = new PagamentoPix();
        }

        // Manda o Controller finalizar a compra usando a Facade
        controller.finalizarCompra(cupom, estrategia);
    }
}