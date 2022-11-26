
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class Nintendo64Demo
{
  public static void main(String[] args)
  {
    BillionDevices.run();
    TitleScreen.run();
    Bounce.run();
    ManyTriangles.run();
    //Credits.run();

    while (true)
    {
    }
  }
}

