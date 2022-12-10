
import net.mikekohn.java_grinder.Math;

public class Matrix3D
{
  static void rotate(float[] coords, int[] rotation)
  {
    float x = coords[0];
    float y = coords[1];
    float z = coords[2];

    int rx = rotation[0];
    int ry = rotation[1];
    int rz = rotation[2];

    float t;

    float s, c;

    // [     1       0         0   ]   [ x ]
    // [     0   cos(rx)  -sin(ry) ] * [ y ]
    // [     0   sin(rx)   cos(ry) ]   [ z ]
    if (rx != 0)
    {
      c = Math.cos512(rx);
      s = Math.sin512(rx);

      t = (y * c) - (z * s);
      z = (y * s) + (z * c);
      y = t;
    }

    // [ cos(ry)     0     sin(ry) ]   [ x ]
    // [      0      1         0   ] * [ y ]
    // [ -sin(ry)    0     cos(ry) ]   [ z ]
    if (ry != 0)
    {
      c = Math.cos512(ry);
      s = Math.sin512(ry);

      t =  (x * c) + (z * s);
      z = -(x * s) + (z * c);
      x = t;
    }

    // [ cos(rz) -sin(rz)      0   ]   [ x ]
    // [ sin(rz)  cos(rz)      0   ] * [ y ]
    // [     0       0         1   ]   [ z ]
    if (rz != 0)
    {
      c = Math.cos512(rz);
      s = Math.sin512(rz);

      t = (x * c) - (y * s);
      y = (x * s) + (y * c);
      x = t;
    }

    coords[0] = x;
    coords[1] = y;
    coords[2] = z;
  }
}

