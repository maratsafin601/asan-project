## HTTP Methods

### GET

**GET** method is used to retrieve data from a server at the specified resource.

GET requests are the most common and widely used methods in APIs and websites. For example, say you have an API with a `/users` endpoint. Making a GET request to that endpoint should return a list of all available users. Since a GET request is only requesting data and not modifying any resources, it's considered a _safe_ and _idempotent_ method.

#### Testing an API with GET requests

### Save vs Idempotent HTTP methods

Safe HTTP method - is a method that does not modify resource. For instance, using GET on a resource URL, should NEVER change the resource.

Idempotent HTTP method - is a method that can be called many times without different outcomes. It would not matter if the method is called only once, or ten times over. The result should be the same. Again, this only applies to the result, not the resource itself.

## Data Formats

