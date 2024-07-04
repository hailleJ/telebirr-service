pipeline {
    agent any
    
    stages {

        stage("Pull from github") {
          steps {
              checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'PersonalPassword', url: 'https://github.com/hailleJ/telebirr-service/']])
          }
        }


        stage("build jar") {
            steps {
                sh "sudo mvn clean package"
            }
        }


        stage("push to local registry") {
            steps {
                sh "sudo mvn jib:build -Djib.allowInsecureRegistries=true -DsendCredentialsOverHttp=true"
            }
        }




    }
}