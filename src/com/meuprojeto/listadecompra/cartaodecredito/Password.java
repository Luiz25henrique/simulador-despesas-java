package com.meuprojeto.listadecompra.cartaodecredito;

import java.util.logging.Logger;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Password extends Purchase {

    private static final Logger logger = Logger.getLogger(Password.class.getName());
    Scanner leitor = new Scanner(System.in);
    Limite limite = new Limite();
    Purchase purchase = new Purchase();
    List<Purchase> listaDeCompras = new ArrayList<>();

    public String getSenha() {
        return "ybxa";
    }

    public void verifyPassword() {
        int tentativasRestantes = 5;

        while (tentativasRestantes > 0) {
            logger.info(Notice.INFORME_A_SENHA);
            String senhaDigitada = leitor.nextLine();

            if (senhaDigitada.equals(getSenha())) {

                //DESCONTANDO DO LIMITE DO CARTÃO
                limite.setLimiteCartao(limite.getLimiteCartao() - purchase.getValorCompra());
                listaDeCompras.add(purchase); //ARMAZENANDO ITENS.

                logger.info(Notice.COMPRA_REALIZADA);
                break;

            } else {
                tentativasRestantes--;
                if (tentativasRestantes > 0){
                    logger.info(Notice.TENTATIVAS_DE_SENHA + " você tem " + tentativasRestantes + " tentativas");
                } else {
                    logger.warning(Notice.TENTATIVAS_DE_SENHA);
                    logger.warning(Notice.LIMITE_TENTATIVAS);
                }
            }
        }


    }


}
