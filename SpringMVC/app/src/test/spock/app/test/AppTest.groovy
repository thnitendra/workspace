package app.test

import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest

/**
 * Created by nitendra.thakur on 2018/01/07.
 *
 * http://thejavatar.com/testing-with-spock/#more-71
 * http://spockframework.org/spock/docs/1.0/interaction_based_testing.html
 * http://spockframework.org/spock/docs/1.0/data_driven_testing.html
 */
class AppTest extends Specification {

    // Invoked before each feature methods (test cases)
    def setup() { println "Setup" }

    def cleanup() { println "Clean up" }

    // Invoked only once before the first feature method
    def setupSpec() { println "Setup specification" }

    def cleanupSpec() { println "Clean up specification" }

    def "should return 2 from first element of list"() {
        given:
        List<Integer> list = new ArrayList<>()
        when:
        list.add(1)
        then:
        2 == list.get(0)
    }

    def "should calculate power of number 2"() {
        expect:
        Math.pow(2) == 4
    }

    def "creating example stubs"() {
        given:
        List list = Stub(List)
        list.size() >> 3

        List list2 = Stub()
        list2.size() >> {
            println "Size method has been invoked"
            return 10
        }

        def list3 = Stub(List)
        list3.size() >>> [1, 2, 3]

        List list4 = Stub()
        list4.size() >> { throw new IllegalStateException() } >>> [1, 2, 3] >> {
            throw new IllegalStateException()
        } >>> [5, 6]
        expect:
        list.size() == 3

        list2.size() == 10

        list3.size() == 1
        list3.size() == 2
        list3.size() == 3

        when:
        list4.size()
        then:
        thrown IllegalStateException

        expect:
        list4.size() == 1
        list4.size() == 2
        list4.size() == 3

        when:
        list4.size()
        then:
        thrown IllegalStateException

    }

    def "creating example mocks"() {
        given:
        List list = Mock(List)

        List list2 = Mock() // preffered way

        def list3 = Mock(List)

        when:
        list.add("One");
        list.add("Two");
        println(list.get(0))
        println(list.size());
        list.remove(0);
        println(list.size());

        then:
        2 * list.add(_ as String)
        1 * list.get(_)
        1 * list.remove(_) >> { println "removed element" }
        2 * list.size() >>> [2, 1]
    }

    @Unroll("#name should have length #length")
    def "name length"() {
        expect:
        name.size() == length

        where:
        name << ["Kirk", "Spock", "Scotty"]
        length << [4, 5, 6]
    }

    def "maximum of two numbers"(int a, int b, int c) {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        1 | 3 | 3
        7 | 4 | 4
        0 | 0 | 0
    }

}
