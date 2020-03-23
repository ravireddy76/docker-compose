# Libraries
import matplotlib.pyplot as plt
import numpy as np

# w = 4
# h = 3
# d = 70
# plt.figure(figsize=(w, h), dpi=d)
# plt.axis([0, 5, 0, 5])
# x = [1, 2, 4]
# y = [2, 1, 3]

# plt.plot(x, y, "o")

rng = np.random.RandomState(0)
labelList = ['Air', 'O2', 'N', 'Co2', 'Co', 'Hy', 'Amn', 'So2']
labelIter = 0
for marker in ['d', '^', 'v', 's', '+', '<', 'x', '.']:
    plt.plot(rng.rand(5), rng.rand(5), marker,
             label=labelList[labelIter].format(labelList[labelIter]))
    labelIter = labelIter+1

plt.legend(numpoints=1)
plt.xlabel('Temperature')
plt.ylabel('Viscosity')
plt.xlim(100, 1000)
plt.ylim(0.5, 14)
plt.show()