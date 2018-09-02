package honey;

public class BasicDistributionTransformingEncoder implements DistributionTransformingEncoder{

    //kell a salt?
    //bejön egy szó
    //szó -> binary
    //binary + salt
    //visszaadja az így kapott stringet
    public String DTEncode(String text) {

        return null;
    }

    //visszafejtés
    //felismerni hogy ez egy valós szó-e (coreNLP)
    //ha valós szó, akkor minden ok
    //ha nem valós szó, akkor kiválasztani egyet az adatbázisból valamilyen számítás alapján
    public String DTDecode(String encodedText) {

        return null;
    }
}
