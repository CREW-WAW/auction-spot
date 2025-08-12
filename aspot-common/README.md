# ASPOT Common Module

이 모듈은 ASPOT 프로젝트의 공통 기능을 제공합니다.

## 주요 기능

### 1. 공통 DTO
- `ApiResponse<T>`: 표준화된 API 응답 형식
- 모든 API에서 일관된 응답 구조 제공

### 2. 공통 예외 처리
- `BusinessException`: 비즈니스 로직 예외
- `GlobalExceptionHandler`: 전역 예외 처리

### 3. 공통 유틸리티
- `CommonUtils`: 문자열 처리, 날짜 포맷 등 유틸리티 메서드
- `CommonConstants`: 시스템 전반에서 사용되는 상수

### 4. 공통 설정
- 공통 상수 및 유틸리티 제공
- 표준화된 API 응답 및 예외 처리

## 사용법

### 의존성 추가
```gradle
implementation project(':aspot-common')
```

### API 응답 사용
```java
@GetMapping("/items")
public ApiResponse<List<ItemDto>> getItems() {
    List<ItemDto> items = itemService.getAllItems();
    return ApiResponse.success(items);
}
```

### 예외 처리 사용
```java
if (item == null) {
    throw new BusinessException("ITEM_NOT_FOUND", "Item not found with id: " + id);
}
```


