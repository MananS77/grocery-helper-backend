[![Build Status](https://travis-ci.org/MananS77/grocery-helper-backend.svg?branch=master)](https://travis-ci.org/MananS77/grocery-helper-backend)   [![codecov](https://codecov.io/gh/MananS77/grocery-helper-backend/branch/master/graph/badge.svg)](https://codecov.io/gh/MananS77/grocery-helper-backend)

* [Introduction](#grocery-helper-backend-application "Goto Introduction")
* [API Reference](#below-apis-are-supported- "Goto API Reference")
* [Docker Build](#building-docker-image-and-running-it-on-container "Goto Docker Build/Pull Information")

# Grocery Helper Backend Application

The Grocery Helper application's backend is built on top of `Java 8`, by leveraging the `Spring Boot` framework.

To keep things simple (as specified in the assessment file), I have used `H2` database, and harnessed the power of `JPA`
to define the entity mappings.

There exists a `many-to-one` and `one-to-many` relationship between `Item` and `Category`.
For eg., one item will have one category (milk in dairy products), whereas one category can have multiple items inside
(Fruits have Apple, Banana, Peach and so on).

I've attempted to resolve this mapping by using the `@ManyToOne` and `@OneToMany` annotations on `Item` and `Category`
respectively. See code to understand this in detail.


## Below APIs are supported : 
#### GET /grocery/item ###
Request -
```
curl -X GET \
  http://localhost:8080/grocery/item \
  -H 'Content-Type: application/json'
```
Response -
```
[
    {
        "itemId": 1,
        "itemName": "Milk",
        "itemCategory": {
            "categoryId": 1,
            "categoryName": "Dairy Products"
        }
    },
    {
        "itemId": 2,
        "itemName": "Cottage Cheese",
        "itemCategory": {
            "categoryId": 1,
            "categoryName": "Dairy Products"
        }
    },
    {
        "itemId": 3,
        "itemName": "Potato",
        "itemCategory": {
            "categoryId": 2,
            "categoryName": "Vegetables"
        }
    },
    {
        "itemId": 4,
        "itemName": "Apple",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    },
    {
        "itemId": 5,
        "itemName": "Kiwi",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    }
]
```

#### GET /grocery/item/{id} ###
Request -
```
curl -X GET \
  http://localhost:8080/grocery/item/1 \
  -H 'Content-Type: application/json'
```
Response -
```
{
    "itemId": 1,
    "itemName": "Milk",
    "itemCategory": {
        "categoryId": 1,
        "categoryName": "Dairy Products"
    }
}
```

#### PUT /grocery/item ###
Request -
```
curl -X PUT \
  http://localhost:8080/grocery/item \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
	"itemName": "Mushroom",
	"itemCategory": {
            "categoryId": 2,
            "categoryName": "Vegetables"
    }
}'
```
Response -
```
Grocery Item added successfully
```

#### DELETE /grocery/item/{id} ###
Request -
```
curl -X DELETE \
  http://localhost:8080/grocery/item/3 \
  -H 'Content-Type: application/json'
```
Response -
```
Grocery Item deleted successfully
```

#### GET /grocery/category/items?categoryId={id} ####
Request -
```
curl -X GET \
  'http://localhost:8080/grocery/category/items?categoryId=3'
```
Response -
```
[
    {
        "itemId": 9,
        "itemName": "Kiwi",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    },
    {
        "itemId": 10,
        "itemName": "Peach",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    },
    {
        "itemId": 11,
        "itemName": "Banana",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    },
    {
        "itemId": 8,
        "itemName": "Apple",
        "itemCategory": {
            "categoryId": 3,
            "categoryName": "Fruits"
        }
    }
]
```

#### GET /grocery/category ###
Request -
```
curl -X GET \
  http://localhost:8080/grocery/category'
```
Response -
```
[
    {
        "categoryId": 1,
        "categoryName": "Dairy Products"
    },
    {
        "categoryId": 2,
        "categoryName": "Vegetables"
    },
    {
        "categoryId": 3,
        "categoryName": "Fruits"
    }
]
```

#### GET /grocery/category/{id} ###
Request -
```
curl -X GET \
  http://localhost:8080/grocery/category/2'
```
Response -
```
{
    "categoryId": 2,
    "categoryName": "Vegetables"
}
```

#### PUT /grocery/category ####
Request -
```
curl -X PUT \
  http://localhost:8080/grocery/category \
  -H 'Accept: */*' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -d '{
	"categoryName" : "Biscuits"
}'
```
Response -
```
Grocery Category added successfully
```


## Building Docker image and running it on container ##
#### Building a Docker image ####
```
docker build -t grocery-helper:latest ./docker
```

#### Running the docker image on the container ####
```
 docker run -d -p 8080:8080 grocery-helper:latest
 ```
 On successful execution you should see something like below:
 
 ```
 docker run -d -p 8080:8080 grocery-helper:latest
 eg62b7190344d852a039db988d2455e55caaf96464309a2513dac6e00736cf8e
 ```
 
## Pulling the Docker Image

I have published the Docker Image for the Grocery Helper application at 
`https://hub.docker.com/r/manans77/grocery-helper`

To pull the same, run the below command -
```
docker pull manans77/grocery-helper
```
