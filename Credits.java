
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;

public class Credits
{
  public static short[] letters_java_grinder =
  {
    'J', 'A', 'V', 'A', ' ', 'G', 'R', 'I', 'N', 'D', 'E', 'R'
  };

  public static short[] letters_2023 =
  {
    '2', '0', '2', '3'
  };

  public static short[] letters_demo_by =
  {
    'D', 'E', 'M', 'O', ' ', 'B', 'Y'
  };

  public static short[] letters_music_by =
  {
    'M', 'U', 'S', 'I', 'C', ' ', 'B', 'Y'
  };

  public static short[] letters_michael_kohn =
  {
    'M', 'I', 'C', 'H', 'A', 'E', 'L', ' ', 'K', 'O', 'H', 'N'
  };

  public static short[] letters_www =
  {
    'W', 'W', 'W', '.', 'M', 'I', 'K', 'E', 'K', 'O', 'H', 'N', '.', 'N', 'E', 'T'
  };

  public static void run()
  {
    int screen = 0;

    Rectangle letter = new Rectangle();
    letter.setSize(16, 16);
    letter.setColor(0xff00ffff);
    letter.setTextureEnabled(16, 16);

    int start_k = 0;

    for (int count = 0; count < 240; count++)
    {
      Song.playNext();

      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      drawText(letter, letters_demo_by, 100, 70, start_k);
      drawText(letter, letters_michael_kohn, 50, 120, start_k);

      start_k = (start_k + 10) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }

    for (int count = 0; count < 240; count++)
    {
      Song.playNext();

      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      drawText(letter, letters_music_by, 100, 70, start_k);
      drawText(letter, letters_michael_kohn, 50, 120, start_k);

      start_k = (start_k + 10) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }

    for (int count = 0; count < 690; count++)
    {
      Song.playNext();

      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      drawText(letter, letters_java_grinder, 50, 40, start_k);
      drawText(letter, letters_2023, 120, 110, start_k);
      drawText(letter, letters_www, 20, 190, start_k);

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

