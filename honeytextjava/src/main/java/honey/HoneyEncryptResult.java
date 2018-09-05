package honey;

import model.Word;

public class HoneyEncryptResult {
    private byte[] initVector;
    private Word ciphertext;

    public HoneyEncryptResult(byte[] initVector, Word ciphertext) {
        this.initVector = initVector;
        this.ciphertext = ciphertext;
    }

    public byte[] getInitVector() {
        return initVector;
    }

    public Word getCiphertext() {
        return ciphertext;
    }

    public void setInitVector(byte[] initVector) {
        this.initVector = initVector;
    }

    public void setCiphertext(Word ciphertext) {
        this.ciphertext = ciphertext;
    }
}
