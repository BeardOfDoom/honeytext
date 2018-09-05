package honey.dte;

import model.Word;

public interface IDistributionTransformingEncoder {

    public String DTEncode(Word word);

    public String DTDecode(Word encodedWord);

}
