package src.ecommerce.patterns.memento;

import src.ecommerce.model.Produto;
import src.ecommerce.model.vo.CarrinhoVO;
import java.util.ArrayList;

//padrão Memento Originator
//manda salvar e restaurar o checkpoint
//aqui são feitas as alterações do memento
//é o único que sabe COMO o seu estado é guardado

public class CarrinhoOriginator {
    
    //VO que guarda a referencia da lista atual de compras
    private CarrinhoVO carrinhoVO;

    public CarrinhoOriginator() {
        this.carrinhoVO = new CarrinhoVO();
    }
    
    public CarrinhoVO getCarrinhoVO() {
        return carrinhoVO; //retorna nosso carrinho atual
    } 

    // adiciona produtos
    public void adicionarProduto(Produto produto) {
        carrinhoVO.adicionarItem(produto);
    }

    //esvazia o carrinho -> substitui por uma lista vazia
    public void limparCarrinho() {
        carrinhoVO.setItens(new ArrayList<>());
    }

    
    // -> RECURSO DE CARRINHO ABANDONADO <-
    // obs: carrinho abandonado é quando o usuario sai, volta, e o carrinho dele é restaurado a como tava
   

    //o controller avisa quando o usuario vai sair, e chama o salvarCarrinhoAbandonado
    // pega a lista que está no VO carrinhoVO.getItens  
    // joga os itens puxados em um novo memento -> CarrinhoMemento(itens puxados)
    
    // return -> entrega o save na mão do Controller 
    
    public CarrinhoMemento salvarCarrinhoAbandonado() {
        System.out.println("📸 Salvando estado atual como Carrinho Abandonado...");
        return new CarrinhoMemento(carrinhoVO.getItens());
    } // return -> entrega o save na mão do Controller 

    // o controller avisa: "usuario voltou" 
    //save é restaurado
    public void restaurarCarrinhoAbandonado(CarrinhoMemento mementoAntigo) {
        carrinhoVO.setItens(new ArrayList<>(mementoAntigo.getEstadoSalvo())); 
        System.out.println("🔄 Carrinho restaurado com sucesso!");
    }
    //recebe o mementoAntigo do controlle
    // getEstadoSalvo() -> retorna uma lista dos itens do save
    //new ArrayList<> -> instancia uma nova lista com os itens puxados do save
    //carrinhoVO.setItens -> passa essa lista restaurada por cima do que quer que esteja no carrinho agora
}