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
            container('maven') {
              sh 'mvn --version'
              sh 'mvn -B -ntp clean package -DskipTests'
            }
        
    }
    
    stage('docker build image') {
      withCredentials([
            usernamePassword(credentialsId: 'sanjitguin-docker',
              usernameVariable: 'username',
              passwordVariable: 'password')
          ]) {
        env.TAG = getTag()

        container('docker') {
          sh 'pwd'
          sh 'ls -l'
          print 'username=' + username + 'password=' + password
          IMAGE_TAG = "JENKINS-${env.BUILD_ID}_${BRANCH_NAME}_${env.TAG}_A".trim()
          print 'IMAGE_TAG : '+IMAGE_TAG
          sh 'docker login -u ${username} -p ${password}'
          sh "docker build -t ${env.REPOSITORY}:${IMAGE_TAG} ."
          sh "docker push ${env.REPOSITORY}:${IMAGE_TAG}"
          sh 'docker logout'
        }
      }
    }
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
