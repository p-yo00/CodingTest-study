def solution(users, emoticons):
    cand = [0 for i in range(len(emoticons))]
    temp_cand = [0 for i in range(len(emoticons))]

    def find1(lst, percent, temp_cand, target):
        temp = []
        for i in range(len(lst)):
            temp.append(lst[i] * (100 - temp_cand[i]) * 0.01)

        for i in range(len(lst)):
            if lst[i] * (100 - max(temp[i], cand)) * 0.01 >= target:
                return [i, temp[i]]
            elif lst[i] * (100 - max([i], percent)) * 0.01 >= target:
                return [i, temp[i]]
        return -1

    def find2(lst, percent, temp_cand, target):
        val = 0
        for i in range(len(lst)):
            val += lst[i] * (100 - max(cand[i], percent)) * 0.01
            if val >= target:
                return i
        return -1

    cnt = 0
    profit = 0

    for uid in range(len(users)):
        percent = users[uid][0]
        print(percent)
        if percent % 10 != 0:
            percent += (10 - percent % 10)
        print("percent", percent)

        i = find1(emoticons, percent, temp_cand, users[uid][1])
        j = find2(emoticons, percent, temp_cand, users[uid][1])
        if i == -1 and j == -1:
            for ei in range(len(emoticons)):
                temp_cand[ei] = percent
            print("temp_cand", temp_cand)
            continue

        jprofit = 0
        for x in range(0, j + 1):
            jprofit += emoticons[x] * (100 - max(cand[x], percent)) * 0.01
        # print("jp", jprofit)

        cnt += 1

        if (emoticons[i] * (100 - percent) * 0.01 > jprofit) or (j == -1):
            print("1")
            cand[i] = max(cand[i], percent)
            continue

        else:
            for x in range(j, -1, -1):
                print("2")
                cand[x] = max(cand[x], percent)

    print(cand)
    print(temp_cand)
    for i in range(len(cand)):
        print(i)
        print(cand[i])
        print(temp_cand[i])
        if cand[i] >= temp_cand[i]:
            profit += emoticons[i] * (100 - cand[i]) * 0.01

    return [cnt, profit]

