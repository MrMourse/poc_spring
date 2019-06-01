#!/usr/bin/env groovy
node {
    def mvnHome = tool name: 'Maven3.6.1', type: 'maven'
    def buildInfo

    stage('Build and install cora-demo') {
       buildInfo = sh "'${mvnHome}/bin/mvn' clean install -f pom.xml"
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
