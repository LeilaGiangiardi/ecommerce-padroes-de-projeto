package src.ecommerce.model.dao;

import java.util.ArrayList;
import java.util.List;

import src.ecommerce.model.vo.ProdutoVO;

//banco de dados falso
 //Padrão Singleton: so pode existir um unico banco/instancia

public class BancoDeMemoria {

    //Cria uma variável estática e privada do mesmo tipo da classe
    //guarda a única instância
    //O 'static' -> todo mundo olha pro mesmo local na memoria
    private static BancoDeMemoria instanciaUnica;
    
    
    // lista que simula as tabelas do banco de dados
    // Vai guardar todos os produtos da loja na memória RAM.
    private List<ProdutoVO> tabelaProdutos;

    //O Construtor precisa ser PRIVADO
    //impede que alguém de fora dê um "new BancoDeMemoria()"
    private BancoDeMemoria() {
        tabelaProdutos = new ArrayList<>();
        
        tabelaProdutos.add(new ProdutoVO(1, "Livro Físico - Clean Code", 120.00));
        tabelaProdutos.add(new ProdutoVO(2, "E-book - Clean Code", 85.00));
        tabelaProdutos.add(new ProdutoVO(3, "Livro Físico - box: O Senhor dos Anéis", 250.00));
        tabelaProdutos.add(new ProdutoVO(4, "E-book - box: O Senhor dos Anéis", 45.00));
        tabelaProdutos.add(new ProdutoVO(5, "Livro Físico - Arquitetura Limpa", 110.00));
    }

    // Criar um método público estático 
    // fornece a única instância criada
    // metodo static pode ser acessado sem precisar criar a instancia antes
    public static BancoDeMemoria getInstance() {
        // Se a instância ainda não existir (primeira vez que alguém chama), cria uma
        if (instanciaUnica == null) {
            instanciaUnica = new BancoDeMemoria();
        }
        //UMA E APENAS UMA instância if + construtor privado garante isso

        //Se já existir, só devolve a que já estava pronta sem criar outra
        return instanciaUnica;
    }

    // devolve a lista de produtos
    public List<ProdutoVO> getTabelaProdutos() {
        return tabelaProdutos;
    }
}