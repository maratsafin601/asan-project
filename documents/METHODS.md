# HTTP Methods

This chapter defines the concept of five most common HTTP methods (aka verbs), which are `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. These methods are also known as **CRUD operations** - Create, Read, Update, Delete.

- [GET](#get)
- [POST](#post)
- [PUT](#put)
- [PATCH](#patch)
- [DELETE](#delete)


## GET

`GET` method is used to **RETRIEVE** the data from a server at the specified _resource_.

`GET` methods are the most common and widely used methods in APIs. Example: say, you have an API with a `/orders` _endpoint_. Making a `GET` _request_ to that endpoint should return a list of orders.

**Resource** - is an object with a type, associated data, relationships to other resources, and a set of methods that operate on it. Resource is the fundamental concept in the RESTful API, similar to an _Object_ in the Object-Oriented Programming or en _Entity_ in the Database. Resources can be grouped into _**collections**_. Example: `/users` - is a collection of resources (or a list of user objects), `/users/{id}` - is a single resource (one user object).

**Endpoint** - is an entry point to an API. _Endpoint_ indicates _where_ exactly a resource is located, it specifies the path to it, eg: `/users`, `/users/{id}`. While the _method_ indicates _how_ you can operate on that resource, it specifies the allowed interactions, eg: `GET`, `POST`, `PUT`, `DELETE`.

#### Testing `GET` endpoint

Positive test cases:
1. Validate that a valid `GET` request returns a `200` (`OK`) status code.
1. Verify that a valid `GET` request to a specific resource returns expected data. Returned data can be a single resource, or a collection of resources (a list of objects). For example, `GET` request to `/customers` endpoint returns a list of customers, request to `/customers/{id}` returns the specified customer.

Negative test cases:
1. Send `GET` request to a non-existing resource . Expected status code: `404` (`NOT_FOUND`).
1. Send `GET` request to a forbidden resource . Expected status code: `403` (`FORBIDDEN`).
1. Send `GET` request with a bad access token . Expected status code: `401` (`UNAUTHORIZED`).

## POST

`POST` method is used to **SEND** the data to a server to **CREATE** a resource.

The data sent to a server is stored in the _request body_ (also known as _payload_) or in _query parameters_ of the request. Example: contact form on a website. When you fill out the inputs in a form and hit "Send", that data is put in the request body of the `POST` request and sent to the server.

#### Testing `POST` endpoint

Positive test cases:
1. Validate that a valid `POST` request returns a `201` (`CREATED`) status code.
1. Verify that a valid `GET` request to a just created resource returns expected data and in the right format, required fields and values are correct and not missing.

Negative test cases:
1. Send `POST` request with a bad payload. Expected status code: `400` or `BAD_REQUEST`.
1. Send `POST` request to a forbidden resource . Expected status code: `403` or `FORBIDDEN`.
1. Send `POST` request with a bad access token . Expected status code: `401` or `UNAUTHORIZED`.

## PUT

`PUT` method is used to **SEND** the data to a server to **UPDATE** a resource.

**POST vs PUT**. The difference between `POST` and `PUT` requests is that PUT is _idempotent_. Calling the same `PUT` request multiple times will always produce the same result. In contrast, calling a `POST` request repeatedly have side effects of creating the same resource multiple times.

### Save vs Idempotent methods

Safe HTTP method - is a method that does not modify resource. For instance, using GET on a resource URL, should NEVER change the resource.

Idempotent HTTP method - is a method that can be called many times without different outcomes. It would not matter if the method is called only once, or ten times over. The result should be the same. Again, this only applies to the result, not the resource itself.

Since **GET** request is only requesting data and not modifying any resources, it's considered a safe and idempotent method.

**POST** request is non-idempotent. It mutates data on the backend server by creating or updating a resource.

## Data Formats

