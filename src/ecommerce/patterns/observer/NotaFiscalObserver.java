package src.ecommerce.patterns.observer;

//padrão: Observer (Ouvinte 1 - Emissor de Nota Fiscal)
//Fica aguardando o aviso de compra aprovada para emitir a NF-e.
 
public class NotaFiscalObserver implements CompraObserver {

    @Override
    public void atualizar(String resumoDaCompra) {
        System.out.println("🧾 [Observer - Nota Fiscal] Emitindo NF-e oficial -> " + resumoDaCompra);
    }
}