import TestData.BitbucketStatusTestData
import TestData.CommitIdTestData
import Utils.Helper
import groovy.json.JsonOutput
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized.class)
class BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_Tests extends GroovyTestCase {

    protected bitbucketStatus_ = new bitbucketStatus()
    @Parameterized.Parameters(name = "{0}")
    static Collection<Object> data() {
        BitbucketStatusTestData.definedBuildStatuses()
    }

    protected String buildStatus

    BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_Tests(String buildStatus){
        this.buildStatus = buildStatus
    }
    @Before
    void setUp(){
        def variables = BitbucketStatusTestData.commonVariablesForFirstBuild()
        Helper.setEnvVariables(variables, bitbucketStatus_)
        InjectVars.injectTo(bitbucketStatus_, 'imageName', 'commitId')
        InjectVars.injectClosureTo(bitbucketStatus_, 'sh', CommitIdTestData.lastCommitIdClosure)
    }


    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_requestUrlIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }
        def expectedUrl = 'https://api.bitbucket.org/2.0/repositories/bilderlings/Job_Name/commit/1111/statuses/build'

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals(expectedUrl, actualHttpRequestParameters[1]['url'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_authenticationIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals('bitbucket-oauth-credentials', actualHttpRequestParameters[1]['authentication'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_httpMethodIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals('POST', actualHttpRequestParameters[1]['httpMode'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_contentTypeIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals('APPLICATION_JSON', actualHttpRequestParameters[1]['contentType'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_responseCodeIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals('200:201', actualHttpRequestParameters[1]['validResponseCodes'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_requestBodyIsCorrect(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        def data = [
                state: BitbucketStatusTestData.buildStateMap()[buildStatus],
                url: 'http://jenkins.k8s.iamoffice.lv/blue/organizations/jenkins/Job_Name/detail/master/1/pipeline/',
                key: 'Job_Name',
                name: 'Job_Name/master #1'
        ]
        def expectedBody = JsonOutput.toJson(data)

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        assertEquals(expectedBody, actualHttpRequestParameters[1]['requestBody'])

    }

    @Test
    void test_BitbucketStatusFirstBuild_NullBitbucketStatus_DefinedBuildStatus_requestHeaderContainsAccessToken(){
        Helper.setBuildStatus(buildStatus, bitbucketStatus_)
        def actualHttpRequestParameters = []
        bitbucketStatus_.httpRequest = { Map map ->
            actualHttpRequestParameters << map
            return BitbucketStatusTestData.httpRequestMock(map)
        }

        def expectedHeader = [name: 'Authorization', value: 'Bearer fake_access_token=']

        bitbucketStatus_(null)

        assertTrue('We should have 2 requests', actualHttpRequestParameters.size() == 2)
        def actualHeaders = actualHttpRequestParameters[1]['customHeaders']
        assertTrue('We should have 1 custom header', actualHeaders.size() == 1)
        assertEquals(expectedHeader, actualHeaders[0])

    }

}
