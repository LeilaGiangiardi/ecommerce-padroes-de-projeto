package src.ecommerce.patterns.adapter;

//Padrão: Adapter (Sistema Externo / Incompatível)
 //Simula a API pronta do Mercado Pago. 
 //Como é código de terceiros, fingimos que NÃO podemos alterar o nome nem os parâmetros do método dela.
 
 //incompatibilidade:
 //Nosso sistem -> pagar(double valor)
 //Mercado Pago -> processarCobranca(double quantia, int parcelas)

public class MercadoPagoAPI {

    public boolean processarCobranca(double quantia, int parcelas) {
        System.out.printf("💙 [API Mercado Pago] Cobrando R$ %.2f em %dx no cartão...%n", quantia, parcelas);
        System.out.println("✅ [API Mercado Pago] Transação autorizada pela operadora!");
        return true;
    }
}