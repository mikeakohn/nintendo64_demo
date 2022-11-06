#!/usr/bin/env python3

import sys

def check_tag(tag, offset):
  if chr(wav[offset + 0]) != tag[0] or \
     chr(wav[offset + 1]) != tag[1] or \
     chr(wav[offset + 2]) != tag[2] or \
     chr(wav[offset + 3]) != tag[3]:
    return False

  return True


# ------------------------------- fold here -----------------------------

if len(sys.argv) != 3:
  print("Usage: wav2pcm.py <wavfile> <pcmfile>")
  sys.exit(0)

fp = open(sys.argv[1], "rb")
out = open(sys.argv[2], "wb")

wav = fp.read()

if not check_tag("RIFF", 0):
  print("Error: Not a wav file.")
  sys.exit(1)

if not check_tag("WAVE", 8):
  print("Error: Not a wav file.")
  sys.exit(1)

if not check_tag("fmt ", 12):
  print("Error: Not a wav file.")
  sys.exit(1)

offset = 12

while offset < len(wav):
  size = int.from_bytes(wav[offset + 4 : offset + 8], byteorder='little')

  if check_tag("data", offset):
    print("found " + str(offset) + " " + str(size))

    offset += 8
    end = offset + size

    while offset < end:
      data = int.from_bytes(wav[offset : offset + 2], byteorder='little')
      out.write(data.to_bytes(2, byteorder='big'))
      offset += 2

    break

  offset += 8 + size


fp.close()
out.close()

