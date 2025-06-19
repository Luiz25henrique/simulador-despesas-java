package com.meuprojeto.listadecompra.cartaodecredito;

public class Cartao implements Comparable<Cartao> {
    private String nomeCompra;
    private double valorCompra;

    //Metodos gets
    public double getValorCompra() {
        return valorCompra;
    }

    //Metodos set
    public void setNomeCompra(String nomeCompra) {
        this.nomeCompra = nomeCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public int compareTo(Cartao outroCartao) {
        return Double.compare(this.valorCompra, outroCartao.valorCompra);
    }

    @Override
    public String toString() {
        return "Produto: " + nomeCompra + "\n" + "Valor: " + valorCompra + "\n" + "***********************";
    }

}
