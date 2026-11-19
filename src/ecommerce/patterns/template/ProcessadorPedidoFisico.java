package src.ecommerce.patterns.template;

import src.ecommerce.model.Produto;
import java.util.List;

//Padrão: Template Method (Subclasse - Entrega Física)
//Herda automaticamente os Passos 1 e 2 da classe pai (ProcessadorPedido)
 //puxa APENAS o Passo 3 (entregar) com a lógica de envio pelos Correios
 
public class ProcessadorPedidoFisico extends ProcessadorPedido {

    @Override
    protected void entregar(List<Produto> itens) {
        System.out.println("🚚 [Template - Passo 3] Separando livros físicos e gerando etiqueta dos Correios:");

        // Percorre cada produto do pedido e manda ele exibir sua própria regra de entrega
        // (se o livro tiver o MarcadorPaginaDecorator, ele repassa para o livro físico normalmente)
        for (Produto p : itens) {
            p.exibirDetalhesDeEntrega();
        }
    }
}