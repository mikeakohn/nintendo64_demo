
import net.mikekohn.java_grinder.Math;
import net.mikekohn.java_grinder.Nintendo64;

public class Credits
{
  public static void run()
  {
    int screen = 0;

    for (int count = 0; count < 690; count++)
    {
      Nintendo64.setScreen(screen);
      Nintendo64.clearScreen();

      screen = (screen + 1) & 1;
      Nintendo64.waitVsync();
    }
  }
}

