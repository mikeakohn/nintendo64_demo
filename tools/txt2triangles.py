#!/usr/bin/evn python3

import sys

if len(sys.argv) != 2:
  print("Usage: python3 txt2texture.py <filename>")
  sys.exit(0)

filename = sys.argv[1]

lines = [ ]
length = 0

fp = open(filename, "r")

for line in fp:
  line = line[:-1]
  lines.append(line)
  if len(line) > length: length = len(line)

fp.close()

print("Line length=" + str(length))

triangle_width = 10
spacing = triangle_width + 1
width = spacing * length
height = spacing * len(lines)

x0 = -(width / 2)
y = -(height / 2)

for line in lines:
  x = x0

  for i in range(0, len(line)):
    if line[i] != ' ':
      coord_x = str(int(x))
      coord_y = str(int(y))
      print("    %4s, %3s," % (coord_x, coord_y))

    x += spacing

  y += spacing

