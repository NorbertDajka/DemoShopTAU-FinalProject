**Final project for Demo Shop Application testing**

**Short description**

This application is responsible for testing the main functionalities of the Demo Shop App.

-   Login
-   Cart
-   Wishlist
-   Search
-   Ordering

**Tech used:**

-   Java 19
-   Maven
-   Selenide Framework
-   TestNG
-   Allure Reporting

**Users:**

-   beetle
-   dino
-   locked
-   turtle

password for all users: choochoo

**Data Providers:**

-   User Data Provider
-   Keywords Data Provider
-   Product Data Provider

**Pages:**

-   Cart Page
-   Checkout Info Page
-   Checkout Summary Page
-   Complete Order Page
-   Demo Shop Page
-   Product Details Page
-   Wishlist Page
-   Other page components: header, footer, login modal

**How to run the tests**

git clone <https://github.com/NorbertDajka/DemoShopTAU-FinalProject.git>

**Execute the following CLI commands to:**

**Execute all tests**

-   mvn clean test

**Generate Report**

-   mvn allure:report

**Presenting the report**

-   mvn allure:serve
