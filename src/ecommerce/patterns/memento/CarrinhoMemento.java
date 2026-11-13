package src.ecommerce.patterns.memento;

import src.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;

//Padrão: Memento (é o arquivo salvo -> memory card)
//objeto imutável que guarda o estado de algo 
// não tem set, não altera nada, só salva

public class CarrinhoMemento {
    
    // o status da lista que queremos salvar
    // 'final' para garantir que vai ser imutável
    private final List<Produto> estadoSalvo;

    //é chamado pelo Originator no momento em que o usuário decide sair
    public CarrinhoMemento(List<Produto> estadoParaSalvar) {
        //instancia um novo array com os itens do carinho do usuario
        //copia independente 
        //this.estadoSalvo = estadoParaSalvar -> errado (seria um atalho, perdido se perder a lista original)
        this.estadoSalvo = new ArrayList<>(estadoParaSalvar);
    }

    // Apenas getter, não pode alterar nada (imutável)
    public List<Produto> getEstadoSalvo() {
        return estadoSalvo;
    }
}