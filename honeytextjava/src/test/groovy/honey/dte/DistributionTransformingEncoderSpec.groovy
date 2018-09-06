package groovy.honey.dte

import honey.dte.BasicDistributionTransformingEncoder
import honey.dte.DataBasedDistributionTransformingEncoder
import model.Word
import model.WordClass
import model.mapper.WordMapper
import spock.lang.Specification

class DistributionTransformingEncoderSpec extends Specification {
    def "Basic test of Basic DTE."() {
        given:
        def input = new Word("good", WordClass.JJ)
        def dte = new BasicDistributionTransformingEncoder()

        when:
        def result = dte.DTEncode(input)

        then:
        input.getText().equals(dte.DTDecode(new Word(result, input.getWordClass())))
    }

    def "Basic test of data based DTE."() {
        given:
        def testDataFile =  new File(getClass().getClassLoader().getResource("adjectives.json").getFile())
        def input = new Word("good", WordClass.JJ)
        def words = WordMapper.mapWordsFromJson(testDataFile)
        def dte = new DataBasedDistributionTransformingEncoder(words)

        when:
        def result = dte.DTEncode(input)

        then:
        input.getText().equals(dte.DTDecode(new Word(result, input.getWordClass())))
    }
}