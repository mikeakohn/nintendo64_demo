
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
    int rx = 0, ry = 0;
    int x = 160, y = 120, z = 256;
    int dx, dy;
    int screen = 0;
    int i;

    Triangle triangle = new Triangle();
    triangle.setVertex0(  0, -5, 0);
    triangle.setVertex1( -5,  5, 0);
    triangle.setVertex2(  5,  5, 0);
    triangle.setZBuffer(true);

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();
      Nintendo64.resetZBuffer();

      triangle.setRotation(rx, ry, 0);

      int coords = 0;
      int color = 0;

      for (i = 0; i < object.length; i++)
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

      //for (x = 0; x < 319; x++) { Nintendo64.plot(x, y, 0xf800); }
      Nintendo64.waitVsync();

/*
      y += dy;
      if (y == 130) { dy = -1; }
      if (y == 50)  { dy =  1; }
*/
      rotation[0] = (rotation[0] + 3) & 511;
      rotation[1] = (rotation[1] + 1) & 511;
      rotation[2] = (rotation[2] + 2) & 511;

      rx = (rx + 3) & 511;
      ry = (ry + 2) & 511;
      screen = (screen + 1) & 1;
    }
  }
}

