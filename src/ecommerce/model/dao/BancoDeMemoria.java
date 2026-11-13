package scr.ecommerce.model.dao;

import scr.ecommerce.model.vo.ProdutoVO;
import java.util.ArrayList;
import java.util.List;


 //Padrão: Singleton (banco de dados falso/ so pode existir um unico banco)

public class BancoDeMemoria {

    // 1º Passo do Singleton: Criar uma variável estática e privada do mesmo tipo da classe.
    // Ela vai guardar a nossa única instância. O 'static' significa que essa variável pertence à classe, e não ao objeto.
    private static BancoDeMemoria instanciaUnica;
    
    //variavel static -> todo mundo olha pro mesmo local na memoria

    // Esta é a lista que vai simular as tabelas do nosso banco de dados. 
    // Vai guardar todos os produtos da loja na memória RAM.
    private List<ProdutoVO> tabelaProdutos;

    // 2º Passo do Singleton: O Construtor precisa ser PRIVADO (private).
    // Isso impede que alguém de fora dê um "new BancoDeMemoria()".
    private BancoDeMemoria() {
        tabelaProdutos = new ArrayList<>();
        
        // Novos produtos: Livros e E-books
        tabelaProdutos.add(new ProdutoVO(1, "Livro Físico - Clean Code", 120.00));
        tabelaProdutos.add(new ProdutoVO(2, "E-book - Clean Code", 85.00));
        tabelaProdutos.add(new ProdutoVO(3, "Livro Físico - box: O Senhor dos Anéis", 250.00));
        tabelaProdutos.add(new ProdutoVO(4, "E-book - box: O Senhor dos Anéis", 45.00));
        tabelaProdutos.add(new ProdutoVO(5, "Livro Físico - Arquitetura Limpa", 110.00));
    }

    // 3º Passo do Singleton: Criar um método público estático (geralmente chamado getInstance)
    // para fornecer a única instância criada.
    //metodo static pode ser acessado sem precisar criar a instancia antes
    public static BancoDeMemoria getInstance() {
        // Se a instância ainda não existir (primeira vez que alguém chama), cria uma
        if (instanciaUnica == null) {
            instanciaUnica = new BancoDeMemoria();
        }
        //UMA E APENAS UMA instância if + construtor privado garante isso

        // Se já existir, a gente só devolve a que já estava pronta.
        return instanciaUnica;
    }

    // devolve a lista de produtos
    public List<ProdutoVO> getTabelaProdutos() {
        return tabelaProdutos;
    }
}