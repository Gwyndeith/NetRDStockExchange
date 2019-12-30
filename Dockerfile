FROM openjdk:8
ADD target/netrd-stockexchange-spring-boot-mysql.jar netrd-stockexchange-spring-boot-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "netrd-stockexchange-spring-boot-mysql.jar"]