package src.ecommerce.patterns.strategy;

//Padrão: Strategy (Estratégia 1 - Pix)
//Implementa Pix diretamente no nosso sistema
//simulação de pix

public class PagamentoPix implements PagamentoStrategy {

    @Override
    public boolean pagar(double valor) {
        System.out.printf("⚡ [Strategy - Pix] Gerando QR Code Pix no valor de R$ %.2f...%n", valor);
        System.out.println("✅ [Strategy - Pix] Pagamento via Pix aprovado na hora!");
        return true;
    }
}