import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;

/**
 * Created by Romina Etezadi on 3/25/2017.
 */
public class Main
{
    public static void main(String args[]) throws  IOException {
        /**/
        ApiContextInitializer.init();

        TelegramBotsApi tlgrBotApi = new TelegramBotsApi();

        try
        {
            K_BOT bt = new K_BOT();
            tlgrBotApi.registerBot(bt);


        } catch (TelegramApiRequestException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         /**/
    }
}
