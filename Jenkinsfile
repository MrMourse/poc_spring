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
              steps {
                script {
                  // requires SonarQube Scanner 2.8+
                  scannerHome = tool 'sonarScanner'
                }
                withSonarQubeEnv('sonar') {
                  sh "${scannerHome}/bin/sonar-scanner"
                }
              }
            }
        stage('Release') {
                steps {
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        stage('Upload Artifact') {
            steps {
                 rtUpload (
                     serverId: "artifactory",
                     spec:
                         """{
                           "files": [
                             {
                               "pattern": "**/target/*.jar",
                               "target": "**/target/*.jar"
                             }
                          ]
                         }"""
                 )
            }
        }
        }

    post {
            always {
                cobertura coberturaReportFile: "**/target/site/cobertura/coverage.xml"
                cleanWs()
            }
        }

 }