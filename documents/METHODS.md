# HTTP Methods

This chapter defines the concept of five most common HTTP methods (or HTTP verbs), which are `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. These methods are also known as **CRUD operations** - Create, Read, Update, Delete.

- [GET](https://github.com/maratsafin601/asan-project/blob/main/documents/METHODS.md#get)
- [POST](https://github.com/maratsafin601/asan-project/blob/main/documents/METHODS.md#post)
- [PUT](https://github.com/maratsafin601/asan-project/blob/main/documents/METHODS.md#put)
- [PATCH](https://github.com/maratsafin601/asan-project/blob/main/documents/METHODS.md#patch)
- [DELETE](https://github.com/maratsafin601/asan-project/blob/main/documents/METHODS.md#delete)

## GET

**`GET`** method is used to retrieve the data from a server at the specified _resource_.

`GET` methods are the most common and widely used methods in APIs and websites. For example, say you have an API with a `/users` endpoint. Making a `GET` request to that endpoint should return a list of all available users.

#### Testing `GET` endpoint

1. Check that a valid `GET` request returns a `200` status code.
1. Ensure that a `GET` request to a specific resource returns the correct data. For example, `GET` `/users` returns a list of users, `GET` `/users/{id}` returns a specified user.

## POST

**`POST`** method is used to send the data to the API server to create or update a _resource_.

The data sent to the server is stored in the _request body_ or _query parameters_ of the HTTP request. The simplest example is a contact form on a website. When you fill out the inputs in a form and hit "Send", that data is put in the response body of the request and sent to the server.

#### Testing `POST` endpoint

1. Create a resource with a POST request and ensure a 200 status code is returned.
1. Next, make a GET request for that resource, and ensure the data was saved correctly.
1. Add tests that ensure POST requests fail with incorrect or ill-formatted data.

### Save vs Idempotent methods

Safe HTTP method - is a method that does not modify resource. For instance, using GET on a resource URL, should NEVER change the resource.

Idempotent HTTP method - is a method that can be called many times without different outcomes. It would not matter if the method is called only once, or ten times over. The result should be the same. Again, this only applies to the result, not the resource itself.

Since **GET** request is only requesting data and not modifying any resources, it's considered a safe and idempotent method.

**POST** request is non-idempotent. It mutates data on the backend server by creating or updating a resource.

## Data Formats

