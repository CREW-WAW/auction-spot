FROM eclipse-temurin:21-jdk

WORKDIR /app

# Gradle 빌드 캐시 최적화를 위한 설정
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# 의존성 먼저 다운로드
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
COPY src ./src

# 애플리케이션 빌드
RUN ./gradlew build --no-daemon -x test

# 포트 설정 (애플리케이션마다 다를 수 있음)
EXPOSE 8082

 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/build/libs/aspot-location-0.0.1-SNAPSHOT.jar"]
