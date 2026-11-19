package src.ecommerce.patterns.observer;

//Padrão: Observer (Ouvinte  2 - Serviço de E-mail)
//O QUE FAZ: Fica aguardando o aviso de compra aprovada para enviar o recibo por e-mail.

public class DisparadorDeEmailObserver implements CompraObserver {

    @Override
    public void atualizar(String resumoDaCompra) {
        System.out.println("📧 [Observer - E-mail] Enviando confirmação da compra para o cliente -> " + resumoDaCompra);
    }
}