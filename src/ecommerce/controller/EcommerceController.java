package src.ecommerce.controller;

import src.ecommerce.model.Produto;
import src.ecommerce.model.dao.ProdutoDAO;
import src.ecommerce.model.dao.ProdutoDAOImpl;
import src.ecommerce.patterns.decorator.MarcadorPaginaDecorator;
import src.ecommerce.patterns.facade.CheckoutFacade;
import src.ecommerce.patterns.memento.CarrinhoMemento;
import src.ecommerce.patterns.memento.CarrinhoOriginator;
import src.ecommerce.patterns.strategy.PagamentoStrategy;


 //Camada: Controller (MVC)
 //Papel no Padrão Memento: Caretaker (o zelador que guarda a "foto" na gaveta)
 // recebe as ordens vindas da View (através dos Commands)
 //e coordena o DAO, o Carrinho, o Decorator e a CheckoutFacade.
 
public class EcommerceController {

    // Dependências principais
    private ProdutoDAO dao;
    private CarrinhoOriginator carrinho;
    private CheckoutFacade checkoutFacade;

    
    // MEMENTO CARETAKER
    // O Controller guarda a foto congelada do carrinho abandonado aqui
    // mas não consegue alterar os itens lá dentro (encapsulamento)
    private CarrinhoMemento carrinhoSalvoNaGaveta;

    public EcommerceController() {
        this.dao = new ProdutoDAOImpl();
        this.carrinho = new CarrinhoOriginator();
        this.checkoutFacade = new CheckoutFacade();
    }

    // Dá acesso ao DAO para os Commands listarem e buscarem livros
    public ProdutoDAO getDao() {
        return dao;
    }

    // Dá acesso ao Carrinho para os Commands exibirem os itens na tela
    public CarrinhoOriginator getCarrinho() {
        return carrinho;
    }

    
    // 1. adicionar produto (com decorator)
    public void adicionarProduto(Produto produtoBase, boolean comMarcador) {
        // Começa com o livro normal (físico ou e-book)
        Produto produtoFinal = produtoBase;

        // Se o cliente pediu o Marcador de Página (+ R$ 5,00), tenta "embrulhar" o livro
        if (comMarcador) {
            try {
                // Se for livro físico: embrulha com sucesso.
                // Se for E-book: o construtor do MarcadorPaginaDecorator lança IllegalArgumentException!
                produtoFinal = new MarcadorPaginaDecorator(produtoBase);

            } catch (IllegalArgumentException e) {
                // Mostra o aviso e mantém 'produtoFinal' como o e-book original (sem marcador)
                System.out.println("❌ Atenção: " + e.getMessage());
                System.out.println("ℹ️ Adicionando o E-book normalmente (sem marcador de página)...");
            }
        }

        // Coloca no carrinho (com marcador se deu certo, ou sem marcador se era e-book)
        carrinho.adicionarProduto(produtoFinal);
        System.out.println("✅ Adicionado ao carrinho: " + produtoFinal.getNome());
    }

    
    // 2. carrinho abandonado (memento)
    public void abandonarCarrinho() {
        // Evita salvar uma foto de carrinho vazio
        if (carrinho.getCarrinhoVO().getItens().isEmpty()) {
            System.out.println("⚠️ O carrinho está vazio, não há nada para salvar.");
            return;
        }

        // Manda o Originator tirar a foto, guarda o retorno na gaveta e limpa o carrinho atual
        this.carrinhoSalvoNaGaveta = carrinho.salvarCarrinhoAbandonado();
        carrinho.limparCarrinho();
        System.out.println("💾 Progresso salvo na gaveta do Controller. Carrinho atual limpo.");
    }

    public void restaurarCarrinho() {
        // Só restaura se existir uma foto guardada na gaveta
        if (this.carrinhoSalvoNaGaveta != null) {
            // Devolve a foto da gaveta para o Originator remontar o carrinho
            carrinho.restaurarCarrinhoAbandonado(this.carrinhoSalvoNaGaveta);
            // Esvazia a gaveta depois de restaurar
            this.carrinhoSalvoNaGaveta = null;
        } else {
            System.out.println("⚠️ Nenhum carrinho abandonado encontrado para restaurar.");
        }
    }

    // 3. finaliza compra (botão/chamada unico do facade)

    public void finalizarCompra(String cupom, PagamentoStrategy estrategiaPagamento) {
        boolean compraAprovada = checkoutFacade.finalizarCompra(
                carrinho.getCarrinhoVO().getItens(),
                cupom,
                estrategiaPagamento
        );

        // Se a compra foi aprovada pela Facade, esvazia o carrinho para o próximo pedido
        if (compraAprovada) {
            carrinho.limparCarrinho();
        }
    }
}