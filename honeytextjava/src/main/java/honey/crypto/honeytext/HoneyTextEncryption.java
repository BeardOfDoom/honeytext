package honey.crypto.honeytext;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import honey.crypto.HoneyEncryption;
import model.Word;
import model.WordClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HoneyTextEncryption {

    private final String SEPARATOR = "~%";
    private HoneyEncryption honeyEncryption;

    public HoneyTextEncryption(HoneyEncryption honeyEncryption) {
        this.honeyEncryption = honeyEncryption;
    }

    public String encrypt(String key, String text) {
        StringBuilder resultBuilder = new StringBuilder();

        List<CoreLabel> tokens = tokenizeText(text);

        for(int i = 0; i < tokens.size(); i++) {
            CoreLabel token = tokens.get(i);
            if(WordClass.contains(token.tag())) {
                Word cipherWord = honeyEncryption.encrypt(
                        key,
                        new Word(
                                token.word(),
                                WordClass.valueOf(token.tag())
                        )
                );
                int wordClassLength = cipherWord.getWordClass().toString().length();
                resultBuilder.append(
                        SEPARATOR +
                        wordClassLength +
                        SEPARATOR +
                        cipherWord.getText().length() +
                        SEPARATOR +
                        cipherWord.getWordClass().toString() +
                        cipherWord.getText()
                );
            } else {
                resultBuilder.append(token.word());
            }

            if(i != tokens.size() - 1) {
                int numOfSpaces = tokens.get(i + 1).beginPosition() - token.endPosition();
                for(int j = 0; j < numOfSpaces; j++) {
                    resultBuilder.append(" ");
                }
            }
        }

        return resultBuilder.toString();
    }

    public String decrypt(String key, String text) {
        StringBuilder resultBuilder = new StringBuilder();

        while(text.length() != 0) {
            int separatorIndex = text.indexOf(SEPARATOR);
            if(separatorIndex == -1) {
                resultBuilder.append(text);
                text = "";
            }
            else if(separatorIndex != 0) {
                resultBuilder.append(text.substring(0, separatorIndex));
                text = text.substring(separatorIndex);
            } else {
                text = text.substring(SEPARATOR.length());

                separatorIndex = text.indexOf(SEPARATOR);
                int wordClassLength = Integer.valueOf(text.substring(0, separatorIndex));
                text = text.substring(separatorIndex + SEPARATOR.length());

                separatorIndex = text.indexOf(SEPARATOR);
                int cipherWordLength = Integer.valueOf(text.substring(0, separatorIndex));
                text = text.substring(separatorIndex + SEPARATOR.length());

                WordClass wordClass = WordClass.valueOf(text.substring(0, wordClassLength));
                text = text.substring(wordClassLength);

                String cipherWord = text.substring(0, cipherWordLength);
                text = text.substring(cipherWordLength);

                resultBuilder.append(honeyEncryption.decrypt(key, new Word(cipherWord, wordClass)));
            }
        }

        return resultBuilder.toString();
    }

    private List<CoreLabel> tokenizeText(String text) {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument doc = new CoreDocument(text);

        pipeline.annotate(doc);

        return doc.tokens();
    }
}
