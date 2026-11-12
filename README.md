# 🛒 E-Commerce CLI — Arquitetura de Software

Projeto acadêmico desenvolvido em **Java puro** (sem frameworks externos e com interface via linha de comando) com o objetivo de demonstrar a aplicação prática da arquitetura **MVC** combinada com **15 Padrões de Projeto (Design Patterns)** em um cenário de loja virtual.

## 🏗️ Padrões Implementados

| # | Padrão | Categoria | Aplicação no Projeto |
| :--- | :--- | :--- | :--- |
| **01** | **MVC** | Arquitetura | Separação entre regras de negócio (`Model`), entrada/saída no terminal (`View`) e fluxo (`Controller`). |
| **02** | **DAO** | Persistência | Abstração das operações de busca e salvamento de produtos e pedidos em memória. |
| **03** | **VO** | Transferência | Objetos enxutos (`ProdutoVO`, `CarrinhoVO`) para trafegar dados entre camadas sem expor regras. |
| **04** | **Singleton** | Criacional | Garante uma única instância do `BancoDeMemoria` durante toda a execução do programa. |
| **05** | **Abstract Factory** | Criacional | Criação de diferentes famílias de produtos: **Físicos** (com frete) e **Digitais** (com link de download). |
| **06** | **Facade** | Estrutural | A classe `CheckoutFacade` unifica validação de estoque, pagamento e recibo em uma única chamada. |
| **07** | **Adapter** | Estrutural | Adapta a interface de um serviço externo de pagamento (ex: PayPal) ao padrão esperado pela loja. |
| **08** | **Decorator** | Estrutural | Adiciona opções extras dinamicamente ao item do carrinho (ex: Embalagem de Presente, Garantia). |
| **09** | **Command** | Comportamental | Transforma cada opção do menu do terminal em uma classe de comando isolada. |
| **10** | **Observer** | Comportamental | Dispara notificações automáticas (Nota Fiscal e E-mail) assim que o pagamento é confirmado. |
| **11** | **Chain of Responsibility** | Comportamental | Cadeia de regras para calcular descontos progressivos (Cupom, Cliente VIP, Valor Mínimo). |
| **12** | **Template Method** | Comportamental | Define o passo a passo fixo de processamento do pedido, variando apenas a etapa de entrega. |
| **13** | **Memento** | Comportamental | Permite salvar o estado do carrinho e desfazer alterações (`Ctrl + Z`). |
| **14** | **Iterator** | Comportamental | Percorre os itens do catálogo e do carrinho de forma padronizada sem expor a estrutura interna. |
| **15** | **Lazy Loading** | Performance | Carrega detalhes pesados (descrição completa e avaliações) apenas quando o usuário solicita. |

## 📂 Estrutura de Pacotes

```text
src/
└── ecommerce/
    ├── Main.java                 # Ponto de entrada da aplicação
    ├── model/                    # Entidades, regras de negócio, VO e DAO
    ├── view/                     # Interface de console (Scanner e System.out)
    ├── controller/               # Controladores e Commands do menu
    └── patterns/                 # Implementações isoladas dos padrões GOF