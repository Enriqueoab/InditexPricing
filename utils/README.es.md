# Inditex Pricing

***Tarea de repositorio para Inditex realizada por Enrique Barca***

[![en](https://img.shields.io/badge/lang-English-green.svg)](./README.md)


## 1. Cómo ejecutar la aplicación y los tests.

### Ejecutarla sin usar la imagen de Docker:

- Una vez que tengamos el código en nuestro IDE, tenemos que establecer nuestra ***configuración de ejecución, qué tipo de aplicación es, qué archivo es
  la clase principal, etc.***. Luego deberíamos poder ejecutar la aplicación, ***la BD se ejecutará en el puerto 3000*** tenlo en cuenta.

#### Ejecutar los tests

- Podemos ejecutar el siguiente comando desde la carpeta raíz de la aplicación para construir y ejecutar todos los tests de la aplicación.

```sh

mvn clean install

```
### Ejecútalo con la imagen de docker

- Podemos construir la imagen desde el archivo docker-compose desde la raíz de la carpeta de la aplicación ***Asegúrate de que tu docker esté
  activo, o puedes obtener un error***

- Podemos construir la imagen y ejecutarla cuando termine, con todos los servicios necesarios con el siguiente comando:

```sh

docker-compose up --build

```

## 2. Base de datos H2:

- La aplicación se está ejecutando en una base de datos en memoria, como se sugirió en el archivo de requisitos, por lo que la URL para visualizar la consola
  sería:

```

http://localhost:3000/h2-console

```

- No es necesario agregar ningún dato a la base de datos, ya que el ***[script](../src/main/resources/data.sql)*** se ejecutará y agregará la
  información necesaria para los tests (Hibernate creará la tabla).

- Y, por si los datos no fuesen los correctos, los valores en la propia consola deberían ser:

JDBC URL:
```
jdbc:h2:file:./src/main/resources/db/InditexPricingTaskDb
```
```
User name: Enrique
password: inditexTask
```

## 3. Colección Postman:

- En este repositorio git, deberíamos poder ver, dentro de la carpeta utils en la raíz del proyecto, un archivo JSON llamado
  ```Inditex.postman_collection.json```. Contiene toda la información del endpoint, las credenciales de seguridad y los tests
  con los valores, del archivo de requisitos, ya rellenados.

Solo tenemos que abrir Postman e importar el archivo ```Pestaña "Colecciones" > botón "Importar"```

## 4. Endpoints - Documentación:

- La aplicación tiene una dependencia de Swagger por lo que, cuando el programa se esté ejecutando, podemos visitar la 
siguiente URL como referencia si es necesario

```
http://localhost:3000/swagger-ui/index.html
```

- #### Ahora una breve introducción al endpoint.

| Nombre | URL | Qué hace | Limita |
|---------------|-------------------------------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| Solicitud de precio | /v1/price | Obtener el precio de un producto y marca para una fecha específica. | Si no hay coincidencia, lanzará una excepción personalizada "PriceNotFoundException". Y si la "applicationDate" no tiene el formato correcto, lanzará una "DateTimeFormatException" |

***Por supuesto, el punto final tiene otros casos extremos en caso de que falte información, los ID tengan un formato
incorrecto o no se envíen los parámetros***


## 5. ¿Qué he creado?

- Este es un pequeño ejemplo de algunas de mis habilidades desarrollando una aplicación de ***arquitectura hexagonal basada y DDD***.
- Agrego una configuración de *seguridad* básica como podemos ver en la [clase de seguridad](../src/main/java/com/inditex/pricing/infrastructure/config/security/SecurityConfig.java)
  y podemos hacer pruebas yendo a nuestro postman una vez que tengamos la colección exportada, yendo a la pestaña "Autorización".
- Creo el ***[Dockerfile](.././Dockerfile)*** y el ***[docker-compose](.././docker-compose.yml)*** para una forma más fácil de
  construir y ejecutar la aplicación y la instancia de base de datos h2.
- Creo excepciones personalizadas como ***[DateTimeFormatException](../src/main/java/com/inditex/pricing/domain/exception/DateTimeFormatException.java)***
  y ***[PriceNotFoundException](../src/main/java/com/inditex/pricing/domain/exception/PriceNotFoundException.java)***, para una
  mejor comunicación back-end, sin olvidar el manejo de excepciones globales ***[GlobalExceptionHandler](../src/main/java/com/inditex/pricing/domain/exception/GlobalExceptionHandler.java)***
  donde podemos ver el método creado para manejar la mayoría de las excepciones generales y todas las personalizadas que podamos crear en el futuro.

## 6. Patrones utilizados:

### Arquitectura Hexagonal (Puertos y Adaptadores)
- Rol: La aplicación está estructurada para separar la lógica de negocio principal (dominio) de los sistemas externos (como bases de datos y APIs REST).

- Componentes:
  Capa de dominio: contiene entidades, objetos de valor y servicios de dominio.
  Capa de aplicación (puertos): define casos de uso e interactúa con sistemas externos a través de interfaces.
  Capa de infraestructura (adaptadores): implementa las interfaces para conectarse a sistemas externos.

### Patrón de repositorio
- Función: abstrae la lógica de acceso a los datos. PriceRepository (un repositorio JPA) interactúa con la base de datos mientras que el dominio
  permanece independiente de los detalles de persistencia.
- Implementación:
  PriceRepository en la capa de infraestructura implementa el patrón de repositorio al extender JpaRepository.

### Patrón Singleton
- Función: garantiza una única instancia de recursos compartidos, como configuración o registro.
- Ejemplos:
  La ***[instancia de Logger](../src/main/java/com/inditex/pricing/infrastructure/LoggerConfig.java)*** en ***[GetPriceUseCaseAdapter](../src/main/java/com/inditex/pricing/application/service/GetPriceUseCaseAdapter.java)***
  u otros componentes.
  Los beans definidos en el contexto de Spring son singletons de forma predeterminada a menos que se especifique lo contrario.

### Patrón de método de plantilla
- Función: define el esqueleto de un algoritmo en una clase base y permite que las subclases anulen pasos específicos.
- Ejemplo:
Como lo que hice con la clase padre de la excepción ***[InditexPricingException](../src/main/java/com/inditex/pricing/domain/exception/InditexPricingException.java)***.

### Patrón de inyección de dependencias (DI)
- Función: promueve el acoplamiento flexible inyectando dependencias en los componentes.
- Ejemplo:
  Spring Boot inyecta automáticamente dependencias como PriceRepository en los servicios.

### Patrón DTO (Objeto de transferencia de datos)
- Función: facilita la transferencia de datos entre capas.
- Ejemplos:
  ***[PriceRequest](../src/main/java/com/inditex/pricing/web/request/PriceRequest.java)*** y ***[PriceResponse](../src/main/java/com/inditex/pricing/web/response/PriceResponse.java)***
  actúan como DTO para pasar datos entre el controlador, el servicio y el cliente.

### Patrón Decorator
- Función: agrega responsabilidades a los objetos de forma dinámica.
- Ejemplo:
  Componentes similares a middleware en la capa de aplicación para registro o seguridad.