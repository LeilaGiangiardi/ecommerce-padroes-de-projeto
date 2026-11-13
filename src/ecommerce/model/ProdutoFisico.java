package src.ecommerce.model;

//Produto físico -> herda de produto
//Um tipo específico de produto
//atributo espeficico: correios, frete
 
public class ProdutoFisico extends Produto {
    
    private double pesoKg;

    public ProdutoFisico(int id, String nome, double preco, double pesoKg) {
        super(id, nome, preco);
        this.pesoKg = pesoKg;
    }

    // implementação do metodo abstrato de Produto
    @Override
    public void exibirDetalhesDeEntrega() {
        System.out.println("📦 Entrega via Correios. Peso da caixa: " + pesoKg + " kg.");
    }
}