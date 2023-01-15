# API for ang.pl site


<img src="https://www.ang.pl/img/mlogo-en.png">

----
API is using  [www.ang.pl](https://www.ang.pl/) site to get english phrasal verbs and idioms. Aplication is based on spring boot. 

----
## Required


Java 11 Version

Spring Boot


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

```
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
.....
]
```


## To get this app.

```

git clone https://github.com/pelzu/english-idioms

```
## Customize app
You can change in application.properties file witch port will be used. Default is 8000
```
server.port=8000
```
Also you should set correct userName and Password
```
spring.datasource.username=
spring.datasource.password=
```
Or other...
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=5
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
