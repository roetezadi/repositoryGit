import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Romina Etezadi on 3/25/2017.
 */
public class K_BOT extends org.telegram.telegrambots.bots.TelegramLongPollingBot
{
    String filename = "F:\\que.txt";
    int[] regPlace = new int[1000000];
    boolean[] mark = new boolean[1000000];
    boolean[] markButton = new boolean[1000000];
    ArrayList<String> questions = new ArrayList<>();
    Map<Long, Integer> map = new HashMap<>();
    int cnt = 0;

    public K_BOT() throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bf = new BufferedReader(fileReader);
        String line;
        while((line = bf.readLine()) != null){
            String q = new String(line);
            questions.add(q);
        }

        for(int i=0;i<1000000;i++){
            regPlace[i] = 0;
            mark[i] = false;
            markButton[i] = true;
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            if(map.get(update.getMessage().getChatId()) == null){
                map.put(update.getMessage().getChatId(), cnt);
                cnt++;
            }

            if(update.getMessage().getText().equals("/register")){
                mark[map.get(update.getMessage().getChatId())] = true;
            }

            if(mark[map.get(update.getMessage().getChatId())] && markButton[map.get(update.getMessage().getChatId())]){
                if(regPlace[map.get(update.getMessage().getChatId())] < 8) {
                    SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
                            .setText(questions.get(regPlace[map.get(update.getMessage().getChatId())]));


                    if(regPlace[map.get(update.getMessage().getChatId())] == 2) {
                       // markButton[map.get(update.getMessage().getChatId())] = false;
                        /**
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        rowInline.add(new InlineKeyboardButton().setText("زن").setCallbackData("زن"));
                        rowInline.add(new InlineKeyboardButton().setText("مرد").setCallbackData("مرد"));
                        // Set the keyboard to the markup
                        rowsInline.add(rowInline);
                        // Add it to the message
                        markupInline.setKeyboard(rowsInline);
                        /**/
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> keyboard = new ArrayList<>();
                        KeyboardRow keyboardFirstRow = new KeyboardRow();
                        keyboardFirstRow.add("زن");
                        keyboardFirstRow.add("مرد");
                        keyboard.add(keyboardFirstRow);
                        replyKeyboardMarkup.setKeyboard(keyboard);
                        message.setReplyMarkup(replyKeyboardMarkup);


                        //message.setReplyMarkup(markupInline);
                    }
                    else if(regPlace[map.get(update.getMessage().getChatId())] == 4){
                        //markButton[map.get(update.getMessage().getChatId())] = false;
                        /**
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        rowInline.add(new InlineKeyboardButton().setText("متآهل").setCallbackData("متآهل"));
                        rowInline.add(new InlineKeyboardButton().setText("مجرد").setCallbackData("مجرد"));
                        // Set the keyboard to the markup
                        rowsInline.add(rowInline);
                        // Add it to the message
                        markupInline.setKeyboard(rowsInline);
                        /**/
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> keyboard = new ArrayList<>();
                        KeyboardRow keyboardFirstRow = new KeyboardRow();
                        keyboardFirstRow.add("متآهل");
                        keyboardFirstRow.add("مجرد");
                        keyboard.add(keyboardFirstRow);
                        replyKeyboardMarkup.setKeyboard(keyboard);
                        message.setReplyMarkup(replyKeyboardMarkup);

                        //message.setReplyMarkup(markupInline);

                    }
                    else if(regPlace[map.get(update.getMessage().getChatId())] == 6){
                        //markButton[map.get(update.getMessage().getChatId())] = false;
                        /**
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        rowInline.add(new InlineKeyboardButton().setText("علوم ریاضی").setCallbackData("0"));
                        rowInline.add(new InlineKeyboardButton().setText("علوم تجربی").setCallbackData("1"));
                        rowInline.add(new InlineKeyboardButton().setText("علوم انسانی").setCallbackData("2"));
                        rowInline.add(new InlineKeyboardButton().setText("هنر و زبان").setCallbackData("3"));
                        // Set the keyboard to the markup
                        rowsInline.add(rowInline);
                        // Add it to the message
                        markupInline.setKeyboard(rowsInline);
                        /**/
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> keyboard = new ArrayList<>();
                        KeyboardRow keyboardFirstRow = new KeyboardRow();
                        keyboardFirstRow.add("علوم ریاضی");
                        keyboardFirstRow.add("علوم تجربی");
                        KeyboardRow keyboardSecondRow = new KeyboardRow();
                        keyboardSecondRow.add("علوم انسانی");
                        keyboardSecondRow.add("هنر و زبان");
                        keyboard.add(keyboardFirstRow);
                        keyboard.add(keyboardSecondRow);
                        replyKeyboardMarkup.setKeyboard(keyboard);

                        message.setReplyMarkup(replyKeyboardMarkup);
                       // message.setReplyMarkup(markupInline);

                    }
                    else if(regPlace[map.get(update.getMessage().getChatId())] == 7){
                        //markButton[map.get(update.getMessage().getChatId())] = false;
                        /**
                        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        rowInline.add(new InlineKeyboardButton().setText("سال دوم").setCallbackData("0"));
                        rowInline.add(new InlineKeyboardButton().setText("سال سوم").setCallbackData("1"));
                        rowInline.add(new InlineKeyboardButton().setText("پیش دانشگاهی").setCallbackData("2"));
                        // Set the keyboard to the markup
                        rowsInline.add(rowInline);
                        // Add it to the message
                        markupInline.setKeyboard(rowsInline);
                         /**/


                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        List<KeyboardRow> keyboard = new ArrayList<>();
                        KeyboardRow keyboardFirstRow = new KeyboardRow();
                        keyboardFirstRow.add("سال دوم");
                        keyboardFirstRow.add("سال سوم");
                        keyboardFirstRow.add("پیش دانشگاهی");
                        keyboard.add(keyboardFirstRow);
                        replyKeyboardMarkup.setKeyboard(keyboard);
                        message.setReplyMarkup(replyKeyboardMarkup);

                        //message.setReplyMarkup(markupInline);

                    }


                    try {
                        sendMessage(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    regPlace[map.get(update.getMessage().getChatId())]++;
                }
            }

        }
        else if (update.hasCallbackQuery()) {
            /**

            markButton[map.get(update.getCallbackQuery().getMessage().getChatId())] = true;

            SendMessage message = new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId())
                    .setText(questions.get(regPlace[map.get(update.getCallbackQuery().getMessage().getChatId())]));
            if(regPlace[map.get(update.getCallbackQuery().getMessage().getChatId())] == 7){
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("سال دوم").setCallbackData("0"));
                rowInline.add(new InlineKeyboardButton().setText("سال سوم").setCallbackData("1"));
                rowInline.add(new InlineKeyboardButton().setText("پیش دانشگاهی").setCallbackData("2"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);

            }
            try {
                sendMessage(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            regPlace[map.get(update.getCallbackQuery().getMessage().getChatId())]++;
             /**/
        }
    }

    @Override
    public String getBotUsername() {
        return "testNLPbot";
    }

    @Override
    public String getBotToken() {
        return "328731622:AAE_j6sXfP_KQPeHbzs5HdQUqadSNVOrjbw";
    }

    @Override
    public void onClosing() {

    }
}
