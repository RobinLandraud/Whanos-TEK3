here is the doc of the whanos

 _
 \'.    
  \ '-._...-           
  |    _.-'             .
  \   /               '   '  ..
  /   \_               ooo ' . .         
  \     '-.__         o 'oo<. .  '            
   \         '--._       ooooo.  '                        
    |             '._    oo' 'o  '                         
  __\__           _  '. o     o         
 (    __         / \   o         
  '--'\          | | (o)\      
       \         \#/    |        
        \__             |::.            
     .:::\ '.          /:::::.   
     :::::'._'-._____.':::::::                         
      :::::: '----'':::::::::"
        ::::::::::::::::        

# Link project jobs parameters

- GIT_REPOSITORY_URL:
    Git URL of the project to link
- DOCKERFILE_PATH:
    Path to the Dockerfile for containerization
- DOCKER_IMAGE_NAME:
    Name of the Docker image to create
- KUBERNETES_DEPLOYMENT_NAME:
    Name of the Kubernetes deployment

# Deployment

The project can't be deployed yet.
In developpement

# Git Repository

The git repository must match one of this criter:
## C
• Default compiler: GNU Compiler Collection 11.2.
• Criteria of detection: Has a Makefile file at the root of the repository.
• Dependency system: None.
• Compilation: Using “make”.
• Execution: With the resulting compiled binary.
• Base image name: whanos-c

## JAVA
• Default version: Java SE 17.
• Criteria of detection: Has a pom.xml file in the app repository.
• Dependency system: Maven.
• Compilation: Using “mvn package”.
• Execution: Using “java -jar app.jar”.
• Base image name: whanos-java.

## JAVASCRIPT
• Default version: Node.js 14.17.5.
• Criteria of detection: Has a package.json file at the root of the repository.
• Dependency system: npm.
• Compilation: Not applicable.
• Execution: Using “node .”.
• Base image name: whanos-javascript.

## PYTHON
• Default version: 3.10.
• Criteria of detection: Has a requirements.txt file at the root of the repository.
• Dependency system: pip.
• Compilation: Not applicable.
• Execution: Using “python -m app”.
• Base image name: whanos-python.

## BEFUNGE
• Default version: Befunge-93.
• Criteria of detection: Has a single main.bf file in the app directory.
• Dependency system: Not applicable.
• Compilation: Free choice.
• Execution: Free choice, from the root of the repository.
• Base image name: whanos-befunge.
