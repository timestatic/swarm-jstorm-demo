FROM ubuntu:xenial

MAINTAINER timestatic

USER  root

ENV JSTORM_HOME=/opt/jstorm
ENV PATH $PATH:$JSTORM_HOME/bin

ENV JAVA_HOME=/opt/jdk1.8.0_131
ENV PATH=$JAVA_HOME/bin:$PATH

ENV TZ=Asia/Shanghai

# install python
RUN apt-get update &&  \
    apt-get install -y  python && \
    apt-get autoremove && apt-get clean && rm -rf /var/lib/apt/lists/*

# install jdk
COPY jdk1.8.0_131/ /opt/jdk1.8.0_131

# install jstorm
COPY jstorm-2.2.1/ /opt/jstorm

WORKDIR $JSTORM_HOME

COPY docker-entrypoint.sh  /

# timezone
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
    # rm storm.yam
    rm -f $JSTORM_HOME/conf/storm.yaml && \
    chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
