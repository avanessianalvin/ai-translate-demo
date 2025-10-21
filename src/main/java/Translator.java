import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.*;

import java.util.List;

public class Translator {

    // https://console.groq.com/docs/quickstart


    public static void main(String[] args) throws Exception {

        List<String> modelList = List.of(
                "groq/compound",
                "groq/compound-mini",
                "llama-3.3-70b-versatile",
                "meta-llama/llama-4-maverick-17b-128e-instruct",
                "meta-llama/llama-4-scout-17b-16e-instruct",
                "moonshotai/kimi-k2-instruct",
                "moonshotai/kimi-k2-instruct-0905",
                "openai/gpt-oss-120b",
                "openai/gpt-oss-20b",
                "qwen/qwen3-32b"
                );

        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv()
                .apiKey(Credentials.grokApiKey0)
                .baseUrl("https://api.groq.com/openai/v1")
                .build();




        String input = """
                {
                    "messages":[
                        {
                            "role":"system",
                            "content":"Translate the following sentence into Farsi. Provide only the translation, no commentary."
                        },
                        {
                            "role":"user",
                            "content":"The wind carried forgotten songs through the valley, where wildflowers swayed and memories of lost summers lingered softly in golden light."
                        }
                    ]
                }
                """;


        //input = "Translate the following sentence into Farsi. Provide only the translation, no commentary. ";
        //input += "The wind carried forgotten songs through the valley, where wildflowers swayed and memories of lost summers lingered softly in golden light.";

        for (String m : modelList) {

                RunCommand runCommand1 = new RunCommand(m, input);
                runCommand1.run();

        }

    }
}
