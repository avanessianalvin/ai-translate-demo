import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class RunCommand {

    public RunCommand(String model, String command) {
        this.model = model;
        this.command = command;
    }

    private String model;
    private String command;

    public void run(){
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input(command)
                .model(model)
                .build();

        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv()
                .apiKey(Credentials.grokApiKey0)
                .baseUrl("https://api.groq.com/openai/v1")
                .build();

        try {
            System.out.println(model + " ---------------------");
            long start = System.currentTimeMillis();
            Response response = client.responses().create(params);

            response.output().stream()
                    .flatMap(item -> item.message().stream())
                    .flatMap(message -> message.content().stream())
                    .flatMap(content -> content.outputText().stream())
                    .forEach(outputText -> System.out.println(outputText.text()));
            long end = System.currentTimeMillis();
            System.out.println(end-start + "mills");
            System.out.println();
        }catch (Exception e){
            System.err.println("ERROR! ");
            System.err.println(e.getMessage());
        }
    }
}
