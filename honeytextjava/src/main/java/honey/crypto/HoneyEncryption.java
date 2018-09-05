package honey.crypto;

import honey.dte.IDistributionTransformingEncoder;
import model.Word;

import java.security.SecureRandom;

public class HoneyEncryption {

    private final IDistributionTransformingEncoder dte;
    private final SecureRandom random;

    public HoneyEncryption(IDistributionTransformingEncoder dte, SecureRandom random) {
        this.dte = dte;
        this.random = random;
    }

    public HoneyEncryptResult encrypt(String key, Word input){
        String dteResult = dte.DTEncode(input);
        byte[] initVector = random.generateSeed(16);
        String ciphertext = SymmetricCrypto.encrypt(key, initVector, dteResult);
        return new HoneyEncryptResult(initVector, new Word(ciphertext, input.getWordClass()));
    }

    public String decrypt(String key, byte[] initVector, Word input){
        String decryptResult = SymmetricCrypto.decrypt(key, initVector, input.getText());
        return dte.DTDecode(new Word(decryptResult, input.getWordClass()));
    }

}
