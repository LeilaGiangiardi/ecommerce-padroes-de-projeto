package src.ecommerce.controller.commands;

//Padrão: Command (Interface Base)
//obriga cada opção do menu a ter um método executar().
// A View (menu) não precisa saber COMO cada ação funciona 
// ela só guarda os comandos num Mapa e chama comando.executar().

public interface Command {
    void executar();
}