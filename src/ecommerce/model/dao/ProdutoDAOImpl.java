package src.ecommerce.model.dao;

import java.util.List;

import src.ecommerce.model.vo.ProdutoVO;

 //Implementação das assinaturas do ProdutoDAO
 //conecta as regra do DAO (buscar e salvar) + "banco de dados" -> BancoDeMemoria
 //seria aqui os codigos SQL, se tivessemos um banco de verdade
 
public class ProdutoDAOImpl implements ProdutoDAO {

    //guarda a referência do nosso banco.
    private BancoDeMemoria banco;

    // No construtor, pega a única instância do Singleton -> metodo getInstance 
    public ProdutoDAOImpl() {
        this.banco = BancoDeMemoria.getInstance();
    }

    //obs: @Override -> implementa os métodos exigido pela interface ProdutoDAO
    @Override
    public void salvar(ProdutoVO produto) {
        // Pega a lista do banco e adiciona o produto novo
        banco.getTabelaProdutos().add(produto);
    }

    @Override
    public List<ProdutoVO> buscarTodos() {
        // Retorna a lista inteira de produtos
        return banco.getTabelaProdutos();
    }

    @Override
    public ProdutoVO buscarPorId(int id) {
        // Percorre a lista de produtos um por um
        for (ProdutoVO produto : banco.getTabelaProdutos()) {
            // Se o ID do produto atual for igual ao ID que estamos procurando
            if (produto.getId() == id) {
                return produto; //devolve o produto
            }
        }
        return null; // Se acabar a lista e não achar, devolve "vazio" (null)
    }
}