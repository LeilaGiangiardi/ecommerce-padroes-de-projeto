package src.ecommerce.patterns.strategy;

//Padrão 13: Strategy (Interface da Estratégia)
//contrato comum que toda forma de pagamento precisa assinar
//O Checkout só chama pagamento.pagar(valor). 
//não precisa de 'if' pra Pix, Cartão ou Boleto.
 
public interface PagamentoStrategy {

    // Retorna true se o pagamento for aprovado, ou false se falhar
    boolean pagar(double valor);
}