#!/usr/bin/env groovy
import jenkins.model.*
jenkins = Jenkins.instance
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
                 script {
                        def server = Artifactory.newServer url: 'http://localhost:8081/artifactory'
                        server.bypassProxy = true
                        def buildInfo = server.upload
                    }
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