
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class TitleScreen
{
  public static short[] letters =
  {
    'J', 'A', 'V', 'A', ' ', 'G', 'R', 'I', 'N', 'D', 'E', 'R'
  };

  public static void run()
  {
    int y = 50, dy = 1, rz = 30;
    int letter_r = 0;
    int size_r = 0, size_rx = 1;
    int screen = 0;
    int x;

    Triangle triangle = new Triangle();
    triangle.setVertex0(  0, -30, 0);
    triangle.setVertex1(-30,  30, 0);
    triangle.setVertex2( 30,  30, 0);
    triangle.setPosition(150, 100, 256 + 128);
    triangle.setColor(0xff00ffff);

    Rectangle rectangle = new Rectangle();
    rectangle.setPosition(100, 100);
    rectangle.setSize(50, 50);
    rectangle.setColor(0x0000ffff);

    Rectangle letter = new Rectangle();
    letter.setPosition(100, 100);
    letter.setSize(50, 50);
    letter.setColor(0xff00ffff);
    letter.setTextureEnabled(16, 16);

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      rectangle.draw();
      triangle.setRotation(0, 0, rz);
      triangle.draw();

      int r0 = letter_r;
      int r1 = size_r;
      int rx = 1;

      for (int i = 0; i < letters.length; i++)
      {
        char c = (char)letters[i];

        if (c != ' ')
        {
          int k = r0;

          if (count > 600)
          {
            if (k > 256 || k < 20) { k = 256; }
          }

          int letter_x = (int)(Math.cos512(k) * 80) + 160;
          int letter_y = (int)(Math.sin512(k) * 80) + 100;
          int size = (int)(Math.cos512(r1) * 25);
          r1 += rx;
          if (r1 == 0) { rx = 1; }
          if (r1 == 90) { rx = -1; }

          Font.loadTexture(c, 0xffff, 0x3000);
          letter.setPosition(letter_x, letter_y);

          if (count < 300)
          {
            letter.setSize(16, 16);
          }
            else
          {
            letter.setSize(size, size);
          }

          letter.draw();
        }

        r0 = (r0 - 25) & 511;
      }

      letter_r = (letter_r + 3) & 511;
      size_r = size_r + size_rx;
      if (size_r == 0) { size_rx = 1; }
      if (size_r == 90) { size_rx = -1; }

      for (x = 0; x < 319; x++) { Nintendo64.plot(x, y, 0xf800); }
      Nintendo64.waitVsync();

      y += dy;
      if (y == 130) { dy = -1; }
      if (y == 50)  { dy =  1; }
      screen = (screen + 1) & 1;
      rz = (rz + 3) & 511;
    }
  }
}

