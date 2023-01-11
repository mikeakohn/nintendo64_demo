
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Triangle;

public class YearTriangles
{
  static int[] points =
  {
    -148, -27,
    -137, -27,
    -126, -27,
    -115, -27,
     -60, -27,
     -49, -27,
     -38, -27,
       5, -27,
      16, -27,
      27, -27,
      38, -27,
      82, -27,
      93, -27,
     104, -27,
     115, -27,
    -104, -16,
     -71, -16,
     -27, -16,
      49, -16,
     126, -16,
    -137,  -5,
    -126,  -5,
    -115,  -5,
     -71,  -5,
     -27,  -5,
      16,  -5,
      27,  -5,
      38,  -5,
      93,  -5,
     104,  -5,
     115,  -5,
    -148,   5,
     -71,   5,
     -27,   5,
       5,   5,
     126,   5,
    -137,  16,
    -126,  16,
    -115,  16,
    -104,  16,
     -60,  16,
     -49,  16,
     -38,  16,
      16,  16,
      27,  16,
      38,  16,
      49,  16,
      82,  16,
      93,  16,
     104,  16,
     115,  16,
  };

  static int[] colors =
  {
    0x00ff00ff,
    0x00ff00ff,
    0x00ff00ff,
    0x00ff00ff,
    0x0000ffff,
    0x0000ffff,
    0x0000ffff,
    0xffff00ff,
    0xffff00ff,
    0xffff00ff,
    0xffff00ff,
    0xff00ffff,
    0xff00ffff,
    0xff00ffff,
    0xff00ffff,
    0x00ff00ff,
    0x0000ffff,
    0x0000ffff,
    0xffff00ff,
    0xff00ffff,
    0x00ff00ff,
    0x00ff00ff,
    0x00ff00ff,
    0x0000ffff,
    0x0000ffff,
    0xffff00ff,
    0xffff00ff,
    0xffff00ff,
    0xff00ffff,
    0xff00ffff,
    0xff00ffff,
    0x00ff00ff,
    0x0000ffff,
    0x0000ffff,
    0xffff00ff,
    0xff00ffff,
    0x00ff00ff,
    0x00ff00ff,
    0x00ff00ff,
    0x00ff00ff,
    0x0000ffff,
    0x0000ffff,
    0x0000ffff,
    0xffff00ff,
    0xffff00ff,
    0xffff00ff,
    0xffff00ff,
    0xff00ffff,
    0xff00ffff,
    0xff00ffff,
    0xff00ffff,
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
      Song.playNext();

      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      int color_index = 0;

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
        triangle.setRotation(0, 0, count & 511);
        triangle.setColor(colors[color_index]);
        triangle.draw();

        color_index++;
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

