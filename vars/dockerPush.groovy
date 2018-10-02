
def call(String imageNameParam=null){

    String imageNameLocal
    if (!imageNameParam?.trim()){
        def imageName = new imageName()
        imageName.binding = this.binding
        imageNameLocal = "${imageName()}"
    }else{
        imageNameLocal = imageNameParam
    }
    if ("${env.DOCKER_REGISTRY}" == 'null') {
        error 'Variable DOCKER_REGISTRY is not defined'
    }

    def imageTag = new imageTag()
    imageTag.binding = this.binding
    sh "docker push ${env.DOCKER_REGISTRY}/bilderlings/${imageNameLocal}:${imageTag()}"

}
