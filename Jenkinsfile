#!/usr/bin/env groovy
pipeline {
    agent any
    tools {
        maven 'maven'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }

        stage('SonarQube analysis') {
            withSonarQubeEnv('sonar') {
              sh 'mvn clean package sonar:sonar'
            } // SonarQube taskId is automatically attached to the pipeline context
          }

        // No need to occupy a node

    }
    stage("Quality Gate"){
             timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
               def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
               if (qg.status != 'OK') {
                 error "Pipeline aborted due to quality gate failure: ${qg.status}"
               }
             }
         }
         }