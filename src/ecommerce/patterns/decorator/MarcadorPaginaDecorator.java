package src.ecommerce.patterns.decorator;

import src.ecommerce.model.Produto;
import src.ecommerce.model.ProdutoDigital;

//Padrão: Decorator: implementação de ProdutoDecorator
//este é o "embrulho" real
//adiciona regras específicas de negócio para marcador de pagina
//intercepta e adiciona o custo do marcador sem alterar o objeto livro (Produto)

public class MarcadorPaginaDecorator extends ProdutoDecorator {

    //recebe o livro que o cliente escolheu e passa para o embrulho base
    public MarcadorPaginaDecorator(Produto produtoEmbrulhado) {
        super(produtoEmbrulhado);

        // REGRA DE NEGÓCIO: marcador físico não pode ser adicionado a um e-book.
        // O operador 'instanceof' checa o tipo do produto
        if (produtoEmbrulhado instanceof ProdutoDigital) {
            throw new IllegalArgumentException("Erro: Não é possível adicionar um marcador físico a um produto digital (E-book).");
        }
    }
    //agora o produto é um produto "embrulhado"

    
    //metodo especificos 
    @Override
    public double getPreco() {
        // pega o preço ao livro original (produtoEmbrulhado) 
        // soma +R$ 5,00 do marcador 
        // devolve o resultado atualizado para o carrinho
        return produtoEmbrulhado.getPreco() + 5.00; 
    }
    
    @Override
    public String getNome() {
        // Pega o nome original ("Livro Clean Code") 
        // adiciona etiqueta no final do nome pra informar que tem marcador
        return produtoEmbrulhado.getNome() + " [+ 🔖 Marcador Temático]";
    }

    @Override
    public String getDescricaoAdicional() {
        //descrição exigida do metodo abstract 
        return "Inclui um marcador de página temático de acordo com o livro";
    }

    @Override
    public void exibirDetalhesDeEntrega() {
        // Como o marcador vai na mesma caixa ou e-mail, ele não muda as regras logísticas.
        // Ele apenas "repassa a bola" para o livro original fazer o trabalho.
        produtoEmbrulhado.exibirDetalhesDeEntrega();
    }
}