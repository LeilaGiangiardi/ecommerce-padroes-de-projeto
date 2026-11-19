package src.ecommerce.controller.commands;

import src.ecommerce.controller.EcommerceController;

//Comando 4: Aciona o Controller (Caretaker) para salvar o carrinho com Memento e esvaziar a sacola.
 
public class AbandonarCarrinhoCommand implements Command {

    private EcommerceController controller;

    public AbandonarCarrinhoCommand(EcommerceController controller) {
        this.controller = controller;
    }

    @Override
    public void executar() {
        controller.abandonarCarrinho();
    }
}