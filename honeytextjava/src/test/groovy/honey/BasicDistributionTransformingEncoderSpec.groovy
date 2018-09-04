package groovy.honey

import honey.BasicDistributionTransformingEncoder
import spock.lang.Specification

class BasicDistributionTransformingEncoderSpec extends Specification {
    def "Basic test of Basic DTE."() {
        given:
        def input = "aő"
        def dte = new BasicDistributionTransformingEncoder()

        when:
        def result = dte.DTEncode(input)

        then:
        input == dte.DTDecode(result)
    }
}