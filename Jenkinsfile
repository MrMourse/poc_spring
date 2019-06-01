#!/usr/bin/env groovy
node {
    def buildInfo
    agent {
            docker {
                image 'maven:3-alpine'
                args '-v /root/.m2:/root/.m2'
            }
    }

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
