package src.ecommerce.patterns.observer;


//Padrão: Observer (Ouvinte)
// O contrato que todo serviço precisa assinar para "ouvir" quando uma venda acontece.

public interface CompraObserver {

    // Método chamado automaticamente assim que uma compra é concluída
    void atualizar(String resumoDaCompra);
}