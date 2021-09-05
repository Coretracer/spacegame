# no.navneet.spacegame

How to start the no.navneet.spacegame application
---

1. Run `mvn clean package` to build application
1. Start application with `java -jar target/no.navneet.spacegame-1.0-SNAPSHOT.jar server src/main/resources/config.yml`
1. To check that your application is running enter url `http://localhost:8080`
 

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`. Best way to check the code is by running `unit test case implemented the solution. There is dockers solution as well, but because of lack of time, I am not documenting it. 


Some choices
1. Why config.yml is inside resources' directory?: Doing such makes easy to create  docker image app via maven lib e.g. using `com.google.cloud.tools.jib-maven-plugin` lib. 


Assumptions 
---
1. Space and Alien has equal size of pixel in each row. 
2. Alien has equal does not have zero size row at the beginning or end. 
3. Space coordinate starts with 0,0 on top left.  

TODO:
---
1. Better design algorithm based on paper "On two-dimensional pattern matching by optimal parallel algorithms "
2. Lots of unit test case are missing. 
3. There is no test case for resources. I myself havn`t tested it. Hopefully it works. 
4. Write other missing things in this list :).  

Reference: .
---
1. Actually I had problem in understanding the case since the alien pattern is not contained in sample space. Quick google search shows exact problem in this github directory "https://github.com/LynxEyes/space_invaders"
2. I complied the code and took their "exact" match semantics. 
3. This code only implemet "exact" match based semantics. If you think , this is not intended, I can modify the semantics. 



