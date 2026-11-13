package src.ecommerce.model.vo;

//Padrão: VO (Value Object): Um objeto simples sem regras de negocio
//O DAO  preenchem esse VO e mandam para a View.
 
public class ProdutoVO {
    //Encapsulamento -> para garantir que não vai ser alterado
    private int id;
    private String nome;
    private double preco;

    public ProdutoVO() {
    }

    public ProdutoVO(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id; 
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Produto: " + nome + " | R$ " + preco;
    }
}