#!/usr/bin/env groovy
node {
    def mvnHome = tool name: 'Maven3.6.1', type: 'maven'
    def buildInfo

    stage('Checkout sources') {
       dir('${artifactId}') {
            git branch : 'master', credentialsId : 'credentials_git_cora_demo', url: 'ssh://git@localhost/srv/git/cora.git'
       }
    }

    stage('Build and install cora-demo') {
       buildInfo = sh "'${mvnHome}/bin/mvn' clean install -f ${artifactId}/pom.xml -s /opt/config/settings-sopra.xml"
    }

    stage('SonarQubeScanner') {
      steps {
         withSonarQubeEnv 'SonarQubeScanner 3.3.0'
      }
   }


    stage('Archive artifacts') {
       archiveArtifacts artifacts: '**/target/*.jar'
       cleanWs()
    }

    stage('Publish build info') {
            server.publishBuildInfo buildInfo
    }
}
