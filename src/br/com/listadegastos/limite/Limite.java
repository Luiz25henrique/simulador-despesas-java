package br.com.listadegastos.limite;

public class Limite {
    private double limiteAtual;

    public Limite(double limiteInicial) {
        this.limiteAtual = limiteInicial;
    }

    public double getLimiteAtual() {
        return limiteAtual;
    }

    public boolean debitar(double valor) {
        if (valor <= limiteAtual) {
            this.limiteAtual -= valor;
            return true;
        } else {
            return false;
        }
    }
}
