# 도시 이음 프로젝트 (2023.11.15 ~ 2023.12.27)
휴먼교육선터 Final project

## 팀원
-팀장 : 오경환
-팀원 : 조세훈
-팀원 : 조선우
-팀원 : 송재훈
-팀원 : 박하민

## 주제
- 대주제 : 대중교통
- 소주제 : 따릉이의 이용현황에 따른 개선과 이용 방향성 발전
    - 따릉이 전체적인 소개
      - 따릉이의 이용현황 (연도별, 월별, 일별 대여량)
      - 따릉이의 이용평균연령
      - 따릉이의 지역별 이용현황 (카토그램)
    - 따릉이 세부 소개
      - 따릉이의 이용현황과 주위 환경(날씨, 도로, 인구수, 인프라)의 데이터 취합하여 비교분석
      - 취합데이터를 이용자(User)와 관리자(Admin)에게 가가 분리 제한/맞춤 데이터를 제공하여 사용편의성 향상
1. 소개
  최근들어서 주요대중교통인 지하철과 버스의 이용량의 지분이 조금더 편리하고 효율적인 따릉이의 사용율로 이동하는 현상이 늘었다.
따릉이의 사용량 증가에 따른 부가적으로 생기는 문제점과 수요증가 등을 분석하여 이용자와 관리자 모두에게 필요한 정보를 제공함으로써,
따릉이 사용에 개선점과 추가요소를 구상해 보게 한다.

2. 데이터 수집
  - 따릉이 데이터를 얻기 위해 공공 데이터 포털이나 따릉이 공식 사이트에서 데이터를 다운로드
  - 데이터는 대여/반납 위치, 시간, 이용자 정보 등을 포함

3. 데이터 전처리
  - 데이터를 탐색하고 불필요한 정보를 제거하며, 결측치나 이상치를 처리
  - 날짜 및 시간 데이터를 적절히 포맷팅하고, 필요한 변수를 추출

4. 시각화 및 탐색적 데이터 분석 (EDA):
  - Matplotlib, Seaborn, Plotly, heatmap, MapApi 등의 시각화 도구를 사용하여 데이터의 특성을 시각적으로 파악
  - 주요 통계량, 상관 관계, 분포 등을 확인하고 인사이트 도출을 위해 데이터를 탐색

5. 모델링 및 예측
  - 수요 예측 모델 또는 클러스터링 분석 등을 통해 따릉이 이용 패턴을 예측하거나 특정 패턴을 식별
  - 머신러닝 알고리즘 (회귀, 분류, 클러스터링 등)을 선택하고 모델을 훈련

⨀ 참고 자료
- Api
서울시 공공자전거 대여소 정보 : https://data.seoul.go.kr/dataList/OA-13252/F/1/datasetView.do
따릉이 고장내역 : https://data.seoul.go.kr/dataList/OA-15644/F/1/datasetView.do
따릉이 사용내역 : https://data.seoul.go.kr/dataList/OA-15182/F/1/datasetView.do
- Kaggle
  https://www.kaggle.com/code/aubertsigouin/bixi-network-analysis
