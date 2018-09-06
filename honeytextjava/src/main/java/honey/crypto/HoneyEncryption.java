package honey.crypto;

import honey.dte.IDistributionTransformingEncoder;
import model.Word;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class HoneyEncryption {

    private final IDistributionTransformingEncoder dte;
    private final SecureRandom random;

    public HoneyEncryption(IDistributionTransformingEncoder dte, SecureRandom random) {
        this.dte = dte;
        this.random = random;
    }

    public Word encrypt(String key, Word input){
        String dteResult = dte.DTEncode(input);
        byte[] initVector = random.generateSeed(16);
        String ciphertext = SymmetricCrypto.encrypt(key, initVector, dteResult);
        return new Word(new String(initVector, StandardCharsets.ISO_8859_1) + ciphertext, input.getWordClass());
    }

    public String decrypt(String key, Word input){
        byte[] initVector = input.getText().substring(0, 16).getBytes(StandardCharsets.ISO_8859_1);
        String ciphertext = input.getText().substring(16);
        String decryptResult = SymmetricCrypto.decrypt(key, initVector, ciphertext);
        return dte.DTDecode(new Word(decryptResult, input.getWordClass()));
    }

}
