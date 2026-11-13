package src.ecommerce.model.vo;

import src.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;


// Padrão: VO (Value Object) 
// objeto simples -> guarda e transporta dados
// usado no banco/controller para a tela (View)  
// sem acesso às regras de negócio

public class CarrinhoVO {
    
    //guarda a lista de produtos escolhidos pelo cliente.
    private List<Produto> itens;

    public CarrinhoVO() {
        // inicializa a lista vazia -> evita erro de "NullPointerException"
        this.itens = new ArrayList<>();
    }

    public List<Produto> getItens() {
        return itens;
    } 

    //adiciona um produto por vez na lista de compras
    public void adicionarItem(Produto produto) {
        this.itens.add(produto);
    }
    
    //permite substituir a lista atual inteira por uma nova lista
    //ESSENCIAL para o Memento(quando o usuário restaurar o Carrinho Abandonado)
    //o sistema pega a lista antiga e joga aqui dentro
    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
}