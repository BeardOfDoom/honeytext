package honey.dte;

import model.Word;

public interface DistributionTransformingEncoder {

    public String DTEncode(Word word);

    public String DTDecode(Word encodedWord);

}
