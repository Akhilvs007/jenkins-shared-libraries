import TestData.Docker.DockerPushTestData
import TestData.Docker.DockerTestData
import Utils.Helper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized.class)
class DockerPush_CustomImageName_Tests extends GroovyTestCase {

    @Parameterized.Parameters(name = "{0}")
    static Collection<Object> data() {
        DockerTestData.customImageNames()
    }

    protected String imageName
    protected dockerPush_ = new dockerPush()

    DockerPush_CustomImageName_Tests(String imageName){
        this.imageName = imageName
    }

    @Before
    void setUp(){
        def variables = DockerPushTestData.commonVariables()
        Helper.setEnvVariables(variables, dockerPush_)
        InjectVars.injectTo(dockerPush_, 'imageName', 'imageTag', 'commitId')
    }

    @Test
    void test_DockerPushLatest_checkOrderOfCommands(){
        def actualCommands = []
        dockerPush_.sh = { command ->
            if (command instanceof Map){
                if (command.returnStdout && command.script == "git log -n 1 --pretty=format:'%H'"){
                    return "1111"
                }
            }
            actualCommands << command; return null
        }
        def expectedShellCommands = [
                "docker push \"registry.com/bilderlings/${imageName}:master-1\"".toString(),
                "docker push \"registry.com/bilderlings/${imageName}:1111\"".toString()
        ]

        dockerPush_(imageName)

        assertEquals(expectedShellCommands, actualCommands)

    }

}