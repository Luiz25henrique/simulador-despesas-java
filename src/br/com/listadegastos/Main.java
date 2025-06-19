package br.com.listadegastos;
import br.com.listadegastos.compradeitens.BoughtItem;
import br.com.listadegastos.compradeitens.PurchaseManager;
import br.com.listadegastos.limite.Limite;
import br.com.listadegastos.notice.Messages;
import br.com.listadegastos.password.Password;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {


        Scanner leitor = new Scanner(System.in);
        Limite limite = new Limite(2000.0);
        Password passwordManager = new Password();
        List<BoughtItem> listaDeCompras = new ArrayList<>();

        PurchaseManager purchaseManager = new PurchaseManager(leitor, limite, passwordManager, listaDeCompras);

        boolean programaRodando = true;

        while(programaRodando) {

            logger.info(Messages.MAIN_MENU);
            logger.info("Limite disponível: R$" + String.format("%.2f", limite.getLimiteAtual()));
            int menuChoice = leitor.nextInt();
            leitor.nextLine(); // Limpa o buffer do scanner

            switch (menuChoice) {
                case 1: // Adicionar despesas (Realizar Compras)

                    boolean shouldExitFromPurchase = purchaseManager.startPurchaseProcess();
                    if (shouldExitFromPurchase) {
                        programaRodando = false;
                    }

                    logger.info(Messages.INTERFACE + "\n");
                    break;

                case 2: // Ver lista de despesas
                    if (listaDeCompras.isEmpty()) {
                        logger.info(Messages.INTERFACE);
                        logger.info("Nenhuma despesa registrada ainda.");
                        logger.info(Messages.INTERFACE + "\n");
                    } else {
                        Collections.sort(listaDeCompras);
                        logger.info(Messages.INTERFACE);
                        logger.info("--- Suas Despesas Registradas (Ordenadas por Valor) ---");
                        for (BoughtItem item : listaDeCompras) {
                            logger.info(item.toString());
                        }
                        logger.info(Messages.INTERFACE + "\n");
                    }
                    break;

                case 3: // Consultar o limite
                    logger.info(Messages.INTERFACE);
                    logger.info("Seu limite disponível atualmente: R$" + String.format("%.2f", limite.getLimiteAtual()));
                    logger.info(Messages.INTERFACE + "\n");
                    break;

                case 0: // Sair
                    logger.info(Messages.EXIT_PROGRAMA);
                    programaRodando = false; // Define a flag para encerrar o loop principal
                    break;

                default: // Opção inválida
                    logger.warning(Messages.INTERFACE);
                    logger.warning("Opção inválida. Por favor, escolha uma opção válida do menu.");
                    logger.warning(Messages.INTERFACE + "\n");
                    break;
            }
        }
        // 5. Fechar recursos
        leitor.close();


        logger.info("Programa encerrado com sucesso.");
    }
}