
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;

public class JavaKong
{
  public static short[] letters_java_kong =
  {
    'J', 'A', 'V', 'A', ' ', 'K', 'O', 'N', 'G'
  };

  public static void run()
  {
    int screen = 0;
    short[] block = Memory.preloadShortArray("assets/block.rgba");
    short[] ladder = Memory.preloadShortArray("assets/ladder.rgba");
    short[] duke = Memory.preloadShortArray("assets/duke.rgba");
    int x, y, i;
    int duke_x = 50, duke_y = 190;
    int jump = 0;

    Rectangle rectangle = new Rectangle();
    rectangle.setSize(16, 16);
    rectangle.setTextureEnabled(16, 16);

    Rectangle rectangle_duke = new Rectangle();
    rectangle_duke.setSize(16, 19);
    rectangle_duke.setTextureEnabled(16, 19);

    Rectangle letter = new Rectangle();
    letter.setSize(16, 16);
    letter.setColor(0xff00ffff);
    letter.setTextureEnabled(16, 16);

    for (int count = 0; count < 360; count++)
    {
      Song.playNext();

      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      Nintendo64.loadTexture(ladder, 16, 16);

      y = 40;
      for (i = 0; i < 4; i++)
      {
        rectangle.setPosition(141, y);
        rectangle.draw();

        rectangle.setPosition(60, y + 60);
        rectangle.draw();

        rectangle.setPosition(238, y + 120);
        rectangle.draw();

        y = y + 16;
      }

      Nintendo64.loadTexture(block, 16, 16);

      x = 40;
      for (i = 0; i < 8; i++)
      {
        rectangle.setPosition(x, 40);
        rectangle.draw();

        x = x + 16;
      }

      x = 40;
      for (i = 0; i < 15; i++)
      {
        rectangle.setPosition(x, 100);
        rectangle.draw();

        rectangle.setPosition(x, 160);
        rectangle.draw();

        rectangle.setPosition(x, 210);
        rectangle.draw();

        x = x + 16;
      }

      if (count < 90)
      {
        drawText(letter, letters_java_kong, 100, 130);
      }
        else
      if (count < 137)
      {
        duke_x += 4;
      }
        else
      if (count < 150)
      {
        duke_y -= 4;
      }
        else
      if (count < 194)
      {
        duke_x -= 4;
      }
        else
      if (count < 209)
      {
        duke_y -= 4;
      }
        else
      if (count < 229)
      {
        duke_x += 4;
      }
        else
      if (count < 244)
      {
        duke_y -= 4;
      }
        else
      if (count < 285)
      {
        duke_x -= 2;
        duke_y = 20 - (int)(20 * Math.cos512(jump));
        jump += 3;
      }

      Nintendo64.loadTexture(duke, 16, 19);
      rectangle_duke.setPosition(duke_x, duke_y);
      rectangle_duke.draw();

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }

  public static void drawText(
    Rectangle letter,
    short[] text,
    int pos_x,
    int pos_y)
  {
    for (int i = 0; i < text.length; i++)
    {
      char c = (char)text[i];

      if (c != ' ')
      {
        Font.loadTexture(c, 0xf0ff, 0x0000);
        letter.setPosition(pos_x, pos_y);
        letter.draw();
      }

      pos_x += 18;
    }
  }
}

