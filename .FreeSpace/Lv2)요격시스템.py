def solution(targets):
    answer = 0
    targets.sort(key=lambda x: x[1])
    jump = 0

    for x in range(len(targets)):
        if jump > 0:
            jump -= 1
            continue
        lst = [targets[x]]

        for y in range(x + 1, len(targets)):
            if targets[y][0] + 0.5 > targets[x][1] - 0.5:
                break
            lst.append(targets[y])

        answer += 1
        jump = len(lst) - 1

    return answer
