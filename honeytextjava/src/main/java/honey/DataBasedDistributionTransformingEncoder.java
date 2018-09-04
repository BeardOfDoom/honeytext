package honey;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import model.Word;
import model.WordClass;

public class DataBasedDistributionTransformingEncoder implements DistributionTransformingEncoder {

    private Map<WordClass, List<String>> data;

    public DataBasedDistributionTransformingEncoder(Map<WordClass, List<String>> data) {
        this.data = data;
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
