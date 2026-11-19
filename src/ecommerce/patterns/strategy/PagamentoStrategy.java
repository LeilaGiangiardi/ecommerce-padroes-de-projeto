package src.ecommerce.patterns.strategy;

//Padrão: Strategy (Interface)
//Contrato comum para todas as formas de pagamento
// O Checkout só precisa chamar estrategia.pagar(valor),
//sem saber se por trás é Pix, Cartão, Boleto 

public interface PagamentoStrategy {
    boolean pagar(double valor);
}