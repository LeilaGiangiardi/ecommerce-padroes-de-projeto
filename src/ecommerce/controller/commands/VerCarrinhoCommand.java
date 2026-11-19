package src.ecommerce.controller.commands;

import src.ecommerce.controller.EcommerceController;
import src.ecommerce.model.Produto;
import java.util.Iterator;
import java.util.List;

/**
 * Comando 3: Lista os itens que estão no carrinho atual e mostra a soma total.
 */
public class VerCarrinhoCommand implements Command {

    private EcommerceController controller;

    public VerCarrinhoCommand(EcommerceController controller) {
        this.controller = controller;
    }

    @Override
    public void executar() {
        System.out.println("\n🛒 --- SEU CARRINHO ---");
        List<Produto> itens = controller.getCarrinho().getCarrinhoVO().getItens();

        if (itens.isEmpty()) {
            System.out.println("(Seu carrinho está vazio)");
            return;
        }

        double total = 0;
        // Percorre os itens do carrinho usando Iterator
        Iterator<Produto> it = itens.iterator();
        while (it.hasNext()) {
            Produto p = it.next();
            System.out.printf("- %s | R$ %.2f%n", p.getNome(), p.getPreco());
            total += p.getPreco();
        }
        System.out.printf("💰 Total atual: R$ %.2f%n", total);
    }
}