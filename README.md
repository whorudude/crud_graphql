## 1. docker-compose.yaml
Avand instalat dockerul, lansam file'ul docker-compose.yaml pentru a ridica instanta de PostgreSQL

## 2. rulam aplicatia
_src/main/java/org/example/crud_graphql/CrudGraphqlApplication.java_

## 3. rulam requesturile pentru a testa functionalul serviciului
pe urmatorul link putem initia requesturile catre serviciul nostru pentru a testa
>http://localhost:8080/graphiql?path=/graphql

exemple de request-uri posibile:

```graphql
query products{
  products {
    id
    name
    price
    amount
    onsite
    category
  }
}
query product{
  product(id: 1) {
    id
    name
    price
    amount
    onsite
    category
  }
}
mutation createProduct{
  createProduct(input: {
    name: "Smart Watch"
    price: 199.99
    amount: 20
    onsite: true
    category: ELECTRONICS
  }) {
    id
    name
    price
    amount
    onsite
    category
  }
}
mutation updateProduct{
  updateProduct(input: {
    id: 1
    name: "Smart Watch v2"
    price: 179.99
    amount: 15
    onsite: false
    category: ELECTRONICS
  }) {
    id
    name
    price
  }
}
mutation deleteProduct{
  deleteProduct(id: 1) {
    id
    name
  }
}

query filter{
  filterProducts(input: { category: CLOTHING, onsite: true }) {
    id
    name
  }
}

```