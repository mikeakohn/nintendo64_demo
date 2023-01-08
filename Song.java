
import net.mikekohn.java_grinder.Memory;
import net.mikekohn.java_grinder.Nintendo64;

public class Song
{
  public static int offset;
  //public static final int dma_length = 16384 * 2;
  public static final int dma_length = 16000 * 2;

  public static void init()
  {
    offset = 0;
  }

  public static void playNext()
  {
    // Start song:
    short[] song = Memory.preloadShortArray("assets/java_kong.pcm");
    int byte_length = song.length * 2;

    if ((Nintendo64.getAudioStatus() & (1 << 30)) != 0) { return; }
    if ((Nintendo64.getAudioStatus() & (1 << 31)) != 0) { return; }
    //if ((Nintendo64.getAudioStatus() & 1) != 0) { return; }
    if (offset >= byte_length) { return; }

    int length = dma_length;

/*
    if (length > byte_length - offset)
    {
      length = byte_length - offset;
    }
*/

    Nintendo64.setAudioDACRate(93750000 / 11000);
    Nintendo64.setAudioBitRate(2);
    Nintendo64.playAudio(song, offset, length);

    offset += length;
  }
}

