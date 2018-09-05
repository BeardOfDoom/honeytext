package honey.dte;

import model.Word;

import java.math.BigInteger;
import java.nio.charset.Charset;

public class BasicDistributionTransformingEncoder implements IDistributionTransformingEncoder {

    @Override
    public String DTEncode(Word word) {
        StringBuilder resultBuilder = new StringBuilder();

        byte[] textBytes = word.getText().getBytes(Charset.forName("UTF-8"));
        for (int i = 0; i < textBytes.length; i++) {
            resultBuilder.append(
                    String.format(
                            "%8s",
                            Integer.toBinaryString(textBytes[i] & 0xFF)
                    ).replace(' ', '0')
            );
        }

        return resultBuilder.toString();
    }

    @Override
    public String DTDecode(Word encodedWord) {
        return new String(new BigInteger(encodedWord.getText(), 2).toByteArray(), Charset.forName("UTF-8"));
    }
}
