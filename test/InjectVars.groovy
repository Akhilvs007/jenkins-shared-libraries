
static injectTo(Script script, String ... propertyNames){
    propertyNames.each { propertyName ->
        def instance = getInstance(propertyName)
        if (instance.hasProperty('binding')) {
            instance.binding = script.binding
        }
        script[propertyName] = instance
    }

}

static injectClosureTo(Script script, String propertyName, Closure closure){
    script[propertyName] = closure

}

private static getInstance(String propertyName){
    switch (propertyName){
        case 'imageName': return new imageName()
        case 'imageTag': return new imageTag()
        case 'commitId': return new commitId()
        case 'bitbucketStatus': return new bitbucketStatus()
        case 'successBuild': return new successBuild()
        case 'TOKEN': return '123'
        default: throw new IllegalArgumentException("Undefined property nmae: ${propertyName}")
    }
}
