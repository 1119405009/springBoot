# 作者信息
MAINTAINER "1119405009@qq.com"

#java环境
ENV JAVA_HOME=/webApp/jdk1.8.0_202
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH=$PATH:$JAVA_HOME/bin

# 暴露8080端口
EXPOSE 8080

ADD  docker-0.0.1-SNAPSHOT.jar app.jarr

# 启动镜像自动运行程序
ENTRYPOINT ["java","-jar","/app.jar"]
