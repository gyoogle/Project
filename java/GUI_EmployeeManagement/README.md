## GUI_EmployeeManagement

GUI를 활용한 직원 관리 프로그램



##### 목표

- Singleton 디자인 패턴을 활용하여 안정적인 객체 생성
- Client와 Server 사이에서 IP/Port 연결을 통해 원활한 소켓 통신 구현
- Client 생성 시 Thread를 활용한 구현
- 프로그램 시각화를 위한 GUI의 JFrame 활용



##### 클래스 다이어그램

![í´ëì¤ë¤ì´ì´ê·¸ë¨.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/%ED%81%B4%EB%9E%98%EC%8A%A4%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.PNG?raw=true)



##### Employee

> 직원 정보 객체 : 번호, 이름, 직책, 거주지
>
> 정보 CRUD를 위한 getter와 setter 생성 및 서버 출력에 필요한 toString 메소드 오버라이드



##### IEmpMgr

> 구현이 필요한 메소드들을 작성한 인터페이스
>
> - load : emp.dat에 저장되어 있는 정보 가져오기
> - save : emp.dat에 save하여 기존 정보 갱신
> - add : 직원 정보 추가
> - search : 직원 정보 검색
> - update : 직원 정보 수정
> - delete : 직원 정보 삭제



##### EmpMgrEmpl

> 인터페이스를 구현한 클래스
>
> Singleton 디자인 패턴을 활용한 instance 메소드 구현



##### EmpClient

> 클라이언트를 Thread로 구현
>
> ip와 port를 지정하고, Socket 라이브러리 활용



##### EmpServer

> ServerSocket을 통한 서버 구현
>
> 지정된 포트로 직원 정보가 들어오면, console 창에 emp.dat에 저장된 모든 직원 정보 출력



##### EmpUI

> GUI를 통한 시각화하여 사용자에게 제공
>
> EmpMgrEmpl에서 구현한 메소드를 활용해 버튼 event 처리



##### RecordNotFoundException, DuplicateException

> 직원 정보를 찾지 못하거나, 이미 저장된 정보일 때 발생할 error 구현





#### 실행 화면

---

1. 서버 실행

   ![ìë²ìì.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/%EC%84%9C%EB%B2%84%EC%8B%9C%EC%9E%91.PNG?raw=true)

2. UI 실행

   ![UIì°ê²°ìë£.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/UI%EC%97%B0%EA%B2%B0%EC%99%84%EB%A3%8C.PNG?raw=true)

3. 연결된 UI 모습


   ![UIêµ¬ì±.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/UI%EA%B5%AC%EC%84%B1.PNG?raw=true)



   > Add, Update, Delete, Search 등 메소드를 실행할 때, 성공/실패 여부를 JLabel을 통해 보여주도록 구현



4. SendToServer 클릭 후의 서버 Console

   ![ìë²ìë°ì´í°ì¶ë ¥ìí©.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/%EC%84%9C%EB%B2%84%EC%97%90%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%B6%9C%EB%A0%A5%EC%83%81%ED%99%A9.PNG?raw=true)



5.  UI 종료 후 재시작

   ![ì¬ìììì ì¥ëìí©.PNG](https://github.com/kim6394/Project/blob/master/images/GUI/%EC%9E%AC%EC%8B%9C%EC%9E%91%EC%8B%9C%EC%A0%80%EC%9E%A5%EB%90%9C%EC%83%81%ED%99%A9.PNG?raw=true)


   재시작 후 최근에 저장된 emp.dat에서 불러온 정보가 출력됨



