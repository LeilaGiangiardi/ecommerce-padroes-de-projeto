package src.ecommerce.patterns.template;

import src.ecommerce.model.Produto;
import java.util.List;


//Padrão 15: Template Method (Subclasse - Entrega Digital)
//Herda os Passos 1 e 2 da classe pai e preenche APENAS o Passo 3
//com a lógica de liberação imediata de download para E-books.

public class ProcessadorPedidoDigital extends ProcessadorPedido {

    @Override
    protected void entregar(List<Produto> itens) {
        System.out.println("☁️ [Template - Passo 3] Liberando links de download imediato dos E-books:");

        for (Produto p : itens) {
            p.exibirDetalhesDeEntrega();
        }
    }
}