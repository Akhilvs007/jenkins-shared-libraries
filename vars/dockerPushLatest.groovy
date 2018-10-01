
def call(String imageName=null){
    String imageNameLocal
    if (!imageName?.trim()){
        imageNameLocal = "${JOB_NAME}".split('/')[0]
    }else{
        imageNameLocal = imageName
    }
    def imageTag = "${BRANCH_NAME}-${BUILD_ID}"
    sh "docker tag ${env.DOCKER_REGISTRY}/bilderlings/${imageNameLocal}:${imageTag} ${env.DOCKER_REGISTRY}/bilderlings/${imageNameLocal}:latest"
    sh "docker push ${env.DOCKER_REGISTRY}/bilderlings/${imageNameLocal}:latest"

}