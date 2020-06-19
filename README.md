**Summary**

I have used springboot framework and implemented the task as an API to make it more detailed
Like the scan() method mentioned in the document would be responsible for keeping track or order items, The API can will order ids (If repeated will be considered as 2 Quantity)
I have used h2 in memory database to design the schema, You can see Entities directly for the mapping or goto localhost:8082/h2-console to view the schema
There is some dump data that is inserted before the application starts up which is in resources/data.sql

**Time Consumption Update**
```
I took 1.5 hour extra while doing this task

The extra time taken was utilized to simplify the price calculation formula

At first I added 2 methods for calculation of 
getPriceOfDiscounbtedProducts()
getProceForNonDiscountedProducts()

But I realized that this was not extensible, So i calculated the price for all items by standard formula

price = qty * unitPrice;

In this was i can extend the functionality if there are multiple discounts needs to be applied or if there is any other type of discounts applied in the future.
The formula for applying the discount after calculating the unti price was a little complicated and I wanted to simplify it and that too a little bit extra time on that front

If I summarize the extension and simplifying the discounting formula took a little bit extra time
```

**Notes**

```
I used a basic spring boot project (developed earlier) which includes some basic custom exceptions built earlier and which ran on port 8082, Also I there might be some edge cases on which i haven't focused heavily 

```


**API Request**
```
curl -X POST \
  http://localhost:8082/checkout \
  -H 'content-type: application/json' \
  -d '["001"]'
```
  
running the application (in the IDE): open and run the main class
  ```
  com.ecommerce.challenge.IdealoApplication
  ```
