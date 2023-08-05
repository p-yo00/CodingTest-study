import math


def solution(users, emoticons):
    sale = [1 for i in range(len(emoticons))]

    def find1(lst, percent, target):
        for i in range(len(lst)):
            if lst[i] * min(sale[i], percent) >= target:
                return i
        return -1

    def find2(lst, percent, target):
        val = 0
        for i in range(len(lst)):
            val += lst[i] * min(sale[i], percent)
            if val >= target:
                return i
        return -1

    cnt = 0
    profit = 0
    temp = []

    for uid in range(len(users)):
        print("sale:", sale)
        print("temp:", temp)
        print("user정보\n", users[uid])
        percent = (100 - (users[uid][0])) * 0.01
        percent = math.floor(percent * 10) / 10
        print(percent)
        i = find1(emoticons, percent, users[uid][1])
        j = find2(emoticons, percent, users[uid][1])
        print("i", i)
        print("j", j)
        if i == -1 and j == -1:
            temp.append(percent)
            continue

        jprofit = 0
        for x in range(0, j + 1):
            jprofit += emoticons[x] * min(sale[x], percent)
        print("jp", jprofit)

        cnt += 1
        if (emoticons[i] * percent > jprofit) or (j == -1):
            print("뿅1")
            if len(temp) > 0 and (percent < min(temp)):
                if emoticons[i] * min(temp) >= users[uid][1]:
                    percent = min(temp)
            sale[i] = min(sale[i], percent)
            continue

        else:
            print("뿅2")
            for x in range(j, -1, -1):
                sale[x] = min(sale[x], percent)
                print("A")
                if len(temp) <= 0:
                    continue
                print("B")
                mintemp = min(temp)
                print(mintemp, sale[x])
                if sale[x] <= mintemp:
                    continue
                print("C")
                print(jprofit)
                print(jprofit - (emoticons[x] * sale[x] - emoticons[x] * mintemp))
                if jprofit - (emoticons[x] * sale[x] - emoticons[x] * mintemp) >= users[uid][1]:
                    jprofit = jprofit - (emoticons[x] * sale[x] - emoticons[x] * mintemp)
                    sale[x] = mintemp

    print(sale)
    for t in temp:
        for i in range(len(sale)):
            if sale[i] <= t:
                print("ADFD!!!")
                profit += emoticons[i] * sale[i]

    return [cnt, profit]