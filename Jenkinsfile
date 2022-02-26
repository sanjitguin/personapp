podTemplate(containers: [
  containerTemplate(
      name: 'maven', 
      image: 'maven:latest', 
      command: 'sleep', 
      args: '99d'
      )      
  ],
  volumes: [
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
  ]) 

{    
  node(POD_LABEL) {

      env.REPOSITORY = 'sanjitguin/repo'
      env.BRANCH_NAME = 'main'

    
    stage('git push') {
      withCredentials([usernamePassword(credentialsId: 'git-username-pwd', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]){    
         sh('''
              pwd
              ls -l
         ''')
      }
    }
     
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
