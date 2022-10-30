# BOJ 1445번 일요일 아침의 데이트

https://www.acmicpc.net/problem/1445

---

## 풀이
1. dfs? Node class로 쓰레기를 지나간 수, 옆을 지나간 수 저장

---

1. 입력을 받아 maps를 생성 
2. maps에서 쓰레기 주변을 찾아서 저장

모든 경로를 탐색하는 재귀방식의 dfs는 시간 초과

쓰레기를 최소로 지나가는 형태의 다익스트라로 해결

3. 다익스트라 Node class와 compare 구현



