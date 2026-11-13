package src.ecommerce.patterns.decorator;

import src.ecommerce.model.Produto;


//padrão: Decorator (classe base)
//molde para "embrulhar" (wrap) um objeto existente
//adiciona funcionalidades novas ao objeto sem precisar mexer na classe original
//sem precisar criar inumeras classes filhas de Produto
 
public abstract class ProdutoDecorator extends Produto {
    
    
    //guarda o produto original que está dentro do embrulho.
    //É 'protected' para que os decoradores filhos (ex: MarcadorPaginaDecorator) possam acessá-la
    protected Produto produtoEmbrulhado;

    //Cria o embrulho e coloca o produto dentro 
    public ProdutoDecorator(Produto produtoEmbrulhado) {
        // puxa os dados produto original
        super(produtoEmbrulhado.getId(), produtoEmbrulhado.getNome(), produtoEmbrulhado.getPreco());
        
        // referência do produto original na nossa variável
        this.produtoEmbrulhado = produtoEmbrulhado;
    }
    
    // Método abstrato: obrigada as classes filhas a criar um texto explicando o que foi adicionado ao produto
    public abstract String getDescricaoAdicional();
}