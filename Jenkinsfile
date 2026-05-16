pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE_NAME = 'karne07/bank-app'
        DOCKER_IMAGE_TAG = 'latest'

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
                git branch: 'master', url: 'https://github.com/Demirors07/bank.git'
                
                // Lokal gradle kurma derdi olmasın diye proje içindeki gradlew'i tetikliyoruz.
                // Çalıştırılabilme yetkisi (chmod) verip ayağa kaldırıyoruz.
                sh "chmod +x gradlew"
                sh "./gradlew clean bootJar"
            }
        }
        
        stage('Build Docker image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
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
                sh "docker push ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
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