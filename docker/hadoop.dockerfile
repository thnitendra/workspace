
# https://hub.docker.com/_/openjdk/
FROM openjdk

# https://www.tutorialspoint.com/hadoop/hadoop_enviornment_setup.htm
RUN cd ~
RUN eho "" | echo "" | echo "" | ssh-keygen -t rsa 
RUN cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys 
RUN chmod 0600 ~/.ssh/authorized_keys 

RUN su
RUN cd /usr/local 
RUN wget http://apache.claz.org/hadoop/common/hadoop-3.0.0/hadoop-3.0.0.tar.gz 
RUN tar xzf hadoop-3.0.0.tar.gz
RUN mv hadoop-3.0.0 to hadoop

ENV HADOOP_HOME /usr/local/hadoop 

RUN /usr/local/hadoop version

CMD ["/sbin/my_init"]


