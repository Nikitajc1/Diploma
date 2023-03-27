[![Build status](https://ci.appveyor.com/api/projects/status/ig6nixe8a2xkf7sf/branch/main?svg=true)](https://ci.appveyor.com/project/Nikitajc1/diploma/branch/main)
# Основные артефакты:
1. 


# Инструкция по запуску
1. Выполнить `pull` данного репозитория
2. Открыть проект в InteliJ Idea
3. Запустить Docker
4. В консоли Idea прописать docker-compose up
### Для запуска через mysql:
1. Прописать в консоли Idea: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar ./artifacts/aqa-shop.jar`
2. В консоли gradle: `gradlew test -Dselenide.headless=true -Ddb.url=jdbc:mysql://localhost:3306/app`

### Для запуска через postgresql:
1. Прописать в консоли Idea: java `-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar ./artifacts/aqa-shop.jar`
2. В консоли gradle: `gradlew test -Dselenide.headless=true -Ddb.url=jdbc:postgresql://localhost:5432/app`

## Для отчёта
1. В консоли gradle: `gradlew allureReport` или `gradle allureServe`