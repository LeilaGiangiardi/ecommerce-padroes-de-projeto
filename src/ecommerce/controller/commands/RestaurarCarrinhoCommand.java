package src.ecommerce.controller.commands;

import src.ecommerce.controller.EcommerceController;

//Comando 5: Aciona o Controller (Caretaker) para restaurar o último carrinho salvo na gaveta.
 
public class RestaurarCarrinhoCommand implements Command {

    private EcommerceController controller;

    public RestaurarCarrinhoCommand(EcommerceController controller) {
        this.controller = controller;
    }

    @Override
    public void executar() {
        controller.restaurarCarrinho();
    }
}