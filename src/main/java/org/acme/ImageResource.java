package org.acme;

import io.quarkiverse.langchain4j.ImageUrl;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/image")
public class ImageResource {

    private final CodeRecognizer codeRecognizer;

    public ImageResource(CodeRecognizer codeRecognizer) {
        this.codeRecognizer = codeRecognizer;
    }

    @GET
    @Path("describe")
    public String describe() {
        return codeRecognizer.getCode("https://github.com/quarkusio/quarkus/assets/405347/8d57711b-4be5-437c-8fd4-90e542e65c3a");
    }

    @RegisterAiService(chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
    @ApplicationScoped
    private interface CodeRecognizer {

        @dev.langchain4j.service.UserMessage("This is image was reported on a GitHub issue on the Quarkus repository. If this is a snippet of Java code, please respond with only the Java code. If it is not, describe what the image is showing")
        String getCode(@ImageUrl String url);
    }
}
