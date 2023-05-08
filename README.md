# java-was-2023

Java Web Application Server 2023

## 프로젝트 정보 

이 프로젝트는 우아한 테크코스 박재성님의 허가를 받아 https://github.com/woowacourse/jwp-was 
를 참고하여 작성되었습니다.

## 내용



## 알게 된 것들

- error: unmappable character (0xEC) for encoding x-windows-949 
  - 기본 양식에 존재하는 주석 처리된 내용을 인코딩하면서 에러 발생
  - (해결) IntelliJ 인코딩에서 project encoding 항목 x-windows-949 -> UTF-8로 변경
- logger debug 출력 시 아무 내용이 없는 ""를 출력할 경우 동작을 멈추고 다음 행이 실행되지 않음
  - (조치)&& line.length() != 0 조건을 추가하여 debug 내용이 ""일 경우 로그를 찍지 않도록 함
  - 다음 행이 실행되지 않는 이유는 아직 잘 모르겠음
- new File() 로 경로 지정 시 현재 경로가 아닌 프로젝트 경로가 기준이 됨
  - ex)webserver 패키지 RequestHandler에서 new File 로 resources 디렉토리에 있는<br>
  index.html 을 호출하려고 상대경로로 ../../resources ~ 지정했으나 기준은 프로젝트 경로기 때문에<br>
    ./src/main/resources/templates 이렇게 지정해줘야함.
  
