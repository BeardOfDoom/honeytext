package honey;

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
        List<String> wordsOfClass = data.get(word.getWordClass());
        return null;
    }

    @Override
    public String DTDecode(Word encodedWord) {
        return null;
    }
}
