pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
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
          // publish junit XML results to Jenkins test result
          junit '**\\target\\surefire-reports\\*.xml'
        }
      }
    }
  }

  post {
    success {
      echo 'Pipeline SUCCESS'

      // generate HTML report from surefire XMLs
      bat 'mvn surefire-report:report'

      // publish the generated HTML report (surefire-report.html in target/site)
      publishHTML([allowMissing: false,
                   alwaysLinkToLastBuild: true,
                   keepAll: true,
                   reportDir: 'target/site',
                   reportFiles: 'surefire-report.html',
                   reportName: 'Test Report'])
    }

    failure {
      echo 'Pipeline FAILED'
    }
  }
}
