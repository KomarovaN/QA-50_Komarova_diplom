<details>
   <summary>Требуемое ПО</summary>

На компьютере дожны быть установлены
1. OC Windows не ниже 10 версии,
1. Docker и образы:
- node-app:1.0
- postgres:12-alpine
- mysql:8.0
</details>

### Порядок проверки с postgresql
В IntelliJ IDEA:
1. Выполнить в терминале команду запуска контейнеров:

    *docker-compose up -d*

2. Dыполнить в терминале команду запуска тестируемого приложения (SUT) с параметрами подключения к базе данных postgresql:

    *java “-Dspring.datasource.url=jdbc:postgresql://localhost:5432/data” “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar artifacts\aqa-shop.jar*

3. Открыть новый терминал и выполнить команды по сборке и запуску тестов с параметрами подключения к базе данных postgresql и генератора отчетов:

    *.\gradlew clean test "-Dsource=jdbc:postgresql://localhost:5432/data" “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass”*

    *.\gradlew allureserve*

4. Посмотреть результаты прохождения тестов в AllureReport.
5. Выполнить <Ctrl+C> для команды *.\gradlew allureserve*, ввести на запрос "y".
6. В первом терминале выполнить <Ctrl+C> для команды *java*.

### Порядок проверки с mysql
1. В терминале выполнить команду запуска тестируемого приложения (SUT) с параметрами подключения к базе данных mysql:

    *java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass” -jar artifacts/aqa-shop.jar*

6. Во втором терминале выполнить команды по сборке и запуску тестов с параметрами подключения к базе данных mysql и генератора отчетов:

    *.\gradlew clean test "-Dsource=jdbc:mysql://localhost:3306/app" “-Dspring.datasource.username=app” “-Dspring.datasource.password=pass”*

    *.\gradlew allureserve*

7. Посмотреть результаты прохождения тестов в AllureReport.
8. Выполнить <Ctrl+C> для команды *.\gradlew allureserve*, ввести на запрос "y".
9. В первом терминале выполнить <Ctrl+C> для команды *java*.
10. Выполнить команду:

    *docker-compose down*
