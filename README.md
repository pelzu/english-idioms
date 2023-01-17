# API for ang.pl site

<img src="https://www.ang.pl/img/mlogo-en.png">

----
API is using  [www.ang.pl](https://www.ang.pl/) site to get english phrasal verbs and idioms. Aplication is based on
spring boot.

----

## Required

**Necessary:**

1. Java 11 Version
2. Spring Boot
3. PostgreSQL 15.1 Version

**Optional use :**
PostgreSQL by docker

### First configuration PostreSQL

1. Chose your operating system and Download PostgreSQL 15.1 Version
   from [link](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
2. Install PostgreSQL
3. Customize and secure save port/userName/Password (you will need it later)

### (Optional)PostgreSQL by docker

1. First you should get docker (remember install necessary Docker Desktop WSL 2 backend when you use windows)
    1. [WSL](https://docs.docker.com/desktop/windows/wsl/)(Only if you use windows)
    2. [Docker Desktop](https://www.docker.com/)
    3. If you have Docker already installed, just open a terminal and run

   ```shell
   docker pull postgres    
   ```
    4. If you download image of postgres try:
   ```shell
   docker run   
                --name=postgress 
                -p=5432:5432 
                -e=POSTGRES_USER=user 
                -e=POSTGRES_PASSWORD=1234 
                -e=POSTGRES_DB=postgresDB 
                -d=postgres
   ```
   ```shell
   docker run --name postgress -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=postgresDB -d postgres
   ```

  ` docker run` is the command used to create and run a new container based on an already downloaded image.
 
 `--name myPostgresDb` is the name we assign to the container that we are creating.

`-p 5455:5432` is the port mapping. Postgres natively exposes the port 5432, and we have to map that port (that lives within Docker) to a local port. In this case, the local 5455 port maps to Docker's 5432 port.

`-e POSTGRES_USER=postgresUser`, `-e POSTGRES_PASSWORD=postgresPW`, and `-e POSTGRES_DB=postgresDB` set some environment variables. Of course, we're defining the username and password of the admin user, as well as the name of the database.

`-d `indicates that the container run in a detached mode. This means that the container runs in a background process.

`postgres` is the name of the image we are using to create the container.

2. DB should be opened when you using this app

## To get this app.

```shell
git clone https://github.com/pelzu/english-idioms
```



### Customize app

You can change in **application.properties** file witch port will be used. Default is 8000

```properties
server.port=8000
```

By the first customization PosgreSQL DB you will also do custom userName and Password. Put both to properties:

```properties
spring.datasource.username=user
spring.datasource.password=1234
```

Or other...

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgress
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=5
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## To start using API fo ang.pl

You should use links bellow.

1. http://localhost:8000/learn?kind=idiom
    1. return all sorted idiom by "id"
    2. -return idiom list
2. http://localhost:8000/learn?kind=idiom&csv=true
   add value to additional parameter `csv=true` to create idiom.csv file with all idioms.

    1. -return idiom list
    2. -create `idiom.csv` file
3. http://localhost:8000/learn?kind=idiom&csv=true&audio=true  
   add value to additional parameter `audio=true` to download audio file for each idiom
    1. -return idiom list
    2. -create `idiom.csv` file
    3. -download all audio file both examples and translations
4. http://localhost:8000/learn?kind=phrasal
    1. return all sorted phrasal by "id"
5. http://localhost:8000/learn?kind=phrasal&csv=true
    1. -return idiom list
    2. -create `phrasal.csv` file

## Returned object.

```JSON
[
  {
    "id": "1",
    "polishMeaning": "trzymać coś w tajemnicy",
    "englishMeaning": "keep sth under one's hat",
    "englishExample": "They tried to keep it under their hat but it soon became obvious that she is pregnant.",
    "audioTranslateLink": "https://www.ang.pl/sound/idioms/keep-sth-under-ones-hat.mp3",
    "audioExampleLink": "https://www.ang.pl/sound/idioms/example/they-tried-to-keep-it-under-their.mp3",
    "linkToIdiom": "https://www.ang.pl/slownictwo/idiomy/1"
  },
  {
    "id": "2",
    "polishMeaning": "druga strona medalu",
    "englishMeaning": "the other side of the coin",
    "englishExample": "Fame has the other side of the coin as well.",
    "audioTranslateLink": "https://www.ang.pl/sound/idioms/the-other-side-of-the-coin.mp3",
    "audioExampleLink": "https://www.ang.pl/sound/idioms/example/fame-has-the-other-side-of-the.mp3",
    "linkToIdiom": "https://www.ang.pl/slownictwo/idiomy/2"
  },
  ....
]
```



