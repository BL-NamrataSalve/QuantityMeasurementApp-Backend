import os
import re

backend_dir = r"d:\QuantityMeasurementApp\quantity-measurement-backend"
services = ["api-gateway", "auth-service", "conversion-service", "eureka-server"]

log4j2_xml_content = """<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
"""

def process_pom(pom_path, main_starter):
    if not os.path.exists(pom_path): return
    with open(pom_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    if "spring-boot-starter-log4j2" in content:
        return # already processed
        
    # Exclude logging from main starter
    exclusion = """
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>"""
    
    # regex to find the main starter dependency
    pattern = r"(<artifactId>" + main_starter + r"</artifactId>\s*)</dependency>"
    replacement = r"\1" + exclusion + r"\n        </dependency>"
    content = re.sub(pattern, replacement, content)
    
    # Add log4j2 dependency
    log4j2_dep = """
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
    </dependencies>"""
    
    # Need to replace the LAST occurrence of </dependencies> or just replace the string. 
    # Since pom.xml usually has one </dependencies> (or one in dependencyManagement), 
    # we should be careful. 
    # Let's split by </dependencies> and join.
    parts = content.rsplit("</dependencies>", 1)
    if len(parts) == 2:
        content = parts[0] + log4j2_dep + parts[1]
    
    with open(pom_path, 'w', encoding='utf-8') as f:
        f.write(content)

for service in services:
    pom_path = os.path.join(backend_dir, service, "pom.xml")
    if service == "api-gateway":
        starter = "spring-cloud-starter-gateway"
    elif service == "eureka-server":
        starter = "spring-cloud-starter-netflix-eureka-server"
    else:
        starter = "spring-boot-starter-web"
        
    process_pom(pom_path, starter)
    
    # write log4j2.xml
    res_dir = os.path.join(backend_dir, service, "src", "main", "resources")
    if os.path.exists(res_dir):
        with open(os.path.join(res_dir, "log4j2.xml"), "w", encoding="utf-8") as f:
            f.write(log4j2_xml_content)

print("Log4j2 implemented in all services.")
