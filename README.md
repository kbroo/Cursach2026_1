# FABORIS
Веб-приложение, разрабатываемое для интернет-магазина (курсовой проект).

### Используемые технологии:
* Java 27
* Apache Tomcat 11
* Jakarta Servlet, JSP, JSTL
* MySQL 8.0
* JDBC, HikariCP, Flyway
* Maven

## Инструкция по развертыванию:

### Необходимые утилиты/программы:
* JDK (Java Development Kit) 17+ версии.
* Apache Maven.
* Apache Tomcat (11+ версии).
* Сервер MySQL (OpenServerPanel, MySQL Workbench и подобные).

### 1. Настройка базы данных
1. Создать пустую базу данных (например, `webMarket`).
2. Изменить значения и переименовать файл db.example.properties (в пакете src/main/resources)

### 2. Сборка и запуск

#### Через терминал:
1. Собрать проект с помощью Maven в корневой папке проекта
    ```bash
    mvn clean package
   ```
2. После скопировать .war файл, созданный после сборки проекта, в папку webapps Apache Tomcat
3. Запустить Tomcat.

#### При помощи IntelliJ IDEA ULTIMATE:
1. Открыть проект в IntelliJ IDEA
2. Добавить конфигурацию запуска `Run` -> `Edit Configurations` -> `+` -> `Tomcat Server` -> `Local`
3. На вкладке `Deployment` нажать `+` -> `Artifact...` и выбрать `webMarket:war exploded`
#### либо (При отсутствии IntelliJ IDEA ULTIMATE):
1. Открыть проект в IntelliJ IDEA 
2. Установить плагин Smart Tomcat
3. Добавить конфигурацию запуска `Run` -> `Edit Configurations` -> `+` -> `Smart Tomcat`.
   В настройках конфигурации указать:
    *   **Name:** любое имя (например, `Local Tomcat`).
    *   **Tomcat Server:** путь к папке, куда распакован Apache Tomcat.
    *   **Deployment:** путь к папке `src/main/webapp` вашего проекта.
    *   **Context Path:** `/` (или `/webMarket`). 
4. `Apply` -> `Run`

### Комментарии:
* Временно добавление товаров доступно только напрямую через базу данных – в таблицу products.\
Пример: INSERT INTO `products` (`id`, `name`, `description`, `category`, `imageUrl`, `price`, `stock`) VALUES (NULL, 'name', 'description', 'ELECTRONICS', 'https://i.imgur.com/oa9osNY.jpeg', '4709.99', '35');
В поле category значения записывать из enum Category.java (src/main/java/model/enums/Category.java)