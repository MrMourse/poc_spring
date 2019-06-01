#!/usr/bin/env groovy
node {
    def buildInfo

    stage('Build and install cora-demo') {
       buildInfo = sh 'mvn -B clean package'
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
