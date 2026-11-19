package src.ecommerce.patterns.strategy;

import src.ecommerce.patterns.adapter.MercadoPagoAdapter;

//Padrão: Strategy (Estratégia - Cartão de Crédito)
//ADAPTER: Esta classe representa a opção "Cartão" da loja,
// mas passa a comunicação técnica para o MercadoPagoAdapter.
 // Se amanhã a loja trocar o Mercado Pago pela Cielo ou Stone, basta trocar o Adapter aqui.
 
public class PagamentoCartao implements PagamentoStrategy {

    private MercadoPagoAdapter gatewayAdapter;

    public PagamentoCartao() {
        this.gatewayAdapter = new MercadoPagoAdapter();
    }

    @Override
    public boolean pagar(double valor) {
        System.out.println("💳 [Strategy - Cartão] Iniciando pagamento no cartão de crédito...");
        // Repassa a cobrança para o Adapter fazer a tradução com o Mercado Pago
        return gatewayAdapter.pagar(valor); //valor + parcela x1
    }
}