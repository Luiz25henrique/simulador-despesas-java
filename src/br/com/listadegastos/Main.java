package br.com.listadegastos;
import br.com.listadegastos.compradeitens.BoughtItem; // Importa a classe BoughtItem
import br.com.listadegastos.compradeitens.PurchaseManager; // Importa a classe PurchaseManager
import br.com.listadegastos.limite.Limite; // Importa a classe Limite
import br.com.listadegastos.notice.Messages; // Importa a classe Messages
import br.com.listadegastos.password.Password; // Importa a classe Password

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    // Logger para a classe Main
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // 1. Instanciação das dependências e objetos centrais
        Scanner leitor = new Scanner(System.in); // O Scanner é único e centralizado aqui
        Limite limite = new Limite(2000.0); // Limite inicial do cartão (ex: R$2000,00)
        Password passwordManager = new Password(); // O gerenciador de senhas
        List<BoughtItem> listaDeCompras = new ArrayList<>(); // A lista principal de compras

        // 2. Instanciação do PurchaseManager, INJETANDO todas as suas dependências
        PurchaseManager purchaseManager = new PurchaseManager(leitor, limite, passwordManager, listaDeCompras);

        boolean programaRodando = true; // Flag para controlar o loop principal do programa

        // 3. Loop Principal do Menu
        while(programaRodando) {
            // Exibe o menu principal e o limite disponível
            logger.info(Messages.MAIN_MENU);
            logger.info("Limite disponível: R$" + String.format("%.2f", limite.getLimiteAtual())); // Exibe o limite atual

            int menuChoice = leitor.nextInt(); // Lê a escolha do usuário no menu principal
            leitor.nextLine(); // Limpa o buffer do scanner

            // 4. Switch para lidar com as opções do menu
            switch (menuChoice) {
                case 1: // Adicionar despesas (Realizar Compras)
                    // Chama o método do PurchaseManager para iniciar o processo de compra.
                    // O retorno de 'startPurchaseProcess' indica se o Main deve encerrar.
                    boolean shouldExitFromPurchase = purchaseManager.startPurchaseProcess();
                    if (shouldExitFromPurchase) {
                        programaRodando = false; // Se o usuário escolheu sair lá dentro, o programa encerra.
                    }

                    logger.info(Messages.INTERFACE + "\n"); // Adiciona um espaçamento após o processo de compra
                    break;

                case 2: // Ver lista de despesas
                    if (listaDeCompras.isEmpty()) {
                        logger.info(Messages.INTERFACE);
                        logger.info("Nenhuma despesa registrada ainda.");
                        logger.info(Messages.INTERFACE + "\n");
                    } else {
                        Collections.sort(listaDeCompras); // Garante que a lista está ordenada pelo compareTo()
                        logger.info(Messages.INTERFACE);
                        logger.info("--- Suas Despesas Registradas (Ordenadas por Valor) ---");
                        for (BoughtItem item : listaDeCompras) {
                            logger.info(item.toString()); // Usa o toString() formatado do BoughtItem
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
                    logger.info(Messages.EXIT_PROGRAMA); // Assumindo SAINDO_PROGRAMA em Messages
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
        leitor.close(); // Fecha o Scanner quando o programa termina

        // Mensagem de encerramento final (opcional, pode já estar no SAINDO_PROGRAMA)
        logger.info("Programa encerrado com sucesso.");
    }
}