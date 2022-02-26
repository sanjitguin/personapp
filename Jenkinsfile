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

      env.repository = 'sanjitguin/repo'
      env.BRANCH_NAME = 'main'

    stage('maven build personapp') {
            git url: 'https://github.com/sanjitguin/personapp.git', branch: 'main'
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
          IMAGE_TAG = "JENKINS-${env.BUILD_ID}_${BRANCH_NAME}_${env.DOCKER_TAG}".trim()
          sh 'docker login -u ${username} -p ${password}'
          sh "docker build -t ${repository}:${IMAGE_TAG} ."
          sh "docker push ${repository}:${IMAGE_TAG}"
          sh 'kubectl apply -f deployment.yml' 
          sh 'docker logout'
        }
      }
    }

    stage('deploy to k8') {
      steps{
        sh 'echo deployment done'
      }
    }
  }
}

def getTag() {
  def tag = sh script: 'git rev-parse HEAD', returnStdout: true
  return tag
}
