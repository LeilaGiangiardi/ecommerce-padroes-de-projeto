package src.ecommerce;

import src.ecommerce.controller.EcommerceController;
import src.ecommerce.view.MenuView;

//Ponto de entrada da aplicação (MVC).
 //Apenas cria o Controller, conecta ele na View e liga o menu.
//sem regra de negócio.
 
public class Main {
    public static void main(String[] args) {
        EcommerceController controller = new EcommerceController();
        MenuView view = new MenuView(controller);
        view.iniciar();
    }
}