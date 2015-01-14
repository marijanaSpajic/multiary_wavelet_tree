import time

file = open("input_10", "r")
lines = file.readline().strip()

dictionary = {}
sign = "A"
limit = 1000000
counter = 0

t = time.time()
for i in range(0,limit):
    if lines[i] == sign:
        counter += 1

print (time.time() - t)
print (counter)
