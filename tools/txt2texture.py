#!/usr/bin/evn python3

import sys

# rrrr_rggg_ggbb_bbba
colors = {
  ' ': 0x0001,
  'R': 0xf801,
  'G': 0x07c1,
  'B': 0x0031,
  'Y': 0xffc1,
  'P': 0xf83f,
}

# ------------------------- fold here ------------------------------

if len(sys.argv) != 2:
  print("Usage: python3 txt2texture.py <filename>")
  sys.exit(0)

if not sys.argv[1].endswith(".txt"):
  print("Error: Input filename should end in .txt")
  sys.exit(0)

filename_txt = sys.argv[1]
filename_raw = sys.argv[1].replace(".txt", ".raw")

fp = open(filename_txt, "r")
out = open(filename_raw, "wb")

for line in fp:
  line = line.strip()
  for i in range(0, len(line), 2):
    data = (colors[line[i]] << 16) | colors[line[i + 1]]
    out.write(data.to_bytes(4, 'big'))

fp.close()

