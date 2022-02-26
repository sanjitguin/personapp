podTemplate(containers: [
  containerTemplate(
      name: 'maven', 
      image: 'maven:latest', 
      command: 'sleep', 
      args: '99d'
      ),
   containerTemplate(
       name: 'docker', 
       image: 'docker', 
       ttyEnabled: true, 
       command: 'cat'
       )      
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
  ]) 

{    
  node(POD_LABEL) {

      env.REPOSITORY = 'sanjitguin/repo'
      env.BRANCH_NAME = 'main'

    stage('maven build personapp') {
         git url: 'https://github.com/sanjitguin/personapp.git', branch: 'main'
            container('maven') {
              sh 'git branch'
              sh 'mvn --version'
            }
        
    }
    
    stage('git argocd repo update') {
      withCredentials([gitUsernamePassword(credentialsId: 'git-username-pwd',
                 gitToolName: 'git-tool')]) {
         sh 'echo abc > t.txt' 
         sh 'git add t.txt'
         sh 'git commit -m test-commit'
         sh 'git push'
      }
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
