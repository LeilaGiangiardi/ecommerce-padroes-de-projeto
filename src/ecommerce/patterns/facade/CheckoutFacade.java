package src.ecommerce.patterns.facade;

import src.ecommerce.model.Produto;
import src.ecommerce.model.ProdutoDigital;
import src.ecommerce.patterns.chain.DescontoCupom;
import src.ecommerce.patterns.chain.DescontoFreteGratis;
import src.ecommerce.patterns.chain.DescontoHandler;
import src.ecommerce.patterns.observer.CompraObserver;
import src.ecommerce.patterns.observer.DisparadorDeEmailObserver;
import src.ecommerce.patterns.observer.NotaFiscalObserver;
import src.ecommerce.patterns.strategy.PagamentoStrategy;
import src.ecommerce.patterns.template.ProcessadorPedido;
import src.ecommerce.patterns.template.ProcessadorPedidoDigital;
import src.ecommerce.patterns.template.ProcessadorPedidoFisico;
import java.util.ArrayList;
import java.util.List;

//Padrão: Facade (fachada Simplificadora)
 
//Uma "porta da frente" que esconde um subsistema complexo
 //Sem a Facade, o Controller teria que saber montar a corrente de
//descontos, decidir qual Template Method usar, disparar o pagamento e avisar
//cada Observer manualmente. 
//Com a Facade, o Controller aperta um único "botão":
//checkoutFacade.finalizarCompra(...).
 
public class CheckoutFacade {

    // Primeiro elo da corrente de descontos (Padrão Chain of Responsibility)
    private DescontoHandler cadeiaDeDescontos;

    // Lista de serviços que querem ser avisados quando uma compra der certo (Padrão Observer)
    private List<CompraObserver> observadores;

    // Construtor: Já deixa todas as engrenagens montadas assim que o sistema nasce
    public CheckoutFacade() {
        // 1. Monta a corrente de descontos: Cupom -> Frete Grátis
        DescontoHandler cupom = new DescontoCupom();
        DescontoHandler freteGratis = new DescontoFreteGratis();
        cupom.setProximo(freteGratis); // Liga o Elo 1 ao Elo 2
        this.cadeiaDeDescontos = cupom; // A fila sempre começa pelo Cupom

        // 2. Inscreve os ouvintes no "sininho" de compras aprovadas
        this.observadores = new ArrayList<>();
        this.observadores.add(new NotaFiscalObserver());
        this.observadores.add(new DisparadorDeEmailObserver());
    }


    // O "BOTÃO ÚNICO" 
    // Recebe apenas os itens do carrinho, o texto do cupom e a forma de pagamento
    public boolean finalizarCompra(List<Produto> itens, String cupom, PagamentoStrategy pagamento) {

        // Trava de segurança: impede finalizar compra com carrinho vazio
        if (itens == null || itens.isEmpty()) {
            System.out.println("⚠️ [Facade] Seu carrinho está vazio! Adicione livros antes de finalizar.");
            return false;
        }

        System.out.println("\n================ INICIANDO CHECKOUT (FACADE) ================");

        // PASSO 1: Soma o valor bruto e verifica se existe algum item físico no carrinho
        double valorBruto = 0;
        boolean possuiItemFisico = false;

        for (Produto p : itens) {
            // Se o livro tiver MarcadorPaginaDecorator, getPreco() já soma os + R$ 5,00 automaticamente!
            valorBruto += p.getPreco();

            // Se o item NÃO for um E-book (ou seja, é ProdutoFisico ou um livro físico com Marcador),
            // precisamos de entrega física (Correios).
            if (!(p instanceof ProdutoDigital)) {
                possuiItemFisico = true;
            }
        }
        System.out.printf("💵 Subtotal do carrinho: R$ %.2f%n", valorBruto);

        // PASSO 2 (Chain of Responsibility): Passa o valor pela cascata Cupom -> Frete Grátis
        double valorFinal = cadeiaDeDescontos.aplicarDesconto(valorBruto, cupom);
        System.out.printf("🧾 Valor Final a Pagar: R$ %.2f%n", valorFinal);

        // PASSO 3 (Template Method + Strategy/Adapter):
        // Escolhe a subclasse de entrega certa (Físico se houver pelo menos 1 livro físico; Digital se só houver E-books)
        ProcessadorPedido processador;
        if (possuiItemFisico) {
            processador = new ProcessadorPedidoFisico();
        } else {
            processador = new ProcessadorPedidoDigital();
        }

        // Executa o roteiro fixo do Template Method: 1. Baixar Estoque -> 2. Cobrar -> 3. Entregar
        boolean sucesso = processador.processarPedido(itens, valorFinal, pagamento);

        // PASSO 4 (Observer): Se o pedido foi pago e entregue, aperta o "sininho" para os ouvintes
        if (sucesso) {
            String resumo = String.format("%d item(ns) | Total pago: R$ %.2f", itens.size(), valorFinal);
            notificarObservadores(resumo);
            System.out.println("================ COMPRA CONCLUÍDA COM SUCESSO! ================\n");
        }

        return sucesso;
    }

    // Método auxiliar privado: percorre a lista e avisa Nota Fiscal + E-mail
    private void notificarObservadores(String resumo) {
        for (CompraObserver obs : observadores) {
            obs.atualizar(resumo);
        }
    }
}