package io.ricardo_paulo.HashTable.DictionaryLists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Data {

    public DictionaryLists getDictionary (int percent) {

        String filePath = "/englishDictionary.json";
        DictionaryLists result = new DictionaryLists();

        InputStream inputStream = Data.class.getResourceAsStream(filePath);

        if (inputStream == null) {
            System.out.printf("Erro ao ler o arquivo em %s", filePath);
            return null;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            List<String> lines = bufferedReader.lines().toList();
            long itemsCount = lines.stream()
                    .filter(l -> l.contains("\"word\":"))
                    .count();
            int rangeIndex = Math.toIntExact(itemsCount * percent / 100);
            AtomicInteger counter = new AtomicInteger(0);

            lines.forEach(line -> {

                if (counter.get() <= rangeIndex) {
                    if (line.contains("\"word\":")) {
                        String word = line
                                .replace("\"word\":", "")
                                .replace("\"", "")
                                .trim();

                        if (word.endsWith(",")) {
                            word = word.substring(0, word.length() - 1);
                        }

                        counter.getAndIncrement();
                        result.addWord(word);

                    } else if (line.contains("\"definition\":")) {
                        String definition = line
                                .replace("\"definition\":", "")
                                .replace("\"", "")
                                .trim();

                        result.addDefinition(definition);

                    } else if (line.contains("\"pos\":")) {
                        String pos = line
                                .replace("\"pos\":", "")
                                .replace("\"", "")
                                .trim();

                        if (pos.endsWith(",")) {
                            pos = pos.substring(0, pos.length() - 1);
                        }

                        result.addPos(pos);

                    }
                }
            });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }

}
