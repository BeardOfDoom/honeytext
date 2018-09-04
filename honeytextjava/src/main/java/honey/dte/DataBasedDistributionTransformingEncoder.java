package honey.dte;

import java.math.BigInteger;
import java.util.*;

import model.Word;
import model.WordClass;

//TODO it only allows one seed for a word. (what is OK for me.)
public class DataBasedDistributionTransformingEncoder implements DistributionTransformingEncoder {

    private Map<WordClass, List<String>> data;

    public DataBasedDistributionTransformingEncoder(List<Word> words) {
        data = new HashMap<>();
        for(Word word : words) {
            if(data.containsKey(word.getWordClass())) {
                data.get(word.getWordClass()).add(word.getText());
            } else {
                data.put(
                        word.getWordClass(),
                        new ArrayList<>(
                                Arrays.asList(word.getText())
                        )
                );
            }
        }
    }

    @Override
    public String DTEncode(Word word) {
        return String.valueOf(data.get(word.getWordClass()).indexOf(word.getText()));
    }

    @Override
    public String DTDecode(Word encodedWord) {
        List<String> wordsOfClass = data.get(encodedWord.getWordClass());
        BigInteger index = new BigInteger(encodedWord.getText(), 16);
        int realIndex = index.mod(BigInteger.valueOf(wordsOfClass.size())).intValue();
        return wordsOfClass.get(realIndex);
    }
}
