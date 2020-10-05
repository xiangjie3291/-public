FROM openjdk:12
WORKDIR /app/
COPY ./* ./
RUN javac GetSym.java