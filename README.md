# uploader-images
REST API реализован на JAVA 8 с использование фраемворка SPRIN BOOT.
WEB интерфейс - VUE.js.
Cистема автоматической сборки - Gradle.
CI интеграция - travis, измерение качества кода SonarCloud.

# Запуск
Для запуска приложениея достаточно запустить скрипт в корне проекта:
./gradlew build
После чего запустить сформерованный jar
java -jar build/libs/gs-spring-boot-0.1.0.jar

Если не расматривать это приложение как тестовое задание, то можно собрать в WAR файл и сделать деплой, например, в Tomcat.

#API

Uploader

По умаолчанию порт установлен 26001, его можно изменить в src/main/resources/application.properties параметр server.port.

<host:port>/upload - загрузка изображений на сервер.
Возможные варианты:
  1. multipart/form-data
  2. application/json типа {encoded, name}.
  3. application/json типа {url, name}. Name - необязательный параметр, в случае отсутствия, сервис определяет имя из URL.
  
  В случае если, с таким наименование файл уже имеется сервис добавляет число в имя.
  
  Во всех 3 вариантах можно отправлять сразу много файлов.
  Есть ограничение по максимальному размеру картинок и запроса, регулировать можно через application.properties параметрами spring.servlet.multipart.max-file-size и spring.servlet.multipart.max-request-size.

#Тестирование

Реализована стартовая страница, без 'красоты'.
Есть возможность отправить 3 вышеуказанных запроса с предварительным превью 100px 100px с сохранением пропорций картинок.
