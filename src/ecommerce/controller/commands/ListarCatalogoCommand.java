package src.ecommerce.controller.commands;

import src.ecommerce.controller.EcommerceController;
import src.ecommerce.model.vo.ProdutoVO;
import java.util.Iterator;
import java.util.List;

//Comando 1: Obtém todos os livros no DAO (como ProdutoVO) e imprime o catálogo usando Iterator.

public class ListarCatalogoCommand implements Command {

    private EcommerceController controller;

    public ListarCatalogoCommand(EcommerceController controller) {
        this.controller = controller;
    }

    @Override
    public void executar() {
        System.out.println("\n📚 --- CATÁLOGO DE LIVROS ---");
        // O DAO devolve ProdutoVO (Padrão VO/DTO) para exibição no ecrã
        List<ProdutoVO> produtos = controller.getDao().buscarTodos();

        // Padrão Iterator: percorre os VOs sem depender de índice [i]
        Iterator<ProdutoVO> it = produtos.iterator();
        while (it.hasNext()) {
            ProdutoVO p = it.next();
            System.out.println(p);
        }
    }
}