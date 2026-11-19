package src.ecommerce.patterns.chain;

//Elo 1: Verifica se o cliente digitou o cupom promocional "LIVRO10".

public class DescontoCupom extends DescontoHandler {

    @Override
    public double aplicarDesconto(double valorTotal, String cupom) {
        // equalsIgnoreCase aceita "LIVRO10" ou "livro10"
        if (cupom != null && cupom.equalsIgnoreCase("LIVRO10")) {
            double valorComDesconto = valorTotal - (valorTotal * 0.10); // 10% de desconto 
            System.out.println("🏷️ [Chain] Cupom LIVRO10 aplicado (-10%)!");
            return valorComDesconto;
        }

        // Não usou cupom? 
        // Passa a para o próximo elo (Frete Grátis)
        if (proximo != null) {
            return proximo.aplicarDesconto(valorTotal, cupom);
        }
        return valorTotal;
    }
}