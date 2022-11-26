
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class ManyTriangles
{
  public static void run()
  {
    int ry = 0, rz = 0;
    int screen = 0;
    int width = 50, height = 50, rect_x = 50, rect_y = 20;
    int width_delta = 1, height_delta = 1, rect_dx = 1, rect_dy = 1;
    int x, y;

    Rectangle rectangle = new Rectangle();
    rectangle.setPosition(50, 20);
    rectangle.setSize(50, 50);
    rectangle.setTextureEnabled(32, 9);

    short[] texture = Memory.preloadShortArray("assets/java_texture.raw");
    Nintendo64.loadTexture(texture, 32, 9);

    Triangle triangle = new Triangle();
    triangle.setVertex0(  0, -15, 0);
    triangle.setVertex1(-15,  15, 0);
    triangle.setVertex2( 15,  15, 0);

    Triangle triangle_1 = new Triangle();
    triangle_1.setVertex0(  0, -30, 0);
    triangle_1.setVertex1(-30,  30, 0);
    triangle_1.setVertex2( 30,  30, 0);
    triangle_1.setPosition(150, 100, 256 + 128 - 100);
    triangle_1.setColor(0xff00ffff);

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      int color_start = 0x00ffffff;
      int color;
      int rotation = rz;

      for (y = 0; y < 12; y++)
      {
        color = color_start;

        for (x = 0; x < 12; x++)
        {
          triangle.setPosition(x * 25 + 20, y * 18 + 18, 256 + 128 - 100);
          triangle.setRotation(0, 0, rotation);
          triangle.setColor(color);
          triangle.draw();

          color -= 0x00130000;
          //rotation = (rotation + 10) & 511;
        }

        color_start -= 0x00001200;
      }

      triangle_1.setRotation(0, ry, rz);
      triangle_1.draw();

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
      ry = (ry + 4) & 511;
      rz = (rz + 3) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }
}

