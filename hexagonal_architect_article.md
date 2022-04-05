# Hexagonal architecture and Domain Driven Design

## Teeny Introduction

In this article we will introduce a software architecture that implements the DDD (Domain Driven Design) introduced by
Eric Evan in the "Bleu Book" : _Domain-Driven Design: Tackling Complexity in the Heart of Software_

When we correlate DDD and Architecture, many of us think of Microservices. But this is not the only one implementation
for DDD concept.

Hexagonal Architecture is also a good candidate for implementing DDD correctly and it is a very good architecture in
general described by Robert C. Martin in this book: _Clean Architecture_

Moreover, in a microservice, we need to keep good practices and good architecture. Even though Hexagonal Architecture
was first designed for monolithic, we can apply it for build our microservice well.

## What we need for following this article :

- JDK 11 or earlier
- Maven 3+
- Any IDE or text editor

## What is DDD

![DDD](domain-driven-design-clean-architecture.png)

Like already explained above, The Domain Drive Design was introduce by Eric Evans in 2003. It is a Software engineering
approach that keep the domain in the center of our core software set. THe whole solution must revolve around the
business rules and its terminologies. We need to create a `Ubiquitous Language` between all stockholders (Domain team,
technical teams). Each terminology in this Language should be found in the application. For Exemple, if we are building
an application to track airplane's schedule, we attempt to found in the application Entity like "Airplane" or "Travel"
and Value Object like "Destination" or "Arrival".

For more information about DDD I advise you to read this free e-book
https://www.infoq.com/minibooks/domain-driven-design-quickly/

## Give me an Hexagone

Well, now what's the relationship with the Hexagonal Architecture ?

When you connect DDD with Architecture, the first likeness is Microservices Architecture. Therefore, each microservice
need to be build into the bounded context. For exemple, when you build an e-commerce, you will probably have one
microservice for account management, another for payment features and another for stock management. Every of them have
only one purpose who are named in domain bounded context. It's what make microservice a good candidate for DDD
implementation.

But it is not the only one. Like Mark Richards says about architecture decisions on his book _Fundamentals of Software
Architecture_: "It's always a trad-off". Indeed, all application not be created for become a microservice. For this type
of architecture, you need to have the ecosystem for managing (for exemple container management tools like Kubernetes or
OpenShift for exemple) and the need of great scalability. Each Architecture decision come with these cons. For
Microservices, reliability and performance can be a challenge.

And even if you choose this way, each microservice need to have their own internal architecture.

So, what is exactly Hexagonal Architecture ?

It's a software architecture introduced by Alistair Cockburn in 2005. It is also called " Port and Adapter
Architecture ". The goal is for all your code about domain should reside in the same place (or module) and all about
other technical purpose (like database access, APIs call or Framework like Spring in Java) need to be outside the
domain.

Like that, you can change any part of the technical detail without impact the domain.

The purpose of DDD is keep the focus on the Domain implementation and not worry about technical detail too soon.

Like explained in the schema below, we first have an application module (cf: app) who will use the Domain by his
interfaces. Domain implement this exposed interfaces with these services and if this need to use external concern, like
database access, it will use others interface, named port (outbound). The module that wants to be used by the Domain,
will have to implement this interface (adapter) and mappe data between him and Domain

![hexagonal-archi](hexagonal_architec_diagrame_1.png)

## Implementation

We will create a new Spring boot application with a very simple case for illustrate this architecture.

You'll found source in the
repository [Github](https://github.com/KevinDupeyrat/Hexagonal_Architecture_Article/tree/master/movies)

It's an application for retrieving movies information from an external public API.

we'll create a Maven project with `movies` parent and 3 submodules like above.

`````shell
movies
├───movies-app
├───movies-domain
└───movies-infra-api
`````

As you can see, we have one "main" module named `movies-app`, one Domain module named `movies-domain` and one `infra`
module named `movies-infra-api`. Each infra represent a detail about infrastructure like database access and external
api call.

Since the Domain only contains class with business Rules, it cannot run alone. It required some configuration and a main
method or other technical details for be used.

But the first rule of Hexagonal Architecture is clear :

- Domain depends on nothing. Everything depends on him.

The second is :

- You do not use an infra module directly, you have to pass only through domain module.

That's way, there is no direct dependency between two infra modules. No one has knowledge of the existence of an others

Therefore, how domain will be able to use infra-api if it not depends on them ?

## Dependencies rules

The pom.xml configuration will prevent us over accidentally coupling between modules.

For respect dependency rules for Hexagonal Architecture you need to have the dependencies part from pom.xml
of `movies-domain` like bellow :

````XML
...
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
        ...
````

As you can see, nothing about Spring, about persistence or external API too. It's an immutable rule, the domain depends on
nothing. Just what that needs for compile (here Lombok for exemple) or testing.

So how he can use other module ?

Domain will expose some Interfaces and each infra module will implement it. Like that the Domain just care about use his
interface and don't case about who and how it will be implemented.

For dependency of all infra, we'll have to importe the domain module with scope `provide` like below :

````XML
...
<dependencies>
    ...
    <dependency>
        <groupId>com.architecture.hexagonal.exemple</groupId>
        <artifactId>movies-domain</artifactId>
        <scope>provided</scope>
    </dependency>
    ...
</dependencies>
        ...
````

and for the application module `movies-app`, we'll need to importe domain and all other module like below:

````XML
...
<dependencies>
    <dependency>
        <groupId>com.architecture.hexagonal.exemple</groupId>
        <artifactId>movies-domain</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>com.architecture.hexagonal.exemple</groupId>
        <artifactId>movies-infra-api</artifactId>
        <scope>runtime</scope>
    </dependency>
    ...
</dependencies>
        ...
````

For all infra module we need to use `runtime` scope. That disable developers to accidentally use directly infra module
in other infra module.

Sometimes the infra module needs another one. To do this, it must go through the domain instead of using it directly. You should not find infra dependencies in other infra modules. It is the 'app' that will orchestrate all the infra
dependencies to make the link.

## Domain

So, we will start with the main important part of this project, the `Domain`. Before start to coding, we need to stop us
one minute for thinks about the `Ubiquitous Language` of our domain. In the real world, this part can take long times at
the start of the project and can evolve throughout the life circle of the project.

What does your application need to do ? Why creat it ?

The answer is : Movies

We want to retrieve Movies information like title, description, rate, director, actors ...

So let's start by creating our Ubiquitous Language that we must find in our application afterwards.

### Ubiquitous Language

As our business domain is very easy, it will be easy to create our UL quickly, but, in real situation, it may take time.
A few days or months before finding it and that every stakeholder approves and uses it.

In our case, it will be something like this :

- Movies : Represent a list of Movie
- Title  : The movie title
- Synopsys : The movie explanation with little synopsys
- Rate : The rate of the movie
- Director : Person who as create the movie
- Actors : Persons who as playing in the movie
- Release Date : Date of movie was released

the Rate need to be between 0 and 5

## Api Movies

For this purpose, we will use the open API [TheMovieDB](https://developers.themoviedb.org/3/getting-started/introduction),
with the library provided by __Holger Brandl__ in this [repository](https://github.com/holgerbrandl/themoviedbapi).


I already created a fake account for this article. The token is put in the `application-local.yml` of the project. If
the token is off, you can create an account and fetch a new token : https://www.themoviedb.org/signup

Once everything is good for this API subscription, you can directly run the Spring Boot application and test it.

## Expose our own APIs

Our API exposes 3 APIs which are mapped with Movie DB APIs. We map the data with our own data model.

- GET http://localhost:8080/api/v1/movies/populars
- GET http://localhost:8080/api/v1/movies/upcoming
- GET http://localhost:8080/api/v1/movies/{{id}}

The first one give you a liste of movies sorted by popularity, the second one give you the liste of movies who are
upcoming, dnd the last one, give you the detail about the movie with the id pass in path parameter.

## Architecture

For the architecture we obviously use the Hexagonal Architecture with [Apache Maven](https://maven.apache.org/) for create
module and build our application.

The configuration of the POM, as already explained above, respects the rules of hexagonal dependencies.

In the `movie-app` module we have the main class for run the Spring Boot application and the management of our little API
exposed.  
The schema represent the architecture for better readability of the project structure.

![hexagonal-archi](hexagonal_architec_diagrame_2.png)

We will explain each module bellow in more detail.

### Domain

In the domain module, we crate a package named `movies` who allow us to create different domain or subdomain part in the
same bounded context. Be worries about this contexte, you need to respect it for be sur to do not mixe different domains
who not allow to evolute together.  
In fact, in the same domain package you do not to find, for exemple, a domain for make coffee and another domain for
watch television.  
In this case you need to create two different domain artifact (.jar for Java).

In this domain module, we will found sub-packages to manage the module, for example, error package for exception handling, model package to represent business model.
In this template package, you need to find the ubiquitous language.

You have a service package too, who represent all the business logic. Everything your application needs to expose as
functionalities need to reside in this package.  
And for finish, whe have a `outbound` module. This module contains all the interfaces used by the `service` module. That
represent all functionalities not covered by the domain, like database access, api call ... It is these interfaces that
will be implemented by an external infra-module.

![domaine_module](domain_modulePNG.PNG)

### App

In the app module, we will found all you need for run your application. This is this module who orchestrate all other
module.  
In is POM configuration we need to found domain module who is provided directly and all infra module declared for be
used at runtime only.  
Like domain module, we found a package for error management (declaring and handling error).  
The configuration package gather all Spring Boot configuration, in our case, we have just a Bean Configuration for
declare domain module service (remember, the domain know nothing about Spring context).  
We found to the package for the Controller for our exposed APIs too.  
And obviously, we have the main class `DemoApplication` for run our Spring Boot application.

![app_module](app_modulePNG.PNG)

### Infra

Finally, we have the infra module who assemble all configuration and services for external API call.  
Under the configuration package we have all configuration about Spring and the library used for call TheMovieDB APIs.  
An usual error management package and Service package who represent the features needed by the domain for work
properly.  
The class `TheMovieDBService` implement the domain interface `MoviesProvider` found in the `outbound` domain package.

![infra_module](infra_modulePNG.PNG)

## Conclusion

I hope this article will help readers to understand the foundation of the clean architecture with implementation of the
DDD : Hexagonal Architecture.
Like is already explained in this article, the architecture choose is always a trad-off and Developers or Architectes
don't need to follow this implementation all the time.  
But, indeed, this architecture is a nice demonstration of a non coupling modules for better maintainability. We can
change any part of the application without scare about regression in other part.  
The Domain Driven Design is better than just a new fashion pattern to follow, it's a point of view of the whole software
development paradigm.   
The development Team need to be focused about Domain concern instead of be entertained by all new Software new tools who
evolve every day.   
When a Software team understand this, he can stand in front of any business rules challenge.
