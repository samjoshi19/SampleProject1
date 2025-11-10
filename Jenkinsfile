pipeline {
  agent any
  stages {
    stage('Checkout') { 
      steps { checkout sc } 
    }
    stage('Build') {
      steps {
        echo 'Running mvn clean package'
        bat 'mvn -B -DskipTests=false clean package'
      }
    }
    stage('Test') {
      steps {
        echo 'Running mvn test'
        bat 'mvn -B test'
      }
      post {
        always {
          junit '**\\target\\surefire-reports\\*.xml'
        }
      }
    }
  }
  post {
    success { echo 'Pipeline SUCCESS' }
    failure { echo 'Pipeline FAILED' }
  }
}
