package src.ecommerce.controller.commands;

import java.util.Scanner;
import src.ecommerce.controller.EcommerceController;
import src.ecommerce.model.Produto;
import src.ecommerce.model.vo.ProdutoVO;
import src.ecommerce.patterns.factory.ProdutoFactory;

// Comando 2: Busca o livro no DAO (ProdutoVO), usa a ProdutoFactory (Abstract Factory)
//para criar o livro Físico ou Digital, e envia ao Controller com a opção do Decorator.
 
public class AdicionarAoCarrinhoCommand implements Command {

    private EcommerceController controller;
    private Scanner scanner;

    public AdicionarAoCarrinhoCommand(EcommerceController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    @Override
    public void executar() {
        System.out.print("\nDigite o ID do livro que deseja adicionar: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ ID inválido! Digite apenas números.");
            return;
        }

        // 1. Busca os dados crus (VO) no banco de memória através do DAO
        ProdutoVO vo = controller.getDao().buscarPorId(id);
        if (vo == null) {
            System.out.println("❌ Nenhum livro encontrado com o ID " + id);
            return;
        }

        // 2. Pergunta o formato para acionar a ProdutoFactory (Padrão Abstract Factory)
        System.out.println("Escolha o formato para \"" + vo.getNome() + "\":");
        System.out.println("1 - Livro Físico");
        System.out.println("2 - E-book (Digital)");
        System.out.print("Opção: ");
        String opcaoFormato = scanner.nextLine();

        Produto produto;
        if (opcaoFormato.equals("2")) {
            // Fabrica um ProdutoDigital passando um link de download automático
            produto = ProdutoFactory.criarProduto(vo, "DIGITAL", "https://loja.com/download/ebook-" + vo.getId());
        } else {
            // Fabrica um ProdutoFisico passando um peso padrão de 0.5 kg
            produto = ProdutoFactory.criarProduto(vo, "FISICO", "0.5");
        }

        // 3. Pergunta pelo opcional do Padrão Decorator
        System.out.print("Deseja adicionar Marcador de Página (+ R$ 5,00)? (s/n): ");
        boolean querMarcador = scanner.nextLine().equalsIgnoreCase("s");

        // 4. O Controller tenta aplicar o Marcador (com try-catch caso seja E-book) e põe no carrinho
        controller.adicionarProduto(produto, querMarcador);
    }
}