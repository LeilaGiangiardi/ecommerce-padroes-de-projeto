package src.ecommerce.patterns.chain;


 //Elo 3 (último): 
 //Compras acima de R$ 100 ganham R$ 15,00 de abatimento de frete.
 
public class DescontoFreteGratis extends DescontoHandler {

    @Override
    public double aplicarDesconto(double valorTotal, String cupom) {
        if (valorTotal >= 100.00) {
            System.out.println("[Chain] Compra acima de R$ 100! Abatimento de Frete Grátis (-R$ 15,00)!");
            return valorTotal - 15.00;
        }

        // Fim da corrente: nenhum desconto foi aplicado, devolve o valor cheio
        System.out.println("[Chain] Nenhum desconto aplicável a esta compra.");
        return valorTotal;
    }
}