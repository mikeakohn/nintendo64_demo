
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

    // [     1       0         0   ]   [ x ]
    // [     0   cos(rx)  -sin(ry) ] * [ y ]
    // [     0   sin(rx)   cos(ry) ]   [ z ]
    if (rx != 0)
    {
      y = (y * Math.cos512(rx)) - (z * Math.sin512(rx));
      z = (y * Math.sin512(rx)) + (z * Math.cos512(rx));
    }

    // [ cos(ry)     0     sin(ry) ]   [ x ]
    // [      0      1         0   ] * [ y ]
    // [ -sin(ry)    0     cos(ry) ]   [ z ]
    if (ry != 0)
    {
      x =  (x * Math.cos512(ry)) + (z * Math.sin512(ry));
      z = -(x * Math.sin512(ry)) + (z * Math.cos512(ry));
    }

    // [ cos(rz) -sin(rz)      0   ]   [ x ]
    // [ sin(rz)  cos(rz)      0   ] * [ y ]
    // [     0       0         1   ]   [ z ]
    if (rz != 0)
    {
      x = (x * Math.cos512(rz)) - (y * Math.sin512(rz));
      y = (x * Math.sin512(rz)) + (y * Math.cos512(rz));
    }

    coords[0] = x;
    coords[1] = y;
    coords[2] = z;
  }
}

