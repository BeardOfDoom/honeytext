package groovy.honey.crypto.honeytext

import honey.crypto.HoneyEncryption
import honey.crypto.honeytext.HoneyTextEncryption
import honey.dte.DataBasedDistributionTransformingEncoder
import model.mapper.WordMapper
import spock.lang.Specification

import java.security.SecureRandom

class HoneyTextEncryptionSpec extends Specification {
    def "Honeytext encryption should just work"() {
        given:
        def encryptionKey = "Bar12345Bar12345" // 128 bit key
        def decryptionKey = "Bar12345Bar12345" // 128 bit key
        def input = "good, good, good."
        def testDataFile =  new File(getClass().getClassLoader().getResource("adjectives.json").getFile())
        def words = WordMapper.mapWordsFromJson(testDataFile)
        def dte = new DataBasedDistributionTransformingEncoder(words)
        def honeyEncryption = new HoneyEncryption(dte, new SecureRandom())
        def honeyTextEncryption = new HoneyTextEncryption(honeyEncryption)

        when:
        def result = honeyTextEncryption.encrypt(encryptionKey, input)

        then:
        input.equals(honeyTextEncryption.decrypt(decryptionKey, result))
    }

    def "Honeytext encryption should return false data"() {
        given:
        def encryptionKey = "Bar12345Bar12345" // 128 bit key
        def decryptionKey = "Bar12345Bar12344" // 128 bit key
        def input = "good, good, good."
        def testDataFile =  new File(getClass().getClassLoader().getResource("adjectives.json").getFile())
        def words = WordMapper.mapWordsFromJson(testDataFile)
        def dte = new DataBasedDistributionTransformingEncoder(words)
        def honeyEncryption = new HoneyEncryption(dte, new SecureRandom())
        def honeyTextEncryption = new HoneyTextEncryption(honeyEncryption)

        when:
        def result = honeyTextEncryption.encrypt(encryptionKey, input)

        then:
        !input.equals(honeyTextEncryption.decrypt(decryptionKey, result))
        println honeyTextEncryption.decrypt(decryptionKey, result)
        println honeyTextEncryption.decrypt(decryptionKey, result)
    }
}

