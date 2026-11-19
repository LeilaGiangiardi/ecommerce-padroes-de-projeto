package src.ecommerce.patterns.chain;

/**
 * Padrão: Chain of Responsibility 
 * (Classe Base / Elo da Corrente)
 * Um molde que obriga cada regra de desconto a conhecer quem é o "próximo" da fila.
 * Evita um bloco gigante de if/else. 
 * Se este elo não aplicar desconto, repassa a conta para o próximo elo.
 */
public abstract class DescontoHandler {

    //referência para o próximo desconto na fila
    protected DescontoHandler proximo;

    // Liga um elo no outro (ex: cupom.setProximo(vip))
    public void setProximo(DescontoHandler proximo) {
        this.proximo = proximo;
    }

    // Cada desconto vai implementar sua própria regra
    public abstract double aplicarDesconto(double valorTotal, String cupom);
}