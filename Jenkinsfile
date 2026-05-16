pipeline {
    agent any
    
    environment {
        // Jenkins Credentials ID'si 'DockerHub' olan veriyi çeker
        // Otomatik olarak DOCKERHUB_CREDENTIALS_USR ve DOCKERHUB_CREDENTIALS_PSW değişkenlerini üretir
        DOCKERHUB_CREDENTIALS = credentials('DockerHub')
    }
    
    triggers {
        githubPush()
    }

    stages {
        stage('Build Jar') {
            steps {
                // Kodu GitHub'dan çekiyoruz
                git branch: 'main', url: 'https://github.com/Demirors07/bank.git'
                
                // Lokal gradle kurma derdi olmasın diye proje içindeki gradlew'i tetikliyoruz.
                // Çalıştırılabilme yetkisi (chmod) verip ayağa kaldırıyoruz.
                sh "chmod +x gradlew"
                sh "./gradlew clean bootJar"
            }
        }
        
        stage('Build Docker image') {
            steps {
                sh "docker build -t karne07/bank-app:latest ."
            }
        }
        
        stage('DockerHub login') {
            steps {
                // Çift tırnak (") kullandık ve echo ile şifreyi pipe ettik.
                // echo -n kullanımı şifrenin sonuna gizli boşluk/alt satır eklenmesini engeller.
                sh "echo -n ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
            }
        }
        
        stage('Push the image') {
            steps {
                sh "docker push karne07/bank-app:latest"
            }
        }
    }
    
    // Güvenlik ve temizlik için eklenti: İş bitince DockerHub oturumunu kapatır
    post {
        always {
            sh "docker logout"
        }
    }
}