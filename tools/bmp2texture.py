#!/usr/bin/env python3

import sys

def get_int16(data, index):
  return data[index] | ((data[index + 1]) << 8)

def get_int32(data, index):
  return data[index] | \
        (data[index + 1] << 8) | \
        (data[index + 2] << 16) | \
        (data[index + 3] << 24)

# ----------------------------- fold -----------------------------

if len(sys.argv) != 3:
  print("Usage: bmp2texture.py <filename> <1/RGBA>")
  sys.exit(0)

filename_bmp = sys.argv[1]
filename_texture = filename_bmp.replace(".bmp", "")

bits_per_pixel = 0

if sys.argv[2] == "1":
  bits_per_pixel = 1
  filename_texture += ".1"
elif sys.argv[2] == "16" or sys.argv[2].lower() == "rgba":
  bits_per_pixel = 16
  filename_texture += ".rgba"
else:
  print("Error: Invalid file type " + sys.argv[2] + ".. should be 1 or RGBA")
  sys.exit(0)

print("    bmp filename: " + filename_bmp)
print("texture filename: " + filename_texture)
print("  bits_per_pixel: " + str(bits_per_pixel))

count = 0

fp = open(filename_bmp, "rb")
out = open(filename_texture, "wb")

data = fp.read(128 * 1024)
bytes_read = len(data)

width = get_int32(data, 18)
height = get_int32(data, 22)
bmp_bits_per_pixel = get_int16(data, 28)
image_offset = get_int32(data, 10)
image_size = get_int32(data, 34)

print("Bytes read " + str(bytes_read))
print(" ---------- BMP File Header ----------")
print("          header: " + chr(data[0]) + chr(data[1]))
print("            size: " + str(get_int32(data, 2)))
print("        reserved: " + str(get_int16(data, 6)))
print("        reserved: " + str(get_int16(data, 8)))
print("          offset: " + str(image_offset))
print(" ---------- BMP Info Header ----------")
print("            size: " + str(get_int32(data, 14)))
print("           width: " + str(width))
print("          height: " + str(height))
print("    color planes: " + str(get_int16(data, 26)))
print("  bits per pixel: " + str(bmp_bits_per_pixel))
print("     compression: " + str(get_int32(data, 30)))
print("      image size: " + str(get_int32(data, 34)))
print("       horiz res: " + str(get_int32(data, 38)))
print("        vert res: " + str(get_int32(data, 42)))
print("  paletta colors: " + str(get_int32(data, 46)))
print("important colors: " + str(get_int32(data, 50)))

if bits_per_pixel == 1:
  if (width % 8) != 0:
    print("Width of bitmapped image must be a multiple of 8 bytes\n")
    sys.exit(1)
elif (width % 8) != 0:
  print("Width of image must be a multiple of 8 pixels to be a hardware")
  print("texture. The image can still be used as an RGBA for drawing.\n")

n = 0

if bits_per_pixel == 1:
  last = -1

  print("image_size=" + str(image_size))

  while n < image_size:
    pixel = 0

    for i in range(0, 8):
      r = (data[image_offset + n + 2]) >> 3
      g = (data[image_offset + n + 1]) >> 3
      b = (data[image_offset + n + 0]) >> 3
      n = n + 3

      pixel = pixel << 1
      if r != 0 or g != 0 or b != 0: pixel |= 1

    out.write(bytes([pixel]))

elif bits_per_pixel == 16:

  while n < image_size:
    r = (data[image_offset + n + 2]) >> 3
    g = (data[image_offset + n + 1]) >> 3
    b = (data[image_offset + n + 0]) >> 3
    n = n + 3

    pixel = (r << 11) | (g << 6) | (b << 1)

    out.write(pixel.to_bytes(2, byteorder='big'))

fp.close()
out.close()

