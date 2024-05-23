package org.acme;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/image")
public class ImageResource {

    private final ChatLanguageModel chatLanguageModel;

    public ImageResource(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @GET
    @Path("describe")
    public String describe() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from(
                        "This is image was reported on a GitHub issue on the Quarkus repository. If this is a snippet of Java code, please respond with only the Java code. If it is not, describe what the image is showing"),
                ImageContent.from("https://github.com/quarkusio/quarkus/assets/405347/8d57711b-4be5-437c-8fd4-90e542e65c3a"));
        Response<AiMessage> response = chatLanguageModel.generate(userMessage);
        return response.content().text();
    }
}
