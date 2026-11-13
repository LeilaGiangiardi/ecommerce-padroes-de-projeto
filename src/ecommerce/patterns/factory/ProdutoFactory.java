package src.ecommerce.patterns.factory;

import src.ecommerce.model.Produto;
import src.ecommerce.model.ProdutoDigital;
import src.ecommerce.model.ProdutoFisico;
import src.ecommerce.model.vo.ProdutoVO;


 //padrão: Abstract Factory
 //classe especifica para "fabricar" objetos por tipo
 //controller não precisa saber regras especificas de fisico ou digital
 //fica aqui essa decisão

public class ProdutoFactory {

    // metodo que fabrica o produto pelo tipo 
    // recebe o VO (que veio do banco) e dados extras: peso ou link
    public static Produto criarProduto(ProdutoVO vo, String tipo, String dadoExtra) {
        
        if (tipo.equalsIgnoreCase("FISICO")) {
            // Se for fisico, o dado extra é convertido para peso (double)
            double peso = Double.parseDouble(dadoExtra);
            return new ProdutoFisico(vo.getId(), vo.getNome(), vo.getPreco(), peso);
            
        } else if (tipo.equalsIgnoreCase("DIGITAL")) {
            // Se for digital, o dado extra é o link
            return new ProdutoDigital(vo.getId(), vo.getNome(), vo.getPreco(), dadoExtra);
        }
        
        return null; // Caso enviem um tipo invalido.
    }
}