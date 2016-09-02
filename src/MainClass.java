import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Created by Gurpreet on 02/09/16.
 */
public class MainClass extends TelegramLongPollingBot{

    public static void main(String[] args) {

        TelegramBotsApi bot = new TelegramBotsApi();
        try{
            bot.registerBot(new MainClass());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                SendMessage toBeSent = new SendMessage();
                toBeSent.setChatId(message.getChatId().toString());
                toBeSent.setText("You said "+message);
                try{
                    sendMessage(toBeSent);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }
}
