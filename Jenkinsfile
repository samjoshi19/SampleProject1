pipeline {
  agent any
  stages {
    stage('Checkout') { 
      steps { checkout scm } 
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
  success {
    echo 'Pipeline SUCCESS'
    publishHTML([allowMissing: false,
                 alwaysLinkToLastBuild: true,
                 keepAll: true,
                 reportDir: 'target/surefire-reports',
                 reportFiles: 'TEST-com.example.calculator.CalculatorTest.xml',
                 reportName: 'Test Report'])
  }
  failure {
    echo 'Pipeline FAILED'
    }
  }

}
