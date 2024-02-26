# Spring Webflux Reactive REST API
A demo project to develop a REST API using **Spring Webflux** and **Spring Reactive Mongo**. The aim is to apply reactive programming paradigm in Java with Spring.

* **Test Driven Development** is adopted in this project.
* Both MVC and functional routes are used to handle requests.
* MVC controllers handle the requests to the endpoints starting with **api/v1** while functional routes deal with the requests to the endpoints starting with **api/v2**
* **MongoDb** is used to persist data.
* **Mongo Express** is used to manage database visually.
* A ***docker-compose.yml*** file for containerization.
* Using the command **docker-compose up** will handle creating container from the images.
---

## Endpoints

| Endpoint                                                      | Method | Function             |
|---------------------------------------------------------------|--------|----------------------|
| <li>/api/v1/products</li> <li>/api/v2/products</li>           | GET    | Fetch all products   |
| <li>/api/v1/products/{id}</li> <li>/api/v2/products/{id}</li> | GET    | Fetch product by id  |
| <li>/api/v1/products</li> <li>/api/v2/products</li>           | POST   | Create a new product |
| <li>/api/v1/products/{id}</li> <li>/api/v2/products/{id}</li> | PUT    | Update product by id |
| <li>/api/v1/products/{id}</li> <li>/api/v2/products/{id}</li> | DELETE | Delete product by id |

## Mongo Express
Mongo Express will run on "http://localhost:8081/". Under the collection called "product" we store the data as documents.
When visiting "http://localhost:8081/db/productDB/product", we will see:
![image](https://github.com/nhtY/Spring-Webflux-Raective-REST-API/assets/89942570/0a3805fc-49cc-42dc-96fe-7538e2bd0ede)

## Postman
As well as testing the app via code, functionality of the API is tested by using Postman:
![image](https://github.com/nhtY/Spring-Webflux-Raective-REST-API/assets/89942570/518d7b55-828c-477f-9393-9d7fdd1257cb)