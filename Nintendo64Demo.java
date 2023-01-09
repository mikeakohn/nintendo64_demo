
import net.mikekohn.java_grinder.Nintendo64;
import net.mikekohn.java_grinder.n64.Rectangle;
import net.mikekohn.java_grinder.n64.Triangle;

public class Nintendo64Demo
{
  public static void main(String[] args)
  {
    Song.init();

    BillionDevices.run();
    TitleScreen.run();
    JavaTriangles.run();
    Bounce.run();
    JavaKong.run();
    ManyTriangles.run();
    YearTriangles.run();
    Credits.run();

    while (true)
    {
    }
  }
}

