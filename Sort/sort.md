**[정렬 | 시간복잡도 | 공간복잡도]**

### 버블 정렬 | O(n^2) | O(1)

![버블정렬](https://github.com/user-attachments/assets/b967c636-17d9-42e3-8500-d0df9dce062d)

: 인접한 원소끼리 비교하면서 뒤쪽이 더 크면 두 원소를 바꾼다.

→ 가장 큰 수부터 정렬이 완료된다.

### 선택 정렬 | O(n^2) | O(1)

![선택정렬](https://github.com/user-attachments/assets/78a1af76-372a-4637-9c19-a519943b06ec)

: 가장 작은 원소를 맨 앞으로 보내는 과정을 반복한다.

```java
for (int i=0; i<n; i++) {
	int min = i;
	for (int j=i+1; j<n; j++) {
		min = (arr[min] > arr[j])? j : min;
	}
	swap(i, min);
}
```

### 병합 정렬 | O(n log n) | O(?)

![병합정렬](https://github.com/user-attachments/assets/d42891a5-8f12-47a1-bd03-0f4714afa9b1)

: 절반 씩 나누어 각각 정렬하고 합쳐서 다시 정렬

→ 나뉘어진 두 배열 왼쪽과 오른쪽을 시작점부터 비교하며 더 작은 값을 새 배열에 넣는 방식


### 퀵 정렬 | 대부분 O(n log n), 최악 O(n^2) | O(log n)

![퀵정렬1](https://github.com/user-attachments/assets/dfab33a2-3c2a-4ad1-9a6e-c2e6dfcfe46a)

![퀵정렬2](https://github.com/user-attachments/assets/963e11e5-b99c-4009-9f9b-db07e79ed308)

: 한 원소(pivot)를 기준으로 배열을 분할하고 피봇은 그 중간으로 이동

→ 만약 pivot이 중간 값이 아니라 계속해서 min 또는 max면 최악


### 기수 정렬 | O(kn) *k=자릿수 개수, n=원소 개수

![기수정렬](https://github.com/user-attachments/assets/af4153a8-2198-4988-9c29-8fd00f421934)

: 각 자리를 순회하며 자릿값에 따라 나눈 그룹에서 또 다음 자리를 기준으로 정렬을 반복

- 데이터가 유한한 비트를 정렬할 때 사용 ex) 정수
