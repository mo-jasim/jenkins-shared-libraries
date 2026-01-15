def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def credentials = config.credentials ?: 'dockerCreds'

    echo "Pushing Docker image: ${imageName}:${imageTag}"

    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD'
    )]) {
        sh """
            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
            
            // This is the missing part from your previous attempt
            docker tag ${imageName}:${imageTag} \$DOCKER_USERNAME/${imageName}:${imageTag}
            
            // Push the version that includes your username
            docker push \$DOCKER_USERNAME/${imageName}:${imageTag}
        """
    }
}
