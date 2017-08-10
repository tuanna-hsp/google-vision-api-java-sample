package com.kaopiz;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.LanguageServiceClient;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class NlpSample {

    public static void main(String... args) throws Exception {
        // Instantiates a client
        LanguageServiceClient language = LanguageServiceClient.create();

        // The text to analyze
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(new File("vision_result.txt"));
        while (scanner.hasNext()) {
            builder.append(scanner.nextLine()).append('\n');
        }
        Document doc = Document.newBuilder()
                .setContent(builder.toString()).setType(Type.PLAIN_TEXT).build();

        // Detects the sentiment of the text
        List<Entity> entities = language.analyzeEntities(doc, EncodingType.UTF16).getEntitiesList();
        PrintStream stream = new PrintStream("nlp_result.txt");
        for (Entity e : entities) {
            stream.println("Name: " + e.getName());
            stream.println("Type: " + e.getType());
            stream.println();
        }
    }
}
