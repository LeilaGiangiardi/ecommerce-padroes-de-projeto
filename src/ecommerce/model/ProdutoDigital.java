package src.ecommerce.model;

//produto digital -> Herda de Produto
//Um tipo específico de produto
// atributo especifico: e-book (email ou link?)
 
public class ProdutoDigital extends Produto {
    
    private String linkDownload;

    public ProdutoDigital(int id, String nome, double preco, String linkDownload) {
        super(id, nome, preco); 
        this.linkDownload = linkDownload;
    }

    //implementa o metodo abstrato de produto
    @Override
    public void exibirDetalhesDeEntrega() {
        System.out.println("🔗 Entrega Imediata por E-mail. Link para baixar: " + linkDownload);
    }
}