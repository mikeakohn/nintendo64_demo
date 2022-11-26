
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;

public class Credits
{
  public static short[] letters_java_grinder =
  {
    'J', 'A', 'V', 'A', ' ', 'G', 'R', 'I', 'N', 'D', 'E', 'R'
  };

  public static short[] letters_2022 =
  {
    '2', '0', '2', '2'
  };

  public static void run()
  {
    int screen = 0;

    Rectangle letter = new Rectangle();
    letter.setSize(16, 16);
    letter.setColor(0xff00ffff);
    letter.setTextureEnabled(16, 16);

    int start_k = 0;

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      drawText(letter, letters_java_grinder, 50, 40, start_k);
      drawText(letter, letters_2022, 120, 60, start_k);

      start_k = (start_k + 10) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }

  public static void drawText(
    Rectangle letter,
    short[] text,
    int pos_x,
    int pos_y,
    int start_k)
  {
    int k = start_k;

    for (int i = 0; i < text.length; i++)
    {
      char c = (char)text[i];

      if (c != ' ')
      {
        int offset_y = (int)(Math.sin512(k) * 15);

        Font.loadTexture(c, 0xffff, 0x3000);
        letter.setPosition(pos_x, pos_y + offset_y);
        letter.draw();
      }

      pos_x += 18;

      k = (k + 30) & 511;
    }
  }
}

