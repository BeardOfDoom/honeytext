package groovy.honey.crypto

import honey.crypto.SymmetricCrypto
import spock.lang.Specification

class SymmetricCryptoSpec extends Specification {
    def "Basic test of the encryption"() {
        given:
        def key = "Bar12345Bar12345" // 128 bit key
        def initVector = "RandomInitVector".getBytes() // 16 bytes IV
        def input = "GET SOME"

        when:
        def result = SymmetricCrypto.encrypt(key, initVector, input)

        then:
        input.equals(SymmetricCrypto.decrypt(key, initVector, result))
    }
}
