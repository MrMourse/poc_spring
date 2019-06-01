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
        stage('SonarQubeScanner') {
          steps {
             withSonarQubeEnv 'sonar'
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
  }