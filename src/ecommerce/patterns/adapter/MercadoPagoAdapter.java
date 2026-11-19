package src.ecommerce.patterns.adapter;

import src.ecommerce.patterns.strategy.PagamentoStrategy;

// Padrão Adapter 
//traduz a api incompativel 
// Por fora, ela fala a língua do nosso sistema
//(implementa PagamentoStrategy). 
// Por dentro, ela conversa com a API do Mercado Pago.
 

public class MercadoPagoAdapter implements PagamentoStrategy {

    // Guarda a API externa incompatível dentro do adaptador
    private MercadoPagoAPI mercadoPagoAPI;

    public MercadoPagoAdapter() {
        this.mercadoPagoAPI = new MercadoPagoAPI();
    }

    @Override
    public boolean pagar(double valor) {
        // AQUI ACONTECE A TRADUÇÃO:
        // Recebe pagar(valor) do nosso sistema e traduz para processarCobranca(valor, 1)
        System.out.println("🔌 [Adapter] Traduzindo cobrança para o formato da API do Mercado Pago...");
        return mercadoPagoAPI.processarCobranca(valor, 1); // 1x à vista por padrão
    }
}