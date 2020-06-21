# How to use
1. Clone this repository
1. Open it using Intellij Idea.
1. Run `Main.java` class at `src/main/java/com/handy/sayurbox`
1. Visit `localhost:8000` to home endpoint
1. Please take a look at [API List](#api-list) section for the API list

# API List
There are several endpoints that can be used
1. `/` is for home endpoint, an instruction to read README.md
1. `/product/stock/lisit` to list products with its stock.
1. `/product/stock/reset` to reset all products' stock.
1. `/product/order/{product-name}/{quantity}/{customer-name}` to order product.
    - `{product-name}`: One of `APPLE`, `PAPAYA`, `MANGO`
    - `{qty}`: Integer value, for example `3`
    - `{customer-name}`: String value, for example `Budi`

# Testing
There are two ways to run test.
1. Run test class at `test` package, for example `ProductServiceTest`.
1. Or simply type `mvn test` at root project folder from terminal.

# Credits
This project is using
1. [`Maven`](https://maven.apache.org/) as project management tool.
1. [`Gson`](https://github.com/google/gson) to for an easier JSON parsing.