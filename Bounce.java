
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Triangle;

public class Bounce
{
  public static float[] object =
  {
    -40,   0,   0,
     40,   0,   0,
      0, -40,   0,
      0,  40,   0,
      0,   0, -40,
      0,   0,  40,
  };

  public static int[] colors =
  {
    0xff00ffff,
    0x0000ffff,
    0x00ffffff,
    0x00ff00ff,
    0xffff00ff,
    0xffffffff,
  };

  public static void run()
  {
    float[] xyz = new float[3];
    int[] rotation = new int[3];
    int rx = 0, ry = 0, rz = 0;
    int x = 160, y = 120, z = 256;
    int dx = 1, dy = 1;
    int screen = 0;
    int i;

    Triangle triangle = new Triangle();
    triangle.setVertex0(  0, -5, 0);
    triangle.setVertex1( -5,  5, 0);
    triangle.setVertex2(  5,  5, 0);
    triangle.setZBuffer(true);

    Triangle triangle_1 = new Triangle();
    triangle_1.setVertex0(  0, -30, 0);
    triangle_1.setVertex1(-30,  30, 0);
    triangle_1.setVertex2( 30,  30, 0);
    triangle_1.setPosition(280, 50, 256 + 128 - 100);
    triangle_1.setColor(0xff00ffff);
    triangle_1.setZBuffer(true);

    Triangle triangle_2 = new Triangle();
    triangle_2.setVertex0(  0, -30, 0);
    triangle_2.setVertex1(-30,  30, 0);
    triangle_2.setVertex2( 30,  30, 0);
    triangle_2.setPosition(50, 180, 256 + 128 - 200);
    triangle_2.setColor(0x00ff00ff);
    triangle_2.setZBuffer(true);

    for (int count = 0; count < 600; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      Nintendo64.resetZBuffer();

      triangle.setRotation(rx, ry, 0);

      int coords = 0;
      int color = 0;

      for (i = 0; i < object.length; i = i + 3)
      {
        xyz[0] = object[coords + 0];
        xyz[1] = object[coords + 1];
        xyz[2] = object[coords + 2];

        Matrix3D.rotate(xyz, rotation);

        triangle.setPosition(
          (int)xyz[0] + x,
          (int)xyz[1] + y,
          (int)xyz[2] + z);
        triangle.setColor(colors[color]);
        triangle.draw();

         coords += 3;
         color += 1;
         if (color == 6) { color = 0; }
      }

      triangle_1.setRotation(0, 0, rz);
      triangle_1.draw();

      triangle_2.setRotation(rx, 0, rz);
      triangle_2.draw();

      x += dx;
      if (x == 265) { dx = -1; }
      if (x == 50)  { dx =  1; }

      y += dy;
      if (y == 180) { dy = -1; }
      if (y == 50)  { dy =  1; }

      rotation[0] = (rotation[0] + 3) & 511;
      rotation[1] = (rotation[1] + 1) & 511;
      //rotation[2] = (rotation[2] + 2) & 511;

      rx = (rx + 3) & 511;
      ry = (ry + 2) & 511;
      rz = (rz + 1) & 511;

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }
}

