# meeting room reservation system

This is my first project as a fresh graduate in 2009.

Background story, I was just reminiscing my past as a junior software developer starting to specialize myself in the Java ecosystem. As a computer science student, C/C++ was the main language. I studied and decided to specialize in java by myself. Obviously need to split my focus to learn the curriculum as well such as discrete math, DB, algebra, DSA, networking, and so on. Mixing up with PHP and VB 6 to complete some assignments as well.

My background until now is application developer. Dealing with CRUD/business application. I'm not a library author nor a DSA specialist. So reading through most of the discussion in [r/java](https://www.reddit.com/r/java/) by all the gurus often triggered my imposter syndrome.

With all that, if a junior java dev, in his first year of working experience (around 2010-2011), working in a non-tech company, with no mentoring from senior java dev (since senior java dev is non-existent there), no SCJP (Sun certified java program) since no money of course, able to build his first java web application with specs as below :

1.  pure raw servlet (understanding web.xml, servlet filter, servlet mapping)
2.  Monolithic. Well, it's kinda small by today's standard.
3.  Using tomcat. Obviously, I deployed it as WAR.
4.  Raw JDBC without connection pool. Always open and close connection per thread request. Commit and rollback bound to this cycle. Any exception that happened will be dispatched to the central exception handler for rollback transaction.
5.  Using prepared statements for speed but wasn't aware of SQL injection.
6.  no backend framework such as spring or struts. So I need to learn and build code infra myself.
7.  SPA web app using EXT JS. So I knew HTML/javascript and DOM manipulation to a certain extent. But I wasn't aware of the term SPA at that time.
8.  The source code is divided into 2 aspects. UI with all ext js, HTML pages, little bit JSP. And backend for servlet and java code.
9.  Login page is controlled by a servlet filter.
10.  One single servlet as the main controller (basic MVC). Obviously, only have a single endpoint over HTTP POST using a request/response body in the format of XML for ajax communication. Kinda RPC model. I don't even aware of REST at that time.
11.  Xpath for XML processing.
12.  Utilizing JODA library, JasperReports, iText, gson.
13.  Using oracle. With a lot of store procedures cross access different schema. Using Oracle Nested Tables. Not aware of optimizing through table index nor query optimization. Database view.
14.  Was using SVN for source control. Netbeans for IDE.
15.  Logging seems was using a mix of java.util.logging.Logger and println. No log4j nor logback.
16.  And a few compromises I made in order to complete it within a tight deadline.

All full-stack, single-handedly within 3 months, with minimal prior knowledge and learning all the stack a long the way.

If people were asking whether I manage to complete the project, yes it is completed.

But for internal quality, I think many people who understand gonna be pissed. Though business people don't care only until it causes problems later and even for that, it's not the technical thing that they care about.

It didn't use build tools like maven, not even ant. And I think I need to revise, I didn't use prep statements, it was using normal statements with string concat to construct the query.

I put many queries under js [https://github.com/andromezez/reservation/tree/main/web/store](https://github.com/andromezez/reservation/tree/main/web/store) , so it will be sent through the request body, and the backend only needs to run it. I asked my supervisor at that time whether the app will be exposed publicly or just intranet. Since they said only intranet, then I made that conscious compromise to meet the deadline. Not enough time to create proper request handlers for every case. Luckily no code review from them.
