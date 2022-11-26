
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class ManyTriangles
{
  public static void run()
  {
    int rz = 0;
    int screen = 0;
    int width = 50, height = 50, rect_x = 50, rect_y = 20;
    int width_delta = 1, height_delta = 1, rect_dx = 1, rect_dy = 1;

    Rectangle rectangle = new Rectangle();
    rectangle.setPosition(50, 20);
    rectangle.setSize(50, 50);
    rectangle.setTextureEnabled(32, 9);

    short[] texture = Memory.preloadShortArray("assets/java_texture.raw");
    Nintendo64.loadTexture(texture, 32, 9);

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      rectangle.setPosition(rect_x, rect_y);
      rectangle.setSize(width, height);
      rectangle.draw();

      // Calculate new rectangle size and position.
      width += width_delta;
      height += height_delta;
      rect_x += rect_dx;
      rect_y += rect_dy;

      if (width >= 80)  { width_delta = -1; }
      if (height >= 70) { height_delta = -1; }
      if (width <= 30)  { width_delta = 1; }
      if (height <= 35) { height_delta = 1; }

      if (rect_x >= 80)  { rect_dx = -1; }
      if (rect_y >= 100) { rect_dy = -1; }
      if (rect_x <= 40)  { rect_dx = 1; }
      if (rect_y <= 20)  { rect_dy = 1; }

      // Rotate the triangles.
      rz = (rz + 1) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }
}

