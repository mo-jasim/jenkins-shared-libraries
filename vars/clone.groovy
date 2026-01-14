def call(String url, String branch, String credentials) {
    git url: "${url}",
        branch: "${branch}",
        credentialsId: "${credentials}"
}
