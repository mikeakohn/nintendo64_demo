
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Triangle;

public class JavaTriangles
{
  static int[] points =
  {
    -121, -27,
    -110, -27,
     -99, -27,
     -44, -27,
      22, -27,
      55, -27,
     110, -27,
    -110, -16,
     -55, -16,
     -33, -16,
      22, -16,
      55, -16,
      99, -16,
     121, -16,
    -110,  -5,
     -66,  -5,
     -22,  -5,
      22,  -5,
      55,  -5,
      88,  -5,
     132,  -5,
    -143,   5,
    -110,   5,
     -66,   5,
     -55,   5,
     -44,   5,
     -33,   5,
     -22,   5,
      22,   5,
      55,   5,
      88,   5,
      99,   5,
     110,   5,
     121,   5,
     132,   5,
    -132,  16,
    -121,  16,
     -66,  16,
     -22,  16,
      33,  16,
      44,  16,
      88,  16,
     132,  16,
  };

  public static void run()
  {
    int screen = 0;
    int x, y;
    float[] xyz = new float[3];
    int[] rotation = new int[3];
    int mode = 0;
    //int ry = 0, rz = 0;

    Triangle triangle = new Triangle();
    triangle.setVertex0(  0, -5, 0);
    triangle.setVertex1( -5,  5, 0);
    triangle.setVertex2(  5,  5, 0);

    rotation[0] = 0;
    rotation[1] = 0;
    rotation[2] = 0;

    for (int count = 0; count < 770; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      for (int n = 0; n < points.length; n = n + 2)
      {
        xyz[0] = points[n + 0];
        xyz[1] = points[n + 1];
        xyz[2] = 0;

        Matrix3D.rotate(xyz, rotation);

        triangle.setPosition(
          (int)(160 + xyz[0]),
          (int)(120 + xyz[1]),
          (int)(256 + 128 - 100 + xyz[2]));
        triangle.setRotation(0, 0, 0);
        triangle.setColor(0x00ff00ff);
        triangle.draw();
      }

      // Rotate the triangles.
      if (count < 128)
      {
        rotation[1] = (rotation[1] + 4) & 511;
      }
        else
      if (count < 256)
      {
        rotation[2] = (rotation[2] + 4) & 511;
      }
        else
      {
        //rotation[0] = (rotation[0] + 1) & 511;
        rotation[1] = (rotation[1] + 2) & 511;
        rotation[2] = (rotation[2] + 4) & 511;
      }

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }
}

