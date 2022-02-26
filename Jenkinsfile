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

    
    stages {
      stage('maven build personapp') {
        script {
          env.USERNAME = input message: 'Please enter the username',
                             parameters: [string(defaultValue: '',
                                          description: '',
                                          name: 'Username')]
          env.PASSWORD = input message: 'Please enter the password',
                             parameters: [password(defaultValue: '',
                                          description: '',
                                          name: 'Password')]
        }
        echo "Username: ${env.USERNAME}"
        echo "Password: ${env.PASSWORD}"
      }
    }  
     
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
