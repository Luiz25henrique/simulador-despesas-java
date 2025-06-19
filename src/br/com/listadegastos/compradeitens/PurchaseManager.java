package br.com.listadegastos.compradeitens;
import br.com.listadegastos.limite.Limite;
import br.com.listadegastos.notice.Messages;
import br.com.listadegastos.password.Password;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class PurchaseManager {

    private static final Logger logger = Logger.getLogger(PurchaseManager.class.getName());

        private Scanner scanner;
        private Limite limite;
        private Password passwordManager;
        private List<BoughtItem> listOfPurchases;

    //CONSTRUTOR PARA INJETAR AS DEPENDENCIAS
    public PurchaseManager(Scanner scanner, Limite limite, Password passwordManager, List<BoughtItem> listOfPurchases) {
        this.scanner = scanner;
        this.limite = limite;
        this.passwordManager = passwordManager;
        this.listOfPurchases = listOfPurchases;
    }

    public boolean startPurchaseProcess() {
        boolean continuingPurchaseProcess = true;
        boolean exitProgram = false;

        while (continuingPurchaseProcess) {

            //INFORMANDO OS ITENS E VALORES
            logger.info(Messages.INFORM_THE_PRODUCT);
            String itemName = scanner.nextLine();
            logger.info(Messages.INFORM_THE_VALUE);
            double itemValue = scanner.nextDouble();
            scanner.nextLine();//LIMPAR O BUFFER

            BoughtItem newItem = new BoughtItem(itemName, itemValue);

            //VALIDAÇÃO DE LIMITE
            if (limite.getLimiteAtual() < newItem.getPurchaseValue()) {
            logger.warning(Messages.INTERFACE + "\n" + Messages.NO_LIMIT + "\nCompra não autorizada por falta de limite");

            logger.info(Messages.OUTRO_MENU);
            int nextAction = scanner.nextInt();
            scanner.nextLine();//LIMPAR O BUFFER
                if (nextAction == 3 ) {
                    exitProgram = true;
                    continuingPurchaseProcess = false;
                } else {
                continuingPurchaseProcess = false;

            }
            continue;
        }
            //LÓGICA DE SENHA

            boolean senhaCorreta = passwordManager.verifyPassword(scanner);

            if (senhaCorreta) {

                if (limite.debitar(newItem.getPurchaseValue())) {
                    listOfPurchases.add(newItem);
                    logger.info(Messages.PURCHASE_MADE);
                    logger.info("Seu limite atualizado " + String.format("%.2f", limite.getLimiteAtual()));
                    logger.info(Messages.INTERFACE);

                    logger.info(Messages.OUTRO_MENU);
                    int next = scanner.nextInt();
                    scanner.nextLine(); //LIMPAR O BUFFER

                    if (next == 2) {
                        continuingPurchaseProcess = false;
                    } else if (next == 3) {
                        continuingPurchaseProcess = false;
                        exitProgram = true;
                    }

                } else {
                    logger.warning("Erro interno: limite insuficiente ao debitar após senha correta. Compra cancelada.");
                    logger.info(Messages.INTERFACE + "\n" + Messages.PURCHASE_CANCELED);
                    continuingPurchaseProcess = false;
                }
            } else {
                //SENHA INCORRETA APOS TODAS AS TENTATIVAS
                logger.warning(Messages.INTERFACE + "\n" + Messages.PURCHASE_CANCELED + "\nRetornando ao menu principal.");
                continuingPurchaseProcess = false; // Retorna ao menu principal
            }
        }
        return exitProgram;
    }

}
