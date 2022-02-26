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
      git url: 'https://github.com/sanjitguin/personapp.git', branch: 'main'
      withCredentials([usernamePassword(credentialsId: 'git-username-pwd', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]){    
         sh('''
              docker --version
              git config --local credential.helper "!f() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; f"
              git config --global user.email "sanjitguin@gmail.com"
              git config --global user.name "sanjit guin"
              echo abc > t.txt
              git add t.txt
              git commit -m test-commit
              git push origin HEAD:main
         ''')
      }
    }
     
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
