package com.meuprojeto.listadecompra.cartaodecredito;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

public class Purchase extends Cartao {
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Purchase.class.getName());
    List<Purchase> listaDeCompras = new ArrayList<>();
    Password password = new Password();

    public void buy() {

        //RESPONSAVEL PELAS COMPRAS.
        logger.info(Notice.INFORME_A_COMPRA);
        setNomeCompra(scanner.nextLine());
        logger.info(Notice.INFORME_O_VALOR);
        setValorCompra(scanner.nextDouble());
        scanner.nextLine(); //LIMPANDO O BUF


    }





}
