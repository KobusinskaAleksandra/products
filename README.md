#Products

Application stored Products: name, current price and date of last update.

All required ports and specifications can be found in application properties file. 
Application runs in 3 profiles: dev, test, prod. If any profile-specific parameters are needed, those can be entered in separate properties file. Profile dev is set as default.

##Prerequisites
To successfully run the application you must have:
1. Maven
2. Java version **8**

Before running application please change default username and password (admin / admin) to proper values. Those details you can find in application.properties file.
All endpoints are protected with Basic Authorization. Login is necessary for accessing each endpoint.

##Overview

** PUT

Application allows to update product using PUT request to `/api/products/{id}`. 
No query parameters needed. 
Request body should be set as application/json in format:
	
	{
		"name":"product1",
		"currentPrice":1.5,
		"lastUpdated":"2019-09-01 12:30:00"
	}
	
No other body format is allowed. 


** POST

Application allows to enter new product using POST request to `/api/products`. 
No query parameters needed. 
Request body should be set as application/json in format:

	{
		"name":"product1",
		"currentPrice":1.5,
		"lastUpdated":"2019-09-01 12:30:00"
	}

OR
	
	{
		"name":"product1",
		"currentPrice":1.5
	}
	
lastUpdated value is not mandatory, if not given, timestamp of sending request will be added to product.


** GET 

Application allows to display all stored products with their details using GET endpoint `/api/products`

No other parameters needed, no body needed. Only GET request will be accepted. Response is in application/json format:

	[
		{
			"id":1,
			"name":"product1",
			"currentPrice":1.5,
			"lastUpdated":"2019-09-01 12:30:00"
		},
		{
			"id":2,
			"name":"product2",
			"currentPrice":2.6,
			"lastUpdated":"2019-09-01 12:30:00"
		}
		...
	]


And presents details: id, name, current price and last updated timestamp.

** GET 

Application allows to display one stored product with its details using GET endpoint `/api/products/{id}`

Parameter "id" is needed - only number format of this parameter is accepted. Only GET request will be accepted. Response is in application/json format:

	{
		"id":1,
		"name":"product1",
		"currentPrice":1.5,
		"lastUpdated":"2019-09-01 12:30:00"
	}


And presents details for product with given id. If product with this id doesn't exist, this information will be returned with 500 response status.

##Known Issues/Workarounds

##Author
- Aleksandra Kobusinska