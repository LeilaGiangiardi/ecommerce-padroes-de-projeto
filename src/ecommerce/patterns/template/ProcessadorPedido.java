package src.ecommerce.patterns.template;

import src.ecommerce.model.Produto;
import src.ecommerce.patterns.strategy.PagamentoStrategy;
import java.util.List;

//Padrão: Template Method (classe Pai /receita de Bolo)
//define a ORDEM FIXA de como um pedido é processado
//garante todos os metodos necessarios para comprar um livro 
 
public abstract class ProcessadorPedido {

    // a palavra 'final' é o segredo (nao pode mudar essa ordem)
    // Todo pedido, físico ou digital, É OBRIGADO a seguir exatamente: 1 -> 2 -> 3.
    public final boolean processarPedido(List<Produto> itens, double valorFinal, PagamentoStrategy pagamento) {

        // Passo 1: Sempre baixa o estoque primeiro (igual para todos)
        baixarEstoque(itens);

        // Passo 2: Sempre cobra o pagamento usando a Strategy escolhida (igual para todos)
        boolean pagamentoAprovado = cobrar(valorFinal, pagamento);

        // Passo 3: Só entrega se o pagamento der certo!
        // 'entregar' é abstrato: o Java vai rodar o código da classe filha
        // (ProcessadorPedidoFisico OU ProcessadorPedidoDigital).
        if (pagamentoAprovado) {
            entregar(itens);
            return true;
        }

        System.out.println("❌ Pagamento recusado. O pedido não será entregue.");
        return false;
    }

    // Passo 1 : Nenhuma classe filha precisa reescrever isso
    private void baixarEstoque(List<Produto> itens) {
        System.out.println("📦 [Template - Passo 1] Reservando e baixando " + itens.size() + " item(ns) no estoque...");
    }

    // Passo 2 : Chama a Strategy (Pix ou Cartão/Mercado Pago)
    private boolean cobrar(double valorFinal, PagamentoStrategy pagamento) {
        System.out.println("💰 [Template - Passo 2] Acionando o pagamento...");
        return pagamento.pagar(valorFinal);
    }

    // Passo 3 (Variável e abstrato):
    // 'protected' = só as classes filhas enxergam/alteram.
    // 'abstract' = não tem corpo aqui; cada classe filha É OBRIGADA a explicar COMO entrega.
    protected abstract void entregar(List<Produto> itens);
}