package src.ecommerce.model;

//Classe base de Produto
//Representa a "ideia" de um produto
//as classes filhas vai implementar produto especifico: físico ou digital

public abstract class Produto {
    protected int id;
    protected String nome;
    protected double preco;

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    
    // Método abstrato: vai ser diferente para fisico ou digital
    //cada um implementa de um jeito diferente
    public abstract void exibirDetalhesDeEntrega();
}