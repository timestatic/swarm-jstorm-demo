FROM tomcat:8-jdk8-corretto

USER root

ENV TZ=Asia/Shanghai

COPY jstorm-ui-2.2.1.war /usr/local/tomcat/webapps

COPY docker-entrypoint.sh  /

RUN mkdir ~/.jstorm && \
    mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/ROOT.old && \
    ln -s /usr/local/tomcat/webapps/jstorm-ui-2.2.1 /usr/local/tomcat/webapps/ROOT && \
    chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh", "run"]
