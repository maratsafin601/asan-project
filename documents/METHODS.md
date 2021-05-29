# HTTP Methods

This chapter defines the concept of five most common HTTP methods (aka verbs), which are `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. These methods are also known as **CRUD operations** - Create, Read, Update, Delete.

- [GET](#get)
- [POST](#post)
- [PUT](#put)
- [PATCH](#patch)
- [DELETE](#delete)


## GET

`GET` method is used to **retrieve** the data from a server at the specified _**resource**_.

`GET` methods are the most common and widely used methods in APIs. Example: say, you have an API with a `/orders` endpoint. Making a `GET` request to that endpoint should return a list of orders.

**Resource** - is an object with a type, associated data, relationships to other resources, and a set of methods that operate on it. Resource is the fundamental concept in the RESTful API, similar to an _Object_ in the Object-Oriented Programming or en _Entity_ in the Database. Resources can be grouped into _**collections**_. Example: `/users` - is a collection of resources (list of user objects), `/users/{id}` - is a single resource (one user object).

#### Testing `GET` endpoint

Positive test cases:
1. Validate that a valid `GET` request returns a `200` or `OK` status code.
1. Verify that a valid `GET` request to a specific resource returns expected data. Returned data can be a single resource, or a collection of resources. For example, `GET` request to `/customers` endpoint returns a list of customers, to `/customers/{id}` - a specified customer profile.

Negative test cases:
1. Send `GET` request to a non-existing resource . Expected status code: `404` or `NOT_FOUND`.
1. Send `GET` request to a forbidden resource . Expected status code: `403` or `FORBIDDEN`.
1. Send `GET` request with a bad access token . Expected status code: `401` or `UNAUTHORIZED`.

## POST

`POST` method is used to **send** the data to a server to create a _resource_.

The data sent to a server is stored in _request body_ (also known as a _payload_) or in _query parameters_ of the HTTP request. Example: contact form on a website. When you fill out the inputs in a form and hit "Send", that data is put in the request body of the `POST` request and sent to the server.

#### Testing `POST` endpoint

Positive test cases:
1. Validate that a valid `POST` request returns a `201` or `CREATED` status code.
1. Verify that a valid `GET` request to a just created resource returns expected data in the right format, fields and values are correct and not missing.

Negative test cases:
1. Send `POST` request with a bad payload. Expected status code: `400` or `BAD_REQUEST`.
1. Send `POST` request to a forbidden resource . Expected status code: `403` or `FORBIDDEN`.
1. Send `POST` request with a bad access token . Expected status code: `401` or `UNAUTHORIZED`.

## PUT

`PUT` method is used to **send** the data to a server to update a _resource_.

### POST vs PUT

The difference between `POST` and `PUT` requests is that PUT is idempotent. Calling the same PUT request multiple times will always produce the same result. In contrast, calling a POST request repeatedly have side effects of creating the same resource multiple times.

### Save vs Idempotent methods

Safe HTTP method - is a method that does not modify resource. For instance, using GET on a resource URL, should NEVER change the resource.

Idempotent HTTP method - is a method that can be called many times without different outcomes. It would not matter if the method is called only once, or ten times over. The result should be the same. Again, this only applies to the result, not the resource itself.

Since **GET** request is only requesting data and not modifying any resources, it's considered a safe and idempotent method.

**POST** request is non-idempotent. It mutates data on the backend server by creating or updating a resource.

## Data Formats

