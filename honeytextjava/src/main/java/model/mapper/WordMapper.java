package model.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Word;

import java.io.File;
import java.io.IOException;
import java.util.List;

//TODO Add new words to existing file.
public class WordMapper {

    public static Word mapWordFromJson(String wordJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Word word = objectMapper.readValue(wordJson, Word.class);

        return word;
    }

    public static Word mapWordFromJson(File wordPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Word word = objectMapper.readValue(wordPath, Word.class);

        return word;
    }

    public static List<Word> mapWordsFromJson(String wordJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Word> words = objectMapper.readValue(wordJson, new TypeReference<List<Word>>(){});

        return words;
    }

    public static List<Word> mapWordsFromJson(File wordPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Word> words = objectMapper.readValue(wordPath, new TypeReference<List<Word>>(){});

        return words;
    }

}
