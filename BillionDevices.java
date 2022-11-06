
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

    Rectangle rectangle_java = new Rectangle();
    rectangle_java.setTextureEnabled(32, 23);

    short[] java = Memory.preloadShortArray("assets/java_32px.rgba");
    Nintendo64.loadTexture(java, 32, 23);

    // Full size: 50 x 35
    int size_x = 16;
    int size_y = 1;

    for (n = 0; n < 30 * 2; n++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      rectangle_1.draw();
      rectangle_2.draw();

      drawBillion();
      drawJavaGrinder();

      Nintendo64.waitForPolygon();
      Nintendo64.waitVsync();

      screen = (screen + 1) & 1;
    }

    for (n = 0; n < 34; n++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      rectangle_1.draw();
      rectangle_2.draw();

      //Nintendo64.loadTexture(java, 32, 23);
      rectangle_java.setPosition(160 - (size_x / 2), 100);
      rectangle_java.setSize(size_x, size_y);
      rectangle_java.draw();

      size_x += 1;
      size_y += 1;

      drawBillion();
      drawJavaGrinder();

      Nintendo64.waitForPolygon();
      Nintendo64.waitVsync();

      screen = (screen + 1) & 1;
    }

    short[] its_me_java = Memory.preloadShortArray("assets/its_me_java.pcm");

    Nintendo64.setAudioDACRate(93750000 / 11000);
    Nintendo64.setAudioBitRate(2);
    Nintendo64.playAudio(its_me_java, its_me_java.length * 2);

    for (n = 0; n < 30 * 5; n++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      rectangle_1.draw();
      rectangle_2.draw();

      //Nintendo64.loadTexture(java, 32, 23);
      rectangle_java.draw();

      drawBillion();
      drawJavaGrinder();

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

  public static void drawJavaGrinder()
  {
    int x, y;
    int ptr = 0;
    int index = 0;
    short[] java_grinder = Memory.preloadShortArray("assets/java_grinder.rgba");

    for (y = 0; y < 13; y++)
    {
      index = ((y + 168) * 320) + 65;

      for (x = 0; x < 190; x++)
      {
        Nintendo64.plot(index++, java_grinder[ptr++]);
      }
    }
  }
}

