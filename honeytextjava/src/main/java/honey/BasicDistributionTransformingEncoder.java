package honey;

import java.math.BigInteger;
import java.nio.charset.Charset;

public class BasicDistributionTransformingEncoder implements DistributionTransformingEncoder{

    //kell a salt?
    //bejön egy szó
    //szó -> binary
    //visszaadja az így kapott stringet
    public String DTEncode(String text) {
        StringBuilder resultBuilder = new StringBuilder();

        byte[] textBytes = text.getBytes(Charset.forName("UTF-8"));
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

    //visszafejtés
    //felismerni hogy ez egy valós szó-e (coreNLP)
    //ha valós szó, akkor minden ok
    //ha nem valós szó, akkor kiválasztani egyet az adatbázisból valamilyen számítás alapján
    public String DTDecode(String encodedText) {
        return new String(new BigInteger(encodedText, 2).toByteArray(), Charset.forName("UTF-8"));
    }
}
