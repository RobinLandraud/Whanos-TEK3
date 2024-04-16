folder('Whanos base images') {
    displayName('Whanos base images')
    description('Folder for building Whanos base images.')
    
    def languages = ["c", "python", "java", "javascript", "befunge"] // List of supported languages
    
    languages.each { language ->
        def jobName = "whanos-${language}" // Job name based on the language's base image name
        def jobDir = "Whanos base images/${jobName}" // Job directory
        
        job(jobDir) {
            description("Builds the ${language} base image for Whanos.")
            label('docker') // Run the job on a docker node
            
            steps {
                shell("docker build -t whanos/${language} -f /images/${language}/Dockerfile.base /images/${language}")
            }
        }
    }

    job('Whanos base images/Build all base images') {
        description('Builds all Whanos base images.')
        label('docker') // Run the job on a docker node
        
        steps {
            // Trigger all base image jobs
            languages.each { language ->
                build("Whanos base images/whanos-${language}")
            }
        }
    }
}

job('Link-project') {
    description('Links a project to the Whanos infrastructure.')
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the project to link')
        stringParam('DOCKERFILE_PATH', '', 'Path to the Dockerfile for containerization')
        stringParam('DOCKER_IMAGE_NAME', '', 'Name of the Docker image to create')
        stringParam('KUBERNETES_DEPLOYMENT_NAME', '', 'Name of the Kubernetes deployment')
    }
    concurrentBuild(false) // Allow only one build at a time
    
    steps {
        // Create job to build and deploy the project
        def projectName = env.JOB_NAME.tokenize('/')[0] // Get the name of the parent folder
        def jobName = "${projectName}-build-deploy"
        def jobDir = "${projectName}/${jobName}"
        
        configFileProvider([configFile(fileId: 'jenkinsfile', variable: 'JENKINSFILE')]) {
            job(jobDir) {
                description("Builds and deploys the ${projectName} project.")
                label('docker') // Run the job on a docker node
                scm {
                    git {
                        remote {
                            url('${GIT_REPOSITORY_URL}')
                            credentials('my-git-credentials')
                        }
                        branch('main') // Build only the default branch
                    }
                }
                
                stages {
                    stage('Build') {
                        steps {
                            sh("docker build -f ${DOCKERFILE_PATH} -t ${DOCKER_IMAGE_NAME} .")
                        }
                    }
                    stage('Deploy') {
                        when { branch 'main' } // Deploy only on the default branch
                        steps {
                            sh("kubectl apply -f ${KUBERNETES_DEPLOYMENT_NAME}.yaml")
                        }
                    }
                }
            }
        }
    }
}