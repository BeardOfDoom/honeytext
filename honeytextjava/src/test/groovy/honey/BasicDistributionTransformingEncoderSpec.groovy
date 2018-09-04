package groovy.honey

import honey.BasicDistributionTransformingEncoder
import model.Word
import model.WordClass
import spock.lang.Specification

class BasicDistributionTransformingEncoderSpec extends Specification {
    def "Basic test of Basic DTE."() {
        given:
        def input = new Word("good", WordClass.ADJECTIVE);
        def dte = new BasicDistributionTransformingEncoder()

        when:
        def result = dte.DTEncode(input)

        then:
        input.getText() == dte.DTDecode(result)
    }
}