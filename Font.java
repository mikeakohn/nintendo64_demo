
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;

public class Font
{
  // Index is:
  //  0: A   1: B   2: C   3: D   4: E   5: F   6: G   7: H
  //  8: I   9: J  10: K  11: L  12: M  13: N  14: O  15: P
  // 16: Q  17: R  18: S  19: T  20: U  21: V  22: W  23: X
  // 20: Y  21: Z  22: 0  23: 1  24: 2  25: 3  26: 4  27: 5
  // 28: 6  29: 7  30: 8  31: 9  32: .  33: !  34: ?  35: ' ' 
  public static void loadTexture(int index, int fg_color, int bg_color)
  {
    short[] font = Memory.preloadShortArray("assets/font.1");
    short[] texture = new short[16 * 16];

    // font.1 is 128x80 but stored bitmapped as a grid of short[] 8x80.
    int offset_x = index & 0x7;
    int offset_y = index >> 3;
    int next = 0;

    int ptr = (offset_y * (8 * 16)) + offset_x;

    for (int y = 0; y < 16; y++)
    {
      int value = font[ptr];

      for (int x = 0; x < 16; x++)
      {
        int color;

        if ((value & 0x8000) == 0)
        {
          color = bg_color;
        }
          else
        {
          color = fg_color;
        }

        //texture[next] = (short)((value & 0x8000) == 0 ? bg_color : fg_color);

        texture[next] = (short)color;

        value = value << 1;
        next++;
      }

      ptr += 8;
    }

    Nintendo64.loadTexture(texture, 16, 16);
  }

  public static void loadTexture(char value, int fg_color, int bg_color)
  {
    if (value >= 'A' && value <= 'Z')
    {
      loadTexture((int)(value - 'A'), fg_color, bg_color);
    }
      else
    if (value >= '0' && value <= '0')
    {
      loadTexture((int)(value - '0' + 26), fg_color, bg_color);
    }
      else
    if (value >= '0' && value <= '9')
    {
      loadTexture((int)(value - '0' + 26), fg_color, bg_color);
    }
      else
    if (value == '.')
    {
      loadTexture(32, fg_color, bg_color);
    }
      else
    if (value == '!')
    {
      loadTexture(33, fg_color, bg_color);
    }
      else
    if (value == '?')
    {
      loadTexture(34, fg_color, bg_color);
    }
      else
    if (value == ' ')
    {
      loadTexture(35, fg_color, bg_color);
    }

    return;
  }
}

