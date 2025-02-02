# Introduction
User project for managing user related data

# Version
Current version : user-1.0.0.jar

# Build and test - This command already integrated in docker file

1. gradle clean build

# DB
Used H2 Db for storing data

# Credentials
1. User credentials:

user_name: user
password: user123

2. Admin credentials:

user_name: admin
password: secret123

# Swagger UI

url :
http://localhost:8087/swagger-ui/index.html#/

# Docker Commands For locally Running

1. docker build -t user .
2. docker run -p 8087:8087 user