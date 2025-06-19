package br.com.listadegastos.password;

import br.com.listadegastos.notice.Messages;
import java.util.logging.Logger;
import java.util.Scanner;
public class Password {

    private static final Logger logger = Logger.getLogger(Password.class.getName());

    private final String defaultPassword = "ybxa";

    public boolean verifyPassword(Scanner scanner) {
        int tentativas = 5;
            while (tentativas > 0) {
                logger.info(Messages.ENTER_THE_PASSWORD);
                String passwordUser = scanner.nextLine();

                if (passwordUser.equals(defaultPassword)) {
                    logger.info(Messages.PASSWORD_OK);
                    return true;
                } else {
                    tentativas--;
                    if (tentativas > 0) {
                        logger.warning("Senha incorreta, você tem " + tentativas + " tentativas");
                    } else {
                        logger.warning(Messages.LIMIT_PASSWORD);
                    }
                }
            }
        return false;
    }

}
