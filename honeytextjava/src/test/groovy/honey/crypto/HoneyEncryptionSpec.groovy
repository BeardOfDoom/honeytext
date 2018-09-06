package groovy.honey.crypto

import honey.crypto.HoneyEncryption
import honey.dte.DataBasedDistributionTransformingEncoder
import model.Word
import model.WordClass
import model.mapper.WordMapper
import spock.lang.Specification

import java.security.SecureRandom

class HoneyEncryptionSpec extends Specification {
    def "Basic test of the honey encryption"() {
        given:
        def key = "Bar12345Bar12345" // 128 bit key
        def input = new Word("good", WordClass.JJ)
        def testDataFile =  new File(getClass().getClassLoader().getResource("adjectives.json").getFile())
        def words = WordMapper.mapWordsFromJson(testDataFile)
        def dte = new DataBasedDistributionTransformingEncoder(words)
        def honeyEncryption = new HoneyEncryption(dte, new SecureRandom())

        when:
        def result = honeyEncryption.encrypt(key, input)

        then:
        input.getText().equals(honeyEncryption.decrypt(key, result))
    }

    def "Basic test of the honey encryption, with wrong password"() {
        given:
        def encryptionKey = "Bar12345Bar12345" // 128 bit key
        def decryptionKey = "Bar12345Bar12asd" // 128 bit key
        def input = new Word("good", WordClass.JJ)
        def testDataFile =  new File(getClass().getClassLoader().getResource("adjectives.json").getFile())
        def words = WordMapper.mapWordsFromJson(testDataFile)
        def dte = new DataBasedDistributionTransformingEncoder(words)
        def honeyEncryption = new HoneyEncryption(dte, new SecureRandom())

        when:
        def result = honeyEncryption.encrypt(encryptionKey, input)

        then:
        !input.getText().equals(honeyEncryption.decrypt(decryptionKey, result))
    }
}

