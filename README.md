# spring-boot-reactive-api

Implements a WebFlux reactive API with functional routers Edit


## Routing

Routing is implemented as a `@Configuration` class that defines a `@Bean` `RoutingFunction` method.

## Handlers

Reactive Netty does not depend on any Servlet implementation, so we can simply declare the handler methods using 
`WebFlux` reactive types, mainly `Mono` and `Flux`
