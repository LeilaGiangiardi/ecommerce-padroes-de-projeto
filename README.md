# 🛒 E-Commerce CLI — Arquitetura de Software

Projeto acadêmico desenvolvido em **Java puro** (sem frameworks externos e com interface via linha de comando) com o objetivo de demonstrar a aplicação prática da arquitetura **MVC** combinada com **16 Padrões de Projeto (Design Patterns)** em um cenário de loja virtual de livros.

## 🏗️ Padrões Implementados

| # | Padrão | Categoria | Aplicação no Projeto |
| :--- | :--- | :--- | :--- |
| **01** | **MVC** | Arquitetura | Estruturação de pastas separando regras (`Model`), terminal (`View`) e orquestração (`Controller`). |
| **02** | **DAO** | Arquitetural | Isola a lógica de buscar/salvar itens no nosso banco de memória falso (`ProdutoDAO`). |
| **03** | **VO (DTO)** | Arquitetural | Envelopes burros (`ProdutoVO`, `CarrinhoVO`) que trafegam dados do banco para a tela sem expor regras. |
| **04** | **Singleton** | Criacional | Trava a classe `BancoDeMemoria` para garantir que o sistema inteiro use o mesmo estoque. |
| **05** | **Abstract Factory** | Criacional | A `ProdutoFactory` constrói o tipo correto de objeto (`ProdutoFisico` ou `ProdutoDigital`) com base nos dados. |
| **06** | **Lazy Loading** | Performance | Atrasa o carregamento da descrição completa e das "reviews" do produto até que o cliente peça para ler. |
| **07** | **Iterator** | Comportamental | Motor que percorre e imprime o catálogo e o carrinho na tela (`System.out`) sem expor a lista real (`ArrayList`). |
| **08** | **Command** | Comportamental | Transforma cada opção do terminal (1 - Listar, 2 - Comprar, 3 - Sair) em uma classe de ação independente. |
| **09** | **Decorator** | Estrutural | Adiciona comportamentos extras ao produto dinamicamente (ex: `EmbalagemPresenteDecorator` que soma R$ 15). |
| **10** | **Memento** | Comportamental | Tira um *snapshot* do estado do carrinho quando o usuário sai (Carrinho Abandonado) e restaura na volta. |
| **11** | **Facade** | Estrutural | A `CheckoutFacade` centraliza o processo de "Finalizar Compra" (estoque + pagar + recibo) em uma única chamada. |
| **12** | **Chain of Responsibility** | Comportamental | Cascata de verificação de descontos: Tenta aplicar Cupom -> Se não, tenta VIP -> Se não, tenta Frete Grátis. |
| **13** | **Strategy** | Comportamental | Permite plugar diferentes algoritmos de pagamento na hora de fechar o pedido (`PagamentoPix`, `PagamentoCartao`). |
| **14** | **Adapter** | Estrutural | A estratégia `PagamentoCartao` usa o Adapter para traduzir nossa interface para a interface do sistema externo "PayPal". |
| **15** | **Template Method** | Comportamental | Define a ordem fixa de processar o pedido: Baixar Estoque -> Cobrar -> Entregar (Variando apenas a forma de entrega). |
| **16** | **Observer** | Comportamental | O módulo de pagamento notifica (`notify`) os ouvintes (`NotaFiscal` e `DisparadorDeEmail`) assim que a compra é aprovada. |

## 📂 Estrutura de Pacotes

```text
src/
└── ecommerce/
    ├── Main.java                 # Ponto de entrada da aplicação
    ├── model/                    # Entidades, regras de negócio, VO e DAO
    ├── view/                     # Interface de console (Scanner e System.out)
    ├── controller/               # Controladores e Commands do menu
    └── patterns/                 # Implementações isoladas dos padrões GOF