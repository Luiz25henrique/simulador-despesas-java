package com.meuprojeto.listadecompra.cartaodecredito;
import java.util.logging.Logger;

public class Limite {
    private static final Logger logger = Logger.getLogger(Limite.class.getName());

    private double limiteCartao;

    public double getLimiteCartao() {
        return 2000;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public boolean debitar(double valor) {
        if (valor <= this.limiteCartao) {
            this.limiteCartao -= valor;
            return true;
        } else{
            return false;
        }
   }

}
