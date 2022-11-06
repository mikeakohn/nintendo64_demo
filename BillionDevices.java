
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class BillionDevices
{
  public static void run()
  {
    // 256x128: Full screen.
    int screen = 0;
    int n;

    // 256x108: White box.
    Rectangle rectangle_1 = new Rectangle();
    rectangle_1.setPosition(32, 56);
    rectangle_1.setSize(256, 108);
    rectangle_1.setColor(0xffffffff);

    // 256x20: 924a40 box.
    Rectangle rectangle_2 = new Rectangle();
    rectangle_2.setPosition(32, 56 + 108);
    rectangle_2.setSize(256, 20);
    rectangle_2.setColor(0x924a40ff);

    for (n = 0; n < 60 * 6; n++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      rectangle_1.draw();
      rectangle_2.draw();

      drawBillion();

      Nintendo64.waitForPolygon();
      Nintendo64.waitVsync();

      screen = (screen + 1) & 1;
    }
  }

  public static void drawBillion()
  {
    int x, y;
    int ptr = 0;
    int index = 0;
    short[] billion = Memory.preloadShortArray("assets/billion.rgba");

    for (y = 0; y < 18; y++)
    {
      index = ((y + 56) * 320) + 32;

      for (x = 0; x < 256; x++)
      {
        Nintendo64.plot(index++, billion[ptr++]);
      }
    }

  }
}
